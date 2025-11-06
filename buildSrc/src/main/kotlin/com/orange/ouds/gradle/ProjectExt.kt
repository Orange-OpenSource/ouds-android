/*
 * Software Name: OUDS Android
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
 */

package com.orange.ouds.gradle

import com.github.jknack.handlebars.Helper
import com.google.auth.oauth2.GoogleCredentials
import org.gradle.api.GradleException
import org.gradle.api.Project
import org.gradle.api.logging.LogLevel
import org.gradle.api.provider.ValueSource
import org.gradle.api.provider.ValueSourceParameters
import org.gradle.process.ExecOperations
import se.bjurr.gitchangelog.api.GitChangelogApi
import se.bjurr.gitchangelog.api.GitChangelogApi.gitChangelogApiBuilder
import se.bjurr.gitchangelog.api.model.Changelog
import se.bjurr.gitchangelog.api.model.Commit
import se.bjurr.gitchangelog.api.model.Tag
import se.bjurr.gitchangelog.internal.semantic.ConventionalCommitParser
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import javax.inject.Inject

/**
 * Returns the value of the given property or null if not found.
 * This is a typed version of the findProperty method on Project instances.
 *
 * @param T The type of the property.
 * @param propertyName The name of the property.
 * @return The value of the property, possibly null or null if not found.
 */
inline fun <reified T> Project.findTypedProperty(propertyName: String): T? {
    return findProperty(propertyName) as? T
}

inline fun <reified T> Project.requireTypedProperty(propertyName: String): T {
    return findTypedProperty(propertyName) ?: throw GradleException("Please set the \"$propertyName\" project property.")
}

fun Project.execute(
    executable: String,
    vararg args: String,
    logLevel: LogLevel = LogLevel.DEBUG,
    onComplete: (output: String) -> ExecuteResult = { ExecuteResult(it, null) }
): String {
    val formattedArgs = args.joinToString(" ") { if (it.contains(" ")) "\"$it\"" else it }
    logger.lifecycle("\u001B[38;2;255;121;0;1m$executable $formattedArgs\u001B[0m")

    val provider = providers.of(ExecuteValueSource::class.java) {
        parameters.executable = executable
        parameters.args = args.toList()
    }

    val result = onComplete(provider.get())
    logger.log(logLevel, result.formattedOutput)
    result.exception?.let { throw it }

    return result.formattedOutput
}

/**
 * Launches cURL with the specified arguments and throws an exception if the received HTTP status code is invalid.
 * When an invalid HTTP status code is received, cURL exits with code 0 and the task succeeds.
 * This method is used to make the task fail when HTTP status code is invalid.
 *
 * @param args The cURL arguments.
 * @return The cURL output.
 */
fun Project.curl(vararg args: String): String {
    // Add an argument to write the HTTP status code at the end of the output
    return execute("curl", *args, "-w", "\n%{http_code}", logLevel = LogLevel.LIFECYCLE) { output ->
        // Retrieve the HTTP status code
        val splitOutput = output.split("\n")
        val httpStatusCode = splitOutput.last().toInt()
        // Reconstruct output without the status code
        val outputWithoutStatus = splitOutput.dropLast(1).joinToString("\n")
        val exception = if (httpStatusCode < 200 || httpStatusCode >= 300) GradleException("Received HTTP error code $httpStatusCode.") else null
        ExecuteResult(outputWithoutStatus, exception)
    }
}

/**
 * Generates and returns the release notes starting from the specified Git revision.
 *
 * @param since The Git revision where the release notes should start from.
 * @return The release notes.
 */
fun Project.generateReleaseNotes(since: String?): String {
    val args = mutableListOf("log", "--pretty=format:- %s")
    if (!since.isNullOrEmpty()) {
        args += "${since}..HEAD"
    }
    val log = execute("git", *args.toTypedArray())

    // Remove useless lines
    val emptyLineRegex = Regex("(?m)^\t*\r?\n")

    return log.replace(emptyLineRegex, "").trim()
}

/**
 * Finds and returns the last Git tag matching a given pattern.
 *
 * @param pattern The pattern which the tag should match.
 * @param before If not null, the research will only take into account tags before this one.
 * @param isAnnotated Indicates if search should be performed through annotated or lightweight tags.
 * @return
 */
fun Project.findLastTag(pattern: String, before: String?, isAnnotated: Boolean): String? {
    val tags = execute("git", "tag", if (isAnnotated) "--sort=-*committerdate" else "--sort=-committerdate")

    // Add multiline option to pattern
    val regex = Regex(pattern, RegexOption.MULTILINE)
    var results = regex.findAll(tags).map { it.value }.toList()

    if (before != null) {
        val index = results.indexOf(before)
        if (index >= 0) {
            results = results.drop(index + 1)
        }
    }

    return results.firstOrNull()
}

fun <T> Project.gitHubRestApi(action: GitHubRestApi.() -> T): T {
    val token = Environment.getVariables("GITHUB_TOKEN").first()
    return GitHubRestApi(token, "Orange-OpenSource/ouds-android").action()
}

fun <T> Project.gitHubGraphQLApi(action: GitHubGraphQLApi.() -> T): T {
    val token = Environment.getVariables("GITHUB_TOKEN").first()
    return GitHubGraphQLApi(token, "Orange-OpenSource", "ouds-android").action()
}

fun <T> Project.firebaseApi(appId: String, action: FirebaseApi.() -> T): T {
    val serviceAccountFilePath = Environment.getVariables("GOOGLE_SERVICE_ACCOUNT_FILE_PATH").first()
    val serviceAccount = FileInputStream(serviceAccountFilePath)
    val credentials = GoogleCredentials.fromStream(serviceAccount).createScoped("https://www.googleapis.com/auth/cloud-platform")
    credentials.refresh()
    val accessToken = credentials.accessToken.tokenValue
    return FirebaseApi(accessToken, "756919609448", appId).action()
}

fun <T> Project.sonatypeOssrhStagingApi(action: SonatypeOssrhStagingApi.() -> T): T {
    val token = Environment.getVariables("CENTRAL_PUBLISHER_PORTAL_TOKEN").first()
    return SonatypeOssrhStagingApi(token).action()
}

val Project.artifactId: String
    get() = "ouds-$name"

val Project.isPublished: Boolean
    get() = extensions.findByType(MavenCentralPublishPluginExtension::class.java)?.enabled == true

val Project.isSnapshot: Boolean
    get() = version.toString().endsWith("SNAPSHOT")

class ExecuteResult(val formattedOutput: String, val exception: Throwable?)

private abstract class ExecuteValueSource : ValueSource<String, ExecuteValueSourceParameters> {

    @get:Inject
    abstract val execOperations: ExecOperations

    override fun obtain(): String {
        val output = ByteArrayOutputStream()
        execOperations.exec {
            standardOutput = output
            executable = parameters.executable
            args = parameters.args
        }

        return output.toString()
    }
}

private interface ExecuteValueSourceParameters : ValueSourceParameters {
    var executable: String
    var args: List<String>
}

private const val CHANGELOG_UNRELEASED_TAG_NAME = "Unreleased"

fun Project.updateChangelog(version: String?) {
    gitChangelogApi(version) {
        val changelogHeader = """
            |# OUDS Android library changelog
            |
            |All notable changes done in OUDS Android library will be documented in this file.
            |
            |The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
            |and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).
            |
            |
        """.trimMargin()

        val untaggedName = version ?: CHANGELOG_UNRELEASED_TAG_NAME
        val gitChangelogApi = withUntaggedName(untaggedName) // Group unreleased commits under the new version tag
            .withIgnoreTagsIfNameMatches("^refs/tags/(ci|sprint-demo)/.*") // Ignore CI and sprint demo tags
            .withTemplatePath("${rootProject.projectDir}/CHANGELOG.mustache") // Use a Mustache template to generate changelog
            .withHandlebarsHelper("commitDescriptionWithPullRequestUrl", Helper<Commit> { commit, options ->
                // This Handlebars helper returns an enriched commit description
                // It searches for a pull request number in the commit description and replace it with a link to the pull request on GitHub
                val commitDescription = ConventionalCommitParser.commitDescription(commit.message)
                return@Helper commitDescription.replace("\\(#(\\d+)\\)$".toRegex()) { matchResult ->
                    val pullRequestNumber = matchResult.groupValues[1]
                    val changelog = options.get<Changelog>("root")
                    val pullRequestUrl = "https://github.com/${changelog.ownerName}/${changelog.repoName}/issues/${pullRequestNumber}"
                    "([#$pullRequestNumber]($pullRequestUrl))"
                }
            })
            .withHandlebarsHelper("tagChangesUrl", Helper<Tag> { tag, options ->
                // This Handlebars helper returns a GitHub URL which lists the changes for the tag
                val changelog = options.get<Changelog>("root")
                val baseUrl = "https://github.com/${changelog.ownerName}/${changelog.repoName}"
                val previousTag = changelog.tags
                    .filter { it.name != untaggedName && (it.tagTimeLong < tag.tagTimeLong || tag.name == untaggedName) }
                    .maxByOrNull { it.tagTimeLong }
                val referenceName = if (tag.name == CHANGELOG_UNRELEASED_TAG_NAME) "develop" else tag.name
                return@Helper if (previousTag != null) {
                    "$baseUrl/compare/${previousTag.name}...$referenceName"
                } else {
                    "$baseUrl/tree/$referenceName"
                }
            })

        val changelog = changelogHeader + gitChangelogApi.render().trim().replace("&#x60;", "`")
        File("${rootProject.projectDir}/CHANGELOG.md").writeText(changelog)
    }
}

fun <T> Project.gitChangelogApi(version: String? = null, action: GitChangelogApi.() -> T): T {
    // GitChangelogApi does not take release tags into account when working on develop or on a feature branch because they are on the main branch.
    // Thus nextSemanticVersion does not return the correct value.
    // That's why we copy the release tags locally on their associated parent commits located on the develop branch.
    // These tags are prefixed by "changelog/" and are removed once the action on GitChangelogApi is finished.
    addChangelogTags(version)
    return gitChangelogApiBuilder()
        .withFromRepo(project.rootDir)
        .withSemanticMajorVersionPattern("BREAKING CHANGE")
        .withSemanticMinorVersionPattern("^feat")
        .withSemanticPatchVersionPattern("^fix")
        .action()
        .also { deleteChangelogTags() }
}

private fun Project.addChangelogTags(version: String?) {
    // Get release tags
    val tags = execute("git", "--no-pager", "tag")
        .trim()
        .split("\n")
        .filter { !it.startsWith("ci/") && !it.startsWith("changelog/") }
    tags.forEach { tag ->
        // Get the second parent commit SHA which is supposed to be on develop
        val parentCommitSha = execute("git", "--no-pager", "log", "-n 1", "--pretty=%P", tag)
            .trim()
            .split(" ")
            .getOrNull(1)
        if (parentCommitSha != null) {
            // Add an identical tag prefixed with "changelog/" on the parent commit and use "--force" in case it already exists
            execute("git", "tag", "changelog/$tag", parentCommitSha, "--force")
        }
    }

    // withUntaggedName(version) should be enough to display unreleased commits under the new version tag,
    // but doing so, the date is missing because there is no associated tag.
    // That's why we also add a temporary tag for the new version.
    if (version != null) {
        execute("git", "tag", "changelog/$version", "HEAD", "--force")
    }
}

private fun Project.deleteChangelogTags() {
    execute("git", "--no-pager", "tag")
        .trim()
        .split("\n")
        .filter { it.startsWith("changelog/") }
        .forEach { execute("git", "tag", "--delete", it) }
}

val Project.releaseVersion: String?
    get() = if (gradle.startParameter.taskNames.contains("prepareRelease")) {
        gradle.startParameter.projectProperties["version"] ?: run { gitChangelogApi { nextSemanticVersion.toString() } }
    } else {
        null
    }

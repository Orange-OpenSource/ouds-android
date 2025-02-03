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

import com.github.jknack.handlebars.Helper
import com.orange.ouds.gradle.artifactId
import com.orange.ouds.gradle.execute
import com.orange.ouds.gradle.isPublished
import com.orange.ouds.gradle.requireTypedProperty
import se.bjurr.gitchangelog.api.GitChangelogApi
import se.bjurr.gitchangelog.api.GitChangelogApi.gitChangelogApiBuilder
import se.bjurr.gitchangelog.api.model.Changelog
import se.bjurr.gitchangelog.api.model.Commit
import se.bjurr.gitchangelog.api.model.Tag
import se.bjurr.gitchangelog.internal.semantic.ConventionalCommitParser

plugins {
    id("se.bjurr.gitchangelog.git-changelog-gradle-plugin")
}

tasks.register<DefaultTask>("prepareRelease") {
    doLast {
        val version = project.gradle.startParameter.projectProperties["version"] ?: run { createGitChangelogApi().nextSemanticVersion.toString() }
        updateVersion(version)
        updateVersionCode()
        updateChangelog(version)
    }
}

tasks.register<DefaultTask>("tagRelease") {
    doLast {
        val tag = version.toString()
        execute("git", "tag", tag)
        execute("git", "push", "origin", tag)
    }
}

fun updateVersion(version: String) {
    File("gradle.properties").replace("(version=).*".toRegex()) { matchResult ->
        "${matchResult.groupValues[1]}$version"
    }
}

fun updateVersionCode() {
    val versionCodeRegex = "(versionCode = project.findTypedProperty<String>\\(\"versionCode\"\\)\\?\\.toInt\\(\\) \\?: )(\\d+)".toRegex()
    File("app/build.gradle.kts").replace(versionCodeRegex) { matchResult ->
        val versionCode = matchResult.groupValues[2].toInt() + 1
        "${matchResult.groupValues[1]}$versionCode"
    }
}

fun updateChangelog(version: String) {
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

    val gitChangelogApi = createGitChangelogApi()
        .withUntaggedName(version) // Group unreleased commits under the new version tag
        .withIgnoreTagsIfNameMatches("^refs/tags/ci/.*") // Ignore CI tags
        .withTemplatePath("CHANGELOG.mustache") // Use a Mustache template to generate changelog
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
                .filter { it.name != version && (it.tagTimeLong < tag.tagTimeLong || tag.name == version) }
                .maxByOrNull { it.tagTimeLong }
            return@Helper if (previousTag != null) {
                "$baseUrl/compare/${previousTag.name}...${tag.name}"
            } else {
                "$baseUrl/tree/${tag.name}"
            }
        })

    val changelog = changelogHeader + gitChangelogApi.render().trim()
    File("CHANGELOG.md").writeText(changelog)
}

tasks.register<DefaultTask>("testSonatypeRepository") {
    doLast {
        val sonatypeRepositoryId = project.requireTypedProperty<String>("sonatypeRepositoryId")

        // Add Sonatype Maven repository in root build.gradle.kts file
        File("settings.gradle.kts").replace("(\\s*)mavenCentral\\(\\)".toRegex()) { matchResult ->
            val indent = matchResult.groupValues[1]
            "${matchResult.value}${indent}maven(url = \"https://oss.sonatype.org/content/repositories/comorange-$sonatypeRepositoryId\")"
        }

        val publishedSubprojects = rootProject.subprojects.filter { it.isPublished }
        val nonPublishedSubprojects = rootProject.subprojects.filter { !it.isPublished }
        publishedSubprojects.forEach { publishedSubproject ->
            // Remove published Android Studio modules from settings.gradle.kts
            File("settings.gradle.kts").replace("include\\(\":${publishedSubproject.name}\"\\)(\\n)?".toRegex(), "")

            // Replace project dependencies with artifact dependencies in build.gradle.kts files of non published modules
            nonPublishedSubprojects.forEach { nonPublishedSubproject ->
                File("${nonPublishedSubproject.name}/build.gradle.kts").replace(
                    "implementation\\(project\\(\":${publishedSubproject.name}\"\\)\\)".toRegex(),
                    "implementation(\"com.orange.ouds.android:${publishedSubproject.artifactId}:$version\")"
                )
            }
        }
    }
}

private fun createGitChangelogApi(): GitChangelogApi {
    return gitChangelogApiBuilder()
        .withFromRepo(project.rootDir)
        .withSemanticMajorVersionPattern("BREAKING CHANGE")
        .withSemanticMinorVersionPattern("^feat")
        .withSemanticPatchVersionPattern("^fix")
}

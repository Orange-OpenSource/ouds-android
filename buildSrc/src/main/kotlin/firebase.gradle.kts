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

import com.orange.ouds.gradle.findLastTag
import com.orange.ouds.gradle.findTypedProperty
import com.orange.ouds.gradle.generateReleaseNotes
import com.orange.ouds.gradle.gitHubApi

object AppDistribution {

    const val releaseNotesFilePath = "./app_distribution_release_notes.txt"
}

internal val releaseNotesExtraKey = "releaseNotesExtraKey"

internal val Project.appDistributionVariants: List<String>
    get() = findTypedProperty<String>("appDistributionVariants")
        .orEmpty()
        .split(",")
        .filter { it.isNotEmpty() }

/**
 * An array containing tasks to execute for Firebase App Distribution deployment
 */
internal val appDistributionTasks: List<Task>
    get() = appDistributionVariants.map { variant -> tasks["appDistributionUpload${variant.replaceFirstChar { it.uppercaseChar() }}"] }

/**
 * The git tag prefix for Firebase App Distribution uploads
 */
internal val Project.gitTagPrefix: String
    get() = findTypedProperty<String>("appDistributionGitTagPrefix") ?: "app-distribution"

/**
 * Uploads APK and associated release notes to Firebase App Distribution
 */
tasks.register<DefaultTask>("appDistributionUpload") {
    dependsOn(
        // Assemble and generate release notes first
        *appDistributionVariants.map { variant -> tasks["assemble${variant.replaceFirstChar { it.uppercaseChar() }}"] }.toTypedArray(),
        tasks["generateAppDistributionReleaseNotes"]
    )

    doLast {
        // If release note is empty, do not upload APK
        val releaseNotes = tasks["generateAppDistributionReleaseNotes"].extra[releaseNotesExtraKey] as? String
        if (releaseNotes == null || releaseNotes.isEmpty()) {
            appDistributionTasks.forEach { it.enabled = false }
            tasks["gitTagAppDistribution"].enabled = false
        }
    }

    finalizedBy(
        // These tasks will only be executed if the release notes is not empty
        // Upload APK and then add a tag in order to generate appropriate release notes when uploading next APK
        *appDistributionTasks.toTypedArray(),
        tasks["gitTagAppDistribution"]
    )
}

/**
 * Generates a release notes for Firebase App Distribution
 */
tasks.register<DefaultTask>("generateAppDistributionReleaseNotes") {
    doLast {
        // Generate release notes
        val customReleaseNotes = findTypedProperty<String>("customReleaseNotes")
        var releaseNotes = customReleaseNotes ?: run {
            // Retrieve latest Firebase App Distribution tag
            // Firebase App Distribution tags are not annotated
            val lastTag = findLastTag("^$gitTagPrefix-.*$", null, false)
            if (lastTag != null) {
                println("Found last App Distribution tag with prefix \"$gitTagPrefix\": \"$lastTag\".")
            } else {
                println("Could not find an App Distribution tag with prefix \"$gitTagPrefix\".")
            }
            generateReleaseNotes(lastTag)
        }
        val maximumLength = 16 * 1024
        if (releaseNotes.length > maximumLength) {
            val truncationSymbol = "..."
            releaseNotes = releaseNotes.take(maximumLength - truncationSymbol.length) + truncationSymbol
        }

        if (releaseNotes.isEmpty()) {
            println("Generated App Distribution release notes is empty.")
        } else {
            println("Generated App Distribution release notes:\n${releaseNotes.replace("(?m)^".toRegex(), " * ")}")
        }

        // Create a file and export release notes as an extra property
        File(AppDistribution.releaseNotesFilePath).apply {
            writeText(releaseNotes)
        }
        extra.set(releaseNotesExtraKey, releaseNotes)
    }
}

/**
 * Adds a Firebase App Distribution tag to the remote git repository
 * This tag is used to know what is the latest commit to be included in the next release notes
 */
tasks.register<DefaultTask>("gitTagAppDistribution") {
    onlyIf {
        // Do not execute this task if any Firebase App Distribution upload failed or if appDistributionVariants is empty
        val uploadFailed = appDistributionTasks.any { it.state.failure != null }
        !uploadFailed && appDistributionTasks.isNotEmpty()
    }

    mustRunAfter(*appDistributionTasks.toTypedArray())

    doLast {
        val sha = findTypedProperty<String>("appDistributionGitTagSha").orEmpty()
        val tag = "${gitTagPrefix}-${sha.take(7)}"
        gitHubApi {
            createTag(tag, sha)
        }
    }
}

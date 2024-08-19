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

import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.qrcode.QRCodeWriter
import com.orange.ouds.gradle.Environment
import com.orange.ouds.gradle.FirebaseAppDistributionRelease
import com.orange.ouds.gradle.findLastTag
import com.orange.ouds.gradle.findTypedProperty
import com.orange.ouds.gradle.firebaseApi
import com.orange.ouds.gradle.generateReleaseNotes
import com.orange.ouds.gradle.gitHubApi
import java.util.EnumMap
import kotlin.io.path.Path

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
        val branchName = Environment.getVariables("GITHUB_REF").first().removePrefix("refs/heads/")
        var releaseNotes = if (branchName == "develop") {
            // Retrieve latest Firebase App Distribution tag
            // Firebase App Distribution tags are not annotated
            val lastTag = findLastTag("^$gitTagPrefix-.*$", null, false)
            if (lastTag != null) {
                println("Found last App Distribution tag with prefix \"$gitTagPrefix\": \"$lastTag\".")
            } else {
                println("Could not find an App Distribution tag with prefix \"$gitTagPrefix\".")
            }
            generateReleaseNotes(lastTag)
        } else {
            gitHubApi {
                val pullRequests = getPullRequests()
                val pullRequest = pullRequests.firstOrNull { it.branchName == branchName }
                pullRequest?.let { "${pullRequest.title} (#${pullRequest.number})" }.orEmpty()
            }
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

/**
 * Publishes a comment in the pull request that includes a QR code to download and install the OUDS Playground app.
 * That app contains the changes of the pull request.
 */
tasks.register<DefaultTask>("publishAppDistributionQrCode") {
    mustRunAfter("appDistributionUpload")

    doLast {
        firebaseApi("1:756919609448:android:08045c141d8ea56d54f3dd") {
            // Find App Distribution release corresponding to version code
            val versionCode = Environment.getVariables("GITHUB_RUN_NUMBER").first().toInt()
            val release = getAppDistributionReleases().firstOrNull { it.buildVersion == versionCode }
            if (release != null) {
                println("Found App Distribution release with version code $versionCode.")
                gitHubApi {
                    // Find pull request for current branch
                    val pullRequests = getPullRequests()
                    val branchName = Environment.getVariables("GITHUB_REF").first().removePrefix("refs/heads/")
                    val pullRequest = pullRequests.firstOrNull { it.branchName == branchName }
                    if (pullRequest != null) {
                        println("Found pull request #${pullRequest.number} for branch $branchName.")
                        // Generate QR code with download URL of App Distribution release
                        val qrCode = generateQrCode(release)

                        // There is now way to attach a file to a comment in an issue or pull request using the GitHub API
                        // A workaround is to add the QR code to the repository and add a link in the comment which references the QR code on the repository
                        println("Add QR code for '${pullRequest.title} (#${pullRequest.number})' to repository.")
                        // Add QR code to repository on 'qrcodes' branch
                        val sha = createFile(
                            qrCode,
                            "qrcodes/${qrCode.name}",
                            "Add QR code for '${pullRequest.title} (#${pullRequest.number})'",
                            "qrcodes"
                        )

                        // Add a comment with a link to the QR code in the repository
                        println("Add comment with QR code to '${pullRequest.title} (#${pullRequest.number})'.")
                        val link = "![qrcode](https://github.com/Orange-OpenSource/ouds-android/raw/$sha/qrcodes/${qrCode.name})"
                        val comment =
                            "Flash the QR code below to download and install the OUDS Playground app which contains the changes of this pull request:\\n$link"
                        // Although we use the "issues/{issue_number}/comments" GitHub API, this will comment the pull request
                        // The "pulls/{pull_number}/comments" is used to add review comments on a pull request
                        commentIssue(pullRequest.number, comment)
                    } else {
                        println("Could not find a pull request for branch $branchName.")
                    }
                }
            } else {
                println("Could not find an App Distribution release with version code $versionCode.")
            }
        }
    }
}

private fun generateQrCode(release: FirebaseAppDistributionRelease): File {
    val size = 256
    val bitMatrix = QRCodeWriter().encode(
        release.binaryDownloadUri,
        BarcodeFormat.QR_CODE,
        size,
        size,
        EnumMap<EncodeHintType, Int>(EncodeHintType::class.java)
    )
    val qrCodeFilePath = "./${release.buildVersion}.png"
    MatrixToImageWriter.writeToPath(bitMatrix, "PNG", Path(qrCodeFilePath))

    return File(qrCodeFilePath)
}

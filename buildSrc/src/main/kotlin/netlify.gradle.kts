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

import com.orange.ouds.gradle.Environment
import com.orange.ouds.gradle.execute
import com.orange.ouds.gradle.gitHubRestApi
import com.orange.ouds.gradle.requireTypedProperty

// This string is prepended to the Netlify comment and is used to retrieve and update the comment instead of creating another one
private val netlifyCommentPreamble = "<!-- Netlify comment -->"

tasks.register<DefaultTask>("publishDocumentationToNetlify") {
    doLast {
        val documentationPath = requireTypedProperty<String>("documentationPath")
        val netlifySiteId = requireTypedProperty<String>("netlifySiteId")
        val netlifyToken = requireTypedProperty<String>("netlifyToken")
        val commitSha = requireTypedProperty<String>("commitSha")

        val args = mutableListOf("netlify", "deploy", "--dir", documentationPath, "--site", netlifySiteId, "--auth", netlifyToken)
        if (Environment.branchName == "develop") {
            args += "--prod"
        }

        val output = try {
            execute("npx", *args.toTypedArray(), logLevel = LogLevel.LIFECYCLE)
        } catch (exception: ProcessExecutionException) {
            logger.lifecycle(exception.stackTraceToString())
            null
        }

        var exception = if (output == null) GradleException("Netlify deploy failed.") else null

        val outputLines = output.orEmpty()
            .replace("\\x1B\\[([0-9]{1,3}(;[0-9]{1,2};?)?)?[mGK]".toRegex(), "") // Removes ANSI colors from output
            .split("\n")

        // Output displays "Draft URL" for draft deployments:
        //
        // Draft URL: <https://69ca52507ba84a48e5405841--ouds-android.netlify.app>
        //
        // Build logs: <https://app.netlify.com/projects/ouds-android/deploys/69ca52507ba84a48e5405841>
        // Function logs: <https://app.netlify.com/projects/ouds-android/logs/functions?scope=deploy:69ca52507ba84a48e5405841>
        // Edge function logs: <https://app.netlify.com/projects/ouds-android/logs/edge-functions?scope=deployid:69ca52507ba84a48e5405841>
        // 
        // and "Unique deploy URL" for prod deployments:
        //
        // Production URL: <https://ouds-android.netlify.app>
        // Unique deploy URL: <https://69ca52a63123710396c64f02--ouds-android.netlify.app>
        //
        // Build logs: <https://app.netlify.com/projects/ouds-android/deploys/69ca52a63123710396c64f02>
        // Function logs: <https://app.netlify.com/projects/ouds-android/logs/functions>
        // Edge function logs: <https://app.netlify.com/projects/ouds-android/logs/edge-functions>
        val netlifyDeployPreviewUrl = outputLines.indexOfFirst { it.contains("Draft URL") || it.contains("Unique deploy URL") }
            .takeIf { it != -1 }
            ?.let { index ->
                "(https:\\/\\/.*--ouds-android\\.netlify\\.app)".toRegex()
                    .find(outputLines[index])
                    ?.groupValues
                    ?.getOrNull(1)
            }

        if (exception == null && netlifyDeployPreviewUrl == null) {
            exception = GradleException("Could not parse Netlify deploy preview URL.")
        }

        val netlifyDeployLogUrl = outputLines.firstNotNullOfOrNull { "^Build logs:\\s+(.*)$".toRegex().find(it) }
            ?.groupValues
            ?.getOrNull(1)

        if (exception == null && netlifyDeployLogUrl == null) {
            exception = GradleException("Could not parse Netlify deploy log URL.")
        }

        // Save deploy preview URL in a file in order to update GitHub Netlify environment URL later on
        File("netlify_deploy_preview_url.txt").writeText(netlifyDeployPreviewUrl.orEmpty())

        if (Environment.branchName != "develop") {
            gitHubRestApi {
                // Find pull request for current branch
                val pullRequests = getPullRequests()
                val pullRequest = pullRequests.firstOrNull { it.branchName == Environment.branchName }
                if (pullRequest != null) {
                    logger.lifecycle("Found pull request #${pullRequest.number} for branch ${Environment.branchName}.")
                    val body = "$netlifyCommentPreamble\\n" + if (output != null) {
                        "### 🟢 Netlify deploy for commit $commitSha succeeded\\n\\nDeploy preview: $netlifyDeployPreviewUrl\\nDeploy log: $netlifyDeployLogUrl"
                    } else {
                        "### 🔴 Netlify deploy for commit $commitSha failed"
                    }
                    // Although we use the "issues/{issue_number}/comments" GitHub API, this will comment the pull request because a pull request is an issue
                    // The "pulls/{pull_number}/comments" is used to add review comments on a pull request
                    val issueComments = getIssueComments(pullRequest.number)
                    val netlifyComment = issueComments.firstOrNull { it.body.startsWith(netlifyCommentPreamble) }
                    if (netlifyComment != null) {
                        logger.lifecycle("Update comment with Netlify deploy info to '${pullRequest.title} (#${pullRequest.number})'.")
                        updateIssueComment(netlifyComment.id, body)
                    } else {
                        logger.lifecycle("Create comment with Netlify deploy info to '${pullRequest.title} (#${pullRequest.number})'.")
                        createIssueComment(pullRequest.number, body)
                    }
                } else if (exception == null) {
                    exception = GradleException("Could not find a pull request for branch ${Environment.branchName}.")
                }
            }
        }

        exception?.let { throw it }
    }
}

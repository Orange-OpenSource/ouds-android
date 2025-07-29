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
import com.orange.ouds.gradle.gitHubApi
import com.orange.ouds.gradle.requireTypedProperty
import org.gradle.process.internal.ExecException

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
        } catch (exception: ExecException) {
            logger.lifecycle(exception.stackTraceToString())
            null
        }

        var exception = if (output == null) GradleException("Netlify deploy failed.") else null

        val outputLines = output.orEmpty()
            .replace("\\x1B\\[([0-9]{1,3}(;[0-9]{1,2};?)?)?[mGK]".toRegex(), "") // Removes ANSI colors from output
            .split("\n")

        // Output displays "Deployed draft to" for draft deployments and "Unique deploy URL" for prod deployments:
        //
        // ╭───────────────────────── ⬥  Draft deploy is live ⬥ ──────────────────────────╮
        // │                                                                               │
        // │                              Deployed draft to                                │
        // │          https://687672e116cf5a2cbd949f0a--ouds-android.netlify.app           │
        // │                                                                               │
        // ╰───────────────────────────────────────────────────────────────────────────────╯
        //
        // ╭──────────────── ⬥  Production deploy is live ⬥ ─────────────────╮
        // │                                                                  │
        // │   Deployed to production URL: https://ouds-android.netlify.app   │
        // │                                                                  │
        // │                        Unique deploy URL:                        │
        // │    https://687673f72c25433090a8bc6e--ouds-android.netlify.app    │
        // │                                                                  │
        // ╰──────────────────────────────────────────────────────────────────╯
        val netlifyDeployPreviewUrl = outputLines.indexOfFirst { it.contains("Deployed draft to") || it.contains("Unique deploy URL") }
            .takeIf { it != -1 }
            ?.plus(1) // Deploy preview URL is displayed on the next line
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
            gitHubApi {
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

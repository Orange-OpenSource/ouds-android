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

        // GITHUB_HEAD_REF is equal to the branch name for a pull request and is empty otherwise
        // GITHUB_REF_NAME is equal to X/merge for a pull request (where X is the pull request number) or to the branch name otherwise
        // That's why we use GITHUB_HEAD_REF for a pull request and GITHUB_REF_NAME otherwise
        val branchName = Environment.getVariablesOrNull("GITHUB_HEAD_REF", "GITHUB_REF_NAME").firstOrNull { it?.isNotBlank() == true }

        val args = mutableListOf("netlify", "deploy", "--dir", documentationPath, "--site", netlifySiteId, "--auth", netlifyToken)
        if (branchName == "develop") {
            args += "--prod"
        }

        val output = try {
            execute("npx", *args.toTypedArray()).also { logger.lifecycle(it) }
        } catch (exception: ExecException) {
            logger.lifecycle(exception.stackTraceToString())
            null
        }

        var exception = if (output == null) GradleException("Netlify deploy failed") else null

        val outputLines = output.orEmpty()
            .replace("\\x1B\\[([0-9]{1,3}(;[0-9]{1,2};?)?)?[mGK]".toRegex(), "") // Removes ANSI colors from output
            .split("\n")
        // Output displays "Website draft URL" for draft deployments and "Website URL" for prod deployments
        val netlifyDeployPreviewUrl = outputLines.firstNotNullOfOrNull { "^Website (?:draft |)URL:\\s+(.*)$".toRegex().find(it) }
            ?.groupValues
            ?.getOrNull(1)
        val netlifyDeployLogUrl = outputLines.firstNotNullOfOrNull { "^Build logs:\\s+(.*)$".toRegex().find(it) }
            ?.groupValues
            ?.getOrNull(1)

        // Save deploy preview URL in a file in order to update GitHub Netlify environment URL later on
        File("netlify_deploy_preview_url.txt").writeText(netlifyDeployPreviewUrl.orEmpty())

        if (branchName != "develop") {
            gitHubApi {
                // Find pull request for current branch
                val pullRequests = getPullRequests()
                val pullRequest = pullRequests.firstOrNull { it.branchName == branchName }
                if (pullRequest != null) {
                    logger.lifecycle("Found pull request #${pullRequest.number} for branch $branchName.")
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
                    exception = GradleException("Could not find a pull request for branch $branchName.")
                }
            }
        }

        exception?.let { throw it }
    }
}

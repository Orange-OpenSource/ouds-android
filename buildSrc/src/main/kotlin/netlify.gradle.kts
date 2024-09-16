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

        val output = try {
            execute("netlify", "deploy", "--dir", documentationPath, "--site", netlifySiteId, "--auth", netlifyToken)
        } catch (exception: ExecException) {
            null
        }

        gitHubApi {
            // GITHUB_HEAD_REF is equal to the branch name for a pull request and is empty otherwise
            // GITHUB_REF_NAME is equal to X/merge for a pull request (where X is the pull request number) or to the branch name otherwise
            // That's why we use GITHUB_HEAD_REF for a pull request and GITHUB_REF_NAME otherwise
            val branchName = Environment.getVariablesOrNull("GITHUB_HEAD_REF", "GITHUB_REF_NAME").firstOrNull { it?.isNotBlank() == true }
            // Find pull request for current branch
            val pullRequests = getPullRequests()
            val pullRequest = pullRequests.firstOrNull { it.branchName == branchName }
            if (pullRequest != null) {
                println("Found pull request #${pullRequest.number} for branch $branchName.")
                val body = "$netlifyCommentPreamble\\n" + if (output != null) {
                    // The regex below removes colors from output
                    val outputLines = output.replace("\\x1B\\[([0-9]{1,3}(;[0-9]{1,2};?)?)?[mGK]".toRegex(), "").split("\n")
                    val netlifyDeployPreviewUrl = outputLines.firstNotNullOfOrNull { "^Website draft URL:\\s+(.*)$".toRegex().find(it) }
                        ?.groupValues
                        ?.getOrNull(1)
                    val netlifyDeployLogUrl = outputLines.firstNotNullOfOrNull { "^Build logs:\\s+(.*)$".toRegex().find(it) }
                        ?.groupValues
                        ?.getOrNull(1)
                    "### 🟢 Netlify deploy preview for commit $commitSha succeeded\\n\\nDeploy preview: $netlifyDeployPreviewUrl\\nDeploy log: $netlifyDeployLogUrl"
                } else {
                    "### 🔴 Netlify deploy preview for commit $commitSha failed"
                }
                // Although we use the "issues/{issue_number}/comments" GitHub API, this will comment the pull request because a pull request is an issue
                // The "pulls/{pull_number}/comments" is used to add review comments on a pull request
                val issueComments = getIssueComments(pullRequest.number)
                val netlifyComment = issueComments.firstOrNull { it.body.startsWith(netlifyCommentPreamble) }
                if (netlifyComment != null) {
                    println("Update comment with Netlify deploy info to '${pullRequest.title} (#${pullRequest.number})'.")
                    updateIssueComment(netlifyComment.id, body)
                } else {
                    println("Create comment with Netlify deploy info to '${pullRequest.title} (#${pullRequest.number})'.")
                    createIssueComment(pullRequest.number, body)
                }
            } else {
                throw GradleException("Could not find a pull request for branch $branchName.")
            }
        }
    }
}

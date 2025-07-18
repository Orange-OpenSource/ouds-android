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

import org.gradle.api.Project
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class GitHubApi(token: String, repository: String) : Api() {

    override val baseUrl = "https://api.github.com/repos/$repository"

    override val headers = mapOf(
        "Authorization" to listOf("token $token"),
        "Accept" to listOf("application/vnd.github.v3+json")
    )

    fun Project.createTag(tag: String, sha: String) {
        launchRequest(
            "git/refs",
            "POST",
            "{\"ref\":\"refs/tags/$tag\",\"sha\":\"$sha\"}"
        )
    }

    fun Project.getPullRequests(): List<GitHubPullRequest> {
        val jsonString = launchRequest("pulls", "GET")
        return parsePullRequests(jsonString)
    }

    fun Project.getIssueComments(issueNumber: Int): List<GitHubIssueComment> {
        val jsonString = launchRequest("issues/$issueNumber/comments", "GET")
        return parseIssueComments(jsonString)
    }

    fun Project.createIssueComment(issueNumber: Int, body: String) {
        launchRequest(
            "issues/$issueNumber/comments",
            "POST",
            "{\"body\":\"$body\"}"
        )
    }

    fun Project.updateIssueComment(commentId: Long, body: String) {
        launchRequest(
            "issues/comments/$commentId",
            "PATCH",
            "{\"body\":\"$body\"}"
        )
    }

    @OptIn(ExperimentalEncodingApi::class)
    fun Project.createFile(file: File, path: String, commitMessage: String, branch: String): String {
        val base64FileContent = Base64.encode(file.readBytes())
        val jsonString = launchRequest(
            "contents/$path",
            "PUT",
            "{\"message\":\"$commitMessage\",\"content\":\"$base64FileContent\",\"branch\":\"$branch\"}"
        )

        return JSONObject(jsonString).getJSONObject("commit").getString("sha")
    }

    fun Project.publishRelease(tag: String, draft: Boolean, prerelease: Boolean) {
        launchRequest(
            "releases",
            "POST",
            "{\"tag_name\":\"$tag\",\"name\":\"$tag\",\"draft\":$draft,\"prerelease\":$prerelease}"
        )
    }

    private fun parsePullRequests(jsonString: String): List<GitHubPullRequest> {
        val jsonPullRequests = JSONArray(jsonString)

        return List(jsonPullRequests.length()) { index ->
            with(jsonPullRequests.getJSONObject(index)) {
                GitHubPullRequest(
                    number = getInt("number"),
                    title = getString("title"),
                    branchName = getJSONObject("head").getString("ref")
                )
            }
        }
    }

    private fun parseIssueComments(jsonString: String): List<GitHubIssueComment> {
        val jsonIssueComments = JSONArray(jsonString)

        return List(jsonIssueComments.length()) { index ->
            with(jsonIssueComments.getJSONObject(index)) {
                GitHubIssueComment(
                    id = getLong("id"),
                    body = getString("body")
                )
            }
        }
    }
}

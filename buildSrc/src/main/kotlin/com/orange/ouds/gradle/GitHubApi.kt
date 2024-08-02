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

class GitHubApi(private val token: String, private val repository: String) {

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

    fun Project.commentIssue(number: Int, comment: String) {
        launchRequest(
            "issues/$number/comments",
            "POST",
            "{\"body\":\"$comment\"}"
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

    private fun Project.launchRequest(path: String, method: String, body: String? = null): String {
        val args = mutableListOf(
            "-X", method,
            "-H", "Authorization: token $token",
            "-H", "Accept: application/vnd.github.v3+json"
        ).apply {
            if ((method == "POST" || method == "PUT") && body != null) {
                add("-d")
                add(body)
            }
            add("https://api.github.com/repos/$repository/$path")
        }
        return curl(*args.toTypedArray())
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
}

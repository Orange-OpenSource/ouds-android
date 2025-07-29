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
import org.json.JSONObject

class GitHubGraphQLApi(token: String, private val owner: String, private val name: String) : Api() {

    override val baseUrl = "https://api.github.com"

    override val headers = mapOf("Authorization" to listOf("Bearer $token"))

    fun Project.getPullRequestClosingIssues(pullRequestNumber: Int): List<GitHubIssue> {
        val query = """query {
            repository(owner: \"$owner\", name: \"${this@GitHubGraphQLApi.name}\") {
                pullRequest(number: $pullRequestNumber) {
                    closingIssuesReferences(first: 100) {
                        nodes {
                            number
                            title
                        }
                    }
                }
            }
        }""".replace("\n\\s*".toRegex(), " ")
        val body = "{ \"query\": \"$query\" }"
        val jsonString = launchGraphQLRequest(body)

        return parsePullRequestClosingIssues(jsonString)
    }

    private fun Project.launchGraphQLRequest(body: String? = null) = launchRequest("graphql", "POST", body)

    private fun parsePullRequestClosingIssues(jsonString: String): List<GitHubIssue> {
        val jsonIssues = JSONObject(jsonString)
            .getJSONObject("data")
            .getJSONObject("repository")
            .getJSONObject("pullRequest")
            .getJSONObject("closingIssuesReferences")
            .getJSONArray("nodes")

        return List(jsonIssues.length()) { index ->
            with(jsonIssues.getJSONObject(index)) {
                GitHubIssue(
                    number = getInt("number"),
                    title = getString("title")
                )
            }
        }
    }
}

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


// Swagger for OSSRH Staging API is available at https://ossrh-staging-api.central.sonatype.com/swagger-ui/
class SonatypeOssrhStagingApi(token: String) : Api() {

    override val baseUrl = "https://ossrh-staging-api.central.sonatype.com"

    override val headers = mapOf(
        "Accept" to listOf("application/json"),
        "Authorization" to listOf("Bearer $token")
    )

    fun Project.searchRepositories(): List<SonatypeOssrhStagingRepository> {
        val jsonString = launchRequest("manual/search/repositories", "GET")
        return parseRepositories(jsonString)
    }

    fun Project.uploadRepository(repositoryKey: String) {
        launchRequest("manual/upload/repository/$repositoryKey", "POST")
    }

    fun Project.dropRepository(repositoryKey: String) {
        launchRequest("manual/drop/repository/$repositoryKey", "DELETE")
    }

    private fun parseRepositories(jsonString: String): List<SonatypeOssrhStagingRepository> {
        val jsonObject = JSONObject(jsonString)
        val jsonRepositories = jsonObject.getJSONArray("repositories")

        return List(jsonRepositories.length()) { index ->
            with(jsonRepositories.getJSONObject(index)) {
                SonatypeOssrhStagingRepository(
                    key = getString("key"),
                    state = SonatypeOssrhStagingRepository.State.valueOf(getString("state").uppercase()),
                )
            }
        }
    }
}

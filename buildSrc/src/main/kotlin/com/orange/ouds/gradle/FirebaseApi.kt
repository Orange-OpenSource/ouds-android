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

class FirebaseApi(private val accessToken: String, private val projectNumber: String, private val appId: String) {

    fun Project.getAppDistributionReleases(): List<FirebaseAppDistributionRelease> {
        val jsonString = launchRequest("releases", "GET")

        return parseAppDistributionReleases(jsonString)
    }

    private fun Project.launchRequest(path: String, method: String, body: String? = null): String {
        val args = mutableListOf(
            "-X", method,
            "-H", "Authorization: Bearer $accessToken",
            "-H", "Accept: application/json",
            "--compressed",
        ).apply {
            if ((method == "POST" || method == "PUT") && body != null) {
                add("-d")
                add(body)
            }
            add("https://firebaseappdistribution.googleapis.com/v1/projects/$projectNumber/apps/$appId/$path")
        }
        return curl(*args.toTypedArray())
    }

    private fun parseAppDistributionReleases(jsonString: String): List<FirebaseAppDistributionRelease> {
        val jsonObject = JSONObject(jsonString)
        val jsonReleases = jsonObject.getJSONArray("releases")

        return List(jsonReleases.length()) { index ->
            with(jsonReleases.getJSONObject(index)) {
                FirebaseAppDistributionRelease(
                    buildVersion = getString("buildVersion").toInt(),
                    binaryDownloadUri = getString("binaryDownloadUri")
                )
            }
        }
    }
}

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

class OmaApi(private val accessToken: String, private val appId: String, private val appToken: String) : Api() {

    override val baseUrl = "https://inside01.api.orange.com/oma-portal/v2"

    override val headers = mapOf(
        "Authorization" to listOf("Bearer $accessToken"),
        "apiKey" to listOf(appToken)
    )

    fun Project.createArtifact(filePath: String) {
        launchRequest(
            "applications/$appId/artifacts?store=google_play_android",
            "POST",
            form = "file=@$filePath",
            baseUrl = "https://oma-portal.orange.fr/oma/api/v2/external" // Specific base URL for artifact creation
        )
    }
}

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

class OrangeDeveloperInsideApi(private val authorizationHeader: String) : Api() {

    override val baseUrl = "https://inside01.api.orange.com"

    override val headers = mapOf(
        "Authorization" to listOf("Basic $authorizationHeader"),
        "Content-Type" to listOf("application/x-www-form-urlencoded"),
        "Accept" to listOf("application/json")
    )

    fun Project.getAccessToken(): String {
        val jsonString = launchRequest(
            "oauth/v3/token",
            "POST",
            "grant_type=client_credentials"
        )

        return JSONObject(jsonString).getString("access_token")
    }
}

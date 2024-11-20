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

import org.json.JSONObject
import java.io.File

class GoogleServices(filePath: String) {

    private val jsonString = File(filePath).readText()

    val apps = parseApps(jsonString)
}

private fun parseApps(jsonString: String): List<GoogleServicesApp> {
    val jsonObject = JSONObject(jsonString)
    val jsonClient = jsonObject.getJSONArray("client")

    return List(jsonClient.length()) { index ->
        with(jsonClient.getJSONObject(index)) {
            val clientInfo = getJSONObject("client_info")
            GoogleServicesApp(
                id = clientInfo.getString("mobilesdk_app_id"),
                packageName = clientInfo.getJSONObject("android_client_info").getString("package_name")
            )
        }
    }
}

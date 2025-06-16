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

abstract class Api() {

    protected abstract val baseUrl: String

    protected open val headers: Map<String, List<String>> = emptyMap()

    protected open val curlOptions: List<String> = emptyList()

    protected fun Project.launchRequest(endpoint: String, method: String, body: String? = null): String {
        val args = buildList {
            add("-X")
            add(method)
            headers.forEach { header ->
                add("-H")
                add("${header.key}: ${header.value.joinToString(", ")}")
            }
            curlOptions.forEach { add("--$it") }
            if ((method == "POST" || method == "PUT" || method == "PATCH") && body != null) {
                add("-d")
                add(body)
            }
            add("$baseUrl/$endpoint")
        }
        return curl(*args.toTypedArray())
    }
}

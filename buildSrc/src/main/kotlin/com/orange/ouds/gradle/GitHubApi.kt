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

class GitHubApi(private val token: String, private val repository: String) {

    fun Project.createTag(tag: String, sha: String) {
        launchRequest(
            "git/refs",
            "POST",
            "{\"ref\":\"refs/tags/$tag\",\"sha\":\"$sha\"}"
        )
    }

    private fun Project.launchRequest(path: String, method: String, postData: String? = null) {
        val args = mutableListOf(
            "-X", method,
            "-H", "Authorization: token $token",
            "-H", "Accept: application/vnd.github.v3+json"
        ).apply {
            if (method == "POST" && postData != null) {
                add("-d")
                add(postData)
            }
            add("https://api.github.com/repos/$repository/$path")
        }
        curl(*args.toTypedArray())
    }
}

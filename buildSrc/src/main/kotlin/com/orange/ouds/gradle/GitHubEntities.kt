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

data class GitHubPullRequest(
    val number: Int,
    val title: String,
    val branchName: String,
)

data class GitHubIssueComment(
    val id: Long,
    val body: String,
)

data class GitHubIssue(
    val number: Int,
    val title: String
)

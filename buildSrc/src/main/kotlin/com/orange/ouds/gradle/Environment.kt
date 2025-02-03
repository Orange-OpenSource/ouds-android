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

import org.gradle.api.GradleException

object Environment {

    /**
     * The Git branch name extracted from the environment variables.
     */
    val branchName: String?
        // GITHUB_HEAD_REF is equal to the branch name for a pull request and is empty otherwise
        // GITHUB_REF_NAME is equal to X/merge for a pull request (where X is the pull request number) or to the branch name otherwise
        // That's why we use GITHUB_HEAD_REF for a pull request and GITHUB_REF_NAME otherwise
        get() = getVariablesOrNull("GITHUB_HEAD_REF", "GITHUB_REF_NAME").firstOrNull { it?.isNotBlank() == true }

    /**
     * Returns the values for the given environment variables names.
     * Throws an exception if at least one environment variable is missing.
     *
     * @param variables The list of environment variables.
     * @return The values associated with these environment variables.
     */
    fun getVariables(vararg variables: String): List<String> {
        val missingVariable = variables.firstOrNull { !System.getenv().containsKey(it) }
        // At least one environment variable is missing
        if (missingVariable != null) {
            throw GradleException("$missingVariable environment variable is missing.")
        }

        return variables.map { System.getenv(it) }
    }

    /**
     * Returns the values for the given environment variables names.
     * A value can be null if the corresponding environment variable is missing.
     *
     * @param variables The list of environment variables.
     * @return The values associated with these environment variables.
     */
    fun getVariablesOrNull(vararg variables: String): List<String?> {
        return variables.map { System.getenv(it) }
    }
}

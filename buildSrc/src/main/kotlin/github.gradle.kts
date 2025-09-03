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

import com.orange.ouds.gradle.Environment
import com.orange.ouds.gradle.gitHubRestApi

tasks.register<DefaultTask>("publishToGitHub") {
    doLast {
        val ref = Environment.getVariables("GITHUB_REF").first()
        val tag = ref.substringAfter("refs/tags/")
        gitHubRestApi {
            publishRelease(tag, draft = true, prerelease = true)
        }
    }
}

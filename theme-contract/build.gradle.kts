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

plugins {
    id("dokka")
    id("library")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.orange.ouds.theme"

    buildFeatures {
        compose = true
    }

    kotlin {
        compilerOptions {
            freeCompilerArgs.add("-opt-in=com.orange.ouds.foundation.InternalOudsApi")
        }
    }
}

dependencies {
    implementation(project(":foundation"))
    implementation(project(":global-raw-tokens"))
    api(platform(libs.androidx.compose.bom))
    api(libs.androidx.compose.material3)
}
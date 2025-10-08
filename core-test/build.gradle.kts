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
    id("library")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.orange.ouds.core.test"

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
    api(project(":core"))
    implementation(project(":foundation"))

    api(libs.androidx.compose.ui.test.junit4)
    implementation(libs.paparazzi)
}

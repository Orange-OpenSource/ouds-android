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
    namespace = "com.orange.ouds.foundation"

    buildFeatures {
        compose = true
    }

    kotlin {
        compilerOptions {
            // From Kotlin 2.2, need to specify default rule for annotations
            // See http://youtrack.jetbrains.com/issue/KT-73255
            freeCompilerArgs.add("-Xannotation-default-target=param-property")
        }
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    api(libs.androidx.compose.ui.tooling.preview)
}
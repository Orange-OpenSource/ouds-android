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
    id(libs.plugins.kotlin.parcelize.get().pluginId) // https://github.com/gradle/gradle/issues/20084#issuecomment-1060822638
}

android {
    namespace = "com.orange.ouds.theme.sosh"

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

    implementation(project(":theme-contract"))
    implementation(project(":global-raw-tokens"))
}
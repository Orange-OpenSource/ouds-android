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
    alias(libs.plugins.paparazzi)
    id(libs.plugins.kotlin.parcelize.get().pluginId) // https://github.com/gradle/gradle/issues/20084#issuecomment-1060822638
}

android {
    namespace = "com.orange.ouds.theme.orange"

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
    // The core dependency is needed otherwise core resources are not found during Paparazzi tests
    // The drawback is that this includes the core module as a dependency of the final package of this module,
    // but this is not really a problem because using OUDS Android requires the core module anyway
    // Also, we can't use implementation otherwise there is a cyclic dependency with core if this theme is used as the preview theme
    // TODO Remove when https://github.com/cashapp/paparazzi/issues/1060 is fixed
    runtimeOnly(project(":core"))
    implementation(project(":foundation"))
    implementation(project(":global-raw-tokens"))
    implementation(project(":theme-contract"))
    
    testImplementation(project(":core-test"))
}
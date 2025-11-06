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
    // TODO Remove the core dependency when https://github.com/cashapp/paparazzi/issues/1060 is fixed
    // A few considerations about this fix:
    // - The core dependency is needed otherwise core resources are not found during Paparazzi tests
    // - We use runtimeOnly instead of implementation otherwise there is a cyclic dependency between core and this module if it is used as the preview theme
    // - Moreover, although using runtimeOnly works when launching tests, this leads to a cyclic dependency when launching the linter,
    //   that's why we only add this dependency when launching Paparazzi tasks
    if (gradle.startParameter.taskNames.any { it.contains("paparazzi", ignoreCase = true) }) {
        runtimeOnly(project(":core"))
    }
    implementation(project(":foundation"))
    implementation(project(":global-raw-tokens"))
    implementation(project(":theme-contract"))
    
    testImplementation(project(":core-test"))
}
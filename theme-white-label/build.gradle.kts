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
    id("paparazzi")
    alias(libs.plugins.compose.compiler)
    id(libs.plugins.kotlin.parcelize.get().pluginId) // https://github.com/gradle/gradle/issues/20084#issuecomment-1060822638
}

// Temporary workaround for https://issuetracker.google.com/issues/476936389
val dokkaKotlinAdapter = objects.newInstance(org.jetbrains.dokka.gradle.adapters.KotlinAdapter::class)
dokkaKotlinAdapter.apply(project)

android {
    namespace = "com.orange.ouds.theme.whitelabel"

    buildFeatures {
        compose = true
    }

    kotlin {
        compilerOptions {
            freeCompilerArgs.addAll(
                "-opt-in=com.orange.ouds.foundation.RestrictedOudsApi",
                "-opt-in=com.orange.ouds.foundation.ExperimentalOudsApi",
                "-opt-in=com.orange.ouds.foundation.InternalOudsApi"
            )
        }
    }
}

dependencies {
    implementation(project(":foundation"))
    implementation(project(":global-raw-tokens"))
    implementation(project(":theme-contract"))
    implementation(project(":theme-wireframe"))

    testImplementation(project(":core-test"))
}
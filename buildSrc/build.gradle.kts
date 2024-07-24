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
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    mavenCentral()
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(libs.android.gradle.plugin)
    implementation(libs.javapoet) // https://github.com/google/dagger/issues/3282
    implementation(libs.kotlin.gradle.plugin) // https://issuetracker.google.com/issues/176079157#comment14
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location)) // https://github.com/gradle/gradle/issues/15383
}
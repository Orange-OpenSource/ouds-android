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
    gradlePluginPortal()
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(libs.android.gradle.plugin)
    implementation(libs.dokka.base)
    implementation(libs.dokka.gradle.plugin)
    implementation(libs.firebase.appdistribution.gradle.plugin)
    implementation(libs.git.changelog.gradle.plugin)
    implementation(libs.git.changelog.lib)
    implementation(libs.google.auth.library.oauth2.http)
    // gRPC dependencies below fix an error that appeared with google-auth-library-oauth2-http 1.25.0
    // which uses grpc-api 1.66.0 whereas another dependency uses a previous and incompatible version of others gRPC modules
    implementation(libs.grpc.core)
    implementation(libs.grpc.netty)
    implementation(libs.grpc.protobuf)
    implementation(libs.grpc.stub)
    implementation(libs.javapoet) // https://github.com/google/dagger/issues/3282
    implementation(libs.json)
    implementation(libs.kotlin.gradle.plugin) // https://issuetracker.google.com/issues/176079157#comment14
    implementation(libs.mustache.java)
    implementation(libs.zxing.core)
    implementation(libs.zxing.javase)
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location)) // https://github.com/gradle/gradle/issues/15383
}

sourceSets {
    named("main") {
        java {
            srcDir("../theme-contract/src/main/java")
            setIncludes(listOf("com/orange/ouds/theme/OudsVersion.kt"))
        }
    }
}

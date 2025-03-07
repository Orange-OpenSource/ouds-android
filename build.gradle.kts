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

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    kotlin("jvm")
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.firebase.appdistribution) apply false
    alias(libs.plugins.firebase.crashlytics) apply false
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.figma.code.connect) apply false
    id(libs.plugins.dokka.gradle.plugin.get().pluginId) // https://github.com/gradle/gradle/issues/20084#issuecomment-1060822638
    id("github")
    id("release")
    id("netlify")
    id("check-notice")
}

dependencies {
    dokkaPlugin(libs.dokka.android.documentation.plugin)
    dokka(project(":core"))
    dokka(project(":theme-contract"))
}

checkNotice {
    additionalResourcePaths += "$rootDir/docs/images"
}

dokka {
    // used as project name in the header
    moduleName.set("OUDS Android")

    pluginsConfiguration.html {
        customAssets.from("docs/assets/logo-icon.svg")
        customStyleSheets.from("docs/assets/orange-style.css")
        footerMessage.set("Copyright © Orange 2024")
    }

    dokkaPublications.html {
        includes.from("docs/index.md")
        outputDirectory.set(projectDir.resolve("docs/dokka"))
    }
}
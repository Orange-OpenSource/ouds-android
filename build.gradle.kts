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

import org.jetbrains.dokka.base.DokkaBase
import org.jetbrains.dokka.base.DokkaBaseConfiguration

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.firebase.appdistribution) apply false
    alias(libs.plugins.firebase.crashlytics) apply false
    alias(libs.plugins.google.services) apply false
    id(libs.plugins.dokka.gradle.plugin.get().pluginId) // https://github.com/gradle/gradle/issues/20084#issuecomment-1060822638
    id("github")
    id("release")
    id("netlify")
    id("check-notice")
}

dependencies {
    dokkaPlugin(libs.android.documentation.plugin)
}

checkNotice {
    additionalResourcePaths += "$rootDir/docs/images"
}

tasks.dokkaHtmlMultiModule {
    outputDirectory.set(projectDir.resolve("dokka"))
    includes.from("Root.md")
    // used as project name in the header
    moduleName.set("OUDS Android")
    pluginConfiguration<DokkaBase, DokkaBaseConfiguration> {
        //customAssets = listOf(projectDir.resolve("assets/favicon.png"))
        footerMessage = "Copyright © Orange 2024"
    }
}

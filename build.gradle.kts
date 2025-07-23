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

import com.orange.ouds.gradle.releaseVersion

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    kotlin("jvm")
    alias(libs.plugins.compose.compiler) apply false
    id(libs.plugins.firebase.appdistribution.get().pluginId) apply false
    alias(libs.plugins.firebase.crashlytics) apply false
    alias(libs.plugins.google.services) apply false
    id(libs.plugins.dokka.get().pluginId) // https://github.com/gradle/gradle/issues/20084#issuecomment-1060822638
    id("github")
    id("release")
    id("netlify")
    id("check-notice")
}

dependencies {
    dokkaPlugin(libs.dokka.android.documentation.plugin)
    dokkaHtmlPlugin(libs.dokka.versioning.plugin)
    dokka(project(":core"))
    dokka(project(":global-raw-tokens"))
    dokka(project(":theme-contract"))
    dokka(project(":theme-orange"))
    dokka(project(":theme-sosh"))
}

checkNotice {
    additionalResourcePaths += "$rootDir/docs/images"
}

dokka {
    // used as project name in the header
    moduleName.set("OUDS Android")

    pluginsConfiguration {
        html {
            customAssets.from("docs/assets/logo-icon.svg", "docs/assets/banner.png")
            customStyleSheets.from("docs/assets/orange-style.css")
            footerMessage.set("Copyright © Orange 2024")
        }
        versioning {
            // This configuration step is evaluated before any task is executed,
            // thus project.version.toString() is not up to date when executing the prepareRelease task.
            // That's why we use releaseVersion to set the correct version.
            version.set(releaseVersion ?: project.version.toString())
            olderVersionsDir.set(projectDir.resolve("docs/previousDocVersions"))
        }
    }

    dokkaPublications.html {
        includes.from("docs/index.md")
        outputDirectory.set(projectDir.resolve("docs/dokka"))
    }
}
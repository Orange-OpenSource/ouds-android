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
import org.jetbrains.dokka.gradle.DokkaTaskPartial

plugins {
    id("org.jetbrains.dokka")
}

tasks.withType<DokkaTaskPartial>().configureEach {
    failOnWarning.set(true)

    dokkaSourceSets {
        configureEach {
            includes.from("Module.md")
            pluginConfiguration<DokkaBase, DokkaBaseConfiguration> {
                customStyleSheets = listOf(file("docs/assets/orange-style.css"))
            }
        }
    }
}
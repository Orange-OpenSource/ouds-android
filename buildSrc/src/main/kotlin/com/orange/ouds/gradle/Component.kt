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

package com.orange.ouds.gradle

import com.orange.ouds.theme.OudsVersion
import org.gradle.api.Project

enum class Component {
    Badge,
    Button,
    Checkbox,
    Chip,
    Divider,
    Link,
    RadioButton,
    Switch,
    Tag;

    val version: String
        get() = with(OudsVersion.Component) {
            when (this@Component) {
                Component.Badge -> Badge
                Component.Button -> Button
                Component.Checkbox -> Checkbox
                Component.Chip -> Chip
                Component.Divider -> Divider
                Component.Link -> Link
                Component.RadioButton -> RadioButton
                Component.Switch -> Switch
                Component.Tag -> Tag
            }
        }

    fun getSourceFilePaths(project: Project): List<String> {
        val filenames = when (this) {
            Component.Badge -> listOf("OudsBadge")
            Component.Button -> listOf("OudsButton")
            Component.Checkbox -> listOf("OudsCheckbox", "OudsCheckboxItem")
            Component.Chip -> listOf("OudsFilterChip", "OudsSuggestionChip")
            Component.Divider -> listOf("OudsDivider")
            Component.Link -> listOf("OudsLink")
            Component.RadioButton -> listOf("OudsRadioButton", "OudsRadioButtonItem")
            Component.Switch -> listOf("OudsSwitch", "OudsSwitchItem")
            Component.Tag -> listOf("OudsTag", "OudsInputTag")
        }

        return filenames.map { "${project.rootProject.projectDir}/core/src/main/java/com/orange/ouds/core/component/$it.kt" }
    }
}
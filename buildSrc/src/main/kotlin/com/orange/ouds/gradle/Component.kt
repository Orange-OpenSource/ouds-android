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
    Bar,
    RadioButton,
    Switch,
    Tag,
    TextInput;

    val version: String
        get() = with(OudsVersion.Component) {
            when (this@Component) {
                Component.Badge -> Badge
                Component.Button -> Button
                Component.Checkbox -> Checkbox
                Component.Chip -> Chip
                Component.Divider -> Divider
                Component.Link -> Link
                Component.Bar -> Bar
                Component.RadioButton -> RadioButton
                Component.Switch -> Switch
                Component.Tag -> Tag
                Component.TextInput -> TextInput
            }
        }

    fun getSourceFilePaths(project: Project): List<String> {
        val filenames = when (this) {
            Badge -> listOf("OudsBadge")
            Button -> listOf("OudsButton")
            Checkbox -> listOf("OudsCheckbox", "OudsCheckboxItem")
            Chip -> listOf("OudsFilterChip", "OudsSuggestionChip")
            Divider -> listOf("OudsDivider")
            Link -> listOf("OudsLink")
            Bar -> listOf("OudsNavigationBar", "OudsTopAppBar")
            RadioButton -> listOf("OudsRadioButton", "OudsRadioButtonItem")
            Switch -> listOf("OudsSwitch", "OudsSwitchItem")
            Tag -> listOf("OudsTag", "OudsInputTag")
            TextInput -> listOf("OudsTextInput")
        }

        return filenames.map { "${project.rootProject.projectDir}/core/src/main/java/com/orange/ouds/core/component/$it.kt" }
    }
}
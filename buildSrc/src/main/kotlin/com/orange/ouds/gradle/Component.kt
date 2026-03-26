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
    Alert,
    Badge,
    Bar,
    BulletList,
    Button,
    Checkbox,
    Chip,
    Divider,
    Link,
    PasswordInput,
    RadioButton,
    Switch,
    Tag,
    TextArea,
    TextInput;

    val version: String
        get() = with(OudsVersion.Component) {
            when (this@Component) {
                Component.Alert -> Alert
                Component.Badge -> Badge
                Component.Bar -> Bar
                Component.BulletList -> BulletList
                Component.Button -> Button
                Component.Checkbox -> Checkbox
                Component.Chip -> Chip
                Component.Divider -> Divider
                Component.Link -> Link
                Component.PasswordInput -> PasswordInput
                Component.RadioButton -> RadioButton
                Component.Switch -> Switch
                Component.Tag -> Tag
                Component.TextArea -> TextArea
                Component.TextInput -> TextInput
            }
        }

    fun getSourceFilePaths(project: Project): List<String> {
        val filenames = when (this) {
            Alert -> listOf("OudsAlertMessage", "OudsInlineAlert")
            Badge -> listOf("OudsBadge")
            Bar -> listOf("OudsNavigationBar", "OudsTopAppBar")
            BulletList -> listOf("OudsBulletList")
            Button -> listOf("OudsButton")
            Checkbox -> listOf("OudsCheckbox", "OudsCheckboxItem")
            Chip -> listOf("OudsFilterChip", "OudsSuggestionChip")
            Divider -> listOf("OudsDivider")
            Link -> listOf("OudsLink")
            PasswordInput -> listOf("OudsPasswordInput")
            RadioButton -> listOf("OudsRadioButton", "OudsRadioButtonItem")
            Switch -> listOf("OudsSwitch", "OudsSwitchItem")
            Tag -> listOf("OudsTag", "OudsInputTag")
            TextArea -> listOf("OudsTextArea")
            TextInput -> listOf("OudsTextInput")
        }

        return filenames.map { "${project.rootProject.projectDir}/core/src/main/java/com/orange/ouds/core/component/$it.kt" }
    }
}
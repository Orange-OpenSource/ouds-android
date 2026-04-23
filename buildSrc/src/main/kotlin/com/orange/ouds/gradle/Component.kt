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
    AlertMessage,
    Badge,
    BadgeCount,
    BadgeIcon,
    BottomSheet,
    BulletList,
    Button,
    Checkbox,
    Divider,
    Fab,
    FilterChip,
    InlineAlert,
    InputTag,
    Link,
    NavigationBar,
    PasswordInput,
    PinCodeInput,
    RadioButton,
    SuggestionChip,
    Switch,
    Tag,
    TextArea,
    TextInput;

    val version: String
        get() = with(OudsVersion.Component) {
            when (this@Component) {
                Component.AlertMessage -> AlertMessage
                Component.Badge -> Badge
                Component.BadgeCount -> BadgeCount
                Component.BadgeIcon -> BadgeIcon
                Component.BottomSheet -> BottomSheet
                Component.BulletList -> BulletList
                Component.Button -> Button
                Component.Checkbox -> Checkbox
                Component.Divider -> Divider
                Component.Fab -> Fab
                Component.FilterChip -> FilterChip
                Component.InlineAlert -> InlineAlert
                Component.InputTag -> InputTag
                Component.Link -> Link
                Component.NavigationBar -> NavigationBar
                Component.PasswordInput -> PasswordInput
                Component.PinCodeInput -> PinCodeInput
                Component.RadioButton -> RadioButton
                Component.SuggestionChip -> SuggestionChip
                Component.Switch -> Switch
                Component.Tag -> Tag
                Component.TextArea -> TextArea
                Component.TextInput -> TextInput
            }
        }

    val designName: String
        get() {
            // Convert enum name to design name (e.g., "BadgeIcon" -> "Badge Icon")
            return this.name
                .replace(Regex("([a-z])([A-Z])"), "$1 $2")
                .replace("Pin", "PIN")
                .replace("Fab", "FAB")
        }

    fun getSourceFilePaths(project: Project): List<String> {
        val filenames = when (this) {
            AlertMessage -> listOf("OudsAlertMessage")
            Badge -> listOf("OudsBadge")
            BadgeCount -> listOf("OudsBadge")
            BadgeIcon -> listOf("OudsBadge")
            BottomSheet -> listOf("OudsBottomSheetScaffold", "OudsModalBottomSheet")
            BulletList -> listOf("OudsBulletList")
            Button -> listOf("OudsButton")
            Checkbox -> listOf("OudsCheckbox", "OudsCheckboxItem")
            Divider -> listOf("OudsDivider")
            Fab -> listOf("OudsFloatingActionButton")
            FilterChip -> listOf("OudsFilterChip")
            InlineAlert -> listOf("OudsInlineAlert")
            InputTag -> listOf("OudsInputTag")
            Link -> listOf("OudsLink")
            NavigationBar -> listOf("OudsNavigationBar") // TODO Waiting for a specific version number for TopAppBar (in Maxime's TODO)
            PasswordInput -> listOf("OudsPasswordInput")
            PinCodeInput -> listOf("OudsPinCodeInput")
            RadioButton -> listOf("OudsRadioButton", "OudsRadioButtonItem")
            SuggestionChip -> listOf("OudsSuggestionChip")
            Switch -> listOf("OudsSwitch", "OudsSwitchItem")
            Tag -> listOf("OudsTag")
            TextArea -> listOf("OudsTextArea")
            TextInput -> listOf("OudsTextInput")
        }

        return filenames.map { "${project.rootProject.projectDir}/core/src/main/java/com/orange/ouds/core/component/$it.kt" }
    }
}
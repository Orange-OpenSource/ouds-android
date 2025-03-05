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

package com.orange.ouds.app.ui.components.button

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.Component
import com.orange.ouds.app.ui.components.coloredBoxCall
import com.orange.ouds.app.ui.components.enabledArgument
import com.orange.ouds.app.ui.components.labelArgument
import com.orange.ouds.app.ui.components.onClickArgument
import com.orange.ouds.app.ui.components.painterArgument
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChips
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextField
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.core.component.OudsButton
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview

@Composable
fun ButtonDemoScreen() {
    val state = rememberButtonDemoState()
    DemoScreen(
        description = stringResource(id = Component.Button.descriptionRes),
        bottomSheetContent = { ButtonDemoBottomSheetContent(state = state) },
        codeSnippet = { buttonDemoCodeSnippet(state = state) },
        demoContent = { ButtonDemoContent(state = state) },
        demoContentOnColoredBox = state.onColoredBox
    )
}

@Composable
private fun ButtonDemoBottomSheetContent(state: ButtonDemoState) {
    with(state) {
        CustomizationSwitchItem(
            label = stringResource(R.string.app_common_enabled_label),
            checked = enabled,
            onCheckedChange = { enabled = it },
            enabled = enabledSwitchEnabled
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_common_onColoredBackground_label),
            checked = onColoredBox,
            onCheckedChange = { onColoredBox = it },
            enabled = onColoredBoxSwitchEnabled
        )
        CustomizationFilterChips(
            modifier = Modifier.padding(top = OudsTheme.spaces.fixed.medium),
            label = stringResource(R.string.app_components_button_hierarchy_label),
            chipsLabels = OudsButton.Hierarchy.entries.map { it.name },
            selectedChipIndex = OudsButton.Hierarchy.entries.indexOf(hierarchy),
            onSelectionChange = { id -> hierarchy = OudsButton.Hierarchy.entries[id] }
        )
        val styles = remember {
            listOf(
                OudsButton.Style.Default,
                OudsButton.Style.Loading(progress = null),
            )
        }
        CustomizationFilterChips(
            modifier = Modifier.padding(top = OudsTheme.spaces.fixed.medium),
            label = stringResource(R.string.app_components_common_style_label),
            chipsLabels = styles.map { it::class.simpleName.orEmpty() },
            selectedChipIndex = styles.indexOf(style),
            onSelectionChange = { id -> style = styles[id] }
        )
        CustomizationFilterChips(
            modifier = Modifier.padding(top = OudsTheme.spaces.fixed.medium),
            label = stringResource(R.string.app_components_common_layout_label),
            chipsLabels = ButtonDemoState.Layout.entries.map { stringResource(it.labelRes) },
            selectedChipIndex = ButtonDemoState.Layout.entries.indexOf(layout),
            onSelectionChange = { id -> layout = ButtonDemoState.Layout.entries[id] }
        )
        CustomizationTextField(
            label = stringResource(R.string.app_components_common_label_label),
            value = label,
            onValueChange = { value -> label = value }
        )
    }
}

@Composable
private fun ButtonDemoContent(state: ButtonDemoState) {
    val icon = OudsButton.Icon(
        painter = painterResource(id = R.drawable.ic_heart),
        contentDescription = stringResource(id = R.string.app_components_common_icon_a11y)
    )
    with(state) {
        when (layout) {
            ButtonDemoState.Layout.TextOnly -> {
                OudsButton(
                    label = label,
                    onClick = {},
                    enabled = enabled,
                    style = style,
                    hierarchy = hierarchy
                )
            }
            ButtonDemoState.Layout.TextAndIcon -> {
                OudsButton(
                    icon = icon,
                    label = label,
                    onClick = {},
                    enabled = enabled,
                    style = style,
                    hierarchy = hierarchy
                )
            }
            ButtonDemoState.Layout.IconOnly -> {
                OudsButton(
                    icon = icon,
                    onClick = {},
                    enabled = enabled,
                    style = style,
                    hierarchy = hierarchy
                )
            }
        }
    }
}

private fun Code.Builder.buttonDemoCodeSnippet(state: ButtonDemoState) {
    with(state) {
        coloredBoxCall(onColoredBox) {
            functionCall(OudsButton::class.simpleName.orEmpty()) {
                if (layout in listOf(ButtonDemoState.Layout.IconOnly, ButtonDemoState.Layout.TextAndIcon)) {
                    constructorCallArgument<OudsButton.Icon>("icon") {
                        painterArgument(R.drawable.ic_heart)
                        contentDescriptionArgument(R.string.app_components_common_icon_a11y)
                    }
                }
                if (layout in listOf(ButtonDemoState.Layout.TextOnly, ButtonDemoState.Layout.TextAndIcon)) {
                    labelArgument(label)
                }
                onClickArgument()
                enabledArgument(enabled)
                typedArgument("style", style)
                typedArgument("hierarchy", hierarchy)
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewButtonDemoScreen() = OudsPreview {
    ButtonDemoScreen()
}
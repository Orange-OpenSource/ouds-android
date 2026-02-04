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

package com.orange.ouds.app.ui.components.floatingactionbutton

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.Component
import com.orange.ouds.app.ui.components.contentDescriptionArgument
import com.orange.ouds.app.ui.components.onClickArgument
import com.orange.ouds.app.ui.components.painterArgument
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.LocalThemeDrawableResources
import com.orange.ouds.app.ui.utilities.ThemeDrawableResources
import com.orange.ouds.app.ui.utilities.composable.AppPreview
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChip
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChips
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextInput
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.core.component.OudsExtendedFloatingActionButton
import com.orange.ouds.core.component.OudsFloatingActionButton
import com.orange.ouds.core.component.OudsFloatingActionButtonAppearance
import com.orange.ouds.core.component.OudsFloatingActionButtonIcon
import com.orange.ouds.core.component.OudsLargeFloatingActionButton
import com.orange.ouds.core.component.OudsSmallFloatingActionButton

@Composable
fun FloatingActionButtonDemoScreen() {
    val state = rememberFloatingActionButtonDemoState()
    val themeDrawableResources = LocalThemeDrawableResources.current
    DemoScreen(
        description = stringResource(id = Component.FloatingActionButton.descriptionRes),
        bottomSheetContent = { FloatingActionButtonDemoBottomSheetContent(state = state) },
        codeSnippet = { floatingActionButtonDemoCodeSnippet(state = state, themeDrawableResources = themeDrawableResources) },
        demoContent = { FloatingActionButtonDemoContent(state = state) }
    )
}

@Composable
private fun FloatingActionButtonDemoBottomSheetContent(state: FloatingActionButtonDemoState) {
    with(state) {
        CustomizationFilterChips(
            applyTopPadding = false,
            label = stringResource(R.string.app_components_common_size_label),
            chipLabels = FloatingActionButtonDemoState.Size.entries.map { stringResource(it.labelRes) },
            selectedChipIndex = FloatingActionButtonDemoState.Size.entries.indexOf(size),
            onSelectionChange = { index -> size = FloatingActionButtonDemoState.Size.entries[index] }
        )
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_appearance_label),
            chipLabels = OudsFloatingActionButtonAppearance.entries.map { it.name },
            selectedChipIndex = OudsFloatingActionButtonAppearance.entries.indexOf(appearance),
            onSelectionChange = { index -> appearance = OudsFloatingActionButtonAppearance.entries[index] }
        )
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_layout_label),
            chips = FloatingActionButtonDemoState.Layout.entries.map { CustomizationFilterChip(stringResource(it.labelRes), enabled = it in enabledLayouts) },
            selectedChipIndex = FloatingActionButtonDemoState.Layout.entries.indexOf(layout),
            onSelectionChange = { index -> layout = FloatingActionButtonDemoState.Layout.entries[index] }
        )

        CustomizationTextInput(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_label_label),
            value = label,
            onValueChange = { value -> label = value },
            enabled = labelTextInputEnabled
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_floatingActionButton_expanded_label),
            checked = expanded,
            onCheckedChange = { expanded = it },
            enabled = expandedSwitchEnabled
        )
    }
}

@Composable
private fun FloatingActionButtonDemoContent(state: FloatingActionButtonDemoState) {
    val icon = OudsFloatingActionButtonIcon(
        painter = painterResource(id = LocalThemeDrawableResources.current.tipsAndTricks),
        contentDescription = stringResource(id = R.string.app_components_common_icon_a11y)
    )
    with(state) {
        when (size) {
            FloatingActionButtonDemoState.Size.Small -> {
                OudsSmallFloatingActionButton(
                    icon = icon,
                    onClick = {},
                    appearance = appearance
                )
            }
            FloatingActionButtonDemoState.Size.Medium -> {
                when (layout) {
                    FloatingActionButtonDemoState.Layout.IconOnly -> {
                        OudsFloatingActionButton(
                            icon = icon,
                            onClick = {},
                            appearance = appearance
                        )
                    }
                    FloatingActionButtonDemoState.Layout.TextAndIcon -> {
                        OudsExtendedFloatingActionButton(
                            label = label,
                            icon = icon,
                            onClick = {},
                            expanded = expanded,
                            appearance = appearance
                        )
                    }
                    FloatingActionButtonDemoState.Layout.TextOnly -> {
                        OudsExtendedFloatingActionButton(
                            label = label,
                            onClick = {},
                            appearance = appearance
                        )
                    }
                }
            }
            FloatingActionButtonDemoState.Size.Large -> {
                OudsLargeFloatingActionButton(
                    icon = icon,
                    onClick = {},
                    appearance = appearance
                )
            }
        }
    }
}

private fun Code.Builder.floatingActionButtonDemoCodeSnippet(state: FloatingActionButtonDemoState, themeDrawableResources: ThemeDrawableResources) {
    with(state) {
        val functionName = when (size) {
            FloatingActionButtonDemoState.Size.Small -> "OudsSmallFloatingActionButton"
            FloatingActionButtonDemoState.Size.Medium -> if (layout == FloatingActionButtonDemoState.Layout.IconOnly) "OudsFloatingActionButton" else "OudsExtendedFloatingActionButton"
            FloatingActionButtonDemoState.Size.Large -> "OudsLargeFloatingActionButton"
        }
        functionCall(functionName) {
            if (layout != FloatingActionButtonDemoState.Layout.IconOnly) {
                typedArgument("label", label)
            }
            if (layout != FloatingActionButtonDemoState.Layout.TextOnly) {
                constructorCallArgument<OudsFloatingActionButtonIcon>("icon") {
                    painterArgument(themeDrawableResources.tipsAndTricks)
                    contentDescriptionArgument(R.string.app_components_common_icon_a11y)
                }
            }
            if (layout == FloatingActionButtonDemoState.Layout.TextAndIcon && !expanded) {
                typedArgument("expanded", expanded)
            }
            onClickArgument()
            typedArgument("appearance", appearance)
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewFloatingActionButtonDemoScreen() = AppPreview {
    FloatingActionButtonDemoScreen()
}

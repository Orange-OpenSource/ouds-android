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

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.Component
import com.orange.ouds.app.ui.components.coloredBoxCall
import com.orange.ouds.app.ui.components.enabledArgument
import com.orange.ouds.app.ui.components.iconArgument
import com.orange.ouds.app.ui.components.labelArgument
import com.orange.ouds.app.ui.components.onClickArgument
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.LocalThemeDrawableResources
import com.orange.ouds.app.ui.utilities.ThemeDrawableResources
import com.orange.ouds.app.ui.utilities.composable.AppPreview
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChip
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChips
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextInput
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.app.ui.utilities.rememberUntintedIconPainter
import com.orange.ouds.core.component.OudsButton
import com.orange.ouds.core.component.OudsButtonAppearance
import com.orange.ouds.core.component.OudsButtonIcon
import com.orange.ouds.core.component.OudsButtonLoader
import com.orange.ouds.core.component.OudsSmallButton
import com.orange.ouds.foundation.ExperimentalOudsApi
import com.orange.ouds.theme.OudsVersion

@Composable
fun ButtonDemoScreen() {
    val state = rememberButtonDemoState()
    val themeDrawableResources = LocalThemeDrawableResources.current
    DemoScreen(
        description = stringResource(id = Component.Button.descriptionRes),
        bottomSheetContent = { ButtonDemoBottomSheetContent(state = state) },
        codeSnippet = { buttonDemoCodeSnippet(state = state, themeDrawableResources = themeDrawableResources) },
        demoContent = { ButtonDemoContent(state = state) },
        demoContentOnColoredBox = state.onColoredBox,
        version = OudsVersion.Component.Button
    )
}

@Composable
private fun ButtonDemoBottomSheetContent(state: ButtonDemoState) {
    with(state) {
        CustomizationSwitchItem(
            label = stringResource(R.string.app_common_enabled_tech),
            checked = enabled,
            onCheckedChange = { enabled = it },
            enabled = enabledSwitchEnabled
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_common_onColoredBackground_tech),
            checked = onColoredBox,
            onCheckedChange = { onColoredBox = it },
            enabled = onColoredBoxSwitchEnabled
        )
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_appearance_tech),
            chipLabels = OudsButtonAppearance.entries.map { it.name },
            selectedChipIndex = OudsButtonAppearance.entries.indexOf(appearance),
            onSelectionChange = { index -> appearance = OudsButtonAppearance.entries[index] }
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_common_loader_tech),
            checked = hasLoader,
            onCheckedChange = { hasLoader = it },
            enabled = loaderSwitchEnabled
        )
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_size_tech),
            chipLabels = ButtonDemoState.Size.entries.map { stringResource(it.labelRes) },
            selectedChipIndex = ButtonDemoState.Size.entries.indexOf(size),
            onSelectionChange = { index -> size = ButtonDemoState.Size.entries[index] }
        )
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_layout_tech),
            chipLabels = ButtonDemoState.Layout.entries.map { stringResource(it.labelRes) },
            selectedChipIndex = ButtonDemoState.Layout.entries.indexOf(layout),
            onSelectionChange = { index -> layout = ButtonDemoState.Layout.entries[index] }
        )
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_icon_tech),
            chips = ButtonDemoState.Icon.entries.map { CustomizationFilterChip(stringResource(it.labelRes), it in enabledIcons) },
            selectedChipIndex = ButtonDemoState.Icon.entries.indexOf(icon),
            onSelectionChange = { index -> icon = ButtonDemoState.Icon.entries[index] }
        )
        CustomizationTextInput(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_label_tech),
            value = label,
            onValueChange = { value -> label = value },
            enabled = labelTextInputEnabled
        )
    }
}

@Composable
@OptIn(ExperimentalOudsApi::class)
private fun ButtonDemoContent(state: ButtonDemoState) {
    with(state) {
        val painter = when (icon) {
            ButtonDemoState.Icon.Tinted -> painterResource(id = LocalThemeDrawableResources.current.tipsAndTricks)
            ButtonDemoState.Icon.Untinted -> rememberUntintedIconPainter()
        }
        val buttonIcon = OudsButtonIcon(
            painter = painter,
            contentDescription = stringResource(id = R.string.app_components_common_icon_a11y),
            tinted = icon == ButtonDemoState.Icon.Tinted
        )
        val loader = if (hasLoader) OudsButtonLoader(null) else null

        when (size) {
            ButtonDemoState.Size.Default -> when (layout) {
                ButtonDemoState.Layout.TextOnly -> OudsButton(
                    label = label,
                    onClick = {},
                    enabled = enabled,
                    loader = loader,
                    appearance = appearance
                )
                ButtonDemoState.Layout.TextAndIcon -> OudsButton(
                    icon = buttonIcon,
                    label = label,
                    onClick = {},
                    enabled = enabled,
                    loader = loader,
                    appearance = appearance
                )
                ButtonDemoState.Layout.IconOnly -> OudsButton(
                    icon = buttonIcon,
                    onClick = {},
                    enabled = enabled,
                    loader = loader,
                    appearance = appearance
                )
            }
            ButtonDemoState.Size.Small -> when (layout) {
                ButtonDemoState.Layout.TextOnly -> OudsSmallButton(
                    label = label,
                    onClick = {},
                    enabled = enabled,
                    loader = loader,
                    appearance = appearance
                )
                ButtonDemoState.Layout.TextAndIcon -> OudsSmallButton(
                    icon = buttonIcon,
                    label = label,
                    onClick = {},
                    enabled = enabled,
                    loader = loader,
                    appearance = appearance
                )
                ButtonDemoState.Layout.IconOnly -> OudsSmallButton(
                    icon = buttonIcon,
                    onClick = {},
                    enabled = enabled,
                    loader = loader,
                    appearance = appearance
                )
            }
        }
    }
}

private fun Code.Builder.buttonDemoCodeSnippet(state: ButtonDemoState, themeDrawableResources: ThemeDrawableResources) {
    with(state) {
        coloredBoxCall(onColoredBox) {
            val functionName = when (size) {
                ButtonDemoState.Size.Default -> "OudsButton"
                ButtonDemoState.Size.Small -> "OudsSmallButton"
            }
            functionCall(functionName) {
                if (layout in listOf(ButtonDemoState.Layout.IconOnly, ButtonDemoState.Layout.TextAndIcon)) {
                    iconArgument<OudsButtonIcon>(
                        "icon",
                        themeDrawableResources.tipsAndTricks,
                        R.string.app_components_common_icon_a11y,
                        icon == ButtonDemoState.Icon.Tinted
                    )
                }
                if (layout in listOf(ButtonDemoState.Layout.TextOnly, ButtonDemoState.Layout.TextAndIcon)) {
                    labelArgument(label)
                }
                onClickArgument()
                enabledArgument(enabled)
                if (hasLoader) {
                    constructorCallArgument<OudsButtonLoader>("loader") {
                        typedArgument("progress", null)
                    }
                }
                typedArgument("appearance", appearance)
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewButtonDemoScreen() = AppPreview {
    ButtonDemoScreen()
}
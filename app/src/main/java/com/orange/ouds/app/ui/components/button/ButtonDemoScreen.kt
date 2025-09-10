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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.navigation.compose.hiltViewModel
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.MainViewModel
import com.orange.ouds.app.ui.ThemeState
import com.orange.ouds.app.ui.components.Component
import com.orange.ouds.app.ui.components.coloredBoxCall
import com.orange.ouds.app.ui.components.contentDescriptionArgument
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
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.theme.OudsThemeSettings
import com.orange.ouds.theme.OudsVersion

@Composable
fun ButtonDemoScreen(themeState: ThemeState, viewModel: MainViewModel = hiltViewModel()) {
    ButtonDemoScreen(
        roundedCorners = themeState.settings.roundedButtonCorners.orElse { false },
        onRoundedCornersChange = { roundedCorners ->
            val themeSettings = themeState.settings.copy(roundedButtonCorners = roundedCorners)
            themeState.settings = themeSettings
            viewModel.storeUserThemeSettings(themeSettings)
        }
    )
}

@Composable
fun ButtonDemoScreen(roundedCorners: Boolean, onRoundedCornersChange: (Boolean) -> Unit) {
    val state = rememberButtonDemoState()
    DemoScreen(
        description = stringResource(id = Component.Button.descriptionRes),
        bottomSheetContent = {
            ButtonDemoBottomSheetContent(
                state = state,
                roundedCorners = roundedCorners,
                onRoundedCornersChange = onRoundedCornersChange
            )
        },
        codeSnippet = { buttonDemoCodeSnippet(state = state) },
        demoContent = { ButtonDemoContent(state = state) },
        demoContentOnColoredBox = state.onColoredBox,
        version = OudsVersion.Component.Button
    )
}

@Composable
private fun ButtonDemoBottomSheetContent(state: ButtonDemoState, roundedCorners: Boolean, onRoundedCornersChange: (Boolean) -> Unit) {
    with(state) {
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_common_roundedCorners_label),
            checked = roundedCorners,
            onCheckedChange = onRoundedCornersChange
        )
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
            label = stringResource(R.string.app_components_common_hierarchy_label),
            chipLabels = OudsButton.Hierarchy.entries.map { it.name },
            selectedChipIndex = OudsButton.Hierarchy.entries.indexOf(hierarchy),
            onSelectionChange = { id -> hierarchy = OudsButton.Hierarchy.entries[id] }
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_common_loader_label),
            checked = hasLoader,
            onCheckedChange = { hasLoader = it },
            enabled = loaderSwitchEnabled
        )
        CustomizationFilterChips(
            modifier = Modifier.padding(top = OudsTheme.spaces.fixed.medium),
            label = stringResource(R.string.app_components_common_layout_label),
            chipLabels = ButtonDemoState.Layout.entries.map { stringResource(it.labelRes) },
            selectedChipIndex = ButtonDemoState.Layout.entries.indexOf(layout),
            onSelectionChange = { id -> layout = ButtonDemoState.Layout.entries[id] }
        )
        CustomizationTextField(
            modifier = Modifier.padding(top = OudsTheme.spaces.fixed.medium),
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
        val loader = if (hasLoader) OudsButton.Loader(null) else null
        when (layout) {
            ButtonDemoState.Layout.TextOnly -> {
                OudsButton(
                    label = label,
                    onClick = {},
                    enabled = enabled,
                    loader = loader,
                    hierarchy = hierarchy
                )
            }
            ButtonDemoState.Layout.TextAndIcon -> {
                OudsButton(
                    icon = icon,
                    label = label,
                    onClick = {},
                    enabled = enabled,
                    loader = loader,
                    hierarchy = hierarchy
                )
            }
            ButtonDemoState.Layout.IconOnly -> {
                OudsButton(
                    icon = icon,
                    onClick = {},
                    enabled = enabled,
                    loader = loader,
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
                if (hasLoader) {
                    constructorCallArgument<OudsButton.Loader>("loader") {
                        typedArgument("progress", null)
                    }
                }
                typedArgument("hierarchy", hierarchy)
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewButtonDemoScreen() = with(OudsThemeSettings(roundedButtonCorners = false)) {
    OudsPreview(themeSettings = this) {
        ButtonDemoScreen(
            roundedCorners = roundedButtonCorners.orElse { false },
            onRoundedCornersChange = {}
        )
    }
}
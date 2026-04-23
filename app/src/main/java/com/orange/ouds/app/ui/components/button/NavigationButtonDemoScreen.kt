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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.Component
import com.orange.ouds.app.ui.components.coloredBoxCall
import com.orange.ouds.app.ui.components.enabledArgument
import com.orange.ouds.app.ui.components.labelArgument
import com.orange.ouds.app.ui.components.onClickArgument
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.composable.AppPreview
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChips
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextInput
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.core.component.OudsButtonLoader
import com.orange.ouds.core.component.OudsNavigationButton
import com.orange.ouds.core.component.OudsNavigationButtonAppearance
import com.orange.ouds.core.component.OudsNavigationButtonDefaults
import com.orange.ouds.core.component.OudsNavigationButtonLayout
import com.orange.ouds.theme.OudsVersion

@Composable
fun NavigationButtonDemoScreen() {
    val state = rememberNavigationButtonDemoState()
    DemoScreen(
        description = stringResource(id = Component.Button.descriptionRes),
        bottomSheetContent = { NavigationButtonDemoBottomSheetContent(state = state) },
        codeSnippet = { navigationButtonDemoCodeSnippet(state = state) },
        demoContent = { NavigationButtonDemoContent(state = state) },
        demoContentOnColoredBox = state.onColoredBox,
        version = OudsVersion.Component.NavigationButton
    )
}

@Composable
private fun NavigationButtonDemoBottomSheetContent(state: NavigationButtonDemoState) {
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
            label = stringResource(R.string.app_components_common_layout_tech),
            chipLabels = OudsNavigationButtonLayout.entries.map { it.name },
            selectedChipIndex = OudsNavigationButtonLayout.entries.indexOf(layout),
            onSelectionChange = { index -> layout = OudsNavigationButtonLayout.entries[index] }
        )
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_appearance_tech),
            chipLabels = OudsNavigationButtonAppearance.entries.map { it.name },
            selectedChipIndex = OudsNavigationButtonAppearance.entries.indexOf(appearance),
            onSelectionChange = { index -> appearance = OudsNavigationButtonAppearance.entries[index] }
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_common_loader_tech),
            checked = hasLoader,
            onCheckedChange = { hasLoader = it },
            enabled = loaderSwitchEnabled
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_common_iconOnlyLayout_tech),
            checked = iconOnly,
            onCheckedChange = { iconOnly = it }
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
private fun NavigationButtonDemoContent(state: NavigationButtonDemoState) {
    with(state) {
        val loader = if (hasLoader) OudsButtonLoader(null) else null
        if (iconOnly) {
            OudsNavigationButton(
                layout = layout,
                onClick = {},
                enabled = enabled,
                loader = loader,
                appearance = appearance
            )
        } else {
            OudsNavigationButton(
                label = label,
                layout = layout,
                onClick = {},
                enabled = enabled,
                loader = loader,
                appearance = appearance
            )
        }
    }
}

private fun Code.Builder.navigationButtonDemoCodeSnippet(state: NavigationButtonDemoState) {
    with(state) {
        coloredBoxCall(onColoredBox) {
            functionCall("OudsNavigationButton") {
                if (!iconOnly) {
                    labelArgument(label)
                }
                typedArgument("layout", layout)
                onClickArgument()
                if (!enabled) enabledArgument(enabled)
                if (hasLoader) {
                    constructorCallArgument<OudsButtonLoader>("loader") {
                        typedArgument("progress", null)
                    }
                }
                if (appearance != OudsNavigationButtonDefaults.Appearance) typedArgument("appearance", appearance)
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewNavigationButtonDemoScreen() = AppPreview {
    NavigationButtonDemoScreen()
}

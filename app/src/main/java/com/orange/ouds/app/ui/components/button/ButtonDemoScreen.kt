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
import com.orange.ouds.core.component.OudsButtonAppearance
import com.orange.ouds.core.component.OudsButtonIcon
import com.orange.ouds.core.component.OudsButtonLoader
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.theme.OudsVersion

@Composable
fun ButtonDemoScreen() {
    val state = rememberButtonDemoState()
    DemoScreen(
        description = stringResource(id = Component.Button.descriptionRes),
        bottomSheetContent = { ButtonDemoBottomSheetContent(state = state) },
        codeSnippet = { buttonDemoCodeSnippet(state = state) },
        demoContent = { ButtonDemoContent(state = state) },
        demoContentOnColoredBox = state.onColoredBox,
        version = OudsVersion.Component.Button
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
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_appearance_label),
            chipLabels = OudsButtonAppearance.entries.map { it.name },
            selectedChipIndex = OudsButtonAppearance.entries.indexOf(appearance),
            onSelectionChange = { id -> appearance = OudsButtonAppearance.entries[id] }
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_common_loader_label),
            checked = hasLoader,
            onCheckedChange = { hasLoader = it },
            enabled = loaderSwitchEnabled
        )
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_layout_label),
            chipLabels = ButtonDemoState.Layout.entries.map { stringResource(it.labelRes) },
            selectedChipIndex = ButtonDemoState.Layout.entries.indexOf(layout),
            onSelectionChange = { id -> layout = ButtonDemoState.Layout.entries[id] }
        )
        CustomizationTextField(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_label_label),
            value = label,
            onValueChange = { value -> label = value },
            enabled = labelTextFieldEnabled
        )
    }
}

@Composable
private fun ButtonDemoContent(state: ButtonDemoState) {
    val icon = OudsButtonIcon(
        painter = painterResource(id = R.drawable.ic_heart),
        contentDescription = stringResource(id = R.string.app_components_common_icon_a11y)
    )
    with(state) {
        val loader = if (hasLoader) OudsButtonLoader(null) else null
        when (layout) {
            ButtonDemoState.Layout.TextOnly -> {
                OudsButton(
                    label = label,
                    onClick = {},
                    enabled = enabled,
                    loader = loader,
                    appearance = appearance
                )
            }
            ButtonDemoState.Layout.TextAndIcon -> {
                OudsButton(
                    icon = icon,
                    label = label,
                    onClick = {},
                    enabled = enabled,
                    loader = loader,
                    appearance = appearance
                )
            }
            ButtonDemoState.Layout.IconOnly -> {
                OudsButton(
                    icon = icon,
                    onClick = {},
                    enabled = enabled,
                    loader = loader,
                    appearance = appearance
                )
            }
        }
    }
}

private fun Code.Builder.buttonDemoCodeSnippet(state: ButtonDemoState) {
    with(state) {
        coloredBoxCall(onColoredBox) {
            functionCall("OudsButton") {
                if (layout in listOf(ButtonDemoState.Layout.IconOnly, ButtonDemoState.Layout.TextAndIcon)) {
                    constructorCallArgument<OudsButtonIcon>("icon") {
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
private fun PreviewButtonDemoScreen() = OudsPreview {
    ButtonDemoScreen()
}
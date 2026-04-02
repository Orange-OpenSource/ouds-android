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

package com.orange.ouds.app.ui.components.radiobutton

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.controlitem.ControlItemCustomizations
import com.orange.ouds.app.ui.components.controlitem.controlItemArguments
import com.orange.ouds.app.ui.components.controlitem.controlItemCustomization
import com.orange.ouds.app.ui.components.onClickArgument
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.LocalThemeDrawableResources
import com.orange.ouds.app.ui.utilities.ThemeDrawableResources
import com.orange.ouds.app.ui.utilities.composable.AppPreview
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextInput
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.core.component.OudsControlItemIcon
import com.orange.ouds.core.component.OudsRadioButtonItem
import com.orange.ouds.core.component.common.OudsError
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.theme.OudsVersion

@Composable
fun RadioButtonItemDemoScreen() {
    val state = rememberRadioButtonItemDemoState()
    val themeDrawableResources = LocalThemeDrawableResources.current
    DemoScreen(
        bottomSheetContent = { RadioButtonItemDemoBottomSheetContent(state = state) },
        codeSnippet = { radioButtonItemDemoCodeSnippet(state = state, themeDrawableResources = themeDrawableResources) },
        demoContent = { RadioButtonItemDemoContent(state = state) },
        demoContentPaddingValues = PaddingValues(horizontal = OudsTheme.spaces.fixed.none),
        version = OudsVersion.Component.RadioButton
    )
}

@Composable
private fun RadioButtonItemDemoBottomSheetContent(state: RadioButtonItemDemoState) {
    with(state) {
        val extraCustomizations = listOf(
            controlItemCustomization(2) {
                CustomizationSwitchItem(
                    label = stringResource(R.string.app_components_common_outlined_tech),
                    checked = outlined,
                    onCheckedChange = { outlined = it },
                )
            },
            controlItemCustomization(9) {
                CustomizationTextInput(
                    applyTopPadding = true,
                    label = stringResource(R.string.app_components_radioButton_radioButtonItem_extraLabel_tech),
                    value = extraLabel.orEmpty(),
                    onValueChange = { value -> extraLabel = value }
                )
            }
        )
        ControlItemCustomizations(state = state, extraCustomizations = extraCustomizations)
    }
}

@Composable
private fun RadioButtonItemDemoContent(state: RadioButtonItemDemoState) {
    with(state) {
        Column(
            modifier = if (edgeToEdge) {
                Modifier
            } else {
                Modifier.padding(horizontal = OudsTheme.grids.margin)
            }.selectableGroup()
        ) {
            RadioButtonItemDemoState.values.forEachIndexed { index, radioButtonValue ->
                val isLastItem = index == RadioButtonItemDemoState.values.lastIndex
                OudsRadioButtonItem(
                    selected = radioButtonValue == selectedValue,
                    onClick = { selectedValue = radioButtonValue },
                    label = label,
                    extraLabel = extraLabel,
                    description = description,
                    icon = if (icon) OudsControlItemIcon(painterResource(id = LocalThemeDrawableResources.current.tipsAndTricks)) else null,
                    edgeToEdge = edgeToEdge,
                    divider = divider,
                    outlined = outlined,
                    reversed = reversed,
                    enabled = enabled,
                    readOnly = readOnly,
                    error = if (error) OudsError(if (isLastItem) errorMessage else "") else null,
                    constrainedMaxWidth = constrainedMaxWidth
                )
            }
        }
    }
}

private fun Code.Builder.radioButtonItemDemoCodeSnippet(state: RadioButtonItemDemoState, themeDrawableResources: ThemeDrawableResources) {
    comment("First radio button item")
    with(state) {
        functionCall("OudsRadioButtonItem") {
            typedArgument("selected", selectedValue == RadioButtonItemDemoState.values.first())
            onClickArgument {
                comment("Change selection")
            }
            controlItemArguments(state, themeDrawableResources)
            if (!extraLabel.isNullOrBlank()) typedArgument("extraLabel", extraLabel)
            if (outlined) typedArgument("outlined", outlined)
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewRadioButtonItemDemoScreen() = AppPreview {
    RadioButtonItemDemoScreen()
}
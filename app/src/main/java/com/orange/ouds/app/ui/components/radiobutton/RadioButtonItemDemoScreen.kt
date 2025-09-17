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
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextField
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.core.component.OudsControlItem
import com.orange.ouds.core.component.OudsRadioButtonItem
import com.orange.ouds.core.component.common.OudsError
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.theme.OudsVersion

@Composable
fun RadioButtonItemDemoScreen() {
    val state = rememberRadioButtonItemDemoState()
    DemoScreen(
        bottomSheetContent = { RadioButtonItemDemoBottomSheetContent(state = state) },
        codeSnippet = { radioButtonItemDemoCodeSnippet(state = state) },
        demoContent = { RadioButtonItemDemoContent(state = state) },
        demoContentPaddingValues = PaddingValues(),
        version = OudsVersion.Component.RadioButton
    )
}

@Composable
private fun RadioButtonItemDemoBottomSheetContent(state: RadioButtonItemDemoState) {
    with(state) {
        val extraCustomizations = listOf(
            controlItemCustomization(2) {
                CustomizationSwitchItem(
                    label = stringResource(R.string.app_components_radioButton_radioButtonItem_outlined_label),
                    checked = outlined,
                    onCheckedChange = { outlined = it },
                )
            },
            controlItemCustomization(8) {
                CustomizationTextField(
                    applyTopPadding = true,
                    label = stringResource(R.string.app_components_radioButton_radioButtonItem_additionalLabel_label),
                    value = additionalLabel.orEmpty(),
                    onValueChange = { value -> additionalLabel = value }
                )
            }
        )
        ControlItemCustomizations(state = state, extraCustomizations = extraCustomizations)
    }
}

@Composable
private fun RadioButtonItemDemoContent(state: RadioButtonItemDemoState) {
    with(state) {
        Column(modifier = Modifier.selectableGroup()) {
            RadioButtonItemDemoState.values.forEach { radioButtonValue ->
                OudsRadioButtonItem(
                    selected = radioButtonValue == selectedValue,
                    onClick = { selectedValue = radioButtonValue },
                    label = label,
                    additionalLabel = additionalLabel,
                    helperText = helperText,
                    icon = if (icon) OudsControlItem.Icon(painterResource(id = R.drawable.ic_heart)) else null,
                    divider = divider,
                    outlined = outlined,
                    reversed = reversed,
                    enabled = enabled,
                    readOnly = readOnly,
                    error = if (error) OudsError(stringResource(R.string.app_components_common_error_a11y)) else null
                )
            }
        }
    }
}

private fun Code.Builder.radioButtonItemDemoCodeSnippet(state: RadioButtonItemDemoState) {
    comment("First radio button item")
    with(state) {
        functionCall("OudsRadioButtonItem") {
            typedArgument("selected", selectedValue == RadioButtonItemDemoState.values.first())
            onClickArgument {
                comment("Change selection")
            }
            controlItemArguments(state)
            if (!additionalLabel.isNullOrBlank()) typedArgument("additionalLabel", additionalLabel)
            if (outlined) typedArgument("outlined", outlined)
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewRadioButtonItemDemoScreen() = OudsPreview {
    RadioButtonItemDemoScreen()
}
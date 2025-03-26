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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.controlitem.ControlItemDividerCustomization
import com.orange.ouds.app.ui.components.controlitem.ControlItemEnabledCustomization
import com.orange.ouds.app.ui.components.controlitem.ControlItemErrorCustomization
import com.orange.ouds.app.ui.components.controlitem.ControlItemHelperTextCustomization
import com.orange.ouds.app.ui.components.controlitem.ControlItemIconCustomization
import com.orange.ouds.app.ui.components.controlitem.ControlItemInvertedCustomization
import com.orange.ouds.app.ui.components.controlitem.ControlItemReadOnlyCustomization
import com.orange.ouds.app.ui.components.controlitem.ControlItemTextCustomization
import com.orange.ouds.app.ui.components.controlitem.controlItemArguments
import com.orange.ouds.app.ui.components.onClickArgument
import com.orange.ouds.app.ui.utilities.composable.CodeSnippet
import com.orange.ouds.app.ui.utilities.composable.CustomizationBottomSheetScaffold
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchListItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextField
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.app.ui.utilities.composable.LightDarkDemo
import com.orange.ouds.core.component.OudsControlItem
import com.orange.ouds.core.component.OudsRadioButtonItem
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.utilities.UiModePreviews

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RadioButtonItemDemoScreen() = DemoScreen(rememberRadioButtonItemDemoState()) {
    CustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
        bottomSheetContent = {
            ControlItemIconCustomization()
            ControlItemDividerCustomization()
            CustomizationSwitchListItem(
                label = stringResource(R.string.app_components_radioButton_radioButtonItem_outlined_label),
                checked = outlined,
                onCheckedChange = { outlined = it },
            )
            ControlItemInvertedCustomization()
            ControlItemEnabledCustomization()
            ControlItemReadOnlyCustomization()
            ControlItemErrorCustomization()
            ControlItemTextCustomization()
            CustomizationTextField(
                label = stringResource(R.string.app_components_radioButton_radioButtonItem_additionalText_label),
                value = additionalText.orEmpty(),
                onValueChange = { value -> additionalText = value }
            )
            ControlItemHelperTextCustomization()
        }
    ) {
        LightDarkDemo {
            RadioButtonItemDemo(state = this@DemoScreen)
        }

        RadioButtonItemDemoCodeSnippet(
            state = this@DemoScreen,
            modifier = Modifier
                .padding(all = OudsTheme.spaces.fixed.medium)
                .padding(top = OudsTheme.spaces.fixed.medium)
        )
    }
}

@Composable
private fun RadioButtonItemDemo(state: RadioButtonItemDemoState) {
    with(state) {
        Column(modifier = Modifier.selectableGroup()) {
            radioButtonItemDemoValues.forEach { radioButtonValue ->
                OudsRadioButtonItem(
                    selected = radioButtonValue == selectedValue,
                    onClick = { selectedValue = radioButtonValue },
                    text = text,
                    additionalText = additionalText,
                    helperText = helperText,
                    icon = if (icon) OudsControlItem.Icon(painterResource(id = R.drawable.ic_heart)) else null,
                    divider = divider,
                    outlined = outlined,
                    inverted = inverted,
                    enabled = enabled,
                    readOnly = readOnly,
                    error = error
                )
            }
        }
    }
}

@Composable
private fun RadioButtonItemDemoCodeSnippet(state: RadioButtonItemDemoState, modifier: Modifier = Modifier) {
    CodeSnippet(modifier = modifier) {
        with(state) {
            functionCall("OudsRadioButtonItem") {
                typedArgument("selected", selectedValue == radioButtonItemDemoValues.first())
                onClickArgument {
                    comment("Change selection")
                }
                controlItemArguments(state)
                if (!additionalText.isNullOrBlank()) typedArgument("additionalText", additionalText)
                if (outlined) typedArgument("outlined", outlined)
            }
        }
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewRadioButtonItemDemoScreen() = OudsPreview {
    RadioButtonItemDemoScreen()
}
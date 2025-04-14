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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.enabledArgument
import com.orange.ouds.app.ui.components.onClickArgument
import com.orange.ouds.app.ui.utilities.composable.CodeSnippet
import com.orange.ouds.app.ui.utilities.composable.CustomizationBottomSheetScaffold
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.app.ui.utilities.composable.LightDarkDemo
import com.orange.ouds.core.component.OudsRadioButton
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RadioButtonDemoScreen() = DemoScreen(rememberRadioButtonDemoState()) {
    CustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
        bottomSheetContent = {
            CustomizationSwitchItem(
                label = stringResource(R.string.app_common_enabled_label),
                checked = enabled,
                onCheckedChange = { enabled = it },
                enabled = enabledSwitchEnabled
            )
            CustomizationSwitchItem(
                label = stringResource(R.string.app_components_common_error_label),
                checked = error,
                onCheckedChange = { error = it },
                enabled = errorSwitchEnabled
            )
        }
    ) {
        LightDarkDemo {
            RadioButtonDemo(state = this@DemoScreen)
        }

        RadioButtonDemoCodeSnippet(
            state = this@DemoScreen,
            modifier = Modifier
                .padding(all = OudsTheme.spaces.fixed.medium)
                .padding(top = OudsTheme.spaces.fixed.medium)
        )
    }
}

@Composable
private fun RadioButtonDemo(state: RadioButtonDemoState) {
    Row(
        modifier = Modifier.selectableGroup(),
        horizontalArrangement = Arrangement.Center
    ) {
        with(state) {
            RadioButtonDemoState.values.forEach { value ->
                OudsRadioButton(
                    selected = value == selectedValue,
                    onClick = { selectedValue = value },
                    enabled = enabled,
                    error = error
                )
            }
        }
    }
}

@Composable
private fun RadioButtonDemoCodeSnippet(state: RadioButtonDemoState, modifier: Modifier = Modifier) {
    CodeSnippet(modifier = modifier) {
        with(state) {
            comment("First radio button")
            functionCall("OudsRadioButton") {
                typedArgument("selected", selectedValue == RadioButtonDemoState.values.first())
                onClickArgument {
                    comment("Change state")
                }
                enabledArgument(enabled)
                typedArgument("error", error)
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewRadioButtonDemoScreen() = OudsPreview {
    RadioButtonDemoScreen()
}
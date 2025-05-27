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
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.enabledArgument
import com.orange.ouds.app.ui.components.onClickArgument
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.core.component.OudsRadioButton
import com.orange.ouds.core.utilities.OudsPreview

@Composable
fun RadioButtonDemoScreen() {
    val state = rememberRadioButtonDemoState()
    DemoScreen(
        bottomSheetContent = { RadioButtonDemoBottomSheetContent(state = state) },
        codeSnippet = { radioButtonDemoCodeSnippet(state = state) },
        demoContent = { RadioButtonDemoContent(state = state) }
    )
}

@Composable
private fun RadioButtonDemoBottomSheetContent(state: RadioButtonDemoState) {
    with(state) {
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
}

@Composable
private fun RadioButtonDemoContent(state: RadioButtonDemoState) {
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

private fun Code.Builder.radioButtonDemoCodeSnippet(state: RadioButtonDemoState) {
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

@PreviewLightDark
@Composable
private fun PreviewRadioButtonDemoScreen() = OudsPreview {
    RadioButtonDemoScreen()
}
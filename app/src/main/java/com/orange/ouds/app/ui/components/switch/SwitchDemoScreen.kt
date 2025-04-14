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

package com.orange.ouds.app.ui.components.switch

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.enabledArgument
import com.orange.ouds.app.ui.utilities.composable.CodeSnippet
import com.orange.ouds.app.ui.utilities.composable.CustomizationBottomSheetScaffold
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.app.ui.utilities.composable.LightDarkDemo
import com.orange.ouds.core.component.OudsSwitch
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwitchDemoScreen() = DemoScreen(rememberSwitchDemoState()) {
    CustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
        bottomSheetContent = {
            CustomizationSwitchItem(
                label = stringResource(R.string.app_common_enabled_label),
                checked = enabled,
                onCheckedChange = { enabled = it }
            )
        }
    ) {
        LightDarkDemo {
            SwitchDemo(state = this@DemoScreen)
        }

        SwitchDemoCodeSnippet(
            state = this@DemoScreen,
            modifier = Modifier
                .padding(horizontal = OudsTheme.grids.margin, vertical = OudsTheme.spaces.fixed.medium)
                .padding(top = OudsTheme.spaces.fixed.medium)
        )
    }
}

@Composable
private fun SwitchDemo(state: SwitchDemoState) {
    with(state) {
        OudsSwitch(
            checked = checked,
            onCheckedChange = { checked = it },
            enabled = enabled
        )
    }
}

@Composable
private fun SwitchDemoCodeSnippet(state: SwitchDemoState, modifier: Modifier = Modifier) {
    CodeSnippet(modifier = modifier) {
        with(state) {
            functionCall("OudsSwitch") {
                typedArgument("checked", checked)
                lambdaArgument("onCheckedChange") {
                    comment("Change state")
                }
                enabledArgument(enabled)
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewSwitchDemoScreen() = OudsPreview {
    SwitchDemoScreen()
}

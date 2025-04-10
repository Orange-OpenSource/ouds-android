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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.controlitem.ControlItemDividerCustomization
import com.orange.ouds.app.ui.components.controlitem.ControlItemEnabledCustomization
import com.orange.ouds.app.ui.components.controlitem.ControlItemErrorCustomization
import com.orange.ouds.app.ui.components.controlitem.ControlItemHelperTextCustomization
import com.orange.ouds.app.ui.components.controlitem.ControlItemIconCustomization
import com.orange.ouds.app.ui.components.controlitem.ControlItemLabelCustomization
import com.orange.ouds.app.ui.components.controlitem.ControlItemReadOnlyCustomization
import com.orange.ouds.app.ui.components.controlitem.ControlItemReversedCustomization
import com.orange.ouds.app.ui.components.controlitem.controlItemArguments
import com.orange.ouds.app.ui.utilities.composable.CodeSnippet
import com.orange.ouds.app.ui.utilities.composable.CustomizationBottomSheetScaffold
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.app.ui.utilities.composable.LightDarkDemo
import com.orange.ouds.core.component.OudsControlItem
import com.orange.ouds.core.component.OudsSwitchItem
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwitchItemDemoScreen() = DemoScreen(rememberSwitchItemDemoState()) {
    CustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
        bottomSheetContent = {
            ControlItemIconCustomization()
            ControlItemDividerCustomization()
            ControlItemReversedCustomization()
            ControlItemEnabledCustomization()
            ControlItemReadOnlyCustomization()
            ControlItemErrorCustomization()
            ControlItemLabelCustomization()
            ControlItemHelperTextCustomization()
        }
    ) {
        LightDarkDemo {
            SwitchItemDemo(state = this@DemoScreen)
        }

        SwitchItemDemoCodeSnippet(
            state = this@DemoScreen,
            modifier = Modifier
                .padding(horizontal = OudsTheme.grids.margin, vertical = OudsTheme.spaces.fixed.medium)
                .padding(top = OudsTheme.spaces.fixed.medium)
        )
    }
}

@Composable
private fun SwitchItemDemo(state: SwitchItemDemoState) {
    with(state) {
        OudsSwitchItem(
            checked = checked,
            label = label,
            onCheckedChange = { checked = it },
            helperText = helperText,
            icon = if (icon) OudsControlItem.Icon(painterResource(id = R.drawable.ic_heart)) else null,
            divider = divider,
            reversed = reversed,
            enabled = enabled,
            readOnly = readOnly,
            error = error
        )
    }
}

@Composable
private fun SwitchItemDemoCodeSnippet(state: SwitchItemDemoState, modifier: Modifier = Modifier) {
    CodeSnippet(modifier = modifier) {
        with(state) {
            functionCall("OudsSwitchItem") {
                typedArgument("checked", checked)
                lambdaArgument("onCheckedChange") {
                    comment("Change state")
                }
                controlItemArguments(state)
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewSwitchItemDemoScreen() = OudsPreview {
    SwitchItemDemoScreen()
}

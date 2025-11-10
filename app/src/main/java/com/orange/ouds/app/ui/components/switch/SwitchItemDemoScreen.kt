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

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.controlitem.ControlItemCustomizations
import com.orange.ouds.app.ui.components.controlitem.controlItemArguments
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.DrawableResources
import com.orange.ouds.app.ui.utilities.LocalDrawableResources
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.core.component.OudsControlItemIcon
import com.orange.ouds.core.component.OudsSwitchItem
import com.orange.ouds.core.component.common.OudsError
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.theme.OudsVersion

@Composable
fun SwitchItemDemoScreen() {
    val state = rememberSwitchItemDemoState()
    val drawableResources = LocalDrawableResources.current
    DemoScreen(
        bottomSheetContent = { ControlItemCustomizations(state = state) },
        codeSnippet = { switchItemDemoCodeSnippet(state = state, drawableResources = drawableResources) },
        demoContent = { SwitchItemDemoContent(state = state) },
        demoContentPaddingValues = PaddingValues(),
        version = OudsVersion.Component.Switch
    )
}

@Composable
private fun SwitchItemDemoContent(state: SwitchItemDemoState) {
    with(state) {
        OudsSwitchItem(
            checked = checked,
            label = label,
            onCheckedChange = { checked = it },
            helperText = helperText,
            icon = if (icon) OudsControlItemIcon(painterResource(id = LocalDrawableResources.current.heartEmpty)) else null,
            divider = divider,
            reversed = reversed,
            enabled = enabled,
            readOnly = readOnly,
            error = if (error) OudsError(stringResource(R.string.app_components_common_error_a11y)) else null
        )
    }
}

private fun Code.Builder.switchItemDemoCodeSnippet(state: SwitchItemDemoState, drawableResources: DrawableResources) {
    with(state) {
        functionCall("OudsSwitchItem") {
            typedArgument("checked", checked)
            lambdaArgument("onCheckedChange") {
                comment("Change state")
            }
            controlItemArguments(state, drawableResources)
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewSwitchItemDemoScreen() = OudsPreview {
    SwitchItemDemoScreen()
}

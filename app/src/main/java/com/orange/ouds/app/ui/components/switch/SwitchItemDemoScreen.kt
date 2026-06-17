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
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.ui.components.controlitem.ControlItemCustomizations
import com.orange.ouds.app.ui.components.controlitem.controlItemArguments
import com.orange.ouds.app.ui.components.controlitem.controlItemError
import com.orange.ouds.app.ui.components.controlitem.controlItemIcon
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.LocalThemeDrawableResources
import com.orange.ouds.app.ui.utilities.ThemeDrawableResources
import com.orange.ouds.app.ui.utilities.composable.AppPreview
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.core.component.OudsSwitchItem
import com.orange.ouds.core.component.common.text.buildOudsAnnotatedErrorMessage
import com.orange.ouds.core.component.common.text.withStrong
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.theme.OudsVersion

@Composable
fun SwitchItemDemoScreen() {
    val state = rememberSwitchItemDemoState()
    val themeDrawableResources = LocalThemeDrawableResources.current
    DemoScreen(
        bottomSheetContent = { ControlItemCustomizations(state = state) },
        codeSnippet = { switchItemDemoCodeSnippet(state = state, themeDrawableResources = themeDrawableResources) },
        demoContent = { SwitchItemDemoContent(state = state) },
        demoContentPaddingValues = PaddingValues(horizontal = OudsTheme.spaces.fixed.none),
        version = OudsVersion.Component.Switch
    )
}

@Composable
private fun SwitchItemDemoContent(state: SwitchItemDemoState) {
    with(state) {
        OudsSwitchItem(
            modifier = if (edgeToEdge) Modifier else Modifier.padding(horizontal = OudsTheme.grids.margin),
            checked = checked,
            label = label,
            onCheckedChange = { checked = it },
            description = description,
            icon = controlItemIcon(this),
            edgeToEdge = edgeToEdge,
            divider = divider,
            reversed = reversed,
            enabled = enabled,
            readOnly = readOnly,
            error = controlItemError(
                state = this,
                isLastItem = true,
                annotatedMessage = buildOudsAnnotatedErrorMessage {
                    append("You must enable ")
                    withStrong { append("automatic payments") }
                    append(" to activate this offer.")
                }
            ),
            constrainedMaxWidth = constrainedMaxWidth
        )
    }
}

private fun Code.Builder.switchItemDemoCodeSnippet(state: SwitchItemDemoState, themeDrawableResources: ThemeDrawableResources) {
    with(state) {
        functionCall("OudsSwitchItem") {
            typedArgument("checked", checked)
            lambdaArgument("onCheckedChange") {
                comment("Change state")
            }
            controlItemArguments(state, themeDrawableResources, true)
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewSwitchItemDemoScreen() = AppPreview {
    SwitchItemDemoScreen()
}

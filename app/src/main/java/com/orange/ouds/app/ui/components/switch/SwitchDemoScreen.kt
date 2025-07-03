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

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.enabledArgument
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.core.component.OudsSwitch
import com.orange.ouds.core.utilities.OudsPreview

@Composable
fun SwitchDemoScreen() {
    val state = rememberSwitchDemoState()
    DemoScreen(
        bottomSheetContent = { SwitchDemoBottomSheetContent(state = state) },
        codeSnippet = { switchDemoCodeSnippet(state = state) },
        demoContent = { SwitchDemoContent(state = state) }
    )
}

@Composable
private fun SwitchDemoBottomSheetContent(state: SwitchDemoState) {
    with(state) {
        CustomizationSwitchItem(
            label = stringResource(R.string.app_common_enabled_label),
            checked = enabled,
            onCheckedChange = { enabled = it }
        )
    }
}

@Composable
private fun SwitchDemoContent(state: SwitchDemoState) {
    with(state) {
        val a11yDescription = stringResource(R.string.app_components_switch_switch_a11y)
        OudsSwitch(
            modifier = Modifier.semantics {
                contentDescription = a11yDescription
            },
            checked = checked,
            onCheckedChange = { checked = it },
            enabled = enabled
        )
    }
}

private fun Code.Builder.switchDemoCodeSnippet(state: SwitchDemoState) {
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

@PreviewLightDark
@Composable
private fun PreviewSwitchDemoScreen() = OudsPreview {
    SwitchDemoScreen()
}

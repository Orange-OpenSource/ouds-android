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

package com.orange.ouds.app.ui.components.checkbox

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.state.ToggleableState
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.Component
import com.orange.ouds.app.ui.components.enabledArgument
import com.orange.ouds.app.ui.components.onClickArgument
import com.orange.ouds.app.ui.utilities.composable.CodeSnippet
import com.orange.ouds.app.ui.utilities.composable.CustomizationBottomSheetScaffold
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchListItem
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.app.ui.utilities.composable.DetailScreenDescription
import com.orange.ouds.core.component.OudsTriStateCheckbox
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.OudsThemeTweak
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.utilities.UiModePreviews

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckboxDemoScreen() = DemoScreen(rememberCheckboxDemoState()) {
    CustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
        bottomSheetContent = {
            CustomizationSwitchListItem(
                label = stringResource(R.string.app_common_enabled_label),
                checked = enabled,
                onCheckedChange = { enabled = it },
                enabled = enabledSwitchEnabled
            )
            CustomizationSwitchListItem(
                label = stringResource(R.string.app_components_checkbox_error_label),
                checked = error,
                onCheckedChange = { error = it },
                enabled = errorSwitchEnabled
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            DetailScreenDescription(
                modifier = Modifier.padding(all = OudsTheme.spaces.fixed.medium),
                descriptionRes = Component.Checkbox.descriptionRes
            )
            val onClick = {
                toggleableState = when (toggleableState) {
                    ToggleableState.On -> ToggleableState.Off
                    ToggleableState.Off -> ToggleableState.Indeterminate
                    ToggleableState.Indeterminate -> ToggleableState.On
                }
            }
            CheckboxDemo(state = this@DemoScreen, onClick = onClick)
            OudsThemeTweak(OudsTheme.Tweak.Invert) {
                CheckboxDemo(state = this@DemoScreen, onClick = onClick)
            }
            CheckboxDemoCodeSnippet(
                state = this@DemoScreen,
                modifier = Modifier
                    .padding(all = OudsTheme.spaces.fixed.medium)
                    .padding(top = OudsTheme.spaces.fixed.medium)
            )
        }
    }
}

@Composable
private fun CheckboxDemo(state: CheckboxDemoState, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .background(OudsTheme.colorScheme.background.primary)
            .padding(all = OudsTheme.spaces.fixed.medium)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        with(state) {
            OudsTriStateCheckbox(
                state = toggleableState,
                onClick = onClick,
                enabled = enabled,
                error = error
            )
        }
    }
}

@Composable
private fun CheckboxDemoCodeSnippet(state: CheckboxDemoState, modifier: Modifier = Modifier) {
    CodeSnippet(modifier = modifier) {
        with(state) {
            functionCall("OudsTriStateCheckbox") {
                typedArgument("state", toggleableState)
                onClickArgument {
                    comment("Change state")
                }
                enabledArgument(enabled)
                typedArgument("error", error)
            }
        }
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewCheckboxDemoScreen() = OudsPreview {
    CheckboxDemoScreen()
}
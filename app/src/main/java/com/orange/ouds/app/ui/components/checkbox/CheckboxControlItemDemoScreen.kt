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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.state.ToggleableState
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.utilities.composable.CustomizationBottomSheetScaffold
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchListItem
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.core.component.OudsCheckboxControlItem
import com.orange.ouds.core.component.OudsTriStateCheckboxControlItem
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.OudsThemeTweak
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.utilities.UiModePreviews

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckboxControlItemDemoScreen() = DemoScreen(rememberCheckboxControlItemDemoState()) {
    CustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
        bottomSheetContent = {
            CustomizationSwitchListItem(
                label = stringResource(R.string.app_components_controlItem_helperText_label),
                checked = helperText,
                onCheckedChange = { helperText = it },
            )
            CustomizationSwitchListItem(
                label = stringResource(R.string.app_components_controlItem_icon_label),
                checked = icon,
                onCheckedChange = { icon = it },
            )
            CustomizationSwitchListItem(
                label = stringResource(R.string.app_components_controlItem_divider_label),
                checked = divider,
                onCheckedChange = { divider = it },
            )
            CustomizationSwitchListItem(
                label = stringResource(R.string.app_components_controlItem_inverted_label),
                checked = inverted,
                onCheckedChange = { inverted = it },
            )
            CustomizationSwitchListItem(
                label = stringResource(R.string.app_common_enabled_label),
                checked = enabled,
                onCheckedChange = { enabled = it },
                enabled = enabledSwitchEnabled
            )
            CustomizationSwitchListItem(
                label = stringResource(R.string.app_components_controlItem_readOnly_label),
                checked = readOnly,
                onCheckedChange = { readOnly = it },
                enabled = readOnlySwitchEnabled
            )
            CustomizationSwitchListItem(
                label = stringResource(R.string.app_components_common_error_label),
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
            CheckboxControlItemDemo(state = this@DemoScreen)
            OudsThemeTweak(OudsTheme.Tweak.Invert) {
                CheckboxControlItemDemo(state = this@DemoScreen)
            }
        }
    }
}

@Composable
private fun CheckboxControlItemDemo(state: CheckboxControlItemDemoState) {
    var toggleableState by rememberSaveable { mutableStateOf(ToggleableState.Off) }

    Box(
        modifier = Modifier
            .background(OudsTheme.colorScheme.background.primary)
            .padding(vertical = OudsTheme.spaces.fixed.medium)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        OudsTriStateCheckboxControlItem(
            state = toggleableState,
            onClick = {
                toggleableState = when (toggleableState) {
                    ToggleableState.On -> ToggleableState.Off
                    ToggleableState.Off -> ToggleableState.Indeterminate
                    ToggleableState.Indeterminate -> ToggleableState.On
                }
            },
            text = stringResource(id = R.string.app_components_common_text_label),
            helperText = if (state.helperText) stringResource(id = R.string.app_components_controlItem_helperText_label) else null,
            icon = if (state.icon) OudsCheckboxControlItem.Icon(painterResource(id = R.drawable.ic_heart)) else null,
            divider = state.divider,
            inverted = state.inverted,
            enabled = state.enabled,
            readOnly = state.readOnly,
            error = state.error
        )
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewCheckboxControlItemDemoScreen() = OudsPreview {
    CheckboxControlItemDemoScreen()
}
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
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.contentDescriptionArgument
import com.orange.ouds.app.ui.components.enabledArgument
import com.orange.ouds.app.ui.components.onClickArgument
import com.orange.ouds.app.ui.components.painterArgument
import com.orange.ouds.app.ui.components.textArgument
import com.orange.ouds.app.ui.utilities.composable.CodeSnippet
import com.orange.ouds.app.ui.utilities.composable.CustomizationBottomSheetScaffold
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchListItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextField
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.core.component.OudsButton
import com.orange.ouds.core.component.OudsCheckboxItem
import com.orange.ouds.core.component.OudsTriStateCheckboxItem
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.OudsThemeTweak
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.utilities.UiModePreviews

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckboxItemDemoScreen() = DemoScreen(rememberCheckboxItemDemoState()) {
    CustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
        bottomSheetContent = {
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
            CustomizationTextField(
                label = stringResource(R.string.app_components_common_text_label),
                value = text,
                onValueChange = { value -> text = value }
            )
            CustomizationTextField(
                label = stringResource(R.string.app_components_controlItem_helperText_label),
                value = helperText.orEmpty(),
                onValueChange = { value -> helperText = value })
        }
    ) {
        CheckboxItemDemo(state = this@DemoScreen)
        OudsThemeTweak(OudsTheme.Tweak.Invert) {
            CheckboxItemDemo(state = this@DemoScreen)
        }
        CheckboxItemDemoCodeSnippet(
            state = this@DemoScreen,
            modifier = Modifier
                .padding(all = OudsTheme.spaces.fixed.medium)
                .padding(top = OudsTheme.spaces.fixed.medium)
        )
    }
}

@Composable
private fun CheckboxItemDemo(state: CheckboxItemDemoState) {
    with(state) {
        Box(
            modifier = Modifier
                .background(OudsTheme.colorScheme.background.primary)
                .padding(vertical = OudsTheme.spaces.fixed.medium, horizontal = OudsTheme.grids.margin)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            OudsTriStateCheckboxItem(
                state = toggleableState,
                onClick = {
                    toggleableState = when (toggleableState) {
                        ToggleableState.On -> ToggleableState.Off
                        ToggleableState.Off -> ToggleableState.Indeterminate
                        ToggleableState.Indeterminate -> ToggleableState.On
                    }
                },
                text = text,
                helperText = helperText?.ifBlank { null },
                icon = if (icon) OudsCheckboxItem.Icon(painterResource(id = R.drawable.ic_heart)) else null,
                divider = divider,
                inverted = inverted,
                enabled = enabled,
                readOnly = readOnly,
                error = error
            )
        }
    }
}

@Composable
private fun CheckboxItemDemoCodeSnippet(state: CheckboxItemDemoState, modifier: Modifier = Modifier) {
    CodeSnippet(modifier = modifier) {
        with(state) {
            functionCall("OudsTriStateCheckboxItem") {
                typedArgument("state", toggleableState)
                textArgument(text)
                onClickArgument {
                    comment("Change state")
                }
                if (!helperText.isNullOrBlank()) typedArgument("helperText", helperText)
                if (icon) {
                    constructorCallArgument<OudsCheckboxItem.Icon>("icon") {
                        painterArgument(R.drawable.ic_heart)
                    }
                }
                if (!divider) typedArgument("divider", divider)
                if (inverted) typedArgument("inverted", inverted)
                if (!enabled) enabledArgument(enabled)
                if (readOnly) typedArgument("readOnly", readOnly)
                if (error) typedArgument("error", error)
            }
        }
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewCheckboxItemDemoScreen() = OudsPreview {
    CheckboxItemDemoScreen()
}
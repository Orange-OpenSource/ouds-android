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

package com.orange.ouds.app.ui.components.controlitem

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.enabledArgument
import com.orange.ouds.app.ui.components.painterArgument
import com.orange.ouds.app.ui.components.textArgument
import com.orange.ouds.app.ui.utilities.FunctionCall
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchListItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextField
import com.orange.ouds.core.component.OudsControlItem

@Composable
fun <T : ControlItemDemoState> T.ControlItemIconCustomization() {
    CustomizationSwitchListItem(
        label = stringResource(R.string.app_components_controlItem_icon_label),
        checked = icon,
        onCheckedChange = { icon = it },
    )
}

@Composable
fun <T : ControlItemDemoState> T.ControlItemDividerCustomization() {
    CustomizationSwitchListItem(
        label = stringResource(R.string.app_components_controlItem_divider_label),
        checked = divider,
        onCheckedChange = { divider = it },
    )
}

@Composable
fun <T : ControlItemDemoState> T.ControlItemInvertedCustomization() {
    CustomizationSwitchListItem(
        label = stringResource(R.string.app_components_controlItem_inverted_label),
        checked = inverted,
        onCheckedChange = { inverted = it },
    )
}

@Composable
fun <T : ControlItemDemoState> T.ControlItemEnabledCustomization() {
    CustomizationSwitchListItem(
        label = stringResource(R.string.app_common_enabled_label),
        checked = enabled,
        onCheckedChange = { enabled = it },
        enabled = enabledSwitchEnabled
    )
}

@Composable
fun <T : ControlItemDemoState> T.ControlItemReadOnlyCustomization() {
    CustomizationSwitchListItem(
        label = stringResource(R.string.app_components_controlItem_readOnly_label),
        checked = readOnly,
        onCheckedChange = { readOnly = it },
        enabled = readOnlySwitchEnabled
    )
}

@Composable
fun <T : ControlItemDemoState> T.ControlItemErrorCustomization() {
    CustomizationSwitchListItem(
        label = stringResource(R.string.app_components_common_error_label),
        checked = error,
        onCheckedChange = { error = it },
        enabled = errorSwitchEnabled
    )
}

@Composable
fun <T : ControlItemDemoState> T.ControlItemTextCustomization() {
    CustomizationTextField(
        label = stringResource(R.string.app_components_common_text_label),
        value = text,
        onValueChange = { value -> text = value }
    )
}

@Composable
fun <T : ControlItemDemoState> T.ControlItemHelperTextCustomization() {
    CustomizationTextField(
        label = stringResource(R.string.app_components_controlItem_helperText_label),
        value = helperText.orEmpty(),
        onValueChange = { value -> helperText = value }
    )
}

fun FunctionCall.Builder.controlItemArguments(state: ControlItemDemoState) = with(state) {
    textArgument(text)
    if (!helperText.isNullOrBlank()) typedArgument("helperText", helperText)
    if (icon) {
        constructorCallArgument<OudsControlItem.Icon>("icon") {
            painterArgument(R.drawable.ic_heart)
        }
    }
    if (!divider) typedArgument("divider", divider)
    if (inverted) typedArgument("inverted", inverted)
    if (!enabled) enabledArgument(enabled)
    if (readOnly) typedArgument("readOnly", readOnly)
    if (error) typedArgument("error", error)
}
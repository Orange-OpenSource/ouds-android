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
import com.orange.ouds.app.ui.components.labelArgument
import com.orange.ouds.app.ui.components.painterArgument
import com.orange.ouds.app.ui.utilities.FunctionCall
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextField
import com.orange.ouds.core.component.OudsControlItem

data class ControlItemCustomization(val index: Int, val content: @Composable () -> Unit)

fun controlItemCustomization(index: Int, content: @Composable () -> Unit) = ControlItemCustomization(index, content)

@Composable
fun <T : ControlItemDemoState> T.ControlItemCustomizations(extraCustomizations: List<ControlItemCustomization> = listOf()) {
    val customizations: MutableList<@Composable () -> Unit> = mutableListOf(
        { ControlItemIconCustomization() },
        { ControlItemDividerCustomization() },
        { ControlItemReversedCustomization() },
        { ControlItemEnabledCustomization() },
        { ControlItemReadOnlyCustomization() },
        { ControlItemErrorCustomization() },
        { ControlItemLabelCustomization() },
        { ControlItemHelperTextCustomization() }
    )
    extraCustomizations.forEach { (index, content) ->
        customizations.add(minOf(index, customizations.count()), content)
    }
    customizations.forEach { it() }
}

@Composable
private fun <T : ControlItemDemoState> T.ControlItemIconCustomization() {
    CustomizationSwitchItem(
        label = stringResource(R.string.app_components_controlItem_icon_label),
        checked = icon,
        onCheckedChange = { icon = it },
    )
}

@Composable
private fun <T : ControlItemDemoState> T.ControlItemDividerCustomization() {
    CustomizationSwitchItem(
        label = stringResource(R.string.app_components_controlItem_divider_label),
        checked = divider,
        onCheckedChange = { divider = it },
    )
}

@Composable
private fun <T : ControlItemDemoState> T.ControlItemReversedCustomization() {
    CustomizationSwitchItem(
        label = stringResource(R.string.app_components_controlItem_reversed_label),
        checked = reversed,
        onCheckedChange = { reversed = it },
    )
}

@Composable
private fun <T : ControlItemDemoState> T.ControlItemEnabledCustomization() {
    CustomizationSwitchItem(
        label = stringResource(R.string.app_common_enabled_label),
        checked = enabled,
        onCheckedChange = { enabled = it },
        enabled = enabledSwitchEnabled
    )
}

@Composable
private fun <T : ControlItemDemoState> T.ControlItemReadOnlyCustomization() {
    CustomizationSwitchItem(
        label = stringResource(R.string.app_components_controlItem_readOnly_label),
        checked = readOnly,
        onCheckedChange = { readOnly = it },
        enabled = readOnlySwitchEnabled
    )
}

@Composable
private fun <T : ControlItemDemoState> T.ControlItemErrorCustomization() {
    CustomizationSwitchItem(
        label = stringResource(R.string.app_components_common_error_label),
        checked = error,
        onCheckedChange = { error = it },
        enabled = errorSwitchEnabled
    )
}

@Composable
private fun <T : ControlItemDemoState> T.ControlItemLabelCustomization() {
    CustomizationTextField(
        label = stringResource(R.string.app_components_common_label_label),
        value = label,
        onValueChange = { value -> label = value }
    )
}

@Composable
private fun <T : ControlItemDemoState> T.ControlItemHelperTextCustomization() {
    CustomizationTextField(
        label = stringResource(R.string.app_components_controlItem_helperText_label),
        value = helperText.orEmpty(),
        onValueChange = { value -> helperText = value }
    )
}

fun FunctionCall.Builder.controlItemArguments(state: ControlItemDemoState) = with(state) {
    labelArgument(label)
    if (!helperText.isNullOrBlank()) typedArgument("helperText", helperText)
    if (icon) {
        constructorCallArgument<OudsControlItem.Icon>("icon") {
            painterArgument(R.drawable.ic_heart)
        }
    }
    if (divider) typedArgument("divider", divider)
    if (reversed) typedArgument("reversed", reversed)
    if (!enabled) enabledArgument(enabled)
    if (readOnly) typedArgument("readOnly", readOnly)
    if (error) typedArgument("error", error)
}
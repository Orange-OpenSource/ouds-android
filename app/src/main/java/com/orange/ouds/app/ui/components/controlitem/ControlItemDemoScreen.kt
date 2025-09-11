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
import com.orange.ouds.core.component.OudsControlItemIcon

data class ControlItemCustomization(val index: Int, val content: @Composable () -> Unit)

fun controlItemCustomization(index: Int, content: @Composable () -> Unit) = ControlItemCustomization(index, content)

@Composable
fun ControlItemCustomizations(state: ControlItemDemoState, extraCustomizations: List<ControlItemCustomization> = listOf()) {
    val customizations: MutableList<@Composable () -> Unit> = mutableListOf(
        { ControlItemIconCustomization(state = state) },
        { ControlItemDividerCustomization(state = state) },
        { ControlItemReversedCustomization(state = state) },
        { ControlItemEnabledCustomization(state = state) },
        { ControlItemReadOnlyCustomization(state = state) },
        { ControlItemErrorCustomization(state = state) },
        { ControlItemLabelCustomization(state = state) },
        { ControlItemHelperTextCustomization(state = state) }
    )
    extraCustomizations.forEach { (index, content) ->
        customizations.add(minOf(index, customizations.count()), content)
    }
    customizations.forEach { it() }
}

@Composable
private fun ControlItemIconCustomization(state: ControlItemDemoState) {
    with(state) {
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_controlItem_icon_label),
            checked = icon,
            onCheckedChange = { icon = it },
        )
    }
}

@Composable
private fun ControlItemDividerCustomization(state: ControlItemDemoState) {
    with(state) {
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_controlItem_divider_label),
            checked = divider,
            onCheckedChange = { divider = it },
        )
    }
}

@Composable
private fun ControlItemReversedCustomization(state: ControlItemDemoState) {
    with(state) {
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_controlItem_reversed_label),
            checked = reversed,
            onCheckedChange = { reversed = it },
        )
    }
}

@Composable
private fun ControlItemEnabledCustomization(state: ControlItemDemoState) {
    with(state) {
        CustomizationSwitchItem(
            label = stringResource(R.string.app_common_enabled_label),
            checked = enabled,
            onCheckedChange = { enabled = it },
            enabled = enabledSwitchEnabled
        )
    }
}

@Composable
private fun ControlItemReadOnlyCustomization(state: ControlItemDemoState) {
    with(state) {
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_common_readOnly_label),
            checked = readOnly,
            onCheckedChange = { readOnly = it },
            enabled = readOnlySwitchEnabled
        )
    }
}

@Composable
private fun ControlItemErrorCustomization(state: ControlItemDemoState) {
    with(state) {
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_common_error_label),
            checked = error,
            onCheckedChange = { error = it },
            enabled = errorSwitchEnabled
        )
    }
}

@Composable
private fun ControlItemLabelCustomization(state: ControlItemDemoState) {
    with(state) {
        CustomizationTextField(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_label_label),
            value = label,
            onValueChange = { value -> label = value }
        )
    }
}

@Composable
private fun ControlItemHelperTextCustomization(state: ControlItemDemoState) {
    with(state) {
        CustomizationTextField(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_helperText_label),
            value = helperText.orEmpty(),
            onValueChange = { value -> helperText = value }
        )
    }
}

fun FunctionCall.Builder.controlItemArguments(state: ControlItemDemoState) = with(state) {
    labelArgument(label)
    if (!helperText.isNullOrBlank()) typedArgument("helperText", helperText)
    if (icon) {
        constructorCallArgument<OudsControlItemIcon>("icon") {
            painterArgument(R.drawable.ic_heart)
        }
    }
    if (divider) typedArgument("divider", divider)
    if (reversed) typedArgument("reversed", reversed)
    if (!enabled) enabledArgument(enabled)
    if (readOnly) typedArgument("readOnly", readOnly)
    if (error) typedArgument("error", error)
}
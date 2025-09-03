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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.state.ToggleableState
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.controlitem.ControlItemDemoState

@Composable
fun rememberCheckboxItemDemoState(
    checkedValues: Pair<Boolean, Boolean> = Pair(false, false), // only used for checkbox item demo
    toggleableStateValues: Pair<ToggleableState, ToggleableState> = Pair(
        ToggleableState.Off,
        ToggleableState.Off
    ), // only used for indeterminate checkbox item demo
    icon: Boolean = false,
    divider: Boolean = true,
    reversed: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    error: Boolean = false,
    label: String = stringResource(id = R.string.app_components_common_label_label),
    helperText: String? = null
) = rememberSaveable(
    checkedValues,
    toggleableStateValues,
    icon,
    divider,
    reversed,
    enabled,
    readOnly,
    error,
    label,
    helperText,
    saver = CheckboxItemDemoState.Saver
) {
    CheckboxItemDemoState(checkedValues, toggleableStateValues, icon, divider, reversed, enabled, readOnly, error, label, helperText)
}

class CheckboxItemDemoState(
    checkedValues: Pair<Boolean, Boolean>,
    toggleableStateValues: Pair<ToggleableState, ToggleableState>,
    icon: Boolean,
    divider: Boolean,
    reversed: Boolean,
    enabled: Boolean,
    readOnly: Boolean,
    error: Boolean,
    label: String,
    helperText: String?
) : ControlItemDemoState(icon, divider, reversed, enabled, readOnly, error, label, helperText) {

    companion object {
        val Saver = listSaver(
            save = { state ->
                listOf(
                    state.checkedValues,
                    state.toggleableStateValues,
                    with(ControlItemDemoState.Saver) { save(state) }
                )
            },
            restore = { list: List<Any?> ->
                val controlItemDemoState = list[2]?.let { ControlItemDemoState.Saver.restore(it) }
                controlItemDemoState?.run {
                    @Suppress("UNCHECKED_CAST")
                    CheckboxItemDemoState(
                        list[0] as Pair<Boolean, Boolean>,
                        list[1] as Pair<ToggleableState, ToggleableState>,
                        icon,
                        divider,
                        reversed,
                        enabled,
                        readOnly,
                        error,
                        label,
                        helperText
                    )
                }
            }
        )
    }

    var checkedValues: Pair<Boolean, Boolean> by mutableStateOf(checkedValues)
    var toggleableStateValues: Pair<ToggleableState, ToggleableState> by mutableStateOf(toggleableStateValues)
}

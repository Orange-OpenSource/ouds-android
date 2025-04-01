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
import androidx.compose.runtime.saveable.mapSaver
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
    divider: Boolean = false,
    inverted: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    error: Boolean = false,
    text: String = stringResource(id = R.string.app_components_common_text_label),
    helperText: String? = null
) = rememberSaveable(
    checkedValues,
    toggleableStateValues,
    icon,
    divider,
    inverted,
    enabled,
    readOnly,
    error,
    text,
    helperText,
    saver = CheckboxItemDemoState.Saver
) {
    CheckboxItemDemoState(checkedValues, toggleableStateValues, icon, divider, inverted, enabled, readOnly, error, text, helperText)
}

class CheckboxItemDemoState(
    checkedValues: Pair<Boolean, Boolean>,
    toggleableStateValues: Pair<ToggleableState, ToggleableState>,
    icon: Boolean,
    divider: Boolean,
    inverted: Boolean,
    enabled: Boolean,
    readOnly: Boolean,
    error: Boolean,
    text: String,
    helperText: String?
) : ControlItemDemoState(icon, divider, inverted, enabled, readOnly, error, text, helperText) {
    companion object {
        val Saver = run {
            val checkedValuesKey = "checkedValues"
            val toggleableStateValuesKey = "toggleableStateValues"
            mapSaver(
                save = { state ->
                    mapOf(
                        checkedValuesKey to state.checkedValues,
                        toggleableStateValuesKey to state.toggleableStateValues,
                        IconKey to state.icon,
                        DividerKey to state.divider,
                        InvertedKey to state.inverted,
                        EnabledKey to state.enabled,
                        ReadOnlyKey to state.readOnly,
                        ErrorKey to state.error,
                        TextKey to state.text,
                        HelperTextKey to state.helperText
                    )
                },
                restore = { map ->
                    @Suppress("UNCHECKED_CAST")
                    CheckboxItemDemoState(
                        map[checkedValuesKey] as Pair<Boolean, Boolean>,
                        map[toggleableStateValuesKey] as Pair<ToggleableState, ToggleableState>,
                        map[IconKey] as Boolean,
                        map[DividerKey] as Boolean,
                        map[InvertedKey] as Boolean,
                        map[EnabledKey] as Boolean,
                        map[ReadOnlyKey] as Boolean,
                        map[ErrorKey] as Boolean,
                        map[TextKey] as String,
                        map[HelperTextKey] as String?
                    )
                }
            )
        }
    }

    var checkedValues: Pair<Boolean, Boolean> by mutableStateOf(checkedValues)
    var toggleableStateValues: Pair<ToggleableState, ToggleableState> by mutableStateOf(toggleableStateValues)
}

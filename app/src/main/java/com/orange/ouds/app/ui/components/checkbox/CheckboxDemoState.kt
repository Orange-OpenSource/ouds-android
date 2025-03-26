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
import androidx.compose.ui.state.ToggleableState

@Composable
fun rememberCheckboxDemoState(
    checkedValues: Pair<Boolean, Boolean> = Pair(false, false), // only used for checkbox demo
    toggleableStateValues: Pair<ToggleableState, ToggleableState> = Pair(ToggleableState.Off, ToggleableState.Off), // only used for indeterminate checkbox demo
    enabled: Boolean = true,
    error: Boolean = false
) = rememberSaveable(
    checkedValues,
    toggleableStateValues,
    enabled,
    error,
    saver = CheckboxDemoState.Saver
) {
    CheckboxDemoState(checkedValues, toggleableStateValues, enabled, error)
}

class CheckboxDemoState(
    checkedValues: Pair<Boolean, Boolean>,
    toggleableStateValues: Pair<ToggleableState, ToggleableState>,
    enabled: Boolean,
    error: Boolean
) {
    companion object {
        enum class CheckboxIdentifier {
            First, Second
        }

        val Saver = run {
            val checkedValuesKey = "checkedValues"
            val toggleableStateValuesKey = "toggleableStateValues"
            val enabledKey = "enabled"
            val errorKey = "error"
            mapSaver(
                save = { state ->
                    mapOf(
                        checkedValuesKey to state.checkedValues,
                        toggleableStateValuesKey to state.toggleableStateValues,
                        enabledKey to state.enabled,
                        errorKey to state.error,
                    )
                },
                restore = { map ->
                    @Suppress("UNCHECKED_CAST")
                    CheckboxDemoState(
                        map[checkedValuesKey] as Pair<Boolean, Boolean>,
                        map[toggleableStateValuesKey] as Pair<ToggleableState, ToggleableState>,
                        map[enabledKey] as Boolean,
                        map[errorKey] as Boolean
                    )
                }
            )
        }
    }

    var checkedValues: Pair<Boolean, Boolean> by mutableStateOf(checkedValues)

    var toggleableStateValues: Pair<ToggleableState, ToggleableState> by mutableStateOf(toggleableStateValues)

    var enabled: Boolean by mutableStateOf(enabled)

    var error: Boolean by mutableStateOf(error)

    val enabledSwitchEnabled: Boolean
        get() = !error

    val errorSwitchEnabled: Boolean
        get() = enabled

}
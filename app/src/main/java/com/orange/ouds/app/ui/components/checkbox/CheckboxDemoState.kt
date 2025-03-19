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
    checked: Boolean = false, // only used for checkbox demo
    toggleableState: ToggleableState = ToggleableState.Off, // only used for indeterminate checkbox demo
    enabled: Boolean = true,
    error: Boolean = false
) = rememberSaveable(checked, toggleableState, enabled, error, saver = CheckboxDemoState.Saver) {
    CheckboxDemoState(checked, toggleableState, enabled, error)
}

class CheckboxDemoState(
    checked: Boolean,
    toggleableState: ToggleableState,
    enabled: Boolean,
    error: Boolean
) {
    companion object {
        val Saver = run {
            val checkedKey = "checked"
            val toggleableStateKey = "toggleableState"
            val enabledKey = "enabled"
            val errorKey = "error"
            mapSaver(
                save = { state ->
                    mapOf(
                        checkedKey to state.checked,
                        toggleableStateKey to state.toggleableState,
                        enabledKey to state.enabled,
                        errorKey to state.error,
                    )
                },
                restore = { map ->
                    CheckboxDemoState(
                        map[checkedKey] as Boolean,
                        map[toggleableStateKey] as ToggleableState,
                        map[enabledKey] as Boolean,
                        map[errorKey] as Boolean
                    )
                }
            )
        }
    }

    var checked: Boolean by mutableStateOf(checked)

    var toggleableState: ToggleableState by mutableStateOf(toggleableState)

    var enabled: Boolean by mutableStateOf(enabled)

    var error: Boolean by mutableStateOf(error)

    val enabledSwitchEnabled: Boolean
        get() = !error

    val errorSwitchEnabled: Boolean
        get() = enabled

}

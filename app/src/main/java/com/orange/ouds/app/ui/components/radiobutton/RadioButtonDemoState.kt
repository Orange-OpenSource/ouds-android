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

package com.orange.ouds.app.ui.components.radiobutton

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.orange.ouds.app.ui.components.radiobutton.RadioButtonDemoState.Companion.values

@Composable
fun rememberRadioButtonDemoState(
    selectedValue: Int = values.first(),
    enabled: Boolean = true,
    readOnly: Boolean = false,
    error: Boolean = false
) = rememberSaveable(selectedValue, enabled, readOnly, error, saver = RadioButtonDemoState.Saver) {
    RadioButtonDemoState(selectedValue, enabled, readOnly, error)
}

class RadioButtonDemoState(
    selectedValue: Int,
    enabled: Boolean,
    readOnly: Boolean,
    error: Boolean
) {
    companion object {
        val values = listOf(1, 2)

        val Saver = run {
            val selectedValueKey = "selectedValue"
            val enabledKey = "enabled"
            val readOnlyKey = "readOnly"
            val errorKey = "error"
            mapSaver(
                save = { state ->
                    mapOf(
                        selectedValueKey to state.selectedValue,
                        enabledKey to state.enabled,
                        readOnlyKey to state.readOnly,
                        errorKey to state.error,
                    )
                },
                restore = { map ->
                    RadioButtonDemoState(
                        map[selectedValueKey] as Int,
                        map[enabledKey] as Boolean,
                        map[readOnlyKey] as Boolean,
                        map[errorKey] as Boolean
                    )
                }
            )
        }
    }

    var selectedValue: Int by mutableIntStateOf(selectedValue)

    var enabled: Boolean by mutableStateOf(enabled)

    var readOnly: Boolean by mutableStateOf(readOnly)

    var error: Boolean by mutableStateOf(error)

    val enabledSwitchEnabled: Boolean
        get() = !error

    val readOnlySwitchEnabled: Boolean
        get() = !error

    val errorSwitchEnabled: Boolean
        get() = enabled && !readOnly

}

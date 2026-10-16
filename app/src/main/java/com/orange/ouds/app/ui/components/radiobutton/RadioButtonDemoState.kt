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
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.orange.ouds.app.ui.components.radiobutton.RadioButtonDemoState.Companion.Values

@Composable
fun rememberRadioButtonDemoState(
    selectedValue: Int = Values.first(),
    enabled: Boolean = true,
    readOnly: Boolean = false,
    error: Boolean = false,
    skeleton: Boolean = false
) = rememberSaveable(selectedValue, enabled, readOnly, error, skeleton, saver = RadioButtonDemoState.Saver) {
    RadioButtonDemoState(selectedValue, enabled, readOnly, error, skeleton)
}

class RadioButtonDemoState(
    selectedValue: Int,
    enabled: Boolean,
    readOnly: Boolean,
    error: Boolean,
    skeleton: Boolean
) {
    companion object {

        val Values = listOf(1, 2)

        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        selectedValue,
                        enabled,
                        readOnly,
                        error,
                        skeleton
                    )
                }
            },
            restore = { list: List<Any?> ->
                RadioButtonDemoState(
                    list[0] as Int,
                    list[1] as Boolean,
                    list[2] as Boolean,
                    list[3] as Boolean,
                    list[4] as Boolean
                )
            }
        )
    }

    var selectedValue: Int by mutableIntStateOf(selectedValue)

    var enabled: Boolean by mutableStateOf(enabled)

    var readOnly: Boolean by mutableStateOf(readOnly)

    var error: Boolean by mutableStateOf(error)

    var skeleton: Boolean by mutableStateOf(skeleton)

    val enabledSwitchEnabled: Boolean
        get() = !error

    val readOnlySwitchEnabled: Boolean
        get() = !error

    val errorSwitchEnabled: Boolean
        get() = enabled && !readOnly
}

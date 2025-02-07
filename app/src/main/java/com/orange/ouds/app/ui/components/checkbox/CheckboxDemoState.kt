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

@Composable
fun rememberCheckboxDemoState(
    enabled: Boolean = true,
    error: Boolean = false
) = rememberSaveable(enabled, error, saver = CheckboxDemoState.Saver) {
    CheckboxDemoState(enabled, error)
}

class CheckboxDemoState(
    enabled: Boolean,
    error: Boolean
) {
    companion object {
        val Saver = run {
            val enabledKey = "enabled"
            val errorKey = "error"
            mapSaver(
                save = { state ->
                    mapOf(
                        enabledKey to state.enabled,
                        errorKey to state.error,
                    )
                },
                restore = { map ->
                    CheckboxDemoState(
                        map[enabledKey] as Boolean,
                        map[errorKey] as Boolean
                    )
                }
            )
        }
    }

    var enabled: Boolean by mutableStateOf(enabled)

    var error: Boolean by mutableStateOf(error)

    val enabledSwitchEnabled: Boolean
        get() = !error

    val errorSwitchEnabled: Boolean
        get() = enabled

}

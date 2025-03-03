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
fun rememberCheckboxItemDemoState(
    helperText: Boolean = false,
    icon: Boolean = false,
    divider: Boolean = true,
    inverted: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    error: Boolean = false
) = rememberSaveable(helperText, icon, divider, inverted, enabled, readOnly, error, saver = CheckboxItemDemoState.Saver) {
    CheckboxItemDemoState(helperText, icon, divider, inverted, enabled, readOnly, error)
}

class CheckboxItemDemoState(
    helperText: Boolean,
    icon: Boolean,
    divider: Boolean,
    inverted: Boolean,
    enabled: Boolean,
    readOnly: Boolean,
    error: Boolean
) {
    companion object {
        val Saver = run {
            val helperTextKey = "helperText"
            val iconKey = "icon"
            val dividerKey = "divider"
            val invertedKey = "inverted"
            val enabledKey = "enabled"
            val readOnlyKey = "readOnly"
            val errorKey = "error"
            mapSaver(
                save = { state ->
                    mapOf(
                        helperTextKey to state.helperText,
                        iconKey to state.icon,
                        dividerKey to state.divider,
                        invertedKey to state.inverted,
                        enabledKey to state.enabled,
                        readOnlyKey to state.readOnly,
                        errorKey to state.error,
                    )
                },
                restore = { map ->
                    CheckboxItemDemoState(
                        map[helperTextKey] as Boolean,
                        map[iconKey] as Boolean,
                        map[dividerKey] as Boolean,
                        map[invertedKey] as Boolean,
                        map[enabledKey] as Boolean,
                        map[readOnlyKey] as Boolean,
                        map[errorKey] as Boolean
                    )
                }
            )
        }
    }

    var helperText: Boolean by mutableStateOf(helperText)
    var icon: Boolean by mutableStateOf(icon)
    var divider: Boolean by mutableStateOf(divider)
    var inverted: Boolean by mutableStateOf(inverted)
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

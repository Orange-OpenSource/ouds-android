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

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.setValue

open class ControlItemDemoState(
    icon: Boolean,
    divider: Boolean,
    reversed: Boolean,
    enabled: Boolean,
    readOnly: Boolean,
    error: Boolean,
    label: String,
    helperText: String?
) {

    companion object {

        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
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
            },
            restore = { list ->
                ControlItemDemoState(
                    list[0] as Boolean,
                    list[1] as Boolean,
                    list[2] as Boolean,
                    list[3] as Boolean,
                    list[4] as Boolean,
                    list[5] as Boolean,
                    list[6] as String,
                    list[7] as String?
                )
            }
        )
    }

    var icon: Boolean by mutableStateOf(icon)
    var divider: Boolean by mutableStateOf(divider)
    var reversed: Boolean by mutableStateOf(reversed)
    var enabled: Boolean by mutableStateOf(enabled)
    var readOnly: Boolean by mutableStateOf(readOnly)
    var error: Boolean by mutableStateOf(error)
    var label: String by mutableStateOf(label)
    var helperText: String? by mutableStateOf(helperText)

    val enabledSwitchEnabled: Boolean
        get() = !error

    val readOnlySwitchEnabled: Boolean
        get() = !error

    val errorSwitchEnabled: Boolean
        get() = enabled && !readOnly
}
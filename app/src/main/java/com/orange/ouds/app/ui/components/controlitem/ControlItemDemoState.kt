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
import androidx.compose.runtime.setValue


abstract class ControlItemDemoState(
    icon: Boolean,
    divider: Boolean,
    inverted: Boolean,
    enabled: Boolean,
    readOnly: Boolean,
    error: Boolean,
    label: String,
    helperText: String?
) {
    companion object {
        const val IconKey = "icon"
        const val DividerKey = "divider"
        const val InvertedKey = "inverted"
        const val EnabledKey = "enabled"
        const val ReadOnlyKey = "readOnly"
        const val ErrorKey = "error"
        const val LabelKey = "label"
        const val HelperTextKey = "helperText"
    }

    var icon: Boolean by mutableStateOf(icon)
    var divider: Boolean by mutableStateOf(divider)
    var inverted: Boolean by mutableStateOf(inverted)
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
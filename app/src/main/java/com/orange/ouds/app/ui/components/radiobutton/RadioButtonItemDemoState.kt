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
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.controlitem.ControlItemDemoState

@Composable
fun rememberRadioButtonItemDemoState(
    selectedValue: Int = RadioButtonItemDemoState.values.first(),
    icon: Boolean = false,
    edgeToEdge: Boolean = true,
    divider: Boolean = false,
    outlined: Boolean = false,
    reversed: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    error: Boolean = false,
    errorMessage: String = stringResource(id = R.string.app_components_common_errorMessage_label),
    label: String = stringResource(id = R.string.app_components_common_label_label),
    extraLabel: String? = null,
    description: String? = null,
    constrainedMaxWidth: Boolean = false
) = rememberSaveable(
    selectedValue,
    icon,
    edgeToEdge,
    divider,
    outlined,
    reversed,
    enabled,
    readOnly,
    error,
    errorMessage,
    label,
    extraLabel,
    description,
    constrainedMaxWidth,
    saver = RadioButtonItemDemoState.Saver
) {
    RadioButtonItemDemoState(
        selectedValue,
        icon,
        edgeToEdge,
        divider,
        outlined,
        reversed,
        enabled,
        readOnly,
        error,
        errorMessage,
        label,
        extraLabel,
        description,
        constrainedMaxWidth
    )
}

class RadioButtonItemDemoState(
    selectedValue: Int,
    icon: Boolean,
    edgeToEdge: Boolean,
    divider: Boolean,
    outlined: Boolean,
    reversed: Boolean,
    enabled: Boolean,
    readOnly: Boolean,
    error: Boolean,
    errorMessage: String,
    label: String,
    extraLabel: String?,
    description: String?,
    constrainedMaxWidth: Boolean,
) : ControlItemDemoState(icon, edgeToEdge, divider, reversed, enabled, readOnly, error, errorMessage, label, description, constrainedMaxWidth) {

    companion object {
        val values = listOf(1, 2)
        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        selectedValue,
                        outlined,
                        extraLabel,
                        with(ControlItemDemoState.Saver) { save(state) }
                    )
                }
            },
            restore = { list: List<Any?> ->
                val controlItemDemoState = list[3]?.let { ControlItemDemoState.Saver.restore(it) }
                controlItemDemoState?.run {
                    RadioButtonItemDemoState(
                        list[0] as Int,
                        icon,
                        edgeToEdge,
                        divider,
                        list[1] as Boolean,
                        reversed,
                        enabled,
                        readOnly,
                        error,
                        errorMessage,
                        label,
                        list[2] as String?,
                        description,
                        constrainedMaxWidth
                    )
                }
            }
        )
    }

    var selectedValue: Int by mutableIntStateOf(selectedValue)
    var outlined: Boolean by mutableStateOf(outlined)
    var extraLabel: String? by mutableStateOf(extraLabel)
}

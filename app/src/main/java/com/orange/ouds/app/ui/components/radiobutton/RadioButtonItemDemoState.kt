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
    divider: Boolean = true,
    outlined: Boolean = false,
    reversed: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    error: Boolean = false,
    label: String = stringResource(id = R.string.app_components_common_label_label),
    additionalLabel: String? = null,
    helperText: String? = null
) = rememberSaveable(
    selectedValue,
    icon,
    divider,
    outlined,
    reversed,
    enabled,
    readOnly,
    error,
    label,
    additionalLabel,
    helperText,
    saver = RadioButtonItemDemoState.Saver
) {
    RadioButtonItemDemoState(selectedValue, icon, divider, outlined, reversed, enabled, readOnly, error, label, additionalLabel, helperText)
}

class RadioButtonItemDemoState(
    selectedValue: Int,
    icon: Boolean,
    divider: Boolean,
    outlined: Boolean,
    reversed: Boolean,
    enabled: Boolean,
    readOnly: Boolean,
    error: Boolean,
    label: String,
    additionalLabel: String?,
    helperText: String?
) : ControlItemDemoState(icon, divider, reversed, enabled, readOnly, error, label, helperText) {

    companion object {
        val values = listOf(1, 2)
        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        selectedValue,
                        outlined,
                        additionalLabel,
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
                        divider,
                        list[1] as Boolean,
                        reversed,
                        enabled,
                        readOnly,
                        error,
                        label,
                        list[2] as String?,
                        helperText
                    )
                }
            }
        )
    }

    var selectedValue: Int by mutableIntStateOf(selectedValue)
    var outlined: Boolean by mutableStateOf(outlined)
    var additionalLabel: String? by mutableStateOf(additionalLabel)
}

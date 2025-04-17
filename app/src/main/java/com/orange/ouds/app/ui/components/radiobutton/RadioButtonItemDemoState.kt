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
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.controlitem.ControlItemDemoState

@Composable
fun rememberRadioButtonItemDemoState(
    selectedValue: Int = RadioButtonItemDemoState.values.first(),
    icon: Boolean = false,
    divider: Boolean = false,
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
        val Saver = run {
            val selectedValueKey = "selectedValue"
            val outlinedKey = "outlined"
            val additionalLabelKey = "additionalLabel"
            mapSaver(
                save = { state ->
                    mapOf(
                        selectedValueKey to state.selectedValue,
                        IconKey to state.icon,
                        DividerKey to state.divider,
                        outlinedKey to state.outlined,
                        ReversedKey to state.reversed,
                        EnabledKey to state.enabled,
                        ReadOnlyKey to state.readOnly,
                        ErrorKey to state.error,
                        LabelKey to state.label,
                        additionalLabelKey to state.additionalLabel,
                        HelperTextKey to state.helperText
                    )
                },
                restore = { map ->
                    RadioButtonItemDemoState(
                        map[selectedValueKey] as Int,
                        map[IconKey] as Boolean,
                        map[DividerKey] as Boolean,
                        map[outlinedKey] as Boolean,
                        map[ReversedKey] as Boolean,
                        map[EnabledKey] as Boolean,
                        map[ReadOnlyKey] as Boolean,
                        map[ErrorKey] as Boolean,
                        map[LabelKey] as String,
                        map[additionalLabelKey] as String?,
                        map[HelperTextKey] as String?
                    )
                }
            )
        }
    }

    var selectedValue: Int by mutableIntStateOf(selectedValue)
    var outlined: Boolean by mutableStateOf(outlined)
    var additionalLabel: String? by mutableStateOf(additionalLabel)
}

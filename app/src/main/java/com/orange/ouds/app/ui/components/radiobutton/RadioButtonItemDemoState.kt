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
    inverted: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    error: Boolean = false,
    text: String = stringResource(id = R.string.app_components_common_text_label),
    additionalText: String? = null,
    helperText: String? = null
) = rememberSaveable(
    selectedValue,
    icon,
    divider,
    outlined,
    inverted,
    enabled,
    readOnly,
    error,
    text,
    additionalText,
    helperText,
    saver = RadioButtonItemDemoState.Saver
) {
    RadioButtonItemDemoState(selectedValue, icon, divider, outlined, inverted, enabled, readOnly, error, text, additionalText, helperText)
}

class RadioButtonItemDemoState(
    selectedValue: Int,
    icon: Boolean,
    divider: Boolean,
    outlined: Boolean,
    inverted: Boolean,
    enabled: Boolean,
    readOnly: Boolean,
    error: Boolean,
    text: String,
    additionalText: String?,
    helperText: String?
) : ControlItemDemoState(icon, divider, inverted, enabled, readOnly, error, text, helperText) {
    companion object {
        val values = listOf(1, 2)
        val Saver = run {
            val selectedValueKey = "selectedValue"
            val outlinedKey = "outlined"
            val additionalTextKey = "additionalText"
            mapSaver(
                save = { state ->
                    mapOf(
                        selectedValueKey to state.selectedValue,
                        IconKey to state.icon,
                        DividerKey to state.divider,
                        outlinedKey to state.outlined,
                        InvertedKey to state.inverted,
                        EnabledKey to state.enabled,
                        ReadOnlyKey to state.readOnly,
                        ErrorKey to state.error,
                        TextKey to state.text,
                        additionalTextKey to state.additionalText,
                        HelperTextKey to state.helperText
                    )
                },
                restore = { map ->
                    RadioButtonItemDemoState(
                        map[selectedValueKey] as Int,
                        map[IconKey] as Boolean,
                        map[DividerKey] as Boolean,
                        map[outlinedKey] as Boolean,
                        map[InvertedKey] as Boolean,
                        map[EnabledKey] as Boolean,
                        map[ReadOnlyKey] as Boolean,
                        map[ErrorKey] as Boolean,
                        map[TextKey] as String,
                        map[additionalTextKey] as String?,
                        map[HelperTextKey] as String?
                    )
                }
            )
        }
    }

    var selectedValue: Int by mutableIntStateOf(selectedValue)
    var outlined: Boolean by mutableStateOf(outlined)
    var additionalText: String? by mutableStateOf(additionalText)
}

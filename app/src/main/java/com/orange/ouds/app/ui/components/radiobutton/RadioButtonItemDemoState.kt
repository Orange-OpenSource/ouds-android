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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.state.ToggleableState
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.checkbox.CheckboxItemDemoState

val radioButtonItemDemoValues = listOf("First", "Second")

@Composable
fun rememberRadioButtonItemDemoState(
    selectedValue: String = radioButtonItemDemoValues.first(),
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
) = rememberSaveable(selectedValue, icon, divider, outlined, inverted, enabled, readOnly, error, text, additionalText, helperText, saver = RadioButtonItemDemoState.Saver) {
    RadioButtonItemDemoState(selectedValue, icon, divider, outlined, inverted, enabled, readOnly, error, text, additionalText, helperText)
}

class RadioButtonItemDemoState(
    selectedValue: String,
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
) {
    companion object {
        val Saver = run {
            val selectedValueKey = "selectedValue"
            val iconKey = "icon"
            val dividerKey = "divider"
            val outlinedKey = "outlined"
            val invertedKey = "inverted"
            val enabledKey = "enabled"
            val readOnlyKey = "readOnly"
            val errorKey = "error"
            val textKey = "text"
            val additionalTextKey = "additionalText"
            val helperTextKey = "helperText"
            mapSaver(
                save = { state ->
                    mapOf(
                        selectedValueKey to state.selectedValue,
                        iconKey to state.icon,
                        dividerKey to state.divider,
                        outlinedKey to state.outlined,
                        invertedKey to state.inverted,
                        enabledKey to state.enabled,
                        readOnlyKey to state.readOnly,
                        errorKey to state.error,
                        textKey to state.text,
                        additionalTextKey to state.additionalText,
                        helperTextKey to state.helperText
                    )
                },
                restore = { map ->
                    RadioButtonItemDemoState(
                        map[selectedValueKey] as String,
                        map[iconKey] as Boolean,
                        map[dividerKey] as Boolean,
                        map[outlinedKey] as Boolean,
                        map[invertedKey] as Boolean,
                        map[enabledKey] as Boolean,
                        map[readOnlyKey] as Boolean,
                        map[errorKey] as Boolean,
                        map[textKey] as String,
                        map[additionalTextKey] as String?,
                        map[helperTextKey] as String?
                    )
                }
            )
        }
    }

    var selectedValue: String by mutableStateOf(selectedValue)
    var icon: Boolean by mutableStateOf(icon)
    var divider: Boolean by mutableStateOf(divider)
    var outlined: Boolean by mutableStateOf(outlined)
    var inverted: Boolean by mutableStateOf(inverted)
    var enabled: Boolean by mutableStateOf(enabled)
    var readOnly: Boolean by mutableStateOf(readOnly)
    var error: Boolean by mutableStateOf(error)
    var text: String by mutableStateOf(text)
    var additionalText: String? by mutableStateOf(additionalText)
    var helperText: String? by mutableStateOf(helperText)


    val enabledSwitchEnabled: Boolean
        get() = !error

    val readOnlySwitchEnabled: Boolean
        get() = !error

    val errorSwitchEnabled: Boolean
        get() = enabled && !readOnly

}

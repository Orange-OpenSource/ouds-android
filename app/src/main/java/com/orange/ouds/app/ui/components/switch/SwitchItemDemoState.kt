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

package com.orange.ouds.app.ui.components.switch

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.controlitem.ControlItemDemoState

@Composable
fun rememberSwitchItemDemoState(
    checked: Boolean = false,
    icon: Boolean = false,
    divider: Boolean = false,
    inverted: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    error: Boolean = false,
    text: String = stringResource(id = R.string.app_components_common_label_label),
    helperText: String? = null
) = rememberSaveable(checked, icon, divider, inverted, enabled, readOnly, error, text, helperText, saver = SwitchItemDemoState.Saver) {
    SwitchItemDemoState(checked, icon, divider, inverted, enabled, readOnly, error, text, helperText)
}

class SwitchItemDemoState(
    checked: Boolean,
    icon: Boolean,
    divider: Boolean,
    inverted: Boolean,
    enabled: Boolean,
    readOnly: Boolean,
    error: Boolean,
    text: String,
    helperText: String?
) : ControlItemDemoState(icon, divider, inverted, enabled, readOnly, error, text, helperText) {
    companion object {
        val Saver = run {
            val checkedKey = "checked"
            mapSaver(
                save = { state ->
                    mapOf(
                        checkedKey to state.checked,
                        IconKey to state.icon,
                        DividerKey to state.divider,
                        ReversedKey to state.reversed,
                        EnabledKey to state.enabled,
                        ReadOnlyKey to state.readOnly,
                        ErrorKey to state.error,
                        LabelKey to state.label,
                        HelperTextKey to state.helperText
                    )
                },
                restore = { map ->
                    SwitchItemDemoState(
                        map[checkedKey] as Boolean,
                        map[IconKey] as Boolean,
                        map[DividerKey] as Boolean,
                        map[ReversedKey] as Boolean,
                        map[EnabledKey] as Boolean,
                        map[ReadOnlyKey] as Boolean,
                        map[ErrorKey] as Boolean,
                        map[LabelKey] as String,
                        map[HelperTextKey] as String?
                    )
                }
            )
        }
    }

    var checked: Boolean by mutableStateOf(checked)
}

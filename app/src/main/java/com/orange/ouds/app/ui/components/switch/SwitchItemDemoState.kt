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
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.controlitem.ControlItemDemoState

@Composable
fun rememberSwitchItemDemoState(
    checked: Boolean = false,
    icon: Boolean = false,
    divider: Boolean = true,
    reversed: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    error: Boolean = false,
    text: String = stringResource(id = R.string.app_components_common_label_label),
    helperText: String? = null
) = rememberSaveable(checked, icon, divider, reversed, enabled, readOnly, error, text, helperText, saver = SwitchItemDemoState.Saver) {
    SwitchItemDemoState(checked, icon, divider, reversed, enabled, readOnly, error, text, helperText)
}

class SwitchItemDemoState(
    checked: Boolean,
    icon: Boolean,
    divider: Boolean,
    reversed: Boolean,
    enabled: Boolean,
    readOnly: Boolean,
    error: Boolean,
    text: String,
    helperText: String?
) : ControlItemDemoState(icon, divider, reversed, enabled, readOnly, error, text, helperText) {

    companion object {

        val Saver = listSaver(
            save = { state ->
                listOf(
                    state.checked,
                    with(ControlItemDemoState.Saver) { save(state) }
                )
            },
            restore = { list: List<Any?> ->
                val controlItemDemoState = list[1]?.let { ControlItemDemoState.Saver.restore(it) }
                controlItemDemoState?.run {
                    SwitchItemDemoState(
                        list[0] as Boolean,
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
            }
        )
    }

    var checked: Boolean by mutableStateOf(checked)
}

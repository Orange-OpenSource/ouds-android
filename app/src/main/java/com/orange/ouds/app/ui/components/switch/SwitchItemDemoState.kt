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
    edgeToEdge: Boolean = true,
    divider: Boolean = false,
    reversed: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    error: Boolean = false,
    errorMessage: String = stringResource(id = R.string.app_components_common_errorMessage_label),
    text: String = stringResource(id = R.string.app_components_common_label_tech),
    description: String? = null,
    constrainedMaxWidth: Boolean = false
) = rememberSaveable(
    checked,
    icon,
    edgeToEdge,
    divider,
    reversed,
    enabled,
    readOnly,
    error,
    errorMessage,
    text,
    description,
    constrainedMaxWidth,
    saver = SwitchItemDemoState.Saver
) {
    SwitchItemDemoState(checked, icon, edgeToEdge, divider, reversed, enabled, readOnly, error, errorMessage, text, description, constrainedMaxWidth)
}

class SwitchItemDemoState(
    checked: Boolean,
    icon: Boolean,
    edgeToEdge: Boolean,
    divider: Boolean,
    reversed: Boolean,
    enabled: Boolean,
    readOnly: Boolean,
    error: Boolean,
    errorMessage: String,
    text: String,
    description: String?,
    constrainedMaxWidth: Boolean
) : ControlItemDemoState(icon, edgeToEdge, divider, reversed, enabled, readOnly, error, errorMessage, text, description, constrainedMaxWidth) {

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
                        edgeToEdge,
                        divider,
                        reversed,
                        enabled,
                        readOnly,
                        error,
                        errorMessage,
                        label,
                        description,
                        constrainedMaxWidth
                    )
                }
            }
        )
    }

    var checked: Boolean by mutableStateOf(checked)
}

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

@Composable
fun rememberSwitchDemoState(
    checked: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    skeleton: Boolean = false
) = rememberSaveable(checked, enabled, readOnly, skeleton, saver = SwitchDemoState.Saver) {
    SwitchDemoState(checked, enabled, readOnly, skeleton)
}

class SwitchDemoState(
    checked: Boolean,
    enabled: Boolean,
    readOnly: Boolean,
    skeleton: Boolean
) {

    companion object {

        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        checked,
                        enabled,
                        readOnly,
                        skeleton
                    )
                }
            },
            restore = { list: List<Any?> ->
                SwitchDemoState(
                    list[0] as Boolean,
                    list[1] as Boolean,
                    list[2] as Boolean,
                    list[3] as Boolean
                )
            }
        )
    }

    var checked: Boolean by mutableStateOf(checked)

    var enabled: Boolean by mutableStateOf(enabled)

    var readOnly: Boolean by mutableStateOf(readOnly)

    var skeleton: Boolean by mutableStateOf(skeleton)
}

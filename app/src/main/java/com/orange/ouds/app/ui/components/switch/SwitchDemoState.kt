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

@Composable
fun rememberSwitchDemoState(
    checked: Boolean = false,
    enabled: Boolean = true
) = rememberSaveable(checked, enabled, saver = SwitchDemoState.Saver) {
    SwitchDemoState(checked, enabled)
}

class SwitchDemoState(
    checked: Boolean,
    enabled: Boolean
) {

    companion object {

        val Saver = run {
            val checkedKey = "checked"
            val enabledKey = "enabled"
            mapSaver(
                save = { state ->
                    mapOf(
                        checkedKey to state.checked,
                        enabledKey to state.enabled
                    )
                },
                restore = { map ->
                    SwitchDemoState(
                        map[checkedKey] as Boolean,
                        map[enabledKey] as Boolean
                    )
                }
            )
        }
    }

    var checked: Boolean by mutableStateOf(checked)

    var enabled: Boolean by mutableStateOf(enabled)
}

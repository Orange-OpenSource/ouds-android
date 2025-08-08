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

package com.orange.ouds.app.ui.components.tag

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.R


@Composable
fun rememberInputTagDemoState(
    label: String = stringResource(id = R.string.app_components_tag_label),
    enabled: Boolean = true,
) = rememberSaveable(label, enabled, saver = InputTagDemoState.Saver) {
    InputTagDemoState(label, enabled)
}

class InputTagDemoState(
    label: String,
    enabled: Boolean
) {

    companion object {

        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        label,
                        enabled
                    )
                }
            },
            restore = { list: List<Any?> ->
                InputTagDemoState(
                    list[0] as String,
                    list[1] as Boolean
                )
            }
        )
    }

    var label: String by mutableStateOf(label)

    var enabled: Boolean by mutableStateOf(enabled)

}

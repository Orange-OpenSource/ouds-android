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

package com.orange.ouds.app.ui.components.bottomsheet

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Composable
fun rememberModalBottomSheetDemoState(
    dragHandle: Boolean = true,
    sheetGesturesEnabled: Boolean = true
) = rememberSaveable(dragHandle, sheetGesturesEnabled, saver = ModalBottomSheetDemoState.Saver) {
    ModalBottomSheetDemoState(dragHandle, sheetGesturesEnabled)
}

class ModalBottomSheetDemoState(
    dragHandle: Boolean,
    sheetGesturesEnabled: Boolean
) {
    companion object {
        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        dragHandle,
                        sheetGesturesEnabled
                    )
                }
            },
            restore = { list: List<Any?> ->
                ModalBottomSheetDemoState(
                    list[0] as Boolean,
                    list[1] as Boolean
                )
            }
        )
    }

    var dragHandle: Boolean by mutableStateOf(dragHandle)
    var sheetGesturesEnabled: Boolean by mutableStateOf(sheetGesturesEnabled)
}
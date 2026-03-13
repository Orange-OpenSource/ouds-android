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

import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.Dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberBottomSheetScaffoldDemoState(
    sheetPeekHeight: Dp = BottomSheetDefaults.SheetPeekHeight,
    sheetDragHandle: Boolean = true,
    sheetSwipeEnabled: Boolean = true
) = rememberSaveable(sheetPeekHeight, sheetDragHandle, sheetSwipeEnabled, saver = BottomSheetScaffoldDemoState.Saver) {
    BottomSheetScaffoldDemoState(sheetPeekHeight, sheetDragHandle, sheetSwipeEnabled)
}

class BottomSheetScaffoldDemoState(
    sheetPeakHeight: Dp,
    sheetDragHandle: Boolean,
    sheetSwipeEnabled: Boolean
) {
    companion object {
        val Saver = listSaver(
            save = { state ->
                listOf(
                    state.sheetPeekHeight.value,
                    state.sheetDragHandle,
                    state.sheetSwipeEnabled
                )
            },
            restore = { list ->
                BottomSheetScaffoldDemoState(
                    Dp(list[0] as Float),
                    list[1] as Boolean,
                    list[2] as Boolean
                )
            }
        )
    }

    var sheetPeekHeight: Dp by mutableStateOf(sheetPeakHeight)
    var sheetDragHandle: Boolean by mutableStateOf(sheetDragHandle)
    var sheetSwipeEnabled: Boolean by mutableStateOf(sheetSwipeEnabled)
}
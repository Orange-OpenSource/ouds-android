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
import com.orange.ouds.app.ui.components.bottomsheet.BottomSheetScaffoldDemoState.SheetState
import com.orange.ouds.app.ui.utilities.toIntString

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberBottomSheetScaffoldDemoState(
    sheetState: SheetState = SheetState.PartiallyExpanded,
    sheetDragHandle: Boolean = true,
    sheetSwipeEnabled: Boolean = true,
    sheetPeekHeight: Dp = BottomSheetDefaults.SheetPeekHeight
) = rememberSaveable(sheetState, sheetDragHandle, sheetSwipeEnabled, sheetPeekHeight, saver = BottomSheetScaffoldDemoState.Saver) {
    BottomSheetScaffoldDemoState(sheetState, sheetDragHandle, sheetSwipeEnabled, sheetPeekHeight)
}

class BottomSheetScaffoldDemoState(
    sheetState: SheetState,
    sheetDragHandle: Boolean,
    sheetSwipeEnabled: Boolean,
    sheetPeakHeight: Dp
) {
    companion object {
        val Saver = listSaver(
            save = { state ->
                listOf(
                    state.sheetState,
                    state.sheetDragHandle,
                    state.sheetSwipeEnabled,
                    state.sheetPeekHeight.value
                )
            },
            restore = { list: List<Any?> ->
                BottomSheetScaffoldDemoState(
                    list[0] as SheetState,
                    list[1] as Boolean,
                    list[2] as Boolean,
                    Dp(list[3] as Float)
                )
            }
        )
    }

    var sheetDragHandle: Boolean by mutableStateOf(sheetDragHandle)
    var sheetPeekHeight: Dp by mutableStateOf(sheetPeakHeight)
    var sheetSwipeEnabled: Boolean by mutableStateOf(sheetSwipeEnabled)
    var sheetState: SheetState by mutableStateOf(sheetState)

    val sheetPeekHeightDisplayValue
        @Composable
        get() = sheetPeekHeight.toIntString()

    enum class SheetState {
        Expanded, PartiallyExpanded,
    }
}
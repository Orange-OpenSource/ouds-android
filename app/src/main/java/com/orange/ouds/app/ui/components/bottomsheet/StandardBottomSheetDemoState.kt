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
import com.orange.ouds.app.ui.components.bottomsheet.StandardBottomSheetDemoState.SheetValue
import com.orange.ouds.app.ui.utilities.toIntString

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberStandardBottomSheetDemoState(
    sheetValue: SheetValue = SheetValue.PartiallyExpanded,
    sheetDragHandle: Boolean = true,
    sheetSwipeEnabled: Boolean = true,
    sheetPeekHeight: Dp = BottomSheetDefaults.SheetPeekHeight
) = rememberSaveable(sheetValue, sheetDragHandle, sheetSwipeEnabled, sheetPeekHeight, saver = StandardBottomSheetDemoState.Saver) {
    StandardBottomSheetDemoState(sheetValue, sheetDragHandle, sheetSwipeEnabled, sheetPeekHeight)
}

class StandardBottomSheetDemoState(
    sheetValue: SheetValue,
    sheetDragHandle: Boolean,
    sheetSwipeEnabled: Boolean,
    sheetPeakHeight: Dp
) {
    companion object {
        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        sheetValue,
                        sheetDragHandle,
                        sheetSwipeEnabled,
                        sheetPeekHeight.value
                    )
                }
            },
            restore = { list: List<Any?> ->
                StandardBottomSheetDemoState(
                    list[0] as SheetValue,
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
    var sheetValue: SheetValue by mutableStateOf(sheetValue)

    val sheetPeekHeightDisplayValue
        @Composable
        get() = sheetPeekHeight.toIntString()

    enum class SheetValue {
        Expanded, PartiallyExpanded,
    }
}
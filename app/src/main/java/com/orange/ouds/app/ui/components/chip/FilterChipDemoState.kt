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

package com.orange.ouds.app.ui.components.chip

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.R

@Composable
fun rememberFilterChipDemoState(
    selectedValues: List<Boolean> = List(ChipDemoState.ChipCount) { it == 0 },
    enabled: Boolean = true,
    layout: ChipDemoState.Layout = ChipDemoState.Layout.entries.first(),
    label: String = stringResource(R.string.app_components_chip_filterChip_chipContent_label)
) = rememberSaveable(selectedValues, enabled, layout, saver = FilterChipDemoState.Saver) {
    FilterChipDemoState(selectedValues, enabled, layout, label)
}

class FilterChipDemoState(
    selectedValues: List<Boolean>,
    enabled: Boolean,
    layout: Layout,
    label: String
) : ChipDemoState(enabled, layout, label) {

    companion object {

        @Suppress("UNCHECKED_CAST")
        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        selectedValues,
                        with(ChipDemoState.Saver) { save(state) }
                    )
                }
            },
            restore = { list: List<Any?> ->
                val chipDemoState = list[1]?.let { ChipDemoState.Saver.restore(it) }
                chipDemoState?.run {
                    FilterChipDemoState(
                        list[0] as List<Boolean>,
                        enabled,
                        layout,
                        label
                    )
                }
            }
        )
    }

    var selectedValues: List<Boolean> by mutableStateOf(selectedValues)
}

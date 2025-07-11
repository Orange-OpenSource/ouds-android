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
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.R

@Composable
fun rememberSuggestionChipDemoState(
    enabled: Boolean = true,
    layout: ChipDemoState.Layout = ChipDemoState.Layout.entries.first(),
    label: String = stringResource(R.string.app_components_chip_suggestionChip_chipContent_label)
) = rememberSaveable(enabled, layout, saver = SuggestionChipDemoState.Saver) {
    SuggestionChipDemoState(enabled, layout, label)
}

class SuggestionChipDemoState(
    enabled: Boolean,
    layout: Layout,
    label: String
) : ChipDemoState(enabled, layout, label) {

    companion object {

        val Saver = Saver<SuggestionChipDemoState, Any>(
            save = { state ->
                with(ChipDemoState.Saver) { save(state) }
            },
            restore = { value ->
                val chipDemoState = ChipDemoState.Saver.restore(value)
                chipDemoState?.let { SuggestionChipDemoState(it.enabled, it.layout, it.label) }
            }
        )
    }
}

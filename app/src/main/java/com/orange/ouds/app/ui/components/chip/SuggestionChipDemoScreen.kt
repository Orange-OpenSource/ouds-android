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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.core.component.OudsSuggestionChip
import com.orange.ouds.core.utilities.OudsPreview

@Composable
fun SuggestionChipDemoScreen() {
    val state = rememberSuggestionChipDemoState()
    DemoScreen(
        bottomSheetContent = { ChipDemoBottomSheetContent(state = state) },
        codeSnippet = { suggestionChipDemoCodeSnippet(state = state) },
        demoContent = { SuggestionChipDemoContent(state = state) }
    )
}

@Composable
private fun SuggestionChipDemoContent(state: SuggestionChipDemoState) {
    ChipDemoContent(state = state) { index, icon ->
        val label = stringResource(R.string.app_components_chip_suggestionChip_label)
        with(state) {
            when (layout) {
                ChipDemoState.Layout.TextOnly -> {
                    OudsSuggestionChip(
                        onClick = {},
                        label = label,
                        enabled = enabled
                    )
                }
                ChipDemoState.Layout.TextAndIcon -> {
                    OudsSuggestionChip(
                        onClick = {},
                        label = label,
                        icon = icon,
                        enabled = enabled
                    )
                }
                ChipDemoState.Layout.IconOnly -> {
                    OudsSuggestionChip(
                        onClick = {},
                        icon = icon,
                        enabled = enabled
                    )
                }
            }
        }
    }
}

private fun Code.Builder.suggestionChipDemoCodeSnippet(state: SuggestionChipDemoState) {
    with(state) {
        comment("First suggestion chip")
        functionCall("OudsSuggestionChip") {
            chipArguments(state, R.string.app_components_chip_suggestionChip_label)
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewSuggestionChipDemoScreen() = OudsPreview {
    SuggestionChipDemoScreen()
}

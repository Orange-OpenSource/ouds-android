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

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.core.component.OudsSuggestionChip
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.theme.OudsVersion

@Composable
fun SuggestionChipDemoScreen() {
    val state = rememberSuggestionChipDemoState()
    val context = LocalContext.current
    DemoScreen(
        bottomSheetContent = { ChipDemoBottomSheetContent(state = state) },
        codeSnippet = { suggestionChipDemoCodeSnippet(state = state, context = context) },
        demoContent = { SuggestionChipDemoContent(state = state) },
        version = OudsVersion.Component.Chip
    )
}

@Composable
private fun SuggestionChipDemoContent(state: SuggestionChipDemoState) {
    ChipDemoContent { index, icon ->
        with(state) {
            val separator = if (label.isBlank()) "" else " "
            val label = "$label$separator${index + 1}"
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

private fun Code.Builder.suggestionChipDemoCodeSnippet(state: SuggestionChipDemoState, context: Context) {
    with(state) {
        comment("First suggestion chip")
        functionCall("OudsSuggestionChip") {
            chipArguments(state)
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewSuggestionChipDemoScreen() = OudsPreview {
    SuggestionChipDemoScreen()
}

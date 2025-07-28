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
import com.orange.ouds.core.component.OudsFilterChip
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.theme.OudsVersion

@Composable
fun FilterChipDemoScreen() {
    val state = rememberFilterChipDemoState()
    val context = LocalContext.current
    DemoScreen(
        bottomSheetContent = { ChipDemoBottomSheetContent(state = state) },
        codeSnippet = { filterChipDemoCodeSnippet(state = state, context = context) },
        demoContent = { FilterChipDemoContent(state = state) },
        version = OudsVersion.Component.Chip
    )
}

@Composable
private fun FilterChipDemoContent(state: FilterChipDemoState) {
    ChipDemoContent { index, icon ->
        with(state) {
            val separator = if (label.isBlank()) "" else " "
            val label = "$label$separator${index + 1}"
            val selected = selectedValues[index]
            val onClick = { selectedValues = selectedValues.toMutableList().also { it[index] = !it[index] } }
            when (layout) {
                ChipDemoState.Layout.TextOnly -> {
                    OudsFilterChip(
                        selected = selected,
                        onClick = onClick,
                        label = label,
                        enabled = enabled
                    )
                }
                ChipDemoState.Layout.TextAndIcon -> {
                    OudsFilterChip(
                        selected = selected,
                        onClick = onClick,
                        label = label,
                        icon = icon,
                        enabled = enabled
                    )
                }
                ChipDemoState.Layout.IconOnly -> {
                    OudsFilterChip(
                        selected = selected,
                        onClick = onClick,
                        icon = icon,
                        enabled = enabled
                    )
                }
            }
        }
    }
}

private fun Code.Builder.filterChipDemoCodeSnippet(state: FilterChipDemoState, context: Context) {
    with(state) {
        comment("First filter chip")
        functionCall("OudsFilterChip") {
            typedArgument("selected", selectedValues[0])
            chipArguments(state)
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewFilterChipDemoScreen() = OudsPreview {
    FilterChipDemoScreen()
}

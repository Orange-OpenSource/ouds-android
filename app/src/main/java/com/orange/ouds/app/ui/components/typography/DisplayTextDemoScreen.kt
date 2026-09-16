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

package com.orange.ouds.app.ui.components.typography

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.composable.AppPreview
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChips
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.core.component.OudsDisplayText
import com.orange.ouds.core.component.OudsDisplayTextSize
import com.orange.ouds.foundation.extensions.toSentenceCase
import com.orange.ouds.theme.OudsVersion

@Composable
fun DisplayTextDemoScreen() {
    val state = rememberDisplayTextDemoState()
    DemoScreen(
        bottomSheetContent = { DisplayTextDemoBottomSheetContent(state = state) },
        codeSnippet = { displayTextDemoCodeSnippet(state = state) },
        demoContent = { DisplayTextDemoContent(state = state) },
        version = OudsVersion.Component.Typography
    )
}

@Composable
private fun DisplayTextDemoBottomSheetContent(state: DisplayTextDemoState) {
    with(state) {
        CustomizationFilterChips(
            applyTopPadding = false,
            label = stringResource(R.string.app_components_typography_common_size_tech),
            chipLabels = OudsDisplayTextSize.entries.map { it.name.toSentenceCase() },
            selectedChipIndex = OudsDisplayTextSize.entries.indexOf(size),
            onSelectionChange = { index: Int -> size = OudsDisplayTextSize.entries[index] }
        )
        TypographyDemoBottomSheetContent(state)
    }
}

@Composable
private fun DisplayTextDemoContent(state: DisplayTextDemoState) {
    with(state) {
        val displayText = text.ifBlank { stringResource(id = R.string.app_components_common_label_label) }
        OudsDisplayText(
            text = displayText,
            size = size
        )
    }
}

private fun Code.Builder.displayTextDemoCodeSnippet(state: DisplayTextDemoState) {
    with(state) {
        functionCall("OudsDisplayText") {
            typographyArguments(state = state)
            typedArgument("size", size)
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewDisplayTextDemoScreen() = AppPreview {
    DisplayTextDemoScreen()
}

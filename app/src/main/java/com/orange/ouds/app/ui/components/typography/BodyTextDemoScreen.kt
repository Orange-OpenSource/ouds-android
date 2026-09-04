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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.composable.AppPreview
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChips
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.core.component.OudsBodyText
import com.orange.ouds.core.component.OudsBodyTextSize
import com.orange.ouds.core.component.OudsTextWeight
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.extensions.toSentenceCase
import com.orange.ouds.theme.OudsVersion

@Composable
fun BodyTextDemoScreen() {
    val state = rememberBodyTextDemoState()
    DemoScreen(
        bottomSheetContent = { BodyTextDemoBottomSheetContent(state = state) },
        codeSnippet = { bodyTextDemoCodeSnippet(state = state) },
        demoContent = { BodyTextDemoContent(state = state) },
        version = OudsVersion.Component.Typography
    )
}

@Composable
private fun BodyTextDemoBottomSheetContent(state: BodyTextDemoState) {
    with(state) {
        CustomizationFilterChips(
            applyTopPadding = false,
            label = stringResource(R.string.app_components_typography_common_size_tech),
            chipLabels = OudsBodyTextSize.entries.map { it.name.toSentenceCase() },
            selectedChipIndex = OudsBodyTextSize.entries.indexOf(size),
            onSelectionChange = { index: Int -> size = OudsBodyTextSize.entries[index] }
        )
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_typography_common_weight_tech),
            chipLabels = OudsTextWeight.entries.map { it.name.toSentenceCase() },
            selectedChipIndex = OudsTextWeight.entries.indexOf(weight),
            onSelectionChange = { index: Int -> weight = OudsTextWeight.entries[index] }
        )
        TypographyDemoBottomSheetContent(state)
    }
}

@Composable
private fun BodyTextDemoContent(state: BodyTextDemoState) {
    with(state) {
        if (annotatedText) {
            with(OudsTheme.colorScheme.content) {
                val color = brandSecondary.takeIf { it != Color.Unspecified }.orElse { brandPrimary }
                OudsBodyText(
                    text = buildAnnotatedString {
                        append("Heading with ")
                        withStyle(SpanStyle(color = color)) {
                            append("colored text")
                        }
                        append(" and ")
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append("bold text") }
                    },
                    size = size,
                    weight = weight
                )
            }
        } else {
            OudsBodyText(
                text = text,
                size = size,
                weight = weight
            )
        }
    }
}

private fun Code.Builder.bodyTextDemoCodeSnippet(state: BodyTextDemoState) {
    with(state) {
        functionCall("OudsBodyText") {
            typographyArguments(state = state)
            typedArgument("size", size)
            typedArgument("weight", weight)
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewBodyTextDemoScreen() = AppPreview {
    BodyTextDemoScreen()
}

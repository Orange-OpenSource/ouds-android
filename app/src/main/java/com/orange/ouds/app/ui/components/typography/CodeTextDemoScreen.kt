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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.composable.AppPreview
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.core.component.OudsCodeText
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.theme.OudsVersion

@Composable
fun CodeTextDemoScreen() {
    val state = rememberCodeTextDemoState()
    DemoScreen(
        bottomSheetContent = { CodeTextDemoBottomSheetContent(state = state) },
        codeSnippet = { codeTextDemoCodeSnippet(state = state) },
        demoContent = { CodeTextDemoContent(state = state) },
        version = OudsVersion.Component.Typography
    )
}

@Composable
private fun CodeTextDemoBottomSheetContent(state: TypographyDemoState) {
    TypographyDemoBottomSheetContent(state)
}

@Composable
private fun CodeTextDemoContent(state: TypographyDemoState) {
    with(state) {
        if (annotatedText) {
            with(OudsTheme.colorScheme.content) {
                val color = brandSecondary.takeIf { it != Color.Unspecified }.orElse { brandPrimary }
                OudsCodeText(
                    text = buildAnnotatedString {
                        append("Code with ")
                        withStyle(SpanStyle(color = color)) {
                            append("colored text")
                        }
                        append(" and ")
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append("bold text") }
                    }
                )
            }
        } else {
            OudsCodeText(text = text)
        }
    }
}

private fun Code.Builder.codeTextDemoCodeSnippet(state: TypographyDemoState) {
    with(state) {
        functionCall("OudsCodeText") {
            typographyArguments(state = state)
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewCodeTextDemoScreen() = AppPreview {
    CodeTextDemoScreen()
}

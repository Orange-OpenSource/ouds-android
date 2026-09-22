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
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.composable.AppPreview
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChips
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.core.component.OudsLabelText
import com.orange.ouds.core.component.OudsLabelTextSize
import com.orange.ouds.core.component.OudsTextWeight
import com.orange.ouds.core.component.common.text.buildOudsAnnotatedLabelText
import com.orange.ouds.core.component.common.text.withColor
import com.orange.ouds.core.component.common.text.withStrong
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.extensions.toSentenceCase
import com.orange.ouds.theme.OudsVersion

@Composable
fun LabelTextDemoScreen() {
    val state = rememberLabelTextDemoState()
    DemoScreen(
        bottomSheetContent = { LabelTextDemoBottomSheetContent(state = state) },
        codeSnippet = { labelTextDemoCodeSnippet(state = state) },
        demoContent = { LabelTextDemoContent(state = state) },
        version = OudsVersion.Component.Label
    )
}

@Composable
private fun LabelTextDemoBottomSheetContent(state: LabelTextDemoState) {
    with(state) {
        CustomizationFilterChips(
            applyTopPadding = false,
            label = stringResource(R.string.app_components_typography_common_size_tech),
            chipLabels = OudsLabelTextSize.entries.map { it.name.toSentenceCase() },
            selectedChipIndex = OudsLabelTextSize.entries.indexOf(size),
            onSelectionChange = { index: Int -> size = OudsLabelTextSize.entries[index] }
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
private fun LabelTextDemoContent(state: LabelTextDemoState) {
    with(state) {
        if (annotatedText) {
            with(OudsTheme.colorScheme.content) {
                OudsLabelText(
                    text = buildOudsAnnotatedLabelText {
                        append("Label with ")
                        withColor(color = brandSecondary.takeIf { it != Color.Unspecified }.orElse { brandPrimary }) {
                            append("colored text")
                        }
                        append(" and ")
                        withStrong { append("strong text") }
                    },
                    size = size,
                    weight = weight
                )
            }
        } else {
            OudsLabelText(
                text = text.ifBlank { stringResource(id = R.string.app_components_common_label_label) },
                size = size,
                weight = weight
            )
        }
    }
}

private fun Code.Builder.labelTextDemoCodeSnippet(state: LabelTextDemoState) {
    with(state) {
        functionCall("OudsLabelText") {
            typographyArguments(state = state, annotatedTextFunctionName = "buildOudsAnnotatedLabelText")
            typedArgument("size", size)
            typedArgument("weight", weight)
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewLabelTextDemoScreen() = AppPreview {
    LabelTextDemoScreen()
}

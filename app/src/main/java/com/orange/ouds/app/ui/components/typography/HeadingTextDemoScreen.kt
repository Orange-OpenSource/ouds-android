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

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.annotatedStringArgument
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.composable.AppPreview
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChips
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextInput
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.core.component.OudsBodyText
import com.orange.ouds.core.component.OudsBodyTextSize
import com.orange.ouds.core.component.OudsHeadingText
import com.orange.ouds.core.component.common.text.OudsAnnotatedHeadingText
import com.orange.ouds.core.component.common.text.buildOudsAnnotatedHeadingText
import com.orange.ouds.core.component.common.text.withColor
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.foundation.RestrictedOudsApi
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.extensions.toSentenceCase
import com.orange.ouds.theme.OudsVersion

@Composable
fun HeadingTextDemoScreen() {
    val state = rememberHeadingTextDemoState()
    DemoScreen(
        bottomSheetContent = { HeadingTextDemoBottomSheetContent(state = state) },
        codeSnippet = { headingTextDemoCodeSnippet(state = state) },
        demoContent = { HeadingTextDemoContent(state = state) },
        version = OudsVersion.Component.Typography
    )
}

@Composable
private fun HeadingTextDemoBottomSheetContent(state: HeadingTextDemoState) {
    with(state) {
        CustomizationFilterChips(
            applyTopPadding = false,
            label = stringResource(R.string.app_components_typography_common_size_tech),
            chipLabels = HeadingTextDemoState.Size.entries.map { it.name.toSentenceCase() },
            selectedChipIndex = HeadingTextDemoState.Size.entries.indexOf(size),
            onSelectionChange = { index: Int -> size = HeadingTextDemoState.Size.entries[index] }
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_typography_headingText_marker_tech),
            checked = headingLargeMarker,
            onCheckedChange = { value ->
                headingLargeMarker = value
            },
            enabled = headingLargeMarkerSwitchEnabled
        )
        @OptIn(RestrictedOudsApi::class)
        if (!OudsTheme.components.typography.headingLargeMarker) {
            OudsBodyText(
                modifier = Modifier.padding(horizontal = OudsTheme.grids.margin),
                text = stringResource(R.string.app_components_typography_headingText_markerNote_text),
                size = OudsBodyTextSize.Small,
                color = if (headingLargeMarkerSwitchEnabled) OudsTheme.colorScheme.content.default else OudsTheme.colorScheme.content.disabled
            )
        }
        CustomizationTextInput(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_typography_common_text_tech),
            value = text,
            onValueChange = { value -> text = value },
            helperText = stringResource(id = R.string.app_components_common_annotatedTextHelperText_tech)
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_common_annotatedText_tech),
            checked = annotatedText,
            onCheckedChange = { annotatedText = it },
        )
    }
}

@Composable
private fun HeadingTextDemoContent(state: HeadingTextDemoState) {
    with(state) {
        if (annotatedText) {
            with(OudsTheme.colorScheme.content) {
                val color = brandSecondary.takeIf { it != Color.Unspecified }.orElse { brandPrimary }
                OudsHeadingText(
                    text = buildOudsAnnotatedHeadingText {
                        append("Heading with ")
                        withColor(color) {
                            append("colored text")
                        }
                    },
                    size = size.toOudsHeadingTextSize(marker = headingLargeMarker)
                )
            }
        } else {
            OudsHeadingText(
                text = text,
                size = size.toOudsHeadingTextSize(marker = headingLargeMarker)
            )
        }
    }
}

private fun Code.Builder.headingTextDemoCodeSnippet(state: HeadingTextDemoState) {
    with(state) {
        functionCall("OudsHeadingText") {
            if (annotatedText) {
                annotatedStringArgument<OudsAnnotatedHeadingText>("text")
            } else {
                typedArgument("text", text)
            }

            if (size == HeadingTextDemoState.Size.Large) {
                functionCallArgument("size", "OudsHeadingTextSize.Large") {
                    isMultiline = false
                    if (!headingLargeMarker) typedArgument("marker", headingLargeMarker)
                }
            } else {
                typedArgument("size", size)
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewHeadingTextDemoScreen() = AppPreview {
    HeadingTextDemoScreen()
}

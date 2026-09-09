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

package com.orange.ouds.app.ui.components.progressindicator

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.Variant
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.composable.AppPreview
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChip
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChips
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.core.component.OudsDeterminateLinearProgressIndicatorHelperText
import com.orange.ouds.core.component.OudsIndeterminateLinearProgressIndicatorHelperText
import com.orange.ouds.core.component.OudsLinearProgressIndicator
import com.orange.ouds.foundation.extensions.toSentenceCase
import com.orange.ouds.theme.OudsVersion

@Composable
fun LinearProgressIndicatorDemoScreen() {
    val state = rememberLinearProgressIndicatorDemoState()
    DemoScreen(
        description = stringResource(id = Variant.LinearProgressIndicator.descriptionRes),
        bottomSheetContent = { LinearProgressIndicatorDemoBottomSheetContent(state = state) },
        codeSnippet = { linearProgressIndicatorDemoCodeSnippet(state = state) },
        demoContent = { LinearProgressIndicatorDemoContent(state = state) },
        demoContentOnColoredBox = state.onColoredBox,
        version = OudsVersion.Component.LinearProgressIndicator
    )
}

@Composable
private fun LinearProgressIndicatorDemoBottomSheetContent(state: LinearProgressIndicatorDemoState) {
    with(state) {
        ProgressIndicatorDemoBottomSheetContent(state = state)
        val helperTextAlignments = LinearProgressIndicatorDemoState.HelperTextAlignment.entries
        CustomizationFilterChips(
            applyTopPadding = false,
            label = stringResource(R.string.app_components_progressIndicator_helperTextProgressAlignment_tech),
            chips = helperTextAlignments.map { CustomizationFilterChip(it.name.toSentenceCase(), helperTextProgressAlignmentEnabled) },
            selectedChipIndex = helperTextAlignments.indexOf(helperTextProgressAlignment),
            onSelectionChange = { index -> helperTextProgressAlignment = helperTextAlignments[index] }
        )
        CustomizationFilterChips(
            applyTopPadding = false,
            label = stringResource(R.string.app_components_progressIndicator_helperTextLabelAlignment_tech),
            chips = helperTextAlignments.map { CustomizationFilterChip(it.name.toSentenceCase(), helperTextLabelAlignmentEnabled) },
            selectedChipIndex = helperTextAlignments.indexOf(helperTextLabelAlignment),
            onSelectionChange = { index -> helperTextLabelAlignment = helperTextAlignments[index] }
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_progressIndicator_linearProgressIndicator_stopIndicator_tech),
            checked = stopIndicator,
            onCheckedChange = { stopIndicator = it },
            enabled = stopIndicatorSwitchEnabled
        )
    }
}

@Composable
private fun LinearProgressIndicatorDemoContent(state: LinearProgressIndicatorDemoState) {
    val progress = animatedProgress(state)

    with(state) {
        when (type) {
            ProgressIndicatorDemoState.Type.Determinate -> {
                if (helperTextProgress || !helperTextLabel.isNullOrBlank()) {
                    val linearProgressIndicatorHelperText = OudsDeterminateLinearProgressIndicatorHelperText(
                        progress = helperTextProgress,
                        label = helperTextLabel,
                        progressAlignment = helperTextProgressAlignment.toHorizontalAlignment(),
                        labelAlignment = helperTextLabelAlignment.toHorizontalAlignment()
                    )
                    OudsLinearProgressIndicator(
                        progress = { progress },
                        status = status,
                        track = track,
                        stopIndicator = stopIndicator,
                        helperText = linearProgressIndicatorHelperText,
                        gapSize = gapSize
                    )
                } else {
                    OudsLinearProgressIndicator(
                        progress = { progress },
                        status = status,
                        track = track,
                        stopIndicator = stopIndicator,
                        gapSize = gapSize
                    )
                }
            }
            ProgressIndicatorDemoState.Type.Indeterminate -> {
                if (!helperTextLabel.isNullOrBlank()) {
                    val linearProgressIndicatorHelperText = OudsIndeterminateLinearProgressIndicatorHelperText(
                        label = helperTextLabel.orEmpty(),
                        alignment = helperTextLabelAlignment.toHorizontalAlignment()
                    )
                    OudsLinearProgressIndicator(
                        status = status,
                        track = track,
                        helperText = linearProgressIndicatorHelperText,
                        gapSize = gapSize
                    )
                } else {
                    OudsLinearProgressIndicator(
                        status = status,
                        track = track,
                        gapSize = gapSize
                    )
                }
            }
        }
    }
}

private fun Code.Builder.linearProgressIndicatorDemoCodeSnippet(state: LinearProgressIndicatorDemoState) {
    progressIndicatorAnimationInitialization(state)
    
    functionCall("OudsLinearProgressIndicator") {
        progressIndicatorArguments(state = state)
        with(state) {
            when (type) {
                ProgressIndicatorDemoState.Type.Determinate -> {
                    if (helperTextProgress || !helperTextLabel.isNullOrBlank()) {
                        constructorCallArgument<OudsDeterminateLinearProgressIndicatorHelperText>("helperText") {
                            typedArgument("progress", helperTextProgress)
                            typedArgument("label", helperTextLabel)
                            rawArgument("progressAlignment", "${Alignment::class.simpleName}.${helperTextProgressAlignment.name}")
                            rawArgument("labelAlignment", "${Alignment::class.simpleName}.${helperTextLabelAlignment.name}")
                        }
                    }
                }
                ProgressIndicatorDemoState.Type.Indeterminate -> {
                    if (!helperTextLabel.isNullOrBlank()) {
                        constructorCallArgument<OudsIndeterminateLinearProgressIndicatorHelperText>("helperText") {
                            typedArgument("label", helperTextLabel)
                            rawArgument("labelAlignment", "${Alignment::class.simpleName}.${helperTextLabelAlignment.name}")
                        }
                    }
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewLinearProgressIndicatorDemoScreen() = AppPreview {
    LinearProgressIndicatorDemoScreen()
}

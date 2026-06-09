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

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.Variant
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.composable.AppPreview
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextInput
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.core.component.OudsLinearProgressIndicator
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.theme.OudsVersion

@Composable
fun LinearProgressIndicatorDemoScreen() {
    val state = rememberLinearProgressIndicatorDemoState()
    DemoScreen(
        description = stringResource(id = Variant.LinearProgressIndicator.descriptionRes),
        bottomSheetContent = { LinearProgressIndicatorDemoBottomSheetContent(state = state) },
        codeSnippet = { linearProgressIndicatorDemoCodeSnippet(state = state) },
        demoContent = { LinearProgressIndicatorDemoContent(state = state) },
        version = OudsVersion.Component.ProgressIndicator
    )
}

@Composable
private fun LinearProgressIndicatorDemoBottomSheetContent(state: LinearProgressIndicatorDemoState) {
    with(state) {
        ProgressIndicatorDemoBottomSheetContent(state = state)
        CustomizationTextInput(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_helperText_tech),
            value = helperText.orEmpty(),
            onValueChange = { value -> helperText = value }
        )
    }
}

@Composable
private fun LinearProgressIndicatorDemoContent(state: LinearProgressIndicatorDemoState) {
    with(state) {
        when (type) {
            ProgressIndicatorDemoState.Type.Determinate -> {
                OudsLinearProgressIndicator(
                    progress = { progress },
                    brandColor = brandColor,
                    track = track,
                    helperText = helperText
                )
            }
            ProgressIndicatorDemoState.Type.Indeterminate -> {
                OudsLinearProgressIndicator(
                    brandColor = brandColor,
                    track = track,
                    helperText = helperText
                )
            }
        }
    }
}

private fun Code.Builder.linearProgressIndicatorDemoCodeSnippet(state: LinearProgressIndicatorDemoState) {
    with(state) {
        functionCall("OudsLinearProgressIndicator") {
            if (type == ProgressIndicatorDemoState.Type.Determinate) {
                lambdaArgument("progress") {
                    value(progress)
                }
            }
            typedArgument("brandColor", brandColor)
            typedArgument("track", track)
            helperText?.let { typedArgument("helperText", it) }
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewLinearProgressIndicatorDemoScreen() = AppPreview {
    LinearProgressIndicatorDemoScreen()
}

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.ui.components.Variant
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.composable.AppPreview
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.core.component.OudsCircularProgressIndicator
import com.orange.ouds.theme.OudsVersion

@Composable
fun CircularProgressIndicatorDemoScreen() {
    val state = rememberCircularProgressIndicatorDemoState()
    DemoScreen(
        description = stringResource(id = Variant.CircularProgressIndicator.descriptionRes),
        bottomSheetContent = { ProgressIndicatorDemoBottomSheetContent(state = state) },
        codeSnippet = { circularProgressIndicatorDemoCodeSnippet(state = state) },
        demoContent = { CircularProgressIndicatorDemoContent(state = state) },
        version = OudsVersion.Component.ProgressIndicator
    )
}

@Composable
private fun CircularProgressIndicatorDemoContent(state: ProgressIndicatorDemoState) {
    with(state) {
        when (type) {
            ProgressIndicatorDemoState.Type.Determinate -> {
                OudsCircularProgressIndicator(
                    progress = { progress },
                    brandColor = brandColor,
                    track = track
                )
            }
            ProgressIndicatorDemoState.Type.Indeterminate -> {
                OudsCircularProgressIndicator(
                    brandColor = brandColor,
                    track = track
                )
            }
        }
    }
}

private fun Code.Builder.circularProgressIndicatorDemoCodeSnippet(state: ProgressIndicatorDemoState) {
    functionCall("OudsCircularProgressIndicator") {
        progressIndicatorArguments(state = state)
    }
}

@PreviewLightDark
@Composable
private fun PreviewCircularProgressIndicatorDemoScreen() = AppPreview {
    CircularProgressIndicatorDemoScreen()
}

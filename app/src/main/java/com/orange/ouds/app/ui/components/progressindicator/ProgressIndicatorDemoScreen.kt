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

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.LayoutDirection
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.FunctionCall
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChips
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextInput

@Composable
fun ProgressIndicatorDemoBottomSheetContent(state: ProgressIndicatorDemoState) {
    with(state) {
        CustomizationFilterChips(
            applyTopPadding = false,
            label = stringResource(R.string.app_components_common_type_tech),
            chipLabels = ProgressIndicatorDemoState.Type.entries.map { stringResource(it.labelRes) },
            selectedChipIndex = ProgressIndicatorDemoState.Type.entries.indexOf(type),
            onSelectionChange = { index: Int -> type = ProgressIndicatorDemoState.Type.entries[index] }
        )
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
            CustomizationTextInput(
                applyTopPadding = true,
                label = stringResource(R.string.app_components_progressIndicator_progress_tech),
                value = TextFieldValue(
                    text = progressText,
                    selection = TextRange(progressText.length)
                ),
                onValueChange = { textFieldValue ->
                    progressText = textFieldValue.text.replace(',', '.').filter { it.isDigit() || it == '.' }
                },
                enabled = progressTextInputEnabled,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal),
                resetValue = ProgressIndicatorDemoState.InitialProgressValue.toString()
            )
        }
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_progressIndicator_brandColor_tech),
            checked = brandColor,
            onCheckedChange = { brandColor = it }
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_progressIndicator_track_tech),
            checked = track,
            onCheckedChange = { track = it }
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_progressIndicator_animated_tech),
            checked = animated,
            onCheckedChange = { animated = it },
            enabled = animatedSwitchEnabled
        )
    }
}

@Composable
fun animatedProgress(state: ProgressIndicatorDemoState): Float {
    var progressValue by remember { mutableFloatStateOf(0f) }
    val animatedProgress by animateFloatAsState(
        targetValue = progressValue,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    )

    if (!state.animated || state.type == ProgressIndicatorDemoState.Type.Indeterminate) {
        progressValue = 0f // Reset progress value for future determinate progress animation
    }

    LaunchedEffect(state.progress, state.animated, state.type) {
        progressValue = state.progress
    }

    return if (state.animated) animatedProgress else state.progress
}


fun FunctionCall.Builder.progressIndicatorArguments(state: ProgressIndicatorDemoState) = with(state) {
    if (type == ProgressIndicatorDemoState.Type.Determinate) {
        lambdaArgument("progress") {
            if (animated) {
                rawValue("animatedProgress")
            } else {
                value(progress)
            }
        }
    }
    typedArgument("brandColor", brandColor)
    typedArgument("track", track)
}

fun Code.Builder.progressIndicatorAnimationInitialization(state: ProgressIndicatorDemoState) = with(state) {
    comment("Progress animation example")
    if (animated && type == ProgressIndicatorDemoState.Type.Determinate) {
        variableDeclaration(name = "progressValue", mutable = true, delegatedProperty = "remember") {
            rememberFunctionCallValue("mutableFloatStateOf", isMultiline = false) {
                typedArgument(null, 0f)
            }
        }
        variableDeclaration(name = "animatedProgress", mutable = false, delegatedProperty = "animateFloatAsState") {
            functionCallValue("animateFloatAsState") {
                typedArgument("targetValue", progress)
                rawArgument("animationSpec", "ProgressIndicatorDefaults.ProgressAnimationSpec")
            }
        }
        newline()
    }
}


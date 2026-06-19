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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.LayoutDirection
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.FunctionCall
import com.orange.ouds.app.ui.utilities.composable.CustomizationDropdownMenu
import com.orange.ouds.app.ui.utilities.composable.CustomizationDropdownMenuItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChips
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextInput
import com.orange.ouds.app.ui.utilities.nestedName
import com.orange.ouds.core.component.OudsProgressIndicatorStatus
import com.orange.ouds.foundation.extensions.toSentenceCase

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
        val statuses = OudsProgressIndicatorStatus.entries
        CustomizationDropdownMenu(
            applyTopPadding = true,
            label = stringResource(id = R.string.app_components_common_status_tech),
            items = statuses.map { status ->
                CustomizationDropdownMenuItem(
                    label = status.name.toSentenceCase(),
                    leadingIcon = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(status.color())
                        )
                    }
                )
            },
            selectedItemIndex = statuses.indexOf(status),
            onSelectionChange = { status = statuses[it] }
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
    return with(state) {
        var progressValue by remember(progress, animated, type) { mutableFloatStateOf(0f) }
        LaunchedEffect(progress, animated, type) {
            progressValue = if (!animated || type == ProgressIndicatorDemoState.Type.Indeterminate) 0f else progress
        }

        val animatedProgress by animateFloatAsState(
            targetValue = progressValue.coerceIn(0f, 1f),
            animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
        )

        if (animated) animatedProgress else progress
    }
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
    rawArgument("status", status::class.java.nestedName)
    typedArgument("track", track)
}

fun Code.Builder.progressIndicatorAnimationInitialization(state: ProgressIndicatorDemoState) = with(state) {
    if (animated && type == ProgressIndicatorDemoState.Type.Determinate) {
        comment("Progress animation example")
        variableDeclaration(name = "progressValue", mutable = true, delegatedProperty = true) {
            rememberFunctionCallValue("mutableFloatStateOf", isMultiline = false) {
                typedArgument(null, 0f)
            }
        }
        variableDeclaration(name = "animatedProgress", mutable = false, delegatedProperty = true) {
            functionCallValue("animateFloatAsState") {
                typedArgument("targetValue", progress)
                rawArgument("animationSpec", "ProgressIndicatorDefaults.ProgressAnimationSpec")
            }
        }
        newline()
    }
}

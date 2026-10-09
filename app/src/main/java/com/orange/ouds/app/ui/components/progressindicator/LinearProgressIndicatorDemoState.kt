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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.orange.ouds.app.ui.components.progressindicator.ProgressIndicatorDemoState.Companion.InitialProgressValue
import com.orange.ouds.app.ui.components.progressindicator.ProgressIndicatorDemoState.Type
import com.orange.ouds.core.component.OudsProgressIndicatorDefaults
import com.orange.ouds.core.component.OudsProgressIndicatorGapSize
import com.orange.ouds.core.component.OudsProgressIndicatorStatus

@Composable
fun rememberLinearProgressIndicatorDemoState(
    progressText: String = InitialProgressValue.toString(),
    type: Type = Type.Determinate,
    status: OudsProgressIndicatorStatus = OudsProgressIndicatorDefaults.Status,
    track: Boolean = true,
    gapSize: OudsProgressIndicatorGapSize = OudsProgressIndicatorDefaults.GapSize,
    animated: Boolean = true,
    onColoredBox: Boolean = false,
    helperTextProgress: Boolean = true,
    helperTextLabel: String? = null,
    stopIndicator: Boolean = false,
    helperTextProgressAlignment: LinearProgressIndicatorDemoState.HelperTextAlignment = LinearProgressIndicatorDemoState.HelperTextAlignment.CenterHorizontally,
    helperTextLabelAlignment: LinearProgressIndicatorDemoState.HelperTextAlignment = LinearProgressIndicatorDemoState.HelperTextAlignment.CenterHorizontally
) = rememberSaveable(
    progressText,
    type,
    status,
    track,
    gapSize,
    animated,
    onColoredBox,
    helperTextProgress,
    helperTextLabel,
    stopIndicator,
    helperTextProgressAlignment,
    helperTextLabelAlignment,
    saver = LinearProgressIndicatorDemoState.Saver
) {
    LinearProgressIndicatorDemoState(
        progressText,
        type,
        status,
        track,
        gapSize,
        animated,
        onColoredBox,
        helperTextProgress,
        helperTextLabel,
        stopIndicator,
        helperTextProgressAlignment,
        helperTextLabelAlignment
    )
}

class LinearProgressIndicatorDemoState(
    progressText: String,
    type: Type,
    status: OudsProgressIndicatorStatus,
    track: Boolean,
    gapSize: OudsProgressIndicatorGapSize,
    animated: Boolean,
    onColoredBox: Boolean,
    helperTextProgress: Boolean,
    helperTextLabel: String?,
    stopIndicator: Boolean,
    helperTextProgressAlignment: HelperTextAlignment,
    helperTextLabelAlignment: HelperTextAlignment
) : ProgressIndicatorDemoState(progressText, type, status, track, gapSize, animated, onColoredBox, helperTextProgress, helperTextLabel) {

    companion object {
        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        with(ProgressIndicatorDemoState.Saver) { save(state) },
                        stopIndicator,
                        helperTextProgressAlignment,
                        helperTextLabelAlignment
                    )
                }
            },
            restore = { list: List<Any?> ->
                val progressIndicatorDemoState = list[0]?.let { ProgressIndicatorDemoState.Saver.restore(it) }
                progressIndicatorDemoState?.run {
                    LinearProgressIndicatorDemoState(
                        progressText,
                        type,
                        status,
                        track,
                        gapSize,
                        animated,
                        onColoredBox,
                        helperTextProgress,
                        helperTextLabel,
                        list[1] as Boolean,
                        list[2] as HelperTextAlignment,
                        list[3] as HelperTextAlignment
                    )
                }
            }
        )
    }

    var stopIndicator by mutableStateOf(stopIndicator)

    var helperTextProgressAlignment by mutableStateOf(helperTextProgressAlignment)

    var helperTextLabelAlignment by mutableStateOf(helperTextLabelAlignment)

    val stopIndicatorSwitchEnabled: Boolean
        get() = type == Type.Determinate

    val helperTextProgressAlignmentEnabled: Boolean
        get() = helperTextProgress && helperTextProgressEnabled

    val helperTextLabelAlignmentEnabled: Boolean
        get() = !helperTextLabel.isNullOrBlank()

    enum class HelperTextAlignment {
        Start, CenterHorizontally, End;

        fun toHorizontalAlignment() = when (this) {
            Start -> Alignment.Start
            CenterHorizontally -> Alignment.CenterHorizontally
            End -> Alignment.End
        }
    }
}

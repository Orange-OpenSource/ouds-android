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
import com.orange.ouds.app.ui.components.progressindicator.ProgressIndicatorDemoState.Companion.InitialProgressValue
import com.orange.ouds.app.ui.components.progressindicator.ProgressIndicatorDemoState.Type
import com.orange.ouds.core.component.OudsProgressIndicatorDefaults
import com.orange.ouds.core.component.OudsProgressIndicatorStatus

@Composable
fun rememberLinearProgressIndicatorDemoState(
    progressText: String = InitialProgressValue.toString(),
    type: Type = Type.Determinate,
    status: OudsProgressIndicatorStatus = OudsProgressIndicatorDefaults.Status,
    track: Boolean = true,
    stopIndicator: Boolean = false,
    helperText: String? = null,
    animated: Boolean = true
) = rememberSaveable(progressText, type, status, track, animated, stopIndicator, helperText, saver = LinearProgressIndicatorDemoState.Saver) {
    LinearProgressIndicatorDemoState(progressText, type, status, track, animated, stopIndicator, helperText)
}

class LinearProgressIndicatorDemoState(
    progressText: String,
    type: Type,
    status: OudsProgressIndicatorStatus,
    track: Boolean,
    animated: Boolean,
    stopIndicator: Boolean,
    helperText: String?
) : ProgressIndicatorDemoState(progressText, type, status, track, animated) {

    companion object {
        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        with(ProgressIndicatorDemoState.Saver) { save(state) },
                        stopIndicator,
                        helperText
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
                        animated,
                        list[1] as Boolean,
                        list[2] as String?
                    )
                }
            }
        )
    }

    var stopIndicator by mutableStateOf(stopIndicator)

    var helperText by mutableStateOf(helperText)

    val stopIndicatorSwitchEnabled: Boolean
        get() = type == Type.Determinate
}

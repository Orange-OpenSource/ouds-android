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

@Composable
fun rememberLinearProgressIndicatorDemoState(
    progressText: String = InitialProgressValue.toString(),
    type: Type = Type.Determinate,
    brandColor: Boolean = true,
    track: Boolean = true,
    helperText: String? = null,
    animated: Boolean = true
) = rememberSaveable(progressText, type, brandColor, track, animated, helperText, saver = LinearProgressIndicatorDemoState.Saver) {
    LinearProgressIndicatorDemoState(progressText, type, brandColor, track, animated, helperText)
}

class LinearProgressIndicatorDemoState(
    progressText: String,
    type: Type,
    brandColor: Boolean,
    track: Boolean,
    animated: Boolean,
    helperText: String?
) : ProgressIndicatorDemoState(progressText, type, brandColor, track, animated) {

    companion object {
        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        with(ProgressIndicatorDemoState.Saver) { save(state) },
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
                        brandColor,
                        track,
                        animated,
                        list[1] as String?
                    )
                }
            }
        )
    }

    var helperText by mutableStateOf(helperText)

}

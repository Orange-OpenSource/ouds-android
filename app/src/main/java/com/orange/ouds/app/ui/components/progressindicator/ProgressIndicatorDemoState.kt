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

import androidx.annotation.StringRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.setValue
import com.orange.ouds.app.R
import com.orange.ouds.core.component.OudsProgressIndicatorGapSize
import com.orange.ouds.core.component.OudsProgressIndicatorStatus

open class ProgressIndicatorDemoState(
    progressText: String,
    type: Type,
    status: OudsProgressIndicatorStatus,
    track: Boolean,
    gapSize: OudsProgressIndicatorGapSize,
    animated: Boolean,
    onColoredBox: Boolean,
    helperTextProgress: Boolean,
    helperTextLabel: String?
) {

    companion object {
        const val InitialProgressValue = 0.75f

        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        progressText,
                        type,
                        status,
                        track,
                        gapSize,
                        animated,
                        onColoredBox,
                        helperTextProgress,
                        helperTextLabel
                    )
                }
            },
            restore = { list ->
                ProgressIndicatorDemoState(
                    list[0] as String,
                    list[1] as Type,
                    list[2] as OudsProgressIndicatorStatus,
                    list[3] as Boolean,
                    list[4] as OudsProgressIndicatorGapSize,
                    list[5] as Boolean,
                    list[6] as Boolean,
                    list[7] as Boolean,
                    list[8] as String?
                )
            }
        )
    }

    var progressText by mutableStateOf(progressText)

    var type by mutableStateOf(type)

    var status by mutableStateOf(status)

    var track by mutableStateOf(track)

    var gapSize by mutableStateOf(gapSize)

    var animated by mutableStateOf(animated)

    var onColoredBox: Boolean by mutableStateOf(onColoredBox)

    var helperTextProgress: Boolean by mutableStateOf(helperTextProgress)

    var helperTextLabel: String? by mutableStateOf(helperTextLabel)

    val progress: Float
        get() = progressText.toFloatOrNull() ?: 0f

    val progressTextInputEnabled: Boolean
        get() = type == Type.Determinate

    val animatedSwitchEnabled: Boolean
        get() = type == Type.Determinate

    val statusDropdownMenuEnabled: Boolean
        get() = !onColoredBox

    val helperTextProgressEnabled: Boolean
        get() = type == Type.Determinate

    enum class Type(@StringRes val labelRes: Int) {
        Determinate(R.string.app_components_progressIndicator_determinate_tech),
        Indeterminate(R.string.app_components_progressIndicator_indeterminate_tech)
    }
}

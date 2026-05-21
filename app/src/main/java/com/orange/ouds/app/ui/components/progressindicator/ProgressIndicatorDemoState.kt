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
import androidx.compose.runtime.setValue
import com.orange.ouds.app.R

abstract class ProgressIndicatorDemoState {

    companion object {
        const val InitialProgressValue = 0.75f
    }

    var progressText by mutableStateOf(InitialProgressValue.toString())

    var type by mutableStateOf(Type.Determinate)

    var brandColor by mutableStateOf(true)

    var track by mutableStateOf(true)

    val progress: Float
        get() = (progressText.toFloatOrNull() ?: 0f).coerceIn(0f, 1f)

    val progressTextInputEnabled: Boolean
        get() = type == Type.Determinate

    enum class Type(@StringRes val labelRes: Int) {
        Determinate(R.string.app_components_progressIndicator_determinate_tech),
        Indeterminate(R.string.app_components_progressIndicator_indeterminate_tech)
    }
}

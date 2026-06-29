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
import androidx.compose.runtime.saveable.rememberSaveable
import com.orange.ouds.app.ui.components.progressindicator.ProgressIndicatorDemoState.Companion.InitialProgressValue
import com.orange.ouds.app.ui.components.progressindicator.ProgressIndicatorDemoState.Type
import com.orange.ouds.core.component.OudsProgressIndicatorDefaults
import com.orange.ouds.core.component.OudsProgressIndicatorStatus

@Composable
fun rememberCircularProgressIndicatorDemoState(
    progressText: String = InitialProgressValue.toString(),
    type: Type = Type.Determinate,
    status: OudsProgressIndicatorStatus = OudsProgressIndicatorDefaults.Status,
    track: Boolean = true,
    animated: Boolean = true,
    onColoredBox: Boolean = false
) = rememberSaveable(progressText, type, status, track, animated, saver = ProgressIndicatorDemoState.Saver) {
    ProgressIndicatorDemoState(progressText, type, status, track, animated, onColoredBox)
}
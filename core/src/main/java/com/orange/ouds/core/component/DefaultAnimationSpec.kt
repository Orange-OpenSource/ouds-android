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

package com.orange.ouds.core.component

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.tween

internal object DefaultAnimationSpec {
    internal const val DurationMillis = 150
}

internal fun <T> defaultAnimationSpec(): AnimationSpec<T> {
    return tween(
        durationMillis = DefaultAnimationSpec.DurationMillis,
        easing = CubicBezierEasing(0.2f, 0.0f, 0.0f, 1.0f)
    )
}

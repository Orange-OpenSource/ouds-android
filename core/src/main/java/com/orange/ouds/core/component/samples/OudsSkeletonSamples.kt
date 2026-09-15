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

package com.orange.ouds.core.component.samples

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.component.OudsSkeleton
import com.orange.ouds.core.component.OudsSkeletonState
import com.orange.ouds.core.component.rememberOudsSkeletonState
import com.orange.ouds.core.utilities.OudsPreview
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@Composable
internal fun OudsSkeletonSample() {
    OudsSkeleton(
        modifier = Modifier.size(200.dp, 62.dp)
    )
}

@Composable
internal fun OudsSkeletonWithTimedAnimationSample() {
    val state = rememberOudsSkeletonState()
    LaunchedEffect(state) {
        delay((OudsSkeletonState.AnimationDuration * 5L).milliseconds)
        state.stopAnimation()
    }
    OudsSkeleton(
        modifier = Modifier.size(200.dp, 62.dp),
        state = state
    )
}

@PreviewLightDark
@Composable
private fun PreviewOudsSkeletonSample() = OudsPreview {
    OudsSkeletonSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsSkeletonWithTimedAnimationSample() = OudsPreview {
    OudsSkeletonWithTimedAnimationSample()
}

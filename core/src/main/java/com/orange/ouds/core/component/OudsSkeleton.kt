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

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.OudsPreviewLightDark
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.theme.OudsThemeContract

/**
 * A skeleton is a UI element that indicates when content is loading. The skeleton enhances user experience by
 * temporarily replacing content with gray areas or animations that simulate the visual structure of the forthcoming
 * content.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://r.orange.fr/r/S-ouds-doc-skeleton)
 *
 * > Design name: Skeleton
 *
 * > Design version: 1.0.0
 */
@Composable
fun OudsSkeleton(
    modifier: Modifier = Modifier,
    securityMargin: Boolean = true
) {
    with(OudsTheme.components.skeleton) {
        val verticalPadding = if (securityMargin) OudsTheme.spaces.paddingBlock.threeExtraSmall else 0.dp
        BoxWithConstraints(
            modifier = modifier
                .padding(vertical = verticalPadding)
                .background(color.background)
                .clipToBounds()
        ) {
            // Don't display gradient in previews
            if (!LocalInspectionMode.current) {
                val infiniteTransition = rememberInfiniteTransition()
                val progress by infiniteTransition.animateFloat(
                    initialValue = 0f,
                    targetValue = 1f,
                    animationSpec = infiniteRepeatable(
                        animation = keyframes {
                            durationMillis = 1250 // 800ms movement + 450ms pause
                            0f at 0 using CubicBezierEasing(0.42f, 0.0f, 0.58f, 1.0f)
                            1f at 800 // Holds at 1f from 800ms to 1250ms (450ms pause)
                        },
                        repeatMode = RepeatMode.Restart
                    )
                )
                val skeletonWidthPx = with(LocalDensity.current) { maxWidth.toPx() }
                val offset = lerp(-skeletonWidthPx, skeletonWidthPx, progress)
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .offset { IntOffset(offset.toInt(), 0) }
                        .background(
                            brush = Brush.horizontalGradient(
                                0.0f to color.gradient.startEnd,
                                0.5f to color.gradient.middle,
                                1.0f to color.gradient.startEnd
                            )
                        )
                )
            }
        }
    }
}

@OudsPreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsSkeleton(@PreviewParameter(OudsSkeletonPreviewParameterProvider::class) securityMargin: Boolean) {
    PreviewOudsSkeleton(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), securityMargin = securityMargin)
}

@Composable
internal fun PreviewOudsSkeleton(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    securityMargin: Boolean
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    OudsSkeleton(
        modifier = Modifier.size(width = 200.dp, height = 62.dp),
        securityMargin = securityMargin
    )
}

internal class OudsSkeletonPreviewParameterProvider : BasicPreviewParameterProvider<Boolean>(true, false)

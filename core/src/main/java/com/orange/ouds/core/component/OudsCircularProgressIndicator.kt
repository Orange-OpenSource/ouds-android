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

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.extensions.value
import com.orange.ouds.core.theme.LocalColorMode
import com.orange.ouds.core.theme.LocalThemeSettings
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.OudsPreviewLightDark
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.theme.OudsThemeContract
import kotlin.math.PI

private val OudsCircularProgressIndicatorSize = 48.dp

// TODO Update description and add design guideline link when available
/**
 * A Circular Progress Indicator shows the progress of a task using a circle. Useful when you need more visual focus or when space is limited.
 *
 * This version of the circular progress indicator is **determinate**. Use the other signature for an indeterminate progress.
 *
 * The component automatically scales all dimensions (stroke width, gap size) proportionally based on its effective size
 * (after applying the modifier). A scale factor is calculated by dividing the actual size by the default size from tokens,
 * then this scale is applied to all dimensions to maintain consistent proportions.
 *
 * > Design name: Progress Indicator
 *
 * > Design version: 1.0.0
 *
 * @param progress The progress of this indicator, where 0.0 represents no progress and 1.0 represents full progress. Values outside of this range are coerced
 *   into the range.
 * @param modifier The [Modifier] to be applied to this circular progress indicator.
 * @param status The status of the progress indicator. Its color is based on this status. See [OudsProgressIndicatorStatus] for allowed values.
 * @param track Whether the track is displayed or not.
 *   Use `true` when the indicator is shown on its own and needs a clear structure. The track helps define the full range of progress and makes the value
 *   easier to read (for determinate variant).
 *   Use `false` when the indicator is embedded inside another component (e.g. button, tag, toast). Also use it when a more minimal and lightweight
 *   appearance is needed.
 *
 * @sample com.orange.ouds.core.component.samples.OudsCircularProgressIndicatorDeterminateSample
 */
@Composable
fun OudsCircularProgressIndicator(
    progress: () -> Float,
    modifier: Modifier = Modifier,
    status: OudsProgressIndicatorStatus = OudsProgressIndicatorDefaults.Status,
    track: Boolean = true
) {
    OudsCircularProgressIndicator(
        nullableProgress = progress,
        modifier = modifier,
        status = status,
        track = track
    )
}

// TODO Update description and add design guideline link when available
/**
 * A Circular Progress Indicator shows the progress of a task using a circle. Useful when you need more visual focus or when space is limited.
 *
 * This version of the circular progress indicator is **indeterminate**. Use the other signature for a determinate progress.
 *
 * The component automatically scales all dimensions (stroke width, gap size) proportionally based on its effective size
 * (after applying the modifier). A scale factor is calculated by dividing the actual size by the default size from tokens,
 * then this scale is applied to all dimensions to maintain consistent proportions.
 *
 * > Design name: Progress Indicator
 *
 * > Design version: 1.0.0
 *
 * @param modifier The [Modifier] to be applied to this circular progress indicator.
 * @param status The status of the progress indicator. Its color is based on this status. See [OudsProgressIndicatorStatus] for allowed values.
 * @param track Whether the track is displayed or not.
 *   Use `true` when the indicator is shown on its own and needs a clear structure. The track helps define the full range of progress and makes the value
 *   easier to read (for determinate variant).
 *   Use `false` when the indicator is embedded inside another component (e.g. button, tag, toast). Also use it when a more minimal and lightweight
 *   appearance is needed.
 *
 * @sample com.orange.ouds.core.component.samples.OudsCircularProgressIndicatorIndeterminateSample
 */
@Composable
fun OudsCircularProgressIndicator(
    modifier: Modifier = Modifier,
    status: OudsProgressIndicatorStatus = OudsProgressIndicatorDefaults.Status,
    track: Boolean = true
) {
    OudsCircularProgressIndicator(
        nullableProgress = null,
        modifier = modifier,
        status = status,
        track = track
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun OudsCircularProgressIndicator(
    nullableProgress: (() -> Float)?,
    modifier: Modifier = Modifier,
    status: OudsProgressIndicatorStatus = OudsProgressIndicatorDefaults.Status,
    track: Boolean = true,
    color: Color? = null
) {
    with(OudsTheme.componentsTokens.progressIndicator) {
        val scale = LocalConfiguration.current.fontScale
        val defaultSize = OudsCircularProgressIndicatorSize * scale

        BoxWithConstraints(modifier = modifier.size(defaultSize)) {
            // The stroke width is equal to 25% of the radius, 12.5% of the diameter
            val strokeWidth = maxWidth * 0.125f
            // The gap corresponds to a 10-degree angle converted into a distance on the circle
            val gapSize = (10f / 360f * PI.toFloat() * maxWidth.value).dp
            val borderRadius = if (LocalThemeSettings.current.roundedCornerProgressIndicators == true) borderRadiusRounded else borderRadiusDefault
            val strokeCap = if (borderRadius.value > 0.dp) StrokeCap.Round else StrokeCap.Butt
            val monochrome = LocalColorMode.current?.monochrome == true
            val monochromeTokens = OudsTheme.componentsTokens.progressIndicatorMonochrome
            val circularProgressIndicatorColor = color.orElse { if (monochrome) monochromeTokens.colorContentIndicator.value else status.color() }
            val trackColor = when {
                track -> if (monochrome) monochromeTokens.colorContentTrack.value else colorContentTrack.value
                else -> Color.Transparent
            }
            val progressIndicatorModifier = Modifier.size(maxWidth, maxHeight)

            if (nullableProgress != null || LocalInspectionMode.current) {
                CircularProgressIndicator(
                    progress = nullableProgress.orElse { { 0.75f } },
                    modifier = progressIndicatorModifier,
                    color = circularProgressIndicatorColor,
                    strokeWidth = strokeWidth,
                    trackColor = trackColor,
                    strokeCap = strokeCap,
                    gapSize = gapSize
                )
            } else {
                CircularProgressIndicator(
                    modifier = progressIndicatorModifier,
                    color = circularProgressIndicatorColor,
                    strokeWidth = strokeWidth,
                    trackColor = trackColor,
                    strokeCap = strokeCap,
                    gapSize = gapSize
                )
            }
        }
    }
}

@OudsPreviewLightDark
@Composable
private fun PreviewCircularProgressIndicator(@PreviewParameter(OudsCircularProgressIndicatorPreviewParameterProvider::class) parameter: OudsCircularProgressIndicatorPreviewParameter) {
    PreviewOudsCircularProgressIndicator(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsCircularProgressIndicator(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsCircularProgressIndicatorPreviewParameter
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        val circularProgressIndicatorPreview: @Composable () -> Unit = {
            OudsCircularProgressIndicator(
                progress = { 0.75f },
                status = status,
                track = track
            )
        }

        if (onColoredBackground) {
            OudsColoredBox(color = OudsColoredBoxColor.BrandPrimary) {
                circularProgressIndicatorPreview()
            }
        } else {
            circularProgressIndicatorPreview()
        }
    }
}

@Suppress("PreviewShouldNotBeCalledRecursively")
@OudsPreview
@Composable
private fun PreviewOudsCircularProgressIndicatorSized(@PreviewParameter(OudsCircularProgressIndicatorSizedPreviewParameterProvider::class) size: Float) {
    PreviewOudsCircularProgressIndicatorSized(theme = getPreviewTheme(), size = size)
}

@Composable
internal fun PreviewOudsCircularProgressIndicatorSized(theme: OudsThemeContract, size: Float) = OudsPreview(theme = theme) {
    OudsCircularProgressIndicator(
        modifier = Modifier.size(size.dp),
        progress = { 0.75f }
    )
}

internal data class OudsCircularProgressIndicatorPreviewParameter(
    val status: OudsProgressIndicatorStatus = OudsProgressIndicatorDefaults.Status,
    val track: Boolean = true,
    val onColoredBackground: Boolean = false
)

internal class OudsCircularProgressIndicatorPreviewParameterProvider :
    BasicPreviewParameterProvider<OudsCircularProgressIndicatorPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsCircularProgressIndicatorPreviewParameter>
    get() = listOf(
        OudsCircularProgressIndicatorPreviewParameter(),
        OudsCircularProgressIndicatorPreviewParameter(status = OudsProgressIndicatorStatus.Neutral),
        OudsCircularProgressIndicatorPreviewParameter(track = false),
        OudsCircularProgressIndicatorPreviewParameter(onColoredBackground = true)
    )

internal class OudsCircularProgressIndicatorSizedPreviewParameterProvider :
    BasicPreviewParameterProvider<Float>(
        OudsCircularProgressIndicatorSize.value / 2f,
        OudsCircularProgressIndicatorSize.value,
        OudsCircularProgressIndicatorSize.value * 2f
    )

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
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.semantics.hideFromAccessibility
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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
 * > Design version: 0.1.0
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
 * > Design version: 0.1.0
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
private fun OudsCircularProgressIndicator(
    nullableProgress: (() -> Float)?,
    status: OudsProgressIndicatorStatus,
    track: Boolean,
    modifier: Modifier = Modifier
) {
    val scale = LocalConfiguration.current.fontScale
    val density = LocalDensity.current

    with(OudsTheme.componentsTokens.progressIndicator) {
        val defaultSize = OudsCircularProgressIndicatorSize * scale

        var strokeWidth by remember { mutableStateOf(computeStrokeWidth(defaultSize)) }
        var gapSize by remember { mutableStateOf(computeGapSize(defaultSize)) }

        val progressIndicatorModifier = modifier
            .size(defaultSize)
            .onSizeChanged { measuredSize ->
                with(density) {
                    val currentSize = measuredSize.width.toDp()
                    strokeWidth = computeStrokeWidth(currentSize)
                    gapSize = computeGapSize(currentSize)
                }
            }
        val borderRadius = if (LocalThemeSettings.current.roundedCornerProgressIndicators == true) borderRadiusRounded else borderRadiusDefault
        val strokeCap = if (borderRadius.value > 0.dp) StrokeCap.Round else StrokeCap.Butt
        val color = progressIndicatorColor(status = status)
        val trackColor = progressIndicatorTrackColor(track = track)

        if (nullableProgress != null || LocalInspectionMode.current) {
            CircularProgressIndicator(
                progress = nullableProgress.orElse { { 0.75f } },
                modifier = progressIndicatorModifier,
                color = color,
                strokeWidth = strokeWidth,
                trackColor = trackColor,
                strokeCap = strokeCap,
                gapSize = gapSize
            )
        } else {
            CircularProgressIndicator(
                modifier = progressIndicatorModifier,
                color = color,
                strokeWidth = strokeWidth,
                trackColor = trackColor,
                strokeCap = strokeCap,
                gapSize = gapSize
            )
        }
    }
}

/**
 * Calculates the stroke width based on the component size.
 * The stroke width is equal to 25% of the radius, 12.5% of the diameter.
 */
private fun computeStrokeWidth(componentSize: Dp): Dp = componentSize * 0.125f

/**
 * Calculates the gap size based on the component size.
 * The gap corresponds to a 10-degree angle converted into a distance on the circle.
 */
private fun computeGapSize(componentSize: Dp): Dp =
    (10f / 360f * PI.toFloat() * componentSize.value).dp

/**
 * A temporary circular progress indicator component used internally by several public components.
 */
@Composable
internal fun InternalOudsCircularProgressIndicator(
    color: Color,
    progress: Float?,
    modifier: Modifier = Modifier,
    scale: Float = 1.0f
) {
    val modifier = modifier
        .size(OudsTheme.componentsTokens.button.sizeLoader.value * scale)
        .semantics { hideFromAccessibility() }
    val strokeWidth = 3.dp * scale
    val trackColor = Color.Transparent
    val strokeCap = StrokeCap.Square
    if (progress != null || LocalInspectionMode.current) {
        CircularProgressIndicator(
            progress = { progress.orElse { 0.75f } },
            modifier = modifier,
            color = color,
            strokeWidth = strokeWidth,
            trackColor = trackColor,
            strokeCap = strokeCap
        )
    } else {
        CircularProgressIndicator(
            modifier = modifier,
            color = color,
            strokeWidth = strokeWidth,
            trackColor = trackColor,
            strokeCap = strokeCap
        )
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
) {
    OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
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

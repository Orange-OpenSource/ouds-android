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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.ProgressIndicatorDefaults.drawStopIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.theme.LocalThemeSettings
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.OudsPreviewLightDark
import com.orange.ouds.core.utilities.PreviewPaddingDefault
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.theme.OudsThemeContract

// TODO Update description and add design guideline link when available
/**
 * A Linear Progress Indicator shows the progress of a task using a horizontal line. It can show a specific value (determinate) or just that something is in
 * progress (indeterminate). Best used inside layouts to show progress.
 *
 * This version of the linear progress indicator is **determinate**. Use the other signature for an indeterminate progress.
 *
 * > Design name: Progress Indicator
 *
 * > Design version: 0.1.0
 *
 * @param progress The progress of this indicator, where 0.0 represents no progress and 1.0 represents full progress. Values outside of this range are coerced
 *   into the range.
 * @param modifier The [Modifier] to be applied to this linear progress indicator.
 * @param status The status of the progress indicator. Its color is based on this status. See [OudsProgressIndicatorStatus] for allowed values.
 * @param track Whether the track is displayed or not.
 *   Use `true` when the indicator is shown on its own and needs a clear structure. The track helps define the full range of progress and makes the value
 *   easier to read (for determinate variant).
 *   Use `false` when the indicator is embedded inside another component (e.g. button, tag, toast). Also use it when a more minimal and lightweight
 *   appearance is needed.
 * @param stopIndicator Whether a stop indicator is displayed or not. It allows to identify the end of the track easily. To respect accessibility criteria, it
 *   is required if the track has a contrast below 3:1 with its container or the surface behind the container.
 * @param helperText Optional additional text displayed with the progress indicator. Helper text can provide context about the process or show the current
 *   progress value.
 *
 * @sample com.orange.ouds.core.component.samples.OudsLinearProgressIndicatorDeterminateSample
 */
@Composable
fun OudsLinearProgressIndicator(
    progress: () -> Float,
    modifier: Modifier = Modifier,
    status: OudsProgressIndicatorStatus = OudsProgressIndicatorDefaults.Status,
    track: Boolean = true,
    stopIndicator: Boolean = false,
    helperText: String? = null
) {
    OudsLinearProgressIndicator(
        nullableProgress = progress,
        modifier = modifier,
        status = status,
        track = track,
        stopIndicator = stopIndicator,
        helperText = helperText
    )
}

// TODO Update description and add design guideline link when available
/**
 * A Linear Progress Indicator shows the progress of a task using a horizontal line. It can show a specific value (determinate) or just that something is in
 * progress (indeterminate). Best used inside layouts to show progress.
 *
 * This version of the linear progress indicator is **indeterminate**. Use the other signature for a determinate progress.
 *
 * > Design name: Progress Indicator
 *
 * > Design version: 0.1.0
 *
 * @param modifier The [Modifier] to be applied to this linear progress indicator.
 * @param status The status of the progress indicator. Its color is based on this status. See [OudsProgressIndicatorStatus] for allowed values.
 * @param track Whether the track is displayed or not.
 *   Use `true` when the indicator is shown on its own and needs a clear structure. The track helps define the full range of progress and makes the value
 *   easier to read (for determinate variant).
 *   Use `false` when the indicator is embedded inside another component (e.g. button, tag, toast). Also use it when a more minimal and lightweight
 *   appearance is needed.
 * @param stopIndicator Whether a stop indicator is displayed or not. It allows to identify the end of the track easily. To respect accessibility criteria, it
 *   is required if the track has a contrast below 3:1 with its container or the surface behind the container.
 * @param helperText Optional additional text displayed with the progress indicator. Helper text can provide context about the process or show the current
 *   progress value.
 *
 * @sample com.orange.ouds.core.component.samples.OudsLinearProgressIndicatorIndeterminateSample
 */
@Composable
fun OudsLinearProgressIndicator(
    modifier: Modifier = Modifier,
    status: OudsProgressIndicatorStatus = OudsProgressIndicatorDefaults.Status,
    track: Boolean = true,
    stopIndicator: Boolean = false,
    helperText: String? = null
) {
    OudsLinearProgressIndicator(
        nullableProgress = null,
        modifier = modifier,
        status = status,
        track = track,
        stopIndicator = stopIndicator,
        helperText = helperText
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun OudsLinearProgressIndicator(
    nullableProgress: (() -> Float)?,
    status: OudsProgressIndicatorStatus,
    track: Boolean,
    stopIndicator: Boolean,
    helperText: String?,
    modifier: Modifier = Modifier
) {
    val scale = LocalConfiguration.current.fontScale
    with(OudsTheme.componentsTokens.progressIndicator) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(spacePaddingBlock.value)
        ) {
            val progressIndicatorModifier = Modifier
                .height(sizeLinearIndicatorHeight.dp * scale)
                .fillMaxWidth()
            val color = progressIndicatorColor(status = status)
            val trackColor = progressIndicatorTrackColor(track = track)
            val gapSize = ProgressIndicatorDefaults.LinearIndicatorTrackGapSize * scale
            val borderRadius = if (LocalThemeSettings.current.roundedCornerProgressIndicators == true) borderRadiusRounded else borderRadiusDefault
            val strokeCap = if (borderRadius.value > 0.dp) StrokeCap.Round else StrokeCap.Butt

            if (nullableProgress != null || LocalInspectionMode.current) {
                LinearProgressIndicator(
                    progress = nullableProgress.orElse { { 0.75f } },
                    modifier = progressIndicatorModifier,
                    color = color,
                    trackColor = trackColor,
                    gapSize = gapSize,
                    strokeCap = strokeCap,
                    drawStopIndicator = {
                        if (stopIndicator) {
                            stopIndicator(color = color, strokeCap = strokeCap)
                        }
                    }
                )
            } else {
                LinearProgressIndicator(
                    modifier = progressIndicatorModifier,
                    color = color,
                    trackColor = trackColor,
                    gapSize = gapSize,
                    strokeCap = strokeCap
                )
            }

            if (!helperText.isNullOrBlank()) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = helperText,
                    style = OudsTheme.typography.label.medium.default,
                    color = OudsTheme.colorScheme.content.default,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
private fun DrawScope.stopIndicator(color: Color, strokeCap: StrokeCap) {
    drawStopIndicator(
        drawScope = this,
        stopSize = ProgressIndicatorDefaults.LinearTrackStopIndicatorSize,
        color = color,
        strokeCap = strokeCap,
    )
}

@OudsPreviewLightDark
@Composable
private fun PreviewLinearProgressIndicator(@PreviewParameter(OudsLinearProgressIndicatorPreviewParameterProvider::class) parameter: OudsLinearProgressIndicatorPreviewParameter) {
    PreviewOudsLinearProgressIndicator(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsLinearProgressIndicator(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsLinearProgressIndicatorPreviewParameter
) {
    OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
        with(parameter) {
            val linearProgressIndicatorPreview: @Composable () -> Unit = {
                OudsLinearProgressIndicator(
                    modifier = Modifier.padding(all = PreviewPaddingDefault),
                    progress = { 0.75f },
                    status = status,
                    track = track,
                    helperText = helperText
                )
            }

            if (onColoredBackground) {
                OudsColoredBox(color = OudsColoredBoxColor.BrandPrimary) {
                    linearProgressIndicatorPreview()
                }
            } else {
                linearProgressIndicatorPreview()
            }
        }
    }
}

@OudsPreview
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsLinearProgressIndicatorWithLongHelperText() = PreviewOudsLinearProgressIndicatorWithLongHelperText(theme = getPreviewTheme())

@Composable
internal fun PreviewOudsLinearProgressIndicatorWithLongHelperText(theme: OudsThemeContract) {
    OudsPreview(theme = theme) {
        OudsLinearProgressIndicator(
            modifier = Modifier.padding(all = PreviewPaddingDefault),
            helperText = "Uploading file: http://download-website.com/directory/file.jpg"
        )
    }
}

internal data class OudsLinearProgressIndicatorPreviewParameter(
    val status: OudsProgressIndicatorStatus = OudsProgressIndicatorDefaults.Status,
    val track: Boolean = true,
    val helperText: String? = null,
    val onColoredBackground: Boolean = false
)

internal class OudsLinearProgressIndicatorPreviewParameterProvider :
    BasicPreviewParameterProvider<OudsLinearProgressIndicatorPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsLinearProgressIndicatorPreviewParameter>
    get() = listOf(
        OudsLinearProgressIndicatorPreviewParameter(),
        OudsLinearProgressIndicatorPreviewParameter(status = OudsProgressIndicatorStatus.Neutral),
        OudsLinearProgressIndicatorPreviewParameter(track = false),
        OudsLinearProgressIndicatorPreviewParameter(helperText = "Loading..."),
        OudsLinearProgressIndicatorPreviewParameter(onColoredBackground = true)
    )

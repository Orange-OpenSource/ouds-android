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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.ProgressIndicatorDefaults.drawStopIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.theme.LocalThemeSettings
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.OudsPreviewLightDark
import com.orange.ouds.core.utilities.PreviewEnumEntries
import com.orange.ouds.core.utilities.PreviewFlowRow
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
 * > Design name: Linear Progress Indicator
 *
 * > Design version: 1.1.0
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
 * @param gapSize The size of the gap between the progress indicator and the track.
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
    helperText: String? = null,
    gapSize: OudsProgressIndicatorGapSize = OudsProgressIndicatorGapSize.Default
) {
    OudsLinearProgressIndicator(
        nullableProgress = progress,
        modifier = modifier,
        status = status,
        track = track,
        stopIndicator = stopIndicator,
        helperText = OudsDeterminateLinearProgressIndicatorHelperText(false, helperText),
        gapSize = gapSize
    )
}

// TODO Update description and add design guideline link when available
/**
 * A Linear Progress Indicator shows the progress of a task using a horizontal line. It can show a specific value (determinate) or just that something is in
 * progress (indeterminate). Best used inside layouts to show progress.
 *
 * This version of the linear progress indicator is **determinate**. Use the other signature for an indeterminate progress.
 *
 * > Design name: Linear Progress Indicator
 *
 * > Design version: 1.1.0
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
 * @param gapSize The size of the gap between the progress indicator and the track.
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
    helperText: OudsDeterminateLinearProgressIndicatorHelperText,
    gapSize: OudsProgressIndicatorGapSize = OudsProgressIndicatorGapSize.Default
) {
    OudsLinearProgressIndicator(
        nullableProgress = progress,
        modifier = modifier,
        status = status,
        track = track,
        stopIndicator = stopIndicator,
        helperText = helperText,
        gapSize = gapSize
    )
}

// TODO Update description and add design guideline link when available
/**
 * A Linear Progress Indicator shows the progress of a task using a horizontal line. It can show a specific value (determinate) or just that something is in
 * progress (indeterminate). Best used inside layouts to show progress.
 *
 * This version of the linear progress indicator is **determinate**. Use the other signature for an indeterminate progress.
 *
 * > Design name: Linear Progress Indicator
 *
 * > Design version: 1.1.0
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
@Deprecated(
    "Maintained for binary compatibility. Use overload with additional parameters.",
    level = DeprecationLevel.HIDDEN
)
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
        progress = progress,
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
 * > Design name: Linear Progress Indicator
 *
 * > Design version: 1.1.0
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
 * @param gapSize The size of the gap between the progress indicator and the track.
 *
 * @sample com.orange.ouds.core.component.samples.OudsLinearProgressIndicatorIndeterminateSample
 */
@Composable
fun OudsLinearProgressIndicator(
    modifier: Modifier = Modifier,
    status: OudsProgressIndicatorStatus = OudsProgressIndicatorDefaults.Status,
    track: Boolean = true,
    stopIndicator: Boolean = false,
    helperText: String? = null,
    gapSize: OudsProgressIndicatorGapSize = OudsProgressIndicatorDefaults.GapSize
) {
    OudsLinearProgressIndicator(
        nullableProgress = null,
        modifier = modifier,
        status = status,
        track = track,
        stopIndicator = stopIndicator,
        helperText = OudsIndeterminateLinearProgressIndicatorHelperText(helperText),
        gapSize = gapSize
    )
}

// TODO Update description and add design guideline link when available
/**
 * A Linear Progress Indicator shows the progress of a task using a horizontal line. It can show a specific value (determinate) or just that something is in
 * progress (indeterminate). Best used inside layouts to show progress.
 *
 * This version of the linear progress indicator is **indeterminate**. Use the other signature for a determinate progress.
 *
 * > Design name: Linear Progress Indicator
 *
 * > Design version: 1.1.0
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
 * @param gapSize The size of the gap between the progress indicator and the track.
 *
 * @sample com.orange.ouds.core.component.samples.OudsLinearProgressIndicatorIndeterminateSample
 */
@Composable
fun OudsLinearProgressIndicator(
    modifier: Modifier = Modifier,
    status: OudsProgressIndicatorStatus = OudsProgressIndicatorDefaults.Status,
    track: Boolean = true,
    stopIndicator: Boolean = false,
    helperText: OudsIndeterminateLinearProgressIndicatorHelperText,
    gapSize: OudsProgressIndicatorGapSize = OudsProgressIndicatorDefaults.GapSize
) {
    OudsLinearProgressIndicator(
        nullableProgress = null,
        modifier = modifier,
        status = status,
        track = track,
        stopIndicator = stopIndicator,
        helperText = helperText,
        gapSize = gapSize
    )
}

// TODO Update description and add design guideline link when available
/**
 * A Linear Progress Indicator shows the progress of a task using a horizontal line. It can show a specific value (determinate) or just that something is in
 * progress (indeterminate). Best used inside layouts to show progress.
 *
 * This version of the linear progress indicator is **indeterminate**. Use the other signature for a determinate progress.
 *
 * > Design name: Linear Progress Indicator
 *
 * > Design version: 1.1.0
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
@Deprecated(
    "Maintained for binary compatibility. Use overload with additional parameters.",
    level = DeprecationLevel.HIDDEN
)
@Composable
fun OudsLinearProgressIndicator(
    modifier: Modifier = Modifier,
    status: OudsProgressIndicatorStatus = OudsProgressIndicatorDefaults.Status,
    track: Boolean = true,
    stopIndicator: Boolean = false,
    helperText: String? = null
) {
    OudsLinearProgressIndicator(
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
    helperText: OudsLinearProgressIndicatorHelperText?,
    gapSize: OudsProgressIndicatorGapSize,
    modifier: Modifier = Modifier
) {
    val scale = LocalConfiguration.current.fontScale
    with(OudsTheme.components.progressIndicator) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(space.paddingBlock)
        ) {
            val progressIndicatorModifier = Modifier
                .height(size.linearIndicatorHeight * scale)
                .fillMaxWidth()
            val color = progressIndicatorColor(status = status)
            val trackColor = progressIndicatorTrackColor(track = track)
            val gapSizeValue = when (gapSize) {
                OudsProgressIndicatorGapSize.Default -> ProgressIndicatorDefaults.LinearIndicatorTrackGapSize
                OudsProgressIndicatorGapSize.Small -> 1.dp
            } * scale
            val borderRadius = if (LocalThemeSettings.current.roundedCornerProgressIndicators == true) border.radius.rounded else border.radius.default
            val strokeCap = if (borderRadius > 0.dp) StrokeCap.Round else StrokeCap.Butt

            if (nullableProgress != null || LocalInspectionMode.current) {
                LinearProgressIndicator(
                    progress = nullableProgress.orElse { { 0.75f } },
                    modifier = progressIndicatorModifier,
                    color = color,
                    trackColor = trackColor,
                    gapSize = gapSizeValue,
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
                    gapSize = gapSizeValue,
                    strokeCap = strokeCap
                )
            }

            helperText?.Content(
                modifier = Modifier.fillMaxWidth(),
                extraParameters = OudsProgressIndicatorHelperText.ExtraParameters(nullableProgress)
            )
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

class OudsDeterminateLinearProgressIndicatorHelperText(
    progress: Boolean,
    text: String?,
    progressAlignment: Alignment.Horizontal = OudsLinearProgressIndicatorHelperTextDefauts.progressAlignment(text),
    textAlignment: Alignment.Horizontal = OudsLinearProgressIndicatorHelperTextDefauts.textAlignment(progress)
) : OudsLinearProgressIndicatorHelperText(progress, text, progressAlignment, textAlignment)

class OudsIndeterminateLinearProgressIndicatorHelperText(
    text: String?,
    alignment: Alignment.Horizontal = OudsLinearProgressIndicatorHelperTextDefauts.textAlignment(false)
) : OudsLinearProgressIndicatorHelperText(false, text, Alignment.CenterHorizontally, alignment)

open class OudsLinearProgressIndicatorHelperText internal constructor(
    progress: Boolean,
    text: String?,
    progressAlignment: Alignment.Horizontal,
    textAlignment: Alignment.Horizontal
) : OudsProgressIndicatorHelperText(progress, text, progressAlignment, textAlignment) {

    @Composable
    override fun getHorizontalArrangement(alignments: List<Alignment.Horizontal>): Arrangement.Horizontal {
        return if (alignments.size == 1) {
            val bias = alignments.first().getBias(LocalLayoutDirection.current)
            when {
                bias > -0.5f && bias < 0.5f -> Arrangement.Center
                bias <= -0.5f -> Arrangement.Start
                else -> Arrangement.End
            }
        } else {
            Arrangement.SpaceBetween
        }
    }

    @Composable
    override fun getTextAlign(alignment: Alignment.Horizontal, index: Int, count: Int): TextAlign {
        return when {
            count == 1 -> {
                val bias = alignment.getBias(LocalLayoutDirection.current)
                when {
                    bias > -0.5f && bias < 0.5f -> TextAlign.Center
                    bias <= -0.5f -> TextAlign.Start
                    else -> TextAlign.End
                }
            }
            index == 0 -> TextAlign.Start
            else -> TextAlign.End
        }
    }
}

private object OudsLinearProgressIndicatorHelperTextDefauts {

    fun progressAlignment(text: String?): Alignment.Horizontal {
        return if (text == null) Alignment.CenterHorizontally else Alignment.Start
    }

    fun textAlignment(progress: Boolean): Alignment.Horizontal {
        return if (!progress) Alignment.CenterHorizontally else Alignment.End
    }
}

@OudsPreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsLinearProgressIndicator(@PreviewParameter(OudsLinearProgressIndicatorPreviewParameterProvider::class) parameter: OudsLinearProgressIndicatorPreviewParameter) {
    PreviewOudsLinearProgressIndicator(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsLinearProgressIndicator(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsLinearProgressIndicatorPreviewParameter
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        val linearProgressIndicatorPreview: @Composable () -> Unit = {
            PreviewEnumEntries<OudsProgressIndicatorStatus>(maxEnumEntriesInEachRow = 1) { status ->
                OudsLinearProgressIndicator(
                    progress = { 0.75f },
                    status = status,
                    track = track,
                    stopIndicator = stopIndicator,
                    helperText = helperText,
                    gapSize = gapSize
                )
            }
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

@OudsPreview
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsLinearProgressIndicatorWithHelperText() = PreviewOudsLinearProgressIndicatorWithHelperText(theme = getPreviewTheme())

@Composable
internal fun PreviewOudsLinearProgressIndicatorWithHelperText(theme: OudsThemeContract) = OudsPreview(theme = theme) {
    val loadingText = "Loading..."
    val multiLineText = "Uploading file\nhttp://download-website.com/directory/file.jpg"
    val helperTexts = listOf(
        OudsDeterminateLinearProgressIndicatorHelperText(true, null, progressAlignment = Alignment.Start),
        OudsDeterminateLinearProgressIndicatorHelperText(true, null, progressAlignment = Alignment.CenterHorizontally),
        OudsDeterminateLinearProgressIndicatorHelperText(true, null, progressAlignment = Alignment.End),
        OudsDeterminateLinearProgressIndicatorHelperText(false, loadingText, textAlignment = Alignment.Start),
        OudsDeterminateLinearProgressIndicatorHelperText(false, loadingText, textAlignment = Alignment.CenterHorizontally),
        OudsDeterminateLinearProgressIndicatorHelperText(false, loadingText, textAlignment = Alignment.End),
        OudsDeterminateLinearProgressIndicatorHelperText(true, loadingText, progressAlignment = Alignment.Start, textAlignment = Alignment.End),
        OudsDeterminateLinearProgressIndicatorHelperText(true, loadingText, progressAlignment = Alignment.End, textAlignment = Alignment.Start),
        OudsDeterminateLinearProgressIndicatorHelperText(true, multiLineText, textAlignment = Alignment.CenterHorizontally)
    )

    PreviewFlowRow(
        items = helperTexts.indices.map { it.toString() },
        itemName = { "" },
        maxItemsInEachRow = 1
    ) { item ->
        val index = item.toInt()
        OudsLinearProgressIndicator(
            progress = { 0.75f },
            helperText = helperTexts[index]
        )
    }
}

internal data class OudsLinearProgressIndicatorPreviewParameter(
    val track: Boolean = true,
    val stopIndicator: Boolean = false,
    val helperText: String? = null,
    val onColoredBackground: Boolean = false,
    val gapSize: OudsProgressIndicatorGapSize = OudsProgressIndicatorDefaults.GapSize
)

internal class OudsLinearProgressIndicatorPreviewParameterProvider :
    BasicPreviewParameterProvider<OudsLinearProgressIndicatorPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsLinearProgressIndicatorPreviewParameter>
    get() = listOf(
        OudsLinearProgressIndicatorPreviewParameter(),
        OudsLinearProgressIndicatorPreviewParameter(track = false),
        OudsLinearProgressIndicatorPreviewParameter(stopIndicator = true),
        OudsLinearProgressIndicatorPreviewParameter(helperText = "Loading..."),
        OudsLinearProgressIndicatorPreviewParameter(onColoredBackground = true),
        OudsLinearProgressIndicatorPreviewParameter(gapSize = OudsProgressIndicatorGapSize.Small)
    )

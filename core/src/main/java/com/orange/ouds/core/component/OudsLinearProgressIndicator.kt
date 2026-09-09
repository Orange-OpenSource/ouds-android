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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.component.content.OudsComponentContent
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
        helperText = helperText?.let { OudsIndeterminateLinearProgressIndicatorHelperText(it) },
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
                extraParameters = OudsLinearProgressIndicatorHelperText.ExtraParameters(nullableProgress)
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

/**
 * Configuration for helper text displayed alongside a determinate linear progress indicator.
 *
 * Helper text can display the current progress percentage and/or a custom text label with configurable alignment.
 *
 * Note: When both progress and label are displayed, expected values for alignments are [Alignment.Start]
 * and [Alignment.End]. Other alignment combinations may produce unexpected results.
 *
 * @param progress Whether to display the progress percentage (e.g., "75%").
 * @param label Custom text label to display.
 * @param progressAlignment Horizontal alignment for the progress percentage text.
 * @param labelAlignment Horizontal alignment for the custom label text.
 */
class OudsDeterminateLinearProgressIndicatorHelperText(
    progress: Boolean = true,
    label: String? = null,
    progressAlignment: Alignment.Horizontal = OudsLinearProgressIndicatorHelperTextDefauts.progressAlignment(label),
    labelAlignment: Alignment.Horizontal = OudsLinearProgressIndicatorHelperTextDefauts.labelAlignment(progress)
) : OudsLinearProgressIndicatorHelperText(progress, label, progressAlignment, labelAlignment)

/**
 * Configuration for helper text displayed alongside an indeterminate linear progress indicator.
 *
 * @param label Text label to display.
 * @param alignment Horizontal alignment for the label text.
 */
class OudsIndeterminateLinearProgressIndicatorHelperText(
    label: String,
    alignment: Alignment.Horizontal = OudsLinearProgressIndicatorHelperTextDefauts.labelAlignment(false)
) : OudsLinearProgressIndicatorHelperText(false, label, Alignment.CenterHorizontally, alignment)

/**
 * Base class for helper text configuration in linear progress indicators.
 *
 * @property progress Whether to display the progress percentage.
 * @property label Custom text label.
 * @property progressAlignment Horizontal alignment for the progress percentage.
 * @property labelAlignment Horizontal alignment for the custom label.
 */
open class OudsLinearProgressIndicatorHelperText internal constructor(
    val progress: Boolean,
    val label: String?,
    val progressAlignment: Alignment.Horizontal,
    val labelAlignment: Alignment.Horizontal
) : OudsComponentContent<OudsLinearProgressIndicatorHelperText.ExtraParameters>(ExtraParameters::class.java) {

    @ConsistentCopyVisibility
    data class ExtraParameters internal constructor(
        internal val progress: (() -> Float)?
    ) : OudsComponentContent.ExtraParameters()

    private fun Alignment.Horizontal.getBias(layoutDirection: LayoutDirection): Float {
        // Calculate alignment bias from -1 (start) to 1 (end) for determining display order
        val space = 100
        val horizontalPosition = align(0, space, layoutDirection)
        return horizontalPosition.toFloat() * 2f / space.toFloat() - 1f
    }

    @Composable
    override fun Content(modifier: Modifier) {
        val layoutDirection = LocalLayoutDirection.current
        val textItems = buildList {
            if (progress) {
                extraParameters.progress?.let { progressLambda ->
                    add(progressIndicatorHelperTextProgress(progressLambda) to progressAlignment)
                }
            }
            if (!label.isNullOrBlank()) {
                add(label to labelAlignment)
            }
        }

        if (textItems.isNotEmpty()) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val textModifier = if (textItems.size == 1) Modifier.fillMaxWidth() else Modifier
                textItems.sortedBy { it.second.getBias(layoutDirection) }
                    .forEachIndexed { index, textItem ->
                        if (index > 0) {
                            // The Spacer allows to specify a spacing when arrangement is Arrangement.SpaceBetween
                            Spacer(modifier = Modifier.width(OudsTheme.components.progressIndicator.space.columnGap))
                        }
                        val textAlign = when {
                            textItems.size == 1 -> {
                                val bias = textItem.second.getBias(LocalLayoutDirection.current)
                                when {
                                    bias > -0.5f && bias < 0.5f -> TextAlign.Center
                                    bias <= -0.5f -> TextAlign.Start
                                    else -> TextAlign.End
                                }
                            }
                            index == 0 -> TextAlign.Start
                            else -> TextAlign.End
                        }
                        Text(
                            modifier = textModifier,
                            text = textItem.first,
                            style = OudsTheme.typography.label.medium.default,
                            color = OudsTheme.colorScheme.content.default,
                            textAlign = textAlign
                        )
                    }
            }
        }
    }
}

private object OudsLinearProgressIndicatorHelperTextDefauts {

    fun progressAlignment(text: String?): Alignment.Horizontal {
        return if (text == null) Alignment.CenterHorizontally else Alignment.Start
    }

    fun labelAlignment(progress: Boolean): Alignment.Horizontal {
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
    val loadingLabel = "Loading..."
    val multiLineLabel = "Uploading file\nhttp://download-website.com/directory/file.jpg"
    val helperTexts = listOf(
        OudsDeterminateLinearProgressIndicatorHelperText(true, null, progressAlignment = Alignment.Start),
        OudsDeterminateLinearProgressIndicatorHelperText(true, null, progressAlignment = Alignment.CenterHorizontally),
        OudsDeterminateLinearProgressIndicatorHelperText(true, null, progressAlignment = Alignment.End),
        OudsDeterminateLinearProgressIndicatorHelperText(false, loadingLabel, labelAlignment = Alignment.Start),
        OudsDeterminateLinearProgressIndicatorHelperText(false, loadingLabel, labelAlignment = Alignment.CenterHorizontally),
        OudsDeterminateLinearProgressIndicatorHelperText(false, loadingLabel, labelAlignment = Alignment.End),
        OudsDeterminateLinearProgressIndicatorHelperText(true, loadingLabel, progressAlignment = Alignment.Start, labelAlignment = Alignment.End),
        OudsDeterminateLinearProgressIndicatorHelperText(true, loadingLabel, progressAlignment = Alignment.End, labelAlignment = Alignment.Start),
        OudsDeterminateLinearProgressIndicatorHelperText(true, multiLineLabel, labelAlignment = Alignment.CenterHorizontally)
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

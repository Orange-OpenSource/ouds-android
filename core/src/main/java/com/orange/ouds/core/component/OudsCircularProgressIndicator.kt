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

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.component.content.OudsComponentContent
import com.orange.ouds.core.theme.LocalThemeSettings
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.OudsPreviewDevice
import com.orange.ouds.core.utilities.OudsPreviewableComponent
import com.orange.ouds.core.utilities.PreviewEnumEntries
import com.orange.ouds.core.utilities.PreviewFlowRow
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.theme.OudsThemeContract
import kotlin.enums.enumEntries
import kotlin.math.PI

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
 * > Design name: Circular Progress Indicator
 *
 * > Design version: 1.2.0
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
 * @param gapSize The size of the gap between the progress indicator and the track.
 *
 * @sample com.orange.ouds.core.component.samples.OudsCircularProgressIndicatorDeterminateSample
 */
@Composable
fun OudsCircularProgressIndicator(
    progress: () -> Float,
    modifier: Modifier = Modifier,
    status: OudsProgressIndicatorStatus = OudsProgressIndicatorDefaults.Status,
    track: Boolean = true,
    gapSize: OudsProgressIndicatorGapSize = OudsProgressIndicatorGapSize.Default,
    helperText: OudsDeterminateCircularProgressIndicatorHelperText? = null,
    progressSize: Dp = OudsProgressIndicatorDefaults.CircularSize
) {
    OudsCircularProgressIndicator(
        nullableProgress = progress,
        modifier = modifier,
        status = status,
        track = track,
        gapSize = gapSize,
        helperText = helperText,
        progressSize = progressSize
    )
}

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
 * > Design name: Circular Progress Indicator
 *
 * > Design version: 1.2.0
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
 * @param gapSize The size of the gap between the progress indicator and the track.
 *
 * @sample com.orange.ouds.core.component.samples.OudsCircularProgressIndicatorDeterminateSample
 */
@Deprecated(
    "Maintained for binary compatibility. Use overload with additional parameters.",
    level = DeprecationLevel.HIDDEN
)
@Composable
fun OudsCircularProgressIndicator(
    progress: () -> Float,
    modifier: Modifier = Modifier,
    status: OudsProgressIndicatorStatus = OudsProgressIndicatorDefaults.Status,
    track: Boolean = true,
    gapSize: OudsProgressIndicatorGapSize = OudsProgressIndicatorGapSize.Default
) {
    OudsCircularProgressIndicator(
        progress = progress,
        modifier = modifier,
        status = status,
        track = track,
        gapSize = gapSize
    )
}

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
 * > Design name: Circular Progress Indicator
 *
 * > Design version: 1.2.0
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
@Deprecated(
    "Maintained for binary compatibility. Use overload with additional parameters.",
    level = DeprecationLevel.HIDDEN
)
@Composable
fun OudsCircularProgressIndicator(
    progress: () -> Float,
    modifier: Modifier = Modifier,
    status: OudsProgressIndicatorStatus = OudsProgressIndicatorDefaults.Status,
    track: Boolean = true
) {
    OudsCircularProgressIndicator(
        progress = progress,
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
 * > Design name: Circular Progress Indicator
 *
 * > Design version: 1.2.0
 *
 * @param modifier The [Modifier] to be applied to this circular progress indicator.
 * @param status The status of the progress indicator. Its color is based on this status. See [OudsProgressIndicatorStatus] for allowed values.
 * @param track Whether the track is displayed or not.
 *   Use `true` when the indicator is shown on its own and needs a clear structure. The track helps define the full range of progress and makes the value
 *   easier to read (for determinate variant).
 *   Use `false` when the indicator is embedded inside another component (e.g. button, tag, toast). Also use it when a more minimal and lightweight
 *   appearance is needed.
 * @param gapSize The size of the gap between the progress indicator and the track.
 *
 * @sample com.orange.ouds.core.component.samples.OudsCircularProgressIndicatorIndeterminateSample
 */
@Composable
fun OudsCircularProgressIndicator(
    modifier: Modifier = Modifier,
    status: OudsProgressIndicatorStatus = OudsProgressIndicatorDefaults.Status,
    track: Boolean = true,
    gapSize: OudsProgressIndicatorGapSize = OudsProgressIndicatorDefaults.GapSize,
    helperText: String? = null,
    progressSize: Dp = OudsProgressIndicatorDefaults.CircularSize
) {
    OudsCircularProgressIndicator(
        nullableProgress = null,
        modifier = modifier,
        status = status,
        track = track,
        gapSize = gapSize,
        helperText = OudsCircularProgressIndicatorHelperText(false, helperText),
        progressSize = progressSize
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
 * > Design name: Circular Progress Indicator
 *
 * > Design version: 1.2.0
 *
 * @param modifier The [Modifier] to be applied to this circular progress indicator.
 * @param status The status of the progress indicator. Its color is based on this status. See [OudsProgressIndicatorStatus] for allowed values.
 * @param track Whether the track is displayed or not.
 *   Use `true` when the indicator is shown on its own and needs a clear structure. The track helps define the full range of progress and makes the value
 *   easier to read (for determinate variant).
 *   Use `false` when the indicator is embedded inside another component (e.g. button, tag, toast). Also use it when a more minimal and lightweight
 *   appearance is needed.
 * @param gapSize The size of the gap between the progress indicator and the track.
 *
 * @sample com.orange.ouds.core.component.samples.OudsCircularProgressIndicatorIndeterminateSample
 */
@Deprecated(
    "Maintained for binary compatibility. Use overload with additional parameters.",
    level = DeprecationLevel.HIDDEN
)
@Composable
fun OudsCircularProgressIndicator(
    modifier: Modifier = Modifier,
    status: OudsProgressIndicatorStatus = OudsProgressIndicatorDefaults.Status,
    track: Boolean = true,
    gapSize: OudsProgressIndicatorGapSize = OudsProgressIndicatorDefaults.GapSize
) {
    OudsCircularProgressIndicator(
        modifier = modifier,
        status = status,
        track = track,
        gapSize = gapSize
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
 * > Design name: Circular Progress Indicator
 *
 * > Design version: 1.2.0
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
@Deprecated(
    "Maintained for binary compatibility. Use overload with additional parameters.",
    level = DeprecationLevel.HIDDEN
)
@Composable
fun OudsCircularProgressIndicator(
    modifier: Modifier = Modifier,
    status: OudsProgressIndicatorStatus = OudsProgressIndicatorDefaults.Status,
    track: Boolean = true
) {
    OudsCircularProgressIndicator(
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
    gapSize: OudsProgressIndicatorGapSize = OudsProgressIndicatorDefaults.GapSize,
    helperText: OudsCircularProgressIndicatorHelperText? = null,
    progressSize: Dp = OudsProgressIndicatorDefaults.CircularSize,
    color: Color? = null
) {
    with(OudsTheme.components.progressIndicator) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(space.paddingBlock)
        ) {
            val scale = LocalConfiguration.current.fontScale
            val scaledProgressSize = progressSize * scale

            // We deliberatly set the modifier here because we want the user to be able to control the circle size
            BoxWithConstraints(modifier = Modifier.size(scaledProgressSize)) {
                // The stroke width is equal to 25% of the radius, 12.5% of the diameter
                val strokeWidth = maxWidth * 0.125f
                val gapSizeValue = when (gapSize) {
                    // The default gap corresponds to a 14-degree angle converted into a distance on the circle
                    OudsProgressIndicatorGapSize.Default -> 14f / 360f * PI.toFloat() * maxWidth.value
                    // The small gap corresponds to 1 dp for the standard size
                    OudsProgressIndicatorGapSize.Small -> maxWidth.value / OudsProgressIndicatorDefaults.CircularSize.value
                }.dp
                val borderRadius = if (LocalThemeSettings.current.roundedCornerProgressIndicators == true) border.radius.rounded else border.radius.default
                val strokeCap = if (borderRadius > 0.dp) StrokeCap.Round else StrokeCap.Butt
                val circularProgressIndicatorColor = color.orElse { progressIndicatorColor(status = status) }
                val trackColor = progressIndicatorTrackColor(track = track)
                val progressIndicatorModifier = Modifier.size(maxWidth, maxHeight)

                if (nullableProgress != null || LocalInspectionMode.current) {
                    CircularProgressIndicator(
                        progress = nullableProgress.orElse { { 0.75f } },
                        modifier = progressIndicatorModifier,
                        color = circularProgressIndicatorColor,
                        strokeWidth = strokeWidth,
                        trackColor = trackColor,
                        strokeCap = strokeCap,
                        gapSize = gapSizeValue
                    )
                } else {
                    CircularProgressIndicator(
                        modifier = progressIndicatorModifier,
                        color = circularProgressIndicatorColor,
                        strokeWidth = strokeWidth,
                        trackColor = trackColor,
                        strokeCap = strokeCap,
                        gapSize = gapSizeValue
                    )
                }
            }

            helperText?.Content(extraParameters = OudsCircularProgressIndicatorHelperText.ExtraParameters(nullableProgress))
        }
    }
}

class OudsDeterminateCircularProgressIndicatorHelperText(
    progress: Boolean,
    text: String?
) : OudsCircularProgressIndicatorHelperText(progress, text)

open class OudsCircularProgressIndicatorHelperText internal constructor(
    val progress: Boolean,
    val text: String?
) : OudsComponentContent<OudsCircularProgressIndicatorHelperText.ExtraParameters>(ExtraParameters::class.java) {

    @ConsistentCopyVisibility
    data class ExtraParameters internal constructor(
        internal val progress: (() -> Float)?
    ) : OudsComponentContent.ExtraParameters()

    @Composable
    override fun Content(modifier: Modifier) {
        if (progress || !text.isNullOrBlank()) {
            Row(
                modifier = modifier,
                horizontalArrangement = Arrangement.spacedBy(OudsTheme.components.progressIndicator.space.columnGap)
            ) {
                val style = OudsTheme.typography.label.medium.default
                val color = OudsTheme.colorScheme.content.default
                if (progress) {
                    extraParameters.progress?.let { progressLambda ->
                        Text(
                            text = progressIndicatorProgressHelperText(progressLambda),
                            style = style,
                            color = color,
                        )
                    }
                }
                if (!text.isNullOrBlank()) {
                    Text(
                        text = text,
                        style = style,
                        color = color,
                    )
                }
            }
        }
    }
}

@Preview(name = "Light", widthDp = OudsPreviewableComponent.CircularProgressIndicator.Default.PreviewWidthDp, device = OudsPreviewDevice)
@Preview(
    name = "Dark",
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL,
    widthDp = OudsPreviewableComponent.CircularProgressIndicator.Default.PreviewWidthDp,
    device = OudsPreviewDevice
)
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
            PreviewEnumEntries<OudsProgressIndicatorStatus> { status ->
                OudsCircularProgressIndicator(
                    progress = { 0.75f },
                    status = status,
                    track = track,
                    gapSize = gapSize,
                    helperText = helperText
                )
            }
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
    PreviewFlowRow(
        items = enumEntries<OudsProgressIndicatorGapSize>().map { it.name },
        itemName = { "$it gap size" },
        content = { item ->
            val gapSize = enumValueOf<OudsProgressIndicatorGapSize>(item)
            OudsCircularProgressIndicator(
                progressSize = size.dp,
                progress = { 0.75f },
                gapSize = gapSize
            )
        }
    )
}

internal data class OudsCircularProgressIndicatorPreviewParameter(
    val track: Boolean = true,
    val onColoredBackground: Boolean = false,
    val gapSize: OudsProgressIndicatorGapSize = OudsProgressIndicatorDefaults.GapSize,
    val helperText: OudsDeterminateCircularProgressIndicatorHelperText? = null
)

internal class OudsCircularProgressIndicatorPreviewParameterProvider :
    BasicPreviewParameterProvider<OudsCircularProgressIndicatorPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsCircularProgressIndicatorPreviewParameter>
    get() = listOf(
        OudsCircularProgressIndicatorPreviewParameter(),
        OudsCircularProgressIndicatorPreviewParameter(track = false),
        OudsCircularProgressIndicatorPreviewParameter(onColoredBackground = true),
        OudsCircularProgressIndicatorPreviewParameter(gapSize = OudsProgressIndicatorGapSize.Small),
        OudsCircularProgressIndicatorPreviewParameter(helperText = OudsDeterminateCircularProgressIndicatorHelperText(true, null)),
    )

internal class OudsCircularProgressIndicatorSizedPreviewParameterProvider :
    BasicPreviewParameterProvider<Float>(
        OudsProgressIndicatorDefaults.CircularSize.value / 2f,
        OudsProgressIndicatorDefaults.CircularSize.value,
        OudsProgressIndicatorDefaults.CircularSize.value * 2f
    )

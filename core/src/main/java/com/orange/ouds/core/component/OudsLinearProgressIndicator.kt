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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.extensions.value
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
 * > Design version: 1.0.0
 *
 * @param progress The progress of this indicator, where 0.0 represents no progress and 1.0 represents full progress. Values outside of this range are coerced
 *   into the range.
 * @param modifier The [Modifier] to be applied to this linear progress indicator.
 * @param brandColor Whether the brand color is used for the indicator color or the default color.
 *   Use `true` for important, user-triggered actions like upload, submit, or confirm. Also use it when maintaining visual consistency with a branded interface
 *   or artistic direction.
 *   Use `false` for background or secondary processes. Use it when the indicator should not compete with the main content or when a more neutral tone
 *   is required.
 * @param track Whether the track is displayed or not.
 *   Use `true` when the indicator is shown on its own and needs a clear structure. The track helps define the full range of progress and makes the value
 *   easier to read (for determinate variant).
 *   Use `false` when the indicator is embedded inside another component (e.g. button, tag, toast). Also use it when a more minimal and lightweight
 *   appearance is needed.
 * @param helperText Optional additional text displayed with the progress indicator. Helper text can provide context about the process or show the current
 *   progress value.
 *
 * @sample com.orange.ouds.core.component.samples.OudsLinearProgressIndicatorDeterminateSample
 */
@Composable
fun OudsLinearProgressIndicator(
    progress: () -> Float,
    modifier: Modifier = Modifier,
    brandColor: Boolean = true,
    track: Boolean = true,
    helperText: String? = null
) {
    OudsLinearProgressIndicator(
        nullableProgress = progress,
        modifier = modifier,
        brandColor = brandColor,
        track = track,
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
 * > Design version: 1.0.0
 *
 * @param modifier The [Modifier] to be applied to this linear progress indicator.
 * @param brandColor Whether the brand color is used for the indicator color or the default color.
 *   Use `true` for important, user-triggered actions like upload, submit, or confirm. Also use it when maintaining visual consistency with a branded interface
 *   or artistic direction.
 *   Use `false` for background or secondary processes. Use it when the indicator should not compete with the main content or when a more neutral tone
 *   is required.
 * @param track Whether the track is displayed or not.
 *   Use `true` when the indicator is shown on its own and needs a clear structure. The track helps define the full range of progress and makes the value
 *   easier to read (for determinate variant).
 *   Use `false` when the indicator is embedded inside another component (e.g. button, tag, toast). Also use it when a more minimal and lightweight
 *   appearance is needed.
 * @param helperText Optional additional text displayed with the progress indicator. Helper text can provide context about the process or show the current
 *   progress value.
 *
 * @sample com.orange.ouds.core.component.samples.OudsLinearProgressIndicatorIndeterminateSample
 */
@Composable
fun OudsLinearProgressIndicator(
    modifier: Modifier = Modifier,
    brandColor: Boolean = true,
    track: Boolean = true,
    helperText: String? = null
) {
    OudsLinearProgressIndicator(
        nullableProgress = null,
        modifier = modifier,
        brandColor = brandColor,
        track = track,
        helperText = helperText
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun OudsLinearProgressIndicator(
    nullableProgress: (() -> Float)?,
    brandColor: Boolean,
    track: Boolean,
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
            val color = if (brandColor) OudsTheme.colorScheme.action.loading else OudsTheme.colorScheme.content.default
            val trackColor = if (track) colorContentTrack.value else Color.Transparent
            val gapSize = ProgressIndicatorDefaults.LinearIndicatorTrackGapSize * scale
            val strokeCap = if (OudsTheme.borders.radius.default.value > 0) StrokeCap.Round else StrokeCap.Square

            nullableProgress?.let {
                LinearProgressIndicator(
                    progress = nullableProgress,
                    modifier = progressIndicatorModifier,
                    color = color,
                    trackColor = trackColor,
                    gapSize = gapSize,
                    strokeCap = strokeCap
                )
            }.orElse {
                LinearProgressIndicator(
                    modifier = progressIndicatorModifier,
                    color = color,
                    trackColor = trackColor,
                    gapSize = gapSize,
                    strokeCap = strokeCap
                )
            }

            helperText?.let {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = it,
                    style = OudsTheme.typography.label.default.medium,
                    color = OudsTheme.colorScheme.content.default,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
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
            OudsLinearProgressIndicator(
                modifier = Modifier.padding(all = PreviewPaddingDefault),
                progress = { 0.75f },
                brandColor = brandColor,
                track = track,
                helperText = helperText
            )
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
    val brandColor: Boolean = true,
    val track: Boolean = true,
    val helperText: String? = null
)

internal class OudsLinearProgressIndicatorPreviewParameterProvider :
    BasicPreviewParameterProvider<OudsLinearProgressIndicatorPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsLinearProgressIndicatorPreviewParameter>
    get() = listOf(
        OudsLinearProgressIndicatorPreviewParameter(),
        OudsLinearProgressIndicatorPreviewParameter(brandColor = false),
        OudsLinearProgressIndicatorPreviewParameter(track = false),
        OudsLinearProgressIndicatorPreviewParameter(helperText = "Loading...")
    )

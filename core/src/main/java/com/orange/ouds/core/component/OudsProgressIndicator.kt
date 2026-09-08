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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import com.orange.ouds.core.R
import com.orange.ouds.core.component.content.OudsComponentContent
import com.orange.ouds.core.extensions.value
import com.orange.ouds.core.theme.LocalColorMode
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.value
import kotlin.math.round

/**
 * The status of an [OudsCircularProgressIndicator] or an [OudsLinearProgressIndicator].
 * It determines the color of the progress indicator.
 */
enum class OudsProgressIndicatorStatus {

    /**
     * Used to highlight primary or brand-related actions.
     * 
     * Recommended for user-initiated operations such as uploads, downloads, installations, onboarding, or other key experiences where reinforcing the brand
     * identity is appropriate.
     */
    Accent,

    /**
     * Default status used when progress has no specific semantic meaning.
     * 
     * Suitable for generic loading, processing, synchronization, or background tasks where only the completion progress needs to be communicated.
     */
    Neutral,

    /**
     * Indicates progress related to an error, recovery, cancellation, or failure.
     * 
     * Use only when the progress itself communicates a negative operation, such as rolling back changes, removing content, or recovering from an error.
     */
    Negative,

    /**
     * Indicates successful progress or a process leading to a successful outcome.
     * 
     * Use for confirmation, completed validations, successful synchronization, or other positive system operations.
     */
    Positive,

    /**
     * Indicates informational or system-related processes.
     * 
     * Use for background synchronization, data retrieval, initialization, or informational operations that are neither positive nor negative.
     */
    Info,

    /**
     * Indicates progress related to an operation that requires user attention or should be monitored.
     * 
     * Use when the process involves caution, validation, or potentially disruptive actions.
     */
    Warning;

    /**
     * The color associated with this status.
     */
    @Composable
    fun color(): Color {
        return toAlertStatus().assetColor
    }

    private fun toAlertStatus(): OudsAlertStatus {
        return when (this) {
            Negative -> OudsAlertStatus.Negative()
            Positive -> OudsAlertStatus.Positive()
            Info -> OudsAlertStatus.Info()
            Warning -> OudsAlertStatus.Warning(layeredTintedPainter = false)
            Accent -> OudsAlertStatus.Accent()
            Neutral -> OudsAlertStatus.Neutral()
        }
    }
}

/**
 * The size of the gap between the progress indicator and the track.
 */
enum class OudsProgressIndicatorGapSize {

    /**
     * Uses the standard gap size.
     * 
     * Recommended for most use cases, providing balanced spacing and optimal visual clarity.
     */
    Default,

    /**
     * Reduces the gap between the progress indicator and the track.
     * 
     * Use when a more compact appearance is preferred or to better align with specific brand styles and visual directions.
     */
    Small
}

/**
 * Contains the default values used by [OudsCircularProgressIndicator] and [OudsLinearProgressIndicator].
 */
object OudsProgressIndicatorDefaults {

    /**
     * The default status.
     */
    val Status = OudsProgressIndicatorStatus.Neutral

    /**
     * The default gap size.
     */
    val GapSize = OudsProgressIndicatorGapSize.Default
}

@Composable
internal fun progressIndicatorColor(status: OudsProgressIndicatorStatus): Color {
    val monochrome = LocalColorMode.current?.monochrome == true
    return if (monochrome) OudsTheme.componentsTokens.progressIndicatorMonochrome.colorContentIndicator.value else status.color()
}

@Composable
internal fun progressIndicatorTrackColor(track: Boolean): Color {
    val monochrome = LocalColorMode.current?.monochrome == true
    return with(OudsTheme.componentsTokens) {
        when {
            track && monochrome -> progressIndicatorMonochrome.colorContentTrack.value
            track && !monochrome -> progressIndicator.colorContentTrack.value
            else -> Color.Transparent
        }
    }
}

abstract class OudsProgressIndicatorHelperText internal constructor(
    val progress: Boolean,
    val text: String?,
    val progressAlignment: Alignment.Horizontal,
    val textAlignment: Alignment.Horizontal
) : OudsComponentContent<OudsProgressIndicatorHelperText.ExtraParameters>(ExtraParameters::class.java) {

    @Composable
    protected abstract fun getHorizontalArrangement(alignments: List<Alignment.Horizontal>): Arrangement.Horizontal

    @Composable
    protected abstract fun getTextAlign(alignment: Alignment.Horizontal, index: Int, count: Int): TextAlign

    @ConsistentCopyVisibility
    data class ExtraParameters internal constructor(
        internal val progress: (() -> Float)?
    ) : OudsComponentContent.ExtraParameters()

    protected fun Alignment.Horizontal.getBias(layoutDirection: LayoutDirection): Float {
        val space = 100
        val horizontalPosition = align(0, space, layoutDirection)
        return horizontalPosition.toFloat() * 2f / space.toFloat() - 1f
    }

    @Composable
    override fun Content(modifier: Modifier) {
        val layoutDirection = LocalLayoutDirection.current
        val textInfos = buildList {
            if (progress) {
                extraParameters.progress?.let { progressLambda ->
                    val progressValue = round(progressLambda() * 100).toInt()
                    val progressText = stringResource(R.string.core_progressIndicator_progressHelperText_label, progressValue)
                    add(progressText to progressAlignment)
                }
            }
            if (!text.isNullOrBlank()) {
                add(text to textAlignment)
            }
        }.sortedBy { it.second.getBias(layoutDirection) }

        if (textInfos.isNotEmpty()) {
            Row(
                modifier = modifier,
                horizontalArrangement = getHorizontalArrangement(textInfos.map { it.second })
            ) {
                textInfos.forEachIndexed { index, textInfo ->
                    if (index != 0) {
                        // The Spacer allows to specify a spacing whatever the value of horizontalArrangement is
                        Spacer(modifier = Modifier.width(OudsTheme.components.progressIndicator.space.columnGap))
                    }
                    Text(
                        text = textInfo.first,
                        style = OudsTheme.typography.label.medium.default,
                        color = OudsTheme.colorScheme.content.default,
                        textAlign = getTextAlign(textInfo.second, index, textInfos.size)
                    )
                }
            }
        }
    }
}

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

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.orange.ouds.core.extensions.value
import com.orange.ouds.core.theme.LocalColorMode
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.value

/**
 * The status of an [OudsCircularProgressIndicator] or an [OudsLinearProgressIndicator].
 * It determines the color of the progress indicator.
 */
enum class OudsProgressIndicatorStatus {

    /**
     * Used for important, user-triggered actions like upload, submit, or confirm. Also use it when maintaining visual consistency with a branded interface
     * or artistic direction.
     */
    Accent,

    /**
     * Used for background or secondary processes. Use it when the indicator should not compete with the main content or when a more neutral tone
     * is required.
     */
    Neutral;

    /**
     * The color associated with this status.
     */
    @Composable
    fun color(): Color {
        return when (this) {
            Neutral -> OudsTheme.colorScheme.content.default
            Accent -> OudsTheme.colorScheme.action.loading
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
    val Status = OudsProgressIndicatorStatus.Accent

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

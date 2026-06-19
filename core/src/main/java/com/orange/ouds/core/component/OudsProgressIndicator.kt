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
import com.orange.ouds.core.theme.OudsTheme

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
 * Contains the default values used by [OudsCircularProgressIndicator] and [OudsLinearProgressIndicator].
 */
object OudsProgressIndicatorDefaults {

    /**
     * The default status.
     */
    val Status = OudsProgressIndicatorStatus.Accent
}
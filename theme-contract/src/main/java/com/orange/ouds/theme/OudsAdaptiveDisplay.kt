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

package com.orange.ouds.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * A class to represent the adaptive displays managed in OUDS.
 */
enum class OudsAdaptiveDisplayType {
    EXTRA_COMPACT, COMPACT, MEDIUM;

    companion object {
        /**
         * Returns the [OudsAdaptiveDisplayType] for the given [windowWidth] in dp.
         */
        fun fromWindowWidth(windowWidth: Dp): OudsAdaptiveDisplayType {
            return when {
                windowWidth < 390.dp -> EXTRA_COMPACT
                windowWidth < 600.dp -> COMPACT
                else -> MEDIUM
            }
        }
    }
}

data class AdaptiveTokenValue(val extraCompact: Dp, val compact: Dp, val medium: Dp)

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

package com.orange.ouds.core.theme

import androidx.compose.material3.adaptive.currentWindowSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.orange.ouds.core.theme.WindowWidthSizeClass.Companion.compute

/**
 * Defines width-based size classes to support responsive layouts in the OUDS design system.
 *
 * This classification is inspired by Material 3's window size class but introduces a custom
 *`EXTRA_COMPACT` category for more granular control on the smallest screens. The theme uses
 * this class to select the appropriate token values (e.g., typography, spacing) based on
 * the available window width.
 *
 * The active class is determined by the [WindowWidthSizeClass.compute] function.
 *
 * @see compute
 */
enum class WindowWidthSizeClass {
    /** Represents the narrowest screens, for devices with a width less than 390.dp. */
    EXTRA_COMPACT,

    /** Represents typical phone screen widths, for devices with a width between 390.dp and 600.dp. */
    COMPACT,

    /** Represents wider screens, such as tablets or phones in landscape mode, with a width of 600.dp or greater. */
    MEDIUM;

    companion object {
        /**
         * Computes the [WindowWidthSizeClass] based on the provided window width.
         *
         * @param windowWidth The width of the window in [Dp].
         * @return The corresponding [WindowWidthSizeClass].
         */
        fun compute(windowWidth: Dp): WindowWidthSizeClass {
            return when {
                windowWidth < 390.dp -> EXTRA_COMPACT
                windowWidth < 600.dp -> COMPACT
                else -> MEDIUM
            }
        }
    }

    internal fun <T> getTokenValue(extraCompact: T, compact: T, medium: T): T {
        return when (this) {
            EXTRA_COMPACT -> extraCompact
            COMPACT -> compact
            MEDIUM -> medium
        }
    }

    internal fun <T> getTokenValue(mobile: T, tablet: T): T {
        return when (this) {
            EXTRA_COMPACT,
            COMPACT -> mobile
            MEDIUM -> tablet
        }
    }
}

/**
 * Returns the current window width in dp.
 */
@Composable
internal fun currentWindowWidth() = with(LocalDensity.current) { currentWindowSize().toSize().toDpSize().width }

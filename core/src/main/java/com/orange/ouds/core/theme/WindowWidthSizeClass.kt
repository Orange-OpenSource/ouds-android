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

internal enum class WindowWidthSizeClass {
    EXTRA_COMPACT, COMPACT, MEDIUM;

    companion object {
        fun compute(windowWidth: Dp): WindowWidthSizeClass {
            return when {
                windowWidth < 390.dp -> EXTRA_COMPACT
                windowWidth < 600.dp -> COMPACT
                else -> MEDIUM
            }
        }
    }

    fun <T> getTokenValue(extraCompact: T, compact: T, medium: T): T {
        return when (this) {
            EXTRA_COMPACT -> extraCompact
            COMPACT -> compact
            MEDIUM -> medium
        }
    }

    fun <T> getTokenValue(mobile: T, tablet: T): T {
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

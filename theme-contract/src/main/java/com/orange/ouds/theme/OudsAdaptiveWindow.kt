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

import androidx.compose.material3.adaptive.currentWindowSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize

/**
 * A class to represent the adaptive windows types managed in OUDS.
 */
enum class OudsAdaptiveWindowType {
    EXTRA_COMPACT, COMPACT, MEDIUM;

    companion object {
        /**
         * Returns the [OudsAdaptiveWindowType] for the given [windowWidth] in dp.
         */
        fun fromWindowWidth(windowWidth: Dp): OudsAdaptiveWindowType {
            return when {
                windowWidth < 390.dp -> EXTRA_COMPACT
                windowWidth < 600.dp -> COMPACT
                else -> MEDIUM
            }
        }
    }
}

data class OudsAdaptiveTokenValue<T>(val extraCompact: T, val compact: T, val medium: T) {
    fun getValue(adaptiveWindowType: OudsAdaptiveWindowType): T = when (adaptiveWindowType) {
        OudsAdaptiveWindowType.EXTRA_COMPACT -> extraCompact
        OudsAdaptiveWindowType.COMPACT -> compact
        OudsAdaptiveWindowType.MEDIUM -> medium
    }
}

/**
 * Returns the current window width in dp.
 */
@Composable
fun currentWindowWidth() = with(LocalDensity.current) { currentWindowSize().toSize().toDpSize().width }

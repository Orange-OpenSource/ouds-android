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

package com.orange.ouds.app.ui.components.coloredbackground

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.orange.ouds.core.component.OudsColoredBoxColor
import com.orange.ouds.foundation.extensions.orElse

@Composable
fun rememberColoredBackgroundDemoState(
    color: OudsColoredBoxColor = ColoredBackgroundDemoStateDefaults.Color
) = rememberSaveable(color, saver = ColoredBackgroundDemoState.Saver) {
    ColoredBackgroundDemoState(color)
}

class ColoredBackgroundDemoState(color: OudsColoredBoxColor) {

    companion object {
        val Saver = Saver<ColoredBackgroundDemoState, OudsColoredBoxColor>(
            save = { it.color },
            restore = { ColoredBackgroundDemoState(it) }
        )
    }

    var color: OudsColoredBoxColor by mutableStateOf(color)
}

object ColoredBackgroundDemoStateDefaults {

    val Color: OudsColoredBoxColor
        @Composable
        get() = OudsColoredBoxColor.BrandPrimary
            .takeIf { it.mode.isSupported }
            .orElse { OudsColoredBoxColor.entries.first { it.mode.isSupported } }
}
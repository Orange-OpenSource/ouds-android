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
import com.orange.ouds.core.component.OudsColoredBox

@Composable
fun rememberColoredBackgroundDemoState(
    color: OudsColoredBox.Color = OudsColoredBox.Color.BrandPrimary
) = rememberSaveable(color, saver = ColoredBackgroundDemoState.Saver) {
    ColoredBackgroundDemoState(color)
}

class ColoredBackgroundDemoState(color: OudsColoredBox.Color) {

    companion object {
        val Saver = Saver<ColoredBackgroundDemoState, OudsColoredBox.Color>(
            save = { it.color },
            restore = { ColoredBackgroundDemoState(it) }
        )
    }

    var color: OudsColoredBox.Color by mutableStateOf(color)
}

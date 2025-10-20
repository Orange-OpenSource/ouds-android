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

package com.orange.ouds.core.component.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import com.orange.ouds.core.theme.LocalHighContrastModeEnabled
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.outerBorder
import com.orange.ouds.core.theme.takeUnlessHairline
import com.orange.ouds.foundation.extensions.orElse

@Composable
internal fun <T : Enum<T>> Modifier.outerBorder(state: T, shape: Shape = RectangleShape, handleHighContrastMode: Boolean = false): Modifier {
    // To be able to distinguish the enabled and the hover states when high contrast mode is activated,
    // the hover state must display the focus border in this case
    return if (state.name == "Focused" || (state.name == "Hovered" && handleHighContrastMode && LocalHighContrastModeEnabled.current)) {
        OudsTheme.borders.width.focus.takeUnlessHairline?.let { width ->
            outerBorder(
                width = width,
                color = OudsTheme.colorScheme.border.focus,
                shape = shape,
                insetWidth = OudsTheme.borders.width.focusInset.takeUnlessHairline.orElse { Dp.Unspecified },
                insetColor = OudsTheme.colorScheme.border.focusInset
            )
        }.orElse {
            this
        }
    } else {
        this
    }
}
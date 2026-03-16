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
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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

internal fun Modifier.bottomBorder(width: Dp, color: Color, cornerRadius: Dp): Modifier {
    return drawWithContent {
        drawContent()
        if (cornerRadius > 0.dp) {
            val cornerRadiusPx = cornerRadius.toPx()
            val path = Path().apply {
                arcTo(
                    rect = Rect(
                        top = size.height - 2 * cornerRadiusPx,
                        left = 0f,
                        bottom = size.height,
                        right = 2 * cornerRadiusPx
                    ),
                    startAngleDegrees = 180f,
                    sweepAngleDegrees = -90f,
                    forceMoveTo = false
                )

                arcTo(
                    rect = Rect(
                        top = size.height - 2 * cornerRadiusPx,
                        left = size.width - 2 * cornerRadiusPx,
                        bottom = size.height,
                        right = size.width,
                    ),
                    startAngleDegrees = 90f,
                    sweepAngleDegrees = -90f,
                    forceMoveTo = false
                )

                arcTo(
                    rect = Rect(
                        top = size.height - 2 * cornerRadiusPx,
                        left = size.width - 2 * cornerRadiusPx,
                        bottom = size.height - width.toPx(),
                        right = size.width,
                    ),
                    startAngleDegrees = 0f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false
                )

                arcTo(
                    rect = Rect(
                        top = size.height - 2 * cornerRadiusPx,
                        left = 0f,
                        right = 2 * cornerRadiusPx,
                        bottom = size.height - width.toPx()
                    ),
                    startAngleDegrees = 90f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false
                )

                close()
            }
            drawPath(path, color = color)
        } else {
            val lineY = size.height - (width.toPx() / 2)
            drawLine(
                color = color,
                start = Offset(0f, lineY),
                end = Offset(size.width, lineY),
                strokeWidth = width.toPx()
            )
        }
    }
}

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

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.util.Locale

enum class OudsBorderStyle {
    None, Solid, Dashed;

    companion object {
        fun fromString(string: String): OudsBorderStyle {
            return try {
                OudsBorderStyle.valueOf(string.lowercase().replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() })
            } catch (exception: IllegalArgumentException) {
                // If the provided border style value is unknown, return the default border style
                None
            }
        }
    }
}

/**
 * Modify element to add a dashed border styled with appearance specified with a [color], a [shape] and a [width], and clip it.
 *
 * @param color Color to paint the border with
 * @param shape Shape of the border
 * @param width Thickness of the border in dp
 */
fun Modifier.dashedBorder(
    color: Color,
    shape: Shape,
    width: Dp = 1.dp
) = this.drawWithContent {
    val outline = shape.createOutline(size, layoutDirection, density = this)
    val dashedStroke = Stroke(
        cap = StrokeCap.Round,
        width = width.toPx(),
        pathEffect = PathEffect.dashPathEffect(
            intervals = floatArrayOf(2.dp.toPx(), 2.dp.toPx())
        )
    )

    drawContent()
    drawOutline(
        outline = outline,
        style = dashedStroke,
        brush = SolidColor(color)
    )
}
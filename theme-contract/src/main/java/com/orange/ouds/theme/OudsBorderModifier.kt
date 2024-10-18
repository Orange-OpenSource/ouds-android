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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StampedPathEffectStyle
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.Dp

/**
 * Modify element to add a dashed border styled with appearance specified with a [width], a [color] and a [shape], and clip it.
 *
 * @param width Thickness of the border in dp
 * @param color Color to paint the border with
 * @param shape Shape of the border
 */
fun Modifier.dashedBorder(
    width: Dp,
    color: Color,
    shape: Shape = RectangleShape,
) = drawWithContent {
    val innerSize = Size(size.width - width.toPx(), size.height - width.toPx())
    val outline = shape.createOutline(innerSize, layoutDirection, density = this)
    val dashedStroke = Stroke(
        width = width.toPx(),
        pathEffect = PathEffect.dashPathEffect(
            intervals = floatArrayOf(width.toPx() * 2f, width.toPx() * 2f)
        )
    )

    drawContent()
    translate(width.toPx() / 2f, width.toPx() / 2f) {
        drawOutline(
            outline = outline,
            style = dashedStroke,
            brush = SolidColor(color)
        )
    }
}

/**
 * Modify element to add a dotted border styled with appearance specified with a [width], a [color] and a [shape], and clip it.
 *
 * @param width Thickness of the border in dp
 * @param color Color to paint the border with
 * @param shape Shape of the border
 */
fun Modifier.dottedBorder(
    width: Dp,
    color: Color,
    shape: Shape = RectangleShape,
) = drawWithContent {
    val dotRadius = width / 2
    val circle = Path()
    circle.addOval(Rect(center = Offset.Zero, radius = dotRadius.toPx()))

    val innerSize = Size(size.width - width.toPx(), size.height - width.toPx())
    val outline = shape.createOutline(innerSize, layoutDirection, density = this)
    val dottedStroke = Stroke(
        width = width.toPx(),
        pathEffect = PathEffect.stampedPathEffect(
            shape = circle,
            advance = (dotRadius * 2).toPx() * 2f,
            phase = 0f,
            style = StampedPathEffectStyle.Translate
        )
    )

    drawContent()
    translate(width.toPx() / 2f, width.toPx() / 2f) {
        drawOutline(
            outline = outline,
            style = dottedStroke,
            brush = SolidColor(color)
        )
    }
}

/**
 * Modify element to add an outer border (drawn outside the element) with appearance specified with a [width], a [color] and a [shape].
 *
 * @param width Thickness of the border in dp
 * @param color Color to paint the border with
 * @param shape Shape of the border
 */
fun Modifier.outerBorder(
    width: Dp,
    color: Color,
    shape: Shape = RectangleShape
) = drawWithContent {
    val outerSize = Size(size.width + width.toPx(), size.height + width.toPx())
    val outline = shape.createOutline(outerSize, layoutDirection, density = this)
    val stroke = Stroke(width = width.toPx())

    drawContent()
    translate(-width.toPx() / 2f, -width.toPx() / 2f) {
        drawOutline(
            outline = outline,
            style = stroke,
            brush = SolidColor(color)
        )
    }
}

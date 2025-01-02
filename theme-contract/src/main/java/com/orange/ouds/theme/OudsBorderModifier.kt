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
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Modify element to add a dashed border styled with appearance specified with a [width], a [color] and a [shape], and clip it.
 *
 * The [intervals] must contain an even number of entries (>=2). The even indices
 * specify "on" intervals and the odd indices represent "off" intervals. The [phase]
 * is the pixel offset into the intervals list (mod the sum of all of the intervals).
 *
 * For example: if `intervals = listOf(10.dp, 20.dp)`, and `phase = 25.dp`, this will set up a dashed
 * border like so: 5 dps off 10 dps on 20 dps off 10 dps on 20 dps off
 *
 * @param width Thickness of the border in dp.
 * @param color Color to paint the border with.
 * @param shape Shape of the border.
 * @param intervals List of "on" and "off" distances for the dashed line segments.
 * @param phase Pixel offset into the intervals list.
 */
fun Modifier.dashedBorder(
    width: Dp,
    color: Color,
    shape: Shape = RectangleShape,
    intervals: List<Dp> = listOf(width * 2, width * 2),
    phase: Dp = 0.dp
) = drawWithContent {
    val innerSize = Size(size.width - width.toPx(), size.height - width.toPx())
    val outline = shape.createOutline(innerSize, layoutDirection, density = this)
    val dashedStroke = Stroke(
        width = width.toPx(),
        pathEffect = PathEffect.dashPathEffect(
            intervals = intervals.map { it.toPx() }.toFloatArray(),
            phase = phase.toPx()
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
 * @param width The width of the border in dp. Use [Dp.Hairline] for a hairline border.
 * @param color The color to paint the border with.
 * @param shape The shape of the border.
 * @param insetWidth The width of the border inset in dp. Use [Dp.Hairline] for a hairline border inset.
 * @param insetColor The color to paint the border inset with.
 */
fun Modifier.outerBorder(
    width: Dp,
    color: Color,
    shape: Shape = RectangleShape,
    insetWidth: Dp = Dp.Unspecified,
    insetColor: Color = Color.Unspecified
) = drawWithContent {
    drawContent()
    drawOuterBorder(width, color, shape)
    drawOuterBorder(insetWidth, insetColor, shape)
}

private fun DrawScope.drawOuterBorder(width: Dp, color: Color, shape: Shape) {
    if (width != Dp.Unspecified) {
        val outerSize = Size(size.width + width.toPx(), size.height + width.toPx())
        translate(-width.toPx() / 2f, -width.toPx() / 2f) {
            drawOutline(
                outline = shape.createOutline(outerSize, layoutDirection, this),
                style = Stroke(width.toPx()),
                brush = SolidColor(color)
            )
        }
    }
}

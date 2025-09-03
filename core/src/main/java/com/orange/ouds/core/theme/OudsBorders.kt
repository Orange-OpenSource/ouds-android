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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
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
import com.orange.ouds.foundation.InternalOudsApi
import com.orange.ouds.theme.tokens.OudsBorderKeyToken
import com.orange.ouds.theme.tokens.semantic.OudsBorderSemanticTokens
import java.util.Locale

/**
 * @suppress
 */
data class OudsBorders(
    val width: Width,
    val radius: Radius,
    val style: Style
) {
    data class Width(
        val none: Dp,
        val default: Dp,
        val thin: Dp,
        val medium: Dp,
        val thick: Dp,
        val thicker: Dp,
        val focus: Dp,
        val focusInset: Dp
    )

    data class Radius(
        val none: Dp,
        val default: Dp,
        val small: Dp,
        val medium: Dp,
        val large: Dp,
        val pill: Dp
    )

    data class Style(
        val default: OudsBorderStyle,
        val drag: OudsBorderStyle
    )
}

internal fun OudsBorderSemanticTokens.getBorders() = OudsBorders(
    width = OudsBorders.Width(
        none = widthNone.dp,
        default = widthDefault.dp,
        thin = widthThin.dp,
        medium = widthMedium.dp,
        thick = widthThick.dp,
        thicker = widthThicker.dp,
        focus = widthFocus.dp,
        focusInset = widthFocusInset.dp
    ),
    radius = OudsBorders.Radius(
        none = radiusNone.dp,
        default = radiusDefault.dp,
        small = radiusSmall.dp,
        medium = radiusMedium.dp,
        large = radiusLarge.dp,
        pill = radiusPill.dp
    ),
    style = OudsBorders.Style(
        default = OudsBorderStyle.fromString(styleDefault),
        drag = OudsBorderStyle.fromString(styleDrag)
    )
)

@Stable
private fun OudsBorders.fromToken(token: OudsBorderKeyToken.Width): Dp {
    return when (token) {
        OudsBorderKeyToken.Width.None -> width.none
        OudsBorderKeyToken.Width.Default -> width.default
        OudsBorderKeyToken.Width.Thin -> width.thin
        OudsBorderKeyToken.Width.Medium -> width.medium
        OudsBorderKeyToken.Width.Thick -> width.thick
        OudsBorderKeyToken.Width.Thicker -> width.thicker
        OudsBorderKeyToken.Width.Focus -> width.focus
        OudsBorderKeyToken.Width.FocusInset -> width.focusInset
    }
}

@Stable
private fun OudsBorders.fromToken(token: OudsBorderKeyToken.Radius): Dp {
    return when (token) {
        OudsBorderKeyToken.Radius.None -> radius.none
        OudsBorderKeyToken.Radius.Default -> radius.default
        OudsBorderKeyToken.Radius.Small -> radius.small
        OudsBorderKeyToken.Radius.Medium -> radius.medium
        OudsBorderKeyToken.Radius.Large -> radius.large
        OudsBorderKeyToken.Radius.Pill -> radius.pill
    }
}

@Stable
private fun OudsBorders.fromToken(token: OudsBorderKeyToken.Style): OudsBorderStyle {
    return when (token) {
        OudsBorderKeyToken.Style.Default -> style.default
        OudsBorderKeyToken.Style.Drag -> style.drag
    }
}

/**
 * Converts an OUDS border radius token to the local border radius value provided by the theme.
 */
@InternalOudsApi
val OudsBorderKeyToken.Radius.value: Dp
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.borders.fromToken(this)

/**
 * Converts an OUDS border style token to the local [OudsBorderStyle] value provided by the theme.
 */
@InternalOudsApi
val OudsBorderKeyToken.Style.value: OudsBorderStyle
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.borders.fromToken(this)

/**
 * Converts an OUDS border width token to the local border width value provided by the theme.
 */
@InternalOudsApi
val OudsBorderKeyToken.Width.value: Dp
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.borders.fromToken(this)

/**
 * Available border styles in OUDS.
 */
enum class OudsBorderStyle {
    None, Solid, Dashed, Dotted;

    internal companion object {
        /**
         * Returns the [OudsBorderStyle] corresponding to the given [string].
         */
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

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

package com.orange.ouds.core.utilities

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import kotlin.math.max

/**
 * A custom painter that draws two other painters layered on top of each other, allowing a specific Color to be applied to each.
 *
 * @param bottomPainter The painter for the bottom layer.
 * @param bottomPainterColor The color to tint the bottom painter.
 * @param topPainter The painter for the top layer.
 * @param topPainterColor The color to tint the top painter.
 */
internal class LayeredTintedPainter(
    private val bottomPainter: Painter,
    private val bottomPainterColor: Color,
    private val topPainter: Painter,
    private val topPainterColor: Color
) : Painter() {

    override fun DrawScope.onDraw() {
        with(bottomPainter) {
            draw(
                size = this@onDraw.size,
                colorFilter = ColorFilter.tint(bottomPainterColor)
            )
        }
        with(topPainter) {
            draw(
                size = this@onDraw.size,
                colorFilter = ColorFilter.tint(topPainterColor)
            )
        }
    }

    override val intrinsicSize: Size
        get() {
            val bottomSize = bottomPainter.intrinsicSize
            val topSize = topPainter.intrinsicSize
            return Size(
                width = max(bottomSize.width, topSize.width),
                height = max(bottomSize.height, topSize.height)
            )
        }

}
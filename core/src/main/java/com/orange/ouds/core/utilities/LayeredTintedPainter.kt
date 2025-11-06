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
 * @param backPainter The painter for the back layer.
 * @param backPainterColor The color to tint the back painter.
 * @param frontPainter The painter for the front layer.
 * @param frontPainterColor The color to tint the front painter.
 */
internal class LayeredTintedPainter(
    private val backPainter: Painter,
    private val backPainterColor: Color,
    private val frontPainter: Painter,
    private val frontPainterColor: Color
) : Painter() {

    override fun DrawScope.onDraw() {
        with(backPainter) {
            draw(
                size = this@onDraw.size,
                colorFilter = ColorFilter.tint(backPainterColor)
            )
        }
        with(frontPainter) {
            draw(
                size = this@onDraw.size,
                colorFilter = ColorFilter.tint(frontPainterColor)
            )
        }
    }

    override val intrinsicSize: Size
        get() {
            val backPainterSize = backPainter.intrinsicSize
            val frontPainterSize = frontPainter.intrinsicSize
            return Size(
                width = max(backPainterSize.width, frontPainterSize.width),
                height = max(backPainterSize.height, frontPainterSize.height)
            )
        }

}
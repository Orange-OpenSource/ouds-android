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

package com.orange.ouds.app.ui.utilities

import androidx.annotation.DrawableRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.orange.ouds.app.R

data class LightDarkResourceId(@DrawableRes val light: Int, @DrawableRes val dark: Int)

@Composable
fun painterResource(id: LightDarkResourceId): Painter {
    return if (isSystemInDarkTheme()) painterResource(id = id.dark) else painterResource(id = id.light)
}

@Composable
fun rememberUntintedIconPainter(): Painter {
    val painter = painterResource(R.drawable.ic_untinted_icon)
    return remember(painter) {
        object : Painter() {
            override val intrinsicSize: Size
                get() = painter.intrinsicSize

            override fun DrawScope.onDraw() {
                val path = Path().apply { addOval(Rect(0f, 0f, size.width, size.height)) }
                clipPath(path) {
                    with(painter) {
                        draw(size)
                    }
                }
            }
        }
    }
}

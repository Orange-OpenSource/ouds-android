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

package com.orange.ouds.core.component.content

import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import com.orange.ouds.foundation.extensions.orElse

/**
 * An icon in a component.
 *
 * @suppress
 */
abstract class OudsComponentIcon<T, S> protected constructor(
    extraParametersClass: Class<T>,
    private val graphicsObjectProvider: @Composable (S) -> Any,
    private val contentDescription: String
) : OudsComponentContent<T>(extraParametersClass) where T : OudsComponentContent.ExtraParameters, S : OudsComponentIcon<T, S> {

    protected constructor(
        extraParametersClass: Class<T>,
        graphicsObject: Any,
        contentDescription: String
    ) : this(extraParametersClass, { graphicsObject }, contentDescription)

    protected open val tint: Color?
        @Composable
        get() = null

    private val graphicsObject: Any
        @Composable
        @Suppress("UNCHECKED_CAST")
        get() = graphicsObjectProvider(this as S)

    @Composable
    override fun Content(modifier: Modifier) {
        val iconTint = tint.orElse { LocalContentColor.current }

        when (val graphicsObject = graphicsObject) {
            is Painter -> Icon(painter = graphicsObject, contentDescription = contentDescription, modifier = modifier, tint = iconTint)
            is ImageVector -> Icon(imageVector = graphicsObject, contentDescription = contentDescription, modifier = modifier, tint = iconTint)
            is ImageBitmap -> Icon(bitmap = graphicsObject, contentDescription = contentDescription, modifier = modifier, tint = iconTint)
            else -> {}
        }
    }
}

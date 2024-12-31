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

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import com.orange.ouds.core.component.icon.OudsIcon
import com.orange.ouds.core.component.icon.OudsIconDefaults
import com.orange.ouds.foundation.extensions.orElse

/**
 * An icon in a component.
 *
 * @suppress
 */
abstract class OudsComponentIcon<T> protected constructor(
    extraParametersClass: Class<T>,
    private val graphicsObject: Any,
    private val contentDescription: String
) : OudsComponentContent<T>(extraParametersClass) where T : OudsComponentContent.ExtraParameters {

    val painter: Painter? = graphicsObject as? Painter
    val imageVector: ImageVector? = graphicsObject as? ImageVector
    val bitmap: ImageBitmap? = graphicsObject as? ImageBitmap

    protected open val tint: Color?
        @Composable
        get() = null

    protected constructor(
        extraParametersClass: Class<T>,
        painter: Painter,
        contentDescription: String
    ) : this(extraParametersClass, painter as Any, contentDescription)

    protected constructor(
        extraParametersClass: Class<T>,
        imageVector: ImageVector,
        contentDescription: String
    ) : this(extraParametersClass, imageVector as Any, contentDescription)

    protected constructor(
        extraParametersClass: Class<T>,
        bitmap: ImageBitmap,
        contentDescription: String
    ) : this(extraParametersClass, bitmap as Any, contentDescription)

    @Composable
    override fun Content(modifier: Modifier) {
        OudsIcon(
            graphicsObject = graphicsObject,
            contentDescription = contentDescription,
            modifier = modifier,
            tint = tint.orElse { OudsIconDefaults.tint }
        )
    }
}

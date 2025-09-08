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
import com.orange.ouds.core.component.OudsButton
import com.orange.ouds.foundation.extensions.orElse

/**
 * An icon or in a component. If clickable, it is displayed as an icon-only [OudsButton].
 *
 * @suppress
 */
abstract class OudsComponentIcon<T> protected constructor(
    extraParametersClass: Class<T>,
    private val graphicsObjectProvider: @Composable () -> Any,
    private val contentDescription: String,
    private val onClick: (() -> Unit)? = null,
) : OudsComponentContent<T>(extraParametersClass) where T : OudsComponentContent.ExtraParameters {

    protected constructor(
        extraParametersClass: Class<T>,
        graphicsObject: Any,
        contentDescription: String,
        onClick: (() -> Unit)? = null,
    ) : this(extraParametersClass, { graphicsObject }, contentDescription, onClick)

    protected open val tint: Color?
        @Composable
        get() = null

    protected open val enabled: Boolean?
        @Composable
        get() = null

    private val graphicsObject: Any
        @Composable
        get() = graphicsObjectProvider()

    @Composable
    override fun Content(modifier: Modifier) {
        val iconTint = tint.orElse { LocalContentColor.current }
        onClick?.let { onClick ->
            when (val graphicsObject = graphicsObject) {
                is Painter -> OudsButton.Icon(painter = graphicsObject, contentDescription = contentDescription)
                is ImageVector -> OudsButton.Icon(imageVector = graphicsObject, contentDescription = contentDescription)
                is ImageBitmap -> OudsButton.Icon(bitmap = graphicsObject, contentDescription = contentDescription)
                else -> null
            }?.let { buttonIcon ->
                OudsButton(
                    icon = buttonIcon,
                    hierarchy = OudsButton.Hierarchy.Minimal,
                    onClick = onClick,
                    modifier = modifier,
                    enabled = enabled.orElse { true },
                )
            }
        }.orElse {
            when (val graphicsObject = graphicsObject) {
                is Painter -> Icon(painter = graphicsObject, contentDescription = contentDescription, modifier = modifier, tint = iconTint)
                is ImageVector -> Icon(imageVector = graphicsObject, contentDescription = contentDescription, modifier = modifier, tint = iconTint)
                is ImageBitmap -> Icon(bitmap = graphicsObject, contentDescription = contentDescription, modifier = modifier, tint = iconTint)
                else -> {}
            }
        }
    }
}

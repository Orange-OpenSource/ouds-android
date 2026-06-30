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
import com.orange.ouds.core.component.OudsButtonAppearance
import com.orange.ouds.core.component.OudsButtonIcon
import com.orange.ouds.core.component.OudsButtonIconBadge
import com.orange.ouds.foundation.extensions.orElse
import kotlin.reflect.KClass

/**
 * An icon in a component. If clickable, it is displayed as an icon-only [OudsButton] with [OudsButtonAppearance.Minimal].
 *
 * @suppress
 */
abstract class OudsComponentIcon<T, S> internal constructor(
    extraParametersClass: KClass<T>,
    private val graphicsObjectProvider: @Composable (S) -> Any,
    private val contentDescriptionProvider: @Composable (S) -> String,
    private val onClick: (() -> Unit)? = null,
) : OudsComponentContent<T>(extraParametersClass) where T : OudsComponentContent.ExtraParameters, S : OudsComponentIcon<T, S> {

    protected constructor(
        extraParametersClass: KClass<T>,
        graphicsObject: Any,
        contentDescription: String,
        onClick: (() -> Unit)? = null,
    ) : this(extraParametersClass, { graphicsObject }, { contentDescription }, onClick)

    protected open val tint: Color?
        @Composable
        get() = null

    internal open val tinted: Boolean = true

    // The badge is not displayed if onClick is null
    internal open val badge: OudsButtonIconBadge?
        @Composable
        get() = null

    internal open val enabled: Boolean?
        @Composable
        get() = null

    private val graphicsObject: Any
        @Composable
        @Suppress("UNCHECKED_CAST")
        get() = graphicsObjectProvider(this as S)

    @Composable
    override fun Content(modifier: Modifier) {
        val iconTint = if (tinted) tint.orElse { LocalContentColor.current } else Color.Unspecified
        @Suppress("UNCHECKED_CAST") val contentDescription = contentDescriptionProvider(this as S)
        onClick?.let { onClick ->
            when (val graphicsObject = graphicsObject) {
                is Painter -> OudsButtonIcon(painter = graphicsObject, contentDescription = contentDescription, tinted = tinted)
                is ImageVector -> OudsButtonIcon(imageVector = graphicsObject, contentDescription = contentDescription, tinted = tinted)
                is ImageBitmap -> OudsButtonIcon(bitmap = graphicsObject, contentDescription = contentDescription, tinted = tinted)
                else -> null
            }?.let { buttonIcon ->
                OudsButton(
                    nullableIcon = buttonIcon,
                    nullableLabel = null,
                    appearance = OudsButtonAppearance.Minimal,
                    onClick = onClick,
                    modifier = modifier,
                    enabled = enabled.orElse { true },
                    iconOnlyBadge = badge
                )
            }
        }.orElse {
            when (val graphicsObject = graphicsObject) {
                is Painter -> Icon(painter = graphicsObject, contentDescription = contentDescription, modifier = modifier, tint = iconTint)
                is ImageVector -> Icon(imageVector = graphicsObject, contentDescription = contentDescription, modifier = modifier, tint = iconTint)
                is ImageBitmap -> Icon(bitmap = graphicsObject, contentDescription = contentDescription, modifier = modifier, tint = iconTint)
            }
        }
    }
}

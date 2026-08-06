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
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * A data conveyor that holds icon configuration (graphics object, content description, and tinting preference)
 * that can be reused across multiple OUDS components.
 *
 * This class allows you to define an icon once and use it in different component contexts
 * (buttons, top app bars, chips, etc.) without recreating the icon configuration.
 *
 * Example usage:
 * ```
 * val favoriteIcon = OudsIconData(
 *     imageVector = Icons.Default.Favorite,
 *     contentDescription = "Favorite",
 *     tinted = true
 * )
 *
 * // Reuse in button
 * OudsButton(
 *     icon = OudsButtonIcon(favoriteIcon),
 *     onClick = { }
 * )
 *
 * // Reuse in top app bar
 * OudsTopAppBar(
 *     title = "My App",
 *     navigationIcon = OudsTopAppBarNavigationIcon(favoriteIcon, onClick = { })
 * )
 * ```
 *
 * @param contentDescription The content description for accessibility.
 * @param tinted Whether the icon should be tinted with the theme color. Defaults to `true`.
 */
class OudsIconData(
    val graphicsObject: Any,
    val contentDescription: String?,
    val tinted: Boolean
) {

    /**
     * Creates an icon data with a [Painter].
     *
     * @param painter The painter to use for the icon.
     * @param contentDescription The content description for accessibility.
     * @param tinted Whether the icon should be tinted with the theme color. Defaults to `true`.
     */
    @JvmOverloads
    constructor(
        painter: Painter,
        contentDescription: String?,
        tinted: Boolean = true
    ) : this(painter as Any, contentDescription, tinted)

    /**
     * Creates an icon data with an [ImageVector].
     *
     * @param imageVector The image vector to use for the icon.
     * @param contentDescription The content description for accessibility.
     * @param tinted Whether the icon should be tinted with the theme color. Defaults to `true`.
     */
    @JvmOverloads
    constructor(
        imageVector: ImageVector,
        contentDescription: String?,
        tinted: Boolean = true
    ) : this(imageVector as Any, contentDescription, tinted)

    /**
     * Creates an icon data with an [ImageBitmap].
     *
     * @param bitmap The image bitmap to use for the icon.
     * @param contentDescription The content description for accessibility.
     * @param tinted Whether the icon should be tinted with the theme color. Defaults to `true`.
     */
    @JvmOverloads
    constructor(
        bitmap: ImageBitmap,
        contentDescription: String?,
        tinted: Boolean = true
    ) : this(bitmap as Any, contentDescription, tinted)
}

@Composable
fun <T, S> OudsComponentIcon<T, S>.toIconData(): OudsIconData where T : OudsComponentContent.ExtraParameters, S : OudsComponentIcon<T, S> {
    @Suppress("UNCHECKED_CAST")
    return OudsIconData(
        graphicsObjectProvider(this as S),
        contentDescriptionProvider(this),
        tinted
    )
}

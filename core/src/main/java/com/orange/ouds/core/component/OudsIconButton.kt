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

package com.orange.ouds.core.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import com.orange.ouds.core.component.content.OudsComponentContent
import com.orange.ouds.core.component.content.OudsComponentIcon

class OudsIconButton internal constructor(
    graphicsObjectProvider: @Composable (OudsIconButton) -> Any,
    contentDescriptionProvider: @Composable (OudsIconButton) -> String?,
    override val tinted: Boolean,
    onClick: () -> Unit
) : OudsComponentIcon<OudsIconButton.ExtraParameters, OudsIconButton>(graphicsObjectProvider, contentDescriptionProvider, onClick) {

    open class ExtraParameters internal constructor(
        val tint: Color? = null,
        val enabled: Boolean = true
    ) : OudsComponentContent.ExtraParameters()

    init {
        // Set default extra parameters to avoid UninitializedPropertyAccessException
        extraParameters = ExtraParameters()
    }

    constructor(
        painter: Painter,
        onClick: () -> Unit,
        contentDescription: String? = null,
        tinted: Boolean = true
    ) : this({ painter }, { contentDescription }, tinted, onClick)

    constructor(
        imageVector: ImageVector,
        onClick: () -> Unit,
        contentDescription: String? = null,
        tinted: Boolean = true
    ) : this({ imageVector }, { contentDescription }, tinted, onClick)

    constructor(
        bitmap: ImageBitmap,
        onClick: () -> Unit,
        contentDescription: String? = null,
        tinted: Boolean = true
    ) : this({ bitmap }, { contentDescription }, tinted, onClick)

    override val enabled: Boolean?
        @Composable
        get() = extraParameters.enabled

    override val tint: Color?
        @Composable
        get() = extraParameters.tint
}

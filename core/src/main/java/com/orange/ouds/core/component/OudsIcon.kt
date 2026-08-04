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

open class OudsIcon internal constructor(
    graphicsObjectProvider: @Composable (OudsIcon) -> Any,
    contentDescriptionProvider: @Composable (OudsIcon) -> String,
    override val tinted: Boolean
) : OudsComponentIcon<OudsIcon.ExtraParameters, OudsIcon>(ExtraParameters::class.java, graphicsObjectProvider, contentDescriptionProvider), OudsTagAsset {

    object Default : OudsTagAsset

    open class ExtraParameters internal constructor(
        internal val tint: Color? = null,
        internal val enabled: Boolean = true
    ) : OudsComponentContent.ExtraParameters()

    override val enabled: Boolean?
        @Composable
        get() = extraParameters.enabled

    override val tint: Color?
        @Composable
        get() = extraParameters.tint

    constructor(
        painter: Painter,
        contentDescription: String,
        tinted: Boolean = true
    ) : this({ painter }, { contentDescription }, tinted)

    constructor(
        imageVector: ImageVector,
        contentDescription: String,
        tinted: Boolean = true
    ) : this({ imageVector }, { contentDescription }, tinted)

    constructor(
        bitmap: ImageBitmap,
        contentDescription: String,
        tinted: Boolean = true
    ) : this({ bitmap }, { contentDescription }, tinted)
}

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

package com.orange.ouds.theme.whitelabel

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.takeOrElse
import com.orange.ouds.theme.whitelabel.tokens.semantic.WhiteLabelBorderSemanticTokens
import com.orange.ouds.theme.wireframe.tokens.semantic.WireframeBorderSemanticTokens

/**
 * Customizable border properties for White Label theme.
 *
 * Allows customization of border radius. Unspecified properties fall back to WireframeTheme.
 *
 * ## Usage Example
 *
 * ```
 * val customBorders = WhiteLabelBorders(
 *     radius = WhiteLabelBorders.Radius(default = 12.0.dp)
 * )
 *
 * WhiteLabelTheme(borders = customBorders)
 * ```
 *
 * @param radius Border radius properties.
 */
class WhiteLabelBorders(
    val radius: Radius = Radius()
) {

    /**
     * Border radius properties.
     *
     * @param default The default border radius used by most components.
     */
    class Radius(
        val default: Dp = Dp.Unspecified
    )
}

internal val WhiteLabelBorders.borderSemanticTokens: WhiteLabelBorderSemanticTokens
    get() = with(WireframeBorderSemanticTokens()) {
        WhiteLabelBorderSemanticTokens(
            radiusDefault = radius.default.takeOrElse { radiusDefault.dp }.value,
            radiusLarge = radiusLarge,
            radiusMedium = radiusMedium,
            radiusNone = radiusNone,
            radiusPill = radiusPill,
            radiusSmall = radiusSmall,
            styleDefault = styleDefault,
            styleDrag = styleDrag,
            widthDefault = widthDefault,
            widthFocus = widthFocus,
            widthFocusInset = widthFocusInset,
            widthMedium = widthMedium,
            widthNone = widthNone,
            widthThick = widthThick,
            widthThicker = widthThicker,
            widthThin = widthThin
        )
    }

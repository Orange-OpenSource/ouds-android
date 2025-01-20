//
// Software Name: OUDS Android
// SPDX-FileCopyrightText: Copyright (c) Orange SA
// SPDX-License-Identifier: MIT
//
// This software is distributed under the MIT license,
// the text of which is available at https://opensource.org/license/MIT/
// or see the "LICENSE" file for more details.
//
// Software description: Android library of reusable graphical components
//

package com.orange.ouds.theme.whitelabel.tokens.semantic

import com.orange.ouds.theme.tokens.semantic.OudsBorderSemanticTokens
import com.orange.ouds.theme.whitelabel.tokens.raw.BorderRawTokens

data class WhiteLabelBorderSemanticTokens(
    override val radiusDefault: Float = BorderRawTokens.borderRadius0,
    override val radiusMedium: Float = BorderRawTokens.borderRadius150,
    override val radiusNone: Float = BorderRawTokens.borderRadius0,
    override val radiusPill: Float = BorderRawTokens.borderRadius9999,
    override val radiusShort: Float = BorderRawTokens.borderRadius75,
    override val radiusTall: Float = BorderRawTokens.borderRadius300,
    override val styleDefault: String = BorderRawTokens.borderStyleSolid,
    override val styleDrag: String = BorderRawTokens.borderStyleDashed,
    override val widthDefault: Float = BorderRawTokens.borderWidth25,
    override val widthFocus: Float = BorderRawTokens.borderWidth125,
    override val widthFocusInset: Float = BorderRawTokens.borderWidth50,
    override val widthMedium: Float = BorderRawTokens.borderWidth50,
    override val widthNone: Float = BorderRawTokens.borderWidth0,
    override val widthThick: Float = BorderRawTokens.borderWidth75,
    override val widthThicker: Float = BorderRawTokens.borderWidth100,
    override val widthThin: Float = BorderRawTokens.borderWidth25
) : OudsBorderSemanticTokens
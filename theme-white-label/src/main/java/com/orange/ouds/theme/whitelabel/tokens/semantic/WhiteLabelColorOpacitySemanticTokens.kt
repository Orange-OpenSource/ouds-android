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

import androidx.compose.ui.graphics.Color
import com.orange.ouds.theme.tokens.semantic.OudsColorOpacitySemanticTokens
import com.orange.ouds.tokens.global.raw.ColorRawTokens

data class WhiteLabelColorOpacitySemanticTokens(
    override val opacityLowerLight: Color = ColorRawTokens.colorOpacityBlack80,
    override val opacityLowestLight: Color = ColorRawTokens.colorOpacityBlack40,
    override val opacityTransparentLight: Color = ColorRawTokens.colorOpacityBlack0,
    override val opacityLowerDark: Color = ColorRawTokens.colorOpacityWhite80,
    override val opacityLowestDark: Color = ColorRawTokens.colorOpacityWhite40,
    override val opacityTransparentDark: Color = ColorRawTokens.colorOpacityWhite0
) : OudsColorOpacitySemanticTokens

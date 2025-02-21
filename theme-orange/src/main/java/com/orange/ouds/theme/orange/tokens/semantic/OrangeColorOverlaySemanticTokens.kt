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

// Tokens version 0.6.0
// Generated by Tokenator

package com.orange.ouds.theme.orange.tokens.semantic

import androidx.compose.ui.graphics.Color
import com.orange.ouds.theme.tokens.semantic.OudsColorOverlaySemanticTokens
import com.orange.ouds.tokens.global.raw.ColorRawTokens

data class OrangeColorOverlaySemanticTokens(
    override val overlayDefaultDark: Color = ColorRawTokens.colorOpacityWhite80,
    override val overlayDefaultLight: Color = ColorRawTokens.colorFunctionalWhite,
    override val overlayDragDark: Color = ColorRawTokens.colorOpacityWhite200,
    override val overlayDragLight: Color = ColorRawTokens.colorOpacityBlack40,
    override val overlayEmphasizedDark: Color = ColorRawTokens.colorFunctionalLightGray160,
    override val overlayEmphasizedLight: Color = ColorRawTokens.colorFunctionalDarkGray720,
    override val overlayModalDark: Color = ColorRawTokens.colorFunctionalDarkGray640,
    override val overlayModalLight: Color = ColorRawTokens.colorFunctionalWhite
) : OudsColorOverlaySemanticTokens

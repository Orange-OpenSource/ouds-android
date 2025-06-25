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
import com.orange.ouds.theme.tokens.semantic.OudsColorAlwaysSemanticTokens
import com.orange.ouds.tokens.raw.ColorRawTokens

data class WhiteLabelColorAlwaysSemanticTokens(
    override val alwaysBlack: Color = ColorRawTokens.colorFunctionalBlack,
    override val alwaysOnBlack: Color = ColorRawTokens.colorFunctionalWhite,
    override val alwaysOnWhite: Color = ColorRawTokens.colorFunctionalBlack,
    override val alwaysWhite: Color = ColorRawTokens.colorFunctionalWhite,
) : OudsColorAlwaysSemanticTokens

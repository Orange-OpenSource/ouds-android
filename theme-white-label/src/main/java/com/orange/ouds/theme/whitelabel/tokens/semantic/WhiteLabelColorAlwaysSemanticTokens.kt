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
import com.orange.ouds.theme.wireframe.tokens.semantic.WireframeColorAlwaysSemanticTokens

/**
 * White Label always color semantic tokens.
 *
 * Inherits from Wireframe always color tokens with customizable overrides.
 * Parameters left as [Color.Unspecified] will use the default Wireframe values.
 *
 * ```
 * WhiteLabelColorAlwaysSemanticTokens(
 *     alwaysBlack = Color.Black,
 *     alwaysWhite = Color.White
 * )
 * ```
 */
class WhiteLabelColorAlwaysSemanticTokens(
    alwaysBlack: Color = Color.Unspecified,
    alwaysOnBlack: Color = Color.Unspecified,
    alwaysOnWhite: Color = Color.Unspecified,
    alwaysWhite: Color = Color.Unspecified
) : OudsColorAlwaysSemanticTokens by WireframeColorAlwaysSemanticTokens().copy(
    alwaysBlack = alwaysBlack,
    alwaysOnBlack = alwaysOnBlack,
    alwaysOnWhite = alwaysOnWhite,
    alwaysWhite = alwaysWhite
)

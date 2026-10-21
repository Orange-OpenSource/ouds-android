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
import com.orange.ouds.theme.wireframe.tokens.semantic.WireframeColorOpacitySemanticTokens

/**
 * White Label opacity color semantic tokens.
 *
 * Inherits from Wireframe opacity color tokens with customizable overrides.
 * Parameters left as [Color.Unspecified] will use the default Wireframe values.
 *
 * ```
 * WhiteLabelColorOpacitySemanticTokens(
 *     opacityLowerLight = Color(0x1A000000),
 *     opacityLowerDark = Color(0x1AFFFFFF)
 * )
 * ```
 */
class WhiteLabelColorOpacitySemanticTokens(
    opacityLowerDark: Color = Color.Unspecified,
    opacityLowerLight: Color = Color.Unspecified,
    opacityLowestDark: Color = Color.Unspecified,
    opacityLowestLight: Color = Color.Unspecified,
    opacityTransparentDark: Color = Color.Unspecified,
    opacityTransparentInverseDark: Color = Color.Unspecified,
    opacityTransparentInverseLight: Color = Color.Unspecified,
    opacityTransparentLight: Color = Color.Unspecified
) : OudsColorOpacitySemanticTokens by WireframeColorOpacitySemanticTokens().copy(
    opacityLowerDark = opacityLowerDark,
    opacityLowerLight = opacityLowerLight,
    opacityLowestDark = opacityLowestDark,
    opacityLowestLight = opacityLowestLight,
    opacityTransparentDark = opacityTransparentDark,
    opacityTransparentInverseDark = opacityTransparentInverseDark,
    opacityTransparentInverseLight = opacityTransparentInverseLight,
    opacityTransparentLight = opacityTransparentLight
)

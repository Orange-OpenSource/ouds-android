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
import com.orange.ouds.theme.tokens.semantic.OudsColorBgSemanticTokens
import com.orange.ouds.theme.wireframe.tokens.semantic.WireframeColorBgSemanticTokens

class WhiteLabelColorBgSemanticTokens(
    bgInverseHighDark: Color = Color.Unspecified,
    bgInverseHighLight: Color = Color.Unspecified,
    bgInverseLowDark: Color = Color.Unspecified,
    bgInverseLowLight: Color = Color.Unspecified,
    bgPrimaryDark: Color = Color.Unspecified,
    bgPrimaryLight: Color = Color.Unspecified,
    bgSecondaryDark: Color = Color.Unspecified,
    bgSecondaryLight: Color = Color.Unspecified,
    bgTertiaryDark: Color = Color.Unspecified,
    bgTertiaryLight: Color = Color.Unspecified
) : OudsColorBgSemanticTokens by WireframeColorBgSemanticTokens().copy(
    bgInverseHighDark = bgInverseHighDark,
    bgInverseHighLight = bgInverseHighLight,
    bgInverseLowDark = bgInverseLowDark,
    bgInverseLowLight = bgInverseLowLight,
    bgPrimaryDark = bgPrimaryDark,
    bgPrimaryLight = bgPrimaryLight,
    bgSecondaryDark = bgSecondaryDark,
    bgSecondaryLight = bgSecondaryLight,
    bgTertiaryDark = bgTertiaryDark,
    bgTertiaryLight = bgTertiaryLight
)

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
import com.orange.ouds.theme.tokens.semantic.OudsColorAiSemanticTokens
import com.orange.ouds.theme.wireframe.tokens.semantic.WireframeColorAiSemanticTokens

/**
 * White Label AI color semantic tokens.
 *
 * Inherits from Wireframe AI color tokens with customizable overrides.
 * Parameters left as [Color.Unspecified] will use the default Wireframe values.
 *
 * ```
 * WhiteLabelColorAiSemanticTokens(
 *     aiPrimaryLight = Color(0xFF6200EE),
 *     aiPrimaryDark = Color(0xFFBB86FC)
 * )
 * ```
 */
class WhiteLabelColorAiSemanticTokens(
    aiPrimaryDark: Color = Color.Unspecified,
    aiPrimaryLight: Color = Color.Unspecified,
    aiQuaternaryDark: Color = Color.Unspecified,
    aiQuaternaryLight: Color = Color.Unspecified,
    aiSecondaryDark: Color = Color.Unspecified,
    aiSecondaryLight: Color = Color.Unspecified,
    aiTertiaryDark: Color = Color.Unspecified,
    aiTertiaryLight: Color = Color.Unspecified
) : OudsColorAiSemanticTokens by WireframeColorAiSemanticTokens().copy(
    aiPrimaryDark = aiPrimaryDark,
    aiPrimaryLight = aiPrimaryLight,
    aiQuaternaryDark = aiQuaternaryDark,
    aiQuaternaryLight = aiQuaternaryLight,
    aiSecondaryDark = aiSecondaryDark,
    aiSecondaryLight = aiSecondaryLight,
    aiTertiaryDark = aiTertiaryDark,
    aiTertiaryLight = aiTertiaryLight
)

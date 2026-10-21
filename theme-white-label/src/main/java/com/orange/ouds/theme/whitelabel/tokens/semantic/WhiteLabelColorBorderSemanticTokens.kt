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
import com.orange.ouds.theme.tokens.semantic.OudsColorBorderSemanticTokens
import com.orange.ouds.theme.wireframe.tokens.semantic.WireframeColorBorderSemanticTokens

/**
 * White Label border color semantic tokens.
 *
 * Inherits from Wireframe border color tokens with customizable overrides.
 * Parameters left as [Color.Unspecified] will use the default Wireframe values.
 *
 * ```
 * WhiteLabelColorBorderSemanticTokens(
 *     borderDefaultLight = Color(0xFFE0E0E0),
 *     borderDefaultDark = Color(0xFF424242)
 * )
 * ```
 */
class WhiteLabelColorBorderSemanticTokens(
    borderBrandPrimaryDark: Color = Color.Unspecified,
    borderBrandPrimaryLight: Color = Color.Unspecified,
    borderBrandSecondaryDark: Color = Color.Unspecified,
    borderBrandSecondaryLight: Color = Color.Unspecified,
    borderBrandTertiaryDark: Color = Color.Unspecified,
    borderBrandTertiaryLight: Color = Color.Unspecified,
    borderDefaultDark: Color = Color.Unspecified,
    borderDefaultLight: Color = Color.Unspecified,
    borderEmphasizedDark: Color = Color.Unspecified,
    borderEmphasizedLight: Color = Color.Unspecified,
    borderFocusDark: Color = Color.Unspecified,
    borderFocusInsetDark: Color = Color.Unspecified,
    borderFocusInsetLight: Color = Color.Unspecified,
    borderFocusLight: Color = Color.Unspecified,
    borderMinimalDark: Color = Color.Unspecified,
    borderMinimalLight: Color = Color.Unspecified,
    borderMutedDark: Color = Color.Unspecified,
    borderMutedLight: Color = Color.Unspecified,
    borderOnBrandPrimaryDark: Color = Color.Unspecified,
    borderOnBrandPrimaryLight: Color = Color.Unspecified,
    borderOnBrandSecondaryDark: Color = Color.Unspecified,
    borderOnBrandSecondaryLight: Color = Color.Unspecified,
    borderOnBrandTertiaryDark: Color = Color.Unspecified,
    borderOnBrandTertiaryLight: Color = Color.Unspecified,
    borderStatusAccentDark: Color = Color.Unspecified,
    borderStatusAccentLight: Color = Color.Unspecified,
    borderStatusInfoDark: Color = Color.Unspecified,
    borderStatusInfoLight: Color = Color.Unspecified,
    borderStatusNegativeDark: Color = Color.Unspecified,
    borderStatusNegativeLight: Color = Color.Unspecified,
    borderStatusPositiveDark: Color = Color.Unspecified,
    borderStatusPositiveLight: Color = Color.Unspecified,
    borderStatusWarningDark: Color = Color.Unspecified,
    borderStatusWarningLight: Color = Color.Unspecified
) : OudsColorBorderSemanticTokens by WireframeColorBorderSemanticTokens().copy(
    borderBrandPrimaryDark = borderBrandPrimaryDark,
    borderBrandPrimaryLight = borderBrandPrimaryLight,
    borderBrandSecondaryDark = borderBrandSecondaryDark,
    borderBrandSecondaryLight = borderBrandSecondaryLight,
    borderBrandTertiaryDark = borderBrandTertiaryDark,
    borderBrandTertiaryLight = borderBrandTertiaryLight,
    borderDefaultDark = borderDefaultDark,
    borderDefaultLight = borderDefaultLight,
    borderEmphasizedDark = borderEmphasizedDark,
    borderEmphasizedLight = borderEmphasizedLight,
    borderFocusDark = borderFocusDark,
    borderFocusInsetDark = borderFocusInsetDark,
    borderFocusInsetLight = borderFocusInsetLight,
    borderFocusLight = borderFocusLight,
    borderMinimalDark = borderMinimalDark,
    borderMinimalLight = borderMinimalLight,
    borderMutedDark = borderMutedDark,
    borderMutedLight = borderMutedLight,
    borderOnBrandPrimaryDark = borderOnBrandPrimaryDark,
    borderOnBrandPrimaryLight = borderOnBrandPrimaryLight,
    borderOnBrandSecondaryDark = borderOnBrandSecondaryDark,
    borderOnBrandSecondaryLight = borderOnBrandSecondaryLight,
    borderOnBrandTertiaryDark = borderOnBrandTertiaryDark,
    borderOnBrandTertiaryLight = borderOnBrandTertiaryLight,
    borderStatusAccentDark = borderStatusAccentDark,
    borderStatusAccentLight = borderStatusAccentLight,
    borderStatusInfoDark = borderStatusInfoDark,
    borderStatusInfoLight = borderStatusInfoLight,
    borderStatusNegativeDark = borderStatusNegativeDark,
    borderStatusNegativeLight = borderStatusNegativeLight,
    borderStatusPositiveDark = borderStatusPositiveDark,
    borderStatusPositiveLight = borderStatusPositiveLight,
    borderStatusWarningDark = borderStatusWarningDark,
    borderStatusWarningLight = borderStatusWarningLight
)

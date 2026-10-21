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
import com.orange.ouds.theme.tokens.semantic.OudsColorOverlaySemanticTokens
import com.orange.ouds.theme.wireframe.tokens.semantic.WireframeColorOverlaySemanticTokens

/**
 * White Label overlay color semantic tokens.
 *
 * Inherits from Wireframe overlay color tokens with customizable overrides.
 * Parameters left as [Color.Unspecified] will use the default Wireframe values.
 *
 * ```
 * WhiteLabelColorOverlaySemanticTokens(
 *     overlayBackdropLight = Color(0x80000000),
 *     overlayBackdropDark = Color(0x80000000)
 * )
 * ```
 */
class WhiteLabelColorOverlaySemanticTokens(
    overlayBackdropDark: Color = Color.Unspecified,
    overlayBackdropLight: Color = Color.Unspecified,
    overlayDragDark: Color = Color.Unspecified,
    overlayDragLight: Color = Color.Unspecified,
    overlayDropdownDark: Color = Color.Unspecified,
    overlayDropdownLight: Color = Color.Unspecified,
    overlayFloatingDark: Color = Color.Unspecified,
    overlayFloatingLight: Color = Color.Unspecified,
    overlayModalSheetDark: Color = Color.Unspecified,
    overlayModalSheetLight: Color = Color.Unspecified,
    overlayTooltipDark: Color = Color.Unspecified,
    overlayTooltipLight: Color = Color.Unspecified
) : OudsColorOverlaySemanticTokens by WireframeColorOverlaySemanticTokens().copy(
    overlayBackdropDark = overlayBackdropDark,
    overlayBackdropLight = overlayBackdropLight,
    overlayDragDark = overlayDragDark,
    overlayDragLight = overlayDragLight,
    overlayDropdownDark = overlayDropdownDark,
    overlayDropdownLight = overlayDropdownLight,
    overlayFloatingDark = overlayFloatingDark,
    overlayFloatingLight = overlayFloatingLight,
    overlayModalSheetDark = overlayModalSheetDark,
    overlayModalSheetLight = overlayModalSheetLight,
    overlayTooltipDark = overlayTooltipDark,
    overlayTooltipLight = overlayTooltipLight
)

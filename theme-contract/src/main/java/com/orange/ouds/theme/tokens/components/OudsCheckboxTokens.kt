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

// Tokens version 0.6.2
// Generated by Tokenator

package com.orange.ouds.theme.tokens.components

import com.orange.ouds.theme.tokens.OudsBorderKeyToken
import com.orange.ouds.theme.tokens.OudsColorKeyToken
import com.orange.ouds.theme.tokens.OudsOpacityKeyToken
import com.orange.ouds.theme.tokens.OudsSizeKeyToken
import com.orange.ouds.tokens.global.raw.DimensionRawTokens

open class OudsCheckboxTokens(
    val borderRadius: OudsBorderKeyToken.Radius = OudsBorderKeyToken.Radius.Default,
    val borderWidthSelected: OudsBorderKeyToken.Width = OudsBorderKeyToken.Width.Medium,
    val borderWidthSelectedFocus: OudsBorderKeyToken.Width = OudsBorderKeyToken.Width.Medium,
    val borderWidthSelectedHover: OudsBorderKeyToken.Width = OudsBorderKeyToken.Width.Medium,
    val borderWidthSelectedPressed: OudsBorderKeyToken.Width = OudsBorderKeyToken.Width.Medium,
    val borderWidthUnselected: OudsBorderKeyToken.Width = OudsBorderKeyToken.Width.Thin,
    val borderWidthUnselectedFocus: OudsBorderKeyToken.Width = OudsBorderKeyToken.Width.Medium,
    val borderWidthUnselectedHover: OudsBorderKeyToken.Width = OudsBorderKeyToken.Width.Medium,
    val borderWidthUnselectedPressed: OudsBorderKeyToken.Width = OudsBorderKeyToken.Width.Medium,
    val colorContentDisabled: OudsColorKeyToken = OudsColorKeyToken.Action.Disabled,
    val colorContentEnabled: OudsColorKeyToken = OudsColorKeyToken.Action.Enabled,
    val colorContentErrorEnabled: OudsColorKeyToken = OudsColorKeyToken.Action.Negative.Enabled,
    val colorContentErrorFocus: OudsColorKeyToken = OudsColorKeyToken.Action.Negative.Focus,
    val colorContentErrorHover: OudsColorKeyToken = OudsColorKeyToken.Action.Negative.Hover,
    val colorContentErrorPressed: OudsColorKeyToken = OudsColorKeyToken.Action.Negative.Pressed,
    val colorContentFocus: OudsColorKeyToken = OudsColorKeyToken.Action.Focus,
    val colorContentHover: OudsColorKeyToken = OudsColorKeyToken.Action.Hover,
    val colorContentPressed: OudsColorKeyToken = OudsColorKeyToken.Action.Pressed,
    val colorContentSelected: OudsColorKeyToken = OudsColorKeyToken.Action.Selected,
    val opacityBgSelected: OudsOpacityKeyToken = OudsOpacityKeyToken.Invisible,
    val opacityBgSelectedFocus: OudsOpacityKeyToken = OudsOpacityKeyToken.Invisible,
    val opacityBgSelectedHover: OudsOpacityKeyToken = OudsOpacityKeyToken.Invisible,
    val opacityBgSelectedPressed: OudsOpacityKeyToken = OudsOpacityKeyToken.Invisible,
    val opacityBgUnselected: OudsOpacityKeyToken = OudsOpacityKeyToken.Invisible,
    val opacityBgUnselectedFocus: OudsOpacityKeyToken = OudsOpacityKeyToken.Invisible,
    val opacityBgUnselectedHover: OudsOpacityKeyToken = OudsOpacityKeyToken.Invisible,
    val opacityBgUnselectedPressed: OudsOpacityKeyToken = OudsOpacityKeyToken.Invisible,
    val sizeIndicator: OudsSizeKeyToken.Icon = OudsSizeKeyToken.Icon.WithLabel.Large.SizeExtraSmall,
    val sizeIndicatorInnerIcon: Float = DimensionRawTokens.dimension200,
    val sizeMaxHeight: Float = DimensionRawTokens.dimension600,
    val sizeMinHeight: Float = DimensionRawTokens.dimension600,
    val sizeMinWidth: Float = DimensionRawTokens.dimension600
)

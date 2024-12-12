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

package com.orange.ouds.theme.tokens.components

import com.orange.ouds.theme.tokens.OudsBorderKeyToken
import com.orange.ouds.theme.tokens.OudsColorKeyToken
import com.orange.ouds.theme.tokens.OudsSizeKeyToken
import com.orange.ouds.theme.tokens.OudsSpaceKeyToken
import com.orange.ouds.tokens.global.raw.DimensionRawTokens

open class OudsRadioButtonTokens(
    val borderRadius: OudsBorderKeyToken.Radius = OudsBorderKeyToken.Radius.None,
    val borderWidth: OudsBorderKeyToken.Width = OudsBorderKeyToken.Width.None,
    val borderWidthHighlight: OudsBorderKeyToken.Width = OudsBorderKeyToken.Width.Default,
    val colorBgDisabled: OudsColorKeyToken = OudsColorKeyToken.Opacity.Invisible.Black,
    val colorBgEnabled: OudsColorKeyToken = OudsColorKeyToken.Opacity.Invisible.Black,
    val colorBgFocus: OudsColorKeyToken = OudsColorKeyToken.Action.Support.Focus,
    val colorBgHover: OudsColorKeyToken = OudsColorKeyToken.Action.Support.Hover,
    val colorBgLoading: OudsColorKeyToken = OudsColorKeyToken.Action.Support.Loading,
    val colorBgPressed: OudsColorKeyToken = OudsColorKeyToken.Action.Support.Pressed,
    val colorBorderDisabled: OudsColorKeyToken = OudsColorKeyToken.Opacity.Invisible.Black,
    val colorBorderEnabled: OudsColorKeyToken = OudsColorKeyToken.Opacity.Invisible.Black,
    val colorBorderHover: OudsColorKeyToken = OudsColorKeyToken.Opacity.Invisible.Black,
    val colorBorderLoading: OudsColorKeyToken = OudsColorKeyToken.Opacity.Invisible.Black,
    val colorBorderPressed: OudsColorKeyToken = OudsColorKeyToken.Opacity.Invisible.Black,
    val colorContentContentDisabled: OudsColorKeyToken = OudsColorKeyToken.Content.Disabled,
    val colorContentContentEnabled: OudsColorKeyToken = OudsColorKeyToken.Content.Default,
    val colorContentContentFocus: OudsColorKeyToken = OudsColorKeyToken.Content.Default,
    val colorContentContentHover: OudsColorKeyToken = OudsColorKeyToken.Content.Default,
    val colorContentContentLoading: OudsColorKeyToken = OudsColorKeyToken.Content.Default,
    val colorContentContentPressed: OudsColorKeyToken = OudsColorKeyToken.Content.Default,
    val colorContentHelperTextDisabled: OudsColorKeyToken = OudsColorKeyToken.Content.Disabled,
    val colorContentHelperTextEnabled: OudsColorKeyToken = OudsColorKeyToken.Content.Muted,
    val colorContentHelperTextFocus: OudsColorKeyToken = OudsColorKeyToken.Content.Muted,
    val colorContentHelperTextHover: OudsColorKeyToken = OudsColorKeyToken.Content.Muted,
    val colorContentHelperTextLoading: OudsColorKeyToken = OudsColorKeyToken.Content.Muted,
    val colorContentHelperTextPressed: OudsColorKeyToken = OudsColorKeyToken.Content.Muted,
    val colorContentPriceDisabled: OudsColorKeyToken = OudsColorKeyToken.Content.Disabled,
    val colorContentPriceEnabled: OudsColorKeyToken = OudsColorKeyToken.Content.Default,
    val colorContentPriceFocus: OudsColorKeyToken = OudsColorKeyToken.Content.Default,
    val colorContentPriceHover: OudsColorKeyToken = OudsColorKeyToken.Content.Default,
    val colorContentPriceLoading: OudsColorKeyToken = OudsColorKeyToken.Content.Default,
    val colorContentPricePressed: OudsColorKeyToken = OudsColorKeyToken.Content.Default,
    val sizeCheckBox: OudsSizeKeyToken.Icon = OudsSizeKeyToken.Icon.WithLabel.Large.SizeSmall,
    val sizeIcon: OudsSizeKeyToken.Icon = OudsSizeKeyToken.Icon.WithLabel.Large.SizeExtraSmall,
    val sizeLoader: OudsSizeKeyToken.Icon = OudsSizeKeyToken.Icon.WithLabel.Large.SizeExtraSmall,
    val sizeMaxHeightContainerIcon: Float = DimensionRawTokens.dimension600,
    val sizeMinHeight: Float = DimensionRawTokens.dimension600,
    val sizeMinHeightIconOnly: Float = DimensionRawTokens.dimension600,
    val sizeMinWidth: Float = DimensionRawTokens.dimension600,
    val sizeMinWidthIconOnly: Float = DimensionRawTokens.dimension600,
    val spaceColumnGap: OudsSpaceKeyToken.ColumnGap = OudsSpaceKeyToken.ColumnGap.Tall,
    val spacePaddingInset: OudsSpaceKeyToken.Inset = OudsSpaceKeyToken.Inset.Medium,
    val spaceRowGap: OudsSpaceKeyToken.RowGap = OudsSpaceKeyToken.RowGap.None
)

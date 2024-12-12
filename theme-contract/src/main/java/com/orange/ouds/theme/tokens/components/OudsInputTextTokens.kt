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

open class OudsInputTextTokens(
    val borderRadius: OudsBorderKeyToken.Radius = OudsBorderKeyToken.Radius.None,
    val borderWidthDefault: OudsBorderKeyToken.Width = OudsBorderKeyToken.Width.Default,
    val borderWidthDefaultInteraction: OudsBorderKeyToken.Width = OudsBorderKeyToken.Width.Medium,
    val colorBgDefaultDisabled: OudsColorKeyToken = OudsColorKeyToken.Action.Support.Enabled,
    val colorBgDefaultEnabled: OudsColorKeyToken = OudsColorKeyToken.Action.Support.Enabled,
    val colorBgDefaultFocus: OudsColorKeyToken = OudsColorKeyToken.Action.Support.Focus,
    val colorBgDefaultHover: OudsColorKeyToken = OudsColorKeyToken.Action.Support.Hover,
    val colorBgDefaultLoading: OudsColorKeyToken = OudsColorKeyToken.Action.Support.Loading,
    val colorBgDefaultPressed: OudsColorKeyToken = OudsColorKeyToken.Action.Support.Pressed,
    val colorBorderDefaultDisabled: OudsColorKeyToken = OudsColorKeyToken.Action.Disabled,
    val colorBorderDefaultLoading: OudsColorKeyToken = OudsColorKeyToken.Action.Loading,
    val colorBorderDefaultPressed: OudsColorKeyToken = OudsColorKeyToken.Action.Pressed,
    val colorBorderDefaultEnabled: OudsColorKeyToken = OudsColorKeyToken.Border.DefaultInverse,
    val colorBorderDefaultHover: OudsColorKeyToken = OudsColorKeyToken.Action.Enabled,
    val colorContentDefaultDisabled: OudsColorKeyToken = OudsColorKeyToken.Content.Disabled,
    val colorContentDefaultEnabled: OudsColorKeyToken = OudsColorKeyToken.Content.Default,
    val colorContentDefaultFocus: OudsColorKeyToken = OudsColorKeyToken.Content.Default,
    val colorContentDefaultHover: OudsColorKeyToken = OudsColorKeyToken.Content.Default,
    val colorContentDefaultHover2: OudsColorKeyToken = OudsColorKeyToken.Content.Default,
    val colorContentDefaultLoading: OudsColorKeyToken = OudsColorKeyToken.Content.Default,
    val colorContentDefaultPressed: OudsColorKeyToken = OudsColorKeyToken.Content.Default,
    val sizeIcon: OudsSizeKeyToken.Icon = OudsSizeKeyToken.Icon.WithLabel.Large.SizeExtraSmall,
    val sizeLoader: OudsSizeKeyToken.Icon = OudsSizeKeyToken.Icon.WithLabel.Large.SizeExtraSmall,
    val sizeMaxHeight: Float = DimensionRawTokens.dimension600,
    val sizeMinHeight: Float = DimensionRawTokens.dimension600,
    val sizeMinWidth: Float = DimensionRawTokens.dimension600,
    val sizePrefixTextHeight: OudsSizeKeyToken.Icon = OudsSizeKeyToken.Icon.WithLabel.Large.SizeSmall,
    val sizeSuffixTextHeight: OudsSizeKeyToken.Icon = OudsSizeKeyToken.Icon.WithLabel.Large.SizeSmall,
    val spaceColumnGapArrow: OudsSpaceKeyToken.ColumnGap = OudsSpaceKeyToken.ColumnGap.Shorter,
    val spaceColumnGapIcon: OudsSpaceKeyToken.ColumnGap = OudsSpaceKeyToken.ColumnGap.Short,
    val spaceInsetIconAlone: OudsSpaceKeyToken.Inset = OudsSpaceKeyToken.Inset.Medium,
    val spacePaddingBlock: OudsSpaceKeyToken.PaddingBlock = OudsSpaceKeyToken.PaddingBlock.Medium,
    val spacePaddingInlineArrowEnd: OudsSpaceKeyToken.PaddingInline = OudsSpaceKeyToken.PaddingInline.Tall,
    val spacePaddingInlineArrowStart: OudsSpaceKeyToken.PaddingInline = OudsSpaceKeyToken.PaddingInline.Tall,
    val spacePaddingInlineEndIconStart: OudsSpaceKeyToken.PaddingInline = OudsSpaceKeyToken.PaddingInline.Spacious,
    val spacePaddingInlineIconNone: OudsSpaceKeyToken.PaddingInline = OudsSpaceKeyToken.PaddingInline.Huge,
    val spacePaddingInlineIconStart: OudsSpaceKeyToken.PaddingInline = OudsSpaceKeyToken.PaddingInline.Taller,
    val spacePaddingInlineStartIconEnd: OudsSpaceKeyToken.PaddingInline = OudsSpaceKeyToken.PaddingInline.Spacious
)

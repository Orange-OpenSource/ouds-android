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

import com.orange.ouds.theme.tokens.OudsColorKeyToken
import com.orange.ouds.theme.tokens.OudsFontKeyToken
import com.orange.ouds.theme.tokens.OudsSizeKeyToken
import com.orange.ouds.theme.tokens.OudsSpaceKeyToken
import com.orange.ouds.tokens.global.raw.DimensionRawTokens

open class OudsInputTextTokens(
    val colorBgDefaultDisabled: OudsColorKeyToken = OudsColorKeyToken.Action.Support.Enabled,
    val colorBgDefaultEnabled: OudsColorKeyToken = OudsColorKeyToken.Action.Support.Enabled,
    val colorBgDefaultFocus: OudsColorKeyToken = OudsColorKeyToken.Action.Support.Focus,
    val colorBgDefaultHover: OudsColorKeyToken = OudsColorKeyToken.Action.Support.Hover,
    val colorBgDefaultLoading: OudsColorKeyToken = OudsColorKeyToken.Action.Support.Loading,
    val colorBgDefaultPressed: OudsColorKeyToken = OudsColorKeyToken.Action.Support.Pressed,
    val colorContentDefaultDisabled: OudsColorKeyToken = OudsColorKeyToken.Content.Disabled,
    val colorContentDefaultEnabled: OudsColorKeyToken = OudsColorKeyToken.Content.Default,
    val colorContentDefaultFocus: OudsColorKeyToken = OudsColorKeyToken.Content.Default,
    val colorContentDefaultHover: OudsColorKeyToken = OudsColorKeyToken.Content.Default,
    val colorContentDefaultLoading: OudsColorKeyToken = OudsColorKeyToken.Content.Default,
    val colorContentDefaultPressed: OudsColorKeyToken = OudsColorKeyToken.Content.Default,
    val sizeIconHeight: OudsSizeKeyToken.Icon = OudsSizeKeyToken.Icon.Decorative.Large,
    val sizeMaxHeight: Float = DimensionRawTokens.dimension600,
    val sizeMinHeight: Float = DimensionRawTokens.dimension600,
    val sizeMinWidth: Float = DimensionRawTokens.dimension600,
    val sizePrefixTextHeight: OudsSizeKeyToken.Icon = OudsSizeKeyToken.Icon.WithLabel.Large.SizeSmall,
    val sizeSuffixTextHeight: OudsFontKeyToken.LineHeight.Heading.ExtraLarge = OudsFontKeyToken.LineHeight.Heading.ExtraLarge.Desktop,
    val sizeTextHeight: Float = DimensionRawTokens.dimension600,
    val spaceInsetIconAlone: OudsSpaceKeyToken.Inset = OudsSpaceKeyToken.Inset.Medium,
    val spacePaddingBlock: OudsSpaceKeyToken.PaddingBlock = OudsSpaceKeyToken.PaddingBlock.Medium,
    val spacePaddingInlineContainerEnd: OudsSpaceKeyToken.PaddingInline = OudsSpaceKeyToken.PaddingInline.Shorter,
    val spacePaddingInlineContainerStart: OudsSpaceKeyToken.PaddingInline = OudsSpaceKeyToken.PaddingInline.Medium,
    val spacePaddingInlineIconEnd: OudsSpaceKeyToken.PaddingInline = OudsSpaceKeyToken.PaddingInline.Shorter,
    val spacePaddingInlinePrefixTextEnd: OudsSpaceKeyToken.PaddingInline = OudsSpaceKeyToken.PaddingInline.Short,
    val spacePaddingInlineSuffixTextEnd: OudsSpaceKeyToken.PaddingInline = OudsSpaceKeyToken.PaddingInline.Shortest,
    val spacePaddingInlineTextEnd: OudsSpaceKeyToken.PaddingInline = OudsSpaceKeyToken.PaddingInline.Short
)

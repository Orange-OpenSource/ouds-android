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

// Tokens version 0.6.1
// Generated by Tokenator

package com.orange.ouds.theme.tokens.components

import com.orange.ouds.theme.tokens.OudsColorKeyToken
import com.orange.ouds.theme.tokens.OudsSizeKeyToken
import com.orange.ouds.theme.tokens.OudsSpaceKeyToken
import com.orange.ouds.tokens.global.raw.DimensionRawTokens

open class OudsLinkTokens(
    val colorArrowEnabled: OudsColorKeyToken = OudsColorKeyToken.Content.BrandPrimary,
    val colorArrowFocus: OudsColorKeyToken = OudsColorKeyToken.Content.BrandPrimary,
    val colorArrowHover: OudsColorKeyToken = OudsColorKeyToken.Content.BrandPrimary,
    val colorArrowPressed: OudsColorKeyToken = OudsColorKeyToken.Content.BrandPrimary,
    val colorContentDisabledMono: OudsColorKeyToken = OudsColorKeyToken.Repository.Opacity.Black.Soft,
    val colorContentEnabled: OudsColorKeyToken = OudsColorKeyToken.Action.Enabled,
    val colorContentEnabledMono: OudsColorKeyToken = OudsColorKeyToken.Repository.Neutral.Emphasized.Black,
    val colorContentFocus: OudsColorKeyToken = OudsColorKeyToken.Action.Focus,
    val colorContentFocusMono: OudsColorKeyToken = OudsColorKeyToken.Repository.Neutral.Emphasized.Black,
    val colorContentHover: OudsColorKeyToken = OudsColorKeyToken.Action.Hover,
    val colorContentHoverMono: OudsColorKeyToken = OudsColorKeyToken.Repository.Neutral.Emphasized.Black,
    val colorContentPressed: OudsColorKeyToken = OudsColorKeyToken.Action.Pressed,
    val colorContentPressedMono: OudsColorKeyToken = OudsColorKeyToken.Repository.Opacity.Black.Higher,
    val sizeIconMedium: OudsSizeKeyToken.Icon = OudsSizeKeyToken.Icon.WithLabel.Large.SizeExtraSmall,
    val sizeIconSmall: OudsSizeKeyToken.Icon = OudsSizeKeyToken.Icon.WithLabel.Medium.SizeSmall,
    val sizeMinHeightMedium: Float = DimensionRawTokens.dimension600,
    val sizeMinHeightSmall: Float = DimensionRawTokens.dimension550,
    val sizeMinWidthMedium: Float = DimensionRawTokens.dimension600,
    val sizeMinWidthSmall: Float = DimensionRawTokens.dimension550,
    val spaceColumnGapArrowMedium: OudsSpaceKeyToken.ColumnGap = OudsSpaceKeyToken.ColumnGap.Shorter,
    val spaceColumnGapArrowSmall: OudsSpaceKeyToken.ColumnGap = OudsSpaceKeyToken.ColumnGap.Shortest,
    val spaceColumnGapIconMedium: OudsSpaceKeyToken.ColumnGap = OudsSpaceKeyToken.ColumnGap.Short,
    val spaceColumnGapIconSmall: OudsSpaceKeyToken.ColumnGap = OudsSpaceKeyToken.ColumnGap.Shorter,
    val spacePaddingBlock: OudsSpaceKeyToken.PaddingBlock = OudsSpaceKeyToken.PaddingBlock.Medium,
    val spacePaddingInline: OudsSpaceKeyToken.PaddingInline = OudsSpaceKeyToken.PaddingInline.None
)

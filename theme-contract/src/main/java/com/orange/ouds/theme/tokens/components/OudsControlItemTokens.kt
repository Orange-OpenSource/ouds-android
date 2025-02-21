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

// Tokens version 0.6.0
// Generated by Tokenator

package com.orange.ouds.theme.tokens.components

import com.orange.ouds.theme.tokens.OudsColorKeyToken
import com.orange.ouds.theme.tokens.OudsSizeKeyToken
import com.orange.ouds.theme.tokens.OudsSpaceKeyToken
import com.orange.ouds.tokens.global.raw.DimensionRawTokens

open class OudsControlItemTokens(
    val colorBgFocus: OudsColorKeyToken = OudsColorKeyToken.Action.Support.Focus,
    val colorBgHover: OudsColorKeyToken = OudsColorKeyToken.Action.Support.Hover,
    val colorBgLoading: OudsColorKeyToken = OudsColorKeyToken.Action.Support.Loading,
    val colorBgPressed: OudsColorKeyToken = OudsColorKeyToken.Action.Support.Pressed,
    val colorContentLoader: OudsColorKeyToken = OudsColorKeyToken.Content.Default,
    val sizeIcon: OudsSizeKeyToken.Icon = OudsSizeKeyToken.Icon.WithLabel.Large.SizeMedium,
    val sizeLoader: OudsSizeKeyToken.Icon = OudsSizeKeyToken.Icon.WithLabel.Large.SizeSmall,
    val sizeMaxHeightAssetsContainer: Float = DimensionRawTokens.dimension1200,
    val sizeMinHeight: Float = DimensionRawTokens.dimension650,
    val sizeMinWidth: Float = DimensionRawTokens.dimension1400,
    val spaceColumnGap: OudsSpaceKeyToken.ColumnGap = OudsSpaceKeyToken.ColumnGap.Tall,
    val spaceInset: OudsSpaceKeyToken.Inset = OudsSpaceKeyToken.Inset.Medium,
    val spaceRowGap: OudsSpaceKeyToken.RowGap = OudsSpaceKeyToken.RowGap.None
)

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
import com.orange.ouds.theme.tokens.OudsSizeKeyToken
import com.orange.ouds.theme.tokens.OudsSpaceKeyToken
import com.orange.ouds.tokens.global.raw.DimensionRawTokens

open class OudsSelectTokens(
    val colorBgDisabled: OudsColorKeyToken = OudsColorKeyToken.Opacity.Transparent,
    val colorBgEnabled: OudsColorKeyToken = OudsColorKeyToken.Opacity.Transparent,
    val colorBgFocus: OudsColorKeyToken = OudsColorKeyToken.Action.Support.Focus,
    val colorBgHover: OudsColorKeyToken = OudsColorKeyToken.Action.Support.Hover,
    val colorBgLoading: OudsColorKeyToken = OudsColorKeyToken.Action.Support.Loading,
    val colorBgPressed: OudsColorKeyToken = OudsColorKeyToken.Action.Support.Pressed,
    val colorBorderDisabled: OudsColorKeyToken = OudsColorKeyToken.Action.Disabled,
    val colorBorderEnabled: OudsColorKeyToken = OudsColorKeyToken.Action.Selected,
    val colorBorderHover: OudsColorKeyToken = OudsColorKeyToken.Action.Hover,
    val colorBorderLoading: OudsColorKeyToken = OudsColorKeyToken.Action.Loading,
    val colorBorderPressed: OudsColorKeyToken = OudsColorKeyToken.Action.Pressed,
    val colorContentDisabled: OudsColorKeyToken = OudsColorKeyToken.Content.Disabled,
    val colorContentEnabled: OudsColorKeyToken = OudsColorKeyToken.Content.Default,
    val colorContentFocus: OudsColorKeyToken = OudsColorKeyToken.Content.Default,
    val colorContentHelperTextDisabled: OudsColorKeyToken = OudsColorKeyToken.Content.Disabled,
    val colorContentHelperTextEnabled: OudsColorKeyToken = OudsColorKeyToken.Content.Muted,
    val colorContentHelperTextFocus: OudsColorKeyToken = OudsColorKeyToken.Content.Muted,
    val colorContentHelperTextHover: OudsColorKeyToken = OudsColorKeyToken.Content.Muted,
    val colorContentHelperTextLoading: OudsColorKeyToken = OudsColorKeyToken.Content.Muted,
    val colorContentHelperTextPressed: OudsColorKeyToken = OudsColorKeyToken.Content.Muted,
    val colorContentHover: OudsColorKeyToken = OudsColorKeyToken.Content.Default,
    val colorContentLoading: OudsColorKeyToken = OudsColorKeyToken.Content.Default,
    val colorContentPressed: OudsColorKeyToken = OudsColorKeyToken.Content.Default,
    val sizeCheckBox: OudsSizeKeyToken.Icon = OudsSizeKeyToken.Icon.WithLabel.Large.SizeSmall,
    val sizeIcon: OudsSizeKeyToken.Icon = OudsSizeKeyToken.Icon.WithLabel.Large.SizeMedium,
    val sizeLoader: OudsSizeKeyToken.Icon = OudsSizeKeyToken.Icon.WithLabel.Large.SizeSmall,
    val sizeMaxHeightContainerIcon: Float = DimensionRawTokens.dimension600,
    val sizeMinHeight: Float = DimensionRawTokens.dimension600,
    val sizeMinHeightIconOnly: Float = DimensionRawTokens.dimension600,
    val sizeMinWidth: Float = DimensionRawTokens.dimension600,
    val sizeMinWidthIconOnly: Float = DimensionRawTokens.dimension600,
    val spaceColumnGap: OudsSpaceKeyToken.ColumnGap = OudsSpaceKeyToken.ColumnGap.Tall,
    val spacePaddingInset: OudsSpaceKeyToken.Inset = OudsSpaceKeyToken.Inset.Medium,
    val spaceRowGap: OudsSpaceKeyToken.RowGap = OudsSpaceKeyToken.RowGap.None
)

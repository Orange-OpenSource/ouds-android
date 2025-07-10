/*
 * Software Name: OUDS Android
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
 */

package com.orange.ouds.theme.tokens.components

import com.orange.ouds.theme.tokens.Mode
import com.orange.ouds.theme.tokens.OudsBorderKeyToken
import com.orange.ouds.theme.tokens.OudsColorKeyToken
import com.orange.ouds.theme.tokens.OudsOpacityKeyToken
import com.orange.ouds.theme.tokens.OudsSingleModeColorKeyToken
import com.orange.ouds.tokens.raw.DimensionRawTokens

class OudsNavigationBarTokens(
    val topActiveIndicatorBorderRadiusBottom: OudsBorderKeyToken.Radius = OudsBorderKeyToken.Radius.Default,
    val topActiveIndicatorSizeHeight: Float = DimensionRawTokens.dimensionOutOfSystem75,
    val topActiveIndicatorSizeWidth: Float = DimensionRawTokens.dimension650,
    val topActiveIndicatorOpacity: OudsOpacityKeyToken = OudsOpacityKeyToken.Opaque,
    val colorBg: OudsColorKeyToken = OudsColorKeyToken.Always.Black,
    val materialActiveIndicatorColorBgSelectedEnabled: OudsColorKeyToken = OudsSingleModeColorKeyToken(OudsColorKeyToken.Opacity.Transparent, Mode.Dark),
    val materialActiveIndicatorColorBgSelectedHover: OudsColorKeyToken = OudsSingleModeColorKeyToken(OudsColorKeyToken.Opacity.Transparent, Mode.Dark),
    val materialActiveIndicatorColorBgSelectedPressed: OudsColorKeyToken = OudsSingleModeColorKeyToken(OudsColorKeyToken.Opacity.Transparent, Mode.Dark),
    val materialActiveIndicatorColorBgSelectedFocus: OudsColorKeyToken = OudsSingleModeColorKeyToken(OudsColorKeyToken.Opacity.Transparent, Mode.Dark),
    val materialActiveIndicatorColorBgUnselectedHover: OudsColorKeyToken = OudsSingleModeColorKeyToken(OudsColorKeyToken.Opacity.Transparent, Mode.Dark),
    val materialActiveIndicatorColorBgUnselectedPressed: OudsColorKeyToken = OudsSingleModeColorKeyToken(OudsColorKeyToken.Opacity.Transparent, Mode.Dark),
    val materialActiveIndicatorColorBgUnselectedFocus: OudsColorKeyToken = OudsSingleModeColorKeyToken(OudsColorKeyToken.Opacity.Transparent, Mode.Dark),
    val colorContentSelectedEnabled: OudsColorKeyToken = OudsSingleModeColorKeyToken(OudsColorKeyToken.Action.Selected, Mode.Dark),
    val colorContentSelectedFocus: OudsColorKeyToken = OudsSingleModeColorKeyToken(OudsColorKeyToken.Action.Focus, Mode.Dark),
    val colorContentSelectedHover: OudsColorKeyToken = OudsSingleModeColorKeyToken(OudsColorKeyToken.Action.Hover, Mode.Dark),
    val colorContentSelectedPressed: OudsColorKeyToken = OudsSingleModeColorKeyToken(OudsColorKeyToken.Action.Pressed, Mode.Dark),
    val colorContentUnselectedDisabled: OudsColorKeyToken = OudsSingleModeColorKeyToken(OudsColorKeyToken.Action.Disabled, Mode.Dark),
    val colorContentUnselectedEnabled: OudsColorKeyToken = OudsSingleModeColorKeyToken(OudsColorKeyToken.Content.Default, Mode.Dark),
    val colorContentUnselectedFocus: OudsColorKeyToken = OudsSingleModeColorKeyToken(OudsColorKeyToken.Action.Focus, Mode.Dark),
    val colorContentUnselectedHover: OudsColorKeyToken = OudsSingleModeColorKeyToken(OudsColorKeyToken.Action.Hover, Mode.Dark),
    val colorContentUnselectedPressed: OudsColorKeyToken = OudsSingleModeColorKeyToken(OudsColorKeyToken.Action.Pressed, Mode.Dark),
)
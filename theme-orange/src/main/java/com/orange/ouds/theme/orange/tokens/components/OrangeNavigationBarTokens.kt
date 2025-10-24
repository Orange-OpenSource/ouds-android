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

package com.orange.ouds.theme.orange.tokens.components

import com.orange.ouds.theme.tokens.Mode
import com.orange.ouds.theme.tokens.OudsBorderKeyToken
import com.orange.ouds.theme.tokens.OudsColorKeyToken
import com.orange.ouds.theme.tokens.OudsOpacityKeyToken
import com.orange.ouds.theme.tokens.OudsSingleModeColorKeyToken
import com.orange.ouds.theme.tokens.components.OudsNavigationBarTokens
import com.orange.ouds.tokens.raw.DimensionRawTokens

data class OrangeNavigationBarTokens(
    override val topActiveIndicatorBorderRadiusBottom: OudsBorderKeyToken.Radius = OudsBorderKeyToken.Radius.Default,
    override val topActiveIndicatorSizeHeight: Float = DimensionRawTokens.dimensionOutOfSystem75,
    override val topActiveIndicatorSizeWidth: Float = DimensionRawTokens.dimension650,
    override val topActiveIndicatorOpacity: OudsOpacityKeyToken = OudsOpacityKeyToken.Opaque,
    override val colorBg: OudsColorKeyToken = OudsColorKeyToken.Always.Black,
    override val materialActiveIndicatorColorBgSelectedEnabled: OudsColorKeyToken = OudsSingleModeColorKeyToken(
        OudsColorKeyToken.Opacity.Transparent,
        Mode.Dark
    ),
    override val materialActiveIndicatorColorBgSelectedHover: OudsColorKeyToken = OudsSingleModeColorKeyToken(OudsColorKeyToken.Opacity.Transparent, Mode.Dark),
    override val materialActiveIndicatorColorBgSelectedPressed: OudsColorKeyToken = OudsSingleModeColorKeyToken(
        OudsColorKeyToken.Opacity.Transparent,
        Mode.Dark
    ),
    override val materialActiveIndicatorColorBgSelectedFocus: OudsColorKeyToken = OudsSingleModeColorKeyToken(OudsColorKeyToken.Opacity.Transparent, Mode.Dark),
    override val materialActiveIndicatorColorBgUnselectedHover: OudsColorKeyToken = OudsSingleModeColorKeyToken(
        OudsColorKeyToken.Opacity.Transparent,
        Mode.Dark
    ),
    override val materialActiveIndicatorColorBgUnselectedPressed: OudsColorKeyToken = OudsSingleModeColorKeyToken(
        OudsColorKeyToken.Opacity.Transparent,
        Mode.Dark
    ),
    override val materialActiveIndicatorColorBgUnselectedFocus: OudsColorKeyToken = OudsSingleModeColorKeyToken(
        OudsColorKeyToken.Opacity.Transparent,
        Mode.Dark
    ),
    override val colorContentSelectedEnabled: OudsColorKeyToken = OudsSingleModeColorKeyToken(OudsColorKeyToken.Action.Selected, Mode.Dark),
    override val colorContentSelectedFocus: OudsColorKeyToken = OudsSingleModeColorKeyToken(OudsColorKeyToken.Action.Focus, Mode.Dark),
    override val colorContentSelectedHover: OudsColorKeyToken = OudsSingleModeColorKeyToken(OudsColorKeyToken.Action.Hover, Mode.Dark),
    override val colorContentSelectedPressed: OudsColorKeyToken = OudsSingleModeColorKeyToken(OudsColorKeyToken.Action.Pressed, Mode.Dark),
    override val colorContentUnselectedDisabled: OudsColorKeyToken = OudsSingleModeColorKeyToken(OudsColorKeyToken.Action.Disabled, Mode.Dark),
    override val colorContentUnselectedEnabled: OudsColorKeyToken = OudsSingleModeColorKeyToken(OudsColorKeyToken.Content.Default, Mode.Dark),
    override val colorContentUnselectedFocus: OudsColorKeyToken = OudsSingleModeColorKeyToken(OudsColorKeyToken.Action.Focus, Mode.Dark),
    override val colorContentUnselectedHover: OudsColorKeyToken = OudsSingleModeColorKeyToken(OudsColorKeyToken.Action.Hover, Mode.Dark),
    override val colorContentUnselectedPressed: OudsColorKeyToken = OudsSingleModeColorKeyToken(OudsColorKeyToken.Action.Pressed, Mode.Dark),
) : OudsNavigationBarTokens
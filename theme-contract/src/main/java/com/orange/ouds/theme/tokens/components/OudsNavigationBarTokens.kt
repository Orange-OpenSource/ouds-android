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

import com.orange.ouds.theme.tokens.OudsBorderKeyToken
import com.orange.ouds.theme.tokens.OudsColorKeyToken
import com.orange.ouds.theme.tokens.OudsOpacityKeyToken

interface OudsNavigationBarTokens {
    val topActiveIndicatorBorderRadiusBottom: OudsBorderKeyToken.Radius
    val topActiveIndicatorSizeHeight: Float
    val topActiveIndicatorSizeWidth: Float
    val topActiveIndicatorOpacity: OudsOpacityKeyToken
    val colorBg: OudsColorKeyToken
    val materialActiveIndicatorColorBgSelectedEnabled: OudsColorKeyToken
    val materialActiveIndicatorColorBgSelectedHover: OudsColorKeyToken
    val materialActiveIndicatorColorBgSelectedPressed: OudsColorKeyToken
    val materialActiveIndicatorColorBgSelectedFocus: OudsColorKeyToken
    val materialActiveIndicatorColorBgUnselectedHover: OudsColorKeyToken
    val materialActiveIndicatorColorBgUnselectedPressed: OudsColorKeyToken
    val materialActiveIndicatorColorBgUnselectedFocus: OudsColorKeyToken
    val colorContentSelectedEnabled: OudsColorKeyToken
    val colorContentSelectedFocus: OudsColorKeyToken
    val colorContentSelectedHover: OudsColorKeyToken
    val colorContentSelectedPressed: OudsColorKeyToken
    val colorContentUnselectedDisabled: OudsColorKeyToken
    val colorContentUnselectedEnabled: OudsColorKeyToken
    val colorContentUnselectedFocus: OudsColorKeyToken
    val colorContentUnselectedHover: OudsColorKeyToken
    val colorContentUnselectedPressed: OudsColorKeyToken
}
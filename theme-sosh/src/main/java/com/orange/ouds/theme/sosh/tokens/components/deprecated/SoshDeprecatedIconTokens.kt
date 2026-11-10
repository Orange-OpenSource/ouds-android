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

package com.orange.ouds.theme.sosh.tokens.components.deprecated

import com.orange.ouds.theme.tokens.OudsColorKeyToken
import com.orange.ouds.theme.tokens.OudsLightDarkColorKeyToken
import com.orange.ouds.theme.tokens.components.deprecated.OudsDeprecatedIconsTokens

@Suppress("OVERRIDE_DEPRECATION")
internal class SoshDeprecatedIconTokens : OudsDeprecatedIconsTokens {

    override val colorContentStatusWarningInverseExternalShape: OudsColorKeyToken =
        OudsLightDarkColorKeyToken(OudsColorKeyToken.Repository.Warning.Low, OudsColorKeyToken.Repository.Warning.Medium)
    override val colorContentStatusWarningInverseInternalShape: OudsColorKeyToken =
        OudsLightDarkColorKeyToken(OudsColorKeyToken.Opacity.Transparent, OudsColorKeyToken.Repository.Warning.High)
}

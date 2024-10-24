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

package com.orange.ouds.theme.tokens.semantic

data class OudsColorSemanticTokens(
    val globalColorTokens: OudsColorGlobalSemanticTokens = OudsColorGlobalSemanticTokens(),
    val actionColorTokens: OudsColorActionSemanticTokens = OudsColorActionSemanticTokens(),
    val alwaysColorTokens: OudsColorAlwaysSemanticTokens = OudsColorAlwaysSemanticTokens(),
    val backgroundColorTokens: OudsColorBgSemanticTokens = OudsColorBgSemanticTokens(),
    val borderColorTokens: OudsColorBorderSemanticTokens = OudsColorBorderSemanticTokens(),
    val brandColorTokens: OudsColorBrandSemanticTokens = OudsColorBrandSemanticTokens(),
    val contentColorTokens: OudsColorContentSemanticTokens = OudsColorContentSemanticTokens(),
    val elevationColorTokens: OudsColorElevationSemanticTokens = OudsColorElevationSemanticTokens(),
    val gradientColorTokens: OudsColorGradientSemanticTokens = OudsColorGradientSemanticTokens(),
    val decorativeColorTokens: OudsColorDecorativeSemanticTokens = OudsColorDecorativeSemanticTokens(),
)
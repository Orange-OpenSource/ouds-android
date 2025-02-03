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

import com.orange.ouds.foundation.InternalOudsApi

interface OudsColorSemanticTokens {
    val actionColorTokens: OudsColorActionSemanticTokens
    val alwaysColorTokens: OudsColorAlwaysSemanticTokens
    val backgroundColorTokens: OudsColorBgSemanticTokens
    val borderColorTokens: OudsColorBorderSemanticTokens
    val contentColorTokens: OudsColorContentSemanticTokens
    val decorativeColorTokens: OudsColorDecorativeSemanticTokens
    val opacityColorTokens: OudsColorOpacitySemanticTokens
    val overlayColorTokens: OudsColorOverlaySemanticTokens
    val surfaceColorTokens: OudsColorSurfaceSemanticTokens

    @InternalOudsApi
    val repositoryColorTokens: OudsColorRepositorySemanticTokens
}
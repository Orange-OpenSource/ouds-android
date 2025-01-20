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

package com.orange.ouds.theme.orange.tokens.semantic

import com.orange.ouds.theme.tokens.semantic.OudsColorRepositorySemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsColorSemanticTokens

data class OrangeColorSemanticTokens(
    override val actionColorTokens: OrangeColorActionSemanticTokens = OrangeColorActionSemanticTokens(),
    override val alwaysColorTokens: OrangeColorAlwaysSemanticTokens = OrangeColorAlwaysSemanticTokens(),
    override val backgroundColorTokens: OrangeColorBgSemanticTokens = OrangeColorBgSemanticTokens(),
    override val borderColorTokens: OrangeColorBorderSemanticTokens = OrangeColorBorderSemanticTokens(),
    override val contentColorTokens: OrangeColorContentSemanticTokens = OrangeColorContentSemanticTokens(),
    override val decorativeColorTokens: OrangeColorDecorativeSemanticTokens = OrangeColorDecorativeSemanticTokens(),
    override val opacityColorTokens: OrangeColorOpacitySemanticTokens = OrangeColorOpacitySemanticTokens(),
    override val overlayColorTokens: OrangeColorOverlaySemanticTokens = OrangeColorOverlaySemanticTokens(),
    override val surfaceColorTokens: OrangeColorSurfaceSemanticTokens = OrangeColorSurfaceSemanticTokens(),
    override val repositoryColorTokens: OudsColorRepositorySemanticTokens = OrangeColorRepositorySemanticTokens()
) : OudsColorSemanticTokens
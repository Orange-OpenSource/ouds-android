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

import com.orange.ouds.theme.tokens.semantic.OudsColorActionSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsColorAlwaysSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsColorBgSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsColorBorderSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsColorContentSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsColorDecorativeSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsColorOpacitySemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsColorOverlaySemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsColorRepositorySemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsColorSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsColorSurfaceSemanticTokens

data class OrangeColorSemanticTokens(
    override val actionColorTokens: OudsColorActionSemanticTokens = OrangeColorActionSemanticTokens(),
    override val alwaysColorTokens: OudsColorAlwaysSemanticTokens = OrangeColorAlwaysSemanticTokens(),
    override val backgroundColorTokens: OudsColorBgSemanticTokens = OrangeColorBgSemanticTokens(),
    override val borderColorTokens: OudsColorBorderSemanticTokens = OrangeColorBorderSemanticTokens(),
    override val contentColorTokens: OudsColorContentSemanticTokens = OrangeColorContentSemanticTokens(),
    override val decorativeColorTokens: OudsColorDecorativeSemanticTokens = OrangeColorDecorativeSemanticTokens(),
    override val opacityColorTokens: OudsColorOpacitySemanticTokens = OrangeColorOpacitySemanticTokens(),
    override val overlayColorTokens: OudsColorOverlaySemanticTokens = OrangeColorOverlaySemanticTokens(),
    override val surfaceColorTokens: OudsColorSurfaceSemanticTokens = OrangeColorSurfaceSemanticTokens(),
    override val repositoryColorTokens: OudsColorRepositorySemanticTokens = OrangeColorRepositorySemanticTokens()
) : OudsColorSemanticTokens
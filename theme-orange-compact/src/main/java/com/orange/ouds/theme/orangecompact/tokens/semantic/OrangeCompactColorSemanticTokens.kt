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

package com.orange.ouds.theme.orangecompact.tokens.semantic

import com.orange.ouds.theme.tokens.semantic.OudsColorActionSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsColorAlwaysSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsColorBgSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsColorBorderSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsColorContentSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsColorModeSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsColorOpacitySemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsColorOverlaySemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsColorRepositorySemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsColorSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsColorSurfaceSemanticTokens

internal data class OrangeCompactColorSemanticTokens(
    override val actionColorTokens: OudsColorActionSemanticTokens = OrangeCompactColorActionSemanticTokens(),
    override val alwaysColorTokens: OudsColorAlwaysSemanticTokens = OrangeCompactColorAlwaysSemanticTokens(),
    override val backgroundColorTokens: OudsColorBgSemanticTokens = OrangeCompactColorBgSemanticTokens(),
    override val borderColorTokens: OudsColorBorderSemanticTokens = OrangeCompactColorBorderSemanticTokens(),
    override val contentColorTokens: OudsColorContentSemanticTokens = OrangeCompactColorContentSemanticTokens(),
    override val opacityColorTokens: OudsColorOpacitySemanticTokens = OrangeCompactColorOpacitySemanticTokens(),
    override val overlayColorTokens: OudsColorOverlaySemanticTokens = OrangeCompactColorOverlaySemanticTokens(),
    override val surfaceColorTokens: OudsColorSurfaceSemanticTokens = OrangeCompactColorSurfaceSemanticTokens(),
    override val repositoryColorTokens: OudsColorRepositorySemanticTokens = OrangeCompactColorRepositorySemanticTokens(),
    override val colorModeTokens: OudsColorModeSemanticTokens = OrangeCompactColorModeSemanticTokens()
) : OudsColorSemanticTokens
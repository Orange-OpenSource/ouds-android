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

package com.orange.ouds.theme.orangebusinesstools.tokens.semantic

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

internal data class OrangeBusinessToolsColorSemanticTokens(
    override val actionColorTokens: OudsColorActionSemanticTokens = OrangeBusinessToolsColorActionSemanticTokens(),
    override val alwaysColorTokens: OudsColorAlwaysSemanticTokens = OrangeBusinessToolsColorAlwaysSemanticTokens(),
    override val backgroundColorTokens: OudsColorBgSemanticTokens = OrangeBusinessToolsColorBgSemanticTokens(),
    override val borderColorTokens: OudsColorBorderSemanticTokens = OrangeBusinessToolsColorBorderSemanticTokens(),
    override val contentColorTokens: OudsColorContentSemanticTokens = OrangeBusinessToolsColorContentSemanticTokens(),
    override val opacityColorTokens: OudsColorOpacitySemanticTokens = OrangeBusinessToolsColorOpacitySemanticTokens(),
    override val overlayColorTokens: OudsColorOverlaySemanticTokens = OrangeBusinessToolsColorOverlaySemanticTokens(),
    override val surfaceColorTokens: OudsColorSurfaceSemanticTokens = OrangeBusinessToolsColorSurfaceSemanticTokens(),
    override val repositoryColorTokens: OudsColorRepositorySemanticTokens = OrangeBusinessToolsColorRepositorySemanticTokens(),
    override val colorModeTokens: OudsColorModeSemanticTokens = OrangeBusinessToolsColorModeSemanticTokens()
) : OudsColorSemanticTokens
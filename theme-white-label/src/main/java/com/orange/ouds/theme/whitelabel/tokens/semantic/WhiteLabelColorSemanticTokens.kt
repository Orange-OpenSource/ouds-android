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

package com.orange.ouds.theme.whitelabel.tokens.semantic

import com.orange.ouds.theme.tokens.semantic.OudsColorActionSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsColorAiSemanticTokens
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

internal class WhiteLabelColorSemanticTokens(
    override val actionColorTokens: OudsColorActionSemanticTokens,
    override val aiColorTokens: OudsColorAiSemanticTokens,
    override val alwaysColorTokens: OudsColorAlwaysSemanticTokens,
    override val backgroundColorTokens: OudsColorBgSemanticTokens,
    override val borderColorTokens: OudsColorBorderSemanticTokens,
    override val contentColorTokens: OudsColorContentSemanticTokens,
    override val opacityColorTokens: OudsColorOpacitySemanticTokens,
    override val overlayColorTokens: OudsColorOverlaySemanticTokens,
    override val repositoryColorTokens: OudsColorRepositorySemanticTokens,
    override val surfaceColorTokens: OudsColorSurfaceSemanticTokens,
    override val colorModeTokens: OudsColorModeSemanticTokens
) : OudsColorSemanticTokens

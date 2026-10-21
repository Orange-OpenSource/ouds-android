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
import com.orange.ouds.theme.wireframe.tokens.semantic.WireframeColorModeSemanticTokens

/**
 * White Label color semantic tokens.
 *
 * Inherits from Wireframe color semantic tokens with customizable overrides.
 * Parameters left as [Color.Unspecified] will use the default Wireframe values.
 *
 * ```
 * WhiteLabelColorSemanticTokens(
 *     actionColorTokens = WhiteLabelColorActionSemanticTokens(
 *         actionEnabledLight = Color(0xFF007AFF)
 *     )
 * )
 * ```
 */
class WhiteLabelColorSemanticTokens(
    override val actionColorTokens: OudsColorActionSemanticTokens = WhiteLabelColorActionSemanticTokens(),
    override val aiColorTokens: OudsColorAiSemanticTokens = WhiteLabelColorAiSemanticTokens(),
    override val alwaysColorTokens: OudsColorAlwaysSemanticTokens = WhiteLabelColorAlwaysSemanticTokens(),
    override val backgroundColorTokens: OudsColorBgSemanticTokens = WhiteLabelColorBgSemanticTokens(),
    override val borderColorTokens: OudsColorBorderSemanticTokens = WhiteLabelColorBorderSemanticTokens(),
    override val contentColorTokens: OudsColorContentSemanticTokens = WhiteLabelColorContentSemanticTokens(),
    override val opacityColorTokens: OudsColorOpacitySemanticTokens = WhiteLabelColorOpacitySemanticTokens(),
    override val overlayColorTokens: OudsColorOverlaySemanticTokens = WhiteLabelColorOverlaySemanticTokens(),
    override val repositoryColorTokens: OudsColorRepositorySemanticTokens = WhiteLabelColorRepositorySemanticTokens(),
    override val surfaceColorTokens: OudsColorSurfaceSemanticTokens = WhiteLabelColorSurfaceSemanticTokens()
) : OudsColorSemanticTokens {

    override val colorModeTokens: OudsColorModeSemanticTokens = WireframeColorModeSemanticTokens()
}

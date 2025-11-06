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

package com.orange.ouds.theme.wireframe.tokens.semantic

import com.orange.ouds.foundation.InternalOudsApi
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

class WireframeColorSemanticTokens(
    override val actionColorTokens: OudsColorActionSemanticTokens = WireframeColorActionSemanticTokens(),
    override val alwaysColorTokens: OudsColorAlwaysSemanticTokens = WireframeColorAlwaysSemanticTokens(),
    override val backgroundColorTokens: OudsColorBgSemanticTokens = WireframeColorBgSemanticTokens(),
    override val borderColorTokens: OudsColorBorderSemanticTokens = WireframeColorBorderSemanticTokens(),
    override val contentColorTokens: OudsColorContentSemanticTokens = WireframeColorContentSemanticTokens(),
    override val opacityColorTokens: OudsColorOpacitySemanticTokens = WireframeColorOpacitySemanticTokens(),
    override val overlayColorTokens: OudsColorOverlaySemanticTokens = WireframeColorOverlaySemanticTokens(),
    override val surfaceColorTokens: OudsColorSurfaceSemanticTokens = WireframeColorSurfaceSemanticTokens(),
    @InternalOudsApi override val repositoryColorTokens: OudsColorRepositorySemanticTokens = WireframeColorRepositorySemanticTokens(),
    @InternalOudsApi override val colorModeTokens: OudsColorModeSemanticTokens = WireframeColorModeSemanticTokens()
) : OudsColorSemanticTokens

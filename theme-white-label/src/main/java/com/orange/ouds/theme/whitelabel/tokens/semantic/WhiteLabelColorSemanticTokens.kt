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

import com.orange.ouds.theme.tokens.semantic.OudsColorRepositorySemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsColorSemanticTokens

data class WhiteLabelColorSemanticTokens(
    override val actionColorTokens: WhiteLabelColorActionSemanticTokens = WhiteLabelColorActionSemanticTokens(),
    override val alwaysColorTokens: WhiteLabelColorAlwaysSemanticTokens = WhiteLabelColorAlwaysSemanticTokens(),
    override val backgroundColorTokens: WhiteLabelColorBgSemanticTokens = WhiteLabelColorBgSemanticTokens(),
    override val borderColorTokens: WhiteLabelColorBorderSemanticTokens = WhiteLabelColorBorderSemanticTokens(),
    override val contentColorTokens: WhiteLabelColorContentSemanticTokens = WhiteLabelColorContentSemanticTokens(),
    override val decorativeColorTokens: WhiteLabelColorDecorativeSemanticTokens = WhiteLabelColorDecorativeSemanticTokens(),
    override val opacityColorTokens: WhiteLabelColorOpacitySemanticTokens = WhiteLabelColorOpacitySemanticTokens(),
    override val overlayColorTokens: WhiteLabelColorOverlaySemanticTokens = WhiteLabelColorOverlaySemanticTokens(),
    override val surfaceColorTokens: WhiteLabelColorSurfaceSemanticTokens = WhiteLabelColorSurfaceSemanticTokens(),
    override val repositoryColorTokens: OudsColorRepositorySemanticTokens = WhiteLabelColorRepositorySemanticTokens()
) : OudsColorSemanticTokens
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

package com.orange.ouds.theme.sosh.tokens.semantic

import com.orange.ouds.foundation.InternalOudsApi
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
import com.orange.ouds.theme.tokens.semantic.OudsColoredBackgroundModeSemanticTokens

data class SoshColorSemanticTokens(
    override val actionColorTokens: OudsColorActionSemanticTokens = SoshColorActionSemanticTokens(),
    override val alwaysColorTokens: OudsColorAlwaysSemanticTokens = SoshColorAlwaysSemanticTokens(),
    override val backgroundColorTokens: OudsColorBgSemanticTokens = SoshColorBgSemanticTokens(),
    override val borderColorTokens: OudsColorBorderSemanticTokens = SoshColorBorderSemanticTokens(),
    override val contentColorTokens: OudsColorContentSemanticTokens = SoshColorContentSemanticTokens(),
    override val decorativeColorTokens: OudsColorDecorativeSemanticTokens = SoshColorDecorativeSemanticTokens(),
    override val opacityColorTokens: OudsColorOpacitySemanticTokens = SoshColorOpacitySemanticTokens(),
    override val overlayColorTokens: OudsColorOverlaySemanticTokens = SoshColorOverlaySemanticTokens(),
    override val surfaceColorTokens: OudsColorSurfaceSemanticTokens = SoshColorSurfaceSemanticTokens(),
    @InternalOudsApi override val repositoryColorTokens: OudsColorRepositorySemanticTokens = SoshColorRepositorySemanticTokens(),
    @InternalOudsApi override val colorModeTokens: OudsColoredBackgroundModeSemanticTokens = SoshColoredBackgroundModeSemanticTokens()
) : OudsColorSemanticTokens
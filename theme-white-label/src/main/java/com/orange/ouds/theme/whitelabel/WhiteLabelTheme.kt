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

package com.orange.ouds.theme.whitelabel

import androidx.compose.ui.text.font.FontFamily
import com.orange.ouds.theme.tokens.material.OudsMaterialColorTokens
import com.orange.ouds.theme.tokens.semantic.OudsColorSemanticTokens
import com.orange.ouds.theme.whitelabel.tokens.material.WhiteLabelMaterialColorTokens
import com.orange.ouds.theme.whitelabel.tokens.semantic.WhiteLabelColorSemanticTokens
import com.orange.ouds.theme.wireframe.WireframeTheme
import java.util.Locale

const val WHITE_LABEL_THEME_NAME = "White Label"

class WhiteLabelTheme(
    private val whiteLabelFontFamily: WhiteLabelFontFamily = WhiteLabelFontFamily.Default,
    colorTokens: WhiteLabelColorSemanticTokens = WhiteLabelColorSemanticTokens(),
    materialColorTokens: WhiteLabelMaterialColorTokens = WhiteLabelMaterialColorTokens()
) : WireframeTheme() {

    override val name: String
        get() = WHITE_LABEL_THEME_NAME

    override fun getFontFamily(locale: Locale): FontFamily {
        return whiteLabelFontFamily.getFontFamily(locale)
    }

    override val colorTokens: OudsColorSemanticTokens = colorTokens

    override val materialColorTokens: OudsMaterialColorTokens = materialColorTokens
}

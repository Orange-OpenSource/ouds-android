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

package com.orange.ouds.theme.orangecountry

import androidx.compose.ui.graphics.Color
import com.orange.ouds.theme.orange.OrangeTheme
import com.orange.ouds.theme.orange.tokens.semantic.OrangeColorBgSemanticTokens
import com.orange.ouds.theme.orange.tokens.semantic.OrangeColorSemanticTokens
import com.orange.ouds.theme.tokens.OudsBorderKeyToken
import com.orange.ouds.theme.tokens.OudsColorKeyToken
import com.orange.ouds.theme.tokens.components.OudsButtonTokens
import com.orange.ouds.theme.tokens.components.OudsComponentsTokens
import com.orange.ouds.theme.tokens.semantic.OudsColorSemanticTokens
import kotlinx.parcelize.Parcelize

@Parcelize
class OrangeCountryTheme : OrangeTheme() {

    override val name: String
        get() = "Orange country"

    override val colorTokens: OudsColorSemanticTokens
        get() = OrangeColorSemanticTokens(
            backgroundColorTokens = OrangeColorBgSemanticTokens(
                bgPrimaryLight = Color(0xFF34D349),
                bgPrimaryDark = Color(0xFF069D1A)
            )
        )

    override val componentsTokens: OudsComponentsTokens
        get() = OudsComponentsTokens(
            button = OudsButtonTokens(colorBgDefaultEnabled = OudsColorKeyToken.Surface.Status.Warning.Muted, borderRadius = OudsBorderKeyToken.Radius.Short)
        )
}
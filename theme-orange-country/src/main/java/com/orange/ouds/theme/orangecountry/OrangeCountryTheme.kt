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
import com.orange.ouds.theme.orange.tokens.components.OrangeComponentsTokens
import com.orange.ouds.theme.orange.tokens.semantic.OrangeColorActionSemanticTokens
import com.orange.ouds.theme.orange.tokens.semantic.OrangeColorBorderSemanticTokens
import com.orange.ouds.theme.orange.tokens.semantic.OrangeColorContentSemanticTokens
import com.orange.ouds.theme.orange.tokens.semantic.OrangeColorSemanticTokens
import com.orange.ouds.theme.orange.tokens.semantic.OrangeColorSurfaceSemanticTokens
import com.orange.ouds.theme.orangecountry.components.OrangeCountryComponentsTokens
import com.orange.ouds.theme.tokens.components.OudsComponentsTokens
import com.orange.ouds.theme.tokens.semantic.OudsColorSemanticTokens
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class OrangeCountryTheme : OrangeTheme() {
    @IgnoredOnParcel
    private val colorGreen500 = Color(0xFF34D349)

    @IgnoredOnParcel
    private val colorGreen600 = Color(0xFF069D1A)

    override val name: String
        get() = "Orange country"

    override val colorTokens: OudsColorSemanticTokens
        get() = OrangeColorSemanticTokens(
            actionColorTokens = OrangeColorActionSemanticTokens(
                actionLoadingLight = colorGreen500,
                actionPressedLight = colorGreen500,
                actionSelectedLight = colorGreen500,
                actionLoadingDark = colorGreen600,
                actionPressedDark = colorGreen600,
                actionSelectedDark = colorGreen600
            ),
            borderColorTokens = OrangeColorBorderSemanticTokens(
                borderBrandPrimaryLight = colorGreen600,
                borderBrandPrimaryDark = colorGreen500
            ),
            contentColorTokens = OrangeColorContentSemanticTokens(
                contentBrandPrimaryLight = colorGreen600,
                contentBrandPrimaryDark = colorGreen500
            ),
            surfaceColorTokens = OrangeColorSurfaceSemanticTokens(
                surfaceBrandPrimaryLight = colorGreen500,
                surfaceBrandPrimaryDark = colorGreen500
            )
        )

    override val componentsTokens: OudsComponentsTokens
        get() = OrangeCountryComponentsTokens()
}
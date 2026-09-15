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

package com.orange.ouds.app

import androidx.compose.ui.graphics.Color
import com.orange.ouds.foundation.RestrictedOudsApi
import com.orange.ouds.theme.orange.OrangeDrawableResources
import com.orange.ouds.theme.orange.OrangeTheme
import com.orange.ouds.theme.orange.tokens.components.OrangeButtonTokens
import com.orange.ouds.theme.orange.tokens.components.OrangeComponentsTokens
import com.orange.ouds.theme.orange.tokens.semantic.OrangeColorContentSemanticTokens
import com.orange.ouds.theme.orange.tokens.semantic.OrangeColorSemanticTokens
import com.orange.ouds.theme.tokens.OudsColorKeyToken
import com.orange.ouds.theme.tokens.components.OudsComponentsTokens

@Suppress("DEPRECATION")
class CustomTheme : OrangeTheme() {

//    override val materialColorTokens = OrangeMaterialColorTokens().copy(
//        backgroundDark = Color.Red
//    )

    @OptIn(RestrictedOudsApi::class)
    override val colorTokens = OrangeColorSemanticTokens(
        contentColorTokens = OrangeColorContentSemanticTokens(
            contentBrandPrimaryLight = Color.Green,
            contentBrandPrimaryDark = Color.Yellow
        )
    )

    @OptIn(RestrictedOudsApi::class)
    override val componentsTokens: OudsComponentsTokens = OrangeComponentsTokens(
        button = OrangeButtonTokens(
            colorBgBrandEnabled = OudsColorKeyToken.Content.BrandPrimary
        )
    )

    @OptIn(RestrictedOudsApi::class)
    override val drawableResources = OrangeDrawableResources(
        component = OrangeDrawableResources.Component(
            bulletList = OrangeDrawableResources.Component.BulletList(
                level0 = R.drawable.ic_component_atom
            )
        )
    )
}

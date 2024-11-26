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

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.orange.ouds.theme.OudsThemeContract
import com.orange.ouds.theme.tokens.OudsBorderKeyToken
import com.orange.ouds.theme.tokens.OudsElevationKeyToken
import com.orange.ouds.theme.tokens.OudsSpaceKeyToken
import com.orange.ouds.theme.tokens.OudsTypographyKeyToken
import com.orange.ouds.theme.tokens.components.OudsButtonTokens
import com.orange.ouds.theme.tokens.components.OudsComponentsTokens
import com.orange.ouds.theme.tokens.semantic.OudsColorSemanticTokens
import com.orange.ouds.theme.whitelabel.tokens.whiteLabelSemanticColorTokens
import kotlinx.parcelize.Parcelize

@Parcelize
open class WhiteLabelTheme : OudsThemeContract {

    override val name: String
        get() = "White label"

    override val colorTokens: OudsColorSemanticTokens
        get() = whiteLabelSemanticColorTokens

    override val fontFamily: FontFamily
        get() = FontFamily(Font(R.font.oswald))

    override val componentsTokens: OudsComponentsTokens
        get() = OudsComponentsTokens(
            button = OudsButtonTokens(
                cornerRadius = OudsBorderKeyToken.Radius.Pill,
                defaultElevation = OudsElevationKeyToken.OverlayDefault,
                focusedElevation = OudsElevationKeyToken.OverlayDefault,
                hoveredElevation = OudsElevationKeyToken.OverlayDefault,
                labelStyle = OudsTypographyKeyToken.Body.Default.Large,
                verticalContentPadding = OudsSpaceKeyToken.Fixed.Shortest,
                horizontalContentPadding = OudsSpaceKeyToken.Fixed.Short
            )
        )
}

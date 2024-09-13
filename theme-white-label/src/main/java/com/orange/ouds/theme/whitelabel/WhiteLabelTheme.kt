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

import com.orange.ouds.theme.OudsThemeContract
import com.orange.ouds.theme.tokens.components.OudsButtonTokens
import com.orange.ouds.theme.tokens.components.OudsComponentsTokens
import com.orange.ouds.theme.tokens.semantic.OudsBorderRadiusToken
import com.orange.ouds.theme.tokens.semantic.OudsColorTokens
import com.orange.ouds.theme.tokens.semantic.OudsElevationToken
import com.orange.ouds.theme.whitelabel.tokens.whiteLabelSemanticColorTokens
import kotlinx.parcelize.Parcelize

@Parcelize
open class WhiteLabelTheme : OudsThemeContract {

    override val name: String
        get() = "White label"

    override val colorTokens: OudsColorTokens
        get() = whiteLabelSemanticColorTokens

    override val componentsTokens: OudsComponentsTokens
        get() = OudsComponentsTokens(
            button = OudsButtonTokens(
                cornerRadius = OudsBorderRadiusToken.Pill,
                defaultElevation = OudsElevationToken.OverlayDefault,
                focusedElevation = OudsElevationToken.OverlayDefault,
                hoveredElevation = OudsElevationToken.OverlayDefault
            )
        )
}

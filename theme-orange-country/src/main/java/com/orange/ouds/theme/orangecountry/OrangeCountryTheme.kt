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

import com.orange.ouds.theme.orange.OrangeTheme
import com.orange.ouds.theme.orangecountry.tokens.orangeCountrySemanticColorTokens
import com.orange.ouds.theme.tokens.OudsBorderKeyToken
import com.orange.ouds.theme.tokens.components.OudsButtonTokens
import com.orange.ouds.theme.tokens.components.OudsComponentsTokens
import com.orange.ouds.theme.tokens.semantic.OudsColorTokens

class OrangeCountryTheme : OrangeTheme() {

    override val name: String
        get() = "Orange country"

    override val colorTokens: OudsColorTokens
        get() = orangeCountrySemanticColorTokens

    override val componentsTokens: OudsComponentsTokens
        get() = OudsComponentsTokens(
            button = OudsButtonTokens(cornerRadius = OudsBorderKeyToken.Radius.Short)
        )
}
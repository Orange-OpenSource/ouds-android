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

import com.orange.ouds.theme.colors.OudsSemanticColorValue
import com.orange.ouds.theme.colors.OudsSemanticColors
import com.orange.ouds.theme.orange.OrangeSemanticColors
import com.orange.ouds.theme.orange.OrangeTheme

class OrangeCountryTheme : OrangeTheme() {

    override val name: String
        get() = "Orange country"

    override val semanticColors: OudsSemanticColors
        get() = OrangeSemanticColors.apply {
            with(OrangeCountryRawColors) {
                primary = OudsSemanticColorValue(alert200, alert100)
            }
        }
}
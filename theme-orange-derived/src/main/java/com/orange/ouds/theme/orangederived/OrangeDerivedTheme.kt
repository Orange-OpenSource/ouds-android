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

package com.orange.ouds.theme.orangederived

import com.orange.ouds.theme.colors.OudsSemanticColorValue
import com.orange.ouds.theme.colors.OudsSemanticColors
import com.orange.ouds.theme.orange.OrangeSemanticColors
import com.orange.ouds.theme.orange.OrangeTheme

class OrangeDerivedTheme : OrangeTheme() {

    override val name: String
        get() = "Orange derived"

    override val semanticColors: OudsSemanticColors
        get() = OrangeSemanticColors.apply {
            with(OrangeDerivedRawColors) {
                primary = OudsSemanticColorValue(alert200, alert100)
            }
        }
}
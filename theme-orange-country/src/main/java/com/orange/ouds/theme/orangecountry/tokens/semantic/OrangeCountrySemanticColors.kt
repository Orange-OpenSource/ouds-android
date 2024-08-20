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

package com.orange.ouds.theme.orangecountry.tokens.semantic

import com.orange.ouds.theme.orange.tokens.semantic.orangeSemanticColors
import com.orange.ouds.theme.orangecountry.OrangeCountryTheme
import com.orange.ouds.theme.orangecountry.tokens.raw.OrangeCountryRawColors
import com.orange.ouds.theme.tokens.semantic.OudsSemanticColorValue

val OrangeCountryTheme.orangeCountrySemanticColors
    get() = orangeSemanticColors.apply {
        with(OrangeCountryRawColors) {
            primary = OudsSemanticColorValue(alert200, alert100)
        }
    }
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

package com.orange.ouds.theme.whitelabel.tokens

import com.orange.ouds.theme.tokens.semantic.OudsColorTokenValue
import com.orange.ouds.theme.tokens.semantic.OudsColorTokens

val WhiteLabelColorSemanticTokens = with(WhiteLabelColorRawTokens) {
    OudsColorTokens(
        primary = OudsColorTokenValue(primary40, primary80),
        onPrimary = OudsColorTokenValue(white, primary20),
        primaryContainer = OudsColorTokenValue(primary90, primary30),
        onPrimaryContainer = OudsColorTokenValue(white, white),
        inversePrimary = OudsColorTokenValue(primary80, primary40),

        secondary = OudsColorTokenValue(secondary40, secondary80),
        onSecondary = OudsColorTokenValue(white, secondary20),
        secondaryContainer = OudsColorTokenValue(secondary90, secondary30),
        onSecondaryContainer = OudsColorTokenValue(secondary10, secondary90),

        tertiary = OudsColorTokenValue(tertiary40, tertiary80),
        onTertiary = OudsColorTokenValue(white, tertiary20),
        tertiaryContainer = OudsColorTokenValue(tertiary90, tertiary30),
        onTertiaryContainer = OudsColorTokenValue(tertiary10, tertiary90),
        background = OudsColorTokenValue(white, black),
        onBackground = OudsColorTokenValue(black, white),
        surface = OudsColorTokenValue(neutral98, neutral6),
        onSurface = OudsColorTokenValue(black, neutral90),
        surfaceVariant = OudsColorTokenValue(neutral94, neutral24),
        onSurfaceVariant = OudsColorTokenValue(black, neutralVariant90),
        surfaceTint = OudsColorTokenValue(white, neutral98),
        inverseSurface = OudsColorTokenValue(neutral20, neutral90),
        inverseOnSurface = OudsColorTokenValue(neutral95, neutral20),
        error = OudsColorTokenValue(error40, error80),
        onError = OudsColorTokenValue(white, error20),
        errorContainer = OudsColorTokenValue(error90, error30),
        onErrorContainer = OudsColorTokenValue(white, error90),
        outline = OudsColorTokenValue(neutralVariant50, neutralVariant60),
        outlineVariant = OudsColorTokenValue(neutralVariant80, neutralVariant30),
        scrim = OudsColorTokenValue(black, black),
        positive = OudsColorTokenValue(green, green),
        onPositive = OudsColorTokenValue(white, white),
        negative = OudsColorTokenValue(red, red),
        onNegative = OudsColorTokenValue(white, white),
        info = OudsColorTokenValue(info, info),
        alert = OudsColorTokenValue(yellow, yellow)
    )
}
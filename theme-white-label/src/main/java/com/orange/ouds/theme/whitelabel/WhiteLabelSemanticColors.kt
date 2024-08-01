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

import com.orange.ouds.theme.tokens.semantic.OudsSemanticColorValue
import com.orange.ouds.theme.tokens.semantic.OudsSemanticColors

val WhiteLabelSemanticColors = with(WhiteLabelRawColors) {
    OudsSemanticColors(
        primary = OudsSemanticColorValue(primary40, primary80),
        onPrimary = OudsSemanticColorValue(white, primary20),
        primaryContainer = OudsSemanticColorValue(primary90, primary30),
        onPrimaryContainer = OudsSemanticColorValue(white, white),
        inversePrimary = OudsSemanticColorValue(primary80, primary40),

        secondary = OudsSemanticColorValue(secondary40, secondary80),
        onSecondary = OudsSemanticColorValue(white, secondary20),
        secondaryContainer = OudsSemanticColorValue(secondary90, secondary30),
        onSecondaryContainer = OudsSemanticColorValue(secondary10, secondary90),

        tertiary = OudsSemanticColorValue(tertiary40, tertiary80),
        onTertiary = OudsSemanticColorValue(white, tertiary20),
        tertiaryContainer = OudsSemanticColorValue(tertiary90, tertiary30),
        onTertiaryContainer = OudsSemanticColorValue(tertiary10, tertiary90),
        background = OudsSemanticColorValue(white, black),
        onBackground = OudsSemanticColorValue(black, white),
        surface = OudsSemanticColorValue(neutral98, neutral6),
        onSurface = OudsSemanticColorValue(black, neutral90),
        surfaceVariant = OudsSemanticColorValue(neutral94, neutral24),
        onSurfaceVariant = OudsSemanticColorValue(black, neutralVariant90),
        surfaceTint = OudsSemanticColorValue(white, neutral98),
        inverseSurface = OudsSemanticColorValue(neutral20, neutral90),
        inverseOnSurface = OudsSemanticColorValue(neutral95, neutral20),
        error = OudsSemanticColorValue(error40, error80),
        onError = OudsSemanticColorValue(white, error20),
        errorContainer = OudsSemanticColorValue(error90, error30),
        onErrorContainer = OudsSemanticColorValue(white, error90),
        outline = OudsSemanticColorValue(neutralVariant50, neutralVariant60),
        outlineVariant = OudsSemanticColorValue(neutralVariant80, neutralVariant30),
        scrim = OudsSemanticColorValue(black, black),
        positive = OudsSemanticColorValue(green, green),
        onPositive = OudsSemanticColorValue(white, white),
        negative = OudsSemanticColorValue(red, red),
        onNegative = OudsSemanticColorValue(white, white),
        info = OudsSemanticColorValue(info, info),
        alert = OudsSemanticColorValue(yellow, yellow)
    )
}
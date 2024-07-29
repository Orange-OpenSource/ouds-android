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

package com.orange.ouds.theme.orange

import com.orange.ouds.theme.colors.OdsSemanticColorValue
import com.orange.ouds.theme.colors.OudsSemanticColors

internal val OrangeSemanticColors = with(OrangeRawColors) {
    OudsSemanticColors(
        primary = OdsSemanticColorValue(orange200, orange100),
        onPrimary = OdsSemanticColorValue(white100, black900),
        primaryContainer = OdsSemanticColorValue(tmpOrangeFFA14D, tmpOrangeFFA14D),
        onPrimaryContainer = OdsSemanticColorValue(black900, black900),
        inversePrimary = OdsSemanticColorValue(tmpOrangeFFB68E, tmpBrown9C4500),

        secondary = OdsSemanticColorValue(orange200, white100),
        onSecondary = OdsSemanticColorValue(black900, black900),
        secondaryContainer = OdsSemanticColorValue(tmpGrey333333, tmpGreyCCCCCC),
        onSecondaryContainer = OdsSemanticColorValue(white100, black900),

        tertiary = OdsSemanticColorValue(tmpGrey666666, tmpGreyCCCCCC),
        onTertiary = OdsSemanticColorValue(white100, black900),
        tertiaryContainer = OdsSemanticColorValue(tmpGreyCCCCCC, tmpGrey333333),
        onTertiaryContainer = OdsSemanticColorValue(black900, white100),
        background = OdsSemanticColorValue(white100, black900),
        onBackground = OdsSemanticColorValue(black900, white100),
        surface = OdsSemanticColorValue(white100, black900),
        onSurface = OdsSemanticColorValue(black900, white100),
        surfaceVariant = OdsSemanticColorValue(tmpGreyEEEEEE, tmpGrey333333),
        onSurfaceVariant = OdsSemanticColorValue(black900, tmpGreyEEEEEE),
        surfaceTint = OdsSemanticColorValue(tmpGrey999999, white100),
        inverseSurface = OdsSemanticColorValue(tmpBrown362F2C, tmpGreyEEEEEE),
        inverseOnSurface = OdsSemanticColorValue(white100, black900),
        error = OdsSemanticColorValue(negative200, tmpRedD53F15),
        onError = OdsSemanticColorValue(white100, black900),
        errorContainer = OdsSemanticColorValue(tmpRedFFDAD6, tmpRed93000A),
        onErrorContainer = OdsSemanticColorValue(tmpRed410002, tmpRedFFDAD6),
        outline = OdsSemanticColorValue(black900, tmpGreyEEEEEE),
        outlineVariant = OdsSemanticColorValue(tmpGreyEBEBEB, tmpBrown52443C),
        scrim = OdsSemanticColorValue(black900, black900),
        positive = OdsSemanticColorValue(positive200, positive100),
        onPositive = OdsSemanticColorValue(white100, black900),
        negative = OdsSemanticColorValue(negative200, negative100),
        onNegative = OdsSemanticColorValue(white100, white100),
        info = OdsSemanticColorValue(info200, info100),
        alert = OdsSemanticColorValue(alert200, alert100)
    )
}
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

import com.orange.ouds.theme.colors.OudsSemanticColorValue
import com.orange.ouds.theme.colors.OudsSemanticColors

val OrangeTheme.OrangeSemanticColors
    get() = with(OrangeRawColors) {
        OudsSemanticColors(
            primary = OudsSemanticColorValue(orange200, orange100),
            onPrimary = OudsSemanticColorValue(white100, black900),
            primaryContainer = OudsSemanticColorValue(tmpOrangeFFA14D, tmpOrangeFFA14D),
            onPrimaryContainer = OudsSemanticColorValue(black900, black900),
            inversePrimary = OudsSemanticColorValue(tmpOrangeFFB68E, tmpBrown9C4500),

            secondary = OudsSemanticColorValue(orange200, white100),
            onSecondary = OudsSemanticColorValue(black900, black900),
            secondaryContainer = OudsSemanticColorValue(tmpGrey333333, tmpGreyCCCCCC),
            onSecondaryContainer = OudsSemanticColorValue(white100, black900),

            tertiary = OudsSemanticColorValue(tmpGrey666666, tmpGreyCCCCCC),
            onTertiary = OudsSemanticColorValue(white100, black900),
            tertiaryContainer = OudsSemanticColorValue(tmpGreyCCCCCC, tmpGrey333333),
            onTertiaryContainer = OudsSemanticColorValue(black900, white100),
            background = OudsSemanticColorValue(white100, black900),
            onBackground = OudsSemanticColorValue(black900, white100),
            surface = OudsSemanticColorValue(white100, black900),
            onSurface = OudsSemanticColorValue(black900, white100),
            surfaceVariant = OudsSemanticColorValue(tmpGreyEEEEEE, tmpGrey333333),
            onSurfaceVariant = OudsSemanticColorValue(black900, tmpGreyEEEEEE),
            surfaceTint = OudsSemanticColorValue(tmpGrey999999, white100),
            inverseSurface = OudsSemanticColorValue(tmpBrown362F2C, tmpGreyEEEEEE),
            inverseOnSurface = OudsSemanticColorValue(white100, black900),
            error = OudsSemanticColorValue(negative200, tmpRedD53F15),
            onError = OudsSemanticColorValue(white100, black900),
            errorContainer = OudsSemanticColorValue(tmpRedFFDAD6, tmpRed93000A),
            onErrorContainer = OudsSemanticColorValue(tmpRed410002, tmpRedFFDAD6),
            outline = OudsSemanticColorValue(black900, tmpGreyEEEEEE),
            outlineVariant = OudsSemanticColorValue(tmpGreyEBEBEB, tmpBrown52443C),
            scrim = OudsSemanticColorValue(black900, black900),
            positive = OudsSemanticColorValue(positive200, positive100),
            onPositive = OudsSemanticColorValue(white100, black900),
            negative = OudsSemanticColorValue(negative200, negative100),
            onNegative = OudsSemanticColorValue(white100, white100),
            info = OudsSemanticColorValue(info200, info100),
            alert = OudsSemanticColorValue(alert200, alert100)
        )
    }
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

package com.orange.ouds.theme.orange.tokens.semantic

import com.orange.ouds.theme.orange.OrangeTheme
import com.orange.ouds.theme.orange.tokens.raw.OrangeRawColors
import com.orange.ouds.theme.tokens.semantic.OudsColorTokenValue
import com.orange.ouds.theme.tokens.semantic.OudsColorTokens

val OrangeTheme.orangeSemanticColors
    get() = with(OrangeRawColors) {
        OudsColorTokens(
            primary = OudsColorTokenValue(orange200, orange100),
            onPrimary = OudsColorTokenValue(white100, black900),
            primaryContainer = OudsColorTokenValue(tmpOrangeFFA14D, tmpOrangeFFA14D),
            onPrimaryContainer = OudsColorTokenValue(black900, black900),
            inversePrimary = OudsColorTokenValue(tmpOrangeFFB68E, tmpBrown9C4500),

            secondary = OudsColorTokenValue(orange200, white100),
            onSecondary = OudsColorTokenValue(black900, black900),
            secondaryContainer = OudsColorTokenValue(tmpGrey333333, tmpGreyCCCCCC),
            onSecondaryContainer = OudsColorTokenValue(white100, black900),

            tertiary = OudsColorTokenValue(tmpGrey666666, tmpGreyCCCCCC),
            onTertiary = OudsColorTokenValue(white100, black900),
            tertiaryContainer = OudsColorTokenValue(tmpGreyCCCCCC, tmpGrey333333),
            onTertiaryContainer = OudsColorTokenValue(black900, white100),
            background = OudsColorTokenValue(white100, black900),
            onBackground = OudsColorTokenValue(black900, white100),
            surface = OudsColorTokenValue(white100, black900),
            onSurface = OudsColorTokenValue(black900, white100),
            surfaceVariant = OudsColorTokenValue(tmpGreyEEEEEE, tmpGrey333333),
            onSurfaceVariant = OudsColorTokenValue(black900, tmpGreyEEEEEE),
            surfaceTint = OudsColorTokenValue(tmpGrey999999, white100),
            inverseSurface = OudsColorTokenValue(tmpBrown362F2C, tmpGreyEEEEEE),
            inverseOnSurface = OudsColorTokenValue(white100, black900),
            error = OudsColorTokenValue(negative200, tmpRedD53F15),
            onError = OudsColorTokenValue(white100, black900),
            errorContainer = OudsColorTokenValue(tmpRedFFDAD6, tmpRed93000A),
            onErrorContainer = OudsColorTokenValue(tmpRed410002, tmpRedFFDAD6),
            outline = OudsColorTokenValue(black900, tmpGreyEEEEEE),
            outlineVariant = OudsColorTokenValue(tmpGreyEBEBEB, tmpBrown52443C),
            scrim = OudsColorTokenValue(black900, black900),
            positive = OudsColorTokenValue(positive200, positive100),
            onPositive = OudsColorTokenValue(white100, black900),
            negative = OudsColorTokenValue(negative200, negative100),
            onNegative = OudsColorTokenValue(white100, white100),
            info = OudsColorTokenValue(info200, info100),
            alert = OudsColorTokenValue(alert200, alert100)
        )
    }
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

package com.orange.ouds.theme

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import com.orange.ouds.theme.tokens.semantic.OudsSemanticColorToken

class OudsColors(
    val primary: Color,
    val onPrimary: Color,
    val primaryContainer: Color,
    val onPrimaryContainer: Color,
    val inversePrimary: Color,
    val secondary: Color,
    val onSecondary: Color,
    val secondaryContainer: Color,
    val onSecondaryContainer: Color,
    val tertiary: Color,
    val onTertiary: Color,
    val tertiaryContainer: Color,
    val onTertiaryContainer: Color,
    val background: Color,
    val onBackground: Color,
    val surface: Color,
    val onSurface: Color,
    val surfaceVariant: Color,
    val onSurfaceVariant: Color,
    val surfaceTint: Color,
    val inverseSurface: Color,
    val inverseOnSurface: Color,
    val error: Color,
    val onError: Color,
    val errorContainer: Color,
    val onErrorContainer: Color,
    val outline: Color,
    val outlineVariant: Color,
    val scrim: Color,
    val positive: Color,
    val onPositive: Color,
    val negative: Color,
    val onNegative: Color,
    val info: Color,
    val alert: Color,
)

@Stable
fun OudsColors.fromToken(value: OudsSemanticColorToken): Color {
    return when (value) {
        OudsSemanticColorToken.Background -> background
        OudsSemanticColorToken.Error -> error
        OudsSemanticColorToken.ErrorContainer -> errorContainer
        OudsSemanticColorToken.InverseOnSurface -> inverseOnSurface
        OudsSemanticColorToken.InversePrimary -> inversePrimary
        OudsSemanticColorToken.InverseSurface -> inverseSurface
        OudsSemanticColorToken.OnBackground -> onBackground
        OudsSemanticColorToken.OnError -> onError
        OudsSemanticColorToken.OnErrorContainer -> onErrorContainer
        OudsSemanticColorToken.OnPrimary -> onPrimary
        OudsSemanticColorToken.OnPrimaryContainer -> onPrimaryContainer
        OudsSemanticColorToken.OnSecondary -> onSecondary
        OudsSemanticColorToken.OnSecondaryContainer -> onSecondaryContainer
        OudsSemanticColorToken.OnSurface -> onSurface
        OudsSemanticColorToken.OnSurfaceVariant -> onSurfaceVariant
        OudsSemanticColorToken.SurfaceTint -> surfaceTint
        OudsSemanticColorToken.OnTertiary -> onTertiary
        OudsSemanticColorToken.OnTertiaryContainer -> onTertiaryContainer
        OudsSemanticColorToken.Outline -> outline
        OudsSemanticColorToken.OutlineVariant -> outlineVariant
        OudsSemanticColorToken.Primary -> primary
        OudsSemanticColorToken.PrimaryContainer -> primaryContainer
        OudsSemanticColorToken.Scrim -> scrim
        OudsSemanticColorToken.Secondary -> secondary
        OudsSemanticColorToken.SecondaryContainer -> secondaryContainer
        OudsSemanticColorToken.Surface -> surface
        OudsSemanticColorToken.SurfaceVariant -> surfaceVariant
        OudsSemanticColorToken.Tertiary -> tertiary
        OudsSemanticColorToken.TertiaryContainer -> tertiaryContainer
        else -> Color.Unspecified
    }
}
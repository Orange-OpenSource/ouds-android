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
import com.orange.ouds.theme.tokens.semantic.OudsColorToken

class OudsColorScheme(
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
fun OudsColorScheme.fromToken(value: OudsColorToken): Color {
    return when (value) {
        OudsColorToken.Background -> background
        OudsColorToken.Error -> error
        OudsColorToken.ErrorContainer -> errorContainer
        OudsColorToken.InverseOnSurface -> inverseOnSurface
        OudsColorToken.InversePrimary -> inversePrimary
        OudsColorToken.InverseSurface -> inverseSurface
        OudsColorToken.OnBackground -> onBackground
        OudsColorToken.OnError -> onError
        OudsColorToken.OnErrorContainer -> onErrorContainer
        OudsColorToken.OnPrimary -> onPrimary
        OudsColorToken.OnPrimaryContainer -> onPrimaryContainer
        OudsColorToken.OnSecondary -> onSecondary
        OudsColorToken.OnSecondaryContainer -> onSecondaryContainer
        OudsColorToken.OnSurface -> onSurface
        OudsColorToken.OnSurfaceVariant -> onSurfaceVariant
        OudsColorToken.SurfaceTint -> surfaceTint
        OudsColorToken.OnTertiary -> onTertiary
        OudsColorToken.OnTertiaryContainer -> onTertiaryContainer
        OudsColorToken.Outline -> outline
        OudsColorToken.OutlineVariant -> outlineVariant
        OudsColorToken.Primary -> primary
        OudsColorToken.PrimaryContainer -> primaryContainer
        OudsColorToken.Scrim -> scrim
        OudsColorToken.Secondary -> secondary
        OudsColorToken.SecondaryContainer -> secondaryContainer
        OudsColorToken.Surface -> surface
        OudsColorToken.SurfaceVariant -> surfaceVariant
        OudsColorToken.Tertiary -> tertiary
        OudsColorToken.TertiaryContainer -> tertiaryContainer
    }
}
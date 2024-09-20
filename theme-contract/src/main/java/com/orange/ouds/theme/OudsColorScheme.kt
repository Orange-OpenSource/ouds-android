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
import com.orange.ouds.theme.tokens.semantic.OudsColorKeyToken

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
fun OudsColorScheme.fromToken(value: OudsColorKeyToken): Color {
    return when (value) {
        OudsColorKeyToken.Background -> background
        OudsColorKeyToken.Error -> error
        OudsColorKeyToken.ErrorContainer -> errorContainer
        OudsColorKeyToken.InverseOnSurface -> inverseOnSurface
        OudsColorKeyToken.InversePrimary -> inversePrimary
        OudsColorKeyToken.InverseSurface -> inverseSurface
        OudsColorKeyToken.OnBackground -> onBackground
        OudsColorKeyToken.OnError -> onError
        OudsColorKeyToken.OnErrorContainer -> onErrorContainer
        OudsColorKeyToken.OnPrimary -> onPrimary
        OudsColorKeyToken.OnPrimaryContainer -> onPrimaryContainer
        OudsColorKeyToken.OnSecondary -> onSecondary
        OudsColorKeyToken.OnSecondaryContainer -> onSecondaryContainer
        OudsColorKeyToken.OnSurface -> onSurface
        OudsColorKeyToken.OnSurfaceVariant -> onSurfaceVariant
        OudsColorKeyToken.SurfaceTint -> surfaceTint
        OudsColorKeyToken.OnTertiary -> onTertiary
        OudsColorKeyToken.OnTertiaryContainer -> onTertiaryContainer
        OudsColorKeyToken.Outline -> outline
        OudsColorKeyToken.OutlineVariant -> outlineVariant
        OudsColorKeyToken.Primary -> primary
        OudsColorKeyToken.PrimaryContainer -> primaryContainer
        OudsColorKeyToken.Scrim -> scrim
        OudsColorKeyToken.Secondary -> secondary
        OudsColorKeyToken.SecondaryContainer -> secondaryContainer
        OudsColorKeyToken.Surface -> surface
        OudsColorKeyToken.SurfaceVariant -> surfaceVariant
        OudsColorKeyToken.Tertiary -> tertiary
        OudsColorKeyToken.TertiaryContainer -> tertiaryContainer
    }
}
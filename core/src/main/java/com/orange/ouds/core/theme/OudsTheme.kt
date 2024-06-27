/*
 * Software Name: Orange Unified Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
 */

package com.orange.ouds.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * OUDS theme is the theme to apply to your screens in an Orange Jetpack Compose application.
 *
 * @param darkThemeEnabled Indicates whether the dark theme is enabled or not.
 * @param content The content of the theme.
 */
@Composable
fun OudsTheme(
    darkThemeEnabled: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(colorScheme = if (darkThemeEnabled) darkColorScheme else lightColorScheme) {
        content()
    }
}

private val lightColorScheme = lightColorScheme(
    primary = Palette.orange200,
    onPrimary = Palette.white100,
    primaryContainer = Palette.orange70,
    onPrimaryContainer = Palette.black900,
    inversePrimary = Palette.orange50,
    secondary = Palette.orange200,
    onSecondary = Palette.black900,
    secondaryContainer = Palette.grey900,
    onSecondaryContainer = Palette.white100,
    tertiary = Palette.grey700,
    onTertiary = Palette.white100,
    tertiaryContainer = Palette.grey300,
    onTertiaryContainer = Palette.black900,
    background = Palette.white100,
    onBackground = Palette.black900,
    surface = Palette.white100,
    onSurface = Palette.black900,
    surfaceVariant = Palette.grey100,
    onSurfaceVariant = Palette.black900,
    surfaceTint = Palette.grey500,
    inverseSurface = Palette.brown900,
    inverseOnSurface = Palette.white100,
    error = Palette.red500,
    onError = Palette.white100,
    errorContainer = Palette.red100,
    onErrorContainer = Palette.red900,
    outline = Palette.grey100,
    outlineVariant = Palette.grey200,
    scrim = Palette.black900,
)

private val darkColorScheme = darkColorScheme(
    primary = Palette.orange100,
    onPrimary = Palette.black900,
    primaryContainer = Palette.orange70,
    onPrimaryContainer = Palette.black900,
    inversePrimary = Palette.brown600,
    secondary = Palette.white100,
    onSecondary = Palette.black900,
    secondaryContainer = Palette.grey300,
    onSecondaryContainer = Palette.black900,
    tertiary = Palette.grey300,
    onTertiary = Palette.black900,
    tertiaryContainer = Palette.grey900,
    onTertiaryContainer = Palette.white100,
    background = Palette.black900,
    onBackground = Palette.white100,
    surface = Palette.black900,
    onSurface = Palette.white100,
    surfaceVariant = Palette.grey900,
    onSurfaceVariant = Palette.grey100,
    surfaceTint = Palette.white100,
    inverseSurface = Palette.grey100,
    inverseOnSurface = Palette.black900,
    error = Palette.red400,
    onError = Palette.black900,
    errorContainer = Palette.red800,
    onErrorContainer = Palette.red100,
    outline = Palette.grey100,
    outlineVariant = Palette.brown800,
    scrim = Palette.black900,
)

private object Palette {
    val white100 = Color(0xffffffff)
    val black900 = Color(0xff000000)

    val orange50 = Color(0xffffb68e)
    val orange70 = Color(0xffffa14d)
    val orange100 = Color(0xffff7900)
    val orange200 = Color(0xfff16e00)

    val brown600 = Color(0xff9C4500)
    val brown800 = Color(0xff52443C)
    val brown900 = Color(0xff362f2c)

    val grey100 = Color(0xffeeeeee)
    val grey200 = Color(0xffebebeb)
    val grey300 = Color(0xffcccccc)
    val grey500 = Color(0xff999999)
    val grey700 = Color(0xff666666)
    val grey900 = Color(0xff333333)

    val red100 = Color(0xffffdad6)
    val red400 = Color(0xffd53f15)
    val red500 = Color(0xffCD3C14)
    val red800 = Color(0xff93000a)
    val red900 = Color(0xff410002)
}
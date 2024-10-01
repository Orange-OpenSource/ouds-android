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

package com.orange.ouds.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import com.orange.ouds.theme.OudsColorScheme
import com.orange.ouds.theme.OudsThemeContract
import com.orange.ouds.theme.tokens.components.OudsComponentsTokens
import com.orange.ouds.theme.tokens.semantic.OudsBorderTokens
import com.orange.ouds.theme.tokens.semantic.OudsElevationTokens
import com.orange.ouds.theme.tokens.semantic.OudsGridTokens
import com.orange.ouds.theme.tokens.semantic.OudsOpacityTokens

private fun missingCompositionLocalError(compositionLocalName: String): Nothing =
    error("OudsTheme not found. $compositionLocalName CompositionLocal not present.")

private val LocalColorScheme = staticCompositionLocalOf<OudsColorScheme> { missingCompositionLocalError("LocalColorScheme") }
private val LocalBorderTokens = staticCompositionLocalOf<OudsBorderTokens> { missingCompositionLocalError("LocalBorderTokens") }
private val LocalElevationTokens = staticCompositionLocalOf<OudsElevationTokens> { missingCompositionLocalError("LocalElevationTokens") }
private val LocalTypography = staticCompositionLocalOf<OudsTypography> { missingCompositionLocalError("LocalTypography") }
private val LocalGridTokens = staticCompositionLocalOf<OudsGridTokens> { missingCompositionLocalError("LocalGridTokens") }
private val LocalOpacityTokens = staticCompositionLocalOf<OudsOpacityTokens> { missingCompositionLocalError("LocalOpacityTokens") }
private val LocalComponentsTokens = staticCompositionLocalOf<OudsComponentsTokens> { missingCompositionLocalError("LocalComponentsTokens") }

object OudsTheme {
    val colorScheme: OudsColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColorScheme.current

    val borderTokens: OudsBorderTokens
        @Composable
        @ReadOnlyComposable
        get() = LocalBorderTokens.current

    val elevationTokens: OudsElevationTokens
        @Composable
        @ReadOnlyComposable
        get() = LocalElevationTokens.current

    val typography: OudsTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val gridTokens: OudsGridTokens
        @Composable
        @ReadOnlyComposable
        get() = LocalGridTokens.current

    val opacityTokens: OudsOpacityTokens
        @Composable
        @ReadOnlyComposable
        get() = LocalOpacityTokens.current

    val componentsTokens: OudsComponentsTokens
        @Composable
        @ReadOnlyComposable
        get() = LocalComponentsTokens.current
}

/**
 * OUDS theme is the theme to apply to your screens in an Orange Jetpack Compose application.
 *
 * @param themeContract Theme contract which contain the configuration of the OudsTheme: colors, typography...
 * @param darkThemeEnabled Indicates whether the dark theme is enabled or not.
 * @param content Theme nested content. OudsTheme will be applied to this content.
 */
@Composable
fun OudsTheme(
    themeContract: OudsThemeContract,
    darkThemeEnabled: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colorScheme = if (darkThemeEnabled) themeContract.colorTokens.darkColorScheme else themeContract.colorTokens.lightColorScheme

    CompositionLocalProvider(
        LocalColorScheme provides colorScheme,
        LocalBorderTokens provides themeContract.borderTokens,
        LocalElevationTokens provides themeContract.elevationTokens,
        LocalTypography provides themeContract.fontTokens.getTypography(),
        LocalGridTokens provides themeContract.gridTokens,
        LocalOpacityTokens provides themeContract.opacityTokens,
        LocalComponentsTokens provides themeContract.componentsTokens
    ) {
        MaterialTheme(colorScheme = if (darkThemeEnabled) themeContract.colorTokens.materialDarkColorScheme else themeContract.colorTokens.materialLightColorScheme) {
            content()
        }
    }
}
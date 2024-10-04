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

private fun missingCompositionLocalError(compositionLocalName: String): Nothing =
    error("OudsTheme not found. $compositionLocalName CompositionLocal not present.")

private val LocalColorScheme = staticCompositionLocalOf<OudsColorScheme> { missingCompositionLocalError("LocalColorScheme") }
private val LocalBorders = staticCompositionLocalOf<OudsBorders> { missingCompositionLocalError("LocalBorders") }
private val LocalElevations = staticCompositionLocalOf<OudsElevations> { missingCompositionLocalError("LocalElevations") }
private val LocalTypography = staticCompositionLocalOf<OudsTypography> { missingCompositionLocalError("LocalTypography") }
private val LocalGrids = staticCompositionLocalOf<OudsGrids> { missingCompositionLocalError("LocalGrids") }
private val LocalOpacities = staticCompositionLocalOf<OudsOpacities> { missingCompositionLocalError("LocalOpacities") }
private val LocalComponentsTokens = staticCompositionLocalOf<OudsComponentsTokens> { missingCompositionLocalError("LocalComponentsTokens") }

object OudsTheme {
    val colorScheme: OudsColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColorScheme.current

    val borders: OudsBorders
        @Composable
        @ReadOnlyComposable
        get() = LocalBorders.current

    val elevations: OudsElevations
        @Composable
        @ReadOnlyComposable
        get() = LocalElevations.current

    val typography: OudsTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val grids: OudsGrids
        @Composable
        @ReadOnlyComposable
        get() = LocalGrids.current

    val opacities: OudsOpacities
        @Composable
        @ReadOnlyComposable
        get() = LocalOpacities.current

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
        LocalBorders provides themeContract.borderTokens.getBorders(),
        LocalElevations provides themeContract.elevationTokens.getElevation(),
        LocalTypography provides themeContract.fontTokens.getTypography(themeContract.fontFamily),
        LocalGrids provides themeContract.gridTokens.getGrids(),
        LocalOpacities provides themeContract.opacityTokens.getOpacity(),
        LocalComponentsTokens provides themeContract.componentsTokens
    ) {
        MaterialTheme(colorScheme = if (darkThemeEnabled) themeContract.colorTokens.materialDarkColorScheme else themeContract.colorTokens.materialLightColorScheme) {
            content()
        }
    }
}
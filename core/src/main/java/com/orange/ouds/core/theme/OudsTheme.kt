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
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import com.orange.ouds.theme.OudsThemeContract
import com.orange.ouds.theme.tokens.components.OudsComponentsTokens

private fun missingCompositionLocalError(compositionLocalName: String): Nothing =
    error("OudsTheme not found. $compositionLocalName CompositionLocal not present.")

private val LocalDarkThemeEnabled = staticCompositionLocalOf<Boolean> { missingCompositionLocalError("LocalDarkThemeEnabled") }
private val LocalColorScheme = staticCompositionLocalOf<OudsColorScheme> { missingCompositionLocalError("LocalColorScheme") }
private val LocalLightColorScheme = compositionLocalOf<OudsColorScheme> { missingCompositionLocalError("LocalLightColorScheme") }
private val LocalDarkColorScheme = compositionLocalOf<OudsColorScheme> { missingCompositionLocalError("LocalDarkColorScheme") }
private val LocalMaterialLightColorScheme = compositionLocalOf<ColorScheme> { missingCompositionLocalError("LocalMaterialLightColorScheme") }
private val LocalMaterialDarkColorScheme = compositionLocalOf<ColorScheme> { missingCompositionLocalError("LocalMaterialDarkColorScheme") }
private val LocalBorders = staticCompositionLocalOf<OudsBorders> { missingCompositionLocalError("LocalBorders") }
private val LocalElevations = staticCompositionLocalOf<OudsElevations> { missingCompositionLocalError("LocalElevations") }
private val LocalTypography = staticCompositionLocalOf<OudsTypography> { missingCompositionLocalError("LocalTypography") }
private val LocalGrids = staticCompositionLocalOf<OudsGrids> { missingCompositionLocalError("LocalGrids") }
private val LocalOpacities = staticCompositionLocalOf<OudsOpacities> { missingCompositionLocalError("LocalOpacities") }
private val LocalSizes = staticCompositionLocalOf<OudsSizes> { missingCompositionLocalError("LocalSizes") }
private val LocalSpaces = staticCompositionLocalOf<OudsSpaces> { missingCompositionLocalError("LocalSpaces") }
private val LocalComponentsTokens = staticCompositionLocalOf<OudsComponentsTokens> { missingCompositionLocalError("LocalComponentsTokens") }
internal val LocalColoredBox = staticCompositionLocalOf<Boolean> { false }

object OudsTheme {

    /**
     * Tweak of the current [OudsTheme] which can be passed to [OudsThemeTweak] composable:
     *   - Invert set theme in dark when app is in light or in light when app is in dark
     *   - ForceDark and ForceLight force the theme to be in dark or in light
     */
    enum class Tweak {
        Invert, ForceDark, ForceLight
    }

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

    val sizes: OudsSizes
        @Composable
        @ReadOnlyComposable
        get() = LocalSizes.current

    val spaces: OudsSpaces
        @Composable
        @ReadOnlyComposable
        get() = LocalSpaces.current

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
    val materialColorScheme = if (darkThemeEnabled) materialDarkColorScheme else materialLightColorScheme

    CompositionLocalProvider(
        LocalDarkThemeEnabled provides darkThemeEnabled,
        LocalColorScheme provides colorScheme,
        LocalLightColorScheme provides themeContract.colorTokens.lightColorScheme,
        LocalDarkColorScheme provides themeContract.colorTokens.darkColorScheme,
        LocalMaterialLightColorScheme provides materialLightColorScheme,
        LocalMaterialDarkColorScheme provides materialDarkColorScheme,
        LocalBorders provides themeContract.borderTokens.getBorders(),
        LocalElevations provides themeContract.elevationTokens.getElevation(),
        LocalTypography provides themeContract.fontTokens.getTypography(themeContract.fontFamily),
        LocalGrids provides themeContract.gridTokens.getGrids(),
        LocalOpacities provides themeContract.opacityTokens.getOpacity(),
        LocalSizes provides themeContract.sizeTokens.getSizes(),
        LocalSpaces provides themeContract.spaceTokens.getSpaces(),
        LocalComponentsTokens provides themeContract.componentsTokens,
    ) {
        MaterialTheme(
            colorScheme = materialColorScheme,
            content = content
        )
    }
}

/**
 * Tweaks the current OUDS theme and displays given [content] according to the selected [tweak].
 * Note: This composable is directly related to [OudsTheme] and MUST be used inside it.
 */
@Composable
fun OudsThemeTweak(tweak: OudsTheme.Tweak, content: @Composable () -> Unit) {
    val tweakedToDark = when (tweak) {
        OudsTheme.Tweak.Invert -> !LocalDarkThemeEnabled.current
        OudsTheme.Tweak.ForceDark -> true
        OudsTheme.Tweak.ForceLight -> false
    }
    val colors = if (tweakedToDark) LocalDarkColorScheme.current else LocalLightColorScheme.current
    val materialColorScheme = if (tweakedToDark) LocalMaterialDarkColorScheme.current else LocalMaterialLightColorScheme.current

    CompositionLocalProvider(
        LocalDarkThemeEnabled provides tweakedToDark,
        LocalColorScheme provides colors
    ) {
        MaterialTheme(
            colorScheme = materialColorScheme,
            content = content
        )
    }
}

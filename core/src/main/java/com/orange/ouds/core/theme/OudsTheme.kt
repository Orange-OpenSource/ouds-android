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
import androidx.compose.ui.platform.LocalContext
import com.orange.ouds.core.extensions.isHighContrastModeEnabled
import com.orange.ouds.theme.OudsDrawableResources
import com.orange.ouds.theme.OudsThemeContract
import com.orange.ouds.theme.OudsThemeSettings
import com.orange.ouds.theme.tokens.components.OudsComponentsTokens
import com.orange.ouds.theme.tokens.material.OudsMaterialColorTokens

private fun missingCompositionLocalError(compositionLocalName: String): Nothing =
    error("OudsTheme not found. $compositionLocalName CompositionLocal not present.")

internal val LocalDarkThemeEnabled = staticCompositionLocalOf<Boolean> { missingCompositionLocalError("LocalDarkThemeEnabled") }
internal val LocalHighContrastModeEnabled = staticCompositionLocalOf<Boolean> { missingCompositionLocalError("LocalHighContrastModeEnabled") }
internal val LocalColorScheme = staticCompositionLocalOf<OudsColorScheme> { missingCompositionLocalError("LocalColorScheme") }
internal val LocalLightColorScheme = compositionLocalOf<OudsColorScheme> { missingCompositionLocalError("LocalLightColorScheme") }
internal val LocalDarkColorScheme = compositionLocalOf<OudsColorScheme> { missingCompositionLocalError("LocalDarkColorScheme") }
internal val LocalMaterialLightColorScheme = compositionLocalOf<ColorScheme> { missingCompositionLocalError("LocalMaterialLightColorScheme") }
internal val LocalMaterialDarkColorScheme = compositionLocalOf<ColorScheme> { missingCompositionLocalError("LocalMaterialDarkColorScheme") }
internal val LocalBorders = staticCompositionLocalOf<OudsBorders> { missingCompositionLocalError("LocalBorders") }
internal val LocalElevations = staticCompositionLocalOf<OudsElevations> { missingCompositionLocalError("LocalElevations") }
internal val LocalTypography = staticCompositionLocalOf<OudsTypography> { missingCompositionLocalError("LocalTypography") }
internal val LocalGrids = staticCompositionLocalOf<OudsGrids> { missingCompositionLocalError("LocalGrids") }
internal val LocalOpacities = staticCompositionLocalOf<OudsOpacities> { missingCompositionLocalError("LocalOpacities") }
internal val LocalSizes = staticCompositionLocalOf<OudsSizes> { missingCompositionLocalError("LocalSizes") }
internal val LocalSpaces = staticCompositionLocalOf<OudsSpaces> { missingCompositionLocalError("LocalSpaces") }
internal val LocalComponentsTokens = staticCompositionLocalOf<OudsComponentsTokens> { missingCompositionLocalError("LocalComponentsTokens") }
internal val LocalColorMode = staticCompositionLocalOf<OudsColorMode?> { null }
internal val LocalDrawableResources = staticCompositionLocalOf<OudsDrawableResources> { missingCompositionLocalError("LocalDrawableResources") }
internal val LocalThemeSettings = staticCompositionLocalOf<OudsThemeSettings> { missingCompositionLocalError("LocalThemeSettings") }

/**
 * Object that stores tokens values for the current theme.
 */
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

    internal val componentsTokens: OudsComponentsTokens
        @Composable
        @ReadOnlyComposable
        get() = LocalComponentsTokens.current

    internal val drawableResources: OudsDrawableResources
        @Composable
        @ReadOnlyComposable
        get() = LocalDrawableResources.current

    internal val settings: OudsThemeSettings
        @Composable
        @ReadOnlyComposable
        get() = LocalThemeSettings.current
}

/**
 * [OudsTheme] is the theme to apply to your screens in a Jetpack Compose application. Use it at the top of
 * your application in replacement of the `MaterialTheme`.
 * Cause OUDS supports multi-theme, you should pass the OUDS supported [theme] used by your application.
 *
 * @param theme Theme to apply to your application. It must implement [OudsThemeContract] (e.g. OrangeTheme, Sosh Theme, ...)
 * @param darkThemeEnabled Indicates whether the dark theme is enabled or not.
 * @param content Theme nested content. The provided [theme] will be applied to this content.
 */
@Composable
fun OudsTheme(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    with(theme) {
        val colorScheme = if (darkThemeEnabled) colorTokens.darkColorScheme else colorTokens.lightColorScheme
        val materialColorScheme = if (darkThemeEnabled) OudsMaterialColorTokens.materialDarkColorScheme else OudsMaterialColorTokens.materialLightColorScheme
        val windowWidthSizeClass = WindowWidthSizeClass.compute(currentWindowWidth())

        CompositionLocalProvider(
            LocalDarkThemeEnabled provides darkThemeEnabled,
            LocalHighContrastModeEnabled provides LocalContext.current.isHighContrastModeEnabled(),
            LocalColorScheme provides colorScheme,
            LocalLightColorScheme provides colorTokens.lightColorScheme,
            LocalDarkColorScheme provides colorTokens.darkColorScheme,
            LocalMaterialLightColorScheme provides OudsMaterialColorTokens.materialLightColorScheme,
            LocalMaterialDarkColorScheme provides OudsMaterialColorTokens.materialDarkColorScheme,
            LocalBorders provides borderTokens.getBorders(),
            LocalElevations provides elevationTokens.getElevation(),
            LocalTypography provides fontTokens.getTypography(fontFamily, windowWidthSizeClass),
            LocalGrids provides gridTokens.getGrids(windowWidthSizeClass),
            LocalOpacities provides opacityTokens.getOpacity(),
            LocalSizes provides sizeTokens.getSizes(windowWidthSizeClass),
            LocalSpaces provides spaceTokens.getSpaces(windowWidthSizeClass),
            LocalComponentsTokens provides componentsTokens,
            LocalDrawableResources provides drawableResources,
            LocalThemeSettings provides settings
        ) {
            MaterialTheme(
                colorScheme = materialColorScheme,
                content = content
            )
        }
    }
}

/**
 * Tweaks the current OUDS theme and displays given [content] according to the selected [tweak].
 *
 * Note: This composable is directly related to [OudsTheme] and MUST be used inside it.
 *
 * @param tweak Tweak applied to the current [OudsTheme].
 * @param content Theme tweak nested content. OudsThemeTweak will be applied to this content.
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

/**
 * This function is equivalent to [isSystemInDarkTheme] except it takes the OUDS theme setting into account instead of the system one.
 *
 * The OUDS theme can be inverted or forced to light or dark when calling [OudsThemeTweak] and the value returned by this method reflects that kind of change.
 * If there is no call to [OudsThemeTweak] anywhere in the layout hierarchy, then this function returns the same value as [isSystemInDarkTheme].
 *
 * @return `true` if OUDS is considered to be in 'dark theme'.
 */
@Composable
@ReadOnlyComposable
fun isOudsInDarkTheme(): Boolean {
    return LocalDarkThemeEnabled.current
}

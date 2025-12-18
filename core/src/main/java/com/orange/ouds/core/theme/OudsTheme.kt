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
import com.orange.ouds.core.theme.OudsTheme.Tweak.ForceDark
import com.orange.ouds.core.theme.OudsTheme.Tweak.ForceLight
import com.orange.ouds.core.theme.OudsTheme.Tweak.Invert
import com.orange.ouds.theme.OudsDrawableResources
import com.orange.ouds.theme.OudsThemeContract
import com.orange.ouds.theme.OudsThemeSettings
import com.orange.ouds.theme.tokens.components.OudsComponentsTokens

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
internal val LocalEffects = staticCompositionLocalOf<OudsEffects> { missingCompositionLocalError("LocalEffects") }
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
internal val LocalComponents = staticCompositionLocalOf<OudsComponents> { missingCompositionLocalError("LocalComponents") }

/**
 * Object that stores tokens values for the current theme.
 */
object OudsTheme {

    /**
     * Defines adjustments that can be applied to the current [OudsTheme] via the [OudsThemeTweak] composable.
     *
     * @property Invert Inverts the current theme (switches from Light to Dark or Dark to Light).
     * @property ForceDark Forces the theme to Dark mode, regardless of the system setting.
     * @property ForceLight Forces the theme to Light mode, regardless of the system setting.
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

    val effects: OudsEffects
        @Composable
        @ReadOnlyComposable
        get() = LocalEffects.current

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

    val components: OudsComponents
        @Composable
        @ReadOnlyComposable
        get() = LocalComponents.current

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
 * Applies the OUDS theme to the composable hierarchy.
 *
 * @param theme Theme to apply to your application. It must implement [OudsThemeContract] (e.g., OrangeTheme, SoshTheme, ...)
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
        val materialColorScheme = if (darkThemeEnabled) materialColorTokens.materialDarkColorScheme else materialColorTokens.materialLightColorScheme
        val windowWidthSizeClass = WindowWidthSizeClass.compute(currentWindowWidth())

        CompositionLocalProvider(
            LocalDarkThemeEnabled provides darkThemeEnabled,
            LocalHighContrastModeEnabled provides LocalContext.current.isHighContrastModeEnabled(),
            LocalColorScheme provides colorScheme,
            LocalLightColorScheme provides colorTokens.lightColorScheme,
            LocalDarkColorScheme provides colorTokens.darkColorScheme,
            LocalMaterialLightColorScheme provides materialColorTokens.materialLightColorScheme,
            LocalMaterialDarkColorScheme provides materialColorTokens.materialDarkColorScheme,
            LocalBorders provides borderTokens.getBorders(),
            LocalEffects provides effectTokens.getEffects(),
            LocalElevations provides elevationTokens.getElevations(),
            LocalTypography provides fontTokens.getTypography(fontFamily, windowWidthSizeClass),
            LocalGrids provides gridTokens.getGrids(windowWidthSizeClass),
            LocalOpacities provides opacityTokens.getOpacities(),
            LocalSizes provides sizeTokens.getSizes(windowWidthSizeClass),
            LocalSpaces provides spaceTokens.getSpaces(windowWidthSizeClass),
            LocalComponentsTokens provides componentsTokens,
            LocalDrawableResources provides drawableResources,
            LocalThemeSettings provides settings,
            LocalComponents provides componentsTokens.getComponents()
        ) {
            MaterialTheme(
                colorScheme = materialColorScheme,
                content = content
            )
        }
    }
}

/**
 * Modifies the current OUDS theme configuration and applies it to the given [content].
 *
 * Note: This composable relies on [OudsTheme] and must be nested within it.
 *
 * @param tweak The specific adjustment to apply to the current [OudsTheme] (e.g., forcing dark mode).
 * @param content The content to which the tweaked theme will be applied.
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
 * Determines if the OUDS theme is currently in dark mode.
 *
 * This function is equivalent to [isSystemInDarkTheme], but it respects overrides applied by [OudsThemeTweak].
 *
 * The OUDS theme can be inverted or forced to light/dark modes using [OudsThemeTweak].
 * The value returned by this function reflects these effective changes.
 *
 * If [OudsThemeTweak] is not used in the layout hierarchy, this function returns the value of the `darkThemeEnabled` parameter of the root `OudsTheme` method.
 *
 * @return `true` if the effective OUDS theme is dark, `false` otherwise.
 */
@Composable
@ReadOnlyComposable
fun isOudsInDarkTheme(): Boolean {
    return LocalDarkThemeEnabled.current
}

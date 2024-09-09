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
import com.orange.ouds.theme.tokens.semantic.OudsOpacityTokens

private fun themeError(message: Any): Nothing = error("OudsTheme not found. $message")

private val LocalColorScheme = staticCompositionLocalOf<OudsColorScheme> { themeError("LocalColorScheme CompositionLocal not present") }
private val LocalOpacityTokens = staticCompositionLocalOf<OudsOpacityTokens> { themeError("LocalOpacityTokens CompositionLocal not present") }
private val LocalComponentsTokens = staticCompositionLocalOf<OudsComponentsTokens> { themeError("LocalComponentsTokens CompositionLocal not present") }

object OudsTheme {
    val colorScheme: OudsColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColorScheme.current

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
        LocalOpacityTokens provides themeContract.opacityTokens,
        LocalComponentsTokens provides themeContract.componentsTokens
    ) {
        MaterialTheme(colorScheme = if (darkThemeEnabled) themeContract.colorTokens.materialDarkColorScheme else themeContract.colorTokens.materialLightColorScheme) {
            content()
        }
    }
}
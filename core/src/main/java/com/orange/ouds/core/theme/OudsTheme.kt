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
import com.orange.ouds.theme.OudsColors
import com.orange.ouds.theme.OudsThemeContract
import com.orange.ouds.theme.tokens.components.OudsComponentsTokens

private fun themeError(message: Any): Nothing = error("OudsTheme not found. $message")

private val LocalColors = staticCompositionLocalOf<OudsColors> { themeError("LocalColors CompositionLocal not present") }
private val LocalComponentsTokens = staticCompositionLocalOf { OudsComponentsTokens() }

object OudsTheme {
    val colors: OudsColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

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

    val colors = if (darkThemeEnabled) themeContract.colorTokens.darkColors else themeContract.colorTokens.lightColors

    CompositionLocalProvider(
        LocalColors provides colors,
        LocalComponentsTokens provides themeContract.componentsTokens
    ) {
        MaterialTheme(colorScheme = if (darkThemeEnabled) themeContract.colorTokens.materialDarkColorScheme else themeContract.colorTokens.materialLightColorScheme) {
            content()
        }
    }
}
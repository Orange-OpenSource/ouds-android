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

package com.orange.ouds.core.utilities

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import com.orange.ouds.core.BuildConfig
import com.orange.ouds.core.extensions.isNightModeEnabled
import com.orange.ouds.core.theme.OudsTheme

/**
 * Configures the Compose OUDS preview environment in Android Studio.
 *
 * @param modifier The modifier for the preview content.
 * @param darkThemeEnabled Indicates whether the dark theme is enabled or not.
 * @param content The content of the preview.
 *
 * @suppress
 */
@Composable
fun OudsPreview(modifier: Modifier = Modifier, darkThemeEnabled: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    // Updating the configuration is only needed for UI tests
    // It is not needed for Android Studio previews because the uiMode parameter of the @Preview annotation already configures the UI mode properly
    val configuration = LocalConfiguration.current.apply {
        isNightModeEnabled = darkThemeEnabled
    }
    CompositionLocalProvider(value = LocalConfiguration provides configuration) {
        OudsTheme(themeContract = BuildConfig.PREVIEW_THEME, darkThemeEnabled) {
            // Add a box to be able to see components
            // Use a box instead of a surface to avoid clipping children in cases where something is drawn outside of the component to preview
            Box(
                modifier = Modifier
                    .background(OudsTheme.colorScheme.background.primary)
                    .then(modifier)
            ) {
                content()
            }
        }
    }
}
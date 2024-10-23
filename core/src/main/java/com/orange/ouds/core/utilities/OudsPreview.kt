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

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.orange.ouds.core.BuildConfig
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.value
import com.orange.ouds.theme.tokens.OudsColorKeyToken

/**
 * Configures the Compose OUDS preview environment in Android Studio.
 *
 * @param darkThemeEnabled Indicates whether the dark theme is enabled or not.
 * @param content The content of the preview.
 */
@Composable
fun OudsPreview(darkThemeEnabled: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    OudsTheme(themeContract = BuildConfig.PREVIEW_THEME, darkThemeEnabled) {
        Surface(color = OudsColorKeyToken.BgPrimary.value, content = content) // Add a surface to be able to see components
    }
}

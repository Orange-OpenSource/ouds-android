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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.orange.ouds.core.BuildConfig
import com.orange.ouds.core.extensions.isNightModeEnabled
import com.orange.ouds.core.theme.LocalHighContrastModeEnabled
import com.orange.ouds.core.theme.OudsTheme
import kotlin.enums.enumEntries

/**
 * Configures the Compose OUDS preview environment in Android Studio.
 *
 * @param modifier The modifier for the preview content.
 * @param darkThemeEnabled Indicates whether the dark theme is enabled or not.
 * @param highContrastModeEnabled Indicates whether the high contrast mode is enabled for the preview.
 * @param content The content of the preview.
 *
 * @suppress
 */
@Composable
fun OudsPreview(modifier: Modifier = Modifier, darkThemeEnabled: Boolean = isSystemInDarkTheme(), highContrastModeEnabled: Boolean = false, content: @Composable () -> Unit) {
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
                CompositionLocalProvider(LocalHighContrastModeEnabled provides highContrastModeEnabled) {
                    content()
                }
            }
        }
    }
}

@Composable
internal inline fun <reified T> PreviewStates(columnCount: Int = enumEntries<T>().count(), content: (T) -> Unit) where T : Enum<T> {
    val chunkedStates = enumEntries<T>().chunked(columnCount)
    val space = 16.dp
    Box(modifier = Modifier.padding(space)) {
        Row(horizontalArrangement = Arrangement.spacedBy(space)) {
            repeat(columnCount) { columnIndex ->
                val columnStates = chunkedStates.mapNotNull { it.getOrNull(columnIndex) }
                Column {
                    columnStates.forEachIndexed { index, state ->
                        Text(
                            modifier = Modifier.padding(top = if (index == 0) 0.dp else space, bottom = 8.dp),
                            text = state.name,
                            color = OudsTheme.colorScheme.content.default,
                            fontFamily = FontFamily.Monospace,
                            fontSize = 10.sp
                        )
                        content(state)
                    }
                }
            }
        }
    }
}

/**
 * Long text used in previews.
 */
internal const val LoremIpsumText =
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
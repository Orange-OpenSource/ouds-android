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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.orange.ouds.core.extensions.isNightModeEnabled
import com.orange.ouds.core.theme.LocalHighContrastModeEnabled
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.theme.OudsThemeContract
import com.orange.ouds.theme.OudsThemeSettings
import com.orange.ouds.theme.orange.OrangeTheme
import kotlin.enums.enumEntries

private val LocalPreviewEnumEntry = staticCompositionLocalOf<Any?> { null }

/**
 * Configures the Compose OUDS preview environment in Android Studio.
 *
 * @param modifier The modifier for the preview content.
 * @param theme The preview theme.
 * @param darkThemeEnabled Indicates whether the dark theme is enabled or not.
 * @param highContrastModeEnabled Indicates whether the high contrast mode is enabled for the preview.
 * @param content The content of the preview.
 *
 * @suppress
 */
@Composable
fun OudsPreview(
    modifier: Modifier = Modifier,
    theme: OudsThemeContract = getPreviewTheme(),
    darkThemeEnabled: Boolean = isSystemInDarkTheme(),
    highContrastModeEnabled: Boolean = false,
    content: @Composable () -> Unit
) {
    // Updating the configuration is only needed for UI tests
    // It is not needed for Android Studio previews because the uiMode parameter of the @Preview annotation already configures the UI mode properly
    val configuration = LocalConfiguration.current.apply {
        isNightModeEnabled = darkThemeEnabled
    }
    CompositionLocalProvider(value = LocalConfiguration provides configuration) {
        OudsTheme(
            theme = theme,
            darkThemeEnabled = darkThemeEnabled
        ) {
            // Override theme settings
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


internal fun OudsThemeContract.mapSettings(transform: (OudsThemeSettings) -> (OudsThemeSettings)): OudsThemeContract {
    return object : OudsThemeContract by this {
        override val settings = transform(this@mapSettings.settings)
    }
}

internal fun getPreviewTheme(): OudsThemeContract = OrangeTheme()

@Composable
internal inline fun <reified T> getPreviewEnumEntry(): T? {
    @Suppress("UNCHECKED_CAST")
    return LocalPreviewEnumEntry.current as? T
}

@Composable
internal inline fun <reified T> PreviewEnumEntries(
    columnCount: Int = enumEntries<T>().count(),
    crossinline content: @Composable (T) -> Unit
) where T : Enum<T> {
    val chunkedEnumEntries = enumEntries<T>().chunked(columnCount)
    val space = 16.dp
    Box(modifier = Modifier.padding(space)) {
        Row(horizontalArrangement = Arrangement.spacedBy(space)) {
            repeat(columnCount) { columnIndex ->
                val columnEnumEntries = chunkedEnumEntries.mapNotNull { it.getOrNull(columnIndex) }
                Column {
                    columnEnumEntries.forEachIndexed { rowIndex, enumEntry ->
                        EnumEntryName(
                            modifier = Modifier.padding(top = if (rowIndex == 0) 0.dp else space, bottom = 8.dp),
                            enumEntry = enumEntry
                        )
                        CompositionLocalProvider(LocalPreviewEnumEntry provides enumEntry) {
                            content(enumEntry)
                        }
                    }
                }
            }
        }
    }
}

@Composable
internal inline fun <reified T, reified S> PreviewEnumEntries(crossinline content: @Composable (T, S) -> Unit) where T : Enum<T>, S : Enum<S> {
    val space = 16.dp
    val columnCount = enumEntries<T>().count()
    val rowCount = enumEntries<S>().count()
    LazyVerticalGrid(
        columns = GridCells.Fixed(1 + columnCount),
        contentPadding = PaddingValues(all = space),
        horizontalArrangement = Arrangement.spacedBy(space),
        verticalArrangement = Arrangement.spacedBy(space)
    ) {
        repeat(1 + rowCount) { rowIndex ->
            repeat(1 + columnCount) { columnIndex ->
                val rowEnumEntry = enumEntries<S>().getOrNull(rowIndex - 1)
                val columnEnumEntry = enumEntries<T>().getOrNull(columnIndex - 1)
                item {
                    when {
                        rowEnumEntry == null && columnEnumEntry != null -> EnumEntryName(columnEnumEntry)
                        rowEnumEntry != null && columnEnumEntry == null -> EnumEntryName(rowEnumEntry)
                        rowEnumEntry != null && columnEnumEntry != null -> {
                            Box {
                                content(columnEnumEntry, rowEnumEntry)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun <T> EnumEntryName(enumEntry: T, modifier: Modifier = Modifier) where T : Enum<T> {
    Text(
        modifier = modifier,
        text = enumEntry.name,
        color = OudsTheme.colorScheme.content.default,
        fontFamily = FontFamily.Monospace,
        fontSize = 10.sp
    )
}

/**
 * Long text used in previews.
 */
internal const val LoremIpsumText =
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
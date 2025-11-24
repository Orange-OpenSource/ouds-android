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

package com.orange.ouds.app.ui.utilities.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.orange.ouds.app.ui.utilities.LocalThemeDrawableResources
import com.orange.ouds.app.ui.utilities.ThemeDrawableResources
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.theme.orange.OrangeTheme

@Composable
fun AppPreview(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val theme = OrangeTheme()
    CompositionLocalProvider(value = LocalThemeDrawableResources provides ThemeDrawableResources(theme)) {
        OudsTheme(
            theme = theme,
            darkThemeEnabled = isSystemInDarkTheme()
        ) {
            // Override theme settings
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
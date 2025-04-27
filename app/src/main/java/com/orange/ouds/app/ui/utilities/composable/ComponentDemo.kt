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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.orange.ouds.core.component.OudsColoredBox
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.OudsThemeTweak

@Composable
fun LightDarkDemo(modifier: Modifier = Modifier, componentDemo: @Composable () -> Unit) {
    ComponentDemoBox(modifier = modifier) {
        componentDemo()
    }
    OudsThemeTweak(OudsTheme.Tweak.Invert) {
        ComponentDemoBox(modifier = modifier) {
            componentDemo()
        }
    }
}

@Composable
fun OnColoredBoxDemo(modifier: Modifier = Modifier, componentDemo: @Composable () -> Unit) {
    ComponentDemoBox(modifier = modifier, colored = true) {
        componentDemo()
    }
}

@Composable
private fun ComponentDemoBox(modifier: Modifier = Modifier, colored: Boolean = false, content: @Composable BoxScope.() -> Unit) {
    @Suppress("NAME_SHADOWING")
    val modifier = modifier
        .padding(vertical = OudsTheme.spaces.fixed.medium)
        .fillMaxWidth()
    val contentAlignment = Alignment.Center
    if (colored) {
        OudsColoredBox(
            modifier = modifier,
            color = OudsColoredBox.Color.BrandPrimary,
            contentAlignment = contentAlignment,
            content = content
        )
    } else {
        Box(
            modifier = Modifier
                .background(OudsTheme.colorScheme.background.primary)
                .then(modifier),
            contentAlignment = contentAlignment,
            content = content
        )
    }
}

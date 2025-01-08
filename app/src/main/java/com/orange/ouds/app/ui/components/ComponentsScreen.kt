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

package com.orange.ouds.app.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.ui.utilities.composable.LargeCard
import com.orange.ouds.app.ui.utilities.composable.Screen
import com.orange.ouds.app.ui.utilities.painterResource
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.utilities.UiModePreviews

@Composable
fun ComponentsScreen(onComponentClick: (Long) -> Unit) {
    ComponentsScreen(
        components = components,
        onComponentClick = onComponentClick
    )
}

@Composable
private fun ComponentsScreen(components: List<Component>, onComponentClick: (Long) -> Unit) {
    Screen {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(OudsTheme.spaces.fixed.medium),
            verticalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.medium)
        ) {
            components.forEach { component ->
                LargeCard(
                    title = stringResource(id = component.nameRes),
                    painter = painterResource(id = component.imageRes),
                    onClick = { onComponentClick(component.id) }
                )
            }
        }
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewComponentsScreen() = OudsPreview {
    ComponentsScreen(
        components = listOf(Component.Button)
    ) {}
}

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

package com.orange.ouds.app.ui

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ColorFilter
import com.orange.ouds.app.ui.navigation.AppNavigationState
import com.orange.ouds.core.theme.value
import com.orange.ouds.theme.tokens.semantic.OudsColorKeyToken

class TopBarState(
    private val navigationState: AppNavigationState,
) {
    val showNavigationIcon: Boolean
        @Composable get() = navigationState.currentScreen?.isHome() == false

    val title: String
        @Composable get() = navigationState.currentScreen?.title?.asString().orEmpty()

    val actions: List<@Composable () -> Unit>
        @Composable get() = navigationState.currentScreen?.getTopBarActions().orEmpty()

    @Composable
    fun getNavigationIcon(upPress: () -> Unit): @Composable () -> Unit = if (showNavigationIcon) {
        {
            IconButton(onClick = upPress) {
                Image(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    colorFilter = ColorFilter.tint(OudsColorKeyToken.OnSurface.value) //TODO use ContentDefault token when available
                )
            }
        }
    } else {
        { }
    }
}

@Composable
fun rememberTopBarState(navigationState: AppNavigationState) = remember(navigationState) {
    TopBarState(navigationState)
}
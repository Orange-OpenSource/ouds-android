/*
 * Software Name: Orange Unified Design System
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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.orange.ouds.app.ui.navigation.AppNavigationState

class TopBarState(
    private val navigationState: AppNavigationState
) {
    val title: String
        @Composable get() = navigationState.currentScreen?.title?.asString().orEmpty()
}

@Composable
fun rememberTopBarState(navigationState: AppNavigationState) = remember(navigationState) {
    TopBarState(navigationState)
}
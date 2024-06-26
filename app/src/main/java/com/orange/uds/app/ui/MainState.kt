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

package com.orange.uds.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.orange.uds.app.ui.navigation.AppNavigationState
import com.orange.uds.app.ui.navigation.rememberAppNavigationState

class MainState(
    val navigationState: AppNavigationState,
    val topBarState: TopBarState
)

@Composable
fun rememberMainState(
    appNavigationState: AppNavigationState = rememberAppNavigationState(),
    topBarState: TopBarState = rememberTopBarState(appNavigationState),
) = remember(appNavigationState, topBarState) {
    MainState(appNavigationState, topBarState)
}

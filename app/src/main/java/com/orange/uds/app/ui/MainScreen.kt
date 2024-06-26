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

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.orange.uds.app.ui.navigation.appNavGraph
import com.orange.uds.core.theme.OudsTheme

@Composable
fun MainScreen() {
    val mainState = rememberMainState()

    OudsTheme {
        Scaffold(
            topBar = { TopBar(mainState.topBarState) },
            bottomBar = {
                BottomBar(
                    items = BottomBarItem.entries.toTypedArray(),
                    currentRoute = mainState.navigationState.currentRoute.orEmpty(),
                    navigateToRoute = { route ->
                        mainState.navigationState.navigateToBottomBarRoute(route)
                    }
                )
            }
        ) { innerPadding ->
            NavHost(
                navController = mainState.navigationState.navController,
                startDestination = BottomBarItem.Guidelines.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                appNavGraph()
            }
        }
    }

}
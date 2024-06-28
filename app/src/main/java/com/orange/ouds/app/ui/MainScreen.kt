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

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.compose.NavHost
import com.orange.ouds.app.ui.navigation.appNavGraph
import com.orange.ouds.core.theme.OudsTheme

@Composable
fun MainScreen() {
    val mainState = rememberMainState()

    OudsTheme {
        Scaffold(
            topBar = { TopBar(mainState.topBarState) },
            bottomBar = {
                AnimatedVisibility(
                    visible = mainState.showBottomBar,
                    enter = fadeIn(tween(100)),
                    exit = fadeOut(tween(100))
                ) {
                    BottomBar(
                        items = BottomBarItem.entries.toTypedArray(),
                        currentRoute = mainState.navigationState.currentRoute.orEmpty(),
                        navigateToRoute = { route ->
                            mainState.navigationState.navigateToBottomBarRoute(route)
                        }
                    )
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = mainState.navigationState.navController,
                startDestination = BottomBarItem.Guidelines.route,
                modifier = Modifier.padding(innerPadding),
                exitTransition = { ExitTransition.None },
            ) {
                appNavGraph(mainState.navigationState.navController)
            }
        }
    }
}
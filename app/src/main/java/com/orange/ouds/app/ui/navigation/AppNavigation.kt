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

package com.orange.ouds.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.orange.ouds.app.ui.Screen
import com.orange.ouds.app.ui.getScreen

@Composable
fun rememberAppNavigationState(navController: NavHostController = rememberNavController()) = remember(navController) { AppNavigationState(navController) }

class AppNavigationState(val navController: NavHostController) {

    val currentRoute: String?
        @Composable
        get() {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            return navBackStackEntry?.destination?.route
        }

    val currentScreen: Screen?
        @Composable get() {
            return currentRoute?.let { getScreen(it) }
        }

    fun navigateToBottomBarRoute(route: String) {
        if (route != navController.currentDestination?.route) {
            navController.navigateToBottomBarRoute(route)
        }
    }

}

fun NavController.navigateToBottomBarRoute(route: String) {
    navigate(route) {
        // Pop up to the start destination of the graph to avoid building up
        // a large stack of destinations on the back stack as users select items
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }
}
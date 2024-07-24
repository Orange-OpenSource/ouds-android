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

package com.orange.ouds.app.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.orange.ouds.app.ui.BottomBarItem
import com.orange.ouds.app.ui.about.AboutDestinations
import com.orange.ouds.app.ui.about.AboutScreen
import com.orange.ouds.app.ui.about.addAboutGraph
import com.orange.ouds.app.ui.components.ComponentsScreen
import com.orange.ouds.app.ui.guidelines.GuidelinesScreen

/**
 * Root navigation graph of the application
 */
fun NavGraphBuilder.appNavGraph(navController: NavController) {
    addAboutGraph()
    addBottomBarGraph(navController)
}

/**
 * Bottom bar navigation graph
 */
private fun NavGraphBuilder.addBottomBarGraph(navController: NavController) {
    composable(BottomBarItem.Guidelines.route) { _ ->
        GuidelinesScreen()
    }
    composable(BottomBarItem.Components.route) { _ ->
        ComponentsScreen()
    }
    composable(BottomBarItem.About.route) { _ ->
        AboutScreen { id -> navController.navigate("${AboutDestinations.FileRoute}/$id") }
    }
}

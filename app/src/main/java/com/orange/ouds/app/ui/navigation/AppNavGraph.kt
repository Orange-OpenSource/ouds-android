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
import com.orange.ouds.app.ui.about.addAboutNavGraph
import com.orange.ouds.app.ui.components.ComponentsScreen
import com.orange.ouds.app.ui.tokens.TokensNavigation
import com.orange.ouds.app.ui.tokens.TokensScreen
import com.orange.ouds.app.ui.tokens.addTokensNavGraph

/**
 * Root navigation graph of the application
 */
fun NavGraphBuilder.appNavGraph(navController: NavController) {
    addTokensNavGraph(navController)
    addAboutNavGraph()
    addBottomBarNavGraph(navController)
}

/**
 * Bottom bar navigation graph
 */
private fun NavGraphBuilder.addBottomBarNavGraph(navController: NavController) {
    composable(BottomBarItem.Tokens.route) { from ->
        TokensScreen(onTokenCategoryClick = { id -> navController.navigateToElement(TokensNavigation.TokenCategoryDetailRoute, id, from) })
    }
    composable(BottomBarItem.Components.route) { _ ->
        ComponentsScreen()
    }
    composable(BottomBarItem.About.route) { _ ->
        AboutScreen { id -> navController.navigate("${AboutDestinations.FileRoute}/$id") }
    }
}

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
import com.orange.ouds.app.ui.about.AboutFileMenuItem
import com.orange.ouds.app.ui.about.AboutMenuItem
import com.orange.ouds.app.ui.about.AboutRouteMenuItem
import com.orange.ouds.app.ui.about.AboutScreen
import com.orange.ouds.app.ui.about.addAboutNavGraph
import com.orange.ouds.app.ui.components.ComponentsNavigation
import com.orange.ouds.app.ui.components.ComponentsScreen
import com.orange.ouds.app.ui.components.addComponentsNavGraph
import com.orange.ouds.app.ui.tokens.TokensNavigation
import com.orange.ouds.app.ui.tokens.TokensScreen
import com.orange.ouds.app.ui.tokens.addTokensNavGraph

/**
 * Root navigation graph of the application
 */
fun NavGraphBuilder.appNavGraph(navController: NavController) {
    addTokensNavGraph(navController)
    addComponentsNavGraph(navController)
    addAboutNavGraph()
    addBottomBarNavGraph(navController)
}

/**
 * Bottom bar navigation graph
 */
private fun NavGraphBuilder.addBottomBarNavGraph(navController: NavController) {
    composable(BottomBarItem.Tokens.route) { from ->
        TokensScreen { id -> navController.navigateToElement(TokensNavigation.TokenCategoryDetailRoute, id, from) }
    }
    composable(BottomBarItem.Components.route) { from ->
        ComponentsScreen { id -> navController.navigateToElement(ComponentsNavigation.ComponentDetailRoute, id, from) }
    }
    composable(BottomBarItem.About.route) { from ->
        AboutScreen { id ->
            when (val aboutMenuItem = AboutMenuItem.fromId(id)) {
                is AboutFileMenuItem -> navController.navigateToElement(AboutDestinations.FileRoute, id.toLong(), from)
                is AboutRouteMenuItem -> navController.navigate(aboutMenuItem.route)
                else -> null
            }

        }
    }
}

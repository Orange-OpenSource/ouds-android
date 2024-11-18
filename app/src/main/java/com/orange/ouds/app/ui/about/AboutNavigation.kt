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

package com.orange.ouds.app.ui.about

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.orange.ouds.app.ui.about.AboutNavigationKey.MenuItemIdKey

object AboutDestinations {
    const val FileRoute = "ouds/module/about/file"
    const val M3ComponentsRoute = "ouds/module/about/m3Components"
}

object AboutNavigationKey {
    const val MenuItemIdKey = "aboutMenuItemId"
}

fun NavGraphBuilder.addAboutNavGraph() {
    composable(
        "${AboutDestinations.FileRoute}/{$MenuItemIdKey}",
        arguments = listOf(navArgument(MenuItemIdKey) { type = NavType.LongType })
    ) { navBackStackEntry ->
        val aboutMenuItemId = requireNotNull(navBackStackEntry.arguments).getLong(MenuItemIdKey).toInt()
        val aboutItem = AboutMenuItem.fromId(aboutMenuItemId)
        (aboutItem as? AboutFileMenuItem)?.let { AboutFileScreen(it.fileRes, isSystemInDarkTheme()) }
    }

    composable(AboutDestinations.M3ComponentsRoute) { _ ->
        AboutM3ComponentsScreen()
    }
}

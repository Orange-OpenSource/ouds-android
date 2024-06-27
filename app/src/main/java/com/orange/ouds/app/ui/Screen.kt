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

import com.orange.ouds.app.R
import com.orange.ouds.foundation.utilities.UiString

/**
 * Returns the [Screen] corresponding to the given [route].
 */
fun getScreen(route: String): Screen? {
    val screens = Screen::class.sealedSubclasses.mapNotNull { it.objectInstance }
    return screens.firstOrNull { screen -> screen.route == route }
}

/**
 * Defines application common changing elements for each screen.
 * It allows to manage top app bar display according to the screen.
 */
sealed class Screen(
    val route: String,
    val title: UiString? = null,
) {

    // Bottom navigation screens

    data object Guidelines : Screen(
        route = BottomBarItem.Guidelines.route,
        title = UiString.StringResource(R.string.app_bottomNavigation_menu_guidelines)
    )

    data object Components : Screen(
        route = BottomBarItem.Components.route,
        title = UiString.StringResource(R.string.app_bottomNavigation_menu_components)
    )

    data object Modules : Screen(
        route = BottomBarItem.Modules.route,
        title = UiString.StringResource(R.string.app_bottomNavigation_menu_modules)
    )

    data object About : Screen(
        route = BottomBarItem.About.route,
        title = UiString.StringResource(R.string.app_bottomNavigation_menu_about)
    )

}
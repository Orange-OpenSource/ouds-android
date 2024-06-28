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

import android.os.Bundle
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.about.AboutDestinations
import com.orange.ouds.app.ui.about.AboutMenuItem
import com.orange.ouds.app.ui.about.AboutNavigationKey
import com.orange.ouds.foundation.UiString

/**
 * Returns the [Screen] corresponding to the given [route].
 */
fun getScreen(route: String, args: Bundle?): Screen? {
    val matchElementRouteResult = Regex("^(.+)/\\{.+\\}$").find(route)
    return if (matchElementRouteResult != null) {
        // Specific element route -> get element id
        val (routeRoot) = matchElementRouteResult.destructured
        when (routeRoot) {
            AboutDestinations.FileRoute -> {
                args?.getLong(AboutNavigationKey.MenuItemIdKey)?.let { Screen.AboutFile(it) }
            }
            else -> null
        }
    } else {
        // Simple route
        val screens = Screen::class.sealedSubclasses.mapNotNull { it.objectInstance }
        screens.firstOrNull { screen -> screen.route == route }
    }
}

/**
 * Defines application common changing elements for each screen.
 * It allows to manage top app bar display according to the screen.
 */
sealed class Screen(
    val route: String,
    val title: UiString? = null,
) {

    fun isHome() = this in listOf(Guidelines, Components, About)

    // Bottom navigation screens

    data object Guidelines : Screen(
        route = BottomBarItem.Guidelines.route,
        title = UiString.StringResource(R.string.app_bottomBar_guidelines_label)
    )

    data object Components : Screen(
        route = BottomBarItem.Components.route,
        title = UiString.StringResource(R.string.app_bottomBar_components_label)
    )

    data object About : Screen(
        route = BottomBarItem.About.route,
        title = UiString.StringResource(R.string.app_bottomBar_about_label)
    )


    // About screens screens

    data class AboutFile(val menuItemId: Long) : Screen(
        route = AboutDestinations.FileRoute,
        title = AboutMenuItem.fromId(menuItemId.toInt())?.labelRes?.let { UiString.StringResource(it) }
    )
}
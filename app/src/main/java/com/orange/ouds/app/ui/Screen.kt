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

package com.orange.ouds.app.ui

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalInspectionMode
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.about.AboutDestinations
import com.orange.ouds.app.ui.about.AboutMenuItem
import com.orange.ouds.app.ui.about.AboutNavigationKey
import com.orange.ouds.app.ui.components.Component
import com.orange.ouds.app.ui.components.ComponentsNavigation
import com.orange.ouds.app.ui.tokens.TokenCategory
import com.orange.ouds.app.ui.tokens.TokensNavigation
import com.orange.ouds.foundation.UiString

/**
 * Returns the [Screen] corresponding to the given [route].
 */
@Composable
fun getScreen(route: String, args: Bundle?): Screen? {
    val matchElementRouteResult = Regex("^(.+)/\\{.+\\}$").find(route)
    return if (matchElementRouteResult != null) {
        // Specific element route -> get element id
        val (routeRoot) = matchElementRouteResult.destructured
        when (routeRoot) {
            TokensNavigation.TokenCategoryDetailRoute -> args?.getLong(TokensNavigation.TokenCategoryIdKey)?.let { Screen.TokenCategoryDetail(it) }
            ComponentsNavigation.ComponentDetailRoute -> args?.getLong(ComponentsNavigation.ComponentIdKey)?.let { Screen.ComponentDetail(it) }
            AboutDestinations.FileRoute -> args?.getLong(AboutNavigationKey.MenuItemIdKey)?.let { Screen.AboutFile(it) }
            else -> null
        }
    } else {
        // Simple route
        screens.firstOrNull { screen -> screen.route == route }
    }
}

val screens: List<Screen>
    @Composable
    get() = if (LocalInspectionMode.current) {
        // Fixes a bug where calling sealedSubclasses returns an empty list in Compose previews
        // See https://issuetracker.google.com/issues/240601093
        listOf(Screen.Tokens, Screen.Components, Screen.About)
    } else {
        Screen::class.sealedSubclasses.mapNotNull { it.objectInstance }
    }

/**
 * Defines application common changing elements for each screen.
 * It allows to manage top app bar display according to the screen.
 */
sealed class Screen(
    val route: String,
    val title: UiString? = null,
) {

    fun isHome() = this in listOf(Tokens, Components, About)

    // Bottom navigation screens

    data object Tokens : Screen(
        route = BottomBarItem.Tokens.route,
        title = UiString.StringResource(R.string.app_bottomBar_tokens_label)
    )

    data object Components : Screen(
        route = BottomBarItem.Components.route,
        title = UiString.StringResource(R.string.app_bottomBar_components_label)
    )

    data object About : Screen(
        route = BottomBarItem.About.route,
        title = UiString.StringResource(R.string.app_bottomBar_about_label)
    )

    // Tokens screens

    data class TokenCategoryDetail(val tokenCategoryId: Long) : Screen(
        route = TokensNavigation.TokenCategoryDetailRoute,
        title = TokenCategory.fromId(tokenCategoryId)?.nameRes?.let { UiString.StringResource(it) }
    )

    // Components screens

    data class ComponentDetail(val componentId: Long) : Screen(
        route = ComponentsNavigation.ComponentDetailRoute,
        title = Component.fromId(componentId)?.nameRes?.let { UiString.StringResource(it) }
    )

    // About screens

    data class AboutFile(val menuItemId: Long) : Screen(
        route = AboutDestinations.FileRoute,
        title = AboutMenuItem.fromId(menuItemId.toInt())?.labelRes?.let { UiString.StringResource(it) }
    )
}
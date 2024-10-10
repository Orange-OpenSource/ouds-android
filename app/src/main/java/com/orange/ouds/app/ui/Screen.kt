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
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.about.AboutDestinations
import com.orange.ouds.app.ui.about.AboutMenuItem
import com.orange.ouds.app.ui.about.AboutNavigationKey
import com.orange.ouds.app.ui.tokens.TokensNavigation
import com.orange.ouds.foundation.UiString
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

/**
 * Returns the [Screen] corresponding to the given [route].
 */
fun getScreen(route: String, args: Bundle?): Screen? {
    val matchElementRouteResult = Regex("^(.+)/\\{.+\\}$").find(route)
    return if (matchElementRouteResult != null) {
        // Specific element route -> get element id
        val (routeRoot) = matchElementRouteResult.destructured
        when (routeRoot) {
            TokensNavigation.TokenDetailRoute -> {
                args?.getLong(TokensNavigation.TokenIdKey)?.let { Screen.Token(it) }
            }

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

    companion object {
        private val _topBarActionClicked = MutableSharedFlow<TopBarAction>(extraBufferCapacity = 1)
        val topBarActionClicked: Flow<TopBarAction> = _topBarActionClicked.asSharedFlow()
    }

    fun isHome() = this in listOf(Tokens, Components, About)

    @Composable
    fun getTopBarActions(): List<@Composable () -> Unit> = getDefaultActions { action -> _topBarActionClicked.tryEmit(action) }

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

    data class Token(val tokenId: Long) : Screen(
        route = TokensNavigation.TokenDetailRoute,
        title = com.orange.ouds.app.ui.tokens.TokenType.fromId(tokenId)?.titleRes?.let { UiString.StringResource(it) }
    )

    // About screens

    data class AboutFile(val menuItemId: Long) : Screen(
        route = AboutDestinations.FileRoute,
        title = AboutMenuItem.fromId(menuItemId.toInt())?.labelRes?.let { UiString.StringResource(it) }
    )
}
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

package com.orange.ouds.app.ui.components

import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

object ComponentsNavigation {
    const val ComponentDetailRoute = "component"
    const val ComponentIdKey = "componentId"
}

fun NavGraphBuilder.addComponentsNavGraph() {
    composable(
        "${ComponentsNavigation.ComponentDetailRoute}/{${ComponentsNavigation.ComponentIdKey}}",
        arguments = listOf(navArgument(ComponentsNavigation.ComponentIdKey) { type = NavType.LongType })
    ) { from ->
        val arguments = requireNotNull(from.arguments)
        val routeComponentId = arguments.getLong(ComponentsNavigation.ComponentIdKey)

        val component = remember(routeComponentId) { Component.fromId(routeComponentId) }
        component?.let {
            ComponentDetailScreen(component = component)
        }
    }
}

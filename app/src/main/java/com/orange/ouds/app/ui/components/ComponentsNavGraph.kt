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
import com.orange.ouds.app.ui.MainState
import com.orange.ouds.app.ui.navigation.navigateToElement

object ComponentsNavigation {
    const val ComponentDetailRoute = "component"
    const val ComponentIdKey = "componentId"
    const val ComponentVariantRoute = "component/variant"
    const val ComponentVariantIdKey = "componentVariantId"
}

fun NavGraphBuilder.addComponentsNavGraph(mainState: MainState) {
    composable(
        "${ComponentsNavigation.ComponentDetailRoute}/{${ComponentsNavigation.ComponentIdKey}}",
        arguments = listOf(navArgument(ComponentsNavigation.ComponentIdKey) { type = NavType.LongType })
    ) { from ->
        val arguments = requireNotNull(from.arguments)
        val routeComponentId = arguments.getLong(ComponentsNavigation.ComponentIdKey)

        val component = remember(routeComponentId) { Component.fromId(routeComponentId) }
        component?.let {
            if (component.variants.isEmpty()) {
                component.demoScreen?.invoke()
            } else {
                ComponentVariantsScreen(component = component, onVariantClick = { id ->
                    mainState.navigationState.navController.navigateToElement(ComponentsNavigation.ComponentVariantRoute, id, from)
                })
            }
        }
    }

    composable(
        "${ComponentsNavigation.ComponentVariantRoute}/{${ComponentsNavigation.ComponentVariantIdKey}}",
        arguments = listOf(navArgument(ComponentsNavigation.ComponentVariantIdKey) { type = NavType.LongType })
    ) { from ->
        val arguments = requireNotNull(from.arguments)
        val routeVariantId = arguments.getLong(ComponentsNavigation.ComponentVariantIdKey)
        val variant = remember(routeVariantId) { Variant.fromId(routeVariantId) }
        variant?.demoScreen?.invoke()
    }
}


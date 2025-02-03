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

package com.orange.ouds.app.ui.tokens

import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.orange.ouds.app.ui.navigation.navigateToElement

object TokensNavigation {
    const val TokenCategoryDetailRoute = "tokenCategory"
    const val TokenCategoryIdKey = "tokenCategoryId"
}

fun NavGraphBuilder.addTokensNavGraph(navController: NavController) {
    composable(
        "${TokensNavigation.TokenCategoryDetailRoute}/{${TokensNavigation.TokenCategoryIdKey}}",
        arguments = listOf(navArgument(TokensNavigation.TokenCategoryIdKey) { type = NavType.LongType })
    ) { from ->
        val arguments = requireNotNull(from.arguments)
        val routeTokenCategoryId = arguments.getLong(TokensNavigation.TokenCategoryIdKey)

        val tokenCategory = remember(routeTokenCategoryId) { TokenCategory.fromId(routeTokenCategoryId) }
        tokenCategory?.let {
            TokenCategoryDetailScreen(
                tokenCategory = tokenCategory,
                onSubcategoryClick = { id ->
                    navController.navigateToElement(TokensNavigation.TokenCategoryDetailRoute, id, from)
                }
            )
        }
    }
}

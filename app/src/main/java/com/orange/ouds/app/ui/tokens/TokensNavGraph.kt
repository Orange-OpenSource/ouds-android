/*
 * Software Name: Orange Design System
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
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

object TokensNavigation {
    const val TokenCategoryDetailRoute = "tokenCategory"
    const val TokenCategoryIdKey = "tokenCategoryId"
}

fun NavGraphBuilder.addTokensNavGraph() {
    composable(
        "${TokensNavigation.TokenCategoryDetailRoute}/{${TokensNavigation.TokenCategoryIdKey}}",
        arguments = listOf(navArgument(TokensNavigation.TokenCategoryIdKey) { type = NavType.LongType })
    ) { from ->
        val arguments = requireNotNull(from.arguments)
        val routeTokenId = arguments.getLong(TokensNavigation.TokenCategoryIdKey)

        val tokenCategory = remember(routeTokenId) { TokenCategory.fromId(routeTokenId) }
        tokenCategory?.let {
            TokenCategoryDetailScreen(tokenCategory = tokenCategory)
        }
    }
}

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
    const val TokenDetailRoute = "token"
    const val TokenIdKey = "tokenId"
}

fun NavGraphBuilder.addTokensNavGraph() {
    composable(
        "${TokensNavigation.TokenDetailRoute}/{${TokensNavigation.TokenIdKey}}",
        arguments = listOf(navArgument(TokensNavigation.TokenIdKey) { type = NavType.LongType })
    ) { from ->
        val arguments = requireNotNull(from.arguments)
        val routeTokenId = arguments.getLong(TokensNavigation.TokenIdKey)

        val token = remember(routeTokenId) { Token.fromId(routeTokenId) }
        token?.let {
            TokenDetailScreen(token = token)
        }
    }
}

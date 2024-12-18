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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.ui.utilities.composable.LargeCard
import com.orange.ouds.app.ui.utilities.composable.Screen
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.utilities.UiModePreviews
import com.orange.ouds.theme.tokens.OudsColorKeyToken
import com.orange.ouds.theme.tokens.OudsSpaceKeyToken

@Composable
fun TokensScreen(onTokenCategoryClick: (Long) -> Unit) {
    TokensScreen(
        tokenCategories = tokenCategories.filter { !it.isSubcategory },
        onTokenCategoryClick = onTokenCategoryClick
    )
}

@Composable
private fun TokensScreen(tokenCategories: List<TokenCategory<*>>, onTokenCategoryClick: (Long) -> Unit) {
    Screen {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(OudsSpaceKeyToken.Fixed.Medium.value),
            verticalArrangement = Arrangement.spacedBy(OudsSpaceKeyToken.Fixed.Medium.value)
        ) {
            tokenCategories.forEach { token ->
                LargeCard(
                    title = stringResource(id = token.nameRes),
                    imageRes = token.imageRes,
                    onClick = { onTokenCategoryClick(token.id) },
                    imageTint = OudsColorKeyToken.Always.White.value
                )
            }
        }
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewTokensScreen() = OudsPreview {
    TokensScreen(
        tokenCategories = listOf(
            TokenCategory.Border,
            TokenCategory.Dimension,
            TokenCategory.Elevation,
            TokenCategory.Opacity,
            TokenCategory.Typography
        )
    ) {}
}

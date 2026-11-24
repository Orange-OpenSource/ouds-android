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

package com.orange.ouds.app.ui.components.card

import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.core.component.OudsCard
import com.orange.ouds.core.component.OudsCardStyle
import com.orange.ouds.core.component.OudsElevatedCard
import com.orange.ouds.core.component.OudsOutlinedCard
import com.orange.ouds.core.component.OudsSkeleton
import com.orange.ouds.core.component.rememberOudsSkeletonState
import com.orange.ouds.foundation.InternalOudsApi
import com.orange.ouds.theme.OudsVersion

@OptIn(InternalOudsApi::class)
@Composable
fun CardDemoScreen() {
    val state = rememberCardDemoState()
    DemoScreen(
        description = stringResource(id = R.string.app_components_card_card_description_text),
        bottomSheetContent = { BaseCardDemoBottomSheetContent(state = state) },
        codeSnippet = { cardDemoCodeSnippet(state = state) },
        demoContent = { CardDemoContent(state = state) },
        demoContentOnColor = state.demoContentOnColor,
        version = OudsVersion.Component.Card
    )
}

@Composable
private fun CardDemoContent(state: CardDemoState) {
    with(state) {
        val modifier = Modifier.width(BaseCardDemoState.DemoCardWidth)
        val skeleton = if (skeleton) OudsSkeleton(rememberOudsSkeletonState()) else null
        when (style) {
            BaseCardDemoState.Style.Filled -> OudsCard(
                modifier = modifier,
                style = OudsCardStyle.Filled(
                    background = background,
                    divider = divider
                ),
                enabled = enabled,
                skeleton = skeleton
            ) {
                BaseCardContentExample(state = state)
            }
            BaseCardDemoState.Style.Outlined -> OudsOutlinedCard(
                modifier = modifier,
                enabled = enabled,
                skeleton = skeleton
            ) {
                BaseCardContentExample(state = state)
            }
            BaseCardDemoState.Style.Elevated -> OudsElevatedCard(
                modifier = modifier,
                alternativeBackground = alternativeBackground,
                onBackgroundColor = elevatedCardOnBackgroundColor,
                enabled = enabled,
                skeleton = skeleton
            ) {
                BaseCardContentExample(state = state)
            }
        }
    }
}

private fun Code.Builder.cardDemoCodeSnippet(state: CardDemoState) {
    this@cardDemoCodeSnippet.baseCardDemoCodeSnippet(state = state)
}
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
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.core.component.OudsCard
import com.orange.ouds.core.component.OudsCardLoader
import com.orange.ouds.core.component.OudsCardStyle
import com.orange.ouds.core.component.OudsElevatedCard
import com.orange.ouds.core.component.OudsOutlinedCard
import com.orange.ouds.core.component.OudsSkeleton
import com.orange.ouds.core.component.rememberOudsSkeletonState
import com.orange.ouds.foundation.InternalOudsApi
import com.orange.ouds.theme.OudsVersion

@OptIn(InternalOudsApi::class)
@Composable
fun ActionCardDemoScreen() {
    val state = rememberActionCardDemoState()
    DemoScreen(
        description = stringResource(id = R.string.app_components_card_actionCard_description_text),
        bottomSheetContent = { ActionCardDemoBottomSheetContent(state = state) },
        codeSnippet = { actionCardDemoCodeSnippet(state = state) },
        demoContent = { ActionCardDemoContent(state = state) },
        version = OudsVersion.Component.ActionCard
    )
}

@Composable
private fun ActionCardDemoBottomSheetContent(state: ActionCardDemoState) {
    with(state) {
        BaseCardDemoBottomSheetContent(state)
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_common_loader_tech),
            checked = hasLoader,
            onCheckedChange = { hasLoader = it },
            enabled = loaderSwitchEnabled
        )
    }
}

@Composable
private fun ActionCardDemoContent(state: ActionCardDemoState) {
    with(state) {
        val modifier = Modifier.width(BaseCardDemoState.DemoCardWidth)
        val skeleton = if (skeleton) OudsSkeleton(rememberOudsSkeletonState()) else null
        when (style) {
            BaseCardDemoState.Style.Filled -> OudsCard(
                onClick = {},
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
                onClick = {},
                modifier = modifier,
                enabled = enabled,
                skeleton = skeleton
            ) {
                BaseCardContentExample(state = state)
            }
            BaseCardDemoState.Style.Elevated -> OudsElevatedCard(
                onClick = {},
                modifier = modifier,
                background = background,
                enabled = enabled,
                skeleton = skeleton
            ) {
                BaseCardContentExample(state = state)
            }
        }
    }
}

private fun Code.Builder.actionCardDemoCodeSnippet(state: ActionCardDemoState) {
    with(state) {
        this@actionCardDemoCodeSnippet.baseCardDemoCodeSnippet(state = state) {
            if (hasLoader && !skeleton) {
                constructorCallArgument<OudsCardLoader>("loader") {
                    rawArgument(null, "null")
                }
            }
        }
    }
}
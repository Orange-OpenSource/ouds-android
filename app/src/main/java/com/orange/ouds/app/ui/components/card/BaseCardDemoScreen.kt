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

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.skeletonArgument
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.FunctionCall
import com.orange.ouds.app.ui.utilities.composable.CustomizationDropdownMenu
import com.orange.ouds.app.ui.utilities.composable.CustomizationDropdownMenuItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChips
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.core.component.OudsButton
import com.orange.ouds.core.component.OudsButtonAppearance
import com.orange.ouds.core.component.OudsCardDefaults
import com.orange.ouds.core.component.OudsCardOnBackgroundColor
import com.orange.ouds.core.component.OudsCardStyle
import com.orange.ouds.core.component.OudsHeadingText
import com.orange.ouds.core.component.OudsLabelText
import com.orange.ouds.core.component.OudsTag
import com.orange.ouds.core.component.OudsTagStatus
import com.orange.ouds.core.component.OudsTextWeight
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.foundation.extensions.toSentenceCase

@Composable
fun BaseCardDemoBottomSheetContent(state: BaseCardDemoState) {
    with(state) {
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_card_style_tech),
            chipLabels = BaseCardDemoState.Style.entries.map { it.name.toSentenceCase() },
            selectedChipIndex = BaseCardDemoState.Style.entries.indexOf(style),
            onSelectionChange = { index -> style = BaseCardDemoState.Style.entries[index] }
        )

        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_common_background_tech),
            checked = background,
            onCheckedChange = { background = it },
            enabled = filledCardSettingsEnabled
        )

        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_card_alternativeBackground_tech),
            checked = alternativeBackground,
            onCheckedChange = { alternativeBackground = it },
            enabled = elevatedCardSettingsEnabled
        )

        val elevatedCardOnBackgroundColors = OudsCardOnBackgroundColor.entries
        CustomizationDropdownMenu(
            applyTopPadding = false,
            label = stringResource(id = R.string.app_components_card_onBackgroundColor_tech),
            items = elevatedCardOnBackgroundColors.map { color ->
                val enabled = elevatedCardSettingsEnabled
                CustomizationDropdownMenuItem(
                    label = color.name.toSentenceCase(),
                    leadingIcon = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(if (enabled) color.value else OudsTheme.colorScheme.action.disabled)
                                .border(width = 1.dp, color = if (enabled) OudsTheme.colorScheme.content.default else OudsTheme.colorScheme.content.disabled)
                        )
                    },
                    enabled = enabled
                )
            },
            selectedItemIndex = elevatedCardOnBackgroundColors.indexOf(elevatedCardOnBackgroundColor),
            onSelectionChange = { elevatedCardOnBackgroundColor = elevatedCardOnBackgroundColors[it] }
        )

        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_common_divider_tech),
            checked = divider,
            onCheckedChange = { divider = it },
            enabled = filledCardSettingsEnabled
        )

        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_card_contentExample_tech),
            chipLabels = BaseCardDemoState.ContentExample.entries.map { it.name.toSentenceCase() },
            selectedChipIndex = BaseCardDemoState.ContentExample.entries.indexOf(contentExample),
            onSelectionChange = { index -> contentExample = BaseCardDemoState.ContentExample.entries[index] }
        )

        CustomizationSwitchItem(
            label = stringResource(R.string.app_common_enabled_tech),
            checked = enabled,
            onCheckedChange = { enabled = it },
        )

        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_common_skeleton_tech),
            checked = skeleton,
            onCheckedChange = { skeleton = it }
        )
    }
}

@Composable
fun BaseCardContentExample(state: BaseCardDemoState) {
    when (state.contentExample) {
        BaseCardDemoState.ContentExample.Empty -> Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
        )
        BaseCardDemoState.ContentExample.UseCase -> BaseCardUseCaseContent()
    }
}

@Composable
private fun BaseCardUseCaseContent() {
    Box(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            OudsTag(label = stringResource(R.string.app_components_card_contentExampleTagLabel_label), status = OudsTagStatus.Warning(), roundedCorners = false)
            Column(modifier = Modifier.padding(all = OudsTheme.spaces.fixed.medium)) {
                OudsHeadingText(text = stringResource(R.string.app_components_card_contentExampleTitle_tech))
                OudsLabelText(text = stringResource(R.string.app_components_card_contentExampleSubtitle_tech), weight = OudsTextWeight.Moderate)
                OudsButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = OudsTheme.spaces.fixed.large),
                    label = stringResource(R.string.app_components_card_contentExampleButtonLabel_label),
                    onClick = {},
                    appearance = OudsButtonAppearance.Strong
                )
            }
        }
    }
}

fun Code.Builder.baseCardDemoCodeSnippet(state: BaseCardDemoState, cardArguments: FunctionCall.Builder.() -> Unit = {}) {
    with(state) {
        functionCall("OudsCard") {
            trailingLambda = true

            when (style) {
                BaseCardDemoState.Style.Filled -> {
                    if (!background || divider) {
                        constructorCallArgument<OudsCardStyle.Filled>("style") {
                            if (background != OudsCardDefaults.Background) {
                                typedArgument("background", background)
                            }
                            if (divider) {
                                typedArgument("divider", divider)
                            }
                        }
                    }
                }
                BaseCardDemoState.Style.Outlined -> {
                    rawArgument("style", "OudsCardStyle.Outlined")
                }
                BaseCardDemoState.Style.Elevated -> {
                    constructorCallArgument<OudsCardStyle.Elevated>("style") {
                        if (alternativeBackground != OudsCardDefaults.AlternativeBackground) {
                            typedArgument("alternativeBackground", alternativeBackground)
                        }
                        if (elevatedCardOnBackgroundColor != OudsCardDefaults.OnBackgroundColor) {
                            typedArgument("elevatedCardOnBackgroundColor", elevatedCardOnBackgroundColor)
                        }
                    }
                }
            }

            cardArguments()

            rawArgument("onClick", "{}")

            if (!enabled) {
                typedArgument("enabled", enabled)
            }

            if (skeleton) {
                skeletonArgument()
            }

            lambdaArgument("content") {
                comment("Your card content here")
            }
        }
    }
}

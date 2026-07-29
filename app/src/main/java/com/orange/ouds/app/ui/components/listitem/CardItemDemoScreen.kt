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

package com.orange.ouds.app.ui.components.listitem

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.listitem.ItemDemoState.Size
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.LocalThemeDrawableResources
import com.orange.ouds.app.ui.utilities.ThemeDrawableResources
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChip
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChips
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.app.ui.utilities.nestedName
import com.orange.ouds.core.component.OudsCardItem
import com.orange.ouds.core.component.OudsListItemDecoration
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.foundation.ExperimentalOudsApi
import com.orange.ouds.foundation.extensions.toSentenceCase
import com.orange.ouds.theme.OudsVersion

@Composable
fun CardItemDemoScreen(size: ItemDemoState.Size) {
    val state = rememberCardItemDemoState(size = size)
    val themeDrawableResources = LocalThemeDrawableResources.current
    DemoScreen(
        bottomSheetTabs = { ItemDemoBottomSheetTabs(state = state) },
        bottomSheetContent = { CardItemDemoBottomSheetContent(state = state) },
        codeSnippet = { cardItemDemoCodeSnippet(state = state, themeDrawableResources = themeDrawableResources) },
        demoContent = { CardItemDemoContent(state = state) },
        demoContentPaddingValues = PaddingValues(horizontal = OudsTheme.spaces.fixed.none),
        version = OudsVersion.Component.ListItem
    )
}

@Composable
private fun CardItemDemoBottomSheetContent(state: CardItemDemoState) {
    ItemDemoBottomSheetContent(state = state)
}

@Composable
internal fun CardItemGlobalCustomizationContent(state: CardItemDemoState) {
    with(state) {
        val extraCustomizations = listOf(
            itemGlobalCustomization(3) {
                CustomizationFilterChips(
                    applyTopPadding = true,
                    label = stringResource(R.string.app_components_listItem_cardItem_decoration_tech),
                    chips = CardItemDemoState.Decoration.entries.map {
                        CustomizationFilterChip(
                            label = it.name.toSentenceCase()
                        )
                    },
                    selectedChipIndex = CardItemDemoState.Decoration.entries.indexOf(decoration),
                    onSelectionChange = { index -> decoration = CardItemDemoState.Decoration.entries[index] }
                )
            },
            itemGlobalCustomization(4) {
                CustomizationSwitchItem(
                    label = stringResource(R.string.app_components_common_divider_tech),
                    checked = divider,
                    onCheckedChange = { divider = it },
                    enabled = dividerEnabled
                )
            }
        )
        ItemGlobalCustomizations(state = state, extraCustomizations = extraCustomizations)
    }
}

@OptIn(ExperimentalOudsApi::class)
@Composable
private fun CardItemDemoContent(state: CardItemDemoState) {
    with(state) {
        val modifier = Modifier.padding(horizontal = OudsTheme.grids.margin)
        if (clickable) {
            OudsCardItem(
                modifier = modifier,
                onClick = {},
                indicator = indicator.toOudsListItemIndicator(),
                label = label,
                verticalAlignment = verticalAlignment,
                overline = overline,
                extraLabel = extraLabel,
                description = description,
                leading = itemDemoLeading(state = state),
                trailing = itemDemoTrailing(state = state),
                decoration = decoration.toOudsListItemDecoration(divider = divider),
                helperText = helperText,
                boldLabel = boldLabel,
                enabled = enabled,
            )
        } else {
            OudsCardItem(
                modifier = modifier,
                label = label,
                verticalAlignment = verticalAlignment,
                overline = overline,
                extraLabel = extraLabel,
                description = description,
                leading = itemDemoLeading(state = state),
                trailing = itemDemoTrailing(state = state),
                decoration = decoration.toOudsListItemDecoration(divider = divider),
                helperText = helperText,
                boldLabel = boldLabel,
                enabled = enabled,
            )
        }
    }
}

private fun Code.Builder.cardItemDemoCodeSnippet(state: CardItemDemoState, themeDrawableResources: ThemeDrawableResources) {
    with(state) {
        val functionName = when (size) {
            Size.Default -> "OudsCardItem"
            Size.Small -> "OudsSmallCardItem"
        }
        functionCall(functionName) {
            val decorationParameterName = "decoration"
            when (decoration) {
                CardItemDemoState.Decoration.Background -> if (!divider) {
                    constructorCallArgument<OudsListItemDecoration.Background>(decorationParameterName) {
                        typedArgument("divider", divider)
                    }
                }
                CardItemDemoState.Decoration.BackgroundOnInteraction ->
                    constructorCallArgument<OudsListItemDecoration.BackgroundOnInteraction>(decorationParameterName) {
                        typedArgument("divider", divider)
                    }
                CardItemDemoState.Decoration.Outlined -> rawArgument(decorationParameterName, OudsListItemDecoration.Outlined::class.java.nestedName)
                CardItemDemoState.Decoration.OutlinedOnInteraction -> rawArgument(
                    decorationParameterName,
                    OudsListItemDecoration.OutlinedOnInteraction::class.java.nestedName
                )
            }

            itemArguments(state, themeDrawableResources)
        }
    }
}
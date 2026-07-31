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
import com.orange.ouds.app.ui.components.listitem.BaseListItemDemoState.Size
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.LocalThemeDrawableResources
import com.orange.ouds.app.ui.utilities.ThemeDrawableResources
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.core.component.OudsListItem
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.foundation.ExperimentalOudsApi
import com.orange.ouds.theme.OudsVersion

@Composable
fun ListItemDemoScreen(size: BaseListItemDemoState.Size) {
    val state = rememberListItemDemoState(size = size)
    val themeDrawableResources = LocalThemeDrawableResources.current
    DemoScreen(
        bottomSheetTabs = { BaseListItemDemoBottomSheetTabs(state = state) },
        bottomSheetContent = { ListItemDemoBottomSheetContent(state = state) },
        codeSnippet = { listItemDemoCodeSnippet(state = state, themeDrawableResources = themeDrawableResources) },
        demoContent = { ListItemDemoContent(state = state) },
        demoContentPaddingValues = PaddingValues(horizontal = OudsTheme.spaces.fixed.none),
        version = OudsVersion.Component.NavigationListItem
    )
}

@Composable
private fun ListItemDemoBottomSheetContent(state: ListItemDemoState) {
    BaseListItemDemoBottomSheetContent(state = state)
}


@Composable
internal fun ListItemGlobalCustomizationContent(state: ListItemDemoState) {
    with(state) {
        val extraCustomizations = listOf(
            baseListItemGlobalCustomization(3) {
                CustomizationSwitchItem(
                    label = stringResource(R.string.app_components_common_divider_tech),
                    checked = divider,
                    onCheckedChange = { divider = it },
                )
            },
            baseListItemGlobalCustomization(4) {
                CustomizationSwitchItem(
                    label = stringResource(R.string.app_components_listItem_background_tech),
                    checked = background,
                    onCheckedChange = { background = it },
                )
            },
            baseListItemGlobalCustomization(6) {
                CustomizationSwitchItem(
                    label = stringResource(R.string.app_components_common_edgeToEdge_tech),
                    checked = edgeToEdge,
                    onCheckedChange = { edgeToEdge = it },
                )
            }
        )
        BaseListItemGlobalCustomizations(state = state, extraCustomizations = extraCustomizations)
    }
}

@OptIn(ExperimentalOudsApi::class)
@Composable
private fun ListItemDemoContent(state: ListItemDemoState) {
    with(state) {
        val modifier = if (edgeToEdge) Modifier else Modifier.padding(horizontal = OudsTheme.grids.margin)
        if (clickable) {
            OudsListItem(
                modifier = modifier,
                onClick = {},
                indicator = indicator.toOudsListItemIndicator(),
                label = label,
                verticalAlignment = verticalAlignment,
                overline = overline,
                extraLabel = extraLabel,
                description = description,
                leading = baseListItemDemoLeading(state = state),
                trailing = baseListItemDemoTrailing(state = state),
                divider = divider,
                background = background,
                helperText = helperText,
                boldLabel = boldLabel,
                enabled = enabled,
                edgeToEdge = edgeToEdge
            )
        } else {
            OudsListItem(
                modifier = modifier,
                label = label,
                verticalAlignment = verticalAlignment,
                overline = overline,
                extraLabel = extraLabel,
                description = description,
                leading = baseListItemDemoLeading(state = state),
                trailing = baseListItemDemoTrailing(state = state),
                divider = divider,
                background = background,
                helperText = helperText,
                boldLabel = boldLabel,
                enabled = enabled,
                edgeToEdge = edgeToEdge
            )
        }
    }
}

private fun Code.Builder.listItemDemoCodeSnippet(state: ListItemDemoState, themeDrawableResources: ThemeDrawableResources) {
    with(state) {
        val functionName = when (size) {
            Size.Default -> "OudsListItem"
            Size.Small -> "OudsSmallListItem"
        }
        functionCall(functionName) {
            baseListItemArguments(state, themeDrawableResources)

            if (!divider) typedArgument("divider", divider)
            if (background) typedArgument("background", background)
            if (!edgeToEdge) typedArgument("edgeToEdge", edgeToEdge)
        }
    }
}
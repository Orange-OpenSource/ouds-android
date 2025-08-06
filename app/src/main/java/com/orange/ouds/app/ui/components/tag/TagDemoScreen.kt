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

package com.orange.ouds.app.ui.components.tag

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.Component
import com.orange.ouds.app.ui.components.labelArgument
import com.orange.ouds.app.ui.components.painterArgument
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.composable.CustomizationDropdownMenu
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChips
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextField
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.app.ui.utilities.formattedName
import com.orange.ouds.core.component.OudsTag
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview

@Composable
fun TagDemoScreen() {
    val state = rememberTagDemoState()
    DemoScreen(
        description = stringResource(id = Component.Tag.descriptionRes),
        bottomSheetContent = { TagDemoBottomSheetContent(state = state) },
        codeSnippet = { tagDemoCodeSnippet(state = state) },
        demoContent = { TagDemoContent(state = state) }
    )
}

@Composable
private fun TagDemoBottomSheetContent(state: TagDemoState) {
    with(state) {
        CustomizationFilterChips(
            label = stringResource(R.string.app_components_common_hierarchy_label),
            chipLabels = OudsTag.Hierarchy.entries.map { it.name },
            selectedChipIndex = OudsTag.Hierarchy.entries.indexOf(hierarchy),
            onSelectionChange = { id -> hierarchy = OudsTag.Hierarchy.entries[id] }
        )
        val statuses = OudsTag.Status.entries
        CustomizationDropdownMenu(
            modifier = Modifier.padding(top = OudsTheme.spaces.fixed.medium),
            label = stringResource(id = R.string.app_components_common_status_label),
            itemLabels = statuses.map { it.formattedName },
            selectedItemIndex = statuses.indexOf(status),
            onSelectionChange = { status = statuses[it] },
            itemLeadingIcons = statuses.map { status ->
                {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(status.backgroundColor(hierarchy))
                    )
                }
            }
        )
        CustomizationFilterChips(
            modifier = Modifier.padding(top = OudsTheme.spaces.fixed.medium),
            label = stringResource(R.string.app_components_common_layout_label),
            chipLabels = TagDemoState.Layout.entries.map { stringResource(it.labelRes) },
            selectedChipIndex = TagDemoState.Layout.entries.indexOf(layout),
            onSelectionChange = { id -> layout = TagDemoState.Layout.entries[id] }
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_tag_loading_label),
            checked = loading,
            onCheckedChange = { loading = it },
        )
        CustomizationFilterChips(
            modifier = Modifier.padding(top = OudsTheme.spaces.fixed.medium),
            label = stringResource(R.string.app_components_tag_shape_label),
            chipLabels = OudsTag.Shape.entries.map { it.name },
            selectedChipIndex = OudsTag.Shape.entries.indexOf(shape),
            onSelectionChange = { id -> shape = OudsTag.Shape.entries[id] }
        )
        CustomizationFilterChips(
            modifier = Modifier.padding(top = OudsTheme.spaces.fixed.medium),
            label = stringResource(R.string.app_components_common_size_label),
            chipLabels = OudsTag.Size.entries.map { it.name },
            selectedChipIndex = OudsTag.Size.entries.indexOf(size),
            onSelectionChange = { id -> size = OudsTag.Size.entries[id] }
        )
        CustomizationTextField(
            modifier = Modifier.padding(top = OudsTheme.spaces.fixed.medium),
            label = stringResource(R.string.app_components_common_label_label),
            value = label,
            onValueChange = { value -> label = value }
        )
    }
}

@Composable
private fun TagDemoContent(state: TagDemoState) {
    with(state) {
        val loading = if (loading) OudsTag.Loading(null) else null
        if (layout == TagDemoState.Layout.TextAndIcon) {
            OudsTag(
                icon = OudsTag.Icon(painter = painterResource(R.drawable.ic_heart)),
                label = label,
                hierarchy = hierarchy,
                status = status,
                size = size,
                shape = shape,
                loading = loading,
            )
        } else {
            OudsTag(
                hasBullet = layout == TagDemoState.Layout.TextAndBullet,
                label = label,
                hierarchy = hierarchy,
                status = status,
                size = size,
                shape = shape,
                loading = loading,
            )
        }
    }
}

private fun Code.Builder.tagDemoCodeSnippet(state: TagDemoState) {
    with(state) {
        functionCall(OudsTag::class.simpleName.orEmpty()) {
            when(layout) {
                TagDemoState.Layout.TextAndBullet -> typedArgument("hasBullet", true)
                TagDemoState.Layout.TextAndIcon -> {
                    constructorCallArgument<OudsTag.Icon>("icon") {
                        painterArgument(R.drawable.ic_heart)
                    }
                }
                TagDemoState.Layout.TextOnly -> {}
            }
            labelArgument(label)
            typedArgument("hierarchy", hierarchy)
            typedArgument("shape", shape)
            typedArgument("size", size)
            typedArgument("status", status)
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewTagDemoScreen() = OudsPreview {
    TagDemoScreen()
}

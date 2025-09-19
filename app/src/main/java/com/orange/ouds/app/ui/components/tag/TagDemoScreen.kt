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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.labelArgument
import com.orange.ouds.app.ui.components.painterArgument
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.composable.CustomizationDropdownMenu
import com.orange.ouds.app.ui.utilities.composable.CustomizationDropdownMenuItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChips
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextField
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.app.ui.utilities.formattedName
import com.orange.ouds.core.component.OudsTag
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.theme.OudsVersion

@Composable
fun TagDemoScreen() {
    val state = rememberTagDemoState()
    DemoScreen(
        bottomSheetContent = { TagDemoBottomSheetContent(state = state) },
        codeSnippet = { tagDemoCodeSnippet(state = state) },
        demoContent = { TagDemoContent(state = state) },
        version = OudsVersion.Component.Tag
    )
}

@Composable
private fun TagDemoBottomSheetContent(state: TagDemoState) {
    with(state) {
        CustomizationFilterChips(
            applyTopPadding = false,
            label = stringResource(R.string.app_components_tag_hierarchy_label),
            chipLabels = OudsTag.Hierarchy.entries.map { it.name },
            selectedChipIndex = OudsTag.Hierarchy.entries.indexOf(hierarchy),
            onSelectionChange = { id -> hierarchy = OudsTag.Hierarchy.entries[id] }
        )
        val statuses = OudsTag.Status.entries
        CustomizationDropdownMenu(
            applyTopPadding = true,
            label = stringResource(id = R.string.app_components_common_status_label),
            items = statuses.map { status ->
                CustomizationDropdownMenuItem(
                    label = status.formattedName,
                    leadingIcon = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    when (hierarchy) {
                                        OudsTag.Hierarchy.Emphasized -> status.color()
                                        OudsTag.Hierarchy.Muted -> status.mutedColor()
                                    }
                                )
                        )
                    },
                    enabled = !(status == OudsTag.Status.Disabled && hasLoader)
                )
            },
            selectedItemIndex = statuses.indexOf(status),
            onSelectionChange = { status = statuses[it] }
        )
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_layout_label),
            chipLabels = TagDemoState.Layout.entries.map { stringResource(it.labelRes) },
            selectedChipIndex = TagDemoState.Layout.entries.indexOf(layout),
            onSelectionChange = { id -> layout = TagDemoState.Layout.entries[id] }
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_common_loader_label),
            checked = hasLoader,
            onCheckedChange = { hasLoader = it },
            enabled = loaderSwitchEnabled
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_tag_roundedCorners_label),
            checked = roundedCorners,
            onCheckedChange = { roundedCorners = it },
        )
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_size_label),
            chipLabels = OudsTag.Size.entries.map { it.name },
            selectedChipIndex = OudsTag.Size.entries.indexOf(size),
            onSelectionChange = { id -> size = OudsTag.Size.entries[id] }
        )
        CustomizationTextField(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_label_label),
            value = label,
            onValueChange = { value -> label = value }
        )
    }
}

@Composable
private fun TagDemoContent(state: TagDemoState) {
    with(state) {
        val content: @Composable (OudsTag.Size, Boolean) -> Unit = { size, visible ->
            val loader = if (hasLoader) OudsTag.Loader(null) else null
            val icon = when (layout) {
                TagDemoState.Layout.TextOnly -> null
                TagDemoState.Layout.TextAndBullet -> OudsTag.Icon.Bullet
                TagDemoState.Layout.TextAndIcon -> OudsTag.Icon(painter = painterResource(R.drawable.ic_heart))
            }
            val alpha = if (visible) 1f else 0f
            OudsTag(
                modifier = Modifier.alpha(alpha),
                icon = icon,
                label = label,
                hierarchy = hierarchy,
                status = status,
                size = size,
                roundedCorners = roundedCorners,
                loader = loader,
            )
        }

        content(size, true)
        // Reserve space to avoid changing the height of the demo box when switching between sizes
        content(OudsTag.Size.Default, false)
    }
}

private fun Code.Builder.tagDemoCodeSnippet(state: TagDemoState) {
    with(state) {
        functionCall(OudsTag::class.simpleName.orEmpty()) {
            when (layout) {
                TagDemoState.Layout.TextOnly -> {}
                TagDemoState.Layout.TextAndBullet -> typedArgument("icon", OudsTag.Icon.Bullet)
                TagDemoState.Layout.TextAndIcon -> {
                    constructorCallArgument<OudsTag.Icon>("icon") {
                        painterArgument(R.drawable.ic_heart)
                    }
                }
            }
            labelArgument(label)
            typedArgument("hierarchy", hierarchy)
            typedArgument("roundedCorners", roundedCorners)
            typedArgument("size", size)
            typedArgument("status", status)
            if (hasLoader) {
                constructorCallArgument<OudsTag.Loader>("loader") {
                    typedArgument("progress", null)
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewTagDemoScreen() = OudsPreview {
    TagDemoScreen()
}

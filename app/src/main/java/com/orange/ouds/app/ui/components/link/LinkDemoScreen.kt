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

package com.orange.ouds.app.ui.components.link

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.Component
import com.orange.ouds.app.ui.components.coloredBoxCall
import com.orange.ouds.app.ui.components.enabledArgument
import com.orange.ouds.app.ui.components.labelArgument
import com.orange.ouds.app.ui.components.onClickArgument
import com.orange.ouds.app.ui.components.painterArgument
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChips
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextField
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.core.component.OudsLink
import com.orange.ouds.core.component.OudsLinkArrow
import com.orange.ouds.core.component.OudsLinkIcon
import com.orange.ouds.core.component.OudsLinkSize
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.theme.OudsVersion

@Composable
fun LinkDemoScreen() {
    val state = rememberLinkDemoState()
    DemoScreen(
        description = stringResource(id = Component.Link.descriptionRes),
        bottomSheetContent = { LinkDemoBottomSheetContent(state = state) },
        codeSnippet = { linkDemoCodeSnippet(state = state) },
        demoContent = { LinkDemoContent(state = state) },
        demoContentOnColoredBox = state.onColoredBox,
        version = OudsVersion.Component.Link
    )
}

@Composable
private fun LinkDemoBottomSheetContent(state: LinkDemoState) {
    with(state) {
        CustomizationSwitchItem(
            label = stringResource(R.string.app_common_enabled_label),
            checked = enabled,
            onCheckedChange = { enabled = it },
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_common_onColoredBackground_label),
            checked = onColoredBox,
            onCheckedChange = { onColoredBox = it },
        )
        val sizes = OudsLinkSize.entries
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_size_label),
            chipLabels = sizes.map { it.name },
            selectedChipIndex = sizes.indexOf(size),
            onSelectionChange = { id -> size = sizes[id] }
        )
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_layout_label),
            chipLabels = LinkDemoState.Layout.entries.map { stringResource(it.labelRes) },
            selectedChipIndex = LinkDemoState.Layout.entries.indexOf(layout),
            onSelectionChange = { id -> layout = LinkDemoState.Layout.entries[id] }
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
private fun LinkDemoContent(state: LinkDemoState) {
    with(state) {
        when (layout) {
            LinkDemoState.Layout.TextOnly -> {
                OudsLink(
                    label = label,
                    onClick = {},
                    enabled = enabled,
                    size = size
                )
            }
            LinkDemoState.Layout.TextAndIcon -> {
                OudsLink(
                    label = label,
                    icon = OudsLinkIcon(painterResource(id = R.drawable.ic_heart)),
                    onClick = {},
                    enabled = enabled,
                    size = size
                )
            }
            LinkDemoState.Layout.ArrowBack -> {
                OudsLink(
                    label = label,
                    arrow = OudsLinkArrow.Back,
                    onClick = {},
                    enabled = enabled,
                    size = size
                )
            }
            LinkDemoState.Layout.ArrowNext -> {
                OudsLink(
                    label = label,
                    arrow = OudsLinkArrow.Next,
                    onClick = {},
                    enabled = enabled,
                    size = size
                )
            }
        }
    }
}

private fun Code.Builder.linkDemoCodeSnippet(state: LinkDemoState) {
    with(state) {
        coloredBoxCall(onColoredBox) {
            functionCall("OudsLink") {
                labelArgument(label)
                when (layout) {
                    LinkDemoState.Layout.TextOnly -> {}
                    LinkDemoState.Layout.TextAndIcon -> {
                        constructorCallArgument<OudsLinkIcon>("icon") {
                            painterArgument(R.drawable.ic_heart)
                        }
                    }
                    LinkDemoState.Layout.ArrowBack -> typedArgument("arrow", OudsLinkArrow.Back)
                    LinkDemoState.Layout.ArrowNext -> typedArgument("arrow", OudsLinkArrow.Next)
                }
                onClickArgument()
                enabledArgument(enabled)
                typedArgument("size", size)
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewLinkDemoScreen() = OudsPreview {
    LinkDemoScreen()
}
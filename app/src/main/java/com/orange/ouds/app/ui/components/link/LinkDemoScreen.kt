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

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.Component
import com.orange.ouds.app.ui.components.coloredBoxCall
import com.orange.ouds.app.ui.components.enabledArgument
import com.orange.ouds.app.ui.components.onClickArgument
import com.orange.ouds.app.ui.components.painterArgument
import com.orange.ouds.app.ui.components.textArgument
import com.orange.ouds.app.ui.utilities.composable.CodeSnippet
import com.orange.ouds.app.ui.utilities.composable.CustomizationBottomSheetScaffold
import com.orange.ouds.app.ui.utilities.composable.CustomizationChoiceChips
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchListItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextField
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.app.ui.utilities.composable.DetailScreenDescription
import com.orange.ouds.app.ui.utilities.composable.LightDarkDemo
import com.orange.ouds.app.ui.utilities.composable.OnColoredBoxDemo
import com.orange.ouds.core.component.OudsLink
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LinkDemoScreen() = DemoScreen(rememberLinkDemoState()) {
    CustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
        bottomSheetContent = {
            CustomizationSwitchListItem(
                label = stringResource(R.string.app_common_enabled_label),
                checked = enabled,
                onCheckedChange = { enabled = it },
            )
            CustomizationSwitchListItem(
                label = stringResource(R.string.app_components_common_onColoredBackground_label),
                checked = onColoredBox,
                onCheckedChange = { onColoredBox = it },
            )
            val sizes = OudsLink.Size.entries
            CustomizationChoiceChips(
                modifier = Modifier.padding(top = OudsTheme.spaces.fixed.medium),
                label = stringResource(R.string.app_components_link_size_label),
                chipsLabels = sizes.map { it.name },
                selectedChipIndex = sizes.indexOf(size),
                onSelectionChange = { id -> size = sizes[id] }
            )
            CustomizationChoiceChips(
                modifier = Modifier.padding(top = OudsTheme.spaces.fixed.medium),
                label = stringResource(R.string.app_components_common_layout_label),
                chipsLabels = LinkDemoState.Layout.entries.map { stringResource(it.labelRes) },
                selectedChipIndex = LinkDemoState.Layout.entries.indexOf(layout),
                onSelectionChange = { id -> layout = LinkDemoState.Layout.entries[id] }
            )
            CustomizationTextField(
                modifier = Modifier.padding(top = OudsTheme.spaces.fixed.medium),
                label = stringResource(R.string.app_components_common_text_label),
                value = text,
                onValueChange = { value -> text = value })
        }
    ) {
        DetailScreenDescription(
            modifier = Modifier.padding(horizontal = OudsTheme.grids.margin, vertical = OudsTheme.spaces.fixed.medium),
            descriptionRes = Component.Link.descriptionRes
        )
        if (!onColoredBox) {
            LightDarkDemo {
                LinkDemo(state = this@DemoScreen)
            }
        } else {
            OnColoredBoxDemo {
                LinkDemo(state = this@DemoScreen)
            }
        }

        LinkDemoCodeSnippet(
            state = this@DemoScreen,
            modifier = Modifier
                .padding(horizontal = OudsTheme.grids.margin, vertical = OudsTheme.spaces.fixed.medium)
                .padding(top = OudsTheme.spaces.fixed.medium)
        )
    }
}

@Composable
private fun LinkDemo(state: LinkDemoState) {
    with(state) {
        when (layout) {
            LinkDemoState.Layout.TextOnly -> {
                OudsLink(
                    text = text,
                    icon = null,
                    onClick = {},
                    enabled = enabled,
                    size = size
                )
            }
            LinkDemoState.Layout.IconAndText -> {
                OudsLink(
                    text = text,
                    icon = OudsLink.Icon(painterResource(id = R.drawable.ic_heart)),
                    onClick = {},
                    enabled = enabled,
                    size = size
                )
            }
            LinkDemoState.Layout.ArrowBack -> {
                OudsLink(
                    text = text,
                    arrow = OudsLink.Arrow.Back,
                    onClick = {},
                    enabled = enabled,
                    size = size
                )
            }
            LinkDemoState.Layout.ArrowNext -> {
                OudsLink(
                    text = text,
                    arrow = OudsLink.Arrow.Next,
                    onClick = {},
                    enabled = enabled,
                    size = size
                )
            }
        }
    }
}

@Composable
private fun LinkDemoCodeSnippet(state: LinkDemoState, modifier: Modifier = Modifier) {
    CodeSnippet(modifier = modifier) {
        with(state) {
            coloredBoxCall(onColoredBox) {
                functionCall(OudsLink::class.simpleName.orEmpty()) {
                    textArgument(text)
                    when (layout) {
                        LinkDemoState.Layout.TextOnly -> {}
                        LinkDemoState.Layout.IconAndText -> {
                            constructorCallArgument<OudsLink.Icon>("icon") {
                                painterArgument(R.drawable.ic_heart)
                            }
                        }
                        LinkDemoState.Layout.ArrowBack -> typedArgument("arrow", OudsLink.Arrow.Back)
                        LinkDemoState.Layout.ArrowNext -> typedArgument("arrow", OudsLink.Arrow.Next)
                    }
                    onClickArgument()
                    enabledArgument(enabled)
                    typedArgument("size", size)
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewLinkDemoScreen() = OudsPreview {
    LinkDemoScreen()
}
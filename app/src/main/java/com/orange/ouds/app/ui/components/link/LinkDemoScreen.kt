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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.Component
import com.orange.ouds.app.ui.utilities.composable.CustomizationBottomSheetScaffold
import com.orange.ouds.app.ui.utilities.composable.CustomizationChoiceChipsColumn
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchListItem
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.app.ui.utilities.composable.DetailScreenDescription
import com.orange.ouds.core.component.coloredbox.OudsColoredBox
import com.orange.ouds.core.component.link.OudsLink
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.OudsThemeTweak
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.utilities.UiModePreviews

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
            CustomizationChoiceChipsColumn(
                modifier = Modifier.padding(top = OudsTheme.spaces.fixed.medium),
                label = stringResource(R.string.app_components_link_size_label),
                chipsLabels = sizes.map { it.name },
                selectedChipIndex = sizes.indexOf(size),
                onSelectionChange = { id -> size = sizes[id] }
            )
            CustomizationChoiceChipsColumn(
                modifier = Modifier.padding(top = OudsTheme.spaces.fixed.medium),
                label = stringResource(R.string.app_components_common_layout_label),
                chipsLabels = LinkDemoState.Layout.entries.map { stringResource(it.labelRes) },
                selectedChipIndex = LinkDemoState.Layout.entries.indexOf(layout),
                onSelectionChange = { id -> layout = LinkDemoState.Layout.entries[id] }
            )
        }
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            DetailScreenDescription(
                modifier = Modifier.padding(all = OudsTheme.spaces.fixed.medium),
                descriptionRes = Component.Link.descriptionRes
            )
            LinkDemo(state = this@DemoScreen)
            if (!onColoredBox) {
                OudsThemeTweak(OudsTheme.Tweak.Invert) {
                    LinkDemo(state = this@DemoScreen)
                }
            }
        }
    }
}

@Composable
private fun LinkDemo(state: LinkDemoState) {
    LinkDemoBox(
        colored = state.onColoredBox,
        modifier = Modifier
            .padding(all = OudsTheme.spaces.fixed.medium)
            .fillMaxWidth()
    ) {
        val text = stringResource(id = R.string.app_components_link_label)
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
}

@Composable
private fun LinkDemoBox(colored: Boolean, modifier: Modifier = Modifier, content: @Composable BoxScope.() -> Unit) {
    if (colored) {
        OudsColoredBox(
            color = OudsColoredBox.Color.BrandPrimary,
            modifier = modifier,
            contentAlignment = Alignment.Center,
            content = content
        )
    } else {
        Box(
            modifier = Modifier
                .background(OudsTheme.colorScheme.background.primary)
                .then(other = modifier),
            contentAlignment = Alignment.Center,
            content = content
        )
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewLinkDemoScreen() = OudsPreview {
    LinkDemoScreen()
}
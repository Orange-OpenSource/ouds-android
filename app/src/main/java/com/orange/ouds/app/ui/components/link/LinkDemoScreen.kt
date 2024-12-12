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
import com.orange.ouds.core.component.link.OudsLink
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.OudsThemeTweak
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.utilities.UiModePreviews
import com.orange.ouds.theme.tokens.OudsSpaceKeyToken

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
                enabled = style == OudsLink.Style.Default
            )
            val styles = remember { listOf(OudsLink.Style.Default, OudsLink.Style.Skeleton) }
            CustomizationChoiceChipsColumn(
                modifier = Modifier.padding(top = OudsSpaceKeyToken.Fixed.Medium.value),
                label = stringResource(R.string.app_components_common_style_label),
                chipsLabels = styles.map { it::class.simpleName.orEmpty() },
                selectedChipIndex = styles.indexOf(style),
                onSelectionChange = { id -> style = styles[id] }
            )
            val sizes = remember { listOf(OudsLink.Size.Small, OudsLink.Size.Medium) }
            CustomizationChoiceChipsColumn(
                modifier = Modifier.padding(top = OudsSpaceKeyToken.Fixed.Medium.value),
                label = stringResource(R.string.app_components_link_size_label),
                chipsLabels = sizes.map { it.name },
                selectedChipIndex = sizes.indexOf(size),
                onSelectionChange = { id -> size = sizes[id] }
            )
            CustomizationChoiceChipsColumn(
                modifier = Modifier.padding(top = OudsSpaceKeyToken.Fixed.Medium.value),
                label = stringResource(R.string.app_components_common_layout_label),
                chipsLabels = LinkDemoState.Layout.entries.map { stringResource(it.labelRes) },
                selectedChipIndex = LinkDemoState.Layout.entries.indexOf(layout),
                onSelectionChange = { id -> layout = LinkDemoState.Layout.entries[id] }
            )
        }
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            DetailScreenDescription(
                modifier = Modifier.padding(all = OudsSpaceKeyToken.Fixed.Medium.value),
                descriptionRes = Component.Link.descriptionRes
            )
            LinkDemo(state = this@DemoScreen)
            OudsThemeTweak(OudsTheme.Tweak.Invert) {
                LinkDemo(state = this@DemoScreen)
            }
        }
    }
}

@Composable
private fun LinkDemo(state: LinkDemoState) {
    Box(
        modifier = Modifier
            .background(OudsTheme.colorScheme.backgroundColors.primary)
            .padding(all = OudsSpaceKeyToken.Fixed.Medium.value)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
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
                        style = style,
                        size = size
                    )
                }
                LinkDemoState.Layout.IconAndText -> {
                    OudsLink(
                        text = text,
                        icon = OudsLink.Icon(painterResource(id = R.drawable.ic_heart)),
                        onClick = {},
                        enabled = enabled,
                        style = style,
                        size = size
                    )
                }
                LinkDemoState.Layout.ArrowBack -> {
                    OudsLink(
                        text = text,
                        arrow = OudsLink.Arrow.Back,
                        onClick = {},
                        enabled = enabled,
                        style = style,
                        size = size
                    )
                }
                LinkDemoState.Layout.ArrowNext -> {
                    OudsLink(
                        text = text,
                        arrow = OudsLink.Arrow.Next,
                        onClick = {},
                        enabled = enabled,
                        style = style,
                        size = size
                    )
                }
            }
        }
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewLinkDemoScreen() = OudsPreview {
    LinkDemoScreen()
}
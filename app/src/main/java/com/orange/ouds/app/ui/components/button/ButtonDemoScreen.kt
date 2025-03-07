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

package com.orange.ouds.app.ui.components.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.orange.ouds.app.ui.components.coloredBoxCall
import com.orange.ouds.app.ui.components.contentDescriptionArgument
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
import com.orange.ouds.core.component.OudsButton
import com.orange.ouds.core.component.OudsColoredBox
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.OudsThemeTweak
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.utilities.UiModePreviews

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ButtonDemoScreen() = DemoScreen(rememberButtonDemoState()) {
    CustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
        bottomSheetContent = {
            CustomizationSwitchListItem(
                label = stringResource(R.string.app_common_enabled_label),
                checked = enabled,
                onCheckedChange = { enabled = it },
                enabled = enabledSwitchEnabled
            )
            CustomizationSwitchListItem(
                label = stringResource(R.string.app_common_onColoredBackground_label),
                checked = onColoredBox,
                onCheckedChange = { onColoredBox = it },
                enabled = onColoredBoxSwitchEnabled
            )
            CustomizationChoiceChips(
                modifier = Modifier.padding(top = OudsTheme.spaces.fixed.medium),
                label = stringResource(R.string.app_components_button_hierarchy_label),
                chipsLabels = OudsButton.Hierarchy.entries.map { it.name },
                selectedChipIndex = OudsButton.Hierarchy.entries.indexOf(hierarchy),
                onSelectionChange = { id -> hierarchy = OudsButton.Hierarchy.entries[id] }
            )
            val styles = remember {
                listOf(
                    OudsButton.Style.Default,
                    OudsButton.Style.Loading(progress = null),
                )
            }
            CustomizationChoiceChips(
                modifier = Modifier.padding(top = OudsTheme.spaces.fixed.medium),
                label = stringResource(R.string.app_components_common_style_label),
                chipsLabels = styles.map { it::class.simpleName.orEmpty() },
                selectedChipIndex = styles.indexOf(style),
                onSelectionChange = { id -> style = styles[id] }
            )
            CustomizationChoiceChips(
                modifier = Modifier.padding(top = OudsTheme.spaces.fixed.medium),
                label = stringResource(R.string.app_components_common_layout_label),
                chipsLabels = ButtonDemoState.Layout.entries.map { stringResource(it.labelRes) },
                selectedChipIndex = ButtonDemoState.Layout.entries.indexOf(layout),
                onSelectionChange = { id -> layout = ButtonDemoState.Layout.entries[id] }
            )
            CustomizationTextField(
                label = stringResource(R.string.app_components_common_text_label),
                value = text,
                onValueChange = { value -> text = value })
        }
    ) {
        DetailScreenDescription(
            modifier = Modifier.padding(all = OudsTheme.spaces.fixed.medium),
            descriptionRes = Component.Button.descriptionRes
        )
        ButtonDemo(state = this@DemoScreen)
        if (!onColoredBox) {
            OudsThemeTweak(OudsTheme.Tweak.Invert) {
                ButtonDemo(state = this@DemoScreen)
            }
        }
        ButtonDemoCodeSnippet(
            state = this@DemoScreen,
            modifier = Modifier
                .padding(all = OudsTheme.spaces.fixed.medium)
                .padding(top = OudsTheme.spaces.fixed.medium)
        )
    }
}

@Composable
private fun ButtonDemo(state: ButtonDemoState) {
    ButtonDemoBox(
        colored = state.onColoredBox,
        modifier = Modifier
            .padding(all = OudsTheme.spaces.fixed.medium)
            .fillMaxWidth()
    ) {
        val icon = OudsButton.Icon(
            painter = painterResource(id = R.drawable.ic_heart),
            contentDescription = stringResource(id = R.string.app_components_button_icon_a11y)
        )
        with(state) {
            when (layout) {
                ButtonDemoState.Layout.TextOnly -> {
                    OudsButton(
                        text = text,
                        onClick = {},
                        enabled = enabled,
                        style = style,
                        hierarchy = hierarchy
                    )
                }
                ButtonDemoState.Layout.IconAndText -> {
                    OudsButton(
                        icon = icon,
                        text = text,
                        onClick = {},
                        enabled = enabled,
                        style = style,
                        hierarchy = hierarchy
                    )
                }
                ButtonDemoState.Layout.IconOnly -> {
                    OudsButton(
                        icon = icon,
                        onClick = {},
                        enabled = enabled,
                        style = style,
                        hierarchy = hierarchy
                    )
                }
            }
        }
    }
}

@Composable
private fun ButtonDemoBox(colored: Boolean, modifier: Modifier = Modifier, content: @Composable BoxScope.() -> Unit) {
    val contentAlignment = Alignment.Center
    if (colored) {
        OudsColoredBox(
            modifier = modifier,
            color = OudsColoredBox.Color.BrandPrimary,
            contentAlignment = contentAlignment,
            content = content
        )
    } else {
        Box(
            modifier = Modifier
                .background(OudsTheme.colorScheme.background.primary)
                .then(modifier),
            contentAlignment = contentAlignment,
            content = content
        )
    }
}

@Composable
private fun ButtonDemoCodeSnippet(state: ButtonDemoState, modifier: Modifier = Modifier) {
    CodeSnippet(modifier = modifier) {
        with(state) {
            coloredBoxCall(onColoredBox) {
                functionCall(OudsButton::class.simpleName.orEmpty()) {
                    if (layout in listOf(ButtonDemoState.Layout.IconOnly, ButtonDemoState.Layout.IconAndText)) {
                        constructorCallArgument<OudsButton.Icon>("icon") {
                            painterArgument(R.drawable.ic_heart)
                            contentDescriptionArgument(R.string.app_components_button_icon_a11y)
                        }
                    }
                    if (layout in listOf(ButtonDemoState.Layout.TextOnly, ButtonDemoState.Layout.IconAndText)) {
                        textArgument(text)
                    }
                    onClickArgument()
                    enabledArgument(enabled)
                    typedArgument("style", style)
                    typedArgument("hierarchy", hierarchy)
                }
            }
        }
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewButtonDemoScreen() = OudsPreview {
    ButtonDemoScreen()
}
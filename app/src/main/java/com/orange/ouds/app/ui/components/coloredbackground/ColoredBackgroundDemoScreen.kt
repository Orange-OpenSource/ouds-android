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

package com.orange.ouds.app.ui.components.coloredbackground

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.Component
import com.orange.ouds.app.ui.components.onClickArgument
import com.orange.ouds.app.ui.components.textArgument
import com.orange.ouds.app.ui.utilities.composable.CodeSnippet
import com.orange.ouds.app.ui.utilities.composable.CustomizationBottomSheetScaffold
import com.orange.ouds.app.ui.utilities.composable.CustomizationDropdownMenu
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.app.ui.utilities.composable.DetailScreenDescription
import com.orange.ouds.app.ui.utilities.formattedName
import com.orange.ouds.core.component.OudsButton
import com.orange.ouds.core.component.OudsColoredBox
import com.orange.ouds.core.component.OudsLink
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.OudsThemeTweak
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.utilities.UiModePreviews

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColoredBackgroundDemoScreen() = DemoScreen(rememberColoredBackgroundDemoState()) {
    CustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
        bottomSheetContent = {
            val colors = OudsColoredBox.Color.entries
            CustomizationDropdownMenu(
                label = stringResource(id = R.string.app_components_coloredBackground_color_label),
                itemLabels = colors.map { it.formattedName },
                selectedItemIndex = colors.indexOf(color),
                onSelectionChange = { color = colors[it] },
                itemLeadingIcons = colors.map { color ->
                    {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color.value)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .consumeWindowInsets(innerPadding)
                .padding(innerPadding)
        ) {
            DetailScreenDescription(
                modifier = Modifier.padding(all = OudsTheme.spaces.fixed.medium),
                descriptionRes = Component.ColoredBackground.descriptionRes
            )
            ColoredBackgroundDemo(state = this@DemoScreen)
            OudsThemeTweak(OudsTheme.Tweak.Invert) {
                ColoredBackgroundDemo(state = this@DemoScreen)
            }
            ColoredBackgroundDemoCodeSnippet(
                state = this@DemoScreen,
                modifier = Modifier
                    .padding(all = OudsTheme.spaces.fixed.medium)
                    .padding(top = OudsTheme.spaces.fixed.medium)
            )
        }
    }
}

@Composable
private fun ColoredBackgroundDemo(state: ColoredBackgroundDemoState) {
    Box(
        modifier = Modifier
            .background(OudsTheme.colorScheme.background.primary)
            .padding(all = OudsTheme.spaces.fixed.medium)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        with(state) {
            OudsColoredBox(
                modifier = Modifier.fillMaxWidth(),
                color = color
            ) {
                Column(
                    modifier = Modifier
                        .padding(all = OudsTheme.spaces.fixed.medium)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.medium),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = color.formattedName,
                        color = OudsTheme.colorScheme.content.default
                    )
                    OudsButton(
                        text = stringResource(id = R.string.app_components_button_label),
                        onClick = {}
                    )
                    OudsLink(
                        text = stringResource(id = R.string.app_components_link_label),
                        arrow = OudsLink.Arrow.Next,
                        onClick = {},
                    )
                }
            }
        }
    }
}

@Composable
private fun ColoredBackgroundDemoCodeSnippet(state: ColoredBackgroundDemoState, modifier: Modifier = Modifier) {
    CodeSnippet(modifier = modifier) {
        with(state) {
            functionCall(OudsColoredBox::class.simpleName.orEmpty()) {
                trailingLambda = true
                typedArgument("color", color)
                lambdaArgument(null) {
                    functionCall("Text") {
                        typedArgument("text", color.formattedName)
                        rawArgument("color", "OudsTheme.colorScheme.content.default")
                    }
                    functionCall(OudsButton::class.java.simpleName) {
                        textArgument(R.string.app_components_button_label)
                        onClickArgument {}
                    }
                    functionCall(OudsLink::class.java.simpleName) {
                        textArgument(R.string.app_components_link_label)
                        typedArgument("arrow", OudsLink.Arrow.Next)
                        onClickArgument {}
                    }
                }
            }
        }
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewColoredBackgroundDemoScreen() = OudsPreview {
    ColoredBackgroundDemoScreen()
}

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

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.Component
import com.orange.ouds.app.ui.components.labelArgument
import com.orange.ouds.app.ui.components.onClickArgument
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.composable.CustomizationDropdownMenu
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.app.ui.utilities.formattedName
import com.orange.ouds.core.component.OudsButton
import com.orange.ouds.core.component.OudsColoredBox
import com.orange.ouds.core.component.OudsLink
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview

@Composable
fun ColoredBackgroundDemoScreen() {
    val state = rememberColoredBackgroundDemoState()
    DemoScreen(
        description = stringResource(id = Component.ColoredBackground.descriptionRes),
        bottomSheetContent = { ColoredBackgroundDemoBottomSheetContent(state = state) },
        codeSnippet = { coloredBackgroundDemoCodeSnippet(state = state) },
        demoContent = { ColoredBackgroundDemoContent(state = state) }
    )
}

@Composable
private fun ColoredBackgroundDemoBottomSheetContent(state: ColoredBackgroundDemoState) {
    with(state) {
        val colors = OudsColoredBox.Color.entries.filter { it.mode.isSupported }
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
}

@Composable
private fun ColoredBackgroundDemoContent(state: ColoredBackgroundDemoState) {
    with(state) {
        if (!color.mode.isSupported) {
            Toast.makeText(
                LocalContext.current,
                stringResource(id = R.string.app_components_coloredBackground_unsupportedColor_text, color.formattedName),
                Toast.LENGTH_LONG
            ).show()
            color = ColoredBackgroundDemoStateDefaults.Color
        }
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
                    label = stringResource(id = R.string.app_components_button_label),
                    onClick = {}
                )
                OudsLink(
                    label = stringResource(id = R.string.app_components_link_label),
                    arrow = OudsLink.Arrow.Next,
                    onClick = {},
                )
            }
        }
    }
}

private fun Code.Builder.coloredBackgroundDemoCodeSnippet(state: ColoredBackgroundDemoState) {
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
                    labelArgument(R.string.app_components_button_label)
                    onClickArgument {}
                }
                functionCall(OudsLink::class.java.simpleName) {
                    labelArgument(R.string.app_components_link_label)
                    typedArgument("arrow", OudsLink.Arrow.Next)
                    onClickArgument {}
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewColoredBackgroundDemoScreen() = OudsPreview {
    ColoredBackgroundDemoScreen()
}

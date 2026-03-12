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

package com.orange.ouds.app.ui.components.bottomsheet

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.composable.AppPreview
import com.orange.ouds.app.ui.utilities.composable.CodeSnippet
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextInput
import com.orange.ouds.app.ui.utilities.composable.DetailScreenDescription
import com.orange.ouds.app.ui.utilities.composable.Screen
import com.orange.ouds.app.ui.utilities.composable.ScreenMainContentColumn
import com.orange.ouds.core.component.OudsBottomSheetScaffold
import com.orange.ouds.core.theme.OudsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetScaffoldDemoScreen() {
    val state = rememberBottomSheetDemoState()
    with(state) {
        Screen {
            OudsBottomSheetScaffold(
                sheetPeekHeight = sheetPeekHeight,
                sheetSwipeEnabled = sheetSwipeEnabled,
                sheetDragHandle = sheetDragHandle,
                sheetContent = { BottomSheetContent() }
            ) { innerPadding ->
                ScreenMainContentColumn(paddingValues = innerPadding) {
                    DetailScreenDescription(
                        modifier = Modifier.padding(horizontal = OudsTheme.grids.margin, vertical = OudsTheme.spaces.fixed.medium),
                        description = stringResource(id = R.string.app_components_bottomSheet_bottomSheetScaffold_description_text)
                    )
                    BottomSheetScaffoldCustomization(state = state)
                    CodeSnippet(
                        modifier = Modifier
                            .padding(horizontal = OudsTheme.grids.margin, vertical = OudsTheme.spaces.fixed.medium)
                            .padding(top = OudsTheme.spaces.fixed.medium),
                        init = { bottomSheetScaffoldDemoCodeSnippet(state = state) }
                    )
                }
            }
        }
    }
}

@Composable
private fun BottomSheetContent() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = OudsTheme.spaces.fixed.medium,
                horizontal = OudsTheme.grids.margin
            ),
        text = "Bottom sheet content."
    )
}

@Composable
private fun BottomSheetScaffoldCustomization(state: BottomSheetDemoState) {
    with(state) {
        val sheetPeakHeightForDisplay = sheetPeekHeight.value.toInt().toString()
        CustomizationTextInput(
            applyTopPadding = false,
            label = stringResource(R.string.app_components_bottomSheet_sheetPeekHeight_tech),
            value = TextFieldValue(
                text = sheetPeakHeightForDisplay,
                selection = TextRange(sheetPeakHeightForDisplay.length)
            ),
            onValueChange = { value ->
                val filteredValue = value.text.filter { it.isDigit() }.take(3)
                sheetPeekHeight = if (filteredValue.isEmpty()) {
                    0.dp
                } else {
                    filteredValue.toInt().dp
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            suffix = "dp"
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_bottomSheet_sheetDragHandle_tech),
            checked = sheetDragHandle,
            onCheckedChange = { sheetDragHandle = it },
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_bottomSheet_sheetSwipeEnabled_tech),
            checked = sheetSwipeEnabled,
            onCheckedChange = { sheetSwipeEnabled = it },
        )
    }
}

private fun Code.Builder.bottomSheetScaffoldDemoCodeSnippet(state: BottomSheetDemoState) {
    functionCall("OudsBottomSheetScaffold") {
        typedArgument("sheetPeekHeight", state.sheetPeekHeight)
        typedArgument("sheetDragHandle", state.sheetDragHandle)
        typedArgument("sheetSwipeEnabled", state.sheetSwipeEnabled)
        lambdaArgument("sheetContent") {
            comment("sheet content")
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewBottomSheetDemoScreen() = AppPreview {
    BottomSheetScaffoldDemoScreen()
}
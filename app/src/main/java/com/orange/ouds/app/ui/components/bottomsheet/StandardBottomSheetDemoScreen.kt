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

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChip
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChips
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextInput
import com.orange.ouds.app.ui.utilities.composable.DetailScreenDescription
import com.orange.ouds.app.ui.utilities.composable.Screen
import com.orange.ouds.app.ui.utilities.composable.ScreenMainContentColumn
import com.orange.ouds.app.ui.utilities.toNumberString
import com.orange.ouds.app.ui.utilities.toSentenceCase
import com.orange.ouds.core.component.OudsBottomSheetScaffold
import com.orange.ouds.core.theme.OudsTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardBottomSheetDemoScreen() {
    val state = rememberStandardBottomSheetDemoState()
    val scaffoldState = rememberBottomSheetScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    with(state) {
        Screen {
            OudsBottomSheetScaffold(
                scaffoldState = scaffoldState,
                sheetPeekHeight = sheetPeekHeight,
                sheetSwipeEnabled = sheetSwipeEnabled,
                sheetDragHandle = sheetDragHandle,
                sheetContent = { BottomSheetDemoContent() }
            ) { innerPadding ->
                ScreenMainContentColumn(paddingValues = innerPadding) {
                    DetailScreenDescription(
                        modifier = Modifier.padding(horizontal = OudsTheme.grids.margin, vertical = OudsTheme.spaces.fixed.medium),
                        description = stringResource(id = R.string.app_components_bottomSheet_standardBottomSheet_description_text)
                    )
                    StandardBottomSheetDemoCustomization(
                        state = state,
                        scaffoldState = scaffoldState,
                        coroutineScope = coroutineScope
                    )
                    CodeSnippet(
                        modifier = Modifier
                            .padding(horizontal = OudsTheme.grids.margin, vertical = OudsTheme.spaces.fixed.medium)
                            .padding(top = OudsTheme.spaces.fixed.medium),
                        init = { standardBottomSheetDemoCodeSnippet(state = state) }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun StandardBottomSheetDemoCustomization(state: StandardBottomSheetDemoState, scaffoldState: BottomSheetScaffoldState, coroutineScope: CoroutineScope) {
    with(state) {
        CustomizationFilterChips(
            applyTopPadding = false,
            label = stringResource(R.string.app_components_bottomSheet_standardBottomSheet_sheetValue_tech),
            chips = StandardBottomSheetDemoState.SheetValue.entries.map { CustomizationFilterChip(it.name.toSentenceCase()) },
            selectedChipIndex = when (scaffoldState.bottomSheetState.targetValue) {
                SheetValue.Hidden, SheetValue.PartiallyExpanded -> StandardBottomSheetDemoState.SheetValue.entries.indexOf(StandardBottomSheetDemoState.SheetValue.PartiallyExpanded)
                SheetValue.Expanded -> StandardBottomSheetDemoState.SheetValue.entries.indexOf(StandardBottomSheetDemoState.SheetValue.Expanded)
            },
            onSelectionChange = { index ->
                sheetValue = StandardBottomSheetDemoState.SheetValue.entries[index]
                coroutineScope.launch {
                    when (sheetValue) {
                        StandardBottomSheetDemoState.SheetValue.Expanded -> scaffoldState.bottomSheetState.expand()
                        StandardBottomSheetDemoState.SheetValue.PartiallyExpanded -> scaffoldState.bottomSheetState.partialExpand()
                    }
                }
            },
            isInBottomSheet = false
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_bottomSheet_standardBottomSheet_sheetDragHandle_tech),
            checked = sheetDragHandle,
            onCheckedChange = { sheetDragHandle = it },
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_bottomSheet_standardBottomSheet_sheetSwipeEnabled_tech),
            checked = sheetSwipeEnabled,
            onCheckedChange = { sheetSwipeEnabled = it },
        )
        CustomizationTextInput(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_bottomSheet_standardBottomSheet_sheetPeekHeight_tech),
            value = TextFieldValue(
                text = sheetPeekHeightDisplayValue,
                selection = TextRange(sheetPeekHeightDisplayValue.length)
            ),
            onValueChange = { textFieldValue ->
                val text = textFieldValue.text.filter { it.isDigit() }.take(3)
                sheetPeekHeight = (text.toIntOrNull() ?: 0).dp
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            suffix = stringResource(R.string.app_components_bottomSheet_standardBottomSheet_sheetPeekHeightSuffix_tech),
            resetValue = BottomSheetDefaults.SheetPeekHeight.toNumberString()
        )
    }
}

private fun Code.Builder.standardBottomSheetDemoCodeSnippet(state: StandardBottomSheetDemoState) {
    functionCall("OudsBottomSheetScaffold") {
        trailingLambda = true
        typedArgument("sheetPeekHeight", state.sheetPeekHeight)
        typedArgument("sheetDragHandle", state.sheetDragHandle)
        typedArgument("sheetSwipeEnabled", state.sheetSwipeEnabled)
        lambdaArgument("sheetContent") {
            comment("sheet content")
        }
        lambdaArgument(null) {
            comment("main content")
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewStandardBottomSheetDemoScreen() = AppPreview {
    StandardBottomSheetDemoScreen()
}
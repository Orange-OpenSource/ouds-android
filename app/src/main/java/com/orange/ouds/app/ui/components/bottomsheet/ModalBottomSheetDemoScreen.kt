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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.composable.AppPreview
import com.orange.ouds.app.ui.utilities.composable.CodeSnippet
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.DetailScreenDescription
import com.orange.ouds.app.ui.utilities.composable.Screen
import com.orange.ouds.app.ui.utilities.consumeTopBarsTopWindowInsets
import com.orange.ouds.app.ui.utilities.topBarsTopPadding
import com.orange.ouds.core.component.OudsButton
import com.orange.ouds.core.component.OudsModalBottomSheet
import com.orange.ouds.core.theme.OudsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalBottomSheetDemoScreen() {
    val state = rememberModalBottomSheetDemoState()
    var modalBottomSheetVisible by rememberSaveable { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    with(state) {
        Screen {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .consumeTopBarsTopWindowInsets()
                    .padding(top = topBarsTopPadding, bottom = OudsTheme.spaces.fixed.medium)
            ) {
                DetailScreenDescription(
                    modifier = Modifier.padding(horizontal = OudsTheme.grids.margin, vertical = OudsTheme.spaces.fixed.medium),
                    description = stringResource(id = R.string.app_components_bottomSheet_modalBottomSheet_description_text)
                )
                ModalBottomSheetCustomization(
                    state = state,
                    onButtonClick = { modalBottomSheetVisible = true }
                )
                CodeSnippet(
                    modifier = Modifier
                        .padding(horizontal = OudsTheme.grids.margin, vertical = OudsTheme.spaces.fixed.medium)
                        .padding(top = OudsTheme.spaces.fixed.medium),
                    init = { modalBottomSheetDemoCodeSnippet(state = state) }
                )
                if (modalBottomSheetVisible) {
                    OudsModalBottomSheet(
                        dragHandle = dragHandle,
                        sheetGesturesEnabled = sheetGesturesEnabled,
                        onDismissRequest = { modalBottomSheetVisible = false },
                        sheetState = sheetState
                    ) {
                        BottomSheetDemoContent()
                    }
                }
            }
        }
    }
}

@Composable
private fun ModalBottomSheetCustomization(state: ModalBottomSheetDemoState, onButtonClick: () -> Unit) {
    with(state) {
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_bottomSheet_modalBottomSheet_dragHandle_tech),
            checked = dragHandle,
            onCheckedChange = { dragHandle = it },
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_bottomSheet_modalBottomSheet_sheetGesturesEnabled_tech),
            checked = sheetGesturesEnabled,
            onCheckedChange = { sheetGesturesEnabled = it },
        )
        OudsButton(
            modifier = Modifier.padding(horizontal = OudsTheme.grids.margin, vertical = OudsTheme.spaces.fixed.small),
            label = "Show bottom sheet",
            onClick = onButtonClick
        )
    }
}

private fun Code.Builder.modalBottomSheetDemoCodeSnippet(state: ModalBottomSheetDemoState) {
    functionCall("OudsModalBottomSheet") {
        trailingLambda = true
        typedArgument("dragHandle", state.dragHandle)
        typedArgument("sheetGesturesEnabled", state.sheetGesturesEnabled)
        functionCallArgument("sheetState", "rememberModalBottomSheetState")
        lambdaArgument("onDismissRequest") {
            comment("do something on dismiss")
        }
        lambdaArgument(null) {
            comment("sheet content")
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewBottomSheetDemoScreen() = AppPreview {
    ModalBottomSheetDemoScreen()
}
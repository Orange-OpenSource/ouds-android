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

package com.orange.ouds.app.ui.components.checkbox

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.controlitem.ControlItemDividerCustomization
import com.orange.ouds.app.ui.components.controlitem.ControlItemEnabledCustomization
import com.orange.ouds.app.ui.components.controlitem.ControlItemErrorCustomization
import com.orange.ouds.app.ui.components.controlitem.ControlItemHelperTextCustomization
import com.orange.ouds.app.ui.components.controlitem.ControlItemIconCustomization
import com.orange.ouds.app.ui.components.controlitem.ControlItemInvertedCustomization
import com.orange.ouds.app.ui.components.controlitem.ControlItemReadOnlyCustomization
import com.orange.ouds.app.ui.components.controlitem.ControlItemLabelCustomization
import com.orange.ouds.app.ui.components.controlitem.controlItemArguments
import com.orange.ouds.app.ui.components.onClickArgument
import com.orange.ouds.app.ui.utilities.composable.CodeSnippet
import com.orange.ouds.app.ui.utilities.composable.CustomizationBottomSheetScaffold
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.app.ui.utilities.composable.LightDarkDemo
import com.orange.ouds.core.component.OudsCheckboxItem
import com.orange.ouds.core.component.OudsControlItem
import com.orange.ouds.core.component.OudsTriStateCheckboxItem
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckboxItemDemoScreen(indeterminate: Boolean = false) = DemoScreen(rememberCheckboxItemDemoState()) {
    CustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
        bottomSheetContent = {
            ControlItemIconCustomization()
            ControlItemDividerCustomization()
            ControlItemInvertedCustomization()
            ControlItemEnabledCustomization()
            ControlItemReadOnlyCustomization()
            ControlItemErrorCustomization()
            ControlItemLabelCustomization()
            ControlItemHelperTextCustomization()
        }
    ) {
        LightDarkDemo {
            if (indeterminate) {
                IndeterminateCheckboxItemDemo(state = this@DemoScreen)
            } else {
                CheckboxItemDemo(state = this@DemoScreen)
            }
        }

        CheckboxItemDemoCodeSnippet(
            state = this@DemoScreen,
            indeterminate = indeterminate,
            modifier = Modifier
                .padding(horizontal = OudsTheme.grids.margin, vertical = OudsTheme.spaces.fixed.medium)
                .padding(top = OudsTheme.spaces.fixed.medium)
        )
    }
}

@Composable
private fun CheckboxItemDemo(state: CheckboxItemDemoState) {
    with(state) {
        Column {
            CheckboxIdentifier.entries.forEach { identifier ->
                OudsCheckboxItem(
                    checked = when (identifier) {
                        CheckboxIdentifier.First -> checkedValues.first
                        CheckboxIdentifier.Second -> checkedValues.second
                    },
                    onCheckedChange = { value ->
                        checkedValues = when (identifier) {
                            CheckboxIdentifier.First -> checkedValues.copy(first = value)
                            CheckboxIdentifier.Second -> checkedValues.copy(second = value)
                        }
                    },
                    label = label,
                    helperText = helperText,
                    icon = if (icon) OudsControlItem.Icon(painterResource(id = R.drawable.ic_heart)) else null,
                    divider = divider,
                    inverted = inverted,
                    enabled = enabled,
                    readOnly = readOnly,
                    error = error
                )
            }
        }
    }
}

@Composable
private fun IndeterminateCheckboxItemDemo(state: CheckboxItemDemoState) {
    with(state) {
        Column {
            CheckboxIdentifier.entries.forEach { identifier ->
                OudsTriStateCheckboxItem(
                    state = when (identifier) {
                        CheckboxIdentifier.First -> toggleableStateValues.first
                        CheckboxIdentifier.Second -> toggleableStateValues.second
                    },
                    onClick = {
                        toggleableStateValues = with(toggleableStateValues) {
                            when (identifier) {
                                CheckboxIdentifier.First -> copy(first = first.next())
                                CheckboxIdentifier.Second -> copy(second = second.next())
                            }
                        }
                    },
                    label = label,
                    helperText = helperText,
                    icon = if (icon) OudsControlItem.Icon(painterResource(id = R.drawable.ic_heart)) else null,
                    divider = divider,
                    inverted = inverted,
                    enabled = enabled,
                    readOnly = readOnly,
                    error = error
                )
            }
        }
    }
}

@Composable
private fun CheckboxItemDemoCodeSnippet(state: CheckboxItemDemoState, indeterminate: Boolean, modifier: Modifier = Modifier) {
    val functionName = if (indeterminate) "OudsTriStateCheckboxItem" else "OudsCheckboxItem"
    val lambdaCommentText = "Change state"
    CodeSnippet(modifier = modifier) {
        comment("First checkbox item")
        with(state) {
            functionCall(functionName) {
                if (indeterminate) {
                    typedArgument("state", toggleableStateValues.first)
                    onClickArgument {
                        comment(lambdaCommentText)
                    }
                } else {
                    typedArgument("checked", checkedValues.first)
                    lambdaArgument("onCheckedChange") {
                        comment(lambdaCommentText)
                    }
                }
                controlItemArguments(state)
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewCheckboxItemDemoScreen() = OudsPreview {
    CheckboxItemDemoScreen()
}

@PreviewLightDark
@Composable
private fun PreviewIndeterminateCheckboxItemDemoScreen() = OudsPreview {
    CheckboxItemDemoScreen(indeterminate = true)
}
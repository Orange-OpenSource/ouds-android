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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.controlitem.ControlItemCustomizations
import com.orange.ouds.app.ui.components.controlitem.controlItemArguments
import com.orange.ouds.app.ui.components.controlitem.controlItemError
import com.orange.ouds.app.ui.components.controlitem.controlItemIcon
import com.orange.ouds.app.ui.components.onClickArgument
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.LocalThemeDrawableResources
import com.orange.ouds.app.ui.utilities.ThemeDrawableResources
import com.orange.ouds.app.ui.utilities.composable.AppPreview
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.core.component.OudsCheckboxItem
import com.orange.ouds.core.component.OudsTriStateCheckboxItem
import com.orange.ouds.core.component.common.OudsError
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.theme.OudsVersion

@Composable
fun CheckboxItemDemoScreen(indeterminate: Boolean = false) {
    val state = rememberCheckboxItemDemoState()
    val themeDrawableResources = LocalThemeDrawableResources.current
    DemoScreen(
        bottomSheetContent = { ControlItemCustomizations(state = state) },
        codeSnippet = { checkboxItemDemoCodeSnippet(state = state, indeterminate = indeterminate, themeDrawableResources = themeDrawableResources) },
        demoContent = {
            if (indeterminate) {
                IndeterminateCheckboxItemDemoContent(state = state)
            } else {
                CheckboxItemDemoContent(state = state)
            }
        },
        demoContentPaddingValues = PaddingValues(horizontal = OudsTheme.spaces.fixed.none),
        version = OudsVersion.Component.Checkbox
    )
}

@Composable
private fun CheckboxItemDemoContent(state: CheckboxItemDemoState) {
    with(state) {
        CheckboxItemDemoColumn(edgeToEdge = edgeToEdge) {
            CheckboxIdentifier.entries.forEachIndexed { index, identifier ->
                val isLastItem = index == CheckboxIdentifier.entries.lastIndex
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
                    description = description,
                    icon = controlItemIcon(state = this),
                    edgeToEdge = edgeToEdge,
                    divider = divider,
                    reversed = reversed,
                    enabled = enabled,
                    readOnly = readOnly,
                    error = checkboxItemError(state = this, isLastItem = isLastItem),
                    constrainedMaxWidth = constrainedMaxWidth
                )
            }
        }
    }
}

@Composable
private fun IndeterminateCheckboxItemDemoContent(state: CheckboxItemDemoState) {
    with(state) {
        CheckboxItemDemoColumn(edgeToEdge = edgeToEdge) {
            CheckboxIdentifier.entries.forEachIndexed { index, identifier ->
                val isLastItem = index == CheckboxIdentifier.entries.lastIndex
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
                    description = description,
                    icon = controlItemIcon(state = this),
                    edgeToEdge = edgeToEdge,
                    divider = divider,
                    reversed = reversed,
                    enabled = enabled,
                    readOnly = readOnly,
                    error = checkboxItemError(state = this, isLastItem = isLastItem),
                    constrainedMaxWidth = constrainedMaxWidth
                )
            }
        }
    }
}

@Composable
private fun CheckboxItemDemoColumn(edgeToEdge: Boolean, content: @Composable () -> Unit) {
    Column(modifier = if (edgeToEdge) Modifier else Modifier.padding(horizontal = OudsTheme.grids.margin)) {
        content()
    }
}

@Composable
private fun checkboxItemError(state: CheckboxItemDemoState, isLastItem: Boolean): OudsError? {
    return controlItemError(
        state = state,
        isLastItem = isLastItem,
        errorMessageHtmlResId = R.string.app_components_checkbox_checkboxItem_annotatedErrorMessage_text
    )
}

private fun Code.Builder.checkboxItemDemoCodeSnippet(state: CheckboxItemDemoState, indeterminate: Boolean, themeDrawableResources: ThemeDrawableResources) {
    val functionName = if (indeterminate) "OudsTriStateCheckboxItem" else "OudsCheckboxItem"
    val lambdaCommentText = "Change state"
    with(state) {
        repeat(CheckboxIdentifier.entries.count()) { index ->
            functionCall(functionName) {
                if (indeterminate) {
                    val value = if (index == 0) toggleableStateValues.first else toggleableStateValues.second
                    typedArgument("state", value)
                    onClickArgument {
                        comment(lambdaCommentText)
                    }
                } else {
                    val value = if (index == 0) checkedValues.first else checkedValues.second
                    typedArgument("checked", value)
                    lambdaArgument("onCheckedChange") {
                        comment(lambdaCommentText)
                    }
                }
                controlItemArguments(state, themeDrawableResources, index == CheckboxIdentifier.entries.lastIndex)
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewCheckboxItemDemoScreen() = AppPreview {
    CheckboxItemDemoScreen()
}

@PreviewLightDark
@Composable
private fun PreviewIndeterminateCheckboxItemDemoScreen() = AppPreview {
    CheckboxItemDemoScreen(indeterminate = true)
}
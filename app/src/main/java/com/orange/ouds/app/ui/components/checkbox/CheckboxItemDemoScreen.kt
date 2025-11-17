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
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.controlitem.ControlItemCustomizations
import com.orange.ouds.app.ui.components.controlitem.controlItemArguments
import com.orange.ouds.app.ui.components.controlitem.controlItemCustomization
import com.orange.ouds.app.ui.components.onClickArgument
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.LocalThemeDrawableResources
import com.orange.ouds.app.ui.utilities.ThemeDrawableResources
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextField
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.core.component.OudsCheckboxItem
import com.orange.ouds.core.component.OudsControlItemIcon
import com.orange.ouds.core.component.OudsTriStateCheckboxItem
import com.orange.ouds.core.component.common.OudsError
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.theme.OudsVersion

@Composable
fun CheckboxItemDemoScreen(indeterminate: Boolean = false) {
    val state = rememberCheckboxItemDemoState()
    val themeDrawableResources = LocalThemeDrawableResources.current
    DemoScreen(
        bottomSheetContent = { CheckboxItemDemoBottomSheetContent(state = state) },
        codeSnippet = { checkboxItemDemoCodeSnippet(state = state, indeterminate = indeterminate, themeDrawableResources = themeDrawableResources) },
        demoContent = {
            if (indeterminate) {
                IndeterminateCheckboxItemDemoContent(state = state)
            } else {
                CheckboxItemDemoContent(state = state)
            }
        },
        demoContentPaddingValues = PaddingValues(),
        version = OudsVersion.Component.Checkbox
    )
}

@Composable
private fun CheckboxItemDemoBottomSheetContent(state: CheckboxItemDemoState) {
    with(state) {
        val extraCustomizations = listOf(
            controlItemCustomization(6) {
                CustomizationTextField(
                    label = stringResource(R.string.app_components_common_errorDescription_label),
                    value = errorDescription,
                    onValueChange = { value -> errorDescription = value },
                    enabled = errorDescriptionEnabled
                )
            },
        )
        ControlItemCustomizations(state = state, extraCustomizations = extraCustomizations)
    }
}

@Composable
private fun CheckboxItemDemoContent(state: CheckboxItemDemoState) {
    with(state) {
        Column {
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
                    helperText = helperText,
                    icon = if (icon) OudsControlItemIcon(painterResource(id = LocalThemeDrawableResources.current.tipsAndTricks)) else null,
                    divider = divider,
                    reversed = reversed,
                    enabled = enabled,
                    readOnly = readOnly,
                    error = if (error) OudsError(if (isLastItem) errorDescription else "") else null
                )
            }
        }
    }
}

@Composable
private fun IndeterminateCheckboxItemDemoContent(state: CheckboxItemDemoState) {
    with(state) {
        Column {
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
                    helperText = helperText,
                    icon = if (icon) OudsControlItemIcon(painterResource(id = LocalThemeDrawableResources.current.tipsAndTricks)) else null,
                    divider = divider,
                    reversed = reversed,
                    enabled = enabled,
                    readOnly = readOnly,
                    error = if (error) OudsError(if (isLastItem) errorDescription else "") else null
                )
            }
        }
    }
}

private fun Code.Builder.checkboxItemDemoCodeSnippet(state: CheckboxItemDemoState, indeterminate: Boolean, themeDrawableResources: ThemeDrawableResources) {
    val functionName = if (indeterminate) "OudsTriStateCheckboxItem" else "OudsCheckboxItem"
    val lambdaCommentText = "Change state"
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
            controlItemArguments(state, themeDrawableResources)
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
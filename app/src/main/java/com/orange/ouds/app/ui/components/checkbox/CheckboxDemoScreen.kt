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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.state.ToggleableState
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.checkbox.CheckboxDemoState.Companion.CheckboxIdentifier
import com.orange.ouds.app.ui.components.enabledArgument
import com.orange.ouds.app.ui.components.onClickArgument
import com.orange.ouds.app.ui.utilities.composable.CodeSnippet
import com.orange.ouds.app.ui.utilities.composable.CustomizationBottomSheetScaffold
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchListItem
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.app.ui.utilities.composable.LightDarkDemo
import com.orange.ouds.core.component.OudsCheckbox
import com.orange.ouds.core.component.OudsTriStateCheckbox
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.utilities.UiModePreviews

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckboxDemoScreen(indeterminate: Boolean = false) = DemoScreen(rememberCheckboxDemoState()) {
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
                label = stringResource(R.string.app_components_common_error_label),
                checked = error,
                onCheckedChange = { error = it },
                enabled = errorSwitchEnabled
            )
        }
    ) {
        LightDarkDemo {
            Row(
                modifier = Modifier
                    .background(OudsTheme.colorScheme.background.primary)
                    .padding(all = OudsTheme.spaces.fixed.medium)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                if (indeterminate) {
                    IndeterminateCheckboxDemo(
                        state = this@DemoScreen,
                        onClick = { identifier ->
                            toggleableStateValues = when (identifier) {
                                CheckboxIdentifier.First -> toggleableStateValues.copy(first = getNewToggleableState(toggleableStateValues.first))
                                CheckboxIdentifier.Second -> toggleableStateValues.copy(second = getNewToggleableState(toggleableStateValues.second))
                            }
                        }
                    )
                } else {
                    CheckboxDemo(
                        state = this@DemoScreen,
                        onCheckedChange = { identifier: CheckboxIdentifier, value: Boolean ->
                            checkedValues = when (identifier) {
                                CheckboxIdentifier.First -> checkedValues.copy(first = value)
                                CheckboxIdentifier.Second -> checkedValues.copy(second = value)
                            }
                        }
                    )
                }
            }
        }

        CheckboxDemoCodeSnippet(
            state = this@DemoScreen,
            indeterminate = indeterminate,
            modifier = Modifier
                .padding(horizontal = OudsTheme.grids.margin, vertical = OudsTheme.spaces.fixed.medium)
                .padding(top = OudsTheme.spaces.fixed.medium)
        )
    }
}

private fun getNewToggleableState(toggleableState: ToggleableState) = when (toggleableState) {
    ToggleableState.On -> ToggleableState.Off
    ToggleableState.Off -> ToggleableState.Indeterminate
    ToggleableState.Indeterminate -> ToggleableState.On
}

@Composable
private fun CheckboxDemo(state: CheckboxDemoState, onCheckedChange: (CheckboxIdentifier, Boolean) -> Unit) {
    with(state) {
        CheckboxIdentifier.entries.forEach { identifier ->
            OudsCheckbox(
                checked = when (identifier) {
                    CheckboxIdentifier.First -> checkedValues.first
                    CheckboxIdentifier.Second -> checkedValues.second
                },
                onCheckedChange = { value -> onCheckedChange(identifier, value) },
                enabled = enabled,
                error = error
            )
        }
    }
}

@Composable
private fun IndeterminateCheckboxDemo(state: CheckboxDemoState, onClick: (CheckboxIdentifier) -> Unit) {
    with(state) {
        CheckboxIdentifier.entries.forEach { identifier ->
            OudsTriStateCheckbox(
                state = when (identifier) {
                    CheckboxIdentifier.First -> toggleableStateValues.first
                    CheckboxIdentifier.Second -> toggleableStateValues.second
                },
                onClick = { onClick(identifier) },
                enabled = enabled,
                error = error
            )
        }
    }
}

@Composable
private fun CheckboxDemoCodeSnippet(state: CheckboxDemoState, indeterminate: Boolean, modifier: Modifier = Modifier) {
    val functionName = if (indeterminate) "OudsTriStateCheckbox" else "OudsCheckbox"
    val lambdaCommentText = "Change state"
    CodeSnippet(modifier = modifier) {
        comment("First checkbox")
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
                enabledArgument(enabled)
                typedArgument("error", error)
            }
        }
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewCheckboxDemoScreen() = OudsPreview {
    CheckboxDemoScreen()
}


@UiModePreviews.Default
@Composable
private fun PreviewIndeterminateCheckboxDemoScreen() = OudsPreview {
    CheckboxDemoScreen(indeterminate = true)
}
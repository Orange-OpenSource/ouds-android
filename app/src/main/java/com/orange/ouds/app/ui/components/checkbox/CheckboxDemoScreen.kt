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

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.enabledArgument
import com.orange.ouds.app.ui.components.onClickArgument
import com.orange.ouds.app.ui.utilities.composable.CodeSnippet
import com.orange.ouds.app.ui.utilities.composable.CustomizationBottomSheetScaffold
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.app.ui.utilities.composable.LightDarkDemo
import com.orange.ouds.core.component.OudsCheckbox
import com.orange.ouds.core.component.OudsTriStateCheckbox
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckboxDemoScreen(indeterminate: Boolean = false) = DemoScreen(rememberCheckboxDemoState()) {
    CustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
        bottomSheetContent = {
            CustomizationSwitchItem(
                label = stringResource(R.string.app_common_enabled_label),
                checked = enabled,
                onCheckedChange = { enabled = it },
                enabled = enabledSwitchEnabled
            )
            CustomizationSwitchItem(
                label = stringResource(R.string.app_components_common_error_label),
                checked = error,
                onCheckedChange = { error = it },
                enabled = errorSwitchEnabled
            )
        }
    ) {
        LightDarkDemo(modifier = Modifier.padding(horizontal = OudsTheme.grids.margin)) {
            if (indeterminate) {
                IndeterminateCheckboxDemo(state = this@DemoScreen)
            } else {
                CheckboxDemo(state = this@DemoScreen)
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

@Composable
private fun CheckboxDemo(state: CheckboxDemoState) {
    with(state) {
        Row {
            CheckboxIdentifier.entries.forEach { identifier ->
                OudsCheckbox(
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
                    enabled = enabled,
                    error = error
                )
            }
        }
    }
}

@Composable
private fun IndeterminateCheckboxDemo(state: CheckboxDemoState) {
    with(state) {
        Row {
            CheckboxIdentifier.entries.forEach { identifier ->
                OudsTriStateCheckbox(
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
                    enabled = enabled,
                    error = error
                )
            }
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

@PreviewLightDark
@Composable
private fun PreviewCheckboxDemoScreen() = OudsPreview {
    CheckboxDemoScreen()
}


@PreviewLightDark
@Composable
private fun PreviewIndeterminateCheckboxDemoScreen() = OudsPreview {
    CheckboxDemoScreen(indeterminate = true)
}
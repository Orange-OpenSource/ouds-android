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

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.state.ToggleableState
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.enabledArgument
import com.orange.ouds.app.ui.components.onClickArgument
import com.orange.ouds.app.ui.components.painterArgument
import com.orange.ouds.app.ui.components.textArgument
import com.orange.ouds.app.ui.utilities.composable.CodeSnippet
import com.orange.ouds.app.ui.utilities.composable.CustomizationBottomSheetScaffold
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchListItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextField
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.app.ui.utilities.composable.LightDarkDemo
import com.orange.ouds.core.component.OudsCheckboxItem
import com.orange.ouds.core.component.OudsTriStateCheckboxItem
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.utilities.UiModePreviews

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckboxItemDemoScreen(indeterminate: Boolean = false) = DemoScreen(rememberCheckboxItemDemoState()) {
    CustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
        bottomSheetContent = {
            CustomizationSwitchListItem(
                label = stringResource(R.string.app_components_controlItem_icon_label),
                checked = icon,
                onCheckedChange = { icon = it },
            )
            CustomizationSwitchListItem(
                label = stringResource(R.string.app_components_controlItem_divider_label),
                checked = divider,
                onCheckedChange = { divider = it },
            )
            CustomizationSwitchListItem(
                label = stringResource(R.string.app_components_controlItem_inverted_label),
                checked = inverted,
                onCheckedChange = { inverted = it },
            )
            CustomizationSwitchListItem(
                label = stringResource(R.string.app_common_enabled_label),
                checked = enabled,
                onCheckedChange = { enabled = it },
                enabled = enabledSwitchEnabled
            )
            CustomizationSwitchListItem(
                label = stringResource(R.string.app_components_controlItem_readOnly_label),
                checked = readOnly,
                onCheckedChange = { readOnly = it },
                enabled = readOnlySwitchEnabled
            )
            CustomizationSwitchListItem(
                label = stringResource(R.string.app_components_common_error_label),
                checked = error,
                onCheckedChange = { error = it },
                enabled = errorSwitchEnabled
            )
            CustomizationTextField(
                label = stringResource(R.string.app_components_common_text_label),
                value = text,
                onValueChange = { value -> text = value }
            )
            CustomizationTextField(
                label = stringResource(R.string.app_components_controlItem_helperText_label),
                value = helperText.orEmpty(),
                onValueChange = { value -> helperText = value })
        }
    ) {
        LightDarkDemo {
            if (indeterminate) {
                IndeterminateCheckboxItemDemo(
                    state = this@DemoScreen,
                    onClick = {
                        toggleableState = when (toggleableState) {
                            ToggleableState.On -> ToggleableState.Off
                            ToggleableState.Off -> ToggleableState.Indeterminate
                            ToggleableState.Indeterminate -> ToggleableState.On
                        }
                    }
                )
            } else {
                CheckboxItemDemo(
                    state = this@DemoScreen,
                    onCheckedChange = { value -> checked = value }
                )
            }
        }

        CheckboxItemDemoCodeSnippet(
            state = this@DemoScreen,
            indeterminate = indeterminate,
            modifier = Modifier
                .padding(all = OudsTheme.spaces.fixed.medium)
                .padding(top = OudsTheme.spaces.fixed.medium)
        )
    }
}

@Composable
private fun CheckboxItemDemo(state: CheckboxItemDemoState, onCheckedChange: (Boolean) -> Unit) {
    with(state) {
        OudsCheckboxItem(
            checked = checked,
            onCheckedChange = onCheckedChange,
            text = text,
            helperText = helperText,
            icon = if (icon) OudsCheckboxItem.Icon(painterResource(id = R.drawable.ic_heart)) else null,
            divider = divider,
            inverted = inverted,
            enabled = enabled,
            readOnly = readOnly,
            error = error
        )
    }
}

@Composable
private fun IndeterminateCheckboxItemDemo(state: CheckboxItemDemoState, onClick: () -> Unit) {
    with(state) {
        OudsTriStateCheckboxItem(
            state = toggleableState,
            onClick = onClick,
            text = text,
            helperText = helperText,
            icon = if (icon) OudsCheckboxItem.Icon(painterResource(id = R.drawable.ic_heart)) else null,
            divider = divider,
            inverted = inverted,
            enabled = enabled,
            readOnly = readOnly,
            error = error
        )
    }
}

@Composable
private fun CheckboxItemDemoCodeSnippet(state: CheckboxItemDemoState, indeterminate: Boolean, modifier: Modifier = Modifier) {
    val functionName = if (indeterminate) "OudsTriStateCheckboxItem" else "OudsCheckboxItem"
    val lambdaCommentText = "Change state"
    CodeSnippet(modifier = modifier) {
        with(state) {
            functionCall(functionName) {
                textArgument(text)
                if (indeterminate) {
                    typedArgument("state", toggleableState)
                    onClickArgument {
                        comment(lambdaCommentText)
                    }
                } else {
                    typedArgument("checked", checked)
                    lambdaArgument("onCheckedChange") {
                        comment(lambdaCommentText)
                    }
                }
                if (!helperText.isNullOrBlank()) typedArgument("helperText", helperText)
                if (icon) {
                    constructorCallArgument<OudsCheckboxItem.Icon>("icon") {
                        painterArgument(R.drawable.ic_heart)
                    }
                }
                if (!divider) typedArgument("divider", divider)
                if (inverted) typedArgument("inverted", inverted)
                if (!enabled) enabledArgument(enabled)
                if (readOnly) typedArgument("readOnly", readOnly)
                if (error) typedArgument("error", error)
            }
        }
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewCheckboxItemDemoScreen() = OudsPreview {
    CheckboxItemDemoScreen()
}

@UiModePreviews.Default
@Composable
private fun PreviewIndeterminateCheckboxItemDemoScreen() = OudsPreview {
    CheckboxItemDemoScreen(indeterminate = true)
}
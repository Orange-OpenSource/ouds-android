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

package com.orange.ouds.app.ui.components.textarea

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.Component
import com.orange.ouds.app.ui.components.constrainedMaxWidthArgument
import com.orange.ouds.app.ui.components.enabledArgument
import com.orange.ouds.app.ui.components.labelArgument
import com.orange.ouds.app.ui.components.onClickArgument
import com.orange.ouds.app.ui.components.readOnlyArgument
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.composable.AppPreview
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextInput
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.core.component.OudsTextArea
import com.orange.ouds.core.component.OudsTextInputHelperLink
import com.orange.ouds.core.component.OudsTextInputLoader
import com.orange.ouds.core.component.common.OudsError
import com.orange.ouds.theme.OudsVersion

@Composable
fun TextAreaDemoScreen() {
    val state = rememberTextAreaDemoState()
    DemoScreen(
        description = stringResource(id = Component.TextArea.descriptionRes),
        bottomSheetContent = { TextAreaDemoBottomSheetContent(state = state) },
        codeSnippet = { textAreaDemoCodeSnippet(state = state) },
        demoContent = { TextAreaDemoContent(state = state) },
        version = OudsVersion.Component.TextArea
    )
}

@Composable
private fun TextAreaDemoBottomSheetContent(state: TextAreaDemoState) {
    with(state) {
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_common_outlined_tech),
            checked = outlined,
            onCheckedChange = { outlined = it },
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_common_loader_tech),
            checked = hasLoader,
            onCheckedChange = { hasLoader = it },
            enabled = loaderSwitchEnabled
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_common_enabled_tech),
            checked = enabled,
            onCheckedChange = { enabled = it },
            enabled = enabledSwitchEnabled
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_common_readOnly_tech),
            checked = readOnly,
            onCheckedChange = { readOnly = it },
            enabled = readOnlySwitchEnabled
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_common_error_tech),
            checked = error,
            onCheckedChange = { error = it },
            enabled = errorSwitchEnabled
        )
        CustomizationTextInput(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_errorMessage_tech),
            value = errorMessage,
            onValueChange = { value -> errorMessage = value },
            enabled = errorMessageTextInputEnabled
        )
        CustomizationTextInput(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_label_tech),
            value = label,
            onValueChange = { value -> label = value }
        )
        CustomizationTextInput(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_placeholder_tech),
            value = placeholder,
            onValueChange = { value -> placeholder = value }
        )
        CustomizationTextInput(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_helperText_tech),
            value = helperText,
            onValueChange = { value -> helperText = value }
        )
        CustomizationTextInput(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_textInput_helperLink_tech),
            value = helperLink,
            onValueChange = { value -> helperLink = value }
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_common_constrainedMaxWidth_tech),
            checked = constrainedMaxWidth,
            onCheckedChange = { constrainedMaxWidth = it },
        )
    }
}

@Composable
private fun TextAreaDemoContent(state: TextAreaDemoState) {
    val focusManager = LocalFocusManager.current
    with(state) {
        OudsTextArea(
            textFieldState = textFieldState,
            label = label,
            placeholder = placeholder,
            outlined = outlined,
            loader = if (hasLoader) OudsTextInputLoader(null) else null,
            enabled = enabled,
            readOnly = readOnly,
            error = if (error) OudsError(errorMessage) else null,
            helperText = helperText,
            helperLink = if (helperLink.isNotEmpty()) OudsTextInputHelperLink(text = helperLink, onClick = { }) else null,
            constrainedMaxWidth = constrainedMaxWidth,
            onKeyboardAction = { focusManager.clearFocus() }
        )
    }
}

private fun Code.Builder.textAreaDemoCodeSnippet(state: TextAreaDemoState) {
    with(state) {
        functionCall("OudsTextArea") {
            functionCallArgument("textFieldState", "rememberTextFieldState")
            if (label.isNotEmpty()) labelArgument(label)
            if (placeholder.isNotEmpty()) typedArgument("placeholder", placeholder)
            typedArgument("outlined", outlined)
            if (hasLoader) {
                constructorCallArgument<OudsTextInputLoader>("loader") {
                    typedArgument("progress", null)
                }
            }
            if (!enabled) enabledArgument(false)
            if (readOnly) readOnlyArgument(true)
            if (error) {
                constructorCallArgument<OudsError>("error") {
                    typedArgument("message", errorMessage)
                }
            }
            if (helperText.isNotEmpty()) typedArgument("helperText", helperText)
            if (helperLink.isNotEmpty()) {
                constructorCallArgument<OudsTextInputHelperLink>("helperLink") {
                    typedArgument("text", helperLink)
                    onClickArgument {
                        comment("Do something")
                    }
                }
            }
            if (constrainedMaxWidth) constrainedMaxWidthArgument(true)
            lambdaArgument("onKeyboardAction") {
                functionCall("focusManager.clearFocus")
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewTextAreaDemoScreen() = AppPreview {
    TextAreaDemoScreen()
}

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

package com.orange.ouds.app.ui.components.passwordinput

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.Component
import com.orange.ouds.app.ui.components.constrainedMaxWidthArgument
import com.orange.ouds.app.ui.components.enabledArgument
import com.orange.ouds.app.ui.components.readOnlyArgument
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.composable.AppPreview
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextInput
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.core.component.OudsPasswordInput
import com.orange.ouds.core.component.OudsTextInputLoader
import com.orange.ouds.core.component.common.OudsError
import com.orange.ouds.foundation.ExperimentalOudsApi
import com.orange.ouds.theme.OudsVersion

@Composable
fun PasswordInputDemoScreen() {
    val state = rememberPasswordInputDemoState()
    DemoScreen(
        description = stringResource(id = Component.PasswordInput.descriptionRes),
        bottomSheetContent = { PasswordInputDemoBottomSheetContent(state = state) },
        codeSnippet = { passwordInputDemoCodeSnippet(state = state) },
        demoContent = { PasswordInputDemoContent(state = state) },
        version = OudsVersion.Component.PasswordInput
    )
}

@Composable
private fun PasswordInputDemoBottomSheetContent(state: PasswordInputDemoState) {
    with(state) {
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_common_outlined_label),
            checked = outlined,
            onCheckedChange = { outlined = it },
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_passwordInput_lockIcon_label),
            checked = lockIcon,
            onCheckedChange = { lockIcon = it },
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_common_loader_label),
            checked = hasLoader,
            onCheckedChange = { hasLoader = it },
            enabled = loaderSwitchEnabled
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_common_enabled_label),
            checked = enabled,
            onCheckedChange = { enabled = it },
            enabled = enabledSwitchEnabled
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_common_readOnly_label),
            checked = readOnly,
            onCheckedChange = { readOnly = it },
            enabled = readOnlySwitchEnabled
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_common_error_label),
            checked = error,
            onCheckedChange = { error = it },
            enabled = errorSwitchEnabled
        )
        CustomizationTextInput(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_errorMessage_label),
            value = errorMessage,
            onValueChange = { value -> errorMessage = value },
            enabled = errorMessageTextInputEnabled
        )
        CustomizationTextInput(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_label_label),
            value = label,
            onValueChange = { value -> label = value }
        )
        CustomizationTextInput(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_placeholder_label),
            value = placeholder,
            onValueChange = { value -> placeholder = value }
        )
        CustomizationTextInput(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_prefix_label),
            value = prefix,
            onValueChange = { value -> prefix = value }
        )
        CustomizationTextInput(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_helperText_label),
            value = helperText,
            onValueChange = { value -> helperText = value }
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_common_constrainedMaxWidth_label),
            checked = constrainedMaxWidth,
            onCheckedChange = { constrainedMaxWidth = it },
        )
    }
}

@OptIn(ExperimentalOudsApi::class)
@Composable
private fun PasswordInputDemoContent(state: PasswordInputDemoState) {
    val focusManager = LocalFocusManager.current
    with(state) {
        OudsPasswordInput(
            value = value,
            onValueChange = { value = it },
            label = label,
            placeholder = placeholder,
            outlined = outlined,
            lockIcon = lockIcon,
            loader = if (hasLoader) OudsTextInputLoader(null) else null,
            enabled = enabled,
            readOnly = readOnly,
            error = if (error) OudsError(errorMessage) else null,
            prefix = prefix,
            helperText = helperText,
            constrainedMaxWidth = constrainedMaxWidth,
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
        )
    }
}

private fun Code.Builder.passwordInputDemoCodeSnippet(state: PasswordInputDemoState) {
    with(state) {
        functionCall("OudsPasswordInput") {
            typedArgument("value", value)
            lambdaArgument("onValueChange") {
                comment("Update value")
            }
            if (label.isNotEmpty()) typedArgument("label", label)
            if (placeholder.isNotEmpty()) typedArgument("placeholder", placeholder)
            typedArgument("outlined", outlined)
            if (lockIcon) typedArgument("lockIcon", lockIcon)
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
            if (prefix.isNotEmpty()) typedArgument("prefix", prefix)
            if (helperText.isNotEmpty()) typedArgument("helperText", helperText)
            if (constrainedMaxWidth) constrainedMaxWidthArgument(true)
            constructorCallArgument<KeyboardActions>("keyboardActions") {
                lambdaArgument("onDone") {
                    functionCall("focusManager.clearFocus")
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewPasswordInputDemoScreen() = AppPreview {
    PasswordInputDemoScreen()
}
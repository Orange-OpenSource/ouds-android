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

package com.orange.ouds.app.ui.components.textinput

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.Component
import com.orange.ouds.app.ui.components.constrainedMaxWidthArgument
import com.orange.ouds.app.ui.components.contentDescriptionArgument
import com.orange.ouds.app.ui.components.enabledArgument
import com.orange.ouds.app.ui.components.labelArgument
import com.orange.ouds.app.ui.components.onClickArgument
import com.orange.ouds.app.ui.components.painterArgument
import com.orange.ouds.app.ui.components.readOnlyArgument
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.LocalThemeDrawableResources
import com.orange.ouds.app.ui.utilities.ThemeDrawableResources
import com.orange.ouds.app.ui.utilities.composable.AppPreview
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextInput
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.core.component.OudsTextInput
import com.orange.ouds.core.component.OudsTextInputHelperLink
import com.orange.ouds.core.component.OudsTextInputLeadingIcon
import com.orange.ouds.core.component.OudsTextInputLoader
import com.orange.ouds.core.component.OudsTextInputTrailingIconButton
import com.orange.ouds.core.component.common.OudsError
import com.orange.ouds.theme.OudsVersion

@Composable
fun TextInputDemoScreen() {
    val state = rememberTextInputDemoState()
    val themeDrawableResources = LocalThemeDrawableResources.current
    DemoScreen(
        description = stringResource(id = Component.TextInput.descriptionRes),
        bottomSheetContent = { TextInputDemoBottomSheetContent(state = state) },
        codeSnippet = { textInputDemoCodeSnippet(state = state, themeDrawableResources = themeDrawableResources) },
        demoContent = { TextInputDemoContent(state = state) },
        version = OudsVersion.Component.TextInput
    )
}

@Composable
private fun TextInputDemoBottomSheetContent(state: TextInputDemoState) {
    with(state) {
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_common_outlined_tech),
            checked = outlined,
            onCheckedChange = { outlined = it },
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_textInput_leadingIcon_tech),
            checked = leadingIcon,
            onCheckedChange = { leadingIcon = it },
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_textInput_trailingAction_tech),
            checked = trailingIcon,
            onCheckedChange = { trailingIcon = it },
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
            label = stringResource(R.string.app_components_common_prefix_tech),
            value = prefix,
            onValueChange = { value -> prefix = value }
        )
        CustomizationTextInput(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_textInput_suffix_tech),
            value = suffix,
            onValueChange = { value -> suffix = value }
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
private fun TextInputDemoContent(state: TextInputDemoState) {
    val focusManager = LocalFocusManager.current
    with(state) {
        OudsTextInput(
            textFieldState = textFieldState,
            label = label,
            placeholder = placeholder,
            outlined = outlined,
            leadingIcon = if (leadingIcon) {
                OudsTextInputLeadingIcon(
                    painterResource(id = LocalThemeDrawableResources.current.tipsAndTricks),
                    contentDescription = ""
                )
            } else {
                null
            },
            trailingIconButton = if (trailingIcon) {
                OudsTextInputTrailingIconButton(
                    painterResource(id = LocalThemeDrawableResources.current.tipsAndTricks),
                    contentDescription = stringResource(id = R.string.app_components_textInput_trailingAction_a11y),
                    onClick = { })
            } else {
                null
            },
            loader = if (hasLoader) OudsTextInputLoader(null) else null,
            enabled = enabled,
            readOnly = readOnly,
            error = if (error) OudsError(errorMessage) else null,
            prefix = prefix,
            suffix = suffix,
            helperText = helperText,
            helperLink = if (helperLink.isNotEmpty()) OudsTextInputHelperLink(text = helperLink, onClick = { }) else null,
            constrainedMaxWidth = constrainedMaxWidth,
            onKeyboardAction = { focusManager.clearFocus() }
        )
    }
}

private fun Code.Builder.textInputDemoCodeSnippet(state: TextInputDemoState, themeDrawableResources: ThemeDrawableResources) {
    with(state) {
        functionCall("OudsTextInput") {
            functionCallArgument("textFieldState", "rememberTextFieldState")
            if (label.isNotEmpty()) labelArgument(label)
            if (placeholder.isNotEmpty()) typedArgument("placeholder", placeholder)
            typedArgument("outlined", outlined)
            if (leadingIcon) {
                constructorCallArgument<OudsTextInputLeadingIcon>("leadingIcon") {
                    painterArgument(themeDrawableResources.tipsAndTricks)
                }
            }
            if (trailingIcon) {
                constructorCallArgument<OudsTextInputTrailingIconButton>("trailingIconButton") {
                    painterArgument(themeDrawableResources.tipsAndTricks)
                    contentDescriptionArgument(R.string.app_components_textInput_trailingAction_a11y)
                    onClickArgument {
                        comment("Do something")
                    }
                }
            }
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
            if (suffix.isNotEmpty()) typedArgument("suffix", suffix)
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
private fun PreviewTextInputDemoScreen() = AppPreview {
    TextInputDemoScreen()
}
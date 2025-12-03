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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.Component
import com.orange.ouds.app.ui.components.contentDescriptionArgument
import com.orange.ouds.app.ui.components.enabledArgument
import com.orange.ouds.app.ui.components.onClickArgument
import com.orange.ouds.app.ui.components.painterArgument
import com.orange.ouds.app.ui.components.readOnlyArgument
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.LocalThemeDrawableResources
import com.orange.ouds.app.ui.utilities.ThemeDrawableResources
import com.orange.ouds.app.ui.utilities.composable.AppPreview
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextField
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
            label = stringResource(R.string.app_components_common_outlined_label),
            checked = outlined,
            onCheckedChange = { outlined = it },
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_textInput_leadingIcon_label),
            checked = leadingIcon,
            onCheckedChange = { leadingIcon = it },
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_textInput_trailingIcon_label),
            checked = trailingIcon,
            onCheckedChange = { trailingIcon = it },
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
        CustomizationTextField(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_errorMessage_label),
            value = errorMessage,
            onValueChange = { value -> errorMessage = value },
            enabled = errorMessageTextInputEnabled
        )
        CustomizationTextField(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_label_label),
            value = label,
            onValueChange = { value -> label = value }
        )
        CustomizationTextField(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_textInput_placeholder_label),
            value = placeholder,
            onValueChange = { value -> placeholder = value }
        )
        CustomizationTextField(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_textInput_prefix_label),
            value = prefix,
            onValueChange = { value -> prefix = value }
        )
        CustomizationTextField(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_textInput_suffix_label),
            value = suffix,
            onValueChange = { value -> suffix = value }
        )
        CustomizationTextField(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_helperText_label),
            value = helperText,
            onValueChange = { value -> helperText = value }
        )
        CustomizationTextField(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_textInput_helperLink_label),
            value = helperLink,
            onValueChange = { value -> helperLink = value }
        )
    }
}

@Composable
private fun TextInputDemoContent(state: TextInputDemoState) {
    with(state) {
        OudsTextInput(
            value = value,
            onValueChange = { value = it },
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
                    contentDescription = stringResource(id = R.string.app_components_textInput_trailingIcon_a11y),
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
            helperLink = if (helperLink.isNotEmpty()) OudsTextInputHelperLink(text = helperLink, onClick = { }) else null
        )
    }
}

private fun Code.Builder.textInputDemoCodeSnippet(state: TextInputDemoState, themeDrawableResources: ThemeDrawableResources) {
    with(state) {
        functionCall("OudsTextInput") {
            typedArgument("value", value)
            lambdaArgument("onValueChange") {
                comment("Update value")
            }
            if (label.isNotEmpty()) typedArgument("label", label)
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
                    contentDescriptionArgument(R.string.app_components_textInput_trailingIcon_a11y)
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
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewTextInputDemoScreen() = AppPreview {
    TextInputDemoScreen()
}
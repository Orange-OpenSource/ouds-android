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

import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.Component
import com.orange.ouds.app.ui.utilities.appendHtml
import com.orange.ouds.app.ui.components.constrainedMaxWidthArgument
import com.orange.ouds.app.ui.components.contentDescriptionArgument
import com.orange.ouds.app.ui.components.enabledArgument
import com.orange.ouds.app.ui.components.errorArgument
import com.orange.ouds.app.ui.components.helperTextArgument
import com.orange.ouds.app.ui.components.iconArgument
import com.orange.ouds.app.ui.components.labelArgument
import com.orange.ouds.app.ui.components.onClickArgument
import com.orange.ouds.app.ui.components.painterArgument
import com.orange.ouds.app.ui.components.readOnlyArgument
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.LocalThemeDrawableResources
import com.orange.ouds.app.ui.utilities.ThemeDrawableResources
import com.orange.ouds.app.ui.utilities.composable.AppPreview
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChips
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextInput
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.app.ui.utilities.rememberUntintedIconPainter
import com.orange.ouds.core.component.OudsTextInput
import com.orange.ouds.core.component.OudsTextInputHelperLink
import com.orange.ouds.core.component.OudsTextInputLeadingIcon
import com.orange.ouds.core.component.OudsTextInputLoader
import com.orange.ouds.core.component.OudsTextInputTrailingIconButton
import com.orange.ouds.core.component.common.OudsError
import com.orange.ouds.core.component.common.text.buildOudsAnnotatedErrorMessage
import com.orange.ouds.core.component.common.text.buildOudsAnnotatedHelperText
import com.orange.ouds.core.component.common.text.withStrong
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
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_textInput_leadingIcon_tech),
            chipLabels = TextInputDemoState.LeadingIcon.entries.map { stringResource(it.labelRes) },
            selectedChipIndex = TextInputDemoState.LeadingIcon.entries.indexOf(leadingIcon),
            onSelectionChange = { index -> leadingIcon = TextInputDemoState.LeadingIcon.entries[index] }
        )

        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_textInput_trailingAction_tech),
            chipLabels = TextInputDemoState.TrailingIcon.entries.map { stringResource(it.labelRes) },
            selectedChipIndex = TextInputDemoState.TrailingIcon.entries.indexOf(trailingIcon),
            onSelectionChange = { index -> trailingIcon = TextInputDemoState.TrailingIcon.entries[index] }
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
            enabled = errorMessageTextInputEnabled,
            helperText = stringResource(R.string.app_components_common_errorMessage_tech)
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
            onValueChange = { value -> helperText = value },
            enabled = helperTextTextInputEnabled,
            helperText = stringResource(R.string.app_components_common_errorMessage_tech)
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
        CustomizationSwitchItem(
            label = stringResource(id = R.string.app_components_common_annotatedText_tech),
            checked = annotatedText,
            onCheckedChange = { annotatedText = it }
        )
    }
}

@Composable
private fun TextInputDemoContent(state: TextInputDemoState) {
    val focusManager = LocalFocusManager.current
    with(state) {
        val textInputLeadingIcon = when (leadingIcon) {
            TextInputDemoState.LeadingIcon.None -> null
            TextInputDemoState.LeadingIcon.Tinted -> OudsTextInputLeadingIcon(
                painter = painterResource(id = LocalThemeDrawableResources.current.tipsAndTricks),
                contentDescription = ""
            )
            TextInputDemoState.LeadingIcon.Untinted -> OudsTextInputLeadingIcon(
                painter = rememberUntintedIconPainter(),
                contentDescription = "",
                tinted = false
            )
        }
        val textInputTrailingIcon = when (trailingIcon) {
            TextInputDemoState.TrailingIcon.None -> null
            TextInputDemoState.TrailingIcon.Tinted -> OudsTextInputTrailingIconButton(
                painter = painterResource(id = LocalThemeDrawableResources.current.tipsAndTricks),
                contentDescription = stringResource(id = R.string.app_components_textInput_trailingAction_a11y),
                onClick = {}
            )
        }
        val loader = if (hasLoader) OudsTextInputLoader(null) else null
        val textInputError = when {
            error && annotatedText -> {
                val errorHtml = stringResource(R.string.app_components_textInput_annotatedErrorMessage_text)
                OudsError(buildOudsAnnotatedErrorMessage {
                    appendHtml(errorHtml)
                })
            }
            error -> OudsError(errorMessage)
            else -> null
        }
        val textInputHelperLink = if (helperLink.isNotEmpty()) OudsTextInputHelperLink(text = helperLink, onClick = {}) else null
        val onKeyboardAction: KeyboardActionHandler = { focusManager.clearFocus() }
        if (annotatedText) {
            val helperTextHtml = stringResource(R.string.app_components_textInput_annotatedHelperText_text)
            val annotatedHelperText = buildOudsAnnotatedHelperText {
                appendHtml(helperTextHtml)
            }
            OudsTextInput(
                textFieldState = textFieldState,
                label = label,
                placeholder = placeholder,
                outlined = outlined,
                leadingIcon = textInputLeadingIcon,
                trailingIconButton = textInputTrailingIcon,
                loader = loader,
                enabled = enabled,
                readOnly = readOnly,
                error = textInputError,
                prefix = prefix,
                suffix = suffix,
                helperText = annotatedHelperText,
                helperLink = textInputHelperLink,
                constrainedMaxWidth = constrainedMaxWidth,
                onKeyboardAction = onKeyboardAction
            )
        } else {
            OudsTextInput(
                textFieldState = textFieldState,
                label = label,
                placeholder = placeholder,
                outlined = outlined,
                leadingIcon = textInputLeadingIcon,
                trailingIconButton = textInputTrailingIcon,
                loader = loader,
                enabled = enabled,
                readOnly = readOnly,
                error = textInputError,
                prefix = prefix,
                suffix = suffix,
                helperText = helperText,
                helperLink = textInputHelperLink,
                constrainedMaxWidth = constrainedMaxWidth,
                onKeyboardAction = onKeyboardAction
            )
        }
    }
}

private fun Code.Builder.textInputDemoCodeSnippet(state: TextInputDemoState, themeDrawableResources: ThemeDrawableResources) {
    with(state) {
        functionCall("OudsTextInput") {
            functionCallArgument("textFieldState", "rememberTextFieldState")
            if (label.isNotEmpty()) labelArgument(label)
            if (placeholder.isNotEmpty()) typedArgument("placeholder", placeholder)
            typedArgument("outlined", outlined)
            if (leadingIcon != TextInputDemoState.LeadingIcon.None) {
                iconArgument<OudsTextInputLeadingIcon>(
                    "leadingIcon",
                    themeDrawableResources.tipsAndTricks,
                    tinted = leadingIcon == TextInputDemoState.LeadingIcon.Tinted
                )
            }
            if (trailingIcon != TextInputDemoState.TrailingIcon.None) {
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
            if (error) errorArgument(errorMessage, annotatedText)
            if (prefix.isNotEmpty()) typedArgument("prefix", prefix)
            if (suffix.isNotEmpty()) typedArgument("suffix", suffix)
            helperTextArgument(helperText, annotatedText)
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
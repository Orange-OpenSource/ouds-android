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

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.navigation.compose.hiltViewModel
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.MainViewModel
import com.orange.ouds.app.ui.ThemeState
import com.orange.ouds.app.ui.components.Component
import com.orange.ouds.app.ui.components.onClickArgument
import com.orange.ouds.app.ui.components.painterArgument
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextField
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.core.component.OudsTextInput
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.theme.OudsThemeSettings
import com.orange.ouds.theme.OudsVersion

@Composable
fun TextInputDemoScreen(themeState: ThemeState, viewModel: MainViewModel = hiltViewModel()) {
    TextInputDemoScreen(
        roundedCorners = themeState.settings.roundedCornerTextInputs.orElse { false },
        onRoundedCornersChange = { roundedCorners ->
            val themeSettings = themeState.settings.copy(roundedCornerTextInputs = roundedCorners)
            themeState.settings = themeSettings
            viewModel.storeUserThemeSettings(themeSettings)
        }
    )
}

@Composable
fun TextInputDemoScreen(roundedCorners: Boolean, onRoundedCornersChange: (Boolean) -> Unit) {
    val state = rememberTextInputDemoState()
    DemoScreen(
        description = stringResource(id = Component.TextInput.descriptionRes),
        bottomSheetContent = {
            TextInputDemoBottomSheetContent(
                state = state,
                roundedCorners = roundedCorners,
                onRoundedCornersChange = onRoundedCornersChange
            )
        },
        codeSnippet = { textInputDemoCodeSnippet(state = state) },
        demoContent = { TextInputDemoContent(state = state) },
        version = OudsVersion.Component.TextInput
    )
}

@Composable
private fun TextInputDemoBottomSheetContent(state: TextInputDemoState, roundedCorners: Boolean, onRoundedCornersChange: (Boolean) -> Unit) {
    with(state) {
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_common_roundedCorners_label),
            checked = roundedCorners,
            onCheckedChange = onRoundedCornersChange
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_textInput_outlined_label),
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
            modifier = Modifier.padding(top = OudsTheme.spaces.fixed.medium),
            label = stringResource(R.string.app_components_common_label_label),
            value = label,
            onValueChange = { value -> label = value }
        )
        CustomizationTextField(
            modifier = Modifier.padding(top = OudsTheme.spaces.fixed.medium),
            label = stringResource(R.string.app_components_textInput_placeholder_label),
            value = placeholder,
            onValueChange = { value -> placeholder = value }
        )
        CustomizationTextField(
            modifier = Modifier.padding(top = OudsTheme.spaces.fixed.medium),
            label = stringResource(R.string.app_components_textInput_prefix_label),
            value = prefix,
            onValueChange = { value -> prefix = value }
        )
        CustomizationTextField(
            modifier = Modifier.padding(top = OudsTheme.spaces.fixed.medium),
            label = stringResource(R.string.app_components_textInput_suffix_label),
            value = suffix,
            onValueChange = { value -> suffix = value }
        )
        CustomizationTextField(
            modifier = Modifier.padding(top = OudsTheme.spaces.fixed.medium),
            label = stringResource(R.string.app_components_textInput_helperText_label),
            value = helperText,
            onValueChange = { value -> helperText = value }
        )
        CustomizationTextField(
            modifier = Modifier.padding(top = OudsTheme.spaces.fixed.medium),
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
            label = label.ifEmpty { null },
            placeholder = placeholder.ifEmpty { null },
            outlined = outlined,
            leadingIcon = if (leadingIcon) OudsTextInput.LeadingIcon(painterResource(id = R.drawable.ic_heart), contentDescription = "") else null,
            trailingIconButton = if (trailingIcon) OudsTextInput.TrailingIconButton(
                painterResource(id = R.drawable.ic_heart),
                contentDescription = "",
                onClick = { }) else null,
            loader = if (hasLoader) OudsTextInput.Loader(null) else null,
            enabled = enabled,
            readOnly = readOnly,
            error = error,
            prefix = prefix.ifEmpty { null },
            suffix = suffix.ifEmpty { null },
            helperText = helperText.ifEmpty { null },
            helperLink = if (helperLink.isNotEmpty()) OudsTextInput.HelperLink(text = helperLink, onClick = { }) else null
        )
    }
}

private fun Code.Builder.textInputDemoCodeSnippet(state: TextInputDemoState) {
    with(state) {
        functionCall(OudsTextInput::class.simpleName.orEmpty()) {
            typedArgument("value", value)
            lambdaArgument("onValueChange") {
                comment("Update value")
            }
            if (label.isNotEmpty()) typedArgument("label", label)
            if (placeholder.isNotEmpty()) typedArgument("placeholder", placeholder)
            typedArgument("outlined", outlined)
            if (leadingIcon) {
                constructorCallArgument<OudsTextInput.LeadingIcon>("leadingIcon") {
                    painterArgument(R.drawable.ic_heart)
                }
            }
            if (trailingIcon) {
                constructorCallArgument<OudsTextInput.TrailingIconButton>("trailingIconButton") {
                    painterArgument(R.drawable.ic_heart)
                    onClickArgument {
                        comment("Do something")
                    }
                }
            }
            if (hasLoader) {
                constructorCallArgument<OudsTextInput.Loader>("loader") {
                    typedArgument("progress", null)
                }
            }
            if (!enabled) typedArgument("enabled", false)
            if (readOnly) typedArgument("readOnly", true)
            if (error) typedArgument("error", true)
            if (prefix.isNotEmpty()) typedArgument("prefix", prefix)
            if (suffix.isNotEmpty()) typedArgument("suffix", suffix)
            if (helperText.isNotEmpty()) typedArgument("helperText", helperText)
            if (helperLink.isNotEmpty()) {
                constructorCallArgument<OudsTextInput.HelperLink>("helperLink") {
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
private fun PreviewTextInputDemoScreen() = with(OudsThemeSettings(roundedCornerTextInputs = false)) {
    OudsPreview(themeSettings = this) {
        TextInputDemoScreen(
            roundedCorners = roundedCornerTextInputs.orElse { false },
            onRoundedCornersChange = {}
        )
    }
}
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

package com.orange.ouds.app.ui.components.pincodeinput

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.Component
import com.orange.ouds.app.ui.components.errorArgument
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChips
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextInput
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.app.ui.utilities.toSentenceCase
import com.orange.ouds.core.component.OudsPinCodeInput
import com.orange.ouds.core.component.OudsPinCodeInputLength
import com.orange.ouds.core.component.common.OudsError
import com.orange.ouds.theme.OudsVersion

@Composable
fun PinCodeInputDemoScreen() {
    val state = rememberPinCodeInputDemoState()
    DemoScreen(
        description = stringResource(id = Component.PinCodeInput.descriptionRes),
        bottomSheetContent = { PinCodeInputDemoBottomSheetContent(state = state) },
        codeSnippet = { pinCodeInputDemoCodeSnippet(state = state) },
        demoContent = { PinCodeInputDemoContent(state = state) },
        version = OudsVersion.Component.PinCodeInput
    )
}

@Composable
private fun PinCodeInputDemoBottomSheetContent(state: PinCodeInputDemoState) {
    with(state) {
        CustomizationFilterChips(
            applyTopPadding = false,
            label = stringResource(R.string.app_components_pinCodeInput_length_tech),
            chipLabels = OudsPinCodeInputLength.entries.map { it.name.toSentenceCase() },
            selectedChipIndex = OudsPinCodeInputLength.entries.indexOf(length),
            onSelectionChange = { index -> length = OudsPinCodeInputLength.entries[index] }
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_common_outlined_tech),
            checked = outlined,
            onCheckedChange = { outlined = it }
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_common_error_tech),
            checked = error,
            onCheckedChange = { error = it }
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
            label = stringResource(R.string.app_components_common_helperText_tech),
            value = helperText,
            onValueChange = { value -> helperText = value }
        )
    }
}

@Composable
private fun PinCodeInputDemoContent(state: PinCodeInputDemoState) {
    with(state) {
        OudsPinCodeInput(
            value = value,
            onValueChange = { value = it },
            length = length,
            outlined = outlined,
            error = if (error) OudsError(errorMessage) else null,
            helperText = helperText
        )
    }
}

private fun Code.Builder.pinCodeInputDemoCodeSnippet(state: PinCodeInputDemoState) {
    with(state) {
        functionCall("OudsPinCodeInput") {
            typedArgument("value", value)
            lambdaArgument("onValueChange") {
                comment("Update value")
            }
            typedArgument("length", length)
            if (outlined) typedArgument("outlined", outlined)
            if (error) {
                errorArgument(errorMessage)
            }
            if (helperText.isNotEmpty()) typedArgument("helperText", helperText)
        }
    }

}

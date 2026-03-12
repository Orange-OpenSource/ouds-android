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
import com.orange.ouds.app.ui.components.Component
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
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
//        CustomizationDropdownMenu(
//            label = stringResource(R.string.app_components_pinCodeInput_length_label),
//            items = PinCodeInputLength.entries.map { it.displayName },
//            selectedItemIndex = PinCodeInputLength.entries.indexOfFirst { it.value == length },
//            onItemSelected = { index, _ ->
//                length = PinCodeInputLength.entries[index].value
//                code = "" // Reset code when length changes
//            }
//        )
//        CustomizationSwitchItem(
//            label = stringResource(R.string.app_components_common_outlined_label),
//            checked = outlined,
//            onCheckedChange = { outlined = it }
//        )
//        CustomizationSwitchItem(
//            label = stringResource(R.string.app_components_pinCodeInput_obscureText_label),
//            checked = obscureText,
//            onCheckedChange = { obscureText = it }
//        )
//        CustomizationSwitchItem(
//            label = stringResource(R.string.app_common_enabled_label),
//            checked = enabled,
//            onCheckedChange = { enabled = it }
//        )
//        CustomizationSwitchItem(
//            label = stringResource(R.string.app_components_common_error_label),
//            checked = error,
//            onCheckedChange = { error = it }
//        )
//        CustomizationTextInput(
//            applyTopPadding = true,
//            label = stringResource(R.string.app_components_common_errorMessage_label),
//            value = errorMessage,
//            onValueChange = { value -> errorMessage = value },
//            enabled = error
//        )
//        CustomizationTextInput(
//            applyTopPadding = true,
//            label = stringResource(R.string.app_components_common_helperText_label),
//            value = helperText,
//            onValueChange = { value -> helperText = value }
//        )
    }
}

@Composable
private fun PinCodeInputDemoContent(state: PinCodeInputDemoState) {
    with(state) {
//        OudsPinCodeInput(
//            value = code,
//            onValueChange = { code = it },
//            length = length,
//            outlined = outlined,
//            obscureText = obscureText,
//            enabled = enabled,
//            error = if (error) OudsError(errorMessage) else null,
//            helperText = if (!error && helperText.isNotBlank()) helperText else null,
//            onComplete = {
//                // Handle PIN code completion
//            }
//        )
    }
}

//@Composable
private fun pinCodeInputDemoCodeSnippet(state: PinCodeInputDemoState) = with(state) {
//    PinCodeInput(
//        length = length,
//        outlined = outlined,
//        obscureText = obscureText,
//        enabled = enabledArgument(enabled),
//        error = if (error) """OudsError("$errorMessage")""" else null,
//        helperText = if (!error && helperText.isNotBlank()) """"$helperText"""" else null
//    )
}

private enum class PinCodeInputLength(val value: Int, val displayName: String) {
    Four(4, "4"),
    Six(6, "6"),
    Eight(8, "8")
}

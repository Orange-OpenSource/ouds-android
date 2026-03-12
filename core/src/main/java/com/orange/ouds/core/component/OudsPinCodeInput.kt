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

package com.orange.ouds.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.error
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.component.common.OudsError
import com.orange.ouds.core.theme.LocalThemeSettings
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.OudsPreview

/**
 * PIN code input is a UI element that allows to capture short, fixed-length numeric codes, typically for authentication or confirmation purposes,
 * such as a four, six or eight-digit personal identification number (PIN).
 *
 * PIN code input is presented as a series of individual input fields or boxes, each representing a single digit, to enhance readability and encourage
 * accurate input, while supporting smooth keyboard navigation and secured input masking if needed.
 *
 * Rounded corners can be enabled or disabled using [com.orange.ouds.theme.OudsThemeSettings.roundedCornerTextInputs] property in the settings of the theme
 * provided when calling the [OudsTheme] method.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://r.orange.fr/r/S-ouds-doc-pin-code-input)
 *
 * > Design version: 1.2.0
 *
 * @param value Current PIN code value entered by the user.
 * @param onValueChange Callback that is triggered when the PIN code changes.
 * @param length Number of digits in the PIN code. Supports 4, 6, or 8 digits. Defaults to 6.
 * @param modifier [Modifier] applied to the PIN code input.
 * @param outlined Controls the style of the PIN code input. When `true`, it displays a minimalist input with a transparent background and a visible
 *   stroke outlining each field. When `false`, it shows a subtle background fill with a visible bottom border.
 * @param error Optional [OudsError] to indicate that the user input does not meet validation rules. The error state must be triggered by an explicit
 *   validation (submission, API response), not in real time with each keystroke. Pass `null` if there is no error.
 * @param helperText An optional helper text displayed below the PIN code input. It conveys additional information about the input, such as
 *   "Enter the 6-digit code sent to your phone." It should ideally only take up a single line.
 * @param enabled Controls the enabled state of the PIN code input. When `false`, the input will not be focusable and will not react to input events.
 *   True by default.
 * @param obscureText When `true`, the entered digits are masked (displayed as bullets). When `false`, the digits are visible. Defaults to `true`.
 * @param onComplete Callback that is triggered when all digits have been entered.
 * @param keyboardActions When the input service emits an IME action, the corresponding callback is called.
 *
 * @sample com.orange.ouds.core.component.samples.OudsPinCodeInputSample
 */
@Composable
fun OudsPinCodeInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    length: Int = 6,
    outlined: Boolean = false,
    error: OudsError? = null,
    helperText: String? = null,
    enabled: Boolean = true,
    obscureText: Boolean = true,
    onComplete: (() -> Unit)? = null,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    require(length in listOf(4, 6, 8)) { "PIN code length must be 4, 6, or 8" }

    val focusRequester = remember { FocusRequester() }
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    val textInputTokens = OudsTheme.componentsTokens.textInput
    val pinCodeInputTokens = OudsTheme.componentsTokens.pinCodeInput

    LaunchedEffect(value) {
        if (value.length == length) {
            onComplete?.invoke()
        }
    }

    Column(
        modifier = modifier.semantics {
            if (error != null) {
                this.error(error.message)
            }
            contentDescription = "PIN code input with $length digits"
        }
    ) {
        // Hidden text field for keyboard input
        BasicTextField(
            value = value,
            onValueChange = { newValue ->
                if (newValue.length <= length && newValue.all { it.isDigit() }) {
                    onValueChange(newValue)
                }
            },
            modifier = Modifier
                .width(0.dp)
                .height(0.dp)
                .focusRequester(focusRequester),
            enabled = enabled,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Done
            ),
            keyboardActions = keyboardActions,
            interactionSource = interactionSource,
            cursorBrush = SolidColor(Color.Transparent)
        )

        // Visible PIN code digit boxes
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(enabled = enabled) {
                    focusRequester.requestFocus()
                },
            horizontalArrangement = Arrangement.spacedBy(pinCodeInputTokens.spaceColumnGapDigitInput.value)
        ) {
            repeat(length) { index ->
                DigitBox(
                    digit = value.getOrNull(index)?.toString(),
                    isFocused = isFocused && value.length == index,
                    outlined = outlined,
                    hasError = error != null,
                    obscureText = obscureText,
                    textInputTokens = textInputTokens,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Helper text or error message
        val displayText = error?.message ?: helperText
        if (displayText != null) {
            Text(
                text = displayText,
                style = OudsTheme.typography.label.default.medium,
                color = if (error != null) {
                    OudsTheme.colorScheme.content.status.negative
                } else {
                    OudsTheme.colorScheme.content.muted
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = textInputTokens.spacePaddingBlockTopHelperText.value)
            )
        }
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

@Composable
private fun DigitBox(
    digit: String?,
    isFocused: Boolean,
    outlined: Boolean,
    hasError: Boolean,
    obscureText: Boolean,
    textInputTokens: com.orange.ouds.theme.tokens.components.OudsTextInputTokens,
    modifier: Modifier = Modifier
) {
    val roundedCorner = LocalThemeSettings.current.roundedCornerTextInputs == true
    val colors = OudsTheme.colorScheme
    val typography = OudsTheme.typography

    val backgroundColor = when {
        outlined -> Color.Transparent
        hasError -> colors.surface.status.negative.muted
        isFocused -> colors.action.support.pressed
        else -> colors.action.support.enabled
    }

    val borderColor = when {
        hasError -> colors.action.negative.enabled
        isFocused -> textInputTokens.colorBorderFocus.value
        else -> textInputTokens.colorBorderEnabled.value
    }

    val borderWidth = if (isFocused) {
        textInputTokens.borderWidthFocus.value
    } else {
        textInputTokens.borderWidthDefault.value
    }

    val shape = if (roundedCorner) {
        RoundedCornerShape(textInputTokens.borderRadiusRounded.value)
    } else {
        RoundedCornerShape(0.dp)
    }

    Box(
        modifier = modifier
            .height(textInputTokens.sizeMinHeight.dp)
            .widthIn(min = 44.dp, max = 56.dp)
            .then(
                if (outlined) {
                    Modifier.border(
                        width = borderWidth,
                        color = borderColor,
                        shape = shape
                    )
                } else {
                    Modifier
                        .background(backgroundColor, shape)
                        .border(
                            width = borderWidth,
                            color = borderColor,
                            shape = RoundedCornerShape(
                                topStart = if (roundedCorner) textInputTokens.borderRadiusRounded.value else 0.dp,
                                topEnd = if (roundedCorner) textInputTokens.borderRadiusRounded.value else 0.dp,
                                bottomStart = 0.dp,
                                bottomEnd = 0.dp
                            )
                        )
                }
            )
            .clip(shape)
            .padding(
                horizontal = textInputTokens.spacePaddingInlineDefault.value,
                vertical = textInputTokens.spacePaddingBlockDefault.value
            ),
        contentAlignment = Alignment.Center
    ) {
        when {
            digit != null -> {
                Text(
                    text = if (obscureText) "●" else digit,
                    style = typography.label.default.large,
                    color = colors.content.default,
                    textAlign = TextAlign.Center
                )
            }
            isFocused -> {
                Text(
                    text = "|",
                    style = typography.label.default.large,
                    color = colors.content.default,
                    textAlign = TextAlign.Center
                )
            }
            else -> {
                Text(
                    text = "-",
                    style = typography.label.default.large,
                    color = colors.content.muted,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview
@Composable
private fun OudsPinCodeInputPreview() {
    OudsPreview {
        var code by remember { mutableStateOf("") }
        OudsPinCodeInput(
            value = code,
            onValueChange = { code = it },
            length = 4,
            helperText = "Enter the 4-digit code sent to your phone."
        )
    }
}

@Preview
@Composable
private fun OudsPinCodeInputErrorPreview() {
    OudsPreview {
        var code by remember { mutableStateOf("1234") }
        OudsPinCodeInput(
            value = code,
            onValueChange = { code = it },
            length = 4,
            error = OudsError("Verification failed. Check and enter the correct code.")
        )
    }
}

@Preview
@Composable
private fun OudsPinCodeInputOutlinedPreview() {
    OudsPreview {
        var code by remember { mutableStateOf("12") }
        OudsPinCodeInput(
            value = code,
            onValueChange = { code = it },
            length = 6,
            outlined = true,
            helperText = "Enter the 6-digit code sent to your phone."
        )
    }
}

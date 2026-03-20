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

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicSecureTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.delete
import androidx.compose.foundation.text.input.forEachChangeReversed
import androidx.compose.foundation.text.input.insert
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.then
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.substring
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.orange.ouds.core.component.common.OudsError
import com.orange.ouds.core.extensions.InteractionState
import com.orange.ouds.core.extensions.collectInteractionStateAsState
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.OudsPreviewLightDark
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.core.utilities.mapSettings
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.theme.OudsThemeContract

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OudsPinCodeInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    length: OudsPinCodeInputLength = OudsPinCodeInputDefaults.Length,
    outlined: Boolean = false,
    error: OudsError? = null,
    helperText: String? = null,
    keyboardOptions: KeyboardOptions = OudsPinCodeInputDefaults.KeyboardOptions,
    onKeyboardAction: KeyboardActionHandler? = null,
    interactionSource: MutableInteractionSource? = null
) {
    @Suppress("NAME_SHADOWING") val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    val interactionState by interactionSource.collectInteractionStateAsState()
    val pinCodeInputTokens = OudsTheme.componentsTokens.pinCodeInput
    val paddedValue = value.padEnd(length.value, OudsDigitInputPlaceholder)
    val textFieldState = rememberTextFieldState(
        initialText = paddedValue,
        initialSelection = TextRange((value.length + 1).coerceIn(0, length.value))
    )

    if (paddedValue != textFieldState.text) {
        textFieldState.edit {
            val cursorPosition = selection.end.coerceIn(0, length.value)
            replace(0, length.value, paddedValue)
            // Set the cursor to its position before the replacement, because the replace method moves the cursor
            placeCursorBeforeCharAt(cursorPosition)
        }
    }

    LaunchedEffect(textFieldState) {
        snapshotFlow { textFieldState.text }
            .collect { text ->
                onValueChange(text.toString())
            }
    }

    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    BasicSecureTextField(
        modifier = modifier.focusRequester(focusRequester),
        state = textFieldState,
        keyboardOptions = keyboardOptions,
        onKeyboardAction = onKeyboardAction,
        inputTransformation = InputTransformation
            .then {
                changes.forEachChangeReversed { range, originalRange ->
                    // Insertion
                    if (range.length > 0) {
                        // Retrieve added text
                        val addedText = asCharSequence().substring(range)
                        // Delete the added text to roll back to the original text
                        delete(range.min, range.max)
                        // Replace the original text with the added text
                        replace(range.min - 1, range.max - 1, addedText)
                        placeCursorAfterCharAt((range.max - 1).coerceIn(0, length.value - 1))
                    }
                    // Deletion
                    else {
                        // Insert placeholder digits to replace the deleted text
                        val padText = OudsDigitInputPlaceholder.toString().repeat(originalRange.length)
                        insert(originalRange.start, padText)
                        // Get the first character of the deleted text
                        // If that character was not a digit (i.e. a placeholder was displayed in the digit input)
                        // then replace the previous character with a placeholder
                        val firstDeletedChar = originalText[originalRange.start]
                        val previousChar = asCharSequence().getOrNull(range.start - 1)
                        if (!firstDeletedChar.isDigit() && previousChar != null) {
                            replace(range.start - 1, range.start, OudsDigitInputPlaceholder.toString())
                            placeCursorAfterCharAt(range.start - 1)
                        }
                    }
                }
            },
        interactionSource = interactionSource,
        decorator = {
            ConstraintLayout {
                val (row, helperTextErrorMessage) = createRefs()
                Row(
                    modifier = Modifier
                        .constrainAs(row) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    horizontalArrangement = Arrangement.spacedBy(pinCodeInputTokens.spaceColumnGapDigitInput.value)
                ) {
                    repeat(length.value) { index ->
                        val isNonErrorPreview = LocalInspectionMode.current && error == null
                        val focusedDigitIndex = (textFieldState.selection.end - 1).coerceIn(0, length.value - 1)
                        val digitInputState = when {
                            (isNonErrorPreview || interactionState == InteractionState.Focused) && index == focusedDigitIndex -> OudsDigitInputState.Focused
                            interactionState == InteractionState.Hovered -> OudsDigitInputState.Hovered
                            else -> OudsDigitInputState.Enabled
                        }
                        OudsDigitInput(
                            digit = value.getOrNull(index),
                            onClick = {
                                focusRequester.requestFocus()
                                // If keyboard is dismissed using the Android back key, the keyboard won't reappear when digit is clicked
                                keyboardController?.show()
                                textFieldState.edit { placeCursorAfterCharAt(index) }
                            },
                            state = digitInputState,
                            outlined = outlined,
                            error = error != null,
                            placeholder = error == null
                        )
                    }
                }
                OudsTextInputHelperTextErrorMessage(
                    modifier = Modifier.constrainAs(helperTextErrorMessage) {
                        top.linkTo(row.bottom)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(row.start)
                        end.linkTo(row.end)
                        width = Dimension.fillToConstraints
                    },
                    enabled = true,
                    error = error,
                    helperText = helperText
                )
            }
        }
    )
}

enum class OudsPinCodeInputLength(val value: Int) {
    Four(4),
    Six(6),
    Eight(8)
}

/**
 * Default values for [OudsPinCodeInput].
 */
object OudsPinCodeInputDefaults {

    /**
     * Default length of an [OudsPinCodeInput].
     */
    val Length = OudsPinCodeInputLength.Six

    /**
     * Default keyboard options for an [OudsPinCodeInput].
     */
    val KeyboardOptions = KeyboardOptions(autoCorrectEnabled = false, keyboardType = KeyboardType.Number)
}

@Suppress("PreviewShouldNotBeCalledRecursively")
@OudsPreviewLightDark
@Composable
private fun PreviewOudsPinCodeInput(@PreviewParameter(OudsPinCodeInputPreviewParameterProvider::class) parameter: OudsPinCodeInputPreviewParameter) {
    PreviewOudsPinCodeInput(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsPinCodeInput(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsPinCodeInputPreviewParameter
) = OudsPreview(modifier = Modifier.padding(16.dp), theme = theme, darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        OudsPinCodeInput(
            value = value,
            onValueChange = {},
            length = OudsPinCodeInputLength.Four,
            outlined = outlined,
            error = error,
            helperText = helperText
        )
    }
}

internal data class OudsPinCodeInputPreviewParameter(
    val value: String,
    val outlined: Boolean = false,
    val error: OudsError? = null,
    val helperText: String? = null
)

internal class OudsPinCodeInputPreviewParameterProvider :
    BasicPreviewParameterProvider<OudsPinCodeInputPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsPinCodeInputPreviewParameter>
    get() {
        return listOf(
            OudsPinCodeInputPreviewParameter(value = "12", helperText = "Enter the 4-digit code sent to your phone."),
            OudsPinCodeInputPreviewParameter(value = "12", error = OudsError("Verification failed. Check and enter the correct code."))
        ).flatMap { listOf(it, it.copy(outlined = true)) }
    }

@OudsPreview
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsPinCodeInputWithRoundedCorners(@PreviewParameter(OudsPinCodeInputWithRoundedCornersPreviewParameterProvider::class) outlined: Boolean) =
    PreviewOudsPinCodeInputWithRoundedCorners(theme = getPreviewTheme(), outlined = outlined)

@Composable
internal fun PreviewOudsPinCodeInputWithRoundedCorners(
    theme: OudsThemeContract,
    outlined: Boolean
) = OudsPreview(modifier = Modifier.padding(16.dp), theme = theme.mapSettings { it.copy(roundedCornerTextInputs = true) }) {
    val value = "12"
    OudsPinCodeInput(
        value = value,
        onValueChange = {},
        length = OudsPinCodeInputLength.Four,
        outlined = outlined
    )
}

internal class OudsPinCodeInputWithRoundedCornersPreviewParameterProvider : BasicPreviewParameterProvider<Boolean>(false, true)

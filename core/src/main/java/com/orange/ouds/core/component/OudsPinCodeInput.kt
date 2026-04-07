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
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicSecureTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.delete
import androidx.compose.foundation.text.input.forEachChangeReversed
import androidx.compose.foundation.text.input.insert
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.RichTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TooltipAnchorPosition
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalClipboard
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.hideFromAccessibility
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.substring
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.orange.ouds.core.R
import com.orange.ouds.core.component.common.OudsError
import com.orange.ouds.core.extensions.InteractionState
import com.orange.ouds.core.extensions.collectInteractionStateAsState
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.currentWindowWidth
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.OudsPreviewLightDark
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.core.utilities.mapSettings
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.theme.OudsThemeContract
import com.orange.ouds.theme.OudsThemeSettings
import kotlinx.coroutines.launch

/**
 * PIN code input is a UI element that allows to capture short, fixed-length numeric codes, typically for authentication or confirmation purposes, such as a
 * four, six or eight-digit personal identification number (PIN). PIN code input is presented as a series of individual input fields or boxes, each
 * representing a single digit, to enhance readability and encourage accurate input, while supporting smooth keyboard navigation and secured input masking if
 * needed.
 *
 * Rounded corners can be enabled or disabled using [OudsThemeSettings.roundedCornerTextInputs] property in the settings of the theme provided when calling
 * the [com.orange.ouds.core.theme.OudsTheme] method.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://r.orange.fr/r/S-ouds-doc-pin-code-input)
 *
 * > Design version: 1.2.0
 *
 * @param value The current PIN code value as a string of digits. The value is automatically truncated to the specified [length].
 * @param onValueChange Callback invoked when the PIN code value changes. The updated PIN code value comes as a parameter of the callback.
 * @param modifier [Modifier] applied to the PIN code input.
 * @param length The number of digits in the PIN code. Defaults to [OudsPinCodeInputDefaults.Length].
 * @param outlined Controls the style of the PIN code input. When `true`, it displays a minimalist PIN code input with a transparent background and a visible
 *   stroke outlining each digit box.
 * @param error Optional [OudsError] to indicate that the user input does not meet validation rules or expected formatting. Pass `null` if there is no error.
 * @param helperText An optional helper text displayed below the PIN code input. It conveys additional information about the input field, such as how it will be
 *   used. It should ideally only take up a single line, though it may wrap to multiple lines if required.
 * @param onKeyboardAction Called when the user presses the action button in the input method editor (IME), or by pressing the enter key on a hardware keyboard.
 *   By default, this parameter is null, and would execute the default behavior for a received IME Action e.g., [androidx.compose.ui.text.input.ImeAction.Done] would close the keyboard,
 *   [androidx.compose.ui.text.input.ImeAction.Next] would switch the focus to the next focusable item on the screen.
 * @param interactionSource An optional hoisted [MutableInteractionSource] for observing and emitting [Interaction]s for this PIN code input. Note that if `null`
 *   is provided, interactions will still happen internally.
 *
 * @sample com.orange.ouds.core.component.samples.OudsPinCodeInputSample
 *
 * @sample com.orange.ouds.core.component.samples.OudsPinCodeInputErrorSample
 */
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun OudsPinCodeInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    length: OudsPinCodeInputLength = OudsPinCodeInputDefaults.Length,
    outlined: Boolean = false,
    error: OudsError? = null,
    helperText: String? = null,
    onKeyboardAction: KeyboardActionHandler? = null,
    interactionSource: MutableInteractionSource? = null
) {
    @Suppress("NAME_SHADOWING") val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    val paddedValue = value.take(length.value).padEnd(length.value, OudsDigitInputPlaceholder)
    val textFieldState = rememberTextFieldState(
        initialText = paddedValue,
        initialSelection = TextRange((value.length + 1).coerceIn(0, length.value))
    )

    if (paddedValue != textFieldState.text) {
        textFieldState.edit {
            val cursorPosition = selection.end.coerceIn(0, length.value)
            delete(0, this.length)
            append(paddedValue)
            // Set the cursor to its position before the text replacement
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

    OudsPinCodeInputTooltipBox(
        textFieldState = textFieldState,
        length = length
    ) {
        BasicSecureTextField(
            modifier = modifier
                .heightIn(min = OudsTheme.componentsTokens.textInput.sizeMinHeight.dp)
                .focusRequester(focusRequester),
            state = textFieldState,
            keyboardOptions = KeyboardOptions(autoCorrectEnabled = false, keyboardType = KeyboardType.Number),
            onKeyboardAction = onKeyboardAction,
            inputTransformation = inputTransformation(length),
            interactionSource = interactionSource,
            decorator = {
                OudsPinCodeInputDecorator(
                    textFieldState = textFieldState,
                    length = length,
                    outlined = outlined,
                    error = error,
                    helperText = helperText,
                    onDigitClick = {
                        focusRequester.requestFocus()
                        // If keyboard is dismissed using the Android back key, the keyboard won't reappear when digit is clicked
                        keyboardController?.show()
                    },
                    interactionSource = interactionSource
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun OudsPinCodeInputTooltipBox(textFieldState: TextFieldState, length: OudsPinCodeInputLength, content: @Composable () -> Unit) {
    val tooltipState = rememberTooltipState(isPersistent = true)
    // TODO: Replace with OUDS tooltip when available 
    TooltipBox(
        positionProvider = TooltipDefaults.rememberTooltipPositionProvider(
            positioning = TooltipAnchorPosition.Above,
            spacingBetweenTooltipAndAnchor = OudsTheme.spaces.fixed.extraSmall
        ),
        tooltip = {
            val clipboard = LocalClipboard.current
            val scope = rememberCoroutineScope()
            RichTooltip {
                CompositionLocalProvider(LocalRippleConfiguration provides null) {
                    TextButton(
                        onClick = {
                            scope.launch {
                                clipboard.getClipEntry()?.clipData?.let { clipData ->
                                    if (clipData.itemCount > 0) {
                                        val text = clipData.getItemAt(0).text.toString()
                                        textFieldState.edit {
                                            insert(selection.min, text)
                                            with(inputTransformation(length)) {
                                                transformInput()
                                            }
                                        }
                                    }
                                }
                                tooltipState.dismiss()
                            }
                        }
                    ) {
                        Text(text = stringResource(R.string.core_pinCodeInput_paste_label))
                    }
                }
            }
        },
        state = tooltipState,
        content = content
    )
}

@Composable
private fun OudsPinCodeInputDecorator(
    textFieldState: TextFieldState,
    length: OudsPinCodeInputLength,
    outlined: Boolean,
    error: OudsError?,
    helperText: String?,
    onDigitClick: (Int) -> Unit,
    interactionSource: MutableInteractionSource
) {
    val interactionState by interactionSource.collectInteractionStateAsState()
    val pinCodeInputTokens = OudsTheme.componentsTokens.pinCodeInput
    val smallDeviceSpecificRules = smallDeviceSpecificRules(length)
    BoxWithConstraints(contentAlignment = Alignment.Center) {
        val horizontalSpace = if (smallDeviceSpecificRules) 6.dp else pinCodeInputTokens.spaceColumnGapDigitInput.value
        val totalHorizontalSpace = horizontalSpace * (length.value - 1)
        val digitWidth = (maxWidth - totalHorizontalSpace) / length.value
        ConstraintLayout {
            val (row, helperTextErrorMessage) = createRefs()
            Row(
                modifier = Modifier
                    .constrainAs(row) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                horizontalArrangement = Arrangement.spacedBy(horizontalSpace)
            ) {
                val isNonErrorPreview = LocalInspectionMode.current && error == null
                val focusedDigitIndex = (textFieldState.selection.end - 1).coerceIn(0, length.value - 1)
                repeat(length.value) { index ->
                    val digitInputState = when {
                        (isNonErrorPreview || interactionState == InteractionState.Focused) && index == focusedDigitIndex -> OudsDigitInputState.Focused
                        interactionState == InteractionState.Hovered -> OudsDigitInputState.Hovered
                        else -> OudsDigitInputState.Enabled
                    }
                    OudsDigitInput(
                        modifier = Modifier
                            .width(digitWidth)
                            .semantics { hideFromAccessibility() },
                        digit = textFieldState.text.getOrNull(index),
                        onClick = {
                            onDigitClick(index)
                            textFieldState.edit { placeCursorAfterCharAt(index) }
                        },
                        state = digitInputState,
                        outlined = outlined,
                        error = error != null,
                        placeholder = error == null,
                        smallDeviceSpecificRules = smallDeviceSpecificRules
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
}

@Composable
private fun smallDeviceSpecificRules(length: OudsPinCodeInputLength): Boolean {
    return length == OudsPinCodeInputLength.Eight && currentWindowWidth() < 484.dp
}

@OptIn(ExperimentalFoundationApi::class)
private fun inputTransformation(length: OudsPinCodeInputLength): InputTransformation {
    return InputTransformation {
        changes.forEachChangeReversed { range, originalRange ->
            // Insertion
            if (range.length > 0) {
                val pasting = range.length > 1
                val baseText = if (pasting) OudsDigitInputPlaceholder.toString().repeat(length.value) else originalText.toString()
                // Retrieve added text
                val addedText = asCharSequence().substring(range).filter { it.isDigit() }
                // Roll back to the original text or placeholders if pasting
                delete(0, this.length)
                insert(0, baseText)
                // Replace the base text with the added text
                // When pasting (i.e. range.length > 1), the base text is replaced from the start
                val start = if (pasting) 0 else range.min - 1
                val end = start + addedText.length
                replace(start.coerceIn(0, length.value), end.coerceIn(0, length.value), addedText)
                placeCursorAfterCharAt(end.coerceIn(0, length.value - 1))
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
    }
}

/**
 * Represents the supported lengths for [OudsPinCodeInput].
 *
 * @property value The number of digits in the PIN code.
 */
enum class OudsPinCodeInputLength(val value: Int) {

    /**
     * Four-digit PIN code.
     */
    Four(4),

    /**
     * Six-digit PIN code.
     */
    Six(6),

    /**
     * Eight-digit PIN code.
     */
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
private fun PreviewOudsPinCodeInputWithRoundedCorners(@PreviewParameter(OudsPinCodeInputWithRoundedCornersPreviewParameterProvider::class) outlined: Boolean) {
    PreviewOudsPinCodeInputWithRoundedCorners(theme = getPreviewTheme(), outlined = outlined)
}

@Composable
internal fun PreviewOudsPinCodeInputWithRoundedCorners(
    theme: OudsThemeContract,
    outlined: Boolean
) = OudsPreview(modifier = Modifier.padding(16.dp), theme = theme.mapSettings { it.copy(roundedCornerTextInputs = true) }) {
    OudsPinCodeInput(
        value = "12",
        onValueChange = {},
        length = OudsPinCodeInputLength.Four,
        outlined = outlined
    )
}

internal class OudsPinCodeInputWithRoundedCornersPreviewParameterProvider : BasicPreviewParameterProvider<Boolean>(false, true)

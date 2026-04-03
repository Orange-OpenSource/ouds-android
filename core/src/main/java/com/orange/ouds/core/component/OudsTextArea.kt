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

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.hideFromAccessibility
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.R
import com.orange.ouds.core.component.common.OudsError
import com.orange.ouds.core.extensions.collectInteractionStateAsState
import com.orange.ouds.core.theme.LocalThemeSettings
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.OudsPreviewDevice
import com.orange.ouds.core.utilities.OudsPreviewableComponent
import com.orange.ouds.core.utilities.PreviewEnumEntries
import com.orange.ouds.core.utilities.getPreviewEnumEntry
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.core.utilities.mapSettings
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.theme.OudsThemeContract
import com.orange.ouds.theme.OudsThemeSettings

/**
 * Text area is a UI element that allows to type, edit, or select longer blocks of textual data, such as comments, messages or descriptions; by expanding
 * vertically and offering more space to input text. Text area includes features like a visible label, placeholder text, character limits and resize behavior;
 * and is ideal for open-ended responses where users need to express detailed information.
 *
 * Rounded corners can be enabled or disabled using [OudsThemeSettings.roundedCornerTextInputs] property in the settings of the theme provided when calling
 * the [com.orange.ouds.core.theme.OudsTheme] method.
 *
 * It is recommended to use state-based text areas rather than value-based ones, as they provide a more complete and reliable approach to managing
 * the state of a text area.
 *
 * This variant, unlike the others, enables a vertical scrollbar when the input text exceeds the text area's maximum capacity.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://r.orange.fr/r/S-ouds-doc-text-area)
 *
 * > Design version: 1.1.0
 *
 * @param textFieldState The editable text state of the text area, including both the text itself and position of the cursor or selection.
 * @param modifier [Modifier] applied to the text area.
 * @param label Label displayed above the text area. It describes the purpose of the input.
 * @param placeholder Text displayed when the text area is empty. It provides a hint or guidance inside the field to suggest expected input.
 * @param enabled Controls the enabled state of the text area. When `false`, this text area will not be focusable and will not react to input events.
 *   True by default.
 * @param readOnly Controls the read-only state of the text area. When `true`, the text is visible but not editable.
 *   False by default.
 * @param loader An optional loading progress indicator displayed in the text area to indicate an ongoing operation.
 * @param outlined Controls the style of the text area. When `true`, it displays a minimalist text area with a transparent background and a visible
 *   stroke outlining the field.
 * @param error Optional [OudsError] to indicate that the user input does not meet validation rules or expected formatting. Pass `null` if there is no error.
 * @param helperText An optional helper text displayed below the text area. It conveys additional information about the input field, such as how it will be
 *   used. It should ideally only take up a single line, though it may wrap to multiple lines if required.
 * @param helperLink An optional helper link displayed below or in place of the helper text.
 * @param constrainedMaxWidth When `true`, the text area width is constrained to a maximum value defined by the design system.
 *   When `false`, no specific width constraint is applied, allowing the component to size itself or follow external modifiers.
 *   Defaults to `false`.
 * @param keyboardOptions Software keyboard options that contain configurations such as [KeyboardType] and [ImeAction].
 * @param onKeyboardAction Called when the user presses the action button in the input method editor (IME), or by pressing the enter key on a hardware keyboard.
 *   By default, this parameter is null, and would execute the default behavior for a received IME Action e.g., [ImeAction.Done] would close the keyboard,
 *   [ImeAction.Next] would switch the focus to the next focusable item on the screen.
 * @param onTextLayout Callback that is executed when the text layout becomes queryable. The callback receives a function that returns a [TextLayoutResult] if
 *   the layout can be calculated, or null if it cannot. The function reads the layout result from a snapshot state object, and will invalidate its caller when
 *   the layout result changes. A [TextLayoutResult] object contains paragraph information, size of the text, baselines and other details. The callback can be
 *   used to add additional decoration or functionality to the text. For example, to draw a cursor or selection around the text. [Density] scope is the one that
 *   was used while creating the given text layout.
 * @param inputTransformation An optional [InputTransformation] that will be used to transform changes to the [TextFieldState] made by the user. The transformation
 *   will be applied to changes made by hardware and software keyboard events, pasting or dropping text, accessibility services, and tests. The transformation
 *   will _not_ be applied when changing the [textFieldState] programmatically, or when the transformation is changed. If the transformation is changed on an
 *   existing text field, it will be applied to the next user edit. The transformation will not immediately affect the current [textFieldState].
 * @param outputTransformation An optional [OutputTransformation] that transforms how the contents of the text field are presented.
 * @param interactionSource An optional hoisted [MutableInteractionSource] for observing and emitting [Interaction]s for this text area. Note that if `null`
 *   is provided, interactions will still happen internally.
 *
 * @sample com.orange.ouds.core.component.samples.OudsTextAreaStateBasedSample
 *
 * @sample com.orange.ouds.core.component.samples.OudsTextAreaStateBasedErrorSample
 */
@Composable
fun OudsTextArea(
    textFieldState: TextFieldState,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    loader: OudsTextInputLoader? = null,
    outlined: Boolean = false,
    error: OudsError? = null,
    helperText: String? = null,
    helperLink: OudsTextInputHelperLink? = null,
    constrainedMaxWidth: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onKeyboardAction: KeyboardActionHandler? = null,
    onTextLayout: (Density.(getResult: () -> TextLayoutResult?) -> Unit)? = null,
    inputTransformation: InputTransformation? = null,
    outputTransformation: OutputTransformation? = null,
    interactionSource: MutableInteractionSource? = null
) {
    @Suppress("NAME_SHADOWING") val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    val interactionState by interactionSource.collectInteractionStateAsState()
    val state = getTextInputState(enabled = enabled, readOnly = readOnly, loader = loader, interactionState = interactionState)

    val emptyText = textFieldState.text.isEmpty()

    val scrollState = rememberScrollState()

    OudsTextArea(
        state = state,
        emptyText = emptyText,
        readOnly = readOnly,
        error = error,
        basicTextField = { minLines, maxLines, textStyle ->
            BasicTextField(
                modifier = modifier.textInputSemantic(label),
                state = textFieldState,
                enabled = textInputEnabled(state = state),
                readOnly = readOnly,
                textStyle = textStyle,
                lineLimits = TextFieldLineLimits.MultiLine(minHeightInLines = minLines, maxHeightInLines = maxLines),
                cursorBrush = textInputCursorBrush(state = state, error = error != null),
                keyboardOptions = keyboardOptions,
                onKeyboardAction = onKeyboardAction,
                onTextLayout = onTextLayout,
                inputTransformation = inputTransformation,
                outputTransformation = outputTransformation,
                interactionSource = interactionSource,
                decorator = { innerTextField ->
                    OudsTextAreaDecorator(
                        innerTextField = innerTextField,
                        value = textFieldState.text.toString(),
                        state = state,
                        label = label,
                        placeholder = placeholder,
                        loader = loader,
                        outlined = outlined,
                        error = error,
                        helperText = helperText,
                        helperLink = helperLink,
                        constrainedMaxWidth = constrainedMaxWidth,
                        scrollState = scrollState,
                    )
                },
                scrollState = scrollState
            )
        }
    )
}

/**
 * Text area is a UI element that allows to type, edit, or select longer blocks of textual data, such as comments, messages or descriptions; by expanding
 * vertically and offering more space to input text. Text area includes features like a visible label, placeholder text, character limits and resize behavior;
 * and is ideal for open-ended responses where users need to express detailed information.
 *
 * Rounded corners can be enabled or disabled using [OudsThemeSettings.roundedCornerTextInputs] property in the settings of the theme provided when calling
 * the [com.orange.ouds.core.theme.OudsTheme] method.
 *
 * It is recommended to use state-based text areas rather than value-based ones, as they provide a more complete and reliable approach to managing
 * the state of a text area.
 *
 * Note: This variant does not support a vertical scrollbar. For scrollbar functionality when text exceeds the available space, please use the state-based
 * version of [OudsTextArea].
 *
 * > Design guidelines: [unified-design-system.orange.com](https://r.orange.fr/r/S-ouds-doc-text-area)
 *
 * > Design version: 1.1.0
 *
 * @param value Input text to be shown in the text area.
 * @param onValueChange Callback that is triggered when the input service updates the text. An updated text comes as a parameter of the callback.
 * @param modifier [Modifier] applied to the text area.
 * @param label Label displayed above the text area. It describes the purpose of the input.
 * @param placeholder Text displayed when the text area is empty. It provides a hint or guidance inside the field to suggest expected input.
 * @param enabled Controls the enabled state of the text area. When `false`, this text area will not be focusable and will not react to input events.
 *   True by default.
 * @param readOnly Controls the read-only state of the text area. When `true`, the text is visible but not editable.
 *   False by default.
 * @param loader An optional loading progress indicator displayed in the text area to indicate an ongoing operation.
 * @param outlined Controls the style of the text area. When `true`, it displays a minimalist text area with a transparent background and a visible
 *   stroke outlining the field.
 * @param error Optional [OudsError] to indicate that the user input does not meet validation rules or expected formatting. Pass `null` if there is no error.
 * @param helperText An optional helper text displayed below the text area. It conveys additional information about the input field, such as how it will be
 *   used. It should ideally only take up a single line, though it may wrap to multiple lines if required.
 * @param helperLink An optional helper link displayed below or in place of the helper text.
 * @param constrainedMaxWidth When `true`, the text area width is constrained to a maximum value defined by the design system.
 *   When `false`, no specific width constraint is applied, allowing the component to size itself or follow external modifiers.
 *   Defaults to `false`.
 * @param keyboardOptions Software keyboard options that contain configuration such as [KeyboardType] and [ImeAction].
 * @param keyboardActions When the input service emits an IME action, the corresponding callback is called. Note that this IME action may be different from what
 *   you specified in [KeyboardOptions.imeAction].
 * @param onTextLayout Callback that is executed when a new text layout is calculated. A [TextLayoutResult] object that callback provides contains paragraph
 *   information, size of the text, baselines and other details. The callback can be used to add additional decoration or functionality to the text.
 *   For example, to draw a cursor or selection around the text.
 * @param visualTransformation The visual transformation filter for changing the visual representation of the input. By default, no visual transformation is applied.
 * @param interactionSource An optional hoisted [MutableInteractionSource] for observing and emitting [Interaction]s for this text area. Note that if `null`
 *   is provided, interactions will still happen internally.
 *
 * @sample com.orange.ouds.core.component.samples.OudsTextAreaValueBasedSample
 *
 * @sample com.orange.ouds.core.component.samples.OudsTextAreaValueBasedErrorSample
 */
@Composable
fun OudsTextArea(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    loader: OudsTextInputLoader? = null,
    outlined: Boolean = false,
    error: OudsError? = null,
    helperText: String? = null,
    helperLink: OudsTextInputHelperLink? = null,
    constrainedMaxWidth: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    visualTransformation: VisualTransformation = VisualTransformation.None,
    interactionSource: MutableInteractionSource? = null
) {
    @Suppress("NAME_SHADOWING") val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    val interactionState by interactionSource.collectInteractionStateAsState()
    val state = getTextInputState(enabled = enabled, readOnly = readOnly, loader = loader, interactionState = interactionState)

    val emptyText = value.isEmpty()

    OudsTextArea(
        state = state,
        emptyText = emptyText,
        readOnly = readOnly,
        error = error,
        basicTextField = { minLines, maxLines, textStyle ->
            BasicTextField(
                modifier = modifier.textInputSemantic(label),
                value = value,
                onValueChange = onValueChange,
                enabled = textInputEnabled(state = state),
                readOnly = readOnly,
                textStyle = textStyle,
                minLines = minLines,
                maxLines = maxLines,
                cursorBrush = textInputCursorBrush(state = state, error = error != null),
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                onTextLayout = onTextLayout,
                visualTransformation = visualTransformation,
                interactionSource = interactionSource,
                decorationBox = { innerTextField ->
                    OudsTextAreaDecorator(
                        innerTextField = innerTextField,
                        value = value,
                        state = state,
                        label = label,
                        placeholder = placeholder,
                        loader = loader,
                        outlined = outlined,
                        error = error,
                        helperText = helperText,
                        helperLink = helperLink,
                        constrainedMaxWidth = constrainedMaxWidth
                    )
                }
            )
        }
    )
}

/**
 * Text area is a UI element that allows to type, edit, or select longer blocks of textual data, such as comments, messages or descriptions; by expanding
 * vertically and offering more space to input text. Text area includes features like a visible label, placeholder text, character limits and resize behavior;
 * and is ideal for open-ended responses where users need to express detailed information.
 *
 * Rounded corners can be enabled or disabled using [OudsThemeSettings.roundedCornerTextInputs] property in the settings of the theme provided when calling
 * the [com.orange.ouds.core.theme.OudsTheme] method.
 *
 * It is recommended to use state-based text areas rather than value-based ones, as they provide a more complete and reliable approach to managing
 * the state of a text area.
 *
 * Note: This variant does not support a vertical scrollbar. For scrollbar functionality when text exceeds the available space, please use the state-based
 * version of [OudsTextArea].
 *
 * > Design guidelines: [unified-design-system.orange.com](https://r.orange.fr/r/S-ouds-doc-text-area)
 *
 * > Design version: 1.1.0
 *
 * @param value The [androidx.compose.ui.text.input.TextFieldValue] to be shown in the text area.
 * @param onValueChange Called when the input service updates the values in [TextFieldValue].
 * @param modifier [Modifier] applied to the text area.
 * @param label Label displayed above the text area. It describes the purpose of the input.
 * @param placeholder Text displayed when the text area is empty. It provides a hint or guidance inside the field to suggest expected input.
 * @param enabled Controls the enabled state of the text area. When `false`, this text area will not be focusable and will not react to input events.
 *   True by default.
 * @param readOnly Controls the read-only state of the text area. When `true`, the text is visible but not editable.
 *   False by default.
 * @param loader An optional loading progress indicator displayed in the text area to indicate an ongoing operation.
 * @param outlined Controls the style of the text area. When `true`, it displays a minimalist text area with a transparent background and a visible
 *   stroke outlining the field.
 * @param error Optional [OudsError] to indicate that the user input does not meet validation rules or expected formatting. Pass `null` if there is no error.
 * @param helperText An optional helper text displayed below the text area. It conveys additional information about the input field, such as how it will be
 *   used. It should ideally only take up a single line, though it may wrap to multiple lines if required.
 * @param helperLink An optional helper link displayed below or in place of the helper text.
 * @param constrainedMaxWidth When `true`, the text area width is constrained to a maximum value defined by the design system.
 *   When `false`, no specific width constraint is applied, allowing the component to size itself or follow external modifiers.
 *   Defaults to `false`.
 * @param keyboardOptions Software keyboard options that contain configuration such as [KeyboardType] and [ImeAction].
 * @param keyboardActions When the input service emits an IME action, the corresponding callback is called. Note that this IME action may be different from what
 *   you specified in [KeyboardOptions.imeAction].
 * @param onTextLayout Callback that is executed when a new text layout is calculated. A [TextLayoutResult] object that callback provides contains paragraph
 *   information, size of the text, baselines and other details. The callback can be used to add additional decoration or functionality to the text.
 *   For example, to draw a cursor or selection around the text.
 * @param visualTransformation The visual transformation filter for changing the visual representation of the input. By default, no visual transformation is applied.
 * @param interactionSource An optional hoisted [MutableInteractionSource] for observing and emitting [Interaction]s for this text area. Note that if `null`
 *   is provided, interactions will still happen internally.
 *
 * @sample com.orange.ouds.core.component.samples.OudsTextAreaValueBasedSample
 *
 * @sample com.orange.ouds.core.component.samples.OudsTextAreaValueBasedErrorSample
 */
@Composable
fun OudsTextArea(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    loader: OudsTextInputLoader? = null,
    outlined: Boolean = false,
    error: OudsError? = null,
    helperText: String? = null,
    helperLink: OudsTextInputHelperLink? = null,
    constrainedMaxWidth: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    visualTransformation: VisualTransformation = VisualTransformation.None,
    interactionSource: MutableInteractionSource? = null
) {
    @Suppress("NAME_SHADOWING") val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    val interactionState by interactionSource.collectInteractionStateAsState()
    val state = getTextInputState(enabled = enabled, readOnly = readOnly, loader = loader, interactionState = interactionState)

    val emptyText = value.text.isEmpty()

    OudsTextArea(
        state = state,
        emptyText = emptyText,
        readOnly = readOnly,
        error = error,
        basicTextField = { minLines, maxLines, textStyle ->
            BasicTextField(
                modifier = modifier.textInputSemantic(label),
                value = value,
                onValueChange = onValueChange,
                enabled = textInputEnabled(state = state),
                readOnly = readOnly,
                textStyle = textStyle,
                minLines = minLines,
                maxLines = maxLines,
                cursorBrush = textInputCursorBrush(state = state, error = error != null),
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                onTextLayout = onTextLayout,
                visualTransformation = visualTransformation,
                interactionSource = interactionSource,
                decorationBox = { innerTextField ->
                    OudsTextAreaDecorator(
                        innerTextField = innerTextField,
                        value = value.text,
                        state = state,
                        label = label,
                        placeholder = placeholder,
                        loader = loader,
                        outlined = outlined,
                        error = error,
                        helperText = helperText,
                        helperLink = helperLink,
                        constrainedMaxWidth = constrainedMaxWidth,
                    )
                }
            )
        }
    )
}

@Composable
internal fun OudsTextArea(
    state: OudsTextInputState,
    emptyText: Boolean,
    readOnly: Boolean,
    error: OudsError?,
    basicTextField: @Composable (minLines: Int, maxLines: Int, textStyle: TextStyle) -> Unit
) {
    val textAreaTokens = OudsTheme.componentsTokens.textArea
    val textStyle = textInputTextStyle(state = state)
    val density = LocalDensity.current
    val fontScale = LocalConfiguration.current.fontScale

    val (minLines, maxLines) = remember(textAreaTokens, textStyle.lineHeight, density, fontScale) {
        computeTextAreaLines(
            minHeightDp = textAreaTokens.sizeMinHeightInput,
            maxHeightDp = textAreaTokens.sizeMaxHeightInput,
            lineHeight = textStyle.lineHeight,
            density = density,
            fontScale = fontScale
        )
    }

    OudsTextInput(
        state = state,
        emptyText = emptyText,
        readOnly = readOnly,
        error = error,
        basicTextField = { basicTextField(minLines, maxLines, textStyle) }
    )
}

/**
 * Minimum lines displayed in an OUDS Text Area.
 * This value ensures that the text area is large enough to be recognized as a multi-line input while maintaining a consistent minimum height across different
 * font scales.
 */
private const val MinLines = 3

/**
 * Calculates the minimum and maximum number of lines for a text area based on design tokens and font scale.
 * This ensures the text area adapts to accessibility settings (e.g., larger font sizes).
 *
 * @param minHeightDp Minimum height from design tokens in dp
 * @param maxHeightDp Maximum height from design tokens in dp
 * @param lineHeight Line height of the text style
 * @param density Current density for dp to px conversion
 * @param fontScale Current font scale for accessibility
 * @return Pair of (minLines, maxLines)
 */
private fun computeTextAreaLines(
    minHeightDp: Float,
    maxHeightDp: Float,
    lineHeight: TextUnit,
    density: Density,
    fontScale: Float
): Pair<Int, Int> {
    with(density) {
        // Convert line height to dp, accounting for font scale
        val lineHeightDp = if (lineHeight.isSp) {
            (lineHeight.value * fontScale).dp.toPx() / this.density
        } else {
            // Default line height approximation if lineHeight is not in sp (shouldn't happen normally)
            24f * fontScale
        }

        // Compute number of lines that fit in the given heights
        val minLines = (minHeightDp / lineHeightDp).toInt().coerceAtLeast(MinLines)
        val maxLines = (maxHeightDp / lineHeightDp).toInt().coerceAtLeast(minLines)

        return minLines to maxLines
    }
}

@Composable
internal fun OudsTextAreaDecorator(
    innerTextField: @Composable () -> Unit,
    value: String,
    state: OudsTextInputState,
    label: String?,
    placeholder: String?,
    loader: OudsTextInputLoader?,
    outlined: Boolean,
    error: OudsError?,
    helperText: String?,
    helperLink: OudsTextInputHelperLink?,
    constrainedMaxWidth: Boolean,
    scrollState: ScrollState = rememberScrollState(),
) {
    val hasError = error != null
    val textInputTokens = OudsTheme.componentsTokens.textInput
    val textAreaTokens = OudsTheme.componentsTokens.textArea
    with(textInputTokens) {
        val borderRadius = if (LocalThemeSettings.current.roundedCornerTextInputs == true) borderRadiusRounded.value else borderRadiusDefault.value
        val shape = RoundedCornerShape(borderRadius)

        val styleModifier = if ((outlined && state != OudsTextInputState.ReadOnly) || (!outlined && state == OudsTextInputState.ReadOnly)) {
            // outlined
            borderWidth(state)?.let { borderWidth ->
                Modifier.border(
                    width = borderWidth,
                    color = borderColor(state = state, outlined = outlined, error = hasError),
                    shape = shape
                )
            }.orElse {
                Modifier
            }
        } else {
            // filled
            Modifier
                .textInputBottomBorder(state = state, outlined = outlined, cornerRadius = borderRadius, error = hasError)
                .background(
                    color = backgroundColor(state = state, outlined = outlined, error = hasError),
                    shape = shape
                )
        }

        Column(modifier = Modifier.sizeIn(minWidth = sizeMinWidth.dp)) {
            Row(
                modifier = styleModifier
                    .widthIn(max = if (constrainedMaxWidth) textAreaTokens.sizeMaxWidth.dp else Dp.Unspecified)
                    .padding(vertical = textAreaTokens.spacePaddingBlock.value)
                    .padding(start = spacePaddingInlineDefault.value, end = spacePaddingInlineTrailingAction.value)
                    .verticalScrollBar(scrollState = scrollState),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(spaceColumnGapDefault.value)
            ) {
                // Central content
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(spaceRowGapLabelInput.value),
                ) {
                    // Small label on top
                    if (!label.isNullOrBlank()) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .semantics { hideFromAccessibility() },
                            text = label,
                            style = OudsTheme.typography.label.default.small,
                            color = labelColor(state = state, error = hasError),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    // Placeholder or Value
                    Box(modifier = Modifier.fillMaxWidth()) {
                        if (value.isEmpty() && !placeholder.isNullOrBlank()) {
                            Text(
                                modifier = if (!helperText.isNullOrBlank()) Modifier.semantics { hideFromAccessibility() } else Modifier,
                                text = placeholder,
                                style = OudsTheme.typography.label.default.large,
                                color = decorativeContentColor(state = state),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        innerTextField()
                    }
                }

                // Trailing elements (error icon or loader)
                Box(
                    modifier = Modifier
                        .widthIn(min = OudsTheme.componentsTokens.button.sizeMinWidth.value)
                        .heightIn(min = OudsTheme.componentsTokens.button.sizeMinHeight.value, max = textAreaTokens.sizeMaxHeightAssetsContainer.dp)
                        .fillMaxHeight()
                        .padding(all = OudsTheme.componentsTokens.button.spaceInsetIconOnly.value),
                    contentAlignment = Alignment.Center
                ) {
                    val buttonTokens = OudsTheme.componentsTokens.button
                    val iconScale = LocalConfiguration.current.fontScale

                    // Error icon
                    if (hasError) {
                        Icon(
                            modifier = Modifier.size(buttonTokens.sizeIconOnly.value * iconScale),
                            painter = painterResource(id = OudsTheme.drawableResources.component.alert.importantFill),
                            contentDescription = if (error.message.isBlank()) stringResource(R.string.core_common_error_a11y) else null,
                            tint = errorIconColor(state = state)
                        )
                    }

                    // Loader
                    if (state == OudsTextInputState.Loading) {
                        val progress = if (getPreviewEnumEntry<OudsTextInputState>() == OudsTextInputState.Loading) 0.75f else loader?.progress
                        OudsCircularProgressIndicator(
                            color = OudsTheme.componentsTokens.button.colorContentMinimalLoading.value,
                            progress = progress,
                            scale = iconScale
                        )
                    }
                }
            }

            // Helper text / Error description
            OudsTextInputHelperTextErrorMessage(
                modifier = Modifier.padding(horizontal = spacePaddingInlineDefault.value),
                enabled = state != OudsTextInputState.Disabled,
                error = error,
                helperText = helperText
            )

            // Helper link
            OptionalHelperLink(state = state, helperLink = helperLink)
        }
    }
}

@Composable
private fun Modifier.verticalScrollBar(scrollState: ScrollState): Modifier {
    val scrollBarColor = OudsTheme.colorScheme.action.disabled
    val scrollBarWidth = 4.dp

    return if (scrollState.maxValue > 0) {
        drawWithContent {
            drawContent()

            val viewportHeight = this.size.height
            val viewportWidth = this.size.width
            val totalContentHeight = scrollState.maxValue.toFloat() + viewportHeight
            if (totalContentHeight > viewportHeight) {
                val scrollBarHeight = (viewportHeight / totalContentHeight) * viewportHeight
                val scrollBarStartOffset = (scrollState.value.toFloat() / totalContentHeight) * viewportHeight

                drawRoundRect(
                    color = scrollBarColor,
                    cornerRadius = CornerRadius(10f, 10f),
                    topLeft = Offset(viewportWidth - scrollBarWidth.toPx(), scrollBarStartOffset),
                    size = Size(scrollBarWidth.toPx(), scrollBarHeight)
                )
            }
        }
    } else {
        this
    }
}

@Preview(name = "Light", heightDp = OudsPreviewableComponent.TextArea.Default.PreviewHeightDp, device = OudsPreviewDevice)
@Preview(
    name = "Dark",
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL,
    heightDp = OudsPreviewableComponent.TextArea.Default.PreviewHeightDp,
    device = OudsPreviewDevice
)
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsTextArea(@PreviewParameter(OudsTextAreaPreviewParameterProvider::class) parameter: OudsTextAreaPreviewParameter) {
    PreviewOudsTextArea(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsTextArea(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsTextAreaPreviewParameter
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        PreviewEnumEntries<OudsTextInputState>(columnCount = 1) { _ ->
            OudsTextArea(
                textFieldState = rememberTextFieldState(value),
                label = label,
                placeholder = placeholder,
                error = error,
                helperText = helperText,
                helperLink = helperLink
            )
        }
    }
}

@Preview(heightDp = OudsPreviewableComponent.TextArea.WithRoundedCorners.PreviewHeightDp, device = OudsPreviewDevice)
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsTextAreaWithRoundedCorners() = PreviewOudsTextAreaWithRoundedCorners(theme = getPreviewTheme())

@Composable
internal fun PreviewOudsTextAreaWithRoundedCorners(theme: OudsThemeContract) =
    OudsPreview(theme = theme.mapSettings { it.copy(roundedCornerTextInputs = true) }) {
        PreviewEnumEntries<OudsTextInputState>(columnCount = 1) { _ ->
            OudsTextArea(
                textFieldState = rememberTextFieldState(""),
                label = "Label",
                placeholder = "Placeholder"
            )
        }
    }

@Preview(widthDp = OudsPreviewableComponent.TextArea.ConstrainedMaxWidth.PreviewWidthDp, device = OudsPreviewDevice)
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
internal fun PreviewOudsTextAreaConstrainedMaxWidth(@PreviewParameter(OudsTextAreaConstrainedMaxWidthPreviewParameterProvider::class) constrainedMaxWidth: Boolean) {
    PreviewOudsTextAreaConstrainedMaxWidth(theme = getPreviewTheme(), constrainedMaxWidth = constrainedMaxWidth)
}

@Composable
internal fun PreviewOudsTextAreaConstrainedMaxWidth(theme: OudsThemeContract, constrainedMaxWidth: Boolean) = OudsPreview(theme = theme) {
    OudsTextArea(
        modifier = Modifier.padding(all = 10.dp),
        textFieldState = rememberTextFieldState(),
        label = "Label",
        placeholder = "Placeholder",
        constrainedMaxWidth = constrainedMaxWidth
    )
}

@OudsPreview
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsTextAreaMultiLineValue() = PreviewOudsTextAreaMultiLineValue(theme = getPreviewTheme())

@Composable
internal fun PreviewOudsTextAreaMultiLineValue(theme: OudsThemeContract) = OudsPreview(theme = theme) {
    Column {
        MultiLineValueTextArea(3)
        MultiLineValueTextArea(5)
        MultiLineValueTextArea(12)
    }
}

@Composable
private fun MultiLineValueTextArea(lineCount: Int) {
    OudsTextArea(
        modifier = Modifier.padding(all = 10.dp),
        textFieldState = rememberTextFieldState(List(lineCount) { "Line ${it + 1}" }.joinToString("\n")),
        label = "$lineCount lines",
        loader = OudsTextInputLoader(progress = 0.75f)
    )
}

internal data class OudsTextAreaPreviewParameter(
    val value: String,
    val label: String? = null,
    val placeholder: String? = null,
    val error: OudsError? = null,
    val helperText: String? = null,
    val helperLink: OudsTextInputHelperLink? = null
)

internal class OudsTextAreaPreviewParameterProvider : BasicPreviewParameterProvider<OudsTextAreaPreviewParameter>(*previewParameterValues.toTypedArray())

internal class OudsTextAreaConstrainedMaxWidthPreviewParameterProvider : BasicPreviewParameterProvider<Boolean>(false, true)

private val previewParameterValues: List<OudsTextAreaPreviewParameter>
    get() {
        val label = "Label"
        val placeholder = "Placeholder"
        val error = OudsError("Error message.")
        val helperText = "Helper text."
        return listOf(
            OudsTextAreaPreviewParameter("", label = label),
            OudsTextAreaPreviewParameter(
                "",
                label = label,
                placeholder = placeholder,
                error = error
            ),
            OudsTextAreaPreviewParameter(
                "Text content",
                label = label,
                placeholder = placeholder,
                helperText = helperText,
                helperLink = OudsTextInputHelperLink("Helper link") {}
            ),
            OudsTextAreaPreviewParameter("Error text", label = label, error = error, helperText = helperText)
        )
    }
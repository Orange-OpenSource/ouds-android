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
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.widthIn
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.component.content.OudsComponentContent
import com.orange.ouds.core.component.content.OudsComponentIcon
import com.orange.ouds.core.extensions.InteractionState
import com.orange.ouds.core.extensions.collectInteractionStateAsState
import com.orange.ouds.core.theme.LocalThemeSettings
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.CheckedContent
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.PreviewEnumEntries
import com.orange.ouds.core.utilities.getPreviewEnumEntry
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.theme.OudsThemeSettings

// TODO: Update documentation URL once it is available
/**
 * A Text Input is a user interface component that allows users to enter, edit, or select single-line textual data. It's one of the most fundamental
 * form elements used to capture user input such as names, emails, passwords, or search queries.
 *
 * It provides a visual and interactive affordance for text entry while supporting labels, placeholders, icons, helper messages, and validation feedback.
 *
 * Rounded corners can be enabled or disabled using [OudsThemeSettings.roundedCornerTextInputs] property in the settings of the theme provided when calling
 * the [com.orange.ouds.core.theme.OudsTheme] method.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://unified-design-system.orange.com)
 *
 * > Design version: 1.1.0
 *
 * @param textFieldState The editable text state of the text input, including both the text itself and position of the cursor or selection.
 * @param modifier [Modifier] applied to the text input.
 * @param label Label displayed above the text input. It describe the purpose of the input.
 * @param placeholder Text displayed when the text input is empty. It provides a hint or guidance inside the field to suggest expected input.
 * @param leadingIcon An optional leading icon displayed at the start of the text input. It helps indicate the purpose of the input (magnifying glass for search,
 *   envelope for email, etc.). Only use a leading icon if it adds clear functional or contextual value.
 * @param trailingIconButton An optional trailing icon button displayed at the end of the text input. It is used to provide actions related to the field:
 *   clear input, toggle password visibility, etc. It can also indicate status or feedback (error checkmark, loading spinner).
 * @param prefix Text placed before the user's input. Commonly used to indicate expected formatting like a country code, a unit...
 * @param suffix Text placed after the user's input, often used to display a currency or a unit (kg, %, cm…).
 * @param enabled Controls the enabled state of the text input. When `false`, this text input will not be focusable and will not react to input events.
 *   True by default.
 * @param readOnly Controls the read-only state of the text input. When `true`, the text is visible but not editable.
 *   False by default.
 * @param loader An optional loading progress indicator displayed in the text input to indicate an ongoing operation.
 * @param outlined Controls the style of the text input. When `true`, it displays a minimalist text input with a transparent background and a visible
 *   stroke outlining the field.
 * @param error Controls the error state of the text input. When `true`, the text input will be displayed in an error state to indicates that the user input
 *   does not meet validation rules or expected formatting.
 *   False by default.
 * @param helperText An optional helper text displayed below the text input. It conveys additional information about the input field, such as how it will be
 *   used. It should ideally only take up a single line, though may wrap to multiple lines if required, and be either persistently visible or visible only on focus.
 * @param helperLink An optional helper link displayed below or in place of the helper text.
 * @param keyboardOptions Software keyboard options that contain configurations such as [KeyboardType] and [ImeAction].
 * @param onKeyboardAction Called when the user presses the action button in the input method editor (IME), or by pressing the enter key on a hardware keyboard.
 *   By default this parameter is null, and would execute the default behavior for a received IME Action e.g., [ImeAction.Done] would close the keyboard,
 *   [ImeAction.Next] would switch the focus to the next focusable item on the screen.
 * @param onTextLayout Callback that is executed when the text layout becomes queryable. The callback receives a function that returns a [TextLayoutResult] if
 *   the layout can be calculated, or null if it cannot. The function reads the layout result from a snapshot state object, and will invalidate its caller when
 *   the layout result changes. A [TextLayoutResult] object contains paragraph information, size of the text, baselines and other details. The callback can be
 *   used to add additional decoration or functionality to the text. For example, to draw a cursor or selection around the text. [Density] scope is the one that
 *   was used while creating the given text layout.
 * @param inputTransformation An optional [InputTransformation] that will be used to transform changes to the [TextFieldState] made by the user. The transformation
 *   will be applied to changes made by hardware and software keyboard events, pasting or dropping text, accessibility services, and tests. The transformation
 *   will _not_ be applied when changing the [textFieldState] programmatically, or when the transformation is changed. If the transformation is changed on an
 *   existing text field, it will be applied to the next user edit. the transformation will not immediately affect the current [textFieldState].
 * @param outputTransformation An optional [OutputTransformation] that transforms how the contents of the text field are presented.
 * @param interactionSource An optional hoisted [MutableInteractionSource] for observing and emitting [Interaction]s for this text input. Note that if `null`
 *   is provided, interactions will still happen internally.
 */
@Composable
fun OudsTextInput(
    textFieldState: TextFieldState,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    leadingIcon: OudsTextInput.LeadingIcon? = null,
    trailingIconButton: OudsTextInput.TrailingIconButton? = null,
    prefix: String? = null,
    suffix: String? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    loader: OudsTextInput.Loader? = null,
    outlined: Boolean = false,
    error: Boolean = false,
    helperText: String? = null,
    helperLink: OudsTextInput.HelperLink? = null,
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

    OudsTextInput(
        state = state,
        emptyText = emptyText,
        readOnly = readOnly,
        error = error,
        basicTextField = {
            BasicTextField(
                modifier = modifier.fillMaxWidth(),
                state = textFieldState,
                enabled = textFieldEnabled(state = state),
                readOnly = readOnly,
                textStyle = textFieldTextStyle(),
                lineLimits = TextFieldLineLimits.SingleLine,
                cursorBrush = cursorBrush(state = state, error = error),
                keyboardOptions = keyboardOptions,
                onKeyboardAction = onKeyboardAction,
                onTextLayout = onTextLayout,
                inputTransformation = inputTransformation,
                outputTransformation = outputTransformation,
                interactionSource = interactionSource,
                decorator = { innerTextField ->
                    OudsTextInputDecorator(
                        innerTextField = innerTextField,
                        emptyText = emptyText,
                        state = state,
                        label = label,
                        placeholder = placeholder,
                        leadingIcon = leadingIcon,
                        trailingIconButton = trailingIconButton,
                        prefix = prefix,
                        suffix = suffix,
                        loader = loader,
                        outlined = outlined,
                        error = error,
                        helperText = helperText,
                        helperLink = helperLink
                    )
                }
            )
        }
    )
}

// TODO: Update documentation URL once it is available
/**
 * A Text Input is a user interface component that allows users to enter, edit, or select single-line textual data. It's one of the most fundamental
 * form elements used to capture user input such as names, emails, passwords, or search queries.
 *
 * It provides a visual and interactive affordance for text entry while supporting labels, placeholders, icons, helper messages, and validation feedback.
 *
 * Rounded corners can be enabled or disabled using [OudsThemeSettings.roundedCornerTextInputs] property in the settings of the theme provided when calling
 * the [com.orange.ouds.core.theme.OudsTheme] method.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://unified-design-system.orange.com)
 *
 * > Design version: 1.1.0
 *
 * @param value Input text to be shown in the text field.
 * @param onValueChange Callback that is triggered when the input service updates the text. An updated text comes as a parameter of the callback.
 * @param modifier [Modifier] applied to the text input.
 * @param label Label displayed above the text input. It describe the purpose of the input.
 * @param placeholder Text displayed when the text input is empty. It provides a hint or guidance inside the field to suggest expected input.
 * @param leadingIcon An optional leading icon displayed at the start of the text input. It helps indicate the purpose of the input (magnifying glass for search,
 *   envelope for email, etc.). Only use a leading icon if it adds clear functional or contextual value.
 * @param trailingIconButton An optional trailing icon button displayed at the end of the text input. It is used to provide actions related to the field:
 *   clear input, toggle password visibility, etc. It can also indicate status or feedback (error checkmark, loading spinner).
 * @param prefix Text placed before the user's input. Commonly used to indicate expected formatting like a country code, a unit...
 * @param suffix Text placed after the user's input, often used to display a currency or a unit (kg, %, cm…).
 * @param enabled Controls the enabled state of the text input. When `false`, this text input will not be focusable and will not react to input events.
 *   True by default.
 * @param readOnly Controls the read-only state of the text input. When `true`, the text is visible but not editable.
 *   False by default.
 * @param loader An optional loading progress indicator displayed in the text input to indicate an ongoing operation.
 * @param outlined Controls the style of the text input. When `true`, it displays a minimalist text input with a transparent background and a visible
 *   stroke outlining the field.
 * @param error Controls the error state of the text input. When `true`, the text input will be displayed in an error state to indicates that the user input
 *   does not meet validation rules or expected formatting.
 *   False by default.
 * @param helperText An optional helper text displayed below the text input. It conveys additional information about the input field, such as how it will be
 *   used. It should ideally only take up a single line, though may wrap to multiple lines if required, and be either persistently visible or visible only on focus.
 * @param helperLink An optional helper link displayed below or in place of the helper text.
 * @param keyboardOptions software keyboard options that contains configuration such as [KeyboardType] and [ImeAction].
 * @param keyboardActions when the input service emits an IME action, the corresponding callback is called. Note that this IME action may be different from what
 *   you specified in [KeyboardOptions.imeAction].
 * @param onTextLayout Callback that is executed when a new text layout is calculated. A [TextLayoutResult] object that callback provides contains paragraph
 *   information, size of the text, baselines and other details. The callback can be used to add additional decoration or functionality to the text.
 *   For example, to draw a cursor or selection around the text.
 * @param visualTransformation The visual transformation filter for changing the visual representation of the input. By default no visual transformation is applied.
 * @param interactionSource An optional hoisted [MutableInteractionSource] for observing and emitting [Interaction]s for this text input. Note that if `null`
 *   is provided, interactions will still happen internally.
 */
@Composable
fun OudsTextInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    leadingIcon: OudsTextInput.LeadingIcon? = null,
    trailingIconButton: OudsTextInput.TrailingIconButton? = null,
    prefix: String? = null,
    suffix: String? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    loader: OudsTextInput.Loader? = null,
    outlined: Boolean = false,
    error: Boolean = false,
    helperText: String? = null,
    helperLink: OudsTextInput.HelperLink? = null,
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

    OudsTextInput(
        state = state,
        emptyText = emptyText,
        readOnly = readOnly,
        error = error,
        basicTextField = {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = modifier.fillMaxWidth(),
                enabled = textFieldEnabled(state = state),
                readOnly = readOnly,
                textStyle = textFieldTextStyle(),
                singleLine = true,
                cursorBrush = cursorBrush(state = state, error = error),
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                onTextLayout = onTextLayout,
                visualTransformation = visualTransformation,
                interactionSource = interactionSource,
                decorationBox = { innerTextField ->
                    OudsTextInputDecorator(
                        innerTextField = innerTextField,
                        emptyText = emptyText,
                        state = state,
                        label = label,
                        placeholder = placeholder,
                        leadingIcon = leadingIcon,
                        trailingIconButton = trailingIconButton,
                        prefix = prefix,
                        suffix = suffix,
                        loader = loader,
                        outlined = outlined,
                        error = error,
                        helperText = helperText,
                        helperLink = helperLink
                    )

                }
            )
        }
    )
}

// TODO: Update documentation URL once it is available
/**
 * A Text Input is a user interface component that allows users to enter, edit, or select single-line textual data. It's one of the most fundamental
 * form elements used to capture user input such as names, emails, passwords, or search queries.
 *
 * It provides a visual and interactive affordance for text entry while supporting labels, placeholders, icons, helper messages, and validation feedback.
 *
 * Rounded corners can be enabled or disabled using [OudsThemeSettings.roundedCornerTextInputs] property in the settings of the theme provided when calling
 * the [com.orange.ouds.core.theme.OudsTheme] method.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://unified-design-system.orange.com)
 *
 * > Design version: 1.1.0
 *
 * @param value The [androidx.compose.ui.text.input.TextFieldValue] to be shown in the text input.
 * @param onValueChange Called when the input service updates the values in [TextFieldValue].
 * @param modifier [Modifier] applied to the text input.
 * @param label Label displayed above the text input. It describe the purpose of the input.
 * @param placeholder Text displayed when the text input is empty. It provides a hint or guidance inside the field to suggest expected input.
 * @param leadingIcon An optional leading icon displayed at the start of the text input. It helps indicate the purpose of the input (magnifying glass for search,
 *   envelope for email, etc.). Only use a leading icon if it adds clear functional or contextual value.
 * @param trailingIconButton An optional trailing icon button displayed at the end of the text input. It is used to provide actions related to the field:
 *   clear input, toggle password visibility, etc. It can also indicate status or feedback (error checkmark, loading spinner).
 * @param prefix Text placed before the user's input. Commonly used to indicate expected formatting like a country code, a unit...
 * @param suffix Text placed after the user's input, often used to display a currency or a unit (kg, %, cm…).
 * @param enabled Controls the enabled state of the text input. When `false`, this text input will not be focusable and will not react to input events.
 *   True by default.
 * @param readOnly Controls the read-only state of the text input. When `true`, the text is visible but not editable.
 *   False by default.
 * @param loader An optional loading progress indicator displayed in the text input to indicate an ongoing operation.
 * @param outlined Controls the style of the text input. When `true`, it displays a minimalist text input with a transparent background and a visible
 *   stroke outlining the field.
 * @param error Controls the error state of the text input. When `true`, the text input will be displayed in an error state to indicates that the user input
 *   does not meet validation rules or expected formatting.
 *   False by default.
 * @param helperText An optional helper text displayed below the text input. It conveys additional information about the input field, such as how it will be
 * used. It should ideally only take up a single line, though may wrap to multiple lines if required, and be either persistently visible or visible only on focus.
 * @param helperLink An optional helper link displayed below or in place of the helper text.
 * @param keyboardOptions software keyboard options that contains configuration such as [KeyboardType] and [ImeAction].
 * @param keyboardActions when the input service emits an IME action, the corresponding callback is called. Note that this IME action may be different from what
 *   you specified in [KeyboardOptions.imeAction].
 * @param onTextLayout Callback that is executed when a new text layout is calculated. A [TextLayoutResult] object that callback provides contains paragraph
 *   information, size of the text, baselines and other details. The callback can be used to add additional decoration or functionality to the text.
 *   For example, to draw a cursor or selection around the text.
 * @param visualTransformation The visual transformation filter for changing the visual representation of the input. By default no visual transformation is applied.
 * @param interactionSource An optional hoisted [MutableInteractionSource] for observing and emitting [Interaction]s for this text input. Note that if `null`
 *   is provided, interactions will still happen internally.
 */
@Composable
fun OudsTextInput(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    leadingIcon: OudsTextInput.LeadingIcon? = null,
    trailingIconButton: OudsTextInput.TrailingIconButton? = null,
    prefix: String? = null,
    suffix: String? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    loader: OudsTextInput.Loader? = null,
    outlined: Boolean = false,
    error: Boolean = false,
    helperText: String? = null,
    helperLink: OudsTextInput.HelperLink? = null,
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

    OudsTextInput(
        state = state,
        emptyText = emptyText,
        readOnly = readOnly,
        error = error,
        basicTextField = {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = modifier.fillMaxWidth(),
                enabled = textFieldEnabled(state = state),
                readOnly = readOnly,
                textStyle = textFieldTextStyle(),
                singleLine = true,
                cursorBrush = cursorBrush(state = state, error = error),
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                onTextLayout = onTextLayout,
                visualTransformation = visualTransformation,
                interactionSource = interactionSource,
                decorationBox = { innerTextField ->
                    OudsTextInputDecorator(
                        innerTextField = innerTextField,
                        emptyText = emptyText,
                        state = state,
                        label = label,
                        placeholder = placeholder,
                        leadingIcon = leadingIcon,
                        trailingIconButton = trailingIconButton,
                        prefix = prefix,
                        suffix = suffix,
                        loader = loader,
                        outlined = outlined,
                        error = error,
                        helperText = helperText,
                        helperLink = helperLink
                    )

                }
            )
        }
    )
}

@Composable
internal fun OudsTextInput(
    state: OudsTextInput.State,
    emptyText: Boolean,
    readOnly: Boolean,
    error: Boolean,
    basicTextField: @Composable () -> Unit
) {

    val isForbidden = (state == OudsTextInput.State.Loading && (emptyText || error)) || (error && state in listOf(
        OudsTextInput.State.ReadOnly,
        OudsTextInput.State.Disabled
    ))

    CheckedContent(
        expression = !isForbidden,
        exceptionMessage = {
            if (state == OudsTextInput.State.Loading) {
                "An OudsTextInput with error parameter activated or an empty value cannot have a loader."
            } else {
                val parameter = if (readOnly) "readOnly" else "disabled"
                "An OudsTextInput set to $parameter with error parameter activated is not allowed."
            }
        },
        previewMessage = {
            if (state == OudsTextInput.State.Loading) {
                val statusDescription = if (error) "Error" else "Empty"
                "$statusDescription status for Loading state is not relevant"
            } else {
                val stateDescription = if (readOnly) "Read only" else "Disabled"
                "Error status for $stateDescription state is not relevant"
            }
        }
    ) {
        basicTextField()
    }
}

@Composable
private fun OudsTextInputDecorator(
    innerTextField: @Composable () -> Unit,
    emptyText: Boolean,
    state: OudsTextInput.State,
    label: String?,
    placeholder: String?,
    leadingIcon: OudsTextInput.LeadingIcon?,
    trailingIconButton: OudsTextInput.TrailingIconButton?,
    prefix: String?,
    suffix: String?,
    loader: OudsTextInput.Loader?,
    outlined: Boolean,
    error: Boolean,
    helperText: String?,
    helperLink: OudsTextInput.HelperLink?,
) {
    with(OudsTheme.componentsTokens.textInput) {
        val borderRadius = if (LocalThemeSettings.current.roundedCornerTextInputs == true) borderRadiusRounded.value else borderRadiusDefault.value
        val shape = RoundedCornerShape(borderRadius)

        val styleModifier = if ((outlined && state != OudsTextInput.State.ReadOnly) || (!outlined && state == OudsTextInput.State.ReadOnly)) {
            // outlined
            Modifier.border(
                width = borderWidth(state),
                color = borderColor(state = state, outlined = outlined, error = error),
                shape = shape
            )
        } else {
            // filled
            Modifier
                .bottomBorder(state = state, outlined = outlined, cornerRadius = borderRadius, error = error)
                .background(
                    color = backgroundColor(state = state, outlined = outlined, error = error),
                    shape = shape
                )
        }

        Column {
            Row(
                modifier = styleModifier
                    .sizeIn(minHeight = sizeMinHeight.dp)
                    .padding(vertical = spacePaddingBlockDefault.value)
                    .padding(
                        start = spacePaddingInlineDefault.value,
                        end = if (trailingIconButton != null || state == OudsTextInput.State.Loading) spacePaddingInlineTrailingAction.value else spacePaddingInlineDefault.value
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(spaceColumnGapDefault.value)
            ) {
                // Leading icon
                leadingIcon?.Content(modifier = Modifier.size(OudsTheme.componentsTokens.button.sizeIconOnly.value))

                // Central content
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {
                    // Small label on top
                    if (label != null && (!emptyText || !placeholder.isNullOrEmpty() || state == OudsTextInput.State.Focused)) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = label,
                            style = OudsTheme.typography.label.default.small,
                            color = labelColor(state = state, error = error)
                        )
                    }

                    // Prefix + Placeholder Label Value + Suffix
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(spaceColumnGapInlineText.value)
                    ) {
                        if (prefix != null) PrefixSuffixText(text = prefix)
                        Box(modifier = Modifier.weight(1f)) {
                            if (emptyText) {
                                if (!placeholder.isNullOrEmpty()) {
                                    Text(
                                        text = placeholder,
                                        style = OudsTheme.typography.label.default.large,
                                        color = placeholderColor(state = state),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                } else if (!label.isNullOrEmpty() && state != OudsTextInput.State.Focused) {
                                    Text(
                                        text = label,
                                        style = OudsTheme.typography.label.default.large,
                                        color = labelColor(state = state, error = error)
                                    )
                                }
                            }
                            innerTextField()
                        }
                        if (suffix != null) PrefixSuffixText(text = suffix)
                    }
                }

                // Trailing elements
                if (error || state == OudsTextInput.State.Loading || trailingIconButton != null) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(spaceColumnGapTrailingErrorAction.value)
                    ) {
                        // Error icon
                        if (error) {
                            Box(
                                modifier = Modifier.size(OudsTheme.componentsTokens.button.sizeIconOnly.value),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = OudsTheme.drawableResources.important),
                                    contentDescription = null,
                                    tint = errorContentColor(state = state)
                                )
                            }
                        }

                        // Loader
                        if (state == OudsTextInput.State.Loading) {
                            val progress = if (getPreviewEnumEntry<OudsTextInput.State>() == OudsTextInput.State.Loading) 0.75f else loader?.progress
                            val buttonTokens = OudsTheme.componentsTokens.button
                            Box(
                                modifier = Modifier
                                    .widthIn(min = buttonTokens.sizeMinWidth.value)
                                    .heightIn(min = buttonTokens.sizeMinHeight.value),
                                contentAlignment = Alignment.Center
                            ) {
                                OudsCircularProgressIndicator(
                                    color = OudsTheme.componentsTokens.button.colorContentMinimalLoading.value,
                                    progress = progress
                                )
                            }
                        } else {
                            trailingIconButton?.Content(extraParameters = OudsTextInput.TrailingIconButton.ExtraParameters(enabled = state != OudsTextInput.State.Disabled))
                        }
                    }
                }
            }

            // Helper text
            helperText?.let { text ->
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = spacePaddingBlockTopHelperText.value)
                        .padding(horizontal = spacePaddingInlineDefault.value),
                    text = text,
                    style = OudsTheme.typography.label.default.medium,
                    color = if (error) OudsTheme.colorScheme.content.status.negative else OudsTheme.colorScheme.content.muted
                )
            }

            // Helper link
            helperLink?.let { link ->
                OudsLink(
                    modifier = Modifier.padding(start = spacePaddingInlineDefault.value),
                    label = link.text,
                    onClick = link.onClick,
                    size = OudsLink.Size.Small
                )
            }
        }
    }
}

@Composable
private fun getTextInputState(enabled: Boolean, readOnly: Boolean, loader: OudsTextInput.Loader?, interactionState: InteractionState): OudsTextInput.State {
    return getPreviewEnumEntry<OudsTextInput.State>().orElse {
        if (loader != null) {
            OudsTextInput.State.Loading
        } else {
            when {
                !enabled -> OudsTextInput.State.Disabled
                readOnly -> OudsTextInput.State.ReadOnly
                interactionState == InteractionState.Hovered -> OudsTextInput.State.Hovered
                interactionState == InteractionState.Focused -> OudsTextInput.State.Focused
                else -> OudsTextInput.State.Enabled
            }
        }
    }
}

@Composable
private fun borderWidth(state: OudsTextInput.State) = with(OudsTheme.componentsTokens.textInput) {
    if (state == OudsTextInput.State.Focused) borderWidthFocus.value else borderWidthDefault.value
}

@Composable
private fun PrefixSuffixText(text: String) {
    Text(text = text, style = OudsTheme.typography.label.default.large, color = OudsTheme.colorScheme.content.muted)
}

@Composable
private fun backgroundColor(state: OudsTextInput.State, outlined: Boolean, error: Boolean): Color {
    return if (error) {
        OudsTheme.colorScheme.surface.status.negative.muted
    } else {
        when (state) {
            OudsTextInput.State.Enabled -> OudsTheme.colorScheme.action.support.enabled
            OudsTextInput.State.Hovered -> OudsTheme.colorScheme.action.support.hover
            OudsTextInput.State.Focused -> OudsTheme.colorScheme.action.support.pressed
            OudsTextInput.State.Disabled -> OudsTheme.colorScheme.action.support.disabled
            OudsTextInput.State.ReadOnly -> if (outlined) OudsTheme.colorScheme.action.support.disabled else Color.Transparent
            OudsTextInput.State.Loading -> OudsTheme.colorScheme.action.support.loading
        }
    }
}

@Composable
private fun cursorBrush(state: OudsTextInput.State, error: Boolean) =
    SolidColor(if (error) errorContentColor(state = state) else OudsTheme.colorScheme.content.default)

@Composable
private fun errorContentColor(state: OudsTextInput.State) = when (state) {
    OudsTextInput.State.Enabled -> OudsTheme.colorScheme.action.negative.enabled
    OudsTextInput.State.Hovered -> OudsTheme.colorScheme.action.negative.hover
    OudsTextInput.State.Focused -> OudsTheme.colorScheme.action.negative.pressed
    OudsTextInput.State.Disabled, OudsTextInput.State.ReadOnly, OudsTextInput.State.Loading -> Color.Unspecified // Not relevant, exception thrown at the beginning of OudsTextInput
}

/**
 * Draws a bottom border on the text input by respecting [cornerRadius] provided.
 * Color and thickness of the border are provided by [state].
 */
@Composable
private fun Modifier.bottomBorder(state: OudsTextInput.State, outlined: Boolean, cornerRadius: Dp, error: Boolean): Modifier {
    val thickness = borderWidth(state)
    val color = borderColor(state = state, outlined = outlined, error = error)

    return drawWithContent {
        drawContent()
        if (cornerRadius > 0.dp) {
            val cornerRadiusPx = cornerRadius.toPx()
            val path = Path().apply {
                arcTo(
                    rect = Rect(
                        top = size.height - 2 * cornerRadiusPx,
                        left = 0f,
                        bottom = size.height,
                        right = 2 * cornerRadiusPx
                    ),
                    startAngleDegrees = 180f,
                    sweepAngleDegrees = -90f,
                    forceMoveTo = false
                )

                arcTo(
                    rect = Rect(
                        top = size.height - 2 * cornerRadiusPx,
                        left = size.width - 2 * cornerRadiusPx,
                        bottom = size.height,
                        right = size.width,
                    ),
                    startAngleDegrees = 90f,
                    sweepAngleDegrees = -90f,
                    forceMoveTo = false
                )

                arcTo(
                    rect = Rect(
                        top = size.height - 2 * cornerRadiusPx,
                        left = size.width - 2 * cornerRadiusPx,
                        bottom = size.height - thickness.toPx(),
                        right = size.width,
                    ),
                    startAngleDegrees = 0f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false
                )

                arcTo(
                    rect = Rect(
                        top = size.height - 2 * cornerRadiusPx,
                        left = 0f,
                        right = 2 * cornerRadiusPx,
                        bottom = size.height - thickness.toPx()
                    ),
                    startAngleDegrees = 90f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false
                )

                close()
            }
            drawPath(path, color = color)
        } else {
            drawLine(
                color = color,
                start = Offset(0f, size.height),
                end = Offset(size.width, size.height),
                strokeWidth = thickness.toPx()
            )
        }
    }
}

@Composable
private fun borderColor(state: OudsTextInput.State, outlined: Boolean, error: Boolean): Color {
    return if (outlined) {
        if (error) {
            errorContentColor(state = state)
        } else {
            with(OudsTheme.componentsTokens.textInput) {
                when (state) {
                    OudsTextInput.State.Enabled -> colorBorderEnabled.value
                    OudsTextInput.State.Hovered -> colorBorderHover.value
                    OudsTextInput.State.Focused -> colorBorderFocus.value
                    OudsTextInput.State.Disabled -> OudsTheme.colorScheme.action.disabled
                    OudsTextInput.State.Loading -> colorBorderLoading.value
                    OudsTextInput.State.ReadOnly -> Color.Unspecified
                }
            }
        }
    } else {
        labelColor(state = state, error = error)
    }
}

@Composable
private fun labelColor(state: OudsTextInput.State, error: Boolean): Color {
    return if (error) {
        errorContentColor(state = state)
    } else {
        if (state == OudsTextInput.State.Disabled) {
            OudsTheme.colorScheme.action.disabled
        } else {
            OudsTheme.colorScheme.content.muted
        }
    }
}

@Composable
private fun placeholderColor(state: OudsTextInput.State) =
    if (state == OudsTextInput.State.Disabled) OudsTheme.colorScheme.action.disabled else OudsTheme.colorScheme.content.muted

@Composable
private fun textFieldTextStyle() = OudsTheme.typography.label.default.large.copy(color = OudsTheme.colorScheme.content.default)

@Composable
private fun textFieldEnabled(state: OudsTextInput.State) = state != OudsTextInput.State.Disabled && state != OudsTextInput.State.Loading

/**
 * Contains classes to build an [OudsTextInput].
 */
object OudsTextInput {

    internal enum class State {
        Enabled, Hovered, Disabled, Focused, ReadOnly, Loading
    }

    /**
     * An helper link displayed below or in place of the helper text.
     */
    data class HelperLink(val text: String, val onClick: () -> Unit)

    /**
     * A circular loading indicator displayed in the text input.
     *
     * @param progress The loading progress, where 0.0 represents no progress and 1.0 represents full progress.
     *   Values outside of this range are coerced into the range.
     *   Set this value to `null` to display a circular indeterminate progress indicator.
     */
    data class Loader(val progress: Float?)

    /**
     * A leading icon in an [OudsTextInput].
     * This icon is non-clickable.
     */
    class LeadingIcon private constructor(
        graphicsObject: Any,
        val contentDescription: String
    ) : OudsComponentIcon<Nothing, LeadingIcon>(Nothing::class.java, graphicsObject, contentDescription) {

        /**
         * Creates an instance of [OudsTextInput.LeadingIcon].
         *
         * @param painter Painter of the icon.
         * @param contentDescription The content description associated with this [OudsTextInput.LeadingIcon].
         */
        constructor(painter: Painter, contentDescription: String) : this(painter as Any, contentDescription)

        /**
         * Creates an instance of [OudsTextInput.LeadingIcon].
         *
         * @param imageVector Image vector of the icon.
         * @param contentDescription The content description associated with this [OudsTextInput.LeadingIcon].
         */
        constructor(imageVector: ImageVector, contentDescription: String) : this(imageVector as Any, contentDescription)

        /**
         * Creates an instance of [OudsTextInput.LeadingIcon].
         *
         * @param bitmap Image bitmap of the icon.
         * @param contentDescription The content description associated with this [OudsTextInput.LeadingIcon].
         */
        constructor(bitmap: ImageBitmap, contentDescription: String) : this(bitmap as Any, contentDescription)

        override val tint: Color?
            @Composable
            get() = OudsTheme.colorScheme.content.default
    }

    /**
     * A trailing icon button in an [OudsTextInput].
     * Displays an icon-only [OudsButton].
     */
    class TrailingIconButton private constructor(
        graphicsObject: Any,
        val contentDescription: String,
        val onClick: () -> Unit
    ) : OudsComponentIcon<TrailingIconButton.ExtraParameters, TrailingIconButton>(ExtraParameters::class.java, graphicsObject, contentDescription, onClick) {

        @ConsistentCopyVisibility
        data class ExtraParameters internal constructor(
            internal val enabled: Boolean
        ) : OudsComponentContent.ExtraParameters()

        /**
         * Creates an instance of [OudsTextInput.TrailingIconButton].
         *
         * @param painter Painter of the icon.
         * @param contentDescription The content description associated to this [OudsTextInput.TrailingIconButton].
         * @param onClick Callback invoked when the button is clicked.
         */
        constructor(
            painter: Painter,
            contentDescription: String,
            onClick: () -> Unit
        ) : this(painter as Any, contentDescription, onClick)

        /**
         * Creates an instance of [OudsTextInput.TrailingIconButton].
         *
         * @param imageVector Image vector of the icon.
         * @param contentDescription The content description associated to this [OudsTextInput.TrailingIconButton].
         * @param onClick Callback invoked when the button is clicked.
         */
        constructor(
            imageVector: ImageVector,
            contentDescription: String,
            onClick: () -> Unit
        ) : this(imageVector as Any, contentDescription, onClick)

        /**
         * Creates an instance of [OudsTextInput.TrailingIconButton].
         *
         * @param bitmap Image bitmap of the icon.
         * @param contentDescription The content description associated to this [OudsTextInput.TrailingIconButton].
         * @param onClick Callback invoked when the button is clicked.
         */
        constructor(
            bitmap: ImageBitmap,
            contentDescription: String,
            onClick: () -> Unit
        ) : this(bitmap as Any, contentDescription, onClick)

        override val enabled: Boolean?
            @Composable
            get() = extraParameters.enabled
    }
}

internal object OudsTextInputPreview {
    const val heightDp = 1100
}

@Preview(name = "Light", heightDp = OudsTextInputPreview.heightDp)
@Preview(name = "Dark", uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL, heightDp = OudsTextInputPreview.heightDp)
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsTextInput(@PreviewParameter(OudsTextInputPreviewParameterProvider::class) parameter: OudsTextInputPreviewParameter) {
    PreviewOudsTextInput(darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsTextInput(darkThemeEnabled: Boolean, parameter: OudsTextInputPreviewParameter) = OudsPreview(darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        PreviewEnumEntries<OudsTextInput.State>(columnCount = 1) { state ->
            OudsTextInput(
                textFieldState = rememberTextFieldState(initialValue),
                label = label,
                placeholder = placeholder,
                //outlined = true,
                leadingIcon = leadingIcon,
                trailingIconButton = trailingIconButton,
                prefix = prefix,
                suffix = suffix,
                error = error,
                helperText = helperText,
                helperLink = helperLink
            )
        }
    }
}

@Preview
@Composable
internal fun PreviewOudsTextInputWithRoundedCorners() = OudsPreview(themeSettings = OudsThemeSettings().copy(roundedCornerTextInputs = true)) {
    PreviewEnumEntries<OudsTextInput.State>(columnCount = 1) { state ->
        OudsTextInput(
            textFieldState = rememberTextFieldState(""),
            label = "Label",
            placeholder = "Placeholder",
            leadingIcon = OudsTextInput.LeadingIcon(Icons.Filled.FavoriteBorder, contentDescription = "Icon"),
        )
    }
}

@Preview
@Composable
internal fun PreviewOudsTextInputWithTwoLinesLabel() = OudsPreview {
    OudsTextInput(
        modifier = Modifier.padding(all = 10.dp),
        textFieldState = rememberTextFieldState(),
        label = "Very long label displayed \non two lines",
    )
}

internal data class OudsTextInputPreviewParameter(
    val initialValue: String,
    val label: String? = null,
    val placeholder: String? = null,
    val leadingIcon: OudsTextInput.LeadingIcon? = null,
    val trailingIconButton: OudsTextInput.TrailingIconButton? = null,
    val prefix: String? = null,
    val suffix: String? = null,
    val enabled: Boolean = true,
    val readOnly: Boolean = false,
    val error: Boolean = false,
    val helperText: String? = null,
    val helperLink: OudsTextInput.HelperLink? = null
)

internal class OudsTextInputPreviewParameterProvider : BasicPreviewParameterProvider<OudsTextInputPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsTextInputPreviewParameter>
    get() {
        val label = "Label"
        val leadingIcon = OudsTextInput.LeadingIcon(Icons.Filled.FavoriteBorder, contentDescription = "Icon")
        val trailingIconButton = OudsTextInput.TrailingIconButton(Icons.Filled.FavoriteBorder, contentDescription = "Icon", onClick = {})
        return listOf(
            OudsTextInputPreviewParameter("", label = label),
            OudsTextInputPreviewParameter(
                "",
                label = label,
                placeholder = "Placeholder",
                leadingIcon = leadingIcon,
                trailingIconButton = trailingIconButton,
                prefix = "£",
                suffix = "€",
                error = true
            ),
            OudsTextInputPreviewParameter(
                "Text",
                label = label,
                placeholder = "Placeholder",
                leadingIcon = leadingIcon,
                trailingIconButton = trailingIconButton,
                prefix = "£",
                suffix = "€",
                helperText = "Helper text.",
                helperLink = OudsTextInput.HelperLink("Helper link") {}
            ),
            OudsTextInputPreviewParameter("Error text", label = label, error = true, helperText = "The format is not valid.")
        )
    }
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

import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.text.BasicSecureTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Density
import com.orange.ouds.core.R
import com.orange.ouds.core.component.common.OudsError
import com.orange.ouds.core.extensions.collectInteractionStateAsState
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.PreviewEnumEntries
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.theme.OudsThemeContract
import com.orange.ouds.theme.OudsThemeSettings

/**
 * Password input is a UI element that allows to securely and confidentially capture a user's password.
 * Password Input enhances privacy by replacing characters with dots, while they are being typed; and also embeds usability features such as the ability
 * to show and hide password, and helper text to guide password creation.
 *
 * Rounded corners can be enabled or disabled using [OudsThemeSettings.roundedCornerTextInputs] property in the settings of the theme provided when calling
 * the [com.orange.ouds.core.theme.OudsTheme] method.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://r.orange.fr/r/S-ouds-doc-password-input)
 *
 * > Design version: 1.2.0
 *
 * @param state The editable text state of the password input, including the text itself, position of the cursor or selection and the text obfuscation mode.
 * @param modifier [Modifier] applied to the password input.
 * @param label Label displayed above the password input. It describes the purpose of the input.
 * @param placeholder Text displayed when the password input is empty. It provides a hint or guidance inside the field to suggest expected input.
 * @param lockIcon When `true`, a lock icon is displayed at the start of the password input to visually reinforce the security context. Defaults to `false`.
 * @param prefix Text placed before the user's input. A prefix is not common and is discouraged in a Password Input component. In very specific cases,
 *   it can provide context or format requirements (e.g., “DEV-” for test accounts, "admin-" as a pattern to define an admin password)
 * @param enabled Controls the enabled state of the password input. When `false`, this password input will not be focusable and will not react to input events.
 *   True by default.
 * @param readOnly Controls the read-only state of the password input. When `true`, the text is visible but not editable.
 *   False by default.
 * @param loader An optional loading progress indicator displayed in the password input to indicate an ongoing operation.
 * @param outlined Controls the style of the password input. When `true`, it displays a minimalist password input with a transparent background and a visible
 *   stroke outlining the field.
 * @param error Optional [OudsError] to indicate that the user input does not meet validation rules or expected formatting. Pass `null` if there is no error.
 * @param helperText An optional helper text displayed below the password input. It conveys additional information about the input field, such as how it will be
 *   used. It should ideally only take up a single line, though it may wrap to multiple lines if required.
 * @param constrainedMaxWidth When `true`, the password input width is constrained to a maximum value defined by the design system.
 *   When `false`, no specific width constraint is applied, allowing the component to size itself or follow external modifiers.
 *   Defaults to `false`.
 * @param inputTransformation An optional [InputTransformation] that will be used to transform changes to the [OudsPasswordInputState] made by the user. The transformation
 *   will be applied to changes made by hardware and software keyboard events, pasting or dropping text, accessibility services, and tests. The transformation
 *   will _not_ be applied when changing the [state] programmatically, or when the transformation is changed. If the transformation is changed on an
 *   existing text field, it will be applied to the next user edit. The transformation will not immediately affect the current [state].
 * @param keyboardOptions Software keyboard options that contain configurations such as [KeyboardType] and [ImeAction].
 * @param onKeyboardAction Called when the user presses the action button in the input method editor (IME), or by pressing the enter key on a hardware keyboard.
 *   By default this parameter is null, and would execute the default behavior for a received IME Action e.g., [ImeAction.Done] would close the keyboard,
 *   [ImeAction.Next] would switch the focus to the next focusable item on the screen.
 * @param onTextLayout Callback that is executed when a new text layout is calculated. A [TextLayoutResult] object that callback provides contains paragraph
 *   information, size of the text, baselines and other details. The callback can be used to add additional decoration or functionality to the text.
 *   For example, to draw a cursor or selection around the text.
 * @param interactionSource An optional hoisted [MutableInteractionSource] for observing and emitting [Interaction]s for this password input. Note that if `null`
 *   is provided, interactions will still happen internally.
 *
 * @sample com.orange.ouds.core.component.samples.OudsPasswordInputSample
 *
 * @sample com.orange.ouds.core.component.samples.OudsPasswordInputErrorSample
 */
@Composable
fun OudsPasswordInput(
    state: OudsPasswordInputState,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    lockIcon: Boolean = false,
    prefix: String? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    loader: OudsTextInputLoader? = null,
    outlined: Boolean = false,
    error: OudsError? = null,
    helperText: String? = null,
    constrainedMaxWidth: Boolean = false,
    inputTransformation: InputTransformation? = null,
    keyboardOptions: KeyboardOptions = OudsPasswordInputDefaults.KeyboardOptions,
    onKeyboardAction: KeyboardActionHandler? = null,
    onTextLayout: (Density.(getResult: () -> TextLayoutResult?) -> Unit)? = null,
    interactionSource: MutableInteractionSource? = null
) {
    @Suppress("NAME_SHADOWING") val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    val interactionState by interactionSource.collectInteractionStateAsState()
    val passwordInputState = getTextInputState(enabled = enabled, readOnly = readOnly, loader = loader, interactionState = interactionState)

    val emptyText = state.text.isEmpty()

    var lastNonVisibleTextObfuscationMode by remember {
        mutableStateOf(
            state.textObfuscationMode.takeIf { it != TextObfuscationMode.Visible }
                .orElse { OudsPasswordInputDefaults.TextObfuscationMode.takeIf { it != TextObfuscationMode.Visible } }
                .orElse { TextObfuscationMode.RevealLastTyped })
    }

    OudsTextInput(
        state = passwordInputState,
        emptyText = emptyText,
        readOnly = readOnly,
        error = error,
        basicTextField = {
            BasicSecureTextField(
                modifier = modifier.textInputSemantic(label),
                state = state.textFieldState,
                enabled = textInputEnabled(state = passwordInputState),
                readOnly = readOnly,
                textStyle = textInputTextStyle(state = passwordInputState),
                cursorBrush = textInputCursorBrush(state = passwordInputState, error = error != null),
                textObfuscationMode = state.textObfuscationMode,
                keyboardOptions = keyboardOptions,
                onKeyboardAction = onKeyboardAction,
                onTextLayout = onTextLayout,
                inputTransformation = inputTransformation,
                interactionSource = interactionSource,
                decorator = { innerTextField ->
                    OudsTextInputDecorator(
                        innerTextField = innerTextField,
                        value = state.text.toString(),
                        state = passwordInputState,
                        label = label,
                        placeholder = placeholder,
                        leadingIcon = if (lockIcon) textInputLockIcon() else null,
                        trailingIconButton = trailingIconButton(isPasswordHidden = state.textObfuscationMode != TextObfuscationMode.Visible) {
                            with(state) {
                                if (textObfuscationMode == TextObfuscationMode.Visible) {
                                    textObfuscationMode = lastNonVisibleTextObfuscationMode
                                } else {
                                    lastNonVisibleTextObfuscationMode = textObfuscationMode
                                    textObfuscationMode = TextObfuscationMode.Visible
                                }
                            }
                        },
                        prefix = prefix,
                        suffix = null,
                        loader = loader,
                        outlined = outlined,
                        error = error,
                        helperText = helperText,
                        helperLink = null,
                        constrainedMaxWidth = constrainedMaxWidth
                    )
                },
                textObfuscationCharacter = '\u25cf'
            )
        }
    )
}

/**
 * Default values for [OudsPasswordInput].
 */
object OudsPasswordInputDefaults {

    /**
     * Default keyboard options of an [OudsPasswordInput].
     */
    val KeyboardOptions = KeyboardOptions(autoCorrectEnabled = false, keyboardType = KeyboardType.Password)

    /**
     * Default text obfuscation mode of an [OudsPasswordInput].
     */
    val TextObfuscationMode = androidx.compose.foundation.text.input.TextObfuscationMode.RevealLastTyped
}

@Composable
private fun textInputLockIcon() = OudsTextInputLeadingIcon(
    painter = painterResource(OudsTheme.drawableResources.communication.securityAndSafety.lock),
    contentDescription = ""
)

@Composable
private fun trailingIconButton(isPasswordHidden: Boolean, onClick: () -> Unit): OudsTextInputTrailingIconButton {
    val painterResId: Int
    val contentDescriptionResId: Int
    if (isPasswordHidden) {
        painterResId = OudsTheme.drawableResources.communication.accessibility.vision
        contentDescriptionResId = R.string.core_passwordInput_showPassword_a11y
    } else {
        painterResId = OudsTheme.drawableResources.functional.settingsAndTools.hide
        contentDescriptionResId = R.string.core_passwordInput_hidePassword_a11y
    }
    return OudsTextInputTrailingIconButton(
        painter = painterResource(painterResId),
        contentDescription = stringResource(contentDescriptionResId),
        onClick = onClick
    )
}

@PreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsPasswordInput(@PreviewParameter(OudsPasswordInputPreviewParameterProvider::class) parameter: OudsPasswordInputPreviewParameter) {
    PreviewOudsPasswordInput(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsPasswordInput(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsPasswordInputPreviewParameter
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        PreviewEnumEntries<OudsTextInputState>(columnCount = 1) {
            OudsPasswordInput(
                state = rememberOudsPasswordInputState(initialText),
                label = label,
                placeholder = placeholder,
                //outlined = true,
                lockIcon = lockIcon,
                prefix = prefix,
                error = error,
                helperText = helperText,
            )
        }
    }
}

internal data class OudsPasswordInputPreviewParameter(
    val initialText: String,
    val label: String? = null,
    val placeholder: String? = null,
    val lockIcon: Boolean = false,
    val prefix: String? = null,
    val enabled: Boolean = true,
    val readOnly: Boolean = false,
    val error: OudsError? = null,
    val helperText: String? = null,
)

internal class OudsPasswordInputPreviewParameterProvider :
    BasicPreviewParameterProvider<OudsPasswordInputPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsPasswordInputPreviewParameter>
    get() {
        val label = "Password"
        val placeholder = "Minimum 8 characters"
        val prefix = "DEV-"
        val error = OudsError("Error message.")
        val helperText = "Helper text."
        return listOf(
            OudsPasswordInputPreviewParameter(initialText = "", label = label),
            OudsPasswordInputPreviewParameter(
                initialText = "",
                label = label,
                placeholder = placeholder,
                lockIcon = false,
                prefix = prefix,
                error = error
            ),
            OudsPasswordInputPreviewParameter(
                initialText = "Text",
                label = label,
                placeholder = placeholder,
                lockIcon = true,
                prefix = prefix,
                helperText = helperText,
            ),
            OudsPasswordInputPreviewParameter(initialText = "Error text", label = label, error = error, helperText = helperText)
        )
    }

/**
 * @see OudsPasswordInput
 */
@Composable
@Deprecated(
    "Please use OudsPasswordInput composable instead, which is the equivalent of Material SecureTextField in OUDS Android.",
    ReplaceWith(
        "OudsPasswordInput(state = state, modifier = modifier, label = label, placeholder = placeholder, lockIcon = lockIcon, prefix = prefix, " +
                "enabled = enabled, readOnly = readOnly, loader = loader, outlined = outlined, error = error, helperText = helperText, " +
                "constrainedMaxWidth = constrainedMaxWidth, inputTransformation = inputTransformation, keyboardOptions = keyboardOptions, " +
                "onKeyboardAction = onKeyboardAction, onTextLayout = onTextLayout, interactionSource = interactionSource)"
    )
)
fun OudsSecureTextField(
    state: OudsPasswordInputState,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    lockIcon: Boolean = false,
    prefix: String? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    loader: OudsTextInputLoader? = null,
    outlined: Boolean = false,
    error: OudsError? = null,
    helperText: String? = null,
    constrainedMaxWidth: Boolean = false,
    inputTransformation: InputTransformation? = null,
    keyboardOptions: KeyboardOptions = OudsPasswordInputDefaults.KeyboardOptions,
    onKeyboardAction: KeyboardActionHandler? = null,
    onTextLayout: (Density.(getResult: () -> TextLayoutResult?) -> Unit)? = null,
    interactionSource: MutableInteractionSource? = null
) {
    OudsPasswordInput(
        state = state,
        modifier = modifier,
        label = label,
        placeholder = placeholder,
        lockIcon = lockIcon,
        prefix = prefix,
        enabled = enabled,
        readOnly = readOnly,
        loader = loader,
        outlined = outlined,
        error = error,
        helperText = helperText,
        constrainedMaxWidth = constrainedMaxWidth,
        inputTransformation = inputTransformation,
        keyboardOptions = keyboardOptions,
        onKeyboardAction = onKeyboardAction,
        onTextLayout = onTextLayout,
        interactionSource = interactionSource
    )
}

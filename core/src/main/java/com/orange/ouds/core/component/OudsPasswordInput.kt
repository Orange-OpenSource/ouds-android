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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.PlatformImeOptions
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Density
import com.orange.ouds.core.R
import com.orange.ouds.core.component.common.OudsError
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.PreviewEnumEntries
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.foundation.ExperimentalOudsApi
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
 * It is recommended to use state-based password inputs rather than value-based ones, as they provide a more complete and reliable approach to managing
 * the state of a password input.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://r.orange.fr/r/S-ouds-doc-password-input)
 *
 * > Design version: 1.2.0
 *
 * @param state The editable text state of the password input, including the text itself, position of the cursor or selection and the password visibility.
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
 * @param keyboardOptions Software-keyboard options that can be customized for this password input. This parameter is of type [OudsPasswordInputKeyboardOptions],
 *   which is a specific class for password fields. It ensures that the keyboard type is always `KeyboardType.Password`, while allowing for the customization of
 *   other common options.
 * @param onKeyboardAction Called when the user presses the action button in the input method editor (IME), or by pressing the enter key on a hardware keyboard.
 *   By default this parameter is null, and would execute the default behavior for a received IME Action e.g., [ImeAction.Done] would close the keyboard,
 *   [ImeAction.Next] would switch the focus to the next focusable item on the screen.
 * @param onTextLayout Callback that is executed when a new text layout is calculated. A [TextLayoutResult] object that callback provides contains paragraph
 *   information, size of the text, baselines and other details. The callback can be used to add additional decoration or functionality to the text.
 *   For example, to draw a cursor or selection around the text.
 * @param inputTransformation An optional [InputTransformation] that will be used to transform changes to the [OudsPasswordInputState] made by the user. The transformation
 *   will be applied to changes made by hardware and software keyboard events, pasting or dropping text, accessibility services, and tests. The transformation
 *   will _not_ be applied when changing the [state] programmatically, or when the transformation is changed. If the transformation is changed on an
 *   existing text field, it will be applied to the next user edit. The transformation will not immediately affect the current [state].
 * @param interactionSource An optional hoisted [MutableInteractionSource] for observing and emitting [Interaction]s for this password input. Note that if `null`
 *   is provided, interactions will still happen internally.
 *
 * @sample com.orange.ouds.core.component.samples.OudsPasswordInputStateBasedSample
 *
 * @sample com.orange.ouds.core.component.samples.OudsPasswordInputStateBasedErrorSample
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
    keyboardOptions: OudsPasswordInputKeyboardOptions = OudsPasswordInputKeyboardOptions(),
    onKeyboardAction: KeyboardActionHandler? = null,
    onTextLayout: (Density.(getResult: () -> TextLayoutResult?) -> Unit)? = null,
    inputTransformation: InputTransformation? = null,
    interactionSource: MutableInteractionSource? = null
) {
    OudsTextInput(
        textFieldState = state.textFieldState,
        modifier = modifier,
        label = label,
        placeholder = placeholder,
        leadingIcon = if (lockIcon) textInputLockIcon() else null,
        trailingIconButton = trailingIconButton(isPasswordHidden = state.isPasswordHidden) {
            state.isPasswordHidden = !state.isPasswordHidden
        },
        prefix = prefix,
        enabled = enabled,
        readOnly = readOnly,
        loader = loader,
        outlined = outlined,
        error = error,
        helperText = helperText,
        constrainedMaxWidth = constrainedMaxWidth,
        keyboardOptions = keyboardOptions.toKeyboardOptions(),
        onKeyboardAction = onKeyboardAction,
        onTextLayout = onTextLayout,
        inputTransformation = inputTransformation,
        outputTransformation = OutputTransformation {
            val visualTransformation = visualTransformation(state.isPasswordHidden)
            val transformedText = visualTransformation.filter(AnnotatedString(state.text.toString()))
            replace(start = 0, end = length, text = transformedText.text)
        },
        interactionSource = interactionSource
    )
}

/**
 * Password input is a UI element that allows to securely and confidentially capture a user's password.
 * Password Input enhances privacy by replacing characters with dots, while they are being typed; and also embeds usability features such as the ability
 * to show and hide password, and helper text to guide password creation.
 *
 * Rounded corners can be enabled or disabled using [OudsThemeSettings.roundedCornerTextInputs] property in the settings of the theme provided when calling
 * the [com.orange.ouds.core.theme.OudsTheme] method.
 *
 * It is recommended to use state-based password inputs rather than value-based ones, as they provide a more complete and reliable approach to managing
 * the state of a password input.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://r.orange.fr/r/S-ouds-doc-password-input)
 *
 * > Design version: 1.2.0
 *
 * @param value The [androidx.compose.ui.text.input.TextFieldValue] to be shown in the password input.
 * @param onValueChange Called when the input service updates the values in [TextFieldValue].
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
 * @param keyboardOptions Software-keyboard options that can be customized for this password input. This parameter is of type [OudsPasswordInputKeyboardOptions],
 *   which is a specific class for password fields. It ensures that the keyboard type is always `KeyboardType.Password`, while allowing for the customization of
 *   other common options.
 * @param keyboardActions When the input service emits an IME action, the corresponding callback is called. Note that this IME action may be different from what
 *   you specified in [OudsPasswordInputKeyboardOptions.imeAction].
 * @param onTextLayout Callback that is executed when a new text layout is calculated. A [TextLayoutResult] object that callback provides contains paragraph
 *   information, size of the text, baselines and other details. The callback can be used to add additional decoration or functionality to the text.
 *   For example, to draw a cursor or selection around the text.
 * @param interactionSource An optional hoisted [MutableInteractionSource] for observing and emitting [Interaction]s for this password input. Note that if `null`
 *   is provided, interactions will still happen internally.
 *
 * @sample com.orange.ouds.core.component.samples.OudsPasswordInputValueBasedSample
 *
 * @sample com.orange.ouds.core.component.samples.OudsPasswordInputValueBasedErrorSample
 */
@Composable
@ExperimentalOudsApi
fun OudsPasswordInput(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
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
    keyboardOptions: OudsPasswordInputKeyboardOptions = OudsPasswordInputKeyboardOptions(),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    interactionSource: MutableInteractionSource? = null
) {
    var isPasswordHidden by remember { mutableStateOf(true) }
    OudsTextInput(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = label,
        placeholder = placeholder,
        leadingIcon = if (lockIcon) textInputLockIcon() else null,
        trailingIconButton = trailingIconButton(isPasswordHidden = isPasswordHidden) {
            isPasswordHidden = !isPasswordHidden
        },
        prefix = prefix,
        enabled = enabled,
        readOnly = readOnly,
        loader = loader,
        outlined = outlined,
        error = error,
        helperText = helperText,
        constrainedMaxWidth = constrainedMaxWidth,
        keyboardOptions = keyboardOptions.toKeyboardOptions(),
        keyboardActions = keyboardActions,
        onTextLayout = onTextLayout,
        visualTransformation = visualTransformation(isPasswordHidden),
        interactionSource = interactionSource
    )
}

/**
 * Password input is a UI element that allows to securely and confidentially capture a user's password.
 * Password Input enhances privacy by replacing characters with dots, while they are being typed; and also embeds usability features such as the ability
 * to show and hide password, and helper text to guide password creation.
 *
 * Rounded corners can be enabled or disabled using [OudsThemeSettings.roundedCornerTextInputs] property in the settings of the theme provided when calling
 * the [com.orange.ouds.core.theme.OudsTheme] method.
 *
 * It is recommended to use state-based password inputs rather than value-based ones, as they provide a more complete and reliable approach to managing
 * the state of a password input.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://r.orange.fr/r/S-ouds-doc-password-input)
 *
 * > Design version: 1.2.0
 *
 * @param value The password text to be shown in the text field.
 * @param onValueChange The callback that is triggered when the user enters text. The updated password text is passed as a parameter.
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
 * @param keyboardOptions Software-keyboard options that can be customized for this password input. This parameter is of type [OudsPasswordInputKeyboardOptions],
 *   which is a specific class for password fields. It ensures that the keyboard type is always `KeyboardType.Password`, while allowing for the customization of
 *   other common options.
 * @param keyboardActions When the input service emits an IME action, the corresponding callback is called. Note that this IME action may be different from what
 *   you specified in [OudsPasswordInputKeyboardOptions.imeAction].
 * @param onTextLayout Callback that is executed when a new text layout is calculated. A [TextLayoutResult] object that callback provides contains paragraph
 *   information, size of the text, baselines and other details. The callback can be used to add additional decoration or functionality to the text.
 *   For example, to draw a cursor or selection around the text.
 * @param interactionSource An optional hoisted [MutableInteractionSource] for observing and emitting [Interaction]s for this password input. Note that if `null`
 *   is provided, interactions will still happen internally.
 *
 * @sample com.orange.ouds.core.component.samples.OudsPasswordInputValueBasedSample
 *
 * @sample com.orange.ouds.core.component.samples.OudsPasswordInputValueBasedErrorSample
 */
@Composable
@ExperimentalOudsApi
fun OudsPasswordInput(
    value: String,
    onValueChange: (String) -> Unit,
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
    keyboardOptions: OudsPasswordInputKeyboardOptions = OudsPasswordInputKeyboardOptions(),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    interactionSource: MutableInteractionSource? = null
) {
    var isPasswordHidden by remember { mutableStateOf(true) }
    OudsTextInput(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = label,
        placeholder = placeholder,
        leadingIcon = if (lockIcon) textInputLockIcon() else null,
        trailingIconButton = trailingIconButton(isPasswordHidden = isPasswordHidden) {
            isPasswordHidden = !isPasswordHidden
        },
        prefix = prefix,
        enabled = enabled,
        readOnly = readOnly,
        loader = loader,
        outlined = outlined,
        error = error,
        helperText = helperText,
        constrainedMaxWidth = constrainedMaxWidth,
        keyboardOptions = keyboardOptions.toKeyboardOptions(),
        keyboardActions = keyboardActions,
        onTextLayout = onTextLayout,
        visualTransformation = visualTransformation(isPasswordHidden),
        interactionSource = interactionSource
    )
}

/**
 * Keyboard options specific to the [OudsPasswordInput] component.
 *
 * This class encapsulates [KeyboardOptions] to configure the software keyboard for password entry.
 * It ensures that the [KeyboardType] is always set to [KeyboardType.Password] and allows for the customization
 * of other common keyboard options.
 *
 * @property imeAction The action to be displayed in the bottom corner of the keyboard (e.g., "Done", "Next").
 * @property platformImeOptions Platform-specific input method editor (IME) options.
 * @property showKeyboardOnFocus When set to `true`, software keyboard will show on focus gain. When false, the user must interact (e.g. tap)
 * before the keyboard is shown. A `null` value (the default parameter value) means the keyboard will be shown on focus.
 */
@ExperimentalOudsApi
data class OudsPasswordInputKeyboardOptions(
    val imeAction: ImeAction = ImeAction.Unspecified,
    val platformImeOptions: PlatformImeOptions? = null,
    val showKeyboardOnFocus: Boolean? = null
) {
    internal fun toKeyboardOptions() = KeyboardOptions(
        keyboardType = KeyboardType.Password,
        imeAction = imeAction,
        platformImeOptions = platformImeOptions,
        showKeyboardOnFocus = showKeyboardOnFocus
    )
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

private fun visualTransformation(isPasswordHidden: Boolean) =
    if (isPasswordHidden) PasswordVisualTransformation(mask = '\u25cf') else VisualTransformation.None

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

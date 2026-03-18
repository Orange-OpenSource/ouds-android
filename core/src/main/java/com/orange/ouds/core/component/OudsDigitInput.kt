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
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicSecureTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalCursorBlinkEnabled
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.component.common.bottomBorder
import com.orange.ouds.core.extensions.InteractionState
import com.orange.ouds.core.extensions.collectInteractionStateAsState
import com.orange.ouds.core.theme.LocalThemeSettings
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.takeUnlessHairline
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.CheckedContent
import com.orange.ouds.core.utilities.LocalPreviewEnumEntry
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.OudsPreviewDevice
import com.orange.ouds.core.utilities.PreviewEnumEntries
import com.orange.ouds.core.utilities.getPreviewEnumEntry
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.core.utilities.mapSettings
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.theme.OudsThemeContract

@Composable
internal fun OudsDigitInput(
    digit: Char?,
    onDigitChange: ((Char?) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    outlined: Boolean = false,
    error: Boolean = false,
    placeholder: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onKeyboardAction: KeyboardActionHandler? = null
) {
    val interactionSource = remember { MutableInteractionSource() }
    val interactionState by interactionSource.collectInteractionStateAsState()
    val state = getDigitInputState(enabled = enabled, readOnly = readOnly, interactionState = interactionState)

    val isForbidden = error && state in listOf(OudsDigitInputState.ReadOnly, OudsDigitInputState.Disabled)
    CheckedContent(
        expression = !isForbidden,
        exceptionMessage = {
            val parameter = if (readOnly) "readOnly" else "disabled"
            "An OudsDigitInput set to $parameter with an error is not allowed."
        }
    ) {
        val textInputTokens = OudsTheme.componentsTokens.textInput
        val pinCodeInputTokens = OudsTheme.componentsTokens.pinCodeInput

        val backgroundColor = rememberInteractionColor(interactionState = interactionState) { digitInputInteractionState ->
            val digitInputState = getDigitInputState(enabled = enabled, readOnly = readOnly, interactionState = digitInputInteractionState)
            backgroundColor(state = digitInputState, outlined = outlined, error = error)
        }

        val borderColor = rememberNullableInteractionColor(interactionState = interactionState) { digitInputInteractionState ->
            val digitInputState = getDigitInputState(enabled = enabled, readOnly = readOnly, interactionState = digitInputInteractionState)
            borderColor(state = digitInputState, outlined = outlined, error = error)
        }

        val borderWidth = rememberInteractionValue(
            interactionState = interactionState,
            toAnimatableFloat = { it?.value.orElse { 0f } },
            fromAnimatableFloat = { it.dp }
        ) { digitInputInteractionState ->
            val digitInputState = getDigitInputState(enabled = enabled, readOnly = readOnly, interactionState = digitInputInteractionState)
            borderWidth(state = digitInputState, outlined = outlined)
        }

        val indication = InteractionValuesIndication(backgroundColor, borderColor, borderWidth)

        val textFieldState = rememberTextFieldState(initialText = digit?.toString().orEmpty())

        LaunchedEffect(textFieldState) {
            snapshotFlow { textFieldState.text }
                .collect { newText ->
                    val newDigit = newText.lastOrNull { it.isDigit() }
                    if (newDigit != digit) {
                        onDigitChange?.invoke(newDigit)
                    }
                }
        }

        LaunchedEffect(digit) {
            val text = digit?.takeIf { it.isDigit() }?.toString().orEmpty()
            if (textFieldState.text != text) {
                textFieldState.setTextAndPlaceCursorAtEnd(text)
            }
        }

        val textStyle = OudsTheme.typography.label.default.large

        var focusRequester: FocusRequester? = null
        val requestFocus = LocalInspectionMode.current && getPreviewEnumEntry<OudsDigitInputState>() == OudsDigitInputState.Focused
        if (requestFocus) {
            focusRequester = remember { FocusRequester() }
            LaunchedEffect(Unit) {
                focusRequester.requestFocus()
            }
        }

        val basicSecureTextField = @Composable {
            BasicSecureTextField(
                modifier = modifier
                    .run { if (focusRequester != null) focusRequester(focusRequester) else this }
                    .indication(interactionSource = interactionSource, indication = indication)
                    .heightIn(min = textInputTokens.sizeMinHeight.dp)
                    .widthIn(min = pinCodeInputTokens.sizeMinWidth.dp, max = pinCodeInputTokens.sizeMaxWidth.dp)
                    .background(color = backgroundColor.value, shape = shape())
                    .digitInputBorder(
                        borderWidth = borderWidth.value,
                        borderColor = borderColor.value,
                        state = state,
                        outlined = outlined
                    )
                    .padding(
                        horizontal = textInputTokens.spacePaddingInlineDefault.value,
                        vertical = textInputTokens.spacePaddingBlockDefault.value
                    ),
                interactionSource = interactionSource,
                state = textFieldState,
                enabled = state !in listOf(OudsDigitInputState.Disabled, OudsDigitInputState.ReadOnly),
                readOnly = readOnly,
                inputTransformation = InputTransformation {
                    val digits = asCharSequence().lastOrNull { it.isDigit() }?.toString().orEmpty()
                    replace(0, length, digits)
                },
                textStyle = textStyle.copy(textAlign = TextAlign.Center, color = textColor(state = state)),
                keyboardOptions = keyboardOptions,
                onKeyboardAction = onKeyboardAction,
                cursorBrush = cursorBrush(error = error),
                textObfuscationMode = TextObfuscationMode.Hidden,
                textObfuscationCharacter = OudsPasswordInputTextObfuscationCharacter,
                decorator = { innerTextField ->
                    Box(contentAlignment = Alignment.Center) {
                        if (placeholder && textFieldState.text.isEmpty() && state != OudsDigitInputState.Focused) {
                            Text(
                                text = "-",
                                color = placeholderColor(state = state),
                                textAlign = TextAlign.Center,
                                style = textStyle
                            )
                        }
                        innerTextField()
                    }
                }
            )
        }

        if (requestFocus) {
            CompositionLocalProvider(
                LocalCursorBlinkEnabled provides false,
                LocalPreviewEnumEntry provides OudsDigitInputState.Focused
            ) {
                basicSecureTextField()
            }
        } else {
            basicSecureTextField()
        }
    }
}

@Composable
private fun textColor(state: OudsDigitInputState): Color {
    return with(OudsTheme.colorScheme) { if (state == OudsDigitInputState.Disabled) action.disabled else content.default }
}

@Composable
private fun placeholderColor(state: OudsDigitInputState): Color {
    return with(OudsTheme.colorScheme) { if (state == OudsDigitInputState.Disabled) action.disabled else content.muted }
}

@Composable
private fun backgroundColor(state: OudsDigitInputState, outlined: Boolean, error: Boolean): Color {
    return if (error) {
        if (outlined) Color.Transparent else OudsTheme.colorScheme.surface.status.negative.muted
    } else {
        if (outlined) {
            if (state == OudsDigitInputState.ReadOnly) OudsTheme.colorScheme.action.support.disabled else Color.Transparent
        } else {
            when (state) {
                OudsDigitInputState.Enabled -> OudsTheme.colorScheme.action.support.enabled
                OudsDigitInputState.Hovered -> OudsTheme.colorScheme.action.support.hover
                OudsDigitInputState.Focused -> OudsTheme.colorScheme.action.support.pressed
                OudsDigitInputState.ReadOnly -> Color.Transparent
                OudsDigitInputState.Disabled -> OudsTheme.colorScheme.action.support.disabled
            }
        }
    }
}

@Composable
private fun borderColor(state: OudsDigitInputState, outlined: Boolean, error: Boolean): Color? {
    return if (error) {
        with(OudsTheme.colorScheme.action.negative) {
            when (state) {
                OudsDigitInputState.Enabled -> enabled
                OudsDigitInputState.Hovered -> hover
                OudsDigitInputState.Focused -> pressed
                OudsDigitInputState.ReadOnly,
                OudsDigitInputState.Disabled -> Color.Unspecified
            }
        }
    } else {
        val textInputTokens = OudsTheme.componentsTokens.textInput
        when (state) {
            OudsDigitInputState.Enabled -> textInputTokens.colorBorderEnabled.value
            OudsDigitInputState.Hovered -> textInputTokens.colorBorderHover.value
            OudsDigitInputState.Focused -> textInputTokens.colorBorderFocus.value
            OudsDigitInputState.ReadOnly -> if (outlined) null else OudsTheme.colorScheme.border.muted
            OudsDigitInputState.Disabled -> OudsTheme.colorScheme.action.disabled
        }
    }
}

@Composable
private fun borderWidth(state: OudsDigitInputState, outlined: Boolean): Dp? {
    return with(OudsTheme.componentsTokens.textInput) {
        when (state) {
            OudsDigitInputState.Enabled,
            OudsDigitInputState.Hovered,
            OudsDigitInputState.Disabled -> borderWidthDefault
            OudsDigitInputState.ReadOnly -> if (outlined) null else borderWidthDefault
            OudsDigitInputState.Focused -> borderWidthFocus
        }
    }?.value?.takeUnlessHairline
}

@Composable
private fun cursorBrush(error: Boolean): Brush {
    val cursorColor = if (error) OudsTheme.colorScheme.action.negative.pressed else OudsTheme.colorScheme.content.default
    return SolidColor(cursorColor)
}

@Composable
private fun Modifier.digitInputBorder(borderWidth: Dp?, borderColor: Color?, state: OudsDigitInputState, outlined: Boolean): Modifier {
    return if (borderWidth != null && borderColor != null) {
        when {
            outlined && state == OudsDigitInputState.ReadOnly -> this
            !outlined && state != OudsDigitInputState.ReadOnly -> bottomBorder(borderWidth, borderColor, borderRadius())
            else -> border(borderWidth, borderColor, shape())
        }
    } else {
        this
    }
}

@Composable
private fun borderRadius(): Dp {
    return with(OudsTheme.componentsTokens.textInput) {
        if (LocalThemeSettings.current.roundedCornerTextInputs == true) borderRadiusRounded else borderRadiusDefault
    }.value
}

@Composable
private fun shape(): Shape {
    return RoundedCornerShape(borderRadius())
}

@Composable
private fun getDigitInputState(enabled: Boolean, readOnly: Boolean, interactionState: InteractionState): OudsDigitInputState {
    return getPreviewEnumEntry<OudsDigitInputState>().orElse {
        when {
            !enabled -> OudsDigitInputState.Disabled
            readOnly -> OudsDigitInputState.ReadOnly
            interactionState == InteractionState.Hovered -> OudsDigitInputState.Hovered
            interactionState == InteractionState.Focused -> OudsDigitInputState.Focused
            else -> OudsDigitInputState.Enabled
        }
    }
}

internal enum class OudsDigitInputState {
    Enabled, Hovered, Focused, ReadOnly, Disabled
}

internal const val OudsDigitInputPreviewWidthDp = 380

@Preview(name = "Light", widthDp = OudsDigitInputPreviewWidthDp, device = OudsPreviewDevice)
@Preview(
    name = "Dark",
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL,
    widthDp = OudsDigitInputPreviewWidthDp,
    device = OudsPreviewDevice
)
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsDigitInput(@PreviewParameter(OudsDigitInputPreviewParameterProvider::class) parameter: OudsDigitInputPreviewParameter) = OudsPreview {
    PreviewOudsDigitInput(theme = getPreviewTheme(), darkThemeEnabled = false, parameter = parameter)
}

@Composable
internal fun PreviewOudsDigitInput(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsDigitInputPreviewParameter
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        PreviewEnumEntries<OudsDigitInputState> {
            OudsDigitInput(
                digit = digit,
                onDigitChange = null,
                outlined = outlined,
                error = error
            )
        }
    }
}

internal data class OudsDigitInputPreviewParameter(
    val digit: Char? = null,
    val outlined: Boolean = false,
    val error: Boolean = false
)

internal class OudsDigitInputPreviewParameterProvider : BasicPreviewParameterProvider<OudsDigitInputPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsDigitInputPreviewParameter>
    get() {
        return listOf(
            OudsDigitInputPreviewParameter(),
            OudsDigitInputPreviewParameter(digit = '1'),
            OudsDigitInputPreviewParameter(digit = '1', error = true)
        ).flatMap { listOf(it, it.copy(outlined = true)) }
    }

@Preview(name = "Light", widthDp = OudsDigitInputPreviewWidthDp, device = OudsPreviewDevice)
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsDigitInputWithRoundedCorners(@PreviewParameter(OudsDigitInputWithRoundedCornersPreviewParameterProvider::class) outlined: Boolean) =
    PreviewOudsDigitInputWithRoundedCorners(theme = getPreviewTheme(), outlined = outlined)

@Composable
internal fun PreviewOudsDigitInputWithRoundedCorners(theme: OudsThemeContract, outlined: Boolean) =
    OudsPreview(theme = theme.mapSettings { it.copy(roundedCornerTextInputs = true) }) {
        PreviewEnumEntries<OudsDigitInputState> {
            OudsDigitInput(
                digit = '1',
                onDigitChange = null,
                outlined = outlined
            )
        }
    }

internal class OudsDigitInputWithRoundedCornersPreviewParameterProvider : BasicPreviewParameterProvider<Boolean>(false, true)

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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.component.common.bottomBorder
import com.orange.ouds.core.theme.LocalThemeSettings
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.takeUnlessHairline
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.CheckedContent
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.OudsPreviewDevice
import com.orange.ouds.core.utilities.OudsPreviewLightDark
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
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    state: OudsDigitInputState = OudsDigitInputState.Enabled,
    outlined: Boolean = false,
    error: Boolean = false,
    placeholder: Boolean = true
) {
    @Suppress("NAME_SHADOWING") val state = getPreviewEnumEntry<OudsDigitInputState>().orElse { state }

    val isForbidden = error && state in listOf(OudsDigitInputState.ReadOnly, OudsDigitInputState.Disabled)
    CheckedContent(
        expression = !isForbidden,
        exceptionMessage = {
            val parameter = if (state == OudsDigitInputState.ReadOnly) "readOnly" else "disabled"
            "An OudsDigitInput set to $parameter with an error is not allowed."
        }
    ) {
        val textInputTokens = OudsTheme.componentsTokens.textInput
        val pinCodeInputTokens = OudsTheme.componentsTokens.pinCodeInput

        val backgroundColor = backgroundColor(state = state, outlined = outlined, error = error)
        val borderColor = borderColor(state = state, outlined = outlined, error = error)
        val borderWidth = borderWidth(state = state, outlined = outlined)

        Row(
            modifier = modifier
                .clickable(
                    onClick = onClick,
                    interactionSource = null,
                    indication = null
                )
                .heightIn(min = textInputTokens.sizeMinHeight.dp)
                .widthIn(min = pinCodeInputTokens.sizeMinWidth.dp, max = pinCodeInputTokens.sizeMaxWidth.dp)
                .background(color = backgroundColor, shape = shape())
                .digitInputBorder(
                    borderWidth = borderWidth,
                    borderColor = borderColor,
                    state = state,
                    outlined = outlined
                )
                .padding(
                    horizontal = textInputTokens.spacePaddingInlineDefault.value,
                    vertical = textInputTokens.spacePaddingBlockDefault.value
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            val text = when {
                digit?.isDigit() == true -> OudsPasswordInputTextObfuscationCharacter.toString()
                placeholder && state != OudsDigitInputState.Focused -> OudsDigitInputPlaceholder.toString()
                else -> ""
            }
            val textStyle = OudsTheme.typography.label.default.large
            val textColor = if (digit != null) textColor(state = state) else placeholderColor(state = state)
            Text(text = text, style = textStyle, color = textColor)
            if (state == OudsDigitInputState.Focused) {
                Text(text = "|", style = textStyle, color = cursorColor(error = error))
            }
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
private fun cursorColor(error: Boolean): Color {
    return if (error) OudsTheme.colorScheme.action.negative.pressed else OudsTheme.colorScheme.content.default
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

internal const val OudsDigitInputPlaceholder = '-'

internal enum class OudsDigitInputState {
    Enabled, Hovered, Focused, ReadOnly, Disabled
}

@OudsPreviewLightDark
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
                onClick = {},
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

@Preview(name = "Light", device = OudsPreviewDevice)
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
                onClick = {},
                outlined = outlined
            )
        }
    }

internal class OudsDigitInputWithRoundedCornersPreviewParameterProvider : BasicPreviewParameterProvider<Boolean>(false, true)

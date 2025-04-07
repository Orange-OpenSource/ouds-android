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
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.R
import com.orange.ouds.core.extensions.InteractionState
import com.orange.ouds.core.extensions.collectInteractionStateAsState
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.outerBorder
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.CheckedContent
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.StatesPreview
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider

/**
 * <a href="https://unified-design-system.orange.com/472794e18/p/90c467-radio-button" class="external" target="_blank">OUDS Radio button design guidelines</a>
 *
 * An OUDS radio button.
 *
 * @param selected Controls the selected state of the radio button.
 * @param onClick Callback invoked on radio button click. If `null`, then this radio button will not be interactable, unless something else handles its
 * input events and updates its state.
 * @param modifier [Modifier] applied to the layout of the radio button.
 * @param enabled Controls the enabled state of the radio button. When `false`, this radio button will not be clickable.
 * @param error Controls the error state of the radio button.
 * @param interactionSource Optional hoisted [MutableInteractionSource] for observing and emitting [Interaction]s for this radio button. Note that if `null`
 * is provided, interactions will still happen internally.
 *
 * @sample com.orange.ouds.core.component.samples.OudsRadioButtonSample
 */
@Composable
fun OudsRadioButton(
    selected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    error: Boolean = false,
    interactionSource: MutableInteractionSource? = null
) {
    OudsRadioButton(
        selected = selected,
        onClick = onClick,
        previewState = null,
        modifier = modifier,
        enabled = enabled,
        error = error,
        interactionSource = interactionSource
    )
}

@Composable
private fun OudsRadioButton(
    selected: Boolean,
    onClick: (() -> Unit)?,
    previewState: OudsRadioButton.State?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    error: Boolean = false,
    interactionSource: MutableInteractionSource? = null
) {
    val isDisabledPreviewState = previewState == OudsRadioButton.State.Disabled
    val isForbidden = error && (!enabled || isDisabledPreviewState)
    CheckedContent(
        expression = !isForbidden,
        exceptionMessage = { "An OudsRadioButton set to disabled with error parameter activated is not allowed." }
    ) {
        val radioButtonTokens = OudsTheme.componentsTokens.radioButton
        val radioButtonInteractionSource = interactionSource ?: remember { MutableInteractionSource() }
        val interactionState by radioButtonInteractionSource.collectInteractionStateAsState()
        val state = previewState.orElse { rememberOudsRadioButtonState(enabled = enabled, interactionState = interactionState) }

        val selectableModifier = if (onClick != null) {
            Modifier.selectable(
                selected = selected,
                onClick = onClick,
                enabled = enabled,
                interactionSource = radioButtonInteractionSource,
                indication = null,
                role = Role.RadioButton,
            )
        } else Modifier

        Box(
            modifier = modifier
                .widthIn(radioButtonTokens.sizeMinWidth.dp)
                .heightIn(min = radioButtonTokens.sizeMinHeight.dp, max = radioButtonTokens.sizeMaxHeight.dp)
                .background(color = backgroundColor(state = state))
                .border(state = state)
                .then(selectableModifier),
            contentAlignment = Alignment.Center,
        ) {
            OudsRadioButtonIndicator(state = state, selected = selected, error = error)
        }
    }
}

@Composable
internal fun OudsRadioButtonIndicator(state: OudsRadioButton.State, selected: Boolean, error: Boolean) {
    val radioButtonTokens = OudsTheme.componentsTokens.radioButton
    Box(
        modifier = Modifier
            .size(radioButtonTokens.sizeIndicator.value)
            .indicatorBorder(state = state, selected = selected, error = error)
    ) {
        if (selected) {
            Icon(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(R.drawable.radiobutton_selected),
                tint = indicatorColor(state = state, selected = true, error = error),
                contentDescription = null
            )
        }
    }
}

@Composable
private fun rememberOudsRadioButtonState(
    enabled: Boolean,
    interactionState: InteractionState
): OudsRadioButton.State = remember(enabled, interactionState) {
    when {
        !enabled -> OudsRadioButton.State.Disabled
        interactionState == InteractionState.Hovered -> OudsRadioButton.State.Hovered
        interactionState == InteractionState.Pressed -> OudsRadioButton.State.Pressed
        interactionState == InteractionState.Focused -> OudsRadioButton.State.Focused
        else -> OudsRadioButton.State.Enabled
    }
}

@Composable
private fun Modifier.indicatorBorder(state: OudsRadioButton.State, selected: Boolean, error: Boolean): Modifier {
    val indicatorBorderWidth = indicatorBorderWidth(state = state, selected = selected)
    return border(
        width = indicatorBorderWidth,
        color = indicatorColor(state = state, selected = selected, error = error),
        shape = RoundedCornerShape(OudsTheme.componentsTokens.radioButton.borderRadius.value)
    )
}

@Composable
private fun indicatorBorderWidth(state: OudsRadioButton.State, selected: Boolean): Dp {
    return with(OudsTheme.componentsTokens.radioButton) {
        when (state) {
            OudsRadioButton.State.Enabled, OudsRadioButton.State.Disabled -> if (selected) borderWidthSelected else borderWidthUnselected
            OudsRadioButton.State.Hovered -> if (selected) borderWidthSelectedHover else borderWidthUnselectedHover
            OudsRadioButton.State.Pressed -> if (selected) borderWidthSelectedPressed else borderWidthUnselectedPressed
            OudsRadioButton.State.Focused -> if (selected) borderWidthSelectedFocus else borderWidthUnselectedFocus
        }.value
    }
}

@Composable
private fun indicatorColor(state: OudsRadioButton.State, selected: Boolean, error: Boolean): Color {
    return with(OudsTheme.colorScheme.action) {
        if (error) {
            when (state) {
                OudsRadioButton.State.Enabled -> negative.enabled
                OudsRadioButton.State.Disabled -> Color.Unspecified // Not allowed, exception thrown at the beginning of OudsRadioButton
                OudsRadioButton.State.Hovered -> negative.hover
                OudsRadioButton.State.Pressed -> negative.pressed
                OudsRadioButton.State.Focused -> negative.focus
            }
        } else {
            when (state) {
                OudsRadioButton.State.Enabled -> if (selected) this.selected else OudsTheme.colorScheme.border.emphasized
                OudsRadioButton.State.Disabled -> disabled
                OudsRadioButton.State.Hovered -> hover
                OudsRadioButton.State.Pressed -> pressed
                OudsRadioButton.State.Focused -> focus
            }
        }
    }
}

@Composable
private fun backgroundColor(state: OudsRadioButton.State): Color {
    return with(OudsTheme.componentsTokens.controlItem) {
        when (state) {
            OudsRadioButton.State.Enabled, OudsRadioButton.State.Disabled -> Color.Transparent
            OudsRadioButton.State.Hovered -> colorBgHover.value
            OudsRadioButton.State.Pressed -> colorBgPressed.value
            OudsRadioButton.State.Focused -> colorBgFocus.value
        }
    }
}

@Composable
private fun Modifier.border(state: OudsRadioButton.State) = if (state == OudsRadioButton.State.Focused) {
    outerBorder(
        width = OudsTheme.borders.width.focus,
        color = OudsTheme.colorScheme.border.focus,
        insetWidth = OudsTheme.borders.width.focusInset,
        insetColor = OudsTheme.colorScheme.border.focusInset
    )
} else {
    this
}

internal object OudsRadioButton {
    enum class State {
        Enabled, Hovered, Pressed, Disabled, Focused
    }
}

@PreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsRadioButton(@PreviewParameter(OudsRadioButtonPreviewParameterProvider::class) parameter: OudsRadioButtonPreviewParameter) {
    PreviewOudsRadioButton(darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsRadioButton(
    darkThemeEnabled: Boolean,
    parameter: OudsRadioButtonPreviewParameter
) = OudsPreview(darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        StatesPreview<OudsRadioButton.State> { state ->
            OudsRadioButton(
                selected = selected,
                onClick = {},
                error = error,
                previewState = state
            )
        }
    }
}

internal data class OudsRadioButtonPreviewParameter(
    val selected: Boolean,
    val error: Boolean
)

internal class OudsRadioButtonPreviewParameterProvider : BasicPreviewParameterProvider<OudsRadioButtonPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsRadioButtonPreviewParameter>
    get() = listOf(
        OudsRadioButtonPreviewParameter(selected = false, error = false),
        OudsRadioButtonPreviewParameter(selected = false, error = true),
        OudsRadioButtonPreviewParameter(selected = true, error = false),
        OudsRadioButtonPreviewParameter(selected = true, error = true)
    )
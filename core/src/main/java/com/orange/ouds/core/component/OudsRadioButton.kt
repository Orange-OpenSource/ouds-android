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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.R
import com.orange.ouds.core.extensions.InteractionState
import com.orange.ouds.core.extensions.collectInteractionStateAsState
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.foundation.utilities.UiModePreviews

// TODO Add DSM URL for radio button when available
// <a href="https://unified-design-system.orange.com/472794e18/p/23f1c1-checkbox" class="external" target="_blank">OUDS Radio button design guidelines</a>

/**
 * An OUDS radio button.
 *
 * @param selected Controls selected state of the radio button.
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
    error: Boolean,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null
) {
    if (error && !enabled) throw IllegalStateException("An OudsRadioButton set to disabled with error parameter activated is not allowed.")

    val radioButtonTokens = OudsTheme.componentsTokens.radioButton
    val radioButtonInteractionSource = interactionSource ?: remember { MutableInteractionSource() }
    val interactionState by radioButtonInteractionSource.collectInteractionStateAsState()
    val state = previewState.orElse { rememberOudsRadioButtonState(enabled = enabled, interactionState = interactionState) }

    val selectableModifier = if (onClick != null) {
        Modifier.selectable(
            selected = selected,
            onClick = { onClick.invoke() },
            enabled = enabled,
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
        Box(
            modifier = Modifier
                .size(radioButtonTokens.sizeIndicator.value)
                .indicatorBorder(state = state, selected = selected, error = error)
        ) {
            if (selected) {
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(R.drawable.radiobutton_selected),
                    tint = selectedIndicatorColor(state = state, error = error),
                    contentDescription = null
                )
            }
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
        color = indicatorBorderColor(state = state, selected = selected, error = error),
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
private fun indicatorBorderColor(state: OudsRadioButton.State, selected: Boolean, error: Boolean): Color {
    return if (error) {
        when (state) {
            OudsRadioButton.State.Enabled -> OudsTheme.colorScheme.action.negative.enabled
            OudsRadioButton.State.Disabled -> Color.Unspecified // Not allowed, exception thrown at the beginning of OudsRadioButton
            OudsRadioButton.State.Hovered -> OudsTheme.colorScheme.action.negative.hover
            OudsRadioButton.State.Pressed -> OudsTheme.colorScheme.action.negative.pressed
            OudsRadioButton.State.Focused -> OudsTheme.colorScheme.action.negative.focus
        }
    } else {
        when (state) {
            OudsRadioButton.State.Enabled -> if (selected) OudsTheme.colorScheme.action.selected else OudsTheme.colorScheme.action.enabled
            OudsRadioButton.State.Disabled -> OudsTheme.colorScheme.action.disabled
            OudsRadioButton.State.Hovered -> OudsTheme.colorScheme.action.hover
            OudsRadioButton.State.Pressed -> OudsTheme.colorScheme.action.pressed
            OudsRadioButton.State.Focused -> OudsTheme.colorScheme.action.focus
        }
    }
}

@Composable
private fun selectedIndicatorColor(state: OudsRadioButton.State, error: Boolean): Color {
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
                OudsRadioButton.State.Enabled -> selected
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
    border(width = OudsTheme.borders.width.focusInset, color = OudsTheme.colorScheme.border.focus)
} else {
    this
}

internal object OudsRadioButton {
    enum class State {
        Enabled, Hovered, Pressed, Disabled, Focused
    }
}

@UiModePreviews.Default
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
        val columnCount = 2
        Box(modifier = Modifier.padding(16.dp)) {
            val chunkedStates = states.chunked(columnCount)
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                chunkedStates.forEach { states ->
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        states.forEach { state ->
                            OudsRadioButton(
                                selected = selected,
                                onClick = {},
                                interactionSource = remember { MutableInteractionSource() },
                                error = error,
                                previewState = state
                            )
                        }
                    }
                }
            }
        }
    }
}

internal data class OudsRadioButtonPreviewParameter(
    val selected: Boolean,
    val error: Boolean
) {
    val states: List<OudsRadioButton.State> = listOf(
        OudsRadioButton.State.Enabled,
        OudsRadioButton.State.Pressed,
        OudsRadioButton.State.Hovered,
        OudsRadioButton.State.Focused,
        OudsRadioButton.State.Disabled
    )
}

internal class OudsRadioButtonPreviewParameterProvider : BasicPreviewParameterProvider<OudsRadioButtonPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsRadioButtonPreviewParameter>
    get() = listOf(
        OudsRadioButtonPreviewParameter(selected = false, error = false),
        OudsRadioButtonPreviewParameter(selected = false, error = true),
        OudsRadioButtonPreviewParameter(selected = true, error = false),
        OudsRadioButtonPreviewParameter(selected = true, error = true)
    )
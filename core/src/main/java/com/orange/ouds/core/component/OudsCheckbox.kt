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

import androidx.compose.foundation.LocalIndication
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
import androidx.compose.foundation.selection.triStateToggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.state.ToggleableState
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

/**
 * <a href="https://unified-design-system.orange.com/472794e18/p/23f1c1-checkbox" class="external" target="_blank">OUDS Checkbox design guidelines</a>
 *
 * An OUDS checkbox.
 *
 * @param checked Controls checked state of the checkbox.
 * @param onCheckedChange Callback invoked on checkbox click. If `null`, then this is passive and relies entirely on a higher-level component to control
 * the checked state.
 * @param modifier [Modifier] applied to the layout of the checkbox.
 * @param enabled Controls the enabled state of the checkbox. When `false`, this checkbox will not be clickable.
 * @param error Controls the error state of the checkbox.
 * @param interactionSource Optional hoisted [MutableInteractionSource] for observing and emitting [Interaction]s for this checkbox. Note that if `null`
 * is provided, interactions will still happen internally.
 *
 * @sample com.orange.ouds.core.component.samples.OudsCheckboxSample
 */
@Composable
fun OudsCheckbox(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    error: Boolean = false,
    interactionSource: MutableInteractionSource? = null
) {
    OudsTriStateCheckbox(
        state = ToggleableState(checked),
        onClick = if (onCheckedChange != null) {
            { onCheckedChange(!checked) }
        } else null,
        modifier = modifier,
        enabled = enabled,
        error = error,
        interactionSource = interactionSource
    )
}

/**
 *
 * <a href="https://unified-design-system.orange.com/472794e18/p/23f1c1-checkbox" class="external" target="_blank">OUDS Checkbox design guidelines</a>
 *
 * An OUDS checkbox parent.
 * Checkboxes can have a parent-child relationship with other checkboxes. When the parent checkbox is checked, all child checkboxes are checked. If a
 * parent checkbox is unchecked, all child checkboxes are unchecked. If some, but not all, child checkboxes are checked, the parent checkbox
 * becomes an indeterminate checkbox.
 *
 * @param state Controls whether TriStateCheckbox is checked, unchecked or in indeterminate state.
 * @param onClick Callback invoked when checkbox is being clicked, therefore the change of [ToggleableState] state is requested. If null, then this is passive
 * and relies entirely on a higher-level component to control the state.
 * @param modifier [Modifier] applied to the layout of the checkbox.
 * @param enabled Controls the enabled state of the checkbox. When `false`, this checkbox will not be clickable.
 * @param error Controls the error state of the checkbox.
 * @param interactionSource Optional hoisted [MutableInteractionSource] for observing and emitting [Interaction]s for this checkbox. Note that if `null`
 * is provided, interactions will still happen internally.
 *
 * @sample com.orange.ouds.core.component.samples.OudsTriStateCheckboxSample
 */
@Composable
fun OudsTriStateCheckbox(
    state: ToggleableState,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    error: Boolean = false,
    interactionSource: MutableInteractionSource? = null
) {
    val checkboxInteractionSource = interactionSource ?: remember { MutableInteractionSource() }

    val toggleableModifier =
        if (onClick != null) {
            Modifier.triStateToggleable(
                interactionSource = checkboxInteractionSource,
                indication = LocalIndication.current,
                state = state,
                onClick = onClick,
                enabled = enabled,
                role = Role.Checkbox
            )
        } else {
            Modifier
        }

    OudsCheckbox(
        value = state,
        interactionSource = checkboxInteractionSource,
        modifier = modifier.then(toggleableModifier),
        previewState = null,
        enabled = enabled,
        error = error
    )
}

@Composable
private fun OudsCheckbox(
    value: ToggleableState,
    interactionSource: MutableInteractionSource,
    error: Boolean,
    previewState: OudsCheckbox.State?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    if (error && !enabled) throw IllegalStateException("An OudsCheckbox or OudsTriStateCheckbox set to disabled with error parameter activated is not allowed.")

    val interactionState by interactionSource.collectInteractionStateAsState()
    val context = LocalContext.current
    val checkboxTokens = OudsTheme.componentsTokens.checkbox
    val state = previewState.orElse { rememberOudsCheckboxState(enabled = enabled, interactionState = interactionState) }

    Box(
        modifier = modifier
            .widthIn(checkboxTokens.sizeMinWidth.dp)
            .heightIn(min = checkboxTokens.sizeMinHeight.dp, max = checkboxTokens.sizeMaxHeight.dp)
            .background(color = backgroundColor(state = state))
            .border(state = state)
            .semantics {
                stateDescription = when (value) {
                    ToggleableState.Off -> context.getString(R.string.core_checkbox_unchecked_a11y)
                    ToggleableState.On -> context.getString(R.string.core_checkbox_checked_a11y)
                    ToggleableState.Indeterminate -> context.getString(R.string.core_checkbox_indeterminate_a11y)
                }
            },
        contentAlignment = Alignment.Center,
    ) {
        OudsCheckboxIndicator(state = state, value = value, error = error)
    }
}

@Composable
internal fun OudsCheckboxIndicator(
    state: OudsCheckbox.State,
    value: ToggleableState,
    error: Boolean
) {
    val checkboxTokens = OudsTheme.componentsTokens.checkbox
    val selected = value != ToggleableState.Off
    val shape = RoundedCornerShape(checkboxTokens.borderRadius.value)

    Box(
        modifier = Modifier
            .size(checkboxTokens.sizeIndicator.value)
            .selectorBorder(state = state, selected = selected, error = error, shape = shape)
    ) {
        val selectorResource = when (value) {
            ToggleableState.On -> R.drawable.ic_tick
            ToggleableState.Off -> null
            ToggleableState.Indeterminate -> R.drawable.ic_less
        }

            selectorResource?.let { resource ->
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(resource),
                    tint = tickColor(state = state, error = error),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
private fun rememberOudsCheckboxState(
    enabled: Boolean,
    interactionState: InteractionState
): OudsCheckbox.State = remember(enabled, interactionState) {
    when {
        !enabled -> OudsCheckbox.State.Disabled
        interactionState == InteractionState.Hovered -> OudsCheckbox.State.Hovered
        interactionState == InteractionState.Pressed -> OudsCheckbox.State.Pressed
        interactionState == InteractionState.Focused -> OudsCheckbox.State.Focused
        else -> OudsCheckbox.State.Enabled
    }
}

@Composable
private fun Modifier.selectorBorder(state: OudsCheckbox.State, selected: Boolean, error: Boolean, shape: Shape): Modifier {
    val selectorBorderWidth = selectorBorderWidth(state = state, selected = selected)
    return border(
        width = selectorBorderWidth,
        color = selectorBorderColor(state = state, selected = selected, error = error),
        shape = shape
    )
}

@Composable
private fun selectorBorderWidth(state: OudsCheckbox.State, selected: Boolean): Dp {
    return with(OudsTheme.componentsTokens.checkbox) {
        when (state) {
            OudsCheckbox.State.Enabled, OudsCheckbox.State.Disabled -> if (selected) borderWidthSelected else borderWidthUnselected
            OudsCheckbox.State.Hovered -> if (selected) borderWidthSelectedHover else borderWidthUnselectedHover
            OudsCheckbox.State.Pressed -> if (selected) borderWidthSelectedPressed else borderWidthUnselectedPressed
            OudsCheckbox.State.Focused -> if (selected) borderWidthSelectedFocus else borderWidthUnselectedFocus
        }.value
    }
}

@Composable
private fun selectorBorderColor(state: OudsCheckbox.State, selected: Boolean, error: Boolean): Color {
    return if (error) {
        when (state) {
            OudsCheckbox.State.Enabled -> OudsTheme.colorScheme.action.negative.enabled
            OudsCheckbox.State.Disabled -> Color.Unspecified // Not allowed, exception thrown at the beginning of OudsCheckbox
            OudsCheckbox.State.Hovered -> OudsTheme.colorScheme.action.negative.hover
            OudsCheckbox.State.Pressed -> OudsTheme.colorScheme.action.negative.pressed
            OudsCheckbox.State.Focused -> OudsTheme.colorScheme.action.negative.focus
        }
    } else {
        when (state) {
            OudsCheckbox.State.Enabled -> if (selected) OudsTheme.colorScheme.action.selected else OudsTheme.colorScheme.action.enabled
            OudsCheckbox.State.Disabled -> OudsTheme.colorScheme.action.disabled
            OudsCheckbox.State.Hovered -> OudsTheme.colorScheme.action.hover
            OudsCheckbox.State.Pressed -> OudsTheme.colorScheme.action.pressed
            OudsCheckbox.State.Focused -> OudsTheme.colorScheme.action.focus
        }
    }
}

@Composable
private fun tickColor(state: OudsCheckbox.State, error: Boolean): Color {
    return with(OudsTheme.colorScheme.action) {
        if (error) {
            when (state) {
                OudsCheckbox.State.Enabled -> negative.enabled
                OudsCheckbox.State.Disabled -> Color.Unspecified // Not allowed, exception thrown at the beginning of OudsCheckbox
                OudsCheckbox.State.Hovered -> negative.hover
                OudsCheckbox.State.Pressed -> negative.pressed
                OudsCheckbox.State.Focused -> negative.focus
            }
        } else {
            when (state) {
                OudsCheckbox.State.Enabled -> selected
                OudsCheckbox.State.Disabled -> disabled
                OudsCheckbox.State.Hovered -> hover
                OudsCheckbox.State.Pressed -> pressed
                OudsCheckbox.State.Focused -> focus
            }
        }
    }
}

@Composable
private fun backgroundColor(state: OudsCheckbox.State): Color {
    return with(OudsTheme.componentsTokens.controlItem) {
        when (state) {
            OudsCheckbox.State.Enabled, OudsCheckbox.State.Disabled -> Color.Transparent
            OudsCheckbox.State.Hovered -> colorBgHover.value
            OudsCheckbox.State.Pressed -> colorBgPressed.value
            OudsCheckbox.State.Focused -> colorBgFocus.value
        }
    }
}

@Composable
private fun Modifier.border(state: OudsCheckbox.State) = if (state == OudsCheckbox.State.Focused) {
    border(width = OudsTheme.borders.width.focusInset, color = OudsTheme.colorScheme.border.focus)
} else {
    this
}

internal object OudsCheckbox {
    enum class State {
        Enabled, Hovered, Pressed, Disabled, Focused
    }
}

@UiModePreviews.Default
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsCheckbox(@PreviewParameter(OudsCheckboxPreviewParameterProvider::class) parameter: OudsCheckboxPreviewParameter) {
    PreviewOudsCheckbox(darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsCheckbox(
    darkThemeEnabled: Boolean,
    parameter: OudsCheckboxPreviewParameter
) = OudsPreview(darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        val columnCount = 2
        Box(modifier = Modifier.padding(16.dp)) {
            val chunkedStates = states.chunked(columnCount)
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                chunkedStates.forEach { states ->
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        states.forEach { state ->
                            OudsCheckbox(
                                value = toggleableState,
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

internal data class OudsCheckboxPreviewParameter(
    val toggleableState: ToggleableState,
    val error: Boolean
) {
    val states: List<OudsCheckbox.State> = listOf(
        OudsCheckbox.State.Enabled,
        OudsCheckbox.State.Pressed,
        OudsCheckbox.State.Hovered,
        OudsCheckbox.State.Focused,
        OudsCheckbox.State.Disabled
    )
}

internal class OudsCheckboxPreviewParameterProvider : BasicPreviewParameterProvider<OudsCheckboxPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsCheckboxPreviewParameter>
    get() = buildList {
        ToggleableState.entries.forEach { toggleableState ->
            val parameters = listOf(
                OudsCheckboxPreviewParameter(toggleableState = toggleableState, error = false),
                OudsCheckboxPreviewParameter(toggleableState = toggleableState, error = true)
            )
            addAll(parameters)
        }
    }
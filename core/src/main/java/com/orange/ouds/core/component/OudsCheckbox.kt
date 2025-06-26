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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import com.orange.ouds.core.R
import com.orange.ouds.core.component.common.outerBorder
import com.orange.ouds.core.extensions.collectInteractionStateAsState
import com.orange.ouds.core.theme.LocalHighContrastModeEnabled
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.CheckedContent
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.PreviewEnumEntries
import com.orange.ouds.core.utilities.getPreviewEnumEntry
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider

/**
 * <a href="https://unified-design-system.orange.com/472794e18/p/23f1c1-checkbox" class="external" target="_blank">**OUDS Checkbox design guidelines**</a>
 *
 * Checkboxes are input controls that allow users to select one or more options from a number of choices.
 *
 * The **standalone checkbox variant** is used when the checkbox input is nested within another component and an alternative label is provided. For example,
 * a checkbox can be used in a data table where its purpose is clear from its position or its connection to other items in the same row or column.
 *
 * @see [OudsTriStateCheckbox] If you require support for an indeterminate state.
 * @see [OudsCheckboxItem] If you want to use a checkbox with an associated label and other optional elements.
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
 * <a href="https://unified-design-system.orange.com/472794e18/p/23f1c1-checkbox" class="external" target="_blank">**OUDS Checkbox design guidelines**</a>
 *
 * Checkboxes are input controls that allow users to select one or more options from a number of choices.
 *
 * This checkbox supports the indeterminate state: Checkboxes can have a parent-child relationship with other checkboxes. When the parent checkbox is checked,
 * all child checkboxes are checked. If a parent checkbox is unchecked, all child checkboxes are unchecked. If some, but not all, child checkboxes are checked,
 * the parent checkbox becomes an indeterminate checkbox.
 *
 * The **indeterminate standalone checkbox variant** allows to manage a checkbox with an indeterminate state that can be used when the checkbox input is nested
 * within another component and an alternative label is provided.
 *
 * @see [OudsCheckbox] If you need a simple component that represents [Boolean] state.
 * @see [OudsTriStateCheckboxItem] If you want to use an indeterminate checkbox with an associated label and other optional elements.
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
    val isDisabledPreviewState = getPreviewEnumEntry<OudsControl.State>() == OudsControl.State.Disabled
    val isForbidden = error && (!enabled || isDisabledPreviewState)
    CheckedContent(
        expression = !isForbidden,
        exceptionMessage = { "An OudsCheckbox or OudsTriStateCheckbox set to disabled with error parameter activated is not allowed." }
    ) {
        @Suppress("NAME_SHADOWING") val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
        val interactionState by interactionSource.collectInteractionStateAsState()
        val checkboxTokens = OudsTheme.componentsTokens.checkbox
        val checkboxState = getControlState(enabled = enabled, interactionState = interactionState)
        val backgroundColor = rememberInteractionColor(interactionState = interactionState) { checkboxInteractionState ->
            val controlState = getControlState(enabled = enabled, interactionState = checkboxInteractionState)
            backgroundColor(state = controlState)
        }

        val toggleableModifier =
            if (onClick != null) {
                Modifier.triStateToggleable(
                    interactionSource = interactionSource,
                    indication = InteractionValuesIndication(backgroundColor),
                    state = state,
                    onClick = onClick,
                    enabled = enabled,
                    role = Role.Checkbox
                )
            } else {
                Modifier
            }

        Box(
            modifier = modifier
                .then(toggleableModifier)
                .widthIn(checkboxTokens.sizeMinWidth.value)
                .heightIn(min = checkboxTokens.sizeMinHeight.value, max = checkboxTokens.sizeMaxHeight.value)
                .background(color = backgroundColor.value)
                .outerBorder(state = checkboxState, handleHighContrastMode = true),
            contentAlignment = Alignment.Center,
        ) {
            OudsCheckboxIndicator(state = checkboxState, value = state, error = error)
        }
    }
}

@Composable
internal fun OudsCheckboxIndicator(
    state: OudsControl.State,
    value: ToggleableState,
    error: Boolean
) {
    val checkboxTokens = OudsTheme.componentsTokens.checkbox
    val selected = value != ToggleableState.Off
    val shape = RoundedCornerShape(checkboxTokens.borderRadius.value)

    Box(
        modifier = Modifier
            .size(checkboxTokens.sizeIndicator.value)
            .indicatorBorder(state = state, selected = selected, error = error, shape = shape)
    ) {
        val indicatorResource = when (value) {
            ToggleableState.On -> R.drawable.checkbox_selected
            ToggleableState.Off -> null
            ToggleableState.Indeterminate -> R.drawable.checkbox_indeterminate
        }

        indicatorResource?.let { resource ->
            Icon(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(resource),
                tint = indicatorColor(state = state, selected = true, error = error),
                contentDescription = null
            )
        }
    }
}

@Composable
private fun Modifier.indicatorBorder(state: OudsControl.State, selected: Boolean, error: Boolean, shape: Shape): Modifier {
    val selectorBorderWidth = indicatorBorderWidth(state = state, selected = selected)
    return border(
        width = selectorBorderWidth,
        color = indicatorColor(state = state, selected = selected, error = error),
        shape = shape
    )
}

@Composable
private fun indicatorBorderWidth(state: OudsControl.State, selected: Boolean): Dp {
    return with(OudsTheme.componentsTokens.checkbox) {
        when (state) {
            OudsControl.State.Enabled, OudsControl.State.Disabled -> if (selected) borderWidthSelected else borderWidthUnselected
            OudsControl.State.Hovered -> if (selected) borderWidthSelectedHover else borderWidthUnselectedHover
            OudsControl.State.Pressed -> if (selected) borderWidthSelectedPressed else borderWidthUnselectedPressed
            OudsControl.State.Focused -> if (selected) borderWidthSelectedFocus else borderWidthUnselectedFocus
        }.value
    }
}

@Composable
private fun indicatorColor(state: OudsControl.State, selected: Boolean, error: Boolean): Color {
    return with(OudsTheme.colorScheme.action) {
        if (error) {
            when (state) {
                OudsControl.State.Enabled -> negative.enabled
                OudsControl.State.Disabled -> Color.Unspecified // Not allowed, exception thrown at the beginning of OudsCheckbox
                OudsControl.State.Hovered -> negative.hover
                OudsControl.State.Pressed -> negative.pressed
                OudsControl.State.Focused -> negative.focus
            }
        } else {
            when (state) {
                OudsControl.State.Enabled -> if (selected) {
                    // In order to reach the a11y AAA level, when high contrast mode is enabled, the selected checkbox must use `color.content.default` token
                    if (LocalHighContrastModeEnabled.current) OudsTheme.colorScheme.content.default else this.selected
                } else {
                    enabled
                }
                OudsControl.State.Disabled -> disabled
                OudsControl.State.Hovered -> hover
                OudsControl.State.Pressed -> pressed
                OudsControl.State.Focused -> focus
            }
        }
    }
}

@Composable
private fun backgroundColor(state: OudsControl.State): Color {
    return with(OudsTheme.componentsTokens.controlItem) {
        when (state) {
            OudsControl.State.Enabled, OudsControl.State.Disabled -> Color.Transparent
            OudsControl.State.Hovered -> colorBgHover.value
            OudsControl.State.Pressed -> colorBgPressed.value
            OudsControl.State.Focused -> colorBgFocus.value
        }
    }
}

@PreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsCheckbox(@PreviewParameter(OudsCheckboxPreviewParameterProvider::class) parameter: OudsCheckboxPreviewParameter) {
    PreviewOudsCheckbox(darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@PreviewLightDark
@Composable
internal fun PreviewOudsCheckboxHighContrastModeEnabled(@PreviewParameter(OudsCheckboxPreviewParameterProvider::class) parameter: OudsCheckboxPreviewParameter) {
    PreviewOudsCheckbox(darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter, highContrastModeEnabled = true)
}

@Composable
internal fun PreviewOudsCheckbox(
    darkThemeEnabled: Boolean,
    parameter: OudsCheckboxPreviewParameter,
    highContrastModeEnabled: Boolean = false
) = OudsPreview(darkThemeEnabled = darkThemeEnabled, highContrastModeEnabled = highContrastModeEnabled) {
    with(parameter) {
        PreviewEnumEntries<OudsControl.State> {
            OudsTriStateCheckbox(
                state = toggleableState,
                onClick = null,
                interactionSource = remember { MutableInteractionSource() },
                error = error
            )
        }
    }
}

internal data class OudsCheckboxPreviewParameter(
    val toggleableState: ToggleableState,
    val error: Boolean
)

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
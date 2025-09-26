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
import androidx.compose.ui.semantics.error
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import com.orange.ouds.core.component.common.OudsError
import com.orange.ouds.core.component.common.outerBorder
import com.orange.ouds.core.extensions.collectInteractionStateAsState
import com.orange.ouds.core.theme.LocalHighContrastModeEnabled
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.CheckedContent
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.PreviewEnumEntries
import com.orange.ouds.core.utilities.getPreviewEnumEntry
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider

/**
 * Radio buttons are input controls that allow users to select a single option from a set of mutually exclusive choices.
 *
 * The **standalone radio button variant** can be used when the radio selector control is nested within another component and an alternative label is provided.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://unified-design-system.orange.com/472794e18/p/90c467-radio-button)
 *
 * > Design version: 1.2.0
 *
 * @see [OudsRadioButtonItem] If you want to use a radio button with an associated label and other optional elements.
 *
 * @param selected Controls the selected state of the radio button.
 * @param onClick Callback invoked on radio button click. If `null`, then this radio button will not be interactable, unless something else handles its
 * input events and updates its state.
 * @param modifier [Modifier] applied to the layout of the radio button.
 * @param enabled Controls the enabled state of the radio button. When `false`, this radio button will not be clickable.
 * @param error Optional [OudsError] to provide in the case of the radio button should appear in error state, `null` otherwise.
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
    error: OudsError? = null,
    interactionSource: MutableInteractionSource? = null
) {
    val isDisabledPreviewState = getPreviewEnumEntry<OudsControl.State>() == OudsControl.State.Disabled
    val isForbidden = error != null && (!enabled || isDisabledPreviewState)
    CheckedContent(
        expression = !isForbidden,
        exceptionMessage = { "An OudsRadioButton set to disabled with error parameter activated is not allowed." }
    ) {
        val radioButtonTokens = OudsTheme.componentsTokens.radioButton
        @Suppress("NAME_SHADOWING") val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
        val interactionState by interactionSource.collectInteractionStateAsState()
        val state = getControlState(enabled = enabled, interactionState = interactionState)
        val backgroundColor = rememberInteractionColor(interactionState = interactionState) { radioButtonInteractionState ->
            val radioButtonState = getControlState(enabled = enabled, interactionState = radioButtonInteractionState)
            backgroundColor(state = radioButtonState)
        }

        val selectableModifier = if (onClick != null) {
            Modifier.selectable(
                selected = selected,
                onClick = onClick,
                enabled = enabled,
                interactionSource = interactionSource,
                indication = InteractionValuesIndication(backgroundColor),
                role = Role.RadioButton,
            )
        } else Modifier

        Box(
            modifier = modifier
                .widthIn(radioButtonTokens.sizeMinWidth.value)
                .heightIn(min = radioButtonTokens.sizeMinHeight.value, max = radioButtonTokens.sizeMaxHeight.value)
                .background(color = backgroundColor.value)
                .outerBorder(state = state, handleHighContrastMode = true)
                .then(selectableModifier)
                .run {
                    error?.description?.let { description ->
                        semantics {
                            error(description)
                        }
                    }.orElse {
                        this
                    }
                },
            contentAlignment = Alignment.Center,
        ) {
            OudsRadioButtonIndicator(state = state, selected = selected, error = error != null)
        }
    }
}

@Composable
internal fun OudsRadioButtonIndicator(state: OudsControl.State, selected: Boolean, error: Boolean) {
    val radioButtonTokens = OudsTheme.componentsTokens.radioButton
    Box(
        modifier = Modifier
            .size(radioButtonTokens.sizeIndicator.value)
            .indicatorBorder(state = state, selected = selected, error = error)
    ) {
        if (selected) {
            Icon(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(OudsTheme.drawableResources.radioButtonSelected),
                tint = indicatorColor(state = state, selected = true, error = error),
                contentDescription = null
            )
        }
    }
}

@Composable
private fun Modifier.indicatorBorder(state: OudsControl.State, selected: Boolean, error: Boolean): Modifier {
    val indicatorBorderWidth = indicatorBorderWidth(state = state, selected = selected)
    return border(
        width = indicatorBorderWidth,
        color = indicatorColor(state = state, selected = selected, error = error),
        shape = RoundedCornerShape(OudsTheme.componentsTokens.radioButton.borderRadius.value)
    )
}

@Composable
private fun indicatorBorderWidth(state: OudsControl.State, selected: Boolean): Dp {
    return with(OudsTheme.componentsTokens.radioButton) {
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
                OudsControl.State.Disabled -> Color.Unspecified // Not allowed, exception thrown at the beginning of OudsRadioButton
                OudsControl.State.Hovered -> negative.hover
                OudsControl.State.Pressed -> negative.pressed
                OudsControl.State.Focused -> negative.focus
            }
        } else {
            when (state) {
                OudsControl.State.Enabled -> if (selected) {
                    // In order to reach the a11y AAA level, when high contrast mode is enabled, the selected radio button must use `color.content.default` token
                    if (LocalHighContrastModeEnabled.current) OudsTheme.colorScheme.content.default else this.selected
                } else {
                    OudsTheme.colorScheme.border.emphasized
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
private fun PreviewOudsRadioButton(@PreviewParameter(OudsRadioButtonPreviewParameterProvider::class) parameter: OudsRadioButtonPreviewParameter) {
    PreviewOudsRadioButton(darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@PreviewLightDark
@Composable
internal fun PreviewOudsRadioButtonHighContrastModeEnabled(@PreviewParameter(OudsRadioButtonPreviewParameterProvider::class) parameter: OudsRadioButtonPreviewParameter) {
    PreviewOudsRadioButton(darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter, highContrastModeEnabled = true)
}

@Composable
internal fun PreviewOudsRadioButton(
    darkThemeEnabled: Boolean,
    parameter: OudsRadioButtonPreviewParameter,
    highContrastModeEnabled: Boolean = false
) = OudsPreview(darkThemeEnabled = darkThemeEnabled, highContrastModeEnabled = highContrastModeEnabled) {
    with(parameter) {
        PreviewEnumEntries<OudsControl.State> {
            OudsRadioButton(
                selected = selected,
                onClick = {},
                error = error
            )
        }
    }
}

internal data class OudsRadioButtonPreviewParameter(
    val selected: Boolean,
    val error: OudsError? = null
)

internal class OudsRadioButtonPreviewParameterProvider : BasicPreviewParameterProvider<OudsRadioButtonPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsRadioButtonPreviewParameter>
    get() = listOf(
        OudsRadioButtonPreviewParameter(selected = false),
        OudsRadioButtonPreviewParameter(selected = false, error = OudsError("")),
        OudsRadioButtonPreviewParameter(selected = true),
        OudsRadioButtonPreviewParameter(selected = true, error = OudsError(""))
    )
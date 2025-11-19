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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.error
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import com.orange.ouds.core.component.common.OudsError
import com.orange.ouds.core.component.common.outerBorder
import com.orange.ouds.core.extensions.collectInteractionStateAsState
import com.orange.ouds.core.theme.LocalHighContrastModeEnabled
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.takeUnlessHairline
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.CheckedContent
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.OudsPreviewableComponent
import com.orange.ouds.core.utilities.PreviewEnumEntries
import com.orange.ouds.core.utilities.getPreviewEnumEntry
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.theme.OudsThemeContract

/**
 * Radio buttons are input controls that allow users to select a single option from a set of mutually exclusive choices.
 *
 * The **standalone radio button variant** can be used when the radio selector control is nested within another component and an alternative label is provided.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://unified-design-system.orange.com/472794e18/p/90c467-radio-button)
 *
 * > Design version: 1.4.0
 *
 * @see [OudsRadioButtonItem] If you want to use a radio button with an associated label and other optional elements.
 *
 * @param selected Controls the selected state of the radio button.
 * @param onClick Callback invoked on radio button click. If `null`, then this radio button will not be interactable, unless something else handles its
 * input events and updates its state.
 * @param modifier [Modifier] applied to the layout of the radio button.
 * @param enabled Controls the enabled state of the radio button. When `false`, this radio button will not be clickable.
 * @param readOnly Controls the read only state of the radio button. When `true`, this radio button is displayed in a specific state (selected or unselected)
 * but the user cannot modify it. Note that if it is set to `true` and [enabled] is set to `false`, the radio button will be displayed in disabled state.
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
    readOnly: Boolean = false,
    error: OudsError? = null,
    interactionSource: MutableInteractionSource? = null
) {
    val previewState = getPreviewEnumEntry<OudsControlState>()
    val isReadOnlyPreviewState = previewState == OudsControlState.ReadOnly
    val isDisabledPreviewState = previewState == OudsControlState.Disabled
    val isForbidden = error != null && (readOnly || !enabled || isReadOnlyPreviewState || isDisabledPreviewState)
    val shape = RoundedCornerShape(OudsTheme.componentsTokens.controlItem.borderRadiusItemOnly.value)
    CheckedContent(
        expression = !isForbidden,
        exceptionMessage = {
            val parameter = if (readOnly) "readOnly" else "disabled"
            "An OudsRadioButton set to $parameter with error parameter activated is not allowed."
        },
        previewDashedBorderShape = shape,
        previewDashedBorderPhase = OudsTheme.componentsTokens.controlItem.borderRadiusItemOnly.value
    ) {
        val radioButtonTokens = OudsTheme.componentsTokens.radioButton
        @Suppress("NAME_SHADOWING") val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
        val interactionState by interactionSource.collectInteractionStateAsState()
        val state = getControlState(enabled = enabled, readOnly = readOnly, interactionState = interactionState)
        val backgroundColor = rememberInteractionColor(interactionState = interactionState) { radioButtonInteractionState ->
            val radioButtonState = getControlState(enabled = enabled, readOnly = readOnly, interactionState = radioButtonInteractionState)
            backgroundColor(state = radioButtonState)
        }

        val selectableModifier = if (onClick != null) {
            Modifier.selectable(
                selected = selected,
                onClick = onClick,
                enabled = enabled && !readOnly,
                interactionSource = interactionSource,
                indication = InteractionValuesIndication(backgroundColor),
                role = Role.RadioButton,
            )
        } else Modifier

        Box(
            modifier = modifier
                .widthIn(radioButtonTokens.sizeMinWidth.value)
                .heightIn(min = radioButtonTokens.sizeMinHeight.value, max = radioButtonTokens.sizeMaxHeight.value)
                .background(color = backgroundColor.value, shape = shape)
                .outerBorder(state = state, shape = shape, handleHighContrastMode = true)
                .then(selectableModifier)
                .run {
                    error?.message?.let { description ->
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
internal fun OudsRadioButtonIndicator(state: OudsControlState, selected: Boolean, error: Boolean) {
    val radioButtonTokens = OudsTheme.componentsTokens.radioButton
    val shape = RoundedCornerShape(OudsTheme.componentsTokens.radioButton.borderRadius.value)

    Box(
        modifier = Modifier
            .size(radioButtonTokens.sizeIndicator.value)
            .clip(shape)
            .indicatorBorder(state = state, selected = selected, error = error, shape = shape)
    ) {
        if (selected) {
            Icon(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(OudsTheme.drawableResources.radioButtonSelected),
                tint = selectionColor(state = state, error = error),
                contentDescription = null
            )
        }
    }
}

@Composable
private fun Modifier.indicatorBorder(state: OudsControlState, selected: Boolean, error: Boolean, shape: Shape): Modifier {
    return indicatorBorderWidth(state = state, selected = selected)?.let { indicatorBorderWidth ->
        border(
            width = indicatorBorderWidth,
            color = indicatorColor(state = state, selected = selected, error = error),
            shape = shape
        )
    }.orElse {
        this
    }
}

@Composable
private fun indicatorBorderWidth(state: OudsControlState, selected: Boolean): Dp? {
    return with(OudsTheme.componentsTokens.radioButton) {
        when (state) {
            OudsControlState.Enabled, OudsControlState.Disabled, OudsControlState.ReadOnly -> if (selected) borderWidthSelected else borderWidthUnselected
            OudsControlState.Hovered -> if (selected) borderWidthSelectedHover else borderWidthUnselectedHover
            OudsControlState.Pressed -> if (selected) borderWidthSelectedPressed else borderWidthUnselectedPressed
            OudsControlState.Focused -> if (selected) borderWidthSelectedFocus else borderWidthUnselectedFocus
        }.value
    }.takeUnlessHairline
}

@Composable
private fun indicatorColor(state: OudsControlState, selected: Boolean, error: Boolean): Color {
    return with(OudsTheme.colorScheme.action) {
        if (error) {
            state.errorColor()
        } else {
            when (state) {
                OudsControlState.Enabled -> if (selected) {
                    // In order to reach the a11y AAA level, when high contrast mode is enabled, the selected radio button must use `color.content.default` token
                    if (LocalHighContrastModeEnabled.current) OudsTheme.colorScheme.content.default else this.selected
                } else {
                    OudsTheme.colorScheme.border.emphasized
                }
                OudsControlState.Disabled -> disabled
                OudsControlState.ReadOnly -> readOnly.secondary
                OudsControlState.Hovered -> hover
                OudsControlState.Pressed -> pressed
                OudsControlState.Focused -> focus
            }
        }
    }
}

@Composable
private fun selectionColor(state: OudsControlState, error: Boolean): Color {
    return if (state == OudsControlState.ReadOnly && !error) {
        OudsTheme.colorScheme.action.readOnly.primary
    } else {
        indicatorColor(state = state, selected = true, error = error)
    }
}

@Composable
private fun backgroundColor(state: OudsControlState): Color {
    return with(OudsTheme.componentsTokens.controlItem) {
        when (state) {
            OudsControlState.Enabled, OudsControlState.Disabled, OudsControlState.ReadOnly -> Color.Transparent
            OudsControlState.Hovered -> colorBgHover.value
            OudsControlState.Pressed -> colorBgPressed.value
            OudsControlState.Focused -> colorBgFocus.value
        }
    }
}

@Preview(name = "Light", widthDp = OudsPreviewableComponent.RadioButton.PreviewWidthDp)
@Preview(name = "Dark", uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL, widthDp = OudsPreviewableComponent.RadioButton.PreviewWidthDp)
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsRadioButton(@PreviewParameter(OudsRadioButtonPreviewParameterProvider::class) parameter: OudsRadioButtonPreviewParameter) {
    PreviewOudsRadioButton(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Preview(name = "Light", widthDp = OudsPreviewableComponent.RadioButton.PreviewWidthDp)
@Preview(name = "Dark", uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL, widthDp = OudsPreviewableComponent.RadioButton.PreviewWidthDp)
@Composable
internal fun PreviewOudsRadioButtonHighContrastModeEnabled() {
    PreviewOudsRadioButton(
        theme = getPreviewTheme(),
        darkThemeEnabled = isSystemInDarkTheme(),
        parameter = OudsRadioButtonPreviewParameter(selected = true),
        highContrastModeEnabled = true
    )
}

@Composable
internal fun PreviewOudsRadioButton(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsRadioButtonPreviewParameter,
    highContrastModeEnabled: Boolean = false
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled, highContrastModeEnabled = highContrastModeEnabled) {
    with(parameter) {
        PreviewEnumEntries<OudsControlState> {
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
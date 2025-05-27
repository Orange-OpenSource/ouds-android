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
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ouds.core.extensions.collectInteractionStateAsState
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.LoremIpsumText
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.PreviewStates

/**
 * <a href="https://unified-design-system.orange.com/472794e18/p/90c467-radio-button" class="external" target="_blank">**OUDS Radio button design guidelines**</a>
 *
 * Radio buttons are input controls that allow users to select a single option from a set of mutually exclusive choices.
 *
 * The **radio button item variant** can function as a simple input with a label in a selection group, or it can be combined with optional elements such as
 * additional label, helper text, a divider, or an icon, allowing it to suit various use cases.
 *
 * The OUDS radio button item layout contains an [OudsRadioButton]. By clicking on the radio button item, the user changes the selected state of its radio button.
 *
 * In most cases, OUDS radio button items span the entire width of the screen. Thus an horizontal padding of `OudsTheme.grids.margin` is applied to the content.
 * This behaviour can be disabled by calling [com.orange.ouds.core.utilities.edgeToEdgePadding] modifier with `enabled` parameter set to `false`.
 *
 * @see [OudsRadioButton] If you want to use a standalone radio button.
 *
 * @param selected Controls selected state of the radio button.
 * @param label The main label of the radio button item.
 * @param onClick Callback invoked on radio button click. If `null`, then this radio button will not be interactable, unless something else handles its
 * input events and updates its state.
 * @param modifier [Modifier] applied to the layout of the radio button item.
 * @param additionalLabel Optional strong accompanying label for the main label. It is displayed between the [label] and the [helperText].
 * @param helperText Optional text displayed below the [label] and the [additionalLabel].
 * @param icon Optional icon displayed in the item. By default, it has a trailing position. If [reversed] is set to `true`, it is displayed as a leading element.
 * @param divider Controls the display of a divider at the bottom of the radio button item.
 * @param outlined When set to `true`, the radio button item, if selected, is outlined to stand out and draw the user's attention.
 * @param reversed When `false`, the radio button has a leading position and the optional [icon] has a trailing position. Otherwise, it is reversed.
 * @param enabled Controls the enabled state of the radio button item. When `false`, the radio button, the texts and the optional icon are disabled, and the item
 * will not be clickable.
 * @param readOnly Controls the read only state of the radio button item. When `true` the item's radio button is disabled but the texts and the icon remain in
 * enabled color. Note that if it is set to `true` and [enabled] is set to `false`, the radio button item will be displayed in disabled state.
 * @param error Controls the error state of the radio button item.
 * @param interactionSource Optional hoisted [MutableInteractionSource] for observing and emitting [Interaction]s for the item's radio button. Note that if `null`
 * is provided, interactions will still happen internally.
 *
 * @sample com.orange.ouds.core.component.samples.OudsRadioButtonItemSample
 */
@Composable
fun OudsRadioButtonItem(
    selected: Boolean,
    label: String,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    additionalLabel: String? = null,
    helperText: String? = null,
    icon: OudsControlItem.Icon? = null,
    divider: Boolean = true,
    outlined: Boolean = false,
    reversed: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    error: Boolean = false,
    interactionSource: MutableInteractionSource? = null
) {
    @Suppress("NAME_SHADOWING") val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    val interactionState by interactionSource.collectInteractionStateAsState()
    val state = getControlItemState(enabled = enabled, readOnly = readOnly, interactionState = interactionState)
    val backgroundColor = rememberControlItemBackgroundColor(enabled = enabled, readOnly = readOnly, interactionState = interactionState)

    val selectableModifier = if (onClick != null) {
        Modifier.selectable(
            selected = selected,
            onClick = onClick,
            enabled = enabled && !readOnly,
            interactionSource = interactionSource,
            indication = InteractionValuesIndication(backgroundColor),
            role = Role.RadioButton,
        )
    } else {
        Modifier
    }

    OudsControlItem(
        state = state,
        label = label,
        additionalLabel = additionalLabel,
        helperText = helperText,
        icon = icon,
        divider = if (outlined && outlineBorderColor(state = state, selected = selected, error = error) != null) false else divider,
        enabled = enabled,
        readOnly = readOnly,
        error = error,
        errorComponentName = "OudsRadioButtonItem",
        indicator = {
            OudsRadioButtonIndicator(
                state = state.toControlState(),
                selected = selected,
                error = error
            )
        },
        indicatorPosition = if (reversed) OudsControlItem.IndicatorPosition.End else OudsControlItem.IndicatorPosition.Start,
        checkedContentPreviewStatus = if (selected) "Selected" else "Unselected",
        modifier = modifier
            .then(selectableModifier)
            .background(color = backgroundColor.value)
            .border(outlined = outlined, selected = selected, error = error, state = state)
            .semantics(mergeDescendants = true) {},
        handleHighContrastMode = true
    )
}

@Composable
private fun Modifier.border(outlined: Boolean, selected: Boolean, error: Boolean, state: OudsControlItem.State): Modifier {
    val borderColor = outlineBorderColor(state, selected, error)

    return if (outlined && borderColor != null) {
        border(width = OudsTheme.borders.width.default, color = borderColor)
    } else {
        this
    }
}

@Composable
private fun outlineBorderColor(state: OudsControlItem.State, selected: Boolean, error: Boolean): Color? {
    return if (error) {
        with(OudsTheme.colorScheme.action.negative) {
            when (state) {
                OudsControlItem.State.Enabled -> if (selected) enabled else null
                OudsControlItem.State.Hovered -> hover
                OudsControlItem.State.Pressed -> pressed
                OudsControlItem.State.Focused -> null
                OudsControlItem.State.Disabled, OudsControlItem.State.ReadOnly -> Color.Unspecified // Not allowed, exception thrown at the beginning of each control item
            }
        }
    } else {
        with(OudsTheme.colorScheme.action) {
            when (state) {
                OudsControlItem.State.Enabled -> if (selected) this.selected else null
                OudsControlItem.State.Hovered -> hover
                OudsControlItem.State.Pressed -> pressed
                OudsControlItem.State.Focused -> null
                OudsControlItem.State.Disabled, OudsControlItem.State.ReadOnly -> if (selected) disabled else null
            }
        }
    }
}

@PreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsRadioButtonItem(@PreviewParameter(OudsRadioButtonItemPreviewParameterProvider::class) parameter: OudsRadioButtonItemPreviewParameter) {
    PreviewOudsRadioButtonItem(darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsRadioButtonItem(
    darkThemeEnabled: Boolean,
    parameter: OudsRadioButtonItemPreviewParameter
) = OudsPreview(darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        PreviewStates<OudsControlItem.State>(columnCount = 1) {
            OudsRadioButtonItem(
                selected = value,
                label = "Label",
                onClick = { },
                additionalLabel = additionalLabel,
                helperText = helperText,
                divider = divider,
                error = error,
                outlined = checkNotNull(extraParameter),
                reversed = reversed,
                icon = if (hasIcon) OudsControlItem.Icon(imageVector = Icons.Filled.Call) else null
            )
        }
    }
}

@PreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsRadioButtonItemHighContrastModeEnabled(@PreviewParameter(OudsRadioButtonItemHighContrastModePreviewParameterProvider::class) parameter: OudsRadioButtonItemHighContrastModePreviewParameter) {
    PreviewOudsRadioButtonItemHighContrastModeEnabled(darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsRadioButtonItemHighContrastModeEnabled(
    darkThemeEnabled: Boolean,
    parameter: OudsRadioButtonItemHighContrastModePreviewParameter
) = OudsPreview(darkThemeEnabled = darkThemeEnabled, highContrastModeEnabled = true) {
    with(parameter) {
        PreviewStates<OudsControlItem.State>(columnCount = 1) {
            OudsRadioButtonItem(
                selected = value,
                label = "Label",
                onClick = {},
                interactionSource = remember { MutableInteractionSource() }
            )
        }
    }
}


@Preview
@Composable
internal fun PreviewOudsRadioButtonItemWithLongHelperText() = OudsPreview {
    OudsRadioButtonItem(
        selected = true,
        label = "Label",
        onClick = {},
        additionalLabel = "Additional label",
        helperText = LoremIpsumText,
        icon = OudsControlItem.Icon(imageVector = Icons.Filled.Call)
    )
}

internal typealias OudsRadioButtonItemPreviewParameter = OudsControlItemPreviewParameter<Boolean, Boolean>

private val previewOutlinedValues = listOf(true, true, false)

internal class OudsRadioButtonItemPreviewParameterProvider :
    OudsControlItemPreviewParameterProvider<Boolean, Boolean>(DefaultBooleanValues, previewOutlinedValues)

internal typealias OudsRadioButtonItemHighContrastModePreviewParameter = OudsControlItemHighContrastModePreviewParameter<Boolean>

internal class OudsRadioButtonItemHighContrastModePreviewParameterProvider :
    OudsControlItemHighContrastModePreviewParameterProvider<Boolean>(listOf(false, true))


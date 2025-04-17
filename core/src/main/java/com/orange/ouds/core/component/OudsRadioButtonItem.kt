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
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider

/**
 * <a href="https://unified-design-system.orange.com/472794e18/p/90c467-radio-button" class="external" target="_blank">OUDS Radio button design guidelines</a>
 *
 * An OUDS radio button item is a layout containing an [OudsRadioButton], an associated label and several other optional elements.
 * It can be used in a list as a list item.
 * By clicking on a radio button item, the user changes the selected state of its radio button.
 *
 * If you want to use a standalone radio button please use [com.orange.ouds.core.component.OudsRadioButton].
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
    divider: Boolean = false,
    outlined: Boolean = false,
    reversed: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    error: Boolean = false,
    interactionSource: MutableInteractionSource? = null
) {
    OudsRadioButtonItem(
        selected = selected,
        label = label,
        onClick = onClick,
        interactionSource = interactionSource,
        modifier = modifier,
        previewState = null,
        additionalLabel = additionalLabel,
        helperText = helperText,
        icon = icon,
        divider = divider,
        outlined = outlined,
        reversed = reversed,
        enabled = enabled,
        readOnly = readOnly,
        error = error
    )
}

@Composable
private fun OudsRadioButtonItem(
    selected: Boolean,
    label: String,
    onClick: (() -> Unit)?,
    previewState: OudsControlItem.State?,
    modifier: Modifier = Modifier,
    additionalLabel: String? = null,
    helperText: String? = null,
    icon: OudsControlItem.Icon? = null,
    divider: Boolean = false,
    outlined: Boolean = false,
    reversed: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    error: Boolean = false,
    interactionSource: MutableInteractionSource? = null
) {
    @Suppress("NAME_SHADOWING") val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    val interactionState by interactionSource.collectInteractionStateAsState()
    val state = previewState.orElse { rememberOudsControlItemState(enabled = enabled, readOnly = readOnly, interactionState = interactionState) }

    val selectableModifier = if (onClick != null) {
        Modifier.selectable(
            selected = selected,
            onClick = onClick,
            enabled = enabled && !readOnly,
            interactionSource = interactionSource,
            indication = null,
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
        reversed = reversed,
        enabled = enabled,
        readOnly = readOnly,
        error = error,
        errorComponentName = "OudsRadioButtonItem",
        indicator = {
            OudsRadioButtonIndicator(
                state = radioButtonState(state),
                selected = selected,
                error = error
            )
        },
        previewState = previewState,
        checkedContentPreviewStatus = if (selected) "Selected" else "Unselected",
        modifier = modifier
            .then(selectableModifier)
            .semantics(mergeDescendants = true) {}
            .border(outlined = outlined, selected = selected, error = error, state = state)
    )
}

private fun radioButtonState(state: OudsControlItem.State) = when (state) {
    OudsControlItem.State.Enabled -> OudsControl.State.Enabled
    OudsControlItem.State.Hovered -> OudsControl.State.Hovered
    OudsControlItem.State.Pressed -> OudsControl.State.Pressed
    OudsControlItem.State.Focused -> OudsControl.State.Focused
    OudsControlItem.State.Disabled, OudsControlItem.State.ReadOnly -> OudsControl.State.Disabled
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
        PreviewStates<OudsControlItem.State>(columnCount = 1) { state ->
            OudsRadioButtonItem(
                selected = selected,
                label = "Label",
                onClick = { },
                previewState = state,
                additionalLabel = additionalLabel,
                helperText = helperText,
                divider = divider,
                error = error,
                outlined = outlined,
                reversed = reversed,
                icon = if (hasIcon) {
                    OudsControlItem.Icon(imageVector = Icons.Filled.Call)
                } else {
                    null
                }
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

internal data class OudsRadioButtonItemPreviewParameter(
    val selected: Boolean,
    val additionalLabel: String? = null,
    val helperText: String? = null,
    val divider: Boolean = false,
    val hasIcon: Boolean = false,
    val error: Boolean = false,
    val outlined: Boolean = false,
    val reversed: Boolean = false
)

internal class OudsRadioButtonItemPreviewParameterProvider :
    BasicPreviewParameterProvider<OudsRadioButtonItemPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsRadioButtonItemPreviewParameter>
    get() {
        val additionalLabel = "Additional label"
        val helperText = "Helper text"
        val reversedValues = listOf(false, true)
        return buildList {
            reversedValues.forEach { reversed ->
                val parameters = listOf(
                    OudsRadioButtonItemPreviewParameter(
                        selected = false,
                        outlined = true,
                        reversed = reversed
                    ),
                    OudsRadioButtonItemPreviewParameter(
                        selected = false,
                        hasIcon = true,
                        additionalLabel = additionalLabel,
                        helperText = helperText,
                        outlined = true,
                        reversed = reversed
                    ),
                    OudsRadioButtonItemPreviewParameter(
                        selected = true,
                        helperText = helperText,
                        divider = true,
                        error = true,
                        reversed = reversed
                    ),
                )
                addAll(parameters)
            }
        }
    }
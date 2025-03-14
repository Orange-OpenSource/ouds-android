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

import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.extensions.collectInteractionStateAsState
import com.orange.ouds.core.utilities.LoremIpsumText
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.foundation.utilities.UiModePreviews

// TODO Add DSM URL for radio button when available
// <a href="https://unified-design-system.orange.com/472794e18/p/23f1c1-checkbox" class="external" target="_blank">OUDS Radio button design guidelines</a>
/**
 * An OUDS radio button item is a layout containing an OudsRadioButton, an associated text and several other optional elements.
 * It can be used in a list as a list item.
 * By clicking on a radio button item, the user changes the selected state of its radio button.
 *
 * If you want to use a standalone radio button please use [com.orange.ouds.core.component.OudsRadioButton].
 *
 * @param selected Controls selected state of the radio button.
 * @param text The main text of the radio button item.
 * @param onClick Callback invoked on radio button click. If `null`, then this radio button will not be interactable, unless something else handles its
 * input events and updates its state.
 * @param modifier [Modifier] applied to the layout of the radio button item.
 * @param helperText Optional text displayed below the main text.
 * @param icon Optional icon displayed in the item. By default, it has a trailing position. If [inverted] is set to `true`, it is displayed as a leading element.
 * @param divider Controls the display of a divider at the bottom of the radio button item.
 * @param inverted When `false`, the radio button has a leading position and the optional [icon] has a trailing position. It is inverted otherwise.
 * @param enabled Controls the enabled state of the radio button item. When `false`, the radio button, the text and the optional icon are disabled, and the item
 * will not be clickable.
 * @param readOnly Controls the read only state of the radio button item. When `true` the item's radio button is disabled but the text and the icon remain in
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
    text: String,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    helperText: String? = null,
    icon: OudsControlItem.Icon? = null,
    divider: Boolean = false,
    inverted: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    error: Boolean = false,
    interactionSource: MutableInteractionSource? = null
) {
    val radioButtonItemInteractionSource = interactionSource ?: remember { MutableInteractionSource() }

    OudsRadioButtonItem(
        selected = selected,
        text = text,
        onClick = onClick,
        interactionSource = radioButtonItemInteractionSource,
        modifier = modifier,
        previewState = null,
        helperText = helperText,
        icon = icon,
        divider = divider,
        inverted = inverted,
        enabled = enabled,
        readOnly = readOnly,
        error = error
    )
}

@Composable
private fun OudsRadioButtonItem(
    selected: Boolean,
    text: String,
    onClick: (() -> Unit)?,
    interactionSource: MutableInteractionSource,
    previewState: OudsControlItem.State?,
    modifier: Modifier = Modifier,
    helperText: String? = null,
    icon: OudsControlItem.Icon? = null,
    divider: Boolean = false,
    inverted: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    error: Boolean = false
) {
    val interactionState by interactionSource.collectInteractionStateAsState()
    val state = previewState.orElse { rememberOudsControlItemState(enabled = enabled, readOnly = readOnly, interactionState = interactionState) }

    OudsControlItem(
        state = state,
        text = text,
        helperText = helperText,
        icon = icon,
        divider = divider,
        inverted = inverted,
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
        modifier = modifier.semantics(mergeDescendants = true) {
            //TODO
        }
    )
}

private fun radioButtonState(state: OudsControlItem.State) = when (state) {
    OudsControlItem.State.Enabled -> OudsRadioButton.State.Enabled
    OudsControlItem.State.Hovered -> OudsRadioButton.State.Hovered
    OudsControlItem.State.Pressed -> OudsRadioButton.State.Pressed
    OudsControlItem.State.Focused -> OudsRadioButton.State.Focused
    OudsControlItem.State.Disabled, OudsControlItem.State.ReadOnly -> OudsRadioButton.State.Disabled
}

@UiModePreviews.Default
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
        Column(Modifier.padding(16.dp)) {
            states.forEach { state ->
                OudsRadioButtonItem(
                    selected = selected,
                    text = "Label",
                    onClick = { },
                    previewState = state,
                    helperText = helperText,
                    divider = divider,
                    error = error,
                    inverted = inverted,
                    icon = if (hasIcon) {
                        OudsControlItem.Icon(imageVector = Icons.Filled.Call)
                    } else {
                        null
                    },
                    interactionSource = remember { MutableInteractionSource() }
                )
            }
        }
    }
}

internal data class OudsRadioButtonItemPreviewParameter(
    val selected: Boolean,
    val helperText: String? = null,
    val divider: Boolean = false,
    val hasIcon: Boolean = false,
    val error: Boolean = false,
    val inverted: Boolean = false
) {
    val states: List<OudsControlItem.State> = listOf(
        OudsControlItem.State.Enabled,
        OudsControlItem.State.Pressed,
        OudsControlItem.State.Hovered,
        OudsControlItem.State.Focused
    ).run {
        if (!error) plus(listOf(OudsControlItem.State.Disabled, OudsControlItem.State.ReadOnly)) else this
    }
}

internal class OudsRadioButtonItemPreviewParameterProvider :
    BasicPreviewParameterProvider<OudsRadioButtonItemPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsRadioButtonItemPreviewParameter>
    get() {
        val helperText = "Helper text"
        val invertedValues = listOf(false, true)
        return buildList {
            invertedValues.forEach { inverted ->
                val parameters = listOf(
                    OudsRadioButtonItemPreviewParameter(
                        selected = false,
                        inverted = inverted
                    ),
                    OudsRadioButtonItemPreviewParameter(
                        selected = false,
                        hasIcon = true,
                        helperText = helperText,
                        inverted = inverted
                    ),
                    OudsRadioButtonItemPreviewParameter(
                        selected = true,
                        helperText = helperText,
                        divider = true,
                        error = true,
                        inverted = inverted
                    ),
                    OudsRadioButtonItemPreviewParameter(
                        selected = true,
                        helperText = LoremIpsumText,
                        divider = true,
                        error = true,
                        hasIcon = true,
                        inverted = inverted
                    ),
                )
                addAll(parameters)
            }
        }
    }
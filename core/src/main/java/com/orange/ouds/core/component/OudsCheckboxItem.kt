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
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.selection.triStateToggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ouds.core.extensions.collectInteractionStateAsState
import com.orange.ouds.core.utilities.LoremIpsumText
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.PreviewStates
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider

/**
 * <a href="https://unified-design-system.orange.com/472794e18/p/23f1c1-checkbox" class="external" target="_blank">OUDS Checkbox design guidelines</a>
 *
 * An OUDS checkbox item is a layout containing an OudsCheckbox, an associated label and several other optional elements.
 * It can be used in a list as a list item or as a single element to validate general conditions for example.
 * By clicking on a checkbox item, the user changes the checked state of its checkbox.
 *
 * If you want to use a standalone checkbox please use [com.orange.ouds.core.component.OudsCheckbox].
 *
 * If you need an indeterminate state for the item's checkbox, please use [OudsTriStateCheckboxItem].
 *
 * @param checked Controls checked state of the item's checkbox.
 * @param label The main text of the checkbox item.
 * @param onCheckedChange Callback invoked on checkbox item click. If `null`, then this is passive and relies entirely on a higher-level component to control
 * the checked state.
 * @param modifier [Modifier] applied to the layout of the checkbox item.
 * @param helperText Optional text displayed below the label.
 * @param icon Optional icon displayed in the item. By default, it has a trailing position. If [reversed] is set to `true`, it is displayed as a leading element.
 * @param divider Controls the display of a divider at the bottom of the checkbox item.
 * @param reversed When `false`, the checkbox has a leading position and the optional [icon] has a trailing position.  Otherwise, it is reversed.
 * @param enabled Controls the enabled state of the checkbox item. When `false`, the checkbox, the texts and the optional icon are disabled, and the item
 * will not be clickable.
 * @param readOnly Controls the read only state of the checkbox item. When `true` the item's checkbox is disabled but the texts and the icon remain in
 * enabled color. Note that if it is set to `true` and [enabled] is set to `false`, the checkbox item will be displayed in disabled state.
 * @param error Controls the error state of the checkbox item.
 * @param interactionSource Optional hoisted [MutableInteractionSource] for observing and emitting [Interaction]s for the item's checkbox. Note that if `null`
 * is provided, interactions will still happen internally.
 *
 * @sample com.orange.ouds.core.component.samples.OudsCheckboxItemSample
 */
@Composable
fun OudsCheckboxItem(
    checked: Boolean,
    label: String,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    helperText: String? = null,
    icon: OudsControlItem.Icon? = null,
    divider: Boolean = false,
    reversed: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    error: Boolean = false,
    interactionSource: MutableInteractionSource? = null
) {
    OudsTriStateCheckboxItem(
        state = ToggleableState(checked),
        label = label,
        onClick = if (onCheckedChange != null) {
            { onCheckedChange(!checked) }
        } else null,
        modifier = modifier,
        helperText = helperText,
        icon = icon,
        divider = divider,
        reversed = reversed,
        enabled = enabled,
        readOnly = readOnly,
        error = error,
        interactionSource = interactionSource
    )
}

/**
 * <a href="https://unified-design-system.orange.com/472794e18/p/23f1c1-checkbox" class="external" target="_blank">OUDS Checkbox design guidelines</a>
 *
 * An OUDS checkbox parent item.
 * It is a layout containing an [com.orange.ouds.core.component.OudsTriStateCheckbox], an associated label and several other optional elements which is often
 * used in a list to handle checkbox items hierarchy.
 * By clicking on a checkbox parent item, the user changes the checked state of its checkbox.
 *
 * If you only need a standalone parent checkbox, please use [com.orange.ouds.core.component.OudsTriStateCheckbox].
 *
 * If you don't need an indeterminate state for the item's checkbox, you may prefer [OudsCheckboxItem].
 *
 * @param state Controls whether item's TriStateCheckbox is checked, unchecked or in indeterminate state.
 * @param label The main text of the checkbox item.
 * @param onClick Callback invoked when checkbox item is being clicked, therefore the change of checkbox [ToggleableState] state is requested. If null, then
 * this is passive and relies entirely on a higher-level component to control the state.
 * @param modifier [Modifier] applied to the layout of the checkbox item.
 * @param helperText Optional text displayed below the label.
 * @param icon Optional icon displayed in the item. By default, it has a trailing position. If [reversed] is set to `true`, it is displayed as a leading element.
 * @param divider Controls the display of a divider at the bottom of the checkbox item.
 * @param reversed When `false`, the checkbox has a leading position and the optional [icon] has a trailing position. Otherwise, it is reversed.
 * @param enabled Controls the enabled state of the checkbox item. When `false`, the checkbox, the texts and the optional icon are disabled, and the item
 * will not be clickable.
 * @param readOnly Controls the read only state of the checkbox item. When `true` the item's checkbox is disabled but the texts and the icon remain in
 * enabled color. Note that if it is set to `true` and [enabled] is set to `false`, the checkbox item will be displayed in disabled state.
 * @param error Controls the error state of the checkbox item.
 * @param interactionSource Optional hoisted [MutableInteractionSource] for observing and emitting [Interaction]s for the item's checkbox. Note that
 * if `null` is provided, interactions will still happen internally.
 *
 * @sample com.orange.ouds.core.component.samples.OudsTriStateCheckboxItemSample
 */
@Composable
fun OudsTriStateCheckboxItem(
    state: ToggleableState,
    label: String,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    helperText: String? = null,
    icon: OudsControlItem.Icon? = null,
    divider: Boolean = false,
    reversed: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    error: Boolean = false,
    interactionSource: MutableInteractionSource? = null
) {
    @Suppress("NAME_SHADOWING") val interactionSource = interactionSource ?: remember { MutableInteractionSource() }

    val toggleableModifier = if (onClick != null) {
        Modifier.triStateToggleable(
            interactionSource = interactionSource,
            indication = LocalIndication.current,
            state = state,
            onClick = onClick,
            enabled = enabled && !readOnly,
            role = Role.Checkbox
        )
    } else {
        Modifier
    }

    OudsCheckboxItem(
        value = state,
        label = label,
        interactionSource = interactionSource,
        modifier = modifier.then(toggleableModifier),
        previewState = null,
        helperText = helperText,
        icon = icon,
        divider = divider,
        reversed = reversed,
        enabled = enabled,
        readOnly = readOnly,
        error = error
    )
}

@Composable
private fun OudsCheckboxItem(
    value: ToggleableState,
    label: String,
    interactionSource: MutableInteractionSource,
    previewState: OudsControlItem.State?,
    modifier: Modifier = Modifier,
    helperText: String? = null,
    icon: OudsControlItem.Icon? = null,
    divider: Boolean = false,
    reversed: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    error: Boolean = false
) {
    val interactionState by interactionSource.collectInteractionStateAsState()
    val state = previewState.orElse { rememberOudsControlItemState(enabled = enabled, readOnly = readOnly, interactionState = interactionState) }

    OudsControlItem(
        state = state,
        label = label,
        helperText = helperText,
        icon = icon,
        divider = divider,
        reversed = reversed,
        enabled = enabled,
        readOnly = readOnly,
        error = error,
        errorComponentName = "OudsCheckboxItem or OudsTriStateCheckboxItem",
        indicator = {
            OudsCheckboxIndicator(
                state = checkboxState(state),
                value = value,
                error = error
            )
        },
        previewState = previewState,
        checkedContentPreviewStatus = when (value) {
            ToggleableState.On -> "Selected"
            ToggleableState.Off -> "Unselected"
            ToggleableState.Indeterminate -> "Indeterminate"
        },
        modifier = modifier.semantics(mergeDescendants = true) {}
    )
}

private fun checkboxState(state: OudsControlItem.State) = when (state) {
    OudsControlItem.State.Enabled -> OudsControl.State.Enabled
    OudsControlItem.State.Hovered -> OudsControl.State.Hovered
    OudsControlItem.State.Pressed -> OudsControl.State.Pressed
    OudsControlItem.State.Focused -> OudsControl.State.Focused
    OudsControlItem.State.Disabled, OudsControlItem.State.ReadOnly -> OudsControl.State.Disabled
}

@PreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsCheckboxItem(@PreviewParameter(OudsCheckboxItemPreviewParameterProvider::class) parameter: OudsCheckboxItemPreviewParameter) {
    PreviewOudsCheckboxItem(darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsCheckboxItem(
    darkThemeEnabled: Boolean,
    parameter: OudsCheckboxItemPreviewParameter
) = OudsPreview(darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        PreviewStates<OudsControlItem.State>(columnCount = 1) { state ->
            OudsCheckboxItem(
                value = toggleableState,
                label = "Label",
                previewState = state,
                helperText = helperText,
                divider = divider,
                error = error,
                reversed = reversed,
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

@Preview
@Composable
internal fun PreviewOudsCheckboxItemWithLongHelperText() = OudsPreview {
    OudsCheckboxItem(
        checked = true,
        label = "Label",
        onCheckedChange = {},
        helperText = LoremIpsumText,
        icon = OudsControlItem.Icon(imageVector = Icons.Filled.Call)
    )
}

internal data class OudsCheckboxItemPreviewParameter(
    val toggleableState: ToggleableState,
    val helperText: String? = null,
    val divider: Boolean = false,
    val hasIcon: Boolean = false,
    val error: Boolean = false,
    val reversed: Boolean = false
)

internal class OudsCheckboxItemPreviewParameterProvider :
    BasicPreviewParameterProvider<OudsCheckboxItemPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsCheckboxItemPreviewParameter>
    get() {
        val helperText = "Helper text"
        val reversedValues = listOf(false, true)
        return buildList {
            reversedValues.forEach { reversed ->
                val parameters = listOf(
                    OudsCheckboxItemPreviewParameter(
                        toggleableState = ToggleableState.Off,
                        reversed = reversed
                    ),
                    OudsCheckboxItemPreviewParameter(
                        toggleableState = ToggleableState.Off,
                        hasIcon = true,
                        helperText = helperText,
                        reversed = reversed
                    ),
                    OudsCheckboxItemPreviewParameter(
                        toggleableState = ToggleableState.On,
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
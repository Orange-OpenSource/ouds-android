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

/**
 * <a href="https://unified-design-system.orange.com/472794e18/p/23f1c1-checkbox" class="external" target="_blank">**OUDS Checkbox design guidelines**</a>
 *
 * Checkboxes are input controls that allow users to select one or more options from a number of choices.
 *
 * The **checkbox item variant** can function as a simple input with a label, or it can be combined with optional elements such as helper text, a divider, or an icon,
 * allowing it to suit various use cases.
 *
 * The OUDS checkbox item layout contains an [OudsCheckbox]. By clicking on a checkbox item, the user changes the checked state of its checkbox.
 *
 * In most cases, OUDS checkbox items span the entire width of the screen. Thus an horizontal padding of `OudsTheme.grids.margin` is applied to the content.
 * This behaviour can be disabled by calling [com.orange.ouds.core.utilities.edgeToEdgePadding] modifier with `enabled` parameter set to `false`.
 *
 * @see [OudsTriStateCheckboxItem] If you need an indeterminate state for the item's checkbox.
 * @see [OudsCheckbox] If you want to use a standalone checkbox without any other element.
 *
 * @param checked Controls checked state of the item's checkbox.
 * @param label The main label of the checkbox item.
 * @param onCheckedChange Callback invoked on checkbox item click. If `null`, then this is passive and relies entirely on a higher-level component to control
 * the checked state.
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
 * <a href="https://unified-design-system.orange.com/472794e18/p/23f1c1-checkbox" class="external" target="_blank">**OUDS Checkbox design guidelines**</a>
 *
 * Checkboxes are input controls that allow users to select one or more options from a number of choices.
 *
 * This checkbox item supports the indeterminate state: Checkboxes can have a parent-child relationship with other checkboxes. When the parent checkbox is
 * checked, all child checkboxes are checked. If a parent checkbox is unchecked, all child checkboxes are unchecked. If some, but not all, child checkboxes are
 * checked, the parent checkbox becomes an indeterminate checkbox.
 *
 * The **indeterminate checkbox item variant** can function as a simple input with a label, or it can be combined with optional elements such as helper text,
 * a divider, or an icon, allowing it to suit various use cases.
 *
 * The OUDS indeterminate checkbox item layout contains an [OudsTriStateCheckbox]. By clicking on an indeterminate checkbox item, the user changes the checked
 * state of its checkbox.
 *
 * In most cases, OUDS checkbox items span the entire width of the screen. Thus an horizontal padding of `OudsTheme.grids.margin` is applied to the content.
 * This behaviour can be disabled by calling [com.orange.ouds.core.utilities.edgeToEdgePadding] modifier with `enabled` parameter set to `false`.
 *
 * @see [OudsCheckboxItem] If you need a simple item's checkbox that represents [Boolean] state.
 * @see [OudsTriStateCheckbox] If you only need an indeterminate standalone parent checkbox without any other element.
 *
 * @param state Controls whether item's TriStateCheckbox is checked, unchecked or in indeterminate state.
 * @param label The main label of the checkbox item.
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
                state = state.toControlState(),
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
        modifier = modifier.semantics(mergeDescendants = true) {},
        handleHighContrastMode = true
    )
}

@PreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsCheckboxItem(@PreviewParameter(OudsCheckboxItemPreviewParameterProvider::class) parameter: OudsCheckboxItemPreviewParameter) {
    PreviewOudsCheckboxItem(darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@PreviewLightDark
@Composable
private fun PreviewOudsCheckboxItemHighContrastModeEnabled(@PreviewParameter(OudsCheckboxItemHighContrastModePreviewParameterProvider::class) parameter: OudsCheckboxItemHighContrastModePreviewParameter) {
    OudsPreview(darkThemeEnabled = isSystemInDarkTheme(), highContrastModeEnabled = true) {
        with(parameter) {
            PreviewStates<OudsControlItem.State>(columnCount = 1) { state ->
                OudsCheckboxItem(
                    value = value,
                    label = "Label",
                    previewState = state,
                    interactionSource = remember { MutableInteractionSource() }
                )
            }
        }
    }
}

@Composable
internal fun PreviewOudsCheckboxItem(
    darkThemeEnabled: Boolean,
    parameter: OudsCheckboxItemPreviewParameter,
    highContrastModeEnabled: Boolean = false
) = OudsPreview(darkThemeEnabled = darkThemeEnabled, highContrastModeEnabled = highContrastModeEnabled) {
    with(parameter) {
        PreviewStates<OudsControlItem.State>(columnCount = 1) { state ->
            OudsCheckboxItem(
                value = value,
                label = "Label",
                previewState = state,
                helperText = helperText,
                divider = divider,
                error = error,
                reversed = reversed,
                icon = if (hasIcon) OudsControlItem.Icon(imageVector = Icons.Filled.Call) else null,
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

internal typealias OudsCheckboxItemPreviewParameter = OudsControlItemPreviewParameter<ToggleableState, Nothing>

internal class OudsCheckboxItemPreviewParameterProvider :
    OudsControlItemPreviewParameterProvider<ToggleableState, Nothing>(DefaultBooleanValues.map { ToggleableState(it) })

internal typealias OudsCheckboxItemHighContrastModePreviewParameter = OudsControlItemHighContrastModePreviewParameter<ToggleableState>

internal class OudsCheckboxItemHighContrastModePreviewParameterProvider :
    OudsControlItemHighContrastModePreviewParameterProvider<ToggleableState>(listOf(ToggleableState.Off, ToggleableState.On))

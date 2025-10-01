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
import com.orange.ouds.core.component.common.OudsError
import com.orange.ouds.core.extensions.collectInteractionStateAsState
import com.orange.ouds.core.utilities.LoremIpsumText
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.PreviewEnumEntries
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.theme.OudsThemeContract

/**
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
 * > Design guidelines: [unified-design-system.orange.com](https://unified-design-system.orange.com/472794e18/p/23f1c1-checkbox)
 *
 * > Design version: 2.2.0
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
 * @param error Optional [OudsError] to provide in the case of the checkbox item should appear in error state, `null` otherwise.
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
    error: OudsError? = null,
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
 * > Design guidelines: [unified-design-system.orange.com](https://unified-design-system.orange.com/472794e18/p/23f1c1-checkbox)
 *
 * > Design version: 2.2.0
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
 * @param error Optional [OudsError] to provide in the case of the checkbox item should appear in error state, `null` otherwise.
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
    error: OudsError? = null,
    interactionSource: MutableInteractionSource? = null
) {
    @Suppress("NAME_SHADOWING") val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    val interactionState by interactionSource.collectInteractionStateAsState()
    val checkboxItemState = getControlItemState(enabled = enabled, readOnly = readOnly, interactionState = interactionState)
    val backgroundColor = rememberControlItemBackgroundColor(enabled = enabled, readOnly = readOnly, interactionState = interactionState)

    val toggleableModifier = if (onClick != null) {
        Modifier.triStateToggleable(
            interactionSource = interactionSource,
            indication = InteractionValuesIndication(backgroundColor),
            state = state,
            onClick = onClick,
            enabled = enabled && !readOnly,
            role = Role.Checkbox
        )
    } else {
        Modifier
    }

    OudsControlItem(
        state = checkboxItemState,
        label = label,
        helperText = helperText,
        icon = icon,
        divider = divider,
        enabled = enabled,
        readOnly = readOnly,
        error = error,
        indicator = {
            OudsCheckboxIndicator(
                state = checkboxItemState.toControlState(),
                value = state,
                error = error != null
            )
        },
        indicatorPosition = if (reversed) OudsControlItem.IndicatorPosition.End else OudsControlItem.IndicatorPosition.Start,
        checkedContentComponentName = "OudsCheckboxItem or OudsTriStateCheckboxItem",
        checkedContentSelectionStatus = when (state) {
            ToggleableState.On -> "Selected"
            ToggleableState.Off -> "Unselected"
            ToggleableState.Indeterminate -> "Indeterminate"
        },
        modifier = modifier
            .then(toggleableModifier)
            .background(color = backgroundColor.value)
            .semantics(mergeDescendants = true) {},
        handleHighContrastMode = true
    )
}

@PreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsCheckboxItem(@PreviewParameter(OudsCheckboxItemPreviewParameterProvider::class) parameter: OudsCheckboxItemPreviewParameter) {
    PreviewOudsCheckboxItem(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
fun PreviewOudsCheckboxItem(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsCheckboxItemPreviewParameter
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        PreviewEnumEntries<OudsControlItem.State>(columnCount = 1) {
            OudsTriStateCheckboxItem(
                state = value,
                label = "Label",
                onClick = {},
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

@PreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsCheckboxItemHighContrastModeEnabled(@PreviewParameter(OudsCheckboxItemHighContrastModePreviewParameterProvider::class) parameter: OudsCheckboxItemHighContrastModePreviewParameter) {
    PreviewOudsCheckboxItemHighContrastModeEnabled(
        theme = getPreviewTheme(),
        darkThemeEnabled = isSystemInDarkTheme(),
        parameter = parameter
    )
}

@Composable
fun PreviewOudsCheckboxItemHighContrastModeEnabled(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsCheckboxItemHighContrastModePreviewParameter
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled, highContrastModeEnabled = true) {
    with(parameter) {
        PreviewEnumEntries<OudsControlItem.State>(columnCount = 1) {
            OudsTriStateCheckboxItem(
                state = value,
                label = "Label",
                onClick = {},
                interactionSource = remember { MutableInteractionSource() }
            )
        }
    }
}

@Preview
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsCheckboxItemWithLongHelperText() = PreviewOudsCheckboxItemWithLongHelperText(theme = getPreviewTheme())

@Composable
fun PreviewOudsCheckboxItemWithLongHelperText(theme: OudsThemeContract) = OudsPreview(theme = theme) {
    OudsCheckboxItem(
        checked = true,
        label = "Label",
        onCheckedChange = {},
        helperText = LoremIpsumText,
        icon = OudsControlItem.Icon(imageVector = Icons.Filled.Call)
    )
}

typealias OudsCheckboxItemPreviewParameter = OudsControlItemPreviewParameter<ToggleableState, Nothing>

class OudsCheckboxItemPreviewParameterProvider :
    OudsControlItemPreviewParameterProvider<ToggleableState, Nothing>(DefaultBooleanValues.map { ToggleableState(it) })

typealias OudsCheckboxItemHighContrastModePreviewParameter = OudsControlItemHighContrastModePreviewParameter<ToggleableState>

class OudsCheckboxItemHighContrastModePreviewParameterProvider :
    OudsControlItemHighContrastModePreviewParameterProvider<ToggleableState>(listOf(ToggleableState.Off, ToggleableState.On))

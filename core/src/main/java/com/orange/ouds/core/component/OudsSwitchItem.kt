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
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
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
 * Switches allow the user to toggle between two states, typically "on" and "off". It is represented as a slider that changes its position or color to indicate
 * the current state. Switches are used to enable or disable features, options, or settings in an intuitive and visual manner.
 *
 * The **switch item variant** can function as a simple input with a label, or it can be combined with optional elements such as helper text, a divider,
 * or an icon, allowing it to suit various use cases.
 * It can be used in a list as a list item or as a single element to validate general conditions for example.
 *
 * The OUDS switch item layout contains an [OudsSwitch]. By clicking on the switch item, the user changes the selected state of its switch.
 *
 * In most cases, OUDS switch items span the entire width of the screen. Thus an horizontal padding of `OudsTheme.grids.margin` is applied to the content.
 * This behaviour can be disabled by calling [com.orange.ouds.core.utilities.edgeToEdgePadding] modifier with `enabled` parameter set to `false`.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://unified-design-system.orange.com/472794e18/p/18acc0-switch)
 *
 * > Design version: 1.3.0
 *
 * @see [OudsSwitch] If you want to use a standalone switch.
 *
 * @param checked Controls checked state of the item's switch.
 * @param label The main label of the switch item.
 * @param onCheckedChange Callback invoked on switch item click. If `null`, then this is passive and relies entirely on a higher-level component to control
 * the checked state.
 * @param modifier [Modifier] applied to the layout of the switch item.
 * @param helperText Optional text displayed below the label.
 * @param icon Optional icon displayed in the item. By default, it has a leading position. If [reversed] is set to `true`, it is displayed as a trailing element.
 * @param divider Controls the display of a divider at the bottom of the switch item.
 * @param reversed When `false`, the switch has a trailing position and the optional [icon] has a leading position. Otherwise, it is reversed.
 * @param enabled Controls the enabled state of the switch item. When `false`, the switch, the texts and the optional icon are disabled, and the item
 * will not be clickable.
 * @param readOnly Controls the read only state of the switch item. When `true` the item's switch is disabled but the texts and the icon remain in
 * enabled color. Note that if it is set to `true` and [enabled] is set to `false`, the switch item will be displayed in disabled state.
 * @param error Optional [OudsError] to provide in the case of the switch item should appear in error state, `null` otherwise.
 * @param interactionSource Optional hoisted [MutableInteractionSource] for observing and emitting [Interaction]s for the item's switch. Note that if `null`
 * is provided, interactions will still happen internally.
 *
 * @sample com.orange.ouds.core.component.samples.OudsSwitchItemSample
 */
@Composable
fun OudsSwitchItem(
    checked: Boolean,
    label: String,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    helperText: String? = null,
    icon: OudsControlItemIcon? = null,
    divider: Boolean = false,
    reversed: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    error: OudsError? = null,
    interactionSource: MutableInteractionSource? = null
) {
    @Suppress("NAME_SHADOWING") val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    val interactionState by interactionSource.collectInteractionStateAsState()
    val state = getControlItemState(enabled = enabled, readOnly = readOnly, interactionState = interactionState)
    val backgroundColor = rememberControlItemBackgroundColor(enabled = enabled, readOnly = readOnly, interactionState = interactionState)

    val toggleableModifier = if (onCheckedChange != null) {
        Modifier.toggleable(
            value = checked,
            interactionSource = interactionSource,
            indication = InteractionValuesIndication(backgroundColor),
            enabled = enabled && !readOnly,
            role = Role.Switch,
            onValueChange = onCheckedChange
        )
    } else {
        Modifier
    }

    OudsControlItem(
        state = state,
        label = label,
        helperText = helperText,
        icon = icon,
        divider = divider,
        enabled = enabled,
        readOnly = readOnly,
        error = error,
        indicator = {
            OudsSwitchIndicator(
                state = state.toControlState(),
                checked = checked
            )
        },
        indicatorPosition = if (reversed) OudsControlItemIndicatorPosition.Start else OudsControlItemIndicatorPosition.End,
        checkedContentComponentName = "OudsSwitchItem",
        checkedContentSelectionStatus = if (checked) "Selected" else "Unselected",
        modifier = modifier
            .then(toggleableModifier)
            .background(color = backgroundColor.value)
            .semantics(mergeDescendants = true) {}
    )
}

@PreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsSwitchItem(@PreviewParameter(OudsSwitchItemPreviewParameterProvider::class) parameter: OudsSwitchItemPreviewParameter) {
    PreviewOudsSwitchItem(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
fun PreviewOudsSwitchItem(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsSwitchItemPreviewParameter
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        PreviewEnumEntries<OudsControlItemState>(columnCount = 1) {
            OudsSwitchItem(
                checked = value,
                label = "Label",
                onCheckedChange = {},
                helperText = helperText,
                icon = if (hasIcon) OudsControlItemIcon(imageVector = Icons.Filled.Call) else null,
                divider = divider,
                reversed = reversed,
                error = error,
                interactionSource = remember { MutableInteractionSource() }
            )
        }
    }
}

@Preview
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsSwitchItemWithLongHelperText() = PreviewOudsSwitchItemWithLongHelperText(theme = getPreviewTheme())

@Composable
fun PreviewOudsSwitchItemWithLongHelperText(theme: OudsThemeContract) = OudsPreview(theme = theme) {
    OudsSwitchItem(
        checked = true,
        label = "Label",
        onCheckedChange = {},
        helperText = LoremIpsumText,
        icon = OudsControlItemIcon(imageVector = Icons.Filled.Call)
    )
}

typealias OudsSwitchItemPreviewParameter = OudsControlItemPreviewParameter<Boolean, Nothing>

class OudsSwitchItemPreviewParameterProvider : OudsControlItemPreviewParameterProvider<Boolean, Nothing>(DefaultBooleanValues)

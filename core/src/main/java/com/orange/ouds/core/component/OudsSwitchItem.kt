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
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.component.common.OudsError
import com.orange.ouds.core.extensions.collectInteractionStateAsState
import com.orange.ouds.core.utilities.LoremIpsumText
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.OudsPreviewableComponent
import com.orange.ouds.core.utilities.PreviewEnumEntries
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.theme.OudsThemeContract

/**
 * Switch item is a UI element that allows to toggle between two states, typically "On" and "Off", and used to enable or disable features, options or settings.
 * Switch item covers a wider range of contexts by allowing to toggle the visibility of additional text labels and icon assets.
 *
 * The **switch item variant** can function as a simple input with a label, or it can be combined with optional elements such as description, a divider,
 * or an icon, allowing it to suit various use cases.
 * It can be used in a list as a list item or as a single element to validate general conditions, for example.
 *
 * The OUDS switch item layout contains an [OudsSwitch]. By clicking the switch item, the user changes the checked state of its switch.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://r.orange.fr/r/S-ouds-doc-switch)
 *
 * > Design version: 1.5.0
 *
 * @see [OudsSwitch] If you want to use a standalone switch.
 *
 * @param checked Controls the checked state of the item's switch.
 * @param label The main label of the switch item.
 * @param onCheckedChange Callback invoked on switch item click. If `null`, then this is passive and relies entirely on a higher-level component to control
 *   the checked state.
 * @param modifier [Modifier] applied to the layout of the switch item.
 * @param description Optional text displayed below the label.
 * @param icon Optional icon displayed in the item. By default, it has a leading position. If [reversed] is set to `true`, it is displayed as a trailing element.
 * @param edgeToEdge Controls the horizontal layout of the item. When `true`, the item is designed to span the full width of the screen or container. When `false`,
 *   it is adapted for use within constrained layouts or containers with their own padding. Defaults to `true`.
 * @param divider Controls the display of a divider at the bottom of the switch item.
 * @param reversed When `false`, the switch has a trailing position and the optional [icon] has a leading position. Otherwise, it is reversed.
 * @param enabled Controls the enabled state of the switch item. When `false`, the switch, the texts and the optional icon are disabled, and the item
 *   will not be clickable.
 * @param readOnly Controls the read-only state of the switch item. When `true`, the item's switch is disabled but the texts and the icon remain in the
 *   enabled color. Note that if it is set to `true` and [enabled] is set to `false`, the switch item will be displayed in the disabled state.
 * @param error Optional [OudsError] to provide if the switch item should appear in an error state, `null` otherwise.
 * @param constrainedMaxWidth When `true`, the item width is constrained to a maximum value defined by the design system.
 *   When `false`, no specific width constraint is applied, allowing the component to size itself or follow external modifiers.
 *   Defaults to `false`.
 * @param interactionSource Optional hoisted [MutableInteractionSource] for observing and emitting [Interaction]s for the item's switch. Note that if `null`
 *   is provided, interactions will still happen internally.
 *
 * @sample com.orange.ouds.core.component.samples.OudsSwitchItemSample
 */
@Composable
fun OudsSwitchItem(
    checked: Boolean,
    label: String,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    description: String? = null,
    icon: OudsControlItemIcon? = null,
    edgeToEdge: Boolean = true,
    divider: Boolean = false,
    reversed: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    error: OudsError? = null,
    constrainedMaxWidth: Boolean = false,
    interactionSource: MutableInteractionSource? = null
) {
    @Suppress("NAME_SHADOWING") val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    val interactionState by interactionSource.collectInteractionStateAsState()
    val state = getControlState(enabled = enabled, readOnly = readOnly, interactionState = interactionState)
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
        description = description,
        icon = icon,
        edgeToEdge = edgeToEdge,
        divider = divider,
        enabled = enabled,
        readOnly = readOnly,
        error = error,
        indicator = {
            OudsSwitchIndicator(
                state = state,
                checked = checked
            )
        },
        indicatorPosition = if (reversed) OudsControlItemIndicatorPosition.Start else OudsControlItemIndicatorPosition.End,
        checkedContentComponentName = "OudsSwitchItem",
        checkedContentSelectionStatus = if (checked) "Selected" else "Unselected",
        backgroundColor = backgroundColor.value,
        modifier = modifier
            .then(toggleableModifier)
            .semantics(mergeDescendants = true) {},
        constrainedMaxWidth = constrainedMaxWidth
    )
}

@PreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsSwitchItem(@PreviewParameter(OudsSwitchItemPreviewParameterProvider::class) parameter: OudsSwitchItemPreviewParameter) {
    PreviewOudsSwitchItem(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsSwitchItem(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsSwitchItemPreviewParameter
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        PreviewEnumEntries<OudsControlState>(columnCount = 1, edgeToEdge = true) {
            OudsSwitchItem(
                checked = value,
                label = "Label",
                onCheckedChange = {},
                description = description,
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
private fun PreviewOudsSwitchItemWithLongDescription() = PreviewOudsSwitchItemWithLongDescription(theme = getPreviewTheme())

@Composable
internal fun PreviewOudsSwitchItemWithLongDescription(theme: OudsThemeContract) = OudsPreview(theme = theme) {
    OudsSwitchItem(
        checked = true,
        label = "Label",
        onCheckedChange = {},
        description = LoremIpsumText,
        icon = OudsControlItemIcon(imageVector = Icons.Filled.Call)
    )
}

@Preview
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsSwitchItemWithEdgeToEdgeDisabled() = PreviewOudsSwitchItemWithEdgeToEdgeDisabled(theme = getPreviewTheme())

@Composable
internal fun PreviewOudsSwitchItemWithEdgeToEdgeDisabled(theme: OudsThemeContract) = OudsPreview(theme = theme) {
    PreviewEnumEntries<OudsControlState>(columnCount = 1) {
        OudsSwitchItem(
            checked = true,
            label = "Label",
            onCheckedChange = {},
            icon = OudsControlItemIcon(imageVector = Icons.Filled.Call),
            edgeToEdge = false,
            divider = true,
            error = OudsError(ControlItemErrorMessage),
        )
    }
}

@Preview(widthDp = OudsPreviewableComponent.SwitchItem.ConstrainedMaxWidth.PreviewWidthDp)
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
internal fun PreviewOudsSwitchItemConstrainedMaxWidth(@PreviewParameter(OudsControlItemConstrainedMaxWidthPreviewParameterProvider::class) constrainedMaxWidth: Boolean) {
    PreviewOudsSwitchItemConstrainedMaxWidth(theme = getPreviewTheme(), constrainedMaxWidth = constrainedMaxWidth)
}

@Composable
internal fun PreviewOudsSwitchItemConstrainedMaxWidth(theme: OudsThemeContract, constrainedMaxWidth: Boolean) = OudsPreview(theme = theme) {
    OudsSwitchItem(
        modifier = Modifier.padding(all = 10.dp),
        checked = true,
        label = "Label",
        onCheckedChange = {},
        icon = OudsControlItemIcon(imageVector = Icons.Filled.Call),
        constrainedMaxWidth = constrainedMaxWidth,
        edgeToEdge = false,
        divider = true
    )
}

internal typealias OudsSwitchItemPreviewParameter = OudsControlItemPreviewParameter<Boolean, Nothing>

internal class OudsSwitchItemPreviewParameterProvider : OudsControlItemPreviewParameterProvider<Boolean, Nothing>(DefaultBooleanValues)
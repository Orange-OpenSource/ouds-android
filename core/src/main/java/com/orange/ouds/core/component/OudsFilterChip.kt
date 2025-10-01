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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.PreviewEnumEntries
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.theme.OudsThemeContract

/**
 * Chips help people enter information, make selections, filter content, or trigger actions. Chips
 * can show multiple interactive elements together in the same area, such as a list of selectable
 * movie times, or a series of email contacts.
 *
 * A filter chip is a compact UI element used in a design system to represent a filter option that can be selected or deselected by the user.
 * Filter chips allow users to refine content or data by applying one or more filters in a visually accessible and interactive way.
 *
 * This version of the filter chip uses the *text only* layout which displays only text, offering a clean and minimalistic look.
 * Best suited for category-based filters that do not require additional visual elements.
 * Other layouts are available for this component: *text + icon* and *icon only*.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://unified-design-system.orange.com/472794e18/p/77fdea-chip/t/14bf4bd854)
 *
 * > Design version: 1.3.0
 *
 * @param selected Whether this chip is selected or not.
 * @param onClick Called when this chip is clicked.
 * @param label Text label for this chip.
 * @param modifier The [Modifier] to be applied to this chip.
 * @param enabled Controls the enabled state of this chip. When `false`, this component will not
 *   respond to user input, and it will appear visually disabled and disabled to accessibility
 *   services.
 * @param interactionSource an optional hoisted [MutableInteractionSource] for observing and
 *   emitting [Interaction]s for this chip. You can use this to change the chip's appearance or
 *   preview the chip in different states. Note that if `null` is provided, interactions will still
 *   happen internally.
 *
 * @sample com.orange.ouds.core.component.samples.OudsFilterChipTextOnlySample
 */
@Composable
fun OudsFilterChip(
    selected: Boolean,
    onClick: () -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null
) {
    OudsFilterChip(
        selected = selected,
        onClick = onClick,
        nullableLabel = label,
        nullableIcon = null,
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource
    )
}

/**
 * Chips help people enter information, make selections, filter content, or trigger actions. Chips
 * can show multiple interactive elements together in the same area, such as a list of selectable
 * movie times, or a series of email contacts.
 *
 * A filter chip is a compact UI element used in a design system to represent a filter option that can be selected or deselected by the user.
 * Filter chips allow users to refine content or data by applying one or more filters in a visually accessible and interactive way.
 *
 * This version of the chip uses the *icon only* layout which uses only an icon, making it a compact option for limited space.
 * Works well with universally recognized symbols, such as a heart for favorites or a checkmark for selection.
 * Other layouts are available for this component: *text only* and *text + icon*.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://unified-design-system.orange.com/472794e18/p/77fdea-chip/t/14bf4bd854)
 *
 * > Design version: 1.3.0
 *
 * @param selected Whether this chip is selected or not.
 * @param onClick Called when this chip is clicked.
 * @param icon Icon displayed in the chip. Use an icon to add additional affordance where the icon has a clear and well-established meaning.
 * @param modifier The [Modifier] to be applied to this chip.
 * @param enabled Controls the enabled state of this chip. When `false`, this component will not
 *   respond to user input, and it will appear visually disabled and disabled to accessibility
 *   services.
 * @param interactionSource an optional hoisted [MutableInteractionSource] for observing and
 *   emitting [Interaction]s for this chip. You can use this to change the chip's appearance or
 *   preview the chip in different states. Note that if `null` is provided, interactions will still
 *   happen internally.
 *
 * @sample com.orange.ouds.core.component.samples.OudsFilterChipIconOnlySample
 */
@Composable
fun OudsFilterChip(
    selected: Boolean,
    onClick: () -> Unit,
    icon: OudsChip.Icon,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null
) {
    OudsFilterChip(
        selected = selected,
        onClick = onClick,
        nullableLabel = null,
        nullableIcon = icon,
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource
    )
}

/**
 * Chips help people enter information, make selections, filter content, or trigger actions. Chips
 * can show multiple interactive elements together in the same area, such as a list of selectable
 * movie times, or a series of email contacts.
 *
 * A filter chip is a compact UI element used in a design system to represent a filter option that can be selected or deselected by the user.
 * Filter chips allow users to refine content or data by applying one or more filters in a visually accessible and interactive way.
 *
 * This version of the chip uses the *text + icon* layout which combines text with an icon to enhance clarity and recognition.
 * Ideal when a visual cue helps reinforce the filter’s meaning.
 * Other layouts are available for this component: *text only* and *icon only*.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://unified-design-system.orange.com/472794e18/p/77fdea-chip/t/14bf4bd854)
 *
 * > Design version: 1.3.0
 *
 * @param selected Whether this chip is selected or not.
 * @param onClick Called when this chip is clicked.
 * @param label Text label for this chip.
 * @param icon Icon displayed in the chip. Use an icon to add additional affordance where the icon has a clear and well-established meaning.
 * @param modifier The [Modifier] to be applied to this chip.
 * @param enabled Controls the enabled state of this chip. When `false`, this component will not
 *   respond to user input, and it will appear visually disabled and disabled to accessibility
 *   services.
 * @param interactionSource an optional hoisted [MutableInteractionSource] for observing and
 *   emitting [Interaction]s for this chip. You can use this to change the chip's appearance or
 *   preview the chip in different states. Note that if `null` is provided, interactions will still
 *   happen internally.
 *
 * @sample com.orange.ouds.core.component.samples.OudsFilterChipTextAndIconSample
 */
@Composable
fun OudsFilterChip(
    selected: Boolean,
    onClick: () -> Unit,
    label: String,
    icon: OudsChip.Icon,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null
) {
    OudsFilterChip(
        selected = selected,
        onClick = onClick,
        nullableLabel = label,
        nullableIcon = icon,
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource
    )
}

@Composable
@JvmName("OudsFilterChipNullableLabelAndIcon")
private fun OudsFilterChip(
    selected: Boolean,
    onClick: () -> Unit,
    nullableLabel: String?,
    nullableIcon: OudsChip.Icon?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null
) {
    OudsChip(
        selectable = true,
        selected = selected,
        onClick = onClick,
        label = nullableLabel,
        icon = nullableIcon,
        iconPosition = OudsChip.IconPosition.End,
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource
    )
}

@PreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsFilterChip(@PreviewParameter(OudsFilterChipPreviewParameterProvider::class) parameter: OudsFilterChipPreviewParameter) {
    PreviewOudsFilterChip(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
fun PreviewOudsFilterChip(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: OudsFilterChipPreviewParameter) =
    OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
        with(parameter) {
            val label = if (hasLabel) "Label" else null
            val icon = if (hasIcon) OudsChip.Icon(Icons.Filled.FavoriteBorder, "") else null
            PreviewEnumEntries<OudsChip.State>(columnCount = 3) {
                OudsFilterChip(selected = selected, nullableIcon = icon, nullableLabel = label, onClick = {})
            }
        }
    }

data class OudsFilterChipPreviewParameter(
    val selected: Boolean,
    val hasLabel: Boolean,
    val hasIcon: Boolean
)

class OudsFilterChipPreviewParameterProvider : BasicPreviewParameterProvider<OudsFilterChipPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsFilterChipPreviewParameter>
    get() = listOf(
        OudsFilterChipPreviewParameter(selected = true, hasLabel = true, hasIcon = true),
        OudsFilterChipPreviewParameter(selected = false, hasLabel = true, hasIcon = true),
        OudsFilterChipPreviewParameter(selected = true, hasLabel = true, hasIcon = false),
        OudsFilterChipPreviewParameter(selected = false, hasLabel = true, hasIcon = false),
        OudsFilterChipPreviewParameter(selected = true, hasLabel = false, hasIcon = true),
        OudsFilterChipPreviewParameter(selected = false, hasLabel = false, hasIcon = true)
    )

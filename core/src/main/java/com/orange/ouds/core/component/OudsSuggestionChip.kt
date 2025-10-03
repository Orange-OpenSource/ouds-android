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
 * A suggestion chip is a compact UI element used to present recommended or predictive options based on user input or context.
 * Often found in search bars, forms, or messaging interfaces, suggestion chips help users quickly select from relevant suggestions.
 * They are typically non-selected by default and can be tapped or clicked to apply the suggestion, streamlining user input and enhancing usability.
 *
 * This version of the suggestion chip uses the *text only* layout which displays only text, offering a clean and minimalistic look.
 * Best suited for category-based filters that do not require additional visual elements.
 * Other layouts are available for this component: *text + icon* and *icon only*.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://unified-design-system.orange.com/472794e18/p/77fdea-chip/t/2d71fd4bb4)
 *
 * > Design version: 1.3.0
 *
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
 * @sample com.orange.ouds.core.component.samples.OudsSuggestionChipTextOnlySample
 */
@Composable
fun OudsSuggestionChip(
    onClick: () -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null
) {
    OudsSuggestionChip(
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
 * A suggestion chip is a compact UI element used to present recommended or predictive options based on user input or context.
 * Often found in search bars, forms, or messaging interfaces, suggestion chips help users quickly select from relevant suggestions.
 * They are typically non-selected by default and can be tapped or clicked to apply the suggestion, streamlining user input and enhancing usability.
 *
 * This version of the chip uses the *icon only* layout which uses only an icon, making it a compact option for limited space.
 * Works well with universally recognized symbols, such as a heart for favorites or a checkmark for selection.
 * Other layouts are available for this component: *text only* and *text + icon*.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://unified-design-system.orange.com/472794e18/p/77fdea-chip/t/2d71fd4bb4)
 *
 * > Design version: 1.3.0
 *
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
 * @sample com.orange.ouds.core.component.samples.OudsSuggestionChipIconOnlySample
 */
@Composable
fun OudsSuggestionChip(
    onClick: () -> Unit,
    icon: OudsChipIcon,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null
) {
    OudsSuggestionChip(
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
 * A suggestion chip is a compact UI element used to present recommended or predictive options based on user input or context.
 * Often found in search bars, forms, or messaging interfaces, suggestion chips help users quickly select from relevant suggestions.
 * They are typically non-selected by default and can be tapped or clicked to apply the suggestion, streamlining user input and enhancing usability.
 *
 * This version of the chip uses the *text + icon* layout which combines text with an icon to enhance clarity and recognition.
 * Ideal when a visual cue helps reinforce the filter’s meaning.
 * Other layouts are available for this component: *text only* and *icon only*.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://unified-design-system.orange.com/472794e18/p/77fdea-chip/t/2d71fd4bb4)
 *
 * > Design version: 1.3.0
 *
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
 * @sample com.orange.ouds.core.component.samples.OudsSuggestionChipTextAndIconSample
 */
@Composable
fun OudsSuggestionChip(
    onClick: () -> Unit,
    label: String,
    icon: OudsChipIcon,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null
) {
    OudsSuggestionChip(
        onClick = onClick,
        nullableLabel = label,
        nullableIcon = icon,
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource
    )
}

@Composable
@JvmName("OudsSuggestionChipNullableLabelAndIcon")
private fun OudsSuggestionChip(
    onClick: () -> Unit,
    nullableLabel: String?,
    nullableIcon: OudsChipIcon?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null
) {
    OudsChip(
        selectable = false,
        selected = false,
        onClick = onClick,
        label = nullableLabel,
        icon = nullableIcon,
        iconPosition = OudsChipIconPosition.Start,
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource
    )
}

@PreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsSuggestionChip(@PreviewParameter(OudsSuggestionChipPreviewParameterProvider::class) parameter: OudsSuggestionChipPreviewParameter) {
    PreviewOudsSuggestionChip(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
fun PreviewOudsSuggestionChip(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsSuggestionChipPreviewParameter
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        val label = if (hasLabel) "Label" else null
        val icon = if (hasIcon) OudsChipIcon(Icons.Filled.FavoriteBorder, "") else null
        PreviewEnumEntries<OudsChipState>(columnCount = 3) {
            OudsSuggestionChip(nullableIcon = icon, nullableLabel = label, onClick = {})
        }
    }
}

data class OudsSuggestionChipPreviewParameter(
    val hasLabel: Boolean,
    val hasIcon: Boolean
)

class OudsSuggestionChipPreviewParameterProvider :
    BasicPreviewParameterProvider<OudsSuggestionChipPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsSuggestionChipPreviewParameter>
    get() = listOf(
        OudsSuggestionChipPreviewParameter(hasLabel = true, hasIcon = true),
        OudsSuggestionChipPreviewParameter(hasLabel = true, hasIcon = false),
        OudsSuggestionChipPreviewParameter(hasLabel = false, hasIcon = true)
    )

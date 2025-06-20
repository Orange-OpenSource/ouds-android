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

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.PreviewStates
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider

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

@Composable
fun OudsSuggestionChip(
    onClick: () -> Unit,
    icon: OudsChip.Icon,
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

@Composable
fun OudsSuggestionChip(
    onClick: () -> Unit,
    label: String,
    icon: OudsChip.Icon,
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
    nullableIcon: OudsChip.Icon?,
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
        iconPosition = OudsChip.IconPosition.Start,
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource
    )
}

@PreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsSuggestionChip(@PreviewParameter(OudsSuggestionChipPreviewParameterProvider::class) parameter: OudsSuggestionChipPreviewParameter) {
    PreviewOudsSuggestionChip(darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsSuggestionChip(darkThemeEnabled: Boolean, parameter: OudsSuggestionChipPreviewParameter) =
    OudsPreview(darkThemeEnabled = darkThemeEnabled) {
        with(parameter) {
            val label = if (hasLabel) "Label" else null
            val icon = if (hasIcon) OudsChip.Icon(Icons.Filled.FavoriteBorder, "") else null
            PreviewStates<OudsChip.State>(columnCount = 3) {
                OudsSuggestionChip(nullableIcon = icon, nullableLabel = label, onClick = {})
            }
        }
    }

internal data class OudsSuggestionChipPreviewParameter(
    val hasLabel: Boolean,
    val hasIcon: Boolean
)

internal class OudsSuggestionChipPreviewParameterProvider :
    BasicPreviewParameterProvider<OudsSuggestionChipPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsSuggestionChipPreviewParameter>
    get() = listOf(
        OudsSuggestionChipPreviewParameter(hasLabel = true, hasIcon = true),
        OudsSuggestionChipPreviewParameter(hasLabel = true, hasIcon = false),
        OudsSuggestionChipPreviewParameter(hasLabel = false, hasIcon = true)
    )

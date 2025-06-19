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
import com.orange.ouds.core.utilities.PreviewEnumEntries
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider

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
@JvmName("OudsChipNullableLabelAndIcon")
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
        selected = selected,
        onClick = onClick,
        nullableLabel = nullableLabel,
        nullableIcon = nullableIcon,
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource
    )
}

@PreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsFilterChip(@PreviewParameter(OudsFilterChipPreviewParameterProvider::class) parameter: OudsFilterChipPreviewParameter) {
    PreviewOudsFilterChip(darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsFilterChip(darkThemeEnabled: Boolean, parameter: OudsFilterChipPreviewParameter) = OudsPreview(darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        val label = if (hasLabel) "Label" else null
        val icon = if (hasIcon) OudsChip.Icon(Icons.Filled.FavoriteBorder, "") else null
        PreviewEnumEntries<OudsChip.State>(columnCount = 3) {
            OudsFilterChip(selected = selected, nullableIcon = icon, nullableLabel = label, onClick = {})
        }
    }
}

internal data class OudsFilterChipPreviewParameter(
    val selected: Boolean,
    val hasLabel: Boolean,
    val hasIcon: Boolean
)

internal class OudsFilterChipPreviewParameterProvider : BasicPreviewParameterProvider<OudsFilterChipPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsFilterChipPreviewParameter>
    get() = listOf(
        OudsFilterChipPreviewParameter(selected = true, hasLabel = true, hasIcon = true),
        OudsFilterChipPreviewParameter(selected = false, hasLabel = true, hasIcon = true),
        OudsFilterChipPreviewParameter(selected = true, hasLabel = true, hasIcon = false),
        OudsFilterChipPreviewParameter(selected = false, hasLabel = true, hasIcon = false),
        OudsFilterChipPreviewParameter(selected = true, hasLabel = false, hasIcon = true),
        OudsFilterChipPreviewParameter(selected = false, hasLabel = false, hasIcon = true),
    )

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

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ouds.core.utilities.CheckerboardPainter
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.OudsPreviewDevice
import com.orange.ouds.core.utilities.OudsPreviewLightDark
import com.orange.ouds.core.utilities.PreviewEnumEntries
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.core.utilities.mapSettings
import com.orange.ouds.foundation.ExperimentalOudsApi
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.theme.OudsThemeContract

typealias OudsSmallCardItemLeading = OudsSmallListItemLeading
typealias OudsSmallCardItemTrailing = OudsSmallListItemTrailing

/**
 * TODO Static Small Card Item
 */
@ExperimentalOudsApi
@Composable
fun OudsSmallCardItem(
    label: String,
    modifier: Modifier = Modifier,
    contentAlignment: OudsListItemContentAlignment = OudsListItemDefaults.ContentAlignment,
    decoration: OudsListItemDecoration = OudsCardItemDefaults.Decoration,
    description: String? = null,
    leading: OudsSmallCardItemLeading? = null,
    trailing: OudsSmallCardItemTrailing? = null,
    helperText: String? = null,
    boldLabel: Boolean = false,
    enabled: Boolean = true,
) {
    OudsListItem(
        size = OudsListItemSize.Small,
        label = label,
        nullableOnClick = null,
        modifier = modifier,
        nullableIndicator = null,
        contentAlignment = contentAlignment,
        overline = null,
        extraLabel = null,
        description = description,
        leading = leading,
        trailing = trailing,
        decoration = decoration,
        helperText = helperText,
        boldLabel = boldLabel,
        enabled = enabled,
        card = true
    )
}

/**
 * TODO Navigation Small Card Item
 */
@ExperimentalOudsApi
@Composable
fun OudsSmallCardItem(
    label: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    indicator: OudsListItemIndicator = OudsListItemDefaults.Indicator,
    contentAlignment: OudsListItemContentAlignment = OudsListItemDefaults.ContentAlignment,
    decoration: OudsListItemDecoration = OudsCardItemDefaults.Decoration,
    description: String? = null,
    leading: OudsSmallCardItemLeading? = null,
    trailing: OudsSmallCardItemTrailing? = null,
    helperText: String? = null,
    boldLabel: Boolean = false,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null
) {
    OudsListItem(
        size = OudsListItemSize.Small,
        label = label,
        nullableOnClick = onClick,
        modifier = modifier,
        nullableIndicator = indicator,
        contentAlignment = contentAlignment,
        overline = null,
        extraLabel = null,
        description = description,
        leading = leading,
        trailing = trailing,
        decoration = decoration,
        helperText = helperText,
        boldLabel = boldLabel,
        enabled = enabled,
        card = true,
        interactionSource = interactionSource
    )
}

@OudsPreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsSmallStaticCardItem(@PreviewParameter(OudsSmallCardItemPreviewParameterProvider::class) parameter: OudsSmallCardItemPreviewParameter) {
    PreviewOudsSmallStaticCardItem(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsSmallStaticCardItem(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsSmallCardItemPreviewParameter
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        OudsSmallCardItem(
            label = label,
            decoration = decoration,
            description = description,
            helperText = helperText,
            leading = leading,
            trailing = trailing,
            enabled = enabled
        )
    }
}

@Preview(name = "Light", heightDp = 700, device = OudsPreviewDevice) // TODO set height in OudsPreviewableComponent
@Preview(
    name = "Dark",
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL,
    heightDp = 700, // TODO set height in OudsPreviewableComponent
    device = OudsPreviewDevice
)
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsNavigationSmallCardItem(@PreviewParameter(OudsSmallCardItemPreviewParameterProvider::class) parameter: OudsSmallCardItemPreviewParameter) {
    PreviewOudsNavigationSmallCardItem(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsNavigationSmallCardItem(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsSmallCardItemPreviewParameter
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        PreviewEnumEntries<OudsListItemState>(maxEnumEntriesInEachRow = 1) {
            OudsSmallCardItem(
                onClick = {},
                decoration = decoration,
                indicator = indicator,
                label = label,
                description = description,
                helperText = helperText,
                contentAlignment = contentAlignment,
                leading = leading,
                trailing = trailing,
                enabled = enabled,
                interactionSource = remember { MutableInteractionSource() }
            )
        }
    }
}

@Preview(name = "Light", heightDp = 700, device = OudsPreviewDevice) // TODO set height in OudsPreviewableComponent
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsNavigationSmallCardItemWithRoundedCorners() {
    PreviewOudsNavigationSmallCardItemWithRoundedCorners(theme = getPreviewTheme())
}

@Composable
internal fun PreviewOudsNavigationSmallCardItemWithRoundedCorners(theme: OudsThemeContract) =
    OudsPreview(theme = theme.mapSettings { it.copy(roundedCornerCardItems = true) }) {
        PreviewEnumEntries<OudsListItemState>(maxEnumEntriesInEachRow = 1) {
            OudsSmallCardItem(
                onClick = {},
                label = "Label",
                description = "Description",
                helperText = "Helper text",
            )
        }
    }

@Preview(name = "Dark", uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL, heightDp = 700, device = OudsPreviewDevice) // TODO set height in OudsPreviewableComponent
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsStaticSmallCardItemWithRoundedCorners() {
    PreviewOudsStaticSmallCardItemWithRoundedCorners(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme())
}

@Composable
internal fun PreviewOudsStaticSmallCardItemWithRoundedCorners(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean
) = OudsPreview(theme = theme.mapSettings { it.copy(roundedCornerCardItems = true) }, darkThemeEnabled = darkThemeEnabled) {
        PreviewEnumEntries<OudsListItemState>(maxEnumEntriesInEachRow = 1) {
            OudsSmallCardItem(
                label = "Label",
                decoration = OudsListItemDecoration.Outlined,
                description = "Description",
                helperText = "Helper text",
            )
        }
    }

internal data class OudsSmallCardItemPreviewParameter(
    val label: String,
    val decoration: OudsListItemDecoration = OudsCardItemDefaults.Decoration,
    val indicator: OudsListItemIndicator = OudsListItemDefaults.Indicator,
    val description: String? = null,
    val helperText: String? = null,
    val contentAlignment: OudsListItemContentAlignment = OudsListItemContentAlignment.Center,
    val leading: OudsSmallListItemLeading? = null,
    val trailing: OudsSmallListItemTrailing? = null,
    val enabled: Boolean = true
)

internal class OudsSmallCardItemPreviewParameterProvider :
    BasicPreviewParameterProvider<OudsSmallCardItemPreviewParameter>(*smallListItemPreviewParameterValues.toTypedArray())

private val smallListItemPreviewParameterValues: List<OudsSmallCardItemPreviewParameter>
    get() {
        val label = "Label"
        val description = "Description"
        val helperText = "Helper text"
        return listOf(
            OudsSmallCardItemPreviewParameter(
                label = label,
                decoration = OudsListItemDecoration.Outlined,
                description = description,
                helperText = helperText,
                leading = OudsSmallListItemLeading.Icon.Info,
                trailing = OudsSmallListItemTrailing.Icon(Icons.Outlined.FavoriteBorder, "")
            ),
            OudsSmallCardItemPreviewParameter(
                label = label,
                indicator = OudsListItemIndicator.External,
                contentAlignment = OudsListItemContentAlignment.Top,
                leading = OudsSmallListItemLeading.Icon(Icons.Outlined.FavoriteBorder, ""),
                trailing = OudsSmallListItemTrailing.Text(label = label, style = OudsListItemTextStyle.LabelStrong)
            ),
            OudsSmallCardItemPreviewParameter(
                label = label,
                indicator = OudsListItemIndicator.Previous,
                decoration = OudsListItemDecoration.BackgroundOnInteraction(divider = true),
                leading = OudsSmallListItemLeading.Image(CheckerboardPainter, "", OudsListItemImageFormat.Panoramic),
                trailing = OudsSmallListItemTrailing.Image(CheckerboardPainter, "", OudsListItemImageFormat.Square)
            )
        )
    }
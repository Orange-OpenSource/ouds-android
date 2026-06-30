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

/**
 * TODO Static Card Item
 */
@ExperimentalOudsApi
@Composable
fun OudsCardItem(
    label: String,
    modifier: Modifier = Modifier,
    contentAlignment: OudsListItemContentAlignment = OudsListItemDefaults.ContentAlignment,
    decoration: OudsListItemDecoration = OudsCardItemDefaults.Decoration,
    overline: String? = null,
    extraLabel: String? = null,
    description: String? = null,
    leading: OudsListItemLeading? = null,
    trailing: OudsListItemTrailing? = null,
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
        overline = overline,
        extraLabel = extraLabel,
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
 * TODO Navigation Card Item
 */
@ExperimentalOudsApi
@Composable
fun OudsCardItem(
    label: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    indicator: OudsListItemIndicator = OudsListItemDefaults.Indicator,
    contentAlignment: OudsListItemContentAlignment = OudsListItemDefaults.ContentAlignment,
    decoration: OudsListItemDecoration = OudsCardItemDefaults.Decoration,
    overline: String? = null,
    extraLabel: String? = null,
    description: String? = null,
    leading: OudsListItemLeading? = null,
    trailing: OudsListItemTrailing? = null,
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
        overline = overline,
        extraLabel = extraLabel,
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

/**
 * Default values for [OudsCardItem].
 */
object OudsCardItemDefaults {
    /**
     * Default decoration of an [OudsCardItem].
     */
    val Decoration = OudsListItemDecoration.Background(true)
}

@OudsPreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsStaticCardItem(@PreviewParameter(OudsCardItemPreviewParameterProvider::class) parameter: OudsCardItemPreviewParameter) {
    PreviewOudsStaticCardItem(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsStaticCardItem(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsCardItemPreviewParameter
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        OudsCardItem(
            label = label,
            decoration = decoration,
            overline = overline,
            extraLabel = extraLabel,
            description = description,
            helperText = helperText,
            contentAlignment = contentAlignment,
            leading = leading,
            trailing = trailing,
            enabled = enabled
        )
    }
}

@Preview(name = "Light", heightDp = 880, device = OudsPreviewDevice) // TODO set height in OudsPreviewableComponent
@Preview(
    name = "Dark",
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL,
    heightDp = 1000, // TODO set height in OudsPreviewableComponent
    device = OudsPreviewDevice
)
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsNavigationCardItem(@PreviewParameter(OudsCardItemPreviewParameterProvider::class) parameter: OudsCardItemPreviewParameter) {
    PreviewOudsNavigationCardItem(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsNavigationCardItem(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsCardItemPreviewParameter
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        PreviewEnumEntries<OudsListItemState>(maxEnumEntriesInEachRow = 1) {
            OudsCardItem(
                onClick = {},
                decoration = decoration,
                indicator = indicator,
                label = label,
                overline = overline,
                extraLabel = extraLabel,
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

@Preview(name = "Light", heightDp = 880, device = OudsPreviewDevice) // TODO set height in OudsPreviewableComponent
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsNavigationCardItemWithRoundedCorners() {
    PreviewOudsNavigationCardItemWithRoundedCorners(theme = getPreviewTheme())
}

@Composable
internal fun PreviewOudsNavigationCardItemWithRoundedCorners(theme: OudsThemeContract) =
    OudsPreview(theme = theme.mapSettings { it.copy(roundedCornerCardItems = true) }) {
        PreviewEnumEntries<OudsListItemState>(maxEnumEntriesInEachRow = 1) {
            OudsCardItem(
                onClick = {},
                label = "Label",
                description = "Description",
                overline = "Overline",
                extraLabel = "Extra label",
                helperText = "Helper text",
            )
        }
    }

@Preview(
    name = "Dark",
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL,
    heightDp = 880,
    device = OudsPreviewDevice
) // TODO set height in OudsPreviewableComponent
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsStaticCardItemWithRoundedCorners() {
    PreviewOudsStaticCardItemWithRoundedCorners(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme())
}

@Composable
internal fun PreviewOudsStaticCardItemWithRoundedCorners(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean
) = OudsPreview(theme = theme.mapSettings { it.copy(roundedCornerCardItems = true) }, darkThemeEnabled = darkThemeEnabled) {
    PreviewEnumEntries<OudsListItemState>(maxEnumEntriesInEachRow = 1) {
        OudsCardItem(
            label = "Label",
            decoration = OudsListItemDecoration.Outlined,
            description = "Description",
            overline = "Overline",
            extraLabel = "Extra label",
            helperText = "Helper text",
        )
    }
}

internal data class OudsCardItemPreviewParameter(
    val label: String,
    val decoration: OudsListItemDecoration = OudsCardItemDefaults.Decoration,
    val indicator: OudsListItemIndicator = OudsListItemDefaults.Indicator,
    val contentAlignment: OudsListItemContentAlignment = OudsListItemContentAlignment.Center,
    val overline: String? = null,
    val extraLabel: String? = null,
    val description: String? = null,
    val leading: OudsListItemLeading? = null,
    val trailing: OudsListItemTrailing? = null,
    val helperText: String? = null,
    val enabled: Boolean = true
)

internal class OudsCardItemPreviewParameterProvider :
    BasicPreviewParameterProvider<OudsCardItemPreviewParameter>(*cardItemPreviewParameterValues.toTypedArray())

private val cardItemPreviewParameterValues: List<OudsCardItemPreviewParameter>
    get() {
        val label = "Label"
        val overline = "Overline"
        val extraLabel = "Extra label"
        val description = "Description"
        val helperText = "Helper text"
        return listOf(
            OudsCardItemPreviewParameter(
                label = label,
                overline = overline,
                extraLabel = extraLabel,
                description = description,
                helperText = helperText,
                leading = OudsListItemLeading.Icon.Info(),
                trailing = OudsListItemTrailing.Icon(Icons.Outlined.FavoriteBorder, ""),
            ),
            OudsCardItemPreviewParameter(
                label = label,
                decoration = OudsListItemDecoration.Outlined,
                indicator = OudsListItemIndicator.External,
                contentAlignment = OudsListItemContentAlignment.Top,
                leading = OudsListItemLeading.Icon(Icons.Outlined.FavoriteBorder, ""),
                trailing = OudsListItemTrailing.Text(label = label, extraLabel = extraLabel),
            ),
            OudsCardItemPreviewParameter(
                label = label,
                decoration = OudsListItemDecoration.BackgroundOnInteraction(divider = false),
                indicator = OudsListItemIndicator.Previous,
                overline = overline,
                extraLabel = extraLabel,
                description = description,
                trailing = OudsListItemTrailing.Image(CheckerboardPainter, "", OudsListItemImageSize.ExtraLarge, OudsListItemImageFormat.Panoramic),
                leading = OudsListItemLeading.Image(CheckerboardPainter, "", OudsListItemImageSize.Medium, OudsListItemImageFormat.Square)
            )
        )
    }
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.OudsPreviewDevice
import com.orange.ouds.core.utilities.OudsPreviewLightDark
import com.orange.ouds.core.utilities.OudsPreviewableComponent
import com.orange.ouds.core.utilities.PreviewEnumEntries
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.core.utilities.mapSettings
import com.orange.ouds.foundation.ExperimentalOudsApi
import com.orange.ouds.theme.OudsThemeContract

/**
 * TODO update description when available and add version and guideline link
 *
 * Static small card item displays non-clickable information in a compact card format.
 *
 * A static small card item combines the compact size of a small list item with the visual
 * emphasis of a card. It is ideal for displaying condensed, grouped content in a contained
 * format, such as compact feature cards, quick access tiles, or dense information grids.
 * Unlike the standard card item, it omits overline and extra label to maintain a smaller footprint.
 *
 * @param label The main label of the small card item.
 * @param modifier [Modifier] applied to the layout of the small card item.
 * @param contentAlignment Controls the vertical alignment of the content. Defaults to [OudsListItemContentAlignment.CenterVertically].
 * @param decoration The decoration style of the card. Defaults to [OudsListItemDecoration.Background] (with divider).
 * @param description Optional text displayed below the [label].
 * @param leading Optional leading content such as an icon or image displayed at the start of the small card item.
 * @param trailing Optional trailing content such as an icon, image, or text displayed at the end of the small card item.
 * @param helperText Optional helper text displayed below the small card item.
 * @param boldLabel Controls whether the label text is displayed in bold. Defaults to `false`.
 * @param enabled Controls the enabled state of the small card item. When `false`, the content is displayed in a disabled state. Defaults to `true`.
 * @param interactionSource Optional hoisted [MutableInteractionSource] for observing and emitting interactions for this small card item.
 *
 * @sample com.orange.ouds.core.component.samples.OudsStaticSmallCardItemSample
 * @sample com.orange.ouds.core.component.samples.OudsSmallCardItemWithImageSample
 * @sample com.orange.ouds.core.component.samples.OudsSmallCardItemWithUntintedIconSample
 */
@ExperimentalOudsApi
@Composable
fun OudsSmallCardItem(
    label: String,
    modifier: Modifier = Modifier,
    contentAlignment: OudsListItemContentAlignment = OudsListItemDefaults.ContentAlignment,
    decoration: OudsListItemDecoration = OudsCardItemDefaults.Decoration,
    description: String? = null,
    leading: OudsSmallListItemLeading? = null,
    trailing: OudsSmallListItemTrailing? = null,
    helperText: String? = null,
    boldLabel: Boolean = false,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null
) {
    OudsListItem(
        size = OudsListItemSize.Small,
        label = label,
        onClick = null,
        modifier = modifier,
        indicator = null,
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

/**
 * TODO update description when available and add version and guideline link
 *
 * Navigation small card item allows users to navigate to another screen or perform an action in a compact card format.
 *
 * A navigation small card item combines the compact size of a small list item with the visual
 * emphasis of a card, while remaining interactive. It is ideal for compact, navigable feature cards,
 * quick action tiles, or dense interactive grids. The indicator type can be customized to show
 * forward navigation, backward navigation, or external links. Unlike the standard card item,
 * it omits overline and extra label to maintain a smaller footprint while remaining clickable.
 *
 * @param label The main label of the small card item.
 * @param modifier [Modifier] applied to the layout of the small card item.
 * @param onClick Callback invoked when the small card item is clicked.
 * @param indicator The navigation indicator to display. Defaults to [OudsListItemIndicator.Next].
 * @param contentAlignment Controls the vertical alignment of the content. Defaults to [OudsListItemContentAlignment.CenterVertically].
 * @param decoration The decoration style of the card. Defaults to [OudsListItemDecoration.Background] (with divider).
 * @param description Optional text displayed below the [label].
 * @param leading Optional leading content such as an icon or image displayed at the start of the small card item.
 * @param trailing Optional trailing content such as an icon, image, or text displayed at the end of the small card item.
 * @param helperText Optional helper text displayed below the small card item.
 * @param boldLabel Controls whether the label text is displayed in bold. Defaults to `false`.
 * @param enabled Controls the enabled state of the small card item. When `false`, the item is not clickable and content is displayed in a disabled state. Defaults to `true`.
 * @param interactionSource Optional hoisted [MutableInteractionSource] for observing and emitting interactions for this small card item.
 *
 * @sample com.orange.ouds.core.component.samples.OudsNavigationSmallCardItemSample
 * @sample com.orange.ouds.core.component.samples.OudsSmallCardItemWithImageSample
 * @sample com.orange.ouds.core.component.samples.OudsSmallCardItemWithUntintedIconSample
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
    leading: OudsSmallListItemLeading? = null,
    trailing: OudsSmallListItemTrailing? = null,
    helperText: String? = null,
    boldLabel: Boolean = false,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null
) {
    OudsListItem(
        size = OudsListItemSize.Small,
        label = label,
        onClick = onClick,
        modifier = modifier,
        indicator = indicator,
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
private fun PreviewOudsStaticSmallCardItem(@PreviewParameter(OudsSmallCardItemPreviewParameterProvider::class) parameter: OudsListItemPreviewParameter<OudsSmallListItemLeading, OudsSmallListItemTrailing>) {
    PreviewOudsStaticSmallCardItem(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsStaticSmallCardItem(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsListItemPreviewParameter<OudsSmallListItemLeading, OudsSmallListItemTrailing>
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

@OudsPreview
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsStaticSmallCardItemWithRoundedCorners(@PreviewParameter(OudsCardItemWithRoundedCornersParameterProvider::class) decoration: OudsListItemDecoration) {
    PreviewOudsStaticSmallCardItemWithRoundedCorners(theme = getPreviewTheme(), decoration = decoration)
}

@Composable
internal fun PreviewOudsStaticSmallCardItemWithRoundedCorners(
    theme: OudsThemeContract,
    decoration: OudsListItemDecoration
) = OudsPreview(theme = theme.mapSettings { it.copy(roundedCornerCardItems = true) }) {
    OudsSmallCardItem(
        label = "Label",
        decoration = decoration,
        description = "Description",
        helperText = "Helper text",
    )
}

@Preview(name = "Light", heightDp = OudsPreviewableComponent.SmallCardItem.Navigation.PreviewHeightDp, device = OudsPreviewDevice)
@Preview(
    name = "Dark",
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL,
    heightDp = OudsPreviewableComponent.SmallCardItem.Navigation.PreviewHeightDp,
    device = OudsPreviewDevice
)
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsNavigationSmallCardItem(@PreviewParameter(OudsSmallCardItemPreviewParameterProvider::class) parameter: OudsListItemPreviewParameter<OudsSmallListItemLeading, OudsSmallListItemTrailing>) {
    PreviewOudsNavigationSmallCardItem(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsNavigationSmallCardItem(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsListItemPreviewParameter<OudsSmallListItemLeading, OudsSmallListItemTrailing>
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
                boldLabel = boldLabel,
                enabled = enabled
            )
        }
    }
}

@Preview(name = "Light", heightDp = OudsPreviewableComponent.SmallCardItem.Navigation.PreviewHeightDp, device = OudsPreviewDevice)
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsNavigationSmallCardItemWithRoundedCorners(@PreviewParameter(OudsCardItemWithRoundedCornersParameterProvider::class) decoration: OudsListItemDecoration) {
    PreviewOudsNavigationSmallCardItemWithRoundedCorners(theme = getPreviewTheme(), decoration = decoration)
}

@Composable
internal fun PreviewOudsNavigationSmallCardItemWithRoundedCorners(theme: OudsThemeContract, decoration: OudsListItemDecoration) =
    OudsPreview(theme = theme.mapSettings { it.copy(roundedCornerCardItems = true) }) {
        PreviewEnumEntries<OudsListItemState>(maxEnumEntriesInEachRow = 1) {
            OudsSmallCardItem(
                onClick = {},
                label = "Label",
                decoration = decoration,
                description = "Description",
                helperText = "Helper text",
            )
        }
    }

internal class OudsSmallCardItemPreviewParameterProvider : OudsBasicListItemPreviewParameterProvider<OudsSmallListItemLeading, OudsSmallListItemTrailing>(
    leading = smallListItemPreviewParameterLeading,
    trailing = smallListItemPreviewParameterTrailing,
    decoration = { index ->
        when (index) {
            0 -> OudsListItemDecoration.Outlined
            1 -> OudsCardItemDefaults.Decoration
            else -> OudsListItemDecoration.BackgroundOnInteraction(divider = false)
        }
    }
)

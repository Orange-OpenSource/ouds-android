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
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.OudsPreviewDevice
import com.orange.ouds.core.utilities.OudsPreviewLightDark
import com.orange.ouds.core.utilities.OudsPreviewableComponent
import com.orange.ouds.core.utilities.PreviewEnumEntries
import com.orange.ouds.core.utilities.PreviewPaddingDefault
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.core.utilities.mapSettings
import com.orange.ouds.foundation.ExperimentalOudsApi
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.theme.OudsThemeContract

/**
 * TODO update description when available and add version and guideline link
 *
 * Static card item displays non-clickable information in a card format with visual emphasis.
 *
 * A static card item can be used to present read-only information in a contained format.
 * Cards are ideal for displaying grouped content like product cards, destination highlights, or
 * feature summaries. The card supports various decorations outlined, background with or without divider.
 *
 * @param label The main label of the card item.
 * @param modifier [Modifier] applied to the layout of the card item.
 * @param verticalAlignment Controls the vertical alignment of the content. Defaults to [OudsListItemVerticalAlignment.CenterVertically].
 * @param decoration The decoration style of the card. Defaults to [OudsListItemDecoration.Background] (with divider).
 * @param overline Optional text displayed above the label.
 * @param extraLabel Optional strong accompanying label for the main label, displayed between the [label] and the [description].
 * @param description Optional text displayed below the [label] and [extraLabel].
 * @param leading Optional leading content such as an icon or image displayed at the start of the card item.
 * @param trailing Optional trailing content such as an icon, image, or text displayed at the end of the card item.
 * @param helperText Optional helper text displayed below the card item.
 * @param boldLabel Controls whether the label text is displayed in bold. Defaults to `false`.
 * @param enabled Controls the enabled state of the card item. When `false`, the content is displayed in a disabled state. Defaults to `true`.
 * @param interactionSource Optional hoisted [MutableInteractionSource] for observing and emitting interactions for this card item.
 *
 * @sample com.orange.ouds.core.component.samples.OudsStaticCardItemSample
 * @sample com.orange.ouds.core.component.samples.OudsCardItemWithAllElementsSample
 * @sample com.orange.ouds.core.component.samples.OudsCardItemWithImageSample
 * @sample com.orange.ouds.core.component.samples.OudsCardItemWithUntintedIconSample
 */
@ExperimentalOudsApi
@Composable
fun OudsCardItem(
    label: String,
    modifier: Modifier = Modifier,
    verticalAlignment: OudsListItemVerticalAlignment = OudsListItemDefaults.VerticalAlignment,
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
        onClick = null,
        modifier = modifier,
        indicator = null,
        verticalAlignment = verticalAlignment,
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
 * TODO update description when available and add version and guideline link
 *
 * Navigation card item allows users to navigate to another screen or perform an action in a card format.
 *
 * A navigation card item is clickable and presented in a card format with visual emphasis.
 * It can be used for navigable product cards, destination selections, or feature highlights.
 * The indicator type can be customized to show forward navigation, backward navigation, or
 * external links. The card supports various decorations to adapt to different visual styles:
 * outlined, background with or without divider.
 *
 * @param label The main label of the card item.
 * @param modifier [Modifier] applied to the layout of the card item.
 * @param onClick Callback invoked when the card item is clicked.
 * @param indicator The navigation indicator to display. Defaults to [OudsListItemIndicator.Next].
 * @param verticalAlignment Controls the vertical alignment of the content. Defaults to [OudsListItemVerticalAlignment.CenterVertically].
 * @param decoration The decoration style of the card. Defaults to [OudsListItemDecoration.Background] (with divider).
 * @param overline Optional text displayed above the label.
 * @param extraLabel Optional strong accompanying label for the main label, displayed between the [label] and the [description].
 * @param description Optional text displayed below the [label] and [extraLabel].
 * @param leading Optional leading content such as an icon or image displayed at the start of the card item.
 * @param trailing Optional trailing content such as an icon, image, or text displayed at the end of the card item.
 * @param helperText Optional helper text displayed below the card item.
 * @param boldLabel Controls whether the label text is displayed in bold. Defaults to `false`.
 * @param enabled Controls the enabled state of the card item. When `false`, the item is not clickable and content is displayed in a disabled state. Defaults to `true`.
 * @param interactionSource Optional hoisted [MutableInteractionSource] for observing and emitting interactions for this card item.
 *
 * @sample com.orange.ouds.core.component.samples.OudsNavigationCardItemSample
 * @sample com.orange.ouds.core.component.samples.OudsCardItemWithAllElementsSample
 * @sample com.orange.ouds.core.component.samples.OudsCardItemWithImageSample
 * @sample com.orange.ouds.core.component.samples.OudsCardItemWithUntintedIconSample
 */
@ExperimentalOudsApi
@Composable
fun OudsCardItem(
    label: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    indicator: OudsListItemIndicator = OudsListItemDefaults.Indicator,
    verticalAlignment: OudsListItemVerticalAlignment = OudsListItemDefaults.VerticalAlignment,
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
        onClick = onClick,
        modifier = modifier,
        indicator = indicator,
        verticalAlignment = verticalAlignment,
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
private fun PreviewOudsStaticCardItem(@PreviewParameter(OudsCardItemPreviewParameterProvider::class) parameter: OudsListItemPreviewParameter<OudsListItemLeading, OudsListItemTrailing>) {
    PreviewOudsStaticCardItem(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsStaticCardItem(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsListItemPreviewParameter<OudsListItemLeading, OudsListItemTrailing>
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        OudsCardItem(
            label = label,
            decoration = decoration,
            overline = overline,
            extraLabel = extraLabel,
            description = description,
            helperText = helperText,
            verticalAlignment = verticalAlignment,
            leading = leading,
            trailing = trailing,
            boldLabel = boldLabel,
            enabled = enabled
        )
    }
}

@OudsPreview
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsStaticCardItemWithRoundedCorners(@PreviewParameter(OudsCardItemWithRoundedCornersParameterProvider::class) decoration: OudsListItemDecoration) {
    PreviewOudsStaticCardItemWithRoundedCorners(theme = getPreviewTheme(), decoration = decoration)
}

@Composable
internal fun PreviewOudsStaticCardItemWithRoundedCorners(theme: OudsThemeContract, decoration: OudsListItemDecoration) =
    OudsPreview(theme = theme.mapSettings { it.copy(roundedCornerCardItems = true) }) {
        OudsCardItem(
            modifier = Modifier.padding(PreviewPaddingDefault),
            label = "Label",
            decoration = decoration,
            description = "Description",
            overline = "Overline",
            extraLabel = "Extra label",
            helperText = "Helper text",
        )
    }

@Preview(name = "Light", heightDp = OudsPreviewableComponent.CardItem.Navigation.PreviewHeightDp, device = OudsPreviewDevice)
@Preview(
    name = "Dark",
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL,
    heightDp = OudsPreviewableComponent.CardItem.Navigation.PreviewHeightDp,
    device = OudsPreviewDevice
)
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsNavigationCardItem(@PreviewParameter(OudsCardItemPreviewParameterProvider::class) parameter: OudsListItemPreviewParameter<OudsListItemLeading, OudsListItemTrailing>) {
    PreviewOudsNavigationCardItem(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsNavigationCardItem(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsListItemPreviewParameter<OudsListItemLeading, OudsListItemTrailing>
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
                verticalAlignment = verticalAlignment,
                leading = leading,
                trailing = trailing,
                enabled = enabled
            )
        }
    }
}

@Preview(name = "Light", heightDp = OudsPreviewableComponent.CardItem.Navigation.PreviewHeightDp, device = OudsPreviewDevice)
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsNavigationCardItemWithRoundedCorners(@PreviewParameter(OudsCardItemWithRoundedCornersParameterProvider::class) decoration: OudsListItemDecoration) {
    PreviewOudsNavigationCardItemWithRoundedCorners(theme = getPreviewTheme(), decoration = decoration)
}

@Composable
internal fun PreviewOudsNavigationCardItemWithRoundedCorners(theme: OudsThemeContract, decoration: OudsListItemDecoration) =
    OudsPreview(theme = theme.mapSettings { it.copy(roundedCornerCardItems = true) }) {
        PreviewEnumEntries<OudsListItemState>(maxEnumEntriesInEachRow = 1) {
            OudsCardItem(
                onClick = {},
                label = "Label",
                decoration = decoration,
                description = "Description",
                overline = "Overline",
                extraLabel = "Extra label",
                helperText = "Helper text",
            )
        }
    }

internal class OudsCardItemPreviewParameterProvider : OudsBasicListItemPreviewParameterProvider<OudsListItemLeading, OudsListItemTrailing>(
    leading = listItemPreviewParameterLeading,
    trailing = listItemPreviewParameterTrailing,
    decoration = { index ->
        when (index) {
            0 -> OudsCardItemDefaults.Decoration
            1 -> OudsListItemDecoration.Outlined
            else -> OudsListItemDecoration.BackgroundOnInteraction(divider = false)
        }
    }
)

internal class OudsCardItemWithRoundedCornersParameterProvider :
    BasicPreviewParameterProvider<OudsListItemDecoration>(OudsListItemDecoration.Outlined, OudsListItemDecoration.Background(true))

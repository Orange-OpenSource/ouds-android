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

@file:OptIn(RestrictedOudsApi::class)

package com.orange.ouds.core.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.R
import com.orange.ouds.core.component.common.bottomBorder
import com.orange.ouds.core.component.common.outerBorder
import com.orange.ouds.core.component.content.OudsComponentContent
import com.orange.ouds.core.component.content.OudsComponentIcon
import com.orange.ouds.core.component.content.OudsComponentImage
import com.orange.ouds.core.component.content.OudsPolymorphicComponentContent
import com.orange.ouds.core.component.content.PolymorphicContent
import com.orange.ouds.core.extensions.InteractionState
import com.orange.ouds.core.extensions.collectInteractionStateAsState
import com.orange.ouds.core.theme.LocalThemeSettings
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.takeUnlessHairline
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.CheckerboardPainter
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.OudsPreviewDevice
import com.orange.ouds.core.utilities.OudsPreviewLightDark
import com.orange.ouds.core.utilities.OudsPreviewableComponent
import com.orange.ouds.core.utilities.PreviewEnumEntries
import com.orange.ouds.core.utilities.getPreviewEnumEntry
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.core.utilities.rememberRainbowHeartPainter
import com.orange.ouds.foundation.ExperimentalOudsApi
import com.orange.ouds.foundation.RestrictedOudsApi
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.theme.OudsThemeContract

/**
 * TODO update description when available and add guideline link
 *
 * Static list item displays non-clickable information in a structured format.
 * They are designed to be stacked within a list, with no spacing between the elements.
 *
 * A static list item can be used to present read-only information such as contact details,
 * product specifications, or settings values.
 * These items are designed to be stacked within a list, with no spacing between the elements.
 *
 * @see [OudsCardItem] If you need spaced items displayed in a card format (with background or outlined).
 *
 * > Design name: Static List Item
 *
 * > Design version: 0.3.0
 *
 * @param label The main label of the list item.
 * @param modifier [Modifier] applied to the layout of the list item.
 * @param verticalAlignment Controls the vertical alignment of the content. Defaults to [OudsListItemVerticalAlignment.CenterVertically].
 * @param overline Optional text displayed above the label.
 * @param extraLabel Optional strong accompanying label for the main label, displayed between the [label] and the [description].
 * @param description Optional text displayed below the [label] and [extraLabel].
 * @param leading Optional leading content such as an icon or image displayed at the start of the list item.
 * @param trailing Optional trailing content such as an icon, image, or text displayed at the end of the list item.
 * @param divider Controls the display of a divider at the bottom of the list item. Defaults to `true`.
 * @param background Controls whether the list item has a background color. Defaults to `false`.
 * @param helperText Optional helper text displayed below the list item.
 * @param boldLabel Controls whether the label text is displayed in bold. Defaults to `false`.
 * @param belowTextContent Optional custom content displayed below the last text of the text container, between [leading] and [trailing].
 * @param bottomContent Optional custom content displayed at the bottom of the list item across the entire width, below the main row content.
 *   Use it when the information cannot be clearly represented by the standard [description] or [helperText] parameters.
 * @param enabled Controls the enabled state of the list item. When `false`, the content is displayed in a disabled state. Defaults to `true`.
 * @param edgeToEdge Controls the horizontal layout of the item. When `true`, the item is designed to span the full width of the screen or container. When `false`,
 *   it is adapted for use within constrained layouts or containers with their own padding. Defaults to `true`.
 * @param interactionSource Optional hoisted [MutableInteractionSource] for observing and emitting interactions for this list item.
 *
 * @sample com.orange.ouds.core.component.samples.OudsStaticListItemSample
 * @sample com.orange.ouds.core.component.samples.OudsListItemWithAllElementsSample
 * @sample com.orange.ouds.core.component.samples.OudsListItemWithLeadingImageAndTrailingTagSample
 * @sample com.orange.ouds.core.component.samples.OudsListItemWithLeadingIconAndTrailingBadgeSample
 * @sample com.orange.ouds.core.component.samples.OudsListItemWithUntintedIconSample
 */
@OptIn(ExperimentalVersionOverloading::class)
@ExperimentalOudsApi
@Composable
fun OudsListItem(
    label: String,
    modifier: Modifier = Modifier,
    verticalAlignment: OudsListItemVerticalAlignment = OudsListItemDefaults.VerticalAlignment,
    overline: String? = null,
    extraLabel: String? = null,
    description: String? = null,
    leading: OudsListItemLeading? = null,
    trailing: OudsListItemTrailing? = null,
    divider: Boolean = true,
    background: Boolean = false,
    helperText: String? = null,
    boldLabel: Boolean = false,
    @IntroducedAt("2.3-Unreleased") belowTextContent: @Composable (OudsListItemScope.() -> Unit)? = null,
    @IntroducedAt("2.3-Unreleased") bottomContent: @Composable (OudsListItemScope.() -> Unit)? = null,
    enabled: Boolean = true,
    edgeToEdge: Boolean = true,
    interactionSource: MutableInteractionSource? = null
) {
    OudsListItem(
        size = OudsListItemSize.Default,
        label = label,
        labelContent = null,
        onClick = null,
        modifier = modifier,
        indicator = null,
        verticalAlignment = verticalAlignment,
        overline = overline,
        extraLabel = extraLabel,
        description = description,
        leading = leading,
        trailing = trailing,
        decoration = listItemDecoration(background, divider),
        helperText = helperText,
        boldLabel = boldLabel,
        belowTextContent = belowTextContent,
        bottomContent = bottomContent,
        enabled = enabled,
        edgeToEdge = edgeToEdge,
        card = false,
        interactionSource = interactionSource
    )
}

/**
 * TODO update description when available and add guideline link
 *
 * Static list item displays non-clickable information in a structured format.
 * They are designed to be stacked within a list, with no spacing between the elements.
 *
 * A static list item can be used to present read-only information such as contact details,
 * product specifications, or settings values.
 * These items are designed to be stacked within a list, with no spacing between the elements.
 *
 * This version of the static list item allows to use a custom content in place of the label. Use it when the standard label cannot support the required
 * content structure.
 *
 * @see [OudsCardItem] If you need spaced items displayed in a card format (with background or outlined).
 *
 * > Design name: Static List Item
 *
 * > Design version: 0.3.0
 *
 * @param labelContent Custom label content of the list item used for custom primary content such as: A product-specific text arrangement, a combination of
 *   text elements not supported by the standard properties.
 * @param modifier [Modifier] applied to the layout of the list item.
 * @param verticalAlignment Controls the vertical alignment of the content. Defaults to [OudsListItemVerticalAlignment.CenterVertically].
 * @param overline Optional text displayed above the label.
 * @param extraLabel Optional strong accompanying label for the main label, displayed between the [labelContent] and the [description].
 * @param description Optional text displayed below the [labelContent] and [extraLabel].
 * @param leading Optional leading content such as an icon or image displayed at the start of the list item.
 * @param trailing Optional trailing content such as an icon, image, or text displayed at the end of the list item.
 * @param divider Controls the display of a divider at the bottom of the list item. Defaults to `true`.
 * @param background Controls whether the list item has a background color. Defaults to `false`.
 * @param helperText Optional helper text displayed below the list item.
 * @param boldLabel Controls whether the label text is displayed in bold. Defaults to `false`.
 * @param belowTextContent Optional custom content displayed below the last text of the text container, between [leading] and [trailing].
 * @param bottomContent Optional custom content displayed at the bottom of the list item across the entire width, below the main row content.
 *   Use it when the information cannot be clearly represented by the standard [description] or [helperText] parameters.
 * @param enabled Controls the enabled state of the list item. When `false`, the content is displayed in a disabled state. Defaults to `true`.
 * @param edgeToEdge Controls the horizontal layout of the item. When `true`, the item is designed to span the full width of the screen or container. When `false`,
 *   it is adapted for use within constrained layouts or containers with their own padding. Defaults to `true`.
 * @param interactionSource Optional hoisted [MutableInteractionSource] for observing and emitting interactions for this list item.
 *
 * //TODO samples
 */
@ExperimentalOudsApi
@Composable
fun OudsListItem(
    labelContent: @Composable OudsListItemScope.() -> Unit,
    modifier: Modifier = Modifier,
    verticalAlignment: OudsListItemVerticalAlignment = OudsListItemDefaults.VerticalAlignment,
    overline: String? = null,
    extraLabel: String? = null,
    description: String? = null,
    leading: OudsListItemLeading? = null,
    trailing: OudsListItemTrailing? = null,
    divider: Boolean = true,
    background: Boolean = false,
    helperText: String? = null,
    boldLabel: Boolean = false,
    belowTextContent: @Composable (OudsListItemScope.() -> Unit)? = null,
    bottomContent: @Composable (OudsListItemScope.() -> Unit)? = null,
    enabled: Boolean = true,
    edgeToEdge: Boolean = true,
    interactionSource: MutableInteractionSource? = null
) {
    OudsListItem(
        size = OudsListItemSize.Default,
        label = null,
        labelContent = labelContent,
        onClick = null,
        modifier = modifier,
        indicator = null,
        verticalAlignment = verticalAlignment,
        overline = overline,
        extraLabel = extraLabel,
        description = description,
        leading = leading,
        trailing = trailing,
        decoration = listItemDecoration(background, divider),
        helperText = helperText,
        boldLabel = boldLabel,
        belowTextContent = belowTextContent,
        bottomContent = bottomContent,
        enabled = enabled,
        edgeToEdge = edgeToEdge,
        card = false,
        interactionSource = interactionSource
    )
}

/**
 * TODO update description when available and add guideline link
 *
 * Navigation list item allows users to navigate to another screen or perform an action.
 * They are designed to be stacked within a list, with no spacing between the elements.
 *
 * A navigation list item is clickable and typically includes a navigation indicator.
 * It can be used for menu items, settings options, or any interactive list that leads to
 * another destination. The indicator type can be customized to show forward navigation,
 * backward navigation, or external links.
 * These items are designed to be stacked within a list, with no spacing between the elements.
 *
 * @see [OudsCardItem] If you need spaced items displayed in a card format (with background or outlined).
 *
 * > Design name: Navigation List Item
 *
 * > Design version: 0.3.0
 *
 * @param label The main label of the list item.
 * @param onClick Callback invoked when the list item is clicked.
 * @param modifier [Modifier] applied to the layout of the list item.
 * @param indicator The navigation indicator to display. Defaults to [OudsListItemIndicator.Next].
 * @param verticalAlignment Controls the vertical alignment of the content. Defaults to [OudsListItemVerticalAlignment.CenterVertically].
 * @param overline Optional text displayed above the label.
 * @param extraLabel Optional strong accompanying label for the main label, displayed between the [label] and the [description].
 * @param description Optional text displayed below the [label] and [extraLabel].
 * @param leading Optional leading content such as an icon or image displayed at the start of the list item.
 * @param trailing Optional trailing content such as an icon, image, or text displayed at the end of the list item.
 * @param divider Controls the display of a divider at the bottom of the list item. Defaults to `true`.
 * @param background Controls whether the list item has a background color. Defaults to `false`.
 * @param helperText Optional helper text displayed below the list item.
 * @param boldLabel Controls whether the label text is displayed in bold. Defaults to `false`.
 * @param belowTextContent Optional custom content displayed below the last text of the text container, between [leading] and [trailing].
 * @param bottomContent Optional custom content displayed at the bottom of the list item across the entire width, below the main row content.
 *   Use it when the information cannot be clearly represented by the standard [description] or [helperText] parameters.
 * @param enabled Controls the enabled state of the list item. When `false`, the item is not clickable and content is displayed in a disabled state. Defaults to `true`.
 * @param edgeToEdge Controls the horizontal layout of the item. When `true`, the item is designed to span the full width of the screen or container. When `false`,
 *   it is adapted for use within constrained layouts or containers with their own padding. Defaults to `true`.
 * @param interactionSource Optional hoisted [MutableInteractionSource] for observing and emitting interactions for this list item.
 *
 * @sample com.orange.ouds.core.component.samples.OudsNavigationListItemSample
 * @sample com.orange.ouds.core.component.samples.OudsListItemWithAllElementsSample
 * @sample com.orange.ouds.core.component.samples.OudsListItemWithLeadingImageAndTrailingTagSample
 * @sample com.orange.ouds.core.component.samples.OudsListItemWithLeadingIconAndTrailingBadgeSample
 * @sample com.orange.ouds.core.component.samples.OudsListItemWithUntintedIconSample
 */
@OptIn(ExperimentalVersionOverloading::class)
@ExperimentalOudsApi
@Composable
fun OudsListItem(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    indicator: OudsListItemIndicator = OudsListItemDefaults.Indicator,
    verticalAlignment: OudsListItemVerticalAlignment = OudsListItemDefaults.VerticalAlignment,
    overline: String? = null,
    extraLabel: String? = null,
    description: String? = null,
    leading: OudsListItemLeading? = null,
    trailing: OudsListItemTrailing? = null,
    divider: Boolean = true,
    background: Boolean = false,
    helperText: String? = null,
    boldLabel: Boolean = false,
    @IntroducedAt("2.3-Unreleased") belowTextContent: @Composable (OudsListItemScope.() -> Unit)? = null,
    @IntroducedAt("2.3-Unreleased") bottomContent: @Composable (OudsListItemScope.() -> Unit)? = null,
    enabled: Boolean = true,
    edgeToEdge: Boolean = true,
    interactionSource: MutableInteractionSource? = null
) {
    OudsListItem(
        label = label,
        labelContent = null,
        onClick = onClick,
        modifier = modifier,
        indicator = indicator,
        verticalAlignment = verticalAlignment,
        overline = overline,
        extraLabel = extraLabel,
        description = description,
        leading = leading,
        trailing = trailing,
        decoration = listItemDecoration(background, divider),
        helperText = helperText,
        boldLabel = boldLabel,
        belowTextContent = belowTextContent,
        bottomContent = bottomContent,
        enabled = enabled,
        edgeToEdge = edgeToEdge,
        card = false,
        interactionSource = interactionSource
    )
}

/**
 * TODO update description when available and add guideline link
 *
 * Navigation list item allows users to navigate to another screen or perform an action.
 * They are designed to be stacked within a list, with no spacing between the elements.
 *
 * A navigation list item is clickable and typically includes a navigation indicator.
 * It can be used for menu items, settings options, or any interactive list that leads to
 * another destination. The indicator type can be customized to show forward navigation,
 * backward navigation, or external links.
 * These items are designed to be stacked within a list, with no spacing between the elements.
 *
 * This version of the navigation list item allows to use a custom content in place of the label. Use it when the standard label cannot support the required
 * content structure.
 *
 * @see [OudsCardItem] If you need spaced items displayed in a card format (with background or outlined).
 *
 * > Design name: Navigation List Item
 *
 * > Design version: 0.3.0
 *
 * @param labelContent Custom label content of the list item used for custom primary content such as: A product-specific text arrangement, a combination of
 *   text elements not supported by the standard properties. It must remain non-interactive. Do not place a link, button or control inside it because the
 *   complete item already acts as one navigation link.
 * @param onClick Callback invoked when the list item is clicked.
 * @param modifier [Modifier] applied to the layout of the list item.
 * @param indicator The navigation indicator to display. Defaults to [OudsListItemIndicator.Next].
 * @param verticalAlignment Controls the vertical alignment of the content. Defaults to [OudsListItemVerticalAlignment.CenterVertically].
 * @param overline Optional text displayed above the label.
 * @param extraLabel Optional strong accompanying label for the main label, displayed between the [labelContent] and the [description].
 * @param description Optional text displayed below the [labelContent] and [extraLabel].
 * @param leading Optional leading content such as an icon or image displayed at the start of the list item.
 * @param trailing Optional trailing content such as an icon, image, or text displayed at the end of the list item.
 * @param divider Controls the display of a divider at the bottom of the list item. Defaults to `true`.
 * @param background Controls whether the list item has a background color. Defaults to `false`.
 * @param helperText Optional helper text displayed below the list item.
 * @param boldLabel Controls whether the label text is displayed in bold. Defaults to `false`.
 * @param belowTextContent Optional custom content displayed below the last text of the text container, between [leading] and [trailing].
 * @param bottomContent Optional custom content displayed at the bottom of the list item across the entire width, below the main row content.
 *   Use it when the information cannot be clearly represented by the standard [description] or [helperText] parameters.
 * @param enabled Controls the enabled state of the list item. When `false`, the item is not clickable and content is displayed in a disabled state. Defaults to `true`.
 * @param edgeToEdge Controls the horizontal layout of the item. When `true`, the item is designed to span the full width of the screen or container. When `false`,
 *   it is adapted for use within constrained layouts or containers with their own padding. Defaults to `true`.
 * @param interactionSource Optional hoisted [MutableInteractionSource] for observing and emitting interactions for this list item.
 *
 * TODO samples
 */
@ExperimentalOudsApi
@Composable
fun OudsListItem(
    labelContent: @Composable OudsListItemScope.() -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    indicator: OudsListItemIndicator = OudsListItemDefaults.Indicator,
    verticalAlignment: OudsListItemVerticalAlignment = OudsListItemDefaults.VerticalAlignment,
    overline: String? = null,
    extraLabel: String? = null,
    description: String? = null,
    leading: OudsListItemLeading? = null,
    trailing: OudsListItemTrailing? = null,
    divider: Boolean = true,
    background: Boolean = false,
    helperText: String? = null,
    boldLabel: Boolean = false,
    belowTextContent: @Composable (OudsListItemScope.() -> Unit)? = null,
    bottomContent: @Composable (OudsListItemScope.() -> Unit)? = null,
    enabled: Boolean = true,
    edgeToEdge: Boolean = true,
    interactionSource: MutableInteractionSource? = null
) {
    OudsListItem(
        label = null,
        labelContent = labelContent,
        onClick = onClick,
        modifier = modifier,
        indicator = indicator,
        verticalAlignment = verticalAlignment,
        overline = overline,
        extraLabel = extraLabel,
        description = description,
        leading = leading,
        trailing = trailing,
        decoration = listItemDecoration(background, divider),
        helperText = helperText,
        boldLabel = boldLabel,
        belowTextContent = belowTextContent,
        bottomContent = bottomContent,
        enabled = enabled,
        edgeToEdge = edgeToEdge,
        card = false,
        interactionSource = interactionSource
    )
}

@Composable
internal fun OudsListItem(
    card: Boolean,
    onClick: (() -> Unit)?,
    decoration: OudsListItemDecoration,
    label: String?,
    labelContent: @Composable (OudsListItemScope.() -> Unit)?,
    modifier: Modifier = Modifier,
    size: OudsListItemSize = OudsListItemSize.Default,
    indicator: OudsListItemIndicator? = null,
    verticalAlignment: OudsListItemVerticalAlignment = OudsListItemDefaults.VerticalAlignment,
    overline: String? = null,
    extraLabel: String? = null,
    description: String? = null,
    leading: OudsListItemLeadingTrailing? = null,
    trailing: OudsListItemLeadingTrailing? = null,
    helperText: String? = null,
    boldLabel: Boolean = false,
    belowTextContent: @Composable (OudsListItemScope.() -> Unit)? = null,
    bottomContent: @Composable (OudsListItemScope.() -> Unit)? = null,
    enabled: Boolean = true,
    edgeToEdge: Boolean = true,
    interactionSource: MutableInteractionSource? = null
) {
    @Suppress("NAME_SHADOWING") val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    val interactionState by interactionSource.collectInteractionStateAsState()
    val state = getListItemState(enabled = enabled, interactionState = interactionState)
    val backgroundColor = rememberInteractionColor(interactionState = interactionState) { listItemInteractionState ->
        val listItemState = getListItemState(enabled = enabled, interactionState = listItemInteractionState)
        backgroundColor(state = listItemState, decoration = decoration)
    }
    val outlineBorderColor = rememberInteractionColor(interactionState = interactionState) { listItemInteractionState ->
        val listItemState = getListItemState(enabled = enabled, interactionState = listItemInteractionState)
        outlineBorderColor(state = listItemState)
    }

    with(OudsTheme.components.listItem) {
        val borderRadius = borderRadius(card = card, focused = state == OudsListItemState.Focused)
        val shape = shape(cornerRadius = borderRadius)

        val clickableModifier = if (onClick != null) {
            Modifier.clickable(
                onClick = onClick,
                enabled = enabled,
                interactionSource = interactionSource,
                indication = interactionValuesIndication(backgroundColor, outlineBorderColor)
            )
        } else {
            Modifier
        }

        Column(
            modifier = modifier.sizeIn(minWidth = this.size.minWidth)
        ) {
            Column(
                modifier = clickableModifier
                    .fillMaxWidth()
                    .heightIn(min = minHeight(size))
                    .background(color = backgroundColor.value, shape = shape)
                    .border(state = state, decoration = decoration, cornerRadius = borderRadius, outlineColor = outlineBorderColor.value)
                    .outerBorder(state = state, shape = shape)
                    .semantics(mergeDescendants = true) { },
            ) {
                Row(
                    modifier = Modifier.containerPadding(size = size, verticalAlignment = verticalAlignment, edgeToEdge = edgeToEdge),
                    horizontalArrangement = Arrangement.spacedBy(space.columnGap),
                    verticalAlignment = verticalAlignment(verticalAlignment)
                ) {
                    if (indicator == OudsListItemIndicator.Previous) {
                        Indicator(drawableId = indicator.drawableId, state = state)
                    } else {
                        leading?.let {
                            when (leading) {
                                is OudsListItemLeadingTrailing.Content -> leading.PolymorphicContent(
                                    extraParameters = OudsListItemLeadingTrailing.Content.ExtraParameters(state = state)
                                )
                                is OudsListItemLeadingTrailing.Icon -> {
                                    leading.PolymorphicContent(
                                        extraParameters = OudsListItemLeadingTrailing.Icon.ExtraParameters(state = state)
                                    )
                                }
                                is OudsListItemLeadingTrailing.Image -> leading.PolymorphicContent()
                                is OudsListItemLeadingTrailing.Badge,
                                is OudsListItemLeadingTrailing.Tag,
                                is OudsListItemLeadingTrailing.Text -> {
                                }
                            }
                        }
                    }

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(top = topTextContainerPadding(verticalAlignment = verticalAlignment, size = size))
                    ) {
                        if (!overline.isNullOrBlank()) {
                            Text(text = overline, style = OudsTheme.typography.label.small.moderate, color = contentColor(state = state, muted = true))
                        }
                        label?.let {
                            Text(
                                text = label,
                                style = if (boldLabel) OudsTheme.typography.label.large.strong else OudsTheme.typography.label.large.default,
                                color = contentColor(state = state)
                            )
                        }
                        labelContent?.let {
                            CustomContentBox(state = state) {
                                labelContent()
                            }
                        }
                        if (!extraLabel.isNullOrBlank()) {
                            Text(text = extraLabel, style = OudsTheme.typography.label.medium.strong, color = contentColor(state = state))
                        }
                        if (!description.isNullOrBlank()) {
                            Text(text = description, style = OudsTheme.typography.label.medium.default, color = contentColor(state = state, muted = true))
                        }
                        belowTextContent?.let {
                            CustomContentBox(state = state) {
                                belowTextContent()
                            }
                        }
                    }

                    trailing?.let {
                        when (trailing) {
                            is OudsListItemLeadingTrailing.Badge -> trailing.PolymorphicContent(
                                extraParameters = OudsListItemLeadingTrailing.Badge.ExtraParameters(state = state)
                            )
                            is OudsListItemLeadingTrailing.Content -> trailing.PolymorphicContent(
                                extraParameters = OudsListItemLeadingTrailing.Content.ExtraParameters(state = state)
                            )
                            is OudsListItemLeadingTrailing.Icon -> trailing.PolymorphicContent(
                                extraParameters = OudsListItemLeadingTrailing.Icon.ExtraParameters(state = state)
                            )
                            is OudsListItemLeadingTrailing.Image -> trailing.PolymorphicContent()
                            is OudsListItemLeadingTrailing.Tag -> trailing.PolymorphicContent(
                                extraParameters = OudsListItemLeadingTrailing.Tag.ExtraParameters(state = state)
                            )
                            is OudsListItemLeadingTrailing.Text -> {
                                trailing.PolymorphicContent(
                                    extraParameters = OudsListItemLeadingTrailing.Text.ExtraParameters(
                                        verticalAlignment = verticalAlignment,
                                        size = size
                                    )
                                )
                            }
                        }
                    }

                    if (indicator != null && indicator in listOf(OudsListItemIndicator.Next, OudsListItemIndicator.External)) {
                        Indicator(drawableId = indicator.drawableId, state = state)
                    }
                }

                bottomContent?.let {
                    CustomContentBox(
                        state = state,
                        paddingValues = PaddingValues(
                            start = space.paddingInline,
                            end = space.paddingInline,
                            bottom = space.paddingBlock.bottomSlotListItemContainer
                        )
                    ) {
                        bottomContent()
                    }
                }
            }

            if (!helperText.isNullOrBlank()) {
                Text(
                    modifier = Modifier
                        .padding(top = space.paddingBlock.topHelperText)
                        .padding(horizontal = space.paddingInline),
                    text = helperText,
                    style = OudsTheme.typography.label.medium.default,
                    color = contentColor(state = state, muted = true)
                )
            }
        }
    }
}

/**
 * Scope for the content of a list item.
 *
 * @property state The current state of the list item.
 */
class OudsListItemScope {
    var state: OudsListItemState by mutableStateOf(OudsListItemState.Enabled)
        internal set
}

@Composable
private fun CustomContentBox(
    state: OudsListItemState,
    paddingValues: PaddingValues = PaddingValues(vertical = OudsTheme.components.listItem.space.paddingBlock.slotTextContainer),
    content: @Composable OudsListItemScope.() -> Unit
) {
    val scope = remember { OudsListItemScope() }
    Box(modifier = Modifier.padding(paddingValues = paddingValues)) {
        with(scope) {
            this.state = state
            content()
        }
    }
}

@Composable
private fun Indicator(drawableId: Int, state: OudsListItemState) {
    with(OudsTheme.components.listItem) {
        Icon(
            modifier = Modifier.size(size.asset.small),
            painter = painterResource(drawableId),
            contentDescription = null,
            tint = indicatorColor(state = state)
        )
    }
}

@Composable
private fun getListItemState(enabled: Boolean, interactionState: InteractionState): OudsListItemState {
    return getPreviewEnumEntry<OudsListItemState>().orElse {
        when {
            !enabled -> OudsListItemState.Disabled
            interactionState == InteractionState.Hovered -> OudsListItemState.Hovered
            interactionState == InteractionState.Pressed -> OudsListItemState.Pressed
            interactionState == InteractionState.Focused -> OudsListItemState.Focused
            else -> OudsListItemState.Enabled
        }
    }
}

@Composable
private fun Modifier.containerPadding(size: OudsListItemSize, verticalAlignment: OudsListItemVerticalAlignment, edgeToEdge: Boolean) =
    with(OudsTheme.components.listItem) {
        when (size) {
            OudsListItemSize.Small -> when (verticalAlignment) {
                OudsListItemVerticalAlignment.CenterVertically -> padding(vertical = space.paddingBlock.small)
                OudsListItemVerticalAlignment.Top -> padding(
                    top = space.paddingBlock.topAlignment.topCounterweightSmall,
                    bottom = space.paddingBlock.small
                )
            }
            OudsListItemSize.Default -> when (verticalAlignment) {
                OudsListItemVerticalAlignment.CenterVertically -> padding(vertical = space.paddingBlock.default)
                OudsListItemVerticalAlignment.Top -> padding(
                    top = space.paddingBlock.topAlignment.topCounterweightDefault,
                    bottom = space.paddingBlock.default
                )
            }
        }.padding(horizontal = contentHorizontalPadding(edgeToEdge = edgeToEdge))
    }

@Composable
private fun contentHorizontalPadding(edgeToEdge: Boolean) =
    if (edgeToEdge) OudsTheme.grids.margin else OudsTheme.componentsTokens.listItem.spacePaddingInline.value

@Composable
private fun backgroundColor(state: OudsListItemState, decoration: OudsListItemDecoration?) = with(OudsTheme.colorScheme.action.support) {
    val backgroundDecoration = decoration is OudsListItemDecoration.Background || decoration is OudsListItemDecoration.BackgroundOnInteraction
    when (state) {
        OudsListItemState.Enabled, OudsListItemState.Disabled -> if (decoration is OudsListItemDecoration.Background) enabled else Color.Transparent
        OudsListItemState.Focused -> if (backgroundDecoration) focus else Color.Transparent
        OudsListItemState.Hovered -> if (backgroundDecoration) hover else Color.Transparent
        OudsListItemState.Pressed -> if (backgroundDecoration) pressed else Color.Transparent
    }
}

@Composable
private fun Modifier.border(state: OudsListItemState, decoration: OudsListItemDecoration, cornerRadius: Dp, outlineColor: Color): Modifier {
    val outlined = decoration is OudsListItemDecoration.Outlined || (decoration is OudsListItemDecoration.OutlinedOnInteraction && state in listOf(
        OudsListItemState.Hovered,
        OudsListItemState.Pressed,
        OudsListItemState.Focused
    ))
    val width = OudsTheme.borders.width.default.takeUnlessHairline

    return when {
        width != null && outlined -> border(width = width, color = outlineColor, shape = shape(cornerRadius = cornerRadius))
        width != null && decoration.divider -> bottomBorder(width = width, color = OudsTheme.colorScheme.border.muted, cornerRadius = cornerRadius)
        else -> this
    }
}

@Composable
private fun borderRadius(card: Boolean, focused: Boolean) = with(OudsTheme.components.listItem) {
    when {
        card && LocalThemeSettings.current.roundedCornerCardItems == true -> border.radius.rounded
        card || focused -> border.radius.default
        else -> 0.dp
    }
}

@Composable
private fun shape(cornerRadius: Dp) = RoundedCornerShape(cornerRadius)

@Composable
private fun outlineBorderColor(state: OudsListItemState) = with(OudsTheme.colorScheme.action) {
    when (state) {
        OudsListItemState.Enabled -> OudsTheme.colorScheme.border.default
        OudsListItemState.Focused -> focus
        OudsListItemState.Hovered -> hover
        OudsListItemState.Pressed -> pressed
        OudsListItemState.Disabled -> disabled
    }
}

@Composable
private fun contentColor(state: OudsListItemState, muted: Boolean = false) =
    when {
        state == OudsListItemState.Disabled -> OudsTheme.colorScheme.content.disabled
        muted -> OudsTheme.colorScheme.content.muted
        else -> OudsTheme.colorScheme.content.default
    }

@Composable
private fun actionColor(state: OudsListItemState, tint: Color? = null) = when {
    state == OudsListItemState.Disabled -> OudsTheme.colorScheme.action.disabled
    tint != null -> tint
    else -> OudsTheme.colorScheme.content.default
}

@Composable
private fun indicatorColor(state: OudsListItemState) = with(OudsTheme.colorScheme.action) {
    when (state) {
        OudsListItemState.Enabled -> OudsTheme.components.link.color.chevron.enabled
        OudsListItemState.Focused -> focus
        OudsListItemState.Hovered -> hover
        OudsListItemState.Pressed -> pressed
        OudsListItemState.Disabled -> disabled
    }
}

@Composable
private fun minHeight(size: OudsListItemSize) = with(OudsTheme.components.listItem) {
    when (size) {
        OudsListItemSize.Default -> this.size.minHeightDefault
        OudsListItemSize.Small -> this.size.minHeightSmall
    }
}

@Composable
private fun verticalAlignment(verticalAlignment: OudsListItemVerticalAlignment) = when (verticalAlignment) {
    OudsListItemVerticalAlignment.CenterVertically -> Alignment.CenterVertically
    OudsListItemVerticalAlignment.Top -> Alignment.Top
}

@Composable
private fun topTextContainerPadding(verticalAlignment: OudsListItemVerticalAlignment, size: OudsListItemSize) =
    with(OudsTheme.components.listItem.space.paddingBlock.topAlignment) {
        when (verticalAlignment) {
            OudsListItemVerticalAlignment.Top -> when (size) {
                OudsListItemSize.Default -> topTextContainerDefault
                OudsListItemSize.Small -> topTextContainerSmall
            }
            OudsListItemVerticalAlignment.CenterVertically -> 0.dp
        }
    }

internal fun listItemDecoration(background: Boolean, divider: Boolean): OudsListItemDecoration {
    return if (background) OudsListItemDecoration.Background(divider) else OudsListItemDecoration.BackgroundOnInteraction(divider)
}

/**
 * Default values for [OudsListItem].
 */
object OudsListItemDefaults {

    /**
     * Default vertical alignment of an [OudsListItem].
     */
    val VerticalAlignment = OudsListItemVerticalAlignment.CenterVertically

    /**
     * Default navigation indicator of an [OudsListItem].
     */
    val Indicator = OudsListItemIndicator.Next

    /**
     * Default size of an [OudsListItem] icon.
     */
    val IconSize = OudsListItemIconSize.Medium

    /**
     * Default size of an [OudsListItem] image.
     */
    val ImageSize = OudsListItemImageSize.Medium

    /**
     * Default ratio of an [OudsListItem] image.
     */
    val ImageRatio = OudsListItemImageRatio.Square

    /**
     * Default content scale of an [OudsListItem] image.
     */
    val ImageContentScale = ContentScale.Fit
}

/**
 * Represents the size of an [OudsListItem].
 */
internal enum class OudsListItemSize {
    /**
     * Default size.
     */
    Default,

    /**
     * Small size.
     */
    Small
}

/**
 * Represents the vertical alignment of an [OudsListItem] content.
 */
enum class OudsListItemVerticalAlignment {
    /**
     * Elements are vertically centered.
     */
    CenterVertically,

    /**
     * Elements are aligned to the top.
     */
    Top
}

/**
 * Represents the navigation indicator of an [OudsListItem].
 */
sealed interface OudsListItemIndicator {

    @get:Composable
    val drawableId: Int

    /**
     * Used in a standard navigation context. This indicator is positioned at the end of the list item and is not customizable.
     */
    object Next : OudsListItemIndicator {
        override val drawableId
            @Composable
            get() = OudsTheme.drawableResources.component.listItem.next
    }

    /**
     * Used for "backward" navigation. This indicator is positioned at the start of the list item and is not customizable.
     */
    object Previous : OudsListItemIndicator {
        override val drawableId
            @Composable
            get() = OudsTheme.drawableResources.component.listItem.previous
    }

    /**
     * Used for "external" navigation (outside the current context). This indicator is positioned at the end of the list item and is not customizable.
     */
    object External : OudsListItemIndicator {
        override val drawableId
            @Composable
            get() = OudsTheme.drawableResources.functional.actions.externalLink
    }
}

/**
 * Represents the decoration applied to an [OudsCardItem] or an [OudsSmallCardItem].
 * Decorations allow customizing the visual appearance of card items, such as adding a background, an outline, or a divider.
 */
sealed class OudsListItemDecoration(val divider: Boolean) {
    /**
     * An outline is displayed around the card item. Use when the item needs clearer visual separation from a surface with a similar background.
     */
    object Outlined : OudsListItemDecoration(false)

    /**
     * An outline is displayed around the card item only on interaction states (hovered, pressed, focused).
     */
    object OutlinedOnInteraction : OudsListItemDecoration(false)

    /**
     * A background is applied to the card.
     * Use it when the card item needs stronger visual separation from its surrounding context or represents a distinct destination within a card-based layout.
     *
     * @property divider Controls the display of a divider at the bottom of the item. Set it to `true` when several items are vertically grouped within the same
     * shared surface and additional visual separation improves scanning. Avoid using a divider when each item already has a clearly separated background.
     */
    class Background(divider: Boolean) : OudsListItemDecoration(divider)

    /**
     * A background is applied to the card item only on interaction states (hovered, pressed, focused).
     *
     * @property divider Controls the display of a divider at the bottom of the item. Set it to `true` when several items are vertically grouped within the same
     * shared surface and additional visual separation improves scanning. Avoid using a divider when each item already has a clearly separated background.
     */
    class BackgroundOnInteraction(divider: Boolean) : OudsListItemDecoration(divider)
}

enum class OudsListItemState {
    Enabled, Hovered, Pressed, Disabled, Focused
}

sealed interface OudsListItemLeadingTrailing : OudsPolymorphicComponentContent {

    interface Badge : OudsListItemLeadingTrailing {
        @ConsistentCopyVisibility
        data class ExtraParameters internal constructor(internal val state: OudsListItemState) : OudsComponentContent.ExtraParameters()
    }

    interface Content : OudsListItemLeadingTrailing {
        @ConsistentCopyVisibility
        data class ExtraParameters internal constructor(internal val state: OudsListItemState) : OudsComponentContent.ExtraParameters()
    }

    interface Icon : OudsListItemLeadingTrailing {
        @ConsistentCopyVisibility
        data class ExtraParameters internal constructor(internal val state: OudsListItemState) : OudsComponentContent.ExtraParameters()
    }

    interface Image : OudsListItemLeadingTrailing {
        val ratio: OudsListItemImageRatio
    }

    interface Tag : OudsListItemLeadingTrailing {
        @ConsistentCopyVisibility
        data class ExtraParameters internal constructor(internal val state: OudsListItemState) : OudsComponentContent.ExtraParameters()
    }

    interface Text : OudsListItemLeadingTrailing {
        @ConsistentCopyVisibility
        data class ExtraParameters internal constructor(internal val verticalAlignment: OudsListItemVerticalAlignment, internal val size: OudsListItemSize) :
            OudsComponentContent.ExtraParameters()
    }
}

internal enum class OudsListItemAssetSize {
    Small, Medium, Large, ExtraLarge;

    val value: Dp
        @Composable
        get() = with(OudsTheme.components.listItem) {
            when (this@OudsListItemAssetSize) {
                Small -> size.asset.small
                Medium -> size.asset.medium
                Large -> size.asset.large
                ExtraLarge -> size.asset.extraLarge
            }
        }
}

/**
 * Represents the size of an [OudsListItemIcon].
 */
enum class OudsListItemIconSize {

    /**
     * Use for standard and compact list items. This is the preferred size when the icon provides secondary visual support.
     */
    Medium,

    /**
     * Use when the icon needs stronger prominence or when the item has a larger height, multiline content or additional supporting information.
     */
    Large;

    internal fun toAssetSize() = when (this) {
        Medium -> OudsListItemAssetSize.Medium
        Large -> OudsListItemAssetSize.Large
    }
}

internal enum class OudsListItemIconStatus {

    Negative, Positive, Info, Warning;

    private fun toAlertStatus(): OudsAlertStatus {
        return when (this) {
            Negative -> OudsAlertStatus.Negative()
            Positive -> OudsAlertStatus.Positive()
            Info -> OudsAlertStatus.Info()
            Warning -> OudsAlertStatus.Warning()
        }
    }

    val painter: Painter
        @Composable
        get() = OudsAlertStatus.getDefaultIconPainter(toAlertStatus()).orElse {
            error("No painter for status ${this::class.simpleName}")
        }

    val contentDescription: String
        @Composable
        get() = when (this) {
            Negative -> stringResource(id = R.string.core_common_error_a11y)
            Warning -> stringResource(id = R.string.core_common_warning_a11y)
            Positive,
            Info -> ""
        }

    val tint
        @Composable
        get() = toAlertStatus().assetColor
}

/**
 * Defines the aspect ratio of the image container.
 */
enum class OudsListItemImageRatio {
    /**
     * Use for square visual content such as products, logos, album covers or profile-related imagery.
     */
    Square,

    /**
     * Use for landscape content such as editorial images or wide media thumbnails.
     */
    Widescreen;

    internal val value: Float
        get() = when (this) {
            Square -> 1f
            Widescreen -> 16f / 9f
        }
}

/**
 * Controls the dimensions and visual prominence of an [OudsListItemImage].
 */
enum class OudsListItemImageSize {
    /**
     * Use in compact or information-dense lists where the image remains secondary to the text.
     */
    Medium,

    /**
     * Use in standard content lists where visual identification is important.
     */
    Large,

    /**
     * Use when the image is a significant part of the content, such as a product or media preview.
     */
    ExtraLarge;

    internal fun toAssetSize() = when (this) {
        Medium -> OudsListItemAssetSize.Medium
        Large -> OudsListItemAssetSize.Large
        ExtraLarge -> OudsListItemAssetSize.ExtraLarge
    }
}

/**
 * Represents the text style of a text in an [OudsListItem].
 */
enum class OudsListItemTextStyle {

    /**
     * Use [Label] for standard secondary information that should remain clearly readable without drawing more attention than the primary label.
     */
    Label,

    /**
     * Use [LabelMuted] for low-priority metadata that supports the item but is not essential to its immediate understanding.
     */
    LabelMuted,

    /**
     * Use [LabelStrong] when the trailing value requires additional visual emphasis, for example when users need to compare important values across
     * a navigation list.
     */
    LabelStrong
}

open class OudsListItemBadge internal constructor(
    private val count: Int?,
    private val status: OudsBadgeStatus,
    private val size: OudsBadgeSize,
    private val enabled: Boolean
) : OudsComponentContent<OudsListItemLeadingTrailing.Badge.ExtraParameters>(OudsListItemLeadingTrailing.Badge.ExtraParameters::class.java),
    OudsListItemLeadingTrailing.Badge {

    @Composable
    override fun Content(modifier: Modifier) {
        val isEnabled = extraParameters.state == OudsListItemState.Enabled && enabled
        Box(modifier = modifier.sizeIn(minHeight = OudsTheme.components.listItem.size.asset.medium), contentAlignment = Alignment.Center) {
            count?.let {
                OudsBadge(count = it, status = status, size = size, enabled = isEnabled)
            }.orElse {
                OudsBadge(status = status, size = size, enabled = isEnabled)
            }
        }
    }
}

open class OudsListItemContent internal constructor(
    private val content: @Composable OudsListItemScope.() -> Unit
) : OudsComponentContent<OudsListItemLeadingTrailing.Content.ExtraParameters>(OudsListItemLeadingTrailing.Content.ExtraParameters::class.java),
    OudsListItemLeadingTrailing.Content {

    @Composable
    override fun Content(modifier: Modifier) {
        val scope = remember { OudsListItemScope() }
        with(OudsTheme.components.listItem) {
            Box(modifier = modifier.sizeIn(maxWidth = size.maxSizeLeadingTrailingSlot, maxHeight = size.maxSizeLeadingTrailingSlot)) {
                with(scope) {
                    state = extraParameters.state
                    content()
                }
            }
        }
    }
}

open class OudsListItemIcon internal constructor(
    graphicsObjectProvider: @Composable (OudsListItemIcon) -> Any,
    contentDescriptionProvider: @Composable (OudsListItemIcon) -> String,
    override val tinted: Boolean,
    internal val size: OudsListItemAssetSize,
    internal val status: OudsListItemIconStatus?
) : OudsComponentIcon<OudsListItemLeadingTrailing.Icon.ExtraParameters, OudsListItemIcon>(
    OudsListItemLeadingTrailing.Icon.ExtraParameters::class.java,
    graphicsObjectProvider,
    contentDescriptionProvider
), OudsListItemLeadingTrailing.Icon {

    override val tint: Color?
        @Composable
        get() = actionColor(
            state = extraParameters.state,
            tint = status?.tint
        )

    @Composable
    override fun Content(modifier: Modifier) {
        super.Content(modifier.size(size.value))
    }
}

open class OudsListItemImage internal constructor(
    graphicsObject: Any,
    contentDescription: String,
    internal val size: OudsListItemAssetSize,
    override val ratio: OudsListItemImageRatio,
    contentScale: ContentScale,
    internal val roundedCorner: Boolean
) : OudsComponentImage<Nothing>(Nothing::class.java, graphicsObject, contentDescription, contentScale = contentScale), OudsListItemLeadingTrailing.Image {

    @Composable
    override fun Content(modifier: Modifier) {
        val cornerRadius = with(OudsTheme.components.listItem.border.radius) { if (roundedCorner) mediaRounded else media }
        super.Content(
            modifier = modifier
                .height(size.value)
                .width(size.value * ratio.value)
                .clip(RoundedCornerShape(cornerRadius))
        )
    }
}

open class OudsListItemTag internal constructor(
    private val label: String,
    private val enabled: Boolean,
    private val appearance: OudsTagAppearance,
    private val status: OudsTagStatus,
    private val roundedCorners: Boolean,
    private val size: OudsTagSize,
    private val loader: OudsTagLoader?
) : OudsComponentContent<OudsListItemLeadingTrailing.Tag.ExtraParameters>(OudsListItemLeadingTrailing.Tag.ExtraParameters::class.java),
    OudsListItemLeadingTrailing.Tag {

    @Composable
    override fun Content(modifier: Modifier) {
        Box(modifier = modifier.sizeIn(minHeight = OudsTheme.components.listItem.size.asset.medium), contentAlignment = Alignment.Center) {
            OudsTag(
                label = label,
                enabled = extraParameters.state == OudsListItemState.Enabled && enabled,
                appearance = appearance,
                status = status,
                roundedCorners = roundedCorners,
                size = size,
                loader = loader
            )
        }
    }
}

open class OudsListItemText internal constructor(
    private val label: String,
    private val style: OudsListItemTextStyle,
    private val extraLabel: String?
) : OudsComponentContent<OudsListItemLeadingTrailing.Text.ExtraParameters>(OudsListItemLeadingTrailing.Text.ExtraParameters::class.java),
    OudsListItemLeadingTrailing.Text {

    @Composable
    override fun Content(modifier: Modifier) {
        Column(modifier = modifier, horizontalAlignment = Alignment.End) {
            Text(
                modifier = modifier.padding(
                    top = topTextContainerPadding(verticalAlignment = extraParameters.verticalAlignment, size = extraParameters.size)
                ),
                text = label,
                style = when (style) {
                    OudsListItemTextStyle.Label, OudsListItemTextStyle.LabelMuted -> OudsTheme.typography.label.large.default
                    OudsListItemTextStyle.LabelStrong -> OudsTheme.typography.label.large.strong
                },
                color = if (style == OudsListItemTextStyle.LabelMuted) OudsTheme.colorScheme.content.muted else OudsTheme.colorScheme.content.default
            )
            extraLabel?.let {
                Text(
                    text = extraLabel,
                    style = OudsTheme.typography.label.medium.strong,
                    color = OudsTheme.colorScheme.content.default
                )
            }
        }
    }
}

/**
 * A leading content of an [OudsListItem].
 */
sealed interface OudsListItemLeading : OudsListItemLeadingTrailing {

    /**
     * A custom content as a list item leading content.
     * Use it for custom leading content that cannot be represented by other available leading types.
     * Content provides flexibility for specific product requirements, but it should be used as an exception rather than the default solution.
     *
     * @param content Custom content to display.
     */
    class Content(content: @Composable OudsListItemScope.() -> Unit) : OudsListItemContent(content), OudsListItemLeading

    /**
     * An icon as a list item leading content.
     */
    open class Icon internal constructor(
        graphicsObjectProvider: @Composable (OudsListItemIcon) -> Any,
        contentDescriptionProvider: @Composable (OudsListItemIcon) -> String,
        override val tinted: Boolean,
        size: OudsListItemIconSize,
        status: OudsListItemIconStatus?
    ) : OudsListItemIcon(graphicsObjectProvider, contentDescriptionProvider, tinted, size.toAssetSize(), status), OudsListItemLeading {

        /**
         * Creates an instance of [OudsListItemLeading.Icon].
         *
         * @param painter Painter of the icon.
         * @param contentDescription The content description associated with this [OudsListItemLeading.Icon].
         * @param size Size of the icon among [OudsListItemIconSize] values.
         * @param tinted Controls whether the icon should be tinted with the theme color. Defaults to `true`.
         *   When set to `false`, the icon is displayed with its original colors (e.g., for multicolor icons).
         *   Note that untinted icons must ensure sufficient contrast with the background for accessibility reasons.
         */
        constructor(
            painter: Painter,
            contentDescription: String,
            size: OudsListItemIconSize = OudsListItemDefaults.IconSize,
            tinted: Boolean = true
        ) : this({ painter as Any }, { contentDescription }, tinted, size, null)

        /**
         * Creates an instance of [OudsListItemLeading.Icon].
         *
         * @param imageVector Image vector of the icon.
         * @param contentDescription The content description associated with this [OudsListItemLeading.Icon].
         * @param size Size of the icon among [OudsListItemIconSize] values.
         * @param tinted Controls whether the icon should be tinted with the theme color. Defaults to `true`.
         *   When set to `false`, the icon is displayed with its original colors (e.g., for multicolor icons).
         *   Note that untinted icons must ensure sufficient contrast with the background for accessibility reasons.
         */
        constructor(
            imageVector: ImageVector,
            contentDescription: String,
            size: OudsListItemIconSize = OudsListItemDefaults.IconSize,
            tinted: Boolean = true
        ) : this({ imageVector as Any }, { contentDescription }, tinted, size, null)

        /**
         * Creates an instance of [OudsListItemLeading.Icon].
         * Use an icon to reinforce the meaning of the destination or help users identify a familiar category, object or service.
         *
         * @param bitmap Image bitmap of the icon.
         * @param contentDescription The content description associated with this [OudsListItemLeading.Icon].
         * @param size Size of the icon among [OudsListItemIconSize] values.
         * @param tinted Controls whether the icon should be tinted with the theme color. Defaults to `true`.
         *   When set to `false`, the icon is displayed with its original colors (e.g., for multicolor icons).
         *   Note that untinted icons must ensure sufficient contrast with the background for accessibility reasons.
         */
        constructor(
            bitmap: ImageBitmap,
            contentDescription: String,
            size: OudsListItemIconSize = OudsListItemDefaults.IconSize,
            tinted: Boolean = true
        ) : this({ bitmap as Any }, { contentDescription }, tinted, size, null)

        private constructor(size: OudsListItemIconSize, status: OudsListItemIconStatus) : this(
            { status.painter },
            { status.contentDescription },
            true,
            size,
            status
        )

        /**
         * Creates an instance of [OudsListItemLeading.Icon] representing an info status.
         * Use for neutral information that requires additional attention.
         *
         * @param size Size of the icon among [OudsListItemIconSize] values.
         */
        class Info(size: OudsListItemIconSize = OudsListItemDefaults.IconSize) : Icon(size, OudsListItemIconStatus.Info)

        /**
         * Creates an instance of [OudsListItemLeading.Icon] representing a negative status.
         * Use for errors, failures, destructive outcomes or critical problems.
         *
         * @param size Size of the icon among [OudsListItemIconSize] values.
         */
        class Negative(size: OudsListItemIconSize = OudsListItemDefaults.IconSize) : Icon(size, OudsListItemIconStatus.Negative)

        /**
         * Creates an instance of [OudsListItemLeading.Icon] representing a positive status.
         * Use for successful, completed or beneficial states.
         *
         * @param size Size of the icon among [OudsListItemIconSize] values.
         */
        class Positive(size: OudsListItemIconSize = OudsListItemDefaults.IconSize) : Icon(size, OudsListItemIconStatus.Positive)

        /**
         * Creates an instance of [OudsListItemLeading.Icon] representing a warning status.
         * Use for situations that may require caution or user awareness.
         *
         * @param size Size of the icon among [OudsListItemIconSize] values.
         */
        class Warning(size: OudsListItemIconSize = OudsListItemDefaults.IconSize) : Icon(size, OudsListItemIconStatus.Warning)
    }

    /**
     * An image as a list item leading content.
     */
    class Image internal constructor(
        graphicsObject: Any,
        contentDescription: String,
        size: OudsListItemAssetSize,
        ratio: OudsListItemImageRatio,
        contentScale: ContentScale,
        roundedCorner: Boolean
    ) : OudsListItemImage(graphicsObject, contentDescription, size, ratio, contentScale, roundedCorner), OudsListItemLeading {

        /**
         * Creates an instance of [OudsListItemLeading.Image].
         *
         * @param painter Painter of the image.
         * @param contentDescription The content description associated with this image.
         * @param size Size of the image among [OudsListItemImageSize] values.
         * @param ratio Ratio of the image among [OudsListItemImageRatio] values.
         * @param contentScale Scale parameter used to determine the aspect ratio scaling to be used if the bounds are a different size from the intrinsic size
         * of the [painter].
         * @param roundedCorner Controls whether the image is displayed with square or rounded corners. False by default.
         */
        constructor(
            painter: Painter,
            contentDescription: String,
            size: OudsListItemImageSize = OudsListItemDefaults.ImageSize,
            ratio: OudsListItemImageRatio = OudsListItemDefaults.ImageRatio,
            contentScale: ContentScale = OudsListItemDefaults.ImageContentScale,
            roundedCorner: Boolean = false
        ) : this(painter, contentDescription, size.toAssetSize(), ratio, contentScale, roundedCorner)

        /**
         * Creates an instance of [OudsListItemLeading.Image].
         *
         * @param imageVector Image vector of the image.
         * @param contentDescription The content description associated with this image.
         * @param size Size of the image among [OudsListItemImageSize] values.
         * @param ratio Ratio of the image among [OudsListItemImageRatio] values.
         * @param contentScale Scale parameter used to determine the aspect ratio scaling to be used if the bounds are a different size from the intrinsic size
         * of the [imageVector].
         * @param roundedCorner Controls whether the image is displayed with square or rounded corners. False by default.
         */
        constructor(
            imageVector: ImageVector,
            contentDescription: String,
            size: OudsListItemImageSize = OudsListItemDefaults.ImageSize,
            ratio: OudsListItemImageRatio = OudsListItemDefaults.ImageRatio,
            contentScale: ContentScale = OudsListItemDefaults.ImageContentScale,
            roundedCorner: Boolean = false
        ) : this(imageVector, contentDescription, size.toAssetSize(), ratio, contentScale, roundedCorner)

        /**
         * Creates an instance of [OudsListItemLeading.Image].
         *
         * @param bitmap Image bitmap of the image.
         * @param contentDescription The content description associated with this image.
         * @param size Size of the image among [OudsListItemImageSize] values.
         * @param ratio Ratio of the image among [OudsListItemImageRatio] values.
         * @param contentScale Scale parameter used to determine the aspect ratio scaling to be used if the bounds are a different size from the intrinsic size
         * of the [bitmap].
         * @param roundedCorner Controls whether the image is displayed with square or rounded corners. False by default.
         */
        constructor(
            bitmap: ImageBitmap,
            contentDescription: String,
            size: OudsListItemImageSize = OudsListItemDefaults.ImageSize,
            ratio: OudsListItemImageRatio = OudsListItemDefaults.ImageRatio,
            contentScale: ContentScale = OudsListItemDefaults.ImageContentScale,
            roundedCorner: Boolean = false
        ) : this(bitmap, contentDescription, size.toAssetSize(), ratio, contentScale, roundedCorner)
    }
}

/**
 * A trailing content of an [OudsListItem].
 */
sealed interface OudsListItemTrailing : OudsListItemLeadingTrailing {

    /**
     * A badge as a list item trailing content.
     * Use a Badge to communicate a compact status or notification associated with the item.
     * A badge should contain secondary information and must not replace the primary label or description.
     *
     * @see [OudsBadge]
     *
     * @param count The number displayed in the badge. Minimum and maximum values are 0 and 99 respectively.
     *   Values greater than 99 are displayed as "+99". If `null`, no number will be displayed in the badge.
     * @param status The status of this badge. The background color of the badge and the number color are based on this status.
     * @param size The size of this badge. The number is not displayed when size is [OudsBadgeSize.ExtraSmall] or [OudsBadgeSize.Small].
     * @param enabled Controls the enabled appearance of the badge.
     */
    class Badge(
        count: Int? = null,
        status: OudsBadgeStatus = OudsBadgeDefaults.Status,
        size: OudsBadgeSize = OudsBadgeDefaults.Size,
        enabled: Boolean = true
    ) : OudsListItemBadge(count = count, status = status, size = size, enabled = enabled), OudsListItemTrailing

    /**
     * A custom content as a list item trailing content.
     * Use it for custom trailing content that cannot be represented by other available trailing types.
     * Content provides flexibility for specific product requirements, but it should be used as an exception rather than the default solution.
     *
     * @param content Custom content to display.
     */
    class Content(content: @Composable OudsListItemScope.() -> Unit) : OudsListItemContent(content), OudsListItemTrailing

    /**
     * An icon as a list item trailing content.
     */
    open class Icon internal constructor(
        graphicsObjectProvider: @Composable (OudsListItemIcon) -> Any,
        contentDescriptionProvider: @Composable (OudsListItemIcon) -> String,
        override val tinted: Boolean,
        size: OudsListItemIconSize,
        status: OudsListItemIconStatus?
    ) : OudsListItemIcon(graphicsObjectProvider, contentDescriptionProvider, tinted, size.toAssetSize(), status), OudsListItemTrailing {

        /**
         * Creates an instance of [OudsListItemTrailing.Icon].
         *
         * @param painter Painter of the icon.
         * @param contentDescription The content description associated with this [OudsListItemTrailing.Icon].
         * @param size Size of the icon among [OudsListItemIconSize] values.
         * @param tinted Controls whether the icon should be tinted with the theme color. Defaults to `true`.
         *   When set to `false`, the icon is displayed with its original colors (e.g., for multicolor icons).
         *   Note that untinted icons must ensure sufficient contrast with the background for accessibility reasons.
         */
        constructor(
            painter: Painter,
            contentDescription: String,
            size: OudsListItemIconSize = OudsListItemDefaults.IconSize,
            tinted: Boolean = true
        ) : this({ painter as Any }, { contentDescription }, tinted, size, null)

        /**
         * Creates an instance of [OudsListItemTrailing.Icon].
         *
         * @param imageVector Image vector of the icon.
         * @param contentDescription The content description associated with this [OudsListItemTrailing.Icon].
         * @param size Size of the icon among [OudsListItemIconSize] values.
         * @param tinted Controls whether the icon should be tinted with the theme color. Defaults to `true`.
         *   When set to `false`, the icon is displayed with its original colors (e.g., for multicolor icons).
         *   Note that untinted icons must ensure sufficient contrast with the background for accessibility reasons.
         */
        constructor(
            imageVector: ImageVector,
            contentDescription: String,
            size: OudsListItemIconSize = OudsListItemDefaults.IconSize,
            tinted: Boolean = true
        ) : this({ imageVector as Any }, { contentDescription }, tinted, size, null)

        /**
         * Creates an instance of [OudsListItemTrailing.Icon].
         *
         * @param bitmap Image bitmap of the icon.
         * @param contentDescription The content description associated with this [OudsListItemTrailing.Icon].
         * @param size Size of the icon among [OudsListItemIconSize] values.
         * @param tinted Controls whether the icon should be tinted with the theme color. Defaults to `true`.
         *   When set to `false`, the icon is displayed with its original colors (e.g., for multicolor icons).
         *   Note that untinted icons must ensure sufficient contrast with the background for accessibility reasons.
         */
        constructor(
            bitmap: ImageBitmap,
            contentDescription: String,
            size: OudsListItemIconSize = OudsListItemDefaults.IconSize,
            tinted: Boolean = true
        ) : this({ bitmap as Any }, { contentDescription }, tinted, size, null)

        private constructor(size: OudsListItemIconSize, status: OudsListItemIconStatus) : this(
            { status.painter },
            { status.contentDescription },
            true,
            size,
            status
        )

        /**
         * Creates an instance of [OudsListItemTrailing.Icon] representing an info status.
         * Use for neutral information that requires additional attention.
         *
         * @param size Size of the icon among [OudsListItemIconSize] values.
         */
        class Info(size: OudsListItemIconSize = OudsListItemDefaults.IconSize) : Icon(size, OudsListItemIconStatus.Info)

        /**
         * Creates an instance of [OudsListItemTrailing.Icon] representing a negative status.
         * Use for errors, failures, destructive outcomes or critical problems.
         *
         * @param size Size of the icon among [OudsListItemIconSize] values.
         */
        class Negative(size: OudsListItemIconSize = OudsListItemDefaults.IconSize) : Icon(size, OudsListItemIconStatus.Negative)

        /**
         * Creates an instance of [OudsListItemTrailing.Icon] representing a positive status.
         * Use for successful, completed or beneficial states.
         *
         * @param size Size of the icon among [OudsListItemIconSize] values.
         */
        class Positive(size: OudsListItemIconSize = OudsListItemDefaults.IconSize) : Icon(size, OudsListItemIconStatus.Positive)

        /**
         * Creates an instance of [OudsListItemTrailing.Icon] representing a warning status.
         * Use for situations that may require caution or user awareness.
         *
         * @param size Size of the icon among [OudsListItemIconSize] values.
         */
        class Warning(size: OudsListItemIconSize = OudsListItemDefaults.IconSize) : Icon(size, OudsListItemIconStatus.Warning)
    }

    /**
     * An image as a list item trailing content.
     */
    class Image internal constructor(
        graphicsObject: Any,
        contentDescription: String,
        size: OudsListItemAssetSize,
        ratio: OudsListItemImageRatio,
        contentScale: ContentScale,
        roundedCorner: Boolean
    ) : OudsListItemImage(graphicsObject, contentDescription, size, ratio, contentScale, roundedCorner), OudsListItemTrailing {

        /**
         * Creates an instance of [OudsListItemTrailing.Image].
         *
         * @param painter Painter of the image.
         * @param contentDescription The content description associated with this image.
         * @param size Size of the image among [OudsListItemImageSize] values.
         * @param ratio Ratio of the image among [OudsListItemImageRatio] values.
         * @param contentScale Scale parameter used to determine the aspect ratio scaling to be used if the bounds are a different size from the intrinsic size
         * of the [painter].
         * @param roundedCorner Controls whether the image is displayed with square or rounded corners. False by default.
         */
        constructor(
            painter: Painter,
            contentDescription: String,
            size: OudsListItemImageSize = OudsListItemDefaults.ImageSize,
            ratio: OudsListItemImageRatio = OudsListItemDefaults.ImageRatio,
            contentScale: ContentScale = OudsListItemDefaults.ImageContentScale,
            roundedCorner: Boolean = false
        ) : this(painter, contentDescription, size.toAssetSize(), ratio, contentScale, roundedCorner)

        /**
         * Creates an instance of [OudsListItemTrailing.Image].
         *
         * @param imageVector Image vector of the image.
         * @param contentDescription The content description associated with this image.
         * @param size Size of the image among [OudsListItemImageSize] values.
         * @param ratio Ratio of the image among [OudsListItemImageRatio] values.
         * @param contentScale Scale parameter used to determine the aspect ratio scaling to be used if the bounds are a different size from the intrinsic size
         * of the [imageVector].
         * @param roundedCorner Controls whether the image is displayed with square or rounded corners. False by default.
         */
        constructor(
            imageVector: ImageVector,
            contentDescription: String,
            size: OudsListItemImageSize = OudsListItemDefaults.ImageSize,
            ratio: OudsListItemImageRatio = OudsListItemDefaults.ImageRatio,
            contentScale: ContentScale = OudsListItemDefaults.ImageContentScale,
            roundedCorner: Boolean = false
        ) : this(imageVector, contentDescription, size.toAssetSize(), ratio, contentScale, roundedCorner)

        /**
         * Creates an instance of [OudsListItemTrailing.Image].
         *
         * @param bitmap Image bitmap of the image.
         * @param contentDescription The content description associated with this image.
         * @param size Size of the image among [OudsListItemImageSize] values.
         * @param ratio Ratio of the image among [OudsListItemImageRatio] values.
         * @param contentScale Scale parameter used to determine the aspect ratio scaling to be used if the bounds are a different size from the intrinsic size
         * of the [bitmap].
         * @param roundedCorner Controls whether the image is displayed with square or rounded corners. False by default.
         */
        constructor(
            bitmap: ImageBitmap,
            contentDescription: String,
            size: OudsListItemImageSize = OudsListItemDefaults.ImageSize,
            ratio: OudsListItemImageRatio = OudsListItemDefaults.ImageRatio,
            contentScale: ContentScale = OudsListItemDefaults.ImageContentScale,
            roundedCorner: Boolean = false
        ) : this(bitmap, contentDescription, size.toAssetSize(), ratio, contentScale, roundedCorner)
    }

    /**
     * A tag as a list item trailing content.
     * Use a Tag to communicate a category, attribute, classification or compact status associated with the item (static item) or with the
     * navigation destination (navigation item).
     *
     * @see [OudsTag]
     *
     * @param label The label displayed in the tag.
     * @param enabled Controls the enabled appearance of the tag.
     * @param appearance Appearance of the tag among [OudsTagAppearance] values. Combined with the [status] of the tag, the appearance determines the tag's background
     *   and content colors.
     * @param status The status of the tag. Its background color and its content color are based on this status combined with the [appearance] of the tag.
     * @param roundedCorners Controls the shape of the tag.
     * @param size Size of the tag among [OudsTagSize] values.
     * @param loader An optional loading spinner (or progress indicator) displayed before the [label]. Used to indicate that a process or action related to the
     * tag is in progress.
     * TODO add skeleton option when available
     */
    class Tag(
        label: String,
        enabled: Boolean = true,
        appearance: OudsTagAppearance = OudsTagDefaults.Appearance,
        status: OudsTagStatus = OudsTagDefaults.Status,
        roundedCorners: Boolean = true,
        size: OudsTagSize = OudsTagDefaults.Size,
        loader: OudsTagLoader? = null
    ) : OudsListItemTag(label, enabled, appearance, status, roundedCorners, size, loader), OudsListItemTrailing

    /**
     * Text as a list item trailing content.
     */
    class Text private constructor(
        label: String,
        style: OudsListItemTextStyle,
        extraLabel: String?
    ) : OudsListItemText(label, style, extraLabel), OudsListItemTrailing {

        /**
         * Creates an instance of [OudsListItemTrailing.Text].
         * Use it to display a short value or secondary piece of information associated with the navigation destination.
         *
         * @param label Label displayed in trailing.
         * @param style Style applied to the label among [OudsListItemTextStyle] values.
         */
        constructor(label: String, style: OudsListItemTextStyle = OudsListItemTextStyle.Label) : this(label, style, null)

        /**
         * Creates an instance of [OudsListItemTrailing.Text] with an extra label.
         * Use it when a value requires a short qualifier, unit or supporting label.
         * Note that when an [extraLabel] is provided, the [label] retains the [OudsListItemTextStyle.Label] style.
         *
         * @param label Label displayed in trailing.
         * @param extraLabel Label displayed below the main label.
         */
        constructor(label: String, extraLabel: String) : this(label, OudsListItemTextStyle.Label, extraLabel)
    }
}

@OudsPreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsStaticListItem(@PreviewParameter(OudsListItemPreviewParameterProvider::class) parameter: OudsListItemPreviewParameter<OudsListItemLeading, OudsListItemTrailing>) {
    PreviewOudsStaticListItem(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsStaticListItem(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsListItemPreviewParameter<OudsListItemLeading, OudsListItemTrailing>
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        OudsListItem(
            card = false,
            onClick = null,
            decoration = decoration,
            label = label,
            labelContent = labelContent,
            overline = overline,
            extraLabel = extraLabel,
            description = description,
            helperText = helperText,
            verticalAlignment = verticalAlignment,
            leading = leading,
            trailing = trailing,
            belowTextContent = belowTextContent,
            bottomContent = bottomContent,
            boldLabel = boldLabel,
            enabled = enabled
        )
    }
}

@Preview(name = "Light", heightDp = OudsPreviewableComponent.ListItem.Navigation.PreviewHeightDp, device = OudsPreviewDevice)
@Preview(
    name = "Dark",
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL,
    heightDp = OudsPreviewableComponent.ListItem.Navigation.PreviewHeightDp,
    device = OudsPreviewDevice
)
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsNavigationListItem(@PreviewParameter(OudsListItemPreviewParameterProvider::class) parameter: OudsListItemPreviewParameter<OudsListItemLeading, OudsListItemTrailing>) {
    PreviewOudsNavigationListItem(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsNavigationListItem(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsListItemPreviewParameter<OudsListItemLeading, OudsListItemTrailing>
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        PreviewEnumEntries<OudsListItemState>(maxEnumEntriesInEachRow = 1, edgeToEdge = true) {
            OudsListItem(
                card = false,
                onClick = {},
                decoration = decoration,
                label = label,
                labelContent = labelContent,
                indicator = indicator,
                overline = overline,
                extraLabel = extraLabel,
                description = description,
                helperText = helperText,
                verticalAlignment = verticalAlignment,
                leading = leading,
                trailing = trailing,
                boldLabel = boldLabel,
                belowTextContent = belowTextContent,
                bottomContent = bottomContent,
                enabled = enabled
            )
        }
    }
}

@OudsPreview
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsNavigationListItemWithUntintedIcon() = PreviewOudsNavigationListItemWithUntintedIcon(getPreviewTheme())

@Composable
internal fun PreviewOudsNavigationListItemWithUntintedIcon(theme: OudsThemeContract) = OudsPreview(theme = theme) {
    PreviewEnumEntries<OudsListItemState>(maxEnumEntriesInEachRow = 1, edgeToEdge = true) {
        OudsListItem(
            onClick = {},
            label = "Label",
            description = "Description",
            leading = OudsListItemLeading.Icon(
                painter = rememberRainbowHeartPainter(),
                contentDescription = "",
                tinted = false
            ),
            background = true
        )
    }
}

@Preview(heightDp = OudsPreviewableComponent.ListItem.WithEdgeToEdgeDisabled.PreviewHeightDp, device = OudsPreviewDevice)
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsNavigationListItemWithEdgeToEdgeDisabled() = PreviewOudsNavigationListItemWithEdgeToEdgeDisabled(theme = getPreviewTheme())

@Composable
internal fun PreviewOudsNavigationListItemWithEdgeToEdgeDisabled(theme: OudsThemeContract) = OudsPreview(theme = theme) {
    PreviewEnumEntries<OudsListItemState>(maxEnumEntriesInEachRow = 1) {
        OudsListItem(
            onClick = {},
            label = "Label",
            description = "Description",
            trailing = OudsListItemTrailing.Icon(
                imageVector = Icons.Outlined.FavoriteBorder,
                contentDescription = ""
            ),
            background = true,
            edgeToEdge = false
        )
    }
}

internal data class OudsListItemPreviewParameter<T : OudsListItemLeadingTrailing, S : OudsListItemLeadingTrailing>(
    val label: String? = null,
    val labelContent: @Composable (OudsListItemScope.() -> Unit)? = null,
    val indicator: OudsListItemIndicator = OudsListItemDefaults.Indicator,
    val verticalAlignment: OudsListItemVerticalAlignment = OudsListItemDefaults.VerticalAlignment,
    val overline: String? = null,
    val extraLabel: String? = null,
    val description: String? = null,
    val leading: T? = null,
    val trailing: S? = null,
    val decoration: OudsListItemDecoration = OudsListItemDecoration.BackgroundOnInteraction(divider = true),
    val helperText: String? = null,
    val boldLabel: Boolean = false,
    val belowTextContent: @Composable (OudsListItemScope.() -> Unit)? = null,
    val bottomContent: @Composable (OudsListItemScope.() -> Unit)? = null,
    val enabled: Boolean = true
)

internal class OudsListItemPreviewParameterProvider : OudsBasicListItemPreviewParameterProvider<OudsListItemLeading, OudsListItemTrailing>(
    leading = listItemPreviewParameterLeading,
    trailing = listItemPreviewParameterTrailing
)

internal val listItemPreviewParameterLeading: (Int) -> OudsListItemLeading? = { index ->
    when (index) {
        0 -> OudsListItemLeading.Icon.Info()
        1 -> OudsListItemLeading.Icon(Icons.Outlined.FavoriteBorder, "")
        2 -> OudsListItemLeading.Image(CheckerboardPainter, "", OudsListItemImageSize.Medium, OudsListItemImageRatio.Square, roundedCorner = true)
        3 -> OudsListItemLeading.Content { PreviewCustomContent(state = state, modifier = Modifier.fillMaxSize()) }
        else -> null
    }
}

internal val listItemPreviewParameterTrailing: (Int) -> OudsListItemTrailing? = { index ->
    when (index) {
        0 -> OudsListItemTrailing.Icon(Icons.Outlined.FavoriteBorder, "")
        1 -> OudsListItemTrailing.Text(label = "Label", extraLabel = "Extra label")
        2 -> OudsListItemTrailing.Image(CheckerboardPainter, "", OudsListItemImageSize.ExtraLarge, OudsListItemImageRatio.Widescreen)
        3 -> OudsListItemTrailing.Content { PreviewCustomContent(state = state, modifier = Modifier.fillMaxSize()) }
        4 -> OudsListItemTrailing.Badge(count = 100, status = OudsBadgeStatus.Negative, size = OudsBadgeSize.Large)
        5 -> OudsListItemTrailing.Tag(label = "Almost used", status = OudsTagStatus.Warning(asset = null))
        else -> null
    }
}

internal open class OudsBasicListItemPreviewParameterProvider<T : OudsListItemLeadingTrailing, S : OudsListItemLeadingTrailing>(
    leading: (Int) -> T?,
    trailing: (Int) -> S?,
    decoration: (Int) -> OudsListItemDecoration = { index ->
        if (index == 1) OudsListItemDecoration.Background(divider = true) else OudsListItemDecoration.BackgroundOnInteraction(divider = true)
    },
    size: OudsListItemSize = OudsListItemSize.Default
) : BasicPreviewParameterProvider<OudsListItemPreviewParameter<T, S>>(*getListItemPreviewParameterValues(leading, trailing, decoration, size).toTypedArray())

@Composable
private fun PreviewCustomContent(state: OudsListItemState, modifier: Modifier = Modifier) {
    val enabled = state == OudsListItemState.Enabled
    Box(
        modifier = modifier
            .background(if (enabled) OudsTheme.colorScheme.surface.status.info.muted else OudsTheme.colorScheme.action.disabled)
            .padding(all = OudsTheme.spaces.fixed.extraSmall),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "SLOT",
            style = OudsTheme.typography.label.small.strong,
            color = if (enabled) OudsTheme.colorScheme.content.onStatus.info.muted else OudsTheme.colorScheme.content.onAction.disabled,
        )
    }
}

private fun <T, S> getListItemPreviewParameterValues(
    leading: (Int) -> T?,
    trailing: (Int) -> S?,
    decoration: (Int) -> OudsListItemDecoration,
    size: OudsListItemSize
): List<OudsListItemPreviewParameter<T, S>> where T : OudsListItemLeadingTrailing, S : OudsListItemLeadingTrailing {
    val label = "Label"
    val overline = "Overline"
    val extraLabel = "Extra label"
    val description = "Description"
    val helperText = "Helper text"

    return List(6) { index ->
        when (index) {
            0 -> OudsListItemPreviewParameter(
                label = label,
                overline = overline,
                extraLabel = extraLabel,
                description = description,
                helperText = helperText,
                leading = leading(index),
                trailing = trailing(index),
                verticalAlignment = OudsListItemVerticalAlignment.Top,
                decoration = decoration(index)
            )
            1 -> OudsListItemPreviewParameter(
                label = label,
                indicator = OudsListItemIndicator.External,
                leading = leading(index),
                trailing = trailing(index),
                decoration = decoration(index),
                boldLabel = true
            )
            2 -> OudsListItemPreviewParameter(
                label = label,
                boldLabel = true,
                indicator = OudsListItemIndicator.Previous,
                overline = overline,
                extraLabel = extraLabel,
                description = description,
                leading = leading(index),
                trailing = trailing(index),
                decoration = decoration(index)
            )
            3 -> OudsListItemPreviewParameter(
                label = when (size) {
                    OudsListItemSize.Default -> null
                    OudsListItemSize.Small -> label
                },
                labelContent = when (size) {
                    OudsListItemSize.Default -> { { PreviewCustomContent(state = state, modifier = Modifier.fillMaxWidth()) } }
                    OudsListItemSize.Small -> null
                },
                indicator = OudsListItemIndicator.Previous,
                overline = overline,
                extraLabel = extraLabel,
                description = description,
                leading = leading(index),
                trailing = trailing(index),
                belowTextContent = { PreviewCustomContent(state = state, modifier = Modifier.fillMaxWidth()) },
                bottomContent = { PreviewCustomContent(state = state, modifier = Modifier.fillMaxWidth()) },
                decoration = decoration(index)
            )
            else -> OudsListItemPreviewParameter(
                label = label,
                indicator = OudsListItemIndicator.Next,
                overline = overline,
                extraLabel = extraLabel,
                description = description,
                leading = leading(index),
                trailing = trailing(index),
                decoration = decoration(index)
            )
        }
    }
}

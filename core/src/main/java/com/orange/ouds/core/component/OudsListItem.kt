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
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.remember
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
import com.orange.ouds.core.utilities.CheckerboardPainter
import com.orange.ouds.core.utilities.LayeredTintedPainter
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.OudsPreviewDevice
import com.orange.ouds.core.utilities.OudsPreviewLightDark
import com.orange.ouds.core.utilities.OudsPreviewableComponent
import com.orange.ouds.core.utilities.PreviewEnumEntries
import com.orange.ouds.core.utilities.getPreviewEnumEntry
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.core.utilities.rememberRainbowHeartPainter
import com.orange.ouds.foundation.ExperimentalOudsApi
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.theme.OudsThemeContract

/**
 * TODO update description when available and add version and guideline link
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
 * @param enabled Controls the enabled state of the list item. When `false`, the content is displayed in a disabled state. Defaults to `true`.
 * @param interactionSource Optional hoisted [MutableInteractionSource] for observing and emitting interactions for this list item.
 *
 * @sample com.orange.ouds.core.component.samples.OudsStaticListItemSample
 * @sample com.orange.ouds.core.component.samples.OudsListItemWithAllElementsSample
 * @sample com.orange.ouds.core.component.samples.OudsListItemWithImageSample
 * @sample com.orange.ouds.core.component.samples.OudsListItemWithUntintedIconSample
 */
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
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null
) {
    OudsListItem(
        size = OudsListItemSize.Default,
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
        decoration = listItemDecoration(background, divider),
        helperText = helperText,
        boldLabel = boldLabel,
        enabled = enabled,
        card = false,
        interactionSource = interactionSource
    )
}

/**
 * TODO update description when available and add version and guideline link
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
 * @param enabled Controls the enabled state of the list item. When `false`, the item is not clickable and content is displayed in a disabled state. Defaults to `true`.
 * @param interactionSource Optional hoisted [MutableInteractionSource] for observing and emitting interactions for this list item.
 *
 * @sample com.orange.ouds.core.component.samples.OudsNavigationListItemSample
 * @sample com.orange.ouds.core.component.samples.OudsListItemWithAllElementsSample
 * @sample com.orange.ouds.core.component.samples.OudsListItemWithImageSample
 * @sample com.orange.ouds.core.component.samples.OudsListItemWithUntintedIconSample
 */
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
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null
) {
    OudsListItem(
        size = OudsListItemSize.Default,
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
        decoration = listItemDecoration(background, divider),
        helperText = helperText,
        boldLabel = boldLabel,
        enabled = enabled,
        card = false,
        interactionSource = interactionSource
    )
}

@Composable
internal fun OudsListItem(
    size: OudsListItemSize,
    label: String,
    onClick: (() -> Unit)?,
    modifier: Modifier,
    indicator: OudsListItemIndicator?,
    verticalAlignment: OudsListItemVerticalAlignment,
    overline: String?,
    extraLabel: String?,
    description: String?,
    leading: OudsListItemLeadingTrailing?,
    trailing: OudsListItemLeadingTrailing?,
    decoration: OudsListItemDecoration,
    helperText: String?,
    boldLabel: Boolean,
    enabled: Boolean,
    card: Boolean,
    interactionSource: MutableInteractionSource?
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
            Row(
                modifier = clickableModifier
                    .fillMaxWidth()
                    .heightIn(min = minHeight(size))
                    .background(color = backgroundColor.value, shape = shape)
                    .border(state = state, decoration = decoration, cornerRadius = borderRadius, outlineColor = outlineBorderColor.value)
                    .outerBorder(state = state, shape = shape)
                    .containerPadding(size = size, verticalAlignment = verticalAlignment)
                    .semantics(mergeDescendants = true) { },
                horizontalArrangement = Arrangement.spacedBy(space.columnGap),
                verticalAlignment = verticalAlignment(verticalAlignment)
            ) {
                if (indicator == OudsListItemIndicator.Previous) {
                    Indicator(drawableId = indicator.drawableId, state = state)
                } else {
                    leading?.let {
                        when (leading) {
                            is OudsListItemLeadingTrailing.Icon -> {
                                leading.PolymorphicContent(
                                    extraParameters = OudsListItemLeadingTrailing.Icon.ExtraParameters(state = state)
                                )
                            }
                            is OudsListItemLeadingTrailing.Image -> leading.PolymorphicContent()
                            is OudsListItemLeadingTrailing.Text -> {}
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
                    Text(
                        text = label,
                        style = if (boldLabel) OudsTheme.typography.label.large.strong else OudsTheme.typography.label.large.default,
                        color = contentColor(state = state)
                    )
                    if (!extraLabel.isNullOrBlank()) {
                        Text(text = extraLabel, style = OudsTheme.typography.label.medium.strong, color = contentColor(state = state))
                    }
                    if (!description.isNullOrBlank()) {
                        Text(text = description, style = OudsTheme.typography.label.medium.default, color = contentColor(state = state, muted = true))
                    }
                }

                trailing?.let {
                    when (trailing) {
                        is OudsListItemLeadingTrailing.Icon -> trailing.PolymorphicContent(
                            extraParameters = OudsListItemLeadingTrailing.Icon.ExtraParameters(
                                state = state
                            )
                        )
                        is OudsListItemLeadingTrailing.Text -> {
                            trailing.PolymorphicContent(
                                extraParameters = OudsListItemLeadingTrailing.Text.ExtraParameters(
                                    verticalAlignment = verticalAlignment,
                                    size = size
                                )
                            )
                        }
                        is OudsListItemLeadingTrailing.Image -> trailing.PolymorphicContent()
                    }
                }

                if (indicator != null && indicator in listOf(OudsListItemIndicator.Next, OudsListItemIndicator.External)) {
                    Indicator(drawableId = indicator.drawableId, state = state)
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
private fun Modifier.containerPadding(size: OudsListItemSize, verticalAlignment: OudsListItemVerticalAlignment) = with(OudsTheme.components.listItem) {
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
    }.padding(horizontal = space.paddingInline)
}

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
        card -> border.radius.default
        focused -> border.radius.default
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
    return if (background) OudsListItemDecoration.Background(divider) else OudsListItemDecoration.None(divider)
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
            get() = OudsTheme.drawableResources.component.controlItem.next
    }

    /**
     * Used for "backward" navigation. This indicator is positioned at the start of the list item and is not customizable.
     */
    object Previous : OudsListItemIndicator {
        override val drawableId
            @Composable
            get() = OudsTheme.drawableResources.component.controlItem.previous
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
 * TODO KDoc
 */
sealed class OudsListItemDecoration(val divider: Boolean) {
    object Outlined : OudsListItemDecoration(false)
    object OutlinedOnInteraction : OudsListItemDecoration(false)
    class Background(divider: Boolean) : OudsListItemDecoration(divider)
    class BackgroundOnInteraction(divider: Boolean) : OudsListItemDecoration(divider)
    internal class None(divider: Boolean) : OudsListItemDecoration(divider)
}

internal enum class OudsListItemState {
    Enabled, Hovered, Pressed, Disabled, Focused
}

sealed interface OudsListItemLeadingTrailing : OudsPolymorphicComponentContent {

    interface Icon : OudsListItemLeadingTrailing {
        @ConsistentCopyVisibility
        data class ExtraParameters internal constructor(internal val state: OudsListItemState) : OudsComponentContent.ExtraParameters()
    }

    interface Image : OudsListItemLeadingTrailing {
        val ratio: OudsListItemImageRatio
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

enum class OudsListItemIconSize {
    Medium, Large;

    internal fun toAssetSize() = when (this) {
        Medium -> OudsListItemAssetSize.Medium
        Large -> OudsListItemAssetSize.Large
    }
}

internal enum class OudsListItemIconStatus(
    val painterProvider: @Composable () -> Painter,
    val contentDescriptionProvider: (@Composable () -> String) = { "" }
) {
    Negative(
        { painterResource(OudsTheme.drawableResources.component.alert.importantFill) },
        { stringResource(R.string.core_common_error_a11y) }
    ),

    Positive({ painterResource(OudsTheme.drawableResources.component.alert.tickConfirmationFill) }),

    Info({ painterResource(OudsTheme.drawableResources.component.alert.infoFill) }),

    Warning(
        {
            val iconTokens = OudsTheme.components.icon
            LayeredTintedPainter(
                backPainter = painterResource(id = OudsTheme.drawableResources.component.alert.warningExternalShape),
                backPainterColor = iconTokens.color.content.status.warning.externalShape,
                frontPainter = painterResource(id = OudsTheme.drawableResources.component.alert.warningInternalShape),
                frontPainterColor = iconTokens.color.content.status.warning.internalShape
            )
        },
        { stringResource(R.string.core_common_warning_a11y) }
    );

    val tint
        @Composable
        get() = with(OudsTheme.colorScheme.content) {
            when (this@OudsListItemIconStatus) {
                Positive -> status.positive
                Warning -> Color.Unspecified
                Negative -> status.negative
                Info -> status.info
            }
        }
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

enum class OudsListItemImageSize {
    Medium, Large, ExtraLarge;

    internal fun toAssetSize() = when (this) {
        Medium -> OudsListItemAssetSize.Medium
        Large -> OudsListItemAssetSize.Large
        ExtraLarge -> OudsListItemAssetSize.ExtraLarge
    }
}

enum class OudsListItemTextStyle {
    Label, LabelMuted, LabelStrong
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

open class OudsListItemText internal constructor(
    private val label: String,
    private val style: OudsListItemTextStyle,
    private val extraLabel: String?
) : OudsComponentContent<OudsListItemLeadingTrailing.Text.ExtraParameters>(OudsListItemLeadingTrailing.Text.ExtraParameters::class.java),
    OudsListItemLeadingTrailing.Text {

    @Composable
    override fun Content(modifier: Modifier) {
        Column(modifier = modifier) {
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
            { status.painterProvider() },
            { status.contentDescriptionProvider() },
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
            { status.painterProvider() },
            { status.contentDescriptionProvider() },
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
     * Label as a list item trailing content.
     */
    class Text private constructor(
        label: String,
        style: OudsListItemTextStyle,
        extraLabel: String?
    ) : OudsListItemText(label, style, extraLabel), OudsListItemTrailing {

        /**
         * Creates an instance of [OudsListItemTrailing.Text].
         *
         * @param label Label displayed in trailing.
         * @param style Style applied to the label among [OudsListItemTextStyle] values.
         */
        constructor(label: String, style: OudsListItemTextStyle = OudsListItemTextStyle.Label) : this(label, style, null)

        /**
         * Creates an instance of [OudsListItemTrailing.Text].
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
            label = label,
            overline = overline,
            extraLabel = extraLabel,
            description = description,
            helperText = helperText,
            verticalAlignment = verticalAlignment,
            leading = leading,
            trailing = trailing,
            divider = decoration.divider,
            background = decoration is OudsListItemDecoration.Background || decoration is OudsListItemDecoration.BackgroundOnInteraction,
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
        PreviewEnumEntries<OudsListItemState>(maxEnumEntriesInEachRow = 1) {
            OudsListItem(
                onClick = {},
                indicator = indicator,
                label = label,
                overline = overline,
                extraLabel = extraLabel,
                description = description,
                helperText = helperText,
                verticalAlignment = verticalAlignment,
                leading = leading,
                trailing = trailing,
                divider = decoration.divider,
                background = decoration is OudsListItemDecoration.Background || decoration is OudsListItemDecoration.BackgroundOnInteraction,
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
    PreviewEnumEntries<OudsListItemState>(maxEnumEntriesInEachRow = 1) {
        OudsListItem(
            onClick = {},
            label = "Label",
            description = "Description",
            leading = OudsListItemLeading.Icon(
                painter = rememberRainbowHeartPainter(),
                contentDescription = "",
                tinted = false
            ),
            background = true,
        )
    }
}

internal data class OudsListItemPreviewParameter<T : OudsListItemLeadingTrailing, S : OudsListItemLeadingTrailing>(
    val label: String,
    val indicator: OudsListItemIndicator = OudsListItemDefaults.Indicator,
    val verticalAlignment: OudsListItemVerticalAlignment = OudsListItemDefaults.VerticalAlignment,
    val overline: String? = null,
    val extraLabel: String? = null,
    val description: String? = null,
    val leading: T? = null,
    val trailing: S? = null,
    val decoration: OudsListItemDecoration = OudsListItemDecoration.None(divider = true),
    val helperText: String? = null,
    val boldLabel: Boolean = false,
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
        else -> null
    }
}

internal val listItemPreviewParameterTrailing: (Int) -> OudsListItemTrailing? = { index ->
    when (index) {
        0 -> OudsListItemTrailing.Icon(Icons.Outlined.FavoriteBorder, "")
        1 -> OudsListItemTrailing.Text(label = "Label", extraLabel = "Extra label")
        2 -> OudsListItemTrailing.Image(CheckerboardPainter, "", OudsListItemImageSize.ExtraLarge, OudsListItemImageRatio.Widescreen)
        else -> null
    }
}

internal open class OudsBasicListItemPreviewParameterProvider<T : OudsListItemLeadingTrailing, S : OudsListItemLeadingTrailing>(
    leading: (Int) -> T?,
    trailing: (Int) -> S?,
    decoration: (Int) -> OudsListItemDecoration = { index ->
        if (index == 1) OudsListItemDecoration.Background(divider = true) else OudsListItemDecoration.None(divider = true)
    }
) : BasicPreviewParameterProvider<OudsListItemPreviewParameter<T, S>>(*getListItemPreviewParameterValues(leading, trailing, decoration).toTypedArray())

private fun <T, S> getListItemPreviewParameterValues(
    leading: (Int) -> T?,
    trailing: (Int) -> S?,
    decoration: (Int) -> OudsListItemDecoration
): List<OudsListItemPreviewParameter<T, S>> where T : OudsListItemLeadingTrailing, S : OudsListItemLeadingTrailing {
    val label = "Label"
    val overline = "Overline"
    val extraLabel = "Extra label"
    val description = "Description"
    val helperText = "Helper text"

    return List(3) { index ->
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
            else -> OudsListItemPreviewParameter(
                label = label,
                indicator = OudsListItemIndicator.Previous,
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

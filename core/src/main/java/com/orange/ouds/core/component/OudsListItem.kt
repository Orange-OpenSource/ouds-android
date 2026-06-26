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
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.orange.ouds.core.R
import com.orange.ouds.core.component.OudsListItemContent.Text.Style
import com.orange.ouds.core.component.common.outerBorder
import com.orange.ouds.core.component.content.OudsComponentContent
import com.orange.ouds.core.component.content.OudsComponentIcon
import com.orange.ouds.core.component.content.OudsPolymorphicComponentContent
import com.orange.ouds.core.component.content.PolymorphicContent
import com.orange.ouds.core.extensions.InteractionState
import com.orange.ouds.core.extensions.collectInteractionStateAsState
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.LayeredTintedPainter
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.OudsPreviewDevice
import com.orange.ouds.core.utilities.OudsPreviewLightDark
import com.orange.ouds.core.utilities.PreviewEnumEntries
import com.orange.ouds.core.utilities.getPreviewEnumEntry
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.theme.OudsThemeContract

/**
 * TODO List Item
 */
@Composable
fun OudsListItem(
    label: String,
    modifier: Modifier = Modifier,
    contentAlignment: OudsListItemContentAlignment = OudsListItemDefaults.ContentAlignment,
    overline: String? = null,
    extraLabel: String? = null,
    description: String? = null,
    leadingContent: OudsListItemLeadingContent? = null,
    trailingContent: OudsListItemTrailingContent? = null,
    divider: Boolean = true,
    background: Boolean = false,
    helperText: String? = null,
    boldLabel: Boolean = false,
    enabled: Boolean = true
) {
    OudsListItem(
        size = OudsListItemSize.Default,
        label = label,
        nullableOnClick = null,
        modifier = modifier,
        nullableChevron = null,
        contentAlignment = contentAlignment,
        overline = overline,
        extraLabel = extraLabel,
        description = description,
        leadingContent = leadingContent,
        trailingContent = trailingContent,
        divider = divider,
        background = background,
        helperText = helperText,
        boldLabel = boldLabel,
        enabled = enabled
    )
}

// TODO Navigation List Item
@Composable
fun OudsListItem(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    chevron: OudsListItemChevron = OudsListItemDefaults.Chevron,
    contentAlignment: OudsListItemContentAlignment = OudsListItemDefaults.ContentAlignment,
    overline: String? = null,
    extraLabel: String? = null,
    description: String? = null,
    leadingContent: OudsListItemLeadingContent? = null,
    trailingContent: OudsListItemTrailingContent? = null,
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
        nullableOnClick = onClick,
        modifier = modifier,
        nullableChevron = chevron,
        contentAlignment = contentAlignment,
        overline = overline,
        extraLabel = extraLabel,
        description = description,
        leadingContent = leadingContent,
        trailingContent = trailingContent,
        divider = divider,
        background = background,
        helperText = helperText,
        boldLabel = boldLabel,
        enabled = enabled,
        interactionSource = interactionSource
    )
}

@Composable
internal fun OudsListItem(
    size: OudsListItemSize,
    label: String,
    nullableOnClick: (() -> Unit)?,
    modifier: Modifier,
    nullableChevron: OudsListItemChevron?,
    contentAlignment: OudsListItemContentAlignment,
    overline: String?,
    extraLabel: String?,
    description: String?,
    leadingContent: OudsListItemContent?,
    trailingContent: OudsListItemContent?,
    divider: Boolean,
    background: Boolean,
    helperText: String?,
    boldLabel: Boolean,
    enabled: Boolean,
    interactionSource: MutableInteractionSource? = null
) {
    @Suppress("NAME_SHADOWING") val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    val interactionState by interactionSource.collectInteractionStateAsState()
    val state = getListItemState(enabled = enabled, interactionState = interactionState)

    with(OudsTheme.componentsTokens.listItem) {
        Column(
            modifier = modifier.widthIn(min = sizeMinWidth.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = minHeight(size))
                    .background(color = backgroundColor(state = state, background = background))
                    .outerBorder(state = state)
                    .containerPadding(size = size, contentAlignment = contentAlignment)
                    .semantics(mergeDescendants = true) {
                        if (nullableOnClick != null) {
                            onClick(null) {
                                nullableOnClick()
                                true
                            }
                        }
                    },
                horizontalArrangement = Arrangement.spacedBy(spaceColumnGap.value),
                verticalAlignment = verticalAlignment(contentAlignment)
            ) {
                if (nullableChevron == OudsListItemChevron.Previous) {
                    Icon(
                        modifier = Modifier.size(OudsTheme.componentsTokens.controlItem.sizeAssetSmall.value),
                        painter = painterResource(OudsTheme.drawableResources.component.controlItem.previous),
                        contentDescription = "",
                        tint = chevronColor(state = state)
                    )
                } else {
                    leadingContent?.let {
                        if (leadingContent is OudsListItemContent.Icon) {
                            leadingContent.PolymorphicContent(
                                modifier = Modifier.size(leadingContent.size.value),
                                extraParameters =
                                    OudsListItemContent.Icon.ExtraParameters(
                                        tint = actionColor(
                                            state = state,
                                            tint = if (leadingContent is OudsListItemContent.StatusIcon) leadingContent.status.tint else null
                                        )
                                    )
                            )
                        } else {
                            leadingContent.PolymorphicContent(
                                modifier = Modifier.apply {
                                    if (leadingContent is OudsListItemContent.Image) {
                                        height(leadingContent.size.value).width(leadingContent.format.ratio * leadingContent.size.value)
                                    }
                                }
                            )
                        }
                    }
                }

                Column(modifier = Modifier.weight(1f)) {
                    overline?.let {
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

                trailingContent?.let {
                    when (trailingContent) {
                        is OudsListItemContent.Icon -> {
                            trailingContent.PolymorphicContent(
                                modifier = Modifier.size(trailingContent.size.value),
                                extraParameters =
                                    OudsListItemContent.Icon.ExtraParameters(
                                        tint = actionColor(
                                            state = state,
                                            tint = if (trailingContent is OudsListItemContent.StatusIcon) trailingContent.status.tint else null
                                        )
                                    )
                            )
                        }
                        is OudsListItemContent.Text -> {
                            trailingContent.PolymorphicContent(
                                modifier = if (contentAlignment == OudsListItemContentAlignment.Top) {
                                    Modifier.padding(top = OudsTheme.componentsTokens.listItem.spacePaddingBlockTopAlignmentTopTextContainerSmall.value)
                                } else {
                                    Modifier
                                }
                            )
                        }
                        else -> {
                            trailingContent.PolymorphicContent(
                                modifier = Modifier.apply {
                                    if (trailingContent is OudsListItemContent.Image) {
                                        height(trailingContent.size.value).width(trailingContent.format.ratio * trailingContent.size.value)
                                    }
                                }
                            )
                        }
                    }
                }

                if (nullableChevron == OudsListItemChevron.Next) {
                    Icon(
                        modifier = Modifier.size(OudsTheme.componentsTokens.controlItem.sizeAssetSmall.value),
                        painter = painterResource(OudsTheme.drawableResources.component.controlItem.next),
                        contentDescription = "",
                        tint = chevronColor(state = state)
                    )
                }
            }

            if (divider) {
                OudsHorizontalDivider(color = OudsTheme.colorScheme.border.muted) // TODO add edgeToEdge management
            }

            if (!helperText.isNullOrBlank()) {
                Text(
                    modifier = Modifier
                        .padding(top = spacePaddingBlockTopHelperText.value)
                        .padding(horizontal = spacePaddingInline.value),
                    text = helperText,
                    style = OudsTheme.typography.label.medium.default,
                    color = contentColor(state = state, muted = true)
                )
            }
        }
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
private fun Modifier.containerPadding(size: OudsListItemSize, contentAlignment: OudsListItemContentAlignment) = with(OudsTheme.componentsTokens.listItem) {
    when (size) {
        OudsListItemSize.Small -> when (contentAlignment) {
            OudsListItemContentAlignment.Center -> padding(vertical = spacePaddingBlockSmall.value)
            OudsListItemContentAlignment.Top -> padding(
                top = spacePaddingBlockTopAlignmentTopCounterweightSmall.value,
                bottom = spacePaddingBlockSmall.value
            )
        }
        OudsListItemSize.Default -> when (contentAlignment) {
            OudsListItemContentAlignment.Center -> padding(vertical = spacePaddingBlockDefault.value)
            OudsListItemContentAlignment.Top -> padding(
                top = spacePaddingBlockTopAlignmentTopCounterweightDefault.value,
                bottom = spacePaddingBlockDefault.value
            )
        }
    }.padding(horizontal = spacePaddingInline.value)
}

@Composable
private fun backgroundColor(state: OudsListItemState, background: Boolean) = with(OudsTheme.colorScheme.action.support) {
    when (state) {
        OudsListItemState.Enabled, OudsListItemState.Disabled -> if (background) OudsTheme.colorScheme.action.support.enabled else Color.Transparent
        OudsListItemState.Focused -> focus
        OudsListItemState.Hovered -> hover
        OudsListItemState.Pressed -> pressed
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
private fun chevronColor(state: OudsListItemState) = with(OudsTheme.colorScheme.action) {
    when (state) {
        OudsListItemState.Enabled -> OudsTheme.componentsTokens.link.colorChevronEnabled.value
        OudsListItemState.Focused -> focus
        OudsListItemState.Hovered -> hover
        OudsListItemState.Pressed -> pressed
        OudsListItemState.Disabled -> disabled
    }
}

@Composable
private fun minHeight(size: OudsListItemSize) = with(OudsTheme.componentsTokens.listItem) {
    when (size) {
        OudsListItemSize.Default -> sizeMinHeightDefault.dp
        OudsListItemSize.Small -> sizeMinHeightSmall.value
    }
}

@Composable
private fun verticalAlignment(contentAlignment: OudsListItemContentAlignment) = when (contentAlignment) {
    OudsListItemContentAlignment.Center -> Alignment.CenterVertically
    OudsListItemContentAlignment.Top -> Alignment.Top
}

/**
 * Default values for [OudsListItem].
 */
object OudsListItemDefaults {

    /**
     * Default content alignment of an [OudsListItem].
     */
    val ContentAlignment = OudsListItemContentAlignment.Center

    /**
     * Default navigation chevron of an [OudsListItem].
     */
    val Chevron = OudsListItemChevron.Next

    /**
     * Default size of an [OudsListItem].
     */
    val Size = OudsListItemSize.Default
}

/**
 * Represents the size of an [OudsListItem].
 */
enum class OudsListItemSize {
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
enum class OudsListItemContentAlignment {
    /**
     * Elements are vertically centered.
     */
    Center,

    /**
     * Elements are aligned to the top.
     */
    Top
}

/**
 * Represents the navigation chevron of an [OudsListItem].
 */
enum class OudsListItemChevron {

    /**
     * Used in a standard navigation context. This chevron is positioned at the end of the list item and is not customizable.
     */
    Next,

    /**
     * Used for "backward" navigation. This chevron is positioned at the start of the list item and is not customizable.
     */
    Previous
}

internal enum class OudsListItemState {
    Enabled, Hovered, Pressed, Disabled, Focused
}

interface OudsListItemContent : OudsPolymorphicComponentContent {
    interface Asset {
        val size: Size

        enum class Size {
            Small, Medium, Large, ExtraLarge;

            val value: Dp
                @Composable
                get() = with(OudsTheme.componentsTokens.listItem) {
                    when (this@Size) {
                        Small -> sizeAssetSmall.value
                        Medium -> sizeAssetMedium.value
                        Large -> sizeAssetLarge.dp
                        ExtraLarge -> sizeAssetXlarge.dp
                    }
                }
        }
    }

    interface Icon : Asset {
        data class ExtraParameters(internal val tint: Color) : OudsComponentContent.ExtraParameters()

        enum class Size {
            Medium, Large;

            val assetSize: Asset.Size
                get() = when (this) {
                    Medium -> Asset.Size.Medium
                    Large -> Asset.Size.Large
                }
        }
    }

    interface Image : Asset {
        val format: Format

        enum class Format {
            Square,
            Panoramic;

            internal val ratio: Float
                get() = when (this) {
                    Square -> 1f
                    Panoramic -> 16f / 9f
                }
        }

        enum class Size {
            Medium, Large, ExtraLarge;

            val assetSize: Asset.Size
                get() = when (this) {
                    Medium -> Asset.Size.Medium
                    Large -> Asset.Size.Large
                    ExtraLarge -> Asset.Size.ExtraLarge
                }
        }
    }

    interface StatusIcon {
        val status: Status

        enum class Status(
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
                    val iconTokens = OudsTheme.componentsTokens.icon
                    LayeredTintedPainter(
                        backPainter = painterResource(id = OudsTheme.drawableResources.component.alert.warningExternalShape),
                        backPainterColor = iconTokens.colorContentStatusWarningExternalShape.value,
                        frontPainter = painterResource(id = OudsTheme.drawableResources.component.alert.warningInternalShape),
                        frontPainterColor = iconTokens.colorContentStatusWarningInternalShape.value
                    )
                },
                { stringResource(R.string.core_common_warning_a11y) }
            );

            val tint
                @Composable
                get() = with(OudsTheme.colorScheme.content) {
                    when (this@Status) {
                        Positive -> status.positive
                        Warning -> Color.Unspecified
                        Negative -> status.negative
                        Info -> status.info
                    }
                }
        }
    }

    interface Text {
        enum class Style {
            Label, LabelMuted, LabelStrong
        }
    }
}

internal open class ListItemIcon internal constructor(
    graphicsObjectProvider: @Composable (ListItemIcon) -> Any,
    contentDescriptionProvider: @Composable (ListItemIcon) -> String,
    override val tinted: Boolean,
    override val size: OudsListItemContent.Asset.Size
) : OudsComponentIcon<OudsListItemContent.Icon.ExtraParameters, ListItemIcon>(
    OudsListItemContent.Icon.ExtraParameters::class.java,
    graphicsObjectProvider,
    contentDescriptionProvider
), OudsListItemContent.Icon {

    override val tint: Color?
        @Composable
        get() = extraParameters.tint
}

internal class ListItemImage(
    private val graphicsObject: Any?,
    private val contentDescription: String,
    override val size: OudsListItemContent.Asset.Size,
    private val imageFormat: OudsListItemContent.Image.Format,
    private val contentScale: ContentScale
) : OudsComponentContent<Nothing>(Nothing::class.java), OudsListItemContent.Image {

    override val format: OudsListItemContent.Image.Format
        get() = imageFormat

    constructor(
        painter: Painter,
        contentDescription: String,
        size: OudsListItemContent.Asset.Size,
        format: OudsListItemContent.Image.Format = OudsListItemContent.Image.Format.Square,
        contentScale: ContentScale = ContentScale.Fit
    ) : this(painter as Any, contentDescription, size, format, contentScale)

    constructor(
        imageVector: ImageVector,
        contentDescription: String,
        size: OudsListItemContent.Asset.Size,
        format: OudsListItemContent.Image.Format = OudsListItemContent.Image.Format.Square,
        contentScale: ContentScale = ContentScale.Fit
    ) : this(imageVector as Any, contentDescription, size, format, contentScale)

    constructor(
        bitmap: ImageBitmap,
        contentDescription: String,
        size: OudsListItemContent.Asset.Size,
        format: OudsListItemContent.Image.Format = OudsListItemContent.Image.Format.Square,
        contentScale: ContentScale = ContentScale.Fit
    ) : this(bitmap as Any, contentDescription, size, format, contentScale)

    @Composable
    override fun Content(modifier: Modifier) {
        val imageModifier = Modifier
            .height(size.value)
            .width(size.value * format.ratio)
            .then(modifier)

        when (graphicsObject) {
            is Painter -> Image(
                painter = graphicsObject,
                contentDescription = contentDescription,
                modifier = imageModifier,
                contentScale = contentScale
            )
            is ImageVector -> Image(
                imageVector = graphicsObject,
                contentDescription = contentDescription,
                modifier = imageModifier,
                contentScale = contentScale
            )
            is ImageBitmap -> Image(
                bitmap = graphicsObject,
                contentDescription = contentDescription,
                modifier = imageModifier,
                contentScale = contentScale
            )
        }
    }
}

internal class ListItemTrailingText(
    private val label: String,
    private val style: Style
) : OudsComponentContent<Nothing>(Nothing::class.java) {

    @Composable
    override fun Content(modifier: Modifier) {
        Text(
            modifier = modifier,
            text = label,
            style = when (style) {
                Style.Label, Style.LabelMuted -> OudsTheme.typography.label.large.default
                Style.LabelStrong -> OudsTheme.typography.label.large.strong
            },
            color = if (style == Style.LabelMuted) OudsTheme.colorScheme.content.muted else OudsTheme.colorScheme.content.default
        )
    }
}

/**
 * A leading content of an [OudsListItem].
 */
sealed interface OudsListItemLeadingContent : OudsListItemContent {

    /**
     * An icon as a list item leading content.
     */
    open class Icon internal constructor(
        internal val listItemIcon: ListItemIcon
    ) : OudsComponentContent<OudsListItemContent.Icon.ExtraParameters>(
        OudsListItemContent.Icon.ExtraParameters::class.java
    ), OudsListItemLeadingContent, OudsListItemContent.Icon {

        override val size get() = listItemIcon.size
        internal val tinted get() = listItemIcon.tinted

        /**
         * Creates an instance of [OudsListItemLeadingContent.Icon].
         *
         * @param painter Painter of the icon.
         * @param contentDescription The content description associated with this [OudsListItemLeadingContent.Icon].
         * @param size Size of the icon among [OudsListItemContent.Icon.Size] values.
         * @param tinted Controls whether the icon should be tinted with the theme color. Defaults to `true`.
         *   When set to `false`, the icon is displayed with its original colors (e.g., for multicolor icons).
         *   Note that untinted icons must ensure sufficient contrast with the background for accessibility reasons.
         */
        constructor(
            painter: Painter,
            contentDescription: String,
            size: OudsListItemContent.Icon.Size = OudsListItemContent.Icon.Size.Medium,
            tinted: Boolean = true
        ) : this(
            ListItemIcon(
                { painter as Any },
                { contentDescription },
                tinted,
                size.assetSize
            )
        )

        /**
         * Creates an instance of [OudsListItemLeadingContent.Icon].
         *
         * @param imageVector Image vector of the icon.
         * @param contentDescription The content description associated with this [OudsListItemLeadingContent.Icon].
         * @param size Size of the icon among [OudsListItemContent.Icon.Size] values.
         * @param tinted Controls whether the icon should be tinted with the theme color. Defaults to `true`.
         *   When set to `false`, the icon is displayed with its original colors (e.g., for multicolor icons).
         *   Note that untinted icons must ensure sufficient contrast with the background for accessibility reasons.
         */
        constructor(
            imageVector: ImageVector,
            contentDescription: String,
            size: OudsListItemContent.Icon.Size = OudsListItemContent.Icon.Size.Medium,
            tinted: Boolean = true
        ) : this(
            ListItemIcon(
                { imageVector as Any },
                { contentDescription },
                tinted,
                size.assetSize
            )
        )

        /**
         * Creates an instance of [OudsListItemLeadingContent.Icon].
         *
         * @param bitmap Image bitmap of the icon.
         * @param contentDescription The content description associated with this [OudsListItemLeadingContent.Icon].
         * @param size Size of the icon among [OudsListItemContent.Icon.Size] values.
         * @param tinted Controls whether the icon should be tinted with the theme color. Defaults to `true`.
         *   When set to `false`, the icon is displayed with its original colors (e.g., for multicolor icons).
         *   Note that untinted icons must ensure sufficient contrast with the background for accessibility reasons.
         */
        constructor(
            bitmap: ImageBitmap,
            contentDescription: String,
            size: OudsListItemContent.Icon.Size = OudsListItemContent.Icon.Size.Medium,
            tinted: Boolean = true
        ) : this(
            ListItemIcon(
                { bitmap as Any },
                { contentDescription },
                tinted,
                size.assetSize
            )
        )

        // TODO KDoc
        class Info(size: OudsListItemContent.Icon.Size = OudsListItemContent.Icon.Size.Medium) : Icon(
            ListItemIcon(
                { OudsListItemContent.StatusIcon.Status.Info.painterProvider() },
                { OudsListItemContent.StatusIcon.Status.Info.contentDescriptionProvider() },
                true,
                size.assetSize
            )
        ), OudsListItemContent.StatusIcon {
            override val status: OudsListItemContent.StatusIcon.Status = OudsListItemContent.StatusIcon.Status.Info
        }

        // TODO KDoc
        class Negative(size: OudsListItemContent.Icon.Size = OudsListItemContent.Icon.Size.Medium) : Icon(
            ListItemIcon(
                { OudsListItemContent.StatusIcon.Status.Negative.painterProvider() },
                { OudsListItemContent.StatusIcon.Status.Negative.contentDescriptionProvider() },
                true,
                size.assetSize
            )
        ), OudsListItemContent.StatusIcon {
            override val status: OudsListItemContent.StatusIcon.Status = OudsListItemContent.StatusIcon.Status.Negative
        }

        // TODO KDoc
        class Positive(size: OudsListItemContent.Icon.Size = OudsListItemContent.Icon.Size.Medium) : Icon(
            ListItemIcon(
                { OudsListItemContent.StatusIcon.Status.Positive.painterProvider() },
                { OudsListItemContent.StatusIcon.Status.Positive.contentDescriptionProvider() },
                true,
                size.assetSize
            )
        ), OudsListItemContent.StatusIcon {
            override val status: OudsListItemContent.StatusIcon.Status = OudsListItemContent.StatusIcon.Status.Positive
        }

        // TODO KDoc
        class Warning(size: OudsListItemContent.Icon.Size = OudsListItemContent.Icon.Size.Medium) : Icon(
            ListItemIcon(
                { OudsListItemContent.StatusIcon.Status.Warning.painterProvider() },
                { OudsListItemContent.StatusIcon.Status.Warning.contentDescriptionProvider() },
                true,
                size.assetSize
            )
        ), OudsListItemContent.StatusIcon {
            override val status: OudsListItemContent.StatusIcon.Status = OudsListItemContent.StatusIcon.Status.Warning
        }

        @Composable
        override fun Content(modifier: Modifier) {
            listItemIcon.Content(modifier)
        }
    }

    /**
     * An image as a list item leading content.
     */
    class Image internal constructor(
        private val listItemImage: ListItemImage
    ) : OudsComponentContent<Nothing>(Nothing::class.java), OudsSmallListItemLeadingContent, OudsListItemContent.Image {

        override val size
            get() = listItemImage.size
        override val format
            get() = listItemImage.format

        /**
         * Creates an instance of [OudsListItemLeadingContent.Image].
         *
         * @param painter Painter of the image.
         * @param contentDescription The content description associated with this image.
         * @param size Size of the icon among [OudsListItemContent.Image.Size] values.
         * @param format Format of the image among [OudsListItemContent.Image.Format] values.
         * @param contentScale Scale parameter used to determine the aspect ratio scaling to be used if the bounds are a different size from the intrinsic size
         * of the [painter].
         */
        constructor(
            painter: Painter,
            contentDescription: String,
            size: OudsListItemContent.Image.Size,
            format: OudsListItemContent.Image.Format = OudsListItemContent.Image.Format.Square,
            contentScale: ContentScale = ContentScale.Fit
        ) : this(ListItemImage(painter, contentDescription, size.assetSize, format, contentScale))

        /**
         * Creates an instance of [OudsListItemLeadingContent.Image].
         *
         * @param imageVector Image vector of the image.
         * @param contentDescription The content description associated with this image.
         * @param size Size of the icon among [OudsListItemContent.Image.Size] values.
         * @param format Format of the image among [OudsListItemContent.Image.Format] values.
         * @param contentScale Scale parameter used to determine the aspect ratio scaling to be used if the bounds are a different size from the intrinsic size
         * of the [imageVector].
         */
        constructor(
            imageVector: ImageVector,
            contentDescription: String,
            size: OudsListItemContent.Image.Size,
            format: OudsListItemContent.Image.Format = OudsListItemContent.Image.Format.Square,
            contentScale: ContentScale = ContentScale.Fit
        ) : this(ListItemImage(imageVector, contentDescription, size.assetSize, format, contentScale))

        /**
         * Creates an instance of [OudsListItemLeadingContent.Image].
         *
         * @param bitmap Image bitmap of the image.
         * @param contentDescription The content description associated with this image.
         * @param size Size of the icon among [OudsListItemContent.Image.Size] values.
         * @param format Format of the image among [OudsListItemContent.Image.Format] values.
         * @param contentScale Scale parameter used to determine the aspect ratio scaling to be used if the bounds are a different size from the intrinsic size
         * of the [bitmap].
         */
        constructor(
            bitmap: ImageBitmap,
            contentDescription: String,
            size: OudsListItemContent.Image.Size,
            format: OudsListItemContent.Image.Format = OudsListItemContent.Image.Format.Square,
            contentScale: ContentScale = ContentScale.Fit
        ) : this(ListItemImage(bitmap, contentDescription, size.assetSize, format, contentScale))

        @Composable
        override fun Content(modifier: Modifier) {
            listItemImage.Content(modifier)
        }
    }
}

/**
 * A trailing content of an [OudsListItem].
 */
sealed interface OudsListItemTrailingContent : OudsListItemContent {

    /**
     * An icon as a list item trailing content.
     */
    open class Icon internal constructor(
        internal val listItemIcon: ListItemIcon
    ) : OudsComponentContent<OudsListItemContent.Icon.ExtraParameters>(
        OudsListItemContent.Icon.ExtraParameters::class.java
    ), OudsListItemTrailingContent, OudsListItemContent.Icon {

        override val size get() = listItemIcon.size
        internal val tinted get() = listItemIcon.tinted

        /**
         * Creates an instance of [OudsListItemTrailingContent.Icon].
         *
         * @param painter Painter of the icon.
         * @param contentDescription The content description associated with this [OudsListItemTrailingContent.Icon].
         * @param size Size of the icon among [OudsListItemContent.Icon.Size] values.
         * @param tinted Controls whether the icon should be tinted with the theme color. Defaults to `true`.
         *   When set to `false`, the icon is displayed with its original colors (e.g., for multicolor icons).
         *   Note that untinted icons must ensure sufficient contrast with the background for accessibility reasons.
         */
        constructor(
            painter: Painter,
            contentDescription: String,
            size: OudsListItemContent.Icon.Size = OudsListItemContent.Icon.Size.Medium,
            tinted: Boolean = true
        ) : this(
            ListItemIcon(
                { painter as Any },
                { contentDescription },
                tinted,
                size.assetSize
            )
        )

        /**
         * Creates an instance of [OudsListItemTrailingContent.Icon].
         *
         * @param imageVector Image vector of the icon.
         * @param contentDescription The content description associated with this [OudsListItemTrailingContent.Icon].
         * @param size Size of the icon among [OudsListItemContent.Icon.Size] values.
         * @param tinted Controls whether the icon should be tinted with the theme color. Defaults to `true`.
         *   When set to `false`, the icon is displayed with its original colors (e.g., for multicolor icons).
         *   Note that untinted icons must ensure sufficient contrast with the background for accessibility reasons.
         */
        constructor(
            imageVector: ImageVector,
            contentDescription: String,
            size: OudsListItemContent.Icon.Size = OudsListItemContent.Icon.Size.Medium,
            tinted: Boolean = true
        ) : this(
            ListItemIcon(
                { imageVector as Any },
                { contentDescription },
                tinted,
                size.assetSize
            )
        )

        /**
         * Creates an instance of [OudsListItemTrailingContent.Icon].
         *
         * @param bitmap Image bitmap of the icon.
         * @param contentDescription The content description associated with this [OudsListItemTrailingContent.Icon].
         * @param size Size of the icon among [OudsListItemContent.Icon.Size] values.
         * @param tinted Controls whether the icon should be tinted with the theme color. Defaults to `true`.
         *   When set to `false`, the icon is displayed with its original colors (e.g., for multicolor icons).
         *   Note that untinted icons must ensure sufficient contrast with the background for accessibility reasons.
         */
        constructor(
            bitmap: ImageBitmap,
            contentDescription: String,
            size: OudsListItemContent.Icon.Size = OudsListItemContent.Icon.Size.Medium,
            tinted: Boolean = true
        ) : this(
            ListItemIcon(
                { bitmap as Any },
                { contentDescription },
                tinted,
                size.assetSize
            )
        )

        class Info(size: OudsListItemContent.Icon.Size = OudsListItemContent.Icon.Size.Medium) : Icon(
            ListItemIcon(
                { OudsListItemContent.StatusIcon.Status.Info.painterProvider() },
                { OudsListItemContent.StatusIcon.Status.Info.contentDescriptionProvider() },
                true,
                size.assetSize
            )
        ), OudsListItemContent.StatusIcon {
            override val status: OudsListItemContent.StatusIcon.Status = OudsListItemContent.StatusIcon.Status.Info
        }

        class Negative(size: OudsListItemContent.Icon.Size = OudsListItemContent.Icon.Size.Medium) : Icon(
            ListItemIcon(
                { OudsListItemContent.StatusIcon.Status.Negative.painterProvider() },
                { OudsListItemContent.StatusIcon.Status.Negative.contentDescriptionProvider() },
                true,
                size.assetSize
            )
        ), OudsListItemContent.StatusIcon {
            override val status: OudsListItemContent.StatusIcon.Status = OudsListItemContent.StatusIcon.Status.Negative
        }

        class Positive(size: OudsListItemContent.Icon.Size = OudsListItemContent.Icon.Size.Medium) : Icon(
            ListItemIcon(
                { OudsListItemContent.StatusIcon.Status.Positive.painterProvider() },
                { OudsListItemContent.StatusIcon.Status.Positive.contentDescriptionProvider() },
                true,
                size.assetSize
            )
        ), OudsListItemContent.StatusIcon {
            override val status: OudsListItemContent.StatusIcon.Status = OudsListItemContent.StatusIcon.Status.Positive
        }

        class Warning(size: OudsListItemContent.Icon.Size = OudsListItemContent.Icon.Size.Medium) : Icon(
            ListItemIcon(
                { OudsListItemContent.StatusIcon.Status.Warning.painterProvider() },
                { OudsListItemContent.StatusIcon.Status.Warning.contentDescriptionProvider() },
                true,
                size.assetSize
            )
        ), OudsListItemContent.StatusIcon {
            override val status: OudsListItemContent.StatusIcon.Status = OudsListItemContent.StatusIcon.Status.Warning
        }

        @Composable
        override fun Content(modifier: Modifier) {
            listItemIcon.Content(modifier)
        }
    }


    /**
     * An image as a list item trailing content.
     */
    class Image internal constructor(
        private val listItemImage: ListItemImage
    ) : OudsComponentContent<Nothing>(Nothing::class.java), OudsSmallListItemLeadingContent, OudsListItemContent.Image {

        override val size
            get() = listItemImage.size
        override val format
            get() = listItemImage.format

        /**
         * Creates an instance of [OudsListItemTrailingContent.Image].
         *
         * @param painter Painter of the image.
         * @param contentDescription The content description associated with this image.
         * @param size Size of the icon among [OudsListItemContent.Image.Size] values.
         * @param format Format of the image among [OudsListItemContent.Image.Format] values.
         * @param contentScale Scale parameter used to determine the aspect ratio scaling to be used if the bounds are a different size from the intrinsic size
         * of the [painter].
         */
        constructor(
            painter: Painter,
            contentDescription: String,
            size: OudsListItemContent.Image.Size,
            format: OudsListItemContent.Image.Format = OudsListItemContent.Image.Format.Square,
            contentScale: ContentScale = ContentScale.Fit
        ) : this(ListItemImage(painter, contentDescription, size.assetSize, format, contentScale))

        /**
         * Creates an instance of [OudsListItemTrailingContent.Image].
         *
         * @param imageVector Image vector of the image.
         * @param contentDescription The content description associated with this image.
         * @param size Size of the icon among [OudsListItemContent.Image.Size] values.
         * @param format Format of the image among [OudsListItemContent.Image.Format] values.
         * @param contentScale Scale parameter used to determine the aspect ratio scaling to be used if the bounds are a different size from the intrinsic size
         * of the [imageVector].
         */
        constructor(
            imageVector: ImageVector,
            contentDescription: String,
            size: OudsListItemContent.Image.Size,
            format: OudsListItemContent.Image.Format = OudsListItemContent.Image.Format.Square,
            contentScale: ContentScale = ContentScale.Fit
        ) : this(ListItemImage(imageVector, contentDescription, size.assetSize, format, contentScale))

        /**
         * Creates an instance of [OudsListItemTrailingContent.Image].
         *
         * @param bitmap Image bitmap of the image.
         * @param contentDescription The content description associated with this image.
         * @param size Size of the icon among [OudsListItemContent.Image.Size] values.
         * @param format Format of the image among [OudsListItemContent.Image.Format] values.
         * @param contentScale Scale parameter used to determine the aspect ratio scaling to be used if the bounds are a different size from the intrinsic size
         * of the [bitmap].
         */
        constructor(
            bitmap: ImageBitmap,
            contentDescription: String,
            size: OudsListItemContent.Image.Size,
            format: OudsListItemContent.Image.Format = OudsListItemContent.Image.Format.Square,
            contentScale: ContentScale = ContentScale.Fit
        ) : this(ListItemImage(bitmap, contentDescription, size.assetSize, format, contentScale))

        @Composable
        override fun Content(modifier: Modifier) {
            listItemImage.Content(modifier)
        }
    }

    /**
     * Label as a list item trailing content.
     */
    class Text private constructor(
        private val listItemTrailingText: ListItemTrailingText,
        private val extraLabel: String?
    ) : OudsComponentContent<Nothing>(Nothing::class.java), OudsListItemTrailingContent {

        /**
         * Creates an instance of [OudsListItemTrailingContent.Text].
         *
         * @param label Label displayed in trailing.
         * @param style Style applied to the label among [OudsListItemContent.Text.Style] values.
         */
        constructor(label: String, style: Style = Style.Label) : this(ListItemTrailingText(label, style), null)

        /**
         * Creates an instance of [OudsListItemTrailingContent.Text].
         * Note that when an [extraLabel] is provided, the [label] retains the [Style.Label] style.
         *
         * @param label Label displayed in trailing.
         * @param extraLabel Label displayed below the main label.
         */
        constructor(label: String, extraLabel: String? = null) : this(ListItemTrailingText(label, Style.Label), extraLabel)

        @Composable
        override fun Content(modifier: Modifier) {
            Column(modifier = modifier) {
                listItemTrailingText.Content()
                extraLabel?.let {
                    Text(
                        text = extraLabel,
                        style = OudsTheme.typography.label.large.strong,
                        color = OudsTheme.colorScheme.content.default
                    )
                }
            }
        }
    }
}

@OudsPreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsStaticListItem(@PreviewParameter(OudsListItemPreviewParameterProvider::class) parameter: OudsListItemPreviewParameter) {
    PreviewOudsStaticListItem(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsStaticListItem(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsListItemPreviewParameter
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        OudsListItem(
            label = label,
            overline = overline,
            extraLabel = extraLabel,
            description = description,
            helperText = helperText,
            contentAlignment = contentAlignment,
            leadingContent = leadingContent,
            trailingContent = trailingContent,
            enabled = enabled
        )
    }
}

@Preview(name = "Light", heightDp = 1000, device = OudsPreviewDevice) // TODO set height in OudsPreviewableComponent
@Preview(
    name = "Dark",
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL,
    heightDp = 1000, // TODO set height in OudsPreviewableComponent
    device = OudsPreviewDevice
)
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsNavigationListItem(@PreviewParameter(OudsListItemPreviewParameterProvider::class) parameter: OudsListItemPreviewParameter) {
    PreviewOudsNavigationListItem(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsNavigationListItem(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsListItemPreviewParameter
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        PreviewEnumEntries<OudsListItemState>(maxEnumEntriesInEachRow = 1) {
            OudsListItem(
                onClick = {},
                chevron = chevron,
                label = label,
                overline = overline,
                extraLabel = extraLabel,
                description = description,
                helperText = helperText,
                contentAlignment = contentAlignment,
                leadingContent = leadingContent,
                trailingContent = trailingContent,
                enabled = enabled,
                interactionSource = remember { MutableInteractionSource() }
            )
        }
    }
}

internal data class OudsListItemPreviewParameter(
    val label: String,
    val chevron: OudsListItemChevron = OudsListItemDefaults.Chevron,
    val overline: String? = null,
    val extraLabel: String? = null,
    val description: String? = null,
    val helperText: String? = null,
    val contentAlignment: OudsListItemContentAlignment = OudsListItemContentAlignment.Center,
    val leadingContent: OudsListItemLeadingContent? = null,
    val trailingContent: OudsListItemTrailingContent? = null,
    val enabled: Boolean = true
)

internal class OudsListItemPreviewParameterProvider :
    BasicPreviewParameterProvider<OudsListItemPreviewParameter>(*listItemPreviewParameterValues.toTypedArray())

private val listItemPreviewParameterValues: List<OudsListItemPreviewParameter>
    get() {
        val label = "Label"
        val overline = "Overline"
        val extraLabel = "Extra label"
        val description = "Description"
        val helperText = "Helper text"
        val iconLeadingContent = OudsListItemLeadingContent.Icon.Info()
        val iconTrailingContent = OudsListItemTrailingContent.Icon(Icons.Outlined.FavoriteBorder, "")
        return listOf(
            OudsListItemPreviewParameter(
                label = label,
                overline = overline,
                extraLabel = extraLabel,
                description = description,
                helperText = helperText,
                leadingContent = iconLeadingContent,
                trailingContent = iconTrailingContent
            ),
            OudsListItemPreviewParameter(
                label = label,
                contentAlignment = OudsListItemContentAlignment.Top,
                leadingContent = OudsListItemLeadingContent.Icon(Icons.Outlined.FavoriteBorder, ""),
                trailingContent = OudsListItemTrailingContent.Text(label = label, extraLabel = "Extra label")

            )
        )
    }
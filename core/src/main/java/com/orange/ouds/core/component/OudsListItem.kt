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
import com.orange.ouds.core.R
import com.orange.ouds.core.component.common.outerBorder
import com.orange.ouds.core.component.content.OudsComponentContent
import com.orange.ouds.core.component.content.OudsComponentIcon
import com.orange.ouds.core.component.content.OudsComponentImage
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
import com.orange.ouds.foundation.ExperimentalOudsApi
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.theme.OudsThemeContract

/**
 * TODO List Item
 */
@ExperimentalOudsApi
@Composable
fun OudsListItem(
    label: String,
    modifier: Modifier = Modifier,
    contentAlignment: OudsListItemContentAlignment = OudsListItemDefaults.ContentAlignment,
    overline: String? = null,
    extraLabel: String? = null,
    description: String? = null,
    leading: OudsListItemLeading? = null,
    trailing: OudsListItemTrailing? = null,
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
        leading = leading,
        trailing = trailing,
        divider = divider,
        background = background,
        helperText = helperText,
        boldLabel = boldLabel,
        enabled = enabled
    )
}

// TODO Navigation List Item
@ExperimentalOudsApi
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
        nullableOnClick = onClick,
        modifier = modifier,
        nullableChevron = chevron,
        contentAlignment = contentAlignment,
        overline = overline,
        extraLabel = extraLabel,
        description = description,
        leading = leading,
        trailing = trailing,
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
    leading: OudsListItemLeadingTrailing?,
    trailing: OudsListItemLeadingTrailing?,
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

                trailing?.let {
                    when (trailing) {
                        is OudsListItemLeadingTrailing.Icon -> trailing.PolymorphicContent(
                            extraParameters = OudsListItemLeadingTrailing.Icon.ExtraParameters(
                                state = state
                            )
                        )
                        is OudsListItemLeadingTrailing.Text -> {
                            trailing.PolymorphicContent(extraParameters = OudsListItemLeadingTrailing.Text.ExtraParameters(contentAlignment = contentAlignment))
                        }
                        is OudsListItemLeadingTrailing.Image -> trailing.PolymorphicContent()
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

sealed interface OudsListItemLeadingTrailing : OudsPolymorphicComponentContent {
    sealed interface Asset : OudsListItemLeadingTrailing {
        val size: OudsListItemAssetSize
    }

    interface Icon : Asset {
        @ConsistentCopyVisibility
        data class ExtraParameters internal constructor(internal val state: OudsListItemState) : OudsComponentContent.ExtraParameters()
    }

    interface Image : Asset {
        val format: OudsListItemImageFormat
    }

    interface Text : OudsListItemLeadingTrailing {
        @ConsistentCopyVisibility
        data class ExtraParameters internal constructor(internal val contentAlignment: OudsListItemContentAlignment) : OudsComponentContent.ExtraParameters()
    }
}

enum class OudsListItemAssetSize {
    Small, Medium, Large, ExtraLarge;

    val value: Dp
        @Composable
        get() = with(OudsTheme.componentsTokens.listItem) {
            when (this@OudsListItemAssetSize) {
                Small -> sizeAssetSmall.value
                Medium -> sizeAssetMedium.value
                Large -> sizeAssetLarge.dp
                ExtraLarge -> sizeAssetXlarge.dp
            }
        }
}

enum class OudsListItemIconSize {
    Medium, Large;

    val assetSize: OudsListItemAssetSize
        get() = when (this) {
            Medium -> OudsListItemAssetSize.Medium
            Large -> OudsListItemAssetSize.Large
        }
}

enum class OudsListItemIconStatus(
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
            when (this@OudsListItemIconStatus) {
                Positive -> status.positive
                Warning -> Color.Unspecified
                Negative -> status.negative
                Info -> status.info
            }
        }
}

enum class OudsListItemImageFormat {
    Square,
    Panoramic;

    internal val ratio: Float
        get() = when (this) {
            Square -> 1f
            Panoramic -> 16f / 9f
        }
}

enum class OudsListItemImageSize {
    Medium, Large, ExtraLarge;

    val assetSize: OudsListItemAssetSize
        get() = when (this) {
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
    override val size: OudsListItemAssetSize,
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
    override val size: OudsListItemAssetSize,
    override val format: OudsListItemImageFormat,
    contentScale: ContentScale
) : OudsComponentImage<Nothing>(Nothing::class.java, graphicsObject, contentDescription, contentScale = contentScale), OudsListItemLeadingTrailing.Image {

    @Composable
    override fun Content(modifier: Modifier) {
        super.Content(
            modifier = modifier
                .height(size.value)
                .width(size.value * format.ratio)
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
                modifier = modifier.run {
                    if (extraParameters.contentAlignment == OudsListItemContentAlignment.Top) {
                        padding(top = OudsTheme.componentsTokens.listItem.spacePaddingBlockTopAlignmentTopTextContainerSmall.value)
                    } else {
                        this
                    }
                },
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
                    style = OudsTheme.typography.label.large.strong,
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
    ) : OudsListItemIcon(graphicsObjectProvider, contentDescriptionProvider, tinted, size.assetSize, status), OudsListItemLeading {

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
            size: OudsListItemIconSize = OudsListItemIconSize.Medium,
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
            size: OudsListItemIconSize = OudsListItemIconSize.Medium,
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
            size: OudsListItemIconSize = OudsListItemIconSize.Medium,
            tinted: Boolean = true
        ) : this({ bitmap as Any }, { contentDescription }, tinted, size, null)

        private constructor(size: OudsListItemIconSize, status: OudsListItemIconStatus) : this(
            { status.painterProvider() },
            { status.contentDescriptionProvider() },
            true,
            size,
            status
        )

        // TODO KDoc
        class Info(size: OudsListItemIconSize = OudsListItemIconSize.Medium) :
            Icon(size, OudsListItemIconStatus.Info)

        // TODO KDoc
        class Negative(size: OudsListItemIconSize = OudsListItemIconSize.Medium) :
            Icon(size, OudsListItemIconStatus.Negative)

        // TODO KDoc
        class Positive(size: OudsListItemIconSize = OudsListItemIconSize.Medium) :
            Icon(size, OudsListItemIconStatus.Positive)

        // TODO KDoc
        class Warning(size: OudsListItemIconSize = OudsListItemIconSize.Medium) :
            Icon(size, OudsListItemIconStatus.Warning)
    }

    /**
     * An image as a list item leading content.
     */
    class Image internal constructor(
        graphicsObject: Any,
        contentDescription: String,
        size: OudsListItemAssetSize,
        imageFormat: OudsListItemImageFormat,
        contentScale: ContentScale
    ) : OudsListItemImage(graphicsObject, contentDescription, size, imageFormat, contentScale), OudsListItemLeading {

        /**
         * Creates an instance of [OudsListItemLeading.Image].
         *
         * @param painter Painter of the image.
         * @param contentDescription The content description associated with this image.
         * @param size Size of the icon among [OudsListItemImageSize] values.
         * @param format Format of the image among [OudsListItemImageFormat] values.
         * @param contentScale Scale parameter used to determine the aspect ratio scaling to be used if the bounds are a different size from the intrinsic size
         * of the [painter].
         */
        constructor(
            painter: Painter,
            contentDescription: String,
            size: OudsListItemImageSize,
            format: OudsListItemImageFormat = OudsListItemImageFormat.Square,
            contentScale: ContentScale = ContentScale.Fit
        ) : this(painter, contentDescription, size.assetSize, format, contentScale)

        /**
         * Creates an instance of [OudsListItemLeading.Image].
         *
         * @param imageVector Image vector of the image.
         * @param contentDescription The content description associated with this image.
         * @param size Size of the icon among [OudsListItemImageSize] values.
         * @param format Format of the image among [OudsListItemImageFormat] values.
         * @param contentScale Scale parameter used to determine the aspect ratio scaling to be used if the bounds are a different size from the intrinsic size
         * of the [imageVector].
         */
        constructor(
            imageVector: ImageVector,
            contentDescription: String,
            size: OudsListItemImageSize,
            format: OudsListItemImageFormat = OudsListItemImageFormat.Square,
            contentScale: ContentScale = ContentScale.Fit
        ) : this(imageVector, contentDescription, size.assetSize, format, contentScale)

        /**
         * Creates an instance of [OudsListItemLeading.Image].
         *
         * @param bitmap Image bitmap of the image.
         * @param contentDescription The content description associated with this image.
         * @param size Size of the icon among [OudsListItemImageSize] values.
         * @param format Format of the image among [OudsListItemImageFormat] values.
         * @param contentScale Scale parameter used to determine the aspect ratio scaling to be used if the bounds are a different size from the intrinsic size
         * of the [bitmap].
         */
        constructor(
            bitmap: ImageBitmap,
            contentDescription: String,
            size: OudsListItemImageSize,
            format: OudsListItemImageFormat = OudsListItemImageFormat.Square,
            contentScale: ContentScale = ContentScale.Fit
        ) : this(bitmap, contentDescription, size.assetSize, format, contentScale)
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
    ) : OudsListItemIcon(graphicsObjectProvider, contentDescriptionProvider, tinted, size.assetSize, status), OudsListItemTrailing {

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
            size: OudsListItemIconSize = OudsListItemIconSize.Medium,
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
            size: OudsListItemIconSize = OudsListItemIconSize.Medium,
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
            size: OudsListItemIconSize = OudsListItemIconSize.Medium,
            tinted: Boolean = true
        ) : this({ bitmap as Any }, { contentDescription }, tinted, size, null)

        private constructor(size: OudsListItemIconSize, status: OudsListItemIconStatus) : this(
            { status.painterProvider() },
            { status.contentDescriptionProvider() },
            true,
            size,
            status
        )

        // TODO KDoc
        class Info(size: OudsListItemIconSize = OudsListItemIconSize.Medium) :
            Icon(size, OudsListItemIconStatus.Info)

        // TODO KDoc
        class Negative(size: OudsListItemIconSize = OudsListItemIconSize.Medium) :
            Icon(size, OudsListItemIconStatus.Negative)

        // TODO KDoc
        class Positive(size: OudsListItemIconSize = OudsListItemIconSize.Medium) :
            Icon(size, OudsListItemIconStatus.Positive)

        // TODO KDoc
        class Warning(size: OudsListItemIconSize = OudsListItemIconSize.Medium) :
            Icon(size, OudsListItemIconStatus.Warning)
    }

    /**
     * An image as a list item trailing content.
     */
    class Image internal constructor(
        graphicsObject: Any,
        contentDescription: String,
        size: OudsListItemAssetSize,
        imageFormat: OudsListItemImageFormat,
        contentScale: ContentScale
    ) : OudsListItemImage(graphicsObject, contentDescription, size, imageFormat, contentScale), OudsListItemTrailing {

        /**
         * Creates an instance of [OudsListItemTrailing.Image].
         *
         * @param painter Painter of the image.
         * @param contentDescription The content description associated with this image.
         * @param size Size of the icon among [OudsListItemImageSize] values.
         * @param format Format of the image among [OudsListItemImageFormat] values.
         * @param contentScale Scale parameter used to determine the aspect ratio scaling to be used if the bounds are a different size from the intrinsic size
         * of the [painter].
         */
        constructor(
            painter: Painter,
            contentDescription: String,
            size: OudsListItemImageSize,
            format: OudsListItemImageFormat = OudsListItemImageFormat.Square,
            contentScale: ContentScale = ContentScale.Fit
        ) : this(painter, contentDescription, size.assetSize, format, contentScale)

        /**
         * Creates an instance of [OudsListItemTrailing.Image].
         *
         * @param imageVector Image vector of the image.
         * @param contentDescription The content description associated with this image.
         * @param size Size of the icon among [OudsListItemImageSize] values.
         * @param format Format of the image among [OudsListItemImageFormat] values.
         * @param contentScale Scale parameter used to determine the aspect ratio scaling to be used if the bounds are a different size from the intrinsic size
         * of the [imageVector].
         */
        constructor(
            imageVector: ImageVector,
            contentDescription: String,
            size: OudsListItemImageSize,
            format: OudsListItemImageFormat = OudsListItemImageFormat.Square,
            contentScale: ContentScale = ContentScale.Fit
        ) : this(imageVector, contentDescription, size.assetSize, format, contentScale)

        /**
         * Creates an instance of [OudsListItemTrailing.Image].
         *
         * @param bitmap Image bitmap of the image.
         * @param contentDescription The content description associated with this image.
         * @param size Size of the icon among [OudsListItemImageSize] values.
         * @param format Format of the image among [OudsListItemImageFormat] values.
         * @param contentScale Scale parameter used to determine the aspect ratio scaling to be used if the bounds are a different size from the intrinsic size
         * of the [bitmap].
         */
        constructor(
            bitmap: ImageBitmap,
            contentDescription: String,
            size: OudsListItemImageSize,
            format: OudsListItemImageFormat = OudsListItemImageFormat.Square,
            contentScale: ContentScale = ContentScale.Fit
        ) : this(bitmap, contentDescription, size.assetSize, format, contentScale)
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
        constructor(label: String, extraLabel: String? = null) : this(label, OudsListItemTextStyle.Label, extraLabel)
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
            leading = leadingContent,
            trailing = trailingContent,
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
                leading = leadingContent,
                trailing = trailingContent,
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
    val leadingContent: OudsListItemLeading? = null,
    val trailingContent: OudsListItemTrailing? = null,
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
        val iconLeadingContent = OudsListItemLeading.Icon.Info()
        val iconTrailingContent = OudsListItemTrailing.Icon(Icons.Outlined.FavoriteBorder, "")
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
                leadingContent = OudsListItemLeading.Icon(Icons.Outlined.FavoriteBorder, ""),
                trailingContent = OudsListItemTrailing.Text(label = label, extraLabel = "Extra label")
            )
        )
    }
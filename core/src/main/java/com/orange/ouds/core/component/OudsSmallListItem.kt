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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.component.OudsListItemContent.Text.Style
import com.orange.ouds.core.component.content.OudsComponentContent
import com.orange.ouds.core.utilities.CheckerboardPainter
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.OudsPreviewDevice
import com.orange.ouds.core.utilities.OudsPreviewLightDark
import com.orange.ouds.core.utilities.PreviewEnumEntries
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.theme.OudsThemeContract
import kotlin.jvm.java

/**
 * TODO Small List Item
 */
@Composable
fun OudsSmallListItem(
    label: String,
    modifier: Modifier = Modifier,
    contentAlignment: OudsListItemContentAlignment = OudsListItemDefaults.ContentAlignment,
    description: String? = null,
    leadingContent: OudsSmallListItemLeadingContent? = null,
    trailingContent: OudsSmallListItemTrailingContent? = null,
    divider: Boolean = true,
    background: Boolean = true,
    helperText: String? = null,
    boldLabel: Boolean = false,
    enabled: Boolean = true,
) {
    OudsListItem(
        size = OudsListItemSize.Small,
        label = label,
        nullableOnClick = null,
        modifier = modifier,
        nullableChevron = null, contentAlignment = contentAlignment,
        overline = null,
        extraLabel = null,
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

// TODO Navigation Small List Item
@Composable
fun OudsSmallListItem(
    label: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    chevron: OudsListItemChevron = OudsListItemDefaults.Chevron,
    contentAlignment: OudsListItemContentAlignment = OudsListItemDefaults.ContentAlignment,
    description: String? = null,
    leadingContent: OudsSmallListItemLeadingContent? = null,
    trailingContent: OudsSmallListItemTrailingContent? = null,
    divider: Boolean = true,
    background: Boolean = false,
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
        nullableChevron = chevron,
        contentAlignment = contentAlignment,
        overline = null,
        extraLabel = null,
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

private val SmallListItemAssetSize = OudsListItemContent.Asset.Size.Small

/**
 * Implementation for small list item icons.
 * Shared by both leading and trailing icons.
 */
internal open class SmallListItemIcon internal constructor(
    graphicsObjectProvider: @Composable (ListItemIcon) -> Any,
    contentDescriptionProvider: @Composable (ListItemIcon) -> String,
    override val tinted: Boolean
) : ListItemIcon(
    graphicsObjectProvider, contentDescriptionProvider, tinted, SmallListItemAssetSize
) {
    constructor(painter: Painter, contentDescription: String, tinted: Boolean)
            : this({ painter as Any }, { contentDescription }, tinted)

    constructor(imageVector: ImageVector, contentDescription: String, tinted: Boolean)
            : this({ imageVector as Any }, { contentDescription }, tinted)

    constructor(bitmap: ImageBitmap, contentDescription: String, tinted: Boolean)
            : this({ bitmap as Any }, { contentDescription }, tinted)
}


/**
 * A leading content of an [OudsSmallListItem].
 */
sealed interface OudsSmallListItemLeadingContent : OudsListItemContent {

    /**
     * An icon as a small list item leading content.
     */
    open class Icon internal constructor(
        internal val smallListItemIcon: SmallListItemIcon
    ) : OudsComponentContent<OudsListItemContent.Icon.ExtraParameters>(
        OudsListItemContent.Icon.ExtraParameters::class.java
    ), OudsSmallListItemLeadingContent, OudsListItemContent.Icon {

        override val size
            get() = smallListItemIcon.size

        internal val tinted
            get() = smallListItemIcon.tinted

        /**
         * Creates an instance of [OudsSmallListItemLeadingContent.Icon].
         *
         * @param painter Painter of the icon.
         * @param contentDescription The content description associated with this icon.
         * @param tinted Controls whether the icon should be tinted with the theme color. Defaults to `true`.
         *   When set to `false`, the icon is displayed with its original colors (e.g., for multicolor icons).
         *   Note that untinted icons must ensure sufficient contrast with the background for accessibility reasons.
         */
        constructor(
            painter: Painter, contentDescription: String, tinted: Boolean = true
        ) : this(SmallListItemIcon(painter, contentDescription, tinted))

        /**
         * Creates an instance of [OudsSmallListItemLeadingContent.Icon].
         *
         * @param imageVector Image vector of the icon.
         * @param contentDescription The content description associated with this icon.
         * @param tinted Controls whether the icon should be tinted with the theme color. Defaults to `true`.
         *   When set to `false`, the icon is displayed with its original colors (e.g., for multicolor icons).
         *   Note that untinted icons must ensure sufficient contrast with the background for accessibility reasons.
         */
        constructor(
            imageVector: ImageVector, contentDescription: String, tinted: Boolean = true
        ) : this(SmallListItemIcon(imageVector, contentDescription, tinted))

        /**
         * Creates an instance of [OudsSmallListItemLeadingContent.Icon].
         *
         * @param bitmap Image bitmap of the icon.
         * @param contentDescription The content description associated with this icon.
         * @param tinted Controls whether the icon should be tinted with the theme color. Defaults to `true`.
         *   When set to `false`, the icon is displayed with its original colors (e.g., for multicolor icons).
         *   Note that untinted icons must ensure sufficient contrast with the background for accessibility reasons.
         */
        constructor(
            bitmap: ImageBitmap, contentDescription: String, tinted: Boolean = true
        ) : this(SmallListItemIcon(bitmap, contentDescription, tinted))

        // TODO KDoc
        object Info : Icon(
            SmallListItemIcon(
                { OudsListItemContent.StatusIcon.Status.Info.painterProvider() },
                { OudsListItemContent.StatusIcon.Status.Info.contentDescriptionProvider() },
                true
            )
        ), OudsListItemContent.StatusIcon {
            override val status: OudsListItemContent.StatusIcon.Status = OudsListItemContent.StatusIcon.Status.Info
        }

        // TODO KDoc
        object Negative : Icon(
            SmallListItemIcon(
                { OudsListItemContent.StatusIcon.Status.Negative.painterProvider() },
                { OudsListItemContent.StatusIcon.Status.Negative.contentDescriptionProvider() },
                true
            )
        ), OudsListItemContent.StatusIcon {
            override val status: OudsListItemContent.StatusIcon.Status = OudsListItemContent.StatusIcon.Status.Negative
        }

        // TODO KDoc
        object Positive : Icon(
            SmallListItemIcon(
                { OudsListItemContent.StatusIcon.Status.Positive.painterProvider() },
                { OudsListItemContent.StatusIcon.Status.Positive.contentDescriptionProvider() },
                true
            )
        ), OudsListItemContent.StatusIcon {
            override val status: OudsListItemContent.StatusIcon.Status = OudsListItemContent.StatusIcon.Status.Positive
        }

        // TODO KDoc
        object Warning : Icon(
            SmallListItemIcon(
                { OudsListItemContent.StatusIcon.Status.Warning.painterProvider() },
                { OudsListItemContent.StatusIcon.Status.Warning.contentDescriptionProvider() },
                true
            )
        ), OudsListItemContent.StatusIcon {
            override val status: OudsListItemContent.StatusIcon.Status = OudsListItemContent.StatusIcon.Status.Warning
        }

        @Composable
        override fun Content(modifier: Modifier) {
            smallListItemIcon.Content(modifier)
        }
    }

    /**
     * An image as a small list item leading content.
     */
    class Image internal constructor(
        private val listItemImage: ListItemImage
    ) : OudsComponentContent<Nothing>(Nothing::class.java), OudsSmallListItemLeadingContent, OudsListItemContent.Image {

        override val size
            get() = listItemImage.size
        override val format
            get() = listItemImage.format

        /**
         * Creates an instance of [OudsSmallListItemLeadingContent.Image].
         *
         * @param painter Painter of the image.
         * @param contentDescription The content description associated with this image.
         * @param format Format of the image among [OudsListItemContent.Image.Format] values.
         * @param contentScale Scale parameter used to determine the aspect ratio scaling to be used if the bounds are a different size from the intrinsic size
         * of the [painter].
         */
        constructor(
            painter: Painter,
            contentDescription: String,
            format: OudsListItemContent.Image.Format = OudsListItemContent.Image.Format.Square,
            contentScale: ContentScale = ContentScale.Fit
        ) : this(ListItemImage(painter, contentDescription, SmallListItemAssetSize, format, contentScale))

        /**
         * Creates an instance of [OudsSmallListItemLeadingContent.Image].
         *
         * @param imageVector Image vector of the image.
         * @param contentDescription The content description associated with this image.
         * @param format Format of the image among [OudsListItemContent.Image.Format] values.
         * @param contentScale Scale parameter used to determine the aspect ratio scaling to be used if the bounds are a different size from the intrinsic size
         * of the [imageVector].
         */
        constructor(
            imageVector: ImageVector,
            contentDescription: String,
            format: OudsListItemContent.Image.Format = OudsListItemContent.Image.Format.Square,
            contentScale: ContentScale = ContentScale.Fit
        ) : this(ListItemImage(imageVector, contentDescription, SmallListItemAssetSize, format, contentScale))

        /**
         * Creates an instance of [OudsSmallListItemLeadingContent.Image].
         *
         * @param bitmap Image bitmap of the image.
         * @param contentDescription The content description associated with this image.
         * @param format Format of the image among [OudsListItemContent.Image.Format] values.
         * @param contentScale Scale parameter used to determine the aspect ratio scaling to be used if the bounds are a different size from the intrinsic size
         * of the [bitmap].
         */
        constructor(
            bitmap: ImageBitmap,
            contentDescription: String,
            format: OudsListItemContent.Image.Format = OudsListItemContent.Image.Format.Square,
            contentScale: ContentScale = ContentScale.Fit
        ) : this(ListItemImage(bitmap, contentDescription, SmallListItemAssetSize, format, contentScale))

        @Composable
        override fun Content(modifier: Modifier) {
            listItemImage.Content(modifier)
        }
    }
}

/**
 * A trailing content of an [OudsSmallListItem].
 */
sealed interface OudsSmallListItemTrailingContent : OudsListItemTrailingContent {

    /**
     * Icon as a small list item trailing content.
     */
    open class Icon private constructor(
        internal val smallListItemIcon: SmallListItemIcon
    ) : OudsComponentContent<OudsListItemContent.Icon.ExtraParameters>(
        OudsListItemContent.Icon.ExtraParameters::class.java
    ), OudsSmallListItemTrailingContent, OudsListItemContent.Icon {

        override val size = smallListItemIcon.size

        internal val tinted get() = smallListItemIcon.tinted

        /**
         * Creates an instance of [OudsSmallListItemTrailingContent.Icon].
         *
         * @param painter Painter of the icon.
         * @param contentDescription The content description associated with this icon.
         * @param tinted Controls whether the icon should be tinted with the theme color. Defaults to `true`.
         *   When set to `false`, the icon is displayed with its original colors (e.g., for multicolor icons).
         *   Note that untinted icons must ensure sufficient contrast with the background for accessibility reasons.
         */
        constructor(
            painter: Painter, contentDescription: String, tinted: Boolean = true
        ) : this(SmallListItemIcon(painter, contentDescription, tinted))

        /**
         * Creates an instance of [OudsSmallListItemTrailingContent.Icon].
         *
         * @param imageVector Image vector of the icon.
         * @param contentDescription The content description associated with this icon.
         * @param tinted Controls whether the icon should be tinted with the theme color. Defaults to `true`.
         *   When set to `false`, the icon is displayed with its original colors (e.g., for multicolor icons).
         *   Note that untinted icons must ensure sufficient contrast with the background for accessibility reasons.
         */
        constructor(
            imageVector: ImageVector, contentDescription: String, tinted: Boolean = true
        ) : this(SmallListItemIcon(imageVector, contentDescription, tinted))

        /**
         * Creates an instance of [OudsSmallListItemTrailingContent.Icon].
         *
         * @param bitmap Image bitmap of the icon.
         * @param contentDescription The content description associated with this icon.
         * @param tinted Controls whether the icon should be tinted with the theme color. Defaults to `true`.
         *   When set to `false`, the icon is displayed with its original colors (e.g., for multicolor icons).
         *   Note that untinted icons must ensure sufficient contrast with the background for accessibility reasons.
         */
        constructor(
            bitmap: ImageBitmap, contentDescription: String, tinted: Boolean = true
        ) : this(SmallListItemIcon(bitmap, contentDescription, tinted))

        // TODO KDoc
        object Info : Icon(
            SmallListItemIcon(
                { OudsListItemContent.StatusIcon.Status.Info.painterProvider() },
                { OudsListItemContent.StatusIcon.Status.Info.contentDescriptionProvider() },
                true
            )
        ), OudsListItemContent.StatusIcon {
            override val status: OudsListItemContent.StatusIcon.Status = OudsListItemContent.StatusIcon.Status.Info
        }

        // TODO KDoc
        object Negative : Icon(
            SmallListItemIcon(
                { OudsListItemContent.StatusIcon.Status.Negative.painterProvider() },
                { OudsListItemContent.StatusIcon.Status.Negative.contentDescriptionProvider() },
                true
            )
        ), OudsListItemContent.StatusIcon {
            override val status: OudsListItemContent.StatusIcon.Status = OudsListItemContent.StatusIcon.Status.Negative
        }

        // TODO KDoc
        object Positive : Icon(
            SmallListItemIcon(
                { OudsListItemContent.StatusIcon.Status.Positive.painterProvider() },
                { OudsListItemContent.StatusIcon.Status.Positive.contentDescriptionProvider() },
                true
            )
        ), OudsListItemContent.StatusIcon {
            override val status: OudsListItemContent.StatusIcon.Status = OudsListItemContent.StatusIcon.Status.Positive
        }

        // TODO KDoc
        object Warning : Icon(
            SmallListItemIcon(
                { OudsListItemContent.StatusIcon.Status.Warning.painterProvider() },
                { OudsListItemContent.StatusIcon.Status.Warning.contentDescriptionProvider() },
                true
            )
        ), OudsListItemContent.StatusIcon {
            override val status: OudsListItemContent.StatusIcon.Status = OudsListItemContent.StatusIcon.Status.Warning
        }

        @Composable
        override fun Content(modifier: Modifier) {
            smallListItemIcon.Content(modifier)
        }
    }

    /**
     * Image as a small list item trailing content.
     */
    class Image internal constructor(
        private val listItemImage: ListItemImage
    ) : OudsComponentContent<Nothing>(Nothing::class.java), OudsSmallListItemTrailingContent, OudsListItemContent.Image {

        override val size get() = listItemImage.size
        override val format get() = listItemImage.format

        /**
         * Creates an instance of [OudsSmallListItemTrailingContent.Image].
         *
         * @param painter Painter of the image.
         * @param contentDescription The content description associated with this image.
         * @param format Format of the image among [OudsListItemContent.Image.Format] values.
         * @param contentScale Scale parameter used to determine the aspect ratio scaling to be used if the bounds are a different size from the intrinsic size
         * of the [painter].
         */
        constructor(
            painter: Painter,
            contentDescription: String,
            format: OudsListItemContent.Image.Format = OudsListItemContent.Image.Format.Square,
            contentScale: ContentScale = ContentScale.Fit
        ) : this(ListItemImage(painter, contentDescription, SmallListItemAssetSize, format, contentScale))

        /**
         * Creates an instance of [OudsSmallListItemTrailingContent.Image].
         *
         * @param imageVector Image vector of the image.
         * @param contentDescription The content description associated with this image.
         * @param format Format of the image among [OudsListItemContent.Image.Format] values.
         * @param contentScale Scale parameter used to determine the aspect ratio scaling to be used if the bounds are a different size from the intrinsic size
         * of the [imageVector].
         */
        constructor(
            imageVector: ImageVector,
            contentDescription: String,
            format: OudsListItemContent.Image.Format = OudsListItemContent.Image.Format.Square,
            contentScale: ContentScale = ContentScale.Fit
        ) : this(ListItemImage(imageVector, contentDescription, SmallListItemAssetSize, format, contentScale))

        /**
         * Creates an instance of [OudsSmallListItemTrailingContent.Image].
         *
         * @param bitmap Image bitmap of the image.
         * @param contentDescription The content description associated with this image.
         * @param format Format of the image among [OudsListItemContent.Image.Format] values.
         * @param contentScale Scale parameter used to determine the aspect ratio scaling to be used if the bounds are a different size from the intrinsic size
         * of the [bitmap].
         */
        constructor(
            bitmap: ImageBitmap,
            contentDescription: String,
            format: OudsListItemContent.Image.Format = OudsListItemContent.Image.Format.Square,
            contentScale: ContentScale = ContentScale.Fit
        ) : this(ListItemImage(bitmap, contentDescription, SmallListItemAssetSize, format, contentScale))

        @Composable
        override fun Content(modifier: Modifier) {
            listItemImage.Content(modifier)
        }
    }

    /**
     * Label as a small list item trailing content.
     */
    class Text private constructor(
        private val listItemTrailingText: ListItemTrailingText
    ) : OudsComponentContent<Nothing>(Nothing::class.java), OudsSmallListItemTrailingContent, OudsListItemContent.Text {

        /**
         * Creates an instance of [OudsSmallListItemTrailingContent.Text].
         *
         * @param label Label displayed in trailing.
         * @param style Style applied to the label among [OudsListItemContent.Text.Style] values.
         */
        constructor(label: String, style: Style = Style.Label) : this(ListItemTrailingText(label, style))

        @Composable
        override fun Content(modifier: Modifier) {
            listItemTrailingText.Content(modifier = modifier)
        }
    }
}

@OudsPreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsSmallStaticListItem(@PreviewParameter(OudsSmallListItemPreviewParameterProvider::class) parameter: OudsSmallListItemPreviewParameter) {
    PreviewOudsSmallStaticListItem(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsSmallStaticListItem(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsSmallListItemPreviewParameter
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        OudsSmallListItem(
            label = label,
            description = description,
            helperText = helperText,
            leadingContent = leadingContent,
            trailingContent = trailingContent,
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
private fun PreviewOudsNavigationSmallListItem(@PreviewParameter(OudsSmallListItemPreviewParameterProvider::class) parameter: OudsSmallListItemPreviewParameter) {
    PreviewOudsNavigationSmallListItem(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsNavigationSmallListItem(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsSmallListItemPreviewParameter
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        PreviewEnumEntries<OudsListItemState>(maxEnumEntriesInEachRow = 1) {
            OudsSmallListItem(
                onClick = {},
                chevron = chevron,
                label = label,
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

internal data class OudsSmallListItemPreviewParameter(
    val label: String,
    val chevron: OudsListItemChevron = OudsListItemDefaults.Chevron,
    val description: String? = null,
    val helperText: String? = null,
    val contentAlignment: OudsListItemContentAlignment = OudsListItemContentAlignment.Center,
    val leadingContent: OudsSmallListItemLeadingContent? = null,
    val trailingContent: OudsSmallListItemTrailingContent? = null,
    val enabled: Boolean = true
)

internal class OudsSmallListItemPreviewParameterProvider :
    BasicPreviewParameterProvider<OudsSmallListItemPreviewParameter>(*smallListItemPreviewParameterValues.toTypedArray())

private val smallListItemPreviewParameterValues: List<OudsSmallListItemPreviewParameter>
    get() {
        val label = "Label"
        val description = "Description"
        val helperText = "Helper text"
        val iconLeadingContent = OudsSmallListItemLeadingContent.Icon.Info
        val iconTrailingContent = OudsSmallListItemTrailingContent.Icon(Icons.Outlined.FavoriteBorder, "")
        val imagePainter = CheckerboardPainter(
            squareSize = 6.dp,
            primaryColor = Color(0xff247a85),
            secondaryColor = Color(0xfffbcd00)
        )
        return listOf(
            OudsSmallListItemPreviewParameter(
                label = label,
                description = description,
                helperText = helperText,
                leadingContent = iconLeadingContent,
                trailingContent = iconTrailingContent
            ),
            OudsSmallListItemPreviewParameter(
                label = label,
                contentAlignment = OudsListItemContentAlignment.Top,
                leadingContent = OudsSmallListItemLeadingContent.Icon(Icons.Outlined.FavoriteBorder, ""),
                trailingContent = OudsSmallListItemTrailingContent.Text(label = label, style = Style.LabelStrong)
            ),
            OudsSmallListItemPreviewParameter(
                label = label,
                leadingContent = OudsSmallListItemLeadingContent.Image(imagePainter, "", OudsListItemContent.Image.Format.Panoramic),
                trailingContent = OudsSmallListItemTrailingContent.Image(imagePainter, "", OudsListItemContent.Image.Format.Square)
            )
        )
    }
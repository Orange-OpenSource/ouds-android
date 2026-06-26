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
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ouds.core.utilities.CheckerboardPainter
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.OudsPreviewDevice
import com.orange.ouds.core.utilities.OudsPreviewLightDark
import com.orange.ouds.core.utilities.PreviewEnumEntries
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.foundation.ExperimentalOudsApi
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.theme.OudsThemeContract

/**
 * TODO Small List Item
 */
@ExperimentalOudsApi
@Composable
fun OudsSmallListItem(
    label: String,
    modifier: Modifier = Modifier,
    contentAlignment: OudsListItemContentAlignment = OudsListItemDefaults.ContentAlignment,
    description: String? = null,
    leading: OudsSmallListItemLeading? = null,
    trailing: OudsSmallListItemTrailing? = null,
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
        leading = leading,
        trailing = trailing,
        divider = divider,
        background = background,
        helperText = helperText,
        boldLabel = boldLabel,
        enabled = enabled
    )
}

// TODO Navigation Small List Item
@ExperimentalOudsApi
@Composable
fun OudsSmallListItem(
    label: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    chevron: OudsListItemChevron = OudsListItemDefaults.Chevron,
    contentAlignment: OudsListItemContentAlignment = OudsListItemDefaults.ContentAlignment,
    description: String? = null,
    leading: OudsSmallListItemLeading? = null,
    trailing: OudsSmallListItemTrailing? = null,
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

private val SmallListItemAssetSize = OudsListItemAssetSize.Small

/**
 * A leading content of an [OudsSmallListItem].
 */
sealed interface OudsSmallListItemLeading : OudsListItemLeadingTrailing {

    /**
     * An icon as a small list item leading content.
     */
    open class Icon internal constructor(
        graphicsObjectProvider: @Composable (OudsListItemIcon) -> Any,
        contentDescriptionProvider: @Composable (OudsListItemIcon) -> String,
        override val tinted: Boolean,
        status: OudsListItemIconStatus?
    ) : OudsListItemIcon(graphicsObjectProvider, contentDescriptionProvider, tinted, SmallListItemAssetSize, status), OudsSmallListItemLeading {

        /**
         * Creates an instance of [OudsSmallListItemLeading.Icon].
         *
         * @param painter Painter of the icon.
         * @param contentDescription The content description associated with this icon.
         * @param tinted Controls whether the icon should be tinted with the theme color. Defaults to `true`.
         *   When set to `false`, the icon is displayed with its original colors (e.g., for multicolor icons).
         *   Note that untinted icons must ensure sufficient contrast with the background for accessibility reasons.
         */
        constructor(
            painter: Painter, contentDescription: String, tinted: Boolean = true
        ) : this({ painter }, { contentDescription }, tinted, null)

        /**
         * Creates an instance of [OudsSmallListItemLeading.Icon].
         *
         * @param imageVector Image vector of the icon.
         * @param contentDescription The content description associated with this icon.
         * @param tinted Controls whether the icon should be tinted with the theme color. Defaults to `true`.
         *   When set to `false`, the icon is displayed with its original colors (e.g., for multicolor icons).
         *   Note that untinted icons must ensure sufficient contrast with the background for accessibility reasons.
         */
        constructor(
            imageVector: ImageVector, contentDescription: String, tinted: Boolean = true
        ) : this({ imageVector }, { contentDescription }, tinted, null)

        /**
         * Creates an instance of [OudsSmallListItemLeading.Icon].
         *
         * @param bitmap Image bitmap of the icon.
         * @param contentDescription The content description associated with this icon.
         * @param tinted Controls whether the icon should be tinted with the theme color. Defaults to `true`.
         *   When set to `false`, the icon is displayed with its original colors (e.g., for multicolor icons).
         *   Note that untinted icons must ensure sufficient contrast with the background for accessibility reasons.
         */
        constructor(
            bitmap: ImageBitmap, contentDescription: String, tinted: Boolean = true
        ) : this({ bitmap }, { contentDescription }, tinted, null)

        private constructor(status: OudsListItemIconStatus) : this(
            { status.painterProvider() },
            { status.contentDescriptionProvider() },
            true,
            status
        )

        // TODO KDoc
        object Info : Icon(OudsListItemIconStatus.Info)

        // TODO KDoc
        object Negative : Icon(OudsListItemIconStatus.Negative)

        // TODO KDoc
        object Positive : Icon(OudsListItemIconStatus.Positive)

        // TODO KDoc
        object Warning : Icon(OudsListItemIconStatus.Warning)
    }

    /**
     * An image as a small list item leading content.
     */
    class Image internal constructor(
        graphicsObject: Any,
        contentDescription: String,
        size: OudsListItemAssetSize,
        imageFormat: OudsListItemImageFormat,
        contentScale: ContentScale
    ) : OudsListItemImage(graphicsObject, contentDescription, size, imageFormat, contentScale), OudsSmallListItemLeading {

        /**
         * Creates an instance of [OudsSmallListItemLeading.Image].
         *
         * @param painter Painter of the image.
         * @param contentDescription The content description associated with this image.
         * @param format Format of the image among [OudsListItemImageFormat] values.
         * @param contentScale Scale parameter used to determine the aspect ratio scaling to be used if the bounds are a different size from the intrinsic size
         * of the [painter].
         */
        constructor(
            painter: Painter,
            contentDescription: String,
            format: OudsListItemImageFormat = OudsListItemImageFormat.Square,
            contentScale: ContentScale = ContentScale.Fit
        ) : this(painter, contentDescription, SmallListItemAssetSize, format, contentScale)

        /**
         * Creates an instance of [OudsSmallListItemLeading.Image].
         *
         * @param imageVector Image vector of the image.
         * @param contentDescription The content description associated with this image.
         * @param format Format of the image among [OudsListItemImageFormat] values.
         * @param contentScale Scale parameter used to determine the aspect ratio scaling to be used if the bounds are a different size from the intrinsic size
         * of the [imageVector].
         */
        constructor(
            imageVector: ImageVector,
            contentDescription: String,
            format: OudsListItemImageFormat = OudsListItemImageFormat.Square,
            contentScale: ContentScale = ContentScale.Fit
        ) : this(imageVector, contentDescription, SmallListItemAssetSize, format, contentScale)

        /**
         * Creates an instance of [OudsSmallListItemLeading.Image].
         *
         * @param bitmap Image bitmap of the image.
         * @param contentDescription The content description associated with this image.
         * @param format Format of the image among [OudsListItemImageFormat] values.
         * @param contentScale Scale parameter used to determine the aspect ratio scaling to be used if the bounds are a different size from the intrinsic size
         * of the [bitmap].
         */
        constructor(
            bitmap: ImageBitmap,
            contentDescription: String,
            format: OudsListItemImageFormat = OudsListItemImageFormat.Square,
            contentScale: ContentScale = ContentScale.Fit
        ) : this(bitmap, contentDescription, SmallListItemAssetSize, format, contentScale)
    }
}

/**
 * A trailing content of an [OudsSmallListItem].
 */
sealed interface OudsSmallListItemTrailing : OudsListItemLeadingTrailing {

    /**
     * An icon as a small list item trailing content.
     */
    open class Icon internal constructor(
        graphicsObjectProvider: @Composable () -> Any,
        contentDescriptionProvider: @Composable () -> String,
        override val tinted: Boolean,
        status: OudsListItemIconStatus?
    ) : OudsListItemIcon({ graphicsObjectProvider() }, { contentDescriptionProvider() }, tinted, SmallListItemAssetSize, status), OudsSmallListItemTrailing {

        /**
         * Creates an instance of [OudsSmallListItemTrailing.Icon].
         *
         * @param painter Painter of the icon.
         * @param contentDescription The content description associated with this icon.
         * @param tinted Controls whether the icon should be tinted with the theme color. Defaults to `true`.
         *   When set to `false`, the icon is displayed with its original colors (e.g., for multicolor icons).
         *   Note that untinted icons must ensure sufficient contrast with the background for accessibility reasons.
         */
        constructor(
            painter: Painter, contentDescription: String, tinted: Boolean = true
        ) : this({ painter }, { contentDescription }, tinted, null)

        /**
         * Creates an instance of [OudsSmallListItemTrailing.Icon].
         *
         * @param imageVector Image vector of the icon.
         * @param contentDescription The content description associated with this icon.
         * @param tinted Controls whether the icon should be tinted with the theme color. Defaults to `true`.
         *   When set to `false`, the icon is displayed with its original colors (e.g., for multicolor icons).
         *   Note that untinted icons must ensure sufficient contrast with the background for accessibility reasons.
         */
        constructor(
            imageVector: ImageVector, contentDescription: String, tinted: Boolean = true
        ) : this({ imageVector }, { contentDescription }, tinted, null)

        /**
         * Creates an instance of [OudsSmallListItemTrailing.Icon].
         *
         * @param bitmap Image bitmap of the icon.
         * @param contentDescription The content description associated with this icon.
         * @param tinted Controls whether the icon should be tinted with the theme color. Defaults to `true`.
         *   When set to `false`, the icon is displayed with its original colors (e.g., for multicolor icons).
         *   Note that untinted icons must ensure sufficient contrast with the background for accessibility reasons.
         */
        constructor(
            bitmap: ImageBitmap, contentDescription: String, tinted: Boolean = true
        ) : this({ bitmap }, { contentDescription }, tinted, null)

        private constructor(status: OudsListItemIconStatus) : this(status.painterProvider, status.contentDescriptionProvider, true, status)

        // TODO KDoc
        object Info : Icon(OudsListItemIconStatus.Info)

        // TODO KDoc
        object Negative : Icon(OudsListItemIconStatus.Negative)

        // TODO KDoc
        object Positive : Icon(OudsListItemIconStatus.Positive)

        // TODO KDoc
        object Warning : Icon(OudsListItemIconStatus.Warning)
    }

    /**
     * Image as a small list item trailing content.
     */
    class Image internal constructor(
        graphicsObject: Any,
        contentDescription: String,
        size: OudsListItemAssetSize,
        imageFormat: OudsListItemImageFormat,
        contentScale: ContentScale
    ) : OudsListItemImage(graphicsObject, contentDescription, size, imageFormat, contentScale), OudsSmallListItemTrailing {

        /**
         * Creates an instance of [OudsSmallListItemTrailing.Image].
         *
         * @param painter Painter of the image.
         * @param contentDescription The content description associated with this image.
         * @param format Format of the image among [OudsListItemImageFormat] values.
         * @param contentScale Scale parameter used to determine the aspect ratio scaling to be used if the bounds are a different size from the intrinsic size
         * of the [painter].
         */
        constructor(
            painter: Painter,
            contentDescription: String,
            format: OudsListItemImageFormat = OudsListItemImageFormat.Square,
            contentScale: ContentScale = ContentScale.Fit
        ) : this(painter, contentDescription, SmallListItemAssetSize, format, contentScale)

        /**
         * Creates an instance of [OudsSmallListItemTrailing.Image].
         *
         * @param imageVector Image vector of the image.
         * @param contentDescription The content description associated with this image.
         * @param format Format of the image among [OudsListItemImageFormat] values.
         * @param contentScale Scale parameter used to determine the aspect ratio scaling to be used if the bounds are a different size from the intrinsic size
         * of the [imageVector].
         */
        constructor(
            imageVector: ImageVector,
            contentDescription: String,
            format: OudsListItemImageFormat = OudsListItemImageFormat.Square,
            contentScale: ContentScale = ContentScale.Fit
        ) : this(imageVector, contentDescription, SmallListItemAssetSize, format, contentScale)

        /**
         * Creates an instance of [OudsSmallListItemTrailing.Image].
         *
         * @param bitmap Image bitmap of the image.
         * @param contentDescription The content description associated with this image.
         * @param format Format of the image among [OudsListItemImageFormat] values.
         * @param contentScale Scale parameter used to determine the aspect ratio scaling to be used if the bounds are a different size from the intrinsic size
         * of the [bitmap].
         */
        constructor(
            bitmap: ImageBitmap,
            contentDescription: String,
            format: OudsListItemImageFormat = OudsListItemImageFormat.Square,
            contentScale: ContentScale = ContentScale.Fit
        ) : this(bitmap, contentDescription, SmallListItemAssetSize, format, contentScale)
    }

    /**
     * Label as a small list item trailing content.
     */
    class Text(
        label: String,
        style: OudsListItemTextStyle
    ) : OudsListItemText(label, style, null), OudsSmallListItemTrailing
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
            leading = leadingContent,
            trailing = trailingContent,
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
                leading = leadingContent,
                trailing = trailingContent,
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
    val leadingContent: OudsSmallListItemLeading? = null,
    val trailingContent: OudsSmallListItemTrailing? = null,
    val enabled: Boolean = true
)

internal class OudsSmallListItemPreviewParameterProvider :
    BasicPreviewParameterProvider<OudsSmallListItemPreviewParameter>(*smallListItemPreviewParameterValues.toTypedArray())

private val smallListItemPreviewParameterValues: List<OudsSmallListItemPreviewParameter>
    get() {
        val label = "Label"
        val description = "Description"
        val helperText = "Helper text"
        val iconLeadingContent = OudsSmallListItemLeading.Icon.Info
        val iconTrailingContent = OudsSmallListItemTrailing.Icon(Icons.Outlined.FavoriteBorder, "")
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
                leadingContent = OudsSmallListItemLeading.Icon(Icons.Outlined.FavoriteBorder, ""),
                trailingContent = OudsSmallListItemTrailing.Text(label = label, style = OudsListItemTextStyle.LabelStrong)
            ),
            OudsSmallListItemPreviewParameter(
                label = label,
                leadingContent = OudsSmallListItemLeading.Image(CheckerboardPainter, "", OudsListItemImageFormat.Panoramic),
                trailingContent = OudsSmallListItemTrailing.Image(CheckerboardPainter, "", OudsListItemImageFormat.Square)
            )
        )
    }
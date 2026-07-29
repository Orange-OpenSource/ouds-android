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
import com.orange.ouds.core.utilities.OudsPreviewableComponent
import com.orange.ouds.core.utilities.PreviewEnumEntries
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.foundation.ExperimentalOudsApi
import com.orange.ouds.theme.OudsThemeContract

/**
 * TODO update description when available and add version and guideline link
 *
 * Static small list item displays non-clickable information in a compact format.
 *
 * A static small list item provides a condensed way to present read-only information.
 * It is ideal for compact lists, quick settings displays, or dense information layouts.
 * Unlike the standard list item, it omits overline and extra label to maintain a smaller footprint.
 * These items are designed to be stacked within a list, with no spacing between the elements.
 *
 * @see [OudsSmallCardItem] If you need spaced items displayed in a card format (with background or outlined).
 *
 * @param label The main label of the small list item.
 * @param modifier [Modifier] applied to the layout of the small list item.
 * @param verticalAlignment Controls the vertical alignment of the content. Defaults to [OudsListItemVerticalAlignment.CenterVertically].
 * @param description Optional text displayed below the [label].
 * @param leading Optional leading content such as an icon or image displayed at the start of the small list item.
 * @param trailing Optional trailing content such as an icon, image, or text displayed at the end of the small list item.
 * @param divider Controls the display of a divider at the bottom of the small list item. Defaults to `true`.
 * @param background Controls whether the small list item has a background color. Defaults to `true`.
 * @param helperText Optional helper text displayed below the small list item.
 * @param boldLabel Controls whether the label text is displayed in bold. Defaults to `false`.
 * @param enabled Controls the enabled state of the small list item. When `false`, the content is displayed in a disabled state. Defaults to `true`.
 * @param edgeToEdge Controls the horizontal layout of the item. When `true`, the item is designed to span the full width of the screen or container. When `false`,
 *   it is adapted for use within constrained layouts or containers with their own padding. Defaults to `true`.
 * @param interactionSource Optional hoisted [MutableInteractionSource] for observing and emitting interactions for this small list item.
 *
 * @sample com.orange.ouds.core.component.samples.OudsStaticSmallListItemSample
 * @sample com.orange.ouds.core.component.samples.OudsSmallListItemWithAllElementsSample
 * @sample com.orange.ouds.core.component.samples.OudsSmallListItemWithImageSample
 * @sample com.orange.ouds.core.component.samples.OudsSmallListItemWithUntintedIconSample
 */
@ExperimentalOudsApi
@Composable
fun OudsSmallListItem(
    label: String,
    modifier: Modifier = Modifier,
    verticalAlignment: OudsListItemVerticalAlignment = OudsListItemDefaults.VerticalAlignment,
    description: String? = null,
    leading: OudsSmallListItemLeading? = null,
    trailing: OudsSmallListItemTrailing? = null,
    divider: Boolean = true,
    background: Boolean = true,
    helperText: String? = null,
    boldLabel: Boolean = false,
    enabled: Boolean = true,
    edgeToEdge: Boolean = true,
    interactionSource: MutableInteractionSource? = null
) {
    OudsListItem(
        size = OudsListItemSize.Small,
        label = label,
        onClick = null,
        modifier = modifier,
        indicator = null,
        verticalAlignment = verticalAlignment,
        overline = null,
        extraLabel = null,
        description = description,
        leading = leading,
        trailing = trailing,
        decoration = listItemDecoration(background, divider),
        helperText = helperText,
        boldLabel = boldLabel,
        enabled = enabled,
        edgeToEdge = edgeToEdge,
        card = false,
        interactionSource = interactionSource
    )
}

/**
 * TODO update description when available and add version and guideline link
 *
 * Navigation small list item allows users to navigate to another screen or perform an action in a compact format.
 *
 * A navigation small list item is clickable and provides a condensed way to navigate.
 * It is ideal for compact menus, quick settings with actions, or dense navigation lists.
 * The indicator type can be customized to show forward navigation, backward navigation, or
 * external links. Unlike the standard list item, it omits overline and extra label to maintain
 * a smaller footprint while remaining interactive.
 * These items are designed to be stacked within a list, with no spacing between the elements.
 *
 * @see [OudsSmallCardItem] If you need spaced items displayed in a card format (with background or outlined).
 *
 * @param label The main label of the small list item.
 * @param modifier [Modifier] applied to the layout of the small list item.
 * @param onClick Callback invoked when the small list item is clicked.
 * @param indicator The navigation indicator to display. Defaults to [OudsListItemDefaults.Indicator] (Next).
 * @param verticalAlignment Controls the vertical alignment of the content. Defaults to [OudsListItemDefaults.VerticalAlignment].
 * @param description Optional text displayed below the [label].
 * @param leading Optional leading content such as an icon or image displayed at the start of the small list item.
 * @param trailing Optional trailing content such as an icon, image, or text displayed at the end of the small list item.
 * @param divider Controls the display of a divider at the bottom of the small list item. Defaults to `true`.
 * @param background Controls whether the small list item has a background color. Defaults to `false`.
 * @param helperText Optional helper text displayed below the small list item.
 * @param boldLabel Controls whether the label text is displayed in bold. Defaults to `false`.
 * @param enabled Controls the enabled state of the small list item. When `false`, the item is not clickable and content is displayed in a disabled state. Defaults to `true`.
 * @param edgeToEdge Controls the horizontal layout of the item. When `true`, the item is designed to span the full width of the screen or container. When `false`,
 *   it is adapted for use within constrained layouts or containers with their own padding. Defaults to `true`.
 * @param interactionSource Optional hoisted [MutableInteractionSource] for observing and emitting interactions for this small list item.
 *
 * @sample com.orange.ouds.core.component.samples.OudsNavigationSmallListItemSample
 * @sample com.orange.ouds.core.component.samples.OudsSmallListItemWithAllElementsSample
 * @sample com.orange.ouds.core.component.samples.OudsSmallListItemWithImageSample
 * @sample com.orange.ouds.core.component.samples.OudsSmallListItemWithUntintedIconSample
 */
@ExperimentalOudsApi
@Composable
fun OudsSmallListItem(
    label: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    indicator: OudsListItemIndicator = OudsListItemDefaults.Indicator,
    verticalAlignment: OudsListItemVerticalAlignment = OudsListItemDefaults.VerticalAlignment,
    description: String? = null,
    leading: OudsSmallListItemLeading? = null,
    trailing: OudsSmallListItemTrailing? = null,
    divider: Boolean = true,
    background: Boolean = false,
    helperText: String? = null,
    boldLabel: Boolean = false,
    enabled: Boolean = true,
    edgeToEdge: Boolean= true,
    interactionSource: MutableInteractionSource? = null
) {
    OudsListItem(
        size = OudsListItemSize.Small,
        label = label,
        onClick = onClick,
        modifier = modifier,
        indicator = indicator,
        verticalAlignment = verticalAlignment,
        overline = null,
        extraLabel = null,
        description = description,
        leading = leading,
        trailing = trailing,
        decoration = listItemDecoration(background, divider),
        helperText = helperText,
        boldLabel = boldLabel,
        enabled = enabled,
        edgeToEdge = edgeToEdge,
        card = false,
        interactionSource = interactionSource
    )
}

internal val SmallListItemAssetSize = OudsListItemAssetSize.Small

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

        /**
         * Creates an instance of [OudsSmallListItemLeading.Icon] representing an info status.
         * Use for neutral information that requires additional attention.
         */
        object Info : Icon(OudsListItemIconStatus.Info)

        /**
         * Creates an instance of [OudsSmallListItemLeading.Icon] representing a negative status.
         * Use for errors, failures, destructive outcomes or critical problems.
         */
        object Negative : Icon(OudsListItemIconStatus.Negative)

        /**
         * Creates an instance of [OudsSmallListItemLeading.Icon] representing a positive status.
         * Use for successful, completed or beneficial states.
         */
        object Positive : Icon(OudsListItemIconStatus.Positive)

        /**
         * Creates an instance of [OudsSmallListItemLeading.Icon] representing a warning status.
         * Use for situations that may require caution or user awareness.
         */
        object Warning : Icon(OudsListItemIconStatus.Warning)
    }

    /**
     * An image as a small list item leading content.
     */
    class Image internal constructor(
        graphicsObject: Any,
        contentDescription: String,
        ratio: OudsListItemImageRatio,
        contentScale: ContentScale,
        roundedCorner: Boolean
    ) : OudsListItemImage(graphicsObject, contentDescription, SmallListItemAssetSize, ratio, contentScale, roundedCorner), OudsSmallListItemLeading {

        /**
         * Creates an instance of [OudsSmallListItemLeading.Image].
         *
         * @param painter Painter of the image.
         * @param contentDescription The content description associated with this image.
         * @param ratio Ratio of the image among [OudsListItemImageRatio] values.
         * @param contentScale Scale parameter used to determine the aspect ratio scaling to be used if the bounds are a different size from the intrinsic size
         * of the [painter].
         * @param roundedCorner Controls whether the image is displayed with square or rounded corners. False by default.
         */
        constructor(
            painter: Painter,
            contentDescription: String,
            ratio: OudsListItemImageRatio = OudsListItemDefaults.ImageRatio,
            contentScale: ContentScale = OudsListItemDefaults.ImageContentScale,
            roundedCorner: Boolean = false
        ) : this(painter as Any, contentDescription, ratio, contentScale, roundedCorner)

        /**
         * Creates an instance of [OudsSmallListItemLeading.Image].
         *
         * @param imageVector Image vector of the image.
         * @param contentDescription The content description associated with this image.
         * @param ratio Ratio of the image among [OudsListItemImageRatio] values.
         * @param contentScale Scale parameter used to determine the aspect ratio scaling to be used if the bounds are a different size from the intrinsic size
         * of the [imageVector].
         * @param roundedCorner Controls whether the image is displayed with square or rounded corners. False by default.
         */
        constructor(
            imageVector: ImageVector,
            contentDescription: String,
            ratio: OudsListItemImageRatio = OudsListItemDefaults.ImageRatio,
            contentScale: ContentScale = OudsListItemDefaults.ImageContentScale,
            roundedCorner: Boolean = false
        ) : this(imageVector as Any, contentDescription, ratio, contentScale, roundedCorner)

        /**
         * Creates an instance of [OudsSmallListItemLeading.Image].
         *
         * @param bitmap Image bitmap of the image.
         * @param contentDescription The content description associated with this image.
         * @param ratio Ratio of the image among [OudsListItemImageRatio] values.
         * @param contentScale Scale parameter used to determine the aspect ratio scaling to be used if the bounds are a different size from the intrinsic size
         * of the [bitmap].
         * @param roundedCorner Controls whether the image is displayed with square or rounded corners. False by default.
         */
        constructor(
            bitmap: ImageBitmap,
            contentDescription: String,
            ratio: OudsListItemImageRatio = OudsListItemDefaults.ImageRatio,
            contentScale: ContentScale = OudsListItemDefaults.ImageContentScale,
            roundedCorner: Boolean = false
        ) : this(bitmap as Any, contentDescription, ratio, contentScale, roundedCorner)
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

        /**
         * Creates an instance of [OudsSmallListItemTrailing.Icon] representing an info status.
         * Use for neutral information that requires additional attention.
         */
        object Info : Icon(OudsListItemIconStatus.Info)

        /**
         * Creates an instance of [OudsSmallListItemTrailing.Icon] representing a negative status.
         * Use for errors, failures, destructive outcomes or critical problems.
         */
        object Negative : Icon(OudsListItemIconStatus.Negative)

        /**
         * Creates an instance of [OudsSmallListItemTrailing.Icon] representing a positive status.
         * Use for successful, completed or beneficial states.
         */
        object Positive : Icon(OudsListItemIconStatus.Positive)

        /**
         * Creates an instance of [OudsSmallListItemTrailing.Icon] representing a warning status.
         * Use for situations that may require caution or user awareness.
         */
        object Warning : Icon(OudsListItemIconStatus.Warning)
    }

    /**
     * Image as a small list item trailing content.
     */
    class Image internal constructor(
        graphicsObject: Any,
        contentDescription: String,
        ratio: OudsListItemImageRatio,
        contentScale: ContentScale,
        roundedCorner: Boolean
    ) : OudsListItemImage(graphicsObject, contentDescription, SmallListItemAssetSize, ratio, contentScale, roundedCorner), OudsSmallListItemTrailing {

        /**
         * Creates an instance of [OudsSmallListItemTrailing.Image].
         *
         * @param painter Painter of the image.
         * @param contentDescription The content description associated with this image.
         * @param ratio Ratio of the image among [OudsListItemImageRatio] values.
         * @param contentScale Scale parameter used to determine the aspect ratio scaling to be used if the bounds are a different size from the intrinsic size
         * of the [painter].
         * @param roundedCorner Controls whether the image is displayed with square or rounded corners. False by default.
         */
        constructor(
            painter: Painter,
            contentDescription: String,
            ratio: OudsListItemImageRatio = OudsListItemDefaults.ImageRatio,
            contentScale: ContentScale = OudsListItemDefaults.ImageContentScale,
            roundedCorner: Boolean = false
        ) : this(painter as Any, contentDescription, ratio, contentScale, roundedCorner)

        /**
         * Creates an instance of [OudsSmallListItemTrailing.Image].
         *
         * @param imageVector Image vector of the image.
         * @param contentDescription The content description associated with this image.
         * @param ratio Ratio of the image among [OudsListItemImageRatio] values.
         * @param contentScale Scale parameter used to determine the aspect ratio scaling to be used if the bounds are a different size from the intrinsic size
         * of the [imageVector].
         * @param roundedCorner Controls whether the image is displayed with square or rounded corners. False by default.
         */
        constructor(
            imageVector: ImageVector,
            contentDescription: String,
            ratio: OudsListItemImageRatio = OudsListItemDefaults.ImageRatio,
            contentScale: ContentScale = OudsListItemDefaults.ImageContentScale,
            roundedCorner: Boolean = false
        ) : this(imageVector as Any, contentDescription, ratio, contentScale, roundedCorner)

        /**
         * Creates an instance of [OudsSmallListItemTrailing.Image].
         *
         * @param bitmap Image bitmap of the image.
         * @param contentDescription The content description associated with this image.
         * @param ratio Ratio of the image among [OudsListItemImageRatio] values.
         * @param contentScale Scale parameter used to determine the aspect ratio scaling to be used if the bounds are a different size from the intrinsic size
         * of the [bitmap].
         * @param roundedCorner Controls whether the image is displayed with square or rounded corners. False by default.
         */
        constructor(
            bitmap: ImageBitmap,
            contentDescription: String,
            ratio: OudsListItemImageRatio = OudsListItemDefaults.ImageRatio,
            contentScale: ContentScale = OudsListItemDefaults.ImageContentScale,
            roundedCorner: Boolean = false
        ) : this(bitmap as Any, contentDescription, ratio, contentScale, roundedCorner)
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
private fun PreviewOudsStaticSmallListItem(@PreviewParameter(OudsSmallListItemPreviewParameterProvider::class) parameter: OudsListItemPreviewParameter<OudsSmallListItemLeading, OudsSmallListItemTrailing>) {
    PreviewOudsStaticSmallListItem(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsStaticSmallListItem(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsListItemPreviewParameter<OudsSmallListItemLeading, OudsSmallListItemTrailing>
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        OudsSmallListItem(
            label = label,
            description = description,
            helperText = helperText,
            leading = leading,
            trailing = trailing,
            divider = decoration.divider,
            background = decoration is OudsListItemDecoration.Background || decoration is OudsListItemDecoration.BackgroundOnInteraction,
            verticalAlignment = verticalAlignment,
            boldLabel = boldLabel,
            enabled = enabled
        )
    }
}

@Preview(name = "Light", heightDp = OudsPreviewableComponent.SmallListItem.Navigation.PreviewHeightDp, device = OudsPreviewDevice)
@Preview(
    name = "Dark",
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL,
    heightDp = OudsPreviewableComponent.SmallListItem.Navigation.PreviewHeightDp,
    device = OudsPreviewDevice
)
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsNavigationSmallListItem(@PreviewParameter(OudsSmallListItemPreviewParameterProvider::class) parameter: OudsListItemPreviewParameter<OudsSmallListItemLeading, OudsSmallListItemTrailing>) {
    PreviewOudsNavigationSmallListItem(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsNavigationSmallListItem(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsListItemPreviewParameter<OudsSmallListItemLeading, OudsSmallListItemTrailing>
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        PreviewEnumEntries<OudsListItemState>(maxEnumEntriesInEachRow = 1) {
            OudsSmallListItem(
                onClick = {},
                indicator = indicator,
                label = label,
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

internal class OudsSmallListItemPreviewParameterProvider : OudsBasicListItemPreviewParameterProvider<OudsSmallListItemLeading, OudsSmallListItemTrailing>(
    leading = smallListItemPreviewParameterLeading,
    trailing = smallListItemPreviewParameterTrailing
)

internal val smallListItemPreviewParameterLeading: (Int) -> OudsSmallListItemLeading? = { index ->
    when (index) {
        0 -> OudsSmallListItemLeading.Icon.Info
        1 -> OudsSmallListItemLeading.Icon(Icons.Outlined.FavoriteBorder, "")
        2 -> OudsSmallListItemLeading.Image(CheckerboardPainter, "", OudsListItemImageRatio.Widescreen)
        else -> null
    }
}

internal val smallListItemPreviewParameterTrailing: (Int) -> OudsSmallListItemTrailing? = { index ->
    when (index) {
        0 -> OudsSmallListItemTrailing.Icon(Icons.Outlined.FavoriteBorder, "")
        1 -> OudsSmallListItemTrailing.Text(label = "Label", style = OudsListItemTextStyle.LabelStrong)
        2 -> OudsSmallListItemTrailing.Image(CheckerboardPainter, "", OudsListItemImageRatio.Square)
        else -> null
    }
}

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

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.hideFromAccessibility
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.component.content.OudsComponentContent
import com.orange.ouds.core.component.content.OudsComponentIcon
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.CheckedContent
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.PreviewEnumEntries
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider

// TODO: Update documentation URL once it is available
/**
 * A tag is a small element that shows short info like a label, keyword, or category.
 * It helps users quickly find, group, or understand content.
 *
 * Tags have seven status depending on the context of the information they represent. Each state is designed
 * to convey a specific meaning and ensure clarity in communication.
 *
 * This tag API allows to:
 *   - displays only text. Used for simple labels, categories, or keywords without additional visual elements.
 *   - displays a small indicator (bullet) alongside the text. Used to show status, presence, or activity next to the label.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://unified-design-system.orange.com)
 *
 * > Design version: 1.1.0
 *
 * @param label The label displayed in the tag.
 * @param modifier [Modifier] applied to the tag.
 * @param hasBullet Controls the display of a bullet before the tag label.
 * @param hierarchy The importance of the tag. Its background color and its content color are based on this hierarchy combined with the [status] of the tag.
 * @param status The status of the tag. Its background color and its content color are based on this status combined with the [hierarchy] of the tag.
 *   A tag with loading spinner cannot have an [OudsTag.Status.Disabled] status. This will throw an [IllegalStateException].
 * @param shape The shape of the tag. This allows to play with its corners appearance.
 * @param size The size of the tag.
 * @param loading An optional loading spinner (or progress indicator) displayed before the [label]. Used to indicate that a process or action related to the
 * tag is in progress.
 *   A tag with an [OudsTag.Status.Disabled] status cannot have a loading spinner. This will throw an [IllegalStateException].
 */
@Composable
fun OudsTag(
    label: String,
    modifier: Modifier = Modifier,
    hasBullet: Boolean = false,
    hierarchy: OudsTag.Hierarchy = OudsTagDefaults.Hierarchy,
    status: OudsTag.Status = OudsTagDefaults.Status,
    shape: OudsTag.Shape = OudsTagDefaults.Shape,
    size: OudsTag.Size = OudsTagDefaults.Size,
    loading: OudsTag.Loading? = null
) {
    OudsTag(
        hasBullet = hasBullet,
        nullableIcon = null,
        label = label,
        modifier = modifier,
        hierarchy = hierarchy,
        status = status,
        shape = shape,
        size = size,
        loading = loading
    )
}

// TODO: Update documentation URL once it is available
/**
 * A tag is a small element that shows short info like a label, keyword, or category.
 * It helps users quickly find, group, or understand content.
 *
 * Tags have seven status depending on the context of the information they represent. Each state is designed
 * to convey a specific meaning and ensure clarity in communication.
 *
 * This version displays an icon to visually reinforce the meaning of the tag, such as status, type, or action.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://unified-design-system.orange.com)
 *
 * > Design version: 1.1.0
 *
 * @param icon The icon displayed before the label.
 * @param label The label displayed in the tag.
 * @param modifier [Modifier] applied to the tag.
 * @param hierarchy The importance of the tag. Its background color and its content color are based on this hierarchy combined with the [status] of the tag.
 * @param status The status of the tag. Its background color and its content color are based on this status combined with the [hierarchy] of the tag.
 *   A tag with loading spinner cannot have an [OudsTag.Status.Disabled] status. This will throw an [IllegalStateException].
 * @param shape The shape of the tag. This allows to play with its corners appearance.
 * @param size The size of the tag.
 * @param loading An optional loading spinner (or progress indicator) displayed before the [label]. Used to indicate that a process or action related to the
 * tag is in progress.
 *   A tag with an [OudsTag.Status.Disabled] status cannot have a loading spinner. This will throw an [IllegalStateException].
 */
@Composable
fun OudsTag(
    icon: OudsTag.Icon,
    label: String,
    modifier: Modifier = Modifier,
    hierarchy: OudsTag.Hierarchy = OudsTagDefaults.Hierarchy,
    status: OudsTag.Status = OudsTagDefaults.Status,
    shape: OudsTag.Shape = OudsTagDefaults.Shape,
    size: OudsTag.Size = OudsTagDefaults.Size,
    loading: OudsTag.Loading? = null
) {
    OudsTag(
        hasBullet = false,
        nullableIcon = icon,
        label = label,
        modifier = modifier,
        hierarchy = hierarchy,
        status = status,
        shape = shape,
        size = size,
        loading = loading
    )
}

@Composable
private fun OudsTag(
    hasBullet: Boolean,
    nullableIcon: OudsTag.Icon?,
    label: String,
    modifier: Modifier,
    hierarchy: OudsTag.Hierarchy,
    status: OudsTag.Status,
    shape: OudsTag.Shape,
    size: OudsTag.Size,
    loading: OudsTag.Loading?
) {
    val hasAsset = hasBullet || nullableIcon != null || loading != null
    val isForbidden = status == OudsTag.Status.Disabled && loading != null

    CheckedContent(
        expression = !isForbidden,
        exceptionMessage = { "An OudsTag with OudsTag.Status.Disabled status cannot have a loading spinner. This is not allowed." },
        previewMessage = { "⛔" }
    ) {
        // This outer box is necessary otherwise the user can change the size of the tag through the modifier
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .sizeIn(minWidth = minWidth(size), minHeight = minHeight(size))
                    .clip(shape = shape(shape))
                    .background(status.backgroundColor(hierarchy = hierarchy))
                    .padding(paddingValues = contentPadding(size = size, hasAsset = hasAsset)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(betweenAssetAndLabelSpace(size = size), Alignment.CenterHorizontally),
            ) {
                val contentColor = contentColor(status = status, hierarchy = hierarchy)

                if (hasAsset) {
                    Box(modifier = Modifier.size(assetSize(size))) {
                        if (loading != null) {
                            LoadingIndicator(status = status, hierarchy = hierarchy, size = size, progress = loading.progress)
                        } else {
                            val asset = if (hasBullet) OudsTag.Icon(painter = painterResource(id = OudsTheme.drawableResources.tagBullet)) else nullableIcon
                            val assetPadding = if (hasBullet) bulletPadding(size = size) else iconPadding(size = size)

                            asset?.Content(
                                modifier = Modifier.padding(all = assetPadding),
                                extraParameters = OudsTag.Icon.ExtraParameters(tint = contentColor)
                            )
                        }
                    }
                }
                Text(
                    text = label,
                    color = contentColor,
                    style = textStyle(size)
                )
            }
        }
    }
}

@Composable
private fun shape(shape: OudsTag.Shape): RoundedCornerShape {
    return with(OudsTheme.componentsTokens.tag) {
        RoundedCornerShape(
            when (shape) {
                OudsTag.Shape.Square -> OudsTheme.borders.radius.none
                OudsTag.Shape.Rounded -> borderRadius.value
            }
        )
    }
}

@Composable
private fun minWidth(size: OudsTag.Size): Dp {
    return with(OudsTheme.componentsTokens.tag) {
        when (size) {
            OudsTag.Size.Default -> sizeMinWidthDefault
            OudsTag.Size.Small -> sizeMinWidthSmall
        }.dp
    }
}

@Composable
private fun minHeight(size: OudsTag.Size): Dp {
    return with(OudsTheme.componentsTokens.tag) {
        when (size) {
            OudsTag.Size.Default -> sizeMinHeightDefault
            OudsTag.Size.Small -> sizeMinHeightSmall
        }.dp
    }
}

@Composable
private fun assetSize(size: OudsTag.Size): Dp {
    return with(OudsTheme.componentsTokens.tag) {
        when (size) {
            OudsTag.Size.Default -> sizeAssetDefault
            OudsTag.Size.Small -> sizeAssetSmall
        }.value
    }
}

@Composable
private fun textStyle(size: OudsTag.Size): TextStyle {
    return when (size) {
        OudsTag.Size.Default -> OudsTheme.typography.label.strong.medium
        OudsTag.Size.Small -> OudsTheme.typography.label.strong.small
    }.run {
        copy(lineHeightStyle = lineHeightStyle?.copy(alignment = LineHeightStyle.Alignment.Center))
    }
}

@Composable
private fun betweenAssetAndLabelSpace(size: OudsTag.Size): Dp {
    return with(OudsTheme.componentsTokens.tag) {
        when (size) {
            OudsTag.Size.Default -> spaceColumnGapDefault
            OudsTag.Size.Small -> spaceColumnGapSmall
        }.value
    }
}

@Composable
private fun contentColor(status: OudsTag.Status, hierarchy: OudsTag.Hierarchy): Color {
    val disabledContentColor = OudsTheme.colorScheme.content.onAction.disabled
    return with(OudsTheme.colorScheme.content.onStatus) {
        when (hierarchy) {
            OudsTag.Hierarchy.Emphasized -> when (status) {
                OudsTag.Status.Neutral -> neutral.emphasized
                OudsTag.Status.Accent -> accent.emphasized
                OudsTag.Status.Positive -> positive.emphasized
                OudsTag.Status.Warning -> warning.emphasized
                OudsTag.Status.Negative -> negative.emphasized
                OudsTag.Status.Info -> info.emphasized
                OudsTag.Status.Disabled -> disabledContentColor
            }
            OudsTag.Hierarchy.Muted -> when (status) {
                OudsTag.Status.Neutral -> neutral.muted
                OudsTag.Status.Accent -> accent.muted
                OudsTag.Status.Positive -> positive.muted
                OudsTag.Status.Warning -> warning.muted
                OudsTag.Status.Negative -> negative.muted
                OudsTag.Status.Info -> info.muted
                OudsTag.Status.Disabled -> disabledContentColor
            }
        }
    }
}

@Composable
private fun contentPadding(size: OudsTag.Size, hasAsset: Boolean): PaddingValues {
    val verticalPadding: Dp
    val startPadding: Dp
    val endPadding: Dp
    with(OudsTheme.componentsTokens.tag) {
        when (size) {
            OudsTag.Size.Default -> {
                verticalPadding = spacePaddingBlockDefault.value
                startPadding = if (hasAsset) spacePaddingInlineAssetDefault.value else spacePaddingInlineDefault.value
                endPadding = spacePaddingInlineDefault.value
            }
            OudsTag.Size.Small -> {
                verticalPadding = spacePaddingBlockSmall.value
                startPadding = if (hasAsset) spacePaddingInlineAssetSmall.value else spacePaddingInlineSmall.value
                endPadding = spacePaddingInlineSmall.value
            }
        }
    }

    return PaddingValues(top = verticalPadding, bottom = verticalPadding, start = startPadding, end = endPadding)
}

@Composable
private fun iconPadding(size: OudsTag.Size): Dp {
    return with(OudsTheme.componentsTokens.tag) {
        when (size) {
            OudsTag.Size.Default -> spaceInsetIconDefault
            OudsTag.Size.Small -> spaceInsetIconSmall
        }.value
    }
}

@Composable
private fun bulletPadding(size: OudsTag.Size): Dp {
    return with(OudsTheme.componentsTokens.tag) {
        when (size) {
            OudsTag.Size.Default -> spaceInsetBulletDefault.dp
            OudsTag.Size.Small -> spaceInsetBulletSmall.value
        }
    }
}

@Composable
private fun loaderPadding(size: OudsTag.Size): Dp {
    return with(OudsTheme.componentsTokens.tag) {
        when (size) {
            OudsTag.Size.Default -> spaceInsetLoaderDefault
            OudsTag.Size.Small -> spaceInsetLoaderSmall
        }.value
    }
}

@Composable
private fun LoadingIndicator(status: OudsTag.Status, hierarchy: OudsTag.Hierarchy, size: OudsTag.Size, progress: Float?) {
    val modifier = Modifier
        .padding(all = loaderPadding(size = size))
        .fillMaxSize()
        .semantics { hideFromAccessibility() }
    val color = contentColor(status = status, hierarchy = hierarchy)
    val strokeWidth = when (size) {
        OudsTag.Size.Default -> 2.4.dp
        OudsTag.Size.Small -> 2.dp
    }
    val trackColor = Color.Transparent
    val strokeCap = StrokeCap.Butt
    if (progress != null) {
        CircularProgressIndicator(
            progress = { progress },
            modifier = modifier,
            color = color,
            strokeWidth = strokeWidth,
            trackColor = trackColor,
            strokeCap = strokeCap
        )
    } else {
        CircularProgressIndicator(
            modifier = modifier,
            color = color,
            strokeWidth = strokeWidth,
            trackColor = trackColor,
            strokeCap = strokeCap
        )
    }
}

/**
 * Default values for [OudsTag].
 */
object OudsTagDefaults {

    /**
     * Default hierarchy of an [OudsTag].
     */
    val Hierarchy = OudsTag.Hierarchy.Emphasized

    /**
     * Default shape of an [OudsTag].
     */
    val Shape = OudsTag.Shape.Rounded

    /**
     * Default size of an [OudsTag].
     */
    val Size = OudsTag.Size.Default

    /**
     * Default status of an [OudsTag].
     */
    val Status = OudsTag.Status.Neutral

}

/**
 * Contains classes to build an [OudsTag].
 */
object OudsTag {

    enum class Hierarchy {

        /**
         * A tag with a solid, high-contrast background.
         * Used to draw strong attention to important labels or categories. Emphasized tags stand out prominently against the interface and
         * are ideal for primary or high-priority information.
         */
        Emphasized,

        /**
         * A tag with a subtle, light, or semi-transparent background.
         * Used for secondary or less prominent information. Muted tags blend more with the background, providing a softer visual emphasis
         * compared to emphasized tags.
         */
        Muted
    }

    /**
     * An icon in an [OudsTag].
     * This icon is non-clickable. No content description is needed because a tag always contains a label.
     */
    class Icon private constructor(
        graphicsObject: Any
    ) : OudsComponentIcon<Icon.ExtraParameters>(ExtraParameters::class.java, graphicsObject, "") {

        @ConsistentCopyVisibility
        data class ExtraParameters internal constructor(
            internal val tint: Color
        ) : OudsComponentContent.ExtraParameters()

        /**
         * Creates an instance of [OudsTag.Icon].
         *
         * @param painter Painter of the icon.
         */
        constructor(painter: Painter) : this(painter as Any)

        /**
         * Creates an instance of [OudsTag.Icon].
         *
         * @param imageVector Image vector of the icon.
         */
        constructor(imageVector: ImageVector) : this(imageVector as Any)

        /**
         * Creates an instance of [OudsTag.Icon].
         *
         * @param bitmap Image bitmap of the icon.
         */
        constructor(bitmap: ImageBitmap) : this(bitmap as Any)

        override val tint: Color?
            @Composable
            get() = extraParameters.tint
    }

    enum class Shape {

        /**
         * A tag with sharp, square corners.
         * Squared tags provide a more formal, structured, or technical feel. They are often used in business contexts to label promotions, offers, or important notices.
         */
        Square,

        /**
         * A tag with fully rounded corners, creating a pill-shaped appearance.
         * Rounded tags offer a softer and more approachable look, suitable for most modern interfaces.
         */
        Rounded
    }

    enum class Size {

        /** The standard tag size, suitable for most use cases and offering good readability. */
        Default,

        /** A compact tag with reduced height and font size. Used when saving space is important or when grouping elements visually. */
        Small
    }

    /**
     * The status of an [OudsTag]. This status determines the background and content colors of the tag.
     */
    enum class Status {

        /** Default or inactive state. Used for standard labels, categories, or when no specific status needs to be communicated. */
        Neutral,

        /**
         * Used to draw attention to new features, recommendations, or content suggestions.
         * Invites users to explore and engage with new offerings, creating an exciting and engaging experience.
         */
        Accent,

        /** Indicates success, confirmation, or a positive status. Commonly used to highlight completed actions or approved items. */
        Positive,

        /** Signals caution or a potentially risky situation. Used to draw attention to items requiring user awareness or intervention. */
        Warning,

        /** Represents errors, critical issues, or urgent attention needed. Used to highlight problems or failed actions. */
        Negative,

        /** Conveys informational messages or supplementary details. Used for neutral, helpful, or contextual information. */
        Info,

        /** Shows that the tag is inactive and cannot be interacted with. Appears faded or greyed out. */
        Disabled;

        /**
         * The tag background color associated with this status.
         */
        @Composable
        fun backgroundColor(hierarchy: Hierarchy): Color {
            val disabledBackgroundColor = OudsTheme.colorScheme.action.disabled
            return when (hierarchy) {
                Hierarchy.Emphasized -> when (this) {
                    Neutral -> OudsTheme.colorScheme.surface.status.neutral.emphasized
                    Accent -> OudsTheme.colorScheme.surface.status.accent.emphasized
                    Positive -> OudsTheme.colorScheme.surface.status.positive.emphasized
                    Warning -> OudsTheme.colorScheme.surface.status.warning.emphasized
                    Negative -> OudsTheme.colorScheme.surface.status.negative.emphasized
                    Info -> OudsTheme.colorScheme.surface.status.info.emphasized
                    Disabled -> disabledBackgroundColor
                }
                Hierarchy.Muted -> when (this) {
                    Neutral -> OudsTheme.colorScheme.surface.status.neutral.muted
                    Accent -> OudsTheme.colorScheme.surface.status.accent.muted
                    Positive -> OudsTheme.colorScheme.surface.status.positive.muted
                    Warning -> OudsTheme.colorScheme.surface.status.warning.muted
                    Negative -> OudsTheme.colorScheme.surface.status.negative.muted
                    Info -> OudsTheme.colorScheme.surface.status.info.muted
                    Disabled -> disabledBackgroundColor
                }
            }
        }
    }

    /**
     * Displays a spinner in the input or tag area to indicate that tags are being loaded or processed.
     *
     * @param progress The loading progress, where 0.0 represents no progress and 1.0 represents full progress.
     *   Values outside of this range are coerced into the range.
     *   Set this value to `null` to display a circular indeterminate progress indicator.
     */
    data class Loading(val progress: Float?)
}

@PreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsTag(@PreviewParameter(OudsTagPreviewParameterProvider::class) parameter: OudsTagPreviewParameter) {
    PreviewOudsTag(darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsTag(darkThemeEnabled: Boolean, parameter: OudsTagPreviewParameter) = OudsPreview(darkThemeEnabled = darkThemeEnabled) {
    val label = "Label"
    with(parameter) {
        PreviewEnumEntries<OudsTag.Size, OudsTag.Status> { size, status ->
            if (icon != null) {
                OudsTag(
                    icon = OudsTag.Icon(imageVector = icon),
                    label = label,
                    hierarchy = hierarchy,
                    status = status,
                    size = size,
                    shape = shape,
                    loading = loading,
                )
            } else {
                OudsTag(
                    hasBullet = hasBullet,
                    label = label,
                    hierarchy = hierarchy,
                    status = status,
                    size = size,
                    shape = shape,
                    loading = loading,
                )
            }
        }
    }
}

internal data class OudsTagPreviewParameter(
    val icon: ImageVector? = null,
    val hasBullet: Boolean = false,
    val hierarchy: OudsTag.Hierarchy = OudsTagDefaults.Hierarchy,
    val shape: OudsTag.Shape = OudsTagDefaults.Shape,
    val loading: OudsTag.Loading? = null
)

internal class OudsTagPreviewParameterProvider : BasicPreviewParameterProvider<OudsTagPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsTagPreviewParameter>
    get() = listOf(
        OudsTagPreviewParameter(null),
        OudsTagPreviewParameter(null, true, hierarchy = OudsTag.Hierarchy.Muted),
        OudsTagPreviewParameter(Icons.Outlined.FavoriteBorder, shape = OudsTag.Shape.Square),
        OudsTagPreviewParameter(loading = OudsTag.Loading(0.6f))
    )

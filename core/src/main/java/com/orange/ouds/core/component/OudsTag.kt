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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.hideFromAccessibility
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.R
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
 * Four different layouts are supported:
 *   - Text only: when [icon] is `null`, the tag displays only text.
 *     Used for simple labels, categories, or keywords without additional visual elements.
 *   - Text and bullet: when [icon] is equal to [OudsTag.Icon.Bullet], the tag displays a small indicator (bullet) alongside the text.
 *     Used to show status, presence, or activity next to the label.
 *   - Text and icon: when [icon] is not `null`, the tag includes an icon before the text.
 *     Used to visually reinforce the meaning of the tag, such as status, type, or action.
 *   - Text and loader: when [loader] is `true`, the tag combines a loading spinner (or progress indicator) with text.
 *     Used to indicate that a process or action related to the tag is in progress.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://unified-design-system.orange.com)
 *
 * > Design version: 1.1.0
 *
 * @param label The label displayed in the tag.
 * @param modifier [Modifier] applied to the tag.
 * @param icon The icon displayed before the label, or `null` if there is no icon.
 * @param hierarchy The importance of the tag. Its background color and its content color are based on this hierarchy combined with the [status] of the tag.
 * @param status The status of the tag. Its background color and its content color are based on this status combined with the [hierarchy] of the tag.
 *   A tag with loading spinner cannot have an [OudsTag.Status.Disabled] status. This will throw an [IllegalStateException].
 * @param roundedCorners Controls the shape of the tag.
 *   When `true`, the tag has rounded corners, providing a softer and more approachable look, suitable for most modern interfaces.
 *   When `false`, the tag has sharp, square corners, providing a more formal, structured, or technical feel. Often used in business context to label
 *   promotions, offers or important notices.
 * @param size The size of the tag.
 * @param loader An optional loading spinner (or progress indicator) displayed before the [label]. Used to indicate that a process or action related to the
 * tag is in progress.
 *   A tag with an [OudsTag.Status.Disabled] status cannot have a loader. This will throw an [IllegalStateException].
 *
 * @sample com.orange.ouds.core.component.samples.OudsTagSample
 *
 * @sample com.orange.ouds.core.component.samples.OudsTagWithBulletSample
 *
 * @sample com.orange.ouds.core.component.samples.OudsTagWithIconSample
 */
@Composable
fun OudsTag(
    label: String,
    modifier: Modifier = Modifier,
    icon: OudsTag.Icon? = null,
    hierarchy: OudsTag.Hierarchy = OudsTagDefaults.Hierarchy,
    status: OudsTag.Status = OudsTagDefaults.Status,
    roundedCorners: Boolean = true,
    size: OudsTag.Size = OudsTagDefaults.Size,
    loader: OudsTag.Loader? = null
) {
    val hasLoader = loader != null
    val hasAsset = icon != null || hasLoader
    val isForbidden = status == OudsTag.Status.Disabled && hasLoader
    val stateDescription = if (hasLoader) stringResource(id = R.string.core_common_loading_a11y) else ""

    val tagShape = shape(roundedCorners = roundedCorners)
    CheckedContent(
        expression = !isForbidden,
        exceptionMessage = { "An OudsTag with OudsTag.Status.Disabled status cannot have a loader. This is not allowed." },
        previewMessagePaddingValues = contentPadding(size, false),
        shape = tagShape
    ) {
        // This outer box is necessary otherwise the user can change the size of the tag through the modifier
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .sizeIn(minWidth = minWidth(size), minHeight = minHeight(size))
                    .clip(shape = tagShape)
                    .background(backgroundColor(status = status, hierarchy = hierarchy, hasLoader = hasLoader))
                    .semantics(mergeDescendants = true) {
                        this.stateDescription = stateDescription
                    }
                    .padding(paddingValues = contentPadding(size = size, hasAsset = hasAsset)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(betweenAssetAndLabelSpace(size = size), Alignment.CenterHorizontally),
            ) {
                val contentColor = contentColor(status = status, hierarchy = hierarchy, hasLoader = hasLoader)

                if (hasAsset) {
                    Box(
                        modifier = Modifier
                            .size(assetSize(size))
                            .semantics { hideFromAccessibility() }
                    ) {
                        if (hasLoader) {
                            ProgressIndicator(status = status, hierarchy = hierarchy, size = size, progress = loader.progress)
                        } else {
                            val iconPadding = if (icon is OudsTag.Icon.Bullet) bulletPadding(size = size) else iconPadding(size = size)
                            icon?.Content(
                                modifier = Modifier.padding(all = iconPadding),
                                extraParameters = OudsTag.Icon.ExtraParameters(tint = iconColor(status = status, hierarchy = hierarchy))
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
private fun shape(roundedCorners: Boolean): RoundedCornerShape {
    return with(OudsTheme.componentsTokens.tag) {
        RoundedCornerShape(if (roundedCorners) borderRadius.value else OudsTheme.borders.radius.none)
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
private fun backgroundColor(status: OudsTag.Status, hierarchy: OudsTag.Hierarchy, hasLoader: Boolean): Color {
    return if (hasLoader) {
        OudsTheme.colorScheme.surface.status.neutral.muted
    } else {
        when (hierarchy) {
            OudsTag.Hierarchy.Emphasized -> status.color()
            OudsTag.Hierarchy.Muted -> status.mutedColor()
        }
    }
}

@Composable
private fun iconColor(status: OudsTag.Status, hierarchy: OudsTag.Hierarchy): Color {
    return when (hierarchy) {
        OudsTag.Hierarchy.Emphasized -> contentColor(status = status, hierarchy = hierarchy, hasLoader = false)
        OudsTag.Hierarchy.Muted -> if (status == OudsTag.Status.Disabled) OudsTheme.colorScheme.content.onAction.disabled else status.color()
    }
}

@Composable
private fun contentColor(status: OudsTag.Status, hierarchy: OudsTag.Hierarchy, hasLoader: Boolean): Color {
    return if (hasLoader) {
        OudsTheme.colorScheme.content.default
    } else {
        val disabledContentColor = OudsTheme.colorScheme.content.onAction.disabled
        with(OudsTheme.colorScheme.content.onStatus) {
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
private fun ProgressIndicator(status: OudsTag.Status, hierarchy: OudsTag.Hierarchy, size: OudsTag.Size, progress: Float?) {
    val modifier = Modifier
        .padding(all = loaderPadding(size = size))
        .fillMaxSize()
        .semantics { hideFromAccessibility() }
    val color = contentColor(status = status, hierarchy = hierarchy, hasLoader = true)
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
    open class Icon protected constructor(
        graphicsObjectProvider: @Composable () -> Any,
    ) : OudsComponentIcon<Icon.ExtraParameters>(ExtraParameters::class.java, graphicsObjectProvider, "") {

        /**
         * A bullet in an [OudsTag].
         * This bullet is non-clickable. No content description is needed because a tag always contains a label.
         */
        data object Bullet : Icon({}) {

            @Composable
            override fun Content(modifier: Modifier) {
                // The bullet is a simple shape
                // Thus instead of adding an XML drawable we override the default icon content
                // That's why the graphicsObjectProvider parameter of the constructor is an empty lambda
                tint?.let { tint ->
                    Box(
                        modifier = modifier
                            .size(10.dp)
                            .background(tint, shape = RoundedCornerShape(percent = 50))
                    )
                }
            }
        }

        @ConsistentCopyVisibility
        data class ExtraParameters internal constructor(
            internal val tint: Color
        ) : OudsComponentContent.ExtraParameters()

        /**
         * Creates an instance of [OudsTag.Icon].
         *
         * @param painter Painter of the icon.
         */
        constructor(painter: Painter) : this({ painter })

        /**
         * Creates an instance of [OudsTag.Icon].
         *
         * @param imageVector Image vector of the icon.
         */
        constructor(imageVector: ImageVector) : this({ imageVector })

        /**
         * Creates an instance of [OudsTag.Icon].
         *
         * @param bitmap Image bitmap of the icon.
         */
        constructor(bitmap: ImageBitmap) : this({ bitmap })

        override val tint: Color?
            @Composable
            get() = extraParameters.tint
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

        /** Conveys informational messages or supplementary details. Used for neutral, helpful, or contextual information. */
        Info,

        /** Signals caution or a potentially risky situation. Used to draw attention to items requiring user awareness or intervention. */
        Warning,

        /** Represents errors, critical issues, or urgent attention needed. Used to highlight problems or failed actions. */
        Negative,

        /** Shows that the tag is inactive and cannot be interacted with. Appears faded or greyed out. */
        Disabled;

        /**
         * The color associated with this status.
         */
        @Composable
        fun color(): Color {
            return when (this) {
                Neutral -> OudsTheme.colorScheme.surface.status.neutral.emphasized
                Accent -> OudsTheme.colorScheme.surface.status.accent.emphasized
                Positive -> OudsTheme.colorScheme.surface.status.positive.emphasized
                Warning -> OudsTheme.colorScheme.surface.status.warning.emphasized
                Negative -> OudsTheme.colorScheme.surface.status.negative.emphasized
                Info -> OudsTheme.colorScheme.surface.status.info.emphasized
                Disabled -> OudsTheme.colorScheme.action.disabled
            }
        }

        /**
         * The muted color associated with this status.
         */
        @Composable
        fun mutedColor(): Color {
            return when (this) {
                Neutral -> OudsTheme.colorScheme.surface.status.neutral.muted
                Accent -> OudsTheme.colorScheme.surface.status.accent.muted
                Positive -> OudsTheme.colorScheme.surface.status.positive.muted
                Warning -> OudsTheme.colorScheme.surface.status.warning.muted
                Negative -> OudsTheme.colorScheme.surface.status.negative.muted
                Info -> OudsTheme.colorScheme.surface.status.info.muted
                Disabled -> OudsTheme.colorScheme.action.disabled
            }
        }
    }

    /**
     * A circular progress indicator displayed in the input or tag area to indicate that tags are being loaded or processed.
     *
     * @param progress The loading progress, where 0.0 represents no progress and 1.0 represents full progress.
     *   Values outside of this range are coerced into the range.
     *   Set this value to `null` to display a circular indeterminate progress indicator.
     */
    data class Loader(val progress: Float?)
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
            OudsTag(
                label = label,
                icon = icon,
                hierarchy = hierarchy,
                status = status,
                size = size,
                roundedCorners = roundedCorners,
                loader = loader,
            )
        }
    }
}

internal data class OudsTagPreviewParameter(
    val icon: OudsTag.Icon? = null,
    val hierarchy: OudsTag.Hierarchy = OudsTagDefaults.Hierarchy,
    val roundedCorners: Boolean = true,
    val loader: OudsTag.Loader? = null
)

internal class OudsTagPreviewParameterProvider : BasicPreviewParameterProvider<OudsTagPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsTagPreviewParameter>
    get() {
        val icon = OudsTag.Icon(Icons.Outlined.FavoriteBorder)
        val loader = OudsTag.Loader(0.6f)
        return listOf(
            OudsTagPreviewParameter(null),
            OudsTagPreviewParameter(OudsTag.Icon.Bullet, hierarchy = OudsTag.Hierarchy.Muted),
            OudsTagPreviewParameter(icon, hierarchy = OudsTag.Hierarchy.Muted),
            OudsTagPreviewParameter(loader = loader, hierarchy = OudsTag.Hierarchy.Muted),
            OudsTagPreviewParameter(icon, roundedCorners = false),
            OudsTagPreviewParameter(loader = loader)
        )
    }
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.hideFromAccessibility
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.orange.ouds.core.R
import com.orange.ouds.core.component.content.OudsComponentContent
import com.orange.ouds.core.component.content.OudsComponentIcon
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.CheckedContent
import com.orange.ouds.core.utilities.LayeredTintedPainter
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.theme.OudsThemeContract
import kotlin.enums.enumEntries

/**
 * A tag is a small element that shows short info like a label, keyword, or category.
 * It helps users quickly find, group, or understand content.
 *
 * Tags have seven status depending on the context of the information they represent. Each state is designed
 * to convey a specific meaning and ensure clarity in communication.
 *
 * Four different layouts are supported:
 *   - Text only: when [status] icon is `null`, the tag displays only text.
 *     Used for simple labels, categories, or keywords without additional visual elements.
 *   - Text and bullet: when [status] icon is equal to [OudsTagIcon.Bullet], the tag displays a small indicator (bullet) alongside the text.
 *     Used to show status, presence, or activity next to the label.
 *   - Text and icon: when [status] icon is not `null`, the tag includes an icon before the text.
 *     Used to visually reinforce the meaning of the tag, such as status, type, or action.
 *   - Text and loader: when [loader] is `true`, the tag combines a loading spinner (or progress indicator) with text.
 *     Used to indicate that a process or action related to the tag is in progress.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://unified-design-system.orange.com/472794e18/p/7565ce-tag)
 *
 * > Design version: 1.4.0
 *
 * @param label The label displayed in the tag.
 * @param modifier [Modifier] applied to the tag.
 * @param enabled Controls the enabled appearance of the tag.
 *   A tag with loading spinner cannot be disabled. This will throw an [IllegalStateException].
 * @param appearance Appearance of the tag among [OudsTagAppearance] values. Combined with the [status] of the tag, the appearance determines tag's background
 *   and content colors.
 * @param status The status of the tag. Its background color and its content color are based on this status combined with the [appearance] of the tag.
 *   There are two types of status:
 *   - Non-functional statuses ([OudsTagStatus.Neutral] or [OudsTagStatus.Accent]) used to display categories, default states, or to draw attention without
 *     carrying a specific functional meaning (unlike functional tags such as success, info, etc.).
 *     Using a non-functional status, you can provide a custom icon related to the tag’s context to enhance recognition by providing an [OudsTagIcon.Custom]
 *     as the icon of the status.
 *   - Functional statuses communicate specific information or system feedback: [OudsTagStatus.Positive], [OudsTagStatus.Warning], [OudsTagStatus.Negative],
 *     `OudsTagStatus.Info`.
 *     Each functional status has its dedicated functional icon that matches the meaning of the tag. This icon will appear by providing [OudsTagIcon.Default]
 *     as icon value of the status.
 * @param roundedCorners Controls the shape of the tag.
 *   When `true`, the tag has rounded corners, providing a softer and more approachable look, suitable for most modern interfaces.
 *   When `false`, the tag has sharp, square corners, providing a more formal, structured, or technical feel. Often used in business context to label
 *   promotions, offers or important notices.
 * @param size The size of the tag.
 * @param loader An optional loading spinner (or progress indicator) displayed before the [label]. Used to indicate that a process or action related to the
 * tag is in progress.
 *   A disabled tag cannot have a loader. This will throw an [IllegalStateException].
 *
 * @sample com.orange.ouds.core.component.samples.OudsTagSample
 *
 * @sample com.orange.ouds.core.component.samples.OudsTagWithBulletSample
 *
 * @sample com.orange.ouds.core.component.samples.OudsTagWithDefaultIconSample
 *
 * @sample com.orange.ouds.core.component.samples.OudsTagWithCustomIconSample
 */
@Composable
fun OudsTag(
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    appearance: OudsTagAppearance = OudsTagDefaults.Appearance,
    status: OudsTagStatus = OudsTagDefaults.Status,
    roundedCorners: Boolean = true,
    size: OudsTagSize = OudsTagDefaults.Size,
    loader: OudsTagLoader? = null
) {
    val hasLoader = loader != null
    val hasAsset = status.icon != null || hasLoader
    val isForbidden = !enabled && hasLoader
    val stateDescription = if (hasLoader) stringResource(id = R.string.core_common_loading_a11y) else ""

    val tagShape = shape(roundedCorners = roundedCorners)
    CheckedContent(
        expression = !isForbidden,
        exceptionMessage = { "A disabled OudsTag cannot have a loader. This is not allowed." },
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
                    .background(backgroundColor(status = status, appearance = appearance, hasLoader = hasLoader, enabled = enabled))
                    .semantics(mergeDescendants = true) {
                        this.stateDescription = stateDescription
                    }
                    .padding(paddingValues = contentPadding(size = size, hasAsset = hasAsset)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(betweenAssetAndLabelSpace(size = size), Alignment.CenterHorizontally),
            ) {
                val contentColor = contentColor(status = status, appearance = appearance, hasLoader = hasLoader, enabled = enabled)

                if (hasAsset) {
                    Box(
                        modifier = Modifier
                            .size(assetSize(size))
                            .semantics { hideFromAccessibility() }
                    ) {
                        if (hasLoader) {
                            ProgressIndicator(status = status, appearance = appearance, size = size, progress = loader.progress, enabled = enabled)
                        } else {
                            val isBulletIcon = status.icon is OudsTagIcon.Bullet
                            val iconPadding = if (isBulletIcon) bulletPadding(size = size) else iconPadding(size = size)
                            status.icon?.Content(
                                modifier = Modifier.padding(all = iconPadding),
                                extraParameters = OudsTagIcon.ExtraParameters(
                                    tint = iconColor(status = status, appearance = appearance, enabled = enabled, isBulletIcon = isBulletIcon),
                                    status = status,
                                    appearance = appearance
                                )
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
private fun minWidth(size: OudsTagSize): Dp {
    return with(OudsTheme.componentsTokens.tag) {
        when (size) {
            OudsTagSize.Default -> sizeMinWidthDefault
            OudsTagSize.Small -> sizeMinWidthSmall
        }.dp
    }
}

@Composable
private fun minHeight(size: OudsTagSize): Dp {
    return with(OudsTheme.componentsTokens.tag) {
        when (size) {
            OudsTagSize.Default -> sizeMinHeightDefault
            OudsTagSize.Small -> sizeMinHeightSmall
        }.dp
    }
}

@Composable
private fun assetSize(size: OudsTagSize): Dp {
    return with(OudsTheme.componentsTokens.tag) {
        when (size) {
            OudsTagSize.Default -> sizeAssetDefault
            OudsTagSize.Small -> sizeAssetSmall
        }.value
    }
}

@Composable
private fun textStyle(size: OudsTagSize): TextStyle {
    return when (size) {
        OudsTagSize.Default -> OudsTheme.typography.label.strong.medium
        OudsTagSize.Small -> OudsTheme.typography.label.strong.small
    }.run {
        copy(lineHeightStyle = lineHeightStyle?.copy(alignment = LineHeightStyle.Alignment.Center))
    }
}

@Composable
private fun betweenAssetAndLabelSpace(size: OudsTagSize): Dp {
    return with(OudsTheme.componentsTokens.tag) {
        when (size) {
            OudsTagSize.Default -> spaceColumnGapDefault
            OudsTagSize.Small -> spaceColumnGapSmall
        }.value
    }
}

@Composable
private fun backgroundColor(status: OudsTagStatus, appearance: OudsTagAppearance, hasLoader: Boolean, enabled: Boolean): Color {
    return if (!enabled) {
        OudsTheme.colorScheme.action.disabled
    } else {
        if (hasLoader) {
            OudsTheme.colorScheme.surface.secondary
        } else {
            when (appearance) {
                OudsTagAppearance.Emphasized -> status.color()
                OudsTagAppearance.Muted -> status.mutedColor()
            }
        }
    }
}

@Composable
private fun iconColor(status: OudsTagStatus, appearance: OudsTagAppearance, enabled: Boolean, isBulletIcon: Boolean): Color {
    return when (appearance) {
        OudsTagAppearance.Emphasized -> contentColor(status = status, appearance = appearance, hasLoader = false, enabled = enabled)
        OudsTagAppearance.Muted -> when {
            !enabled -> OudsTheme.colorScheme.content.onAction.disabled
            !isBulletIcon && status is OudsTagStatus.Warning -> Color.Unspecified // Case of two colors icon. Colors are managed by the `LayeredTintedPainter`.
            else -> status.color()
        }
    }
}

@Composable
private fun contentColor(status: OudsTagStatus, appearance: OudsTagAppearance, hasLoader: Boolean, enabled: Boolean): Color {
    return if (hasLoader) {
        OudsTheme.colorScheme.content.default
    } else {
        if (!enabled) {
            OudsTheme.colorScheme.content.onAction.disabled
        } else {
            with(OudsTheme.colorScheme.content) {
                when (appearance) {
                    OudsTagAppearance.Emphasized -> when (status) {
                        is OudsTagStatus.Neutral -> inverse
                        is OudsTagStatus.Accent -> onStatus.accent.emphasized
                        is OudsTagStatus.Positive -> onStatus.positive.emphasized
                        is OudsTagStatus.Warning -> onStatus.warning.emphasized
                        is OudsTagStatus.Negative -> onStatus.negative.emphasized
                        is OudsTagStatus.Info -> onStatus.info.emphasized
                    }
                    OudsTagAppearance.Muted -> when (status) {
                        is OudsTagStatus.Neutral -> default
                        is OudsTagStatus.Accent -> onStatus.accent.muted
                        is OudsTagStatus.Positive -> onStatus.positive.muted
                        is OudsTagStatus.Warning -> onStatus.warning.muted
                        is OudsTagStatus.Negative -> onStatus.negative.muted
                        is OudsTagStatus.Info -> onStatus.info.muted
                    }
                }
            }
        }
    }
}

@Composable
private fun contentPadding(size: OudsTagSize, hasAsset: Boolean): PaddingValues {
    val verticalPadding: Dp
    val startPadding: Dp
    val endPadding: Dp
    with(OudsTheme.componentsTokens.tag) {
        when (size) {
            OudsTagSize.Default -> {
                verticalPadding = spacePaddingBlockDefault.value
                startPadding = if (hasAsset) spacePaddingInlineAssetDefault.value else spacePaddingInlineDefault.value
                endPadding = spacePaddingInlineDefault.value
            }
            OudsTagSize.Small -> {
                verticalPadding = spacePaddingBlockSmall.value
                startPadding = if (hasAsset) spacePaddingInlineAssetSmall.value else spacePaddingInlineSmall.value
                endPadding = spacePaddingInlineSmall.value
            }
        }
    }

    return PaddingValues(top = verticalPadding, bottom = verticalPadding, start = startPadding, end = endPadding)
}

@Composable
private fun iconPadding(size: OudsTagSize): Dp {
    return with(OudsTheme.componentsTokens.tag) {
        when (size) {
            OudsTagSize.Default -> spaceInsetIconDefault
            OudsTagSize.Small -> spaceInsetIconSmall
        }.value
    }
}

@Composable
private fun bulletPadding(size: OudsTagSize): Dp {
    return with(OudsTheme.componentsTokens.tag) {
        when (size) {
            OudsTagSize.Default -> spaceInsetBulletDefault.dp
            OudsTagSize.Small -> spaceInsetBulletSmall.value
        }
    }
}

@Composable
private fun loaderPadding(size: OudsTagSize): Dp {
    return with(OudsTheme.componentsTokens.tag) {
        when (size) {
            OudsTagSize.Default -> spaceInsetLoaderDefault
            OudsTagSize.Small -> spaceInsetLoaderSmall
        }.value
    }
}

@Composable
private fun ProgressIndicator(status: OudsTagStatus, appearance: OudsTagAppearance, size: OudsTagSize, progress: Float?, enabled: Boolean) {
    val modifier = Modifier
        .padding(all = loaderPadding(size = size))
        .fillMaxSize()
        .semantics { hideFromAccessibility() }
    val color = contentColor(status = status, appearance = appearance, hasLoader = true, enabled = enabled)
    val strokeWidth = when (size) {
        OudsTagSize.Default -> 2.4.dp
        OudsTagSize.Small -> 2.dp
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
     * Default appearance of an [OudsTag].
     */
    val Appearance = OudsTagAppearance.Emphasized

    /**
     * Default size of an [OudsTag].
     */
    val Size = OudsTagSize.Default

    /**
     * Default status of an [OudsTag].
     */
    val Status = OudsTagStatus.Neutral()
}

enum class OudsTagAppearance {

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
open class OudsTagIcon protected constructor(
    graphicsObjectProvider: @Composable (ExtraParameters) -> Any,
) : OudsComponentIcon<OudsTagIcon.ExtraParameters>(ExtraParameters::class.java, graphicsObjectProvider, "") {

    /**
     * A bullet in an [OudsTag].
     * This bullet is non-clickable. No content description is needed because a tag always contains a label.
     */
    data object Bullet : OudsTagIcon({}) {
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

    open class Custom internal constructor(
        graphicsObjectProvider: @Composable (ExtraParameters) -> Any,
    ) : OudsTagIcon(graphicsObjectProvider) {

        /**
         * Creates an instance of [OudsTagIcon.Custom].
         *
         * @param painter Painter of the icon.
         */
        constructor(painter: Painter) : this({ painter })

        /**
         * Creates an instance of [OudsTagIcon.Custom].
         *
         * @param imageVector Image vector of the icon.
         */
        constructor(imageVector: ImageVector) : this({ imageVector })

        /**
         * Creates an instance of [OudsTagIcon.Custom].
         *
         * @param bitmap Image bitmap of the icon.
         */
        constructor(bitmap: ImageBitmap) : this({ bitmap })
    }

    data object Default : OudsTagIcon({ extraParameters ->
        (extraParameters.status as? FunctionalStatus)?.getDedicatedIconPainter(extraParameters.appearance).orElse { error("No predefined icon for status") }
    })

    @ConsistentCopyVisibility
    data class ExtraParameters internal constructor(
        internal val tint: Color,
        internal val status: OudsTagStatus,
        internal val appearance: OudsTagAppearance
    ) : OudsComponentContent.ExtraParameters()

    override val tint: Color?
        @Composable
        get() = extraParameters.tint
}

/**
 * A circular progress indicator displayed in the input or tag area to indicate that tags are being loaded or processed.
 *
 * @param progress The loading progress, where 0.0 represents no progress and 1.0 represents full progress.
 *   Values outside of this range are coerced into the range.
 *   Set this value to `null` to display a circular indeterminate progress indicator.
 */
data class OudsTagLoader(val progress: Float?)

/**
 * Represents the size of an OUDS tag.
 */
enum class OudsTagSize {

    /** The standard tag size, suitable for most use cases and offering good readability. */
    Default,

    /** A compact tag with reduced height and font size. Used when saving space is important or when grouping elements visually. */
    Small
}

/**
 * The status of an [OudsTag]. Each status is designed to convey a specific meaning and ensure clarity in communication.
 * It determines the background and the content colors of the tag.
 * It also carries the optional icon to be displayed in the tag: bullet or icon. Depending on the status, this icon can be customizable or be a status
 * dedicated icon.
 */
sealed class OudsTagStatus(val icon: OudsTagIcon? = null) {

    /**
     * Default or inactive status. Used for standard labels, categories, or when no specific status needs to be communicated.
     * Its [icon] can be an [OudsTagIcon.Bullet], an [OudsTagIcon.Custom] or `null` if no icon is needed.
     */
    class Neutral internal constructor(icon: OudsTagIcon?) : OudsTagStatus(icon) {
        /**
         * Creates an instance of [OudsTagStatus.Neutral] with a bullet.
         */
        constructor(icon: OudsTagIcon.Bullet) : this(icon as OudsTagIcon)

        /*
         * Creates an instance of [OudsTagStatus.Neutral] with a custom icon or no asset if [icon] is null.
         */
        constructor(icon: OudsTagIcon.Custom? = null) : this(icon as? OudsTagIcon)
    }

    /**
     * Used to draw attention to new features, recommendations, or content suggestions.
     * Invites users to explore and engage with new offerings, creating an exciting and engaging experience.
     * Its [icon] can be an [OudsTagIcon.Bullet], an [OudsTagIcon.Custom] or `null` if no icon is needed.
     */
    class Accent internal constructor(icon: OudsTagIcon?) : OudsTagStatus(icon) {
        /**
         * Creates an instance of [OudsTagStatus.Accent] with a bullet.
         */
        constructor(icon: OudsTagIcon.Bullet) : this(icon as OudsTagIcon)

        /*
         * Creates an instance of [OudsTagStatus.Custom] with a custom icon or no asset if [icon] is null.
         */
        constructor(icon: OudsTagIcon.Custom? = null) : this(icon as? OudsTagIcon)
    }

    /**
     * Indicates success, confirmation, or a positive status. This functional status is commonly used to highlight completed actions or approved items.
     * Its [icon] can be an [OudsTagIcon.Bullet], an [OudsTagIcon.Default] or `null` if no icon is needed.
     */
    class Positive internal constructor(icon: OudsTagIcon?) : OudsTagStatus(icon), FunctionalStatus {
        /**
         * Creates an instance of [OudsTagStatus.Positive] with a bullet.
         */
        constructor(icon: OudsTagIcon.Bullet) : this(icon as OudsTagIcon)

        /**
         * Creates an instance of [OudsTagStatus.Positive] with its default dedicated icon or no asset if [icon] is null.
         */
        constructor(icon: OudsTagIcon.Default? = null) : this(icon as? OudsTagIcon)

        @Composable
        override fun getDedicatedIconPainter(appearance: OudsTagAppearance) = painterResource(OudsTheme.drawableResources.success)
    }

    /**
     * Conveys informational messages or supplementary details. This functional status is used for neutral, helpful, or contextual information.
     * Its [icon] can be an [OudsTagIcon.Bullet], an [OudsTagIcon.Default] or `null` if no icon is needed.
     */
    class Info internal constructor(icon: OudsTagIcon?) : OudsTagStatus(icon), FunctionalStatus {
        /**
         * Creates an instance of [OudsTagStatus.Info] with a bullet.
         */
        constructor(icon: OudsTagIcon.Bullet) : this(icon as OudsTagIcon)

        /**
         * Creates an instance of [OudsTagStatus.Info] with its default dedicated icon or no asset if [icon] is null.
         */
        constructor(icon: OudsTagIcon.Default? = null) : this(icon as? OudsTagIcon)

        @Composable
        override fun getDedicatedIconPainter(appearance: OudsTagAppearance) = painterResource(OudsTheme.drawableResources.information)
    }

    /**
     * Signals caution or a potentially risky situation. This functional status is used to draw attention to items requiring user awareness or intervention.
     * Its [icon] can be an [OudsTagIcon.Bullet], an [OudsTagIcon.Default] or `null` if no icon is needed.
     */
    class Warning internal constructor(icon: OudsTagIcon?) : OudsTagStatus(icon), FunctionalStatus {
        /**
         * Creates an instance of [OudsTagStatus.Warning] with a bullet.
         */
        constructor(icon: OudsTagIcon.Bullet) : this(icon as OudsTagIcon)

        /**
         * Creates an instance of [OudsTagStatus.Warning] with its default dedicated icon or no asset if [icon] is null.
         */
        constructor(icon: OudsTagIcon.Default? = null) : this(icon as? OudsTagIcon)

        @Composable
        override fun getDedicatedIconPainter(appearance: OudsTagAppearance): Painter {
            val iconTokens = OudsTheme.componentsTokens.icon
            return when (appearance) {
                OudsTagAppearance.Emphasized -> painterResource(id = OudsTheme.drawableResources.warningExternalShape)
                OudsTagAppearance.Muted -> LayeredTintedPainter(
                    bottomPainter = painterResource(id = OudsTheme.drawableResources.warningExternalShape),
                    bottomPainterColor = iconTokens.colorContentStatusWarningExternalShape.value,
                    topPainter = painterResource(id = OudsTheme.drawableResources.warningInternalShape),
                    topPainterColor = iconTokens.colorContentStatusWarningInternalShape.value
                )
            }
        }
    }

    /**
     * Represents errors, critical issues, or urgent attention needed. This functional status is used to highlight problems or failed actions.
     * Its [icon] can be an [OudsTagIcon.Bullet], an [OudsTagIcon.Default] or `null` if no icon is needed.
     */
    class Negative internal constructor(icon: OudsTagIcon?) : OudsTagStatus(icon), FunctionalStatus {
        /**
         * Creates an instance of [OudsTagStatus.Negative] with a bullet.
         */
        constructor(icon: OudsTagIcon.Bullet) : this(icon as OudsTagIcon)

        /**
         * Creates an instance of [OudsTagStatus.Negative] with its default dedicated icon or no asset if [icon] is null.
         */
        constructor(icon: OudsTagIcon.Default? = null) : this(icon as? OudsTagIcon)

        @Composable
        override fun getDedicatedIconPainter(appearance: OudsTagAppearance) = painterResource(OudsTheme.drawableResources.important)
    }

    /**
     * The color associated with this status.
     */
    @Composable
    fun color(): Color {
        return when (this) {
            is Neutral -> OudsTheme.colorScheme.surface.inverseHigh
            is Accent -> OudsTheme.colorScheme.surface.status.accent.emphasized
            is Positive -> OudsTheme.colorScheme.surface.status.positive.emphasized
            is Warning -> OudsTheme.colorScheme.surface.status.warning.emphasized
            is Negative -> OudsTheme.colorScheme.surface.status.negative.emphasized
            is Info -> OudsTheme.colorScheme.surface.status.info.emphasized
        }
    }

    /**
     * The muted color associated with this status.
     */
    @Composable
    fun mutedColor(): Color {
        return when (this) {
            is Neutral -> OudsTheme.colorScheme.surface.secondary
            is Accent -> OudsTheme.colorScheme.surface.status.accent.muted
            is Positive -> OudsTheme.colorScheme.surface.status.positive.muted
            is Warning -> OudsTheme.colorScheme.surface.status.warning.muted
            is Negative -> OudsTheme.colorScheme.surface.status.negative.muted
            is Info -> OudsTheme.colorScheme.surface.status.info.muted
        }
    }
}

private interface FunctionalStatus {
    @Composable
    fun getDedicatedIconPainter(appearance: OudsTagAppearance): Painter
}

@PreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsTag(@PreviewParameter(OudsTagPreviewParameterProvider::class) parameter: OudsTagPreviewParameter) {
    PreviewOudsTag(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsTag(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsTagPreviewParameter
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    val label = "Label"
    with(parameter) {
        val space = 16.dp
        val sizes = enumEntries<OudsTagSize>()
        val columnCount = sizes.count()
        val rowCount = statuses.size

        LazyVerticalGrid(
            columns = GridCells.Fixed(1 + columnCount),
            contentPadding = PaddingValues(all = space),
            horizontalArrangement = Arrangement.spacedBy(space),
            verticalArrangement = Arrangement.spacedBy(space)
        ) {
            repeat(1 + rowCount) { rowIndex ->
                repeat(1 + columnCount) { columnIndex ->
                    val status = statuses.getOrNull(rowIndex - 1)
                    val size = sizes.getOrNull(columnIndex - 1)
                    item {
                        when {
                            status == null && size != null -> PreviewTableHeader(size.name)
                            status != null && size == null -> PreviewTableHeader(status::class.simpleName.orEmpty())
                            status != null && size != null -> {
                                Box {
                                    OudsTag(
                                        label = label,
                                        status = status,
                                        appearance = appearance,
                                        size = size,
                                        roundedCorners = roundedCorners,
                                        loader = loader,
                                        enabled = enabled
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PreviewTableHeader(header: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = header,
        color = OudsTheme.colorScheme.content.default,
        fontFamily = FontFamily.Monospace,
        fontSize = 10.sp
    )
}

internal data class OudsTagPreviewParameter(
    val statuses: List<OudsTagStatus> = listOf(
        OudsTagStatus.Neutral(),
        OudsTagStatus.Accent(),
        OudsTagStatus.Positive(),
        OudsTagStatus.Warning(),
        OudsTagStatus.Negative(),
        OudsTagStatus.Info()
    ),
    val appearance: OudsTagAppearance = OudsTagDefaults.Appearance,
    val roundedCorners: Boolean = true,
    val loader: OudsTagLoader? = null,
    val enabled: Boolean = true
)

internal class OudsTagPreviewParameterProvider : BasicPreviewParameterProvider<OudsTagPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsTagPreviewParameter>
    get() {
        val icon = OudsTagIcon.Custom(Icons.Outlined.FavoriteBorder)
        val statusesWithBullet = listOf(
            OudsTagStatus.Neutral(icon = OudsTagIcon.Bullet),
            OudsTagStatus.Accent(icon = OudsTagIcon.Bullet),
            OudsTagStatus.Positive(icon = OudsTagIcon.Bullet),
            OudsTagStatus.Warning(icon = OudsTagIcon.Bullet),
            OudsTagStatus.Negative(icon = OudsTagIcon.Bullet),
            OudsTagStatus.Info(icon = OudsTagIcon.Bullet)
        )
        val statusesWithIcon = listOf(
            OudsTagStatus.Neutral(icon = icon),
            OudsTagStatus.Accent(icon = icon),
            OudsTagStatus.Positive(icon = OudsTagIcon.Default),
            OudsTagStatus.Warning(icon = OudsTagIcon.Default),
            OudsTagStatus.Negative(icon = OudsTagIcon.Default),
            OudsTagStatus.Info(icon = OudsTagIcon.Default)
        )
        val loader = OudsTagLoader(0.6f)

        return listOf(
            OudsTagPreviewParameter(),
            OudsTagPreviewParameter(statusesWithBullet, appearance = OudsTagAppearance.Muted),
            OudsTagPreviewParameter(statusesWithIcon, appearance = OudsTagAppearance.Muted),
            OudsTagPreviewParameter(statusesWithIcon, roundedCorners = false),
            OudsTagPreviewParameter(loader = loader),
            OudsTagPreviewParameter(enabled = false, appearance = OudsTagAppearance.Muted),
        )
    }

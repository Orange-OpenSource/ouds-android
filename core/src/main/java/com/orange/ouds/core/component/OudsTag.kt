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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.hideFromAccessibility
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.R
import com.orange.ouds.core.component.content.OudsComponentContent
import com.orange.ouds.core.component.content.OudsComponentIcon
import com.orange.ouds.core.component.content.OudsPolymorphicComponentContent
import com.orange.ouds.core.component.content.PolymorphicContent
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.CheckedContent
import com.orange.ouds.core.utilities.LayeredTintedPainter
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.OudsPreviewLightDark
import com.orange.ouds.core.utilities.PreviewGrid
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.theme.OudsThemeContract
import kotlin.enums.enumEntries

/**
 * Tag is a UI element that allows to display short info like a label, keyword, or category. Tag helps users quickly find, group, or understand content.
 *
 * Tags have six statuses depending on the context of the information they represent. Each status is designed
 * to convey a specific meaning and ensure clarity in communication.
 *
 * Four different layouts are supported:
 *   - Text only: when [status] asset is `null`, the tag displays only text.
 *     Used for simple labels, categories, or keywords without additional visual elements.
 *   - Text and bullet: when [status] asset is equal to [OudsTagAsset.Bullet], the tag displays a small indicator (bullet) alongside the text.
 *     Used to show status, presence, or activity next to the label.
 *   - Text and icon: when [status] asset is an [OudsTagAsset.Icon] or an [OudsTagAsset.Icon.Default], the tag includes an icon before the text.
 *     Used to visually reinforce the meaning of the tag, such as status, type, or action.
 *   - Text and loader: when [loader] is not `null`, the tag combines a loading spinner (or progress indicator) with text.
 *     Used to indicate that a process or action related to the tag is in progress.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://r.orange.fr/r/S-ouds-doc-tag)
 *
 * > Design version: 1.4.0
 *
 * @param label The label displayed in the tag.
 * @param modifier [Modifier] applied to the tag.
 * @param enabled Controls the enabled appearance of the tag.
 *   A tag with a loading spinner cannot be disabled. This will throw an [IllegalStateException].
 * @param appearance Appearance of the tag among [OudsTagAppearance] values. Combined with the [status] of the tag, the appearance determines the tag's background
 *   and content colors.
 * @param status The status of the tag. Its background color and its content color are based on this status combined with the [appearance] of the tag.
 *   There are two types of statuses:
 *   - Non-functional statuses ([OudsTagStatus.Neutral] or [OudsTagStatus.Accent]) used to display categories, default states, or to draw attention without
 *   carrying a specific functional meaning (unlike functional tags such as success, info, etc.).
 *   Using a non-functional status, you can provide an icon related to the tag’s context to enhance recognition by providing an [OudsTagAsset.Icon]
 *   as the asset of the status.
 *   - Functional statuses communicate specific information or system feedback: [OudsTagStatus.Positive], [OudsTagStatus.Warning], [OudsTagStatus.Negative],
 *   [OudsTagStatus.Info].
 *   Each functional status has its dedicated functional icon that matches the meaning of the tag. This icon will appear by providing [OudsTagAsset.Icon.Default]
 *   as the asset of the status.
 * @param roundedCorners Controls the shape of the tag.
 *   When `true`, the tag has rounded corners, providing a softer and more approachable look, suitable for most modern interfaces.
 *   When `false`, the tag has sharp, square corners, providing a more formal, structured, or technical feel. Often used in a business context to label
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
 * @sample com.orange.ouds.core.component.samples.OudsTagWithIconSample
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
    val hasAsset = status.asset != null || hasLoader
    val isForbidden = !enabled && hasLoader
    val stateDescription = if (hasLoader) stringResource(id = R.string.core_common_loading_a11y) else ""

    val tagShape = shape(roundedCorners = roundedCorners)
    CheckedContent(
        expression = !isForbidden,
        exceptionMessage = { "A disabled OudsTag cannot have a loader. This is not allowed." }
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
                if (hasAsset) {
                    Box {
                        if (hasLoader) {
                            ProgressIndicator(status = status, appearance = appearance, size = size, progress = loader.progress, enabled = enabled)
                        } else {
                            val isBulletAsset = status.asset is OudsTagAsset.Bullet
                            val assetPadding = if (isBulletAsset) bulletPadding(size = size) else iconPadding(size = size)
                            val scale = LocalConfiguration.current.fontScale
                            status.asset?.PolymorphicContent(
                                modifier = Modifier
                                    .size(assetSize(size) * scale)
                                    .padding(all = assetPadding),
                                extraParameters = OudsTagAsset.ExtraParameters(
                                    tint = assetColor(status = status, appearance = appearance, enabled = enabled, isBullet = isBulletAsset),
                                    status = status,
                                    appearance = appearance
                                )
                            )
                        }
                    }
                }
                Text(
                    text = label,
                    color = contentColor(status = status, appearance = appearance, hasLoader = hasLoader, enabled = enabled),
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
    return when {
        !enabled -> OudsTheme.colorScheme.action.disabled
        hasLoader -> OudsTheme.colorScheme.surface.secondary
        else -> when (appearance) {
            OudsTagAppearance.Emphasized -> status.color()
            OudsTagAppearance.Muted -> status.mutedColor()
        }
    }
}

@Composable
private fun assetColor(status: OudsTagStatus, appearance: OudsTagAppearance, enabled: Boolean, isBullet: Boolean): Color {
    return when (appearance) {
        OudsTagAppearance.Emphasized -> contentColor(status = status, appearance = appearance, hasLoader = false, enabled = enabled)
        OudsTagAppearance.Muted -> when {
            !enabled -> OudsTheme.colorScheme.content.onAction.disabled
            !isBullet && status is OudsTagStatus.Warning -> Color.Unspecified // Case of two colors icon. Colors are managed by the `LayeredTintedPainter`.
            else -> status.color()
        }
    }
}

@Composable
private fun contentColor(status: OudsTagStatus, appearance: OudsTagAppearance, hasLoader: Boolean, enabled: Boolean): Color {
    return when {
        hasLoader -> OudsTheme.colorScheme.content.default
        !enabled -> OudsTheme.colorScheme.content.onAction.disabled
        else -> with(OudsTheme.colorScheme.content) {
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
    val scale = LocalConfiguration.current.fontScale
    val modifier = Modifier
        .size(assetSize(size) * scale)
        .padding(all = loaderPadding(size = size))
        .semantics { hideFromAccessibility() }
    val color = contentColor(status = status, appearance = appearance, hasLoader = true, enabled = enabled)
    val strokeWidth = when (size) {
        OudsTagSize.Default -> 2.4.dp
        OudsTagSize.Small -> 2.dp
    } * scale
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
 * An asset in an [OudsTag].
 * This asset is non-clickable. No content description is needed because a tag always contains a label.
 */
sealed interface OudsTagAsset : OudsPolymorphicComponentContent {

    /**
     * A bullet in an [OudsTag].
     * This bullet is non-clickable. No content description is needed because a tag always contains a label.
     */
    data object Bullet : OudsTagAsset, OudsComponentContent<OudsTagAsset.ExtraParameters>(OudsTagAsset.ExtraParameters::class.java) {

        @Composable
        override fun Content(modifier: Modifier) {
            Box(
                modifier = modifier
                    .size(10.dp)
                    .background(extraParameters.tint, shape = RoundedCornerShape(percent = 50))
            )
        }
    }

    /**
     * An icon in an [OudsTag].
     * This icon is non-clickable. No content description is needed because a tag always contains a label.
     */
    class Icon private constructor(graphicsObject: Any) : OudsTagAsset,
        OudsComponentIcon<OudsTagAsset.ExtraParameters, Icon>(OudsTagAsset.ExtraParameters::class.java, graphicsObject, "") {

        /**
         * Creates an instance of [OudsTagAsset.Icon].
         *
         * @param painter Painter of the icon.
         */
        constructor(painter: Painter) : this(painter as Any)

        /**
         * Creates an instance of [OudsTagAsset.Icon].
         *
         * @param imageVector Image vector of the icon.
         */
        constructor(imageVector: ImageVector) : this(imageVector as Any)

        /**
         * Creates an instance of [OudsTagAsset.Icon].
         *
         * @param bitmap Image bitmap of the icon.
         */
        constructor(bitmap: ImageBitmap) : this(bitmap as Any)

        override val tint: Color?
            @Composable
            get() = extraParameters.tint

        /**
         * The default icon of an [OudsTag].
         * This icon is non-clickable. A content description is only set for Warning and Error statuses to provide context. No content description is needed
         * for other statuses because the tag's `label` should provide the necessary context.
         */
        data object Default : OudsTagAsset, OudsComponentIcon<OudsTagAsset.ExtraParameters, Default>(
            OudsTagAsset.ExtraParameters::class.java,
            { icon ->
                with(icon.extraParameters) {
                    status.getDefaultIconPainter(appearance).orElse {
                        error("No default icon for status ${status::class.simpleName}")
                    }
                }
            },
            { icon -> icon.extraParameters.status.defaultIconContentDescription }
        ) {

            override val tint: Color?
                @Composable
                get() = extraParameters.tint
        }
    }

    @ConsistentCopyVisibility
    data class ExtraParameters internal constructor(
        internal val tint: Color,
        internal val status: OudsTagStatus,
        internal val appearance: OudsTagAppearance
    ) : OudsComponentContent.ExtraParameters()
}

/**
 * A circular progress indicator displayed in the tag area to indicate that tags are being loaded or processed.
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
 * It also carries the optional asset to be displayed in the tag: bullet or icon. Depending on the status, this icon can be customizable or be a status
 * dedicated icon.
 *
 * @property asset The asset to be displayed in the tag, or `null` if there is no asset.
 */
sealed class OudsTagStatus(val asset: OudsTagAsset? = null) {

    @Composable
    internal open fun getDefaultIconPainter(appearance: OudsTagAppearance): Painter? = null

    internal open val defaultIconContentDescription: String
        @Composable
        get() = ""

    /**
     * Default or inactive status. Used for standard labels, categories, or when no specific status needs to be communicated.
     * Its [asset] can be an [OudsTagAsset.Bullet], an [OudsTagAsset.Icon] or `null` if no asset is needed.
     */
    class Neutral internal constructor(asset: OudsTagAsset?) : OudsTagStatus(asset) {
        /**
         * Creates an instance of [OudsTagStatus.Neutral] with a bullet.
         */
        constructor(asset: OudsTagAsset.Bullet) : this(asset as OudsTagAsset)

        /**
         * Creates an instance of [OudsTagStatus.Neutral] with an icon.
         */
        constructor(asset: OudsTagAsset.Icon) : this(asset as? OudsTagAsset)

        /**
         * Creates an instance of [OudsTagStatus.Neutral] with no asset.
         */
        constructor() : this(null)
    }

    /**
     * Used to draw attention to new features, recommendations, or content suggestions.
     * Invites users to explore and engage with new offerings, creating an exciting and engaging experience.
     * Its [asset] can be an [OudsTagAsset.Bullet], an [OudsTagAsset.Icon] or `null` if no asset is needed.
     */
    class Accent internal constructor(asset: OudsTagAsset?) : OudsTagStatus(asset) {
        /**
         * Creates an instance of [OudsTagStatus.Accent] with a bullet.
         */
        constructor(asset: OudsTagAsset.Bullet) : this(asset as OudsTagAsset)

        /**
         * Creates an instance of [OudsTagStatus.Accent] with an icon.
         */
        constructor(asset: OudsTagAsset.Icon) : this(asset as? OudsTagAsset)

        /**
         * Creates an instance of [OudsTagStatus.Accent] with no asset.
         */
        constructor() : this(null)
    }

    /**
     * Indicates success, confirmation, or a positive status. This functional status is commonly used to highlight completed actions or approved items.
     * Its [asset] can be an [OudsTagAsset.Bullet], an [OudsTagAsset.Icon.Default] or `null` if no asset is needed.
     */
    class Positive internal constructor(asset: OudsTagAsset?) : OudsTagStatus(asset) {
        /**
         * Creates an instance of [OudsTagStatus.Positive] with a bullet.
         */
        constructor(asset: OudsTagAsset.Bullet) : this(asset as OudsTagAsset)

        /**
         * Creates an instance of [OudsTagStatus.Positive] with its default dedicated icon.
         */
        constructor(asset: OudsTagAsset.Icon.Default) : this(asset as? OudsTagAsset)

        /**
         * Creates an instance of [OudsTagStatus.Positive] with no asset.
         */
        constructor() : this(null)

        @Composable
        override fun getDefaultIconPainter(appearance: OudsTagAppearance) = painterResource(OudsTheme.drawableResources.component.alert.tickConfirmationFill)
    }

    /**
     * Conveys informational messages or supplementary details. This functional status is used for neutral, helpful, or contextual information.
     * Its [asset] can be an [OudsTagAsset.Bullet], an [OudsTagAsset.Icon.Default] or `null` if no asset is needed.
     */
    class Info internal constructor(asset: OudsTagAsset?) : OudsTagStatus(asset) {
        /**
         * Creates an instance of [OudsTagStatus.Info] with a bullet.
         */
        constructor(asset: OudsTagAsset.Bullet) : this(asset as OudsTagAsset)

        /**
         * Creates an instance of [OudsTagStatus.Info] with its default dedicated icon.
         */
        constructor(asset: OudsTagAsset.Icon.Default) : this(asset as? OudsTagAsset)

        /**
         * Creates an instance of [OudsTagStatus.Info] with no asset.
         */
        constructor() : this(null)

        @Composable
        override fun getDefaultIconPainter(appearance: OudsTagAppearance) = painterResource(OudsTheme.drawableResources.component.alert.infoFill)
    }

    /**
     * Signals caution or a potentially risky situation. This functional status is used to draw attention to items requiring user awareness or intervention.
     * Its [asset] can be an [OudsTagAsset.Bullet], an [OudsTagAsset.Icon.Default] or `null` if no asset is needed.
     */
    class Warning internal constructor(asset: OudsTagAsset?) : OudsTagStatus(asset) {
        /**
         * Creates an instance of [OudsTagStatus.Warning] with a bullet.
         */
        constructor(asset: OudsTagAsset.Bullet) : this(asset as OudsTagAsset)

        /**
         * Creates an instance of [OudsTagStatus.Warning] with its default dedicated icon.
         */
        constructor(asset: OudsTagAsset.Icon.Default) : this(asset as? OudsTagAsset)

        /**
         * Creates an instance of [OudsTagStatus.Warning] with no asset.
         */
        constructor() : this(null)

        @Composable
        override fun getDefaultIconPainter(appearance: OudsTagAppearance): Painter {
            val iconTokens = OudsTheme.componentsTokens.icon
            return when (appearance) {
                OudsTagAppearance.Emphasized -> painterResource(id = OudsTheme.drawableResources.component.alert.warningExternalShape)
                OudsTagAppearance.Muted -> LayeredTintedPainter(
                    backPainter = painterResource(id = OudsTheme.drawableResources.component.alert.warningExternalShape),
                    backPainterColor = iconTokens.colorContentStatusWarningExternalShape.value,
                    frontPainter = painterResource(id = OudsTheme.drawableResources.component.alert.warningInternalShape),
                    frontPainterColor = iconTokens.colorContentStatusWarningInternalShape.value
                )
            }
        }

        override val defaultIconContentDescription
            @Composable
            get() = stringResource(id = R.string.core_common_warning_a11y)
    }

    /**
     * Represents errors, critical issues, or urgent attention needed. This functional status is used to highlight problems or failed actions.
     * Its [asset] can be an [OudsTagAsset.Bullet], an [OudsTagAsset.Icon.Default] or `null` if no asset is needed.
     */
    class Negative internal constructor(asset: OudsTagAsset?) : OudsTagStatus(asset) {
        /**
         * Creates an instance of [OudsTagStatus.Negative] with a bullet.
         */
        constructor(asset: OudsTagAsset.Bullet) : this(asset as OudsTagAsset)

        /**
         * Creates an instance of [OudsTagStatus.Negative] with its default dedicated icon.
         */
        constructor(asset: OudsTagAsset.Icon.Default) : this(asset as? OudsTagAsset)

        /**
         * Creates an instance of [OudsTagStatus.Negative] with no asset.
         */
        constructor() : this(null)

        @Composable
        override fun getDefaultIconPainter(appearance: OudsTagAppearance) = painterResource(OudsTheme.drawableResources.component.alert.importantFill)

        override val defaultIconContentDescription
            @Composable
            get() = stringResource(id = R.string.core_common_error_a11y)
    }

    /**
     * The color associated with this status.
     */
    @Composable
    fun color(): Color {
        return with(OudsTheme.colorScheme.surface) {
            when (this@OudsTagStatus) {
                is Neutral -> inverseHigh
                is Accent -> status.accent.emphasized
                is Positive -> status.positive.emphasized
                is Warning -> status.warning.emphasized
                is Negative -> status.negative.emphasized
                is Info -> status.info.emphasized
            }
        }
    }

    /**
     * The muted color associated with this status.
     */
    @Composable
    fun mutedColor(): Color {
        return with(OudsTheme.colorScheme.surface) {
            when (this@OudsTagStatus) {
                is Neutral -> secondary
                is Accent -> status.accent.muted
                is Positive -> status.positive.muted
                is Warning -> status.warning.muted
                is Negative -> status.negative.muted
                is Info -> status.info.muted
            }
        }
    }
}

@OudsPreviewLightDark
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
        PreviewGrid(
            columns = enumEntries<OudsTagSize>(),
            rows = statuses,
            columnTitle = { it.name },
            rowTitle = { it::class.simpleName.orEmpty() }
        ) { size, status ->
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
        val icon = OudsTagAsset.Icon(Icons.Outlined.FavoriteBorder)
        val statusesWithBullet = listOf(
            OudsTagStatus.Neutral(asset = OudsTagAsset.Bullet),
            OudsTagStatus.Accent(asset = OudsTagAsset.Bullet),
            OudsTagStatus.Positive(asset = OudsTagAsset.Bullet),
            OudsTagStatus.Warning(asset = OudsTagAsset.Bullet),
            OudsTagStatus.Negative(asset = OudsTagAsset.Bullet),
            OudsTagStatus.Info(asset = OudsTagAsset.Bullet)
        )
        val statusesWithIcon = listOf(
            OudsTagStatus.Neutral(asset = icon),
            OudsTagStatus.Accent(asset = icon),
            OudsTagStatus.Positive(asset = OudsTagAsset.Icon.Default),
            OudsTagStatus.Warning(asset = OudsTagAsset.Icon.Default),
            OudsTagStatus.Negative(asset = OudsTagAsset.Icon.Default),
            OudsTagStatus.Info(asset = OudsTagAsset.Icon.Default)
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

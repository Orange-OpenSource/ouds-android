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
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.component.content.OudsComponentContent
import com.orange.ouds.core.component.content.OudsComponentIcon
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.OudsPreviewableComponent
import com.orange.ouds.core.utilities.PreviewEnumEntries
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.theme.OudsThemeContract


/**
 * The badge is a small UI element used to highlight status, notifications, or categorization within an interface.
 * It is often displayed as a label or indicator with a distinct background color and text.
 *
 * Badges have five statuses depending on the context of the information they represent.
 * Each status is designed to convey a specific meaning and ensure clarity in communication.
 *
 * This version of the badge renders as a static label without a number.
 * It is used for status indicators (e.g., "New", "Pending", "Success").
 * The size remains unchanged despite the increase in the interface size.
 *
 * See [BadgedBox] for a top level layout that will properly place the badge relative to content
 * such as text or an icon.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://unified-design-system.orange.com/472794e18/p/698ea8-badge)
 *
 * > Design version: 1.1.0
 *
 * @param modifier The [Modifier] to be applied to this badge.
 * @param status The status of this badge. The background color of the badge is based on this status.
 * @param size The size of this badge.
 *
 * @sample com.orange.ouds.core.component.samples.OudsBadgeSample
 */
@Composable
fun OudsBadge(
    modifier: Modifier = Modifier,
    status: OudsBadgeStatus = OudsBadgeDefaults.Status,
    size: OudsBadgeSize = OudsBadgeDefaults.Size
) {
    OudsBadge(
        count = null,
        icon = null,
        modifier = modifier,
        status = status,
        size = size
    )
}

/**
 * The badge is a small UI element used to highlight status, notifications, or categorization within an interface.
 * It is often displayed as a label or indicator with a distinct background color and text.
 *
 * Badges have five statuses depending on the context of the information they represent.
 * Each status is designed to convey a specific meaning and ensure clarity in communication.
 *
 * This version of the badge displays numerical values (e.g., unread messages, notifications).
 *
 * See [BadgedBox] for a top level layout that will properly place the badge relative to content
 * such as text or an icon.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://unified-design-system.orange.com/472794e18/p/698ea8-badge/t/7f61fd0dac)
 *
 * > Design version: 1.1.0
 *
 * @param count The number displayed in the badge. Minimum and maximum values are 0 and 99 respectively.
 *   Values greater than 99 are displayed as "+99".
 * @param modifier The [Modifier] to be applied to this badge.
 * @param status The status of this badge. The background color of the badge and the number color are based on this status.
 * @param size The size of this badge. The number is not displayed when size is [OudsBadgeSize.ExtraSmall] or [OudsBadgeSize.Small].
 *
 * @sample com.orange.ouds.core.component.samples.OudsBadgeWithIconSample
 *
 * @sample com.orange.ouds.core.component.samples.OudsBadgeWithCountInNavigationBarItemSample
 */
@Composable
fun OudsBadge(
    count: Int,
    modifier: Modifier = Modifier,
    status: OudsBadgeStatus = OudsBadgeDefaults.Status,
    size: OudsBadgeSize = OudsBadgeDefaults.Size
) {
    OudsBadge(
        count = count,
        icon = null,
        modifier = modifier,
        status = status,
        size = size
    )
}

/**
 * The badge is a small UI element used to highlight status, notifications, or categorization within an interface.
 * It is often displayed as a label or indicator with a distinct background color and text.
 *
 * Badges have five statuses depending on the context of the information they represent.
 * Each status is designed to convey a specific meaning and ensure clarity in communication.
 *
 * This version of the badge displays an icon to visually reinforce meaning.
 *
 * See [BadgedBox] for a top level layout that will properly place the badge relative to content
 * such as text or an icon.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://unified-design-system.orange.com/472794e18/p/698ea8-badge/t/cb0a05d005)
 *
 * > Design version: 1.1.0
 *
 * @param icon The icon displayed in the badge.
 * @param modifier The [Modifier] to be applied to this badge.
 * @param status The status of this badge. The background color of the badge and the icon color are based on this status.
 * @param size The size of this badge. The icon is not displayed when size is [OudsBadgeSize.ExtraSmall] or [OudsBadgeSize.Small].
 *
 * @sample com.orange.ouds.core.component.samples.OudsBadgeWithIconSample
 */
@Composable
fun OudsBadge(
    icon: OudsBadgeIcon,
    modifier: Modifier = Modifier,
    status: OudsBadgeStatus = OudsBadgeDefaults.Status,
    size: OudsBadgeSize = OudsBadgeDefaults.Size
) {
    OudsBadge(
        count = null,
        icon = icon,
        modifier = modifier,
        status = status,
        size = size
    )
}

@Composable
private fun OudsBadge(
    count: Int?,
    icon: OudsBadgeIcon?,
    modifier: Modifier = Modifier,
    status: OudsBadgeStatus = OudsBadgeDefaults.Status,
    size: OudsBadgeSize = OudsBadgeDefaults.Size
) {
    // This outer box is necessary otherwise the user can change the size of the badge through the modifier
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        // Apply the font scale to the size when displaying a count or an icon badge.
        // This allows to:
        // - get consistent sizes between count and icon badges
        // - get a round badge with low count values. Otherwise, a count badge with an increase text size and a value of 1 will look like a vertical ellipse
        val scale = if (count != null || icon != null) LocalConfiguration.current.fontScale else 1.0f
        val sizeDp = size(size) * scale
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(OudsTheme.borders.radius.pill))
                .background(status.color)
                .run {
                    if (count != null && size in OudsBadgeSize.countEntries) {
                        sizeIn(minWidth = sizeDp, minHeight = sizeDp)
                    } else {
                        size(sizeDp)
                    }
                }
                .semantics(mergeDescendants = true) { }
                .padding(paddingValues = contentPadding(size = size, count = count, icon = icon, scale = scale)),
            contentAlignment = Alignment.Center
        ) {
            val text = count?.let { if (it > OudsBadgeMaxCount) "+${OudsBadgeMaxCount}" else it.coerceAtLeast(0).toString() }
            val contentColor = contentColor(status = status)
            if (size in OudsBadgeSize.countEntries && text != null) {
                val textStyle = textStyle(size = size)
                if (textStyle != null) {
                    Text(
                        text = text,
                        color = contentColor,
                        style = textStyle
                    )
                }
            }
            if (size in OudsBadgeSize.iconEntries) {
                icon?.Content(
                    modifier = Modifier.fillMaxSize(),
                    extraParameters = OudsBadgeIcon.ExtraParameters(tint = contentColor)
                )
            }
        }
    }
}

@Composable
private fun size(size: OudsBadgeSize): Dp {
    return with(OudsTheme.componentsTokens.badge) {
        when (size) {
            OudsBadgeSize.ExtraSmall -> sizeXsmall
            OudsBadgeSize.Small -> sizeSmall
            OudsBadgeSize.Medium -> sizeMedium
            OudsBadgeSize.Large -> sizeLarge
        }.dp
    }
}

@Composable
private fun contentColor(status: OudsBadgeStatus): Color {
    return when (status) {
        OudsBadgeStatus.Neutral -> OudsTheme.colorScheme.content.inverse
        OudsBadgeStatus.Accent -> OudsTheme.colorScheme.content.onStatus.accent.emphasized
        OudsBadgeStatus.Positive -> OudsTheme.colorScheme.content.onStatus.positive.emphasized
        OudsBadgeStatus.Info -> OudsTheme.colorScheme.content.onStatus.info.emphasized
        OudsBadgeStatus.Warning -> OudsTheme.colorScheme.content.onStatus.warning.emphasized
        OudsBadgeStatus.Negative -> OudsTheme.colorScheme.content.onStatus.negative.emphasized
        OudsBadgeStatus.Disabled -> OudsTheme.colorScheme.content.onAction.disabled
    }
}

@Composable
private fun textStyle(size: OudsBadgeSize): TextStyle? {
    return when (size) {
        OudsBadgeSize.ExtraSmall,
        OudsBadgeSize.Small -> null
        OudsBadgeSize.Medium -> OudsTheme.typography.label.default.small
        OudsBadgeSize.Large -> OudsTheme.typography.label.default.medium
    }?.run {
        copy(lineHeightStyle = lineHeightStyle?.copy(alignment = LineHeightStyle.Alignment.Center))
    }
}

@Composable
private fun contentPadding(size: OudsBadgeSize, count: Int?, icon: OudsBadgeIcon?, scale: Float): PaddingValues {
    return with(OudsTheme.componentsTokens.badge) {
        when {
            count != null && size == OudsBadgeSize.Medium -> PaddingValues(horizontal = spacePaddingInlineMedium.value * scale)
            count != null && size == OudsBadgeSize.Large -> PaddingValues(horizontal = spacePaddingInlineLarge.value * scale)
            icon != null && size in OudsBadgeSize.iconEntries -> PaddingValues(all = spaceInset.dp * scale)
            else -> PaddingValues(all = 0.dp)
        }
    }
}

/**
 * Default values for [OudsBadge].
 */
object OudsBadgeDefaults {

    /**
     * Default status of an [OudsBadge].
     */
    val Status = OudsBadgeStatus.Neutral

    /**
     * Default size of an [OudsBadge].
     */
    val Size = OudsBadgeSize.Medium
}

/**
 * The maximum count value.
 */
const val OudsBadgeMaxCount = 99

/**
 * An icon in an [OudsBadge].
 * This icon is non-clickable.
 */
class OudsBadgeIcon private constructor(
    graphicsObject: Any,
    val contentDescription: String
) : OudsComponentIcon<OudsBadgeIcon.ExtraParameters>(ExtraParameters::class.java, graphicsObject, contentDescription) {

    @ConsistentCopyVisibility
    data class ExtraParameters internal constructor(
        internal val tint: Color
    ) : OudsComponentContent.ExtraParameters()

    /**
     * Creates an instance of [OudsBadgeIcon].
     *
     * @param painter Painter of the icon.
     * @param contentDescription The content description associated with this [OudsBadgeIcon]. This value is ignored if the badge also contains label.
     */
    constructor(painter: Painter, contentDescription: String) : this(painter as Any, contentDescription)

    /**
     * Creates an instance of [OudsBadgeIcon].
     *
     * @param imageVector Image vector of the icon.
     * @param contentDescription The content description associated with this [OudsBadgeIcon]. This value is ignored if the badge also contains label.
     */
    constructor(imageVector: ImageVector, contentDescription: String) : this(imageVector as Any, contentDescription)

    /**
     * Creates an instance of [OudsBadgeIcon].
     *
     * @param bitmap Image bitmap of the icon.
     * @param contentDescription The content description associated with this [OudsBadgeIcon]. This value is ignored if the badge also contains label.
     */
    constructor(bitmap: ImageBitmap, contentDescription: String) : this(bitmap as Any, contentDescription)

    override val tint: Color?
        @Composable
        get() = extraParameters.tint
}

/**
 * The status of an [OudsBadge]. This status determines the background and content colors of the badge.
 */
enum class OudsBadgeStatus {

    /** Used for general labels without specific emphasis. */
    Neutral,

    /** Employed to highlight discovery or exploration-related content. */
    Accent,

    /** Indicates success, completion, or approval. */
    Positive,

    /** Provides informational context without urgency. */
    Info,

    /** Negatives the user to potential risks or cautionary messages. */
    Warning,

    /**
     * Draws attention to important or critical information.
     * Often used for errors, restrictions, or urgent messages, but not exclusively for failures.
     */
    Negative,

    /**
     * Indicate when the user isn’t allowed to interact with an element.
     * They remove all interactivity from a text or icon elements.
     */
    Disabled;

    /**
     * The color associated with this status.
     */
    val color: Color
        @Composable
        get() = when (this) {
            Neutral -> OudsTheme.colorScheme.surface.inverseHigh
            Accent -> OudsTheme.colorScheme.surface.status.accent.emphasized
            Positive -> OudsTheme.colorScheme.surface.status.positive.emphasized
            Info -> OudsTheme.colorScheme.surface.status.info.emphasized
            Warning -> OudsTheme.colorScheme.surface.status.warning.emphasized
            Negative -> OudsTheme.colorScheme.surface.status.negative.emphasized
            Disabled -> OudsTheme.colorScheme.action.disabled
        }
}

/**
 * The size of an [OudsBadge].
 */
enum class OudsBadgeSize {

    /** A compact badge for minimal space usage, ideal for small UI elements like icons or tooltips. */
    ExtraSmall,

    /** A slightly larger badge that remains subtle but improves readability, often used for inline labels. */
    Small,

    /** The default size, providing a balance between visibility and space efficiency, suitable for most use cases. */
    Medium,

    /** A prominent badge for drawing more attention, often used in dashboards or highlighted sections. */
    Large;

    internal companion object {

        val countEntries: List<OudsBadgeSize>
            get() = listOf(Medium, Large)

        val iconEntries: List<OudsBadgeSize>
            get() = listOf(Medium, Large)
    }
}

@Preview(name = "Light", widthDp = OudsPreviewableComponent.Badge.PreviewWidthDp)
@Preview(name = "Dark", uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL, widthDp = OudsPreviewableComponent.Badge.PreviewWidthDp)
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsBadge(@PreviewParameter(OudsBadgePreviewParameterProvider::class) parameter: OudsBadgePreviewParameter) {
    PreviewOudsBadge(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsBadge(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: OudsBadgePreviewParameter) =
    OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
        with(parameter) {
            PreviewEnumEntries<OudsBadgeSize, OudsBadgeStatus> { size, status ->
                OudsBadge(
                    count = count,
                    icon = icon?.let {
                        OudsBadgeIcon(
                            imageVector = it,
                            contentDescription = ""
                        )
                    },
                    status = status,
                    size = size
                )
            }
        }
    }

internal data class OudsBadgePreviewParameter(
    val count: Int?,
    val icon: ImageVector?
)

internal class OudsBadgePreviewParameterProvider : BasicPreviewParameterProvider<OudsBadgePreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsBadgePreviewParameter>
    get() = listOf(
        OudsBadgePreviewParameter(null, null),
        OudsBadgePreviewParameter(1, null),
        OudsBadgePreviewParameter(99, null),
        OudsBadgePreviewParameter(100, null),
        OudsBadgePreviewParameter(null, Icons.Filled.FavoriteBorder)
    )

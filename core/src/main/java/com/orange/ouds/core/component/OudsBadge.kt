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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.component.OudsBadge.Status.Accent
import com.orange.ouds.core.component.OudsBadge.Status.Disabled
import com.orange.ouds.core.component.OudsBadge.Status.Info
import com.orange.ouds.core.component.OudsBadge.Status.Negative
import com.orange.ouds.core.component.OudsBadge.Status.Neutral
import com.orange.ouds.core.component.OudsBadge.Status.Positive
import com.orange.ouds.core.component.OudsBadge.Status.Warning
import com.orange.ouds.core.component.content.OudsComponentContent
import com.orange.ouds.core.component.content.OudsComponentIcon
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.PreviewEnumEntries
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider

@Composable
fun OudsBadge(
    modifier: Modifier = Modifier,
    status: OudsBadge.Status = OudsBadgeDefaults.Status,
    size: OudsBadge.Size = OudsBadgeDefaults.Size
) {
    OudsBadge(
        count = null,
        icon = null,
        modifier = modifier,
        status = status,
        size = size
    )
}

@Composable
fun OudsBadge(
    count: Int,
    modifier: Modifier = Modifier,
    status: OudsBadge.Status = OudsBadgeDefaults.Status,
    size: OudsBadge.Size = OudsBadgeDefaults.Size
) {
    OudsBadge(
        count = count,
        icon = null,
        modifier = modifier,
        status = status,
        size = size
    )
}

@Composable
fun OudsBadge(
    icon: OudsBadge.Icon,
    modifier: Modifier = Modifier,
    status: OudsBadge.Status = OudsBadgeDefaults.Status,
    size: OudsBadge.Size = OudsBadgeDefaults.Size
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
    icon: OudsBadge.Icon?,
    modifier: Modifier = Modifier,
    status: OudsBadge.Status = OudsBadgeDefaults.Status,
    size: OudsBadge.Size = OudsBadgeDefaults.Size
) {
    val sizeDp = size(size)
    val maxWidth = if (count != null) Dp.Unspecified else sizeDp
    // This outer box is necessary otherwise the user can change the size of the badge through the modifier
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(OudsTheme.borders.radius.pill))
                .background(backgroundColor(status = status))
                .heightIn(min = sizeDp, max = sizeDp)
                .widthIn(min = sizeDp, max = maxWidth)
                .padding(paddingValues = contentPadding(size = size, count = count, icon = icon)),
            contentAlignment = Alignment.Center
        ) {
            val text = count?.let { if (it > OudsBadge.MaxCount) "+${OudsBadge.MaxCount}" else it.coerceAtLeast(0).toString() }
            val contentColor = contentColor(status = status)
            if (size in OudsBadge.Size.countEntries && text != null) {
                val textStyle = textStyle(size = size)
                if (textStyle != null) {
                    Text(
                        modifier = Modifier,
                        text = text,
                        color = contentColor,
                        style = textStyle
                    )
                }
            }
            if (size in OudsBadge.Size.iconEntries) {
                icon?.Content(
                    extraParameters = OudsBadge.Icon.ExtraParameters(tint = contentColor)
                )
            }
        }
    }
}

@Composable
fun size(size: OudsBadge.Size): Dp {
    return with(OudsTheme.componentsTokens.badge) {
        when (size) {
            OudsBadge.Size.ExtraSmall -> sizeXsmall
            OudsBadge.Size.Small -> sizeSmall
            OudsBadge.Size.Medium -> sizeMedium
            OudsBadge.Size.Large -> sizeLarge
        }.dp
    }
}

@Composable
private fun backgroundColor(status: OudsBadge.Status): Color {
    return when (status) {
        Neutral -> OudsTheme.colorScheme.surface.status.neutral.emphasized
        Accent -> OudsTheme.colorScheme.surface.status.accent.emphasized
        Positive -> OudsTheme.colorScheme.surface.status.positive.emphasized
        Info -> OudsTheme.colorScheme.surface.status.info.emphasized
        Warning -> OudsTheme.colorScheme.surface.status.warning.emphasized
        Negative -> OudsTheme.colorScheme.surface.status.negative.emphasized
        Disabled -> OudsTheme.colorScheme.action.disabled
    }
}

@Composable
private fun contentColor(status: OudsBadge.Status): Color {
    return when (status) {
        Neutral -> OudsTheme.colorScheme.content.onStatus.neutral.emphasized
        Accent -> OudsTheme.colorScheme.content.onStatus.accent.emphasized
        Positive -> OudsTheme.colorScheme.content.onStatus.positive.emphasized
        Info -> OudsTheme.colorScheme.content.onStatus.info.emphasized
        Warning -> OudsTheme.colorScheme.content.onStatus.warning.emphasized
        Negative -> OudsTheme.colorScheme.content.onStatus.negative.emphasized
        Disabled -> OudsTheme.colorScheme.content.onAction.disabled
    }
}

@Composable
private fun textStyle(size: OudsBadge.Size): TextStyle? {
    return when (size) {
        OudsBadge.Size.ExtraSmall,
        OudsBadge.Size.Small -> null
        OudsBadge.Size.Medium -> OudsTheme.typography.label.default.small
        OudsBadge.Size.Large -> OudsTheme.typography.label.default.medium
    }?.run {
        copy(lineHeightStyle = lineHeightStyle?.copy(alignment = LineHeightStyle.Alignment.Center))
    }
}

@Composable
private fun contentPadding(size: OudsBadge.Size, count: Int?, icon: OudsBadge.Icon?): PaddingValues {
    val horizontalPadding = with(OudsTheme.componentsTokens.badge) {
        when {
            count != null && size == OudsBadge.Size.Medium -> spacePaddingInlineMedium.value
            count != null && size == OudsBadge.Size.Large -> spacePaddingInlineLarge.value
            icon != null && size in OudsBadge.Size.iconEntries -> spaceInset.dp
            else -> 0.dp
        }
    }

    return PaddingValues(horizontal = horizontalPadding)
}

/**
 * Default values for [OudsBadge].
 */
object OudsBadgeDefaults {

    /**
     * Default status of an [OudsBadge].
     */
    val Status = OudsBadge.Status.Neutral

    /**
     * Default size of an [OudsBadge].
     */
    val Size = OudsBadge.Size.Medium
}

/**
 * Contains classes to build an [OudsBadge].
 */
object OudsBadge {

    internal const val MaxCount = 99

    /**
     * An icon in an [OudsBadge].
     * This icon is non-clickable.
     */
    class Icon private constructor(
        graphicsObject: Any,
        val contentDescription: String
    ) : OudsComponentIcon<Icon.ExtraParameters>(ExtraParameters::class.java, graphicsObject, contentDescription) {

        @ConsistentCopyVisibility
        data class ExtraParameters internal constructor(
            internal val tint: Color
        ) : OudsComponentContent.ExtraParameters()

        /**
         * Creates an instance of [OudsBadge.Icon].
         *
         * @param painter Painter of the icon.
         * @param contentDescription The content description associated with this [OudsBadge.Icon]. This value is ignored if the chip also contains label.
         */
        constructor(painter: Painter, contentDescription: String) : this(painter as Any, contentDescription)

        /**
         * Creates an instance of [OudsBadge.Icon].
         *
         * @param imageVector Image vector of the icon.
         * @param contentDescription The content description associated with this [OudsBadge.Icon]. This value is ignored if the chip also contains label.
         */
        constructor(imageVector: ImageVector, contentDescription: String) : this(imageVector as Any, contentDescription)

        /**
         * Creates an instance of [OudsBadge.Icon].
         *
         * @param bitmap Image bitmap of the icon.
         * @param contentDescription The content description associated with this [OudsBadge.Icon]. This value is ignored if the chip also contains label.
         */
        constructor(bitmap: ImageBitmap, contentDescription: String) : this(bitmap as Any, contentDescription)

        override val tint: Color?
            @Composable
            get() = extraParameters.tint
    }

    enum class Status {
        Neutral, Accent, Positive, Info, Warning, Negative, Disabled;
    }

    enum class Size {
        ExtraSmall, Small, Medium, Large;

        internal companion object {
            val countEntries: List<Size>
                get() = listOf(Medium, Large)

            val iconEntries: List<Size>
                get() = listOf(Medium, Large)
        }
    }
}

@PreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsBadge(@PreviewParameter(OudsBadgePreviewParameterProvider::class) parameter: OudsBadgePreviewParameter) {
    PreviewOudsBadge(darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsBadge(darkThemeEnabled: Boolean, parameter: OudsBadgePreviewParameter) = OudsPreview(darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        PreviewEnumEntries<OudsBadge.Size> { size ->
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                OudsBadge.Status.entries.forEach { status ->
                    OudsBadge(
                        modifier = Modifier.height(size(OudsBadge.Size.Large)), // User the biggest height to align the badges vertically
                        count = count,
                        icon = icon?.let {
                            OudsBadge.Icon(
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

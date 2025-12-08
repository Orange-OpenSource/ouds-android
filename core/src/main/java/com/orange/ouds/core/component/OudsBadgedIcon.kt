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

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isUnspecified
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.outerBorder
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.theme.OudsThemeContract

/**
 * An OUDS equivalent of the Material [BadgedBox] which is restricted to icons.
 * Please note that the size of the [OudsBadgedIcon] must be explicitly set to the size of the contained icon,
 * otherwise the size of the [OudsBadgedIcon] may be bigger than expected when the badge is displayed outside of the icon.
 *
 * @param badgeContentDescription The badge content description.
 * @param modifier The Modifier to be applied to this [OudsBadgedIcon].
 * @param badgeCount The badge count.
 * @param badgeBorderColor The badge border color.
 * @param badgeMaximumEndOverflow The maximum amount of space that the badge can overflow to the end of the icon.
 *   This allows to constrained the badge layout when space is limited, for instance in an [OudsButton].
 * @param icon The icon to be displayed.
 */
@Composable
internal fun OudsBadgedIcon(
    badgeContentDescription: String,
    modifier: Modifier = Modifier,
    badgeCount: Int? = null,
    badgeBorderColor: Color = Color.Unspecified,
    badgeMaximumEndOverflow: Dp = Dp.Unspecified,
    icon: @Composable BoxScope.() -> Unit
) {
    Box(modifier = modifier) {
        icon()
        val badgeMaximumEndOverflowPx = with(LocalDensity.current) { badgeMaximumEndOverflow.toPx() }
        var badgeSize by remember { mutableStateOf(IntSize.Zero) }
        val badgeModifier = Modifier
            .requiredWidth(IntrinsicSize.Min) // This allows to make the badge bigger than it's parent box if needed
            // We should use the first IntSize parameter of the align lambda below to retrieve the badge size
            // but the value is incorrect and coerced to the parent size when the badge is bigger than it's parent
            // That is why we retrieve the icon badge size using the onSizeChanged modifier
            .onSizeChanged { badgeSize = it }
            .align { _, parentSize, layoutDirection ->
                // Important: hypothesis for the calculations below is that the box content alignment is equal to Alignment.TopStart
                // Note: parentSize is equal to icon size
                if (badgeCount == null) {
                    IntOffset(x = parentSize.width - badgeSize.width, y = 0)
                } else {
                    // Compose layouts the badge differently if its width is bigger than the icon width or not
                    val initialXOffset = if (badgeSize.width > parentSize.width) (parentSize.width - badgeSize.width) / 2 else 0

                    val xOffset = when {
                        // 1. Badge has enough space to be displayed at it's expected location
                        // i.e. start at the horizontal center of the icon and bottom at the vertical center of the icon 
                        badgeMaximumEndOverflow.isUnspecified || (badgeSize.width < parentSize.width / 2 + badgeMaximumEndOverflowPx.toInt()) -> if (layoutDirection == LayoutDirection.Ltr) {
                            -initialXOffset + parentSize.width / 2
                        } else {
                            -initialXOffset + parentSize.width / 2 - badgeSize.width
                        }
                        // 2. Badge does not have enough space to be displayed at it's expected location
                        // There are two cases:
                        // 2.1. Badge width is bigger than the icon width
                        // In that case Compose layouts the badge so that the horizontal center of the badge is the same as the horizontal center of the icon
                        // i.e. The initial offset of the badge is equal to (parentSize.width - badgeSize.width) / 2 in LTR
                        badgeSize.width > parentSize.width -> if (layoutDirection == LayoutDirection.Ltr) {
                            initialXOffset + badgeMaximumEndOverflowPx.toInt()
                        } else {
                            -initialXOffset - badgeMaximumEndOverflowPx.toInt()
                        }
                        // 2.2. Badge width is smaller than the icon width
                        // In that case Compose layouts the badge so that it starts at the start of the icon
                        else -> if (layoutDirection == LayoutDirection.Ltr) {
                            parentSize.width + badgeMaximumEndOverflowPx.toInt() - badgeSize.width
                        } else {
                            -badgeMaximumEndOverflowPx.toInt()
                        }
                    }
                    IntOffset(x = xOffset, y = parentSize.height / 2 - badgeSize.height)
                }
            }
            .outerBorder(1.dp, color = badgeBorderColor, shape = OudsBadgeShape, innerOffsetPx = -1f)
            .semantics {
                contentDescription = badgeContentDescription
            }

        val status = OudsBadgeStatus.Negative // A badge always has a negative status on an icon
        badgeCount?.let {
            OudsBadge(modifier = badgeModifier, count = badgeCount, status = status, size = OudsBadgeSize.Medium)
        }.orElse {
            OudsBadge(modifier = badgeModifier, status = status, size = OudsBadgeSize.ExtraSmall)
        }
    }
}

@PreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsBadgedIcon(@PreviewParameter(OudsBadgedIconPreviewParameterProvider::class) parameter: OudsBadgedIconPreviewParameter) {
    PreviewOudsBadgedIcon(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsBadgedIcon(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsBadgedIconPreviewParameter
) = OudsPreview(theme = theme, modifier = Modifier.padding(8.dp), darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        val size = 26.dp
        OudsBadgedIcon(
            modifier = Modifier.size(size),
            badgeContentDescription = "",
            badgeCount = badgeCount,
            badgeBorderColor = badgeBorderColor,
            badgeMaximumEndOverflow = badgeMaximumEndOverflow
        ) {
            Icon(
                modifier = Modifier.size(size),
                imageVector = Icons.Filled.FavoriteBorder,
                contentDescription = "",
                tint = OudsTheme.colorScheme.content.default
            )
        }
    }
}

internal data class OudsBadgedIconPreviewParameter(
    val badgeCount: Int? = null,
    val badgeBorderColor: Color = Color.Unspecified,
    val badgeMaximumEndOverflow: Dp = Dp.Unspecified
)

internal class OudsBadgedIconPreviewParameterProvider : BasicPreviewParameterProvider<OudsBadgedIconPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsBadgedIconPreviewParameter>
    get() = listOf(
        OudsBadgedIconPreviewParameter(badgeBorderColor = Color.White),
        // Count badges
        OudsBadgedIconPreviewParameter(badgeCount = 1), // Case 1 when computing x offset
        OudsBadgedIconPreviewParameter(badgeCount = 100), // Case 1 when computing x offset
        OudsBadgedIconPreviewParameter(badgeCount = 100, badgeBorderColor = Color.White, badgeMaximumEndOverflow = 8.dp), // Case 2.1 when computing x offset
        OudsBadgedIconPreviewParameter(badgeCount = 20, badgeMaximumEndOverflow = 8.dp), // Case 2.2 when computing x offset
    )

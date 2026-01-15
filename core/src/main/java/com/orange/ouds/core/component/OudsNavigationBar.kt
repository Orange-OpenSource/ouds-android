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
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.NavigationItemIconPosition
import androidx.compose.material3.ShortNavigationBar
import androidx.compose.material3.ShortNavigationBarArrangement
import androidx.compose.material3.ShortNavigationBarDefaults
import androidx.compose.material3.ShortNavigationBarItem
import androidx.compose.material3.ShortNavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.component.content.OudsComponentContent
import com.orange.ouds.core.component.content.OudsComponentIcon
import com.orange.ouds.core.extensions.InteractionState
import com.orange.ouds.core.extensions.collectInteractionStateAsState
import com.orange.ouds.core.extensions.value
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.OudsPreviewableComponent
import com.orange.ouds.core.utilities.PreviewEnumEntries
import com.orange.ouds.core.utilities.getPreviewEnumEntry
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.theme.OudsThemeContract

/**
 * Height of OUDS navigation bar.
 */
val OudsNavigationBarHeight = 80.dp

/**
 * The Navigation bar (aka Bottom navigation bar on Material 2) provides access to an app’s primary destinations using 3 to 5 persistent tabs.
 * Each destination is represented by an icon and optionally a text label. Positioned at the bottom of the screen, it supports quick switching
 * between top-level sections, following Material Design navigation patterns.
 *
 * [OudsNavigationBar] should contain three to five [OudsNavigationBarItem], each representing a singular destination.
 *
 * [OudsNavigationBar] default appearance is opaque but, if you need a **translucent blurred navigation bar** as specified on OUDS design
 * side, you can implement it in your app with the help of [Haze](https://chrisbanes.github.io/haze/latest/) library. To do this, use [OudsNavigationBar] with
 * [translucent] parameter set to true and follow these steps:
 * 1. Add Haze dependency
 * 2. Follow Haze basic usage instructions:
 * - Define Haze state in the screen containing the navigation bar: `val hazeState = rememberHazeState()`
 * - Use `hazeEffect` Modifier on [OudsNavigationBar] providing OUDS blur radius: `Modifier.hazeEffect(state = hazeState, style = HazeStyle(tint = null, blurRadius = OudsTheme.components.bar.blurRadius.dp)),`
 * - Apply `hazeSource` Modifier on the content that scrolls behind the navigation bar: `Modifier.hazeSource(state = hazeState)`
 * 3. As your screen content needs to scroll behind the navigation bar, you'll probably need to add an additional bottom padding
 * that will have the height of [OudsNavigationBar]. For this, please use [OudsNavigationBarHeight] constant.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://r.orange.fr/r/S-ouds-doc-android-navigation-bar)
 *
 * > Design version: 1.0.0
 *
 * @param items List of [OudsNavigationBarItem] to display in the navigation bar.
 * @param modifier [Modifier] applied to the navigation bar.
 * @param translucent Whether the navigation bar should be translucent.
 * @param windowInsets Window insets of the navigation bar.
 * @param arrangement The [ShortNavigationBarArrangement] of this navigation bar.
 *
 * @sample com.orange.ouds.core.component.samples.OudsNavigationBarSample
 * @sample com.orange.ouds.core.component.samples.OudsNavigationBarWithHorizontalItemsSample
 */
@Composable
fun OudsNavigationBar(
    items: List<OudsNavigationBarItem>,
    modifier: Modifier = Modifier,
    translucent: Boolean = false,
    windowInsets: WindowInsets = ShortNavigationBarDefaults.windowInsets,
    arrangement: ShortNavigationBarArrangement = ShortNavigationBarDefaults.arrangement
) {
    with(OudsTheme.componentsTokens.bar) {
        ShortNavigationBar(
            modifier = modifier,
            containerColor = if (translucent) colorBgTranslucent.value else colorBgOpaque.value,
            contentColor = colorContentUnselectedEnabled.value,
            windowInsets = windowInsets,
            arrangement = arrangement,
            content = {
                val topBorderColor = OudsTheme.colorScheme.border.minimal
                Row(
                    modifier = Modifier
                        .drawBehind {
                            val topBorderWidth = 1.dp.toPx()
                            drawLine(
                                color = topBorderColor,
                                start = Offset(x = 0f, y = topBorderWidth / 2f),
                                end = Offset(x = size.width, y = topBorderWidth / 2f),
                                strokeWidth = topBorderWidth
                            )
                        }
                ) {
                    items.forEach { item ->
                        item.Content(
                            modifier = Modifier.weight(1f),
                            extraParameters = OudsNavigationBarItem.ExtraParameters(this)
                        )
                    }
                }
            }
        )
    }
}

/**
 * An OUDS navigation bar item, used in [OudsNavigationBar].
 *
 * The recommended configuration for an [OudsNavigationBarItem] depends on how many items there are inside a [OudsNavigationBar]:
 * - Three destinations: Display icons and text labels for all destinations.
 * - Four destinations: Active destinations display an icon and text label. Inactive destinations display icons, and text labels are recommended.
 * - Five destinations: Active destinations display an icon and text label. Inactive destinations use icons, and use text labels if space permits.
 *
 * [OudsNavigationBarItem] always shows text labels.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://r.orange.fr/r/S-ouds-doc-android-navigation-bar)
 *
 * > Design version: 1.0.0
 *
 * @param selected Whether this item is selected or not.
 * @param onClick Called when this item is clicked.
 * @param icon Icon of the item.
 * @param label Label of the item.
 * @param badge Optional badge displayed on the item icon.
 * @param interactionSource [MutableInteractionSource] that will be used to dispatch events when this item is pressed, hovered or focused.
 * @param iconPosition The [NavigationItemIconPosition] for the icon. By default, the icon is positioned on top of the label.
 *
 * @sample com.orange.ouds.core.component.samples.OudsNavigationBarSample
 */
data class OudsNavigationBarItem(
    val selected: Boolean,
    val onClick: () -> Unit,
    val icon: OudsNavigationBarItemIcon,
    val label: String? = null,
    val badge: OudsNavigationBarItemBadge? = null,
    val interactionSource: MutableInteractionSource? = null,
    val iconPosition: NavigationItemIconPosition = NavigationItemIconPosition.Top
) : OudsComponentContent<OudsNavigationBarItem.ExtraParameters>(ExtraParameters::class.java) {

    @ConsistentCopyVisibility
    data class ExtraParameters internal constructor(
        internal val rowScope: RowScope
    ) : OudsComponentContent.ExtraParameters()

    @Composable
    override fun Content(modifier: Modifier) {
        @Suppress("NAME_SHADOWING") val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
        val interactionState by interactionSource.collectInteractionStateAsState()
        val state = getNavigationBarItemState(interactionState = interactionState)
        with(OudsTheme.componentsTokens.bar) {
            val selectedContentColor = contentColor(state = state, selected = true)
            val unselectedContentColor = contentColor(state = state, selected = false)

            CompositionLocalProvider(LocalRippleConfiguration provides null) {
                ShortNavigationBarItem(
                    modifier = modifier
                        .fillMaxHeight()
                        .indicator(state = state, selected = selected, iconPosition = iconPosition)
                        .semantics {
                            contentDescription = badge?.contentDescription.orEmpty()
                        },
                    selected = selected,
                    onClick = onClick,
                    icon = {
                        if (badge != null) {
                            OudsBadgedIcon(
                                modifier = Modifier.size(OudsNavigationBarItemIcon.Size),
                                badgeCount = badge.count,
                                badgeBorderColor = OudsTheme.componentsTokens.bar.colorBorderBadge.value
                            ) {
                                icon.Content()
                            }
                        } else {
                            icon.Content()
                        }
                    },
                    iconPosition = iconPosition,
                    label = label?.let {
                        {
                            Text(
                                text = it,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = OudsTheme.typography.label.moderate.small
                            )
                        }
                    },
                    colors = ShortNavigationBarItemDefaults.colors(
                        selectedIconColor = selectedContentColor,
                        selectedTextColor = selectedContentColor,
                        selectedIndicatorColor = materialIndicatorColor(state = state, selected = selected),
                        unselectedIconColor = unselectedContentColor,
                        unselectedTextColor = unselectedContentColor,
                    ),
                    interactionSource = interactionSource
                )
            }
        }
    }
}

@Composable
private fun Modifier.indicator(state: OudsNavigationBarItemState, selected: Boolean, iconPosition: NavigationItemIconPosition): Modifier {
    with(OudsTheme.componentsTokens.bar) {
        val indicatorColor = topIndicatorColor(state = state)
        val indicatorBottomCornersRadius = borderRadiusActiveIndicatorCustomTop.value
        val opacityActiveIndicatorCustomValue = opacityActiveIndicatorCustom.value

        // This is the same animation spec that the NavigationBarItem internally uses to animate the color of the icon and the text
        val animationSpec = spring<Float>(
            dampingRatio = 1.0f, // StandardMotionTokens.SpringDefaultEffectsDamping
            stiffness = 1600.0f // StandardMotionTokens.SpringDefaultEffectsStiffness
        )

        val indicatorAnimatedAlpha by animateFloatAsState(
            targetValue = if (selected || state == OudsNavigationBarItemState.Hovered) 1.0f else 0.0f,
            animationSpec = animationSpec,
        )

        return if (indicatorAnimatedAlpha == 0f || opacityActiveIndicatorCustomValue == 0f) {
            this@indicator
        } else {
            drawWithContent {
                val indicatorAlphaColor = indicatorColor.copy(alpha = indicatorColor.alpha * opacityActiveIndicatorCustomValue * indicatorAnimatedAlpha)
                val indicatorHeight = sizeHeightActiveIndicatorCustom.dp.toPx()
                val indicatorWidth: Float
                val indicatorStartXOffset: Float
                val indicatorEndXOffset: Float
                when (iconPosition) {
                    NavigationItemIconPosition.Top -> {
                        indicatorWidth = sizeWidthActiveIndicatorCustomTop.dp.toPx()
                        indicatorStartXOffset = (size.width - indicatorWidth).coerceAtLeast(0f) / 2
                        indicatorEndXOffset = size.width - indicatorStartXOffset
                    }
                    else -> {
                        val horizontalItemIndicatorPaddingStart = 14.dp.toPx() // Constant value defined in Figma
                        val horizontalItemIndicatorPaddingEnd = 10.dp.toPx() // Constant value defined in Figma
                        indicatorWidth = size.width - (horizontalItemIndicatorPaddingStart + horizontalItemIndicatorPaddingEnd)
                        indicatorStartXOffset = horizontalItemIndicatorPaddingStart
                        indicatorEndXOffset = size.width - horizontalItemIndicatorPaddingEnd
                    }
                }

                drawContent()

                if (indicatorBottomCornersRadius > 0.dp) {
                    val bottomCornersRadiusPx = indicatorBottomCornersRadius.toPx()
                    val path = Path().apply {
                        addRoundRect(
                            RoundRect(
                                rect = Rect(offset = Offset(indicatorStartXOffset, 0f), size = Size(indicatorWidth, indicatorHeight)),
                                bottomLeft = CornerRadius(bottomCornersRadiusPx),
                                bottomRight = CornerRadius(bottomCornersRadiusPx)
                            )
                        )
                    }
                    drawPath(path, color = indicatorAlphaColor)
                } else {
                    val lineY = indicatorHeight / 2
                    drawLine(
                        color = indicatorAlphaColor,
                        start = Offset(indicatorStartXOffset, lineY),
                        end = Offset(indicatorEndXOffset, lineY),
                        strokeWidth = indicatorHeight
                    )
                }
            }
        }
    }
}

@Composable
private fun getNavigationBarItemState(interactionState: InteractionState): OudsNavigationBarItemState {
    return getPreviewEnumEntry<OudsNavigationBarItemState>().orElse {
        when (interactionState) {
            InteractionState.Hovered -> OudsNavigationBarItemState.Hovered
            InteractionState.Pressed -> OudsNavigationBarItemState.Pressed
            InteractionState.Focused -> OudsNavigationBarItemState.Focused
            else -> OudsNavigationBarItemState.Enabled
        }
    }
}

@Composable
private fun contentColor(state: OudsNavigationBarItemState, selected: Boolean): Color {
    return with(OudsTheme.componentsTokens.bar) {
        when (state) {
            OudsNavigationBarItemState.Enabled -> if (selected) colorContentSelectedEnabled.value else colorContentUnselectedEnabled.value
            OudsNavigationBarItemState.Hovered -> if (selected) colorContentSelectedHover.value else colorContentUnselectedHover.value
            OudsNavigationBarItemState.Pressed -> if (selected) colorContentSelectedPressed.value else colorContentUnselectedPressed.value
            OudsNavigationBarItemState.Focused -> if (selected) colorContentSelectedFocus.value else colorContentUnselectedFocus.value
        }
    }
}

@Composable
private fun materialIndicatorColor(state: OudsNavigationBarItemState, selected: Boolean): Color {
    return with(OudsTheme.componentsTokens.bar) {
        when (state) {
            OudsNavigationBarItemState.Enabled -> if (selected) colorActiveIndicatorAndroidSelectedEnabled.value else OudsTheme.colorScheme.opacity.transparent
            OudsNavigationBarItemState.Hovered -> if (selected) colorActiveIndicatorAndroidSelectedHover.value else colorActiveIndicatorAndroidUnselectedHover.value
            OudsNavigationBarItemState.Pressed -> if (selected) colorActiveIndicatorAndroidSelectedPressed.value else colorActiveIndicatorAndroidUnselectedPressed.value
            OudsNavigationBarItemState.Focused -> if (selected) colorActiveIndicatorAndroidSelectedFocus.value else colorActiveIndicatorAndroidUnselectedFocus.value
        }
    }
}

@Composable
private fun topIndicatorColor(state: OudsNavigationBarItemState): Color {
    return with(OudsTheme.componentsTokens.bar) {
        when (state) {
            OudsNavigationBarItemState.Enabled -> colorActiveIndicatorCustomSelectedEnabled.value
            OudsNavigationBarItemState.Hovered -> colorActiveIndicatorCustomSelectedHover.value
            OudsNavigationBarItemState.Pressed -> colorActiveIndicatorCustomSelectedPressed.value
            OudsNavigationBarItemState.Focused -> colorActiveIndicatorCustomSelectedFocus.value
        }
    }
}

/**
 * An icon in an [OudsNavigationBarItem].
 */
class OudsNavigationBarItemIcon private constructor(
    graphicsObject: Any,
) : OudsComponentIcon<Nothing, OudsNavigationBarItemIcon>(Nothing::class.java, graphicsObject, "") {

    internal companion object {
        val Size = 24.dp
    }

    /**
     * Creates an instance of [OudsNavigationBarItemIcon].
     *
     * @param painter Painter of the icon.
     */
    constructor(painter: Painter) : this(painter as Any)

    /**
     * Creates an instance of [OudsNavigationBarItemIcon].
     *
     * @param imageVector Image vector of the icon.
     */
    constructor(imageVector: ImageVector) : this(imageVector as Any)

    /**
     * Creates an instance of [OudsNavigationBarItemIcon].
     *
     * @param bitmap Image bitmap of the icon.
     */
    constructor(bitmap: ImageBitmap) : this(bitmap as Any)

    @Composable
    override fun Content(modifier: Modifier) {
        super.Content(modifier = modifier.size(Size))
    }
}

/**
 * A badge in an [OudsNavigationBarItem].
 *
 * @see [OudsBadge]
 *
 * @property contentDescription Content description of the badge, needed for accessibility support (vocalized by Talkback).
 * @property count Optional number displayed in the badge. If not null, the badge has an [OudsBadgeSize.Medium] size. Otherwise, it has an [OudsBadgeSize.ExtraSmall] size.
 */
data class OudsNavigationBarItemBadge(val contentDescription: String, val count: Int? = null)

internal enum class OudsNavigationBarItemState {
    Enabled, Hovered, Pressed, Focused
}

@PreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsNavigationBar(@PreviewParameter(OudsNavigationBarPreviewParameterProvider::class) itemCount: Int) {
    PreviewOudsNavigationBar(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), itemCount = itemCount)
}

@Preview(name = "Light", widthDp = OudsPreviewableComponent.NavigationBar.PreviewWithHorizontalItemsWidthDp)
@Preview(name = "Dark", uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL, widthDp = OudsPreviewableComponent.NavigationBar.PreviewWithHorizontalItemsWidthDp)
@Composable
private fun PreviewOudsNavigationBarWithHorizontalItems(@PreviewParameter(OudsNavigationBarPreviewParameterProvider::class) itemCount: Int) {
    PreviewOudsNavigationBar(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), itemCount = itemCount, horizontalItems = true)
}

@Preview(name = "Light", widthDp = OudsPreviewableComponent.NavigationBarItem.PreviewWidthDp)
@Preview(name = "Dark", uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL, widthDp = OudsPreviewableComponent.NavigationBarItem.PreviewWidthDp)
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsNavigationBarItem(@PreviewParameter(OudsNavigationBarItemPreviewParameterProvider::class) selected: Boolean) {
    PreviewOudsNavigationBarItem(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), selected = selected)
}

@Preview(name = "Light", widthDp = OudsPreviewableComponent.NavigationBarItem.PreviewWidthDp)
@Preview(name = "Dark", uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL, widthDp = OudsPreviewableComponent.NavigationBarItem.PreviewWidthDp)
@Composable
private fun PreviewOudsNavigationBarHorizontalItem(@PreviewParameter(OudsNavigationBarItemPreviewParameterProvider::class) selected: Boolean) {
    PreviewOudsNavigationBarItem(
        theme = getPreviewTheme(),
        darkThemeEnabled = isSystemInDarkTheme(),
        selected = selected,
        iconPosition = NavigationItemIconPosition.Start
    )
}

private data class OudsNavigationBarPreviewItem(
    val imageVector: ImageVector,
    val label: String? = null,
    val badge: OudsNavigationBarItemBadge? = null,
)

private val navigationBarPreviewItems = listOf(
    OudsNavigationBarPreviewItem(
        imageVector = Icons.Default.Call,
        label = "Calls"
    ),
    OudsNavigationBarPreviewItem(
        imageVector = Icons.Default.Email,
        label = "Emails",
        badge = OudsNavigationBarItemBadge(contentDescription = "24 unread emails", count = 24)
    ),
    OudsNavigationBarPreviewItem(
        imageVector = Icons.Default.DateRange,
        label = "Agenda",
        badge = OudsNavigationBarItemBadge("Unread notifications")
    ),
    OudsNavigationBarPreviewItem(
        imageVector = Icons.Default.AccountCircle,
        label = "Account",
    ),
    OudsNavigationBarPreviewItem(
        imageVector = Icons.Default.Settings,
        label = "Settings"
    ),
)

@Composable
internal fun PreviewOudsNavigationBar(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    itemCount: Int,
    horizontalItems: Boolean = false
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    OudsNavigationBar(
        arrangement = if (horizontalItems) ShortNavigationBarArrangement.Centered else ShortNavigationBarArrangement.EqualWeight,
        items = navigationBarPreviewItems.take(itemCount).mapIndexed { index, item ->
            OudsNavigationBarItem(
                selected = index == 0,
                onClick = {},
                icon = OudsNavigationBarItemIcon(imageVector = item.imageVector),
                iconPosition = if (horizontalItems) NavigationItemIconPosition.Start else NavigationItemIconPosition.Top,
                label = item.label,
                badge = item.badge
            )
        }
    )
}

@Composable
internal fun PreviewOudsNavigationBarItem(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    selected: Boolean,
    iconPosition: NavigationItemIconPosition = NavigationItemIconPosition.Top
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    Row {
        val item = OudsNavigationBarItem(
            selected = selected,
            onClick = {},
            icon = OudsNavigationBarItemIcon(imageVector = Icons.Default.Star),
            iconPosition = iconPosition,
            label = "Label"
        )
        PreviewEnumEntries<OudsNavigationBarItemState> {
            item.Content(
                modifier = Modifier
                    .size(width = 80.dp, height = 64.dp)
                    .background(OudsTheme.componentsTokens.bar.colorBgOpaque.value),
                extraParameters = OudsNavigationBarItem.ExtraParameters(this)
            )
        }
    }
}

internal class OudsNavigationBarItemPreviewParameterProvider : BasicPreviewParameterProvider<Boolean>(true, false)

internal class OudsNavigationBarPreviewParameterProvider : BasicPreviewParameterProvider<Int>(3, 5)
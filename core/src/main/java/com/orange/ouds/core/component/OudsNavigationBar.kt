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

import android.os.Build
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
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

//TODO add DSM link when available:  * <a href="https://unified-design-system.orange.com/472794e18/p/31c33b-link" class="external" target="_blank">**OUDS Navigation bar design guidelines**</a>
/**
 * The navigation bar lets people switch between UI views on smaller devices.
 * It offers a persistent and convenient way to switch between primary destinations in an app.
 *
 * [OudsNavigationBar] should contain three to five [OudsNavigationBarItem], each representing a singular destination.
 *
 * [OudsNavigationBar] default appearance is opaque but, if you need a **translucent blurred navigation bar** (supported from Android 13) as specified on OUDS design
 * side, you can implement it in your app with the help of [Haze](https://chrisbanes.github.io/haze/latest/) library. To do this, use [OudsNavigationBar] with
 * [translucent] parameter set to true and follow these steps:
 * 1. Add Haze dependency
 * 2. Follow Haze basic usage instructions:
 * - Define Haze state in the screen containing the navigation bar: `val hazeState = rememberHazeState()`
 * - Use `hazeEffect` Modifier on [OudsNavigationBar] providing OUDS blur radius: `Modifier.hazeEffect(state = hazeState, style = HazeStyle(tint = null, blurRadius = OudsTheme.components.navigationBar.blurRadius.dp)),`
 * - Apply `hazeSource` Modifier on the content that scrolls behind the navigation bar: `Modifier.hazeSource(state = hazeState)`
 * 3. As your screen content needs to scroll behind the navigation bar, you'll probably need to adjust paddings and to add a spacer at the end of the screen
 * content that will have the height of [OudsNavigationBar]. For this, please use [OudsNavigationBarHeight] constant.
 *
 * @param items List of [OudsNavigationBarItem] to display in the navigation bar.
 * @param modifier [Modifier] applied to the navigation bar.
 * @param translucent Whether the navigation bar should be translucent.
 * @param windowInsets Window insets of the navigation bar.
 *
 * @sample com.orange.ouds.core.component.samples.OudsNavigationBarSample
 */
@Composable
fun OudsNavigationBar(
    items: List<OudsNavigationBarItem>,
    modifier: Modifier = Modifier,
    translucent: Boolean = false,
    windowInsets: WindowInsets = NavigationBarDefaults.windowInsets
) {
    with(OudsTheme.componentsTokens.bar) {
        NavigationBar(
            modifier = modifier,
            containerColor = Color.Transparent, // Background color is handled by the Row in content
            contentColor = colorContentUnselectedEnabled.value,
            windowInsets = windowInsets,
            content = {
                val topBorderColor = OudsTheme.colorScheme.border.minimal
                Row(
                    modifier
                        .background(color = if (translucent && Build.VERSION.SDK_INT > Build.VERSION_CODES.S_V2) colorBgTranslucent.value else colorBgOpaque.value)
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
 * @param selected Whether this item is selected or not.
 * @param onClick Called when this item is clicked.
 * @param icon Icon of the item.
 * @param label Label of the item.
 * @param badge Optional badge display on the item icon.
 * @param interactionSource [MutableInteractionSource] that will be used to dispatch events when this item is pressed, hovered or focused.
 *
 * @sample com.orange.ouds.core.component.samples.OudsNavigationBarSample
 */
data class OudsNavigationBarItem(
    val selected: Boolean,
    val onClick: () -> Unit,
    val icon: OudsNavigationBarItemIcon,
    val label: String? = null,
    val badge: OudsNavigationBarItemBadge? = null,
    val interactionSource: MutableInteractionSource? = null
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

            ConstraintLayout(
                modifier = modifier
                    .selectable(
                        selected = selected,
                        onClick = { },
                        role = Role.Tab
                    )
            ) {
                val (itemRef, topIndicatorRef) = createRefs()

                // Top active indicator: visual alternative for selected item
                val topIndicatorColor = topIndicatorColor(state = state)
                val topIndicatorShape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = borderRadiusActiveIndicatorCustomTop.value,
                    bottomEnd = borderRadiusActiveIndicatorCustomTop.value
                )

                // This is the same animation spec that the NavigationBarItem internally uses to animate the color of the icon and the text
                val animationSpec = spring<Float>(
                    dampingRatio = 1.0f, // StandardMotionTokens.SpringDefaultEffectsDamping
                    stiffness = 1600.0f // StandardMotionTokens.SpringDefaultEffectsStiffness
                )

                extraParameters.rowScope.AnimatedVisibility(
                    modifier = Modifier.constrainAs(topIndicatorRef) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        height = Dimension.value(sizeHeightActiveIndicatorCustom.dp)
                        width = Dimension.value(sizeWidthActiveIndicatorCustomTop.dp)
                    },
                    visible = selected || state == OudsNavigationBarItemState.Hovered,
                    enter = fadeIn(animationSpec),
                    exit = fadeOut(animationSpec)
                ) {
                    Box(
                        modifier = Modifier
                            .alpha(opacityActiveIndicatorCustom.value)
                            .background(color = topIndicatorColor, shape = topIndicatorShape)
                    )
                }

                CompositionLocalProvider(LocalRippleConfiguration provides null) {
                    extraParameters.rowScope.NavigationBarItem(
                        selected = selected,
                        onClick = onClick,
                        icon = {
                            if (badge != null) {
                                BadgedBox(
                                    badge = { badge.Content() },
                                ) {
                                    icon.Content()
                                }
                            } else {
                                icon.Content()
                            }
                        },
                        modifier = Modifier
                            .constrainAs(itemRef) {
                                centerTo(parent)
                            }
                            .semantics {
                                contentDescription = badge?.contentDescription.orEmpty()
                            },
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
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = selectedContentColor,
                            selectedTextColor = selectedContentColor,
                            indicatorColor = materialIndicatorColor(state = state, selected = selected),
                            unselectedIconColor = unselectedContentColor,
                            unselectedTextColor = unselectedContentColor,
                        ),
                        interactionSource = interactionSource
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
class OudsNavigationBarItemBadge(val contentDescription: String, val count: Int? = null) : OudsComponentContent<Nothing>(Nothing::class.java) {

    @Composable
    override fun Content(modifier: Modifier) {
        val status = OudsBadgeStatus.Negative // In a navigation bar a badge has always a negative status.
        val positionModifier = if (count != null) {
            Modifier
        } else {
            val startPosition = OudsNavigationBarItemIcon.Size / 2
            val badgeSize = OudsTheme.componentsTokens.badge.sizeXsmall.dp
            val xOffset = startPosition - badgeSize
            val yOffset = (startPosition - badgeSize) + 2.dp
            Modifier.offset(x = xOffset, y = -yOffset)
        }

        Box(
            modifier = positionModifier
                .background(color = OudsTheme.componentsTokens.bar.colorBorderBadge.value, shape = OudsBadgeShape)
                .padding(1.dp)
        ) {
            count?.let {
                OudsBadge(count = count, modifier = modifier, status = status, size = OudsBadgeSize.Medium)
            }.orElse {
                OudsBadge(modifier = modifier, status = status, size = OudsBadgeSize.ExtraSmall)
            }
        }
    }
}

internal enum class OudsNavigationBarItemState {
    Enabled, Hovered, Pressed, Focused
}

@PreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
internal fun PreviewOudsNavigationBar(@PreviewParameter(OudsNavigationBarPreviewParameterProvider::class) itemCount: Int) {
    PreviewOudsNavigationBar(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), itemCount = itemCount)
}

@Preview(name = "Light", widthDp = OudsPreviewableComponent.NavigationBarItem.PreviewWidthDp)
@Preview(name = "Dark", widthDp = OudsPreviewableComponent.NavigationBarItem.PreviewWidthDp)
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
internal fun PreviewOudsNavigationBarItem(@PreviewParameter(OudsNavigationBarItemPreviewParameterProvider::class) selected: Boolean) {
    PreviewOudsNavigationBarItem(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), selected = selected)
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
    itemCount: Int
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    OudsNavigationBar(
        items = navigationBarPreviewItems.take(itemCount).mapIndexed { index, item ->
            OudsNavigationBarItem(
                selected = index == 0,
                onClick = {},
                icon = OudsNavigationBarItemIcon(imageVector = item.imageVector),
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
    selected: Boolean
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    Row {
        val item = OudsNavigationBarItem(
            selected = selected,
            onClick = {},
            icon = OudsNavigationBarItemIcon(imageVector = Icons.Default.Star),
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
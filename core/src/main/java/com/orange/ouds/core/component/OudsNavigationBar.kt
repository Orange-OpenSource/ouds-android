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

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.offset
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
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.orange.ouds.core.component.content.OudsComponentContent
import com.orange.ouds.core.component.content.OudsComponentIcon
import com.orange.ouds.core.extensions.InteractionState
import com.orange.ouds.core.extensions.collectInteractionStateAsState
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.PreviewEnumEntries
import com.orange.ouds.core.utilities.getPreviewEnumEntry
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.theme.OudsThemeContract

//TODO add DSM link when available:  * <a href="https://unified-design-system.orange.com/472794e18/p/31c33b-link" class="external" target="_blank">**OUDS Navigation bar design guidelines**</a>
/**
 * Navigation bars let people switch between UI views on smaller devices.
 * They offer a persistent and convenient way to switch between primary destinations in an app.
 *
 * OudsNavigationBar should contain three to five [OudsNavigationBarItem], each representing a singular destination.
 *
 * @param modifier [Modifier] applied to the navigation bar.
 * @param windowInsets Window insets of the navigation bar.
 * @param content Content of the navigation bar.
 *
 * @sample com.orange.ouds.core.component.samples.OudsNavigationBarSample
 */
@Composable
fun OudsNavigationBar(
    modifier: Modifier = Modifier,
    windowInsets: WindowInsets = NavigationBarDefaults.windowInsets,
    content: @Composable RowScope.() -> Unit
) {
    with(OudsTheme.componentsTokens.navigationBar) {
        NavigationBar(
            modifier = modifier.focusProperties { canFocus = false },
            containerColor = colorBg.value,
            contentColor = colorContentUnselectedEnabled.value,
            windowInsets = windowInsets,
            content = content
        )
    }
}

/**
 * An OUDS navigation bar item.
 *
 * It must be used in [OudsNavigationBar].
 *
 * The recommended configuration for an [OudsNavigationBarItem] depends on how many items there are inside a [OudsNavigationBarItem]:
 * - Three destinations: Display icons and text labels for all destinations.
 * - Four destinations: Active destinations display an icon and text label. Inactive destinations display icons, and text labels are recommended.
 * - Five destinations: Active destinations display an icon and text label. Inactive destinations use icons, and use text labels if space permits.
 *
 * A [OudsNavigationBarItem] always shows text labels (if it exists) when selected. Showing text labels if not selected is controlled by [alwaysShowLabel].
 *
 * @param selected Whether this item is selected or not.
 * @param onClick Called when this item is clicked.
 * @param icon Icon of the item.
 * @param modifier [Modifier] applied to the navigation bar item.
 * @param label Label of the item.
 * @param enabled Controls the enabled state of the item. When `false`, the item will not be clickable.
 * @param badge Optional badge display on the item icon.
 * @param alwaysShowLabel Whether the label should always be shown.
 * @param interactionSource [MutableInteractionSource] that will be used to dispatch events when this item is pressed, hovered or focused.
 */
@Composable
fun RowScope.OudsNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: OudsNavigationBarItem.Icon,
    modifier: Modifier = Modifier,
    label: String? = null,
    enabled: Boolean = true,
    badge: OudsNavigationBarItem.Badge? = null,
    alwaysShowLabel: Boolean = true,
    interactionSource: MutableInteractionSource? = null
) {
    @Suppress("NAME_SHADOWING") val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    val interactionState by interactionSource.collectInteractionStateAsState()
    val state = getNavigationBarItemState(enabled = enabled, interactionState = interactionState)
    with(OudsTheme.componentsTokens.navigationBar) {
        val selectedContentColor = contentColor(state = state, selected = true)
        val unselectedContentColor = contentColor(state = state, selected = false)
        val disabledContentColor = contentColor(state = OudsNavigationBarItem.State.Disabled, selected = false)

        ConstraintLayout(
            modifier = modifier
                .selectable(
                    selected = selected,
                    onClick = { },
                    enabled = enabled,
                    role = Role.Tab
                )
        ) {
            val (itemRef, topIndicatorRef) = createRefs()

            // Top active indicator: visual alternative for selected item (a11y)
            val topIndicatorColor = contentColor(state = state, selected = selected)
            val topIndicatorOpacityColor = topIndicatorColor.copy(alpha = topIndicatorColor.alpha * topActiveIndicatorOpacity.value)
            val topIndicatorShape = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 0.dp,
                bottomStart = topActiveIndicatorBorderRadiusBottom.value,
                bottomEnd = topActiveIndicatorBorderRadiusBottom.value
            )
            this@OudsNavigationBarItem.AnimatedVisibility(
                modifier = Modifier.constrainAs(topIndicatorRef) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.value(topActiveIndicatorSizeHeight.dp)
                    width = Dimension.value(topActiveIndicatorSizeWidth.dp)
                },
                visible = selected || state == OudsNavigationBarItem.State.Hovered,
                enter = fadeIn() + slideInVertically { -it * 2 },
                exit = fadeOut() + slideOutVertically { -it * 2 }
            ) {
                Box(modifier = Modifier.background(color = topIndicatorOpacityColor, shape = topIndicatorShape))
            }

            this@OudsNavigationBarItem.NavigationBarItem(
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
                modifier = Modifier.constrainAs(itemRef) {
                    centerTo(parent)
                },
                enabled = enabled,
                label = label?.let {
                    {
                        Text(
                            text = it,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = OudsTheme.typography.label.default.small
                        )
                    }
                },
                alwaysShowLabel = alwaysShowLabel,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = selectedContentColor,
                    selectedTextColor = selectedContentColor,
                    indicatorColor = materialIndicatorColor(state = state, selected = selected),
                    unselectedIconColor = unselectedContentColor,
                    unselectedTextColor = unselectedContentColor,
                    disabledIconColor = disabledContentColor,
                    disabledTextColor = disabledContentColor,
                ),
                interactionSource = interactionSource
            )
        }
    }
}

@Composable
private fun getNavigationBarItemState(enabled: Boolean, interactionState: InteractionState): OudsNavigationBarItem.State {
    return getPreviewEnumEntry<OudsNavigationBarItem.State>().orElse {
        when {
            !enabled -> OudsNavigationBarItem.State.Disabled
            interactionState == InteractionState.Hovered -> OudsNavigationBarItem.State.Hovered
            interactionState == InteractionState.Pressed -> OudsNavigationBarItem.State.Pressed
            interactionState == InteractionState.Focused -> OudsNavigationBarItem.State.Focused
            else -> OudsNavigationBarItem.State.Enabled
        }
    }
}

@Composable
private fun contentColor(state: OudsNavigationBarItem.State, selected: Boolean): Color {
    return with(OudsTheme.componentsTokens.navigationBar) {
        when (state) {
            OudsNavigationBarItem.State.Enabled -> if (selected) colorContentSelectedEnabled else colorContentUnselectedEnabled
            OudsNavigationBarItem.State.Hovered -> if (selected) colorContentSelectedHover else colorContentUnselectedHover
            OudsNavigationBarItem.State.Pressed -> if (selected) colorContentSelectedPressed else colorContentUnselectedPressed
            OudsNavigationBarItem.State.Disabled -> colorContentUnselectedDisabled
            OudsNavigationBarItem.State.Focused -> if (selected) colorContentSelectedFocus else colorContentUnselectedFocus
        }.value
    }
}

@Composable
private fun materialIndicatorColor(state: OudsNavigationBarItem.State, selected: Boolean): Color {
    return with(OudsTheme.componentsTokens.navigationBar) {
        when (state) {
            OudsNavigationBarItem.State.Enabled -> if (selected) materialActiveIndicatorColorBgSelectedEnabled.value else OudsTheme.colorScheme.opacity.transparent
            OudsNavigationBarItem.State.Hovered -> if (selected) materialActiveIndicatorColorBgSelectedHover.value else materialActiveIndicatorColorBgUnselectedHover.value
            OudsNavigationBarItem.State.Pressed -> if (selected) materialActiveIndicatorColorBgSelectedPressed.value else materialActiveIndicatorColorBgUnselectedPressed.value
            OudsNavigationBarItem.State.Disabled -> OudsTheme.colorScheme.opacity.transparent
            OudsNavigationBarItem.State.Focused -> if (selected) materialActiveIndicatorColorBgSelectedFocus.value else materialActiveIndicatorColorBgUnselectedFocus.value
        }
    }
}

/**
 * Contains classes to build an [OudsNavigationBarItem].
 */
object OudsNavigationBarItem {

    internal enum class State {
        Enabled, Hovered, Pressed, Disabled, Focused
    }

    /**
     * An icon in an [OudsNavigationBarItem].
     * To be accessible, its [contentDescription] should not be empty if there is no label for the item or if `alwaysShowLabel` is set to false.
     */
    class Icon private constructor(
        graphicsObject: Any,
        val contentDescription: String
    ) : OudsComponentIcon<Nothing>(Nothing::class.java, graphicsObject, contentDescription) {

        companion object {
            val Size = 24.dp
        }

        /**
         * Creates an instance of [OudsNavigationBarItem.Icon].
         *
         * @param painter Painter of the icon.
         * @param contentDescription The content description associated with the [OudsNavigationBarItem.Icon].
         */
        constructor(painter: Painter, contentDescription: String) : this(painter as Any, contentDescription)

        /**
         * Creates an instance of [OudsNavigationBarItem.Icon].
         *
         * @param imageVector Image vector of the icon.
         * @param contentDescription The content description associated with the [OudsNavigationBarItem.Icon].
         */
        constructor(imageVector: ImageVector, contentDescription: String) : this(imageVector as Any, contentDescription)

        /**
         * Creates an instance of [OudsNavigationBarItem.Icon].
         *
         * @param bitmap Image bitmap of the icon.
         * @param contentDescription The content description associated with the [OudsNavigationBarItem.Icon].
         */
        constructor(bitmap: ImageBitmap, contentDescription: String) : this(bitmap as Any, contentDescription)
    }

    /**
     * A badge in an [OudsNavigationBarItem].
     *
     * @see [OudsBadge]
     *
     * @property count Optional number displayed in the badge. If not null, the badge has an [OudsBadge.Size.Medium] size. Otherwise, it has an [OudsBadge.Size.Small] size.
     */
    class Badge(val count: Int? = null) : OudsComponentContent<Nothing>(Nothing::class.java) {

        /**
         * Status of the badge.
         * In a navigation bar it has always a negative status.
         */
        private val status = OudsBadge.Status.Negative

        @Composable
        override fun Content(modifier: Modifier) {
            if (count != null) {
                OudsBadge(count = count, modifier = modifier, status = this.status, size = OudsBadge.Size.Medium)
            } else {
                val startPosition = Icon.Size / 2
                val badgeSize = OudsTheme.componentsTokens.badge.sizeXsmall.dp
                val xOffset = startPosition - badgeSize
                val yOffset = (startPosition - badgeSize) + 2.dp
                OudsBadge(modifier = modifier.offset(x = xOffset, y = -yOffset), status = this.status, size = OudsBadge.Size.ExtraSmall)
            }
        }

    }
}

@PreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
internal fun PreviewOudsNavigationBar(@PreviewParameter(OudsNavigationBarPreviewParameterProvider::class) parameter: OudsNavigationBarPreviewParameter) {
    PreviewOudsNavigationBar(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@PreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
internal fun PreviewOudsNavigationBarItem(@PreviewParameter(OudsNavigationBarItemPreviewParameterProvider::class) parameter: OudsNavigationBarItemPreviewParameter) {
    PreviewOudsNavigationBarItem(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

private data class OudsNavigationBarPreviewItem(
    val imageVector: ImageVector,
    val label: String? = null,
    val badge: OudsNavigationBarItem.Badge? = null,
    val enabled: Boolean = true
)

private val navigationBarPreviewItems = listOf(
    OudsNavigationBarPreviewItem(
        imageVector = Icons.Default.Call,
        label = "Calls"
    ),
    OudsNavigationBarPreviewItem(
        imageVector = Icons.Default.Email,
        label = "Emails",
        badge = OudsNavigationBarItem.Badge(count = 24)
    ),
    OudsNavigationBarPreviewItem(
        imageVector = Icons.Default.DateRange,
        label = "Agenda",
        badge = OudsNavigationBarItem.Badge()
    ),
    OudsNavigationBarPreviewItem(
        imageVector = Icons.Default.AccountCircle,
        label = "Account",
        enabled = false
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
    parameter: OudsNavigationBarPreviewParameter
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        OudsNavigationBar(
            content = {
                navigationBarPreviewItems.subList(0, navigationBarItemCount).forEachIndexed { index, item ->
                    OudsNavigationBarItem(
                        modifier = Modifier.weight(1f),
                        selected = index == 0,
                        onClick = {},
                        icon = OudsNavigationBarItem.Icon(imageVector = item.imageVector, contentDescription = ""),
                        label = item.label,
                        badge = item.badge,
                        enabled = item.enabled,
                        alwaysShowLabel = alwaysShowLabel
                    )
                }
            }
        )
    }
}

@Composable
internal fun PreviewOudsNavigationBarItem(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsNavigationBarItemPreviewParameter
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        Row {
            PreviewEnumEntries<OudsNavigationBarItem.State>(columnCount = 2) {
                OudsNavigationBarItem(
                    modifier = Modifier
                        .size(width = 80.dp, height = 64.dp)
                        .background(OudsTheme.componentsTokens.navigationBar.colorBg.value),
                    selected = selected,
                    onClick = {},
                    icon = OudsNavigationBarItem.Icon(imageVector = Icons.Default.Star, contentDescription = ""),
                    label = "Label",
                    enabled = true,
                    alwaysShowLabel = true
                )
            }
        }
    }
}

internal data class OudsNavigationBarPreviewParameter(
    val navigationBarItemCount: Int = 3,
    val alwaysShowLabel: Boolean = true
)

internal data class OudsNavigationBarItemPreviewParameter(
    val selected: Boolean
)

internal class OudsNavigationBarPreviewParameterProvider :
    BasicPreviewParameterProvider<OudsNavigationBarPreviewParameter>(*previewParameterValues.toTypedArray())

internal class OudsNavigationBarItemPreviewParameterProvider :
    BasicPreviewParameterProvider<OudsNavigationBarItemPreviewParameter>(*itemPreviewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsNavigationBarPreviewParameter>
    get() = listOf(
        OudsNavigationBarPreviewParameter(),
        OudsNavigationBarPreviewParameter(navigationBarItemCount = 5, alwaysShowLabel = false),
    )

private val itemPreviewParameterValues: List<OudsNavigationBarItemPreviewParameter>
    get() = listOf(
        OudsNavigationBarItemPreviewParameter(true),
        OudsNavigationBarItemPreviewParameter(false),
    )
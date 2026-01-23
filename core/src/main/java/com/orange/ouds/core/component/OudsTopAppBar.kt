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

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.R
import com.orange.ouds.core.component.content.OudsComponentContent
import com.orange.ouds.core.component.content.OudsComponentIcon
import com.orange.ouds.core.component.content.OudsPolymorphicComponentContent
import com.orange.ouds.core.component.content.PolymorphicContent
import com.orange.ouds.core.extensions.value
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.PreviewCheckerboardPainter
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.theme.OudsThemeContract

/**
 * App bar (aka Top app bar on Material 2) is a top-aligned component that displays the screen title and provides access to key actions and navigation
 * elements. It may include an Up button, action icons, overflow menus, search, or tabs. It provides context and controls relevant to the current view.
 *
 * This small top app bar has slots for a title, navigation icon, and actions.
 *
 * [OudsTopAppBar] default appearance is opaque but, if you need a **translucent blurred top app bar** as specified on OUDS design
 * side, you can implement it in your app with the help of [Haze](https://chrisbanes.github.io/haze/latest/) library. To do this, use [OudsTopAppBar] with
 * [translucent] parameter set to true and follow these steps:
 * 1. Add Haze dependency
 * 2. Follow Haze basic usage instructions:
 * - Define Haze state in the screen containing the top app bar: `val hazeState = rememberHazeState()`
 * - Use `hazeEffect` Modifier on [OudsTopAppBar] providing OUDS blur radius: `Modifier.hazeEffect(state = hazeState, style = HazeStyle(tint = null, blurRadius = OudsTheme.components.bar.blurRadius.dp)),`
 * - Apply `hazeSource` Modifier on the content that scrolls behind the top app bar: `Modifier.hazeSource(state = hazeState)`
 * 3. As your screen content needs to scroll behind the top app bar, you'll probably need to add an additional bottom padding
 * that will have the height of [OudsTopAppBar].
 *
 * > Design guidelines: [unified-design-system.orange.com](https://r.orange.fr/r/S-ouds-doc-android-app-bar)
 *
 * > Design version: 1.0.0
 *
 * @param title The title to be displayed in the top app bar.
 * @param modifier The [Modifier] to be applied to this top app bar.
 * @param translucent Whether the top app bar should be translucent.
 * @param navigationIcon The navigation icon displayed at the start of the top app bar.
 * @param actions The actions displayed at the end of the top app bar. These can be
 *   instances of [OudsTopAppBarAction.Icon] or [OudsTopAppBarAction.Avatar].
 *   The default layout here is a [Row], so actions will be placed horizontally.
 *   The maximum recommended number of actions is three. Please use a dropdown menu if you need more than three actions.
 * @param expandedHeight This app bar's height. When a specified [scrollBehavior] causes the app bar
 *   to collapse or expand, this value will represent the maximum height that the bar will be
 *   allowed to expand. This value must be specified and finite, otherwise it will be ignored and
 *   replaced with [TopAppBarDefaults.TopAppBarExpandedHeight].
 * @param windowInsets A window insets that app bar will respect.
 * @param scrollBehavior A [TopAppBarScrollBehavior] which holds various offset values that will be
 *   applied by this top app bar to set up its height and colors. A scroll behavior is designed to
 *   work in conjunction with a scrolled content to change the top app bar appearance as the content
 *   scrolls. See [TopAppBarScrollBehavior.nestedScrollConnection].
 *
 * @sample com.orange.ouds.core.component.samples.OudsTopAppBarSample
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OudsTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    translucent: Boolean = false,
    navigationIcon: OudsTopAppBarNavigationIcon? = null,
    actions: List<OudsTopAppBarAction> = emptyList(),
    expandedHeight: Dp = TopAppBarDefaults.TopAppBarExpandedHeight,
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    TopAppBar(
        title = {
            Title(
                title = title,
                topAppBarSize = OudsTopAppBarSize.Small,
                centerAligned = false
            )
        },
        modifier = modifier.bottomBorder(),
        navigationIcon = { navigationIcon?.Content() },
        actions = { actions.forEach { it.PolymorphicContent() } },
        expandedHeight = expandedHeight,
        windowInsets = windowInsets,
        colors = colors(translucent = translucent),
        scrollBehavior = scrollBehavior
    )
}

/**
 * App bar (aka Top app bar on Material 2) is a top-aligned component that displays the screen title and provides access to key actions and navigation
 * elements. It may include an Up button, action icons, overflow menus, search, or tabs. It provides context and controls relevant to the current view.
 *
 * This small top app bar has slots for a title that is horizontally aligned to the center, navigation icon, and actions.
 *
 * [OudsCenterAlignedTopAppBar] default appearance is opaque but, if you need a **translucent blurred top app bar** as specified on OUDS design
 * side, you can implement it in your app with the help of [Haze](https://chrisbanes.github.io/haze/latest/) library. To do this, use [OudsCenterAlignedTopAppBar] with
 * [translucent] parameter set to true and follow these steps:
 * 1. Add Haze dependency
 * 2. Follow Haze basic usage instructions:
 * - Define Haze state in the screen containing the top app bar: `val hazeState = rememberHazeState()`
 * - Use `hazeEffect` Modifier on [OudsCenterAlignedTopAppBar] providing OUDS blur radius: `Modifier.hazeEffect(state = hazeState, style = HazeStyle(tint = null, blurRadius = OudsTheme.components.bar.blurRadius.dp)),`
 * - Apply `hazeSource` Modifier on the content that scrolls behind the top app bar: `Modifier.hazeSource(state = hazeState)`
 * 3. As your screen content needs to scroll behind the top app bar, you'll probably need to add an additional bottom padding
 * that will have the height of [OudsCenterAlignedTopAppBar].
 *
 * > Design guidelines: [unified-design-system.orange.com](https://r.orange.fr/r/S-ouds-doc-android-app-bar)
 *
 * > Design version: 1.0.0
 *
 * @param title The title to be displayed in the top app bar.
 * @param modifier The [Modifier] to be applied to this top app bar.
 * @param translucent Whether the top app bar should be translucent.
 * @param navigationIcon The navigation icon displayed at the start of the top app bar.
 * @param actions The actions displayed at the end of the top app bar. These can be
 *   instances of [OudsTopAppBarAction.Icon] or [OudsTopAppBarAction.Avatar].
 *   The default layout here is a [Row], so actions will be placed horizontally.
 *   The maximum recommended number of actions is three. Please use a dropdown menu if you need more than three actions.
 * @param expandedHeight This app bar's height. When a specified [scrollBehavior] causes the app bar
 *   to collapse or expand, this value will represent the maximum height that the bar will be
 *   allowed to expand. This value must be specified and finite, otherwise it will be ignored and
 *   replaced with [TopAppBarDefaults.TopAppBarExpandedHeight].
 * @param windowInsets A window insets that app bar will respect.
 * @param scrollBehavior A [TopAppBarScrollBehavior] which holds various offset values that will be
 *   applied by this top app bar to set up its height and colors. A scroll behavior is designed to
 *   work in conjunction with a scrolled content to change the top app bar appearance as the content
 *   scrolls. See [TopAppBarScrollBehavior.nestedScrollConnection].
 *
 * @sample com.orange.ouds.core.component.samples.OudsCenterAlignedTopAppBarSample
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OudsCenterAlignedTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    translucent: Boolean = false,
    navigationIcon: OudsTopAppBarNavigationIcon? = null,
    actions: List<OudsTopAppBarAction> = emptyList(),
    expandedHeight: Dp = TopAppBarDefaults.TopAppBarExpandedHeight,
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    CenterAlignedTopAppBar(
        title = {
            Title(
                title = title,
                topAppBarSize = OudsTopAppBarSize.Small,
                centerAligned = true
            )
        },
        modifier = modifier.bottomBorder(),
        navigationIcon = { navigationIcon?.Content() },
        actions = { actions.forEach { it.PolymorphicContent() } },
        expandedHeight = expandedHeight,
        windowInsets = windowInsets,
        colors = colors(translucent = translucent),
        scrollBehavior = scrollBehavior
    )
}

/**
 * App bar (aka Top app bar on Material 2) is a top-aligned component that displays the screen title and provides access to key actions and navigation
 * elements. It may include an Up button, action icons, overflow menus, search, or tabs. It provides context and controls relevant to the current view.
 *
 * This medium top app bar has slots for a title, navigation icon, and actions. In its default expanded
 * state, the title is displayed in a second row under the navigation and actions.
 *
 * [OudsMediumTopAppBar] default appearance is opaque but, if you need a **translucent blurred top app bar** as specified on OUDS design
 * side, you can implement it in your app with the help of [Haze](https://chrisbanes.github.io/haze/latest/) library. To do this, use [OudsMediumTopAppBar] with
 * [translucent] parameter set to true and follow these steps:
 * 1. Add Haze dependency
 * 2. Follow Haze basic usage instructions:
 * - Define Haze state in the screen containing the top app bar: `val hazeState = rememberHazeState()`
 * - Use `hazeEffect` Modifier on [OudsMediumTopAppBar] providing OUDS blur radius: `Modifier.hazeEffect(state = hazeState, style = HazeStyle(tint = null, blurRadius = OudsTheme.components.bar.blurRadius.dp)),`
 * - Apply `hazeSource` Modifier on the content that scrolls behind the top app bar: `Modifier.hazeSource(state = hazeState)`
 * 3. As your screen content needs to scroll behind the top app bar, you'll probably need to add an additional bottom padding
 * that will have the height of [OudsMediumTopAppBar].
 *
 * > Design guidelines: [unified-design-system.orange.com](https://r.orange.fr/r/S-ouds-doc-android-app-bar)
 *
 * > Design version: 1.0.0
 *
 * @param title The title to be displayed in the top app bar. This title will be used in the app
 *   bar's expanded and collapsed states, although in its collapsed state it will be composed with a
 *   smaller sized [TextStyle].
 * @param modifier The [Modifier] to be applied to this top app bar.
 * @param translucent Whether the top app bar should be translucent.
 * @param navigationIcon The navigation icon displayed at the start of the top app bar.
 * @param actions The actions displayed at the end of the top app bar. These can be
 *   instances of [OudsTopAppBarAction.Icon] or [OudsTopAppBarAction.Avatar].
 *   The default layout here is a [Row], so actions will be placed horizontally.
 *   The maximum recommended number of actions is three. Please use a dropdown menu if you need more than three actions.
 * @param collapsedHeight This app bar height when collapsed by a provided [scrollBehavior]. This
 *   value must be specified and finite, otherwise it will be ignored and replaced with
 *   [TopAppBarDefaults.MediumAppBarCollapsedHeight].
 * @param expandedHeight This app bar's maximum height. When a specified [scrollBehavior] causes the
 *   app bar to collapse or expand, this value will represent the maximum height that the app-bar
 *   will be allowed to expand. The expanded height is expected to be greater or equal to the
 *   [collapsedHeight], and the function will throw an [IllegalArgumentException] otherwise. Also,
 *   this value must be specified and finite, otherwise it will be ignored and replaced with
 *   [TopAppBarDefaults.MediumAppBarExpandedHeight].
 * @param windowInsets A window insets that app bar will respect.
 * @param scrollBehavior A [TopAppBarScrollBehavior] which holds various offset values that will be
 *   applied by this top app bar to set up its height and colors. A scroll behavior is designed to
 *   work in conjunction with a scrolled content to change the top app bar appearance as the content
 *   scrolls. See [TopAppBarScrollBehavior.nestedScrollConnection].
 * @throws IllegalArgumentException if the provided [expandedHeight] is smaller than the
 *   [collapsedHeight]
 *
 * @sample com.orange.ouds.core.component.samples.OudsMediumTopAppBarSample
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OudsMediumTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    translucent: Boolean = false,
    navigationIcon: OudsTopAppBarNavigationIcon? = null,
    actions: List<OudsTopAppBarAction> = emptyList(),
    collapsedHeight: Dp = TopAppBarDefaults.MediumAppBarCollapsedHeight,
    expandedHeight: Dp = TopAppBarDefaults.MediumAppBarExpandedHeight,
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    MediumTopAppBar(
        title = {
            Title(
                title = title,
                topAppBarSize = OudsTopAppBarSize.Medium,
                centerAligned = false
            )
        },
        modifier = modifier.bottomBorder(),
        navigationIcon = { navigationIcon?.Content() },
        actions = { actions.forEach { it.PolymorphicContent() } },
        collapsedHeight = collapsedHeight,
        expandedHeight = expandedHeight,
        windowInsets = windowInsets,
        colors = colors(translucent = translucent),
        scrollBehavior = scrollBehavior
    )
}

/**
 * App bar (aka Top app bar on Material 2) is a top-aligned component that displays the screen title and provides access to key actions and navigation
 * elements. It may include an Up button, action icons, overflow menus, search, or tabs. It provides context and controls relevant to the current view.
 *
 * This LargeTopAppBar has slots for a title, navigation icon, and actions. In its default expanded
 * state, the title is displayed in a second row under the navigation and actions.
 *
 * [OudsLargeTopAppBar] default appearance is opaque but, if you need a **translucent blurred top app bar** as specified on OUDS design
 * side, you can implement it in your app with the help of [Haze](https://chrisbanes.github.io/haze/latest/) library. To do this, use [OudsLargeTopAppBar] with
 * [translucent] parameter set to true and follow these steps:
 * 1. Add Haze dependency
 * 2. Follow Haze basic usage instructions:
 * - Define Haze state in the screen containing the top app bar: `val hazeState = rememberHazeState()`
 * - Use `hazeEffect` Modifier on [OudsLargeTopAppBar] providing OUDS blur radius: `Modifier.hazeEffect(state = hazeState, style = HazeStyle(tint = null, blurRadius = OudsTheme.components.bar.blurRadius.dp)),`
 * - Apply `hazeSource` Modifier on the content that scrolls behind the top app bar: `Modifier.hazeSource(state = hazeState)`
 * 3. As your screen content needs to scroll behind the top app bar, you'll probably need to add an additional bottom padding
 * that will have the height of [OudsLargeTopAppBar].
 *
 * > Design guidelines: [unified-design-system.orange.com](https://r.orange.fr/r/S-ouds-doc-android-app-bar)
 *
 * > Design version: 1.0.0
 *
 * @param title The title to be displayed in the top app bar. This title will be used in the app
 *   bar's expanded and collapsed states, although in its collapsed state it will be composed with a
 *   smaller sized [TextStyle].
 * @param modifier The [Modifier] to be applied to this top app bar.
 * @param translucent Whether the top app bar should be translucent.
 * @param navigationIcon The navigation icon displayed at the start of the top app bar.
 * @param actions The actions displayed at the end of the top app bar. These can be
 *   instances of [OudsTopAppBarAction.Icon] or [OudsTopAppBarAction.Avatar].
 *   The default layout here is a [Row], so actions will be placed horizontally.
 *   The maximum recommended number of actions is three. Please use a dropdown menu if you need more than three actions.
 * @param collapsedHeight This app bar height when collapsed by a provided [scrollBehavior]. This
 *   value must be specified and finite, otherwise it will be ignored and replaced with
 *   [TopAppBarDefaults.LargeAppBarCollapsedHeight].
 * @param expandedHeight This app bar's maximum height. When a specified [scrollBehavior] causes the
 *   app bar to collapse or expand, this value will represent the maximum height that the app-bar
 *   will be allowed to expand. The expanded height is expected to be greater or equal to the
 *   [collapsedHeight], and the function will throw an [IllegalArgumentException] otherwise. Also,
 *   this value must be specified and finite, otherwise it will be ignored and replaced with
 *   [TopAppBarDefaults.LargeAppBarExpandedHeight].
 * @param windowInsets A window insets that app bar will respect.
 * @param scrollBehavior A [TopAppBarScrollBehavior] which holds various offset values that will be
 *   applied by this top app bar to set up its height and colors. A scroll behavior is designed to
 *   work in conjunction with a scrolled content to change the top app bar appearance as the content
 *   scrolls. See [TopAppBarScrollBehavior.nestedScrollConnection].
 * @throws IllegalArgumentException if the provided [expandedHeight] is smaller than the
 *   [collapsedHeight]
 *
 * @sample com.orange.ouds.core.component.samples.OudsLargeTopAppBarSample
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OudsLargeTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    translucent: Boolean = false,
    navigationIcon: OudsTopAppBarNavigationIcon? = null,
    actions: List<OudsTopAppBarAction> = emptyList(),
    collapsedHeight: Dp = TopAppBarDefaults.LargeAppBarCollapsedHeight,
    expandedHeight: Dp = TopAppBarDefaults.LargeAppBarExpandedHeight,
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    LargeTopAppBar(
        title = {
            Title(
                title = title,
                topAppBarSize = OudsTopAppBarSize.Large,
                centerAligned = false
            )
        },
        modifier = modifier.bottomBorder(),
        navigationIcon = { navigationIcon?.Content() },
        actions = { actions.forEach { it.PolymorphicContent() } },
        collapsedHeight = collapsedHeight,
        expandedHeight = expandedHeight,
        windowInsets = windowInsets,
        colors = colors(translucent = translucent),
        scrollBehavior = scrollBehavior
    )
}

@Composable
private fun Title(title: String, topAppBarSize: OudsTopAppBarSize, centerAligned: Boolean) {
    Column(horizontalAlignment = if (centerAligned) Alignment.CenterHorizontally else Alignment.Start) {
        val maxLines = if (topAppBarSize == OudsTopAppBarSize.Small) 1 else Int.MAX_VALUE
        Text(
            text = title,
            maxLines = maxLines,
            overflow = TextOverflow.Ellipsis,
            color = OudsTheme.colorScheme.content.default,
            fontFamily = OudsTheme.typography.fontFamily
        )
    }
}

@Composable
private fun colors(translucent: Boolean): TopAppBarColors {
    val backgroundColor = with(OudsTheme.componentsTokens.bar) {
        if (translucent) colorBgTranslucent.value else colorBgOpaque.value
    }
    return TopAppBarDefaults.topAppBarColors(
        containerColor = backgroundColor,
        scrolledContainerColor = backgroundColor
    )
}

@Composable
private fun Modifier.bottomBorder(): Modifier {
    val color = OudsTheme.colorScheme.border.minimal
    return drawWithContent {
        drawContent()
        val width = 1.dp.toPx()
        drawLine(
            color = color,
            start = Offset(x = 0f, y = size.height - width / 2f),
            end = Offset(x = size.width, y = size.height - width / 2f),
            strokeWidth = width
        )
    }
}

/**
 * A navigation icon in an [OudsTopAppBar] or any other variant.
 */
open class OudsTopAppBarNavigationIcon private constructor(
    graphicsObjectProvider: @Composable (OudsTopAppBarNavigationIcon) -> Any,
    contentDescriptionProvider: @Composable (OudsTopAppBarNavigationIcon) -> String,
    onClick: () -> Unit
) : OudsComponentIcon<Nothing, OudsTopAppBarNavigationIcon>(Nothing::class.java, graphicsObjectProvider, contentDescriptionProvider, onClick) {

    /**
     * A predefined [OudsTopAppBarNavigationIcon] with a back icon.
     *
     * @constructor Creates an instance of [OudsTopAppBarNavigationIcon.Back].
     * @param onClick Callback invoked when the navigation icon is clicked.
     */
    class Back(onClick: () -> Unit) : OudsTopAppBarNavigationIcon(
        { painterResource(id = OudsTheme.drawableResources.functional.navigation.formChevronLeft) },
        { stringResource(R.string.core_topAppBar_backNavigationIcon_a11y) },
        onClick
    )

    /**
     * A predefined [OudsTopAppBarNavigationIcon] with a close icon.
     *
     * @constructor Creates an instance of [OudsTopAppBarNavigationIcon.Close].
     * @param onClick Callback invoked when the navigation icon is clicked.
     */
    class Close(onClick: () -> Unit) : OudsTopAppBarNavigationIcon(
        { painterResource(id = OudsTheme.drawableResources.functional.actions.delete) },
        { stringResource(R.string.core_topAppBar_closeNavigationIcon_a11y) },
        onClick
    )

    /**
     * A predefined [OudsTopAppBarNavigationIcon] with a menu icon.
     *
     * @constructor Creates an instance of [OudsTopAppBarNavigationIcon.Menu].
     * @param onClick Callback invoked when the navigation icon is clicked.
     */
    class Menu(onClick: () -> Unit) : OudsTopAppBarNavigationIcon(
        { painterResource(id = OudsTheme.drawableResources.functional.navigation.menu) },
        { stringResource(R.string.core_topAppBar_menuNavigationIcon_a11y) },
        onClick
    )

    /**
     * Creates an instance of [OudsTopAppBarNavigationIcon].
     *
     * @param painter Painter of the navigation icon.
     * @param contentDescription The content description associated with this [OudsTopAppBarNavigationIcon].
     * @param onClick Callback invoked when the navigation icon is clicked.
     */
    constructor(
        painter: Painter,
        contentDescription: String,
        onClick: () -> Unit
    ) : this({ painter }, { contentDescription }, onClick)

    /**
     * Creates an instance of [OudsTopAppBarNavigationIcon].
     *
     * @param imageVector Image vector of the navigation icon.
     * @param contentDescription The content description associated with this [OudsTopAppBarNavigationIcon].
     * @param onClick Callback invoked when the navigation icon is clicked.
     */
    constructor(
        imageVector: ImageVector,
        contentDescription: String,
        onClick: () -> Unit
    ) : this({ imageVector }, { contentDescription }, onClick)

    /**
     * Creates an instance of [OudsTopAppBarNavigationIcon].
     *
     * @param bitmap Image bitmap of the navigation icon.
     * @param contentDescription The content description associated with this [OudsTopAppBarNavigationIcon].
     * @param onClick Callback invoked when the navigation icon is clicked.
     */
    constructor(
        bitmap: ImageBitmap,
        contentDescription: String,
        onClick: () -> Unit
    ) : this({ bitmap }, { contentDescription }, onClick)
}

/**
 * An action in an [OudsTopAppBar] or any other variant.
 */
sealed interface OudsTopAppBarAction : OudsPolymorphicComponentContent {

    /**
     * A top app bar action that displays an icon.
     */
    class Icon private constructor(
        graphicsObject: Any,
        contentDescription: String,
        badge: OudsTopAppBarActionBadge?,
        onClick: () -> Unit
    ) : OudsTopAppBarAction, OudsComponentIcon<Nothing, Icon>(Nothing::class.java, { graphicsObject }, { contentDescription }, onClick) {

        private val _badge = badge
        override val badge: OudsButtonIconBadge?
            @Composable
            get() = _badge?.let { badge ->
                OudsButtonIconBadge(
                    contentDescription = badge.contentDescription,
                    count = badge.count,
                    borderColor = OudsTheme.componentsTokens.bar.colorBorderBadge.value
                )
            }

        /**
         * Creates an instance of [OudsTopAppBarAction.Icon].
         *
         * @param painter Painter of the icon.
         * @param contentDescription The content description associated with this [OudsTopAppBarAction.Icon].
         * @param badge Optional badge displayed on the icon.
         * @param onClick Callback invoked when the icon is clicked.
         */
        constructor(
            painter: Painter,
            contentDescription: String,
            badge: OudsTopAppBarActionBadge? = null,
            onClick: () -> Unit
        ) : this(painter as Any, contentDescription, badge, onClick)

        /**
         * Creates an instance of [OudsTopAppBarAction.Icon].
         *
         * @param imageVector Image vector of the icon.
         * @param contentDescription The content description associated with this [OudsTopAppBarAction.Icon].
         * @param badge Optional badge displayed on the icon.
         * @param onClick Callback invoked when the icon is clicked.
         */
        constructor(
            imageVector: ImageVector,
            contentDescription: String,
            badge: OudsTopAppBarActionBadge? = null,
            onClick: () -> Unit
        ) : this(imageVector as Any, contentDescription, badge, onClick)

        /**
         * Creates an instance of [OudsTopAppBarAction.Icon].
         *
         * @param bitmap Image bitmap of the icon.
         * @param contentDescription The content description associated with this [OudsTopAppBarAction.Icon].
         * @param badge Optional badge displayed on the icon.
         * @param onClick Callback invoked when the icon is clicked.
         */
        constructor(
            bitmap: ImageBitmap,
            contentDescription: String,
            badge: OudsTopAppBarActionBadge? = null,
            onClick: () -> Unit
        ) : this(bitmap as Any, contentDescription, badge, onClick)
    }

    /**
     * A top app bar action that displays an avatar.
     * The content of the avatar can either be an image or a single letter monogram.
     */
    class Avatar private constructor(
        private val graphicsObject: Any?,
        private val monogram: Char?,
        private val monogramColor: Color?,
        private val monogramBackgroundColor: Color?,
        private val contentDescription: String,
        private val onClick: (() -> Unit)?
    ) : OudsTopAppBarAction, OudsComponentContent<Nothing>(Nothing::class.java) {

        /**
         * Creates an instance of [OudsTopAppBarAction.Avatar].
         *
         * @param painter Painter of the avatar.
         * @param contentDescription The content description associated with this [OudsTopAppBarAction.Avatar].
         * @param onClick Callback invoked when the avatar is clicked.
         */
        constructor(
            painter: Painter,
            contentDescription: String,
            onClick: (() -> Unit)?
        ) : this(painter as Any, null, null, null, contentDescription, onClick)

        /**
         * Creates an instance of [OudsTopAppBarAction.Avatar].
         *
         * @param imageVector Image vector of the avatar.
         * @param contentDescription The content description associated with this [OudsTopAppBarAction.Avatar].
         * @param onClick Callback invoked when the avatar is clicked.
         */
        constructor(
            imageVector: ImageVector,
            contentDescription: String,
            onClick: (() -> Unit)?
        ) : this(imageVector as Any, null, null, null, contentDescription, onClick)

        /**
         * Creates an instance of [OudsTopAppBarAction.Avatar].
         *
         * @param bitmap Image bitmap of the avatar.
         * @param contentDescription The content description associated with this [OudsTopAppBarAction.Avatar].
         * @param onClick Callback invoked when the avatar is clicked.
         */
        constructor(
            bitmap: ImageBitmap,
            contentDescription: String,
            onClick: (() -> Unit)?
        ) : this(bitmap as Any, null, null, null, contentDescription, onClick)

        /**
         * Creates an instance of [OudsTopAppBarAction.Avatar].
         *
         * @param monogram The single letter monogram for this avatar.
         * @param color The color of the monogram.
         * @param backgroundColor The background color of the monogram.
         * @param contentDescription The content description associated with this [OudsTopAppBarAction.Avatar].
         * @param onClick Callback invoked when the avatar is clicked.
         */
        constructor(
            monogram: Char,
            color: Color,
            backgroundColor: Color,
            contentDescription: String,
            onClick: (() -> Unit)?
        ) : this(null, monogram, color, backgroundColor, contentDescription, onClick)

        @Composable
        override fun Content(modifier: Modifier) {
            CompositionLocalProvider(LocalRippleConfiguration provides null) {
                Box(
                    modifier = Modifier
                        .size(OudsTheme.sizes.minInteractiveArea)
                        .run { if (onClick != null) clickable(onClick = onClick, role = Role.Button) else this }
                        .semantics(mergeDescendants = true) {
                            contentDescription = this@Avatar.contentDescription
                        },
                    contentAlignment = Alignment.Center
                ) {
                    val modifier = Modifier
                        .clip(CircleShape)
                        .size(32.dp)
                    val contentScale = ContentScale.Crop
                    if (graphicsObject != null) {
                        when (graphicsObject) {
                            is Painter -> Image(
                                modifier = modifier,
                                painter = graphicsObject,
                                contentDescription = null,
                                contentScale = contentScale
                            )
                            is ImageVector -> Image(
                                modifier = modifier,
                                imageVector = graphicsObject,
                                contentDescription = null,
                                contentScale = contentScale
                            )
                            is ImageBitmap -> Image(
                                modifier = modifier,
                                bitmap = graphicsObject,
                                contentDescription = null,
                                contentScale = contentScale
                            )
                        }
                    } else if (monogram != null && monogramColor != null && monogramBackgroundColor != null) {
                        Box(
                            modifier = modifier.background(monogramBackgroundColor),
                            contentAlignment = Alignment.Center,
                        ) {
                            CompositionLocalProvider(
                                // Do not take user font scale into account
                                value = LocalDensity provides Density(LocalDensity.current.density, 1f)
                            ) {
                                Text(
                                    modifier = Modifier.clearAndSetSemantics {},
                                    text = monogram.uppercase(),
                                    color = monogramColor,
                                    style = MaterialTheme.typography.titleMedium, // This looks like the most accurate style according to Material specs at https://m3.material.io/components/app-bars/specs#606c6564-ce7d-489d-8852-af2b3b478bc6
                                    fontFamily = OudsTheme.typography.fontFamily
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

/**
 * A badge in an [OudsTopAppBarAction].
 *
 * @see [OudsBadge]
 *
 * @property contentDescription Content description of the badge, needed for accessibility support (vocalized by Talkback).
 * @property count Optional number displayed in the badge. If not null, the badge has an [OudsBadgeSize.Medium] size. Otherwise, it has an [OudsBadgeSize.ExtraSmall] size.
 */
data class OudsTopAppBarActionBadge(val contentDescription: String, val count: Int? = null)

private enum class OudsTopAppBarSize {

    Small, Medium, Large
}

@PreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsTopAppBar(@PreviewParameter(OudsTopAppBarPreviewParameterProvider::class) parameter: OudsTopAppBarPreviewParameter) {
    PreviewOudsTopAppBar(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PreviewOudsTopAppBar(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsTopAppBarPreviewParameter
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        OudsTopAppBar(
            title = title,
            navigationIcon = navigationIcon,
            actions = actions
        )
    }
}

@PreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsCenterAlignedTopAppBar(@PreviewParameter(OudsTopAppBarPreviewParameterProvider::class) parameter: OudsTopAppBarPreviewParameter) {
    PreviewOudsCenterAlignedTopAppBar(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PreviewOudsCenterAlignedTopAppBar(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsTopAppBarPreviewParameter
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        OudsCenterAlignedTopAppBar(
            title = title,
            navigationIcon = navigationIcon,
            actions = actions
        )
    }
}

@PreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsMediumTopAppBar(@PreviewParameter(OudsTopAppBarPreviewParameterProvider::class) parameter: OudsTopAppBarPreviewParameter) {
    PreviewOudsMediumTopAppBar(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PreviewOudsMediumTopAppBar(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsTopAppBarPreviewParameter
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        OudsMediumTopAppBar(
            title = title,
            navigationIcon = navigationIcon,
            actions = actions
        )
    }
}

@PreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsLargeTopAppBar(@PreviewParameter(OudsTopAppBarPreviewParameterProvider::class) parameter: OudsTopAppBarPreviewParameter) {
    PreviewOudsLargeTopAppBar(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PreviewOudsLargeTopAppBar(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsTopAppBarPreviewParameter
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        OudsLargeTopAppBar(
            title = title,
            navigationIcon = navigationIcon,
            actions = actions
        )
    }
}

internal data class OudsTopAppBarPreviewParameter(
    val title: String = "Title",
    val navigationIcon: OudsTopAppBarNavigationIcon? = null,
    val actions: List<OudsTopAppBarAction> = emptyList()
)

internal class OudsTopAppBarPreviewParameterProvider() : BasicPreviewParameterProvider<OudsTopAppBarPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsTopAppBarPreviewParameter>
    get() = listOf(
        OudsTopAppBarPreviewParameter(),
        OudsTopAppBarPreviewParameter(
            navigationIcon = OudsTopAppBarNavigationIcon.Back(onClick = {}),
            actions = listOf(OudsTopAppBarAction.Icon(imageVector = Icons.Outlined.FavoriteBorder, contentDescription = "", onClick = {}))
        ),
        OudsTopAppBarPreviewParameter(
            navigationIcon = OudsTopAppBarNavigationIcon(imageVector = Icons.Outlined.Settings, contentDescription = "", onClick = {}),
            actions = listOf(
                OudsTopAppBarAction.Icon(
                    imageVector = Icons.Outlined.AccountCircle,
                    contentDescription = "",
                    badge = OudsTopAppBarActionBadge(contentDescription = "", count = 10),
                    onClick = {}
                ),
                OudsTopAppBarAction.Avatar(
                    painter = PreviewCheckerboardPainter(
                        squareSize = 6.dp,
                        primaryColor = Color(0xff247a85),
                        secondaryColor = Color(0xfffbcd00)
                    ),
                    contentDescription = "",
                    onClick = {}
                ),
                OudsTopAppBarAction.Avatar(
                    monogram = 'A',
                    color = Color.White,
                    backgroundColor = Color(0xffd5204e),
                    contentDescription = "",
                    onClick = {}
                )
            )
        )
    )

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.R
import com.orange.ouds.core.component.content.OudsComponentContent
import com.orange.ouds.core.component.content.OudsComponentIcon
import com.orange.ouds.core.component.content.OudsPolymorphicComponentContent
import com.orange.ouds.core.component.content.PolymorphicContent
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.theme.OudsThemeContract
import kotlin.math.ceil

//TODO add DSM link when available:  * <a href="https://unified-design-system.orange.com/472794e18/p/31c33b-link" class="external" target="_blank">**OUDS App bar design guidelines**</a>
/**
 * Top app bars display information and actions at the top of a screen.
 *
 * This small top app bar has slots for a title, navigation icon, and actions.
 *
 * @param title The title to be displayed in the top app bar.
 * @param modifier The [Modifier] to be applied to this top app bar.
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
                type = OudsTopAppBarType.Small
            )
        },
        modifier = modifier,
        navigationIcon = { navigationIcon?.Content() },
        actions = { actions.forEach { it.PolymorphicContent() } },
        expandedHeight = expandedHeight,
        windowInsets = windowInsets,
        scrollBehavior = scrollBehavior
    )
}

//TODO add DSM link when available:  * <a href="https://unified-design-system.orange.com/472794e18/p/31c33b-link" class="external" target="_blank">**OUDS App bar design guidelines**</a>
/**
 * Top app bars display information and actions at the top of a screen.
 *
 * This small top app bar has a header title that is horizontally aligned to the center.
 *
 * @param title The title to be displayed in the top app bar.
 * @param modifier The [Modifier] to be applied to this top app bar.
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
                type = OudsTopAppBarType.CenterAligned
            )
        },
        modifier = modifier,
        navigationIcon = { navigationIcon?.Content() },
        actions = { actions.forEach { it.PolymorphicContent() } },
        expandedHeight = expandedHeight,
        windowInsets = windowInsets,
        scrollBehavior = scrollBehavior
    )
}

//TODO add DSM link when available:  * <a href="https://unified-design-system.orange.com/472794e18/p/31c33b-link" class="external" target="_blank">**OUDS App bar design guidelines**</a>
/**
 * Top app bars display information and actions at the top of a screen.
 *
 * This medium top app bar has slots for a title, navigation icon, and actions. In its default expanded
 * state, the title is displayed in a second row under the navigation and actions.
 *
 * @param title The title to be displayed in the top app bar. This title will be used in the app
 *   bar's expanded and collapsed states, although in its collapsed state it will be composed with a
 *   smaller sized [TextStyle].
 * @param modifier The [Modifier] to be applied to this top app bar.
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
 * @param scrollBehavior a [TopAppBarScrollBehavior] which holds various offset values that will be
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
                type = OudsTopAppBarType.Medium
            )
        },
        modifier = modifier,
        navigationIcon = { navigationIcon?.Content() },
        actions = { actions.forEach { it.PolymorphicContent() } },
        collapsedHeight = collapsedHeight,
        expandedHeight = expandedHeight,
        windowInsets = windowInsets,
        scrollBehavior = scrollBehavior
    )
}

//TODO add DSM link when available:  * <a href="https://unified-design-system.orange.com/472794e18/p/31c33b-link" class="external" target="_blank">**OUDS App bar design guidelines**</a>
/**
 * Top app bars display information and actions at the top of a screen.
 *
 * This LargeTopAppBar has slots for a title, navigation icon, and actions. In its default expanded
 * state, the title is displayed in a second row under the navigation and actions.
 *
 * @param title The title to be displayed in the top app bar. This title will be used in the app
 *   bar's expanded and collapsed states, although in its collapsed state it will be composed with a
 *   smaller sized [TextStyle].
 * @param modifier The [Modifier] to be applied to this top app bar.
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
 * @param scrollBehavior a [TopAppBarScrollBehavior] which holds various offset values that will be
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
                type = OudsTopAppBarType.Large
            )
        },
        modifier = modifier,
        navigationIcon = { navigationIcon?.Content() },
        actions = { actions.forEach { it.PolymorphicContent() } },
        collapsedHeight = collapsedHeight,
        expandedHeight = expandedHeight,
        windowInsets = windowInsets,
        scrollBehavior = scrollBehavior
    )
}

@Composable
private fun Title(title: String, type: OudsTopAppBarType) {
    Column(horizontalAlignment = if (type == OudsTopAppBarType.CenterAligned) Alignment.CenterHorizontally else Alignment.Start) {
        val maxLines = if (type in listOf(OudsTopAppBarType.Small, OudsTopAppBarType.CenterAligned)) 1 else Int.MAX_VALUE
        Text(
            text = title,
            maxLines = maxLines,
            overflow = TextOverflow.Ellipsis,
            color = OudsTheme.colorScheme.content.default,
            fontFamily = OudsTheme.typography.fontFamily
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
        onClick: () -> Unit
    ) : OudsTopAppBarAction, OudsComponentIcon<Nothing, Icon>(Nothing::class.java, graphicsObject, contentDescription, onClick) {

        /**
         * Creates an instance of [OudsTopAppBarAction.Icon].
         *
         * @param painter Painter of the icon.
         * @param contentDescription The content description associated with this [OudsTopAppBarAction.Icon].
         * @param onClick Callback invoked when the icon is clicked.
         */
        constructor(
            painter: Painter,
            contentDescription: String,
            onClick: () -> Unit
        ) : this(painter as Any, contentDescription, onClick)

        /**
         * Creates an instance of [OudsTopAppBarAction.Icon].
         *
         * @param imageVector Image vector of the icon.
         * @param contentDescription The content description associated with this [OudsTopAppBarAction.Icon].
         * @param onClick Callback invoked when the icon is clicked.
         */
        constructor(
            imageVector: ImageVector,
            contentDescription: String,
            onClick: () -> Unit
        ) : this(imageVector as Any, contentDescription, onClick)

        /**
         * Creates an instance of [OudsTopAppBarAction.Icon].
         *
         * @param bitmap Image bitmap of the icon.
         * @param contentDescription The content description associated with this [OudsTopAppBarAction.Icon].
         * @param onClick Callback invoked when the icon is clicked.
         */
        constructor(
            bitmap: ImageBitmap,
            contentDescription: String,
            onClick: () -> Unit
        ) : this(bitmap as Any, contentDescription, onClick)
    }

    /**
     * A top app bar action that displays an avatar.
     * The content of the avatar can either be an image or a monogram consisting of one or two letters.
     */
    class Avatar private constructor(
        private val graphicsObject: Any?,
        private val monogram: String?,
        private val monogramColor: Color?,
        private val monogramBackgroundColor: Color?,
        private val contentDescription: String,
        private val onClick: () -> Unit
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
            onClick: () -> Unit
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
            onClick: () -> Unit
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
            onClick: () -> Unit
        ) : this(bitmap as Any, null, null, null, contentDescription, onClick)

        /**
         * Creates an instance of [OudsTopAppBarAction.Avatar].
         *
         * @param monogram The monogram for this avatar, consisting of one or two letters.
         * @param color The color of the monogram.
         * @param backgroundColor The background color of the monogram.
         * @param contentDescription The content description associated with this [OudsTopAppBarAction.Avatar].
         * @param onClick Callback invoked when the avatar is clicked.
         */
        constructor(
            monogram: String,
            color: Color,
            backgroundColor: Color,
            contentDescription: String,
            onClick: () -> Unit
        ) : this(null, monogram, color, backgroundColor, contentDescription, onClick)

        @Composable
        override fun Content(modifier: Modifier) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clickable(onClick = onClick),
                contentAlignment = Alignment.Center
            ) {
                val modifier = Modifier
                    .clip(CircleShape)
                    .size(32.dp)
                if (graphicsObject != null) {
                    when (graphicsObject) {
                        is Painter -> Image(modifier = modifier, painter = graphicsObject, contentDescription = contentDescription)
                        is ImageVector -> Image(modifier = modifier, imageVector = graphicsObject, contentDescription = contentDescription)
                        is ImageBitmap -> Image(modifier = modifier, bitmap = graphicsObject, contentDescription = contentDescription)
                    }
                } else if (monogram != null && monogramColor != null && monogramBackgroundColor != null) {
                    Box(
                        modifier = modifier.background(monogramBackgroundColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = monogram.take(2).uppercase(),
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

private enum class OudsTopAppBarType {

    Small, CenterAligned, Medium, Large
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

private class OudsTopAppBarPreviewParameterProvider() : BasicPreviewParameterProvider<OudsTopAppBarPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsTopAppBarPreviewParameter>
    get() = listOf(
        OudsTopAppBarPreviewParameter(),
        OudsTopAppBarPreviewParameter(
            navigationIcon = OudsTopAppBarNavigationIcon.Back {},
            actions = listOf(OudsTopAppBarAction.Icon(Icons.Outlined.FavoriteBorder, "") {})
        ),
        OudsTopAppBarPreviewParameter(
            navigationIcon = OudsTopAppBarNavigationIcon(Icons.Outlined.Settings, "") {},
            actions = listOf(
                OudsTopAppBarAction.Icon(Icons.Outlined.AccountCircle, "") {},
                OudsTopAppBarAction.Avatar(PreviewCheckerboardPainter(), "") {},
                OudsTopAppBarAction.Avatar(monogram = "A", color = Color.White, backgroundColor = Color(0xffd5204e), "") {}
            )
        )
    )

private class PreviewCheckerboardPainter() : Painter() {

    override val intrinsicSize = Size.Unspecified

    override fun DrawScope.onDraw() {
        val primaryColor = Color(0xff247a85)
        val secondaryColor = Color(0xfffbcd00)
        val squareSize = 6.dp.toPx()
        val columnCount = ceil(size.width / squareSize).toInt()
        val rowCount = ceil(size.height / squareSize).toInt()
        val drawSize = Size(squareSize, squareSize)

        repeat(rowCount) { row ->
            repeat(columnCount) { column ->
                val color = if ((row + column) % 2 == 0) primaryColor else secondaryColor
                drawRect(
                    color = color,
                    topLeft = Offset(column * squareSize, row * squareSize),
                    size = drawSize
                )
            }
        }
    }
}

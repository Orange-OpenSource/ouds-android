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

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.component.common.outerBorder
import com.orange.ouds.core.component.content.OudsComponentContent
import com.orange.ouds.core.component.content.OudsComponentIcon
import com.orange.ouds.core.extensions.InteractionState
import com.orange.ouds.core.extensions.collectInteractionStateAsState
import com.orange.ouds.core.extensions.iconSize
import com.orange.ouds.core.theme.LocalColorMode
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.WindowWidthSizeClass
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.OudsPreviewLightDark
import com.orange.ouds.core.utilities.PreviewEnumEntries
import com.orange.ouds.core.utilities.getPreviewEnumEntry
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.core.utilities.rememberRainbowHeartPainter
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.theme.OudsThemeContract
import com.orange.ouds.theme.tokens.OudsKeyToken
import com.orange.ouds.theme.tokens.OudsSizeKeyToken
import com.orange.ouds.theme.tokens.OudsSpaceKeyToken
import com.orange.ouds.theme.tokens.components.OudsLinkMonoTokens

private val LocalWindowWidthSizeClass = staticCompositionLocalOf { WindowWidthSizeClass.MEDIUM }

/**
 * Link is a UI element that allows to navigate from one location to another, either within the same page
 * or across different pages in the same resource, or to an external resource. Link's primary function is navigation
 * and it communicates its interactive nature visually and semantically.
 *
 * Note that if it is placed in an [OudsColoredBox], its monochrome variant is automatically displayed.
 * The tokens associated with this variant can be customized by overriding [OudsLinkMonoTokens].
 *
 * > Design guidelines: [unified-design-system.orange.com](https://r.orange.fr/r/S-ouds-doc-link)
 *
 * > Design name: Link
 *
 * > Design version: 2.4.0
 *
 * @param label Label describing what is being linked to.
 * @param onClick Callback invoked when the link is clicked.
 * @param modifier [Modifier] applied to the link.
 * @param size Size of the link. See [OudsLinkSize] for available sizes.
 * @param enabled Controls the enabled state of the link. When `false`, the link will not be clickable.
 * @param density Density of the link. See [OudsLinkDensity] for available densities.
 *
 * @sample com.orange.ouds.core.component.samples.OudsLinkSample
 */
@Composable
fun OudsLink(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: OudsLinkSize = OudsLinkDefaults.Size,
    enabled: Boolean = true,
    density: OudsLinkDensity = OudsLinkDefaults.Density
) {
    OudsLink(
        label = label,
        icon = null,
        indicator = null,
        onClick = onClick,
        modifier = modifier,
        size = size,
        enabled = enabled,
        density = density
    )
}

/**
 * Link is a UI element that allows to navigate from one location to another, either within the same page
 * or across different pages in the same resource, or to an external resource. Link's primary function is navigation
 * and it communicates its interactive nature visually and semantically.
 *
 * Note that if it is placed in an [OudsColoredBox], its monochrome variant is automatically displayed.
 * The tokens associated with this variant can be customized by overriding [OudsLinkMonoTokens].
 *
 * > Design guidelines: [unified-design-system.orange.com](https://r.orange.fr/r/S-ouds-doc-link)
 *
 * > Design name: Link
 *
 * > Design version: 2.4.0
 *
 * @param label Label describing what is being linked to.
 * @param onClick Callback invoked when the link is clicked.
 * @param modifier [Modifier] applied to the link.
 * @param size Size of the link. See [OudsLinkSize] for available sizes.
 * @param enabled Controls the enabled state of the link. When `false`, the link will not be clickable.
 *
 * @sample com.orange.ouds.core.component.samples.OudsLinkSample
 */
@Deprecated(
    "Maintained for binary compatibility. Use overload with additional parameters.",
    level = DeprecationLevel.HIDDEN
)
@Composable
fun OudsLink(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: OudsLinkSize = OudsLinkDefaults.Size,
    enabled: Boolean = true
) {
    OudsLink(
        label = label,
        icon = null,
        indicator = null,
        onClick = onClick,
        modifier = modifier,
        size = size,
        enabled = enabled
    )
}

/**
 * Link is a UI element that allows to navigate from one location to another, either within the same page
 * or across different pages in the same resource, or to an external resource. Link's primary function is navigation
 * and it communicates its interactive nature visually and semantically.
 *
 * Note that if it is placed in an [OudsColoredBox], its monochrome variant is automatically displayed.
 * The tokens associated with this variant can be customized by overriding [OudsLinkMonoTokens].
 *
 * > Design guidelines: [unified-design-system.orange.com](https://r.orange.fr/r/S-ouds-doc-link)
 *
 * > Design name: Link
 *
 * > Design version: 2.4.0
 *
 * @param label Label describing what is being linked to.
 * @param icon Icon displayed in the link that can be used to indicate the destination or type of content being referenced.
 * @param onClick Callback invoked when the link is clicked.
 * @param modifier [Modifier] applied to the link.
 * @param size Size of the link. See [OudsLinkSize] for available sizes.
 * @param enabled Controls the enabled state of the link. When `false`, the link will not be clickable.
 * @param density Density of the link. See [OudsLinkDensity] for available densities.
 *
 * @sample com.orange.ouds.core.component.samples.OudsLinkWithIconSample
 *
 * @sample com.orange.ouds.core.component.samples.OudsLinkWithUntintedIconSample
 */
@Composable
fun OudsLink(
    label: String,
    icon: OudsLinkIcon,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: OudsLinkSize = OudsLinkDefaults.Size,
    enabled: Boolean = true,
    density: OudsLinkDensity = OudsLinkDefaults.Density
) {
    OudsLink(
        label = label,
        icon = icon,
        indicator = null,
        onClick = onClick,
        modifier = modifier,
        size = size,
        enabled = enabled,
        density = density
    )
}

/**
 * Link is a UI element that allows to navigate from one location to another, either within the same page
 * or across different pages in the same resource, or to an external resource. Link's primary function is navigation
 * and it communicates its interactive nature visually and semantically.
 *
 * Note that if it is placed in an [OudsColoredBox], its monochrome variant is automatically displayed.
 * The tokens associated with this variant can be customized by overriding [OudsLinkMonoTokens].
 *
 * > Design guidelines: [unified-design-system.orange.com](https://r.orange.fr/r/S-ouds-doc-link)
 *
 * > Design name: Link
 *
 * > Design version: 2.4.0
 *
 * @param label Label describing what is being linked to.
 * @param icon Icon displayed in the link that can be used to indicate the destination or type of content being referenced.
 * @param onClick Callback invoked when the link is clicked.
 * @param modifier [Modifier] applied to the link.
 * @param size Size of the link. See [OudsLinkSize] for available sizes.
 * @param enabled Controls the enabled state of the link. When `false`, the link will not be clickable.
 *
 * @sample com.orange.ouds.core.component.samples.OudsLinkWithIconSample
 *
 * @sample com.orange.ouds.core.component.samples.OudsLinkWithUntintedIconSample
 */
@Deprecated(
    "Maintained for binary compatibility. Use overload with additional parameters.",
    level = DeprecationLevel.HIDDEN
)
@Composable
fun OudsLink(
    label: String,
    icon: OudsLinkIcon,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: OudsLinkSize = OudsLinkDefaults.Size,
    enabled: Boolean = true
) {
    OudsLink(
        label = label,
        icon = icon,
        indicator = null,
        onClick = onClick,
        modifier = modifier,
        size = size,
        enabled = enabled
    )
}

/**
 * Link is a UI element that allows to navigate from one location to another, either within the same page
 * or across different pages in the same resource, or to an external resource. Link's primary function is navigation
 * and it communicates its interactive nature visually and semantically.
 *
 * This version of the link displays an [indicator] before ([OudsLinkIndicator.Previous]) or after ([OudsLinkIndicator.Next] or [OudsLinkIndicator.External]) a label.
 *
 * If it is used in an [OudsColoredBox], its monochrome variant is automatically displayed.
 * The tokens associated with this variant can be customized by overriding [OudsLinkMonoTokens].
 *
 * > Design guidelines: [unified-design-system.orange.com](https://r.orange.fr/r/S-ouds-doc-link)
 *
 * > Design name: Link
 *
 * > Design version: 2.4.0
 *
 * @param label Label describing what is being linked to.
 * @param indicator Navigation indicator displayed in the link. See [OudsLinkIndicator] for allowed values.
 * @param onClick Callback invoked when the link is clicked.
 * @param modifier [Modifier] applied to the link.
 * @param size Size of the link. See [OudsLinkSize] for available sizes.
 * @param enabled Controls the enabled state of the link. When `false`, the link will not be clickable.
 * @param density Density of the link. See [OudsLinkDensity] for available densities.
 *
 * @sample com.orange.ouds.core.component.samples.OudsLinkWithIndicatorSample
 */
@Composable
fun OudsLink(
    label: String,
    indicator: OudsLinkIndicator,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: OudsLinkSize = OudsLinkDefaults.Size,
    enabled: Boolean = true,
    density: OudsLinkDensity = OudsLinkDefaults.Density
) {
    OudsLink(
        label = label,
        icon = null,
        indicator = indicator,
        onClick = onClick,
        modifier = modifier,
        size = size,
        enabled = enabled,
        density = density
    )
}

/**
 * Link is a UI element that allows to navigate from one location to another, either within the same page
 * or across different pages in the same resource, or to an external resource. Link's primary function is navigation
 * and it communicates its interactive nature visually and semantically.
 *
 * This version of the link displays a [chevron] before ([OudsLinkChevron.Back]) or after ([OudsLinkChevron.Next]) a label.
 *
 * If it is used in an [OudsColoredBox], its monochrome variant is automatically displayed.
 * The tokens associated with this variant can be customized by overriding [OudsLinkMonoTokens].
 *
 * > Design guidelines: [unified-design-system.orange.com](https://r.orange.fr/r/S-ouds-doc-link)
 *
 * > Design name: Link
 *
 * > Design version: 2.4.0
 *
 * @param label Label describing what is being linked to.
 * @param chevron Navigation chevron displayed in the link. See [OudsLinkChevron] for allowed values.
 * @param onClick Callback invoked when the link is clicked.
 * @param modifier [Modifier] applied to the link.
 * @param size Size of the link. See [OudsLinkSize] for available sizes.
 * @param enabled Controls the enabled state of the link. When `false`, the link will not be clickable.
 *
 * @sample com.orange.ouds.core.component.samples.OudsLinkWithIndicatorSample
 */
@Deprecated(
    message = "Use overload with indicator parameter instead. Replace OudsLinkChevron.Back with OudsLinkIndicator.Previous, or OudsLinkChevron.Next with OudsLinkIndicator.Next.",
    level = DeprecationLevel.WARNING
)
@Composable
fun OudsLink(
    label: String,
    @Suppress("DEPRECATION") chevron: OudsLinkChevron,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: OudsLinkSize = OudsLinkDefaults.Size,
    enabled: Boolean = true,
) {
    OudsLink(
        label = label,
        icon = null,
        indicator = chevron.toIndicator(),
        onClick = onClick,
        modifier = modifier,
        size = size,
        enabled = enabled,
    )
}

@Composable
private fun OudsLink(
    label: String,
    icon: OudsLinkIcon?,
    indicator: OudsLinkIndicator?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: OudsLinkSize = OudsLinkDefaults.Size,
    enabled: Boolean = true,
    density: OudsLinkDensity = OudsLinkDefaults.Density
) {
    with(OudsTheme.componentsTokens.link) {
        val interactionSource = remember { MutableInteractionSource() }
        val interactionState by interactionSource.collectInteractionStateAsState()
        val state = getLinkState(enabled = enabled, interactionState = interactionState)
        val isTextOnly = icon == null && indicator == null

        val minHeight = when (density) {
            OudsLinkDensity.Default -> getTokenValue(size = size, default = sizeMinHeightDefault, small = sizeMinHeightSmall)
            OudsLinkDensity.Compact -> sizeMinHeightCompactDensity.dp
        }
        val verticalPadding = when (density) {
            OudsLinkDensity.Default -> getTokenValue(size = size, default = spacePaddingBlockDefault, small = spacePaddingBlockSmall)
            OudsLinkDensity.Compact -> getTokenValue(
                size = size,
                default = spacePaddingBlockCompactDensityDefault,
                small = spacePaddingBlockCompactDensitySmall
            )
        }
        val monochrome = LocalColorMode.current?.monochrome == true
        val contentColor = rememberInteractionColor(interactionState = interactionState) { linkInteractionState ->
            val linkState = getLinkState(enabled = enabled, interactionState = linkInteractionState)
            linkContentColor(state = linkState, monochrome = monochrome)
        }

        val chevronColor = rememberInteractionColor(interactionState = interactionState) { linkInteractionState ->
            val linkState = getLinkState(enabled = enabled, interactionState = linkInteractionState)
            chevronColor(state = linkState, monochrome = monochrome)
        }

        // Underlined text style cannot be animated with alpha, thus we use an interaction boolean to make it appear while the other animations are ongoing
        val isUnderlined = rememberInteractionValue(
            interactionState = interactionState,
            // The underlying animatable value is equal to 1f when isUnderlined is true and to 0f when isUnderlined is false,
            // meaning that the animatable value will smoothly move back and forth between 0f and 1f during the animation
            toAnimatableFloat = { if (it) 1f else 0f },
            // isUnderlined is true if the underlying animatable value is greater than or equal to 0.5f, false otherwise
            // meaning that the text will be underlined in the middle of the pressed animation and will come back to normal in the middle of the resting animation
            fromAnimatableFloat = { it >= 0.5f }
        ) { linkInteractionState ->
            val linkState = getLinkState(enabled = enabled, interactionState = linkInteractionState)
            isTextOnly || linkState in listOf(OudsLinkState.Hovered, OudsLinkState.Pressed, OudsLinkState.Focused)
        }

        Box(
            modifier = modifier
                .widthIn(min = sizeMinWidth.dp)
                .heightIn(min = minHeight)
                .outerBorder(state = state)
                .padding(horizontal = spacePaddingInline.value, vertical = verticalPadding)
                .clickable(
                    interactionSource = interactionSource,
                    indication = interactionValuesIndication(contentColor, chevronColor, isUnderlined),
                    enabled = state != OudsLinkState.Disabled,
                    onClick = onClick
                ),
            contentAlignment = Alignment.CenterStart
        ) {
            val columnGap: Dp
            val iconSize: Dp
            var textStyle: TextStyle
            when (size) {
                OudsLinkSize.Default -> {
                    columnGap = if (indicator != null) spaceColumnGapChevronDefault.value else spaceColumnGapIconDefault.value
                    iconSize = sizeIconDefault.value
                    textStyle = OudsTheme.typography.label.large.strong
                }
                OudsLinkSize.Small -> {
                    columnGap = if (indicator != null) spaceColumnGapChevronSmall.value else spaceColumnGapIconSmall.value
                    iconSize = sizeIconSmall.value
                    textStyle = OudsTheme.typography.label.medium.strong
                }
            }

            if (isUnderlined.value) {
                textStyle = textStyle.copy(textDecoration = TextDecoration.Underline)
            }

            val iconTint = if (indicator != null) chevronColor.value else contentColor.value

            Row(
                horizontalArrangement = Arrangement.spacedBy(columnGap),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (icon != null || indicator == OudsLinkIndicator.Previous) {
                    icon.orElse { OudsLinkIcon(painterResource(OudsTheme.drawableResources.component.link.previous)) }.Content(
                        modifier = Modifier.iconSize(iconSize, icon?.tinted.orElse { true }),
                        extraParameters = OudsLinkIcon.ExtraParameters(tint = iconTint)
                    )
                }
                Text(
                    modifier = Modifier.weight(1f, fill = false),
                    text = label,
                    color = contentColor.value,
                    style = textStyle
                )
                if (indicator != null && indicator != OudsLinkIndicator.Previous) {
                    val indicatorPainterResId = when (indicator) {
                        OudsLinkIndicator.Next -> OudsTheme.drawableResources.component.link.next
                        OudsLinkIndicator.External -> OudsTheme.drawableResources.component.link.externalLink
                    }
                    OudsLinkIcon(painterResource(indicatorPainterResId)).Content(
                        modifier = Modifier
                            .size(iconSize)
                            .fillMaxHeight()
                            .align(Alignment.Bottom),
                        extraParameters = OudsLinkIcon.ExtraParameters(tint = iconTint)
                    )
                }
            }
        }
    }
}

@Composable
private fun getLinkState(enabled: Boolean, interactionState: InteractionState): OudsLinkState {
    return getPreviewEnumEntry<OudsLinkState>().orElse {
        when {
            !enabled -> OudsLinkState.Disabled
            interactionState == InteractionState.Hovered -> OudsLinkState.Hovered
            interactionState == InteractionState.Pressed -> OudsLinkState.Pressed
            interactionState == InteractionState.Focused -> OudsLinkState.Focused
            else -> OudsLinkState.Enabled
        }
    }
}

@Composable
internal fun linkContentColor(state: OudsLinkState, monochrome: Boolean): Color {
    return if (monochrome) {
        with(OudsTheme.componentsTokens.linkMonochrome) {
            when (state) {
                OudsLinkState.Enabled -> colorContentEnabled
                OudsLinkState.Focused -> colorContentFocus
                OudsLinkState.Hovered -> colorContentHover
                OudsLinkState.Pressed -> colorContentPressed
                OudsLinkState.Disabled -> colorContentDisabled
            }.value
        }
    } else {
        with(OudsTheme.componentsTokens.link) {
            when (state) {
                OudsLinkState.Enabled -> colorContentEnabled.value
                OudsLinkState.Focused -> colorContentFocus.value
                OudsLinkState.Hovered -> colorContentHover.value
                OudsLinkState.Pressed -> colorContentPressed.value
                OudsLinkState.Disabled -> OudsTheme.colorScheme.action.disabled
            }
        }
    }
}

@Composable
private fun chevronColor(state: OudsLinkState, monochrome: Boolean): Color {
    return with(OudsTheme.componentsTokens.link) {
        if (monochrome) {
            linkContentColor(state = state, monochrome = true)
        } else {
            when (state) {
                OudsLinkState.Enabled -> colorChevronEnabled.value
                OudsLinkState.Focused -> colorContentFocus.value
                OudsLinkState.Hovered -> colorContentHover.value
                OudsLinkState.Pressed -> colorContentPressed.value
                OudsLinkState.Disabled -> OudsTheme.colorScheme.action.disabled
            }
        }
    }
}

@Composable
private fun <T> getTokenValue(size: OudsLinkSize, default: T, small: T): Dp where T : OudsSizeKeyToken = getKeyToken(size, default, small).value

@Composable
private fun <T> getTokenValue(size: OudsLinkSize, default: T, small: T): Dp where T : OudsSpaceKeyToken = getKeyToken(size, default, small).value

private fun <T> getKeyToken(size: OudsLinkSize, default: T, small: T): T where T : OudsKeyToken {
    return when (size) {
        OudsLinkSize.Default -> default
        OudsLinkSize.Small -> small
    }
}

/**
 * Contains the default values used by OUDS links.
 */
object OudsLinkDefaults {

    /**
     * The default size.
     */
    val Size = OudsLinkSize.Default

    /**
     * The default density.
     */
    val Density = OudsLinkDensity.Default
}

/**
 * Represents the size of an [OudsLink].
 */
enum class OudsLinkSize {
    /**
     * A standard link size used in most cases.
     */
    Default,

    /**
     * A small size for a link, particularly useful in an information-dense interface or in a component requiring the use
     * of small elements ("Inline alert" component, for example).
     */
    Small
}

/**
 * Represents the type of chevron displayed in an [OudsLink].
 */
@Deprecated(
    message = "OudsLinkChevron has been replaced with OudsLinkIndicator.",
    replaceWith = ReplaceWith("OudsLinkIndicator"),
    level = DeprecationLevel.WARNING
)
enum class OudsLinkChevron {

    /**
     * Used for "backward" navigation. This chevron is positioned before the label and features a "chevron left" icon, which is not customizable.
     */
    @Deprecated(message = "Use OudsLinkIndicator.Previous instead.")
    Back,

    /**
     * Used in a standard navigation context. This chevron is positioned after the label and features a "chevron right" icon, which is not customizable.
     */
    @Deprecated(message = "Use OudsLinkIndicator.Next instead.")
    Next;

    @Suppress("DEPRECATION")
    internal fun toIndicator(): OudsLinkIndicator = when (this) {
        Back -> OudsLinkIndicator.Previous
        Next -> OudsLinkIndicator.Next
    }
}

/**
 * Represents the type of navigation indicator displayed in an [OudsLink].
 */
enum class OudsLinkIndicator {

    /**
     * Used for "backward" navigation. Positioned before the label, it features a "chevron left" icon, which is not customizable.
     */
    Previous,

    /**
     * Used in a standard navigation context. Positioned after the label, it features a "chevron right" icon, which is not customizable.
     */
    Next,

    /**
     * Used to navigate outside the current product, service or application. The external navigation indicator informs users that the destination belongs to another context.
     */
    External
}

/**
 * Represents the density of an [OudsLink].
 */
enum class OudsLinkDensity {

    /**
     * This is the default density of the component, recommended for most interfaces and primary navigation contexts.
     * 
     * This density which is used for the vast majority of applications, provides a comfortable touch target that meets accessibility recommendations.
     */
    Default,

    /**
     * Reduces the vertical footprint while preserving the same interaction and visual behavior.
     * 
     * Use in dense layouts where space is limited and a smaller touch target is acceptable (desktop or pointer-based contexts).
     */
    Compact
}

/**
 * An icon in an [OudsLink].
 * This icon is non-clickable and no content description is needed because a link label is always present.
 */
open class OudsLinkIcon private constructor(
    graphicsObject: Any,
    override val tinted: Boolean
) : OudsComponentIcon<OudsLinkIcon.ExtraParameters, OudsLinkIcon>(ExtraParameters::class.java, graphicsObject, "") {

    @ConsistentCopyVisibility
    data class ExtraParameters internal constructor(
        internal val tint: Color
    ) : OudsComponentContent.ExtraParameters()

    /**
     * Creates an instance of [OudsLinkIcon].
     *
     * @param painter Painter of the icon.
     * @param tinted Controls whether the icon should be tinted with the theme color. Defaults to `true`.
     *   When set to `false`, the icon is displayed with its original colors (e.g., for multi-color icons).
     *   Note that untinted icons must ensure sufficient contrast with the background for accessibility reasons.
     */
    @JvmOverloads
    constructor(painter: Painter, tinted: Boolean = true) : this(painter as Any, tinted)

    /**
     * Creates an instance of [OudsLinkIcon].
     *
     * @param imageVector Image vector of the icon.
     * @param tinted Controls whether the icon should be tinted with the theme color. Defaults to `true`.
     *   When set to `false`, the icon is displayed with its original colors (e.g., for multi-color icons).
     *   Note that untinted icons must ensure sufficient contrast with the background for accessibility reasons.
     */
    @JvmOverloads
    constructor(imageVector: ImageVector, tinted: Boolean = true) : this(imageVector as Any, tinted)

    /**
     * Creates an instance of [OudsLinkIcon].
     *
     * @param bitmap Image bitmap of the icon.
     * @param tinted Controls whether the icon should be tinted with the theme color. Defaults to `true`.
     *   When set to `false`, the icon is displayed with its original colors (e.g., for multi-color icons).
     *   Note that untinted icons must ensure sufficient contrast with the background for accessibility reasons.
     */
    @JvmOverloads
    constructor(bitmap: ImageBitmap, tinted: Boolean = true) : this(bitmap as Any, tinted)

    override val tint: Color?
        @Composable
        get() = extraParameters.tint
}

internal enum class OudsLinkState {
    Enabled, Hovered, Pressed, Disabled, Focused
}

@OudsPreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsLink(@PreviewParameter(OudsLinkPreviewParameterProvider::class) parameter: OudsLinkPreviewParameter) {
    PreviewOudsLink(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsLink(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsLinkPreviewParameter
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        val icon = if (hasIcon) OudsLinkIcon(Icons.Filled.FavoriteBorder) else null
        val linkPreview: @Composable () -> Unit = {
            PreviewEnumEntries<OudsLinkState>(maxEnumEntriesInEachRow = 3) {
                OudsLink(
                    icon = icon,
                    label = "Label",
                    indicator = indicator,
                    onClick = {},
                    size = size
                )
            }
        }

        if (onColoredBackground) {
            OudsColoredBox(color = OudsColoredBoxColor.BrandPrimary) {
                linkPreview()
            }
        } else {
            linkPreview()
        }
    }
}

@OudsPreview
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsLinkCompactDensity(@PreviewParameter(OudsLinkCompactDensityPreviewParameterProvider::class) size: OudsLinkSize) {
    PreviewOudsLinkCompactDensity(theme = getPreviewTheme(), size = size)
}

@Composable
internal fun PreviewOudsLinkCompactDensity(theme: OudsThemeContract, size: OudsLinkSize) = OudsPreview(theme = theme) {
    PreviewEnumEntries<OudsLinkState>(maxEnumEntriesInEachRow = 3) {
        OudsLink(
            icon = null,
            label = "Label",
            indicator = OudsLinkIndicator.Next,
            onClick = {},
            size = size,
            density = OudsLinkDensity.Compact
        )
    }
}

@OudsPreview
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsLinkOnTwoLines() = PreviewOudsLinkOnTwoLines(theme = getPreviewTheme())

@Composable
internal fun PreviewOudsLinkOnTwoLines(theme: OudsThemeContract) {
    OudsPreview(theme = theme) {
        val label = "Link\non two lines"
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            listOf(OudsLinkIndicator.Previous, OudsLinkIndicator.Next).forEach { indicator ->
                OudsLink(
                    label = label,
                    indicator = indicator,
                    onClick = {},
                )
            }
        }
    }
}

@OudsPreview
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsLinkWithUntintedIcon() = PreviewOudsLinkWithUntintedIcon(theme = getPreviewTheme())

@Composable
internal fun PreviewOudsLinkWithUntintedIcon(theme: OudsThemeContract) = OudsPreview(theme = theme) {
    PreviewEnumEntries<OudsLinkState>(maxEnumEntriesInEachRow = 3) {
        OudsLink(
            label = "Label",
            icon = OudsLinkIcon(painter = rememberRainbowHeartPainter(), tinted = false),
            onClick = {}
        )
    }
}

internal data class OudsLinkPreviewParameter(
    val hasIcon: Boolean,
    val onColoredBackground: Boolean,
    val size: OudsLinkSize,
    val indicator: OudsLinkIndicator? = null
)

internal class OudsLinkPreviewParameterProvider : BasicPreviewParameterProvider<OudsLinkPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsLinkPreviewParameter>
    get() = buildList {
        OudsLinkSize.entries.forEach { size ->
            repeat(2) { index ->
                val onColoredBackground = index == 1
                add(OudsLinkPreviewParameter(hasIcon = false, onColoredBackground = onColoredBackground, size = size))
                OudsLinkIndicator.entries.forEach { indicator ->
                    add(OudsLinkPreviewParameter(hasIcon = false, indicator = indicator, onColoredBackground = onColoredBackground, size = size))
                }
                add(OudsLinkPreviewParameter(hasIcon = true, onColoredBackground = onColoredBackground, size = size))
            }
        }
    }

internal class OudsLinkCompactDensityPreviewParameterProvider : BasicPreviewParameterProvider<OudsLinkSize>(*OudsLinkSize.entries.toTypedArray())

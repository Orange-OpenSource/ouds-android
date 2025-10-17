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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.component.OudsLinkIcon.ExtraParameters
import com.orange.ouds.core.component.common.outerBorder
import com.orange.ouds.core.component.content.OudsComponentContent
import com.orange.ouds.core.component.content.OudsComponentIcon
import com.orange.ouds.core.extensions.InteractionState
import com.orange.ouds.core.extensions.collectInteractionStateAsState
import com.orange.ouds.core.theme.LocalColorMode
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.PreviewEnumEntries
import com.orange.ouds.core.utilities.getPreviewEnumEntry
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.theme.OudsThemeContract
import com.orange.ouds.theme.tokens.components.OudsLinkMonoTokens

/**
 * Links are interactive elements that allow users to navigate to a new screen, website, or a specific section within the current screen.
 *
 * Note that in the case it is placed in an [OudsColoredBox], its monochrome variant is automatically displayed.
 * The tokens associated with this variant can be customized by overriding [OudsLinkMonoTokens].
 *
 * > Design guidelines: [unified-design-system.orange.com](https://unified-design-system.orange.com/472794e18/p/31c33b-link)
 *
 * > Design version: 2.2.0
 *
 * @param label Label describing what is being linked to.
 * @param onClick Callback invoked when the link is clicked.
 * @param modifier [Modifier] applied to the link.
 * @param size Size of the link. See [OudsLinkSize] for available sizes.
 * @param enabled Controls the enabled state of the link. When `false`, the link will not be clickable.
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
) {
    OudsLink(
        label = label,
        icon = null,
        arrow = null,
        onClick = onClick,
        modifier = modifier,
        size = size,
        enabled = enabled,
    )
}

/**
 * Links are interactive elements that allow users to navigate to a new screen, website, or a specific section within the current screen.
 *
 * Note that in the case it is placed in an [OudsColoredBox], its monochrome variant is automatically displayed.
 * The tokens associated with this variant can be customized by overriding [OudsLinkMonoTokens].
 *
 * > Design guidelines: [unified-design-system.orange.com](https://unified-design-system.orange.com/472794e18/p/31c33b-link)
 *
 * > Design version: 2.2.0
 *
 * @param label Label describing what is being linked to.
 * @param icon Icon displayed in the link that can be used to indicate the destination or type of content being referenced.
 * @param onClick Callback invoked when the link is clicked.
 * @param modifier [Modifier] applied to the link.
 * @param size Size of the link. See [OudsLinkSize] for available sizes.
 * @param enabled Controls the enabled state of the link. When `false`, the link will not be clickable.
 *
 * @sample com.orange.ouds.core.component.samples.OudsLinkWithIconSample
 */
@Composable
fun OudsLink(
    label: String,
    icon: OudsLinkIcon,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: OudsLinkSize = OudsLinkDefaults.Size,
    enabled: Boolean = true,
) {
    OudsLink(
        label = label,
        icon = icon,
        arrow = null,
        onClick = onClick,
        modifier = modifier,
        size = size,
        enabled = enabled,
    )
}

/**
 * An OUDS link which displays an [arrow] before ([OudsLinkArrow.Back]) or after ([OudsLinkArrow.Next]) a label.
 *
 * In the case it is used in an [OudsColoredBox], its monochrome variant is automatically displayed.
 * The tokens associated with this variant can be customized by overriding [OudsLinkMonoTokens].
 *
 * > Design guidelines: [unified-design-system.orange.com](https://unified-design-system.orange.com/472794e18/p/31c33b-link)
 *
 * > Design version: 2.2.0
 *
 * @param label Label describing what is being linked to.
 * @param arrow Navigation arrow displayed in the link. See [OudsLinkArrow] for allowed values.
 * @param onClick Callback invoked when the link is clicked.
 * @param modifier [Modifier] applied to the link.
 * @param size Size of the link. See [OudsLinkSize] for available sizes.
 * @param enabled Controls the enabled state of the link. When `false`, the link will not be clickable.
 *
 * @sample com.orange.ouds.core.component.samples.OudsLinkWithArrowSample
 */
@Composable
fun OudsLink(
    label: String,
    arrow: OudsLinkArrow,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: OudsLinkSize = OudsLinkDefaults.Size,
    enabled: Boolean = true,
) {
    OudsLink(
        label = label,
        icon = null,
        arrow = arrow,
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
    arrow: OudsLinkArrow?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: OudsLinkSize = OudsLinkDefaults.Size,
    enabled: Boolean = true
) {
    val linkTokens = OudsTheme.componentsTokens.link
    val interactionSource = remember { MutableInteractionSource() }
    val interactionState by interactionSource.collectInteractionStateAsState()
    val state = getLinkState(enabled = enabled, interactionState = interactionState)
    val isTextOnly = icon == null && arrow == null

    val (minWidth, minHeight) = when (size) {
        OudsLinkSize.Default -> linkTokens.sizeMinWidthDefault.value to linkTokens.sizeMinHeightDefault.value
        OudsLinkSize.Small -> linkTokens.sizeMinWidthSmall.dp to linkTokens.sizeMinHeightSmall.dp
    }

    val monochrome = LocalColorMode.current?.monochrome == true
    val contentColor = rememberInteractionColor(interactionState = interactionState) { linkInteractionState ->
        val linkState = getLinkState(enabled = enabled, interactionState = linkInteractionState)
        contentColor(state = linkState, monochrome = monochrome)
    }

    val arrowColor = rememberInteractionColor(interactionState = interactionState) { linkInteractionState ->
        val linkState = getLinkState(enabled = enabled, interactionState = linkInteractionState)
        arrowColor(state = linkState, monochrome = monochrome)
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
            .widthIn(min = minWidth)
            .heightIn(min = minHeight)
            .outerBorder(state = state)
            .padding(horizontal = linkTokens.spacePaddingInline.value, vertical = linkTokens.spacePaddingBlock.value)
            .semantics {
                role = Role.Button
            }
            .clickable(
                interactionSource = interactionSource,
                indication = InteractionValuesIndication(contentColor, arrowColor, isUnderlined),
                enabled = state != OudsLinkState.Disabled,
                onClick = onClick
            ),
        contentAlignment = Alignment.CenterStart
    ) {
        val columnGap: Dp
        val iconSize: Dp
        var textStyle: TextStyle
        with(linkTokens) {
            when (size) {
                OudsLinkSize.Default -> {
                    columnGap = if (arrow != null) spaceColumnGapChevronDefault.value else spaceColumnGapIconDefault.value
                    iconSize = sizeIconDefault.value
                    textStyle = OudsTheme.typography.label.strong.large
                }
                OudsLinkSize.Small -> {
                    columnGap = if (arrow != null) spaceColumnGapChevronSmall.value else spaceColumnGapIconSmall.value
                    iconSize = sizeIconSmall.value
                    textStyle = OudsTheme.typography.label.strong.medium
                }
            }
        }

        if (isUnderlined.value) {
            textStyle = textStyle.copy(textDecoration = TextDecoration.Underline)
        }

        val iconTint = if (arrow != null) arrowColor.value else contentColor.value

        Row(
            horizontalArrangement = Arrangement.spacedBy(columnGap),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (icon != null || arrow == OudsLinkArrow.Back) {
                icon.orElse { OudsLinkIcon(painterResource(OudsTheme.drawableResources.chevronLeft)) }.Content(
                    modifier = Modifier.size(iconSize),
                    extraParameters = ExtraParameters(tint = iconTint)
                )
            }
            Text(
                modifier = Modifier.weight(1f, fill = false),
                text = label,
                color = contentColor.value,
                style = textStyle.run {
                    copy(lineHeightStyle = lineHeightStyle?.copy(alignment = LineHeightStyle.Alignment.Center))
                }
            )
            if (arrow == OudsLinkArrow.Next) {
                OudsLinkIcon(painterResource(OudsTheme.drawableResources.chevronLeft)).Content(
                    modifier = Modifier
                        .size(iconSize)
                        .rotate(180f)
                        .fillMaxHeight()
                        .align(Alignment.Bottom),
                    extraParameters = ExtraParameters(tint = iconTint)
                )
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
private fun contentColor(state: OudsLinkState, monochrome: Boolean): Color {
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
private fun arrowColor(state: OudsLinkState, monochrome: Boolean): Color {
    return with(OudsTheme.componentsTokens.link) {
        if (monochrome) {
            contentColor(state = state, monochrome = true)
        } else {
            when (state) {
                OudsLinkState.Enabled -> colorChevronEnabled.value
                OudsLinkState.Focused -> colorChevronFocus.value
                OudsLinkState.Hovered -> colorChevronHover.value
                OudsLinkState.Pressed -> colorChevronPressed.value
                OudsLinkState.Disabled -> OudsTheme.colorScheme.action.disabled
            }
        }
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
     * of small elements ("In-line alert" component, for example).
     */
    Small
}

/**
 * Represents the type of arrow displayed in an [OudsLink].
 */
enum class OudsLinkArrow {
    /**
     * Used for "backward" navigation. This arrow is positioned before the label, it features a "chevron left" icon, which is not customizable.
     */
    Back,

    /**
     * Used in a standard navigation context. This arrow is positioned after the label, it features a "chevron right" icon, which is not customizable.
     */
    Next
}

/**
 * An icon in an [OudsLink].
 * This icon is non-clickable and no content description is needed because a link label is always present.
 */
open class OudsLinkIcon private constructor(
    graphicsObject: Any
) : OudsComponentIcon<ExtraParameters, OudsLinkIcon>(ExtraParameters::class.java, graphicsObject, "") {

    @ConsistentCopyVisibility
    data class ExtraParameters internal constructor(
        internal val tint: Color
    ) : OudsComponentContent.ExtraParameters()

    /**
     * Creates an instance of [OudsLinkIcon].
     *
     * @param painter Painter of the icon.
     */
    constructor(painter: Painter) : this(painter as Any)

    /**
     * Creates an instance of [OudsLinkIcon].
     *
     * @param imageVector Image vector of the icon.
     */
    constructor(imageVector: ImageVector) : this(imageVector as Any)

    /**
     * Creates an instance of [OudsLinkIcon].
     *
     * @param bitmap Image bitmap of the icon.
     */
    constructor(bitmap: ImageBitmap) : this(bitmap as Any)

    override val tint: Color?
        @Composable
        get() = extraParameters.tint
}

internal enum class OudsLinkState {
    Enabled, Hovered, Pressed, Disabled, Focused
}

@PreviewLightDark
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
            PreviewEnumEntries<OudsLinkState>(columnCount = 3) {
                OudsLink(
                    icon = icon,
                    label = "Label",
                    arrow = arrow,
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

@Preview
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsLinkOnTwoLines() = PreviewOudsLinkOnTwoLines(theme = getPreviewTheme())

@Composable
internal fun PreviewOudsLinkOnTwoLines(theme: OudsThemeContract) {
    OudsPreview(theme = theme) {
        val label = "Link\non two lines"
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            listOf(OudsLinkArrow.Back, OudsLinkArrow.Next).forEach { arrow ->
                OudsLink(
                    label = label,
                    arrow = arrow,
                    onClick = {},
                )
            }
        }
    }
}

internal data class OudsLinkPreviewParameter(
    val hasIcon: Boolean,
    val onColoredBackground: Boolean,
    val size: OudsLinkSize,
    val arrow: OudsLinkArrow? = null
)

internal class OudsLinkPreviewParameterProvider : BasicPreviewParameterProvider<OudsLinkPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsLinkPreviewParameter>
    get() = buildList {
        OudsLinkSize.entries.forEach { size ->
            add(OudsLinkPreviewParameter(hasIcon = false, onColoredBackground = false, size = size))
            add(OudsLinkPreviewParameter(hasIcon = false, arrow = OudsLinkArrow.Back, onColoredBackground = false, size = size))
            add(OudsLinkPreviewParameter(hasIcon = false, arrow = OudsLinkArrow.Next, onColoredBackground = false, size = size))
            add(OudsLinkPreviewParameter(hasIcon = true, onColoredBackground = false, size = size))
            add(OudsLinkPreviewParameter(hasIcon = false, onColoredBackground = true, size = size))
            add(OudsLinkPreviewParameter(hasIcon = false, arrow = OudsLinkArrow.Back, onColoredBackground = true, size = size))
            add(OudsLinkPreviewParameter(hasIcon = false, arrow = OudsLinkArrow.Next, onColoredBackground = true, size = size))
            add(OudsLinkPreviewParameter(hasIcon = true, onColoredBackground = true, size = size))
        }
    }

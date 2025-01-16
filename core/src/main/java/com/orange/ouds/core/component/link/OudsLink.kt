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

package com.orange.ouds.core.component.link

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.R
import com.orange.ouds.core.component.coloredbox.OudsColoredBox
import com.orange.ouds.core.component.content.OudsComponentContent
import com.orange.ouds.core.component.content.OudsComponentIcon
import com.orange.ouds.core.component.link.OudsLink.Icon.ExtraParameters
import com.orange.ouds.core.extensions.InteractionState
import com.orange.ouds.core.extensions.collectInteractionStateAsState
import com.orange.ouds.core.theme.LocalMonoComponents
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.foundation.utilities.UiModePreviews
import com.orange.ouds.theme.outerBorder
import com.orange.ouds.theme.tokens.OudsBorderKeyToken
import com.orange.ouds.theme.tokens.OudsColorKeyToken
import com.orange.ouds.theme.tokens.OudsTypographyKeyToken
import com.orange.ouds.theme.tokens.components.OudsLinkTokens

/**
 * An OUDS link which displays a text and an optional icon.
 *
 * In the case it is used in an [OudsColoredBox], its monochrome variant is automatically displayed.
 * The tokens associated with this variant can be customized and are identified with the `Mono` suffix (for instance `colorContentEnabledMono`
 * in [OudsLinkTokens]).
 *
 * @sample com.orange.ouds.core.component.samples.OudsLinkSample
 *
 * @param text Text displayed in the link.
 * @param icon Icon displayed in the link.
 * @param onClick Callback invoked when the link is clicked.
 * @param modifier [Modifier] applied to the link.
 * @param size Size of the button.
 * @param enabled Controls the enabled state of the link. When `false`, the link will not be clickable.
 */
@Composable
fun OudsLink(
    text: String,
    icon: OudsLink.Icon?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: OudsLink.Size = OudsLinkDefaults.Size,
    enabled: Boolean = true,
) {
    OudsLink(
        text = text,
        icon = icon,
        arrow = null,
        onClick = onClick,
        modifier = modifier,
        size = size,
        enabled = enabled,
    )
}

/**
 * An OUDS link which displays an [arrow] before ([OudsLink.Arrow.Back]) or after ([OudsLink.Arrow.Next]) a text.
 *
 * In the case it is used in an [OudsColoredBox], its monochrome variant is automatically displayed.
 * The tokens associated with this variant can be customized and are identified with the `Mono` suffix (for instance `colorContentEnabledMono`
 * in [OudsLinkTokens]).
 *
 * @sample com.orange.ouds.core.component.samples.OudsLinkWithArrowSample
 *
 * @param text Text displayed in the link.
 * @param arrow Arrow displayed in the link.
 *   When [OudsLink.Arrow.Back], the arrow is displayed before the text.
 *   When [OudsLink.Arrow.Next], the arrow is displayed after the text.
 * @param onClick Callback invoked when the link is clicked.
 * @param modifier [Modifier] applied to the link.
 * @param size Size of the button.
 * @param enabled Controls the enabled state of the link. When `false`, the link will not be clickable.
 */
@Composable
fun OudsLink(
    text: String,
    arrow: OudsLink.Arrow,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: OudsLink.Size = OudsLinkDefaults.Size,
    enabled: Boolean = true,
) {
    OudsLink(
        text = text,
        icon = null,
        arrow = arrow,
        onClick = onClick,
        modifier = modifier,
        size = size,
        enabled = enabled,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun OudsLink(
    text: String,
    icon: OudsLink.Icon?,
    arrow: OudsLink.Arrow?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: OudsLink.Size = OudsLinkDefaults.Size,
    enabled: Boolean = true,
    previewState: OudsLink.State? = null
) {
    val linkTokens = OudsTheme.componentsTokens.link
    val interactionSource = remember { MutableInteractionSource() }
    val interactionState by interactionSource.collectInteractionStateAsState()
    val state = previewState.orElse { rememberOudsLinkState(enabled = enabled, interactionState = interactionState) }
    val isTextOnly = icon == null && arrow == null

    val minWidth: Dp
    val minHeight: Dp
    when (size) {
        OudsLink.Size.Medium -> {
            minWidth = linkTokens.sizeMinWidthMedium.dp
            minHeight = linkTokens.sizeMinHeightMedium.dp
        }
        OudsLink.Size.Small -> {
            minWidth = linkTokens.sizeMinWidthSmall.dp
            minHeight = linkTokens.sizeMinHeightSmall.dp
        }
    }

    CompositionLocalProvider(LocalRippleConfiguration provides null) {
        Button(
            onClick = onClick,
            modifier = modifier
                .widthIn(min = minWidth)
                .heightIn(min = minHeight)
                .outerBorder(state = state),
            enabled = state != OudsLink.State.Disabled,
            shape = RectangleShape,
            colors = buttonColors(linkState = state, monochrome = LocalMonoComponents.current),
            elevation = null,
            contentPadding = PaddingValues(
                horizontal = linkTokens.spacePaddingInline.value,
                vertical = linkTokens.spacePaddingBlock.value
            ),
            interactionSource = interactionSource
        ) {
            val columnGap: Dp
            val iconSize: Dp
            var textStyle: TextStyle
            with(linkTokens) {
                when (size) {
                    OudsLink.Size.Medium -> {
                        columnGap = if (arrow != null) spaceColumnGapArrowMedium.value else spaceColumnGapIconMedium.value
                        iconSize = sizeIconMedium.value
                        textStyle = OudsTypographyKeyToken.Label.Strong.Large.value
                    }
                    OudsLink.Size.Small -> {
                        columnGap = if (arrow != null) spaceColumnGapArrowSmall.value else spaceColumnGapIconSmall.value
                        iconSize = sizeIconSmall.value
                        textStyle = OudsTypographyKeyToken.Label.Strong.Medium.value
                    }
                }
            }

            if (isTextOnly || state in listOf(OudsLink.State.Hovered, OudsLink.State.Pressed, OudsLink.State.Focused)) {
                textStyle = textStyle.copy(textDecoration = TextDecoration.Underline)
            }

            val iconTint = if (arrow != null) {
                arrowColor(state = state, monochrome = LocalMonoComponents.current)
            } else {
                contentColor(state = state, monochrome = LocalMonoComponents.current)
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(columnGap),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (icon != null || arrow == OudsLink.Arrow.Back) {
                    (icon ?: OudsLink.Icon(painterResource(R.drawable.ic_form_chevron_left))).Content(
                        modifier = Modifier.size(iconSize),
                        extraParameters = ExtraParameters(tint = iconTint)
                    )
                }
                Text(
                    modifier = modifier,
                    text = text,
                    style = textStyle
                )
                if (arrow == OudsLink.Arrow.Next) {
                    OudsLink.Icon(painterResource(R.drawable.ic_form_chevron_left)).Content(
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
}

@Composable
private fun rememberOudsLinkState(
    enabled: Boolean,
    interactionState: InteractionState
): OudsLink.State = remember(enabled, interactionState) {
    when {
        !enabled -> OudsLink.State.Disabled
        interactionState == InteractionState.Hovered -> OudsLink.State.Hovered
        interactionState == InteractionState.Pressed -> OudsLink.State.Pressed
        interactionState == InteractionState.Focused -> OudsLink.State.Focused
        else -> OudsLink.State.Enabled
    }
}

@Composable
private fun Modifier.outerBorder(state: OudsLink.State): Modifier {
    return if (state == OudsLink.State.Focused) {
        outerBorder(
            width = OudsBorderKeyToken.Width.Focus.value,
            color = OudsColorKeyToken.Border.Focus.value,
            shape = RectangleShape,
            insetWidth = OudsBorderKeyToken.Width.FocusInset.value,
            insetColor = OudsColorKeyToken.Border.FocusInset.value
        )
    } else {
        this
    }
}

@Composable
private fun buttonColors(linkState: OudsLink.State, monochrome: Boolean) = ButtonDefaults.buttonColors(
    containerColor = containerColor,
    contentColor = contentColor(state = linkState, monochrome = monochrome),
    disabledContainerColor = containerColor,
    disabledContentColor = contentColor(state = linkState, monochrome = monochrome)
)

private val containerColor: Color
    @Composable
    get() = OudsColorKeyToken.Opacity.Transparent.value

@Composable
private fun contentColor(state: OudsLink.State, monochrome: Boolean): Color {
    return with(OudsTheme.componentsTokens.link) {
        if (monochrome) {
            when (state) {
                OudsLink.State.Enabled -> colorContentEnabledMono
                OudsLink.State.Focused -> colorContentFocusMono
                OudsLink.State.Hovered -> colorContentHoverMono
                OudsLink.State.Pressed -> colorContentPressedMono
                OudsLink.State.Disabled -> colorContentDisabledMono
            }
        } else {
            when (state) {
                OudsLink.State.Enabled -> colorContentEnabled
                OudsLink.State.Focused -> colorContentFocus
                OudsLink.State.Hovered -> colorContentHover
                OudsLink.State.Pressed -> colorContentPressed
                OudsLink.State.Disabled -> OudsColorKeyToken.Action.Disabled
            }
        }
    }.value
}

@Composable
private fun arrowColor(state: OudsLink.State, monochrome: Boolean): Color {
    return with(OudsTheme.componentsTokens.link) {
        if (monochrome) {
            contentColor(state = state, monochrome = true)
        } else {
            when (state) {
                OudsLink.State.Enabled -> colorArrowEnabled
                OudsLink.State.Focused -> colorArrowFocus
                OudsLink.State.Hovered -> colorArrowHover
                OudsLink.State.Pressed -> colorArrowPressed
                OudsLink.State.Disabled -> OudsColorKeyToken.Action.Disabled
            }.value
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
    val Size = OudsLink.Size.Medium

}

/**
 * Contains classes to build an [com.orange.ouds.core.component.link.OudsLink].
 */
object OudsLink {

    /**
     * Represents the size of an OUDS link.
     */
    enum class Size {
        Small, Medium
    }


    /**
     * Represents the arrow of an OUDS link.
     */
    enum class Arrow {
        Back, Next
    }

    /**
     * A link icon in an [OudsLink].
     * It is non-clickable and no content description is needed cause a link label is always present.
     */
    open class Icon private constructor(
        graphicsObject: Any
    ) : OudsComponentIcon<ExtraParameters>(ExtraParameters::class.java, graphicsObject, "") {

        @ConsistentCopyVisibility
        data class ExtraParameters internal constructor(
            internal val tint: Color
        ) : OudsComponentContent.ExtraParameters()

        /**
         * Creates an instance of [OudsLink.Icon].
         *
         * @param painter Painter of the icon.
         */
        constructor(painter: Painter) : this(painter as Any)

        /**
         * Creates an instance of [OudsLink.Icon].
         *
         * @param imageVector Image vector of the icon.
         */
        constructor(imageVector: ImageVector) : this(imageVector as Any)

        /**
         * Creates an instance of [OudsLink.Icon].
         *
         * @param bitmap Image bitmap of the icon.
         */
        constructor(bitmap: ImageBitmap) : this(bitmap as Any)

        override val tint: Color?
            @Composable
            get() = extraParameters.tint

        @Composable
        override fun Content(modifier: Modifier) {
            super.Content(modifier)
        }
    }

    internal enum class State {
        Enabled, Hovered, Pressed, Disabled, Focused
    }
}


@UiModePreviews.Default
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsLink(@PreviewParameter(OudsLinkPreviewParameterProvider::class) parameter: OudsLinkPreviewParameter) {
    PreviewOudsLink(darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Preview
@Composable
internal fun PreviewOudsLinkOnTwoLines() {
    OudsPreview {
        val text = "Link\non two lines"
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            listOf(OudsLink.Arrow.Back, OudsLink.Arrow.Next).forEach { arrow ->
                OudsLink(
                    text = text,
                    arrow = arrow,
                    onClick = {},
                )
            }
        }
    }
}


@Composable
internal fun PreviewOudsLink(
    darkThemeEnabled: Boolean,
    parameter: OudsLinkPreviewParameter
) = OudsPreview(darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        val icon = if (hasIcon) OudsLink.Icon(painter = painterResource(id = android.R.drawable.star_on)) else null
        val chunkedStates = states.chunked(2)
        val linkPreview: @Composable () -> Unit = {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                chunkedStates.forEach { states ->
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        states.forEach { state ->
                            OudsLink(
                                icon = icon,
                                text = "Label",
                                arrow = arrow,
                                onClick = {},
                                size = size,
                                previewState = state
                            )
                        }
                    }
                }
            }
        }

        val boxModifier = Modifier.padding(16.dp)
        if (onColoredBackground) {
            OudsColoredBox(color = OudsColoredBox.Color.BrandPrimary, modifier = boxModifier) {
                linkPreview()
            }
        } else {
            Box(modifier = boxModifier) {
                linkPreview()
            }
        }

    }
}

internal data class OudsLinkPreviewParameter(
    val hasIcon: Boolean,
    val onColoredBackground: Boolean,
    val size: OudsLink.Size,
    val arrow: OudsLink.Arrow? = null,
    val states: List<OudsLink.State> = listOf(
        OudsLink.State.Enabled,
        OudsLink.State.Hovered,
        OudsLink.State.Pressed,
        OudsLink.State.Disabled,
        OudsLink.State.Focused
    )
)

internal class OudsLinkPreviewParameterProvider : BasicPreviewParameterProvider<OudsLinkPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsLinkPreviewParameter>
    get() = buildList {
        OudsLink.Size.entries.forEach { size ->
            add(OudsLinkPreviewParameter(hasIcon = false, onColoredBackground = false, size = size))
            add(OudsLinkPreviewParameter(hasIcon = false, arrow = OudsLink.Arrow.Back, onColoredBackground = false, size = size))
            add(OudsLinkPreviewParameter(hasIcon = false, arrow = OudsLink.Arrow.Next, onColoredBackground = false, size = size))
            add(OudsLinkPreviewParameter(hasIcon = true, onColoredBackground = false, size = size))
            add(OudsLinkPreviewParameter(hasIcon = false, onColoredBackground = true, size = size))
            add(OudsLinkPreviewParameter(hasIcon = false, arrow = OudsLink.Arrow.Back, onColoredBackground = true, size = size))
            add(OudsLinkPreviewParameter(hasIcon = false, arrow = OudsLink.Arrow.Next, onColoredBackground = true, size = size))
            add(OudsLinkPreviewParameter(hasIcon = true, onColoredBackground = true, size = size))
        }
    }
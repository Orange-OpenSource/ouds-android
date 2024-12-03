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

package com.orange.ouds.core.component.button

import android.os.Parcelable
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.component.content.OudsComponentContent
import com.orange.ouds.core.component.content.OudsComponentIcon
import com.orange.ouds.core.extensions.InteractionState
import com.orange.ouds.core.extensions.collectInteractionStateAsState
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
import kotlinx.parcelize.Parcelize

/**
 * An OUDS button which displays only text.
 *
 * @param text Text displayed in the button.
 * @param onClick Callback invoked when the button is clicked.
 * @param modifier [Modifier] applied to the button.
 * @param enabled Controls the enabled state of the button when [style] is equal to [OudsButton.Style.Default].
 *   When `false`, this button will not be clickable.
 *   Has no effect when [style] is equal to [OudsButton.Style.Loading] or [OudsButton.Style.Skeleton].
 * @param style The button style.
 * @param hierarchy The button hierarchy.
 */
@Composable
fun OudsButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    style: OudsButton.Style = OudsButtonDefaults.Style,
    hierarchy: OudsButton.Hierarchy = OudsButtonDefaults.Hierarchy
) {
    OudsButton(
        icon = null,
        text = text,
        onClick = onClick,
        previewState = null,
        modifier = modifier,
        enabled = enabled,
        style = style,
        hierarchy = hierarchy
    )
}

/**
 * An OUDS button which displays only an icon.
 *
 * @param icon Icon displayed in the button.
 * @param onClick Callback invoked when the button is clicked.
 * @param modifier [Modifier] applied to the button.
 * @param enabled Controls the enabled state of the button when [style] is equal to [OudsButton.Style.Default].
 *   When `false`, this button will not be clickable.
 *   Has no effect when [style] is equal to [OudsButton.Style.Loading] or [OudsButton.Style.Skeleton].
 * @param style The button style.
 * @param hierarchy The button hierarchy.
 */
@Composable
fun OudsButton(
    icon: OudsButton.Icon,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    style: OudsButton.Style = OudsButtonDefaults.Style,
    hierarchy: OudsButton.Hierarchy = OudsButtonDefaults.Hierarchy
) {
    OudsButton(
        icon = icon,
        text = null,
        onClick = onClick,
        previewState = null,
        modifier = modifier,
        enabled = enabled,
        style = style,
        hierarchy = hierarchy
    )
}

/**
 * An OUDS button which displays an icon and text.
 *
 * @param icon Icon displayed in the button.
 * @param text Text displayed in the button.
 * @param onClick Callback invoked when the button is clicked.
 * @param modifier [Modifier] applied to the button.
 * @param enabled Controls the enabled state of the button when [style] is equal to [OudsButton.Style.Default].
 *   When `false`, this button will not be clickable.
 *   Has no effect when [style] is equal to [OudsButton.Style.Loading] or [OudsButton.Style.Skeleton].
 * @param style The button style.
 * @param hierarchy The button hierarchy.
 */
@Composable
fun OudsButton(
    icon: OudsButton.Icon,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    style: OudsButton.Style = OudsButtonDefaults.Style,
    hierarchy: OudsButton.Hierarchy = OudsButtonDefaults.Hierarchy
) {
    OudsButton(
        icon = icon,
        text = text,
        onClick = onClick,
        previewState = null,
        modifier = modifier,
        enabled = enabled,
        style = style,
        hierarchy = hierarchy
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun OudsButton(
    icon: OudsButton.Icon?,
    text: String?,
    onClick: () -> Unit,
    previewState: OudsButton.State?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    style: OudsButton.Style = OudsButton.Style.Default,
    hierarchy: OudsButton.Hierarchy = OudsButtonDefaults.Hierarchy
) {
    val buttonTokens = OudsTheme.componentsTokens.button
    val interactionSource = remember { MutableInteractionSource() }
    val interactionState by interactionSource.collectInteractionStateAsState()
    val state = previewState.orElse { rememberOudsButtonState(enabled = enabled, style = style, interactionState = interactionState) }
    val maxHeight = if (icon != null && text == null) buttonTokens.sizeMaxHeight.dp else Dp.Unspecified
    val shape = RoundedCornerShape(buttonTokens.borderRadius.value)

    CompositionLocalProvider(LocalRippleConfiguration provides null) {
        Button(
            onClick = onClick,
            modifier = modifier
                .widthIn(min = buttonTokens.sizeMinWidth.dp)
                .heightIn(min = buttonTokens.sizeMinHeight.dp, max = maxHeight)
                .border(hierarchy = hierarchy, state = state, shape = shape),
            enabled = state !in remember { listOf(OudsButton.State.Disabled, OudsButton.State.Loading, OudsButton.State.Skeleton) },
            shape = shape,
            colors = buttonColors(hierarchy = hierarchy, buttonState = state),
            elevation = null,
            contentPadding = contentPadding(icon = icon, text = text),
            interactionSource = interactionSource
        ) {
            Box(contentAlignment = Alignment.Center) {
                val isLoadingIndicatorVisible = state == OudsButton.State.Loading
                if (isLoadingIndicatorVisible) {
                    val loadingStyle = style as? OudsButton.Style.Loading
                    val progress = if (previewState == OudsButton.State.Loading) 0.75f else loadingStyle?.progress
                    LoadingIndicator(hierarchy = hierarchy, progress)
                }

                Row(
                    modifier = Modifier.alpha(if (isLoadingIndicatorVisible) 0f else 1f),
                    horizontalArrangement = Arrangement.spacedBy(buttonTokens.spaceColumnGapIcon.value),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (icon != null) {
                        val size = if (text == null) buttonTokens.sizeIconOnly else buttonTokens.sizeIcon
                        val tint = contentColor(hierarchy = hierarchy, state = state)
                        icon.Content(
                            modifier = Modifier.size(size.value),
                            extraParameters = OudsButton.Icon.ExtraParameters(tint = tint)
                        )
                    }
                    if (text != null) {
                        Text(
                            modifier = modifier,
                            text = text,
                            style = OudsTypographyKeyToken.Label.Strong.Large.value
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun rememberOudsButtonState(
    enabled: Boolean,
    style: OudsButton.Style,
    interactionState: InteractionState
): OudsButton.State = remember(enabled, style, interactionState) {
    when (style) {
        OudsButton.Style.Default -> when {
            !enabled -> OudsButton.State.Disabled
            interactionState == InteractionState.Hovered -> OudsButton.State.Hovered
            interactionState == InteractionState.Pressed -> OudsButton.State.Pressed
            interactionState == InteractionState.Focused -> OudsButton.State.Focused
            else -> OudsButton.State.Enabled
        }
        is OudsButton.Style.Loading -> OudsButton.State.Loading
        OudsButton.Style.Skeleton -> OudsButton.State.Skeleton
    }
}

@Composable
private fun Modifier.border(hierarchy: OudsButton.Hierarchy, state: OudsButton.State, shape: Shape): Modifier {
    val borderWidth = borderWidth(hierarchy = hierarchy, state = state)
    val borderColor = borderColor(hierarchy = hierarchy, state = state)

    return if (borderWidth != null && borderColor != null) {
        if (state != OudsButton.State.Focused) {
            border(width = borderWidth, color = borderColor, shape = shape)
        } else {
            outerBorder(
                width = borderWidth,
                color = borderColor,
                shape = shape,
                insetWidth = OudsBorderKeyToken.Width.FocusInset.value,
                insetColor = OudsColorKeyToken.Border.FocusInset.value
            )
        }
    } else {
        this
    }
}

@Composable
private fun borderWidth(hierarchy: OudsButton.Hierarchy, state: OudsButton.State): Dp? {
    return with(OudsTheme.componentsTokens.button) {
        when (hierarchy) {
            OudsButton.Hierarchy.Default -> when (state) {
                OudsButton.State.Enabled,
                OudsButton.State.Disabled -> borderWidthDefault
                OudsButton.State.Hovered,
                OudsButton.State.Pressed,
                OudsButton.State.Loading -> borderWidthDefaultInteraction
                OudsButton.State.Focused -> OudsBorderKeyToken.Width.Focus
                OudsButton.State.Skeleton -> null
            }
            OudsButton.Hierarchy.Minimal -> when (state) {
                OudsButton.State.Enabled,
                OudsButton.State.Disabled -> borderWidthMinimal
                OudsButton.State.Hovered,
                OudsButton.State.Pressed,
                OudsButton.State.Loading -> borderWidthMinimalInteraction
                OudsButton.State.Focused -> OudsBorderKeyToken.Width.Focus
                OudsButton.State.Skeleton -> null
            }
            OudsButton.Hierarchy.Strong,
            OudsButton.Hierarchy.Negative -> if (state == OudsButton.State.Focused) OudsBorderKeyToken.Width.Focus else null
        }?.value
    }
}

@Composable
private fun borderColor(hierarchy: OudsButton.Hierarchy, state: OudsButton.State): Color? {
    return with(OudsTheme.componentsTokens.button) {
        when (hierarchy) {
            OudsButton.Hierarchy.Default -> when (state) {
                OudsButton.State.Enabled -> colorBorderDefaultEnabled
                OudsButton.State.Hovered -> colorBorderDefaultHover
                OudsButton.State.Pressed -> colorBorderDefaultPressed
                OudsButton.State.Loading -> colorBorderDefaultLoading
                OudsButton.State.Disabled -> colorBorderDefaultDisabled
                OudsButton.State.Focused -> OudsColorKeyToken.Border.Focus
                OudsButton.State.Skeleton -> null
            }
            OudsButton.Hierarchy.Minimal -> when (state) {
                OudsButton.State.Enabled -> colorBorderMinimalEnabled
                OudsButton.State.Hovered -> colorBorderMinimalHover
                OudsButton.State.Pressed -> colorBorderMinimalPressed
                OudsButton.State.Loading -> colorBorderMinimalLoading
                OudsButton.State.Disabled -> colorBorderMinimalDisabled
                OudsButton.State.Focused -> OudsColorKeyToken.Border.Focus
                OudsButton.State.Skeleton -> null
            }
            OudsButton.Hierarchy.Strong,
            OudsButton.Hierarchy.Negative -> if (state == OudsButton.State.Focused) OudsColorKeyToken.Border.Focus else null
        }?.value
    }
}

@Composable
private fun buttonColors(hierarchy: OudsButton.Hierarchy, buttonState: OudsButton.State): ButtonColors {
    return ButtonDefaults.buttonColors(
        containerColor = containerColor(hierarchy = hierarchy, state = buttonState),
        contentColor = contentColor(hierarchy = hierarchy, state = buttonState),
        disabledContainerColor = containerColor(hierarchy = hierarchy, state = buttonState),
        disabledContentColor = contentColor(hierarchy = hierarchy, state = buttonState)
    )
}

@Composable
private fun containerColor(hierarchy: OudsButton.Hierarchy, state: OudsButton.State): Color {
    return with(OudsTheme.componentsTokens.button) {
        when (hierarchy) {
            OudsButton.Hierarchy.Default -> when (state) {
                OudsButton.State.Enabled -> colorBgDefaultEnabled.value
                OudsButton.State.Focused -> colorBgDefaultFocus.value
                OudsButton.State.Hovered -> colorBgDefaultHover.value
                OudsButton.State.Pressed -> colorBgDefaultPressed.value
                OudsButton.State.Loading -> colorBgDefaultLoading.value
                OudsButton.State.Disabled -> colorBgDefaultDisabled.value
                OudsButton.State.Skeleton -> OudsTheme.componentsTokens.skeleton.colorBg
            }
            OudsButton.Hierarchy.Minimal -> when (state) {
                OudsButton.State.Enabled -> colorBgMinimalEnabled.value
                OudsButton.State.Focused -> colorBgMinimalFocus.value
                OudsButton.State.Hovered -> colorBgMinimalHover.value
                OudsButton.State.Pressed -> colorBgMinimalPressed.value
                OudsButton.State.Loading -> colorBgMinimalLoading.value
                OudsButton.State.Disabled -> colorBgMinimalDisabled.value
                OudsButton.State.Skeleton -> OudsTheme.componentsTokens.skeleton.colorBg
            }
            OudsButton.Hierarchy.Strong -> when (state) {
                OudsButton.State.Enabled -> OudsColorKeyToken.Action.Enabled.value
                OudsButton.State.Focused -> OudsColorKeyToken.Action.Focus.value
                OudsButton.State.Hovered -> OudsColorKeyToken.Action.Hover.value
                OudsButton.State.Pressed -> OudsColorKeyToken.Action.Pressed.value
                OudsButton.State.Loading -> OudsColorKeyToken.Action.Loading.value
                OudsButton.State.Disabled -> OudsColorKeyToken.Action.Disabled.value
                OudsButton.State.Skeleton -> OudsTheme.componentsTokens.skeleton.colorBg
            }
            OudsButton.Hierarchy.Negative -> when (state) {
                OudsButton.State.Enabled -> OudsColorKeyToken.Action.Negative.Enabled.value
                OudsButton.State.Focused -> OudsColorKeyToken.Action.Negative.Focus.value
                OudsButton.State.Hovered -> OudsColorKeyToken.Action.Negative.Hover.value
                OudsButton.State.Pressed -> OudsColorKeyToken.Action.Negative.Pressed.value
                OudsButton.State.Loading -> OudsColorKeyToken.Action.Negative.Loading.value
                OudsButton.State.Disabled -> OudsColorKeyToken.Action.Disabled.value
                OudsButton.State.Skeleton -> OudsTheme.componentsTokens.skeleton.colorBg
            }
        }
    }
}

@Composable
private fun contentColor(hierarchy: OudsButton.Hierarchy, state: OudsButton.State): Color {
    return with(OudsTheme.componentsTokens.button) {
        when (hierarchy) {
            OudsButton.Hierarchy.Default -> when (state) {
                OudsButton.State.Enabled -> colorContentDefaultEnabled.value
                OudsButton.State.Focused -> colorContentDefaultFocus.value
                OudsButton.State.Hovered -> colorContentDefaultHover.value
                OudsButton.State.Pressed -> colorContentDefaultPressed.value
                OudsButton.State.Loading -> colorContentDefaultLoading.value
                OudsButton.State.Disabled -> colorContentDefaultDisabled.value
                OudsButton.State.Skeleton -> OudsTheme.componentsTokens.skeleton.colorBg
            }
            OudsButton.Hierarchy.Minimal -> when (state) {
                OudsButton.State.Enabled -> colorContentMinimalEnabled.value
                OudsButton.State.Focused -> colorContentMinimalFocus.value
                OudsButton.State.Hovered -> colorContentMinimalHover.value
                OudsButton.State.Pressed -> colorContentMinimalPressed.value
                OudsButton.State.Loading -> colorContentMinimalLoading.value
                OudsButton.State.Disabled -> colorContentMinimalDisabled.value
                OudsButton.State.Skeleton -> OudsTheme.componentsTokens.skeleton.colorBg
            }
            OudsButton.Hierarchy.Strong -> when (state) {
                OudsButton.State.Enabled -> OudsColorKeyToken.Content.OnAction.Enabled.value
                OudsButton.State.Focused -> OudsColorKeyToken.Content.OnAction.Focus.value
                OudsButton.State.Hovered -> OudsColorKeyToken.Content.OnAction.Hover.value
                OudsButton.State.Pressed -> OudsColorKeyToken.Content.OnAction.Pressed.value
                OudsButton.State.Loading -> OudsColorKeyToken.Content.OnAction.Loading.value
                OudsButton.State.Disabled -> OudsColorKeyToken.Content.OnAction.Disabled.value
                OudsButton.State.Skeleton -> OudsTheme.componentsTokens.skeleton.colorBg
            }
            OudsButton.Hierarchy.Negative -> when (state) {
                OudsButton.State.Enabled,
                OudsButton.State.Hovered,
                OudsButton.State.Pressed,
                OudsButton.State.Loading,
                OudsButton.State.Focused -> OudsColorKeyToken.Content.OnAction.Negative.value
                OudsButton.State.Disabled -> OudsColorKeyToken.Content.OnAction.Disabled.value
                OudsButton.State.Skeleton -> OudsTheme.componentsTokens.skeleton.colorBg
            }
        }
    }
}

@Composable
private fun contentPadding(icon: OudsButton.Icon?, text: String?): PaddingValues {
    return with(OudsTheme.componentsTokens.button) {
        when {
            icon != null && text != null -> PaddingValues(
                start = spacePaddingInlineIconStart.value,
                top = spacePaddingBlock.value,
                end = spacePaddingInlineEndIconStart.value,
                bottom = spacePaddingBlock.value
            )
            icon != null && text == null -> PaddingValues(
                horizontal = spaceInsetIconAlone.value,
                vertical = spacePaddingBlock.value
            )
            else -> PaddingValues(
                horizontal = spacePaddingInlineIconNone.value,
                vertical = spacePaddingBlock.value
            )
        }
    }
}

@Composable
private fun LoadingIndicator(hierarchy: OudsButton.Hierarchy, progress: Float?) {
    val modifier = Modifier.size(OudsTheme.componentsTokens.button.sizeLoader.value)
    val color = contentColor(hierarchy = hierarchy, state = OudsButton.State.Loading)
    val strokeWidth = 3.dp
    val trackColor = Color.Transparent
    val strokeCap = StrokeCap.Square
    if (progress != null) {
        CircularProgressIndicator(
            progress = { progress },
            modifier = modifier,
            color = color,
            strokeWidth = strokeWidth,
            trackColor = trackColor,
            strokeCap = strokeCap
        )
    } else {
        CircularProgressIndicator(
            modifier = modifier,
            color = color,
            strokeWidth = strokeWidth,
            trackColor = trackColor,
            strokeCap = strokeCap
        )
    }
}

/**
 * Contains the default values used by OUDS buttons.
 */
object OudsButtonDefaults {

    /**
     * The default hierarchy.
     */
    val Hierarchy = OudsButton.Hierarchy.Default

    /**
     * The default style.
     */
    val Style = OudsButton.Style.Default
}

/**
 * Contains classes to build an [com.orange.ouds.core.component.button.OudsButton].
 */
object OudsButton {

    /**
     * A button icon in an [OudsButton].
     * It is non-clickable and no content description is needed cause a button label is always present.
     */
    class Icon private constructor(
        graphicsObject: Any
    ) : OudsComponentIcon<Icon.ExtraParameters>(ExtraParameters::class.java, graphicsObject, "") {

        @ConsistentCopyVisibility
        data class ExtraParameters internal constructor(
            internal val tint: Color
        ) : OudsComponentContent.ExtraParameters()

        /**
         * Creates an instance of [OudsButton.Icon].
         *
         * @param painter Painter of the icon.
         */
        constructor(painter: Painter) : this(painter as Any)

        /**
         * Creates an instance of [OudsButton.Icon].
         *
         * @param imageVector Image vector of the icon.
         */
        constructor(imageVector: ImageVector) : this(imageVector as Any)

        /**
         * Creates an instance of [OudsButton.Icon].
         *
         * @param bitmap Image bitmap of the icon.
         */
        constructor(bitmap: ImageBitmap) : this(bitmap as Any)

        override val tint: Color?
            @Composable
            get() = extraParameters.tint
    }

    /**
     * Represents the hierarchy of an OUDS button.
     */
    enum class Hierarchy {
        Default, Strong, Minimal, Negative
    }

    /**
     * Represents the different styles of an OUDS button.
     */
    sealed class Style : Parcelable {

        /**
         * The button displays an icon and/or a text and supports user interactions if it is enabled.
         */
        @Parcelize
        data object Default : Style()

        /**
         * The button displays a circular loading indicator.
         *
         * @param progress The loading progress, where 0.0 represents no progress and 1.0 represents full progress.
         *   Values outside of this range are coerced into the range.
         *   Set this value to `null` to display a circular indeterminate progress indicator.
         */
        @Parcelize
        data class Loading(val progress: Float?) : Style()

        /**
         * The button displays a skeleton.
         */
        @Parcelize
        data object Skeleton : Style()
    }

    internal enum class State {
        Enabled, Hovered, Pressed, Loading, Disabled, Focused, Skeleton
    }
}

@UiModePreviews.Default
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsButton(@PreviewParameter(OudsButtonPreviewParameterProvider::class) parameter: OudsButtonPreviewParameter) {
    PreviewOudsButton(darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsButton(
    darkThemeEnabled: Boolean,
    parameter: OudsButtonPreviewParameter
) = OudsPreview(modifier = Modifier.padding(16.dp), darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        val text = if (hasText) hierarchy.name else null
        val icon = if (hasIcon) OudsButton.Icon(painter = painterResource(id = android.R.drawable.star_on)) else null
        val chunkedStates = states.chunked(2)
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            chunkedStates.forEach { states ->
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    states.forEach { state ->
                        OudsButton(icon = icon, text = text, onClick = {}, hierarchy = hierarchy, previewState = state)
                    }
                }
            }
        }
    }
}

internal data class OudsButtonPreviewParameter(
    val hierarchy: OudsButton.Hierarchy,
    val hasText: Boolean,
    val hasIcon: Boolean,
    val states: List<OudsButton.State> = listOf(
        OudsButton.State.Enabled,
        OudsButton.State.Hovered,
        OudsButton.State.Pressed,
        OudsButton.State.Loading,
        OudsButton.State.Disabled,
        OudsButton.State.Focused,
        OudsButton.State.Skeleton
    )
)

internal class OudsButtonPreviewParameterProvider : BasicPreviewParameterProvider<OudsButtonPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsButtonPreviewParameter>
    get() = buildList {
        OudsButton.Hierarchy.entries.forEach { hierarchy ->
            add(OudsButtonPreviewParameter(hierarchy, hasText = true, hasIcon = false))
            add(OudsButtonPreviewParameter(hierarchy, hasText = true, hasIcon = true))
            add(OudsButtonPreviewParameter(hierarchy, hasText = false, hasIcon = true))
        }
    }

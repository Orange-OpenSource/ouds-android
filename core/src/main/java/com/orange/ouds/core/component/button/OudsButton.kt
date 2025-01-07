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
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.invisibleToUser
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.R
import com.orange.ouds.core.component.coloredbox.OudsColoredBox
import com.orange.ouds.core.component.content.OudsComponentContent
import com.orange.ouds.core.component.content.OudsComponentIcon
import com.orange.ouds.core.extensions.InteractionState
import com.orange.ouds.core.extensions.collectInteractionStateAsState
import com.orange.ouds.core.theme.LocalColoredBox
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.foundation.utilities.UiModePreviews
import com.orange.ouds.theme.dashedBorder
import com.orange.ouds.theme.outerBorder
import kotlinx.parcelize.Parcelize

/**
 * An OUDS button which displays text only.
 *
 * The colors of this button are automatically adjusted when it is displayed in an [OudsColoredBox].
 * Some tokens associated with these specific colors can be customized and are identified with the `Mono` suffix (for instance [OudsButtonTokens.colorBgDefaultEnabledMono]).
 *
 * @param text Text displayed in the button.
 * @param onClick Callback invoked when the button is clicked.
 * @param modifier [Modifier] applied to the button.
 * @param enabled Controls the enabled state of the button when [style] is equal to [OudsButton.Style.Default].
 *   When `false`, this button will not be clickable.
 *   Has no effect when [style] is equal to [OudsButton.Style.Loading].
 * @param style The button style.
 * @param hierarchy The button hierarchy. Please note that a button with [OudsButton.Hierarchy.Negative] hierarchy is not allowed in an [OudsColoredBox].
 *
 * @sample com.orange.ouds.core.component.samples.OudsButtonWithTextSample
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
 * An OUDS button which displays an icon only.
 *
 * The colors of this button are automatically adjusted when it is displayed in an [OudsColoredBox].
 * Some tokens associated with these specific colors can be customized and are identified with the `Mono` suffix (for instance [OudsButtonTokens.colorBgDefaultEnabledMono]).
 *
 * @param icon Icon displayed in the button.
 * @param onClick Callback invoked when the button is clicked.
 * @param modifier [Modifier] applied to the button.
 * @param enabled Controls the enabled state of the button when [style] is equal to [OudsButton.Style.Default].
 *   When `false`, this button will not be clickable.
 *   Has no effect when [style] is equal to [OudsButton.Style.Loading].
 * @param style The button style.
 * @param hierarchy The button hierarchy. Please note that a button with [OudsButton.Hierarchy.Negative] hierarchy is not allowed in an [OudsColoredBox].
 *
 * @sample com.orange.ouds.core.component.samples.OudsButtonWithIconSample
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
 * The colors of this button are automatically adjusted when it is displayed in an [OudsColoredBox].
 * Some tokens associated with these specific colors can be customized and are identified with the `Mono` suffix (for instance [OudsButtonTokens.colorBgDefaultEnabledMono]).
 *
 * @param icon Icon displayed in the button.
 * @param text Text displayed in the button.
 * @param onClick Callback invoked when the button is clicked.
 * @param modifier [Modifier] applied to the button.
 * @param enabled Controls the enabled state of the button when [style] is equal to [OudsButton.Style.Default].
 *   When `false`, this button will not be clickable.
 *   Has no effect when [style] is equal to [OudsButton.Style.Loading].
 * @param style The button style.
 * @param hierarchy The button hierarchy. Please note that a button with [OudsButton.Hierarchy.Negative] hierarchy is not allowed in an [OudsColoredBox].
 *
 * @sample com.orange.ouds.core.component.samples.OudsButtonWithIconAndTextSample
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
    val isForbidden = hierarchy == OudsButton.Hierarchy.Negative && LocalColoredBox.current
    val displayedIcon = when {
        isForbidden && icon != null && text == null -> OudsButton.Icon(painterResource(id = R.drawable.ic_error_severe), "")
        isForbidden -> null
        else -> icon
    }
    val displayedText = if (isForbidden && text != null) stringResource(id = R.string.core_button_notOnColoredBackground_label) else text
    val buttonTokens = OudsTheme.componentsTokens.button
    val interactionSource = remember { MutableInteractionSource() }
    val interactionState by interactionSource.collectInteractionStateAsState()
    val state = previewState.orElse { rememberOudsButtonState(enabled = enabled, style = style, interactionState = interactionState) }
    val iconScale = if (displayedIcon != null && displayedText == null) LocalContext.current.resources.configuration.fontScale else 1.0f
    val maxHeight = if (displayedIcon != null && displayedText == null) buttonTokens.sizeMaxHeight.dp * iconScale else Dp.Unspecified
    val shape = RoundedCornerShape(buttonTokens.borderRadius.value)

    CompositionLocalProvider(LocalRippleConfiguration provides null) {
        val isLoadingIndicatorVisible = state == OudsButton.State.Loading && !isForbidden
        val stateDescription = if (isLoadingIndicatorVisible) stringResource(id = R.string.core_button_loading_a11y) else ""
        Button(
            onClick = onClick,
            modifier = modifier
                .widthIn(min = buttonTokens.sizeMinWidth.dp)
                .heightIn(min = buttonTokens.sizeMinHeight.dp, max = maxHeight)
                .border(hierarchy = hierarchy, state = state, shape = shape)
                .outerBorder(hierarchy = hierarchy, state = state, shape = shape)
                .semantics {
                    this.stateDescription = stateDescription
                },
            enabled = state !in remember { listOf(OudsButton.State.Disabled, OudsButton.State.Loading) },
            shape = shape,
            colors = buttonColors(hierarchy = hierarchy, buttonState = state),
            elevation = null,
            contentPadding = contentPadding(icon = displayedIcon, text = displayedText),
            interactionSource = interactionSource
        ) {
            Box(contentAlignment = Alignment.Center) {
                if (isLoadingIndicatorVisible) {
                    val loadingStyle = style as? OudsButton.Style.Loading
                    val progress = if (previewState == OudsButton.State.Loading) 0.75f else loadingStyle?.progress
                    LoadingIndicator(hierarchy = hierarchy, progress = progress, scale = iconScale)
                }

                val alpha = if (isLoadingIndicatorVisible) 0f else 1f
                Row(
                    modifier = Modifier.alpha(alpha = alpha),
                    horizontalArrangement = Arrangement.spacedBy(buttonTokens.spaceColumnGapIcon.value),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (displayedIcon != null) {
                        val size = if (displayedText == null) buttonTokens.sizeIconOnly else buttonTokens.sizeIcon
                        val tint = contentColor(hierarchy = hierarchy, state = state)
                        displayedIcon.Content(
                            modifier = Modifier
                                .size(size.value * iconScale)
                                .semantics {
                                    contentDescription = if (displayedText == null) displayedIcon.contentDescription else ""
                                },
                            extraParameters = OudsButton.Icon.ExtraParameters(tint = tint)
                        )
                    }
                    if (displayedText != null) {
                        Text(
                            modifier = modifier,
                            text = displayedText,
                            style = OudsTheme.typography.label.strong.large,
                            textAlign = TextAlign.Center
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
    }
}

@Composable
private fun Modifier.border(hierarchy: OudsButton.Hierarchy, state: OudsButton.State, shape: Shape): Modifier {
    val borderWidth = borderWidth(hierarchy = hierarchy, state = state)
    val borderColor = borderColor(hierarchy = hierarchy, state = state)

    return if (borderWidth != null && borderColor != null) {
        if (hierarchy == OudsButton.Hierarchy.Negative && LocalColoredBox.current) {
            dashedBorder(width = borderWidth, color = borderColor, shape = shape, intervals = listOf(10.dp, 5.dp))
        } else {
            border(width = borderWidth, color = borderColor, shape = shape)
        }
    } else {
        this
    }
}

@Composable
private fun Modifier.outerBorder(hierarchy: OudsButton.Hierarchy, state: OudsButton.State, shape: Shape): Modifier {
    return if (state == OudsButton.State.Focused && (hierarchy != OudsButton.Hierarchy.Negative || !LocalColoredBox.current)) {
        outerBorder(
            width = OudsTheme.borders.width.focus,
            color = OudsTheme.colorScheme.border.focus,
            shape = shape,
            insetWidth = OudsTheme.borders.width.focusInset,
            insetColor = OudsTheme.colorScheme.border.focusInset
        )
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
                OudsButton.State.Disabled -> borderWidthDefault.value
                OudsButton.State.Hovered,
                OudsButton.State.Pressed,
                OudsButton.State.Loading -> if (LocalColoredBox.current) borderWidthDefaultInteractionMono.value else borderWidthDefaultInteraction.value
                OudsButton.State.Focused -> OudsTheme.borders.width.focusInset
            }
            OudsButton.Hierarchy.Minimal -> when (state) {
                OudsButton.State.Enabled,
                OudsButton.State.Disabled -> borderWidthMinimal.value
                OudsButton.State.Hovered,
                OudsButton.State.Pressed,
                OudsButton.State.Loading -> borderWidthMinimalInteraction.value
                OudsButton.State.Focused -> OudsTheme.borders.width.focusInset
            }
            OudsButton.Hierarchy.Strong -> if (state == OudsButton.State.Focused) OudsTheme.borders.width.focusInset else null
            OudsButton.Hierarchy.Negative -> if (!LocalColoredBox.current) {
                if (state == OudsButton.State.Focused) OudsTheme.borders.width.focusInset else null
            } else {
                1.dp
            }
        }
    }
}

@Composable
private fun borderColor(hierarchy: OudsButton.Hierarchy, state: OudsButton.State): Color? {
    return with(OudsTheme.componentsTokens.button) {
        when (hierarchy) {
            OudsButton.Hierarchy.Default -> when (state) {
                OudsButton.State.Enabled -> if (LocalColoredBox.current) colorBorderDefaultEnabledMono else colorBorderDefaultEnabled
                OudsButton.State.Hovered -> if (LocalColoredBox.current) colorBorderDefaultHoverMono else colorBorderDefaultHover
                OudsButton.State.Pressed -> if (LocalColoredBox.current) colorBorderDefaultPressedMono else colorBorderDefaultPressed
                OudsButton.State.Loading -> if (LocalColoredBox.current) colorBorderDefaultLoadingMono else colorBorderDefaultLoading
                OudsButton.State.Disabled -> if (LocalColoredBox.current) colorBorderDefaultDisabledMono else colorBorderDefaultDisabled
                OudsButton.State.Focused -> if (LocalColoredBox.current) colorBorderDefaultFocusMono else colorBorderDefaultFocus
            }.value
            OudsButton.Hierarchy.Minimal -> when (state) {
                OudsButton.State.Enabled -> if (LocalColoredBox.current) colorBorderMinimalEnabledMono else colorBorderMinimalEnabled
                OudsButton.State.Hovered -> if (LocalColoredBox.current) colorBorderMinimalHoverMono else colorBorderMinimalHover
                OudsButton.State.Pressed -> if (LocalColoredBox.current) colorBorderMinimalPressedMono else colorBorderMinimalPressed
                OudsButton.State.Loading -> if (LocalColoredBox.current) colorBorderMinimalLoadingMono else colorBorderMinimalLoading
                OudsButton.State.Disabled -> if (LocalColoredBox.current) colorBorderMinimalDisabledMono else colorBorderMinimalDisabled
                OudsButton.State.Focused -> if (LocalColoredBox.current) colorBorderMinimalFocusMono else colorBorderMinimalFocus
            }.value
            OudsButton.Hierarchy.Strong -> if (LocalColoredBox.current) {
                when (state) {
                    OudsButton.State.Enabled -> colorBorderStrongEnabledMono
                    OudsButton.State.Hovered -> colorBorderStrongHoverMono
                    OudsButton.State.Pressed -> colorBorderStrongPressedMono
                    OudsButton.State.Loading -> colorBorderStrongLoadingMono
                    OudsButton.State.Disabled -> colorBorderStrongDisabledMono
                    OudsButton.State.Focused -> colorBorderStrongFocusMono
                }
            } else {
                null
            }?.value
            OudsButton.Hierarchy.Negative -> if (LocalColoredBox.current) OudsTheme.colorScheme.always.black else null
        }
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
                OudsButton.State.Enabled -> if (LocalColoredBox.current) colorBgDefaultEnabledMono else colorBgDefaultEnabled
                OudsButton.State.Focused -> if (LocalColoredBox.current) colorBgDefaultFocusMono else colorBgDefaultFocus
                OudsButton.State.Hovered -> if (LocalColoredBox.current) colorBgDefaultHoverMono else colorBgDefaultHover
                OudsButton.State.Pressed -> if (LocalColoredBox.current) colorBgDefaultPressedMono else colorBgDefaultPressed
                OudsButton.State.Loading -> if (LocalColoredBox.current) colorBgDefaultLoadingMono else colorBgDefaultLoading
                OudsButton.State.Disabled -> if (LocalColoredBox.current) colorBgDefaultDisabledMono else colorBgDefaultDisabled
            }.value
            OudsButton.Hierarchy.Minimal -> when (state) {
                OudsButton.State.Enabled -> if (LocalColoredBox.current) colorBgMinimalEnabledMono else colorBgMinimalEnabled
                OudsButton.State.Focused -> if (LocalColoredBox.current) colorBgMinimalFocusMono else colorBgMinimalFocus
                OudsButton.State.Hovered -> if (LocalColoredBox.current) colorBgMinimalHoverMono else colorBgMinimalHover
                OudsButton.State.Pressed -> if (LocalColoredBox.current) colorBgMinimalPressedMono else colorBgMinimalPressed
                OudsButton.State.Loading -> if (LocalColoredBox.current) colorBgMinimalLoadingMono else colorBgMinimalLoading
                OudsButton.State.Disabled -> if (LocalColoredBox.current) colorBgMinimalDisabledMono else colorBgMinimalDisabled
            }.value
            OudsButton.Hierarchy.Strong -> when (state) {
                OudsButton.State.Enabled -> if (LocalColoredBox.current) colorBgStrongEnabledMono.value else OudsTheme.colorScheme.action.enabled
                OudsButton.State.Focused -> if (LocalColoredBox.current) colorBgStrongFocusMono.value else OudsTheme.colorScheme.action.focus
                OudsButton.State.Hovered -> if (LocalColoredBox.current) colorBgStrongHoverMono.value else OudsTheme.colorScheme.action.hover
                OudsButton.State.Pressed -> if (LocalColoredBox.current) colorBgStrongPressedMono.value else OudsTheme.colorScheme.action.pressed
                OudsButton.State.Loading -> if (LocalColoredBox.current) colorBgStrongLoadingMono.value else OudsTheme.colorScheme.action.loading
                OudsButton.State.Disabled -> if (LocalColoredBox.current) colorBgStrongDisabledMono.value else OudsTheme.colorScheme.action.disabled
            }
            OudsButton.Hierarchy.Negative -> if (!LocalColoredBox.current) {
                when (state) {
                    OudsButton.State.Enabled -> OudsTheme.colorScheme.action.negative.enabled
                    OudsButton.State.Focused -> OudsTheme.colorScheme.action.negative.focus
                    OudsButton.State.Hovered -> OudsTheme.colorScheme.action.negative.hover
                    OudsButton.State.Pressed -> OudsTheme.colorScheme.action.negative.pressed
                    OudsButton.State.Loading -> OudsTheme.colorScheme.action.negative.loading
                    OudsButton.State.Disabled -> OudsTheme.colorScheme.action.disabled
                }
            } else {
                Color(red = 0.0f, green = 0.0f, blue = 0.0f, alpha = 0.68f)
            }
        }
    }
}

@Composable
private fun contentColor(hierarchy: OudsButton.Hierarchy, state: OudsButton.State): Color {
    return with(OudsTheme.componentsTokens.button) {
        when (hierarchy) {
            OudsButton.Hierarchy.Default -> when (state) {
                OudsButton.State.Enabled -> if (LocalColoredBox.current) colorContentDefaultEnabledMono else colorContentDefaultEnabled
                OudsButton.State.Focused -> if (LocalColoredBox.current) colorContentDefaultFocusMono else colorContentDefaultFocus
                OudsButton.State.Hovered -> if (LocalColoredBox.current) colorContentDefaultHoverMono else colorContentDefaultHover
                OudsButton.State.Pressed -> if (LocalColoredBox.current) colorContentDefaultPressedMono else colorContentDefaultPressed
                OudsButton.State.Loading -> if (LocalColoredBox.current) colorContentDefaultLoadingMono else colorContentDefaultLoading
                OudsButton.State.Disabled -> if (LocalColoredBox.current) colorContentDefaultDisabledMono else colorContentDefaultDisabled
            }.value
            OudsButton.Hierarchy.Minimal -> when (state) {
                OudsButton.State.Enabled -> if (LocalColoredBox.current) colorContentMinimalEnabledMono else colorContentMinimalEnabled
                OudsButton.State.Focused -> if (LocalColoredBox.current) colorContentMinimalFocusMono else colorContentMinimalFocus
                OudsButton.State.Hovered -> if (LocalColoredBox.current) colorContentMinimalHoverMono else colorContentMinimalHover
                OudsButton.State.Pressed -> if (LocalColoredBox.current) colorContentMinimalPressedMono else colorContentMinimalPressed
                OudsButton.State.Loading -> if (LocalColoredBox.current) colorContentMinimalLoadingMono else colorContentMinimalLoading
                OudsButton.State.Disabled -> if (LocalColoredBox.current) colorContentMinimalDisabledMono else colorContentMinimalDisabled
            }.value
            OudsButton.Hierarchy.Strong -> when (state) {
                OudsButton.State.Enabled -> if (LocalColoredBox.current) colorContentStrongEnabledMono.value else OudsTheme.colorScheme.content.onAction.enabled
                OudsButton.State.Focused -> if (LocalColoredBox.current) colorContentStrongFocusMono.value else OudsTheme.colorScheme.content.onAction.focus
                OudsButton.State.Hovered -> if (LocalColoredBox.current) colorContentStrongHoverMono.value else OudsTheme.colorScheme.content.onAction.hover
                OudsButton.State.Pressed -> if (LocalColoredBox.current) colorContentStrongPressedMono.value else OudsTheme.colorScheme.content.onAction.pressed
                OudsButton.State.Loading -> if (LocalColoredBox.current) colorContentStrongLoadingMono.value else OudsTheme.colorScheme.content.onAction.loading
                OudsButton.State.Disabled -> if (LocalColoredBox.current) colorContentStrongDisabledMono.value else OudsTheme.colorScheme.content.onAction.disabled
            }
            OudsButton.Hierarchy.Negative -> if (!LocalColoredBox.current) {
                when (state) {
                    OudsButton.State.Enabled,
                    OudsButton.State.Hovered,
                    OudsButton.State.Pressed,
                    OudsButton.State.Loading,
                    OudsButton.State.Focused -> OudsTheme.colorScheme.content.onAction.negative
                    OudsButton.State.Disabled -> OudsTheme.colorScheme.content.onAction.disabled
                }
            } else {
                OudsTheme.colorScheme.always.white
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
private fun LoadingIndicator(hierarchy: OudsButton.Hierarchy, progress: Float?, scale: Float) {
    val modifier = Modifier
        .size(OudsTheme.componentsTokens.button.sizeLoader.value * scale)
        .semantics { invisibleToUser() }
    val color = contentColor(hierarchy = hierarchy, state = OudsButton.State.Loading)
    val strokeWidth = 3.dp * scale
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
        graphicsObject: Any,
        val contentDescription: String
    ) : OudsComponentIcon<Icon.ExtraParameters>(ExtraParameters::class.java, graphicsObject, contentDescription) {

        @ConsistentCopyVisibility
        data class ExtraParameters internal constructor(
            internal val tint: Color
        ) : OudsComponentContent.ExtraParameters()

        /**
         * Creates an instance of [OudsButton.Icon].
         *
         * @param painter Painter of the icon.
         * @param contentDescription The content description associated with this [OudsButton.Icon]. This value is ignored if the button also contains text.
         */
        constructor(painter: Painter, contentDescription: String) : this(painter as Any, contentDescription)

        /**
         * Creates an instance of [OudsButton.Icon].
         *
         * @param imageVector Image vector of the icon.
         * @param contentDescription The content description associated with this [OudsButton.Icon]. This value is ignored if the button also contains text.
         */
        constructor(imageVector: ImageVector, contentDescription: String) : this(imageVector as Any, contentDescription)

        /**
         * Creates an instance of [OudsButton.Icon].
         *
         * @param bitmap Image bitmap of the icon.
         * @param contentDescription The content description associated with this [OudsButton.Icon]. This value is ignored if the button also contains text.
         */
        constructor(bitmap: ImageBitmap, contentDescription: String) : this(bitmap as Any, contentDescription)

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
    }

    internal enum class State {
        Enabled, Hovered, Pressed, Loading, Disabled, Focused
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
) = OudsPreview(darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        val columnCount = 2
        val content: @Composable () -> Unit = {
            Box(modifier = Modifier.padding(16.dp)) {
                val text = if (hasText) hierarchy.name else null
                val icon = if (hasIcon) OudsButton.Icon(painterResource(id = android.R.drawable.star_on), "") else null
                val chunkedStates = states.chunked(columnCount)
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
        if (surfaceKeyTokenGroup != null) {
            RainbowColoredBox(surfaceKeyTokenGroup = surfaceKeyTokenGroup, columnCount = columnCount, content = { content() })
        } else {
            content()
        }
    }
}

internal enum class SurfaceKeyTokenGroup {
    Brand, StatusEmphasized, StatusMuted
}

@Composable
private fun RainbowColoredBox(surfaceKeyTokenGroup: SurfaceKeyTokenGroup, columnCount: Int, content: @Composable BoxScope.() -> Unit) {
    // The color parameter below can be whichever surface
    // because the actual colors will be drawn as a rainbow in order to display multiple colored boxes at once
    OudsColoredBox(color = OudsColoredBox.Color.BrandPrimary) {
        Row(modifier = Modifier.matchParentSize()) {
            val colors = buildList {
                repeat(columnCount) {
                    addAll(
                        when (surfaceKeyTokenGroup) {
                            SurfaceKeyTokenGroup.Brand -> listOf(
                                OudsColoredBox.Color.BrandPrimary
                            )
                            SurfaceKeyTokenGroup.StatusEmphasized -> listOf(
                                OudsColoredBox.Color.StatusPositiveEmphasized,
                                OudsColoredBox.Color.StatusNegativeEmphasized,
                                OudsColoredBox.Color.StatusWarningEmphasized,
                                OudsColoredBox.Color.StatusInfoEmphasized
                            )
                            SurfaceKeyTokenGroup.StatusMuted -> listOf(
                                OudsColoredBox.Color.StatusPositiveMuted,
                                OudsColoredBox.Color.StatusNegativeMuted,
                                OudsColoredBox.Color.StatusWarningMuted,
                                OudsColoredBox.Color.StatusInfoMuted
                            )
                        }
                    )
                }
            }
            colors.map { color ->
                Box(
                    modifier = Modifier
                        .background(color = color.value)
                        .fillMaxHeight()
                        .weight(1.0f)
                )
            }
        }
        content()
    }
}

internal data class OudsButtonPreviewParameter(
    val hierarchy: OudsButton.Hierarchy,
    val hasText: Boolean,
    val hasIcon: Boolean,
    val surfaceKeyTokenGroup: SurfaceKeyTokenGroup? = null,
) {
    val states: List<OudsButton.State> = listOf(
        OudsButton.State.Enabled,
        OudsButton.State.Hovered,
        OudsButton.State.Pressed,
        OudsButton.State.Loading,
        OudsButton.State.Disabled,
        OudsButton.State.Focused
    )
}

internal class OudsButtonPreviewParameterProvider : BasicPreviewParameterProvider<OudsButtonPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsButtonPreviewParameter>
    get() = buildList {
        OudsButton.Hierarchy.entries.forEach { hierarchy ->
            val parameters = listOf(
                OudsButtonPreviewParameter(hierarchy, hasText = true, hasIcon = false),
                OudsButtonPreviewParameter(hierarchy, hasText = true, hasIcon = true),
                OudsButtonPreviewParameter(hierarchy, hasText = false, hasIcon = true),
            )
            addAll(parameters)
            //addAll(parameters.map { it.copy(surfaceKeyTokenGroup = SurfaceKeyTokenGroup.Brand) })
            addAll(parameters.map { it.copy(surfaceKeyTokenGroup = SurfaceKeyTokenGroup.StatusEmphasized) })
            //addAll(parameters.map { it.copy(surfaceKeyTokenGroup = SurfaceKeyTokenGroup.StatusMuted) })
        }
    }
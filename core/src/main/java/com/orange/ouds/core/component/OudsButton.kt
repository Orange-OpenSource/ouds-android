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

import android.os.Parcelable
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.hideFromAccessibility
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.R
import com.orange.ouds.core.component.common.outerBorder
import com.orange.ouds.core.component.content.OudsComponentContent
import com.orange.ouds.core.component.content.OudsComponentIcon
import com.orange.ouds.core.extensions.InteractionState
import com.orange.ouds.core.extensions.collectInteractionStateAsState
import com.orange.ouds.core.theme.LocalColoredBox
import com.orange.ouds.core.theme.LocalUseMonoComponents
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.CheckedContent
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.PreviewStates
import com.orange.ouds.core.utilities.getPreviewState
import com.orange.ouds.foundation.extensions.ifNotNull
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.theme.tokens.components.OudsButtonTokens
import kotlinx.parcelize.Parcelize

/**
 * <a href="https://unified-design-system.orange.com/472794e18/p/48a788-button" class="external" target="_blank">**OUDS Button design guidelines**</a>
 *
 * Buttons are interactive elements designed to trigger specific actions or events when tapped by a user.
 *
 * This version of the button uses the *text only* layout which is the most used layout.
 * Other layouts are available for this component: *text + icon* and *icon only*.
 *
 * Note that in the case it is placed in an [OudsColoredBox], its monochrome variant is automatically displayed.
 * Some tokens associated with these specific colors can be customized and are identified with the `Mono` suffix (for instance [OudsButtonTokens.colorBgDefaultEnabledMono]).
 *
 * @param label Label displayed in the button which describes the button action. Use action verbs or phrases to tell the user what will happen next.
 * @param onClick Callback invoked when the button is clicked.
 * @param modifier [Modifier] applied to the button.
 * @param enabled Controls the enabled state of the button when [style] is equal to [OudsButton.Style.Default].
 *   When `false`, this button will not be clickable.
 *   Has no effect when [style] is equal to [OudsButton.Style.Loading].
 * @param style The [OudsButton.Style] used for the button. Use [OudsButton.Style.Default] for a standard button, or [OudsButton.Style.Loading] to indicate
 *   an ongoing operation.
 * @param hierarchy The button appearance based on its [OudsButton.Hierarchy].
 *   A button with [OudsButton.Hierarchy.Negative] hierarchy is not allowed as a direct or indirect child of an [OudsColoredBox] and will throw an [IllegalStateException].
 * @param interactionSource An optional hoisted [MutableInteractionSource] for observing and emitting [Interaction]s for this button. Note that if `null`
 *   is provided, interactions will still happen internally.
 *
 * @sample com.orange.ouds.core.component.samples.OudsButtonTextOnlySample
 *
 * @sample com.orange.ouds.core.component.samples.OudsButtonTextOnlyOnColoredBackgroundSample
 */
@Composable
fun OudsButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    style: OudsButton.Style = OudsButtonDefaults.Style,
    hierarchy: OudsButton.Hierarchy = OudsButtonDefaults.Hierarchy,
    interactionSource: MutableInteractionSource? = null
) {
    OudsButton(
        nullableIcon = null,
        nullableLabel = label,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        style = style,
        hierarchy = hierarchy,
        interactionSource = interactionSource
    )
}

/**
 * <a href="https://unified-design-system.orange.com/472794e18/p/48a788-button" class="external" target="_blank">**OUDS Button design guidelines**</a>
 *
 * Buttons are interactive elements designed to trigger specific actions or events when tapped by a user.
 *
 * This version of the button uses the *icon only* layout which is typically used in business or back-office interfaces, it is rarely used alone (usually part of a group of elements).
 * Other layouts are available for this component: *text only* and *text + icon*.
 *
 * Note that in the case it is placed in an [OudsColoredBox], its monochrome variant is automatically displayed.
 * Some tokens associated with these specific colors can be customized and are identified with the `Mono` suffix (for instance [OudsButtonTokens.colorBgDefaultEnabledMono]).
 *
 * @param icon Icon displayed in the button. Use an icon to add additional affordance where the icon has a clear and well-established meaning.
 * @param onClick Callback invoked when the button is clicked.
 * @param modifier [Modifier] applied to the button.
 * @param enabled Controls the enabled state of the button when [style] is equal to [OudsButton.Style.Default].
 *   When `false`, this button will not be clickable.
 *   Has no effect when [style] is equal to [OudsButton.Style.Loading].
 * @param style The [OudsButton.Style] used for the button. Use [OudsButton.Style.Default] for a standard button, or [OudsButton.Style.Loading] to indicate
 *   an ongoing operation.
 * @param hierarchy The button appearance based on its [OudsButton.Hierarchy].
 *   A button with [OudsButton.Hierarchy.Negative] hierarchy is not allowed as a direct or indirect child of an [OudsColoredBox] and will throw an [IllegalStateException].
 * @param interactionSource An optional hoisted [MutableInteractionSource] for observing and emitting [Interaction]s for this button. Note that if `null`
 *   is provided, interactions will still happen internally.
 *
 * @sample com.orange.ouds.core.component.samples.OudsButtonIconOnlySample
 *
 * @sample com.orange.ouds.core.component.samples.OudsButtonIconOnlyOnColoredBackgroundSample
 */
@Composable
fun OudsButton(
    icon: OudsButton.Icon,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    style: OudsButton.Style = OudsButtonDefaults.Style,
    hierarchy: OudsButton.Hierarchy = OudsButtonDefaults.Hierarchy,
    interactionSource: MutableInteractionSource? = null
) {
    OudsButton(
        nullableIcon = icon,
        nullableLabel = null,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        style = style,
        hierarchy = hierarchy,
        interactionSource = interactionSource
    )
}

/**
 * <a href="https://unified-design-system.orange.com/472794e18/p/48a788-button" class="external" target="_blank">**OUDS Button design guidelines**</a>
 *
 * Buttons are interactive elements designed to trigger specific actions or events when tapped by a user.
 *
 * This version of the button uses the *text + icon* layout which should remain specific to some clearly identified contexts (e.g. the use of an icon with a
 * "Play" button is standard in the context of TV or video streaming).
 * Other layouts are available for this component: *text only* and *text + icon*.
 *
 * Note that in the case it is placed in an [OudsColoredBox], its monochrome variant is automatically displayed.
 * Some tokens associated with these specific colors can be customized and are identified with the `Mono` suffix (for instance [OudsButtonTokens.colorBgDefaultEnabledMono]).
 *
 * @param icon Icon displayed in the button. Use an icon to add additional affordance where the icon has a clear and well-established meaning.
 * @param label Label displayed in the button which describes the button action. Use action verbs or phrases to tell the user what will happen next.
 * @param onClick Callback invoked when the button is clicked.
 * @param modifier [Modifier] applied to the button.
 * @param enabled Controls the enabled state of the button when [style] is equal to [OudsButton.Style.Default].
 *   When `false`, this button will not be clickable.
 *   Has no effect when [style] is equal to [OudsButton.Style.Loading].
 * @param style The [OudsButton.Style] used for the button. Use [OudsButton.Style.Default] for a standard button, or [OudsButton.Style.Loading] to indicate
 *   an ongoing operation.
 * @param hierarchy The button appearance based on its [OudsButton.Hierarchy].
 *   A button with [OudsButton.Hierarchy.Negative] hierarchy is not allowed as a direct or indirect child of an [OudsColoredBox] and will throw an [IllegalStateException].
 * @param interactionSource An optional hoisted [MutableInteractionSource] for observing and emitting [Interaction]s for this button. Note that if `null`
 *   is provided, interactions will still happen internally.
 *
 * @sample com.orange.ouds.core.component.samples.OudsButtonIconAndTextSample
 *
 * @sample com.orange.ouds.core.component.samples.OudsButtonIconAndTextOnColoredBackgroundSample
 */
@Composable
fun OudsButton(
    icon: OudsButton.Icon,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    style: OudsButton.Style = OudsButtonDefaults.Style,
    hierarchy: OudsButton.Hierarchy = OudsButtonDefaults.Hierarchy,
    interactionSource: MutableInteractionSource? = null
) {
    OudsButton(
        nullableIcon = icon,
        nullableLabel = label,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        style = style,
        hierarchy = hierarchy,
        interactionSource = interactionSource
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
@JvmName("OudsButtonNullableIconAndLabel")
private fun OudsButton(
    nullableIcon: OudsButton.Icon?,
    nullableLabel: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    style: OudsButton.Style = OudsButton.Style.Default,
    hierarchy: OudsButton.Hierarchy = OudsButtonDefaults.Hierarchy,
    interactionSource: MutableInteractionSource? = null
) {
    val icon = nullableIcon
    val label = nullableLabel
    val isForbidden = hierarchy == OudsButton.Hierarchy.Negative && LocalColoredBox.current
    CheckedContent(
        expression = !isForbidden,
        exceptionMessage = { "An OudsButton with OudsButton.Hierarchy.Negative hierarchy displayed as a direct or indirect child of an OudsColoredBox is not allowed." },
        previewMessage = { if (icon != null && label == null) "⛔" else "Not on a\ncolored\nbackground" }
    ) {
        val buttonTokens = OudsTheme.componentsTokens.button
        @Suppress("NAME_SHADOWING") val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
        val interactionState by interactionSource.collectInteractionStateAsState()
        val state = getButtonState(enabled = enabled, style = style, interactionState = interactionState)
        val iconScale = if (icon != null && label == null) LocalConfiguration.current.fontScale else 1.0f
        val maxHeight = if (icon != null && label == null) buttonTokens.sizeMaxHeightIconOnly.value * iconScale else Dp.Unspecified
        val shape = RoundedCornerShape(buttonTokens.borderRadius.value)

        CompositionLocalProvider(LocalRippleConfiguration provides null) {
            val stateDescription = if (state == OudsButton.State.Loading) stringResource(id = R.string.core_button_loading_a11y) else ""
            val contentColor = rememberInteractionColor(interactionState = interactionState) { buttonInteractionState ->
                val buttonState = getButtonState(enabled = enabled, style = style, interactionState = buttonInteractionState)
                contentColor(hierarchy = hierarchy, state = buttonState)
            }
            val backgroundColor = rememberInteractionColor(interactionState = interactionState) { buttonInteractionState ->
                val buttonState = getButtonState(enabled = enabled, style = style, interactionState = buttonInteractionState)
                backgroundColor(hierarchy = hierarchy, state = buttonState)
            }
            val borderWidth = rememberInteractionValue(
                interactionState = interactionState,
                toAnimatableFloat = { it?.value.orElse { 0f } },
                fromAnimatableFloat = { it.dp }
            ) { buttonInteractionState ->
                val buttonState = getButtonState(enabled = enabled, style = style, interactionState = buttonInteractionState)
                borderWidth(hierarchy = hierarchy, state = buttonState)
            }
            val borderColor = rememberNullableInteractionColor(interactionState = interactionState) { buttonInteractionState ->
                val buttonState = getButtonState(enabled = enabled, style = style, interactionState = buttonInteractionState)
                borderColor(hierarchy = hierarchy, state = buttonState)
            }

            Box(
                modifier = modifier
                    .widthIn(min = buttonTokens.sizeMinWidth.value)
                    .heightIn(min = buttonTokens.sizeMinHeight.value, max = maxHeight)
                    .background(color = backgroundColor.value, shape = shape)
                    .run {
                        ifNotNull(borderWidth.value, borderColor.value) { borderWidth, borderColor ->
                            border(width = borderWidth, color = borderColor, shape = shape)
                        }.orElse {
                            this
                        }
                    }
                    .outerBorder(state = state, shape = shape)
                    .semantics {
                        this.stateDescription = stateDescription
                        role = Role.Button
                    }
                    .clickable(
                        enabled = state !in remember { listOf(OudsButton.State.Disabled, OudsButton.State.Loading) },
                        interactionSource = interactionSource,
                        indication = InteractionValuesIndication(contentColor, backgroundColor, borderColor, borderWidth),
                        onClick = onClick
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (state == OudsButton.State.Loading) {
                    val loadingStyle = style as? OudsButton.Style.Loading
                    val progress = if (getPreviewState<OudsButton.State>() == OudsButton.State.Loading) 0.75f else loadingStyle?.progress
                    LoadingIndicator(hierarchy = hierarchy, progress = progress, scale = iconScale)
                }

                val alpha = if (state == OudsButton.State.Loading) 0f else 1f
                Row(
                    modifier = Modifier
                        .alpha(alpha = alpha)
                        .padding(contentPadding(icon = icon, label = label)),
                    horizontalArrangement = Arrangement.spacedBy(buttonTokens.spaceColumnGapIcon.value),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (icon != null) {
                        val size = if (label == null) buttonTokens.sizeIconOnly else buttonTokens.sizeIcon
                        icon.Content(
                            modifier = Modifier
                                .size(size.value * iconScale)
                                .semantics {
                                    contentDescription = if (label == null) icon.contentDescription else ""
                                },
                            extraParameters = OudsButton.Icon.ExtraParameters(tint = contentColor.value)
                        )
                    }
                    if (label != null) {
                        Text(
                            modifier = modifier,
                            text = label,
                            color = contentColor.value,
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
private fun getButtonState(enabled: Boolean, style: OudsButton.Style, interactionState: InteractionState): OudsButton.State {
    return getPreviewState<OudsButton.State>().orElse {
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
                OudsButton.State.Loading -> if (LocalUseMonoComponents.current) borderWidthDefaultInteractionMono.value else borderWidthDefaultInteraction.value
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
            OudsButton.Hierarchy.Strong,
            OudsButton.Hierarchy.Negative -> if (state == OudsButton.State.Focused) OudsTheme.borders.width.focusInset else null
        }
    }
}

@Composable
private fun borderColor(hierarchy: OudsButton.Hierarchy, state: OudsButton.State): Color? {
    return if (LocalUseMonoComponents.current) {
        with(OudsTheme.componentsTokens.buttonMono) {
            when (hierarchy) {
                OudsButton.Hierarchy.Default -> when (state) {
                    OudsButton.State.Enabled -> colorBorderDefaultEnabled
                    OudsButton.State.Hovered -> colorBorderDefaultHover
                    OudsButton.State.Pressed -> colorBorderDefaultPressed
                    OudsButton.State.Loading -> colorBorderDefaultLoading
                    OudsButton.State.Disabled -> colorBorderDefaultDisabled
                    OudsButton.State.Focused -> colorBorderDefaultFocus
                }
                OudsButton.Hierarchy.Minimal -> when (state) {
                    OudsButton.State.Enabled -> colorBorderMinimalEnabled
                    OudsButton.State.Hovered -> colorBorderMinimalHover
                    OudsButton.State.Pressed -> colorBorderMinimalPressed
                    OudsButton.State.Loading -> colorBorderMinimalLoading
                    OudsButton.State.Disabled -> colorBorderMinimalDisabled
                    OudsButton.State.Focused -> colorBorderMinimalFocus
                }
                OudsButton.Hierarchy.Strong -> when (state) {
                    OudsButton.State.Enabled -> colorBorderStrongEnabled
                    OudsButton.State.Hovered -> colorBorderStrongHover
                    OudsButton.State.Pressed -> colorBorderStrongPressed
                    OudsButton.State.Loading -> colorBorderStrongLoading
                    OudsButton.State.Disabled -> colorBorderStrongDisabled
                    OudsButton.State.Focused -> colorBorderStrongFocus
                }
                OudsButton.Hierarchy.Negative -> null
            }
        }
    } else {
        with(OudsTheme.componentsTokens.button) {
            when (hierarchy) {
                OudsButton.Hierarchy.Default -> when (state) {
                    OudsButton.State.Enabled -> colorBorderDefaultEnabled
                    OudsButton.State.Hovered -> colorBorderDefaultHover
                    OudsButton.State.Pressed -> colorBorderDefaultPressed
                    OudsButton.State.Loading -> colorBorderDefaultLoading
                    OudsButton.State.Disabled -> colorBorderDefaultDisabled
                    OudsButton.State.Focused -> colorBorderDefaultFocus
                }
                OudsButton.Hierarchy.Minimal -> when (state) {
                    OudsButton.State.Enabled -> colorBorderMinimalEnabled
                    OudsButton.State.Hovered -> colorBorderMinimalHover
                    OudsButton.State.Pressed -> colorBorderMinimalPressed
                    OudsButton.State.Loading -> colorBorderMinimalLoading
                    OudsButton.State.Disabled -> colorBorderMinimalDisabled
                    OudsButton.State.Focused -> colorBorderMinimalFocus
                }
                OudsButton.Hierarchy.Strong,
                OudsButton.Hierarchy.Negative -> null
            }
        }
    }?.value
}

@Composable
private fun backgroundColor(hierarchy: OudsButton.Hierarchy, state: OudsButton.State): Color {
    val negativeBackgroundColor = when (state) {
        OudsButton.State.Enabled -> OudsTheme.colorScheme.action.negative.enabled
        OudsButton.State.Focused -> OudsTheme.colorScheme.action.negative.focus
        OudsButton.State.Hovered -> OudsTheme.colorScheme.action.negative.hover
        OudsButton.State.Pressed -> OudsTheme.colorScheme.action.negative.pressed
        OudsButton.State.Loading -> OudsTheme.colorScheme.action.negative.loading
        OudsButton.State.Disabled -> OudsTheme.colorScheme.action.disabled
    }
    return if (LocalUseMonoComponents.current) {
        with(OudsTheme.componentsTokens.buttonMono) {
            when (hierarchy) {
                OudsButton.Hierarchy.Default -> when (state) {
                    OudsButton.State.Enabled -> colorBgDefaultEnabled
                    OudsButton.State.Focused -> colorBgDefaultFocus
                    OudsButton.State.Hovered -> colorBgDefaultHover
                    OudsButton.State.Pressed -> colorBgDefaultPressed
                    OudsButton.State.Loading -> colorBgDefaultLoading
                    OudsButton.State.Disabled -> colorBgDefaultDisabled
                }.value
                OudsButton.Hierarchy.Minimal -> when (state) {
                    OudsButton.State.Enabled -> colorBgMinimalEnabled
                    OudsButton.State.Focused -> colorBgMinimalFocus
                    OudsButton.State.Hovered -> colorBgMinimalHover
                    OudsButton.State.Pressed -> colorBgMinimalPressed
                    OudsButton.State.Loading -> colorBgMinimalLoading
                    OudsButton.State.Disabled -> colorBgMinimalDisabled
                }.value
                OudsButton.Hierarchy.Strong -> when (state) {
                    OudsButton.State.Enabled -> colorBgStrongEnabled
                    OudsButton.State.Focused -> colorBgStrongFocus
                    OudsButton.State.Hovered -> colorBgStrongHover
                    OudsButton.State.Pressed -> colorBgStrongPressed
                    OudsButton.State.Loading -> colorBgStrongLoading
                    OudsButton.State.Disabled -> colorBgStrongDisabled
                }.value
                OudsButton.Hierarchy.Negative -> negativeBackgroundColor
            }
        }
    } else {
        with(OudsTheme.componentsTokens.button) {
            when (hierarchy) {
                OudsButton.Hierarchy.Default -> when (state) {
                    OudsButton.State.Enabled -> colorBgDefaultEnabled
                    OudsButton.State.Focused -> colorBgDefaultFocus
                    OudsButton.State.Hovered -> colorBgDefaultHover
                    OudsButton.State.Pressed -> colorBgDefaultPressed
                    OudsButton.State.Loading -> colorBgDefaultLoading
                    OudsButton.State.Disabled -> colorBgDefaultDisabled
                }.value
                OudsButton.Hierarchy.Minimal -> when (state) {
                    OudsButton.State.Enabled -> colorBgMinimalEnabled
                    OudsButton.State.Focused -> colorBgMinimalFocus
                    OudsButton.State.Hovered -> colorBgMinimalHover
                    OudsButton.State.Pressed -> colorBgMinimalPressed
                    OudsButton.State.Loading -> colorBgMinimalLoading
                    OudsButton.State.Disabled -> colorBgMinimalDisabled
                }.value
                OudsButton.Hierarchy.Strong -> when (state) {
                    OudsButton.State.Enabled -> OudsTheme.colorScheme.action.enabled
                    OudsButton.State.Focused -> OudsTheme.colorScheme.action.focus
                    OudsButton.State.Hovered -> OudsTheme.colorScheme.action.hover
                    OudsButton.State.Pressed -> OudsTheme.colorScheme.action.pressed
                    OudsButton.State.Loading -> OudsTheme.colorScheme.action.loading
                    OudsButton.State.Disabled -> OudsTheme.colorScheme.action.disabled
                }
                OudsButton.Hierarchy.Negative -> negativeBackgroundColor
            }
        }
    }
}

@Composable
private fun contentColor(hierarchy: OudsButton.Hierarchy, state: OudsButton.State): Color {
    val negativeContentColor = when (state) {
        OudsButton.State.Enabled,
        OudsButton.State.Hovered,
        OudsButton.State.Pressed,
        OudsButton.State.Loading,
        OudsButton.State.Focused -> OudsTheme.colorScheme.content.onStatus.negative.emphasized
        OudsButton.State.Disabled -> OudsTheme.colorScheme.content.onAction.disabled
    }
    return if (LocalUseMonoComponents.current) {
        with(OudsTheme.componentsTokens.buttonMono) {
            when (hierarchy) {
                OudsButton.Hierarchy.Default -> when (state) {
                    OudsButton.State.Enabled -> colorContentDefaultEnabled
                    OudsButton.State.Focused -> colorContentDefaultFocus
                    OudsButton.State.Hovered -> colorContentDefaultHover
                    OudsButton.State.Pressed -> colorContentDefaultPressed
                    OudsButton.State.Loading -> colorContentDefaultLoading
                    OudsButton.State.Disabled -> colorContentDefaultDisabled
                }.value
                OudsButton.Hierarchy.Minimal -> when (state) {
                    OudsButton.State.Enabled -> colorContentMinimalEnabled
                    OudsButton.State.Focused -> colorContentMinimalFocus
                    OudsButton.State.Hovered -> colorContentMinimalHover
                    OudsButton.State.Pressed -> colorContentMinimalPressed
                    OudsButton.State.Loading -> colorContentMinimalLoading
                    OudsButton.State.Disabled -> colorContentMinimalDisabled
                }.value
                OudsButton.Hierarchy.Strong -> when (state) {
                    OudsButton.State.Enabled -> colorContentStrongEnabled
                    OudsButton.State.Focused -> colorContentStrongFocus
                    OudsButton.State.Hovered -> colorContentStrongHover
                    OudsButton.State.Pressed -> colorContentStrongPressed
                    OudsButton.State.Loading -> colorContentStrongLoading
                    OudsButton.State.Disabled -> colorContentStrongDisabled
                }.value
                OudsButton.Hierarchy.Negative -> negativeContentColor
            }
        }
    } else {
        with(OudsTheme.componentsTokens.button) {
            when (hierarchy) {
                OudsButton.Hierarchy.Default -> when (state) {
                    OudsButton.State.Enabled -> colorContentDefaultEnabled
                    OudsButton.State.Focused -> colorContentDefaultFocus
                    OudsButton.State.Hovered -> colorContentDefaultHover
                    OudsButton.State.Pressed -> colorContentDefaultPressed
                    OudsButton.State.Loading -> colorContentDefaultLoading
                    OudsButton.State.Disabled -> colorContentDefaultDisabled
                }.value
                OudsButton.Hierarchy.Minimal -> when (state) {
                    OudsButton.State.Enabled -> colorContentMinimalEnabled
                    OudsButton.State.Focused -> colorContentMinimalFocus
                    OudsButton.State.Hovered -> colorContentMinimalHover
                    OudsButton.State.Pressed -> colorContentMinimalPressed
                    OudsButton.State.Loading -> colorContentMinimalLoading
                    OudsButton.State.Disabled -> colorContentMinimalDisabled
                }.value
                OudsButton.Hierarchy.Strong -> when (state) {
                    OudsButton.State.Enabled -> OudsTheme.colorScheme.content.onAction.enabled
                    OudsButton.State.Focused -> OudsTheme.colorScheme.content.onAction.focus
                    OudsButton.State.Hovered -> OudsTheme.colorScheme.content.onAction.hover
                    OudsButton.State.Pressed -> OudsTheme.colorScheme.content.onAction.pressed
                    OudsButton.State.Loading -> OudsTheme.colorScheme.content.onAction.loading
                    OudsButton.State.Disabled -> OudsTheme.colorScheme.content.onAction.disabled
                }
                OudsButton.Hierarchy.Negative -> negativeContentColor
            }
        }
    }
}

@Composable
private fun contentPadding(icon: OudsButton.Icon?, label: String?): PaddingValues {
    return with(OudsTheme.componentsTokens.button) {
        when {
            icon != null && label != null -> PaddingValues(
                start = spacePaddingInlineIconStart.value,
                top = spacePaddingBlock.value,
                end = spacePaddingInlineEndIconStart.value,
                bottom = spacePaddingBlock.value
            )
            icon != null && label == null -> PaddingValues(
                horizontal = spaceInsetIconOnly.value,
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
        .semantics { hideFromAccessibility() }
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
 * Default values for [OudsButton].
 */
object OudsButtonDefaults {

    /**
     * Default hierarchy of an OUDS button.
     */
    val Hierarchy = OudsButton.Hierarchy.Default

    /**
     * Default style of an OUDS button.
     */
    val Style = OudsButton.Style.Default
}

/**
 * Contains classes to build an [OudsButton].
 */
object OudsButton {

    /**
     * A button icon in an [OudsButton].
     * It is non-clickable and no content description is needed because a button label is always present.
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
         * @param contentDescription The content description associated with this [OudsButton.Icon]. This value is ignored if the button also contains label.
         */
        constructor(painter: Painter, contentDescription: String) : this(painter as Any, contentDescription)

        /**
         * Creates an instance of [OudsButton.Icon].
         *
         * @param imageVector Image vector of the icon.
         * @param contentDescription The content description associated with this [OudsButton.Icon]. This value is ignored if the button also contains label.
         */
        constructor(imageVector: ImageVector, contentDescription: String) : this(imageVector as Any, contentDescription)

        /**
         * Creates an instance of [OudsButton.Icon].
         *
         * @param bitmap Image bitmap of the icon.
         * @param contentDescription The content description associated with this [OudsButton.Icon]. This value is ignored if the button also contains label.
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
        /**
         * A standard button style used for actions which are not mandatory or essential for the user.
         */
        Default,

        /**
         * A strong style for a button which should be singular and prominent, limited to one per view. It should be reserved for the most critical action,
         * such as "Next," "Save," "Submit," etc...
         */
        Strong,

        /**
         * Minimal buttons are commonly used for actions that are considered less crucial. They can be used independently or together with a strong button.
         */
        Minimal,

        /**
         * Negative buttons should be used sparingly to warn of a destructive action, for example, delete or remove, typically resulting in the opening of a
         * confirmation dialog.
         */
        Negative
    }

    /**
     * Represents the different styles of an OUDS button.
     */
    sealed class Style : Parcelable {

        /**
         * The button displays an icon and/or a label and supports user interactions if it is enabled.
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

@PreviewLightDark
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
        val label = if (hasLabel) hierarchy.name else null
        val icon = if (hasIcon) OudsButton.Icon(painterResource(id = android.R.drawable.star_on), "") else null
        val content: @Composable () -> Unit = {
            PreviewStates<OudsButton.State>(columnCount = 2) {
                OudsButton(nullableIcon = icon, nullableLabel = label, onClick = {}, hierarchy = hierarchy)
            }
        }
        if (onColoredBox) {
            OudsColoredBox(color = OudsColoredBox.Color.BrandPrimary) {
                content()
            }
        } else {
            content()
        }
    }
}

internal data class OudsButtonPreviewParameter(
    val hierarchy: OudsButton.Hierarchy,
    val hasLabel: Boolean,
    val hasIcon: Boolean,
    val onColoredBox: Boolean = false
)

internal class OudsButtonPreviewParameterProvider : BasicPreviewParameterProvider<OudsButtonPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsButtonPreviewParameter>
    get() = buildList {
        OudsButton.Hierarchy.entries.forEach { hierarchy ->
            val parameters = listOf(
                OudsButtonPreviewParameter(hierarchy, hasLabel = true, hasIcon = false),
                OudsButtonPreviewParameter(hierarchy, hasLabel = true, hasIcon = true),
                OudsButtonPreviewParameter(hierarchy, hasLabel = false, hasIcon = true),
            )
            addAll(parameters)
            addAll(parameters.map { it.copy(onColoredBox = true) })
        }
    }
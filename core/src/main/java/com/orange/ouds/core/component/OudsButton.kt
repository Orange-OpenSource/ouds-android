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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.hideFromAccessibility
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.tooling.preview.Preview
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
import com.orange.ouds.core.theme.LocalColorMode
import com.orange.ouds.core.theme.LocalThemeSettings
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.isOudsInDarkTheme
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.CheckedContent
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.PreviewEnumEntries
import com.orange.ouds.core.utilities.getPreviewEnumEntry
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.core.utilities.mapSettings
import com.orange.ouds.foundation.extensions.ifNotNull
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.theme.OudsThemeContract
import com.orange.ouds.theme.OudsThemeSettings
import com.orange.ouds.theme.tokens.components.OudsButtonMonoTokens

/**
 * Buttons are interactive elements designed to trigger specific actions or events when tapped by a user.
 *
 * This version of the button uses the *text only* layout which is the most used layout.
 * Other layouts are available for this component: *text + icon* and *icon only*.
 *
 * Note that in the case it is placed in an [OudsColoredBox], its monochrome variant is automatically displayed.
 * The tokens associated with these specific colors can be customized by overriding [OudsButtonMonoTokens].
 *
 * Rounded corners can be enabled or disabled using the [OudsThemeSettings.roundedCornerButtons] property of an [OudsThemeSettings] when calling the [com.orange.ouds.core.theme.OudsTheme] method.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://unified-design-system.orange.com/472794e18/p/48a788-button)
 *
 * > Design version: 3.1.0
 *
 * @param label Label displayed in the button which describes the button action. Use action verbs or phrases to tell the user what will happen next.
 * @param onClick Callback invoked when the button is clicked.
 * @param modifier [Modifier] applied to the button.
 * @param enabled Controls the enabled state of the button when there is no [loader].
 *   When `false`, this button will not be clickable.
 *   Has no effect when [loader] is not null.
 * @param loader An optional loading progress indicator displayed in the button to indicate an ongoing operation.
 * @param appearance Appearance of the button among [OudsButtonAppearance] values.
 *   A button with [OudsButtonAppearance.Negative] is not allowed as a direct or indirect child of an [OudsColoredBox] and will throw an [IllegalStateException].
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
    loader: OudsButtonLoader? = null,
    appearance: OudsButtonAppearance = OudsButtonDefaults.Appearance,
    interactionSource: MutableInteractionSource? = null
) {
    OudsButton(
        nullableIcon = null,
        nullableLabel = label,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        loader = loader,
        appearance = appearance,
        interactionSource = interactionSource
    )
}

/**
 * Buttons are interactive elements designed to trigger specific actions or events when tapped by a user.
 *
 * This version of the button uses the *icon only* layout which is typically used in business or back-office interfaces, it is rarely used alone (usually part of a group of elements).
 * Other layouts are available for this component: *text only* and *text + icon*.
 *
 * Note that in the case it is placed in an [OudsColoredBox], its monochrome variant is automatically displayed.
 * The tokens associated with these specific colors can be customized by overriding [OudsButtonMonoTokens].
 *
 * Rounded corners can be enabled or disabled using the [OudsThemeSettings.roundedCornerButtons] property of an [OudsThemeSettings] when calling the [com.orange.ouds.core.theme.OudsTheme] method.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://unified-design-system.orange.com/472794e18/p/48a788-button)
 *
 * > Design version: 3.1.0
 *
 * @param icon Icon displayed in the button. Use an icon to add additional affordance where the icon has a clear and well-established meaning.
 * @param onClick Callback invoked when the button is clicked.
 * @param modifier [Modifier] applied to the button.
 * @param enabled Controls the enabled state of the button when there is no [loader].
 *   When `false`, this button will not be clickable.
 *   Has no effect when [loader] is not null.
 * @param loader An optional loading progress indicator displayed in the button to indicate an ongoing operation.
 * @param appearance Appearance of the button among [OudsButtonAppearance] values.
 *   A button with [OudsButtonAppearance.Negative] is not allowed as a direct or indirect child of an [OudsColoredBox] and will throw an [IllegalStateException].
 * @param interactionSource An optional hoisted [MutableInteractionSource] for observing and emitting [Interaction]s for this button. Note that if `null`
 *   is provided, interactions will still happen internally.
 *
 * @sample com.orange.ouds.core.component.samples.OudsButtonIconOnlySample
 *
 * @sample com.orange.ouds.core.component.samples.OudsButtonIconOnlyOnColoredBackgroundSample
 */
@Composable
fun OudsButton(
    icon: OudsButtonIcon,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loader: OudsButtonLoader? = null,
    appearance: OudsButtonAppearance = OudsButtonDefaults.Appearance,
    interactionSource: MutableInteractionSource? = null
) {
    OudsButton(
        nullableIcon = icon,
        nullableLabel = null,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        loader = loader,
        appearance = appearance,
        interactionSource = interactionSource
    )
}

/**
 * Buttons are interactive elements designed to trigger specific actions or events when tapped by a user.
 *
 * This version of the button uses the *text + icon* layout which should remain specific to some clearly identified contexts (e.g. the use of an icon with a
 * "Play" button is standard in the context of TV or video streaming).
 * Other layouts are available for this component: *text only* and *icon only*.
 *
 * Note that in the case it is placed in an [OudsColoredBox], its monochrome variant is automatically displayed.
 * The tokens associated with these specific colors can be customized by overriding [OudsButtonMonoTokens].
 *
 * Rounded corners can be enabled or disabled using the [OudsThemeSettings.roundedCornerButtons] property of an [OudsThemeSettings] when calling the [com.orange.ouds.core.theme.OudsTheme] method.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://unified-design-system.orange.com/472794e18/p/48a788-button)
 *
 * > Design version: 3.1.0
 *
 * @param icon Icon displayed in the button. Use an icon to add additional affordance where the icon has a clear and well-established meaning.
 * @param label Label displayed in the button which describes the button action. Use action verbs or phrases to tell the user what will happen next.
 * @param onClick Callback invoked when the button is clicked.
 * @param modifier [Modifier] applied to the button.
 * @param enabled Controls the enabled state of the button when there is no [loader].
 *   When `false`, this button will not be clickable.
 *   Has no effect when [loader] is not null.
 * @param loader An optional loading progress indicator displayed in the button to indicate an ongoing operation.
 * @param appearance Appearance of the button among [OudsButtonAppearance] values.
 *   A button with [OudsButtonAppearance.Negative] is not allowed as a direct or indirect child of an [OudsColoredBox] and will throw an [IllegalStateException].
 * @param interactionSource An optional hoisted [MutableInteractionSource] for observing and emitting [Interaction]s for this button. Note that if `null`
 *   is provided, interactions will still happen internally.
 *
 * @sample com.orange.ouds.core.component.samples.OudsButtonTextAndIconSample
 *
 * @sample com.orange.ouds.core.component.samples.OudsButtonTextAndIconOnColoredBackgroundSample
 */
@Composable
fun OudsButton(
    icon: OudsButtonIcon,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loader: OudsButtonLoader? = null,
    appearance: OudsButtonAppearance = OudsButtonDefaults.Appearance,
    interactionSource: MutableInteractionSource? = null
) {
    OudsButton(
        nullableIcon = icon,
        nullableLabel = label,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        loader = loader,
        appearance = appearance,
        interactionSource = interactionSource
    )
}

@Composable
@JvmName("OudsButtonNullableIconAndLabel")
private fun OudsButton(
    nullableIcon: OudsButtonIcon?,
    nullableLabel: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loader: OudsButtonLoader? = null,
    appearance: OudsButtonAppearance = OudsButtonDefaults.Appearance,
    interactionSource: MutableInteractionSource? = null
) {
    val icon = nullableIcon
    val label = nullableLabel
    val forbiddenAppearancesOnColoredBox = remember { listOf(OudsButtonAppearance.Brand, OudsButtonAppearance.Negative) }
    val isForbidden = (appearance in forbiddenAppearancesOnColoredBox) && LocalColorMode.current != null
    CheckedContent(
        expression = !isForbidden,
        exceptionMessage = { "An OudsButton with $appearance appearance displayed as a direct or indirect child of an OudsColoredBox is not allowed." },
        previewMessage = { if (icon != null && label == null) "⛔" else "Not on a\ncolored\nbackground" }
    ) {
        val buttonTokens = OudsTheme.componentsTokens.button
        @Suppress("NAME_SHADOWING") val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
        val interactionState by interactionSource.collectInteractionStateAsState()
        val state = getButtonState(enabled = enabled, loader = loader, interactionState = interactionState)
        val iconScale = if (icon != null && label == null) LocalConfiguration.current.fontScale else 1.0f
        val maxHeight = if (icon != null && label == null) buttonTokens.sizeMaxHeightIconOnly.value * iconScale else Dp.Unspecified
        val borderRadius = if (LocalThemeSettings.current.roundedCornerButtons == true) buttonTokens.borderRadiusRounded else buttonTokens.borderRadiusDefault
        val shape = RoundedCornerShape(borderRadius.value)

        val stateDescription = if (state == OudsButtonState.Loading) stringResource(id = R.string.core_common_loading_a11y) else ""
        val contentColor = rememberInteractionColor(interactionState = interactionState) { buttonInteractionState ->
            val buttonState = getButtonState(enabled = enabled, loader = loader, interactionState = buttonInteractionState)
            contentColor(appearance = appearance, state = buttonState)
        }
        val backgroundColor = rememberInteractionColor(interactionState = interactionState) { buttonInteractionState ->
            val buttonState = getButtonState(enabled = enabled, loader = loader, interactionState = buttonInteractionState)
            backgroundColor(appearance = appearance, state = buttonState)
        }
        val borderWidth = rememberInteractionValue(
            interactionState = interactionState,
            toAnimatableFloat = { it?.value.orElse { 0f } },
            fromAnimatableFloat = { it.dp }
        ) { buttonInteractionState ->
            val buttonState = getButtonState(enabled = enabled, loader = loader, interactionState = buttonInteractionState)
            borderWidth(appearance = appearance, state = buttonState)
        }
        val borderColor = rememberNullableInteractionColor(interactionState = interactionState) { buttonInteractionState ->
            val buttonState = getButtonState(enabled = enabled, loader = loader, interactionState = buttonInteractionState)
            borderColor(appearance = appearance, state = buttonState)
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
                    enabled = state !in remember { listOf(OudsButtonState.Disabled, OudsButtonState.Loading) },
                    interactionSource = interactionSource,
                    indication = InteractionValuesIndication(contentColor, backgroundColor, borderColor, borderWidth),
                    onClick = onClick
                ),
            contentAlignment = Alignment.Center
        ) {
            if (state == OudsButtonState.Loading) {
                val progress = if (getPreviewEnumEntry<OudsButtonState>() == OudsButtonState.Loading) 0.75f else loader?.progress
                ProgressIndicator(appearance = appearance, progress = progress, scale = iconScale)
            }

            val alpha = if (state == OudsButtonState.Loading) 0f else 1f
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
                        extraParameters = OudsButtonIcon.ExtraParameters(tint = contentColor.value)
                    )
                }
                if (label != null) {
                    Text(
                        modifier = modifier,
                        text = label,
                        color = contentColor.value,
                        style = OudsTheme.typography.label.strong.large
                    )
                }
            }
        }
    }
}

@Composable
private fun getButtonState(enabled: Boolean, loader: OudsButtonLoader?, interactionState: InteractionState): OudsButtonState {
    return getPreviewEnumEntry<OudsButtonState>().orElse {
        if (loader != null) {
            OudsButtonState.Loading
        } else {
            when {
                !enabled -> OudsButtonState.Disabled
                interactionState == InteractionState.Hovered -> OudsButtonState.Hovered
                interactionState == InteractionState.Pressed -> OudsButtonState.Pressed
                interactionState == InteractionState.Focused -> OudsButtonState.Focused
                else -> OudsButtonState.Enabled
            }
        }
    }
}

@Composable
private fun borderWidth(appearance: OudsButtonAppearance, state: OudsButtonState): Dp? {
    return with(OudsTheme.componentsTokens.button) {
        when (appearance) {
            OudsButtonAppearance.Default -> when (state) {
                OudsButtonState.Enabled,
                OudsButtonState.Disabled -> borderWidthDefault.value
                OudsButtonState.Hovered,
                OudsButtonState.Pressed,
                OudsButtonState.Loading -> if (LocalColorMode.current?.monochrome == true) borderWidthDefaultInteractionMono.value else borderWidthDefaultInteraction.value
                OudsButtonState.Focused -> OudsTheme.borders.width.focusInset
            }
            OudsButtonAppearance.Strong,
            OudsButtonAppearance.Brand,
            OudsButtonAppearance.Minimal,
            OudsButtonAppearance.Negative -> if (state == OudsButtonState.Focused) OudsTheme.borders.width.focusInset else null
        }
    }
}

@Composable
private fun borderColor(appearance: OudsButtonAppearance, state: OudsButtonState): Color? {
    return if (LocalColorMode.current?.monochrome == true) {
        with(OudsTheme.componentsTokens.buttonMonochrome) {
            when (appearance) {
                OudsButtonAppearance.Default -> when (state) {
                    OudsButtonState.Enabled -> colorBorderDefaultEnabled
                    OudsButtonState.Hovered -> colorBorderDefaultHover
                    OudsButtonState.Pressed -> colorBorderDefaultPressed
                    OudsButtonState.Loading -> colorBorderDefaultLoading
                    OudsButtonState.Disabled -> colorBorderDefaultDisabled
                    OudsButtonState.Focused -> colorBorderDefaultFocus
                }.value
                OudsButtonAppearance.Strong -> when (state) {
                    OudsButtonState.Enabled -> colorBorderStrongEnabled
                    OudsButtonState.Hovered -> colorBorderStrongHover
                    OudsButtonState.Pressed -> colorBorderStrongPressed
                    OudsButtonState.Loading -> colorBorderStrongLoading
                    OudsButtonState.Disabled -> colorBorderStrongDisabled
                    OudsButtonState.Focused -> colorBorderStrongFocus
                }.value
                OudsButtonAppearance.Minimal -> null
                OudsButtonAppearance.Brand,
                OudsButtonAppearance.Negative -> Color.Unspecified // Not allowed, exception thrown at the beginning of OudsButton
            }
        }
    } else {
        with(OudsTheme.componentsTokens.button) {
            when (appearance) {
                OudsButtonAppearance.Default -> when (state) {
                    OudsButtonState.Enabled -> colorBorderDefaultEnabled
                    OudsButtonState.Hovered -> colorBorderDefaultHover
                    OudsButtonState.Pressed -> colorBorderDefaultPressed
                    OudsButtonState.Loading -> colorBorderDefaultLoading
                    OudsButtonState.Disabled -> colorBorderDefaultDisabled
                    OudsButtonState.Focused -> colorBorderDefaultFocus
                }
                OudsButtonAppearance.Strong,
                OudsButtonAppearance.Brand,
                OudsButtonAppearance.Minimal,
                OudsButtonAppearance.Negative -> null
            }
        }?.value
    }
}

@Composable
private fun backgroundColor(appearance: OudsButtonAppearance, state: OudsButtonState): Color {
    return if (LocalColorMode.current?.monochrome == true) {
        with(OudsTheme.componentsTokens.buttonMonochrome) {
            when (appearance) {
                OudsButtonAppearance.Default -> when (state) {
                    OudsButtonState.Enabled -> colorBgDefaultEnabled
                    OudsButtonState.Focused -> colorBgDefaultFocus
                    OudsButtonState.Hovered -> colorBgDefaultHover
                    OudsButtonState.Pressed -> colorBgDefaultPressed
                    OudsButtonState.Loading -> colorBgDefaultLoading
                    OudsButtonState.Disabled -> colorBgDefaultDisabled
                }.value
                OudsButtonAppearance.Minimal -> when (state) {
                    OudsButtonState.Enabled,
                    OudsButtonState.Disabled -> Color.Transparent
                    OudsButtonState.Focused -> colorBgMinimalFocus.value
                    OudsButtonState.Hovered -> colorBgMinimalHover.value
                    OudsButtonState.Pressed -> colorBgMinimalPressed.value
                    OudsButtonState.Loading -> if (isOudsInDarkTheme()) OudsTheme.colorScheme.repository.opacity.black.higher else OudsTheme.colorScheme.repository.opacity.white.higher
                }
                OudsButtonAppearance.Strong -> when (state) {
                    OudsButtonState.Enabled -> colorBgStrongEnabled
                    OudsButtonState.Focused -> colorBgStrongFocus
                    OudsButtonState.Hovered -> colorBgStrongHover
                    OudsButtonState.Pressed -> colorBgStrongPressed
                    OudsButtonState.Loading -> colorBgStrongLoading
                    OudsButtonState.Disabled -> colorBgStrongDisabled
                }.value
                OudsButtonAppearance.Brand,
                OudsButtonAppearance.Negative -> Color.Unspecified // Not allowed, exception thrown at the beginning of OudsButton
            }
        }
    } else {
        with(OudsTheme.componentsTokens.button) {
            when (appearance) {
                OudsButtonAppearance.Default -> when (state) {
                    OudsButtonState.Enabled -> colorBgDefaultEnabled
                    OudsButtonState.Focused -> colorBgDefaultFocus
                    OudsButtonState.Hovered -> colorBgDefaultHover
                    OudsButtonState.Pressed -> colorBgDefaultPressed
                    OudsButtonState.Loading -> colorBgDefaultLoading
                    OudsButtonState.Disabled -> colorBgDefaultDisabled
                }.value
                OudsButtonAppearance.Minimal -> when (state) {
                    OudsButtonState.Enabled,
                    OudsButtonState.Disabled -> Color.Transparent
                    OudsButtonState.Focused -> colorBgMinimalFocus.value
                    OudsButtonState.Hovered -> colorBgMinimalHover.value
                    OudsButtonState.Pressed -> colorBgMinimalPressed.value
                    OudsButtonState.Loading -> OudsTheme.colorScheme.action.support.loading
                }
                OudsButtonAppearance.Strong -> when (state) {
                    OudsButtonState.Enabled -> OudsTheme.colorScheme.action.enabled
                    OudsButtonState.Focused -> OudsTheme.colorScheme.action.focus
                    OudsButtonState.Hovered -> OudsTheme.colorScheme.action.hover
                    OudsButtonState.Pressed -> OudsTheme.colorScheme.action.pressed
                    OudsButtonState.Loading -> OudsTheme.colorScheme.action.loading
                    OudsButtonState.Disabled -> OudsTheme.colorScheme.action.disabled
                }
                OudsButtonAppearance.Brand -> when (state) {
                    OudsButtonState.Enabled -> colorBgBrandEnabled.value
                    OudsButtonState.Focused -> colorBgBrandFocus.value
                    OudsButtonState.Hovered -> colorBgBrandHover.value
                    OudsButtonState.Pressed -> colorBgBrandPressed.value
                    OudsButtonState.Loading -> colorBgBrandLoading.value
                    OudsButtonState.Disabled -> OudsTheme.colorScheme.action.disabled
                }
                OudsButtonAppearance.Negative -> when (state) {
                    OudsButtonState.Enabled -> OudsTheme.colorScheme.action.negative.enabled
                    OudsButtonState.Focused -> OudsTheme.colorScheme.action.negative.focus
                    OudsButtonState.Hovered -> OudsTheme.colorScheme.action.negative.hover
                    OudsButtonState.Pressed -> OudsTheme.colorScheme.action.negative.pressed
                    OudsButtonState.Loading -> OudsTheme.colorScheme.action.negative.loading
                    OudsButtonState.Disabled -> OudsTheme.colorScheme.action.disabled
                }
            }
        }
    }
}

@Composable
private fun contentColor(appearance: OudsButtonAppearance, state: OudsButtonState): Color {
    return if (LocalColorMode.current?.monochrome == true) {
        with(OudsTheme.componentsTokens.buttonMonochrome) {
            when (appearance) {
                OudsButtonAppearance.Default -> when (state) {
                    OudsButtonState.Enabled -> colorContentDefaultEnabled
                    OudsButtonState.Focused -> colorContentDefaultFocus
                    OudsButtonState.Hovered -> colorContentDefaultHover
                    OudsButtonState.Pressed -> colorContentDefaultPressed
                    OudsButtonState.Loading -> colorContentDefaultLoading
                    OudsButtonState.Disabled -> colorContentDefaultDisabled
                }.value
                OudsButtonAppearance.Minimal -> when (state) {
                    OudsButtonState.Enabled -> colorContentMinimalEnabled
                    OudsButtonState.Focused -> colorContentMinimalFocus
                    OudsButtonState.Hovered -> colorContentMinimalHover
                    OudsButtonState.Pressed -> colorContentMinimalPressed
                    OudsButtonState.Loading -> colorContentMinimalLoading
                    OudsButtonState.Disabled -> colorContentMinimalDisabled
                }.value
                OudsButtonAppearance.Strong -> when (state) {
                    OudsButtonState.Enabled -> colorContentStrongEnabled
                    OudsButtonState.Focused -> colorContentStrongFocus
                    OudsButtonState.Hovered -> colorContentStrongHover
                    OudsButtonState.Pressed -> colorContentStrongPressed
                    OudsButtonState.Loading -> colorContentStrongLoading
                    OudsButtonState.Disabled -> colorContentStrongDisabled
                }.value
                OudsButtonAppearance.Brand,
                OudsButtonAppearance.Negative -> Color.Unspecified // Not allowed, exception thrown at the beginning of OudsButton
            }
        }
    } else {
        with(OudsTheme.componentsTokens.button) {
            when (appearance) {
                OudsButtonAppearance.Default -> when (state) {
                    OudsButtonState.Enabled -> colorContentDefaultEnabled
                    OudsButtonState.Focused -> colorContentDefaultFocus
                    OudsButtonState.Hovered -> colorContentDefaultHover
                    OudsButtonState.Pressed -> colorContentDefaultPressed
                    OudsButtonState.Loading -> colorContentDefaultLoading
                    OudsButtonState.Disabled -> colorContentDefaultDisabled
                }.value
                OudsButtonAppearance.Minimal -> when (state) {
                    OudsButtonState.Enabled -> colorContentMinimalEnabled
                    OudsButtonState.Focused -> colorContentMinimalFocus
                    OudsButtonState.Hovered -> colorContentMinimalHover
                    OudsButtonState.Pressed -> colorContentMinimalPressed
                    OudsButtonState.Loading -> colorContentMinimalLoading
                    OudsButtonState.Disabled -> colorContentMinimalDisabled
                }.value
                OudsButtonAppearance.Strong -> when (state) {
                    OudsButtonState.Enabled -> OudsTheme.colorScheme.content.onAction.enabled
                    OudsButtonState.Focused -> OudsTheme.colorScheme.content.onAction.focus
                    OudsButtonState.Hovered -> OudsTheme.colorScheme.content.onAction.hover
                    OudsButtonState.Pressed -> OudsTheme.colorScheme.content.onAction.pressed
                    OudsButtonState.Loading -> OudsTheme.colorScheme.content.onAction.loading
                    OudsButtonState.Disabled -> OudsTheme.colorScheme.content.onAction.disabled
                }
                OudsButtonAppearance.Brand -> when (state) {
                    OudsButtonState.Enabled -> colorContentBrandEnabled.value
                    OudsButtonState.Focused -> colorContentBrandFocus.value
                    OudsButtonState.Hovered -> colorContentBrandHover.value
                    OudsButtonState.Pressed -> colorContentBrandPressed.value
                    OudsButtonState.Loading -> colorContentBrandLoading.value
                    OudsButtonState.Disabled -> OudsTheme.colorScheme.content.onAction.disabled
                }
                OudsButtonAppearance.Negative -> when (state) {
                    OudsButtonState.Enabled,
                    OudsButtonState.Hovered,
                    OudsButtonState.Pressed,
                    OudsButtonState.Loading,
                    OudsButtonState.Focused -> OudsTheme.colorScheme.content.onStatus.negative.emphasized
                    OudsButtonState.Disabled -> OudsTheme.colorScheme.content.onAction.disabled
                }
            }
        }
    }
}

@Composable
private fun contentPadding(icon: OudsButtonIcon?, label: String?): PaddingValues {
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
private fun ProgressIndicator(appearance: OudsButtonAppearance, progress: Float?, scale: Float) {
    val modifier = Modifier
        .size(OudsTheme.componentsTokens.button.sizeLoader.value * scale)
        .semantics { hideFromAccessibility() }
    val color = contentColor(appearance = appearance, state = OudsButtonState.Loading)

    OudsCircularProgressIndicator(modifier = modifier, color = color, progress = progress, scale = scale)
}

/**
 * Default values for [OudsButton].
 */
object OudsButtonDefaults {

    /**
     * Default appearance of an [OudsButton].
     */
    val Appearance = OudsButtonAppearance.Default
}

/**
 * An icon in an [OudsButton].
 * This icon is non-clickable.
 */
class OudsButtonIcon private constructor(
    graphicsObject: Any,
    val contentDescription: String
) : OudsComponentIcon<OudsButtonIcon.ExtraParameters>(ExtraParameters::class.java, graphicsObject, contentDescription) {

    @ConsistentCopyVisibility
    data class ExtraParameters internal constructor(
        internal val tint: Color
    ) : OudsComponentContent.ExtraParameters()

    /**
     * Creates an instance of [OudsButtonIcon].
     *
     * @param painter Painter of the icon.
     * @param contentDescription The content description associated with this [OudsButtonIcon]. This value is ignored if the button also contains label.
     */
    constructor(painter: Painter, contentDescription: String) : this(painter as Any, contentDescription)

    /**
     * Creates an instance of [OudsButtonIcon].
     *
     * @param imageVector Image vector of the icon.
     * @param contentDescription The content description associated with this [OudsButtonIcon]. This value is ignored if the button also contains label.
     */
    constructor(imageVector: ImageVector, contentDescription: String) : this(imageVector as Any, contentDescription)

    /**
     * Creates an instance of [OudsButtonIcon].
     *
     * @param bitmap Image bitmap of the icon.
     * @param contentDescription The content description associated with this [OudsButtonIcon]. This value is ignored if the button also contains label.
     */
    constructor(bitmap: ImageBitmap, contentDescription: String) : this(bitmap as Any, contentDescription)

    override val tint: Color?
        @Composable
        get() = extraParameters.tint
}

/**
 * Represents the appearance of an [OudsButton].
 */
enum class OudsButtonAppearance {
    /**
     * Default buttons are used for actions which are not mandatory or essential for the user.
     * Often screens will include multiple Outline buttons alongside one of the Full button.
     */
    Default,

    /**
     * The Strong button on the page should be singular and prominent, ideally limited to one per view.
     * It should be reserved for the most critical action, such as "Buy," "Save," "Submit," etc.
     */
    Strong,

    /**
     * A brand primary color alternative to the Strong button. To be used sparingly for high-value specific actions or to visually anchor a brand moment.
     * Do not use it as the default primary button in your interfaces.
     */
    Brand,

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
 * A circular loading indicator displayed in an [OudsButton].
 *
 * @param progress The loading progress, where 0.0 represents no progress and 1.0 represents full progress.
 *   Values outside of this range are coerced into the range.
 *   Set this value to `null` to display a circular indeterminate progress indicator.
 */
data class OudsButtonLoader(val progress: Float?)

internal enum class OudsButtonState {
    Enabled, Hovered, Pressed, Loading, Disabled, Focused
}

@PreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsButton(@PreviewParameter(OudsButtonPreviewParameterProvider::class) parameter: OudsButtonPreviewParameter) {
    PreviewOudsButton(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsButton(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsButtonPreviewParameter
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        val label = if (hasLabel) appearance.name else null
        val icon = if (hasIcon) OudsButtonIcon(Icons.Filled.FavoriteBorder, "") else null
        val content: @Composable () -> Unit = {
            PreviewEnumEntries<OudsButtonState>(columnCount = 2) {
                OudsButton(nullableIcon = icon, nullableLabel = label, onClick = {}, appearance = appearance)
            }
        }
        if (onColoredBox) {
            OudsColoredBox(color = OudsColoredBoxColor.BrandPrimary) {
                content()
            }
        } else {
            content()
        }
    }
}

@Preview
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsButtonWithRoundedCorners() = PreviewOudsButtonWithRoundedCorners(theme = getPreviewTheme())

@Composable
internal fun PreviewOudsButtonWithRoundedCorners(theme: OudsThemeContract) =
    OudsPreview(theme = theme.mapSettings { it.copy(roundedCornerButtons = true) }) {
        val appearance = OudsButtonAppearance.Default
        PreviewEnumEntries<OudsButtonState>(columnCount = 2) { state ->
            OudsButton(
                nullableIcon = OudsButtonIcon(Icons.Filled.FavoriteBorder, ""),
                nullableLabel = appearance.name,
                onClick = {},
                appearance = appearance
            )
        }
    }

internal data class OudsButtonPreviewParameter(
    val appearance: OudsButtonAppearance,
    val hasLabel: Boolean,
    val hasIcon: Boolean,
    val onColoredBox: Boolean = false
)

internal class OudsButtonPreviewParameterProvider : BasicPreviewParameterProvider<OudsButtonPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsButtonPreviewParameter>
    get() = buildList {
        OudsButtonAppearance.entries.forEach { appearance ->
            val parameters = listOf(
                OudsButtonPreviewParameter(appearance, hasLabel = true, hasIcon = false),
                OudsButtonPreviewParameter(appearance, hasLabel = true, hasIcon = true),
                OudsButtonPreviewParameter(appearance, hasLabel = false, hasIcon = true),
            )
            addAll(parameters)
            addAll(parameters.map { it.copy(onColoredBox = true) })
        }
    }

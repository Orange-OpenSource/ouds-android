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

@file:OptIn(RestrictedOudsApi::class)

package com.orange.ouds.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.component.common.outerBorder
import com.orange.ouds.core.extensions.InteractionState
import com.orange.ouds.core.extensions.collectInteractionStateAsState
import com.orange.ouds.core.extensions.iconSize
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.takeUnlessHairline
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.getPreviewEnumEntry
import com.orange.ouds.foundation.RestrictedOudsApi
import com.orange.ouds.foundation.extensions.ifNotNull
import com.orange.ouds.foundation.extensions.orElse

@Composable
internal fun OudsBasicChip(
    selectable: Boolean,
    selected: Boolean,
    onClick: () -> Unit,
    label: String?,
    icon: OudsChipIcon?,
    iconPosition: OudsChipIconPosition,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    skeleton: OudsSkeleton? = null,
    interactionSource: MutableInteractionSource? = null,
    content: @Composable OudsChipScope.() -> Unit = { DefaultChipContent(iconPosition) }
) {
    @Suppress("NAME_SHADOWING") val selected = selectable && selected
    val chipTokens = OudsTheme.componentsTokens.chip
    @Suppress("NAME_SHADOWING") val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    val interactionState by interactionSource.collectInteractionStateAsState()
    val state = getChipState(enabled = enabled, skeleton = skeleton, interactionState = interactionState)
    val iconScale = if (icon != null && label == null) LocalConfiguration.current.fontScale else 1.0f
    val shape = RoundedCornerShape(chipTokens.borderRadius.value)

    val contentColor = rememberInteractionColor(interactionState = interactionState) { chipInteractionState ->
        val chipState = getChipState(enabled = enabled, skeleton = skeleton, interactionState = chipInteractionState)
        contentColor(state = chipState, selected = selected)
    }
    val tickColor = rememberNullableInteractionColor(interactionState = interactionState) { chipInteractionState ->
        val chipState = getChipState(enabled = enabled, skeleton = skeleton, interactionState = chipInteractionState)
        tickColor(state = chipState, selected = selected)
    }
    val backgroundColor = rememberInteractionColor(interactionState = interactionState) { chipInteractionState ->
        val chipState = getChipState(enabled = enabled, skeleton = skeleton, interactionState = chipInteractionState)
        backgroundColor(state = chipState, selected = selected)
    }
    val borderWidth = rememberInteractionValue(
        interactionState = interactionState,
        toAnimatableFloat = { it?.value.orElse { 0f } },
        fromAnimatableFloat = { it.dp }
    ) { chipInteractionState ->
        val chipState = getChipState(enabled = enabled, skeleton = skeleton, interactionState = chipInteractionState)
        borderWidth(state = chipState, selected = selected)
    }
    val borderColor = rememberNullableInteractionColor(interactionState = interactionState) { chipInteractionState ->
        val chipState = getChipState(enabled = enabled, skeleton = skeleton, interactionState = chipInteractionState)
        borderColor(state = chipState, selected = selected)
    }

    Box(
        modifier = Modifier.heightIn(min = chipTokens.sizeMinHeightInteractiveArea.value),
        contentAlignment = Alignment.Center
    ) {
        SkeletonLayout(
            modifier = modifier,
            componentState = state,
            state = skeleton?.state,
            securityMargin = false,
            shape = shape
        ) { contentModifier ->
            Box(
                propagateMinConstraints = true,
                modifier = contentModifier
                    .widthIn(min = chipTokens.sizeMinWidth.dp)
                    .heightIn(min = chipTokens.sizeMinHeight.dp)
                    .background(color = backgroundColor.value, shape = shape)
                    .run {
                        ifNotNull(borderWidth.value, borderColor.value) { borderWidth, borderColor ->
                            border(width = borderWidth, color = borderColor, shape = shape)
                        }.orElse {
                            this
                        }
                    }
                    .outerBorder(state = state, shape = shape)
                    .run {
                        val indication = interactionValuesIndication(contentColor, tickColor, backgroundColor, borderColor, borderWidth)
                        if (selectable) {
                            selectable(
                                selected = selected,
                                enabled = state.areInteractionsEnabled,
                                interactionSource = interactionSource,
                                indication = indication,
                                onClick = onClick,
                                role = Role.Button
                            )
                        } else {
                            clickable(
                                enabled = state.areInteractionsEnabled,
                                interactionSource = interactionSource,
                                indication = indication,
                                onClick = onClick,
                                role = Role.Button
                            )
                        }
                    }
                    .padding(paddingValues = contentPadding(label, icon, iconPosition, selected)),
            ) {
                val tickContent: @Composable () -> Unit = {
                    if (selected) {
                        tickColor.value?.let { tickColor ->
                            Icon(
                                modifier = Modifier.size(chipTokens.sizeIcon.value * iconScale),
                                painter = painterResource(id = OudsTheme.drawableResources.component.chip.tick),
                                tint = tickColor,
                                contentDescription = null
                            )
                        }
                    }
                }
                val labelContent: @Composable (Modifier) -> Unit = { modifier ->
                    if (label != null) {
                        Text(
                            modifier = modifier,
                            text = label,
                            color = contentColor.value,
                            style = OudsTheme.typography.label.medium.moderate
                        )
                    }
                }
                val iconContent: @Composable (Modifier) -> Unit = { modifier ->
                    icon?.Content(
                        modifier = modifier
                            .iconSize(chipTokens.sizeIcon.value * iconScale, tinted = icon.tinted)
                            .semantics {
                                contentDescription = if (label == null) icon.contentDescription else ""
                            },
                        extraParameters = OudsChipIcon.ExtraParameters(tint = contentColor.value)
                    )
                }

                val scope = remember { OudsChipScope() }
                with(scope) {
                    this.icon = iconContent
                    this.label = labelContent
                    this.tick = tickContent
                    this.state = state
                    this.contentColor = contentColor.value
                    content()
                }
            }
        }
    }
}

@Composable
private fun getChipState(interactionState: InteractionState, skeleton: OudsSkeleton?, enabled: Boolean): OudsChipState {
    return getPreviewEnumEntry<OudsChipState>().orElse {
        when {
            skeleton != null -> OudsChipState.Skeleton
            !enabled -> OudsChipState.Disabled
            interactionState == InteractionState.Hovered -> OudsChipState.Hovered
            interactionState == InteractionState.Pressed -> OudsChipState.Pressed
            interactionState == InteractionState.Focused -> OudsChipState.Focused
            else -> OudsChipState.Enabled
        }
    }
}

@Composable
private fun borderWidth(state: OudsChipState, selected: Boolean): Dp? {
    return with(OudsTheme.componentsTokens.chip) {
        if (selected) {
            borderWidthSelected
        } else {
            when (state) {
                OudsChipState.Enabled,
                OudsChipState.Disabled -> borderWidthUnselected
                OudsChipState.Hovered,
                OudsChipState.Pressed,
                OudsChipState.Focused -> borderWidthUnselectedInteraction
                OudsChipState.Skeleton -> null
            }
        }?.value
    }?.takeUnlessHairline
}

@Composable
private fun borderColor(state: OudsChipState, selected: Boolean): Color? {
    return with(OudsTheme.componentsTokens.chip) {
        when (state) {
            OudsChipState.Enabled -> if (selected) colorBorderSelectedEnabled.value else colorBorderUnselectedEnabled.value
            OudsChipState.Focused -> if (selected) colorBorderSelectedFocus.value else colorBorderUnselectedFocus.value
            OudsChipState.Hovered -> if (selected) colorBorderSelectedHover.value else colorBorderUnselectedHover.value
            OudsChipState.Pressed -> if (selected) colorBorderSelectedPressed.value else colorBorderUnselectedPressed.value
            OudsChipState.Disabled -> if (selected) colorBorderSelectedDisabled.value else colorBorderUnselectedDisabled.value
            OudsChipState.Skeleton -> null
        }
    }
}

@Composable
private fun backgroundColor(state: OudsChipState, selected: Boolean): Color {
    return with(OudsTheme.componentsTokens.chip) {
        when (state) {
            OudsChipState.Enabled -> if (selected) colorBgSelectedEnabled.value else colorBgUnselectedEnabled.value
            OudsChipState.Focused -> if (selected) colorBgSelectedFocus.value else colorBgUnselectedFocus.value
            OudsChipState.Hovered -> if (selected) colorBgSelectedHover.value else colorBgUnselectedHover.value
            OudsChipState.Pressed -> if (selected) colorBgSelectedPressed.value else colorBgUnselectedPressed.value
            OudsChipState.Disabled -> if (selected) colorBgSelectedDisabled.value else colorBgUnselectedDisabled.value
            OudsChipState.Skeleton -> Color.Transparent
        }
    }
}

@Composable
private fun contentColor(state: OudsChipState, selected: Boolean): Color {
    return with(OudsTheme.componentsTokens.chip) {
        when (state) {
            OudsChipState.Enabled -> if (selected) colorContentSelectedEnabled.value else colorContentUnselectedEnabled.value
            OudsChipState.Focused -> if (selected) colorContentSelectedFocus.value else colorContentUnselectedFocus.value
            OudsChipState.Hovered -> if (selected) colorContentSelectedHover.value else colorContentUnselectedHover.value
            OudsChipState.Pressed -> if (selected) colorContentSelectedPressed.value else colorContentUnselectedPressed.value
            OudsChipState.Disabled -> if (selected) colorContentSelectedDisabled.value else colorContentUnselectedDisabled.value
            OudsChipState.Skeleton -> Color.Transparent
        }
    }
}

@Composable
private fun tickColor(state: OudsChipState, selected: Boolean): Color? {
    return with(OudsTheme.componentsTokens.chip) {
        if (selected) {
            when (state) {
                OudsChipState.Enabled -> colorContentSelectedTickEnabled.value
                OudsChipState.Focused -> colorContentSelectedFocus.value
                OudsChipState.Hovered -> colorContentSelectedHover.value
                OudsChipState.Pressed -> colorContentSelectedPressed.value
                OudsChipState.Disabled -> colorContentSelectedDisabled.value
                OudsChipState.Skeleton -> Color.Transparent // Do not return null otherwise the tick will not be composed and skeleton width will be wrong 
            }
        } else {
            null
        }
    }
}

@Composable
private fun contentPadding(label: String?, icon: OudsChipIcon?, iconPosition: OudsChipIconPosition, selected: Boolean): PaddingValues {
    return with(OudsTheme.componentsTokens.chip) {
        // If chip layout starts with an icon or the tick then we use spacePaddingInlineIcon as the start padding, otherwise spacePaddingInlineIconNone
        val start = if (selected
            || (icon != null && iconPosition == OudsChipIconPosition.Start)
            || (icon != null && label == null)
        ) {
            spacePaddingInlineIcon.value
        } else {
            spacePaddingInlineIconNone.value
        }
        val end = if ((icon != null && iconPosition == OudsChipIconPosition.End)
            || (icon != null && label == null)
        ) {
            spacePaddingInlineIcon.value
        } else {
            spacePaddingInlineIconNone.value
        }
        val vertical = if (label != null) spacePaddingBlock.value else spacePaddingBlockIconOnly.value
        PaddingValues(start = start, top = vertical, end = end, bottom = vertical)
    }
}

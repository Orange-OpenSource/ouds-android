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
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.component.common.outerBorder
import com.orange.ouds.core.component.content.OudsComponentContent
import com.orange.ouds.core.component.content.OudsComponentIcon
import com.orange.ouds.core.extensions.InteractionState
import com.orange.ouds.core.extensions.collectInteractionStateAsState
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.getPreviewEnumEntry
import com.orange.ouds.foundation.extensions.orElse

@Composable
internal fun OudsChip(
    selectable: Boolean,
    selected: Boolean,
    onClick: () -> Unit,
    label: String?,
    icon: OudsChip.Icon?,
    iconPosition: OudsChip.IconPosition,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null
) {
    @Suppress("NAME_SHADOWING") val selected = selectable && selected
    val chipTokens = OudsTheme.componentsTokens.chip
    @Suppress("NAME_SHADOWING") val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    val interactionState by interactionSource.collectInteractionStateAsState()
    val state = getChipState(enabled = enabled, interactionState = interactionState)
    val iconScale = if (icon != null && label == null) LocalConfiguration.current.fontScale else 1.0f
    val shape = RoundedCornerShape(chipTokens.borderRadius.value)

    val contentColor = rememberInteractionColor(interactionState = interactionState) { chipInteractionState ->
        val chipState = getChipState(enabled = enabled, interactionState = chipInteractionState)
        contentColor(state = chipState, selected = selected)
    }
    val tickColor = rememberNullableInteractionColor(interactionState = interactionState) { chipInteractionState ->
        val chipState = getChipState(enabled = enabled, interactionState = chipInteractionState)
        tickColor(state = chipState, selected = selected)
    }
    val backgroundColor = rememberInteractionColor(interactionState = interactionState) { chipInteractionState ->
        val chipState = getChipState(enabled = enabled, interactionState = chipInteractionState)
        backgroundColor(state = chipState, selected = selected)
    }
    val borderWidth = rememberInteractionValue(
        interactionState = interactionState,
        toAnimatableFloat = { it.value },
        fromAnimatableFloat = { it.dp }
    ) { chipInteractionState ->
        val chipState = getChipState(enabled = enabled, interactionState = chipInteractionState)
        borderWidth(state = chipState, selected = selected)
    }
    val borderColor = rememberInteractionColor(interactionState = interactionState) { chipInteractionState ->
        val chipState = getChipState(enabled = enabled, interactionState = chipInteractionState)
        borderColor(state = chipState, selected = selected)
    }

    Box(
        modifier = Modifier.heightIn(min = chipTokens.sizeMinHeightInteractiveArea.value),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = modifier
                .widthIn(min = chipTokens.sizeMinWidth.dp)
                .heightIn(min = chipTokens.sizeMinHeight.dp)
                .background(color = backgroundColor.value, shape = shape)
                .border(width = borderWidth.value, color = borderColor.value, shape = shape)
                .outerBorder(state = state, shape = shape)
                .padding(paddingValues = contentPadding(label, icon, iconPosition, selected))
                .run {
                    val indication = InteractionValuesIndication(contentColor, tickColor, backgroundColor, borderColor, borderWidth)
                    if (selectable) {
                        selectable(
                            selected = selected,
                            enabled = enabled,
                            interactionSource = interactionSource,
                            indication = indication,
                            onClick = onClick,
                            role = Role.Checkbox
                        )
                    } else {
                        clickable(
                            enabled = enabled,
                            interactionSource = interactionSource,
                            indication = indication,
                            onClick = onClick,
                            role = Role.Button
                        )
                    }
                },
            horizontalArrangement = Arrangement.spacedBy(chipTokens.spaceColumnGapIcon.value, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (selected) {
                tickColor.value?.let { tickColor ->
                    Icon(
                        modifier = Modifier.size(chipTokens.sizeIcon.value * iconScale),
                        painter = painterResource(id = OudsTheme.drawableResources.tick),
                        tint = tickColor,
                        contentDescription = null
                    )
                }
            }

            val iconContent: @Composable () -> Unit = {
                icon?.Content(
                    modifier = Modifier
                        .size(chipTokens.sizeIcon.value * iconScale)
                        .semantics {
                            contentDescription = if (label == null) icon.contentDescription else ""
                        },
                    extraParameters = OudsChip.Icon.ExtraParameters(tint = contentColor.value)
                )
            }

            if (iconPosition == OudsChip.IconPosition.Start) {
                iconContent()
            }
            if (label != null) {
                Text(
                    text = label,
                    color = contentColor.value,
                    style = OudsTheme.typography.label.strong.medium,
                    textAlign = TextAlign.Center
                )
            }
            if (iconPosition == OudsChip.IconPosition.End) {
                iconContent()
            }
        }
    }
}

@Composable
private fun getChipState(interactionState: InteractionState, enabled: Boolean): OudsChip.State {
    return getPreviewEnumEntry<OudsChip.State>().orElse {
        when {
            !enabled -> OudsChip.State.Disabled
            interactionState == InteractionState.Hovered -> OudsChip.State.Hovered
            interactionState == InteractionState.Pressed -> OudsChip.State.Pressed
            interactionState == InteractionState.Focused -> OudsChip.State.Focused
            else -> OudsChip.State.Enabled
        }
    }
}

@Composable
private fun borderWidth(state: OudsChip.State, selected: Boolean): Dp {
    return with(OudsTheme.componentsTokens.chip) {
        if (selected) {
            borderWidthSelected
        } else {
            when (state) {
                OudsChip.State.Enabled,
                OudsChip.State.Disabled -> borderWidthUnselected
                OudsChip.State.Hovered,
                OudsChip.State.Pressed,
                OudsChip.State.Focused -> borderWidthUnselectedInteraction
            }
        }.value
    }
}

@Composable
private fun borderColor(state: OudsChip.State, selected: Boolean): Color {
    return with(OudsTheme.componentsTokens.chip) {
        when (state) {
            OudsChip.State.Enabled -> if (selected) colorBorderSelectedEnabled else colorBorderUnselectedEnabled
            OudsChip.State.Focused -> if (selected) colorBorderSelectedFocus else colorBorderUnselectedFocus
            OudsChip.State.Hovered -> if (selected) colorBorderSelectedHover else colorBorderUnselectedHover
            OudsChip.State.Pressed -> if (selected) colorBorderSelectedPressed else colorBorderUnselectedPressed
            OudsChip.State.Disabled -> if (selected) colorBorderSelectedDisabled else colorBorderUnselectedDisabled
        }.value
    }
}

@Composable
private fun backgroundColor(state: OudsChip.State, selected: Boolean): Color {
    return with(OudsTheme.componentsTokens.chip) {
        when (state) {
            OudsChip.State.Enabled -> if (selected) colorBgSelectedEnabled else colorBgUnselectedEnabled
            OudsChip.State.Focused -> if (selected) colorBgSelectedFocus else colorBgUnselectedFocus
            OudsChip.State.Hovered -> if (selected) colorBgSelectedHover else colorBgUnselectedHover
            OudsChip.State.Pressed -> if (selected) colorBgSelectedPressed else colorBgUnselectedPressed
            OudsChip.State.Disabled -> if (selected) colorBgSelectedDisabled else colorBgUnselectedDisabled
        }.value
    }
}

@Composable
private fun contentColor(state: OudsChip.State, selected: Boolean): Color {
    return with(OudsTheme.componentsTokens.chip) {
        when (state) {
            OudsChip.State.Enabled -> if (selected) colorContentSelectedEnabled else colorContentUnselectedEnabled
            OudsChip.State.Focused -> if (selected) colorContentSelectedFocus else colorContentUnselectedFocus
            OudsChip.State.Hovered -> if (selected) colorContentSelectedHover else colorContentUnselectedHover
            OudsChip.State.Pressed -> if (selected) colorContentSelectedPressed else colorContentUnselectedPressed
            OudsChip.State.Disabled -> if (selected) colorContentSelectedDisabled else colorContentUnselectedDisabled
        }.value
    }
}

@Composable
private fun tickColor(state: OudsChip.State, selected: Boolean): Color? {
    return with(OudsTheme.componentsTokens.chip) {
        if (selected) {
            when (state) {
                OudsChip.State.Enabled -> colorContentSelectedTickEnabled
                OudsChip.State.Focused -> colorContentSelectedFocus
                OudsChip.State.Hovered -> colorContentSelectedHover
                OudsChip.State.Pressed -> colorContentSelectedPressed
                OudsChip.State.Disabled -> colorContentSelectedDisabled
            }.value
        } else {
            null
        }
    }
}

@Composable
private fun contentPadding(label: String?, icon: OudsChip.Icon?, iconPosition: OudsChip.IconPosition, selected: Boolean): PaddingValues {
    return with(OudsTheme.componentsTokens.chip) {
        // If chip layout starts with an icon or the tick then we use spacePaddingInlineIcon as the start padding, otherwise spacePaddingInlineIconNone
        val start = if (selected
            || (icon != null && iconPosition == OudsChip.IconPosition.Start)
            || (icon != null && label == null)
        ) {
            spacePaddingInlineIcon.value
        } else {
            spacePaddingInlineIconNone.value
        }
        val end = if ((icon != null && iconPosition == OudsChip.IconPosition.End)
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

/**
 * Contains classes to build an [OudsFilterChip] or an [OudsSuggestionChip].
 */
object OudsChip {

    /**
     * An icon in an [OudsFilterChip] or an [OudsSuggestionChip].
     * This icon is non-clickable.
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
         * Creates an instance of [OudsChip.Icon].
         *
         * @param painter Painter of the icon.
         * @param contentDescription The content description associated with this [OudsChip.Icon]. This value is ignored if the chip also contains label.
         */
        constructor(painter: Painter, contentDescription: String) : this(painter as Any, contentDescription)

        /**
         * Creates an instance of [OudsChip.Icon].
         *
         * @param imageVector Image vector of the icon.
         * @param contentDescription The content description associated with this [OudsChip.Icon]. This value is ignored if the chip also contains label.
         */
        constructor(imageVector: ImageVector, contentDescription: String) : this(imageVector as Any, contentDescription)

        /**
         * Creates an instance of [OudsChip.Icon].
         *
         * @param bitmap Image bitmap of the icon.
         * @param contentDescription The content description associated with this [OudsChip.Icon]. This value is ignored if the chip also contains label.
         */
        constructor(bitmap: ImageBitmap, contentDescription: String) : this(bitmap as Any, contentDescription)

        override val tint: Color?
            @Composable
            get() = extraParameters.tint
    }

    internal enum class State {
        Enabled, Hovered, Pressed, Disabled, Focused
    }

    internal enum class IconPosition {
        Start, End
    }
}

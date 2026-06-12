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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.component.common.outerBorder
import com.orange.ouds.core.component.content.OudsComponentContent
import com.orange.ouds.core.component.content.OudsComponentIcon
import com.orange.ouds.core.extensions.InteractionState
import com.orange.ouds.core.extensions.collectInteractionStateAsState
import com.orange.ouds.core.extensions.iconSize
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.takeUnlessHairline
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.getPreviewEnumEntry
import com.orange.ouds.foundation.LowLevelOudsApi
import com.orange.ouds.foundation.ExperimentalOudsApi
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.theme.tokens.components.OudsChipTokens

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
    interactionSource: MutableInteractionSource? = null,
    content: @Composable OudsChipScope.() -> Unit = { DefaultChipContent(iconPosition) }
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
        toAnimatableFloat = { it?.value.orElse { 0f } },
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
        modifier = Modifier
            .heightIn(min = chipTokens.sizeMinHeightInteractiveArea.value),
        contentAlignment = Alignment.Center
    ) {
        val scope = remember(chipTokens) { OudsChipScope(tokens = chipTokens) }
        Box(
            propagateMinConstraints = true,
            modifier = modifier
                .widthIn(min = chipTokens.sizeMinWidth.dp)
                .heightIn(min = chipTokens.sizeMinHeight.dp)
                .background(color = backgroundColor.value, shape = shape)
                .run {
                    borderWidth.value?.let { borderWidth ->
                        border(width = borderWidth, color = borderColor.value, shape = shape)
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
                            enabled = enabled,
                            interactionSource = interactionSource,
                            indication = indication,
                            onClick = onClick,
                            role = Role.Button
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
                }
                .padding(paddingValues = contentPadding(label, icon, iconPosition, selected)),
        ) {
            val tick: @Composable () -> Unit = {
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
            val labelContent: @Composable () -> Unit = {
                if (label != null) {
                    Text(
                        text = label,
                        color = contentColor.value,
                        style = OudsTheme.typography.label.moderate.medium
                    )
                }
            }
            val iconContent: @Composable () -> Unit = {
                icon?.Content(
                    modifier = Modifier
                        .iconSize(chipTokens.sizeIcon.value * iconScale, icon.tinted)
                        .semantics {
                            contentDescription = if (label == null) icon.contentDescription else ""
                        },
                    extraParameters = OudsChipIcon.ExtraParameters(tint = contentColor.value)
                )
            }

            with(scope) {
                this.icon = iconContent
                this.label = labelContent
                this.tick = tick
                this.state = state
                this.contentColor = contentColor.value
                content()
            }
        }
    }
}

@Composable
private fun getChipState(interactionState: InteractionState, enabled: Boolean): OudsChipState {
    return getPreviewEnumEntry<OudsChipState>().orElse {
        when {
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
            }
        }.value
    }.takeUnlessHairline
}

@Composable
private fun borderColor(state: OudsChipState, selected: Boolean): Color {
    return with(OudsTheme.componentsTokens.chip) {
        when (state) {
            OudsChipState.Enabled -> if (selected) colorBorderSelectedEnabled else colorBorderUnselectedEnabled
            OudsChipState.Focused -> if (selected) colorBorderSelectedFocus else colorBorderUnselectedFocus
            OudsChipState.Hovered -> if (selected) colorBorderSelectedHover else colorBorderUnselectedHover
            OudsChipState.Pressed -> if (selected) colorBorderSelectedPressed else colorBorderUnselectedPressed
            OudsChipState.Disabled -> if (selected) colorBorderSelectedDisabled else colorBorderUnselectedDisabled
        }.value
    }
}

@Composable
private fun backgroundColor(state: OudsChipState, selected: Boolean): Color {
    return with(OudsTheme.componentsTokens.chip) {
        when (state) {
            OudsChipState.Enabled -> if (selected) colorBgSelectedEnabled else colorBgUnselectedEnabled
            OudsChipState.Focused -> if (selected) colorBgSelectedFocus else colorBgUnselectedFocus
            OudsChipState.Hovered -> if (selected) colorBgSelectedHover else colorBgUnselectedHover
            OudsChipState.Pressed -> if (selected) colorBgSelectedPressed else colorBgUnselectedPressed
            OudsChipState.Disabled -> if (selected) colorBgSelectedDisabled else colorBgUnselectedDisabled
        }.value
    }
}

@Composable
private fun contentColor(state: OudsChipState, selected: Boolean): Color {
    return with(OudsTheme.componentsTokens.chip) {
        when (state) {
            OudsChipState.Enabled -> if (selected) colorContentSelectedEnabled else colorContentUnselectedEnabled
            OudsChipState.Focused -> if (selected) colorContentSelectedFocus else colorContentUnselectedFocus
            OudsChipState.Hovered -> if (selected) colorContentSelectedHover else colorContentUnselectedHover
            OudsChipState.Pressed -> if (selected) colorContentSelectedPressed else colorContentUnselectedPressed
            OudsChipState.Disabled -> if (selected) colorContentSelectedDisabled else colorContentUnselectedDisabled
        }.value
    }
}

@Composable
private fun tickColor(state: OudsChipState, selected: Boolean): Color? {
    return with(OudsTheme.componentsTokens.chip) {
        if (selected) {
            when (state) {
                OudsChipState.Enabled -> colorContentSelectedTickEnabled
                OudsChipState.Focused -> colorContentSelectedFocus
                OudsChipState.Hovered -> colorContentSelectedHover
                OudsChipState.Pressed -> colorContentSelectedPressed
                OudsChipState.Disabled -> colorContentSelectedDisabled
            }.value
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

/**
 * An icon in an [OudsFilterChip] or an [OudsSuggestionChip].
 * This icon is not clickable.
 */
class OudsChipIcon private constructor(
    graphicsObject: Any,
    val contentDescription: String,
    override val tinted: Boolean
) : OudsComponentIcon<OudsChipIcon.ExtraParameters, OudsChipIcon>(ExtraParameters::class.java, graphicsObject, contentDescription) {

    @ConsistentCopyVisibility
    data class ExtraParameters internal constructor(
        internal val tint: Color
    ) : OudsComponentContent.ExtraParameters()

    /**
     * Creates an instance of [OudsChipIcon].
     *
     * @param painter Painter of the icon.
     * @param contentDescription The content description associated with this [OudsChipIcon]. This value is ignored if the chip also contains a label.
     * @param tinted Controls whether the icon should be tinted with the theme color. Defaults to `true`.
     *   When set to `false`, the icon is displayed with its original colors (e.g., for multi-color icons).
     *   Note that untinted icons must ensure sufficient contrast with the background for accessibility reasons.
     */
    @JvmOverloads
    constructor(painter: Painter, contentDescription: String, tinted: Boolean = true) : this(painter as Any, contentDescription, tinted)

    /**
     * Creates an instance of [OudsChipIcon].
     *
     * @param imageVector Image vector of the icon.
     * @param contentDescription The content description associated with this [OudsChipIcon]. This value is ignored if the chip also contains a label.
     * @param tinted Controls whether the icon should be tinted with the theme color. Defaults to `true`.
     *   When set to `false`, the icon is displayed with its original colors (e.g., for multi-color icons).
     *   Note that untinted icons must ensure sufficient contrast with the background for accessibility reasons.
     */
    @JvmOverloads
    constructor(imageVector: ImageVector, contentDescription: String, tinted: Boolean = true) : this(imageVector as Any, contentDescription, tinted)

    /**
     * Creates an instance of [OudsChipIcon].
     *
     * @param bitmap Image bitmap of the icon.
     * @param contentDescription The content description associated with this [OudsChipIcon]. This value is ignored if the chip also contains a label.
     * @param tinted Controls whether the icon should be tinted with the theme color. Defaults to `true`.
     *   When set to `false`, the icon is displayed with its original colors (e.g., for multi-color icons).
     *   Note that untinted icons must ensure sufficient contrast with the background for accessibility reasons.
     */
    @JvmOverloads
    constructor(bitmap: ImageBitmap, contentDescription: String, tinted: Boolean = true) : this(bitmap as Any, contentDescription, tinted)

    override val tint: Color?
        @Composable
        get() = extraParameters.tint
}

@LowLevelOudsApi
enum class OudsChipState {
    Enabled, Hovered, Pressed, Disabled, Focused
}

internal enum class OudsChipIconPosition {
    Start, End
}

@ExperimentalOudsApi
@LowLevelOudsApi
class OudsChipScope internal constructor(val tokens: OudsChipTokens) {

    var state: OudsChipState by mutableStateOf(OudsChipState.Enabled)
        internal set

    var contentColor: Color by mutableStateOf(Color.Unspecified)
        internal set

    internal var icon: @Composable () -> Unit = {}

    internal var label: @Composable () -> Unit = {}

    internal var tick: @Composable () -> Unit = {}

    @Composable
    fun Icon() = icon()

    @Composable
    fun Label() = label()

    @Composable
    internal fun Tick() = tick()
}

@Composable
internal fun OudsChipScope.DefaultChipContent(iconPosition: OudsChipIconPosition) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(tokens.spaceColumnGapIcon.value, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Tick()
        if (iconPosition == OudsChipIconPosition.Start) {
            Icon()
        }
        Label()
        if (iconPosition == OudsChipIconPosition.End) {
            Icon()
        }
    }
}

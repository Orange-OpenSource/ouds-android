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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.orange.ouds.core.component.content.OudsComponentContent
import com.orange.ouds.core.component.content.OudsComponentIcon
import com.orange.ouds.core.extensions.InteractionState
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.outerBorder
import com.orange.ouds.core.theme.value


@Composable
internal fun OudsControlItem(
    state: OudsControlItem.State,
    text: String,
    helperText: String?,
    icon: OudsControlItem.Icon?,
    divider: Boolean,
    inverted: Boolean,
    enabled: Boolean,
    readOnly: Boolean,
    error: Boolean,
    errorComponentName: String,
    indicator: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    additionalText: String? = null,
) {
    if (error) {
        if (readOnly) throw IllegalStateException("An $errorComponentName set to readOnly with error parameter activated is not allowed.")
        if (!enabled) throw IllegalStateException("An $errorComponentName set to disabled with error parameter activated is not allowed.")
    }

    val controlItemTokens = OudsTheme.componentsTokens.controlItem

    val itemIcon: (@Composable () -> Unit)? = icon?.let {
        {
            icon.Content(
                extraParameters = OudsControlItem.Icon.ExtraParameters(
                    tint = if (state == OudsControlItem.State.Disabled) OudsTheme.colorScheme.content.disabled else OudsTheme.colorScheme.content.default
                )
            )
        }
    }

    val leadingElement: (@Composable () -> Unit)? = if (inverted) itemIcon else indicator
    val trailingElement: (@Composable () -> Unit)? = if (inverted) indicator else itemIcon
    val dividerThickness = 1.dp

    Column(
        modifier = modifier
            .height(IntrinsicSize.Min)
            .heightIn(min = controlItemTokens.sizeMinHeight.dp)
            .widthIn(min = controlItemTokens.sizeMinWidth.dp)
            .background(color = backgroundColor(state = state))
            .outerBorder(state = state)
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .padding(all = controlItemTokens.spaceInset.value),
            horizontalArrangement = Arrangement.spacedBy(controlItemTokens.spaceColumnGap.value)
        ) {
            leadingElement?.let { LeadingTrailingBox(leadingElement) }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically),
                verticalArrangement = Arrangement.spacedBy(controlItemTokens.spaceRowGap.value)
            ) {
                Text(text = text, style = OudsTheme.typography.label.default.large, color = textColor(state = state, error = error))
                if (!additionalText.isNullOrBlank()) {
                    Text(
                        text = additionalText,
                        style = OudsTheme.typography.label.strong.medium,
                        color = additionalTextColor(state = state)
                    )
                }
                if (!helperText.isNullOrBlank()) {
                    Text(
                        text = helperText,
                        style = OudsTheme.typography.label.default.medium,
                        color = helperTextColor(state = state)
                    )
                }
            }
            trailingElement?.let { LeadingTrailingBox(trailingElement) }
        }
        if (divider) {
            HorizontalDivider(
                thickness = dividerThickness,
                color = OudsTheme.colorScheme.border.default.copy(alpha = 0.2f)
            ) //TODO Replace with OudsHorizontalDivider when available
        }
    }
}

/**
 * Contains classes to build a control item: [com.orange.ouds.core.component.OudsCheckboxItem], [com.orange.ouds.core.component.OudsRadioButtonItem].
 */
object OudsControlItem {
    internal enum class State {
        Enabled, Hovered, Pressed, Disabled, Focused, ReadOnly
    }

    /**
     * An icon in a control item like [OudsCheckboxItem] or [OudsRadioButtonItem].
     * It is non-clickable and no content description is needed cause a checkbox item text is always present.
     */
    class Icon private constructor(
        graphicsObject: Any,
    ) : OudsComponentIcon<Icon.ExtraParameters>(ExtraParameters::class.java, graphicsObject, "") {

        @ConsistentCopyVisibility
        data class ExtraParameters internal constructor(
            internal val tint: Color
        ) : OudsComponentContent.ExtraParameters()

        /**
         * Creates an instance of [OudsRadioButtonItem.Icon].
         *
         * @param painter Painter of the icon.
         */
        constructor(painter: Painter) : this(painter as Any)

        /**
         * Creates an instance of [OudsRadioButtonItem.Icon].
         *
         * @param imageVector Image vector of the icon.
         */
        constructor(imageVector: ImageVector) : this(imageVector as Any)

        /**
         * Creates an instance of [OudsRadioButtonItem.Icon].
         *
         * @param bitmap Image bitmap of the icon.
         */
        constructor(bitmap: ImageBitmap) : this(bitmap as Any)

        override val tint: Color?
            @Composable
            get() = extraParameters.tint

        @Composable
        override fun Content(modifier: Modifier) {
            super.Content(modifier.size(OudsTheme.componentsTokens.controlItem.sizeIcon.value))
        }
    }
}

@Composable
internal fun rememberOudsControlItemState(
    enabled: Boolean,
    readOnly: Boolean,
    interactionState: InteractionState
): OudsControlItem.State = remember(enabled, readOnly, interactionState) {
    when {
        !enabled -> OudsControlItem.State.Disabled
        readOnly -> OudsControlItem.State.ReadOnly
        interactionState == InteractionState.Hovered -> OudsControlItem.State.Hovered
        interactionState == InteractionState.Pressed -> OudsControlItem.State.Pressed
        interactionState == InteractionState.Focused -> OudsControlItem.State.Focused
        else -> OudsControlItem.State.Enabled
    }
}

@Composable
private fun LeadingTrailingBox(content: @Composable () -> Unit) {
    val assetContainerMaxHeight = OudsTheme.componentsTokens.controlItem.sizeMaxHeightAssetsContainer.dp
    val checkboxIndicatorSize = OudsTheme.componentsTokens.checkbox.sizeMinHeight.dp

    val maxHeight = max(assetContainerMaxHeight, checkboxIndicatorSize)
    Box(
        modifier = Modifier
            .heightIn(max = maxHeight)
            .fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Composable
private fun Modifier.outerBorder(state: OudsControlItem.State) = if (state == OudsControlItem.State.Focused) {
    outerBorder(
        width = OudsTheme.borders.width.focus,
        color = OudsTheme.colorScheme.border.focus,
        insetWidth = OudsTheme.borders.width.focusInset,
        insetColor = OudsTheme.colorScheme.border.focusInset
    )
} else {
    this
}

@Composable
private fun backgroundColor(state: OudsControlItem.State): Color {
    return with(OudsTheme.componentsTokens.controlItem) {
        when (state) {
            OudsControlItem.State.Enabled, OudsControlItem.State.Disabled, OudsControlItem.State.ReadOnly -> Color.Transparent
            OudsControlItem.State.Hovered -> colorBgHover.value
            OudsControlItem.State.Pressed -> colorBgPressed.value
            OudsControlItem.State.Focused -> colorBgFocus.value
        }
    }
}

@Composable
private fun textColor(state: OudsControlItem.State, error: Boolean) =
    if (error) {
        with(OudsTheme.colorScheme.action.negative) {
            when (state) {
                OudsControlItem.State.Enabled -> enabled
                OudsControlItem.State.Hovered -> hover
                OudsControlItem.State.Pressed -> pressed
                OudsControlItem.State.Focused -> focus
                OudsControlItem.State.Disabled, OudsControlItem.State.ReadOnly -> Color.Unspecified // Not allowed, exception thrown at the beginning of each control item
            }
        }
    } else {
        if (state == OudsControlItem.State.Disabled) OudsTheme.colorScheme.content.disabled else OudsTheme.colorScheme.content.default
    }

@Composable
private fun additionalTextColor(state: OudsControlItem.State) =
    if (state == OudsControlItem.State.Disabled) OudsTheme.colorScheme.content.disabled else OudsTheme.colorScheme.content.default

@Composable
private fun helperTextColor(state: OudsControlItem.State) =
    if (state == OudsControlItem.State.Disabled) OudsTheme.colorScheme.content.disabled else OudsTheme.colorScheme.content.muted
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.orange.ouds.core.component.common.outerBorder
import com.orange.ouds.core.component.content.OudsComponentContent
import com.orange.ouds.core.component.content.OudsComponentIcon
import com.orange.ouds.core.extensions.InteractionState
import com.orange.ouds.core.extensions.filter
import com.orange.ouds.core.extensions.last
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.CheckedContent
import com.orange.ouds.core.utilities.EdgeToEdgePaddingElement
import com.orange.ouds.core.utilities.edgeToEdgePadding
import com.orange.ouds.core.utilities.getPreviewState
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider

/**
 * Control item composable helps to factorize common layout elements between [OudsCheckboxItem], [OudsTriStateCheckboxItem], [OudsRadioButtonItem]
 * and [OudsSwitchItem].
 */
@Composable
internal fun OudsControlItem(
    state: OudsControlItem.State,
    label: String,
    helperText: String?,
    icon: OudsControlItem.Icon?,
    divider: Boolean,
    enabled: Boolean,
    readOnly: Boolean,
    error: Boolean,
    errorComponentName: String,
    indicator: @Composable () -> Unit,
    indicatorPosition: OudsControlItem.IndicatorPosition,
    checkedContentPreviewStatus: String,
    modifier: Modifier = Modifier,
    additionalLabel: String? = null,
    handleHighContrastMode: Boolean = false
) {
    val previewState = getPreviewState<OudsControlItem.State>()
    val isReadOnlyPreviewState = previewState == OudsControlItem.State.ReadOnly
    val isDisabledPreviewState = previewState == OudsControlItem.State.Disabled
    val isForbidden = error && (readOnly || !enabled || isReadOnlyPreviewState || isDisabledPreviewState)

    CheckedContent(
        expression = !isForbidden,
        exceptionMessage = {
            val parameter = if (readOnly) "readOnly" else "disabled"
            "An $errorComponentName set to $parameter with error parameter activated is not allowed."
        },
        previewMessage = {
            val stateDescription = if (isReadOnlyPreviewState) "Read only" else "Disabled"
            "Error $checkedContentPreviewStatus status for $stateDescription state is not relevant"
        }
    ) {
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


        val leadingElement: (@Composable () -> Unit)? = if (indicatorPosition == OudsControlItem.IndicatorPosition.Start) indicator else itemIcon
        val trailingElement: (@Composable () -> Unit)? = if (indicatorPosition == OudsControlItem.IndicatorPosition.Start) itemIcon else indicator

        val filteredModifier = modifier.filter { it !is EdgeToEdgePaddingElement }
        Box(
            modifier = filteredModifier
                .height(IntrinsicSize.Min)
                .heightIn(min = controlItemTokens.sizeMinHeight.dp)
                .widthIn(min = controlItemTokens.sizeMinWidth.dp)
                .outerBorder(state = state, handleHighContrastMode = handleHighContrastMode),
            contentAlignment = Alignment.BottomCenter
        ) {
            val edgeToEdgePaddingModifier = modifier.filter { it is EdgeToEdgePaddingElement }
            Row(
                modifier = Modifier
                    .padding(vertical = controlItemTokens.spaceInset.value)
                    .edgeToEdgePadding(true)
                    .then(edgeToEdgePaddingModifier) // Override edgeToEdgePadding setting
                    .run {
                        // Apply default horizontal padding if edgeToEdgePadding is disabled
                        val element = edgeToEdgePaddingModifier.last() as? EdgeToEdgePaddingElement
                        if (element?.enabled == false) padding(horizontal = controlItemTokens.spaceInset.value) else this
                    },
                horizontalArrangement = Arrangement.spacedBy(controlItemTokens.spaceColumnGap.value)
            ) {
                leadingElement?.let { LeadingTrailingBox(leadingElement) }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically),
                    verticalArrangement = Arrangement.spacedBy(controlItemTokens.spaceRowGap.value)
                ) {
                    Text(text = label, style = OudsTheme.typography.label.default.large, color = labelColor(state = state, error = error))
                    if (!additionalLabel.isNullOrBlank()) {
                        Text(
                            text = additionalLabel,
                            style = OudsTheme.typography.label.strong.medium,
                            color = additionalLabelColor(state = state)
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
                OudsHorizontalDivider()
            }
        }
    }
}

/**
 * Contains classes to build a control item: [OudsCheckboxItem], [OudsRadioButtonItem].
 */
object OudsControlItem {
    internal enum class State {
        Enabled, Hovered, Pressed, Disabled, Focused, ReadOnly;

        fun toControlState(): OudsControl.State {
            return when (this) {
                Enabled -> OudsControl.State.Enabled
                Hovered -> OudsControl.State.Hovered
                Pressed -> OudsControl.State.Pressed
                Focused -> OudsControl.State.Focused
                Disabled, ReadOnly -> OudsControl.State.Disabled
            }
        }
    }

    internal enum class IndicatorPosition {
        Start, End
    }

    /**
     * An icon in a control item like [OudsCheckboxItem] or [OudsRadioButtonItem].
     * It is non-clickable and no content description is needed because a control item label is always present.
     */
    class Icon private constructor(
        graphicsObject: Any,
    ) : OudsComponentIcon<Icon.ExtraParameters>(ExtraParameters::class.java, graphicsObject, "") {

        @ConsistentCopyVisibility
        data class ExtraParameters internal constructor(
            internal val tint: Color
        ) : OudsComponentContent.ExtraParameters()

        /**
         * Creates an instance of [OudsControlItem.Icon].
         *
         * @param painter Painter of the icon.
         */
        constructor(painter: Painter) : this(painter as Any)

        /**
         * Creates an instance of [OudsControlItem.Icon].
         *
         * @param imageVector Image vector of the icon.
         */
        constructor(imageVector: ImageVector) : this(imageVector as Any)

        /**
         * Creates an instance of [OudsControlItem.Icon].
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
internal fun getControlItemState(enabled: Boolean, readOnly: Boolean, interactionState: InteractionState): OudsControlItem.State {
    return getPreviewState<OudsControlItem.State>().orElse {
        when {
            !enabled -> OudsControlItem.State.Disabled
            readOnly -> OudsControlItem.State.ReadOnly
            interactionState == InteractionState.Hovered -> OudsControlItem.State.Hovered
            interactionState == InteractionState.Pressed -> OudsControlItem.State.Pressed
            interactionState == InteractionState.Focused -> OudsControlItem.State.Focused
            else -> OudsControlItem.State.Enabled
        }
    }
}

@Composable
internal fun rememberControlItemBackgroundColor(
    enabled: Boolean,
    readOnly: Boolean,
    interactionState: InteractionState
) = rememberInteractionColor(interactionState = interactionState) { controlItemInteractionState ->
    val state = getControlItemState(enabled = enabled, readOnly = readOnly, interactionState = controlItemInteractionState)
    backgroundColor(state = state)
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
private fun labelColor(state: OudsControlItem.State, error: Boolean) =
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
private fun additionalLabelColor(state: OudsControlItem.State) =
    if (state == OudsControlItem.State.Disabled) OudsTheme.colorScheme.content.disabled else OudsTheme.colorScheme.content.default

@Composable
private fun helperTextColor(state: OudsControlItem.State) =
    if (state == OudsControlItem.State.Disabled) OudsTheme.colorScheme.content.disabled else OudsTheme.colorScheme.content.muted

internal data class OudsControlItemPreviewParameter<T, S>(
    val value: T,
    val extraParameter: S?,
    val helperText: String? = null,
    val divider: Boolean = true,
    val hasIcon: Boolean = false,
    val error: Boolean = false,
    val reversed: Boolean = false,
    val additionalLabel: String? = null
)

internal open class OudsControlItemPreviewParameterProvider<T, S>(
    values: List<T>,
    extraParameters: List<S> = listOf()
) : BasicPreviewParameterProvider<OudsControlItemPreviewParameter<T, S>>(*getPreviewParameterValues(values, extraParameters).toTypedArray()) {

    companion object {
        val DefaultBooleanValues = listOf(false, false, true)
    }
}

private fun <T, S> getPreviewParameterValues(values: List<T>, extraParameters: List<S> = listOf()): List<OudsControlItemPreviewParameter<T, S>> {
    val additionalLabel = "Additional label"
    val helperText = "Helper text"
    val reversedValues = listOf(false, true)

    return buildList {
        reversedValues.forEach { reversed ->
            val parameters = List(3) { index ->
                OudsControlItemPreviewParameter(
                    value = values[index],
                    extraParameter = extraParameters.getOrNull(index),
                    reversed = reversed
                ).run {
                    when (index) {
                        0 -> this
                        1 -> copy(hasIcon = true, additionalLabel = additionalLabel, helperText = helperText)
                        else -> copy(helperText = helperText, divider = false, error = true)
                    }
                }
            }
            addAll(parameters)
        }
    }
}

internal data class OudsControlItemHighContrastModePreviewParameter<T>(
    val value: T,
)

internal open class OudsControlItemHighContrastModePreviewParameterProvider<T>(
    values: List<T>
) : BasicPreviewParameterProvider<OudsControlItemHighContrastModePreviewParameter<T>>(*getHighContrastModePreviewParameterValues(values).toTypedArray())

private fun <T> getHighContrastModePreviewParameterValues(values: List<T>): List<OudsControlItemHighContrastModePreviewParameter<T>> {
    return buildList {
        List(2) { index ->
            add(OudsControlItemHighContrastModePreviewParameter(value = values[index]))
        }
    }
}

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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.error
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.orange.ouds.core.component.common.OudsError
import com.orange.ouds.core.component.common.outerBorder
import com.orange.ouds.core.component.content.OudsComponentContent
import com.orange.ouds.core.component.content.OudsComponentIcon
import com.orange.ouds.core.extensions.InteractionState
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.CheckedContent
import com.orange.ouds.core.utilities.getPreviewEnumEntry
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider

/**
 * Control item composable helps to factorize common layout elements between [OudsCheckboxItem], [OudsTriStateCheckboxItem], [OudsRadioButtonItem]
 * and [OudsSwitchItem].
 */
@Composable
internal fun OudsControlItem(
    state: OudsControlState,
    label: String,
    description: String?,
    icon: OudsControlItemIcon?,
    divider: Boolean,
    enabled: Boolean,
    readOnly: Boolean,
    error: OudsError?,
    indicator: @Composable () -> Unit,
    indicatorPosition: OudsControlItemIndicatorPosition,
    checkedContentComponentName: String,
    checkedContentSelectionStatus: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    extraLabel: String? = null,
    handleHighContrastMode: Boolean = false
) {
    val previewState = getPreviewEnumEntry<OudsControlState>()
    val isReadOnlyPreviewState = previewState == OudsControlState.ReadOnly
    val isDisabledPreviewState = previewState == OudsControlState.Disabled
    val isForbidden = error != null && (readOnly || !enabled || isReadOnlyPreviewState || isDisabledPreviewState)

    CheckedContent(
        expression = !isForbidden,
        exceptionMessage = {
            val parameter = if (readOnly) "readOnly" else "disabled"
            "An $checkedContentComponentName set to $parameter with error parameter activated is not allowed."
        },
        previewMessage = {
            val stateDescription = if (isReadOnlyPreviewState) "Read only" else "Disabled"
            "Error $checkedContentSelectionStatus status for $stateDescription state is not relevant"
        }
    ) {
        val controlItemTokens = OudsTheme.componentsTokens.controlItem

        val itemIcon: (@Composable () -> Unit)? = if (error != null) {
            {
                ErrorIcon(
                    state = state, modifier = if (error.message.isBlank()) {
                        Modifier.semantics { error(" ") } // Allows Talkback to vocalize there is an error on this item even if error message is blank
                    } else Modifier
                )
            }
        } else {
            icon?.let {
                {
                    icon.Content(
                        extraParameters = OudsControlItemIcon.ExtraParameters(
                            tint = if (state == OudsControlState.Disabled) OudsTheme.colorScheme.content.disabled else OudsTheme.colorScheme.content.default
                        )
                    )
                }
            }
        }

        val leadingElement: (@Composable () -> Unit)? = if (indicatorPosition == OudsControlItemIndicatorPosition.Start) indicator else itemIcon
        val trailingElement: (@Composable () -> Unit)? = if (indicatorPosition == OudsControlItemIndicatorPosition.Start) itemIcon else indicator

        Column(modifier = modifier) {
            val shape = RoundedCornerShape(controlItemTokens.borderRadius.value)
            Box(
                modifier = Modifier
                    .height(IntrinsicSize.Min)
                    .heightIn(min = controlItemTokens.sizeMinHeight.dp)
                    .widthIn(min = controlItemTokens.sizeMinWidth.dp, max = controlItemTokens.sizeMaxWidth.dp)
                    .background(color = backgroundColor, shape = shape)
                    .outerBorder(state = state, shape = shape, handleHighContrastMode = handleHighContrastMode),
                contentAlignment = Alignment.BottomCenter
            ) {

                Row(
                    modifier = Modifier.padding(
                        vertical = controlItemTokens.spacePaddingBlockDefault.value,
                        horizontal = controlItemTokens.spacePaddingInline.value
                    ),
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
                        if (!extraLabel.isNullOrBlank()) {
                            Text(
                                text = extraLabel,
                                style = OudsTheme.typography.label.strong.medium,
                                color = extraLabelColor(state = state)
                            )
                        }
                        if (!description.isNullOrBlank()) {
                            Text(
                                text = description,
                                style = OudsTheme.typography.label.default.medium,
                                color = descriptionColor(state = state)
                            )
                        }
                    }
                    trailingElement?.let { LeadingTrailingBox(trailingElement) }
                }
                if (divider) {
                    OudsHorizontalDivider(color = dividerColor(state = state, error = error))
                }
            }
            if (error != null && error.message.isNotBlank()) {
                ErrorMessageText(text = error.message)
            }
        }
    }
}

internal enum class OudsControlItemIndicatorPosition {
    Start, End
}

/**
 * An icon in a control item like [OudsCheckboxItem] or [OudsRadioButtonItem].
 * It is non-clickable and no content description is needed because a control item label is always present.
 */
class OudsControlItemIcon private constructor(
    graphicsObject: Any,
) : OudsComponentIcon<OudsControlItemIcon.ExtraParameters, OudsControlItemIcon>(ExtraParameters::class.java, graphicsObject, "") {

    @ConsistentCopyVisibility
    data class ExtraParameters internal constructor(
        internal val tint: Color
    ) : OudsComponentContent.ExtraParameters()

    /**
     * Creates an instance of [OudsControlItemIcon].
     *
     * @param painter Painter of the icon.
     */
    constructor(painter: Painter) : this(painter as Any)

    /**
     * Creates an instance of [OudsControlItemIcon].
     *
     * @param imageVector Image vector of the icon.
     */
    constructor(imageVector: ImageVector) : this(imageVector as Any)

    /**
     * Creates an instance of [OudsControlItemIcon].
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

@Composable
internal fun rememberControlItemBackgroundColor(
    enabled: Boolean,
    readOnly: Boolean,
    interactionState: InteractionState
) = rememberInteractionColor(interactionState = interactionState) { controlItemInteractionState ->
    val state = getControlState(enabled = enabled, readOnly = readOnly, interactionState = controlItemInteractionState)
    backgroundColor(state = state)
}

@Composable
private fun LeadingTrailingBox(content: @Composable () -> Unit) {
    val assetContainerMaxHeight = OudsTheme.componentsTokens.controlItem.sizeMaxHeightAssetsContainer.dp
    val checkboxIndicatorSize = OudsTheme.componentsTokens.checkbox.sizeMinHeight.value

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
private fun ErrorMessageText(text: String) {
    with(OudsTheme.componentsTokens.controlItem) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = spacePaddingBlockTopErrorText.value)
                .padding(horizontal = spacePaddingInline.value)
                .clearAndSetSemantics {
                    error(text)
                },
            text = text,
            style = OudsTheme.typography.label.default.medium,
            color = OudsTheme.colorScheme.content.status.negative
        )
    }
}

@Composable
private fun ErrorIcon(state: OudsControlState, modifier: Modifier = Modifier) {
    with(OudsTheme.componentsTokens.controlItem) {
        Icon(
            modifier = modifier
                .padding(spacePaddingInlineErrorIcon.value)
                .size(sizeErrorIcon.value),
            painter = painterResource(id = OudsTheme.drawableResources.alertImportant),
            contentDescription = null,
            tint = errorColor(state = state)
        )
    }
}

@Composable
private fun backgroundColor(state: OudsControlState): Color {
    return with(OudsTheme.componentsTokens.controlItem) {
        when (state) {
            OudsControlState.Enabled, OudsControlState.Disabled, OudsControlState.ReadOnly -> Color.Transparent
            OudsControlState.Hovered -> colorBgHover.value
            OudsControlState.Pressed -> colorBgPressed.value
            OudsControlState.Focused -> colorBgFocus.value
        }
    }
}

@Composable
private fun dividerColor(state: OudsControlState, error: OudsError?) =
    if (error != null) {
        errorColor(state = state)
    } else {
        OudsTheme.colorScheme.border.default
    }

@Composable
private fun labelColor(state: OudsControlState, error: OudsError?) =
    if (error != null) {
        errorColor(state = state)
    } else {
        if (state == OudsControlState.Disabled) OudsTheme.colorScheme.content.disabled else OudsTheme.colorScheme.content.default
    }

@Composable
private fun extraLabelColor(state: OudsControlState) =
    if (state == OudsControlState.Disabled) OudsTheme.colorScheme.content.disabled else OudsTheme.colorScheme.content.default

@Composable
private fun descriptionColor(state: OudsControlState) =
    if (state == OudsControlState.Disabled) OudsTheme.colorScheme.content.disabled else OudsTheme.colorScheme.content.muted

internal data class OudsControlItemPreviewParameter<T, S>(
    val value: T,
    val extraParameter: S?,
    val description: String? = null,
    val divider: Boolean = false,
    val hasIcon: Boolean = false,
    val error: OudsError? = null,
    val reversed: Boolean = false,
    val extraLabel: String? = null
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
    val extraLabel = "Extra label"
    val description = "Description"
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
                        1 -> copy(hasIcon = true, extraLabel = extraLabel, description = description)
                        else -> copy(description = description, divider = true, error = OudsError("This field can't be activated"))
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

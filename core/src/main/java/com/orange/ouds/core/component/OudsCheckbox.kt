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
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.selection.triStateToggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.error
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import com.orange.ouds.core.component.common.OudsError
import com.orange.ouds.core.component.common.outerBorder
import com.orange.ouds.core.extensions.collectInteractionStateAsState
import com.orange.ouds.core.theme.LocalHighContrastModeEnabled
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.takeUnlessHairline
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.CheckedContent
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.PreviewEnumEntries
import com.orange.ouds.core.utilities.getPreviewEnumEntry
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.theme.OudsThemeContract

/**
 * Checkboxes are input controls that allow users to select one or more options from a number of choices.
 *
 * The **standalone checkbox variant** is used when the checkbox input is nested within another component and an alternative label is provided. For example,
 * a checkbox can be used in a data table where its purpose is clear from its position or its connection to other items in the same row or column.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://unified-design-system.orange.com/472794e18/p/23f1c1-checkbox)
 *
 * > Design version: 2.3.0
 *
 * @see [OudsTriStateCheckbox] If you require support for an indeterminate state.
 * @see [OudsCheckboxItem] If you want to use a checkbox with an associated label and other optional elements.
 *
 * @param checked Controls checked state of the checkbox.
 * @param onCheckedChange Callback invoked on checkbox click. If `null`, then this is passive and relies entirely on a higher-level component to control
 * the checked state.
 * @param modifier [Modifier] applied to the layout of the checkbox.
 * @param enabled Controls the enabled state of the checkbox. When `false`, this checkbox will not be clickable.
 * @param error Optional [OudsError] to provide in the case of the checkbox should appear in error state, `null` otherwise.
 * @param interactionSource Optional hoisted [MutableInteractionSource] for observing and emitting [Interaction]s for this checkbox. Note that if `null`
 * is provided, interactions will still happen internally.
 *
 * @sample com.orange.ouds.core.component.samples.OudsCheckboxSample
 */
@Composable
fun OudsCheckbox(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    error: OudsError? = null,
    interactionSource: MutableInteractionSource? = null
) {
    OudsTriStateCheckbox(
        state = ToggleableState(checked),
        onClick = if (onCheckedChange != null) {
            { onCheckedChange(!checked) }
        } else null,
        modifier = modifier,
        enabled = enabled,
        error = error,
        interactionSource = interactionSource
    )
}

/**
 * Checkboxes are input controls that allow users to select one or more options from a number of choices.
 *
 * This checkbox supports the indeterminate state: Checkboxes can have a parent-child relationship with other checkboxes. When the parent checkbox is checked,
 * all child checkboxes are checked. If a parent checkbox is unchecked, all child checkboxes are unchecked. If some, but not all, child checkboxes are checked,
 * the parent checkbox becomes an indeterminate checkbox.
 *
 * The **indeterminate standalone checkbox variant** allows to manage a checkbox with an indeterminate state that can be used when the checkbox input is nested
 * within another component and an alternative label is provided.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://unified-design-system.orange.com/472794e18/p/23f1c1-checkbox)
 *
 * > Design version: 2.3.0
 *
 * @see [OudsCheckbox] If you need a simple component that represents [Boolean] state.
 * @see [OudsTriStateCheckboxItem] If you want to use an indeterminate checkbox with an associated label and other optional elements.
 *
 * @param state Controls whether TriStateCheckbox is checked, unchecked or in indeterminate state.
 * @param onClick Callback invoked when checkbox is being clicked, therefore the change of [ToggleableState] state is requested. If null, then this is passive
 * and relies entirely on a higher-level component to control the state.
 * @param modifier [Modifier] applied to the layout of the checkbox.
 * @param enabled Controls the enabled state of the checkbox. When `false`, this checkbox will not be clickable.
 * @param error Optional [OudsError] to provide in the case of the checkbox should appear in error state, `null` otherwise.
 * @param interactionSource Optional hoisted [MutableInteractionSource] for observing and emitting [Interaction]s for this checkbox. Note that if `null`
 * is provided, interactions will still happen internally.
 *
 * @sample com.orange.ouds.core.component.samples.OudsTriStateCheckboxSample
 */
@Composable
fun OudsTriStateCheckbox(
    state: ToggleableState,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    error: OudsError? = null,
    interactionSource: MutableInteractionSource? = null
) {
    val isDisabledPreviewState = getPreviewEnumEntry<OudsControlState>() == OudsControlState.Disabled
    val isForbidden = error != null && (!enabled || isDisabledPreviewState)
    CheckedContent(
        expression = !isForbidden,
        exceptionMessage = { "An OudsCheckbox or OudsTriStateCheckbox set to disabled with error parameter activated is not allowed." }
    ) {
        @Suppress("NAME_SHADOWING") val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
        val interactionState by interactionSource.collectInteractionStateAsState()
        val checkboxTokens = OudsTheme.componentsTokens.checkbox
        val checkboxState = getControlState(enabled = enabled, interactionState = interactionState)
        val backgroundColor = rememberInteractionColor(interactionState = interactionState) { checkboxInteractionState ->
            val controlState = getControlState(enabled = enabled, interactionState = checkboxInteractionState)
            backgroundColor(state = controlState)
        }

        val toggleableModifier =
            if (onClick != null) {
                Modifier.triStateToggleable(
                    interactionSource = interactionSource,
                    indication = InteractionValuesIndication(backgroundColor),
                    state = state,
                    onClick = onClick,
                    enabled = enabled,
                    role = Role.Checkbox
                )
            } else {
                Modifier
            }

        Box(
            modifier = modifier
                .then(toggleableModifier)
                .widthIn(checkboxTokens.sizeMinWidth.value)
                .heightIn(min = checkboxTokens.sizeMinHeight.value, max = checkboxTokens.sizeMaxHeight.value)
                .background(color = backgroundColor.value)
                .outerBorder(state = checkboxState, handleHighContrastMode = true)
                .run {
                    error?.description?.let { description ->
                        semantics {
                            error(description)
                        }
                    }.orElse {
                        this
                    }
                },
            contentAlignment = Alignment.Center,
        ) {
            OudsCheckboxIndicator(state = checkboxState, value = state, error = error != null)
        }
    }
}

@Composable
internal fun OudsCheckboxIndicator(
    state: OudsControlState,
    value: ToggleableState,
    error: Boolean
) {
    val checkboxTokens = OudsTheme.componentsTokens.checkbox
    val selected = value != ToggleableState.Off
    val shape = RoundedCornerShape(checkboxTokens.borderRadius.value)

    Box(
        modifier = Modifier
            .size(checkboxTokens.sizeIndicator.value)
            .clip(shape)
            .indicatorBorder(state = state, selected = selected, error = error, shape = shape)
    ) {
        val indicatorResource = when (value) {
            ToggleableState.On -> OudsTheme.drawableResources.checkboxSelected
            ToggleableState.Off -> null
            ToggleableState.Indeterminate -> OudsTheme.drawableResources.checkboxIndeterminate
        }

        indicatorResource?.let { resource ->
            Icon(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(resource),
                tint = indicatorColor(state = state, selected = true, error = error),
                contentDescription = null
            )
        }
    }
}

@Composable
private fun Modifier.indicatorBorder(state: OudsControlState, selected: Boolean, error: Boolean, shape: Shape): Modifier {
    return indicatorBorderWidth(state = state, selected = selected)?.let { indicatorBorderWidth ->
        border(
            width = indicatorBorderWidth,
            color = indicatorColor(state = state, selected = selected, error = error),
            shape = shape
        )
    }.orElse {
        this
    }
}

@Composable
private fun indicatorBorderWidth(state: OudsControlState, selected: Boolean): Dp? {
    return with(OudsTheme.componentsTokens.checkbox) {
        when (state) {
            OudsControlState.Enabled, OudsControlState.Disabled -> if (selected) borderWidthSelected else borderWidthUnselected
            OudsControlState.Hovered -> if (selected) borderWidthSelectedHover else borderWidthUnselectedHover
            OudsControlState.Pressed -> if (selected) borderWidthSelectedPressed else borderWidthUnselectedPressed
            OudsControlState.Focused -> if (selected) borderWidthSelectedFocus else borderWidthUnselectedFocus
        }.value
    }.takeUnlessHairline
}

@Composable
private fun indicatorColor(state: OudsControlState, selected: Boolean, error: Boolean): Color {
    return with(OudsTheme.colorScheme.action) {
        if (error) {
            when (state) {
                OudsControlState.Enabled -> negative.enabled
                OudsControlState.Disabled -> Color.Unspecified // Not allowed, exception thrown at the beginning of OudsCheckbox
                OudsControlState.Hovered -> negative.hover
                OudsControlState.Pressed -> negative.pressed
                OudsControlState.Focused -> negative.focus
            }
        } else {
            when (state) {
                OudsControlState.Enabled -> if (selected) {
                    // In order to reach the a11y AAA level, when high contrast mode is enabled, the selected checkbox must use `color.content.default` token
                    if (LocalHighContrastModeEnabled.current) OudsTheme.colorScheme.content.default else this.selected
                } else {
                    enabled
                }
                OudsControlState.Disabled -> disabled
                OudsControlState.Hovered -> hover
                OudsControlState.Pressed -> pressed
                OudsControlState.Focused -> focus
            }
        }
    }
}

@Composable
private fun backgroundColor(state: OudsControlState): Color {
    return with(OudsTheme.componentsTokens.controlItem) {
        when (state) {
            OudsControlState.Enabled, OudsControlState.Disabled -> Color.Transparent
            OudsControlState.Hovered -> colorBgHover.value
            OudsControlState.Pressed -> colorBgPressed.value
            OudsControlState.Focused -> colorBgFocus.value
        }
    }
}

@PreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsCheckbox(@PreviewParameter(OudsCheckboxPreviewParameterProvider::class) parameter: OudsCheckboxPreviewParameter) {
    PreviewOudsCheckbox(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@PreviewLightDark
@Composable
internal fun PreviewOudsCheckboxHighContrastModeEnabled(@PreviewParameter(OudsCheckboxPreviewParameterProvider::class) parameter: OudsCheckboxPreviewParameter) {
    PreviewOudsCheckbox(
        theme = getPreviewTheme(),
        darkThemeEnabled = isSystemInDarkTheme(),
        parameter = parameter,
        highContrastModeEnabled = true
    )
}

@Composable
internal fun PreviewOudsCheckbox(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsCheckboxPreviewParameter,
    highContrastModeEnabled: Boolean = false
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled, highContrastModeEnabled = highContrastModeEnabled) {
    with(parameter) {
        PreviewEnumEntries<OudsControlState> {
            OudsTriStateCheckbox(
                state = toggleableState,
                onClick = null,
                interactionSource = remember { MutableInteractionSource() },
                error = error
            )
        }
    }
}

internal data class OudsCheckboxPreviewParameter(
    val toggleableState: ToggleableState,
    val error: OudsError? = null
)

internal class OudsCheckboxPreviewParameterProvider : BasicPreviewParameterProvider<OudsCheckboxPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsCheckboxPreviewParameter>
    get() = buildList {
        ToggleableState.entries.forEach { toggleableState ->
            val parameters = listOf(
                OudsCheckboxPreviewParameter(toggleableState = toggleableState),
                OudsCheckboxPreviewParameter(toggleableState = toggleableState, error = OudsError(""))
            )
            addAll(parameters)
        }
    }
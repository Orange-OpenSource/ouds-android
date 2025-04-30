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

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateSizeAsState
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.R
import com.orange.ouds.core.extensions.collectInteractionStateAsState
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.PreviewStates
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider

//TODO Add DSM link when available
// <a href="https://unified-design-system.orange.com/" class="external" target="_blank">**OUDS Switch design guidelines**</a>
/**
 * Switches allow the user to toggle between two states, typically "on" and "off." It is represented as a slider that changes its position or color to indicate
 * the current state. Switches are used to enable or disable features, options, or settings in an intuitive and visual manner.
 *
 * The **standalone switch variant** can be used when the switch selector control is nested within another component and an alternative label is provided.
 *
 * @see [OudsSwitchItem]  if you want to use a switch with an associated label and other optional elements.
 *
 * @param checked Controls checked state of the switch.
 * @param onCheckedChange Callback invoked on switch click. If `null`, then this is passive and relies entirely on a higher-level component to control
 * the checked state.
 * @param modifier [Modifier] applied to the layout of the switch.
 * @param enabled Controls the enabled state of the switch. When `false`, this switch will not be clickable.
 * @param interactionSource Optional hoisted [MutableInteractionSource] for observing and emitting [Interaction]s for this switch. Note that if `null`
 * is provided, interactions will still happen internally.
 *
 * @sample com.orange.ouds.core.component.samples.OudsSwitchSample
 */
@Composable
fun OudsSwitch(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null
) {
    OudsSwitch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        previewState = null,
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource
    )
}

@Composable
private fun OudsSwitch(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    previewState: OudsControl.State?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
) {
    val switchTokens = OudsTheme.componentsTokens.switch
    @Suppress("NAME_SHADOWING") val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    val interactionState by interactionSource.collectInteractionStateAsState()
    val state = previewState.orElse { rememberOudsControlState(enabled = enabled, interactionState = interactionState) }

    val toggleableModifier = if (onCheckedChange != null) {
        Modifier.toggleable(
            value = checked,
            onValueChange = onCheckedChange,
            enabled = enabled,
            role = Role.Switch,
            interactionSource = interactionSource,
            indication = null
        )
    } else {
        Modifier
    }

    Box(
        modifier = modifier
            .widthIn(min = switchTokens.sizeMinWidth.dp)
            .heightIn(min = switchTokens.sizeMinHeight.dp, max = switchTokens.sizeMaxHeight.dp)
            .outerBorder(state = state)
            .then(toggleableModifier),
        contentAlignment = Alignment.Center,
    ) {
        OudsSwitchIndicator(state = state, checked = checked)
    }
}

@Composable
internal fun OudsSwitchIndicator(state: OudsControl.State, checked: Boolean) {
    val switchTokens = OudsTheme.componentsTokens.switch
    val shape = RoundedCornerShape(switchTokens.borderRadius.value)

    // The cursor animation is obtained by using a column and updating its horizontalAlignment parameter
    val horizontalAlignment by animateHorizontalAlignmentAsState(
        targetBiasValue = if (checked) 1f else -1f,
        animationSpec = cursorAnimationSpec()
    )
    Column(
        modifier = Modifier
            .size(width = switchTokens.sizeWidthTrack.dp, height = switchTokens.sizeHeightTrack.dp)
            .clip(shape)
            .background(indicatorBackgroundColor(state = state, checked = checked))
            .padding(start = switchTokens.spacePaddingInlineUnselected.value, end = switchTokens.spacePaddingInlineSelected.value),
        horizontalAlignment = horizontalAlignment,
        verticalArrangement = Arrangement.Center
    ) {
        val cursorSize by animateSizeAsState(
            targetValue = cursorSize(state = state, checked = checked),
            animationSpec = cursorAnimationSpec()
        )
        Box(
            modifier = Modifier
                .size(cursorSize.width.dp, cursorSize.height.dp)
                .shadow(OudsTheme.elevations.raised.value.dp, shape)
                .background(switchTokens.colorCursor.value),
            contentAlignment = Alignment.Center
        ) {
            val checkColor = checkColor(state = state, checked = checked)
            // This is a hack to delay the appearance of the check when switching from unchecked state to checked
            // There is no issue when switching from checked state to unchecked because check color is null thus the check is not displayed
            val checkAlpha by animateFloatAsState(
                targetValue = if (checkColor != null) 1.0f else 0.0f,
                animationSpec = snap(OudsSwitch.AnimationDuration)
            )
            if (checkColor != null) {
                Icon(
                    modifier = Modifier
                        .alpha(switchTokens.opacityCheck.value)
                        .alpha(checkAlpha),
                    painter = painterResource(id = R.drawable.switch_selected),
                    contentDescription = null,
                    tint = checkColor
                )
            }
        }
    }
}

@Composable
private fun indicatorBackgroundColor(state: OudsControl.State, checked: Boolean): Color {
    return with(OudsTheme.componentsTokens.switch) {
        when (state) {
            OudsControl.State.Enabled -> if (checked) colorTrackSelected.value else colorTrackUnselected.value
            OudsControl.State.Hovered,
            OudsControl.State.Pressed,
            OudsControl.State.Focused -> if (checked) colorTrackSelectedInteraction.value else colorTrackUnselectedInteraction.value
            OudsControl.State.Disabled -> OudsTheme.colorScheme.action.disabled
        }
    }
}

@Composable
private fun cursorSize(state: OudsControl.State, checked: Boolean): Size {
    return with(OudsTheme.componentsTokens.switch) {
        val width = when {
            state == OudsControl.State.Pressed && checked -> sizeWidthCursorSelectedPressed
            state == OudsControl.State.Pressed && !checked -> sizeWidthCursorUnselectedPressed
            checked -> sizeWidthCursorSelected
            else -> sizeWidthCursorUnselected
        }
        val height = if (checked) sizeHeightCursorSelected else sizeHeightCursorUnselected
        Size(width, height)
    }
}

@Composable
private fun checkColor(state: OudsControl.State, checked: Boolean): Color? {
    return if (checked) {
        when (state) {
            OudsControl.State.Enabled,
            OudsControl.State.Hovered,
            OudsControl.State.Focused -> OudsTheme.componentsTokens.switch.colorCheck.value
            OudsControl.State.Pressed -> null
            OudsControl.State.Disabled -> OudsTheme.colorScheme.action.disabled
        }
    } else {
        null
    }
}

private fun <T> cursorAnimationSpec(): AnimationSpec<T> {
    return tween(
        durationMillis = OudsSwitch.AnimationDuration,
        easing = CubicBezierEasing(0.2f, 0.0f, 0.0f, 1.0f)
    )
}

@Composable
private fun animateHorizontalAlignmentAsState(targetBiasValue: Float, animationSpec: AnimationSpec<Float> = spring()): State<BiasAlignment.Horizontal> {
    val bias by animateFloatAsState(targetBiasValue, animationSpec)
    return remember { derivedStateOf { BiasAlignment.Horizontal(bias) } }
}

private object OudsSwitch {

    const val AnimationDuration = 150
}

@PreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsSwitch(@PreviewParameter(OudsSwitchPreviewParameterProvider::class) checked: Boolean) {
    PreviewOudsSwitch(darkThemeEnabled = isSystemInDarkTheme(), checked = checked)
}

@Composable
internal fun PreviewOudsSwitch(
    darkThemeEnabled: Boolean,
    checked: Boolean
) = OudsPreview(darkThemeEnabled = darkThemeEnabled) {
    PreviewStates<OudsControl.State>(columnCount = 3) { state ->
        OudsSwitch(
            checked = checked,
            onCheckedChange = {},
            previewState = state
        )
    }
}

internal class OudsSwitchPreviewParameterProvider : BasicPreviewParameterProvider<Boolean>(false, true)

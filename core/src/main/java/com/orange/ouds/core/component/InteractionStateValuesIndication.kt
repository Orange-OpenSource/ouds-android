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

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.DrawModifierNode
import com.orange.ouds.core.extensions.InteractionState
import com.orange.ouds.foundation.extensions.orElse
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


internal data class InteractionStateValuesIndication(val values: List<InteractionStateValue<*>>) : IndicationNodeFactory {

    constructor(vararg values: InteractionStateValue<*>) : this(values.toList())

    override fun create(interactionSource: InteractionSource): DelegatableNode {
        return InteractionStateValuesNode(interactionSource, values)
    }
}

internal class InteractionStateValuesNode(
    private val interactionSource: InteractionSource,
    private val values: List<InteractionStateValue<*>>
) : Modifier.Node(), DrawModifierNode {

    private companion object {
        val Easing: Easing = CubicBezierEasing(0.3f, 0.3f, 0.2f, 1.0f)
    }

    private var pressedAnimation: Job? = null

    private var restingAnimation: Job? = null

    private var isPressing = false

    private val animatables = values.map { it ->
        when (it) {
            is InteractionStateColor -> androidx.compose.animation.Animatable(it.restingValue)
            is InteractionStateFloat -> Animatable(it.restingValue)
        }
    }

    override fun onAttach() {
        coroutineScope.launch {
            interactionSource.interactions.collectLatest { interaction ->
                isPressing = interaction is PressInteraction.Press
                when (interaction) {
                    is PressInteraction.Press -> animateToPressed()
                    is PressInteraction.Release, is PressInteraction.Cancel -> animateToResting()
                }
            }
        }
    }

    override fun ContentDrawScope.draw() {
        this@draw.drawContent()
        values.forEach { it.updatePressedValue() }
    }

    private fun animateToPressed() {
        // Finish any existing animations, in case of a new press while we are still showing
        // an animation for a previous one
        restingAnimation?.cancel()
        pressedAnimation?.cancel()
        pressedAnimation = coroutineScope.launch {
            val jobs = animatables.map { animatable ->
                async { animatable.animateToPressed() }
            }
            awaitAll(*jobs.toTypedArray())
        }
    }

    private fun animateToResting() {
        restingAnimation = coroutineScope.launch {
            // Wait for the existing press animation to finish if it is still ongoing
            pressedAnimation?.join()
            val jobs = animatables.map { animatable ->
                async { animatable.animateToResting() }
            }
            awaitAll(*jobs.toTypedArray())
        }
    }


    @Suppress("UNCHECKED_CAST")
    private fun <T : Any> getValue(animatable: Animatable<T, *>): InteractionStateValue<T> {
        val index = animatables.indexOf(animatable)
        return values[index] as InteractionStateValue<T>
    }

    private suspend fun <T : Any> Animatable<T, *>.animateToPressed() {
        val value = getValue(this)
        snapTo(value.restingValue)
        animateTo(value.pressed, tween(easing = Easing))
    }

    private suspend fun <T : Any> Animatable<T, *>.animateToResting() {
        val value = getValue(this)
        animateTo(value.restingValue, tween(easing = Easing))
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T : Any> InteractionStateValue<T>.updatePressedValue() {
        val index = values.indexOf(this)
        val animatable = animatables[index] as Animatable<T, *>
        pressedValue = when {
            animatable.isRunning -> animatable.value
            isPressing -> pressed
            else -> null
        }
    }
}

internal sealed class InteractionStateValue<T : Any>(
    val none: T,
    val focused: T,
    val hovered: T,
    val pressed: T
) {

    var interactionState: InteractionState = InteractionState.None
        set(value) {
            field = value
            if (value != InteractionState.Pressed) {
                restingState = value
            }
        }

    var restingState: InteractionState = InteractionState.None
        private set(value) {
            field = value
            restingValue = when (value) {
                InteractionState.None -> none
                InteractionState.Focused -> focused
                InteractionState.Hovered -> hovered
                InteractionState.Pressed -> error("Resting state cannot be set to pressed.")
            }
        }

    var pressedValue: T? = null
        set(value) {
            field = value
            setValue(value, restingValue)
        }

    var restingValue: T = none
        private set(value) {
            field = value
            setValue(pressedValue, value)
        }

    var value by mutableStateOf(none)

    private fun setValue(pressedValue: T?, restingValue: T) {
        value = pressedValue.orElse { restingValue }
    }
}

internal class InteractionStateColor(
    none: Color,
    focused: Color,
    hovered: Color,
    pressed: Color
) : InteractionStateValue<Color>(none, focused, hovered, pressed)

internal class InteractionStateFloat(
    none: Float,
    focused: Float,
    hovered: Float,
    pressed: Float
) : InteractionStateValue<Float>(none, focused, hovered, pressed)

@Composable
internal fun rememberInteractionStateColor(
    none: Color,
    focused: Color,
    hovered: Color,
    pressed: Color,
    interactionState: InteractionState
) = remember(none, focused, hovered, pressed) {
    InteractionStateColor(none, focused, hovered, pressed)
}.apply {
    this.interactionState = interactionState
}

@Composable
internal fun rememberInteractionStateFloat(
    none: Float,
    focused: Float,
    hovered: Float,
    pressed: Float,
    interactionState: InteractionState
) = remember(none, focused, hovered, pressed) {
    InteractionStateFloat(none, focused, hovered, pressed)
}.apply {
    this.interactionState = interactionState
}

@Composable
internal fun rememberInteractionStateColor(
    interactionState: InteractionState,
    init: @Composable (InteractionState) -> Color,
) = rememberInteractionStateColor(
    none = init(InteractionState.None),
    focused = init(InteractionState.Focused),
    hovered = init(InteractionState.Hovered),
    pressed = init(InteractionState.Pressed),
    interactionState = interactionState
)

@Composable
internal fun rememberInteractionStateFloat(
    interactionState: InteractionState,
    init: @Composable (InteractionState) -> Float
) = rememberInteractionStateFloat(
    none = init(InteractionState.None),
    focused = init(InteractionState.Focused),
    hovered = init(InteractionState.Hovered),
    pressed = init(InteractionState.Pressed),
    interactionState = interactionState
)

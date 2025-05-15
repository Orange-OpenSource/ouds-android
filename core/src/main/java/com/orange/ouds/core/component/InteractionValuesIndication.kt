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


internal data class InteractionValuesIndication(val values: List<InteractionValue<*, *>>) : IndicationNodeFactory {

    constructor(vararg interactionValues: InteractionValue<*, *>) : this(interactionValues.toList())

    override fun create(interactionSource: InteractionSource): DelegatableNode {
        return InteractionValuesNode(interactionSource, values)
    }
}

internal class InteractionValuesNode(
    private val interactionSource: InteractionSource,
    interactionValues: List<InteractionValue<*, *>>
) : Modifier.Node(), DrawModifierNode {

    // The aim of this class is to resolve issues related to generics when manipulating instances of InteractionValue and Animatable
    private class AnimatableInteractionValue<T, S>(val interactionValue: InteractionValue<T, S>, val animatable: Animatable<S, *>)

    private companion object {
        val Easing: Easing = CubicBezierEasing(0.3f, 0.3f, 0.2f, 1.0f)
    }

    private var pressedAnimations: Job? = null

    private var restingAnimations: Job? = null

    private var isPressing = false

    private val animatableInteractionValues: List<AnimatableInteractionValue<*, *>> = interactionValues.map { createAnimatableInteractionValue(it) }

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
        animatableInteractionValues.forEach { it.updateAnimatingPressed() }
    }

    private fun <T, S> createAnimatableInteractionValue(interactionValue: InteractionValue<T, S>): AnimatableInteractionValue<T, S> {
        val resting = with(interactionValue) { toAnimatableValue(resting) }

        @Suppress("UNCHECKED_CAST")
        val animatable = when (resting) {
            is Color -> androidx.compose.animation.Animatable(resting)
            is Float -> Animatable(resting)
            else -> error("toAnimatableValue must return either a Compose Color or a Float.")
        } as Animatable<S, *>

        return AnimatableInteractionValue(interactionValue, animatable)
    }

    private fun animateToPressed() {
        // Finish any existing animations, in case of a new press while we are still showing animations for a previous one
        restingAnimations?.cancel()
        pressedAnimations?.cancel()
        pressedAnimations = coroutineScope.launch {
            // Launch all pressed animations at once
            animatableInteractionValues.map { async { animateToPressed(it) } }.awaitAll()
        }
    }

    private fun animateToResting() {
        restingAnimations = coroutineScope.launch {
            // Wait for the existing press animations to finish if they are still ongoing
            pressedAnimations?.join()
            // Launch all resting animations at once
            animatableInteractionValues.map { async { animateToResting(it) } }.awaitAll()
        }
    }

    private suspend fun <T, S> animateToPressed(animatableInteractionValue: AnimatableInteractionValue<T, S>) {
        with(animatableInteractionValue) {
            animatable.snapTo(with(interactionValue) { toAnimatableValue(resting) })
            animatable.animateTo(with(interactionValue) { toAnimatableValue(pressed) }, tween(easing = Easing))
        }
    }

    private suspend fun <T, S> animateToResting(animatableInteraction: AnimatableInteractionValue<T, S>) {
        with(animatableInteraction) {
            animatable.animateTo(with(interactionValue) { toAnimatableValue(resting) }, tween(easing = Easing))
        }
    }

    private fun <T, S> AnimatableInteractionValue<T, S>.updateAnimatingPressed() {
        interactionValue.animatingPressed = when {
            animatable.isRunning -> interactionValue.fromAnimatableValue(animatable.value)
            isPressing -> interactionValue.pressed
            else -> null
        }
    }
}

/**
 * A value holder that automatically updates its value when the user is interacting with the component it is attached to.
 * [InteractionValuesNode] uses instances of this class and associated animatables to update the value.
 *
 * @param T The type of the value.
 * @param S The type of the associated animatable value. This must be a Compose [Color] or a [Float].
 * @param none The value when [interactionState] is `InteractionState.None`.
 * @param focused The value when [interactionState] state is `InteractionState.Focused`.
 * @param hovered The value when [interactionState] state is `InteractionState.Hovered`.
 * @param pressed The value when [interactionState] state is `InteractionState.Pressed`.
 * @param toAnimatableValue Converts a value of type T to an animatable value of type S.
 * @param fromAnimatableValue Converts an animatable value of type S to a value of type T.
 */
internal sealed class InteractionValue<T, S>(
    val none: T,
    val focused: T,
    val hovered: T,
    val pressed: T,
    val toAnimatableValue: (T) -> S,
    val fromAnimatableValue: (S) -> T
) {

    /** The interaction state of the component this value is attached to. */
    var interactionState: InteractionState = InteractionState.None
        set(value) {
            field = value
            if (value != InteractionState.Pressed) {
                @Suppress("KotlinConstantConditions")
                resting = when (value) {
                    InteractionState.None -> none
                    InteractionState.Focused -> focused
                    InteractionState.Hovered -> hovered
                    InteractionState.Pressed -> error("Resting does not change when pressed.")
                }
            }
        }

    /**
     * The value when animating the press interaction.
     * It is equal to:
     *  - the associated animatable value if the later is running
     *  - [pressed] if the user is still pressing the component but animations are finished
     *  - null otherwise
     */
    var animatingPressed: T? = null
        set(value) {
            field = value
            setValue(value, resting)
        }

    /** The value when there is no press interaction. */
    var resting: T = none
        private set(value) {
            field = value
            setValue(animatingPressed, value)
        }

    /** The value. */
    var value by mutableStateOf(none)

    private fun setValue(animatingPressed: T?, resting: T) {
        value = animatingPressed.orElse { resting }
    }
}

/**
 * An interaction value associated with an animatable Color.
 */
private class AnimatableColorInteractionValue<T>(
    none: T,
    focused: T,
    hovered: T,
    pressed: T,
    toAnimatableColor: (T) -> Color,
    fromAnimatableColor: (Color) -> T
) : InteractionValue<T, Color>(none, focused, hovered, pressed, toAnimatableColor, fromAnimatableColor)

/**
 * An interaction value associated with an animatable Float.
 */
private class AnimatableFloatInteractionValue<T>(
    none: T,
    focused: T,
    hovered: T,
    pressed: T,
    toAnimatableFloat: (T) -> Float,
    fromAnimatableFloat: (Float) -> T
) : InteractionValue<T, Float>(none, focused, hovered, pressed, toAnimatableFloat, fromAnimatableFloat)

@Composable
@JvmName("rememberAnimatableColorInteractionValue")
private fun <T> rememberInteractionValue(
    none: T,
    focused: T,
    hovered: T,
    pressed: T,
    interactionState: InteractionState,
    toAnimatableColor: (T) -> Color,
    fromAnimatableColor: (Color) -> T
): InteractionValue<T, Color> =
    remember(none, focused, hovered, pressed, toAnimatableColor, fromAnimatableColor) {
        AnimatableColorInteractionValue(none, focused, hovered, pressed, toAnimatableColor, fromAnimatableColor)
    }.apply {
        this.interactionState = interactionState
    }

@Composable
internal fun rememberInteractionColor(
    none: Color,
    focused: Color,
    hovered: Color,
    pressed: Color,
    interactionState: InteractionState
) = rememberInteractionValue(
    none = none,
    focused = focused,
    hovered = hovered,
    pressed = pressed,
    interactionState = interactionState,
    toAnimatableColor = { it },
    fromAnimatableColor = { it })

@Composable
internal fun rememberInteractionColor(
    interactionState: InteractionState,
    init: @Composable (InteractionState) -> Color
) = rememberInteractionColor(
    none = init(InteractionState.None),
    focused = init(InteractionState.Focused),
    hovered = init(InteractionState.Hovered),
    pressed = init(InteractionState.Pressed),
    interactionState = interactionState
)

@Composable
internal fun rememberNullableInteractionColor(
    none: Color?,
    focused: Color?,
    hovered: Color?,
    pressed: Color?,
    interactionState: InteractionState
) = rememberInteractionValue(
    none = none,
    focused = focused,
    hovered = hovered,
    pressed = pressed,
    interactionState = interactionState,
    toAnimatableColor = { it.orElse { Color.Transparent } },
    fromAnimatableColor = { it })

@Composable
internal fun rememberNullableInteractionColor(
    interactionState: InteractionState,
    init: @Composable (InteractionState) -> Color?
) = rememberNullableInteractionColor(
    none = init(InteractionState.None),
    focused = init(InteractionState.Focused),
    hovered = init(InteractionState.Hovered),
    pressed = init(InteractionState.Pressed),
    interactionState = interactionState
)

@Composable
@JvmName("rememberAnimatableFloatInteractionValue")
internal fun <T> rememberInteractionValue(
    none: T,
    focused: T,
    hovered: T,
    pressed: T,
    interactionState: InteractionState,
    toAnimatableFloat: (T) -> Float,
    fromAnimatableFloat: (Float) -> T
): InteractionValue<T, Float> = remember(none, focused, hovered, pressed, toAnimatableFloat, fromAnimatableFloat) {
    AnimatableFloatInteractionValue(none, focused, hovered, pressed, toAnimatableFloat, fromAnimatableFloat)
}.apply {
    this.interactionState = interactionState
}

@Composable
internal fun <T> rememberInteractionValue(
    interactionState: InteractionState,
    toAnimatableFloat: (T) -> Float,
    fromAnimatableFloat: (Float) -> T,
    init: @Composable (InteractionState) -> T
) = rememberInteractionValue(
    none = init(InteractionState.None),
    focused = init(InteractionState.Focused),
    hovered = init(InteractionState.Hovered),
    pressed = init(InteractionState.Pressed),
    interactionState = interactionState,
    toAnimatableFloat = toAnimatableFloat,
    fromAnimatableFloat = fromAnimatableFloat
)

@Composable
internal fun rememberInteractionFloat(
    none: Float,
    focused: Float,
    hovered: Float,
    pressed: Float,
    interactionState: InteractionState
) = rememberInteractionValue(
    none = none,
    focused = focused,
    hovered = hovered,
    pressed = pressed,
    interactionState = interactionState,
    toAnimatableFloat = { it },
    fromAnimatableFloat = { it })

@Composable
internal fun rememberInteractionFloat(
    interactionState: InteractionState,
    init: @Composable (InteractionState) -> Float
) = rememberInteractionFloat(
    none = init(InteractionState.None),
    focused = init(InteractionState.Focused),
    hovered = init(InteractionState.Hovered),
    pressed = init(InteractionState.Pressed),
    interactionState = interactionState
)

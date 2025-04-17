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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.DrawModifierNode
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private class BackgroundColorAlphaNode(private val interactionSource: InteractionSource, private val color: Color) :
    Modifier.Node(), DrawModifierNode {

    val backgroundColorAlphaEasing: Easing = CubicBezierEasing(0.3f, 0.3f, 0.2f, 1.0f)
    val animatedPressAlpha = Animatable(0f)
    var pressedAnimation: Job? = null
    var restingAnimation: Job? = null

    private fun animateToPressed() {
        // Finish any existing animations, in case of a new press while we are still showing
        // an animation for a previous one
        restingAnimation?.cancel()
        pressedAnimation?.cancel()
        pressedAnimation = coroutineScope.launch {
            animatedPressAlpha.snapTo(0f)
            animatedPressAlpha.animateTo(color.alpha, tween(easing = backgroundColorAlphaEasing))
        }
    }

    private fun animateToResting() {
        restingAnimation = coroutineScope.launch {
            // Wait for the existing press animation to finish if it is still ongoing
            pressedAnimation?.join()
            animatedPressAlpha.animateTo(0f, tween(easing = backgroundColorAlphaEasing))
        }

    }

    override fun onAttach() {
        coroutineScope.launch {
            interactionSource.interactions.collectLatest { interaction ->
                when (interaction) {
                    is PressInteraction.Press -> animateToPressed()
                    is PressInteraction.Release, is PressInteraction.Cancel -> animateToResting()
                }
            }
        }
    }

    override fun ContentDrawScope.draw() {
        drawRect(color = color.copy(alpha = animatedPressAlpha.value), size = this.size)
        this@draw.drawContent()
    }
}

internal data class BackgroundColorAlphaIndication(private val color: Color) : IndicationNodeFactory {
    override fun create(interactionSource: InteractionSource): DelegatableNode {
        return BackgroundColorAlphaNode(interactionSource, color)
    }
}
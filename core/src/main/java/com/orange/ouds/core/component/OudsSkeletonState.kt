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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

/**
 * Creates and remembers an [OudsSkeletonState]. The state is remembered using [rememberSaveable]
 * and will be saved and restored with the composition.
 *
 * @param initialIsAnimationRunning Whether the animation should be running initially. Defaults to true.
 */
@Composable
fun rememberOudsSkeletonState(
    initialIsAnimationRunning: Boolean = true
): OudsSkeletonState = rememberSaveable(saver = OudsSkeletonState.Saver) {
    OudsSkeletonState(initialIsAnimationRunning)
}

/**
 * State object that controls the animation of an [OudsSkeleton].
 *
 * When instantiating this class from a composable, use [rememberOudsSkeletonState] to automatically
 * save and restore the skeleton state.
 *
 * @param initialIsAnimationRunning Whether the animation should be running initially. Defaults to true.
 */
@Stable
class OudsSkeletonState(initialIsAnimationRunning: Boolean = true) {

    companion object {

        internal val ShimmerAnimationDuration = 800

        internal val ShimmerPauseDuration = 450

        val AnimationDuration = ShimmerAnimationDuration + ShimmerPauseDuration

        /**
         * Saves and restores an [OudsSkeletonState] for [rememberSaveable].
         */
        val Saver = listSaver(
            save = { state ->
                listOf(state.isAnimationRunning)
            },
            restore = { list: List<Any?> ->
                OudsSkeletonState(list[0] as Boolean)
            }
        )
    }

    /**
     * Whether the skeleton animation is currently running.
     */
    var isAnimationRunning: Boolean by mutableStateOf(initialIsAnimationRunning)
        private set

    /**
     * Starts the skeleton gradient animation. The animation will loop infinitely
     * until [stopAnimation] is called.
     *
     * If the animation was previously stopped, calling this method will restart
     * the animation from the beginning.
     */
    fun startAnimation() {
        isAnimationRunning = true
    }

    /**
     * Stops the skeleton gradient animation.
     */
    fun stopAnimation() {
        isAnimationRunning = false
    }
}

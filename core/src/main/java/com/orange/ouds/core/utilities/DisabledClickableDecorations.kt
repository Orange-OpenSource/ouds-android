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

package com.orange.ouds.core.utilities

import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.RippleConfiguration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalViewConfiguration
import androidx.compose.ui.platform.ViewConfiguration
import androidx.compose.ui.unit.DpSize

/**
 * Wraps content to remove the automatic decorations applied by the clickable modifier.
 * 
 * By default, clickable applies:
 * - A minimum touch target size of 48dp for accessibility (even if the visual element is smaller)
 * - Ripple effects on interaction
 * 
 * This wrapper removes both decorations, ensuring that:
 * - Clicks are only detected within the actual bounds of the element (no invisible expanded touch area)
 * - No ripple animation is shown
 * 
 * **Usage**: This should be used in conjunction with clickable applied at the call site,
 * typically to prevent child elements from triggering a parent's click handler.
 * 
 * **Note**: If you need to apply semantics, they must be placed **before** the clickable modifier
 * in the modifier chain, otherwise they won't work correctly.
 * 
 * Example:
 * ```
 * Box(modifier = Modifier.clickable { /* Parent action */ }) {
 *     DisabledClickableDecorations {
 *         Box(
 *             modifier = Modifier
 *                 .size(20.dp)
 *                 .semantics { contentDescription = "Non-interactive area" }
 *                 .clickable {} // Blocks parent clicks within this 20.dp area only
 *         ) {
 *             // Child content
 *         }
 *     }
 * }
 * ```
 */
@Composable
internal fun DisabledClickableDecorations(content: @Composable DisabledClickableDecorationsScope.() -> Unit) {
    val originalViewConfiguration = LocalViewConfiguration.current
    val viewConfiguration = object : ViewConfiguration by originalViewConfiguration {
        override val minimumTouchTargetSize: DpSize = DpSize.Zero
    }
    val originalRippleConfiguration = LocalRippleConfiguration.current
    CompositionLocalProvider(
        LocalViewConfiguration provides viewConfiguration,
        LocalRippleConfiguration provides null,
    ) {
        DisabledClickableDecorationsScope(originalViewConfiguration, originalRippleConfiguration).content()
    }
}

internal class DisabledClickableDecorationsScope(val viewConfiguration: ViewConfiguration, val rippleConfiguration: RippleConfiguration?) {

    @Composable
    fun EnabledClickableDecorations(content: @Composable () -> Unit) {
        CompositionLocalProvider(
            LocalViewConfiguration provides viewConfiguration,
            LocalRippleConfiguration provides rippleConfiguration,
            content = content
        )
    }
}

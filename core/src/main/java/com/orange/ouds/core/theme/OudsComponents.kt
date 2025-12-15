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

package com.orange.ouds.core.theme

import com.orange.ouds.theme.tokens.components.OudsComponentsTokens

/**
 * Holds component-specific tokens that are exposed for public use.
 *
 * While most component styling is handled automatically by OUDS components,
 * these properties allow accessing specific token values that might be needed
 * for custom implementations.
 *
 * @property bar Properties specific to the OUDS bars (e.g. [com.orange.ouds.core.component.OudsTopAppBar] or [com.orange.ouds.core.component.OudsNavigationBar]).
 */
@ConsistentCopyVisibility
data class OudsComponents internal constructor(
    val bar: Bar
) {

    /**
     * Properties specific to the OUDS bars.
     *
     * @property blurRadius The blur radius applied to the bars background
     * to create the glassmorphism effect.
     */
    @ConsistentCopyVisibility
    data class Bar internal constructor(
        val blurRadius: Int
    )
}

internal fun OudsComponentsTokens.getComponents() = OudsComponents(
    bar = OudsComponents.Bar(
        blurRadius = bar.effectBgBlur
    )
)

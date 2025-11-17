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
 * @suppress
 */
data class OudsComponents(
    val navigationBar: NavigationBar
) {

    data class NavigationBar(
        val blurRadius: Int
    )
}

internal fun OudsComponentsTokens.getComponents() = OudsComponents(
    navigationBar = OudsComponents.NavigationBar(
        blurRadius = bar.effectBgBlur
    )
)

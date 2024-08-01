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

package com.orange.ouds.theme

import android.os.Parcelable
import com.orange.ouds.theme.tokens.components.OudsComponentsTokens
import com.orange.ouds.theme.tokens.semantic.OudsSemanticColors

interface OudsCustomTheme : Parcelable {

    /**
     * Theme display name
     */
    val name: String

    /**
     * Semantic colors values used in the theme
     */
    val semanticColors: OudsSemanticColors

    /**
     * Customization of the OUDS components if needed
     */
    val componentsTokens: OudsComponentsTokens
        get() = OudsComponentsTokens()
}
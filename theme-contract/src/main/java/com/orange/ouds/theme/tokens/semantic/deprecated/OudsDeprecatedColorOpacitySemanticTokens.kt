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

package com.orange.ouds.theme.tokens.semantic.deprecated

import androidx.compose.ui.graphics.Color
import com.orange.ouds.foundation.InternalOudsApi

/** @suppress */
@InternalOudsApi
interface OudsDeprecatedColorOpacitySemanticTokens {

    @InternalOudsApi
    companion object {

        @Suppress("OVERRIDE_DEPRECATION")
        val Default = object : OudsDeprecatedColorOpacitySemanticTokens {
            override val opacityTransparentInverseDark: Color = Color.Unspecified
            override val opacityTransparentInverseLight: Color = Color.Unspecified
        }
    }

    @Deprecated("")
    val opacityTransparentInverseDark: Color

    @Deprecated("")
    val opacityTransparentInverseLight: Color
}

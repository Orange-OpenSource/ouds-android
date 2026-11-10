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
interface OudsDeprecatedColorContentSemanticTokens {

    @InternalOudsApi
    companion object {

        @Suppress("OVERRIDE_DEPRECATION")
        val Default = object : OudsDeprecatedColorContentSemanticTokens {
            override val contentStatusInverseInfoDark: Color = Color.Unspecified
            override val contentStatusInverseInfoLight: Color = Color.Unspecified
            override val contentStatusInverseNegativeDark: Color = Color.Unspecified
            override val contentStatusInverseNegativeLight: Color = Color.Unspecified
            override val contentStatusInversePositiveDark: Color = Color.Unspecified
            override val contentStatusInversePositiveLight: Color = Color.Unspecified
            override val contentStatusInverseWarningDark: Color = Color.Unspecified
            override val contentStatusInverseWarningLight: Color = Color.Unspecified
        }
    }

    @Deprecated("")
    val contentStatusInverseInfoDark: Color

    @Deprecated("")
    val contentStatusInverseInfoLight: Color

    @Deprecated("")
    val contentStatusInverseNegativeDark: Color

    @Deprecated("")
    val contentStatusInverseNegativeLight: Color

    @Deprecated("")
    val contentStatusInversePositiveDark: Color

    @Deprecated("")
    val contentStatusInversePositiveLight: Color

    @Deprecated("")
    val contentStatusInverseWarningDark: Color

    @Deprecated("")
    val contentStatusInverseWarningLight: Color
}

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

package com.orange.ouds.theme.orangecompact.tokens.semantic.deprecated

import androidx.compose.ui.graphics.Color
import com.orange.ouds.theme.tokens.semantic.deprecated.OudsDeprecatedColorOpacitySemanticTokens
import com.orange.ouds.tokens.raw.OudsColorRawTokens

internal class OrangeCompactDeprecatedColorOpacitySemanticTokens : OudsDeprecatedColorOpacitySemanticTokens {

    @Suppress("OVERRIDE_DEPRECATION")
    override val opacityTransparentInverseDark: Color = OudsColorRawTokens.ColorOpacityBlack0

    @Suppress("OVERRIDE_DEPRECATION")
    override val opacityTransparentInverseLight: Color = OudsColorRawTokens.ColorOpacityWhite0
}

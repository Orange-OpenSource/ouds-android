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

package com.orange.ouds.theme.wireframe.tokens.semantic.deprecated

import androidx.compose.ui.graphics.Color
import com.orange.ouds.theme.tokens.semantic.deprecated.OudsDeprecatedColorOpacitySemanticTokens
import com.orange.ouds.theme.wireframe.tokens.raw.WireframeColorRawTokens

internal class WireframeDeprecatedColorOpacitySemanticTokens : OudsDeprecatedColorOpacitySemanticTokens {

    @Suppress("OVERRIDE_DEPRECATION")
    override val opacityTransparentInverseDark: Color = WireframeColorRawTokens.ColorOpacityBlack0

    @Suppress("OVERRIDE_DEPRECATION")
    override val opacityTransparentInverseLight: Color = WireframeColorRawTokens.ColorOpacityWhite0
}

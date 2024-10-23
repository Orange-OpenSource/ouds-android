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

package com.orange.ouds.theme.whitelabel.tokens

import androidx.compose.ui.graphics.Color
import com.orange.ouds.theme.tokens.semantic.OudsBgColorSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsColorSemanticTokens

val whiteLabelSemanticColorTokens = with(WhiteLabelRawColorTokens) {
    OudsColorSemanticTokens(
        backgroundColorTokens = OudsBgColorSemanticTokens(
            bgBrandPrimaryLight = Color(0xFF34D349),
            bgBrandPrimaryDark = Color(0xFF069D1A),
            bgSecondaryLight = secondary40,
            bgSecondaryDark = secondary80,
        )
    )
}
//
// Software Name: OUDS Android
// SPDX-FileCopyrightText: Copyright (c) Orange SA
// SPDX-License-Identifier: MIT
//
// This software is distributed under the MIT license,
// the text of which is available at https://opensource.org/license/MIT/
// or see the "LICENSE" file for more details.
//
// Software description: Android library of reusable graphical components
//

package com.orange.ouds.theme.whitelabel.tokens.semantic

import com.orange.ouds.theme.tokens.semantic.OudsOpacitySemanticTokens
import com.orange.ouds.theme.whitelabel.tokens.raw.OpacityRawTokens

data class WhiteLabelOpacitySemanticTokens(
    override val invisible: Float = OpacityRawTokens.opacity0,
    override val medium: Float = OpacityRawTokens.opacity320,
    override val opaque: Float = OpacityRawTokens.opacity1000,
    override val strong: Float = OpacityRawTokens.opacity640,
    override val weak: Float = OpacityRawTokens.opacity160,
    override val weaker: Float = OpacityRawTokens.opacity40
) : OudsOpacitySemanticTokens

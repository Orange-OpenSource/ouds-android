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

import com.orange.ouds.theme.tokens.semantic.OudsGridSemanticTokens
import com.orange.ouds.tokens.raw.GridRawTokens

data class WhiteLabelGridSemanticTokens(
    override val extraCompactMinWidth: Int = GridRawTokens.gridMinWidthExtraCompact,
    override val extraCompactMaxWidth: Int = GridRawTokens.gridMaxWidthExtraCompact,
    override val extraCompactMargin: Int = GridRawTokens.gridMargin100,
    override val extraCompactColumnGap: Int = GridRawTokens.gridColumnGap100,
    override val compactMinWidth: Int = GridRawTokens.gridMinWidthCompact,
    override val compactMaxWidth: Int = GridRawTokens.gridMaxWidthCompact,
    override val compactMargin: Int = GridRawTokens.gridMargin300,
    override val compactColumnGap: Int = GridRawTokens.gridColumnGap100,
    override val mediumMinWidth: Int = GridRawTokens.gridMinWidthMedium,
    override val mediumMaxWidth: Int = GridRawTokens.gridMaxWidthMedium,
    override val mediumMargin: Int = GridRawTokens.gridMargin500,
    override val mediumColumnGap: Int = GridRawTokens.gridColumnGap400
) : OudsGridSemanticTokens

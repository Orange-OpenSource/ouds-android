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

import com.orange.ouds.theme.tokens.semantic.OudsBorderSemanticTokens
import com.orange.ouds.theme.wireframe.tokens.semantic.WireframeBorderSemanticTokens
import com.orange.ouds.tokens.raw.OudsBorderRawTokens

/**
 * White Label border semantic tokens.
 *
 * Inherits from Wireframe border tokens with customizable overrides.
 * Parameters left with their default values will use the default Wireframe values.
 *
 * ```
 * WhiteLabelBorderSemanticTokens(
 *     radiusDefault = 8.dp.value
 * )
 * ```
 */
class WhiteLabelBorderSemanticTokens(
    radiusDefault: Float = OudsBorderRawTokens.BorderRadius150
) : OudsBorderSemanticTokens by WireframeBorderSemanticTokens().copy(radiusDefault = radiusDefault)

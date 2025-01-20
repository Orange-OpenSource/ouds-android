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

import com.orange.ouds.theme.tokens.semantic.OudsElevationSemanticTokens
import com.orange.ouds.theme.whitelabel.tokens.raw.ElevationRawTokens

data class WhiteLabelElevationSemanticTokens(
    override val drag: Float = ElevationRawTokens.elevation4,
    override val none: Float = ElevationRawTokens.elevation0,
    override val overlayDefault: Float = ElevationRawTokens.elevation2,
    override val overlayEmphasized: Float = ElevationRawTokens.elevation5,
    override val raised: Float = ElevationRawTokens.elevation1,
    override val stickyDefault: Float = ElevationRawTokens.elevation3,
    override val stickyEmphasized: Float = ElevationRawTokens.elevation3,
    override val stickyNavigationScrolled: Float = ElevationRawTokens.elevation3
) : OudsElevationSemanticTokens

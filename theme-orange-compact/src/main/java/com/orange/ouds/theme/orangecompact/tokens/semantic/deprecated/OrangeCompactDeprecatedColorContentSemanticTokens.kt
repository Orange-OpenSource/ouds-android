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
import com.orange.ouds.theme.tokens.semantic.deprecated.OudsDeprecatedColorContentSemanticTokens
import com.orange.ouds.tokens.raw.OudsColorRawTokens

internal class OrangeCompactDeprecatedColorContentSemanticTokens : OudsDeprecatedColorContentSemanticTokens {

    @Suppress("OVERRIDE_DEPRECATION")
    override val contentStatusInverseInfoDark: Color = OudsColorRawTokens.ColorFunctionalDodgerBlue700

    @Suppress("OVERRIDE_DEPRECATION")
    override val contentStatusInverseInfoLight: Color = OudsColorRawTokens.ColorFunctionalDodgerBlue300

    @Suppress("OVERRIDE_DEPRECATION")
    override val contentStatusInverseNegativeDark: Color = OudsColorRawTokens.ColorFunctionalScarlet600

    @Suppress("OVERRIDE_DEPRECATION")
    override val contentStatusInverseNegativeLight: Color = OudsColorRawTokens.ColorFunctionalScarlet300

    @Suppress("OVERRIDE_DEPRECATION")
    override val contentStatusInversePositiveDark: Color = OudsColorRawTokens.ColorFunctionalMalachite750

    @Suppress("OVERRIDE_DEPRECATION")
    override val contentStatusInversePositiveLight: Color = OudsColorRawTokens.ColorFunctionalMalachite300

    @Suppress("OVERRIDE_DEPRECATION")
    override val contentStatusInverseWarningDark: Color = OudsColorRawTokens.ColorFunctionalSun750

    @Suppress("OVERRIDE_DEPRECATION")
    override val contentStatusInverseWarningLight: Color = OudsColorRawTokens.ColorFunctionalSun300
}

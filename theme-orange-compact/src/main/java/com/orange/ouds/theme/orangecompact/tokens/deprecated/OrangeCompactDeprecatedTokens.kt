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

package com.orange.ouds.theme.orangecompact.tokens.deprecated

import com.orange.ouds.theme.orangecompact.tokens.components.deprecated.OrangeCompactDeprecatedComponentsTokens
import com.orange.ouds.theme.orangecompact.tokens.semantic.deprecated.OrangeCompactDeprecatedSemanticTokens
import com.orange.ouds.theme.tokens.components.deprecated.OudsDeprecatedComponentsTokens
import com.orange.ouds.theme.tokens.deprecated.OudsDeprecatedTokens
import com.orange.ouds.theme.tokens.semantic.deprecated.OudsDeprecatedSemanticTokens

internal class OrangeCompactDeprecatedTokens : OudsDeprecatedTokens {
    
    override val components: OudsDeprecatedComponentsTokens = OrangeCompactDeprecatedComponentsTokens()
    override val semantic: OudsDeprecatedSemanticTokens = OrangeCompactDeprecatedSemanticTokens()
}

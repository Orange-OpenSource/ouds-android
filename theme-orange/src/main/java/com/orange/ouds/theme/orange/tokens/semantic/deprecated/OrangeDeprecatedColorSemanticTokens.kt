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

package com.orange.ouds.theme.orange.tokens.semantic.deprecated

import com.orange.ouds.theme.tokens.semantic.deprecated.OudsDeprecatedColorContentSemanticTokens
import com.orange.ouds.theme.tokens.semantic.deprecated.OudsDeprecatedColorOpacitySemanticTokens
import com.orange.ouds.theme.tokens.semantic.deprecated.OudsDeprecatedColorSemanticTokens

internal class OrangeDeprecatedColorSemanticTokens : OudsDeprecatedColorSemanticTokens {

    override val content: OudsDeprecatedColorContentSemanticTokens = OrangeDeprecatedColorContentSemanticTokens()
    override val opacity: OudsDeprecatedColorOpacitySemanticTokens = OrangeDeprecatedColorOpacitySemanticTokens()
}

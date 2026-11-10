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

package com.orange.ouds.theme.tokens.semantic.deprecated

import com.orange.ouds.foundation.InternalOudsApi

/** @suppress */
@InternalOudsApi
interface OudsDeprecatedColorSemanticTokens {

    @InternalOudsApi
    companion object {

        val Default = object : OudsDeprecatedColorSemanticTokens {
            override val content: OudsDeprecatedColorContentSemanticTokens = OudsDeprecatedColorContentSemanticTokens.Default
            override val opacity: OudsDeprecatedColorOpacitySemanticTokens = OudsDeprecatedColorOpacitySemanticTokens.Default
        }
    }

    val content: OudsDeprecatedColorContentSemanticTokens
    val opacity: OudsDeprecatedColorOpacitySemanticTokens
}

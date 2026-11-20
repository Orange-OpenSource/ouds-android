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

package com.orange.ouds.theme.tokens.deprecated

import com.orange.ouds.foundation.InternalOudsApi
import com.orange.ouds.theme.tokens.components.deprecated.OudsDeprecatedComponentsTokens
import com.orange.ouds.theme.tokens.semantic.deprecated.OudsDeprecatedSemanticTokens

/** @suppress */
@InternalOudsApi
interface OudsDeprecatedTokens {

    @InternalOudsApi
    companion object {

        val Default = object : OudsDeprecatedTokens {
            override val components: OudsDeprecatedComponentsTokens = OudsDeprecatedComponentsTokens.Default
            override val semantic: OudsDeprecatedSemanticTokens = OudsDeprecatedSemanticTokens.Default
        }
    }

    val components: OudsDeprecatedComponentsTokens
    val semantic: OudsDeprecatedSemanticTokens
}

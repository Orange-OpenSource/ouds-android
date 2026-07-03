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

package com.orange.ouds.theme.orange.tokens.components.deprecated

import com.orange.ouds.foundation.OudsDeprecatedTokensProvider
import com.orange.ouds.theme.orange.tokens.components.OrangeButtonTokens

internal interface OrangeDeprecatedTokensProvider<T> : OudsDeprecatedTokensProvider<T> {

    @Suppress("UNCHECKED_CAST")
    override val deprecated: T
        get() = when (this) {
            is OrangeButtonTokens -> OrangeDeprecatedButtonTokens() as T
            else -> error("No deprecated tokens available for this type.")
        }
}

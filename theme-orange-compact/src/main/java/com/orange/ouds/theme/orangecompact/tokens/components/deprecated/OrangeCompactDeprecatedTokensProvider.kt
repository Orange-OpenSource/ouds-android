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

package com.orange.ouds.theme.orangecompact.tokens.components.deprecated

import com.orange.ouds.foundation.OudsDeprecatedTokensProvider
import com.orange.ouds.theme.orangecompact.tokens.components.OrangeCompactButtonTokens

internal interface OrangeCompactDeprecatedTokensProvider<T> : OudsDeprecatedTokensProvider<T> {

    @Suppress("UNCHECKED_CAST")
    override val deprecated: T
        get() = when (this) {
            is OrangeCompactButtonTokens -> OrangeCompactDeprecatedButtonTokens() as T
            else -> error("No deprecated tokens available for this type.")
        }
}

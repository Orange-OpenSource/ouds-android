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

package com.orange.ouds.theme.tokens.components.deprecated

import com.orange.ouds.foundation.InternalOudsApi
import com.orange.ouds.theme.tokens.OudsColorKeyToken

/** @suppress */
@InternalOudsApi
interface OudsDeprecatedIconsTokens {

    @InternalOudsApi
    companion object {

        @Suppress("OVERRIDE_DEPRECATION")
        val Default = object : OudsDeprecatedIconsTokens {
            override val colorContentStatusWarningInverseExternalShape: OudsColorKeyToken = OudsColorKeyToken.Unspecified
            override val colorContentStatusWarningInverseInternalShape: OudsColorKeyToken = OudsColorKeyToken.Unspecified
        }
    }

    @Deprecated("")
    val colorContentStatusWarningInverseExternalShape: OudsColorKeyToken

    @Deprecated("")
    val colorContentStatusWarningInverseInternalShape: OudsColorKeyToken
}

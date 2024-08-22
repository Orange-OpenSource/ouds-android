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

package com.orange.ouds.theme.tokens.components

import com.orange.ouds.theme.tokens.semantic.OudsBorderRadiusToken
import com.orange.ouds.theme.tokens.semantic.OudsColorToken

open class OudsButtonTokens(
    val containerColor: OudsColorToken = OudsColorToken.Primary,
    val contentColor: OudsColorToken = OudsColorToken.OnPrimary,
    val disabledContainerColor: OudsColorToken = OudsColorToken.Secondary,
    val disabledContentColor: OudsColorToken = OudsColorToken.OnSecondary,
    val cornerRadius: OudsBorderRadiusToken = OudsBorderRadiusToken.None
)
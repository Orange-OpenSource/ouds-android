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

package com.orange.ouds.theme.components

import com.orange.ouds.theme.colors.OudsSemanticColorToken

open class OudsButtonTokens(
    val containerColor: OudsSemanticColorToken = OudsSemanticColorToken.Primary,
    val contentColor: OudsSemanticColorToken = OudsSemanticColorToken.OnPrimary,
    val disabledContainerColor: OudsSemanticColorToken = OudsSemanticColorToken.Secondary,
    val disabledContentColor: OudsSemanticColorToken = OudsSemanticColorToken.OnSecondary,
)
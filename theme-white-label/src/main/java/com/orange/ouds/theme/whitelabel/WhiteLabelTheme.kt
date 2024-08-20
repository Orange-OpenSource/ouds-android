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

package com.orange.ouds.theme.whitelabel

import com.orange.ouds.theme.OudsCustomTheme
import com.orange.ouds.theme.tokens.semantic.OudsSemanticColors
import com.orange.ouds.theme.whitelabel.tokens.semantic.WhiteLabelSemanticColors
import kotlinx.parcelize.Parcelize

@Parcelize
open class WhiteLabelTheme : OudsCustomTheme {

    override val name: String
        get() = "White label"

    override val colors: OudsSemanticColors
        get() = WhiteLabelSemanticColors
}

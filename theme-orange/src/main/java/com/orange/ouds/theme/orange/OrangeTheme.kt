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

package com.orange.ouds.theme.orange

import com.orange.ouds.theme.OudsThemeContract
import kotlinx.parcelize.Parcelize

const val ORANGE_THEME_NAME = "Orange"

@Parcelize
open class OrangeTheme : OudsThemeContract {

    override val name: String
        get() = ORANGE_THEME_NAME

}

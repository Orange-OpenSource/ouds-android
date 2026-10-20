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

import androidx.compose.ui.text.font.FontFamily
import java.util.Locale

fun interface WhiteLabelFontFamily {

    companion object {

        val Default = WhiteLabelFontFamily { FontFamily.Default }
    }

    fun getFontFamily(locale: Locale): FontFamily
}

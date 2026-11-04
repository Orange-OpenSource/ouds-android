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

/**
 * A functional interface for providing font families to the White Label theme.
 *
 * This interface allows customization of the font family used by [WhiteLabelTheme],
 * with locale-aware font selection support.
 *
 * ## Default Implementation
 *
 * The [Default] companion object provides a default implementation that returns [FontFamily.Default]:
 *
 * ```
 * OudsTheme(theme = WhiteLabelTheme(whiteLabelFontFamily = WhiteLabelFontFamily.Default)) {
 *     // Your composables
 * }
 * ```
 *
 * ## Custom Implementation
 *
 * Create a custom implementation to use your own fonts:
 *
 * ```
 * val customFontFamily = WhiteLabelFontFamily { locale ->
 *     if (locale.language == "ar") {
 *         FontFamily(
 *             Font(R.font.my_arabic_regular, FontWeight.Normal),
 *             Font(R.font.my_arabic_bold, FontWeight.Bold)
 *         )
 *     } else {
 *         FontFamily(
 *             Font(R.font.my_latin_regular, FontWeight.Normal),
 *             Font(R.font.my_latin_bold, FontWeight.Bold)
 *         )
 *     }
 * }
 *
 * OudsTheme(theme = WhiteLabelTheme(whiteLabelFontFamily = customFontFamily)) {
 *     // Your composables
 * }
 * ```
 */
fun interface WhiteLabelFontFamily {

    companion object {

        /**
         * Default font family implementation that returns [FontFamily.Default].
         */
        val Default = WhiteLabelFontFamily { FontFamily.Default }
    }

    /**
     * Returns the font family to use for the given locale.
     *
     * @param locale The current locale.
     * @return The font family to use.
     */
    fun getFontFamily(locale: Locale): FontFamily
}

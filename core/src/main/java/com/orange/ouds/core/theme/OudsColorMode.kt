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

package com.orange.ouds.core.theme

@ConsistentCopyVisibility
data class OudsColorMode private constructor(val tweak: OudsTheme.Tweak, val monochrome: Boolean, private val identifier: String? = null) {

    constructor(tweak: OudsTheme.Tweak, monochrome: Boolean) : this(tweak, monochrome, null)

    companion object {

        // This identifier is used to differentiate Unspecified from "light" color mode
        private const val UnspecifiedIdentifier = "OudsColorMode.Unspecified"

        val Unspecified = OudsColorMode(tweak = OudsTheme.Tweak.ForceLight, monochrome = false, UnspecifiedIdentifier)

        internal fun fromString(string: String): OudsColorMode {
            return when (string) {
                "dark" -> OudsColorMode(tweak = OudsTheme.Tweak.ForceDark, monochrome = false)
                "light" -> OudsColorMode(tweak = OudsTheme.Tweak.ForceLight, monochrome = false)
                "mono-dark" -> OudsColorMode(tweak = OudsTheme.Tweak.ForceDark, monochrome = true)
                "mono-light" -> OudsColorMode(tweak = OudsTheme.Tweak.ForceLight, monochrome = true)
                // \uFE0F is the code for the variation selector that specifies that an emoji should be presented as an image
                "⛔\uFE0F", "⛔" -> Unspecified
                else -> error("Color mode $string is unknown.")
            }
        }
    }

    /** `true` when this is [com.orange.ouds.core.theme.OudsColorMode.Unspecified]. */
    val isUnspecified: Boolean
        get() = identifier == UnspecifiedIdentifier
}

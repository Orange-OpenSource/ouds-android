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
data class OudsColorMode private constructor(val dark: Boolean, val mono: Boolean, private val identifier: String? = null) {

    constructor(dark: Boolean, mono: Boolean) : this(dark, mono, null)

    companion object {

        // This identifier is used to differentiate Unspecified from "light" color mode
        private const val UnspecifiedIdentifier = "OudsColorMode.Unspecified"

        val Unspecified = OudsColorMode(dark = false, mono = false, UnspecifiedIdentifier)

        internal fun fromString(string: String): OudsColorMode {
            return when (string) {
                "dark" -> OudsColorMode(dark = true, mono = false)
                "light" -> OudsColorMode(dark = false, mono = false)
                "mono-dark" -> OudsColorMode(dark = true, mono = true)
                "mono-light" -> OudsColorMode(dark = false, mono = true)
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

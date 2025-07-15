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

/**
 * The color mode associated with a colored background.
 * This defines how content is displayed when it is layout on a colored background.
 *
 * @property tweak The theme tweak associated with this color mode.
 * @property monochrome Indicates if the monochrome variant of components should be used.
 *   When `true`, OUDS Android components are displayed using their monochrome variant, if it exists.
 */
@ConsistentCopyVisibility
data class OudsColorMode private constructor(val tweak: OudsTheme.Tweak, val monochrome: Boolean, private val identifier: String? = null) {

    /**
     * Creates an instance of [OudsColorMode].
     *
     * @param tweak The theme tweak associated with this color mode.
     * @param monochrome Indicates if the monochrome variant of components should be used.
     *   When `true`, OUDS Android components are displayed using their monochrome variant, if it exists.
     */
    constructor(tweak: OudsTheme.Tweak, monochrome: Boolean) : this(tweak, monochrome, null)

    companion object {

        // This identifier is used to differentiate Unsupported from "light" color mode
        private const val UnsupportedIdentifier = "OudsColorMode.Unsupported"

        /** An unsupported color mode. */
        val Unsupported = OudsColorMode(tweak = OudsTheme.Tweak.ForceLight, monochrome = false, UnsupportedIdentifier)

        internal fun fromString(string: String): OudsColorMode {
            return when (string) {
                "dark" -> OudsColorMode(tweak = OudsTheme.Tweak.ForceDark, monochrome = false)
                "light" -> OudsColorMode(tweak = OudsTheme.Tweak.ForceLight, monochrome = false)
                "mono-dark" -> OudsColorMode(tweak = OudsTheme.Tweak.ForceDark, monochrome = true)
                "mono-light" -> OudsColorMode(tweak = OudsTheme.Tweak.ForceLight, monochrome = true)
                // \uFE0F is the code for the variation selector that specifies that an emoji should be presented as an image
                "⛔\uFE0F", "⛔" -> Unsupported
                else -> error("Color mode $string is unknown.")
            }
        }
    }

    /** `true` when this color mode is not [com.orange.ouds.core.theme.OudsColorMode.Unsupported]. */
    val isSupported: Boolean
        get() = identifier != UnsupportedIdentifier
}

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

package com.orange.ouds.theme.tokens.type

import java.util.Locale

enum class BorderStyle {
    None, Solid, Dashed, Dotted;

    companion object {
        fun fromString(string: String): BorderStyle {
            return try {
                BorderStyle.valueOf(string.lowercase().replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() })
            } catch (exception: IllegalArgumentException) {
                // If the provided border style value is unknown, return the default border style
                None
            }
        }
    }
}
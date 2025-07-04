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

package com.orange.ouds.app.ui.utilities

/**
 * Returns the formatted name of the enum. For instance `FooBar` becomes `Foo Bar`.
 */
val Enum<*>.formattedName: String
    get() = name.split("(?=\\p{Lu})".toRegex())
        .joinToString(separator = " ") { it.lowercase() }
        .trim()
        .replaceFirstChar { it.uppercaseChar() }

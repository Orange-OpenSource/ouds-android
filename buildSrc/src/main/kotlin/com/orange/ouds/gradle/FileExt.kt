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

import java.io.File

fun File.replace(regex: Regex, replacement: String) {
    val text = readText()
    writeText(text.replace(regex, replacement))
}

fun File.replace(regex: Regex, transform: (MatchResult) -> CharSequence) {
    val text = readText()
    writeText(text.replace(regex, transform))
}

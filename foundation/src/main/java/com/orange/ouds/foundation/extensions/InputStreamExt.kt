/*
 * Software Name: Orange Unified Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
 */

package com.orange.ouds.foundation.extensions

import java.io.InputStream
import java.util.Scanner

fun InputStream.contentAsString(): String? {
    val scanner = Scanner(this).useDelimiter("\\A")
    return if (scanner.hasNext()) scanner.next() else null
}

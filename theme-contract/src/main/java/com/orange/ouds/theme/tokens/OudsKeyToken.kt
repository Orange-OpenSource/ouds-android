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

package com.orange.ouds.theme.tokens

sealed interface OudsKeyToken {

    val name: String
        get() {
            val packageName = this::class.java.`package`?.name.orEmpty()
            return this::class.qualifiedName.orEmpty().removePrefix("$packageName.")
        }
}

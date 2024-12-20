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

import kotlin.reflect.KClass

fun <T : Any> KClass<T>.getSealedSubclasses(recursive: Boolean = true): List<KClass<out T>> {
    return if (recursive) {
        sealedSubclasses + sealedSubclasses.flatMap { it.getSealedSubclasses(recursive = true) }
    } else {
        sealedSubclasses.toList()
    }
}

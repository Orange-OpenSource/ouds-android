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

package com.orange.ouds.core.extensions

import androidx.compose.ui.Modifier

internal fun Modifier.filter(predicate: (Modifier.Element) -> Boolean): Modifier {
    return foldIn<Modifier>(Modifier) { result, element ->
        if (predicate(element)) result.then(element) else result
    }
}

internal fun Modifier.last(): Modifier {
    return foldOut<Modifier>(Modifier) { element, result ->
        if (result == Modifier) element else result
    }
}

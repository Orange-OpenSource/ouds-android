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

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

internal fun Modifier.filter(predicate: (Modifier.Element) -> Boolean): Modifier {
    return foldIn<Modifier>(Modifier) { result, element ->
        if (predicate(element)) result.then(element) else result
    }
}

internal fun Modifier.iconSize(size: Dp, tinted: Boolean): Modifier = if (tinted) this.size(size) else height(size)

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

package com.orange.ouds.core.component.content

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * A marker interface for objects that can represent multiple [OudsComponentContent].
 * This interface is typically implemented by sealed interface where subclasses are different implementations of [OudsComponentContent].
 */
// Using extension methods instead of adding them to the interface allows to use the internal keyword
interface OudsPolymorphicComponentContent

@Composable
internal fun OudsPolymorphicComponentContent.PolymorphicContent() {
    return (this as OudsComponentContent<*>).Content()
}

@Composable
internal fun OudsPolymorphicComponentContent.PolymorphicContent(modifier: Modifier) {
    return (this as OudsComponentContent<*>).Content(modifier)
}

@Composable
internal fun <T> OudsPolymorphicComponentContent.PolymorphicContent(extraParameters: T) where T : OudsComponentContent.ExtraParameters {
    @Suppress("UNCHECKED_CAST")
    return (this as OudsComponentContent<T>).Content(extraParameters)
}

@Composable
internal fun <T> OudsPolymorphicComponentContent.PolymorphicContent(modifier: Modifier, extraParameters: T) where T : OudsComponentContent.ExtraParameters {
    @Suppress("UNCHECKED_CAST")
    return (this as OudsComponentContent<T>).Content(modifier, extraParameters)
}

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

package com.orange.ouds.foundation.utilities

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

/**
 * A basic implementation of [PreviewParameterProvider].
 *
 * @param T The type of the preview parameter.
 * @param values The preview parameter values.
 */
open class BasicPreviewParameterProvider<T>(vararg values: T) : PreviewParameterProvider<T> {

    override val values = values.asSequence()
}

/**
 * A preview parameter provider for enum values.
 *
 * @param clazz The enum class.
 */
open class EnumPreviewParameterProvider(clazz: Class<out Enum<*>>) : BasicPreviewParameterProvider<Enum<*>>(*clazz.enumConstants)

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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import com.orange.ouds.foundation.extensions.asOrNull
import com.orange.ouds.foundation.extensions.orElse


internal val localExtraParametersByClass =
    mutableMapOf<Class<out OudsComponentContent.ExtraParameters>, ProvidableCompositionLocal<out OudsComponentContent.ExtraParameters>>()

internal fun <T> getLocalExtraParameters(clazz: Class<T>): ProvidableCompositionLocal<T> where T : OudsComponentContent.ExtraParameters {
    return localExtraParametersByClass[clazz]?.asOrNull<ProvidableCompositionLocal<T>>().orElse {
        staticCompositionLocalOf<T> { error("CompositionLocal LocalExtraParameters for class ${clazz.name} not present") }.also { compositionLocal ->
            localExtraParametersByClass[clazz] = compositionLocal
        }
    }
}

/**
 * The content of a component.
 *
 * Subclasses of [OudsComponentContent] should be used instead of composable methods when passing parameters to components.
 * This prevents using generic composable methods that can encapsulate any kind of views and thus helps developers to follow UI guidelines more easily.
 * This also allows to group parameters that are related to the same content inside a component.
 * For instance it is possible to create an `Icon` subclass to replace both `icon: @Composable () -> Unit` and `onIconClick: () -> Unit` parameters with a single `icon: Icon` parameter.
 *
 * @param extraParametersClass The extra parameters class.
 * @param T The type of extra parameters.
 */
abstract class OudsComponentContent<T> internal constructor(private val extraParametersClass: Class<T>) where T : OudsComponentContent.ExtraParameters {

    /**
     * Extra parameters that can be passed to the `Content` method when other parameters than those provided by the user are needed to layout the component.
     */
    abstract class ExtraParameters

    /**
     * The extra parameters.
     */
    protected val extraParameters: T
        @Composable
        get() = getLocalExtraParameters(extraParametersClass).current

    /**
     * The Jetpack Compose UI for this component content.
     *
     * Calls `Content(Modifier)` with the default `Modifier`.
     */
    @Composable
    internal fun Content() = Content(modifier = Modifier)

    /**
     * The Jetpack Compose UI for this component content.
     *
     * Calls `Content(Modifier, T)` with the default `Modifier`.
     *
     * @param extraParameters The extra parameters for this content.
     */
    @Composable
    internal fun Content(extraParameters: T) = Content(modifier = Modifier, extraParameters = extraParameters)

    /**
     * The Jetpack Compose UI for this component content.
     *
     * @param modifier The modifier for this content.
     * @param extraParameters The extra parameters for this content.
     */
    @Composable
    internal fun Content(modifier: Modifier, extraParameters: T) {
        CompositionLocalProvider(getLocalExtraParameters(extraParametersClass) provides extraParameters) {
            Content(modifier = modifier)
        }
    }

    /**
     * The Jetpack Compose UI for this component content.
     * Subclasses must implement this method to provide content.
     *
     * @param modifier The modifier for this content.
     */
    @Composable
    internal abstract fun Content(modifier: Modifier)
}

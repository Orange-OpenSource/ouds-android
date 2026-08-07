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
import androidx.compose.ui.platform.testTag
import com.orange.ouds.foundation.extensions.orElse

/**
 * Content of a component.
 *
 * Subclasses of [OudsComponentContent] should be used instead of composable methods when passing parameters to components.
 * This prevents using generic composable methods that can encapsulate any kind of views and thus helps developers to follow UI guidelines more easily.
 * This also allows grouping parameters that are related to the same content inside a component.
 * For instance, it is possible to create an `Icon` subclass to replace both `icon: @Composable () -> Unit` and `onIconClick: () -> Unit` parameters with a single `icon: Icon` parameter.
 *
 * @param T The type of extra parameters.
 *
 * @suppress
 */
abstract class OudsComponentContent<T> internal constructor() where T : OudsComponentContent.ExtraParameters {

    /**
     * Optional test tag for UI testing purposes.
     *
     * This property should only be used when writing UI tests and can be ignored in production code.
     * Test tags provide stable identifiers for UI components that are independent of text content
     * or accessibility semantics.
     */
    var testTag: String? = null

    /**
     * Extra parameters that can be passed to the `Content` method when other parameters than those provided by the user are needed to layout the component.
     */
    abstract class ExtraParameters

    /**
     * The extra parameters.
     */
    internal lateinit var extraParameters: T

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
        this.extraParameters = extraParameters
        Content(modifier = modifier)
    }

    /**
     * The Jetpack Compose UI for this component content.
     * Subclasses must implement this method to provide content.
     *
     * @param modifier The modifier for this content.
     */
    @Composable
    internal abstract fun Content(modifier: Modifier)

    internal fun Modifier.componentContentTestTag() = testTag?.let { testTag(it) }.orElse { this }
}

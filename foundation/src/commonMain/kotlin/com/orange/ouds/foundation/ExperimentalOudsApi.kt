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

package com.orange.ouds.foundation

/**
 * This annotation marks an OUDS Android API that is considered experimental and is not subject to the
 * [Semantic Versioning](https://semver.org) rules:
 * the behavior of such API may be changed or the API may be removed completely in any further release.
 *
 * > Beware using the annotated API especially if you're developing a library, since your library might become binary incompatible
 * with the future versions of OUDS Android.
 *
 * Any usage of a declaration annotated with `@ExperimentalOudsApi` must be accepted either by
 * annotating that usage with the [OptIn] annotation, e.g. `@OptIn(ExperimentalOudsApi::class)`,
 * or by using the compiler argument `-opt-in=com.orange.ouds.foundation.ExperimentalOudsApi`.
 */
@RequiresOptIn(
    level = RequiresOptIn.Level.ERROR,
    message = "This OUDS API is experimental and is likely to change or to be removed in the future."
)
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.BINARY)
annotation class ExperimentalOudsApi

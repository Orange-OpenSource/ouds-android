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
 * An annotation for OUDS Android APIs intended for developers building custom components
 * that extend or closely resemble OUDS components.
 *
 * These APIs provide lower-level building blocks and advanced customization options beyond
 * the standard OUDS components. While stable and supported, they are not the recommended
 * approach for typical app development.
 *
 * **Prefer using standard OUDS components** (e.g., `OudsSuggestionChip`) over their "Basic"
 * variants or direct token access when possible.
 *
 * APIs marked with this annotation include:
 * - "Basic" component variants that expose content/layout parameters (e.g., `OudsBasicSuggestionChip`)
 * - Direct access to component tokens via `OudsTheme.components`
 * - Other APIs designed for custom component development
 *
 * Any usage of a declaration annotated with `@DeveloperOudsApi` must be accepted either by
 * annotating that usage with the [OptIn] annotation, e.g. `@OptIn(DeveloperOudsApi::class)`,
 * or by using the compiler argument `-opt-in=com.orange.ouds.foundation.DeveloperOudsApi`.
 */
@RequiresOptIn(
    level = RequiresOptIn.Level.ERROR,
    message = "This OUDS API is intended for custom component development. Prefer using standard OUDS components when possible."
)
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.BINARY)
annotation class DeveloperOudsApi

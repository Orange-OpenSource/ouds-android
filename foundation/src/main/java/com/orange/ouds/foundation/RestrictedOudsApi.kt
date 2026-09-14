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
 * An annotation for restricted OUDS Android APIs that provide building blocks for custom
 * component development and advanced theme customization.
 *
 * These APIs offer advanced customization capabilities beyond what standard OUDS components
 * and theme configuration provide. While stable and supported, they are restricted to specific
 * use cases and should only be used when standard OUDS APIs are insufficient for your requirements.
 *
 * **Prefer using standard OUDS components** (e.g., `OudsSuggestionChip`) and theme configuration
 * when possible, as they provide a higher-level, more convenient API.
 *
 * ## APIs marked with this annotation include:
 *
 * ### Component Development
 * - "Basic" component variants that expose content/layout parameters (e.g., `OudsBasicSuggestionChip`)
 * - Direct access to component tokens via `OudsTheme.components`
 * - Component token classes and interfaces
 *
 * ### Advanced Theme Customization
 * - Token data classes for granular token overrides
 * - Drawable resource data classes for icon and graphic customization
 * - Font family configuration classes for typography customization
 * - Other low-level building blocks for custom theme development
 *
 * Any usage of a declaration annotated with `@RestrictedOudsApi` must be accepted either by
 * annotating that usage with the [OptIn] annotation, e.g. `@OptIn(RestrictedOudsApi::class)`,
 * or by using the compiler argument `-opt-in=com.orange.ouds.foundation.RestrictedOudsApi`.
 */
@RequiresOptIn(
    level = RequiresOptIn.Level.ERROR,
    message = "This is a restricted OUDS API for advanced customization. Use only when standard OUDS components and theme configuration are insufficient for your needs."
)
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.BINARY)
annotation class RestrictedOudsApi

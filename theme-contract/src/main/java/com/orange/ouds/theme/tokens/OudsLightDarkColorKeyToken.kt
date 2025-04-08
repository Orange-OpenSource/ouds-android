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

package com.orange.ouds.theme.tokens

/**
 * A color key token which aggregates two color key tokens, with one being used for the light theme and the other for the dark theme.
 *
 * @property light The color key token used for the light theme.
 * @property dark The color key token used for the dark theme.
 * @constructor Creates an instance of [OudsLightDarkColorKeyToken].
 */
data class OudsLightDarkColorKeyToken(val light: OudsColorKeyToken, val dark: OudsColorKeyToken) : OudsColorKeyToken {

    init {
        if (light is OudsLightDarkColorKeyToken || dark is OudsLightDarkColorKeyToken) {
            throw IllegalArgumentException("Light and dark key tokens cannot be of type ${OudsLightDarkColorKeyToken::class.simpleName}.")
        }
    }
}

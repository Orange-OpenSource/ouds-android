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

package com.orange.ouds.theme.tokens.components

import androidx.compose.ui.graphics.Color

/**
 * A color token which aggregates two colors, with one being used for the light theme and the other for the dark theme.
 *
 * @property light The color used for the light theme.
 * @property dark The color used for the dark theme.
 * @constructor Creates an instance of [OudsLightDarkColorToken].
 */
data class OudsLightDarkColorToken(val light: Color, val dark: Color)
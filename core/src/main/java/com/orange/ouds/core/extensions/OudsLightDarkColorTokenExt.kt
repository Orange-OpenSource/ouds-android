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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import com.orange.ouds.core.theme.isOudsInDarkTheme
import com.orange.ouds.theme.tokens.components.OudsLightDarkColorToken

val OudsLightDarkColorToken.value: Color
    @ReadOnlyComposable
    @Composable
    get() = if (isOudsInDarkTheme()) dark else light
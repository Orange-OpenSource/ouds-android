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

package com.orange.ouds.app.ui.tokens

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.orange.ouds.app.R

/**
 * A design system token that will be displayed in tokens screens.
 *
 * @property name The full name of the token.
 * @property relativeName The name of the token relative to the section in which it is displayed in [TokenCategoryDetailScreen].
 *   For instance the token named "OudsTheme.colorScheme.action.negative.enabled" will be displayed in the "Action" section
 *   of the color tokens screen as "negative.enabled".
 * @property value A composable method that returns the value of the token.
 */
data class Token<T>(val name: String, val relativeName: String, val value: @Composable () -> T) {

    val literalValue: String
        @Composable
        get() = when (val value = value()) {
            is Color -> value.toHexString()
            is Float -> stringResource(id = R.string.app_tokens_floatFormat_label, value)
            is Dp -> stringResource(id = R.string.app_tokens_dpFormat_label, value.toString().replace(".0.dp", "").substringBeforeLast(".dp"))
            is TextStyle -> stringResource(
                id = R.string.app_tokens_spFormat_label,
                value.fontSize.toString().replace(".0.sp", "").substringBeforeLast(".sp")
            )
            is Enum<*> -> value.name
            else -> this.value.toString()
        }
}

/**
 * @return the hexadecimal value of the Color
 */
private fun Color.toHexString() = "#" + value.toString(16).substring(2, 8).uppercase()
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.orange.ouds.app.R

data class Token<T>(val name: String, val value: T) {
    val literalValue: String
        @Composable
        get() = when (value) {
            is Float -> stringResource(id = R.string.app_tokens_floatFormat_label, value)
            is Dp -> stringResource(id = R.string.app_tokens_dpFormat_label, value.toString().replace(".0.dp", "").substringBeforeLast(".dp"))
            is TextStyle -> stringResource(id = R.string.app_tokens_spFormat_label, value.fontSize.toString().replace(".0.sp", "").substringBeforeLast(".sp"))
            else -> value.toString()
        }
}

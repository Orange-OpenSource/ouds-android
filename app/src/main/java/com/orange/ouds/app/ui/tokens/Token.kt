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
import androidx.compose.ui.graphics.isUnspecified
import androidx.compose.ui.graphics.toArgb
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
            is Color -> {
                if (value.isUnspecified) {
                    stringResource(id = R.string.app_tokens_color_unspecified_label)
                } else {
                    stringResource(
                        id = R.string.app_tokens_colorFormat_label,
                        value.toArgb().toHexString().uppercase().let { if (it.startsWith("FF")) it.drop(2) else it }
                    )
                }
            }
            is Float -> "${value}f"
            // "\u200e" forces LTR display even if the app is in arabic
            is Dp -> "\u200e${value.value.normalized} dp"
            is TextStyle -> {
                listOfNotNull(
                    stringResource(R.string.app_tokens_typography_size_label, value.fontSize.value.normalized.toString()),
                    value.fontWeight?.weight?.let { stringResource(R.string.app_tokens_typography_weight_label, it) },
                    stringResource(R.string.app_tokens_typography_letterSpacing_label, value.letterSpacing.value.normalized.toString()),
                    stringResource(R.string.app_tokens_typography_lineHeight_label, value.lineHeight.value.normalized.toString())
                ).joinToString("\n")
            }
            is Enum<*> -> value.name
            else -> this.value.toString()
        }
}

private val Float.normalized: Number
    get() = if (this % 1 == 0f) toInt() else this

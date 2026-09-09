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

package com.orange.ouds.app.ui.components.typography

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.R

@Composable
fun rememberCodeTextDemoState(
    text: String = stringResource(id = R.string.app_components_typography_common_text_label),
    annotatedText: Boolean = false
) = rememberSaveable(text, annotatedText, saver = TypographyDemoState.Saver) { TypographyDemoState(text, annotatedText) }

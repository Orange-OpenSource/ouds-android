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

package com.orange.ouds.app.ui.utilities

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml
import com.orange.ouds.core.component.common.text.OudsAnnotatedString

fun <T> OudsAnnotatedString.Builder<T>.appendHtml(html: String) where T : OudsAnnotatedString<T> {
    append(AnnotatedString.fromHtml(html))
}

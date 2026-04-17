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

package com.orange.ouds.core.component.common.text

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString

class OudsAnnotatedHelperText internal constructor(annotatedString: AnnotatedString) : OudsAnnotatedString<OudsAnnotatedHelperText>(annotatedString) {

    class Builder(capacity: Int = 16) : OudsAnnotatedString.Builder<OudsAnnotatedHelperText>(capacity, OudsAnnotatedHelperText::class.java), StrongBuilder {

        constructor(text: String) : this() {
            append(text)
        }

        constructor(text: OudsAnnotatedHelperText) : this() {
            append(text)
        }

        @Composable
        override fun AddStrong(start: Int, end: Int) = AddStrongImpl(start, end)

        @Composable
        override fun pushStrong(): Int = pushStrongImpl()
    }
}

@Composable
fun buildOudsAnnotatedHelperText(builder: @Composable ((OudsAnnotatedHelperText.Builder).() -> Unit)): OudsAnnotatedHelperText {
    return buildOudsAnnotatedString<OudsAnnotatedHelperText, OudsAnnotatedHelperText.Builder>(builder)
}

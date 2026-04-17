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

class OudsAnnotatedAlertMessageDescription internal constructor(annotatedString: AnnotatedString) :
    OudsAnnotatedString<OudsAnnotatedAlertMessageDescription>(annotatedString) {

    class Builder(capacity: Int = 16) :
        OudsAnnotatedString.Builder<OudsAnnotatedAlertMessageDescription>(capacity, OudsAnnotatedAlertMessageDescription::class.java),
        StrongBuilder, LinkBuilder {

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

        @Composable
        override fun AddLink(url: OudsLinkAnnotation.Url, start: Int, end: Int) = AddLinkImpl(url, start, end)

        @Composable
        override fun AddLink(clickable: OudsLinkAnnotation.Clickable, start: Int, end: Int) = AddLinkImpl(clickable, start, end)

        @Composable
        override fun pushLink(link: OudsLinkAnnotation): Int = pushLinkImpl(link)
    }
}

@Composable
fun buildOudsAnnotatedAlertMessageDescription(builder: @Composable ((OudsAnnotatedAlertMessageDescription.Builder).() -> Unit)): OudsAnnotatedAlertMessageDescription {
    return buildOudsAnnotatedString<OudsAnnotatedAlertMessageDescription, OudsAnnotatedAlertMessageDescription.Builder>(builder)
}

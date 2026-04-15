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

import androidx.compose.ui.text.AnnotatedString

class OudsAnnotatedAlertMessageBulletListLabel internal constructor(annotatedString: AnnotatedString) :
    OudsAnnotatedString<OudsAnnotatedAlertMessageBulletListLabel>(annotatedString) {

    class Builder(capacity: Int = 16) :
        OudsAnnotatedString.Builder<OudsAnnotatedAlertMessageBulletListLabel>(capacity, OudsAnnotatedAlertMessageBulletListLabel::class.java),
        StrongBuilder, LinkBuilder {

        constructor(text: String) : this() {
            append(text)
        }

        constructor(text: OudsAnnotatedHelperText) : this() {
            append(text)
        }

        override fun addStrong(start: Int, end: Int) = addStrongImpl(start, end)

        override fun pushStrong(): Int = pushStrongImpl()

        override fun addLink(url: OudsLinkAnnotation.Url, start: Int, end: Int) = addLinkImpl(url, start, end)

        override fun addLink(clickable: OudsLinkAnnotation.Clickable, start: Int, end: Int) = addLinkImpl(clickable, start, end)

        override fun pushLink(link: OudsLinkAnnotation): Int = pushLinkImpl(link)
    }
}

fun buildOudsAnnotatedAlertMessageBulletListLabel(builder: (OudsAnnotatedAlertMessageBulletListLabel.Builder).() -> Unit): OudsAnnotatedAlertMessageBulletListLabel {
    return buildOudsAnnotatedString<OudsAnnotatedAlertMessageBulletListLabel, OudsAnnotatedAlertMessageBulletListLabel.Builder>(builder)
}

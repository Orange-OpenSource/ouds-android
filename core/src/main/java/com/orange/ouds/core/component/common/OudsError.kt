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

package com.orange.ouds.core.component.common

import com.orange.ouds.core.component.common.text.OudsAnnotatedErrorMessage
import com.orange.ouds.foundation.extensions.orElse

/**
 * An OUDS error that takes accessibility into account and supports rich text messages.
 */
class OudsError private constructor(message: String?, annotatedMessage: OudsAnnotatedErrorMessage?) {

    val message = message.orElse { annotatedMessage?.text }.orEmpty()

    val annotatedMessage = annotatedMessage

    constructor(message: String) : this(message, null)

    constructor(annotatedMessage: OudsAnnotatedErrorMessage) : this(null, annotatedMessage)
}

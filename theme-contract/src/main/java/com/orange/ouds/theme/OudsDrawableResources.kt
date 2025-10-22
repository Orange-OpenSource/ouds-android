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

package com.orange.ouds.theme

import androidx.annotation.DrawableRes
import com.orange.ouds.foundation.InternalOudsApi

@InternalOudsApi
interface OudsDrawableResources {
    val alertImportant: Int
        @DrawableRes get
    val alertInformation: Int
        @DrawableRes get
    val alertSuccess: Int
        @DrawableRes get
    val alertWarningExternalShape: Int
        @DrawableRes get
    val alertWarningInternalShape: Int
        @DrawableRes get
    val checkboxIndeterminate: Int
        @DrawableRes get
    val checkboxSelected: Int
        @DrawableRes get
    val chipTick: Int
        @DrawableRes get
    val linkNext: Int
        @DrawableRes get
    val linkPrevious: Int
        @DrawableRes get
    val radioButtonSelected: Int
        @DrawableRes get
    val switchSelected: Int
        @DrawableRes get
    val tagClose: Int
        @DrawableRes get
}
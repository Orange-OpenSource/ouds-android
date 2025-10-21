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
open class OudsDrawableResources {
    open val bulletListLevel0: Int
        @DrawableRes get() = R.drawable.ic_bullet_list_level0
    open val bulletListLevel1: Int
        @DrawableRes get() = R.drawable.ic_bullet_list_level1
    open val bulletListLevel2: Int
        @DrawableRes get() = R.drawable.ic_bullet_list_level2
    open val checkboxIndeterminate: Int
        @DrawableRes get() = R.drawable.ic_checkbox_indeterminate
    open val checkboxSelected: Int
        @DrawableRes get() = R.drawable.ic_checkbox_selected
    open val chevronLeft: Int
        @DrawableRes get() = R.drawable.ic_chevron_left
    open val delete: Int
        @DrawableRes get() = R.drawable.ic_delete
    open val important: Int
        @DrawableRes get() = R.drawable.ic_important
    open val information: Int
        @DrawableRes get() = R.drawable.ic_information
    open val radioButtonSelected: Int
        @DrawableRes get() = R.drawable.ic_radio_button_selected
    open val switchSelected: Int
        @DrawableRes get() = R.drawable.ic_switch_selected
    open val success: Int
        @DrawableRes get() = R.drawable.ic_success
    open val tick: Int
        @DrawableRes get() = R.drawable.ic_tick
    open val warningExternalShape: Int
        @DrawableRes get() = R.drawable.ic_warning_external_shape
    open val warningInternalShape: Int
        @DrawableRes get() = R.drawable.ic_warning_internal_shape
}
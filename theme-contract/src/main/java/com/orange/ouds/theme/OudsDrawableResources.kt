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
    open val checkboxIndeterminate: Int
        @DrawableRes get() = R.drawable.ic_checkbox_indeterminate
    open val checkboxSelected: Int
        @DrawableRes get() = R.drawable.ic_checkbox_selected
    open val chevronLeft: Int
        @DrawableRes get() = R.drawable.ic_chevron_left
    open val delete: Int
        @DrawableRes get() = R.drawable.ic_delete
    open val radioButtonSelected: Int
        @DrawableRes get() = R.drawable.ic_radio_button_selected
    open val switchSelected: Int
        @DrawableRes get() = R.drawable.ic_switch_selected
    open val tick: Int
        @DrawableRes get() = R.drawable.ic_tick
}
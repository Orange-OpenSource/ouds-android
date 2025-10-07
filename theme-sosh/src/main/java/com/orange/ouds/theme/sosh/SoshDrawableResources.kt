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

package com.orange.ouds.theme.sosh

import androidx.annotation.DrawableRes
import com.orange.ouds.theme.OudsDrawableResources

class SoshDrawableResources : OudsDrawableResources() {
    override val checkboxIndeterminate
        @DrawableRes get() = R.drawable.sosh_checkbox_indeterminate
    override val checkboxSelected: Int
        @DrawableRes get() = R.drawable.sosh_checkbox_selected
    override val chevronLeft: Int
        @DrawableRes get() = R.drawable.sosh_chevron_left
    override val delete: Int
        @DrawableRes get() = R.drawable.sosh_delete
    override val important: Int
        @DrawableRes get() = R.drawable.sosh_important
    override val information: Int
        @DrawableRes get() = R.drawable.sosh_information
    override val radioButtonSelected: Int
        @DrawableRes get() = R.drawable.sosh_radio_button_selected
    override val success: Int
        @DrawableRes get() = R.drawable.sosh_success
    override val switchSelected: Int
        @DrawableRes get() = R.drawable.sosh_switch_selected
    override val tick: Int
        @DrawableRes get() = R.drawable.sosh_tick
    override val warningExternalShape: Int
        @DrawableRes get() = R.drawable.sosh_warning_external_shape
    override val warningInternalShape: Int
        @DrawableRes get() = R.drawable.sosh_warning_internal_shape
}
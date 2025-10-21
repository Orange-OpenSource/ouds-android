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
    open val alertImportant: Int
        @DrawableRes get() = R.drawable.ic_alert_important
    open val alertInformation: Int
        @DrawableRes get() = R.drawable.ic_alert_information
    open val alertSuccess: Int
        @DrawableRes get() = R.drawable.ic_alert_success
    open val alertWarningExternalShape: Int
        @DrawableRes get() = R.drawable.ic_alert_warning_external_shape
    open val alertWarningInternalShape: Int
        @DrawableRes get() = R.drawable.ic_alert_warning_internal_shape
    open val checkboxIndeterminate: Int
        @DrawableRes get() = R.drawable.ic_checkbox_indeterminate
    open val checkboxSelected: Int
        @DrawableRes get() = R.drawable.ic_checkbox_selected
    open val chipTick: Int
        @DrawableRes get() = R.drawable.ic_chip_tick
    open val linkNext: Int
        @DrawableRes get() = R.drawable.ic_link_next
    open val linkPrevious: Int
        @DrawableRes get() = R.drawable.ic_link_previous
    open val radioButtonSelected: Int
        @DrawableRes get() = R.drawable.ic_radio_button_selected
    open val switchSelected: Int
        @DrawableRes get() = R.drawable.ic_switch_selected
    open val tagClose: Int
        @DrawableRes get() = R.drawable.ic_tag_close
}
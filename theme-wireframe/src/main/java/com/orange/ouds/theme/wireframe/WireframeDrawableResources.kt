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

package com.orange.ouds.theme.wireframe

import androidx.annotation.DrawableRes
import com.orange.ouds.theme.OudsDrawableResources

class WireframeDrawableResources : OudsDrawableResources() {
    override val alertImportant: Int
        @DrawableRes get() = R.drawable.wireframe_alert_important
    override val alertInformation: Int
        @DrawableRes get() = R.drawable.wireframe_alert_information
    override val alertSuccess: Int
        @DrawableRes get() = R.drawable.wireframe_alert_success
    override val alertWarningExternalShape: Int
        @DrawableRes get() = R.drawable.wireframe_alert_warning_external_shape
    override val alertWarningInternalShape: Int
        @DrawableRes get() = R.drawable.wireframe_alert_warning_internal_shape
    override val checkboxIndeterminate: Int
        @DrawableRes get() = R.drawable.wireframe_checkbox_indeterminate
    override val checkboxSelected: Int
        @DrawableRes get() = R.drawable.wireframe_checkbox_selected
    override val chipTick: Int
        @DrawableRes get() = R.drawable.wireframe_chip_tick
    override val linkNext: Int
        @DrawableRes get() = R.drawable.wireframe_link_next
    override val linkPrevious: Int
        @DrawableRes get() = R.drawable.wireframe_link_previous
    override val radioButtonSelected: Int
        @DrawableRes get() = R.drawable.wireframe_radio_button_selected
    override val switchSelected: Int
        @DrawableRes get() = R.drawable.wireframe_switch_selected
    override val tagClose: Int
        @DrawableRes get() = R.drawable.wireframe_tag_close
}

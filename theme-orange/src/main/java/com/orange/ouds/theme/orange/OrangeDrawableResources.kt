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

package com.orange.ouds.theme.orange

import com.orange.ouds.theme.OudsDrawableResources

internal class OrangeDrawableResources : OudsDrawableResources {
    override val component = object : OudsDrawableResources.Component() {
        override val alert = object : Alert() {
            override val importantFill = R.drawable.ic_orange_component_alert_important_fill
            override val infoFill = R.drawable.ic_orange_component_alert_info_fill
            override val tickConfirmationFill = R.drawable.ic_orange_component_alert_tick_confirmation_fill
            override val warningExternalShape = R.drawable.ic_orange_component_alert_warning_external_shape
            override val warningInternalShape = R.drawable.ic_orange_component_alert_warning_internal_shape
        }
        override val checkbox = object : Checkbox() {
            override val selected = R.drawable.ic_orange_component_checkbox_selected
            override val undetermined = R.drawable.ic_orange_component_checkbox_undetermined
        }
        override val chip = object : Chip() {
            override val tick = R.drawable.ic_orange_component_chip_tick
        }
        override val link = object : Link() {
            override val next = R.drawable.ic_orange_component_link_next
            override val previous = R.drawable.ic_orange_component_link_previous
        }
        override val radioButton = object : RadioButton() {
            override val selected = R.drawable.ic_orange_component_radio_button_selected
        }
        override val switch = object : Switch() {
            override val selected = R.drawable.ic_orange_component_switch_selected_switch
        }
        override val tag = object : Tag() {
            override val close = R.drawable.ic_orange_component_tag_close
        }
    }
}
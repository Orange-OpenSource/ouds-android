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

import com.orange.ouds.theme.OudsDrawableResources

internal class WireframeDrawableResources : OudsDrawableResources {
    override val component = Component()

    class Component : OudsDrawableResources.Component {
        override val alert = Alert()
        override val checkbox = Checkbox()
        override val chip = Chip()
        override val link = Link()
        override val radioButton = RadioButton()
        override val switch = Switch()
        override val tag = Tag()
    }

    class Alert : OudsDrawableResources.Component.Alert {
        override val importantFill = R.drawable.ic_wireframe_component_alert_important_fill
        override val infoFill = R.drawable.ic_wireframe_component_alert_info_fill
        override val tickConfirmationFill = R.drawable.ic_wireframe_component_alert_tick_confirmation_fill
        override val warningExternalShape = R.drawable.ic_wireframe_component_alert_warning_external_shape
        override val warningInternalShape = R.drawable.ic_wireframe_component_alert_warning_internal_shape
    }

    class Checkbox : OudsDrawableResources.Component.Checkbox {
        override val selected = R.drawable.ic_wireframe_component_checkbox_selected
        override val undetermined = R.drawable.ic_wireframe_component_checkbox_undetermined
    }

    class Chip : OudsDrawableResources.Component.Chip {
        override val tick = R.drawable.ic_wireframe_component_chip_tick
    }

    class Link : OudsDrawableResources.Component.Link {
        override val next = R.drawable.ic_wireframe_component_link_next
        override val previous = R.drawable.ic_wireframe_component_link_previous
    }

    class RadioButton : OudsDrawableResources.Component.RadioButton {
        override val selected = R.drawable.ic_wireframe_component_radio_button_selected
    }

    class Switch : OudsDrawableResources.Component.Switch {
        override val selected = R.drawable.ic_wireframe_component_switch_selected_switch
    }

    class Tag : OudsDrawableResources.Component.Tag {
        override val close = R.drawable.ic_wireframe_component_tag_close
    }
}

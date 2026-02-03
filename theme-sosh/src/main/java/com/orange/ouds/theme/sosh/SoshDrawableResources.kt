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

import com.orange.ouds.theme.OudsDrawableResources

internal class SoshDrawableResources : OudsDrawableResources {
    override val communication = Communication()
    override val component = Component()
    override val functional = Functional()

    class Communication : OudsDrawableResources.Communication {
        override val accessibility = Accessibility()
        override val securityAndSafety = SecurityAndSafety()

        class Accessibility : OudsDrawableResources.Communication.Accessibility {
            override val vision = R.drawable.ic_sosh_communication_accessibility_vision
        }

        class SecurityAndSafety : OudsDrawableResources.Communication.SecurityAndSafety {
            override val lock = R.drawable.ic_sosh_communication_security_and_safety_lock
        }
    }

    class Component : OudsDrawableResources.Component {
        override val alert = Alert()
        override val bulletList = BulletList()
        override val button = Button()
        override val checkbox = Checkbox()
        override val chip = Chip()
        override val link = Link()
        override val radioButton = RadioButton()
        override val switch = Switch()
        override val tag = Tag()

        class Alert : OudsDrawableResources.Component.Alert {
            override val importantFill = R.drawable.ic_sosh_component_alert_important_fill
            override val infoFill = R.drawable.ic_sosh_component_alert_info_fill
            override val tickConfirmationFill = R.drawable.ic_sosh_component_alert_tick_confirmation_fill
            override val warningExternalShape = R.drawable.ic_sosh_component_alert_warning_external_shape
            override val warningInternalShape = R.drawable.ic_sosh_component_alert_warning_internal_shape
        }

        class BulletList : OudsDrawableResources.Component.BulletList {
            override val level0 = R.drawable.ic_sosh_component_bullet_list_level0
            override val level1 = R.drawable.ic_sosh_component_bullet_list_level1
            override val level2 = R.drawable.ic_sosh_component_bullet_list_level2
            override val tick = R.drawable.ic_sosh_component_bullet_list_tick
        }

        class Button : OudsDrawableResources.Component.Button {
            override val expurge = R.drawable.ic_sosh_component_button_expurge
        }

        class Checkbox : OudsDrawableResources.Component.Checkbox {
            override val selected = R.drawable.ic_sosh_component_checkbox_selected
            override val undetermined = R.drawable.ic_sosh_component_checkbox_undetermined
        }

        class Chip : OudsDrawableResources.Component.Chip {
            override val tick = R.drawable.ic_sosh_component_chip_tick
        }

        class Link : OudsDrawableResources.Component.Link {
            override val next = R.drawable.ic_sosh_component_link_next
            override val previous = R.drawable.ic_sosh_component_link_previous
        }

        class RadioButton : OudsDrawableResources.Component.RadioButton {
            override val selected = R.drawable.ic_sosh_component_radio_button_selected
        }

        class Switch : OudsDrawableResources.Component.Switch {
            override val selected = R.drawable.ic_sosh_component_switch_selected_switch
        }

        class Tag : OudsDrawableResources.Component.Tag {
            override val close = R.drawable.ic_sosh_component_tag_close
        }
    }

    class Functional : OudsDrawableResources.Functional {
        override val actions = Actions()
        override val navigation = Navigation()
        override val settingsAndTools = SettingsAndTools()

        class Actions : OudsDrawableResources.Functional.Actions {
            override val delete = R.drawable.ic_sosh_functional_actions_delete
        }

        class Navigation : OudsDrawableResources.Functional.Navigation {
            override val formChevronLeft = R.drawable.ic_sosh_functional_navigation_form_chevron_left
            override val menu = R.drawable.ic_sosh_functional_navigation_menu
        }

        class SettingsAndTools : OudsDrawableResources.Functional.SettingsAndTools {
            override val hide = R.drawable.ic_sosh_functional_settings_and_tools_hide
        }
    }
}

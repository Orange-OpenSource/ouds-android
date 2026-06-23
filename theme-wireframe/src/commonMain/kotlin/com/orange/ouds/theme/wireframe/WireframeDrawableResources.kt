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
import ouds_android.theme_wireframe.generated.resources.Res
import ouds_android.theme_wireframe.generated.resources.ic_wireframe_communication_accessibility_vision
import ouds_android.theme_wireframe.generated.resources.ic_wireframe_communication_security_and_safety_lock_closed
import ouds_android.theme_wireframe.generated.resources.ic_wireframe_component_alert_important_fill
import ouds_android.theme_wireframe.generated.resources.ic_wireframe_component_alert_info_fill
import ouds_android.theme_wireframe.generated.resources.ic_wireframe_component_alert_tick_confirmation_fill
import ouds_android.theme_wireframe.generated.resources.ic_wireframe_component_alert_warning_external_shape
import ouds_android.theme_wireframe.generated.resources.ic_wireframe_component_alert_warning_internal_shape
import ouds_android.theme_wireframe.generated.resources.ic_wireframe_component_badge_icon_error_fill
import ouds_android.theme_wireframe.generated.resources.ic_wireframe_component_badge_icon_info_fill
import ouds_android.theme_wireframe.generated.resources.ic_wireframe_component_badge_icon_tick_confirmation_fill
import ouds_android.theme_wireframe.generated.resources.ic_wireframe_component_badge_icon_warning_external_shape
import ouds_android.theme_wireframe.generated.resources.ic_wireframe_component_badge_icon_warning_internal_shape
import ouds_android.theme_wireframe.generated.resources.ic_wireframe_component_bullet_list_level0
import ouds_android.theme_wireframe.generated.resources.ic_wireframe_component_bullet_list_level1
import ouds_android.theme_wireframe.generated.resources.ic_wireframe_component_bullet_list_level2
import ouds_android.theme_wireframe.generated.resources.ic_wireframe_component_bullet_list_tick
import ouds_android.theme_wireframe.generated.resources.ic_wireframe_component_button_expurge
import ouds_android.theme_wireframe.generated.resources.ic_wireframe_component_button_next
import ouds_android.theme_wireframe.generated.resources.ic_wireframe_component_button_previous
import ouds_android.theme_wireframe.generated.resources.ic_wireframe_component_checkbox_selected
import ouds_android.theme_wireframe.generated.resources.ic_wireframe_component_checkbox_undetermined
import ouds_android.theme_wireframe.generated.resources.ic_wireframe_component_chip_tick
import ouds_android.theme_wireframe.generated.resources.ic_wireframe_component_link_next
import ouds_android.theme_wireframe.generated.resources.ic_wireframe_component_link_previous
import ouds_android.theme_wireframe.generated.resources.ic_wireframe_component_radio_button_selected
import ouds_android.theme_wireframe.generated.resources.ic_wireframe_component_switch_selected_switch
import ouds_android.theme_wireframe.generated.resources.ic_wireframe_component_tag_close
import ouds_android.theme_wireframe.generated.resources.ic_wireframe_functional_actions_delete_cross_round
import ouds_android.theme_wireframe.generated.resources.ic_wireframe_functional_navigation_form_chevron_left
import ouds_android.theme_wireframe.generated.resources.ic_wireframe_functional_navigation_menu_grid_ui_round
import ouds_android.theme_wireframe.generated.resources.ic_wireframe_functional_settings_and_tools_accessibility_hide
import ouds_android.theme_wireframe.generated.resources.ic_wireframe_functional_social_and_engagement_heart_recommend

internal class WireframeDrawableResources : OudsDrawableResources {
    override val communication = Communication()
    override val component = Component()
    override val functional = Functional()

    class Communication : OudsDrawableResources.Communication {
        override val accessibility = Accessibility()
        override val securityAndSafety = SecurityAndSafety()

        class Accessibility : OudsDrawableResources.Communication.Accessibility {
            override val vision = Res.drawable.ic_wireframe_communication_accessibility_vision
        }

        class SecurityAndSafety : OudsDrawableResources.Communication.SecurityAndSafety {
            override val lockClosed = Res.drawable.ic_wireframe_communication_security_and_safety_lock_closed
        }
    }

    class Component : OudsDrawableResources.Component {
        override val alert = Alert()
        override val badgeIcon = BadgeIcon()
        override val bulletList = BulletList()
        override val button = Button()
        override val checkbox = Checkbox()
        override val chip = Chip()
        override val link = Link()
        override val radioButton = RadioButton()
        override val switch = Switch()
        override val tag = Tag()

        class Alert : OudsDrawableResources.Component.Alert {
            override val importantFill = Res.drawable.ic_wireframe_component_alert_important_fill
            override val infoFill = Res.drawable.ic_wireframe_component_alert_info_fill
            override val tickConfirmationFill = Res.drawable.ic_wireframe_component_alert_tick_confirmation_fill
            override val warningExternalShape = Res.drawable.ic_wireframe_component_alert_warning_external_shape
            override val warningInternalShape = Res.drawable.ic_wireframe_component_alert_warning_internal_shape
        }

        class BadgeIcon : OudsDrawableResources.Component.BadgeIcon {
            override val errorFill = Res.drawable.ic_wireframe_component_badge_icon_error_fill
            override val infoFill = Res.drawable.ic_wireframe_component_badge_icon_info_fill
            override val tickConfirmationFill = Res.drawable.ic_wireframe_component_badge_icon_tick_confirmation_fill
            override val warningExternalShape = Res.drawable.ic_wireframe_component_badge_icon_warning_external_shape
            override val warningInternalShape = Res.drawable.ic_wireframe_component_badge_icon_warning_internal_shape
        }

        class BulletList : OudsDrawableResources.Component.BulletList {
            override val level0 = Res.drawable.ic_wireframe_component_bullet_list_level0
            override val level1 = Res.drawable.ic_wireframe_component_bullet_list_level1
            override val level2 = Res.drawable.ic_wireframe_component_bullet_list_level2
            override val tick = Res.drawable.ic_wireframe_component_bullet_list_tick
        }

        class Button : OudsDrawableResources.Component.Button {
            override val expurge = Res.drawable.ic_wireframe_component_button_expurge
            override val next = Res.drawable.ic_wireframe_component_button_next
            override val previous = Res.drawable.ic_wireframe_component_button_previous
        }

        class Checkbox : OudsDrawableResources.Component.Checkbox {
            override val selected = Res.drawable.ic_wireframe_component_checkbox_selected
            override val undetermined = Res.drawable.ic_wireframe_component_checkbox_undetermined
        }

        class Chip : OudsDrawableResources.Component.Chip {
            override val tick = Res.drawable.ic_wireframe_component_chip_tick
        }

        class Link : OudsDrawableResources.Component.Link {
            override val next = Res.drawable.ic_wireframe_component_link_next
            override val previous = Res.drawable.ic_wireframe_component_link_previous
        }

        class RadioButton : OudsDrawableResources.Component.RadioButton {
            override val selected = Res.drawable.ic_wireframe_component_radio_button_selected
        }

        class Switch : OudsDrawableResources.Component.Switch {
            override val selected = Res.drawable.ic_wireframe_component_switch_selected_switch
        }

        class Tag : OudsDrawableResources.Component.Tag {
            override val close = Res.drawable.ic_wireframe_component_tag_close
        }
    }

    class Functional : OudsDrawableResources.Functional {
        override val actions = Actions()
        override val navigation = Navigation()
        override val settingsAndTools = SettingsAndTools()
        override val socialAndEngagement = SocialAndEngagement()

        class Actions : OudsDrawableResources.Functional.Actions {
            override val deleteCrossRound = Res.drawable.ic_wireframe_functional_actions_delete_cross_round
        }

        class Navigation : OudsDrawableResources.Functional.Navigation {
            override val formChevronLeft = Res.drawable.ic_wireframe_functional_navigation_form_chevron_left
            override val menuGridUiRound = Res.drawable.ic_wireframe_functional_navigation_menu_grid_ui_round
        }

        class SettingsAndTools : OudsDrawableResources.Functional.SettingsAndTools {
            override val accessibilityHide = Res.drawable.ic_wireframe_functional_settings_and_tools_accessibility_hide
        }

        class SocialAndEngagement : OudsDrawableResources.Functional.SocialAndEngagement {
            override val heartRecommend = Res.drawable.ic_wireframe_functional_social_and_engagement_heart_recommend
        }
    }
}

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

import androidx.annotation.DrawableRes
import com.orange.ouds.foundation.RestrictedOudsApi
import com.orange.ouds.theme.OudsDrawableResources

/**
 * @suppress
 */
@RestrictedOudsApi
data class OrangeDrawableResources(
    override val communication: OudsDrawableResources.Communication = Communication(),
    override val component: OudsDrawableResources.Component = Component(),
    override val functional: OudsDrawableResources.Functional = Functional()
) : OudsDrawableResources {

    data class Communication(
        override val accessibility: OudsDrawableResources.Communication.Accessibility = Accessibility(),
        override val securityAndSafety: OudsDrawableResources.Communication.SecurityAndSafety = SecurityAndSafety()
    ) : OudsDrawableResources.Communication {

        data class Accessibility(
            @DrawableRes override val vision: Int = R.drawable.ic_orange_communication_accessibility_vision
        ) : OudsDrawableResources.Communication.Accessibility

        data class SecurityAndSafety(
            @DrawableRes override val lockClosed: Int = R.drawable.ic_orange_communication_security_and_safety_lock_closed
        ) : OudsDrawableResources.Communication.SecurityAndSafety
    }

    data class Component(
        override val alert: OudsDrawableResources.Component.Alert = Alert(),
        override val badgeIcon: OudsDrawableResources.Component.BadgeIcon = BadgeIcon(),
        override val bulletList: OudsDrawableResources.Component.BulletList = BulletList(),
        override val button: OudsDrawableResources.Component.Button = Button(),
        override val checkbox: OudsDrawableResources.Component.Checkbox = Checkbox(),
        override val chip: OudsDrawableResources.Component.Chip = Chip(),
        override val link: OudsDrawableResources.Component.Link = Link(),
        override val listItem: OudsDrawableResources.Component.ListItem = ListItem(),
        override val radioButton: OudsDrawableResources.Component.RadioButton = RadioButton(),
        override val switch: OudsDrawableResources.Component.Switch = Switch(),
        override val tag: OudsDrawableResources.Component.Tag = Tag()
    ) : OudsDrawableResources.Component {

        data class Alert(
            @DrawableRes override val importantFill: Int = R.drawable.ic_orange_component_alert_important_fill,
            @DrawableRes override val infoFill: Int = R.drawable.ic_orange_component_alert_info_fill,
            @DrawableRes override val tickConfirmationFill: Int = R.drawable.ic_orange_component_alert_tick_confirmation_fill,
            @DrawableRes override val warningExternalShape: Int = R.drawable.ic_orange_component_alert_warning_external_shape,
            @DrawableRes override val warningInternalShape: Int = R.drawable.ic_orange_component_alert_warning_internal_shape
        ) : OudsDrawableResources.Component.Alert

        data class BadgeIcon(
            @DrawableRes override val errorFill: Int = R.drawable.ic_orange_component_badge_icon_error_fill,
            @DrawableRes override val infoFill: Int = R.drawable.ic_orange_component_badge_icon_info_fill,
            @DrawableRes override val tickConfirmationFill: Int = R.drawable.ic_orange_component_badge_icon_tick_confirmation_fill,
            @DrawableRes override val warningExternalShape: Int = R.drawable.ic_orange_component_badge_icon_warning_external_shape,
            @DrawableRes override val warningInternalShape: Int = R.drawable.ic_orange_component_badge_icon_warning_internal_shape
        ) : OudsDrawableResources.Component.BadgeIcon

        data class BulletList(
            @DrawableRes override val level0: Int = R.drawable.ic_orange_component_bullet_list_level0,
            @DrawableRes override val level1: Int = R.drawable.ic_orange_component_bullet_list_level1,
            @DrawableRes override val level2: Int = R.drawable.ic_orange_component_bullet_list_level2,
            @DrawableRes override val tick: Int = R.drawable.ic_orange_component_bullet_list_tick
        ) : OudsDrawableResources.Component.BulletList

        data class Button(
            @DrawableRes override val expurge: Int = R.drawable.ic_orange_component_button_expurge,
            @DrawableRes override val next: Int = R.drawable.ic_orange_component_button_next,
            @DrawableRes override val previous: Int = R.drawable.ic_orange_component_button_previous
        ) : OudsDrawableResources.Component.Button

        data class Checkbox(
            @DrawableRes override val selected: Int = R.drawable.ic_orange_component_checkbox_selected,
            @DrawableRes override val undetermined: Int = R.drawable.ic_orange_component_checkbox_undetermined
        ) : OudsDrawableResources.Component.Checkbox

        data class Chip(
            @DrawableRes override val tick: Int = R.drawable.ic_orange_component_chip_tick
        ) : OudsDrawableResources.Component.Chip

        data class Link(
            @DrawableRes override val externalLink: Int = R.drawable.ic_orange_component_link_external_link,
            @DrawableRes override val next: Int = R.drawable.ic_orange_component_link_next,
            @DrawableRes override val previous: Int = R.drawable.ic_orange_component_link_previous
        ) : OudsDrawableResources.Component.Link

        data class ListItem(
            @DrawableRes override val next: Int = R.drawable.ic_orange_component_list_item_next,
            @DrawableRes override val previous: Int = R.drawable.ic_orange_component_list_item_previous
        ) : OudsDrawableResources.Component.ListItem

        data class RadioButton(
            @DrawableRes override val selected: Int = R.drawable.ic_orange_component_radio_button_selected
        ) : OudsDrawableResources.Component.RadioButton

        data class Switch(
            @DrawableRes override val selected: Int = R.drawable.ic_orange_component_switch_selected
        ) : OudsDrawableResources.Component.Switch

        data class Tag(
            @DrawableRes override val close: Int = R.drawable.ic_orange_component_tag_close
        ) : OudsDrawableResources.Component.Tag
    }

    data class Functional(
        override val actions: OudsDrawableResources.Functional.Actions = Actions(),
        override val navigation: OudsDrawableResources.Functional.Navigation = Navigation(),
        override val settingsAndTools: OudsDrawableResources.Functional.SettingsAndTools = SettingsAndTools(),
        override val socialAndEngagement: OudsDrawableResources.Functional.SocialAndEngagement = SocialAndEngagement()
    ) : OudsDrawableResources.Functional {

        data class Actions(
            @DrawableRes override val deleteCrossRound: Int = R.drawable.ic_orange_functional_actions_delete_cross_round,
            @DrawableRes override val externalLink: Int = R.drawable.ic_orange_functional_actions_external_link
        ) : OudsDrawableResources.Functional.Actions

        data class Navigation(
            @DrawableRes override val formChevronLeft: Int = R.drawable.ic_orange_functional_navigation_form_chevron_left,
            @DrawableRes override val menuGridUiRound: Int = R.drawable.ic_orange_functional_navigation_menu_grid_ui_round
        ) : OudsDrawableResources.Functional.Navigation

        data class SettingsAndTools(
            @DrawableRes override val accessibilityHide: Int = R.drawable.ic_orange_functional_settings_and_tools_accessibility_hide
        ) : OudsDrawableResources.Functional.SettingsAndTools

        data class SocialAndEngagement(
            @DrawableRes override val heartRecommend: Int = R.drawable.ic_orange_functional_social_and_engagement_heart_recommend
        ) : OudsDrawableResources.Functional.SocialAndEngagement
    }
}

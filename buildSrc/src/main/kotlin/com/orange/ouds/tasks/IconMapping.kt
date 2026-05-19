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

package com.orange.ouds.tasks

/**
 * Represents a mapping between an OudsDrawableResources interface property
 * and its corresponding SVG file in the OUDS icons zip.
 *
 * @property interfacePath The dot-separated path in OudsDrawableResources (e.g., "communication.accessibility.vision")
 * @property svgPath The relative path to the SVG file within a theme folder (e.g., "communication/accessibility/accessibility-vision.svg")
 * @property resourceBaseName The base name for the Android resource file (e.g., "communication_accessibility_vision")
 * @property autoMirrored Whether the icon should have android:autoMirrored="true" for RTL support
 */
data class IconDefinition(
    val interfacePath: String,
    val svgPath: String,
    val resourceBaseName: String,
    val autoMirrored: Boolean = false
)

/**
 * Database of all icon mappings from OudsDrawableResources interface to SVG files.
 * This object maintains the complete mapping of all icons defined in the interface.
 */
object IconMappings {

    /**
     * All icon definitions from OudsDrawableResources interface.
     * Each icon maps to a specific SVG file path within the theme folders.
     */
    val allIcons: List<IconDefinition> = listOf(
        // Communication - Accessibility
        IconDefinition(
            interfacePath = "communication.accessibility.vision",
            svgPath = "communication/accessibility/accessibility-vision.svg",
            resourceBaseName = "communication_accessibility_vision"
        ),

        // Communication - Security and Safety
        IconDefinition(
            interfacePath = "communication.securityAndSafety.lock",
            svgPath = "communication/security-and-safety/lock.svg",
            resourceBaseName = "communication_security_and_safety_lock"
        ),

        // Component - Alert
        IconDefinition(
            interfacePath = "component.alert.importantFill",
            svgPath = "Component/alert/important-fill.svg",
            resourceBaseName = "component_alert_important_fill"
        ),
        IconDefinition(
            interfacePath = "component.alert.infoFill",
            svgPath = "Component/alert/info-fill.svg",
            resourceBaseName = "component_alert_info_fill"
        ),
        IconDefinition(
            interfacePath = "component.alert.tickConfirmationFill",
            svgPath = "Component/alert/tick-confirmation-fill.svg",
            resourceBaseName = "component_alert_tick_confirmation_fill"
        ),
        IconDefinition(
            interfacePath = "component.alert.warningExternalShape",
            svgPath = "Component/alert/warning-external-shape.svg",
            resourceBaseName = "component_alert_warning_external_shape"
        ),
        IconDefinition(
            interfacePath = "component.alert.warningInternalShape",
            svgPath = "Component/alert/warning-internal-shape.svg",
            resourceBaseName = "component_alert_warning_internal_shape"
        ),

        // Component - BadgeIcon
        IconDefinition(
            interfacePath = "component.badgeIcon.errorFill",
            svgPath = "Component/badge-icon/error-fill.svg",
            resourceBaseName = "component_badge_icon_error_fill"
        ),
        IconDefinition(
            interfacePath = "component.badgeIcon.infoFill",
            svgPath = "Component/badge-icon/info-fill.svg",
            resourceBaseName = "component_badge_icon_info_fill"
        ),
        IconDefinition(
            interfacePath = "component.badgeIcon.tickConfirmationFill",
            svgPath = "Component/badge-icon/tick-confirmation-fill.svg",
            resourceBaseName = "component_badge_icon_tick_confirmation_fill"
        ),
        IconDefinition(
            interfacePath = "component.badgeIcon.warningExternalShape",
            svgPath = "Component/badge-icon/warning-external-shape.svg",
            resourceBaseName = "component_badge_icon_warning_external_shape"
        ),
        IconDefinition(
            interfacePath = "component.badgeIcon.warningInternalShape",
            svgPath = "Component/badge-icon/warning-internal-shape.svg",
            resourceBaseName = "component_badge_icon_warning_internal_shape"
        ),

        // Component - BulletList
        IconDefinition(
            interfacePath = "component.bulletList.level0",
            svgPath = "Component/bullet-list/bullet-level-0.svg",
            resourceBaseName = "component_bullet_list_level0"
        ),
        IconDefinition(
            interfacePath = "component.bulletList.level1",
            svgPath = "Component/bullet-list/bullet-level-1.svg",
            resourceBaseName = "component_bullet_list_level1"
        ),
        IconDefinition(
            interfacePath = "component.bulletList.level2",
            svgPath = "Component/bullet-list/bullet-level-2.svg",
            resourceBaseName = "component_bullet_list_level2"
        ),
        IconDefinition(
            interfacePath = "component.bulletList.tick",
            svgPath = "Component/bullet-list/bullet-tick.svg",
            resourceBaseName = "component_bullet_list_tick"
        ),

        // Component - Button
        IconDefinition(
            interfacePath = "component.button.expurge",
            svgPath = "Component/button/expurge.svg",
            resourceBaseName = "component_button_expurge"
        ),

        // Component - Checkbox
        IconDefinition(
            interfacePath = "component.checkbox.selected",
            svgPath = "Component/checkbox/checkbox-selected.svg",
            resourceBaseName = "component_checkbox_selected"
        ),
        IconDefinition(
            interfacePath = "component.checkbox.undetermined",
            svgPath = "Component/checkbox/checkbox-undetermined.svg",
            resourceBaseName = "component_checkbox_undetermined"
        ),

        // Component - Chip
        IconDefinition(
            interfacePath = "component.chip.tick",
            svgPath = "Component/chip/tick.svg",
            resourceBaseName = "component_chip_tick"
        ),

        // Component - Link
        IconDefinition(
            interfacePath = "component.link.next",
            svgPath = "Component/link/next.svg",
            resourceBaseName = "component_link_next",
            autoMirrored = true
        ),
        IconDefinition(
            interfacePath = "component.link.previous",
            svgPath = "Component/link/previous.svg",
            resourceBaseName = "component_link_previous",
            autoMirrored = true
        ),

        // Component - RadioButton
        IconDefinition(
            interfacePath = "component.radioButton.selected",
            svgPath = "Component/radio-button/radio-button-selected.svg",
            resourceBaseName = "component_radio_button_selected"
        ),

        // Component - Switch
        IconDefinition(
            interfacePath = "component.switch.selected",
            svgPath = "Component/switch/selected-switch.svg",
            resourceBaseName = "component_switch_selected_switch"
        ),

        // Component - Tag
        IconDefinition(
            interfacePath = "component.tag.close",
            svgPath = "Component/tag/close.svg",
            resourceBaseName = "component_tag_close"
        ),

        // Functional - Actions
        IconDefinition(
            interfacePath = "functional.actions.delete",
            svgPath = "functional/actions/delete.svg",
            resourceBaseName = "functional_actions_delete"
        ),

        // Functional - Navigation
        IconDefinition(
            interfacePath = "functional.navigation.formChevronLeft",
            svgPath = "functional/navigation/form-chevron-left.svg",
            resourceBaseName = "functional_navigation_form_chevron_left",
            autoMirrored = true
        ),
        IconDefinition(
            interfacePath = "functional.navigation.menu",
            svgPath = "functional/navigation/menu.svg",
            resourceBaseName = "functional_navigation_menu"
        ),

        // Functional - Settings and Tools
        IconDefinition(
            interfacePath = "functional.settingsAndTools.hide",
            svgPath = "functional/settings-and-tools/hide.svg",
            resourceBaseName = "functional_settings_and_tools_hide"
        ),

        // Functional - Social and Engagement
        IconDefinition(
            interfacePath = "functional.socialAndEngagement.heartEmpty",
            svgPath = "functional/social-and-engagement/heart-empty.svg",
            resourceBaseName = "functional_social_and_engagement_heart_empty"
        )
    )

    /**
     * Supported themes in OUDS icons zip file.
     */
    val supportedThemes = listOf("orange", "sosh", "wireframe")
}

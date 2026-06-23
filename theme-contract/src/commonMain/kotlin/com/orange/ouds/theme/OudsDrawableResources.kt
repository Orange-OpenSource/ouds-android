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

import com.orange.ouds.foundation.InternalOudsApi
import org.jetbrains.compose.resources.DrawableResource

/**
 * @suppress
 */
@InternalOudsApi
interface OudsDrawableResources {
    val communication: Communication
    val component: Component
    val functional: Functional

    interface Communication {
        val accessibility: Accessibility
        val securityAndSafety: SecurityAndSafety

        interface Accessibility {
            val vision: DrawableResource
        }

        interface SecurityAndSafety {
            val lockClosed: DrawableResource
        }
    }

    interface Component {
        val alert: Alert
        val badgeIcon: BadgeIcon
        val bulletList: BulletList
        val button: Button
        val checkbox: Checkbox
        val chip: Chip
        val link: Link
        val radioButton: RadioButton
        val switch: Switch
        val tag: Tag

        interface Alert {
            val importantFill: DrawableResource

            val infoFill: DrawableResource
            
            val tickConfirmationFill: DrawableResource

            val warningExternalShape: DrawableResource

            val warningInternalShape: DrawableResource
        }

        interface BadgeIcon {
            val errorFill: DrawableResource

            val infoFill: DrawableResource

            val tickConfirmationFill: DrawableResource

            val warningExternalShape: DrawableResource

            val warningInternalShape: DrawableResource
        }

        interface BulletList {
            val level0: DrawableResource

            val level1: DrawableResource

            val level2: DrawableResource

            val tick: DrawableResource
        }

        interface Button {
            val expurge: DrawableResource

            val next: DrawableResource

            val previous: DrawableResource
        }

        interface Checkbox {
            val selected: DrawableResource

            val undetermined: DrawableResource
        }

        interface Chip {
            val tick: DrawableResource
        }

        interface Link {
            val next: DrawableResource

            val previous: DrawableResource
        }

        interface RadioButton {
            val selected: DrawableResource
        }

        interface Switch {
            val selected: DrawableResource
        }

        interface Tag {
            val close: DrawableResource
        }
    }

    interface Functional {
        val actions: Actions
        val navigation: Navigation
        val settingsAndTools: SettingsAndTools
        val socialAndEngagement: SocialAndEngagement

        interface Actions {
            val deleteCrossRound: DrawableResource
        }

        interface Navigation {
            val formChevronLeft: DrawableResource

            val menuGridUiRound: DrawableResource
        }

        interface SettingsAndTools {
            val accessibilityHide: DrawableResource
        }

        interface SocialAndEngagement {
            val heartRecommend: DrawableResource
        }
    }
}
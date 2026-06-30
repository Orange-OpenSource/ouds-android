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
            @get:DrawableRes
            val vision: Int
        }

        interface SecurityAndSafety {
            @get:DrawableRes
            val lockClosed: Int
        }
    }

    interface Component {
        val alert: Alert
        val badgeIcon: BadgeIcon
        val bulletList: BulletList
        val button: Button
        val checkbox: Checkbox
        val chip: Chip
        val controlItem: ControlItem
        val link: Link
        val radioButton: RadioButton
        val switch: Switch
        val tag: Tag

        interface Alert {
            @get:DrawableRes
            val importantFill: Int

            @get:DrawableRes
            val infoFill: Int

            @get:DrawableRes
            val tickConfirmationFill: Int

            @get:DrawableRes
            val warningExternalShape: Int

            @get:DrawableRes
            val warningInternalShape: Int
        }

        interface BadgeIcon {
            @get:DrawableRes
            val errorFill: Int

            @get:DrawableRes
            val infoFill: Int

            @get:DrawableRes
            val tickConfirmationFill: Int

            @get:DrawableRes
            val warningExternalShape: Int

            @get:DrawableRes
            val warningInternalShape: Int
        }

        interface BulletList {
            @get:DrawableRes
            val level0: Int

            @get:DrawableRes
            val level1: Int

            @get:DrawableRes
            val level2: Int

            @get:DrawableRes
            val tick: Int
        }

        interface Button {
            @get:DrawableRes
            val expurge: Int

            @get:DrawableRes
            val next: Int

            @get:DrawableRes
            val previous: Int
        }

        interface Checkbox {
            @get:DrawableRes
            val selected: Int

            @get:DrawableRes
            val undetermined: Int
        }

        interface Chip {
            @get:DrawableRes
            val tick: Int
        }

        interface ControlItem {
            @get:DrawableRes
            val next: Int

            @get:DrawableRes
            val previous: Int
        }

        interface Link {
            @get:DrawableRes
            val next: Int

            @get:DrawableRes
            val previous: Int
        }

        interface RadioButton {
            @get:DrawableRes
            val selected: Int
        }

        interface Switch {
            @get:DrawableRes
            val selected: Int
        }

        interface Tag {
            @get:DrawableRes
            val close: Int
        }
    }

    interface Functional {
        val actions: Actions
        val navigation: Navigation
        val settingsAndTools: SettingsAndTools
        val socialAndEngagement: SocialAndEngagement

        interface Actions {
            @get:DrawableRes
            val deleteCrossRound: Int

            @get:DrawableRes
            val externalLink: Int
        }

        interface Navigation {
            @get:DrawableRes
            val formChevronLeft: Int

            @get:DrawableRes
            val menuGridUiRound: Int
        }

        interface SettingsAndTools {
            @get:DrawableRes
            val accessibilityHide: Int
        }

        interface SocialAndEngagement {
            @get:DrawableRes
            val heartRecommend: Int
        }
    }
}
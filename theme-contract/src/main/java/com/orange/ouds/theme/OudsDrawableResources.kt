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
 * Version of the icons pack
 * @suppress
 */
@InternalOudsApi
const val OudsIconsPackVersion = "2.3"

/**
 * @suppress
 */
@InternalOudsApi
interface OudsDrawableResources {
    val communication: Communication
    val component: Component
    val functional: Functional

    @InternalOudsApi
    interface Communication {
        val accessibility: Accessibility
        val securityAndSafety: SecurityAndSafety

        @InternalOudsApi
        interface Accessibility {
            @get:DrawableRes
            val vision: Int
        }

        @InternalOudsApi
        interface SecurityAndSafety {
            @get:DrawableRes
            val lockClosed: Int
        }
    }

    @InternalOudsApi
    interface Component {
        val alert: Alert
        val badgeIcon: BadgeIcon
        val bulletList: BulletList
        val button: Button
        val checkbox: Checkbox
        val chip: Chip
        val link: Link
        val listItem: ListItem
        val radioButton: RadioButton
        val switch: Switch
        val tag: Tag

        @InternalOudsApi
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

        @InternalOudsApi
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

        @InternalOudsApi
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

        @InternalOudsApi
        interface Button {
            @get:DrawableRes
            val expurge: Int

            @get:DrawableRes
            val next: Int

            @get:DrawableRes
            val previous: Int
        }

        @InternalOudsApi
        interface Checkbox {
            @get:DrawableRes
            val selected: Int

            @get:DrawableRes
            val undetermined: Int
        }

        @InternalOudsApi
        interface Chip {
            @get:DrawableRes
            val tick: Int
        }

        @InternalOudsApi
        interface Link {
            @get:DrawableRes
            val externalLink: Int

            @get:DrawableRes
            val next: Int

            @get:DrawableRes
            val previous: Int
        }

        @InternalOudsApi
        interface ListItem {
            @get:DrawableRes
            val next: Int

            @get:DrawableRes
            val previous: Int
        }

        @InternalOudsApi
        interface RadioButton {
            @get:DrawableRes
            val selected: Int
        }

        @InternalOudsApi
        interface Switch {
            @get:DrawableRes
            val selected: Int
        }

        @InternalOudsApi
        interface Tag {
            @get:DrawableRes
            val close: Int
        }
    }

    @InternalOudsApi
    interface Functional {
        val actions: Actions
        val navigation: Navigation
        val settingsAndTools: SettingsAndTools
        val socialAndEngagement: SocialAndEngagement

        @InternalOudsApi
        interface Actions {
            @get:DrawableRes
            val deleteCrossRound: Int

            @get:DrawableRes
            val externalLink: Int
        }

        @InternalOudsApi
        interface Navigation {
            @get:DrawableRes
            val formChevronLeft: Int

            @get:DrawableRes
            val menuGridUiRound: Int
        }

        @InternalOudsApi
        interface SettingsAndTools {
            @get:DrawableRes
            val accessibilityHide: Int
        }

        @InternalOudsApi
        interface SocialAndEngagement {
            @get:DrawableRes
            val heartRecommend: Int
        }
    }
}
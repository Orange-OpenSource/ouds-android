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

@InternalOudsApi
interface OudsDrawableResources {
    val component: Component

    abstract class Component {
        abstract val alert: Alert
        abstract val checkbox: Checkbox
        abstract val chip: Chip
        abstract val link: Link
        abstract val radioButton: RadioButton
        abstract val switch: Switch
        abstract val tag: Tag

        abstract class Alert {
            @get:DrawableRes
            abstract val importantFill: Int

            @get:DrawableRes
            abstract val infoFill: Int

            @get:DrawableRes
            abstract val tickConfirmationFill: Int

            @get:DrawableRes
            abstract val warningExternalShape: Int

            @get:DrawableRes
            abstract val warningInternalShape: Int
        }

        abstract class Checkbox {
            @get:DrawableRes
            abstract val selected: Int

            @get:DrawableRes
            abstract val undetermined: Int
        }

        abstract class Chip {
            @get:DrawableRes
            abstract val tick: Int
        }

        abstract class Link {
            @get:DrawableRes
            abstract val next: Int

            @get:DrawableRes
            abstract val previous: Int
        }

        abstract class RadioButton {
            @get:DrawableRes
            abstract val selected: Int
        }

        abstract class Switch {
            @get:DrawableRes
            abstract val selected: Int
        }

        abstract class Tag {
            @get:DrawableRes
            abstract val close: Int
        }
    }
}
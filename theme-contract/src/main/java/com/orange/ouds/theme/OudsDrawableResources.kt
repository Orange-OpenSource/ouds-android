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

    interface Component {
        val alert: Alert
        val checkbox: Checkbox
        val chip: Chip
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
}
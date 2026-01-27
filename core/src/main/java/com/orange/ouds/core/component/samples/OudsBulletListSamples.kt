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

package com.orange.ouds.core.component.samples

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.core.component.OudsBulletList
import com.orange.ouds.core.component.OudsBulletListType
import com.orange.ouds.core.utilities.OudsPreview

@Composable
internal fun OudsBulletListUnorderedSample() {
    OudsBulletList {
        item(label = "Milk")
        item(label = "Vegetables", subListType = OudsBulletListType.Unordered(brandColor = true)) {
            item(label = "Tomatoes")
            item(label = "Salad", subListHasBoldText = false) {
                item(label = "Lettuce")
                item(label = "Arugula")
            }
        }
    }
}

@Composable
internal fun OudsBulletListOrderedSample() {
    OudsBulletList(type = OudsBulletListType.Ordered()) {
        item(label = "Prepare the ingredients")
        item(label = "Cook the pasta") {
            item(label = "Boil water in a large pot")
            item(label = "Add salt and then the pasta", subListHasBoldText = false) {
                item(label = "Cook for 8-10 minutes")
                item(label = "Stir occasionally")
            }
        }
        item(label = "Drain the pasta and serve")
    }
}

@Composable
internal fun OudsBulletListBareSample() {
    OudsBulletList(type = OudsBulletListType.Bare()) {
        item(label = "Event Planning")
        item(label = "Logistic Team", subListHasBoldText = false) {
            item(label = "Venue Booking")
            item(label = "Catering")
        }
        item(label = "Communication Team", subListHasBoldText = false) {
            item(label = "Invitations")
            item(label = "Social Media")
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewOudsBulletListUnorderedSample() = OudsPreview {
    OudsBulletListUnorderedSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsBulletListOrderedSample() = OudsPreview {
    OudsBulletListOrderedSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsBulletListBareSample() = OudsPreview {
    OudsBulletListBareSample()
}
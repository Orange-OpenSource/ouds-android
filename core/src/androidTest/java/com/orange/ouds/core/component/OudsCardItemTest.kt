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

package com.orange.ouds.core.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.orange.ouds.core.extension.setOudsContent
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

internal class OudsCardItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun oudsCardItem_click_succeeds() {
        with(composeTestRule) {
            val testTag = "OudsCardItem"
            val onClick = mock<() -> Unit>()

            setOudsContent {
                OudsCardItem(
                    label = "Label",
                    onClick = onClick,
                    modifier = Modifier.testTag(testTag)
                )
            }

            onNodeWithTag(testTag).performClick()
            verify(onClick).invoke()
        }
    }

    @Test
    fun oudsCardItem_withDescription_descriptionDisplayed() {
        with(composeTestRule) {
            val description = "Description"

            setOudsContent {
                OudsCardItem(
                    label = "Label",
                    description = description
                )
            }

            onNodeWithText(description).assertIsDisplayed()
        }
    }

    @Test
    fun oudsCardItem_withBlankDescription_descriptionNotDisplayed() {
        with(composeTestRule) {
            val description = "   "

            setOudsContent {
                OudsCardItem(
                    label = "Label",
                    description = description
                )
            }

            onNodeWithText(description).assertIsNotDisplayed()
        }
    }

    @Test
    fun oudsCardItem_withExtraLabel_extraLabelDisplayed() {
        with(composeTestRule) {
            val extraLabel = "Extra label"

            setOudsContent {
                OudsCardItem(
                    label = "Label",
                    extraLabel = extraLabel
                )
            }

            onNodeWithText(extraLabel).assertIsDisplayed()
        }
    }

    @Test
    fun oudsCardItem_withBlankExtraLabel_extraLabelNotDisplayed() {
        with(composeTestRule) {
            val extraLabel = "   "

            setOudsContent {
                OudsCardItem(
                    label = "Label",
                    extraLabel = extraLabel
                )
            }

            onNodeWithText(extraLabel).assertIsNotDisplayed()
        }
    }

    @Test
    fun oudsCardItem_withOverline_overlineDisplayed() {
        with(composeTestRule) {
            val overline = "Overline"

            setOudsContent {
                OudsCardItem(
                    label = "Label",
                    overline = overline
                )
            }

            onNodeWithText(overline).assertIsDisplayed()
        }
    }

    @Test
    fun oudsCardItem_withHelperText_helperTextDisplayed() {
        with(composeTestRule) {
            val helperText = "Helper text"

            setOudsContent {
                OudsCardItem(
                    label = "Label",
                    helperText = helperText
                )
            }

            onNodeWithText(helperText).assertIsDisplayed()
        }
    }

    @Test
    fun oudsCardItem_withBlankHelperText_helperTextNotDisplayed() {
        with(composeTestRule) {
            val helperText = "   "

            setOudsContent {
                OudsCardItem(
                    label = "Label",
                    helperText = helperText
                )
            }

            onNodeWithText(helperText).assertIsNotDisplayed()
        }
    }

    @Test
    fun oudsCardItem_withLeadingIcon_leadingIconDisplayed() {
        with(composeTestRule) {
            val leadingIconContentDescription = "Leading icon"

            setOudsContent {
                OudsCardItem(
                    label = "Label",
                    leading = OudsListItemLeading.Icon(
                        imageVector = Icons.Outlined.Favorite,
                        contentDescription = leadingIconContentDescription
                    )
                )
            }

            onNodeWithContentDescription(leadingIconContentDescription).assertIsDisplayed()
        }
    }

    @Test
    fun oudsCardItem_withTrailingIcon_trailingIconDisplayed() {
        with(composeTestRule) {
            val trailingIconContentDescription = "Trailing icon"

            setOudsContent {
                OudsCardItem(
                    label = "Label",
                    trailing = OudsListItemTrailing.Icon(
                        imageVector = Icons.Outlined.FavoriteBorder,
                        contentDescription = trailingIconContentDescription
                    )
                )
            }

            onNodeWithContentDescription(trailingIconContentDescription).assertIsDisplayed()
        }
    }
}

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

internal class OudsListItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun oudsListItem_click_succeeds() {
        with(composeTestRule) {
            val testTag = "OudsListItem"
            val onClick = mock<() -> Unit>()

            setOudsContent {
                OudsListItem(
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
    fun oudsListItem_withDescription_descriptionDisplayed() {
        with(composeTestRule) {
            val description = "Description"

            setOudsContent {
                OudsListItem(
                    label = "Label",
                    description = description
                )
            }

            onNodeWithText(description).assertIsDisplayed()
        }
    }

    @Test
    fun oudsListItem_withBlankDescription_descriptionNotDisplayed() {
        with(composeTestRule) {
            val description = "   "

            setOudsContent {
                OudsListItem(
                    label = "Label",
                    description = description
                )
            }

            onNodeWithText(description).assertIsNotDisplayed()
        }
    }

    @Test
    fun oudsListItem_withExtraLabel_extraLabelDisplayed() {
        with(composeTestRule) {
            val extraLabel = "Extra label"

            setOudsContent {
                OudsListItem(
                    label = "Label",
                    extraLabel = extraLabel
                )
            }

            onNodeWithText(extraLabel).assertIsDisplayed()
        }
    }

    @Test
    fun oudsListItem_withBlankExtraLabel_extraLabelNotDisplayed() {
        with(composeTestRule) {
            val extraLabel = "   "

            setOudsContent {
                OudsListItem(
                    label = "Label",
                    extraLabel = extraLabel
                )
            }

            onNodeWithText(extraLabel).assertIsNotDisplayed()
        }
    }

    @Test
    fun oudsListItem_withOverline_overlineDisplayed() {
        with(composeTestRule) {
            val overline = "Overline"

            setOudsContent {
                OudsListItem(
                    label = "Label",
                    overline = overline
                )
            }

            onNodeWithText(overline).assertIsDisplayed()
        }
    }

    @Test
    fun oudsListItem_withHelperText_helperTextDisplayed() {
        with(composeTestRule) {
            val helperText = "Helper text"

            setOudsContent {
                OudsListItem(
                    label = "Label",
                    helperText = helperText
                )
            }

            onNodeWithText(helperText).assertIsDisplayed()
        }
    }

    @Test
    fun oudsListItem_withBlankHelperText_helperTextNotDisplayed() {
        with(composeTestRule) {
            val helperText = "   "

            setOudsContent {
                OudsListItem(
                    label = "Label",
                    helperText = helperText
                )
            }

            onNodeWithText(helperText).assertIsNotDisplayed()
        }
    }

    @Test
    fun oudsListItem_withLeadingIcon_leadingIconDisplayed() {
        with(composeTestRule) {
            val leadingIconContentDescription = "Leading icon"

            setOudsContent {
                OudsListItem(
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
    fun oudsListItem_withTrailingIcon_trailingIconDisplayed() {
        with(composeTestRule) {
            val trailingIconContentDescription = "Trailing icon"

            setOudsContent {
                OudsListItem(
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

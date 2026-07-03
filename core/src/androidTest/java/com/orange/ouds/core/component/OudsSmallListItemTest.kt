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

internal class OudsSmallListItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun oudsSmallListItem_navigation_click_succeeds() {
        with(composeTestRule) {
            val testTag = "OudsSmallListItem"
            val onClick = mock<() -> Unit>()

            setOudsContent {
                OudsSmallListItem(
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
    fun oudsSmallListItem_withDescription_descriptionDisplayed() {
        with(composeTestRule) {
            val description = "Description"

            setOudsContent {
                OudsSmallListItem(
                    label = "Label",
                    description = description
                )
            }

            onNodeWithText(description).assertIsDisplayed()
        }
    }

    @Test
    fun oudsSmallListItem_withBlankDescription_descriptionNotDisplayed() {
        with(composeTestRule) {
            val description = "   "

            setOudsContent {
                OudsSmallListItem(
                    label = "Label",
                    description = description
                )
            }

            onNodeWithText(description).assertIsNotDisplayed()
        }
    }

    @Test
    fun oudsSmallListItem_withHelperText_helperTextDisplayed() {
        with(composeTestRule) {
            val helperText = "Helper text"

            setOudsContent {
                OudsSmallListItem(
                    label = "Label",
                    helperText = helperText
                )
            }

            onNodeWithText(helperText).assertIsDisplayed()
        }
    }

    @Test
    fun oudsSmallListItem_withBlankHelperText_helperTextNotDisplayed() {
        with(composeTestRule) {
            val helperText = "   "

            setOudsContent {
                OudsSmallListItem(
                    label = "Label",
                    helperText = helperText
                )
            }

            onNodeWithText(helperText).assertIsNotDisplayed()
        }
    }

    @Test
    fun oudsSmallListItem_withLeadingIcon_leadingIconDisplayed() {
        with(composeTestRule) {
            val leadingIconContentDescription = "Leading icon"

            setOudsContent {
                OudsSmallListItem(
                    label = "Label",
                    leading = OudsSmallListItemLeading.Icon(
                        imageVector = Icons.Outlined.Favorite,
                        contentDescription = leadingIconContentDescription
                    )
                )
            }

            onNodeWithContentDescription(leadingIconContentDescription).assertIsDisplayed()
        }
    }

    @Test
    fun oudsSmallListItem_withTrailingIcon_trailingIconDisplayed() {
        with(composeTestRule) {
            val trailingIconContentDescription = "Trailing icon"

            setOudsContent {
                OudsSmallListItem(
                    label = "Label",
                    trailing = OudsSmallListItemTrailing.Icon(
                        imageVector = Icons.Outlined.FavoriteBorder,
                        contentDescription = trailingIconContentDescription
                    )
                )
            }

            onNodeWithContentDescription(trailingIconContentDescription).assertIsDisplayed()
        }
    }
}

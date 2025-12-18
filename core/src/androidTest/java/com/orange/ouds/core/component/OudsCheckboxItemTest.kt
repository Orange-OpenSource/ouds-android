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

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.orange.ouds.core.extension.setOudsContent
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

internal class OudsCheckboxItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun oudsCheckboxItem_click_succeeds() {
        with(composeTestRule) {
            val testTag = "OudsCheckboxItem"
            val checked = false
            val onCheckedChange = mock<(Boolean) -> Unit>()

            setOudsContent {
                OudsCheckboxItem(
                    checked = checked,
                    label = "Label",
                    onCheckedChange = onCheckedChange,
                    modifier = Modifier.testTag(testTag)
                )
            }

            onNodeWithTag(testTag).assertIsOff()
            onNodeWithTag(testTag).performClick()
            verify(onCheckedChange).invoke(!checked)
        }
    }

    @Test
    fun oudsCheckboxItem_withDescription_descriptionDisplayed() {
        with(composeTestRule) {
            val description = "Description"

            setOudsContent {
                OudsCheckboxItem(
                    checked = false,
                    label = "Label",
                    description = description,
                    onCheckedChange = { },
                )
            }

            onNodeWithText(description).assertIsDisplayed()
        }
    }

    @Test
    fun oudsCheckboxItem_withBlankDescription_descriptionNotDisplayed() {
        with(composeTestRule) {
            val description = "   "

            setOudsContent {
                OudsCheckboxItem(
                    checked = false,
                    label = "Label",
                    description = description,
                    onCheckedChange = { },
                )
            }

            onNodeWithText(description).assertIsNotDisplayed()
        }
    }
}
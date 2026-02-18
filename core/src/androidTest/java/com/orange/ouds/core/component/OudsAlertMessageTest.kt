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

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.orange.ouds.core.R
import com.orange.ouds.core.extension.setOudsContent
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class OudsAlertMessageTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun oudsAlertMessage_actionLinkClick_succeeds() {
        with(composeTestRule) {
            val actionLinkLabel = "Action"
            val onClick = mock<() -> Unit>()

            setOudsContent {
                OudsAlertMessage(
                    label = "Label",
                    actionLink = OudsAlertMessageActionLink(actionLinkLabel, onClick = onClick)
                )
            }

            onNodeWithText(actionLinkLabel).performClick()
            verify(onClick).invoke()
        }
    }

    @Test
    fun oudsAlertMessage_closeClick_succeeds() {
        with(composeTestRule) {
            val onClose = mock<() -> Unit>()

            setOudsContent {
                OudsAlertMessage(
                    label = "Label",
                    onClose = onClose
                )
            }

            onNodeWithContentDescription(activity.getString(R.string.core_alertMessage_close_a11y)).performClick()
            verify(onClose).invoke()
        }
    }

    @Test
    fun oudsAlertMessage_blankActionLinkLabel_actionLinkNotDisplayed() {
        with(composeTestRule) {
            val actionLinkLabel = "   "
            setOudsContent {
                OudsAlertMessage(
                    label = "Label",
                    actionLink = OudsAlertMessageActionLink(label = actionLinkLabel, onClick = {})
                )
            }

            onNodeWithText(actionLinkLabel).assertDoesNotExist()
        }
    }
}
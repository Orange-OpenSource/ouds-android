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

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.orange.ouds.core.extension.setOudsContent
import org.junit.Rule
import org.junit.Test

internal class OudsBadgeTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun oudsBadge_maxCount_succeeds() {
        with(composeTestRule) {
            val count = OudsBadge.MaxCount

            setOudsContent {
                OudsBadge(count = count)
            }

            onNodeWithText(count.toString()).assertExists()
        }
    }

    @Test
    fun oudsBadge_maxCountOverflow_succeeds() {
        with(composeTestRule) {
            val count = OudsBadge.MaxCount + 1

            setOudsContent {
                OudsBadge(count = count)
            }

            onNodeWithText(count.toString()).assertDoesNotExist()
            onNodeWithText("+${OudsBadge.MaxCount}").assertExists()
        }
    }
}

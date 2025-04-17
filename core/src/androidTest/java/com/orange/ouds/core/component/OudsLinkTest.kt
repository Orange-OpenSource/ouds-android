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
import androidx.compose.ui.test.performClick
import com.orange.ouds.core.extension.setOudsContent
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

internal class OudsLinkTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun oudsLink_click_succeeds() {
        with(composeTestRule) {
            val label = "Label"
            val onClick = mock<() -> Unit>()

            setOudsContent {
                OudsLink(
                    label = label,
                    icon = null,
                    onClick = onClick
                )
            }

            onNodeWithText(label).performClick()
            verify(onClick).invoke()
        }
    }
}
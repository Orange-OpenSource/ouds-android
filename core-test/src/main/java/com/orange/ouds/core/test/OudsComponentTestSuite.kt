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

package com.orange.ouds.core.test

import com.orange.ouds.theme.OudsThemeContract
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    OudsBadgeTest::class,
    OudsButtonTest::class,
    OudsCheckboxItemTest::class,
    OudsCheckboxTest::class,
    OudsColoredBoxTest::class,
    OudsFilterChipTest::class,
    OudsHorizontalDividerTest::class,
    OudsInputTagTest::class,
    OudsLinkTest::class,
    OudsRadioButtonItemTest::class,
    OudsRadioButtonTest::class,
    OudsSuggestionChipTest::class,
    OudsSwitchItemTest::class,
    OudsSwitchTest::class,
    OudsTagTest::class,
    OudsVerticalDividerTest::class
)
abstract class OudsComponentTestSuite {

    companion object {
        lateinit var theme: OudsThemeContract
    }
}

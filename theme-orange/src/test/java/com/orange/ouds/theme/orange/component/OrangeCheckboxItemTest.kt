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

package com.orange.ouds.theme.orange.component

import com.orange.ouds.core.component.OudsCheckboxItemHighContrastModePreviewParameter
import com.orange.ouds.core.component.OudsCheckboxItemPreviewParameter
import com.orange.ouds.core.test.OudsCheckboxItemTest
import com.orange.ouds.theme.orange.OrangeTheme

class OrangeCheckboxItemTest : OudsCheckboxItemTest() {

    class Parameterized(parameter: OudsCheckboxItemPreviewParameter) : OudsCheckboxItemTest.Parameterized(parameter, OrangeTheme())

    class ParameterizedHighContrastMode(parameter: OudsCheckboxItemHighContrastModePreviewParameter) :
        OudsCheckboxItemTest.ParameterizedHighContrastMode(parameter, OrangeTheme())

    class NonParameterized() : OudsCheckboxItemTest.NonParameterized(OrangeTheme())
}

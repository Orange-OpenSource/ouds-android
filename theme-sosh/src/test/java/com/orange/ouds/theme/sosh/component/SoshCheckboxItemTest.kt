/*
 * Software Name: OUDS Android
 * SPDX-FileCopyrightText: Copyright (c) Sosh SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components 
 */

package com.orange.ouds.theme.sosh.component

import com.orange.ouds.core.component.OudsCheckboxItemHighContrastModePreviewParameter
import com.orange.ouds.core.component.OudsCheckboxItemPreviewParameter
import com.orange.ouds.core.test.OudsCheckboxItemTest
import com.orange.ouds.theme.sosh.SoshTheme

class SoshCheckboxItemTest : OudsCheckboxItemTest() {

    class Parameterized(parameter: OudsCheckboxItemPreviewParameter) : OudsCheckboxItemTest.Parameterized(parameter, SoshTheme())

    class ParameterizedHighContrastMode(parameter: OudsCheckboxItemHighContrastModePreviewParameter) :
        OudsCheckboxItemTest.ParameterizedHighContrastMode(parameter, SoshTheme())

    class NonParameterized() : OudsCheckboxItemTest.NonParameterized(SoshTheme())
}

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

package com.orange.ouds.theme.sosh.component

import com.orange.ouds.core.component.OudsRadioButtonItemHighContrastModePreviewParameter
import com.orange.ouds.core.component.OudsRadioButtonItemPreviewParameter
import com.orange.ouds.core.test.OudsRadioButtonItemTest
import com.orange.ouds.theme.sosh.SoshTheme

class SoshRadioButtonItemTest : OudsRadioButtonItemTest() {

    class Parameterized(parameter: OudsRadioButtonItemPreviewParameter) : OudsRadioButtonItemTest.Parameterized(parameter, SoshTheme())

    class ParameterizedHighContrastMode(parameter: OudsRadioButtonItemHighContrastModePreviewParameter) :
        OudsRadioButtonItemTest.ParameterizedHighContrastMode(parameter, SoshTheme())

    class NonParameterized() : OudsRadioButtonItemTest.NonParameterized(SoshTheme())
}

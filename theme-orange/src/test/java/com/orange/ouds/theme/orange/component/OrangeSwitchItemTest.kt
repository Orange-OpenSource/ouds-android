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

import com.orange.ouds.core.component.OudsSwitchItemPreviewParameter
import com.orange.ouds.core.test.OudsSwitchItemTest
import com.orange.ouds.theme.orange.OrangeTheme

class OrangeSwitchItemTest : OudsSwitchItemTest() {

    class Parameterized(parameter: OudsSwitchItemPreviewParameter) : OudsSwitchItemTest.Parameterized(parameter, OrangeTheme())

    class NonParameterized() : OudsSwitchItemTest.NonParameterized(OrangeTheme())
}

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

import com.orange.ouds.core.component.OudsLinkPreviewParameter
import com.orange.ouds.core.test.OudsLinkTest
import com.orange.ouds.theme.orange.OrangeTheme

class OrangeLinkTest : OudsLinkTest() {

    class Parameterized(parameter: OudsLinkPreviewParameter) : OudsLinkTest.Parameterized(parameter, OrangeTheme())

    class NonParameterized() : OudsLinkTest.NonParameterized(OrangeTheme())
}

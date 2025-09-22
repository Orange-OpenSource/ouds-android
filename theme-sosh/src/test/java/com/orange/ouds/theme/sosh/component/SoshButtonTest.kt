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

import com.orange.ouds.core.component.OudsButtonPreviewParameter
import com.orange.ouds.core.test.OudsButtonTest
import com.orange.ouds.theme.sosh.SoshTheme

class SoshButtonTest : OudsButtonTest() {

    class Parameterized(parameter: OudsButtonPreviewParameter) : OudsButtonTest.Parameterized(parameter, SoshTheme())

    class NonParameterized() : OudsButtonTest.NonParameterized(SoshTheme())
}

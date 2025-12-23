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

package com.orange.ouds.core.component.figma

import androidx.compose.runtime.Composable
import com.figma.code.connect.FigmaConnect
import com.figma.code.connect.FigmaVariant
import com.orange.ouds.core.component.OudsHorizontalDivider
import com.orange.ouds.core.component.OudsVerticalDivider

@FigmaConnect(url = "<FIGMA_COMPONENTS_DIVIDER>")
@FigmaVariant("Orientation", "Horizontal")
class OudsDividerConnection {

    @Composable
    fun OudsHorizontalDividerExample() {
        OudsHorizontalDivider()
    }
}

@FigmaConnect(url = "<FIGMA_COMPONENTS_DIVIDER>")
@FigmaVariant("Orientation", "Vertical")
class OudsVerticalDividerConnection {

    @Composable
    fun OudsVerticalDividerExample() {
        OudsVerticalDivider()
    }
}
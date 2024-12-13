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

package com.orange.ouds.theme.tokens

sealed interface OudsElevationKeyToken {

    data object None : OudsElevationKeyToken
    data object Raised : OudsElevationKeyToken
    data object OverlayDefault : OudsElevationKeyToken
    data object StickyDefault : OudsElevationKeyToken
    data object StickyEmphasized : OudsElevationKeyToken
    data object StickyNavigationScrolled : OudsElevationKeyToken
    data object Drag : OudsElevationKeyToken
    data object OverlayEmphasized : OudsElevationKeyToken
}
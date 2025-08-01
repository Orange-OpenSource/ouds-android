//
// Software Name: OUDS Android
// SPDX-FileCopyrightText: Copyright (c) Orange SA
// SPDX-License-Identifier: MIT
//
// This software is distributed under the MIT license,
// the text of which is available at https://opensource.org/license/MIT/
// or see the "LICENSE" file for more details.
//
// Software description: Android library of reusable graphical components
//

// OUDS Core tokens version 1.2.0
//
// Android Core tokens version 1.0.0
// Android System tokens version 1.0.0
//
// Orange Core tokens version 1.1.0
// Orange Brand tokens version 1.2.0
//
// Generated by Tokenator

package com.orange.ouds.theme.tokens

import com.orange.ouds.foundation.InternalOudsApi

@InternalOudsApi
sealed interface OudsElevationKeyToken : OudsKeyToken {
    data object Drag : OudsElevationKeyToken
    data object None : OudsElevationKeyToken
    data object OverlayDefault : OudsElevationKeyToken
    data object OverlayEmphasized : OudsElevationKeyToken
    data object Raised : OudsElevationKeyToken
    data object StickyDefault : OudsElevationKeyToken
    data object StickyEmphasized : OudsElevationKeyToken
    data object StickyNavigationScrolled : OudsElevationKeyToken
}


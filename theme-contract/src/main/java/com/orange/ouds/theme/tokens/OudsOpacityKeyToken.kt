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

package com.orange.ouds.theme.tokens

@InternalOudsApi
sealed interface OudsOpacityKeyToken {
    data object Invisible : OudsOpacityKeyToken
    data object Medium : OudsOpacityKeyToken
    data object Opaque : OudsOpacityKeyToken
    data object Strong : OudsOpacityKeyToken
    data object Weak : OudsOpacityKeyToken
    data object Weaker : OudsOpacityKeyToken
}


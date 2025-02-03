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

import com.orange.ouds.foundation.InternalOudsApi

@InternalOudsApi
sealed interface OudsBorderKeyToken : OudsKeyToken {
    sealed interface Radius : OudsBorderKeyToken {
        data object Default : Radius
        data object Medium : Radius
        data object None : Radius
        data object Pill : Radius
        data object Short : Radius
        data object Tall : Radius
    }
    sealed interface Style : OudsBorderKeyToken {
        data object Default : Style
        data object Drag : Style
    }
    sealed interface Width : OudsBorderKeyToken {
        data object Default : Width
        data object Focus : Width
        data object FocusInset : Width
        data object Medium : Width
        data object None : Width
        data object Thick : Width
        data object Thicker : Width
        data object Thin : Width
    }
}


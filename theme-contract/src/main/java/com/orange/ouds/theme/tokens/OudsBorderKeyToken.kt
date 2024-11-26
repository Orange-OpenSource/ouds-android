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

sealed interface OudsBorderKeyToken {

    sealed interface Width : OudsBorderKeyToken {
        data object None : Width
        data object Default : Width
        data object Thin : Width
        data object Medium : Width
        data object Thick : Width
        data object Thicker : Width

        sealed interface Focus : Width {
            companion object : Focus
            data object Inset : Focus
        }
    }

    sealed interface Radius : OudsBorderKeyToken {
        data object None : Radius
        data object Default : Radius
        data object Short : Radius
        data object Medium : Radius
        data object Tall : Radius
        data object Pill : Radius
    }

    sealed interface Style : OudsBorderKeyToken {
        data object Default : Style
        data object Drag : Style
    }
}
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

class OudsBorderKeyToken {
    enum class Width {
        None,
        Default,
        Thin,
        Medium,
        Thick,
        Thicker,
        Focus,
        FocusInset
    }

    enum class Radius {
        None,
        Default,
        Short,
        Medium,
        Tall,
        Pill
    }

    enum class Style {
        Default,
        Drag
    }
}
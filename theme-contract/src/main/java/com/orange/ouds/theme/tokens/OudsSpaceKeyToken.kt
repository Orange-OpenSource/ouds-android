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

object OudsSpaceKeyToken {
    enum class Fixed {
        None,
        Smash,
        Shortest,
        Shorter,
        Short,
        Medium,
        Tall,
        Taller,
        Tallest,
        Spacious,
        Huge,
        Jumbo
    }

    enum class Scaled {
        None,
        Smash,
        Shortest,
        Shorter,
        Short,
        Medium,
        Tall,
        Taller,
        Tallest,
        Spacious
    }

    enum class PaddingInline {
        None,
        Shorter,
        Short,
        Medium,
        Tall,
        Taller,
        Tallest;

        enum class WithIcon {
            None,
            Shortest,
            Shorter,
            Short,
            Medium,
            Tall,
            Taller,
            Tallest,
        }

        enum class WithArrow {
            None,
            Shortest,
            Shorter,
            Short,
            Medium,
            Tall,
            Taller,
            Tallest,
        }
    }

    enum class PaddingBlock {
        None,
        Shorter,
        Short,
        Medium,
        Tall,
        Taller,
        Tallest;

        enum class WithIcon {
            None,
            Shortest,
            Shorter,
            Short,
            Medium,
            Tall,
            Taller,
        }
    }

    enum class Inset {
        None,
        Smash,
        Shortest,
        Shorter,
        Short,
        Medium,
        Tall,
        Taller,
        Tallest,
        Spacious,
    }

    enum class ColumnGap {
        None,
        Shorter,
        Short,
        Medium,
        Tall,
        Taller;

        enum class WithIcon {
            None,
            Shortest,
            Shorter,
            Short,
            Medium,
            Tall,
        }

        enum class WithArrow {
            None,
            Shortest,
            Shorter,
            Short,
            Medium,
            Tall,
        }
    }

    enum class RowGap {
        None,
        Shortest,
        Shorter,
        Short,
        Medium,
        Tall;

        enum class WithIcon {
            None,
            Shortest,
            Shorter,
            Short,
            Medium,
            Tall,
        }
    }
}
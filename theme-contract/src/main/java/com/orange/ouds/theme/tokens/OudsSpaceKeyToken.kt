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
sealed interface OudsSpaceKeyToken {
    sealed interface ColumnGap : OudsSpaceKeyToken {
        data object Medium : ColumnGap
        data object None : ColumnGap
        data object Short : ColumnGap
        data object Shorter : ColumnGap
        data object Shortest : ColumnGap
        data object Smash : ColumnGap
        data object Tall : ColumnGap
        data object Taller : ColumnGap
        data object Tallest : ColumnGap
    }
    sealed interface Fixed : OudsSpaceKeyToken {
        data object Huge : Fixed
        data object Jumbo : Fixed
        data object Medium : Fixed
        data object None : Fixed
        data object Short : Fixed
        data object Shorter : Fixed
        data object Shortest : Fixed
        data object Smash : Fixed
        data object Spacious : Fixed
        data object Tall : Fixed
        data object Taller : Fixed
        data object Tallest : Fixed
    }
    sealed interface Inset : OudsSpaceKeyToken {
        data object Medium : Inset
        data object None : Inset
        data object Short : Inset
        data object Shorter : Inset
        data object Shortest : Inset
        data object Smash : Inset
        data object Spacious : Inset
        data object Tall : Inset
        data object Taller : Inset
        data object Tallest : Inset
    }
    sealed interface PaddingBlock : OudsSpaceKeyToken {
        data object Huge : PaddingBlock
        data object Medium : PaddingBlock
        data object None : PaddingBlock
        data object Short : PaddingBlock
        data object Shorter : PaddingBlock
        data object Shortest : PaddingBlock
        data object Smash : PaddingBlock
        data object Spacious : PaddingBlock
        data object Tall : PaddingBlock
        data object Taller : PaddingBlock
        data object Tallest : PaddingBlock
    }
    sealed interface PaddingInline : OudsSpaceKeyToken {
        data object Huge : PaddingInline
        data object Medium : PaddingInline
        data object None : PaddingInline
        data object Short : PaddingInline
        data object Shorter : PaddingInline
        data object Shortest : PaddingInline
        data object Smash : PaddingInline
        data object Spacious : PaddingInline
        data object Tall : PaddingInline
        data object Taller : PaddingInline
        data object Tallest : PaddingInline
    }
    sealed interface RowGap : OudsSpaceKeyToken {
        data object Medium : RowGap
        data object None : RowGap
        data object Short : RowGap
        data object Shorter : RowGap
        data object Shortest : RowGap
        data object Smash : RowGap
        data object Tall : RowGap
    }
    sealed interface Scaled : OudsSpaceKeyToken {
        data object Medium : Scaled
        data object None : Scaled
        data object Short : Scaled
        data object Shorter : Scaled
        data object Shortest : Scaled
        data object Smash : Scaled
        data object Spacious : Scaled
        data object Tall : Scaled
        data object Taller : Scaled
        data object Tallest : Scaled
    }
}


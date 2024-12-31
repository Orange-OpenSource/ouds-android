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

sealed interface OudsSizeKeyToken : OudsKeyToken {
    sealed interface Icon : OudsSizeKeyToken {
        sealed interface Decorative : Icon {
            data object ExtraExtraLarge : Decorative
            data object ExtraExtraSmall : Decorative
            data object Large : Decorative
            data object Medium : Decorative
            data object Small : Decorative
            data object ExtraLarge : Decorative
            data object ExtraSmall : Decorative
        }
        sealed interface WithBody : Icon {
            sealed interface Large : WithBody {
                data object SizeLarge : Large
                data object SizeMedium : Large
                data object SizeSmall : Large
            }
            sealed interface Medium : WithBody {
                data object SizeLarge : Medium
                data object SizeMedium : Medium
                data object SizeSmall : Medium
            }
            sealed interface Small : WithBody {
                data object SizeLarge : Small
                data object SizeMedium : Small
                data object SizeSmall : Small
            }
        }
        sealed interface WithHeading : Icon {
            sealed interface Large : WithHeading {
                data object SizeLarge : Large
                data object SizeMedium : Large
                data object SizeSmall : Large
            }
            sealed interface Medium : WithHeading {
                data object SizeLarge : Medium
                data object SizeMedium : Medium
                data object SizeSmall : Medium
            }
            sealed interface Small : WithHeading {
                data object SizeLarge : Small
                data object SizeMedium : Small
                data object SizeSmall : Small
            }
            sealed interface ExtraLarge : WithHeading {
                data object SizeLarge : ExtraLarge
                data object SizeMedium : ExtraLarge
                data object SizeSmall : ExtraLarge
            }
        }
        sealed interface WithLabel : Icon {
            sealed interface Large : WithLabel {
                data object SizeLarge : Large
                data object SizeMedium : Large
                data object SizeSmall : Large
                data object SizeExtraLarge : Large
                data object SizeExtraSmall : Large
            }
            sealed interface Medium : WithLabel {
                data object SizeLarge : Medium
                data object SizeMedium : Medium
                data object SizeSmall : Medium
                data object SizeExtraSmall : Medium
            }
            sealed interface Small : WithLabel {
                data object SizeLarge : Small
                data object SizeMedium : Small
                data object SizeSmall : Small
                data object SizeExtraSmall : Small
            }
            sealed interface ExtraLarge : WithLabel {
                data object SizeLarge : ExtraLarge
                data object SizeMedium : ExtraLarge
                data object SizeSmall : ExtraLarge
            }
        }
    }
    sealed interface MaxWidth : OudsSizeKeyToken {
        sealed interface Type : MaxWidth {
            sealed interface Body : Type {
                data object Large : Body
                data object Medium : Body
                data object Small : Body
            }
            sealed interface Display : Type {
                data object Large : Display
                data object Medium : Display
                data object Small : Display
            }
            sealed interface Heading : Type {
                data object Large : Heading
                data object Medium : Heading
                data object Small : Heading
                data object ExtraLarge : Heading
            }
        }
    }
}


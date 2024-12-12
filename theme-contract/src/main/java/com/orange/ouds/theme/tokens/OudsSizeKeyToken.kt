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

sealed interface OudsSizeKeyToken {

    sealed interface Icon : OudsSizeKeyToken {
        sealed interface Decorative : Icon {
            data object ExtraExtraSmall : Decorative
            data object ExtraSmall : Decorative
            data object Small : Decorative
            data object Medium : Decorative
            data object Large : Decorative
            data object ExtraLarge : Decorative
            data object ExtraExtraLarge : Decorative
        }

        sealed interface WithHeadingExtraLarge : Icon {
            data object SizeSmall : WithHeadingExtraLarge
            data object SizeMedium : WithHeadingExtraLarge
            data object SizeLarge : WithHeadingExtraLarge
        }

        sealed interface WithHeadingLarge : Icon {
            data object SizeSmall : WithHeadingLarge
            data object SizeMedium : WithHeadingLarge
            data object SizeLarge : WithHeadingLarge
        }

        sealed interface WithHeadingMedium : Icon {
            data object SizeSmall : WithHeadingMedium
            data object SizeMedium : WithHeadingMedium
            data object SizeLarge : WithHeadingMedium
        }

        sealed interface WithHeadingSmall : Icon {
            data object SizeSmall : WithHeadingSmall
            data object SizeMedium : WithHeadingSmall
            data object SizeLarge : WithHeadingSmall
        }

        sealed interface WithBodyLarge : Icon {
            data object SizeSmall : WithBodyLarge
            data object SizeMedium : WithBodyLarge
            data object SizeLarge : WithBodyLarge
        }

        sealed interface WithBodyMedium : Icon {
            data object SizeSmall : WithBodyMedium
            data object SizeMedium : WithBodyMedium
            data object SizeLarge : WithBodyMedium
        }

        sealed interface WithBodySmall : Icon {
            data object SizeSmall : WithBodySmall
            data object SizeMedium : WithBodySmall
            data object SizeLarge : WithBodySmall
        }

        sealed interface WithLabelExtraLarge : Icon {
            data object SizeSmall : WithLabelExtraLarge
            data object SizeMedium : WithLabelExtraLarge
            data object SizeLarge : WithLabelExtraLarge
        }

        sealed interface WithLabelLarge : Icon {
            data object SizeExtraSmall : WithLabelLarge
            data object SizeSmall : WithLabelLarge
            data object SizeMedium : WithLabelLarge
            data object SizeLarge : WithLabelLarge
            data object SizeExtraLarge : WithLabelLarge
        }

        sealed interface WithLabelMedium : Icon {
            data object SizeSmall : WithLabelMedium
            data object SizeMedium : WithLabelMedium
            data object SizeLarge : WithLabelMedium
        }

        sealed interface WithLabelSmall : Icon {
            data object SizeSmall : WithLabelSmall
            data object SizeMedium : WithLabelSmall
            data object SizeLarge : WithLabelSmall
        }
    }

    sealed interface MaxWidth : OudsSizeKeyToken {
        sealed interface Type : MaxWidth {
            data object DisplaySmall : Type
            data object DisplayMedium : Type
            data object DisplayLarge : Type
            data object HeadingSmall : Type
            data object HeadingMedium : Type
            data object HeadingLarge : Type
            data object HeadingExtraLarge : Type
            data object BodySmall : Type
            data object BodyMedium : Type
            data object BodyLarge : Type
        }
    }
}
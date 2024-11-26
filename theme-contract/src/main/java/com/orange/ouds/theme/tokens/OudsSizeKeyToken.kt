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
            data object Shortest : Decorative
            data object Shorter : Decorative
            data object Short : Decorative
            data object Medium : Decorative
            data object Tall : Decorative
            data object Taller : Decorative
            data object Tallest : Decorative
        }

        sealed interface WithHeadingExtraLarge : Icon {
            data object SizeShort : WithHeadingExtraLarge
            data object SizeMedium : WithHeadingExtraLarge
            data object SizeTall : WithHeadingExtraLarge
        }

        sealed interface WithHeadingLarge : Icon {
            data object SizeShort : WithHeadingLarge
            data object SizeMedium : WithHeadingLarge
            data object SizeTall : WithHeadingLarge
        }

        sealed interface WithHeadingMedium : Icon {
            data object SizeShort : WithHeadingMedium
            data object SizeMedium : WithHeadingMedium
            data object SizeTall : WithHeadingMedium
        }

        sealed interface WithHeadingSmall : Icon {
            data object SizeShort : WithHeadingSmall
            data object SizeMedium : WithHeadingSmall
            data object SizeTall : WithHeadingSmall
        }

        sealed interface WithBodyLarge : Icon {
            data object SizeShort : WithBodyLarge
            data object SizeMedium : WithBodyLarge
            data object SizeTall : WithBodyLarge
        }

        sealed interface WithBodyMedium : Icon {
            data object SizeShort : WithBodyMedium
            data object SizeMedium : WithBodyMedium
            data object SizeTall : WithBodyMedium
        }

        sealed interface WithBodySmall : Icon {
            data object SizeShort : WithBodySmall
            data object SizeMedium : WithBodySmall
            data object SizeTall : WithBodySmall
        }

        sealed interface WithLabelExtraLarge : Icon {
            data object SizeShort : WithLabelExtraLarge
            data object SizeMedium : WithLabelExtraLarge
            data object SizeTall : WithLabelExtraLarge
        }

        sealed interface WithLabelLarge : Icon {
            data object SizeShorter : WithLabelLarge
            data object SizeShort : WithLabelLarge
            data object SizeMedium : WithLabelLarge
            data object SizeTall : WithLabelLarge
            data object SizeTaller : WithLabelLarge
        }

        sealed interface WithLabelMedium : Icon {
            data object SizeShort : WithLabelMedium
            data object SizeMedium : WithLabelMedium
            data object SizeTall : WithLabelMedium
        }

        sealed interface WithLabelSmall : Icon {
            data object SizeShort : WithLabelSmall
            data object SizeMedium : WithLabelSmall
            data object SizeTall : WithLabelSmall
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
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

import com.orange.ouds.foundation.InternalOudsApi

@InternalOudsApi
sealed interface OudsTypographyKeyToken : OudsKeyToken {

    sealed interface Display : OudsTypographyKeyToken {
        data object Large : Display
        data object Medium : Display
        data object Small : Display
    }

    sealed interface Heading : OudsTypographyKeyToken {
        data object ExtraLarge : Heading
        data object Large : Heading
        data object Medium : Heading
        data object Small : Heading
    }

    sealed interface Body : OudsTypographyKeyToken {
        sealed interface Default : Body {
            data object Large : Default
            data object Medium : Default
            data object Small : Default
        }

        sealed interface Strong : Body {
            data object Large : Strong
            data object Medium : Strong
            data object Small : Strong
        }
    }

    sealed interface Label : OudsTypographyKeyToken {
        sealed interface Default : Label {
            data object ExtraLarge : Default
            data object Large : Default
            data object Medium : Default
            data object Small : Default
        }

        sealed interface Strong : Label {
            data object ExtraLarge : Strong
            data object Large : Strong
            data object Medium : Strong
            data object Small : Strong
        }
    }
}
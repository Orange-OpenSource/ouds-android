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

/**
 * @suppress
 */
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
        sealed interface Large : Body {
            data object Default : Large
            data object Strong : Large
        }

        sealed interface Medium : Body {
            data object Default : Medium
            data object Strong : Medium
        }

        sealed interface Small : Body {
            data object Default : Small
            data object Strong : Small
        }
    }

    sealed interface Label : OudsTypographyKeyToken {
        sealed interface ExtraLarge : Label {
            data object Default : ExtraLarge
            data object Strong : ExtraLarge
        }

        sealed interface Large : Label {
            data object Default : Large
            data object Strong : Large
        }

        sealed interface Medium : Label {
            data object Default : Medium
            data object Strong : Medium
        }

        sealed interface Small : Label {
            data object Default : Small
            data object Strong : Small
        }
    }
}
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

    @InternalOudsApi
    sealed interface Display : OudsTypographyKeyToken {
        @InternalOudsApi
        data object Large : Display

        @InternalOudsApi
        data object Medium : Display

        @InternalOudsApi
        data object Small : Display
    }

    @InternalOudsApi
    sealed interface Heading : OudsTypographyKeyToken {
        @InternalOudsApi
        data object ExtraLarge : Heading

        @InternalOudsApi
        data object Large : Heading

        @InternalOudsApi
        data object Medium : Heading

        @InternalOudsApi
        data object Small : Heading
    }

    @InternalOudsApi
    sealed interface Body : OudsTypographyKeyToken {
        @InternalOudsApi
        sealed interface Large : Body {
            @InternalOudsApi
            data object Default : Large

            @InternalOudsApi
            data object Strong : Large
        }

        @InternalOudsApi
        sealed interface Medium : Body {
            @InternalOudsApi
            data object Default : Medium

            @InternalOudsApi
            data object Strong : Medium
        }

        @InternalOudsApi
        sealed interface Small : Body {
            @InternalOudsApi
            data object Default : Small

            @InternalOudsApi
            data object Strong : Small
        }
    }

    @InternalOudsApi
    sealed interface Label : OudsTypographyKeyToken {
        @InternalOudsApi
        sealed interface ExtraLarge : Label {
            @InternalOudsApi
            data object Default : ExtraLarge

            @InternalOudsApi
            data object Strong : ExtraLarge
        }

        @InternalOudsApi
        sealed interface Large : Label {
            @InternalOudsApi
            data object Default : Large

            @InternalOudsApi
            data object Strong : Large
        }

        @InternalOudsApi
        sealed interface Medium : Label {
            @InternalOudsApi
            data object Default : Medium

            @InternalOudsApi
            data object Strong : Medium
        }

        @InternalOudsApi
        sealed interface Small : Label {
            @InternalOudsApi
            data object Default : Small

            @InternalOudsApi
            data object Strong : Small
        }
    }
}
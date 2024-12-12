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

sealed interface OudsColorKeyToken {
    sealed interface Opacity : OudsColorKeyToken {
        data object Lower : Opacity
        data object Lowest : Opacity
        data object Transparent : Opacity
    }

    sealed interface Action : OudsColorKeyToken {
        data object Disabled : Action
        data object Enabled : Action
        data object Focus : Action
        data object Highlighted : Action
        data object Hover : Action
        data object Loading : Action
        sealed interface Negative : Action {
            data object Enabled : Negative
            data object Focus : Negative
            data object Hover : Negative
            data object Loading : Negative
            data object Pressed : Negative
        }

        data object Pressed : Action
        data object Selected : Action
        sealed interface Support : Action {
            data object Enabled : Support
            data object Focus : Support
            data object Hover : Support
            data object Loading : Support
            data object Pressed : Support
        }

        data object Visited : Action
    }

    sealed interface Always : OudsColorKeyToken {
        data object Black : Always
        data object OnBlack : Always
        data object OnWhite : Always
        data object White : Always
    }

    sealed interface Background : OudsColorKeyToken {
        data object Emphasized : Background
        data object Primary : Background
        data object Secondary : Background
        data object Tertiary : Background
    }

    sealed interface Border : OudsColorKeyToken {
        data object BrandPrimary : Border
        data object Default : Border
        data object Emphasized : Border
        data object Focus : Border
        data object FocusInset : Border
        sealed interface OnBrand : Border {
            data object Primary : OnBrand
        }
    }

    sealed interface Content : OudsColorKeyToken {
        data object BrandPrimary : Content
        data object Default : Content
        data object Disabled : Content
        data object Muted : Content
        sealed interface OnAction : Content {
            data object Disabled : OnAction
            data object Enabled : OnAction
            data object Focus : OnAction
            data object Highlighted : OnAction
            data object Hover : OnAction
            data object Loading : OnAction
            data object Negative : OnAction
            data object Pressed : OnAction
        }

        sealed interface OnBrand : Content {
            data object Primary : OnBrand
        }

        sealed interface OnOverlay : Content {
            data object Emphasized : OnOverlay
        }

        sealed interface OnStatus : Content {
            data object Emphasized : OnStatus
            data object EmphasizedNeutral : OnStatus
            data object Muted : OnStatus
        }

        sealed interface Status : Content {
            data object Info : Status
            data object Negative : Status
            data object Positive : Status
            data object Warning : Status
        }
    }

    sealed interface Overlay : OudsColorKeyToken {
        data object Default : Overlay
        data object Drag : Overlay
        data object Emphasized : Overlay
        data object Modal : Overlay
    }

    sealed interface Surface : OudsColorKeyToken {
        sealed interface Brand : Surface {
            data object Primary : Brand
        }

        sealed interface Status : Surface {
            sealed interface Accent : Status {
                data object Emphasized : Accent
                data object Muted : Accent
            }

            sealed interface Info : Status {
                data object Emphasized : Info
                data object Muted : Info
            }

            sealed interface Negative : Status {
                data object Emphasized : Negative
                data object Muted : Negative
            }

            sealed interface Neutral : Status {
                data object Emphasized : Neutral
                data object Muted : Neutral
            }

            sealed interface Positive : Status {
                data object Emphasized : Positive
                data object Muted : Positive
            }

            sealed interface Warning : Status {
                data object Emphasized : Warning
                data object Muted : Warning
            }
        }
    }

    sealed interface Decorative : OudsColorKeyToken {
        sealed interface Accent1 : Decorative {
            data object Default : Accent1
            data object Emphasized : Accent1
            data object Muted : Accent1
        }

        sealed interface Accent2 : Decorative {
            data object Default : Accent2
            data object Emphasized : Accent2
            data object Muted : Accent2
        }

        sealed interface Accent3 : Decorative {
            data object Default : Accent3
            data object Emphasized : Accent3
            data object Muted : Accent3
        }

        sealed interface Accent4 : Decorative {
            data object Default : Accent4
            data object Emphasized : Accent4
            data object Muted : Accent4
        }

        sealed interface Accent5 : Decorative {
            data object Default : Accent5
            data object Emphasized : Accent5
            data object Muted : Accent5
        }

        sealed interface Brand : Decorative {
            data object Primary : Brand
            data object Secondary : Brand
            data object Tertiary : Brand
        }

        sealed interface Neutral : Decorative {
            data object Default : Neutral
            data object Emphasized : Neutral
            data object Muted : Neutral
        }

        sealed interface Skin : Decorative {
            data object Tint100 : Skin
            data object Tint200 : Skin
            data object Tint300 : Skin
            data object Tint400 : Skin
            data object Tint500 : Skin
            data object Tint600 : Skin
            data object Tint700 : Skin
            data object Tint800 : Skin
            data object Tint900 : Skin
        }
    }
}


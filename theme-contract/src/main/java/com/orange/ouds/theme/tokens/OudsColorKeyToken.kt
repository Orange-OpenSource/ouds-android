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

// Tokens version 0.6.1
// Generated by Tokenator

package com.orange.ouds.theme.tokens

import com.orange.ouds.foundation.InternalOudsApi

@InternalOudsApi
sealed interface OudsColorKeyToken : OudsKeyToken {
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
        data object Muted : Border
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
            data object EmphasizedAlt : OnStatus
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
            sealed interface Emphasized : Neutral {
                data object Higher : Emphasized
                data object Low : Emphasized
                data object Lower : Emphasized
                data object Lowest : Emphasized
            }
            sealed interface Muted : Neutral {
                data object High : Muted
                data object Higher : Muted
                data object Highest : Muted
                data object Low : Muted
                data object Lower : Muted
                data object Lowest : Muted
                data object Medium : Muted
            }
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
    sealed interface Repository : OudsColorKeyToken {
        sealed interface Accent : Repository {
            data object Default : Accent
            data object Highest : Accent
            data object Low : Accent
            data object Lowest : Accent
        }
        sealed interface Info : Repository {
            data object Default : Info
            data object Highest : Info
            data object Low : Info
            data object Lowest : Info
        }
        sealed interface Negative : Repository {
            data object Default : Negative
            data object High : Negative
            data object Higher : Negative
            data object Highest : Negative
            data object Low : Negative
            data object Lower : Negative
            data object Lowest : Negative
        }
        sealed interface Neutral : Repository {
            sealed interface Emphasized : Neutral {
                data object Black : Emphasized
                data object High : Emphasized
                data object Higher : Emphasized
                data object Highest : Emphasized
                data object Medium : Emphasized
            }
            sealed interface Muted : Neutral {
                data object Lower : Muted
                data object Lowest : Muted
                data object White : Muted
            }
        }
        sealed interface Opacity : Repository {
            sealed interface Black : Opacity {
                data object High : Black
                data object Higher : Black
                data object Highest : Black
                data object Low : Black
                data object Lower : Black
                data object Lowest : Black
                data object Medium : Black
                data object Soft : Black
                data object Transparent : Black
            }
            data object Info : Opacity
            data object Negative : Opacity
            data object Positive : Opacity
            data object Warning : Opacity
            sealed interface White : Opacity {
                data object High : White
                data object Higher : White
                data object Highest : White
                data object Low : White
                data object Lower : White
                data object Lowest : White
                data object Medium : White
                data object Transparent : White
            }
        }
        sealed interface Positive : Repository {
            data object Default : Positive
            data object High : Positive
            data object Highest : Positive
            data object Low : Positive
            data object Lowest : Positive
        }
        sealed interface Primary : Repository {
            data object Default : Primary
            data object Low : Primary
        }
        sealed interface Warning : Repository {
            data object Default : Warning
            data object Highest : Warning
            data object Low : Warning
            data object Lowest : Warning
        }
    }
}


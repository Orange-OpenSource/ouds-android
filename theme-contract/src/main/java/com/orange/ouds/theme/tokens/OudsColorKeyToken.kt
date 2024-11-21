/*
 * Software Name: OUDS Android
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
 */

package com.orange.ouds.theme.tokens


sealed interface OudsColorKeyToken {

    sealed interface Transparent : OudsColorKeyToken {
        data object Default : Transparent
    }

    sealed interface Action : OudsColorKeyToken {
        sealed interface Disabled : Action {
            companion object : Disabled
            data object OnBgEmphasized : Disabled
        }

        sealed interface Negative : Action {
            data object Enabled : Negative
            data object Focus : Negative
            data object Hover : Negative
            data object Loading : Negative
            data object Pressed : Negative
        }

        sealed interface Primary : Action {
            sealed interface Enabled : Primary {
                companion object : Enabled
                data object OnBgEmphasized : Enabled
            }

            sealed interface Focus : Primary {
                companion object : Focus
                data object OnBgEmphasized : Focus
            }

            sealed interface Hover : Primary {
                companion object : Hover
                data object OnBgEmphasized : Hover
            }

            sealed interface Loading : Primary {
                companion object : Loading
                data object OnBgEmphasized : Loading
            }

            sealed interface Pressed : Primary {
                companion object : Pressed
                data object OnBgEmphasized : Pressed
            }
        }

        sealed interface Secondary : Action {
            sealed interface Enabled : Secondary {
                companion object : Enabled
                data object OnBgEmphasized : Enabled
            }

            sealed interface Focus : Secondary {
                companion object : Focus
                data object OnBgEmphasized : Focus
            }

            sealed interface Hover : Secondary {
                companion object : Hover
                data object OnBgEmphasized : Hover
            }

            sealed interface Loading : Secondary {
                companion object : Loading
                data object OnBgEmphasized : Loading
            }

            sealed interface Pressed : Secondary {
                companion object : Pressed
                data object OnBgEmphasized : Pressed
            }
        }

        sealed interface Selected : Action {
            companion object : Selected
            data object OnBgEmphasized : Selected
        }

        sealed interface Visited : Action {
            companion object : Visited
            data object OnBgEmphasized : Visited
        }
    }

    sealed interface Always : OudsColorKeyToken {
        data object Accent : Always
        data object Black : Always
        data object Info : Always
        data object Negative : Always
        data object OnAccent : Always
        data object OnBlack : Always
        data object OnInfo : Always
        data object OnNegative : Always
        data object OnPositive : Always
        data object OnWarning : Always
        data object OnWhite : Always
        data object Positive : Always
        data object Warning : Always
        data object White : Always
    }

    sealed interface Background : OudsColorKeyToken {
        data object Emphasized : Background
        data object Primary : Background
        data object Secondary : Background
        data object Tertiary : Background

        sealed interface Brand : Background {
            data object Primary : Brand
        }

        sealed interface Status : Background {
            sealed interface Accent : Status {
                data object Emphasized : Accent
                sealed interface Muted : Accent {
                    companion object : Muted
                    data object OnBgEmphasized : Muted
                }
            }

            sealed interface Info : Background {
                data object Emphasized : Info
                sealed interface Muted : Info {
                    companion object : Muted
                    data object OnBgEmphasized : Muted
                }
            }

            sealed interface Negative : Background {
                data object Emphasized : Negative
                sealed interface Muted : Negative {
                    companion object : Muted
                    data object OnBgEmphasized : Muted
                }
            }

            sealed interface Neutral : Background {
                companion object : Neutral
                data object OnBgEmphasized : Neutral
            }

            sealed interface Positive : Background {
                data object Emphasized : Positive
                sealed interface Muted : Positive {
                    companion object : Muted
                    data object OnBgEmphasized : Muted
                }
            }

            sealed interface Warning : Background {
                data object Emphasized : Warning
                sealed interface Muted : Warning {
                    companion object : Muted
                    data object OnBgEmphasized : Muted
                }
            }
        }
    }

    sealed interface Border : OudsColorKeyToken {
        sealed interface Brand : Border {
            sealed interface Primary : Brand {
                companion object : Primary
                data object OnBgEmphasized : Primary
            }
        }

        sealed interface Default : Border {
            companion object : Default
            data object OnBgEmphasized : Default
        }

        sealed interface Emphasized : Border {
            companion object : Emphasized
            data object OnBgEmphasized : Emphasized
        }

        sealed interface Focus : Border {
            companion object : Focus

            sealed interface Inset : Focus {
                companion object : Inset
                data object OnBgEmphasized : Inset
            }

            data object OnBgEmphasized : Focus
        }

        sealed interface OnBrand : Border {
            data object Primary : OnBrand
        }
    }

    sealed interface Brand : OudsColorKeyToken {
        sealed interface Accent : Brand {
            data object Default : Accent
            data object High : Accent
            data object Highest : Accent
            data object Lowest : Accent
        }

        sealed interface Info : Brand {
            data object Default : Info
            data object Highest : Info
            data object Lowest : Info
        }

        sealed interface Negative : Brand {
            data object Default : Negative
            data object High : Negative
            data object Higher : Negative
            data object Highest : Negative
            data object Lowest : Negative
        }

        sealed interface Neutral : Brand {
            sealed interface Emphasized : Neutral {
                data object Black : Emphasized
                data object High : Emphasized
                data object Higher : Emphasized
                data object Highest : Emphasized
                data object Low : Emphasized
                data object Lower : Emphasized
                data object Lowest : Emphasized
                data object Medium : Emphasized
            }

            sealed interface Muted : Neutral {
                data object Highest : Muted
                data object Low : Muted
                data object Lower : Muted
                data object Lowest : Muted
                data object Medium : Muted
                data object White : Muted
            }
        }

        sealed interface Positive : Brand {
            data object Default : Positive
            data object Highest : Positive
            data object Lowest : Positive
        }

        sealed interface Primary : Brand {
            data object Default : Primary
            data object Low : Primary
        }

        sealed interface Warning : Brand {
            data object Default : Warning
            data object High : Warning
            data object Highest : Warning
            data object Lowest : Warning
        }
    }

    sealed interface Content : OudsColorKeyToken {
        sealed interface Brand : Content {
            sealed interface Primary : Brand {
                companion object : Primary
                data object OnBgEmphasized : Primary
            }
        }

        sealed interface Default : Content {
            companion object : Default
            data object OnBgEmphasized : Default
        }

        sealed interface Disabled : Content {
            companion object : Disabled
            data object OnBgEmphasized : Disabled
        }

        sealed interface Muted : Content {
            companion object : Muted
            data object OnBgEmphasized : Muted
        }

        sealed interface OnAction : Content {
            sealed interface Disabled : OnAction {
                companion object : Disabled
                data object OnBgEmphasized : Disabled
            }

            data object Negative : OnAction

            sealed interface Primary : OnAction {
                sealed interface Enabled : Primary {
                    companion object : Enabled
                    data object OnBgEmphasized : Enabled
                }

                sealed interface Focus : Primary {
                    companion object : Focus
                    data object OnBgEmphasized : Focus
                }

                sealed interface Hover : Primary {
                    companion object : Hover
                    data object OnBgEmphasized : Hover
                }

                sealed interface Loading : Primary {
                    companion object : Loading
                    data object OnBgEmphasized : Loading
                }

                sealed interface Pressed : Primary {
                    companion object : Pressed
                    data object OnBgEmphasized : Pressed
                }
            }
        }

        sealed interface OnBrand : Content {
            data object Primary : OnBrand
        }

        sealed interface OnStatus : Content {
            sealed interface Accent : OnStatus {
                data object Emphasized : Accent
                sealed interface Muted : Accent {
                    companion object : Muted
                    data object OnBgEmphasized : Muted
                }
            }

            sealed interface Info : OnStatus {
                data object Emphasized : Info
                sealed interface Muted : Info {
                    companion object : Muted
                    data object OnBgEmphasized : Muted
                }
            }

            sealed interface Negative : OnStatus {
                data object Emphasized : Negative
                sealed interface Muted : Negative {
                    companion object : Muted
                    data object OnBgEmphasized : Muted
                }
            }

            sealed interface Positive : OnStatus {
                data object Emphasized : Positive
                sealed interface Muted : Positive {
                    companion object : Muted
                    data object OnBgEmphasized : Muted
                }
            }

            sealed interface Warning : OnStatus {
                data object Emphasized : Warning
                sealed interface Muted : Warning {
                    companion object : Muted
                    data object OnBgEmphasized : Muted
                }
            }
        }

        sealed interface Status : Content {
            data object Info : Status
            data object Negative : Status
            data object Positive : Status
            data object Warning : Status
        }
    }

    sealed interface Elevation : OudsColorKeyToken {
        sealed interface Drag : Elevation {
            companion object : Drag
            data object OnBgEmphasized : Drag
            data object OnBgSecondary : Drag
        }

        data object Modal : Elevation

        sealed interface Overlay : Elevation {
            sealed interface Default : Overlay {
                companion object : Default
                data object OnBgEmphasized : Default
                data object OnBgSecondary : Default
            }

            sealed interface Emphasized : Overlay {
                companion object : Emphasized
                data object OnBgEmphasized : Emphasized
                data object OnBgSecondary : Emphasized
            }
        }

        sealed interface Raised : Elevation {
            companion object : Raised
            data object OnBgEmphasized : Raised
            data object OnBgSecondary : Raised
        }
    }

    sealed interface Gradient : OudsColorKeyToken {
        sealed interface Skeleton : Gradient {
            sealed interface Middle : Skeleton {
                companion object : Skeleton
                data object OnBgEmphasized : Skeleton
            }

            sealed interface StartEnd : Skeleton {
                companion object : Skeleton
                data object OnBgEmphasized : Skeleton
            }
        }
    }

    sealed interface Decorative : OudsColorKeyToken {
        sealed interface Accent : Decorative {
            sealed interface One : Accent {
                data object Default : One
                data object Emphasized : One
                data object Muted : One
            }

            sealed interface Two : Accent {
                data object Default : Two
                data object Emphasized : Two
                data object Muted : Two
            }

            sealed interface Three : Accent {
                data object Default : Three
                data object Emphasized : Three
                data object Muted : Three
            }

            sealed interface Four : Accent {
                data object Default : Four
                data object Emphasized : Four
                data object Muted : Four
            }

            sealed interface Five : Accent {
                data object Default : Five
                data object Emphasized : Five
                data object Muted : Five
            }
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

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

package com.orange.ouds.core.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.foundation.InternalOudsApi
import com.orange.ouds.theme.tokens.OudsElevationKeyToken
import com.orange.ouds.theme.tokens.semantic.OudsElevationSemanticTokens

/**
 * Holds the elevation values defined in the OUDS theme.
 *
 * Elevation is used to create a sense of depth and hierarchy in a design, helping to distinguish between layers and interactive elements.
 *
 * > Design guidelines: [Elevation tokens documentation](https://r.orange.fr/r/S-ouds-doc-token-elevation)
 *
 * @property none No elevation (0 dp). The element is at the same level as the background.
 * @property default The standard elevation level for components.
 * @property drag The elevation applied to an element when it is being dragged.
 * @property emphasized A higher elevation for emphasized elements.
 * @property raised A slight elevation to detach an element from the surface.
 * @property sticky The elevation used for sticky elements.
 */
data class OudsElevations(
    val none: Dp,
    val default: Dp,
    val drag: Dp,
    val emphasized: Dp,
    val raised: Dp,
    val sticky: Dp,
)

internal fun OudsElevationSemanticTokens.getElevations() = OudsElevations(
    none = none.dp,
    raised = raised.dp,
    default = default.dp,
    sticky = sticky.dp,
    drag = drag.dp,
    emphasized = emphasized.dp
)

@Stable
private fun OudsElevations.fromToken(token: OudsElevationKeyToken): Dp {
    return when (token) {
        OudsElevationKeyToken.None -> none
        OudsElevationKeyToken.Default -> default
        OudsElevationKeyToken.Drag -> drag
        OudsElevationKeyToken.Emphasized -> emphasized
        OudsElevationKeyToken.Sticky -> sticky
        OudsElevationKeyToken.Raised -> raised
    }
}

/**
 * Converts an OUDS elevation token to the local elevation value provided by the theme.
 *
 * @suppress
 */
@InternalOudsApi
val OudsElevationKeyToken.value: Dp
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.elevations.fromToken(this)

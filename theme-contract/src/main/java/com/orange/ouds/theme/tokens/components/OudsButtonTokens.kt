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

package com.orange.ouds.theme.tokens.components

import com.orange.ouds.theme.tokens.OudsBorderKeyToken
import com.orange.ouds.theme.tokens.OudsColorKeyToken
import com.orange.ouds.theme.tokens.OudsElevationKeyToken
import com.orange.ouds.theme.tokens.OudsSpaceKeyToken
import com.orange.ouds.theme.tokens.OudsTypographyKeyToken

open class OudsButtonTokens(
    val containerColor: OudsColorKeyToken = OudsColorKeyToken.BgBrandPrimary,
    val contentColor: OudsColorKeyToken = OudsColorKeyToken.ContentBrandPrimary,
    val disabledContainerColor: OudsColorKeyToken = OudsColorKeyToken.ActionDisabled,
    val disabledContentColor: OudsColorKeyToken = OudsColorKeyToken.ContentDisabled,
    val cornerRadius: OudsBorderKeyToken.Radius = OudsBorderKeyToken.Radius.None,
    val defaultElevation: OudsElevationKeyToken = OudsElevationKeyToken.None,
    val pressedElevation: OudsElevationKeyToken = OudsElevationKeyToken.None,
    val focusedElevation: OudsElevationKeyToken = OudsElevationKeyToken.None,
    val hoveredElevation: OudsElevationKeyToken = OudsElevationKeyToken.None,
    val disabledElevation: OudsElevationKeyToken = OudsElevationKeyToken.None,
    val labelStyle: OudsTypographyKeyToken = OudsTypographyKeyToken.BodyStrongLarge,
    val verticalContentPadding: OudsSpaceKeyToken.Fixed = OudsSpaceKeyToken.Fixed.Smash,
    val horizontalContentPadding: OudsSpaceKeyToken.Fixed = OudsSpaceKeyToken.Fixed.Tall
)
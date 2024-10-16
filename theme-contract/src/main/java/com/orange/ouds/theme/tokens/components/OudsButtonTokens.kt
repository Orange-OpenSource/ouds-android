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

import com.orange.ouds.theme.tokens.OudsBorderRadiusKeyToken
import com.orange.ouds.theme.tokens.OudsElevationKeyToken
import com.orange.ouds.theme.tokens.OudsSpacingFixedKeyToken
import com.orange.ouds.theme.tokens.OudsTypographyKeyToken
import com.orange.ouds.theme.tokens.semantic.OudsColorKeyToken

open class OudsButtonTokens(
    val containerColor: OudsColorKeyToken = OudsColorKeyToken.Primary,
    val contentColor: OudsColorKeyToken = OudsColorKeyToken.OnPrimary,
    val disabledContainerColor: OudsColorKeyToken = OudsColorKeyToken.Secondary,
    val disabledContentColor: OudsColorKeyToken = OudsColorKeyToken.OnSecondary,
    val cornerRadius: OudsBorderRadiusKeyToken = OudsBorderRadiusKeyToken.None,
    val defaultElevation: OudsElevationKeyToken = OudsElevationKeyToken.None,
    val pressedElevation: OudsElevationKeyToken = OudsElevationKeyToken.None,
    val focusedElevation: OudsElevationKeyToken = OudsElevationKeyToken.None,
    val hoveredElevation: OudsElevationKeyToken = OudsElevationKeyToken.None,
    val disabledElevation: OudsElevationKeyToken = OudsElevationKeyToken.None,
    val labelStyle: OudsTypographyKeyToken = OudsTypographyKeyToken.BodyStrongLarge,
    val verticalContentPadding: OudsSpacingFixedKeyToken = OudsSpacingFixedKeyToken.Smash,
    val horizontalContentPadding: OudsSpacingFixedKeyToken = OudsSpacingFixedKeyToken.Tall
)
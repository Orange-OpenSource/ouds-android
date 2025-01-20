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

package com.orange.ouds.theme.whitelabel.tokens.semantic

import androidx.compose.ui.graphics.Color
import com.orange.ouds.theme.tokens.semantic.OudsColorDecorativeSemanticTokens
import com.orange.ouds.theme.whitelabel.tokens.raw.ColorRawTokens
import com.orange.ouds.theme.whitelabel.tokens.raw.WhiteLabelColorRawTokens

data class WhiteLabelColorDecorativeSemanticTokens(
    override val decorativeAccent1DefaultLight: Color = ColorRawTokens.colorDecorativeEmerald500,
    override val decorativeAccent1EmphasizedLight: Color = ColorRawTokens.colorDecorativeEmerald700,
    override val decorativeAccent1MutedLight: Color = ColorRawTokens.colorDecorativeEmerald200,
    override val decorativeAccent1DefaultDark: Color = ColorRawTokens.colorDecorativeEmerald500,
    override val decorativeAccent1EmphasizedDark: Color = ColorRawTokens.colorDecorativeEmerald700,
    override val decorativeAccent1MutedDark: Color = ColorRawTokens.colorDecorativeEmerald200,
    override val decorativeAccent2DefaultLight: Color = ColorRawTokens.colorDecorativeSky400,
    override val decorativeAccent2EmphasizedLight: Color = ColorRawTokens.colorDecorativeSky700,
    override val decorativeAccent2MutedLight: Color = ColorRawTokens.colorDecorativeSky200,
    override val decorativeAccent2DefaultDark: Color = ColorRawTokens.colorDecorativeSky400,
    override val decorativeAccent2EmphasizedDark: Color = ColorRawTokens.colorDecorativeSky700,
    override val decorativeAccent2MutedDark: Color = ColorRawTokens.colorDecorativeSky200,
    override val decorativeAccent3DefaultLight: Color = ColorRawTokens.colorFunctionalSun500,
    override val decorativeAccent3EmphasizedLight: Color = ColorRawTokens.colorDecorativeAmber500,
    override val decorativeAccent3MutedLight: Color = ColorRawTokens.colorFunctionalSun200,
    override val decorativeAccent3DefaultDark: Color = ColorRawTokens.colorFunctionalSun500,
    override val decorativeAccent3EmphasizedDark: Color = ColorRawTokens.colorDecorativeAmber500,
    override val decorativeAccent3MutedDark: Color = ColorRawTokens.colorFunctionalSun200,
    override val decorativeAccent4DefaultLight: Color = ColorRawTokens.colorDecorativeAmethyst400,
    override val decorativeAccent4EmphasizedLight: Color = ColorRawTokens.colorDecorativeAmethyst800,
    override val decorativeAccent4MutedLight: Color = ColorRawTokens.colorDecorativeAmethyst200,
    override val decorativeAccent4DefaultDark: Color = ColorRawTokens.colorDecorativeAmethyst400,
    override val decorativeAccent4EmphasizedDark: Color = ColorRawTokens.colorDecorativeAmethyst800,
    override val decorativeAccent4MutedDark: Color = ColorRawTokens.colorDecorativeAmethyst200,
    override val decorativeAccent5DefaultLight: Color = ColorRawTokens.colorDecorativeShockingPink200,
    override val decorativeAccent5EmphasizedLight: Color = ColorRawTokens.colorDecorativeShockingPink300,
    override val decorativeAccent5MutedLight: Color = ColorRawTokens.colorDecorativeShockingPink100,
    override val decorativeAccent5DefaultDark: Color = ColorRawTokens.colorDecorativeShockingPink200,
    override val decorativeAccent5EmphasizedDark: Color = ColorRawTokens.colorDecorativeShockingPink300,
    override val decorativeAccent5MutedDark: Color = ColorRawTokens.colorDecorativeShockingPink100,
    override val decorativeBrandPrimaryLight: Color = WhiteLabelColorRawTokens.colorBlue500,
    override val decorativeBrandSecondaryLight: Color = ColorRawTokens.colorFunctionalBlack,
    override val decorativeBrandTertiaryLight: Color = ColorRawTokens.colorFunctionalWhite,
    override val decorativeBrandPrimaryDark: Color = WhiteLabelColorRawTokens.colorBlue500,
    override val decorativeBrandSecondaryDark: Color = ColorRawTokens.colorFunctionalLightGray160,
    override val decorativeBrandTertiaryDark: Color = ColorRawTokens.colorFunctionalDarkGray880,
    override val decorativeNeutralDefaultLight: Color = ColorRawTokens.colorFunctionalDarkGray400,
    override val decorativeNeutralEmphasizedLight: Color = ColorRawTokens.colorFunctionalDarkGray640,
    override val decorativeNeutralMutedLight: Color = ColorRawTokens.colorFunctionalLightGray160,
    override val decorativeNeutralDefaultDark: Color = ColorRawTokens.colorFunctionalLightGray560,
    override val decorativeNeutralEmphasizedDark: Color = ColorRawTokens.colorFunctionalLightGray160,
    override val decorativeNeutralMutedDark: Color = ColorRawTokens.colorFunctionalDarkGray720,
    override val decorativeSkinTint100Light: Color = ColorRawTokens.colorDecorativeDeepPeach100,
    override val decorativeSkinTint200Light: Color = ColorRawTokens.colorDecorativeDeepPeach200,
    override val decorativeSkinTint300Light: Color = ColorRawTokens.colorDecorativeDeepPeach300,
    override val decorativeSkinTint400Light: Color = ColorRawTokens.colorDecorativeDeepPeach400,
    override val decorativeSkinTint500Light: Color = ColorRawTokens.colorDecorativeDeepPeach500,
    override val decorativeSkinTint600Light: Color = ColorRawTokens.colorDecorativeDeepPeach600,
    override val decorativeSkinTint700Light: Color = ColorRawTokens.colorDecorativeDeepPeach700,
    override val decorativeSkinTint800Light: Color = ColorRawTokens.colorDecorativeDeepPeach800,
    override val decorativeSkinTint900Light: Color = ColorRawTokens.colorDecorativeDeepPeach900,
    override val decorativeSkinTint100Dark: Color = ColorRawTokens.colorDecorativeDeepPeach100,
    override val decorativeSkinTint200Dark: Color = ColorRawTokens.colorDecorativeDeepPeach200,
    override val decorativeSkinTint300Dark: Color = ColorRawTokens.colorDecorativeDeepPeach300,
    override val decorativeSkinTint400Dark: Color = ColorRawTokens.colorDecorativeDeepPeach400,
    override val decorativeSkinTint500Dark: Color = ColorRawTokens.colorDecorativeDeepPeach500,
    override val decorativeSkinTint600Dark: Color = ColorRawTokens.colorDecorativeDeepPeach600,
    override val decorativeSkinTint700Dark: Color = ColorRawTokens.colorDecorativeDeepPeach700,
    override val decorativeSkinTint800Dark: Color = ColorRawTokens.colorDecorativeDeepPeach800,
    override val decorativeSkinTint900Dark: Color = ColorRawTokens.colorDecorativeDeepPeach900
) : OudsColorDecorativeSemanticTokens

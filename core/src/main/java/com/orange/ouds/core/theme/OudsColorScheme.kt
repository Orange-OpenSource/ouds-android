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

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import com.orange.ouds.theme.tokens.OudsColorKeyToken
import com.orange.ouds.theme.tokens.semantic.OudsColorSemanticTokens

data class OudsColorScheme(
    val transparentColors: Transparent,
    val actionColors: Action,
    val alwaysColors: Always,
    val backgroundColors: Background,
    val borderColors: Border,
    val brandColors: Brand,
    val contentColors: Content,
    val elevationColors: Elevation,
    val gradientColors: Gradient,
    val decorativeColors: Decorative,
) {

    data class Transparent(
        val transparentDefault: Color
    )

    data class Action(
        val disabled: Color,
        val disabledOnBgEmphasized: Color,
        val negativeEnabled: Color,
        val negativeFocus: Color,
        val negativeHover: Color,
        val negativeLoading: Color,
        val negativePressed: Color,
        val primaryEnabled: Color,
        val primaryEnabledOnBgEmphasized: Color,
        val primaryFocus: Color,
        val primaryFocusOnBgEmphasized: Color,
        val primaryHover: Color,
        val primaryHoverOnBgEmphasized: Color,
        val primaryLoading: Color,
        val primaryLoadingOnBgEmphasized: Color,
        val primaryPressed: Color,
        val primaryPressedOnBgEmphasized: Color,
        val secondaryEnabled: Color,
        val secondaryEnabledOnBgEmphasized: Color,
        val secondaryFocus: Color,
        val secondaryFocusOnBgEmphasized: Color,
        val secondaryHover: Color,
        val secondaryHoverOnBgEmphasized: Color,
        val secondaryLoading: Color,
        val secondaryLoadingOnBgEmphasized: Color,
        val secondaryPressed: Color,
        val secondaryPressedOnBgEmphasized: Color,
        val selected: Color,
        val selectedOnBgEmphasized: Color,
        val visited: Color,
        val visitedOnBgEmphasized: Color,
    )

    data class Always(
        val accent: Color,
        val black: Color,
        val info: Color,
        val negative: Color,
        val onAccent: Color,
        val onBlack: Color,
        val onInfo: Color,
        val onNegative: Color,
        val onPositive: Color,
        val onWarning: Color,
        val onWhite: Color,
        val positive: Color,
        val warning: Color,
        val white: Color,
    )

    data class Background(
        val brandPrimary: Color,
        val emphasized: Color,
        val primary: Color,
        val secondary: Color,
        val statusAccentEmphasized: Color,
        val statusAccentMuted: Color,
        val statusAccentMutedOnBgEmphasized: Color,
        val statusInfoEmphasized: Color,
        val statusInfoMuted: Color,
        val statusInfoMutedOnBgEmphasized: Color,
        val statusNegativeEmphasized: Color,
        val statusNegativeMuted: Color,
        val statusNegativeMutedOnBgEmphasized: Color,
        val statusNeutral: Color,
        val statusNeutralOnBgEmphasized: Color,
        val statusPositiveEmphasized: Color,
        val statusPositiveMuted: Color,
        val statusPositiveMutedOnBgEmphasized: Color,
        val statusWarningEmphasized: Color,
        val statusWarningMuted: Color,
        val statusWarningMutedOnBgEmphasized: Color,
        val tertiary: Color,
    )

    data class Border(
        val brandPrimary: Color,
        val brandPrimaryOnBgEmphasized: Color,
        val default: Color,
        val defaultOnBgEmphasized: Color,
        val emphasized: Color,
        val emphasizedOnBgEmphasized: Color,
        val focus: Color,
        val focusInset: Color,
        val focusInsetOnBgEmphasized: Color,
        val focusOnBgEmphasized: Color,
        val onBrandPrimary: Color,
    )

    data class Brand(
        val accentDefault: Color,
        val accentHigh: Color,
        val accentHighest: Color,
        val accentLowest: Color,
        val infoDefault: Color,
        val infoHighest: Color,
        val infoLowest: Color,
        val negativeDefault: Color,
        val negativeHigh: Color,
        val negativeHigher: Color,
        val negativeHighest: Color,
        val negativeLowest: Color,
        val neutralEmphasizedBlack: Color,
        val neutralEmphasizedHigh: Color,
        val neutralEmphasizedHigher: Color,
        val neutralEmphasizedHighest: Color,
        val neutralEmphasizedLow: Color,
        val neutralEmphasizedLower: Color,
        val neutralEmphasizedLowest: Color,
        val neutralEmphasizedMedium: Color,
        val neutralMutedHighest: Color,
        val neutralMutedLow: Color,
        val neutralMutedLower: Color,
        val neutralMutedLowest: Color,
        val neutralMutedMedium: Color,
        val neutralMutedWhite: Color,
        val positiveDefault: Color,
        val positiveHighest: Color,
        val positiveLowest: Color,
        val primaryDefault: Color,
        val primaryLow: Color,
        val warningDefault: Color,
        val warningHigh: Color,
        val warningHighest: Color,
        val warningLowest: Color,
    )

    data class Content(
        val brandPrimary: Color,
        val brandPrimaryOnBgEmphasized: Color,
        val default: Color,
        val defaultOnBgEmphasized: Color,
        val disabled: Color,
        val disabledOnBgEmphasized: Color,
        val muted: Color,
        val mutedOnBgEmphasized: Color,
        val onActionDisabled: Color,
        val onActionDisabledOnBgEmphasized: Color,
        val onActionNegative: Color,
        val onActionPrimaryEnabled: Color,
        val onActionPrimaryEnabledOnBgEmphasized: Color,
        val onActionPrimaryFocus: Color,
        val onActionPrimaryFocusOnBgEmphasized: Color,
        val onActionPrimaryHover: Color,
        val onActionPrimaryHoverOnBgEmphasized: Color,
        val onActionPrimaryLoading: Color,
        val onActionPrimaryLoadingOnBgEmphasized: Color,
        val onActionPrimaryPressed: Color,
        val onActionPrimaryPressedOnBgEmphasized: Color,
        val onBrandPrimary: Color,
        val onStatusAccentEmphasized: Color,
        val onStatusAccentMuted: Color,
        val onStatusAccentMutedOnBgEmphasized: Color,
        val onStatusInfoEmphasized: Color,
        val onStatusInfoMuted: Color,
        val onStatusInfoMutedOnBgEmphasized: Color,
        val onStatusNegativeEmphasized: Color,
        val onStatusNegativeMuted: Color,
        val onStatusNegativeMutedOnBgEmphasized: Color,
        val onStatusPositiveEmphasized: Color,
        val onStatusPositiveMuted: Color,
        val onStatusPositiveMutedOnBgEmphasized: Color,
        val onStatusWarningEmphasized: Color,
        val onStatusWarningMuted: Color,
        val onStatusWarningMutedOnBgEmphasized: Color,
        val statusInfo: Color,
        val statusNegative: Color,
        val statusPositive: Color,
        val statusWarning: Color,
    )

    data class Elevation(
        val drag: Color,
        val dragOnBgEmphasized: Color,
        val dragOnBgSecondary: Color,
        val modal: Color,
        val overlayDefault: Color,
        val overlayDefaultOnBgEmphasized: Color,
        val overlayDefaultOnBgSecondary: Color,
        val overlayEmphasized: Color,
        val overlayEmphasizedOnBgEmphasized: Color,
        val overlayEmphasizedOnBgSecondary: Color,
        val raised: Color,
        val raisedOnBgEmphasized: Color,
        val raisedOnBgSecondary: Color,
    )

    data class Gradient(
        val skeletonMiddle: Color,
        val skeletonMiddleOnBgEmphasized: Color,
        val skeletonStartEnd: Color,
        val skeletonStartEndOnBgEmphasized: Color,
    )

    data class Decorative(
        val accent1Default: Color,
        val accent1Emphasized: Color,
        val accent1Muted: Color,
        val accent2Default: Color,
        val accent2Emphasized: Color,
        val accent2Muted: Color,
        val accent3Default: Color,
        val accent3Emphasized: Color,
        val accent3Muted: Color,
        val accent4Default: Color,
        val accent4Emphasized: Color,
        val accent4Muted: Color,
        val accent5Default: Color,
        val accent5Emphasized: Color,
        val accent5Muted: Color,
        val brandPrimary: Color,
        val brandSecondary: Color,
        val brandTertiary: Color,
        val neutralDefault: Color,
        val neutralEmphasized: Color,
        val neutralMuted: Color,
        val skinTint100: Color,
        val skinTint200: Color,
        val skinTint300: Color,
        val skinTint400: Color,
        val skinTint500: Color,
        val skinTint600: Color,
        val skinTint700: Color,
        val skinTint800: Color,
        val skinTint900: Color,
    )
}

val OudsColorSemanticTokens.lightColorScheme: OudsColorScheme
    get() = OudsColorScheme(
        transparentColors = OudsColorScheme.Transparent(
            transparentDefault = transparentColorTokens.transparentDefaultLight,
        ),
        actionColors = OudsColorScheme.Action(
            disabled = actionColorTokens.actionDisabledLight,
            disabledOnBgEmphasized = actionColorTokens.actionDisabledOnBgEmphasizedLight,
            negativeEnabled = actionColorTokens.actionNegativeEnabledLight,
            negativeFocus = actionColorTokens.actionNegativeFocusLight,
            negativeHover = actionColorTokens.actionNegativeHoverLight,
            negativeLoading = actionColorTokens.actionNegativeLoadingLight,
            negativePressed = actionColorTokens.actionNegativePressedLight,
            primaryEnabled = actionColorTokens.actionPrimaryEnabledLight,
            primaryEnabledOnBgEmphasized = actionColorTokens.actionPrimaryEnabledOnBgEmphasizedLight,
            primaryFocus = actionColorTokens.actionPrimaryFocusLight,
            primaryFocusOnBgEmphasized = actionColorTokens.actionPrimaryFocusOnBgEmphasizedLight,
            primaryHover = actionColorTokens.actionPrimaryHoverLight,
            primaryHoverOnBgEmphasized = actionColorTokens.actionPrimaryHoverOnBgEmphasizedLight,
            primaryLoading = actionColorTokens.actionPrimaryLoadingLight,
            primaryLoadingOnBgEmphasized = actionColorTokens.actionPrimaryLoadingOnBgEmphasizedLight,
            primaryPressed = actionColorTokens.actionPrimaryPressedLight,
            primaryPressedOnBgEmphasized = actionColorTokens.actionPrimaryPressedOnBgEmphasizedLight,
            secondaryEnabled = actionColorTokens.actionSecondaryEnabledLight,
            secondaryEnabledOnBgEmphasized = actionColorTokens.actionSecondaryEnabledOnBgEmphasizedLight,
            secondaryFocus = actionColorTokens.actionSecondaryFocusLight,
            secondaryFocusOnBgEmphasized = actionColorTokens.actionSecondaryFocusOnBgEmphasizedLight,
            secondaryHover = actionColorTokens.actionSecondaryHoverLight,
            secondaryHoverOnBgEmphasized = actionColorTokens.actionSecondaryHoverOnBgEmphasizedLight,
            secondaryLoading = actionColorTokens.actionSecondaryLoadingLight,
            secondaryLoadingOnBgEmphasized = actionColorTokens.actionSecondaryLoadingOnBgEmphasizedLight,
            secondaryPressed = actionColorTokens.actionSecondaryPressedLight,
            secondaryPressedOnBgEmphasized = actionColorTokens.actionSecondaryPressedOnBgEmphasizedLight,
            selected = actionColorTokens.actionSelectedLight,
            selectedOnBgEmphasized = actionColorTokens.actionSelectedOnBgEmphasizedLight,
            visited = actionColorTokens.actionVisitedLight,
            visitedOnBgEmphasized = actionColorTokens.actionVisitedOnBgEmphasizedLight,
        ),
        alwaysColors = OudsColorScheme.Always(
            accent = alwaysColorTokens.alwaysAccentLight,
            black = alwaysColorTokens.alwaysBlackLight,
            info = alwaysColorTokens.alwaysInfoLight,
            negative = alwaysColorTokens.alwaysNegativeLight,
            onAccent = alwaysColorTokens.alwaysOnAccentLight,
            onBlack = alwaysColorTokens.alwaysOnBlackLight,
            onInfo = alwaysColorTokens.alwaysOnInfoLight,
            onNegative = alwaysColorTokens.alwaysOnNegativeLight,
            onPositive = alwaysColorTokens.alwaysOnPositiveLight,
            onWarning = alwaysColorTokens.alwaysOnWarningLight,
            onWhite = alwaysColorTokens.alwaysOnWhiteLight,
            positive = alwaysColorTokens.alwaysPositiveLight,
            warning = alwaysColorTokens.alwaysWarningLight,
            white = alwaysColorTokens.alwaysWhiteLight,
        ),
        backgroundColors = OudsColorScheme.Background(
            brandPrimary = backgroundColorTokens.bgBrandPrimaryLight,
            emphasized = backgroundColorTokens.bgEmphasizedLight,
            primary = backgroundColorTokens.bgPrimaryLight,
            secondary = backgroundColorTokens.bgSecondaryLight,
            statusAccentEmphasized = backgroundColorTokens.bgStatusAccentEmphasizedLight,
            statusAccentMuted = backgroundColorTokens.bgStatusAccentMutedLight,
            statusAccentMutedOnBgEmphasized = backgroundColorTokens.bgStatusAccentMutedOnBgEmphasizedLight,
            statusInfoEmphasized = backgroundColorTokens.bgStatusInfoEmphasizedLight,
            statusInfoMuted = backgroundColorTokens.bgStatusInfoMutedLight,
            statusInfoMutedOnBgEmphasized = backgroundColorTokens.bgStatusInfoMutedOnBgEmphasizedLight,
            statusNegativeEmphasized = backgroundColorTokens.bgStatusNegativeEmphasizedLight,
            statusNegativeMuted = backgroundColorTokens.bgStatusNegativeMutedLight,
            statusNegativeMutedOnBgEmphasized = backgroundColorTokens.bgStatusNegativeMutedOnBgEmphasizedLight,
            statusNeutral = backgroundColorTokens.bgStatusNeutralLight,
            statusNeutralOnBgEmphasized = backgroundColorTokens.bgStatusNeutralOnBgEmphasizedLight,
            statusPositiveEmphasized = backgroundColorTokens.bgStatusPositiveEmphasizedLight,
            statusPositiveMuted = backgroundColorTokens.bgStatusPositiveMutedLight,
            statusPositiveMutedOnBgEmphasized = backgroundColorTokens.bgStatusPositiveMutedOnBgEmphasizedLight,
            statusWarningEmphasized = backgroundColorTokens.bgStatusWarningEmphasizedLight,
            statusWarningMuted = backgroundColorTokens.bgStatusWarningMutedLight,
            statusWarningMutedOnBgEmphasized = backgroundColorTokens.bgStatusWarningMutedOnBgEmphasizedLight,
            tertiary = backgroundColorTokens.bgTertiaryLight,
        ),
        borderColors = OudsColorScheme.Border(
            brandPrimary = borderColorTokens.borderBrandPrimaryLight,
            brandPrimaryOnBgEmphasized = borderColorTokens.borderBrandPrimaryOnBgEmphasizedLight,
            default = borderColorTokens.borderDefaultLight,
            defaultOnBgEmphasized = borderColorTokens.borderDefaultOnBgEmphasizedLight,
            emphasized = borderColorTokens.borderEmphasizedLight,
            emphasizedOnBgEmphasized = borderColorTokens.borderEmphasizedOnBgEmphasizedLight,
            focus = borderColorTokens.borderFocusLight,
            focusInset = borderColorTokens.borderFocusInsetLight,
            focusInsetOnBgEmphasized = borderColorTokens.borderFocusInsetOnBgEmphasizedLight,
            focusOnBgEmphasized = borderColorTokens.borderFocusOnBgEmphasizedLight,
            onBrandPrimary = borderColorTokens.borderOnBrandPrimaryLight,
        ),
        brandColors = OudsColorScheme.Brand(
            accentDefault = brandColorTokens.brandAccentDefaultLight,
            accentHigh = brandColorTokens.brandAccentHighLight,
            accentHighest = brandColorTokens.brandAccentHighestLight,
            accentLowest = brandColorTokens.brandAccentLowestLight,
            infoDefault = brandColorTokens.brandInfoDefaultLight,
            infoHighest = brandColorTokens.brandInfoHighestLight,
            infoLowest = brandColorTokens.brandInfoLowestLight,
            negativeDefault = brandColorTokens.brandNegativeDefaultLight,
            negativeHigh = brandColorTokens.brandNegativeHighLight,
            negativeHigher = brandColorTokens.brandNegativeHigherLight,
            negativeHighest = brandColorTokens.brandNegativeHighestLight,
            negativeLowest = brandColorTokens.brandNegativeLowestLight,
            neutralEmphasizedBlack = brandColorTokens.brandNeutralEmphasizedBlackLight,
            neutralEmphasizedHigh = brandColorTokens.brandNeutralEmphasizedHighLight,
            neutralEmphasizedHigher = brandColorTokens.brandNeutralEmphasizedHigherLight,
            neutralEmphasizedHighest = brandColorTokens.brandNeutralEmphasizedHighestLight,
            neutralEmphasizedLow = brandColorTokens.brandNeutralEmphasizedLowLight,
            neutralEmphasizedLower = brandColorTokens.brandNeutralEmphasizedLowerLight,
            neutralEmphasizedLowest = brandColorTokens.brandNeutralEmphasizedLowestLight,
            neutralEmphasizedMedium = brandColorTokens.brandNeutralEmphasizedMediumLight,
            neutralMutedHighest = brandColorTokens.brandNeutralMutedHighestLight,
            neutralMutedLow = brandColorTokens.brandNeutralMutedLowLight,
            neutralMutedLower = brandColorTokens.brandNeutralMutedLowerLight,
            neutralMutedLowest = brandColorTokens.brandNeutralMutedLowestLight,
            neutralMutedMedium = brandColorTokens.brandNeutralMutedMediumLight,
            neutralMutedWhite = brandColorTokens.brandNeutralMutedWhiteLight,
            positiveDefault = brandColorTokens.brandPositiveDefaultLight,
            positiveHighest = brandColorTokens.brandPositiveHighestLight,
            positiveLowest = brandColorTokens.brandPositiveLowestLight,
            primaryDefault = brandColorTokens.brandPrimaryDefaultLight,
            primaryLow = brandColorTokens.brandPrimaryLowLight,
            warningDefault = brandColorTokens.brandWarningDefaultLight,
            warningHigh = brandColorTokens.brandWarningHighLight,
            warningHighest = brandColorTokens.brandWarningHighestLight,
            warningLowest = brandColorTokens.brandWarningLowestLight,
        ),
        contentColors = OudsColorScheme.Content(
            brandPrimary = contentColorTokens.contentBrandPrimaryLight,
            brandPrimaryOnBgEmphasized = contentColorTokens.contentBrandPrimaryOnBgEmphasizedLight,
            default = contentColorTokens.contentDefaultLight,
            defaultOnBgEmphasized = contentColorTokens.contentDefaultOnBgEmphasizedLight,
            disabled = contentColorTokens.contentDisabledLight,
            disabledOnBgEmphasized = contentColorTokens.contentDisabledOnBgEmphasizedLight,
            muted = contentColorTokens.contentMutedLight,
            mutedOnBgEmphasized = contentColorTokens.contentMutedOnBgEmphasizedLight,
            onActionDisabled = contentColorTokens.contentOnActionDisabledLight,
            onActionDisabledOnBgEmphasized = contentColorTokens.contentOnActionDisabledOnBgEmphasizedLight,
            onActionNegative = contentColorTokens.contentOnActionNegativeLight,
            onActionPrimaryEnabled = contentColorTokens.contentOnActionPrimaryEnabledLight,
            onActionPrimaryEnabledOnBgEmphasized = contentColorTokens.contentOnActionPrimaryEnabledOnBgEmphasizedLight,
            onActionPrimaryFocus = contentColorTokens.contentOnActionPrimaryFocusLight,
            onActionPrimaryFocusOnBgEmphasized = contentColorTokens.contentOnActionPrimaryFocusOnBgEmphasizedLight,
            onActionPrimaryHover = contentColorTokens.contentOnActionPrimaryHoverLight,
            onActionPrimaryHoverOnBgEmphasized = contentColorTokens.contentOnActionPrimaryHoverOnBgEmphasizedLight,
            onActionPrimaryLoading = contentColorTokens.contentOnActionPrimaryLoadingLight,
            onActionPrimaryLoadingOnBgEmphasized = contentColorTokens.contentOnActionPrimaryLoadingOnBgEmphasizedLight,
            onActionPrimaryPressed = contentColorTokens.contentOnActionPrimaryPressedLight,
            onActionPrimaryPressedOnBgEmphasized = contentColorTokens.contentOnActionPrimaryPressedOnBgEmphasizedLight,
            onBrandPrimary = contentColorTokens.contentOnBrandPrimaryLight,
            onStatusAccentEmphasized = contentColorTokens.contentOnStatusAccentEmphasizedLight,
            onStatusAccentMuted = contentColorTokens.contentOnStatusAccentMutedLight,
            onStatusAccentMutedOnBgEmphasized = contentColorTokens.contentOnStatusAccentMutedOnBgEmphasizedLight,
            onStatusInfoEmphasized = contentColorTokens.contentOnStatusInfoEmphasizedLight,
            onStatusInfoMuted = contentColorTokens.contentOnStatusInfoMutedLight,
            onStatusInfoMutedOnBgEmphasized = contentColorTokens.contentOnStatusInfoMutedOnBgEmphasizedLight,
            onStatusNegativeEmphasized = contentColorTokens.contentOnStatusNegativeEmphasizedLight,
            onStatusNegativeMuted = contentColorTokens.contentOnStatusNegativeMutedLight,
            onStatusNegativeMutedOnBgEmphasized = contentColorTokens.contentOnStatusNegativeMutedOnBgEmphasizedLight,
            onStatusPositiveEmphasized = contentColorTokens.contentOnStatusPositiveEmphasizedLight,
            onStatusPositiveMuted = contentColorTokens.contentOnStatusPositiveMutedLight,
            onStatusPositiveMutedOnBgEmphasized = contentColorTokens.contentOnStatusPositiveMutedOnBgEmphasizedLight,
            onStatusWarningEmphasized = contentColorTokens.contentOnStatusWarningEmphasizedLight,
            onStatusWarningMuted = contentColorTokens.contentOnStatusWarningMutedLight,
            onStatusWarningMutedOnBgEmphasized = contentColorTokens.contentOnStatusWarningMutedOnBgEmphasizedLight,
            statusInfo = contentColorTokens.contentStatusInfoLight,
            statusNegative = contentColorTokens.contentStatusNegativeLight,
            statusPositive = contentColorTokens.contentStatusPositiveLight,
            statusWarning = contentColorTokens.contentStatusWarningLight,
        ),
        elevationColors = OudsColorScheme.Elevation(
            drag = elevationColorTokens.elevationDragLight,
            dragOnBgEmphasized = elevationColorTokens.elevationDragOnBgEmphasizedLight,
            dragOnBgSecondary = elevationColorTokens.elevationDragOnBgSecondaryLight,
            modal = elevationColorTokens.elevationModalLight,
            overlayDefault = elevationColorTokens.elevationOverlayDefaultLight,
            overlayDefaultOnBgEmphasized = elevationColorTokens.elevationOverlayDefaultOnBgEmphasizedLight,
            overlayDefaultOnBgSecondary = elevationColorTokens.elevationOverlayDefaultOnBgSecondaryLight,
            overlayEmphasized = elevationColorTokens.elevationOverlayEmphasizedLight,
            overlayEmphasizedOnBgEmphasized = elevationColorTokens.elevationOverlayEmphasizedOnBgEmphasizedLight,
            overlayEmphasizedOnBgSecondary = elevationColorTokens.elevationOverlayEmphasizedOnBgSecondaryLight,
            raised = elevationColorTokens.elevationRaisedLight,
            raisedOnBgEmphasized = elevationColorTokens.elevationRaisedOnBgEmphasizedLight,
            raisedOnBgSecondary = elevationColorTokens.elevationRaisedOnBgSecondaryLight,
        ),
        gradientColors = OudsColorScheme.Gradient(
            skeletonMiddle = gradientColorTokens.gradientSkeletonMiddleLight,
            skeletonMiddleOnBgEmphasized = gradientColorTokens.gradientSkeletonMiddleOnBgEmphasizedLight,
            skeletonStartEnd = gradientColorTokens.gradientSkeletonStartEndLight,
            skeletonStartEndOnBgEmphasized = gradientColorTokens.gradientSkeletonStartEndOnBgEmphasizedLight,
        ),
        decorativeColors = OudsColorScheme.Decorative(
            accent1Default = decorativeColorTokens.decorativeAccent1DefaultLight,
            accent1Emphasized = decorativeColorTokens.decorativeAccent1EmphasizedLight,
            accent1Muted = decorativeColorTokens.decorativeAccent1MutedLight,
            accent2Default = decorativeColorTokens.decorativeAccent2DefaultLight,
            accent2Emphasized = decorativeColorTokens.decorativeAccent2EmphasizedLight,
            accent2Muted = decorativeColorTokens.decorativeAccent2MutedLight,
            accent3Default = decorativeColorTokens.decorativeAccent3DefaultLight,
            accent3Emphasized = decorativeColorTokens.decorativeAccent3EmphasizedLight,
            accent3Muted = decorativeColorTokens.decorativeAccent3MutedLight,
            accent4Default = decorativeColorTokens.decorativeAccent4DefaultLight,
            accent4Emphasized = decorativeColorTokens.decorativeAccent4EmphasizedLight,
            accent4Muted = decorativeColorTokens.decorativeAccent4MutedLight,
            accent5Default = decorativeColorTokens.decorativeAccent5DefaultLight,
            accent5Emphasized = decorativeColorTokens.decorativeAccent5EmphasizedLight,
            accent5Muted = decorativeColorTokens.decorativeAccent5MutedLight,
            brandPrimary = decorativeColorTokens.decorativeBrandPrimaryLight,
            brandSecondary = decorativeColorTokens.decorativeBrandSecondaryLight,
            brandTertiary = decorativeColorTokens.decorativeBrandTertiaryLight,
            neutralDefault = decorativeColorTokens.decorativeNeutralDefaultLight,
            neutralEmphasized = decorativeColorTokens.decorativeNeutralEmphasizedLight,
            neutralMuted = decorativeColorTokens.decorativeNeutralMutedLight,
            skinTint100 = decorativeColorTokens.decorativeSkinTint100Light,
            skinTint200 = decorativeColorTokens.decorativeSkinTint200Light,
            skinTint300 = decorativeColorTokens.decorativeSkinTint300Light,
            skinTint400 = decorativeColorTokens.decorativeSkinTint400Light,
            skinTint500 = decorativeColorTokens.decorativeSkinTint500Light,
            skinTint600 = decorativeColorTokens.decorativeSkinTint600Light,
            skinTint700 = decorativeColorTokens.decorativeSkinTint700Light,
            skinTint800 = decorativeColorTokens.decorativeSkinTint800Light,
            skinTint900 = decorativeColorTokens.decorativeSkinTint900Light,
        ),
    )

val OudsColorSemanticTokens.darkColorScheme: OudsColorScheme
    get() = OudsColorScheme(
        transparentColors = OudsColorScheme.Transparent(
            transparentDefault = transparentColorTokens.transparentDefaultDark,
        ),
        actionColors = with(actionColorTokens) {
            OudsColorScheme.Action(
                disabled = actionDisabledDark,
                disabledOnBgEmphasized = actionDisabledOnBgEmphasizedDark,
                negativeEnabled = actionNegativeEnabledDark,
                negativeFocus = actionNegativeFocusDark,
                negativeHover = actionNegativeHoverDark,
                negativeLoading = actionNegativeLoadingDark,
                negativePressed = actionNegativePressedDark,
                primaryEnabled = actionPrimaryEnabledDark,
                primaryEnabledOnBgEmphasized = actionPrimaryEnabledOnBgEmphasizedDark,
                primaryFocus = actionPrimaryFocusDark,
                primaryFocusOnBgEmphasized = actionPrimaryFocusOnBgEmphasizedDark,
                primaryHover = actionPrimaryHoverDark,
                primaryHoverOnBgEmphasized = actionPrimaryHoverOnBgEmphasizedDark,
                primaryLoading = actionPrimaryLoadingDark,
                primaryLoadingOnBgEmphasized = actionPrimaryLoadingOnBgEmphasizedDark,
                primaryPressed = actionPrimaryPressedDark,
                primaryPressedOnBgEmphasized = actionPrimaryPressedOnBgEmphasizedDark,
                secondaryEnabled = actionSecondaryEnabledDark,
                secondaryEnabledOnBgEmphasized = actionSecondaryEnabledOnBgEmphasizedDark,
                secondaryFocus = actionSecondaryFocusDark,
                secondaryFocusOnBgEmphasized = actionSecondaryFocusOnBgEmphasizedDark,
                secondaryHover = actionSecondaryHoverDark,
                secondaryHoverOnBgEmphasized = actionSecondaryHoverOnBgEmphasizedDark,
                secondaryLoading = actionSecondaryLoadingDark,
                secondaryLoadingOnBgEmphasized = actionSecondaryLoadingOnBgEmphasizedDark,
                secondaryPressed = actionSecondaryPressedDark,
                secondaryPressedOnBgEmphasized = actionSecondaryPressedOnBgEmphasizedDark,
                selected = actionSelectedDark,
                selectedOnBgEmphasized = actionSelectedOnBgEmphasizedDark,
                visited = actionVisitedDark,
                visitedOnBgEmphasized = actionVisitedOnBgEmphasizedDark,
            )
        },
        alwaysColors = with(alwaysColorTokens) {
            OudsColorScheme.Always(
                accent = alwaysAccentDark,
                black = alwaysBlackDark,
                info = alwaysInfoDark,
                negative = alwaysNegativeDark,
                onAccent = alwaysOnAccentDark,
                onBlack = alwaysOnBlackDark,
                onInfo = alwaysOnInfoDark,
                onNegative = alwaysOnNegativeDark,
                onPositive = alwaysOnPositiveDark,
                onWarning = alwaysOnWarningDark,
                onWhite = alwaysOnWhiteDark,
                positive = alwaysPositiveDark,
                warning = alwaysWarningDark,
                white = alwaysWhiteDark,
            )
        },
        backgroundColors = with(backgroundColorTokens) {
            OudsColorScheme.Background(
                brandPrimary = bgBrandPrimaryDark,
                emphasized = bgEmphasizedDark,
                primary = bgPrimaryDark,
                secondary = bgSecondaryDark,
                statusAccentEmphasized = bgStatusAccentEmphasizedDark,
                statusAccentMuted = bgStatusAccentMutedDark,
                statusAccentMutedOnBgEmphasized = bgStatusAccentMutedOnBgEmphasizedDark,
                statusInfoEmphasized = bgStatusInfoEmphasizedDark,
                statusInfoMuted = bgStatusInfoMutedDark,
                statusInfoMutedOnBgEmphasized = bgStatusInfoMutedOnBgEmphasizedDark,
                statusNegativeEmphasized = bgStatusNegativeEmphasizedDark,
                statusNegativeMuted = bgStatusNegativeMutedDark,
                statusNegativeMutedOnBgEmphasized = bgStatusNegativeMutedOnBgEmphasizedDark,
                statusNeutral = bgStatusNeutralDark,
                statusNeutralOnBgEmphasized = bgStatusNeutralOnBgEmphasizedDark,
                statusPositiveEmphasized = bgStatusPositiveEmphasizedDark,
                statusPositiveMuted = bgStatusPositiveMutedDark,
                statusPositiveMutedOnBgEmphasized = bgStatusPositiveMutedOnBgEmphasizedDark,
                statusWarningEmphasized = bgStatusWarningEmphasizedDark,
                statusWarningMuted = bgStatusWarningMutedDark,
                statusWarningMutedOnBgEmphasized = bgStatusWarningMutedOnBgEmphasizedDark,
                tertiary = bgTertiaryDark,
            )
        },
        borderColors = with(borderColorTokens) {
            OudsColorScheme.Border(
                brandPrimary = borderBrandPrimaryDark,
                brandPrimaryOnBgEmphasized = borderBrandPrimaryOnBgEmphasizedDark,
                default = borderDefaultDark,
                defaultOnBgEmphasized = borderDefaultOnBgEmphasizedDark,
                emphasized = borderEmphasizedDark,
                emphasizedOnBgEmphasized = borderEmphasizedOnBgEmphasizedDark,
                focus = borderFocusDark,
                focusInset = borderFocusInsetDark,
                focusInsetOnBgEmphasized = borderFocusInsetOnBgEmphasizedDark,
                focusOnBgEmphasized = borderFocusOnBgEmphasizedDark,
                onBrandPrimary = borderOnBrandPrimaryDark,
            )
        },
        brandColors = with(brandColorTokens) {
            OudsColorScheme.Brand(
                accentDefault = brandAccentDefaultDark,
                accentHigh = brandAccentHighDark,
                accentHighest = brandAccentHighestDark,
                accentLowest = brandAccentLowestDark,
                infoDefault = brandInfoDefaultDark,
                infoHighest = brandInfoHighestDark,
                infoLowest = brandInfoLowestDark,
                negativeDefault = brandNegativeDefaultDark,
                negativeHigh = brandNegativeHighDark,
                negativeHigher = brandNegativeHigherDark,
                negativeHighest = brandNegativeHighestDark,
                negativeLowest = brandNegativeLowestDark,
                neutralEmphasizedBlack = brandNeutralEmphasizedBlackDark,
                neutralEmphasizedHigh = brandNeutralEmphasizedHighDark,
                neutralEmphasizedHigher = brandNeutralEmphasizedHigherDark,
                neutralEmphasizedHighest = brandNeutralEmphasizedHighestDark,
                neutralEmphasizedLow = brandNeutralEmphasizedLowDark,
                neutralEmphasizedLower = brandNeutralEmphasizedLowerDark,
                neutralEmphasizedLowest = brandNeutralEmphasizedLowestDark,
                neutralEmphasizedMedium = brandNeutralEmphasizedMediumDark,
                neutralMutedHighest = brandNeutralMutedHighestDark,
                neutralMutedLow = brandNeutralMutedLowDark,
                neutralMutedLower = brandNeutralMutedLowerDark,
                neutralMutedLowest = brandNeutralMutedLowestDark,
                neutralMutedMedium = brandNeutralMutedMediumDark,
                neutralMutedWhite = brandNeutralMutedWhiteDark,
                positiveDefault = brandPositiveDefaultDark,
                positiveHighest = brandPositiveHighestDark,
                positiveLowest = brandPositiveLowestDark,
                primaryDefault = brandPrimaryDefaultDark,
                primaryLow = brandPrimaryLowDark,
                warningDefault = brandWarningDefaultDark,
                warningHigh = brandWarningHighDark,
                warningHighest = brandWarningHighestDark,
                warningLowest = brandWarningLowestDark,
            )
        },
        contentColors = with(contentColorTokens) {
            OudsColorScheme.Content(
                brandPrimary = contentBrandPrimaryDark,
                brandPrimaryOnBgEmphasized = contentBrandPrimaryOnBgEmphasizedDark,
                default = contentDefaultDark,
                defaultOnBgEmphasized = contentDefaultOnBgEmphasizedDark,
                disabled = contentDisabledDark,
                disabledOnBgEmphasized = contentDisabledOnBgEmphasizedDark,
                muted = contentMutedDark,
                mutedOnBgEmphasized = contentMutedOnBgEmphasizedDark,
                onActionDisabled = contentOnActionDisabledDark,
                onActionDisabledOnBgEmphasized = contentOnActionDisabledOnBgEmphasizedDark,
                onActionNegative = contentOnActionNegativeDark,
                onActionPrimaryEnabled = contentOnActionPrimaryEnabledDark,
                onActionPrimaryEnabledOnBgEmphasized = contentOnActionPrimaryEnabledOnBgEmphasizedDark,
                onActionPrimaryFocus = contentOnActionPrimaryFocusDark,
                onActionPrimaryFocusOnBgEmphasized = contentOnActionPrimaryFocusOnBgEmphasizedDark,
                onActionPrimaryHover = contentOnActionPrimaryHoverDark,
                onActionPrimaryHoverOnBgEmphasized = contentOnActionPrimaryHoverOnBgEmphasizedDark,
                onActionPrimaryLoading = contentOnActionPrimaryLoadingDark,
                onActionPrimaryLoadingOnBgEmphasized = contentOnActionPrimaryLoadingOnBgEmphasizedDark,
                onActionPrimaryPressed = contentOnActionPrimaryPressedDark,
                onActionPrimaryPressedOnBgEmphasized = contentOnActionPrimaryPressedOnBgEmphasizedDark,
                onBrandPrimary = contentOnBrandPrimaryDark,
                onStatusAccentEmphasized = contentOnStatusAccentEmphasizedDark,
                onStatusAccentMuted = contentOnStatusAccentMutedDark,
                onStatusAccentMutedOnBgEmphasized = contentOnStatusAccentMutedOnBgEmphasizedDark,
                onStatusInfoEmphasized = contentOnStatusInfoEmphasizedDark,
                onStatusInfoMuted = contentOnStatusInfoMutedDark,
                onStatusInfoMutedOnBgEmphasized = contentOnStatusInfoMutedOnBgEmphasizedDark,
                onStatusNegativeEmphasized = contentOnStatusNegativeEmphasizedDark,
                onStatusNegativeMuted = contentOnStatusNegativeMutedDark,
                onStatusNegativeMutedOnBgEmphasized = contentOnStatusNegativeMutedOnBgEmphasizedDark,
                onStatusPositiveEmphasized = contentOnStatusPositiveEmphasizedDark,
                onStatusPositiveMuted = contentOnStatusPositiveMutedDark,
                onStatusPositiveMutedOnBgEmphasized = contentOnStatusPositiveMutedOnBgEmphasizedDark,
                onStatusWarningEmphasized = contentOnStatusWarningEmphasizedDark,
                onStatusWarningMuted = contentOnStatusWarningMutedDark,
                onStatusWarningMutedOnBgEmphasized = contentOnStatusWarningMutedOnBgEmphasizedDark,
                statusInfo = contentStatusInfoDark,
                statusNegative = contentStatusNegativeDark,
                statusPositive = contentStatusPositiveDark,
                statusWarning = contentStatusWarningDark,
            )
        },
        elevationColors = with(elevationColorTokens) {
            OudsColorScheme.Elevation(
                drag = elevationDragDark,
                dragOnBgEmphasized = elevationDragOnBgEmphasizedDark,
                dragOnBgSecondary = elevationDragOnBgSecondaryDark,
                modal = elevationModalDark,
                overlayDefault = elevationOverlayDefaultDark,
                overlayDefaultOnBgEmphasized = elevationOverlayDefaultOnBgEmphasizedDark,
                overlayDefaultOnBgSecondary = elevationOverlayDefaultOnBgSecondaryDark,
                overlayEmphasized = elevationOverlayEmphasizedDark,
                overlayEmphasizedOnBgEmphasized = elevationOverlayEmphasizedOnBgEmphasizedDark,
                overlayEmphasizedOnBgSecondary = elevationOverlayEmphasizedOnBgSecondaryDark,
                raised = elevationRaisedDark,
                raisedOnBgEmphasized = elevationRaisedOnBgEmphasizedDark,
                raisedOnBgSecondary = elevationRaisedOnBgSecondaryDark,
            )
        },
        gradientColors = with(gradientColorTokens) {
            OudsColorScheme.Gradient(
                skeletonMiddle = gradientSkeletonMiddleDark,
                skeletonMiddleOnBgEmphasized = gradientSkeletonMiddleOnBgEmphasizedDark,
                skeletonStartEnd = gradientSkeletonStartEndDark,
                skeletonStartEndOnBgEmphasized = gradientSkeletonStartEndOnBgEmphasizedDark,
            )
        },
        decorativeColors = with(decorativeColorTokens) {
            OudsColorScheme.Decorative(
                accent1Default = decorativeAccent1DefaultDark,
                accent1Emphasized = decorativeAccent1EmphasizedDark,
                accent1Muted = decorativeAccent1MutedDark,
                accent2Default = decorativeAccent2DefaultDark,
                accent2Emphasized = decorativeAccent2EmphasizedDark,
                accent2Muted = decorativeAccent2MutedDark,
                accent3Default = decorativeAccent3DefaultDark,
                accent3Emphasized = decorativeAccent3EmphasizedDark,
                accent3Muted = decorativeAccent3MutedDark,
                accent4Default = decorativeAccent4DefaultDark,
                accent4Emphasized = decorativeAccent4EmphasizedDark,
                accent4Muted = decorativeAccent4MutedDark,
                accent5Default = decorativeAccent5DefaultDark,
                accent5Emphasized = decorativeAccent5EmphasizedDark,
                accent5Muted = decorativeAccent5MutedDark,
                brandPrimary = decorativeBrandPrimaryDark,
                brandSecondary = decorativeBrandSecondaryDark,
                brandTertiary = decorativeBrandTertiaryDark,
                neutralDefault = decorativeNeutralDefaultDark,
                neutralEmphasized = decorativeNeutralEmphasizedDark,
                neutralMuted = decorativeNeutralMutedDark,
                skinTint100 = decorativeSkinTint100Dark,
                skinTint200 = decorativeSkinTint200Dark,
                skinTint300 = decorativeSkinTint300Dark,
                skinTint400 = decorativeSkinTint400Dark,
                skinTint500 = decorativeSkinTint500Dark,
                skinTint600 = decorativeSkinTint600Dark,
                skinTint700 = decorativeSkinTint700Dark,
                skinTint800 = decorativeSkinTint800Dark,
                skinTint900 = decorativeSkinTint900Dark,
            )
        },
    )

@Stable
fun OudsColorScheme.fromToken(token: OudsColorKeyToken.Action): Color {
    return with(actionColors) {
        when (token) {
            OudsColorKeyToken.Action.Disabled -> disabled
            OudsColorKeyToken.Action.DisabledOnBgEmphasized -> disabledOnBgEmphasized
            OudsColorKeyToken.Action.NegativeEnabled -> negativeEnabled
            OudsColorKeyToken.Action.NegativeFocus -> negativeFocus
            OudsColorKeyToken.Action.NegativeHover -> negativeHover
            OudsColorKeyToken.Action.NegativeLoading -> negativeLoading
            OudsColorKeyToken.Action.NegativePressed -> negativePressed
            OudsColorKeyToken.Action.PrimaryEnabled -> primaryEnabled
            OudsColorKeyToken.Action.PrimaryEnabledOnBgEmphasized -> primaryEnabledOnBgEmphasized
            OudsColorKeyToken.Action.PrimaryFocus -> primaryFocus
            OudsColorKeyToken.Action.PrimaryFocusOnBgEmphasized -> primaryFocusOnBgEmphasized
            OudsColorKeyToken.Action.PrimaryHover -> primaryHover
            OudsColorKeyToken.Action.PrimaryHoverOnBgEmphasized -> primaryHoverOnBgEmphasized
            OudsColorKeyToken.Action.PrimaryLoading -> primaryLoading
            OudsColorKeyToken.Action.PrimaryLoadingOnBgEmphasized -> primaryLoadingOnBgEmphasized
            OudsColorKeyToken.Action.PrimaryPressed -> primaryPressed
            OudsColorKeyToken.Action.PrimaryPressedOnBgEmphasized -> primaryPressedOnBgEmphasized
            OudsColorKeyToken.Action.SecondaryEnabled -> secondaryEnabled
            OudsColorKeyToken.Action.SecondaryEnabledOnBgEmphasized -> secondaryEnabledOnBgEmphasized
            OudsColorKeyToken.Action.SecondaryFocus -> secondaryFocus
            OudsColorKeyToken.Action.SecondaryFocusOnBgEmphasized -> secondaryFocusOnBgEmphasized
            OudsColorKeyToken.Action.SecondaryHover -> secondaryHover
            OudsColorKeyToken.Action.SecondaryHoverOnBgEmphasized -> secondaryHoverOnBgEmphasized
            OudsColorKeyToken.Action.SecondaryLoading -> secondaryLoading
            OudsColorKeyToken.Action.SecondaryLoadingOnBgEmphasized -> secondaryLoadingOnBgEmphasized
            OudsColorKeyToken.Action.SecondaryPressed -> secondaryPressed
            OudsColorKeyToken.Action.SecondaryPressedOnBgEmphasized -> secondaryPressedOnBgEmphasized
            OudsColorKeyToken.Action.Selected -> selected
            OudsColorKeyToken.Action.SelectedOnBgEmphasized -> selectedOnBgEmphasized
            OudsColorKeyToken.Action.Visited -> visited
            OudsColorKeyToken.Action.VisitedOnBgEmphasized -> visitedOnBgEmphasized
        }
    }
}

@Stable
fun OudsColorScheme.fromToken(token: OudsColorKeyToken.Always): Color {
    return with(alwaysColors) {
        when (token) {
            OudsColorKeyToken.Always.Accent -> accent
            OudsColorKeyToken.Always.Black -> black
            OudsColorKeyToken.Always.Info -> info
            OudsColorKeyToken.Always.Negative -> negative
            OudsColorKeyToken.Always.OnAccent -> onAccent
            OudsColorKeyToken.Always.OnBlack -> onBlack
            OudsColorKeyToken.Always.OnInfo -> onInfo
            OudsColorKeyToken.Always.OnNegative -> onNegative
            OudsColorKeyToken.Always.OnPositive -> onPositive
            OudsColorKeyToken.Always.OnWarning -> onWarning
            OudsColorKeyToken.Always.OnWhite -> onWhite
            OudsColorKeyToken.Always.Positive -> positive
            OudsColorKeyToken.Always.Warning -> warning
            OudsColorKeyToken.Always.White -> white
        }
    }
}

@Stable
fun OudsColorScheme.fromToken(token: OudsColorKeyToken.Background): Color {
    return with(backgroundColors) {
        when (token) {
            OudsColorKeyToken.Background.BrandPrimary -> brandPrimary
            OudsColorKeyToken.Background.Emphasized -> emphasized
            OudsColorKeyToken.Background.Primary -> primary
            OudsColorKeyToken.Background.Secondary -> secondary
            OudsColorKeyToken.Background.StatusAccentEmphasized -> statusAccentEmphasized
            OudsColorKeyToken.Background.StatusAccentMuted -> statusAccentMuted
            OudsColorKeyToken.Background.StatusAccentMutedOnBgEmphasized -> statusAccentMutedOnBgEmphasized
            OudsColorKeyToken.Background.StatusInfoEmphasized -> statusInfoEmphasized
            OudsColorKeyToken.Background.StatusInfoMuted -> statusInfoMuted
            OudsColorKeyToken.Background.StatusInfoMutedOnBgEmphasized -> statusInfoMutedOnBgEmphasized
            OudsColorKeyToken.Background.StatusNegativeEmphasized -> statusNegativeEmphasized
            OudsColorKeyToken.Background.StatusNegativeMuted -> statusNegativeMuted
            OudsColorKeyToken.Background.StatusNegativeMutedOnBgEmphasized -> statusNegativeMutedOnBgEmphasized
            OudsColorKeyToken.Background.StatusNeutral -> statusNeutral
            OudsColorKeyToken.Background.StatusNeutralOnBgEmphasized -> statusNeutralOnBgEmphasized
            OudsColorKeyToken.Background.StatusPositiveEmphasized -> statusPositiveEmphasized
            OudsColorKeyToken.Background.StatusPositiveMuted -> statusPositiveMuted
            OudsColorKeyToken.Background.StatusPositiveMutedOnBgEmphasized -> statusPositiveMutedOnBgEmphasized
            OudsColorKeyToken.Background.StatusWarningEmphasized -> statusWarningEmphasized
            OudsColorKeyToken.Background.StatusWarningMuted -> statusWarningMuted
            OudsColorKeyToken.Background.StatusWarningMutedOnBgEmphasized -> statusWarningMutedOnBgEmphasized
            OudsColorKeyToken.Background.Tertiary -> tertiary
        }
    }
}

@Stable
fun OudsColorScheme.fromToken(token: OudsColorKeyToken.Border): Color {
    return with(borderColors) {
        when (token) {
            OudsColorKeyToken.Border.BrandPrimary -> brandPrimary
            OudsColorKeyToken.Border.BrandPrimaryOnBgEmphasized -> brandPrimaryOnBgEmphasized
            OudsColorKeyToken.Border.Default -> default
            OudsColorKeyToken.Border.DefaultOnBgEmphasized -> defaultOnBgEmphasized
            OudsColorKeyToken.Border.Emphasized -> emphasized
            OudsColorKeyToken.Border.EmphasizedOnBgEmphasized -> emphasizedOnBgEmphasized
            OudsColorKeyToken.Border.Focus -> focus
            OudsColorKeyToken.Border.FocusInset -> focusInset
            OudsColorKeyToken.Border.FocusInsetOnBgEmphasized -> focusInsetOnBgEmphasized
            OudsColorKeyToken.Border.FocusOnBgEmphasized -> focusOnBgEmphasized
            OudsColorKeyToken.Border.OnBrandPrimary -> onBrandPrimary
        }
    }
}

@Stable
fun OudsColorScheme.fromToken(token: OudsColorKeyToken.Brand): Color {
    return with(brandColors) {
        when (token) {
            OudsColorKeyToken.Brand.AccentDefault -> accentDefault
            OudsColorKeyToken.Brand.AccentHigh -> accentHigh
            OudsColorKeyToken.Brand.AccentHighest -> accentHighest
            OudsColorKeyToken.Brand.AccentLowest -> accentLowest
            OudsColorKeyToken.Brand.InfoDefault -> infoDefault
            OudsColorKeyToken.Brand.InfoHighest -> infoHighest
            OudsColorKeyToken.Brand.InfoLowest -> infoLowest
            OudsColorKeyToken.Brand.NegativeDefault -> negativeDefault
            OudsColorKeyToken.Brand.NegativeHigh -> negativeHigh
            OudsColorKeyToken.Brand.NegativeHigher -> negativeHigher
            OudsColorKeyToken.Brand.NegativeHighest -> negativeHighest
            OudsColorKeyToken.Brand.NegativeLowest -> negativeLowest
            OudsColorKeyToken.Brand.NeutralEmphasizedBlack -> neutralEmphasizedBlack
            OudsColorKeyToken.Brand.NeutralEmphasizedHigh -> neutralEmphasizedHigh
            OudsColorKeyToken.Brand.NeutralEmphasizedHigher -> neutralEmphasizedHigher
            OudsColorKeyToken.Brand.NeutralEmphasizedHighest -> neutralEmphasizedHighest
            OudsColorKeyToken.Brand.NeutralEmphasizedLow -> neutralEmphasizedLow
            OudsColorKeyToken.Brand.NeutralEmphasizedLower -> neutralEmphasizedLower
            OudsColorKeyToken.Brand.NeutralEmphasizedLowest -> neutralEmphasizedLowest
            OudsColorKeyToken.Brand.NeutralEmphasizedMedium -> neutralEmphasizedMedium
            OudsColorKeyToken.Brand.NeutralMutedHighest -> neutralMutedHighest
            OudsColorKeyToken.Brand.NeutralMutedLow -> neutralMutedLow
            OudsColorKeyToken.Brand.NeutralMutedLower -> neutralMutedLower
            OudsColorKeyToken.Brand.NeutralMutedLowest -> neutralMutedLowest
            OudsColorKeyToken.Brand.NeutralMutedMedium -> neutralMutedMedium
            OudsColorKeyToken.Brand.NeutralMutedWhite -> neutralMutedWhite
            OudsColorKeyToken.Brand.PositiveDefault -> positiveDefault
            OudsColorKeyToken.Brand.PositiveHighest -> positiveHighest
            OudsColorKeyToken.Brand.PositiveLowest -> positiveLowest
            OudsColorKeyToken.Brand.PrimaryDefault -> primaryDefault
            OudsColorKeyToken.Brand.PrimaryLow -> primaryLow
            OudsColorKeyToken.Brand.WarningDefault -> warningDefault
            OudsColorKeyToken.Brand.WarningHigh -> warningHigh
            OudsColorKeyToken.Brand.WarningHighest -> warningHighest
            OudsColorKeyToken.Brand.WarningLowest -> warningLowest
        }
    }
}

@Stable
fun OudsColorScheme.fromToken(token: OudsColorKeyToken.Content): Color {
    return with(contentColors) {
        when (token) {
            OudsColorKeyToken.Content.BrandPrimary -> brandPrimary
            OudsColorKeyToken.Content.BrandPrimaryOnBgEmphasized -> brandPrimaryOnBgEmphasized
            OudsColorKeyToken.Content.Default -> default
            OudsColorKeyToken.Content.DefaultOnBgEmphasized -> defaultOnBgEmphasized
            OudsColorKeyToken.Content.Disabled -> disabled
            OudsColorKeyToken.Content.DisabledOnBgEmphasized -> disabledOnBgEmphasized
            OudsColorKeyToken.Content.Muted -> muted
            OudsColorKeyToken.Content.MutedOnBgEmphasized -> mutedOnBgEmphasized
            OudsColorKeyToken.Content.OnActionDisabled -> onActionDisabled
            OudsColorKeyToken.Content.OnActionDisabledOnBgEmphasized -> onActionDisabledOnBgEmphasized
            OudsColorKeyToken.Content.OnActionNegative -> onActionNegative
            OudsColorKeyToken.Content.OnActionPrimaryEnabled -> onActionPrimaryEnabled
            OudsColorKeyToken.Content.OnActionPrimaryEnabledOnBgEmphasized -> onActionPrimaryEnabledOnBgEmphasized
            OudsColorKeyToken.Content.OnActionPrimaryFocus -> onActionPrimaryFocus
            OudsColorKeyToken.Content.OnActionPrimaryFocusOnBgEmphasized -> onActionPrimaryFocusOnBgEmphasized
            OudsColorKeyToken.Content.OnActionPrimaryHover -> onActionPrimaryHover
            OudsColorKeyToken.Content.OnActionPrimaryHoverOnBgEmphasized -> onActionPrimaryHoverOnBgEmphasized
            OudsColorKeyToken.Content.OnActionPrimaryLoading -> onActionPrimaryLoading
            OudsColorKeyToken.Content.OnActionPrimaryLoadingOnBgEmphasized -> onActionPrimaryLoadingOnBgEmphasized
            OudsColorKeyToken.Content.OnActionPrimaryPressed -> onActionPrimaryPressed
            OudsColorKeyToken.Content.OnActionPrimaryPressedOnBgEmphasized -> onActionPrimaryPressedOnBgEmphasized
            OudsColorKeyToken.Content.OnBrandPrimary -> onBrandPrimary
            OudsColorKeyToken.Content.OnStatusAccentEmphasized -> onStatusAccentEmphasized
            OudsColorKeyToken.Content.OnStatusAccentMuted -> onStatusAccentMuted
            OudsColorKeyToken.Content.OnStatusAccentMutedOnBgEmphasized -> onStatusAccentMutedOnBgEmphasized
            OudsColorKeyToken.Content.OnStatusInfoEmphasized -> onStatusInfoEmphasized
            OudsColorKeyToken.Content.OnStatusInfoMuted -> onStatusInfoMuted
            OudsColorKeyToken.Content.OnStatusInfoMutedOnBgEmphasized -> onStatusInfoMutedOnBgEmphasized
            OudsColorKeyToken.Content.OnStatusNegativeEmphasized -> onStatusNegativeEmphasized
            OudsColorKeyToken.Content.OnStatusNegativeMuted -> onStatusNegativeMuted
            OudsColorKeyToken.Content.OnStatusNegativeMutedOnBgEmphasized -> onStatusNegativeMutedOnBgEmphasized
            OudsColorKeyToken.Content.OnStatusPositiveEmphasized -> onStatusPositiveEmphasized
            OudsColorKeyToken.Content.OnStatusPositiveMuted -> onStatusPositiveMuted
            OudsColorKeyToken.Content.OnStatusPositiveMutedOnBgEmphasized -> onStatusPositiveMutedOnBgEmphasized
            OudsColorKeyToken.Content.OnStatusWarningEmphasized -> onStatusWarningEmphasized
            OudsColorKeyToken.Content.OnStatusWarningMuted -> onStatusWarningMuted
            OudsColorKeyToken.Content.OnStatusWarningMutedOnBgEmphasized -> onStatusWarningMutedOnBgEmphasized
            OudsColorKeyToken.Content.StatusInfo -> statusInfo
            OudsColorKeyToken.Content.StatusNegative -> statusNegative
            OudsColorKeyToken.Content.StatusPositive -> statusPositive
            OudsColorKeyToken.Content.StatusWarning -> statusWarning
        }
    }
}

@Stable
fun OudsColorScheme.fromToken(token: OudsColorKeyToken.Elevation): Color {
    return with(elevationColors) {
        when (token) {
            OudsColorKeyToken.Elevation.Drag -> drag
            OudsColorKeyToken.Elevation.DragOnBgEmphasized -> dragOnBgEmphasized
            OudsColorKeyToken.Elevation.DragOnBgSecondary -> dragOnBgSecondary
            OudsColorKeyToken.Elevation.Modal -> modal
            OudsColorKeyToken.Elevation.OverlayDefault -> overlayDefault
            OudsColorKeyToken.Elevation.OverlayDefaultOnBgEmphasized -> overlayDefaultOnBgEmphasized
            OudsColorKeyToken.Elevation.OverlayDefaultOnBgSecondary -> overlayDefaultOnBgSecondary
            OudsColorKeyToken.Elevation.OverlayEmphasized -> overlayEmphasized
            OudsColorKeyToken.Elevation.OverlayEmphasizedOnBgEmphasized -> overlayEmphasizedOnBgEmphasized
            OudsColorKeyToken.Elevation.OverlayEmphasizedOnBgSecondary -> overlayEmphasizedOnBgSecondary
            OudsColorKeyToken.Elevation.Raised -> raised
            OudsColorKeyToken.Elevation.RaisedOnBgEmphasized -> raisedOnBgEmphasized
            OudsColorKeyToken.Elevation.RaisedOnBgSecondary -> raisedOnBgSecondary
        }
    }
}

@Stable
fun OudsColorScheme.fromToken(token: OudsColorKeyToken.Gradient): Color {
    return with(gradientColors) {
        when (token) {
            OudsColorKeyToken.Gradient.SkeletonMiddle -> skeletonMiddle
            OudsColorKeyToken.Gradient.SkeletonMiddleOnBgEmphasized -> skeletonMiddleOnBgEmphasized
            OudsColorKeyToken.Gradient.SkeletonStartEnd -> skeletonStartEnd
            OudsColorKeyToken.Gradient.SkeletonStartEndOnBgEmphasized -> skeletonStartEndOnBgEmphasized
        }
    }
}

@Stable
fun OudsColorScheme.fromToken(token: OudsColorKeyToken.Decorative): Color {
    return with(decorativeColors) {
        when (token) {
            OudsColorKeyToken.Decorative.Accent1Default -> accent1Default
            OudsColorKeyToken.Decorative.Accent1Emphasized -> accent1Emphasized
            OudsColorKeyToken.Decorative.Accent1Muted -> accent1Muted
            OudsColorKeyToken.Decorative.Accent2Default -> accent2Default
            OudsColorKeyToken.Decorative.Accent2Emphasized -> accent2Emphasized
            OudsColorKeyToken.Decorative.Accent2Muted -> accent2Muted
            OudsColorKeyToken.Decorative.Accent3Default -> accent3Default
            OudsColorKeyToken.Decorative.Accent3Emphasized -> accent3Emphasized
            OudsColorKeyToken.Decorative.Accent3Muted -> accent3Muted
            OudsColorKeyToken.Decorative.Accent4Default -> accent4Default
            OudsColorKeyToken.Decorative.Accent4Emphasized -> accent4Emphasized
            OudsColorKeyToken.Decorative.Accent4Muted -> accent4Muted
            OudsColorKeyToken.Decorative.Accent5Default -> accent5Default
            OudsColorKeyToken.Decorative.Accent5Emphasized -> accent5Emphasized
            OudsColorKeyToken.Decorative.Accent5Muted -> accent5Muted
            OudsColorKeyToken.Decorative.BrandPrimary -> brandPrimary
            OudsColorKeyToken.Decorative.BrandSecondary -> brandSecondary
            OudsColorKeyToken.Decorative.BrandTertiary -> brandTertiary
            OudsColorKeyToken.Decorative.NeutralDefault -> neutralDefault
            OudsColorKeyToken.Decorative.NeutralEmphasized -> neutralEmphasized
            OudsColorKeyToken.Decorative.NeutralMuted -> neutralMuted
            OudsColorKeyToken.Decorative.SkinTint100 -> skinTint100
            OudsColorKeyToken.Decorative.SkinTint200 -> skinTint200
            OudsColorKeyToken.Decorative.SkinTint300 -> skinTint300
            OudsColorKeyToken.Decorative.SkinTint400 -> skinTint400
            OudsColorKeyToken.Decorative.SkinTint500 -> skinTint500
            OudsColorKeyToken.Decorative.SkinTint600 -> skinTint600
            OudsColorKeyToken.Decorative.SkinTint700 -> skinTint700
            OudsColorKeyToken.Decorative.SkinTint800 -> skinTint800
            OudsColorKeyToken.Decorative.SkinTint900 -> skinTint900
        }
    }
}

@Stable
fun OudsColorScheme.fromToken(token: OudsColorKeyToken.Transparent): Color {
    return when (token) {
        OudsColorKeyToken.Transparent.TransparentDefault -> transparentColors.transparentDefault
    }
}

val OudsColorSemanticTokens.materialLightColorScheme: ColorScheme
    get() = lightColorScheme(
        primary = backgroundColorTokens.bgBrandPrimaryLight,
        onPrimary = contentColorTokens.contentDefaultLight,
        primaryContainer = backgroundColorTokens.bgPrimaryLight,
        onPrimaryContainer = contentColorTokens.contentDefaultLight,
        inversePrimary = backgroundColorTokens.bgBrandPrimaryDark,
        secondary = backgroundColorTokens.bgSecondaryLight,
        onSecondary = elevationColorTokens.elevationDragOnBgSecondaryLight,
        secondaryContainer = backgroundColorTokens.bgEmphasizedLight,
        onSecondaryContainer = contentColorTokens.contentDefaultDark,
        tertiary = backgroundColorTokens.bgTertiaryLight,
        onTertiary = contentColorTokens.contentDefaultDark,
        tertiaryContainer = decorativeColorTokens.decorativeBrandTertiaryLight,
        onTertiaryContainer = contentColorTokens.contentDefaultOnBgEmphasizedLight,
        background = backgroundColorTokens.bgPrimaryLight,
        onBackground = contentColorTokens.contentDefaultLight,
        surface = elevationColorTokens.elevationRaisedLight,
        onSurface = contentColorTokens.contentDefaultLight,
        surfaceVariant = elevationColorTokens.elevationModalLight,
        onSurfaceVariant = contentColorTokens.contentDefaultLight,
        surfaceTint = elevationColorTokens.elevationModalLight,
        inverseSurface = elevationColorTokens.elevationRaisedDark,
        inverseOnSurface = contentColorTokens.contentDefaultDark,
        error = backgroundColorTokens.bgStatusNegativeMutedLight,
        onError = contentColorTokens.contentOnActionNegativeLight,
        errorContainer = backgroundColorTokens.bgStatusNegativeEmphasizedLight,
        onErrorContainer = contentColorTokens.contentOnActionDisabledOnBgEmphasizedLight,
        outline = borderColorTokens.borderBrandPrimaryLight,
        outlineVariant = borderColorTokens.borderBrandPrimaryLight,
        scrim = contentColorTokens.contentBrandPrimaryLight,
    )

val OudsColorSemanticTokens.materialDarkColorScheme: ColorScheme
    get() = darkColorScheme(
        primary = backgroundColorTokens.bgBrandPrimaryDark,
        onPrimary = contentColorTokens.contentDefaultDark,
        primaryContainer = backgroundColorTokens.bgPrimaryDark,
        onPrimaryContainer = contentColorTokens.contentDefaultDark,
        inversePrimary = backgroundColorTokens.bgBrandPrimaryLight,
        secondary = backgroundColorTokens.bgSecondaryDark,
        onSecondary = elevationColorTokens.elevationDragOnBgSecondaryDark,
        secondaryContainer = backgroundColorTokens.bgEmphasizedDark,
        onSecondaryContainer = contentColorTokens.contentDefaultOnBgEmphasizedDark,
        tertiary = backgroundColorTokens.bgTertiaryDark,
        onTertiary = contentColorTokens.contentDefaultDark,
        tertiaryContainer = decorativeColorTokens.decorativeBrandTertiaryDark,
        onTertiaryContainer = contentColorTokens.contentDefaultOnBgEmphasizedDark,
        background = backgroundColorTokens.bgPrimaryDark,
        onBackground = contentColorTokens.contentDefaultDark,
        surface = elevationColorTokens.elevationRaisedDark,
        onSurface = contentColorTokens.contentDefaultDark,
        surfaceVariant = elevationColorTokens.elevationModalDark,
        onSurfaceVariant = contentColorTokens.contentDefaultDark,
        surfaceTint = elevationColorTokens.elevationModalDark,
        inverseSurface = elevationColorTokens.elevationRaisedLight,
        inverseOnSurface = contentColorTokens.contentDefaultLight,
        error = backgroundColorTokens.bgStatusNegativeMutedDark,
        onError = contentColorTokens.contentOnActionNegativeDark,
        errorContainer = backgroundColorTokens.bgStatusNegativeEmphasizedDark,
        onErrorContainer = contentColorTokens.contentOnStatusNegativeEmphasizedDark,
        outline = borderColorTokens.borderBrandPrimaryDark,
        outlineVariant = borderColorTokens.borderBrandPrimaryDark,
        scrim = contentColorTokens.contentBrandPrimaryDark,
    )

/**
 * Converts an OUDS color token to the local color value provided by the theme.
 */
val OudsColorKeyToken.value: Color
    @ReadOnlyComposable
    @Composable
    get() = when (this) {
        is OudsColorKeyToken.Action -> OudsTheme.colorScheme.fromToken(this)
        is OudsColorKeyToken.Always -> OudsTheme.colorScheme.fromToken(this)
        is OudsColorKeyToken.Background -> OudsTheme.colorScheme.fromToken(this)
        is OudsColorKeyToken.Border -> OudsTheme.colorScheme.fromToken(this)
        is OudsColorKeyToken.Brand -> OudsTheme.colorScheme.fromToken(this)
        is OudsColorKeyToken.Content -> OudsTheme.colorScheme.fromToken(this)
        is OudsColorKeyToken.Decorative -> OudsTheme.colorScheme.fromToken(this)
        is OudsColorKeyToken.Elevation -> OudsTheme.colorScheme.fromToken(this)
        is OudsColorKeyToken.Transparent -> OudsTheme.colorScheme.fromToken(this)
        is OudsColorKeyToken.Gradient -> OudsTheme.colorScheme.fromToken(this)
    }
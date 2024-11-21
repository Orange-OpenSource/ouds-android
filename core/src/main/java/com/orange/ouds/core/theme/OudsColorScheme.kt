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
        actionColors = with(actionColorTokens) {
            OudsColorScheme.Action(
                disabled = actionDisabledLight,
                disabledOnBgEmphasized = actionDisabledOnBgEmphasizedLight,
                negativeEnabled = actionNegativeEnabledLight,
                negativeFocus = actionNegativeFocusLight,
                negativeHover = actionNegativeHoverLight,
                negativeLoading = actionNegativeLoadingLight,
                negativePressed = actionNegativePressedLight,
                primaryEnabled = actionPrimaryEnabledLight,
                primaryEnabledOnBgEmphasized = actionPrimaryEnabledOnBgEmphasizedLight,
                primaryFocus = actionPrimaryFocusLight,
                primaryFocusOnBgEmphasized = actionPrimaryFocusOnBgEmphasizedLight,
                primaryHover = actionPrimaryHoverLight,
                primaryHoverOnBgEmphasized = actionPrimaryHoverOnBgEmphasizedLight,
                primaryLoading = actionPrimaryLoadingLight,
                primaryLoadingOnBgEmphasized = actionPrimaryLoadingOnBgEmphasizedLight,
                primaryPressed = actionPrimaryPressedLight,
                primaryPressedOnBgEmphasized = actionPrimaryPressedOnBgEmphasizedLight,
                secondaryEnabled = actionSecondaryEnabledLight,
                secondaryEnabledOnBgEmphasized = actionSecondaryEnabledOnBgEmphasizedLight,
                secondaryFocus = actionSecondaryFocusLight,
                secondaryFocusOnBgEmphasized = actionSecondaryFocusOnBgEmphasizedLight,
                secondaryHover = actionSecondaryHoverLight,
                secondaryHoverOnBgEmphasized = actionSecondaryHoverOnBgEmphasizedLight,
                secondaryLoading = actionSecondaryLoadingLight,
                secondaryLoadingOnBgEmphasized = actionSecondaryLoadingOnBgEmphasizedLight,
                secondaryPressed = actionSecondaryPressedLight,
                secondaryPressedOnBgEmphasized = actionSecondaryPressedOnBgEmphasizedLight,
                selected = actionSelectedLight,
                selectedOnBgEmphasized = actionSelectedOnBgEmphasizedLight,
                visited = actionVisitedLight,
                visitedOnBgEmphasized = actionVisitedOnBgEmphasizedLight,
            )
        },
        alwaysColors = with(alwaysColorTokens) {
            OudsColorScheme.Always(
                accent = alwaysAccentLight,
                black = alwaysBlackLight,
                info = alwaysInfoLight,
                negative = alwaysNegativeLight,
                onAccent = alwaysOnAccentLight,
                onBlack = alwaysOnBlackLight,
                onInfo = alwaysOnInfoLight,
                onNegative = alwaysOnNegativeLight,
                onPositive = alwaysOnPositiveLight,
                onWarning = alwaysOnWarningLight,
                onWhite = alwaysOnWhiteLight,
                positive = alwaysPositiveLight,
                warning = alwaysWarningLight,
                white = alwaysWhiteLight,
            )
        },
        backgroundColors = with(backgroundColorTokens) {
            OudsColorScheme.Background(
                brandPrimary = bgBrandPrimaryLight,
                emphasized = bgEmphasizedLight,
                primary = bgPrimaryLight,
                secondary = bgSecondaryLight,
                statusAccentEmphasized = bgStatusAccentEmphasizedLight,
                statusAccentMuted = bgStatusAccentMutedLight,
                statusAccentMutedOnBgEmphasized = bgStatusAccentMutedOnBgEmphasizedLight,
                statusInfoEmphasized = bgStatusInfoEmphasizedLight,
                statusInfoMuted = bgStatusInfoMutedLight,
                statusInfoMutedOnBgEmphasized = bgStatusInfoMutedOnBgEmphasizedLight,
                statusNegativeEmphasized = bgStatusNegativeEmphasizedLight,
                statusNegativeMuted = bgStatusNegativeMutedLight,
                statusNegativeMutedOnBgEmphasized = bgStatusNegativeMutedOnBgEmphasizedLight,
                statusNeutral = bgStatusNeutralLight,
                statusNeutralOnBgEmphasized = bgStatusNeutralOnBgEmphasizedLight,
                statusPositiveEmphasized = bgStatusPositiveEmphasizedLight,
                statusPositiveMuted = bgStatusPositiveMutedLight,
                statusPositiveMutedOnBgEmphasized = bgStatusPositiveMutedOnBgEmphasizedLight,
                statusWarningEmphasized = bgStatusWarningEmphasizedLight,
                statusWarningMuted = bgStatusWarningMutedLight,
                statusWarningMutedOnBgEmphasized = bgStatusWarningMutedOnBgEmphasizedLight,
                tertiary = bgTertiaryLight,
            )
        },
        borderColors = with(borderColorTokens) {
            OudsColorScheme.Border(
                brandPrimary = borderBrandPrimaryLight,
                brandPrimaryOnBgEmphasized = borderBrandPrimaryOnBgEmphasizedLight,
                default = borderDefaultLight,
                defaultOnBgEmphasized = borderDefaultOnBgEmphasizedLight,
                emphasized = borderEmphasizedLight,
                emphasizedOnBgEmphasized = borderEmphasizedOnBgEmphasizedLight,
                focus = borderFocusLight,
                focusInset = borderFocusInsetLight,
                focusInsetOnBgEmphasized = borderFocusInsetOnBgEmphasizedLight,
                focusOnBgEmphasized = borderFocusOnBgEmphasizedLight,
                onBrandPrimary = borderOnBrandPrimaryLight,
            )
        },
        brandColors = with(brandColorTokens) {
            OudsColorScheme.Brand(
                accentDefault = brandAccentDefaultLight,
                accentHigh = brandAccentHighLight,
                accentHighest = brandAccentHighestLight,
                accentLowest = brandAccentLowestLight,
                infoDefault = brandInfoDefaultLight,
                infoHighest = brandInfoHighestLight,
                infoLowest = brandInfoLowestLight,
                negativeDefault = brandNegativeDefaultLight,
                negativeHigh = brandNegativeHighLight,
                negativeHigher = brandNegativeHigherLight,
                negativeHighest = brandNegativeHighestLight,
                negativeLowest = brandNegativeLowestLight,
                neutralEmphasizedBlack = brandNeutralEmphasizedBlackLight,
                neutralEmphasizedHigh = brandNeutralEmphasizedHighLight,
                neutralEmphasizedHigher = brandNeutralEmphasizedHigherLight,
                neutralEmphasizedHighest = brandNeutralEmphasizedHighestLight,
                neutralEmphasizedLow = brandNeutralEmphasizedLowLight,
                neutralEmphasizedLower = brandNeutralEmphasizedLowerLight,
                neutralEmphasizedLowest = brandNeutralEmphasizedLowestLight,
                neutralEmphasizedMedium = brandNeutralEmphasizedMediumLight,
                neutralMutedHighest = brandNeutralMutedHighestLight,
                neutralMutedLow = brandNeutralMutedLowLight,
                neutralMutedLower = brandNeutralMutedLowerLight,
                neutralMutedLowest = brandNeutralMutedLowestLight,
                neutralMutedMedium = brandNeutralMutedMediumLight,
                neutralMutedWhite = brandNeutralMutedWhiteLight,
                positiveDefault = brandPositiveDefaultLight,
                positiveHighest = brandPositiveHighestLight,
                positiveLowest = brandPositiveLowestLight,
                primaryDefault = brandPrimaryDefaultLight,
                primaryLow = brandPrimaryLowLight,
                warningDefault = brandWarningDefaultLight,
                warningHigh = brandWarningHighLight,
                warningHighest = brandWarningHighestLight,
                warningLowest = brandWarningLowestLight,
            )
        },
        contentColors = with(contentColorTokens) {
            OudsColorScheme.Content(
                brandPrimary = contentBrandPrimaryLight,
                brandPrimaryOnBgEmphasized = contentBrandPrimaryOnBgEmphasizedLight,
                default = contentDefaultLight,
                defaultOnBgEmphasized = contentDefaultOnBgEmphasizedLight,
                disabled = contentDisabledLight,
                disabledOnBgEmphasized = contentDisabledOnBgEmphasizedLight,
                muted = contentMutedLight,
                mutedOnBgEmphasized = contentMutedOnBgEmphasizedLight,
                onActionDisabled = contentOnActionDisabledLight,
                onActionDisabledOnBgEmphasized = contentOnActionDisabledOnBgEmphasizedLight,
                onActionNegative = contentOnActionNegativeLight,
                onActionPrimaryEnabled = contentOnActionPrimaryEnabledLight,
                onActionPrimaryEnabledOnBgEmphasized = contentOnActionPrimaryEnabledOnBgEmphasizedLight,
                onActionPrimaryFocus = contentOnActionPrimaryFocusLight,
                onActionPrimaryFocusOnBgEmphasized = contentOnActionPrimaryFocusOnBgEmphasizedLight,
                onActionPrimaryHover = contentOnActionPrimaryHoverLight,
                onActionPrimaryHoverOnBgEmphasized = contentOnActionPrimaryHoverOnBgEmphasizedLight,
                onActionPrimaryLoading = contentOnActionPrimaryLoadingLight,
                onActionPrimaryLoadingOnBgEmphasized = contentOnActionPrimaryLoadingOnBgEmphasizedLight,
                onActionPrimaryPressed = contentOnActionPrimaryPressedLight,
                onActionPrimaryPressedOnBgEmphasized = contentOnActionPrimaryPressedOnBgEmphasizedLight,
                onBrandPrimary = contentOnBrandPrimaryLight,
                onStatusAccentEmphasized = contentOnStatusAccentEmphasizedLight,
                onStatusAccentMuted = contentOnStatusAccentMutedLight,
                onStatusAccentMutedOnBgEmphasized = contentOnStatusAccentMutedOnBgEmphasizedLight,
                onStatusInfoEmphasized = contentOnStatusInfoEmphasizedLight,
                onStatusInfoMuted = contentOnStatusInfoMutedLight,
                onStatusInfoMutedOnBgEmphasized = contentOnStatusInfoMutedOnBgEmphasizedLight,
                onStatusNegativeEmphasized = contentOnStatusNegativeEmphasizedLight,
                onStatusNegativeMuted = contentOnStatusNegativeMutedLight,
                onStatusNegativeMutedOnBgEmphasized = contentOnStatusNegativeMutedOnBgEmphasizedLight,
                onStatusPositiveEmphasized = contentOnStatusPositiveEmphasizedLight,
                onStatusPositiveMuted = contentOnStatusPositiveMutedLight,
                onStatusPositiveMutedOnBgEmphasized = contentOnStatusPositiveMutedOnBgEmphasizedLight,
                onStatusWarningEmphasized = contentOnStatusWarningEmphasizedLight,
                onStatusWarningMuted = contentOnStatusWarningMutedLight,
                onStatusWarningMutedOnBgEmphasized = contentOnStatusWarningMutedOnBgEmphasizedLight,
                statusInfo = contentStatusInfoLight,
                statusNegative = contentStatusNegativeLight,
                statusPositive = contentStatusPositiveLight,
                statusWarning = contentStatusWarningLight,
            )
        },
        elevationColors = with(elevationColorTokens) {
            OudsColorScheme.Elevation(
                drag = elevationDragLight,
                dragOnBgEmphasized = elevationDragOnBgEmphasizedLight,
                dragOnBgSecondary = elevationDragOnBgSecondaryLight,
                modal = elevationModalLight,
                overlayDefault = elevationOverlayDefaultLight,
                overlayDefaultOnBgEmphasized = elevationOverlayDefaultOnBgEmphasizedLight,
                overlayDefaultOnBgSecondary = elevationOverlayDefaultOnBgSecondaryLight,
                overlayEmphasized = elevationOverlayEmphasizedLight,
                overlayEmphasizedOnBgEmphasized = elevationOverlayEmphasizedOnBgEmphasizedLight,
                overlayEmphasizedOnBgSecondary = elevationOverlayEmphasizedOnBgSecondaryLight,
                raised = elevationRaisedLight,
                raisedOnBgEmphasized = elevationRaisedOnBgEmphasizedLight,
                raisedOnBgSecondary = elevationRaisedOnBgSecondaryLight,
            )
        },
        gradientColors = with(gradientColorTokens) {
            OudsColorScheme.Gradient(
                skeletonMiddle = gradientSkeletonMiddleLight,
                skeletonMiddleOnBgEmphasized = gradientSkeletonMiddleOnBgEmphasizedLight,
                skeletonStartEnd = gradientSkeletonStartEndLight,
                skeletonStartEndOnBgEmphasized = gradientSkeletonStartEndOnBgEmphasizedLight,
            )
        },
        decorativeColors = with(decorativeColorTokens) {
            OudsColorScheme.Decorative(
                accent1Default = decorativeAccent1DefaultLight,
                accent1Emphasized = decorativeAccent1EmphasizedLight,
                accent1Muted = decorativeAccent1MutedLight,
                accent2Default = decorativeAccent2DefaultLight,
                accent2Emphasized = decorativeAccent2EmphasizedLight,
                accent2Muted = decorativeAccent2MutedLight,
                accent3Default = decorativeAccent3DefaultLight,
                accent3Emphasized = decorativeAccent3EmphasizedLight,
                accent3Muted = decorativeAccent3MutedLight,
                accent4Default = decorativeAccent4DefaultLight,
                accent4Emphasized = decorativeAccent4EmphasizedLight,
                accent4Muted = decorativeAccent4MutedLight,
                accent5Default = decorativeAccent5DefaultLight,
                accent5Emphasized = decorativeAccent5EmphasizedLight,
                accent5Muted = decorativeAccent5MutedLight,
                brandPrimary = decorativeBrandPrimaryLight,
                brandSecondary = decorativeBrandSecondaryLight,
                brandTertiary = decorativeBrandTertiaryLight,
                neutralDefault = decorativeNeutralDefaultLight,
                neutralEmphasized = decorativeNeutralEmphasizedLight,
                neutralMuted = decorativeNeutralMutedLight,
                skinTint100 = decorativeSkinTint100Light,
                skinTint200 = decorativeSkinTint200Light,
                skinTint300 = decorativeSkinTint300Light,
                skinTint400 = decorativeSkinTint400Light,
                skinTint500 = decorativeSkinTint500Light,
                skinTint600 = decorativeSkinTint600Light,
                skinTint700 = decorativeSkinTint700Light,
                skinTint800 = decorativeSkinTint800Light,
                skinTint900 = decorativeSkinTint900Light,
            )
        },
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
            OudsColorKeyToken.Action.Disabled.OnBgEmphasized -> disabledOnBgEmphasized
            OudsColorKeyToken.Action.Negative.Enabled -> negativeEnabled
            OudsColorKeyToken.Action.Negative.Focus -> negativeFocus
            OudsColorKeyToken.Action.Negative.Hover -> negativeHover
            OudsColorKeyToken.Action.Negative.Loading -> negativeLoading
            OudsColorKeyToken.Action.Negative.Pressed -> negativePressed
            OudsColorKeyToken.Action.Primary.Enabled -> primaryEnabled
            OudsColorKeyToken.Action.Primary.Enabled.OnBgEmphasized -> primaryEnabledOnBgEmphasized
            OudsColorKeyToken.Action.Primary.Focus -> primaryFocus
            OudsColorKeyToken.Action.Primary.Focus.OnBgEmphasized -> primaryFocusOnBgEmphasized
            OudsColorKeyToken.Action.Primary.Hover -> primaryHover
            OudsColorKeyToken.Action.Primary.Hover.OnBgEmphasized -> primaryHoverOnBgEmphasized
            OudsColorKeyToken.Action.Primary.Loading -> primaryLoading
            OudsColorKeyToken.Action.Primary.Loading.OnBgEmphasized -> primaryLoadingOnBgEmphasized
            OudsColorKeyToken.Action.Primary.Pressed -> primaryPressed
            OudsColorKeyToken.Action.Primary.Pressed.OnBgEmphasized -> primaryPressedOnBgEmphasized
            OudsColorKeyToken.Action.Secondary.Enabled -> secondaryEnabled
            OudsColorKeyToken.Action.Secondary.Enabled.OnBgEmphasized -> secondaryEnabledOnBgEmphasized
            OudsColorKeyToken.Action.Secondary.Focus -> secondaryFocus
            OudsColorKeyToken.Action.Secondary.Focus.OnBgEmphasized -> secondaryFocusOnBgEmphasized
            OudsColorKeyToken.Action.Secondary.Hover -> secondaryHover
            OudsColorKeyToken.Action.Secondary.Hover.OnBgEmphasized -> secondaryHoverOnBgEmphasized
            OudsColorKeyToken.Action.Secondary.Loading -> secondaryLoading
            OudsColorKeyToken.Action.Secondary.Loading.OnBgEmphasized -> secondaryLoadingOnBgEmphasized
            OudsColorKeyToken.Action.Secondary.Pressed -> secondaryPressed
            OudsColorKeyToken.Action.Secondary.Pressed.OnBgEmphasized -> secondaryPressedOnBgEmphasized
            OudsColorKeyToken.Action.Selected -> selected
            OudsColorKeyToken.Action.Selected.OnBgEmphasized -> selectedOnBgEmphasized
            OudsColorKeyToken.Action.Visited -> visited
            OudsColorKeyToken.Action.Visited.OnBgEmphasized -> visitedOnBgEmphasized
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
            OudsColorKeyToken.Background.Brand.Primary -> brandPrimary
            OudsColorKeyToken.Background.Emphasized -> emphasized
            OudsColorKeyToken.Background.Primary -> primary
            OudsColorKeyToken.Background.Secondary -> secondary
            OudsColorKeyToken.Background.Status.Accent.Emphasized -> statusAccentEmphasized
            OudsColorKeyToken.Background.Status.Accent.Muted -> statusAccentMuted
            OudsColorKeyToken.Background.Status.Accent.Muted.OnBgEmphasized -> statusAccentMutedOnBgEmphasized
            OudsColorKeyToken.Background.Status.Info.Emphasized -> statusInfoEmphasized
            OudsColorKeyToken.Background.Status.Info.Muted -> statusInfoMuted
            OudsColorKeyToken.Background.Status.Info.Muted.OnBgEmphasized -> statusInfoMutedOnBgEmphasized
            OudsColorKeyToken.Background.Status.Negative.Emphasized -> statusNegativeEmphasized
            OudsColorKeyToken.Background.Status.Negative.Muted -> statusNegativeMuted
            OudsColorKeyToken.Background.Status.Negative.Muted.OnBgEmphasized -> statusNegativeMutedOnBgEmphasized
            OudsColorKeyToken.Background.Status.Neutral -> statusNeutral
            OudsColorKeyToken.Background.Status.Neutral.OnBgEmphasized -> statusNeutralOnBgEmphasized
            OudsColorKeyToken.Background.Status.Positive.Emphasized -> statusPositiveEmphasized
            OudsColorKeyToken.Background.Status.Positive.Muted -> statusPositiveMuted
            OudsColorKeyToken.Background.Status.Positive.Muted.OnBgEmphasized -> statusPositiveMutedOnBgEmphasized
            OudsColorKeyToken.Background.Status.Warning.Emphasized -> statusWarningEmphasized
            OudsColorKeyToken.Background.Status.Warning.Muted -> statusWarningMuted
            OudsColorKeyToken.Background.Status.Warning.Muted.OnBgEmphasized -> statusWarningMutedOnBgEmphasized
            OudsColorKeyToken.Background.Tertiary -> tertiary
        }
    }
}

@Stable
fun OudsColorScheme.fromToken(token: OudsColorKeyToken.Border): Color {
    return with(borderColors) {
        when (token) {
            OudsColorKeyToken.Border.Brand.Primary -> brandPrimary
            OudsColorKeyToken.Border.Brand.Primary.OnBgEmphasized -> brandPrimaryOnBgEmphasized
            OudsColorKeyToken.Border.Default -> default
            OudsColorKeyToken.Border.Default.OnBgEmphasized -> defaultOnBgEmphasized
            OudsColorKeyToken.Border.Emphasized -> emphasized
            OudsColorKeyToken.Border.Emphasized.OnBgEmphasized -> emphasizedOnBgEmphasized
            OudsColorKeyToken.Border.Focus -> focus
            OudsColorKeyToken.Border.Focus.Inset -> focusInset
            OudsColorKeyToken.Border.Focus.Inset.OnBgEmphasized -> focusInsetOnBgEmphasized
            OudsColorKeyToken.Border.Focus.OnBgEmphasized -> focusOnBgEmphasized
            OudsColorKeyToken.Border.OnBrand.Primary -> onBrandPrimary
        }
    }
}

@Stable
fun OudsColorScheme.fromToken(token: OudsColorKeyToken.Brand): Color {
    return with(brandColors) {
        when (token) {
            OudsColorKeyToken.Brand.Accent.Default -> accentDefault
            OudsColorKeyToken.Brand.Accent.High -> accentHigh
            OudsColorKeyToken.Brand.Accent.Highest -> accentHighest
            OudsColorKeyToken.Brand.Accent.Lowest -> accentLowest
            OudsColorKeyToken.Brand.Info.Default -> infoDefault
            OudsColorKeyToken.Brand.Info.Highest -> infoHighest
            OudsColorKeyToken.Brand.Info.Lowest -> infoLowest
            OudsColorKeyToken.Brand.Negative.Default -> negativeDefault
            OudsColorKeyToken.Brand.Negative.High -> negativeHigh
            OudsColorKeyToken.Brand.Negative.Higher -> negativeHigher
            OudsColorKeyToken.Brand.Negative.Highest -> negativeHighest
            OudsColorKeyToken.Brand.Negative.Lowest -> negativeLowest
            OudsColorKeyToken.Brand.Neutral.Emphasized.Black -> neutralEmphasizedBlack
            OudsColorKeyToken.Brand.Neutral.Emphasized.High -> neutralEmphasizedHigh
            OudsColorKeyToken.Brand.Neutral.Emphasized.Higher -> neutralEmphasizedHigher
            OudsColorKeyToken.Brand.Neutral.Emphasized.Highest -> neutralEmphasizedHighest
            OudsColorKeyToken.Brand.Neutral.Emphasized.Low -> neutralEmphasizedLow
            OudsColorKeyToken.Brand.Neutral.Emphasized.Lower -> neutralEmphasizedLower
            OudsColorKeyToken.Brand.Neutral.Emphasized.Lowest -> neutralEmphasizedLowest
            OudsColorKeyToken.Brand.Neutral.Emphasized.Medium -> neutralEmphasizedMedium
            OudsColorKeyToken.Brand.Neutral.Muted.Highest -> neutralMutedHighest
            OudsColorKeyToken.Brand.Neutral.Muted.Low -> neutralMutedLow
            OudsColorKeyToken.Brand.Neutral.Muted.Lower -> neutralMutedLower
            OudsColorKeyToken.Brand.Neutral.Muted.Lowest -> neutralMutedLowest
            OudsColorKeyToken.Brand.Neutral.Muted.Medium -> neutralMutedMedium
            OudsColorKeyToken.Brand.Neutral.Muted.White -> neutralMutedWhite
            OudsColorKeyToken.Brand.Positive.Default -> positiveDefault
            OudsColorKeyToken.Brand.Positive.Highest -> positiveHighest
            OudsColorKeyToken.Brand.Positive.Lowest -> positiveLowest
            OudsColorKeyToken.Brand.Primary.Default -> primaryDefault
            OudsColorKeyToken.Brand.Primary.Low -> primaryLow
            OudsColorKeyToken.Brand.Warning.Default -> warningDefault
            OudsColorKeyToken.Brand.Warning.High -> warningHigh
            OudsColorKeyToken.Brand.Warning.Highest -> warningHighest
            OudsColorKeyToken.Brand.Warning.Lowest -> warningLowest
        }
    }
}

@Stable
fun OudsColorScheme.fromToken(token: OudsColorKeyToken.Content): Color {
    return with(contentColors) {
        when (token) {
            OudsColorKeyToken.Content.Brand.Primary -> brandPrimary
            OudsColorKeyToken.Content.Brand.Primary.OnBgEmphasized -> brandPrimaryOnBgEmphasized
            OudsColorKeyToken.Content.Default -> default
            OudsColorKeyToken.Content.Default.OnBgEmphasized -> defaultOnBgEmphasized
            OudsColorKeyToken.Content.Disabled -> disabled
            OudsColorKeyToken.Content.Disabled.OnBgEmphasized -> disabledOnBgEmphasized
            OudsColorKeyToken.Content.Muted -> muted
            OudsColorKeyToken.Content.Muted.OnBgEmphasized -> mutedOnBgEmphasized
            OudsColorKeyToken.Content.OnAction.Disabled -> onActionDisabled
            OudsColorKeyToken.Content.OnAction.Disabled.OnBgEmphasized -> onActionDisabledOnBgEmphasized
            OudsColorKeyToken.Content.OnAction.Negative -> onActionNegative
            OudsColorKeyToken.Content.OnAction.Primary.Enabled -> onActionPrimaryEnabled
            OudsColorKeyToken.Content.OnAction.Primary.Enabled.OnBgEmphasized -> onActionPrimaryEnabledOnBgEmphasized
            OudsColorKeyToken.Content.OnAction.Primary.Focus -> onActionPrimaryFocus
            OudsColorKeyToken.Content.OnAction.Primary.Focus.OnBgEmphasized -> onActionPrimaryFocusOnBgEmphasized
            OudsColorKeyToken.Content.OnAction.Primary.Hover -> onActionPrimaryHover
            OudsColorKeyToken.Content.OnAction.Primary.Hover.OnBgEmphasized -> onActionPrimaryHoverOnBgEmphasized
            OudsColorKeyToken.Content.OnAction.Primary.Loading -> onActionPrimaryLoading
            OudsColorKeyToken.Content.OnAction.Primary.Loading.OnBgEmphasized -> onActionPrimaryLoadingOnBgEmphasized
            OudsColorKeyToken.Content.OnAction.Primary.Pressed -> onActionPrimaryPressed
            OudsColorKeyToken.Content.OnAction.Primary.Pressed.OnBgEmphasized -> onActionPrimaryPressedOnBgEmphasized
            OudsColorKeyToken.Content.OnBrand.Primary -> onBrandPrimary
            OudsColorKeyToken.Content.OnStatus.Accent.Emphasized -> onStatusAccentEmphasized
            OudsColorKeyToken.Content.OnStatus.Accent.Muted -> onStatusAccentMuted
            OudsColorKeyToken.Content.OnStatus.Accent.Muted.OnBgEmphasized -> onStatusAccentMutedOnBgEmphasized
            OudsColorKeyToken.Content.OnStatus.Info.Emphasized -> onStatusInfoEmphasized
            OudsColorKeyToken.Content.OnStatus.Info.Muted -> onStatusInfoMuted
            OudsColorKeyToken.Content.OnStatus.Info.Muted.OnBgEmphasized -> onStatusInfoMutedOnBgEmphasized
            OudsColorKeyToken.Content.OnStatus.Negative.Emphasized -> onStatusNegativeEmphasized
            OudsColorKeyToken.Content.OnStatus.Negative.Muted -> onStatusNegativeMuted
            OudsColorKeyToken.Content.OnStatus.Negative.Muted.OnBgEmphasized -> onStatusNegativeMutedOnBgEmphasized
            OudsColorKeyToken.Content.OnStatus.Positive.Emphasized -> onStatusPositiveEmphasized
            OudsColorKeyToken.Content.OnStatus.Positive.Muted -> onStatusPositiveMuted
            OudsColorKeyToken.Content.OnStatus.Positive.Muted.OnBgEmphasized -> onStatusPositiveMutedOnBgEmphasized
            OudsColorKeyToken.Content.OnStatus.Warning.Emphasized -> onStatusWarningEmphasized
            OudsColorKeyToken.Content.OnStatus.Warning.Muted -> onStatusWarningMuted
            OudsColorKeyToken.Content.OnStatus.Warning.Muted.OnBgEmphasized -> onStatusWarningMutedOnBgEmphasized
            OudsColorKeyToken.Content.Status.Info -> statusInfo
            OudsColorKeyToken.Content.Status.Negative -> statusNegative
            OudsColorKeyToken.Content.Status.Positive -> statusPositive
            OudsColorKeyToken.Content.Status.Warning -> statusWarning
        }
    }
}

@Stable
fun OudsColorScheme.fromToken(token: OudsColorKeyToken.Decorative): Color {
    return with(decorativeColors) {
        when (token) {
            OudsColorKeyToken.Decorative.Accent.One.Default -> accent1Default
            OudsColorKeyToken.Decorative.Accent.One.Emphasized -> accent1Emphasized
            OudsColorKeyToken.Decorative.Accent.One.Muted -> accent1Muted
            OudsColorKeyToken.Decorative.Accent.Two.Default -> accent2Default
            OudsColorKeyToken.Decorative.Accent.Two.Emphasized -> accent2Emphasized
            OudsColorKeyToken.Decorative.Accent.Two.Muted -> accent2Muted
            OudsColorKeyToken.Decorative.Accent.Three.Default -> accent3Default
            OudsColorKeyToken.Decorative.Accent.Three.Emphasized -> accent3Emphasized
            OudsColorKeyToken.Decorative.Accent.Three.Muted -> accent3Muted
            OudsColorKeyToken.Decorative.Accent.Four.Default -> accent4Default
            OudsColorKeyToken.Decorative.Accent.Four.Emphasized -> accent4Emphasized
            OudsColorKeyToken.Decorative.Accent.Four.Muted -> accent4Muted
            OudsColorKeyToken.Decorative.Accent.Five.Default -> accent5Default
            OudsColorKeyToken.Decorative.Accent.Five.Emphasized -> accent5Emphasized
            OudsColorKeyToken.Decorative.Accent.Five.Muted -> accent5Muted
            OudsColorKeyToken.Decorative.Brand.Primary -> brandPrimary
            OudsColorKeyToken.Decorative.Brand.Secondary -> brandSecondary
            OudsColorKeyToken.Decorative.Brand.Tertiary -> brandTertiary
            OudsColorKeyToken.Decorative.Neutral.Default -> neutralDefault
            OudsColorKeyToken.Decorative.Neutral.Emphasized -> neutralEmphasized
            OudsColorKeyToken.Decorative.Neutral.Muted -> neutralMuted
            OudsColorKeyToken.Decorative.Skin.Tint100 -> skinTint100
            OudsColorKeyToken.Decorative.Skin.Tint200 -> skinTint200
            OudsColorKeyToken.Decorative.Skin.Tint300 -> skinTint300
            OudsColorKeyToken.Decorative.Skin.Tint400 -> skinTint400
            OudsColorKeyToken.Decorative.Skin.Tint500 -> skinTint500
            OudsColorKeyToken.Decorative.Skin.Tint600 -> skinTint600
            OudsColorKeyToken.Decorative.Skin.Tint700 -> skinTint700
            OudsColorKeyToken.Decorative.Skin.Tint800 -> skinTint800
            OudsColorKeyToken.Decorative.Skin.Tint900 -> skinTint900
        }
    }
}

@Stable
fun OudsColorScheme.fromToken(token: OudsColorKeyToken.Elevation): Color {
    return with(elevationColors) {
        when (token) {
            OudsColorKeyToken.Elevation.Drag -> drag
            OudsColorKeyToken.Elevation.Drag.OnBgEmphasized -> dragOnBgEmphasized
            OudsColorKeyToken.Elevation.Drag.OnBgSecondary -> dragOnBgSecondary
            OudsColorKeyToken.Elevation.Modal -> modal
            OudsColorKeyToken.Elevation.Overlay.Default -> overlayDefault
            OudsColorKeyToken.Elevation.Overlay.Default.OnBgEmphasized -> overlayDefaultOnBgEmphasized
            OudsColorKeyToken.Elevation.Overlay.Default.OnBgSecondary -> overlayDefaultOnBgSecondary
            OudsColorKeyToken.Elevation.Overlay.Emphasized -> overlayEmphasized
            OudsColorKeyToken.Elevation.Overlay.Emphasized.OnBgEmphasized -> overlayEmphasizedOnBgEmphasized
            OudsColorKeyToken.Elevation.Overlay.Emphasized.OnBgSecondary -> overlayEmphasizedOnBgSecondary
            OudsColorKeyToken.Elevation.Raised -> raised
            OudsColorKeyToken.Elevation.Raised.OnBgEmphasized -> raisedOnBgEmphasized
            OudsColorKeyToken.Elevation.Raised.OnBgSecondary -> raisedOnBgSecondary
        }
    }
}

@Stable
fun OudsColorScheme.fromToken(token: OudsColorKeyToken.Gradient): Color {
    return with(gradientColors) {
        when (token) {
            OudsColorKeyToken.Gradient.Skeleton.Middle -> skeletonMiddle
            OudsColorKeyToken.Gradient.Skeleton.Middle.OnBgEmphasized -> skeletonMiddleOnBgEmphasized
            OudsColorKeyToken.Gradient.Skeleton.StartEnd -> skeletonStartEnd
            OudsColorKeyToken.Gradient.Skeleton.StartEnd.OnBgEmphasized -> skeletonStartEndOnBgEmphasized
        }
    }
}

@Stable
fun OudsColorScheme.fromToken(token: OudsColorKeyToken.Transparent): Color {
    return when (token) {
        OudsColorKeyToken.Transparent.Default -> transparentColors.transparentDefault
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
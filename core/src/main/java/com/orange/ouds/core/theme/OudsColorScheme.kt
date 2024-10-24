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
import com.orange.ouds.theme.tokens.OudsColorActionKeyToken
import com.orange.ouds.theme.tokens.OudsColorAlwaysKeyToken
import com.orange.ouds.theme.tokens.OudsColorBackgroundKeyToken
import com.orange.ouds.theme.tokens.OudsColorBorderKeyToken
import com.orange.ouds.theme.tokens.OudsColorBrandKeyToken
import com.orange.ouds.theme.tokens.OudsColorContentKeyToken
import com.orange.ouds.theme.tokens.OudsColorDecorativeKeyToken
import com.orange.ouds.theme.tokens.OudsColorElevationKeyToken
import com.orange.ouds.theme.tokens.OudsColorGlobalKeyToken
import com.orange.ouds.theme.tokens.OudsColorGradientKeyToken
import com.orange.ouds.theme.tokens.semantic.OudsColorSemanticTokens

data class OudsColorScheme(
    val globalColors: Global,
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

    data class Global(
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
        globalColors = OudsColorScheme.Global(
            transparentDefault = globalColorTokens.transparentDefaultLight,
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
        globalColors = OudsColorScheme.Global(
            transparentDefault = globalColorTokens.transparentDefaultDark,
        ),
        actionColors = OudsColorScheme.Action(
            disabled = actionColorTokens.actionDisabledDark,
            disabledOnBgEmphasized = actionColorTokens.actionDisabledOnBgEmphasizedDark,
            negativeEnabled = actionColorTokens.actionNegativeEnabledDark,
            negativeFocus = actionColorTokens.actionNegativeFocusDark,
            negativeHover = actionColorTokens.actionNegativeHoverDark,
            negativeLoading = actionColorTokens.actionNegativeLoadingDark,
            negativePressed = actionColorTokens.actionNegativePressedDark,
            primaryEnabled = actionColorTokens.actionPrimaryEnabledDark,
            primaryEnabledOnBgEmphasized = actionColorTokens.actionPrimaryEnabledOnBgEmphasizedDark,
            primaryFocus = actionColorTokens.actionPrimaryFocusDark,
            primaryFocusOnBgEmphasized = actionColorTokens.actionPrimaryFocusOnBgEmphasizedDark,
            primaryHover = actionColorTokens.actionPrimaryHoverDark,
            primaryHoverOnBgEmphasized = actionColorTokens.actionPrimaryHoverOnBgEmphasizedDark,
            primaryLoading = actionColorTokens.actionPrimaryLoadingDark,
            primaryLoadingOnBgEmphasized = actionColorTokens.actionPrimaryLoadingOnBgEmphasizedDark,
            primaryPressed = actionColorTokens.actionPrimaryPressedDark,
            primaryPressedOnBgEmphasized = actionColorTokens.actionPrimaryPressedOnBgEmphasizedDark,
            secondaryEnabled = actionColorTokens.actionSecondaryEnabledDark,
            secondaryEnabledOnBgEmphasized = actionColorTokens.actionSecondaryEnabledOnBgEmphasizedDark,
            secondaryFocus = actionColorTokens.actionSecondaryFocusDark,
            secondaryFocusOnBgEmphasized = actionColorTokens.actionSecondaryFocusOnBgEmphasizedDark,
            secondaryHover = actionColorTokens.actionSecondaryHoverDark,
            secondaryHoverOnBgEmphasized = actionColorTokens.actionSecondaryHoverOnBgEmphasizedDark,
            secondaryLoading = actionColorTokens.actionSecondaryLoadingDark,
            secondaryLoadingOnBgEmphasized = actionColorTokens.actionSecondaryLoadingOnBgEmphasizedDark,
            secondaryPressed = actionColorTokens.actionSecondaryPressedDark,
            secondaryPressedOnBgEmphasized = actionColorTokens.actionSecondaryPressedOnBgEmphasizedDark,
            selected = actionColorTokens.actionSelectedDark,
            selectedOnBgEmphasized = actionColorTokens.actionSelectedOnBgEmphasizedDark,
            visited = actionColorTokens.actionVisitedDark,
            visitedOnBgEmphasized = actionColorTokens.actionVisitedOnBgEmphasizedDark,
        ),
        alwaysColors = OudsColorScheme.Always(
            accent = alwaysColorTokens.alwaysAccentDark,
            black = alwaysColorTokens.alwaysBlackDark,
            info = alwaysColorTokens.alwaysInfoDark,
            negative = alwaysColorTokens.alwaysNegativeDark,
            onAccent = alwaysColorTokens.alwaysOnAccentDark,
            onBlack = alwaysColorTokens.alwaysOnBlackDark,
            onInfo = alwaysColorTokens.alwaysOnInfoDark,
            onNegative = alwaysColorTokens.alwaysOnNegativeDark,
            onPositive = alwaysColorTokens.alwaysOnPositiveDark,
            onWarning = alwaysColorTokens.alwaysOnWarningDark,
            onWhite = alwaysColorTokens.alwaysOnWhiteDark,
            positive = alwaysColorTokens.alwaysPositiveDark,
            warning = alwaysColorTokens.alwaysWarningDark,
            white = alwaysColorTokens.alwaysWhiteDark,
        ),
        backgroundColors = OudsColorScheme.Background(
            brandPrimary = backgroundColorTokens.bgBrandPrimaryDark,
            emphasized = backgroundColorTokens.bgEmphasizedDark,
            primary = backgroundColorTokens.bgPrimaryDark,
            secondary = backgroundColorTokens.bgSecondaryDark,
            statusAccentEmphasized = backgroundColorTokens.bgStatusAccentEmphasizedDark,
            statusAccentMuted = backgroundColorTokens.bgStatusAccentMutedDark,
            statusAccentMutedOnBgEmphasized = backgroundColorTokens.bgStatusAccentMutedOnBgEmphasizedDark,
            statusInfoEmphasized = backgroundColorTokens.bgStatusInfoEmphasizedDark,
            statusInfoMuted = backgroundColorTokens.bgStatusInfoMutedDark,
            statusInfoMutedOnBgEmphasized = backgroundColorTokens.bgStatusInfoMutedOnBgEmphasizedDark,
            statusNegativeEmphasized = backgroundColorTokens.bgStatusNegativeEmphasizedDark,
            statusNegativeMuted = backgroundColorTokens.bgStatusNegativeMutedDark,
            statusNegativeMutedOnBgEmphasized = backgroundColorTokens.bgStatusNegativeMutedOnBgEmphasizedDark,
            statusNeutral = backgroundColorTokens.bgStatusNeutralDark,
            statusNeutralOnBgEmphasized = backgroundColorTokens.bgStatusNeutralOnBgEmphasizedDark,
            statusPositiveEmphasized = backgroundColorTokens.bgStatusPositiveEmphasizedDark,
            statusPositiveMuted = backgroundColorTokens.bgStatusPositiveMutedDark,
            statusPositiveMutedOnBgEmphasized = backgroundColorTokens.bgStatusPositiveMutedOnBgEmphasizedDark,
            statusWarningEmphasized = backgroundColorTokens.bgStatusWarningEmphasizedDark,
            statusWarningMuted = backgroundColorTokens.bgStatusWarningMutedDark,
            statusWarningMutedOnBgEmphasized = backgroundColorTokens.bgStatusWarningMutedOnBgEmphasizedDark,
            tertiary = backgroundColorTokens.bgTertiaryDark,
        ),
        borderColors = OudsColorScheme.Border(
            brandPrimary = borderColorTokens.borderBrandPrimaryDark,
            brandPrimaryOnBgEmphasized = borderColorTokens.borderBrandPrimaryOnBgEmphasizedDark,
            default = borderColorTokens.borderDefaultDark,
            defaultOnBgEmphasized = borderColorTokens.borderDefaultOnBgEmphasizedDark,
            emphasized = borderColorTokens.borderEmphasizedDark,
            emphasizedOnBgEmphasized = borderColorTokens.borderEmphasizedOnBgEmphasizedDark,
            focus = borderColorTokens.borderFocusDark,
            focusInset = borderColorTokens.borderFocusInsetDark,
            focusInsetOnBgEmphasized = borderColorTokens.borderFocusInsetOnBgEmphasizedDark,
            focusOnBgEmphasized = borderColorTokens.borderFocusOnBgEmphasizedDark,
            onBrandPrimary = borderColorTokens.borderOnBrandPrimaryDark,
        ),
        brandColors = OudsColorScheme.Brand(
            accentDefault = brandColorTokens.brandAccentDefaultDark,
            accentHigh = brandColorTokens.brandAccentHighDark,
            accentHighest = brandColorTokens.brandAccentHighestDark,
            accentLowest = brandColorTokens.brandAccentLowestDark,
            infoDefault = brandColorTokens.brandInfoDefaultDark,
            infoHighest = brandColorTokens.brandInfoHighestDark,
            infoLowest = brandColorTokens.brandInfoLowestDark,
            negativeDefault = brandColorTokens.brandNegativeDefaultDark,
            negativeHigh = brandColorTokens.brandNegativeHighDark,
            negativeHigher = brandColorTokens.brandNegativeHigherDark,
            negativeHighest = brandColorTokens.brandNegativeHighestDark,
            negativeLowest = brandColorTokens.brandNegativeLowestDark,
            neutralEmphasizedBlack = brandColorTokens.brandNeutralEmphasizedBlackDark,
            neutralEmphasizedHigh = brandColorTokens.brandNeutralEmphasizedHighDark,
            neutralEmphasizedHigher = brandColorTokens.brandNeutralEmphasizedHigherDark,
            neutralEmphasizedHighest = brandColorTokens.brandNeutralEmphasizedHighestDark,
            neutralEmphasizedLow = brandColorTokens.brandNeutralEmphasizedLowDark,
            neutralEmphasizedLower = brandColorTokens.brandNeutralEmphasizedLowerDark,
            neutralEmphasizedLowest = brandColorTokens.brandNeutralEmphasizedLowestDark,
            neutralEmphasizedMedium = brandColorTokens.brandNeutralEmphasizedMediumDark,
            neutralMutedHighest = brandColorTokens.brandNeutralMutedHighestDark,
            neutralMutedLow = brandColorTokens.brandNeutralMutedLowDark,
            neutralMutedLower = brandColorTokens.brandNeutralMutedLowerDark,
            neutralMutedLowest = brandColorTokens.brandNeutralMutedLowestDark,
            neutralMutedMedium = brandColorTokens.brandNeutralMutedMediumDark,
            neutralMutedWhite = brandColorTokens.brandNeutralMutedWhiteDark,
            positiveDefault = brandColorTokens.brandPositiveDefaultDark,
            positiveHighest = brandColorTokens.brandPositiveHighestDark,
            positiveLowest = brandColorTokens.brandPositiveLowestDark,
            primaryDefault = brandColorTokens.brandPrimaryDefaultDark,
            primaryLow = brandColorTokens.brandPrimaryLowDark,
            warningDefault = brandColorTokens.brandWarningDefaultDark,
            warningHigh = brandColorTokens.brandWarningHighDark,
            warningHighest = brandColorTokens.brandWarningHighestDark,
            warningLowest = brandColorTokens.brandWarningLowestDark,
        ),
        contentColors = OudsColorScheme.Content(
            brandPrimary = contentColorTokens.contentBrandPrimaryDark,
            brandPrimaryOnBgEmphasized = contentColorTokens.contentBrandPrimaryOnBgEmphasizedDark,
            default = contentColorTokens.contentDefaultDark,
            defaultOnBgEmphasized = contentColorTokens.contentDefaultOnBgEmphasizedDark,
            disabled = contentColorTokens.contentDisabledDark,
            disabledOnBgEmphasized = contentColorTokens.contentDisabledOnBgEmphasizedDark,
            muted = contentColorTokens.contentMutedDark,
            mutedOnBgEmphasized = contentColorTokens.contentMutedOnBgEmphasizedDark,
            onActionDisabled = contentColorTokens.contentOnActionDisabledDark,
            onActionDisabledOnBgEmphasized = contentColorTokens.contentOnActionDisabledOnBgEmphasizedDark,
            onActionNegative = contentColorTokens.contentOnActionNegativeDark,
            onActionPrimaryEnabled = contentColorTokens.contentOnActionPrimaryEnabledDark,
            onActionPrimaryEnabledOnBgEmphasized = contentColorTokens.contentOnActionPrimaryEnabledOnBgEmphasizedDark,
            onActionPrimaryFocus = contentColorTokens.contentOnActionPrimaryFocusDark,
            onActionPrimaryFocusOnBgEmphasized = contentColorTokens.contentOnActionPrimaryFocusOnBgEmphasizedDark,
            onActionPrimaryHover = contentColorTokens.contentOnActionPrimaryHoverDark,
            onActionPrimaryHoverOnBgEmphasized = contentColorTokens.contentOnActionPrimaryHoverOnBgEmphasizedDark,
            onActionPrimaryLoading = contentColorTokens.contentOnActionPrimaryLoadingDark,
            onActionPrimaryLoadingOnBgEmphasized = contentColorTokens.contentOnActionPrimaryLoadingOnBgEmphasizedDark,
            onActionPrimaryPressed = contentColorTokens.contentOnActionPrimaryPressedDark,
            onActionPrimaryPressedOnBgEmphasized = contentColorTokens.contentOnActionPrimaryPressedOnBgEmphasizedDark,
            onBrandPrimary = contentColorTokens.contentOnBrandPrimaryDark,
            onStatusAccentEmphasized = contentColorTokens.contentOnStatusAccentEmphasizedDark,
            onStatusAccentMuted = contentColorTokens.contentOnStatusAccentMutedDark,
            onStatusAccentMutedOnBgEmphasized = contentColorTokens.contentOnStatusAccentMutedOnBgEmphasizedDark,
            onStatusInfoEmphasized = contentColorTokens.contentOnStatusInfoEmphasizedDark,
            onStatusInfoMuted = contentColorTokens.contentOnStatusInfoMutedDark,
            onStatusInfoMutedOnBgEmphasized = contentColorTokens.contentOnStatusInfoMutedOnBgEmphasizedDark,
            onStatusNegativeEmphasized = contentColorTokens.contentOnStatusNegativeEmphasizedDark,
            onStatusNegativeMuted = contentColorTokens.contentOnStatusNegativeMutedDark,
            onStatusNegativeMutedOnBgEmphasized = contentColorTokens.contentOnStatusNegativeMutedOnBgEmphasizedDark,
            onStatusPositiveEmphasized = contentColorTokens.contentOnStatusPositiveEmphasizedDark,
            onStatusPositiveMuted = contentColorTokens.contentOnStatusPositiveMutedDark,
            onStatusPositiveMutedOnBgEmphasized = contentColorTokens.contentOnStatusPositiveMutedOnBgEmphasizedDark,
            onStatusWarningEmphasized = contentColorTokens.contentOnStatusWarningEmphasizedDark,
            onStatusWarningMuted = contentColorTokens.contentOnStatusWarningMutedDark,
            onStatusWarningMutedOnBgEmphasized = contentColorTokens.contentOnStatusWarningMutedOnBgEmphasizedDark,
            statusInfo = contentColorTokens.contentStatusInfoDark,
            statusNegative = contentColorTokens.contentStatusNegativeDark,
            statusPositive = contentColorTokens.contentStatusPositiveDark,
            statusWarning = contentColorTokens.contentStatusWarningDark,
        ),
        elevationColors = OudsColorScheme.Elevation(
            drag = elevationColorTokens.elevationDragDark,
            dragOnBgEmphasized = elevationColorTokens.elevationDragOnBgEmphasizedDark,
            dragOnBgSecondary = elevationColorTokens.elevationDragOnBgSecondaryDark,
            modal = elevationColorTokens.elevationModalDark,
            overlayDefault = elevationColorTokens.elevationOverlayDefaultDark,
            overlayDefaultOnBgEmphasized = elevationColorTokens.elevationOverlayDefaultOnBgEmphasizedDark,
            overlayDefaultOnBgSecondary = elevationColorTokens.elevationOverlayDefaultOnBgSecondaryDark,
            overlayEmphasized = elevationColorTokens.elevationOverlayEmphasizedDark,
            overlayEmphasizedOnBgEmphasized = elevationColorTokens.elevationOverlayEmphasizedOnBgEmphasizedDark,
            overlayEmphasizedOnBgSecondary = elevationColorTokens.elevationOverlayEmphasizedOnBgSecondaryDark,
            raised = elevationColorTokens.elevationRaisedDark,
            raisedOnBgEmphasized = elevationColorTokens.elevationRaisedOnBgEmphasizedDark,
            raisedOnBgSecondary = elevationColorTokens.elevationRaisedOnBgSecondaryDark,
        ),
        gradientColors = OudsColorScheme.Gradient(
            skeletonMiddle = gradientColorTokens.gradientSkeletonMiddleDark,
            skeletonMiddleOnBgEmphasized = gradientColorTokens.gradientSkeletonMiddleOnBgEmphasizedDark,
            skeletonStartEnd = gradientColorTokens.gradientSkeletonStartEndDark,
            skeletonStartEndOnBgEmphasized = gradientColorTokens.gradientSkeletonStartEndOnBgEmphasizedDark,
        ),
        decorativeColors = OudsColorScheme.Decorative(
            accent1Default = decorativeColorTokens.decorativeAccent1DefaultDark,
            accent1Emphasized = decorativeColorTokens.decorativeAccent1EmphasizedDark,
            accent1Muted = decorativeColorTokens.decorativeAccent1MutedDark,
            accent2Default = decorativeColorTokens.decorativeAccent2DefaultDark,
            accent2Emphasized = decorativeColorTokens.decorativeAccent2EmphasizedDark,
            accent2Muted = decorativeColorTokens.decorativeAccent2MutedDark,
            accent3Default = decorativeColorTokens.decorativeAccent3DefaultDark,
            accent3Emphasized = decorativeColorTokens.decorativeAccent3EmphasizedDark,
            accent3Muted = decorativeColorTokens.decorativeAccent3MutedDark,
            accent4Default = decorativeColorTokens.decorativeAccent4DefaultDark,
            accent4Emphasized = decorativeColorTokens.decorativeAccent4EmphasizedDark,
            accent4Muted = decorativeColorTokens.decorativeAccent4MutedDark,
            accent5Default = decorativeColorTokens.decorativeAccent5DefaultDark,
            accent5Emphasized = decorativeColorTokens.decorativeAccent5EmphasizedDark,
            accent5Muted = decorativeColorTokens.decorativeAccent5MutedDark,
            brandPrimary = decorativeColorTokens.decorativeBrandPrimaryDark,
            brandSecondary = decorativeColorTokens.decorativeBrandSecondaryDark,
            brandTertiary = decorativeColorTokens.decorativeBrandTertiaryDark,
            neutralDefault = decorativeColorTokens.decorativeNeutralDefaultDark,
            neutralEmphasized = decorativeColorTokens.decorativeNeutralEmphasizedDark,
            neutralMuted = decorativeColorTokens.decorativeNeutralMutedDark,
            skinTint100 = decorativeColorTokens.decorativeSkinTint100Dark,
            skinTint200 = decorativeColorTokens.decorativeSkinTint200Dark,
            skinTint300 = decorativeColorTokens.decorativeSkinTint300Dark,
            skinTint400 = decorativeColorTokens.decorativeSkinTint400Dark,
            skinTint500 = decorativeColorTokens.decorativeSkinTint500Dark,
            skinTint600 = decorativeColorTokens.decorativeSkinTint600Dark,
            skinTint700 = decorativeColorTokens.decorativeSkinTint700Dark,
            skinTint800 = decorativeColorTokens.decorativeSkinTint800Dark,
            skinTint900 = decorativeColorTokens.decorativeSkinTint900Dark,
        ),
    )

@Stable
fun OudsColorScheme.fromToken(token: OudsColorActionKeyToken): Color {
    return when (token) {
        OudsColorActionKeyToken.Disabled -> actionColors.disabled
        OudsColorActionKeyToken.DisabledOnBgEmphasized -> actionColors.disabledOnBgEmphasized
        OudsColorActionKeyToken.NegativeEnabled -> actionColors.negativeEnabled
        OudsColorActionKeyToken.NegativeFocus -> actionColors.negativeFocus
        OudsColorActionKeyToken.NegativeHover -> actionColors.negativeHover
        OudsColorActionKeyToken.NegativeLoading -> actionColors.negativeLoading
        OudsColorActionKeyToken.NegativePressed -> actionColors.negativePressed
        OudsColorActionKeyToken.PrimaryEnabled -> actionColors.primaryEnabled
        OudsColorActionKeyToken.PrimaryEnabledOnBgEmphasized -> actionColors.primaryEnabledOnBgEmphasized
        OudsColorActionKeyToken.PrimaryFocus -> actionColors.primaryFocus
        OudsColorActionKeyToken.PrimaryFocusOnBgEmphasized -> actionColors.primaryFocusOnBgEmphasized
        OudsColorActionKeyToken.PrimaryHover -> actionColors.primaryHover
        OudsColorActionKeyToken.PrimaryHoverOnBgEmphasized -> actionColors.primaryHoverOnBgEmphasized
        OudsColorActionKeyToken.PrimaryLoading -> actionColors.primaryLoading
        OudsColorActionKeyToken.PrimaryLoadingOnBgEmphasized -> actionColors.primaryLoadingOnBgEmphasized
        OudsColorActionKeyToken.PrimaryPressed -> actionColors.primaryPressed
        OudsColorActionKeyToken.PrimaryPressedOnBgEmphasized -> actionColors.primaryPressedOnBgEmphasized
        OudsColorActionKeyToken.SecondaryEnabled -> actionColors.secondaryEnabled
        OudsColorActionKeyToken.SecondaryEnabledOnBgEmphasized -> actionColors.secondaryEnabledOnBgEmphasized
        OudsColorActionKeyToken.SecondaryFocus -> actionColors.secondaryFocus
        OudsColorActionKeyToken.SecondaryFocusOnBgEmphasized -> actionColors.secondaryFocusOnBgEmphasized
        OudsColorActionKeyToken.SecondaryHover -> actionColors.secondaryHover
        OudsColorActionKeyToken.SecondaryHoverOnBgEmphasized -> actionColors.secondaryHoverOnBgEmphasized
        OudsColorActionKeyToken.SecondaryLoading -> actionColors.secondaryLoading
        OudsColorActionKeyToken.SecondaryLoadingOnBgEmphasized -> actionColors.secondaryLoadingOnBgEmphasized
        OudsColorActionKeyToken.SecondaryPressed -> actionColors.secondaryPressed
        OudsColorActionKeyToken.SecondaryPressedOnBgEmphasized -> actionColors.secondaryPressedOnBgEmphasized
        OudsColorActionKeyToken.Selected -> actionColors.selected
        OudsColorActionKeyToken.SelectedOnBgEmphasized -> actionColors.selectedOnBgEmphasized
        OudsColorActionKeyToken.Visited -> actionColors.visited
        OudsColorActionKeyToken.VisitedOnBgEmphasized -> actionColors.visitedOnBgEmphasized
    }
}

@Stable
fun OudsColorScheme.fromToken(token: OudsColorAlwaysKeyToken): Color {
    return when (token) {
        OudsColorAlwaysKeyToken.Accent -> alwaysColors.accent
        OudsColorAlwaysKeyToken.Black -> alwaysColors.black
        OudsColorAlwaysKeyToken.Info -> alwaysColors.info
        OudsColorAlwaysKeyToken.Negative -> alwaysColors.negative
        OudsColorAlwaysKeyToken.OnAccent -> alwaysColors.onAccent
        OudsColorAlwaysKeyToken.OnBlack -> alwaysColors.onBlack
        OudsColorAlwaysKeyToken.OnInfo -> alwaysColors.onInfo
        OudsColorAlwaysKeyToken.OnNegative -> alwaysColors.onNegative
        OudsColorAlwaysKeyToken.OnPositive -> alwaysColors.onPositive
        OudsColorAlwaysKeyToken.OnWarning -> alwaysColors.onWarning
        OudsColorAlwaysKeyToken.OnWhite -> alwaysColors.onWhite
        OudsColorAlwaysKeyToken.Positive -> alwaysColors.positive
        OudsColorAlwaysKeyToken.Warning -> alwaysColors.warning
        OudsColorAlwaysKeyToken.White -> alwaysColors.white
    }
}

@Stable
fun OudsColorScheme.fromToken(token: OudsColorBackgroundKeyToken): Color {
    return when (token) {
        OudsColorBackgroundKeyToken.BrandPrimary -> backgroundColors.brandPrimary
        OudsColorBackgroundKeyToken.Emphasized -> backgroundColors.emphasized
        OudsColorBackgroundKeyToken.Primary -> backgroundColors.primary
        OudsColorBackgroundKeyToken.Secondary -> backgroundColors.secondary
        OudsColorBackgroundKeyToken.StatusAccentEmphasized -> backgroundColors.statusAccentEmphasized
        OudsColorBackgroundKeyToken.StatusAccentMuted -> backgroundColors.statusAccentMuted
        OudsColorBackgroundKeyToken.StatusAccentMutedOnBgEmphasized -> backgroundColors.statusAccentMutedOnBgEmphasized
        OudsColorBackgroundKeyToken.StatusInfoEmphasized -> backgroundColors.statusInfoEmphasized
        OudsColorBackgroundKeyToken.StatusInfoMuted -> backgroundColors.statusInfoMuted
        OudsColorBackgroundKeyToken.StatusInfoMutedOnBgEmphasized -> backgroundColors.statusInfoMutedOnBgEmphasized
        OudsColorBackgroundKeyToken.StatusNegativeEmphasized -> backgroundColors.statusNegativeEmphasized
        OudsColorBackgroundKeyToken.StatusNegativeMuted -> backgroundColors.statusNegativeMuted
        OudsColorBackgroundKeyToken.StatusNegativeMutedOnBgEmphasized -> backgroundColors.statusNegativeMutedOnBgEmphasized
        OudsColorBackgroundKeyToken.StatusNeutral -> backgroundColors.statusNeutral
        OudsColorBackgroundKeyToken.StatusNeutralOnBgEmphasized -> backgroundColors.statusNeutralOnBgEmphasized
        OudsColorBackgroundKeyToken.StatusPositiveEmphasized -> backgroundColors.statusPositiveEmphasized
        OudsColorBackgroundKeyToken.StatusPositiveMuted -> backgroundColors.statusPositiveMuted
        OudsColorBackgroundKeyToken.StatusPositiveMutedOnBgEmphasized -> backgroundColors.statusPositiveMutedOnBgEmphasized
        OudsColorBackgroundKeyToken.StatusWarningEmphasized -> backgroundColors.statusWarningEmphasized
        OudsColorBackgroundKeyToken.StatusWarningMuted -> backgroundColors.statusWarningMuted
        OudsColorBackgroundKeyToken.StatusWarningMutedOnBgEmphasized -> backgroundColors.statusWarningMutedOnBgEmphasized
        OudsColorBackgroundKeyToken.Tertiary -> backgroundColors.tertiary
    }
}

@Stable
fun OudsColorScheme.fromToken(token: OudsColorBorderKeyToken): Color {
    return when (token) {
        OudsColorBorderKeyToken.BrandPrimary -> borderColors.brandPrimary
        OudsColorBorderKeyToken.BrandPrimaryOnBgEmphasized -> borderColors.brandPrimaryOnBgEmphasized
        OudsColorBorderKeyToken.Default -> borderColors.default
        OudsColorBorderKeyToken.DefaultOnBgEmphasized -> borderColors.defaultOnBgEmphasized
        OudsColorBorderKeyToken.Emphasized -> borderColors.emphasized
        OudsColorBorderKeyToken.EmphasizedOnBgEmphasized -> borderColors.emphasizedOnBgEmphasized
        OudsColorBorderKeyToken.Focus -> borderColors.focus
        OudsColorBorderKeyToken.FocusInset -> borderColors.focusInset
        OudsColorBorderKeyToken.FocusInsetOnBgEmphasized -> borderColors.focusInsetOnBgEmphasized
        OudsColorBorderKeyToken.FocusOnBgEmphasized -> borderColors.focusOnBgEmphasized
        OudsColorBorderKeyToken.OnBrandPrimary -> borderColors.onBrandPrimary
    }
}

@Stable
fun OudsColorScheme.fromToken(token: OudsColorBrandKeyToken): Color {
    return when (token) {
        OudsColorBrandKeyToken.AccentDefault -> brandColors.accentDefault
        OudsColorBrandKeyToken.AccentHigh -> brandColors.accentHigh
        OudsColorBrandKeyToken.AccentHighest -> brandColors.accentHighest
        OudsColorBrandKeyToken.AccentLowest -> brandColors.accentLowest
        OudsColorBrandKeyToken.InfoDefault -> brandColors.infoDefault
        OudsColorBrandKeyToken.InfoHighest -> brandColors.infoHighest
        OudsColorBrandKeyToken.InfoLowest -> brandColors.infoLowest
        OudsColorBrandKeyToken.NegativeDefault -> brandColors.negativeDefault
        OudsColorBrandKeyToken.NegativeHigh -> brandColors.negativeHigh
        OudsColorBrandKeyToken.NegativeHigher -> brandColors.negativeHigher
        OudsColorBrandKeyToken.NegativeHighest -> brandColors.negativeHighest
        OudsColorBrandKeyToken.NegativeLowest -> brandColors.negativeLowest
        OudsColorBrandKeyToken.NeutralEmphasizedBlack -> brandColors.neutralEmphasizedBlack
        OudsColorBrandKeyToken.NeutralEmphasizedHigh -> brandColors.neutralEmphasizedHigh
        OudsColorBrandKeyToken.NeutralEmphasizedHigher -> brandColors.neutralEmphasizedHigher
        OudsColorBrandKeyToken.NeutralEmphasizedHighest -> brandColors.neutralEmphasizedHighest
        OudsColorBrandKeyToken.NeutralEmphasizedLow -> brandColors.neutralEmphasizedLow
        OudsColorBrandKeyToken.NeutralEmphasizedLower -> brandColors.neutralEmphasizedLower
        OudsColorBrandKeyToken.NeutralEmphasizedLowest -> brandColors.neutralEmphasizedLowest
        OudsColorBrandKeyToken.NeutralEmphasizedMedium -> brandColors.neutralEmphasizedMedium
        OudsColorBrandKeyToken.NeutralMutedHighest -> brandColors.neutralMutedHighest
        OudsColorBrandKeyToken.NeutralMutedLow -> brandColors.neutralMutedLow
        OudsColorBrandKeyToken.NeutralMutedLower -> brandColors.neutralMutedLower
        OudsColorBrandKeyToken.NeutralMutedLowest -> brandColors.neutralMutedLowest
        OudsColorBrandKeyToken.NeutralMutedMedium -> brandColors.neutralMutedMedium
        OudsColorBrandKeyToken.NeutralMutedWhite -> brandColors.neutralMutedWhite
        OudsColorBrandKeyToken.PositiveDefault -> brandColors.positiveDefault
        OudsColorBrandKeyToken.PositiveHighest -> brandColors.positiveHighest
        OudsColorBrandKeyToken.PositiveLowest -> brandColors.positiveLowest
        OudsColorBrandKeyToken.PrimaryDefault -> brandColors.primaryDefault
        OudsColorBrandKeyToken.PrimaryLow -> brandColors.primaryLow
        OudsColorBrandKeyToken.WarningDefault -> brandColors.warningDefault
        OudsColorBrandKeyToken.WarningHigh -> brandColors.warningHigh
        OudsColorBrandKeyToken.WarningHighest -> brandColors.warningHighest
        OudsColorBrandKeyToken.WarningLowest -> brandColors.warningLowest
    }
}

@Stable
fun OudsColorScheme.fromToken(token: OudsColorContentKeyToken): Color {
    return when (token) {
        OudsColorContentKeyToken.BrandPrimary -> contentColors.brandPrimary
        OudsColorContentKeyToken.BrandPrimaryOnBgEmphasized -> contentColors.brandPrimaryOnBgEmphasized
        OudsColorContentKeyToken.Default -> contentColors.default
        OudsColorContentKeyToken.DefaultOnBgEmphasized -> contentColors.defaultOnBgEmphasized
        OudsColorContentKeyToken.Disabled -> contentColors.disabled
        OudsColorContentKeyToken.DisabledOnBgEmphasized -> contentColors.disabledOnBgEmphasized
        OudsColorContentKeyToken.Muted -> contentColors.muted
        OudsColorContentKeyToken.MutedOnBgEmphasized -> contentColors.mutedOnBgEmphasized
        OudsColorContentKeyToken.OnActionDisabled -> contentColors.onActionDisabled
        OudsColorContentKeyToken.OnActionDisabledOnBgEmphasized -> contentColors.onActionDisabledOnBgEmphasized
        OudsColorContentKeyToken.OnActionNegative -> contentColors.onActionNegative
        OudsColorContentKeyToken.OnActionPrimaryEnabled -> contentColors.onActionPrimaryEnabled
        OudsColorContentKeyToken.OnActionPrimaryEnabledOnBgEmphasized -> contentColors.onActionPrimaryEnabledOnBgEmphasized
        OudsColorContentKeyToken.OnActionPrimaryFocus -> contentColors.onActionPrimaryFocus
        OudsColorContentKeyToken.OnActionPrimaryFocusOnBgEmphasized -> contentColors.onActionPrimaryFocusOnBgEmphasized
        OudsColorContentKeyToken.OnActionPrimaryHover -> contentColors.onActionPrimaryHover
        OudsColorContentKeyToken.OnActionPrimaryHoverOnBgEmphasized -> contentColors.onActionPrimaryHoverOnBgEmphasized
        OudsColorContentKeyToken.OnActionPrimaryLoading -> contentColors.onActionPrimaryLoading
        OudsColorContentKeyToken.OnActionPrimaryLoadingOnBgEmphasized -> contentColors.onActionPrimaryLoadingOnBgEmphasized
        OudsColorContentKeyToken.OnActionPrimaryPressed -> contentColors.onActionPrimaryPressed
        OudsColorContentKeyToken.OnActionPrimaryPressedOnBgEmphasized -> contentColors.onActionPrimaryPressedOnBgEmphasized
        OudsColorContentKeyToken.OnBrandPrimary -> contentColors.onBrandPrimary
        OudsColorContentKeyToken.OnStatusAccentEmphasized -> contentColors.onStatusAccentEmphasized
        OudsColorContentKeyToken.OnStatusAccentMuted -> contentColors.onStatusAccentMuted
        OudsColorContentKeyToken.OnStatusAccentMutedOnBgEmphasized -> contentColors.onStatusAccentMutedOnBgEmphasized
        OudsColorContentKeyToken.OnStatusInfoEmphasized -> contentColors.onStatusInfoEmphasized
        OudsColorContentKeyToken.OnStatusInfoMuted -> contentColors.onStatusInfoMuted
        OudsColorContentKeyToken.OnStatusInfoMutedOnBgEmphasized -> contentColors.onStatusInfoMutedOnBgEmphasized
        OudsColorContentKeyToken.OnStatusNegativeEmphasized -> contentColors.onStatusNegativeEmphasized
        OudsColorContentKeyToken.OnStatusNegativeMuted -> contentColors.onStatusNegativeMuted
        OudsColorContentKeyToken.OnStatusNegativeMutedOnBgEmphasized -> contentColors.onStatusNegativeMutedOnBgEmphasized
        OudsColorContentKeyToken.OnStatusPositiveEmphasized -> contentColors.onStatusPositiveEmphasized
        OudsColorContentKeyToken.OnStatusPositiveMuted -> contentColors.onStatusPositiveMuted
        OudsColorContentKeyToken.OnStatusPositiveMutedOnBgEmphasized -> contentColors.onStatusPositiveMutedOnBgEmphasized
        OudsColorContentKeyToken.OnStatusWarningEmphasized -> contentColors.onStatusWarningEmphasized
        OudsColorContentKeyToken.OnStatusWarningMuted -> contentColors.onStatusWarningMuted
        OudsColorContentKeyToken.OnStatusWarningMutedOnBgEmphasized -> contentColors.onStatusWarningMutedOnBgEmphasized
        OudsColorContentKeyToken.StatusInfo -> contentColors.statusInfo
        OudsColorContentKeyToken.StatusNegative -> contentColors.statusNegative
        OudsColorContentKeyToken.StatusPositive -> contentColors.statusPositive
        OudsColorContentKeyToken.StatusWarning -> contentColors.statusWarning
    }
}

@Stable
fun OudsColorScheme.fromToken(token: OudsColorElevationKeyToken): Color {
    return when (token) {
        OudsColorElevationKeyToken.Drag -> elevationColors.drag
        OudsColorElevationKeyToken.DragOnBgEmphasized -> elevationColors.dragOnBgEmphasized
        OudsColorElevationKeyToken.DragOnBgSecondary -> elevationColors.dragOnBgSecondary
        OudsColorElevationKeyToken.Modal -> elevationColors.modal
        OudsColorElevationKeyToken.OverlayDefault -> elevationColors.overlayDefault
        OudsColorElevationKeyToken.OverlayDefaultOnBgEmphasized -> elevationColors.overlayDefaultOnBgEmphasized
        OudsColorElevationKeyToken.OverlayDefaultOnBgSecondary -> elevationColors.overlayDefaultOnBgSecondary
        OudsColorElevationKeyToken.OverlayEmphasized -> elevationColors.overlayEmphasized
        OudsColorElevationKeyToken.OverlayEmphasizedOnBgEmphasized -> elevationColors.overlayEmphasizedOnBgEmphasized
        OudsColorElevationKeyToken.OverlayEmphasizedOnBgSecondary -> elevationColors.overlayEmphasizedOnBgSecondary
        OudsColorElevationKeyToken.Raised -> elevationColors.raised
        OudsColorElevationKeyToken.RaisedOnBgEmphasized -> elevationColors.raisedOnBgEmphasized
        OudsColorElevationKeyToken.RaisedOnBgSecondary -> elevationColors.raisedOnBgSecondary
    }
}

@Stable
fun OudsColorScheme.fromToken(token: OudsColorGradientKeyToken): Color {
    return when (token) {
        OudsColorGradientKeyToken.SkeletonMiddle -> gradientColors.skeletonMiddle
        OudsColorGradientKeyToken.SkeletonMiddleOnBgEmphasized -> gradientColors.skeletonMiddleOnBgEmphasized
        OudsColorGradientKeyToken.SkeletonStartEnd -> gradientColors.skeletonStartEnd
        OudsColorGradientKeyToken.SkeletonStartEndOnBgEmphasized -> gradientColors.skeletonStartEndOnBgEmphasized
    }
}

@Stable
fun OudsColorScheme.fromToken(token: OudsColorDecorativeKeyToken): Color {
    return when (token) {
        OudsColorDecorativeKeyToken.Accent1Default -> decorativeColors.accent1Default
        OudsColorDecorativeKeyToken.Accent1Emphasized -> decorativeColors.accent1Emphasized
        OudsColorDecorativeKeyToken.Accent1Muted -> decorativeColors.accent1Muted
        OudsColorDecorativeKeyToken.Accent2Default -> decorativeColors.accent2Default
        OudsColorDecorativeKeyToken.Accent2Emphasized -> decorativeColors.accent2Emphasized
        OudsColorDecorativeKeyToken.Accent2Muted -> decorativeColors.accent2Muted
        OudsColorDecorativeKeyToken.Accent3Default -> decorativeColors.accent3Default
        OudsColorDecorativeKeyToken.Accent3Emphasized -> decorativeColors.accent3Emphasized
        OudsColorDecorativeKeyToken.Accent3Muted -> decorativeColors.accent3Muted
        OudsColorDecorativeKeyToken.Accent4Default -> decorativeColors.accent4Default
        OudsColorDecorativeKeyToken.Accent4Emphasized -> decorativeColors.accent4Emphasized
        OudsColorDecorativeKeyToken.Accent4Muted -> decorativeColors.accent4Muted
        OudsColorDecorativeKeyToken.Accent5Default -> decorativeColors.accent5Default
        OudsColorDecorativeKeyToken.Accent5Emphasized -> decorativeColors.accent5Emphasized
        OudsColorDecorativeKeyToken.Accent5Muted -> decorativeColors.accent5Muted
        OudsColorDecorativeKeyToken.BrandPrimary -> decorativeColors.brandPrimary
        OudsColorDecorativeKeyToken.BrandSecondary -> decorativeColors.brandSecondary
        OudsColorDecorativeKeyToken.BrandTertiary -> decorativeColors.brandTertiary
        OudsColorDecorativeKeyToken.NeutralDefault -> decorativeColors.neutralDefault
        OudsColorDecorativeKeyToken.NeutralEmphasized -> decorativeColors.neutralEmphasized
        OudsColorDecorativeKeyToken.NeutralMuted -> decorativeColors.neutralMuted
        OudsColorDecorativeKeyToken.SkinTint100 -> decorativeColors.skinTint100
        OudsColorDecorativeKeyToken.SkinTint200 -> decorativeColors.skinTint200
        OudsColorDecorativeKeyToken.SkinTint300 -> decorativeColors.skinTint300
        OudsColorDecorativeKeyToken.SkinTint400 -> decorativeColors.skinTint400
        OudsColorDecorativeKeyToken.SkinTint500 -> decorativeColors.skinTint500
        OudsColorDecorativeKeyToken.SkinTint600 -> decorativeColors.skinTint600
        OudsColorDecorativeKeyToken.SkinTint700 -> decorativeColors.skinTint700
        OudsColorDecorativeKeyToken.SkinTint800 -> decorativeColors.skinTint800
        OudsColorDecorativeKeyToken.SkinTint900 -> decorativeColors.skinTint900
    }
}

@Stable
fun OudsColorScheme.fromToken(token: OudsColorGlobalKeyToken): Color {
    return when (token) {
        OudsColorGlobalKeyToken.TransparentDefault -> globalColors.transparentDefault
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
 * Converts an OUDS action color token to the local color value provided by the theme.
 */
val OudsColorActionKeyToken.value: Color
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.colorScheme.fromToken(this)

/**
 * Converts an OUDS always color token to the local color value provided by the theme.
 */
val OudsColorAlwaysKeyToken.value: Color
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.colorScheme.fromToken(this)

/**
 * Converts an OUDS background color token to the local color value provided by the theme.
 */
val OudsColorBackgroundKeyToken.value: Color
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.colorScheme.fromToken(this)

/**
 * Converts an OUDS border color token to the local color value provided by the theme.
 */
val OudsColorBorderKeyToken.value: Color
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.colorScheme.fromToken(this)

/**
 * Converts an OUDS brand color token to the local color value provided by the theme.
 */
val OudsColorBrandKeyToken.value: Color
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.colorScheme.fromToken(this)

/**
 * Converts an OUDS content color token to the local color value provided by the theme.
 */
val OudsColorContentKeyToken.value: Color
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.colorScheme.fromToken(this)

/**
 * Converts an OUDS elevation color token to the local color value provided by the theme.
 */
val OudsColorElevationKeyToken.value: Color
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.colorScheme.fromToken(this)

/**
 * Converts an OUDS gradient color token to the local color value provided by the theme.
 */
val OudsColorGradientKeyToken.value: Color
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.colorScheme.fromToken(this)

/**
 * Converts an OUDS decorative color token to the local color value provided by the theme.
 */
val OudsColorDecorativeKeyToken.value: Color
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.colorScheme.fromToken(this)
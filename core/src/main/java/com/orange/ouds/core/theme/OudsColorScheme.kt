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
import com.orange.ouds.foundation.InternalOudsApi
import com.orange.ouds.theme.tokens.OudsColorKeyToken
import com.orange.ouds.theme.tokens.android.OudsAndroidColorDarkTokens
import com.orange.ouds.theme.tokens.android.OudsAndroidColorLightTokens
import com.orange.ouds.theme.tokens.semantic.OudsColorSemanticTokens

/**
 * @suppress
 */
data class OudsColorScheme(
    val action: Action,
    val always: Always,
    val background: Background,
    val border: Border,
    val content: Content,
    val decorative: Decorative,
    val opacity: Opacity,
    val overlay: Overlay,
    val repository: Repository,
    val surface: Surface
) {

    data class Action(
        val disabled: Color,
        val enabled: Color,
        val focus: Color,
        val highlighted: Color,
        val hover: Color,
        val loading: Color,
        val negative: Negative,
        val pressed: Color,
        val selected: Color,
        val support: Support,
        val visited: Color
    ) {
        data class Negative(
            val enabled: Color,
            val focus: Color,
            val hover: Color,
            val loading: Color,
            val pressed: Color
        )

        data class Support(
            val enabled: Color,
            val focus: Color,
            val hover: Color,
            val loading: Color,
            val pressed: Color
        )
    }

    data class Always(
        val black: Color,
        val onBlack: Color,
        val onWhite: Color,
        val white: Color
    )

    data class Background(
        val emphasized: Color,
        val primary: Color,
        val secondary: Color,
        val tertiary: Color
    )

    data class Border(
        val brandPrimary: Color,
        val default: Color,
        val emphasized: Color,
        val focus: Color,
        val focusInset: Color,
        val onBrand: OnBrand
    ) {
        data class OnBrand(
            val primary: Color
        )
    }

    data class Content(
        val brandPrimary: Color,
        val default: Color,
        val disabled: Color,
        val muted: Color,
        val onAction: OnAction,
        val onBrand: OnBrand,
        val onOverlay: OnOverlay,
        val onStatus: OnStatus,
        val status: Status
    ) {
        data class OnAction(
            val disabled: Color,
            val enabled: Color,
            val focus: Color,
            val highlighted: Color,
            val hover: Color,
            val loading: Color,
            val negative: Color,
            val pressed: Color
        )

        data class OnBrand(
            val primary: Color
        )

        data class OnOverlay(
            val emphasized: Color
        )

        data class OnStatus(
            val emphasized: Color,
            val emphasizedNeutral: Color,
            val muted: Color
        )

        data class Status(
            val info: Color,
            val negative: Color,
            val positive: Color,
            val warning: Color
        )
    }

    data class Decorative(
        val accent1: Accent1,
        val accent2: Accent2,
        val accent3: Accent3,
        val accent4: Accent4,
        val accent5: Accent5,
        val brand: Brand,
        val neutral: Neutral,
        val skin: Skin
    ) {
        data class Accent1(
            val default: Color,
            val emphasized: Color,
            val muted: Color
        )

        data class Accent2(
            val default: Color,
            val emphasized: Color,
            val muted: Color
        )

        data class Accent3(
            val default: Color,
            val emphasized: Color,
            val muted: Color
        )

        data class Accent4(
            val default: Color,
            val emphasized: Color,
            val muted: Color
        )

        data class Accent5(
            val default: Color,
            val emphasized: Color,
            val muted: Color
        )

        data class Brand(
            val primary: Color,
            val secondary: Color,
            val tertiary: Color
        )

        data class Neutral(
            val default: Color,
            val emphasized: Color,
            val muted: Color
        )

        data class Skin(
            val tint100: Color,
            val tint200: Color,
            val tint300: Color,
            val tint400: Color,
            val tint500: Color,
            val tint600: Color,
            val tint700: Color,
            val tint800: Color,
            val tint900: Color
        )
    }

    data class Opacity(
        val transparent: Color,
        val lowest: Color,
        val lower: Color
    )

    data class Overlay(
        val default: Color,
        val drag: Color,
        val emphasized: Color,
        val modal: Color
    )

    data class Repository(
        val accent: Accent,
        val info: Info,
        val negative: Negative,
        val neutral: Neutral,
        val opacity: Opacity,
        val positive: Positive,
        val primary: Primary,
        val warning: Warning
    ) {
        data class Accent(
            val default: Color,
            val highest: Color,
            val low: Color,
            val lowest: Color
        )

        data class Info(
            val default: Color,
            val highest: Color,
            val low: Color,
            val lowest: Color
        )

        data class Negative(
            val default: Color,
            val high: Color,
            val higher: Color,
            val highest: Color,
            val low: Color,
            val lower: Color,
            val lowest: Color
        )

        data class Neutral(
            val emphasized: Emphasized,
            val muted: Muted
        ) {
            data class Emphasized(
                val black: Color,
                val high: Color,
                val higher: Color,
                val highest: Color,
                val medium: Color
            )

            data class Muted(
                val lower: Color,
                val lowest: Color,
                val white: Color
            )
        }

        data class Opacity(
            val black: Black,
            val info: Color,
            val negative: Color,
            val positive: Color,
            val warning: Color,
            val white: White
        ) {
            data class Black(
                val higher: Color,
                val highest: Color,
                val low: Color,
                val lower: Color,
                val lowest: Color,
                val medium: Color,
                val transparent: Color
            )

            data class White(
                val high: Color,
                val higher: Color,
                val highest: Color,
                val low: Color,
                val lower: Color,
                val lowest: Color,
                val transparent: Color
            )
        }

        data class Positive(
            val default: Color,
            val highest: Color,
            val low: Color,
            val lowest: Color
        )

        data class Primary(
            val default: Color,
            val low: Color
        )

        data class Warning(
            val default: Color,
            val highest: Color,
            val low: Color,
            val lowest: Color
        )
    }

    data class Surface(
        val brand: Brand,
        val status: Status
    ) {
        data class Brand(
            val primary: Color
        )

        data class Status(
            val accent: Accent,
            val info: Info,
            val negative: Negative,
            val neutral: Neutral,
            val positive: Positive,
            val warning: Warning
        ) {
            data class Accent(
                val emphasized: Color,
                val muted: Color
            )

            data class Info(
                val emphasized: Color,
                val muted: Color
            )

            data class Negative(
                val emphasized: Color,
                val muted: Color
            )

            data class Neutral(
                val emphasized: Color,
                val muted: Color
            )

            data class Positive(
                val emphasized: Color,
                val muted: Color
            )

            data class Warning(
                val emphasized: Color,
                val muted: Color
            )
        }
    }
}

internal val OudsColorSemanticTokens.lightColorScheme: OudsColorScheme
    get() = OudsColorScheme(
        action = with(actionColorTokens) {
            OudsColorScheme.Action(
                disabled = actionDisabledLight,
                negative = OudsColorScheme.Action.Negative(
                    enabled = actionNegativeEnabledLight,
                    focus = actionNegativeFocusLight,
                    hover = actionNegativeHoverLight,
                    loading = actionNegativeLoadingLight,
                    pressed = actionNegativePressedLight,
                ),
                enabled = actionEnabledLight,
                focus = actionFocusLight,
                highlighted = actionHighlightedLight,
                hover = actionHoverLight,
                loading = actionLoadingLight,
                pressed = actionPressedLight,
                support = OudsColorScheme.Action.Support(
                    enabled = actionSupportEnabledLight,
                    focus = actionSupportFocusLight,
                    hover = actionSupportHoverLight,
                    loading = actionSupportLoadingLight,
                    pressed = actionSupportPressedLight,
                ),
                selected = actionSelectedLight,
                visited = actionVisitedLight,
            )
        },
        always = with(alwaysColorTokens) {
            OudsColorScheme.Always(
                black = alwaysBlackLight,
                onBlack = alwaysOnBlackLight,
                onWhite = alwaysOnWhiteLight,
                white = alwaysWhiteLight,
            )
        },
        background = with(backgroundColorTokens) {
            OudsColorScheme.Background(
                emphasized = bgEmphasizedLight,
                primary = bgPrimaryLight,
                secondary = bgSecondaryLight,
                tertiary = bgTertiaryLight,
            )
        },
        border = with(borderColorTokens) {
            OudsColorScheme.Border(
                brandPrimary = borderBrandPrimaryLight,
                default = borderDefaultLight,
                emphasized = borderEmphasizedLight,
                focus = borderFocusLight,
                focusInset = borderFocusInsetLight,
                onBrand = OudsColorScheme.Border.OnBrand(
                    primary = borderOnBrandPrimaryLight,
                ),
            )
        },
        content = with(contentColorTokens) {
            OudsColorScheme.Content(
                brandPrimary = contentBrandPrimaryLight,
                default = contentDefaultLight,
                disabled = contentDisabledLight,
                muted = contentMutedLight,
                onAction = OudsColorScheme.Content.OnAction(
                    disabled = contentOnActionDisabledLight,
                    negative = contentOnActionNegativeLight,
                    enabled = contentOnActionEnabledLight,
                    focus = contentOnActionFocusLight,
                    highlighted = contentOnActionHighlightedLight,
                    hover = contentOnActionHoverLight,
                    loading = contentOnActionLoadingLight,
                    pressed = contentOnActionPressedLight,
                ),
                onBrand = OudsColorScheme.Content.OnBrand(
                    primary = contentOnBrandPrimaryLight
                ),
                onOverlay = OudsColorScheme.Content.OnOverlay(
                    emphasized = contentOnOverlayEmphasizedLight,
                ),
                onStatus = OudsColorScheme.Content.OnStatus(
                    emphasized = contentOnStatusEmphasizedLight,
                    emphasizedNeutral = contentOnStatusEmphasizedNeutralLight,
                    muted = contentOnStatusMutedLight,
                ),
                status = OudsColorScheme.Content.Status(
                    info = contentStatusInfoLight,
                    negative = contentStatusNegativeLight,
                    positive = contentStatusPositiveLight,
                    warning = contentStatusWarningLight,
                ),
            )
        },
        decorative = with(decorativeColorTokens) {
            OudsColorScheme.Decorative(
                accent1 = OudsColorScheme.Decorative.Accent1(
                    default = decorativeAccent1DefaultLight,
                    emphasized = decorativeAccent1EmphasizedLight,
                    muted = decorativeAccent1MutedLight,
                ),
                accent2 = OudsColorScheme.Decorative.Accent2(
                    default = decorativeAccent2DefaultLight,
                    emphasized = decorativeAccent2EmphasizedLight,
                    muted = decorativeAccent2MutedLight,
                ),
                accent3 = OudsColorScheme.Decorative.Accent3(
                    default = decorativeAccent3DefaultLight,
                    emphasized = decorativeAccent3EmphasizedLight,
                    muted = decorativeAccent3MutedLight,
                ),
                accent4 = OudsColorScheme.Decorative.Accent4(
                    default = decorativeAccent4DefaultLight,
                    emphasized = decorativeAccent4EmphasizedLight,
                    muted = decorativeAccent4MutedLight,
                ),
                accent5 = OudsColorScheme.Decorative.Accent5(
                    default = decorativeAccent5DefaultLight,
                    emphasized = decorativeAccent5EmphasizedLight,
                    muted = decorativeAccent5MutedLight,
                ),
                brand = OudsColorScheme.Decorative.Brand(
                    primary = decorativeBrandPrimaryLight,
                    secondary = decorativeBrandSecondaryLight,
                    tertiary = decorativeBrandTertiaryLight,
                ),
                neutral = OudsColorScheme.Decorative.Neutral(
                    default = decorativeNeutralDefaultLight,
                    emphasized = decorativeNeutralEmphasizedLight,
                    muted = decorativeNeutralMutedLight,
                ),
                skin = OudsColorScheme.Decorative.Skin(
                    tint100 = decorativeSkinTint100Light,
                    tint200 = decorativeSkinTint200Light,
                    tint300 = decorativeSkinTint300Light,
                    tint400 = decorativeSkinTint400Light,
                    tint500 = decorativeSkinTint500Light,
                    tint600 = decorativeSkinTint600Light,
                    tint700 = decorativeSkinTint700Light,
                    tint800 = decorativeSkinTint800Light,
                    tint900 = decorativeSkinTint900Light,
                ),
            )
        },
        opacity = with(opacityColorTokens) {
            OudsColorScheme.Opacity(
                transparent = opacityTransparentLight,
                lowest = opacityLowestLight,
                lower = opacityLowerLight,
            )
        },
        overlay = with(overlayColorTokens) {
            OudsColorScheme.Overlay(
                default = overlayDefaultLight,
                drag = overlayDragLight,
                emphasized = overlayEmphasizedLight,
                modal = overlayModalLight
            )
        },
        repository = with(repositoryColorTokens) {
            OudsColorScheme.Repository(
                accent = OudsColorScheme.Repository.Accent(
                    default = repositoryAccentDefaultLight,
                    highest = repositoryAccentHighestLight,
                    low = repositoryAccentLowLight,
                    lowest = repositoryAccentLowestLight,
                ),
                info = OudsColorScheme.Repository.Info(
                    default = repositoryInfoDefaultLight,
                    highest = repositoryInfoHighestLight,
                    low = repositoryInfoLowLight,
                    lowest = repositoryInfoLowestLight,
                ),
                negative = OudsColorScheme.Repository.Negative(
                    default = repositoryNegativeDefaultLight,
                    high = repositoryNegativeHighLight,
                    higher = repositoryNegativeHigherLight,
                    highest = repositoryNegativeHighestLight,
                    low = repositoryNegativeLowLight,
                    lower = repositoryNegativeLowerLight,
                    lowest = repositoryNegativeLowestLight,
                ),
                neutral = OudsColorScheme.Repository.Neutral(
                    emphasized = OudsColorScheme.Repository.Neutral.Emphasized(
                        black = repositoryNeutralEmphasizedBlackLight,
                        high = repositoryNeutralEmphasizedHighLight,
                        higher = repositoryNeutralEmphasizedHigherLight,
                        highest = repositoryNeutralEmphasizedHighestLight,
                        medium = repositoryNeutralEmphasizedMediumLight,
                    ),
                    muted = OudsColorScheme.Repository.Neutral.Muted(
                        lower = repositoryNeutralMutedLowerLight,
                        lowest = repositoryNeutralMutedLowestLight,
                        white = repositoryNeutralMutedWhiteLight,
                    )
                ),
                opacity = OudsColorScheme.Repository.Opacity(
                    black = OudsColorScheme.Repository.Opacity.Black(
                        higher = repositoryOpacityBlackHigherLight,
                        highest = repositoryOpacityBlackHighestLight,
                        low = repositoryOpacityBlackLowLight,
                        lower = repositoryOpacityBlackLowerLight,
                        lowest = repositoryOpacityBlackLowestLight,
                        medium = repositoryOpacityBlackMediumLight,
                        transparent = repositoryOpacityBlackTransparentLight,
                    ),
                    info = repositoryOpacityInfoLight,
                    negative = repositoryOpacityNegativeLight,
                    positive = repositoryOpacityPositiveLight,
                    warning = repositoryOpacityWarningLight,
                    white = OudsColorScheme.Repository.Opacity.White(
                        high = repositoryOpacityWhiteHighLight,
                        higher = repositoryOpacityWhiteHigherLight,
                        highest = repositoryOpacityWhiteHighestLight,
                        low = repositoryOpacityWhiteLowLight,
                        lower = repositoryOpacityWhiteLowerLight,
                        lowest = repositoryOpacityWhiteLowestLight,
                        transparent = repositoryOpacityWhiteTransparentLight,
                    )
                ),
                positive = OudsColorScheme.Repository.Positive(
                    default = repositoryPositiveDefaultLight,
                    highest = repositoryPositiveHighestLight,
                    low = repositoryPositiveLowLight,
                    lowest = repositoryPositiveLowestLight,
                ),
                primary = OudsColorScheme.Repository.Primary(
                    default = repositoryPrimaryDefaultLight,
                    low = repositoryPrimaryLowLight,
                ),
                warning = OudsColorScheme.Repository.Warning(
                    default = repositoryWarningDefaultLight,
                    highest = repositoryWarningHighestLight,
                    low = repositoryWarningLowLight,
                    lowest = repositoryWarningLowestLight,
                ),
            )
        },
        surface = with(surfaceColorTokens) {
            OudsColorScheme.Surface(
                brand = OudsColorScheme.Surface.Brand(
                    primary = surfaceBrandPrimaryLight,
                ),
                status = OudsColorScheme.Surface.Status(
                    accent = OudsColorScheme.Surface.Status.Accent(
                        emphasized = surfaceStatusAccentEmphasizedLight,
                        muted = surfaceStatusAccentMutedLight,
                    ),
                    info = OudsColorScheme.Surface.Status.Info(
                        emphasized = surfaceStatusInfoEmphasizedLight,
                        muted = surfaceStatusInfoMutedLight,
                    ),
                    negative = OudsColorScheme.Surface.Status.Negative(
                        emphasized = surfaceStatusNegativeEmphasizedLight,
                        muted = surfaceStatusNegativeMutedLight,
                    ),
                    neutral = OudsColorScheme.Surface.Status.Neutral(
                        emphasized = surfaceStatusNeutralEmphasizedLight,
                        muted = surfaceStatusNeutralMutedLight,
                    ),
                    positive = OudsColorScheme.Surface.Status.Positive(
                        emphasized = surfaceStatusPositiveEmphasizedLight,
                        muted = surfaceStatusPositiveMutedLight,
                    ),
                    warning = OudsColorScheme.Surface.Status.Warning(
                        emphasized = surfaceStatusWarningEmphasizedLight,
                        muted = surfaceStatusWarningMutedLight,
                    )
                ),
            )
        },
    )

internal val OudsColorSemanticTokens.darkColorScheme: OudsColorScheme
    get() = OudsColorScheme(
        action = with(actionColorTokens) {
            OudsColorScheme.Action(
                disabled = actionDisabledDark,
                negative = OudsColorScheme.Action.Negative(
                    enabled = actionNegativeEnabledDark,
                    focus = actionNegativeFocusDark,
                    hover = actionNegativeHoverDark,
                    loading = actionNegativeLoadingDark,
                    pressed = actionNegativePressedDark,
                ),
                enabled = actionEnabledDark,
                focus = actionFocusDark,
                highlighted = actionHighlightedDark,
                hover = actionHoverDark,
                loading = actionLoadingDark,
                pressed = actionPressedDark,
                support = OudsColorScheme.Action.Support(
                    enabled = actionSupportEnabledDark,
                    focus = actionSupportFocusDark,
                    hover = actionSupportHoverDark,
                    loading = actionSupportLoadingDark,
                    pressed = actionSupportPressedDark,
                ),
                selected = actionSelectedDark,
                visited = actionVisitedDark,
            )
        },
        always = with(alwaysColorTokens) {
            OudsColorScheme.Always(
                black = alwaysBlackDark,
                onBlack = alwaysOnBlackDark,
                onWhite = alwaysOnWhiteDark,
                white = alwaysWhiteDark,
            )
        },
        background = with(backgroundColorTokens) {
            OudsColorScheme.Background(
                emphasized = bgEmphasizedDark,
                primary = bgPrimaryDark,
                secondary = bgSecondaryDark,
                tertiary = bgTertiaryDark,
            )
        },
        border = with(borderColorTokens) {
            OudsColorScheme.Border(
                brandPrimary = borderBrandPrimaryDark,
                default = borderDefaultDark,
                emphasized = borderEmphasizedDark,
                focus = borderFocusDark,
                focusInset = borderFocusInsetDark,
                onBrand = OudsColorScheme.Border.OnBrand(
                    primary = borderOnBrandPrimaryDark,
                ),
            )
        },
        content = with(contentColorTokens) {
            OudsColorScheme.Content(
                brandPrimary = contentBrandPrimaryDark,
                default = contentDefaultDark,
                disabled = contentDisabledDark,
                muted = contentMutedDark,
                onAction = OudsColorScheme.Content.OnAction(
                    disabled = contentOnActionDisabledDark,
                    negative = contentOnActionNegativeDark,
                    enabled = contentOnActionEnabledDark,
                    focus = contentOnActionFocusDark,
                    highlighted = contentOnActionHighlightedDark,
                    hover = contentOnActionHoverDark,
                    loading = contentOnActionLoadingDark,
                    pressed = contentOnActionPressedDark,
                ),
                onBrand = OudsColorScheme.Content.OnBrand(
                    primary = contentOnBrandPrimaryDark,
                ),
                onOverlay = OudsColorScheme.Content.OnOverlay(
                    emphasized = contentOnOverlayEmphasizedDark,
                ),
                onStatus = OudsColorScheme.Content.OnStatus(
                    emphasized = contentOnStatusEmphasizedDark,
                    emphasizedNeutral = contentOnStatusEmphasizedNeutralDark,
                    muted = contentOnStatusMutedDark,
                ),
                status = OudsColorScheme.Content.Status(
                    info = contentStatusInfoDark,
                    negative = contentStatusNegativeDark,
                    positive = contentStatusPositiveDark,
                    warning = contentStatusWarningDark,
                ),
            )
        },
        decorative = with(decorativeColorTokens) {
            OudsColorScheme.Decorative(
                accent1 = OudsColorScheme.Decorative.Accent1(
                    default = decorativeAccent1DefaultDark,
                    emphasized = decorativeAccent1EmphasizedDark,
                    muted = decorativeAccent1MutedDark,
                ),
                accent2 = OudsColorScheme.Decorative.Accent2(
                    default = decorativeAccent2DefaultDark,
                    emphasized = decorativeAccent2EmphasizedDark,
                    muted = decorativeAccent2MutedDark,
                ),
                accent3 = OudsColorScheme.Decorative.Accent3(
                    default = decorativeAccent3DefaultDark,
                    emphasized = decorativeAccent3EmphasizedDark,
                    muted = decorativeAccent3MutedDark,
                ),
                accent4 = OudsColorScheme.Decorative.Accent4(
                    default = decorativeAccent4DefaultDark,
                    emphasized = decorativeAccent4EmphasizedDark,
                    muted = decorativeAccent4MutedDark,
                ),
                accent5 = OudsColorScheme.Decorative.Accent5(
                    default = decorativeAccent5DefaultDark,
                    emphasized = decorativeAccent5EmphasizedDark,
                    muted = decorativeAccent5MutedDark,
                ),
                brand = OudsColorScheme.Decorative.Brand(
                    primary = decorativeBrandPrimaryDark,
                    secondary = decorativeBrandSecondaryDark,
                    tertiary = decorativeBrandTertiaryDark,
                ),
                neutral = OudsColorScheme.Decorative.Neutral(
                    default = decorativeNeutralDefaultDark,
                    emphasized = decorativeNeutralEmphasizedDark,
                    muted = decorativeNeutralMutedDark,
                ),
                skin = OudsColorScheme.Decorative.Skin(
                    tint100 = decorativeSkinTint100Dark,
                    tint200 = decorativeSkinTint200Dark,
                    tint300 = decorativeSkinTint300Dark,
                    tint400 = decorativeSkinTint400Dark,
                    tint500 = decorativeSkinTint500Dark,
                    tint600 = decorativeSkinTint600Dark,
                    tint700 = decorativeSkinTint700Dark,
                    tint800 = decorativeSkinTint800Dark,
                    tint900 = decorativeSkinTint900Dark,
                ),
            )
        },
        opacity = with(opacityColorTokens) {
            OudsColorScheme.Opacity(
                transparent = opacityTransparentDark,
                lowest = opacityLowestDark,
                lower = opacityLowerDark,
            )
        },
        overlay = with(overlayColorTokens) {
            OudsColorScheme.Overlay(
                default = overlayDefaultDark,
                drag = overlayDragDark,
                emphasized = overlayEmphasizedDark,
                modal = overlayModalLight
            )
        },
        repository = with(repositoryColorTokens) {
            OudsColorScheme.Repository(
                accent = OudsColorScheme.Repository.Accent(
                    default = repositoryAccentDefaultDark,
                    highest = repositoryAccentHighestDark,
                    low = repositoryAccentLowDark,
                    lowest = repositoryAccentLowestDark,
                ),
                info = OudsColorScheme.Repository.Info(
                    default = repositoryInfoDefaultDark,
                    highest = repositoryInfoHighestDark,
                    low = repositoryInfoLowDark,
                    lowest = repositoryInfoLowestDark,
                ),
                negative = OudsColorScheme.Repository.Negative(
                    default = repositoryNegativeDefaultDark,
                    high = repositoryNegativeHighDark,
                    higher = repositoryNegativeHigherDark,
                    highest = repositoryNegativeHighestDark,
                    low = repositoryNegativeLowDark,
                    lower = repositoryNegativeLowerDark,
                    lowest = repositoryNegativeLowestDark,
                ),

                neutral = OudsColorScheme.Repository.Neutral(
                    emphasized = OudsColorScheme.Repository.Neutral.Emphasized(
                        black = repositoryNeutralEmphasizedBlackDark,
                        high = repositoryNeutralEmphasizedHighDark,
                        higher = repositoryNeutralEmphasizedHigherDark,
                        highest = repositoryNeutralEmphasizedHighestDark,
                        medium = repositoryNeutralEmphasizedMediumDark,
                    ),
                    muted = OudsColorScheme.Repository.Neutral.Muted(
                        lower = repositoryNeutralMutedLowerDark,
                        lowest = repositoryNeutralMutedLowestDark,
                        white = repositoryNeutralMutedWhiteDark,
                    )
                ),
                opacity = OudsColorScheme.Repository.Opacity(
                    black = OudsColorScheme.Repository.Opacity.Black(
                        higher = repositoryOpacityBlackHigherDark,
                        highest = repositoryOpacityBlackHighestDark,
                        low = repositoryOpacityBlackLowDark,
                        lower = repositoryOpacityBlackLowerDark,
                        lowest = repositoryOpacityBlackLowestDark,
                        medium = repositoryOpacityBlackMediumDark,
                        transparent = repositoryOpacityBlackTransparentDark,
                    ),
                    info = repositoryOpacityInfoDark,
                    negative = repositoryOpacityNegativeDark,
                    positive = repositoryOpacityPositiveDark,
                    warning = repositoryOpacityWarningDark,
                    white = OudsColorScheme.Repository.Opacity.White(
                        high = repositoryOpacityWhiteHighDark,
                        higher = repositoryOpacityWhiteHigherDark,
                        highest = repositoryOpacityWhiteHighestDark,
                        low = repositoryOpacityWhiteLowDark,
                        lower = repositoryOpacityWhiteLowerDark,
                        lowest = repositoryOpacityWhiteLowestDark,
                        transparent = repositoryOpacityWhiteTransparentDark,
                    ),
                ),
                positive = OudsColorScheme.Repository.Positive(
                    default = repositoryPositiveDefaultDark,
                    highest = repositoryPositiveHighestDark,
                    low = repositoryPositiveLowDark,
                    lowest = repositoryPositiveLowestDark,
                ),
                primary = OudsColorScheme.Repository.Primary(
                    default = repositoryPrimaryDefaultDark,
                    low = repositoryPrimaryLowDark,
                ),
                warning = OudsColorScheme.Repository.Warning(
                    default = repositoryWarningDefaultDark,
                    highest = repositoryWarningHighestDark,
                    low = repositoryWarningLowDark,
                    lowest = repositoryWarningLowestDark,
                ),
            )
        },
        surface = with(surfaceColorTokens) {
            OudsColorScheme.Surface(
                brand = OudsColorScheme.Surface.Brand(
                    primary = surfaceBrandPrimaryDark,
                ),
                status = OudsColorScheme.Surface.Status(
                    accent = OudsColorScheme.Surface.Status.Accent(
                        emphasized = surfaceStatusAccentEmphasizedDark,
                        muted = surfaceStatusAccentMutedDark,
                    ),
                    info = OudsColorScheme.Surface.Status.Info(
                        emphasized = surfaceStatusInfoEmphasizedDark,
                        muted = surfaceStatusInfoMutedDark,
                    ),
                    negative = OudsColorScheme.Surface.Status.Negative(
                        emphasized = surfaceStatusNegativeEmphasizedDark,
                        muted = surfaceStatusNegativeMutedDark,
                    ),
                    neutral = OudsColorScheme.Surface.Status.Neutral(
                        emphasized = surfaceStatusNeutralEmphasizedDark,
                        muted = surfaceStatusNeutralMutedDark,
                    ),
                    positive = OudsColorScheme.Surface.Status.Positive(
                        emphasized = surfaceStatusPositiveEmphasizedDark,
                        muted = surfaceStatusPositiveMutedDark,
                    ),
                    warning = OudsColorScheme.Surface.Status.Warning(
                        emphasized = surfaceStatusWarningEmphasizedDark,
                        muted = surfaceStatusWarningMutedDark,
                    )
                ),
            )
        },
    )

@Stable
private fun OudsColorScheme.fromToken(token: OudsColorKeyToken.Action): Color {
    return with(action) {
        when (token) {
            OudsColorKeyToken.Action.Disabled -> disabled
            OudsColorKeyToken.Action.Enabled -> enabled
            OudsColorKeyToken.Action.Focus -> focus
            OudsColorKeyToken.Action.Highlighted -> highlighted
            OudsColorKeyToken.Action.Hover -> hover
            OudsColorKeyToken.Action.Loading -> loading
            OudsColorKeyToken.Action.Negative.Enabled -> negative.enabled
            OudsColorKeyToken.Action.Negative.Focus -> negative.focus
            OudsColorKeyToken.Action.Negative.Hover -> negative.hover
            OudsColorKeyToken.Action.Negative.Loading -> negative.loading
            OudsColorKeyToken.Action.Negative.Pressed -> negative.pressed
            OudsColorKeyToken.Action.Pressed -> pressed
            OudsColorKeyToken.Action.Selected -> selected
            OudsColorKeyToken.Action.Support.Enabled -> support.enabled
            OudsColorKeyToken.Action.Support.Focus -> support.focus
            OudsColorKeyToken.Action.Support.Hover -> support.hover
            OudsColorKeyToken.Action.Support.Loading -> support.loading
            OudsColorKeyToken.Action.Support.Pressed -> support.pressed
            OudsColorKeyToken.Action.Visited -> visited
        }
    }
}

@Stable
private fun OudsColorScheme.fromToken(token: OudsColorKeyToken.Always): Color {
    return with(always) {
        when (token) {
            OudsColorKeyToken.Always.Black -> black
            OudsColorKeyToken.Always.OnBlack -> onBlack
            OudsColorKeyToken.Always.OnWhite -> onWhite
            OudsColorKeyToken.Always.White -> white
        }
    }
}

@Stable
private fun OudsColorScheme.fromToken(token: OudsColorKeyToken.Background): Color {
    return with(background) {
        when (token) {
            OudsColorKeyToken.Background.Emphasized -> emphasized
            OudsColorKeyToken.Background.Primary -> primary
            OudsColorKeyToken.Background.Secondary -> secondary
            OudsColorKeyToken.Background.Tertiary -> tertiary
        }
    }
}

@Stable
private fun OudsColorScheme.fromToken(token: OudsColorKeyToken.Border): Color {
    return with(border) {
        when (token) {
            OudsColorKeyToken.Border.BrandPrimary -> brandPrimary
            OudsColorKeyToken.Border.Default -> default
            OudsColorKeyToken.Border.Emphasized -> emphasized
            OudsColorKeyToken.Border.Focus -> focus
            OudsColorKeyToken.Border.FocusInset -> focusInset
            OudsColorKeyToken.Border.OnBrand.Primary -> onBrand.primary
        }
    }
}

@Stable
private fun OudsColorScheme.fromToken(token: OudsColorKeyToken.Content): Color {
    return with(content) {
        when (token) {
            OudsColorKeyToken.Content.BrandPrimary -> brandPrimary
            OudsColorKeyToken.Content.Default -> default
            OudsColorKeyToken.Content.Disabled -> disabled
            OudsColorKeyToken.Content.Muted -> muted
            OudsColorKeyToken.Content.OnAction.Disabled -> onAction.disabled
            OudsColorKeyToken.Content.OnAction.Negative -> onAction.negative
            OudsColorKeyToken.Content.OnAction.Enabled -> onAction.enabled
            OudsColorKeyToken.Content.OnAction.Focus -> onAction.focus
            OudsColorKeyToken.Content.OnAction.Highlighted -> onAction.highlighted
            OudsColorKeyToken.Content.OnAction.Hover -> onAction.hover
            OudsColorKeyToken.Content.OnAction.Loading -> onAction.loading
            OudsColorKeyToken.Content.OnAction.Pressed -> onAction.pressed
            OudsColorKeyToken.Content.OnBrand.Primary -> onBrand.primary
            OudsColorKeyToken.Content.OnOverlay.Emphasized -> onOverlay.emphasized
            OudsColorKeyToken.Content.OnStatus.Emphasized -> onStatus.emphasized
            OudsColorKeyToken.Content.OnStatus.EmphasizedNeutral -> onStatus.emphasizedNeutral
            OudsColorKeyToken.Content.OnStatus.Muted -> onStatus.muted
            OudsColorKeyToken.Content.Status.Info -> status.info
            OudsColorKeyToken.Content.Status.Negative -> status.negative
            OudsColorKeyToken.Content.Status.Positive -> status.positive
            OudsColorKeyToken.Content.Status.Warning -> status.warning
        }
    }
}

@Stable
private fun OudsColorScheme.fromToken(token: OudsColorKeyToken.Decorative): Color {
    return with(decorative) {
        when (token) {
            OudsColorKeyToken.Decorative.Accent1.Default -> accent1.default
            OudsColorKeyToken.Decorative.Accent1.Emphasized -> accent1.emphasized
            OudsColorKeyToken.Decorative.Accent1.Muted -> accent1.muted
            OudsColorKeyToken.Decorative.Accent2.Default -> accent2.default
            OudsColorKeyToken.Decorative.Accent2.Emphasized -> accent2.emphasized
            OudsColorKeyToken.Decorative.Accent2.Muted -> accent2.muted
            OudsColorKeyToken.Decorative.Accent3.Default -> accent3.default
            OudsColorKeyToken.Decorative.Accent3.Emphasized -> accent3.emphasized
            OudsColorKeyToken.Decorative.Accent3.Muted -> accent3.muted
            OudsColorKeyToken.Decorative.Accent4.Default -> accent4.default
            OudsColorKeyToken.Decorative.Accent4.Emphasized -> accent4.emphasized
            OudsColorKeyToken.Decorative.Accent4.Muted -> accent4.muted
            OudsColorKeyToken.Decorative.Accent5.Default -> accent5.default
            OudsColorKeyToken.Decorative.Accent5.Emphasized -> accent5.emphasized
            OudsColorKeyToken.Decorative.Accent5.Muted -> accent5.muted
            OudsColorKeyToken.Decorative.Brand.Primary -> brand.primary
            OudsColorKeyToken.Decorative.Brand.Secondary -> brand.secondary
            OudsColorKeyToken.Decorative.Brand.Tertiary -> brand.tertiary
            OudsColorKeyToken.Decorative.Neutral.Default -> neutral.default
            OudsColorKeyToken.Decorative.Neutral.Emphasized -> neutral.emphasized
            OudsColorKeyToken.Decorative.Neutral.Muted -> neutral.muted
            OudsColorKeyToken.Decorative.Skin.Tint100 -> skin.tint100
            OudsColorKeyToken.Decorative.Skin.Tint200 -> skin.tint200
            OudsColorKeyToken.Decorative.Skin.Tint300 -> skin.tint300
            OudsColorKeyToken.Decorative.Skin.Tint400 -> skin.tint400
            OudsColorKeyToken.Decorative.Skin.Tint500 -> skin.tint500
            OudsColorKeyToken.Decorative.Skin.Tint600 -> skin.tint600
            OudsColorKeyToken.Decorative.Skin.Tint700 -> skin.tint700
            OudsColorKeyToken.Decorative.Skin.Tint800 -> skin.tint800
            OudsColorKeyToken.Decorative.Skin.Tint900 -> skin.tint900
        }
    }
}

@Stable
private fun OudsColorScheme.fromToken(token: OudsColorKeyToken.Repository): Color {
    return with(repository) {
        when (token) {
            OudsColorKeyToken.Repository.Accent.Default -> accent.default
            OudsColorKeyToken.Repository.Accent.Highest -> accent.highest
            OudsColorKeyToken.Repository.Accent.Low -> accent.low
            OudsColorKeyToken.Repository.Accent.Lowest -> accent.lowest
            OudsColorKeyToken.Repository.Info.Default -> info.default
            OudsColorKeyToken.Repository.Info.Highest -> info.highest
            OudsColorKeyToken.Repository.Info.Low -> info.low
            OudsColorKeyToken.Repository.Info.Lowest -> info.lowest
            OudsColorKeyToken.Repository.Negative.Default -> negative.default
            OudsColorKeyToken.Repository.Negative.High -> negative.high
            OudsColorKeyToken.Repository.Negative.Higher -> negative.higher
            OudsColorKeyToken.Repository.Negative.Highest -> negative.highest
            OudsColorKeyToken.Repository.Negative.Low -> negative.low
            OudsColorKeyToken.Repository.Negative.Lower -> negative.lower
            OudsColorKeyToken.Repository.Negative.Lowest -> negative.lowest
            OudsColorKeyToken.Repository.Neutral.Emphasized.Black -> neutral.emphasized.black
            OudsColorKeyToken.Repository.Neutral.Emphasized.High -> neutral.emphasized.high
            OudsColorKeyToken.Repository.Neutral.Emphasized.Higher -> neutral.emphasized.higher
            OudsColorKeyToken.Repository.Neutral.Emphasized.Highest -> neutral.emphasized.highest
            OudsColorKeyToken.Repository.Neutral.Emphasized.Medium -> neutral.emphasized.medium
            OudsColorKeyToken.Repository.Neutral.Muted.Lower -> neutral.muted.lower
            OudsColorKeyToken.Repository.Neutral.Muted.Lowest -> neutral.muted.lowest
            OudsColorKeyToken.Repository.Neutral.Muted.White -> neutral.muted.white
            OudsColorKeyToken.Repository.Opacity.Black.Higher -> opacity.black.higher
            OudsColorKeyToken.Repository.Opacity.Black.Highest -> opacity.black.highest
            OudsColorKeyToken.Repository.Opacity.Black.Low -> opacity.black.low
            OudsColorKeyToken.Repository.Opacity.Black.Lower -> opacity.black.lower
            OudsColorKeyToken.Repository.Opacity.Black.Lowest -> opacity.black.lowest
            OudsColorKeyToken.Repository.Opacity.Black.Medium -> opacity.black.medium
            OudsColorKeyToken.Repository.Opacity.Black.Transparent -> opacity.black.transparent
            OudsColorKeyToken.Repository.Opacity.Info -> opacity.info
            OudsColorKeyToken.Repository.Opacity.Negative -> opacity.negative
            OudsColorKeyToken.Repository.Opacity.Positive -> opacity.positive
            OudsColorKeyToken.Repository.Opacity.Warning -> opacity.warning
            OudsColorKeyToken.Repository.Opacity.White.High -> opacity.white.high
            OudsColorKeyToken.Repository.Opacity.White.Higher -> opacity.white.higher
            OudsColorKeyToken.Repository.Opacity.White.Highest -> opacity.white.highest
            OudsColorKeyToken.Repository.Opacity.White.Low -> opacity.white.low
            OudsColorKeyToken.Repository.Opacity.White.Lower -> opacity.white.lower
            OudsColorKeyToken.Repository.Opacity.White.Lowest -> opacity.white.lowest
            OudsColorKeyToken.Repository.Opacity.White.Transparent -> opacity.white.transparent
            OudsColorKeyToken.Repository.Positive.Default -> positive.default
            OudsColorKeyToken.Repository.Positive.Highest -> positive.highest
            OudsColorKeyToken.Repository.Positive.Low -> positive.low
            OudsColorKeyToken.Repository.Positive.Lowest -> positive.lowest
            OudsColorKeyToken.Repository.Primary.Default -> primary.default
            OudsColorKeyToken.Repository.Primary.Low -> primary.low
            OudsColorKeyToken.Repository.Warning.Default -> warning.default
            OudsColorKeyToken.Repository.Warning.Highest -> warning.highest
            OudsColorKeyToken.Repository.Warning.Low -> warning.low
            OudsColorKeyToken.Repository.Warning.Lowest -> warning.lowest
        }
    }
}

@Stable
private fun OudsColorScheme.fromToken(token: OudsColorKeyToken.Surface): Color {
    return with(surface) {
        when (token) {
            OudsColorKeyToken.Surface.Brand.Primary -> brand.primary
            OudsColorKeyToken.Surface.Status.Accent.Emphasized -> status.accent.emphasized
            OudsColorKeyToken.Surface.Status.Accent.Muted -> status.accent.muted
            OudsColorKeyToken.Surface.Status.Info.Emphasized -> status.info.emphasized
            OudsColorKeyToken.Surface.Status.Info.Muted -> status.info.muted
            OudsColorKeyToken.Surface.Status.Negative.Emphasized -> status.negative.emphasized
            OudsColorKeyToken.Surface.Status.Negative.Muted -> status.negative.muted
            OudsColorKeyToken.Surface.Status.Neutral.Emphasized -> status.neutral.emphasized
            OudsColorKeyToken.Surface.Status.Neutral.Muted -> status.neutral.muted
            OudsColorKeyToken.Surface.Status.Positive.Emphasized -> status.positive.emphasized
            OudsColorKeyToken.Surface.Status.Positive.Muted -> status.positive.muted
            OudsColorKeyToken.Surface.Status.Warning.Emphasized -> status.warning.emphasized
            OudsColorKeyToken.Surface.Status.Warning.Muted -> status.warning.muted
        }
    }
}

@Stable
private fun OudsColorScheme.fromToken(token: OudsColorKeyToken.Opacity): Color {
    return with(opacity) {
        when (token) {
            OudsColorKeyToken.Opacity.Lower -> lower
            OudsColorKeyToken.Opacity.Lowest -> lowest
            OudsColorKeyToken.Opacity.Transparent -> transparent
        }
    }
}

@Stable
private fun OudsColorScheme.fromToken(token: OudsColorKeyToken.Overlay): Color {
    return with(overlay) {
        when (token) {
            OudsColorKeyToken.Overlay.Default -> default
            OudsColorKeyToken.Overlay.Drag -> drag
            OudsColorKeyToken.Overlay.Emphasized -> emphasized
            OudsColorKeyToken.Overlay.Modal -> modal
        }
    }
}

val OudsColorSemanticTokens.materialLightColorScheme: ColorScheme
    get() = lightColorScheme(
        primary = repositoryColorTokens.repositoryNeutralEmphasizedBlackLight,
        onPrimary = repositoryColorTokens.repositoryNeutralMutedLowerLight,
        primaryContainer = repositoryColorTokens.repositoryNeutralEmphasizedBlackLight,
        onPrimaryContainer = repositoryColorTokens.repositoryNeutralMutedWhiteLight,
        secondary = repositoryColorTokens.repositoryNeutralEmphasizedBlackLight,
        onSecondary = repositoryColorTokens.repositoryNeutralMutedLowerLight,
        secondaryContainer = repositoryColorTokens.repositoryNeutralEmphasizedBlackLight,
        onSecondaryContainer = repositoryColorTokens.repositoryNeutralMutedWhiteLight,
        tertiary = repositoryColorTokens.repositoryNeutralMutedWhiteLight,
        onTertiary = repositoryColorTokens.repositoryNeutralEmphasizedBlackLight,
        tertiaryContainer = repositoryColorTokens.repositoryNeutralMutedWhiteLight,
        onTertiaryContainer = repositoryColorTokens.repositoryNeutralEmphasizedBlackLight,
        error = repositoryColorTokens.repositoryNegativeDefaultLight,
        onError = repositoryColorTokens.repositoryNeutralMutedWhiteLight,
        errorContainer = repositoryColorTokens.repositoryNegativeLowestLight,
        onErrorContainer = repositoryColorTokens.repositoryNeutralEmphasizedBlackLight,
        surfaceDim = repositoryColorTokens.repositoryNeutralMutedLowestLight,
        surface = repositoryColorTokens.repositoryNeutralMutedWhiteLight,
        surfaceBright = repositoryColorTokens.repositoryNeutralMutedWhiteLight,
        onSurface = repositoryColorTokens.repositoryNeutralEmphasizedBlackLight,
        onSurfaceVariant = repositoryColorTokens.repositoryOpacityBlackHigherLight,
        surfaceContainerLowest = repositoryColorTokens.repositoryNeutralMutedWhiteLight,
        surfaceContainerLow = repositoryColorTokens.repositoryNeutralMutedWhiteLight,
        surfaceContainer = repositoryColorTokens.repositoryNeutralMutedLowestLight,
        surfaceContainerHigh = repositoryColorTokens.repositoryNeutralMutedLowestLight,
        surfaceContainerHighest = repositoryColorTokens.repositoryNeutralMutedLowestLight,
        inverseSurface = repositoryColorTokens.repositoryNeutralEmphasizedHighLight,
        inverseOnSurface = repositoryColorTokens.repositoryNeutralMutedWhiteLight,
        inversePrimary = repositoryColorTokens.repositoryNeutralMutedWhiteLight,
        outline = repositoryColorTokens.repositoryNeutralEmphasizedBlackLight,
        outlineVariant = repositoryColorTokens.repositoryOpacityBlackLowLight,
        scrim = repositoryColorTokens.repositoryNeutralEmphasizedBlackLight,
        background = repositoryColorTokens.repositoryNeutralMutedWhiteLight,
        onBackground = repositoryColorTokens.repositoryNeutralEmphasizedBlackLight,
        surfaceVariant = repositoryColorTokens.repositoryNeutralMutedLowestLight,
        surfaceTint = repositoryColorTokens.repositoryNeutralMutedLowestLight,
    )

val OudsColorSemanticTokens.materialDarkColorScheme: ColorScheme
    get() = darkColorScheme(
        primary = repositoryColorTokens.repositoryNeutralMutedLowerDark,
        onPrimary = repositoryColorTokens.repositoryNeutralEmphasizedBlackDark,
        primaryContainer = repositoryColorTokens.repositoryNeutralMutedLowerDark,
        onPrimaryContainer = repositoryColorTokens.repositoryNeutralEmphasizedBlackDark,
        secondary = repositoryColorTokens.repositoryNeutralMutedLowerDark,
        onSecondary = repositoryColorTokens.repositoryNeutralEmphasizedBlackDark,
        secondaryContainer = repositoryColorTokens.repositoryNeutralMutedLowerDark,
        onSecondaryContainer = repositoryColorTokens.repositoryNeutralEmphasizedBlackDark,
        tertiary = repositoryColorTokens.repositoryNeutralEmphasizedHighestDark,
        onTertiary = repositoryColorTokens.repositoryNeutralMutedLowerDark,
        tertiaryContainer = repositoryColorTokens.repositoryNeutralEmphasizedHighestDark,
        onTertiaryContainer = repositoryColorTokens.repositoryNeutralMutedLowerDark,
        error = repositoryColorTokens.repositoryNegativeLowDark,
        onError = repositoryColorTokens.repositoryNeutralEmphasizedBlackDark,
        errorContainer = repositoryColorTokens.repositoryNegativeHighestDark,
        onErrorContainer = repositoryColorTokens.repositoryNeutralMutedLowerDark,
        surfaceDim = repositoryColorTokens.repositoryNeutralEmphasizedHighestDark,
        surface = repositoryColorTokens.repositoryNeutralEmphasizedHighestDark,
        surfaceBright =repositoryColorTokens.repositoryNeutralEmphasizedMediumDark,
        onSurface = repositoryColorTokens.repositoryNeutralMutedLowerDark,
        onSurfaceVariant = repositoryColorTokens.repositoryOpacityWhiteHighDark,
        surfaceContainerLowest = repositoryColorTokens.repositoryNeutralEmphasizedHighestDark,
        surfaceContainerLow = repositoryColorTokens.repositoryNeutralEmphasizedHighestDark,
        surfaceContainer = repositoryColorTokens.repositoryNeutralMutedLowestDark,
        surfaceContainerHigh = repositoryColorTokens.repositoryNeutralMutedLowestDark,
        surfaceContainerHighest = repositoryColorTokens.repositoryNeutralMutedLowestDark,
        inverseSurface = repositoryColorTokens.repositoryNeutralMutedLowerDark,
        inverseOnSurface = repositoryColorTokens.repositoryNeutralEmphasizedBlackDark,
        inversePrimary = repositoryColorTokens.repositoryNeutralEmphasizedBlackDark,
        outline = repositoryColorTokens.repositoryNeutralMutedLowerDark,
        outlineVariant = repositoryColorTokens.repositoryOpacityWhiteLowDark,
        scrim = repositoryColorTokens.repositoryNeutralEmphasizedBlackDark,
        background = repositoryColorTokens.repositoryNeutralEmphasizedHighestDark,
        onBackground = repositoryColorTokens.repositoryNeutralMutedLowerDark,
        surfaceVariant = repositoryColorTokens.repositoryNeutralMutedLowestDark,
        surfaceTint = repositoryColorTokens.repositoryNeutralMutedLowestDark,
    )

/**
 * Converts an OUDS color token to the local color value provided by the theme.
 */
@InternalOudsApi
val OudsColorKeyToken.value: Color
    @ReadOnlyComposable
    @Composable
    get() = when (this) {
        is OudsColorKeyToken.Action -> OudsTheme.colorScheme.fromToken(this)
        is OudsColorKeyToken.Always -> OudsTheme.colorScheme.fromToken(this)
        is OudsColorKeyToken.Background -> OudsTheme.colorScheme.fromToken(this)
        is OudsColorKeyToken.Border -> OudsTheme.colorScheme.fromToken(this)
        is OudsColorKeyToken.Content -> OudsTheme.colorScheme.fromToken(this)
        is OudsColorKeyToken.Decorative -> OudsTheme.colorScheme.fromToken(this)
        is OudsColorKeyToken.Opacity -> OudsTheme.colorScheme.fromToken(this)
        is OudsColorKeyToken.Overlay -> OudsTheme.colorScheme.fromToken(this)
        is OudsColorKeyToken.Repository -> OudsTheme.colorScheme.fromToken(this)
        is OudsColorKeyToken.Surface -> OudsTheme.colorScheme.fromToken(this)
    }
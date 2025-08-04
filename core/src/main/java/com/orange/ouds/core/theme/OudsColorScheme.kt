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
import com.orange.ouds.theme.tokens.OudsLightDarkColorKeyToken
import com.orange.ouds.theme.tokens.material.OudsMaterialColorTokens
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
    internal val repository: Repository,
    val surface: Surface,
    internal val modes: Modes
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
            val disabled: Color,
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
        val brandSecondary: Color,
        val brandTertiary: Color,
        val default: Color,
        val emphasized: Color,
        val focus: Color,
        val focusInset: Color,
        val muted: Color,
        val onBrand: OnBrand,
        val status: Status
    ) {
        data class OnBrand(
            val primary: Color,
            val secondary: Color,
            val tertiary: Color
        )

        data class Status(
            val accent: Color,
            val info: Color,
            val negative: Color,
            val positive: Color,
            val warning: Color
        )
    }

    data class Content(
        val brandPrimary: Color,
        val brandSecondary: Color,
        val brandTertiary: Color,
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
            val pressed: Color,
            val selected: Color,
        )

        data class OnBrand(
            val primary: Color,
            val secondary: Color,
            val tertiary: Color
        )

        data class OnOverlay(
            val emphasized: Color
        )

        data class OnStatus(
            val neutral: Neutral,
            val positive: Positive,
            val info: Info,
            val warning: Warning,
            val negative: Negative,
            val accent: Accent
        ) {
            data class Neutral(
                val emphasized: Color,
                val muted: Color
            )

            data class Positive(
                val emphasized: Color,
                val muted: Color
            )

            data class Info(
                val emphasized: Color,
                val muted: Color
            )

            data class Warning(
                val emphasized: Color,
                val muted: Color
            )

            data class Negative(
                val emphasized: Color,
                val muted: Color
            )

            data class Accent(
                val emphasized: Color,
                val muted: Color
            )
        }

        data class Status(
            val accent: Color,
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
            val primaryEmphasized: Color,
            val primaryMuted: Color,
            val primary: Color,
            val secondaryEmphasized: Color,
            val secondaryMuted: Color,
            val secondary: Color,
            val tertiaryEmphasized: Color,
            val tertiaryMuted: Color,
            val tertiary: Color
        )

        data class Neutral(val emphasized: Emphasized, val muted: Muted) {
            data class Emphasized(
                val high: Color,
                val higher: Color,
                val highest: Color,
                val low: Color,
                val lower: Color,
                val lowest: Color,
                val medium: Color
            )

            data class Muted(
                val high: Color,
                val higher: Color,
                val highest: Color,
                val low: Color,
                val lower: Color,
                val lowest: Color,
                val medium: Color
            )
        }

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
        val secondary: Secondary,
        val tertiary: Tertiary,
        val warning: Warning
    ) {
        data class Accent(
            val default: Color,
            val high: Color,
            val higher: Color,
            val highest: Color,
            val low: Color,
            val lower: Color,
            val lowest: Color
        )

        data class Info(
            val default: Color,
            val high: Color,
            val higher: Color,
            val highest: Color,
            val low: Color,
            val lower: Color,
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
                val low: Color,
                val lower: Color,
                val lowest: Color,
                val medium: Color
            )

            data class Muted(
                val high: Color,
                val higher: Color,
                val highest: Color,
                val low: Color,
                val lower: Color,
                val lowest: Color,
                val medium: Color,
                val white: Color
            )
        }

        data class Opacity(
            val accent: Color,
            val black: Black,
            val info: Color,
            val negative: Color,
            val positive: Color,
            val primary: Primary,
            val warning: Color,
            val white: White
        ) {
            data class Black(
                val high: Color,
                val higher: Color,
                val highest: Color,
                val low: Color,
                val lower: Color,
                val lowest: Color,
                val medium: Color,
                val mediumHigh: Color,
                val mediumLow: Color,
                val transparent: Color
            )

            data class Primary(
                val high: Color,
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
                val medium: Color,
                val mediumLow: Color,
                val transparent: Color
            )
        }

        data class Positive(
            val default: Color,
            val high: Color,
            val higher: Color,
            val highest: Color,
            val low: Color,
            val lower: Color,
            val lowest: Color
        )

        data class Primary(
            val default: Color,
            val high: Color,
            val higher: Color,
            val highest: Color,
            val low: Color,
            val lower: Color,
            val lowest: Color
        )

        data class Secondary(
            val default: Color,
            val high: Color,
            val higher: Color,
            val highest: Color,
            val low: Color,
            val lower: Color,
            val lowest: Color
        )

        data class Tertiary(
            val default: Color,
            val high: Color,
            val higher: Color,
            val highest: Color,
            val low: Color,
            val lower: Color,
            val lowest: Color
        )

        data class Warning(
            val default: Color,
            val high: Color,
            val higher: Color,
            val highest: Color,
            val low: Color,
            val lower: Color,
            val lowest: Color
        )
    }

    data class Surface(
        val brand: Brand,
        val status: Status
    ) {
        data class Brand(
            val primary: Color,
            val secondary: Color,
            val tertiary: Color
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

    data class Modes(
        val onBackground: OnBackground,
        val onBrand: OnBrand,
        val onOverlay: OnOverlay,
        val onStatus: OnStatus
    ) {

        data class OnBackground(
            val emphasized: OudsColorMode,
            val primary: OudsColorMode,
            val secondary: OudsColorMode,
            val tertiary: OudsColorMode
        )

        data class OnBrand(
            val primary: OudsColorMode,
            val secondary: OudsColorMode,
            val tertiary: OudsColorMode
        )

        data class OnOverlay(
            val default: OudsColorMode,
            val emphasized: OudsColorMode,
            val modal: OudsColorMode
        )

        data class OnStatus(
            val accent: Accent,
            val info: Info,
            val negative: Negative,
            val neutral: Neutral,
            val positive: Positive,
            val warning: Warning
        ) {
            data class Accent(
                val emphasized: OudsColorMode,
                val muted: OudsColorMode
            )

            data class Info(
                val emphasized: OudsColorMode,
                val muted: OudsColorMode
            )

            data class Negative(
                val emphasized: OudsColorMode,
                val muted: OudsColorMode
            )

            data class Neutral(
                val emphasized: OudsColorMode,
                val muted: OudsColorMode
            )

            data class Positive(
                val emphasized: OudsColorMode,
                val muted: OudsColorMode
            )

            data class Warning(
                val emphasized: OudsColorMode,
                val muted: OudsColorMode
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
                    disabled = actionSupportDisabledLight,
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
        always = alwaysColorScheme,
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
                brandSecondary = borderBrandSecondaryLight,
                brandTertiary = borderBrandTertiaryLight,
                default = borderDefaultLight,
                emphasized = borderEmphasizedLight,
                focus = borderFocusLight,
                focusInset = borderFocusInsetLight,
                muted = borderMutedLight,
                onBrand = OudsColorScheme.Border.OnBrand(
                    primary = borderOnBrandPrimaryLight,
                    secondary = borderOnBrandSecondaryLight,
                    tertiary = borderOnBrandTertiaryLight,
                ),
                status = OudsColorScheme.Border.Status(
                    accent = borderStatusAccentLight,
                    info = borderStatusInfoLight,
                    negative = borderStatusNegativeLight,
                    positive = borderStatusPositiveLight,
                    warning = borderStatusWarningLight,
                ),
            )
        },
        content = with(contentColorTokens) {
            OudsColorScheme.Content(
                brandPrimary = contentBrandPrimaryLight,
                brandSecondary = contentBrandSecondaryLight,
                brandTertiary = contentBrandTertiaryLight,
                default = contentDefaultLight,
                disabled = contentDisabledLight,
                muted = contentMutedLight,
                onAction = OudsColorScheme.Content.OnAction(
                    disabled = contentOnActionDisabledLight,
                    enabled = contentOnActionEnabledLight,
                    focus = contentOnActionFocusLight,
                    highlighted = contentOnActionHighlightedLight,
                    hover = contentOnActionHoverLight,
                    loading = contentOnActionLoadingLight,
                    pressed = contentOnActionPressedLight,
                    selected = contentOnActionSelectedLight,
                ),
                onBrand = OudsColorScheme.Content.OnBrand(
                    primary = contentOnBrandPrimaryLight,
                    secondary = contentBrandSecondaryLight,
                    tertiary = contentOnBrandTertiaryLight
                ),
                onOverlay = OudsColorScheme.Content.OnOverlay(
                    emphasized = contentOnOverlayEmphasizedLight,
                ),
                onStatus = OudsColorScheme.Content.OnStatus(
                    neutral = OudsColorScheme.Content.OnStatus.Neutral(
                        emphasized = contentOnStatusNeutralEmphasizedLight,
                        muted = contentOnStatusNeutralMutedLight
                    ),
                    positive = OudsColorScheme.Content.OnStatus.Positive(
                        emphasized = contentOnStatusPositiveEmphasizedLight,
                        muted = contentOnStatusPositiveMutedLight
                    ),
                    info = OudsColorScheme.Content.OnStatus.Info(
                        emphasized = contentOnStatusInfoEmphasizedLight,
                        muted = contentOnStatusInfoMutedLight
                    ),
                    warning = OudsColorScheme.Content.OnStatus.Warning(
                        emphasized = contentOnStatusWarningEmphasizedLight,
                        muted = contentOnStatusWarningMutedLight
                    ),
                    negative = OudsColorScheme.Content.OnStatus.Negative(
                        emphasized = contentOnStatusNegativeEmphasizedLight,
                        muted = contentOnStatusNegativeMutedLight
                    ),
                    accent = OudsColorScheme.Content.OnStatus.Accent(
                        emphasized = contentOnStatusAccentEmphasizedLight,
                        muted = contentOnStatusAccentMutedLight
                    )
                ),
                status = OudsColorScheme.Content.Status(
                    accent = contentStatusAccentLight,
                    info = contentStatusInfoLight,
                    negative = contentStatusNegativeLight,
                    positive = contentStatusPositiveLight,
                    warning = contentStatusWarningLight,
                ),
            )
        },
        decorative = decorativeColorScheme,
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
        repository = repositoryColorScheme,
        surface = with(surfaceColorTokens) {
            OudsColorScheme.Surface(
                brand = OudsColorScheme.Surface.Brand(
                    primary = surfaceBrandPrimaryLight,
                    secondary = surfaceBrandSecondaryLight,
                    tertiary = surfaceBrandTertiaryLight,
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
        modes = with(colorModeTokens) {
            OudsColorScheme.Modes(
                onBackground = OudsColorScheme.Modes.OnBackground(
                    emphasized = OudsColorMode.fromString(onBgEmphasizedLight),
                    primary = OudsColorMode.fromString(onBgPrimaryLight),
                    secondary = OudsColorMode.fromString(onBgSecondaryLight),
                    tertiary = OudsColorMode.fromString(onBgTertiaryLight)
                ),
                onBrand = OudsColorScheme.Modes.OnBrand(
                    primary = OudsColorMode.fromString(onBrandPrimaryLight),
                    secondary = OudsColorMode.fromString(onBrandSecondaryLight),
                    tertiary = OudsColorMode.fromString(onBrandTertiaryLight)
                ),
                onOverlay = OudsColorScheme.Modes.OnOverlay(
                    default = OudsColorMode.fromString(onOverlayDefaultLight),
                    emphasized = OudsColorMode.fromString(onOverlayEmphasizedLight),
                    modal = OudsColorMode.fromString(onOverlayModalLight)
                ),
                onStatus = OudsColorScheme.Modes.OnStatus(
                    accent = OudsColorScheme.Modes.OnStatus.Accent(
                        emphasized = OudsColorMode.fromString(onStatusAccentEmphasizedLight),
                        muted = OudsColorMode.fromString(onStatusAccentMutedLight)
                    ),
                    info = OudsColorScheme.Modes.OnStatus.Info(
                        emphasized = OudsColorMode.fromString(onStatusInfoEmphasizedLight),
                        muted = OudsColorMode.fromString(onStatusInfoMutedLight)
                    ),
                    negative = OudsColorScheme.Modes.OnStatus.Negative(
                        emphasized = OudsColorMode.fromString(onStatusNegativeEmphasizedLight),
                        muted = OudsColorMode.fromString(onStatusNegativeMutedLight)
                    ),
                    neutral = OudsColorScheme.Modes.OnStatus.Neutral(
                        emphasized = OudsColorMode.fromString(onStatusNeutralEmphasizedLight),
                        muted = OudsColorMode.fromString(onStatusNeutralMutedLight)
                    ),
                    positive = OudsColorScheme.Modes.OnStatus.Positive(
                        emphasized = OudsColorMode.fromString(onStatusPositiveEmphasizedLight),
                        muted = OudsColorMode.fromString(onStatusPositiveMutedLight)
                    ),
                    warning = OudsColorScheme.Modes.OnStatus.Warning(
                        emphasized = OudsColorMode.fromString(onStatusWarningEmphasizedLight),
                        muted = OudsColorMode.fromString(onStatusWarningMutedLight)
                    )
                )
            )
        }
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
                    disabled = actionSupportDisabledDark,
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
        always = alwaysColorScheme,
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
                brandSecondary = borderBrandSecondaryDark,
                brandTertiary = borderBrandTertiaryDark,
                default = borderDefaultDark,
                emphasized = borderEmphasizedDark,
                focus = borderFocusDark,
                focusInset = borderFocusInsetDark,
                muted = borderMutedDark,
                onBrand = OudsColorScheme.Border.OnBrand(
                    primary = borderOnBrandPrimaryDark,
                    secondary = borderOnBrandSecondaryDark,
                    tertiary = borderOnBrandTertiaryDark,
                ),
                status = OudsColorScheme.Border.Status(
                    accent = borderStatusAccentDark,
                    info = borderStatusInfoDark,
                    negative = borderStatusNegativeDark,
                    positive = borderStatusPositiveDark,
                    warning = borderStatusWarningDark,
                ),
            )
        },
        content = with(contentColorTokens) {
            OudsColorScheme.Content(
                brandPrimary = contentBrandPrimaryDark,
                brandSecondary = contentBrandSecondaryDark,
                brandTertiary = contentBrandTertiaryDark,
                default = contentDefaultDark,
                disabled = contentDisabledDark,
                muted = contentMutedDark,
                onAction = OudsColorScheme.Content.OnAction(
                    disabled = contentOnActionDisabledDark,
                    enabled = contentOnActionEnabledDark,
                    focus = contentOnActionFocusDark,
                    highlighted = contentOnActionHighlightedDark,
                    hover = contentOnActionHoverDark,
                    loading = contentOnActionLoadingDark,
                    pressed = contentOnActionPressedDark,
                    selected = contentOnActionSelectedDark,
                ),
                onBrand = OudsColorScheme.Content.OnBrand(
                    primary = contentOnBrandPrimaryDark,
                    secondary = contentOnBrandSecondaryDark,
                    tertiary = contentOnBrandTertiaryDark,
                ),
                onOverlay = OudsColorScheme.Content.OnOverlay(
                    emphasized = contentOnOverlayEmphasizedDark,
                ),
                onStatus = OudsColorScheme.Content.OnStatus(
                    accent = OudsColorScheme.Content.OnStatus.Accent(
                        emphasized = contentOnStatusAccentEmphasizedDark,
                        muted = contentOnStatusAccentMutedDark
                    ),
                    info = OudsColorScheme.Content.OnStatus.Info(
                        emphasized = contentOnStatusInfoEmphasizedDark,
                        muted = contentOnStatusInfoMutedDark
                    ),
                    negative = OudsColorScheme.Content.OnStatus.Negative(
                        emphasized = contentOnStatusNegativeEmphasizedDark,
                        muted = contentOnStatusNegativeMutedDark
                    ),
                    neutral = OudsColorScheme.Content.OnStatus.Neutral(
                        emphasized = contentOnStatusNeutralEmphasizedDark,
                        muted = contentOnStatusNeutralMutedDark
                    ),
                    positive = OudsColorScheme.Content.OnStatus.Positive(
                        emphasized = contentOnStatusPositiveEmphasizedDark,
                        muted = contentOnStatusPositiveMutedDark
                    ),
                    warning = OudsColorScheme.Content.OnStatus.Warning(
                        emphasized = contentOnStatusWarningEmphasizedDark,
                        muted = contentOnStatusWarningMutedDark
                    )
                ),
                status = OudsColorScheme.Content.Status(
                    accent = contentStatusAccentDark,
                    info = contentStatusInfoDark,
                    negative = contentStatusNegativeDark,
                    positive = contentStatusPositiveDark,
                    warning = contentStatusWarningDark,
                ),
            )
        },
        decorative = decorativeColorScheme,
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
                modal = overlayModalDark
            )
        },
        repository = repositoryColorScheme,
        surface = with(surfaceColorTokens) {
            OudsColorScheme.Surface(
                brand = OudsColorScheme.Surface.Brand(
                    primary = surfaceBrandPrimaryDark,
                    secondary = surfaceBrandSecondaryDark,
                    tertiary = surfaceBrandTertiaryDark,
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
        modes = with(colorModeTokens) {
            OudsColorScheme.Modes(
                onBackground = OudsColorScheme.Modes.OnBackground(
                    emphasized = OudsColorMode.fromString(onBgEmphasizedDark),
                    primary = OudsColorMode.fromString(onBgPrimaryDark),
                    secondary = OudsColorMode.fromString(onBgSecondaryDark),
                    tertiary = OudsColorMode.fromString(onBgTertiaryDark)
                ),
                onBrand = OudsColorScheme.Modes.OnBrand(
                    primary = OudsColorMode.fromString(onBrandPrimaryDark),
                    secondary = OudsColorMode.fromString(onBrandSecondaryDark),
                    tertiary = OudsColorMode.fromString(onBrandTertiaryDark)
                ),
                onOverlay = OudsColorScheme.Modes.OnOverlay(
                    default = OudsColorMode.fromString(onOverlayDefaultDark),
                    emphasized = OudsColorMode.fromString(onOverlayEmphasizedDark),
                    modal = OudsColorMode.fromString(onOverlayModalDark)
                ),
                onStatus = OudsColorScheme.Modes.OnStatus(
                    accent = OudsColorScheme.Modes.OnStatus.Accent(
                        emphasized = OudsColorMode.fromString(onStatusAccentEmphasizedDark),
                        muted = OudsColorMode.fromString(onStatusAccentMutedDark)
                    ),
                    info = OudsColorScheme.Modes.OnStatus.Info(
                        emphasized = OudsColorMode.fromString(onStatusInfoEmphasizedDark),
                        muted = OudsColorMode.fromString(onStatusInfoMutedDark)
                    ),
                    negative = OudsColorScheme.Modes.OnStatus.Negative(
                        emphasized = OudsColorMode.fromString(onStatusNegativeEmphasizedDark),
                        muted = OudsColorMode.fromString(onStatusNegativeMutedDark)
                    ),
                    neutral = OudsColorScheme.Modes.OnStatus.Neutral(
                        emphasized = OudsColorMode.fromString(onStatusNeutralEmphasizedDark),
                        muted = OudsColorMode.fromString(onStatusNeutralMutedDark)
                    ),
                    positive = OudsColorScheme.Modes.OnStatus.Positive(
                        emphasized = OudsColorMode.fromString(onStatusPositiveEmphasizedDark),
                        muted = OudsColorMode.fromString(onStatusPositiveMutedDark)
                    ),
                    warning = OudsColorScheme.Modes.OnStatus.Warning(
                        emphasized = OudsColorMode.fromString(onStatusWarningEmphasizedDark),
                        muted = OudsColorMode.fromString(onStatusWarningMutedDark)
                    )
                )
            )
        }
    )

// Always colors are the same in light & dark modes
private val OudsColorSemanticTokens.alwaysColorScheme: OudsColorScheme.Always
    get() = with(alwaysColorTokens) {
        OudsColorScheme.Always(
            black = alwaysBlack,
            onBlack = alwaysOnBlack,
            onWhite = alwaysOnWhite,
            white = alwaysWhite,
        )
    }

// Decorative colors are the same in light & dark modes
private val OudsColorSemanticTokens.decorativeColorScheme: OudsColorScheme.Decorative
    get() = with(decorativeColorTokens) {
        OudsColorScheme.Decorative(
            accent1 = OudsColorScheme.Decorative.Accent1(
                default = decorativeAccent1Default,
                emphasized = decorativeAccent1Emphasized,
                muted = decorativeAccent1Muted,
            ),
            accent2 = OudsColorScheme.Decorative.Accent2(
                default = decorativeAccent2Default,
                emphasized = decorativeAccent2Emphasized,
                muted = decorativeAccent2Muted,
            ),
            accent3 = OudsColorScheme.Decorative.Accent3(
                default = decorativeAccent3Default,
                emphasized = decorativeAccent3Emphasized,
                muted = decorativeAccent3Muted,
            ),
            accent4 = OudsColorScheme.Decorative.Accent4(
                default = decorativeAccent4Default,
                emphasized = decorativeAccent4Emphasized,
                muted = decorativeAccent4Muted,
            ),
            accent5 = OudsColorScheme.Decorative.Accent5(
                default = decorativeAccent5Default,
                emphasized = decorativeAccent5Emphasized,
                muted = decorativeAccent5Muted,
            ),
            brand = OudsColorScheme.Decorative.Brand(
                primaryEmphasized = decorativeBrandPrimaryEmphasized,
                primaryMuted = decorativeBrandPrimaryMuted,
                primary = decorativeBrandPrimary,
                secondary = decorativeBrandSecondary,
                secondaryEmphasized = decorativeBrandSecondaryEmphasized,
                secondaryMuted = decorativeBrandSecondaryMuted,
                tertiary = decorativeBrandTertiary,
                tertiaryEmphasized = decorativeBrandTertiaryEmphasized,
                tertiaryMuted = decorativeBrandTertiaryMuted,
            ),
            neutral = OudsColorScheme.Decorative.Neutral(
                emphasized = OudsColorScheme.Decorative.Neutral.Emphasized(
                    high = decorativeNeutralEmphasizedHigh,
                    higher = decorativeNeutralEmphasizedHigher,
                    highest = decorativeNeutralEmphasizedHighest,
                    low = decorativeNeutralEmphasizedLow,
                    lower = decorativeNeutralEmphasizedLower,
                    lowest = decorativeNeutralEmphasizedLowest,
                    medium = decorativeNeutralEmphasizedMedium
                ),
                muted = OudsColorScheme.Decorative.Neutral.Muted(
                    high = decorativeNeutralMutedHigh,
                    higher = decorativeNeutralMutedHigher,
                    highest = decorativeNeutralMutedHighest,
                    lower = decorativeNeutralMutedLower,
                    lowest = decorativeNeutralMutedLowest,
                    low = decorativeNeutralMutedLow,
                    medium = decorativeNeutralMutedMedium
                )
            ),
            skin = OudsColorScheme.Decorative.Skin(
                tint100 = decorativeSkinTint100,
                tint200 = decorativeSkinTint200,
                tint300 = decorativeSkinTint300,
                tint400 = decorativeSkinTint400,
                tint500 = decorativeSkinTint500,
                tint600 = decorativeSkinTint600,
                tint700 = decorativeSkinTint700,
                tint800 = decorativeSkinTint800,
                tint900 = decorativeSkinTint900,
            ),
        )
    }

// Repository colors are the same in light & dark modes
private val OudsColorSemanticTokens.repositoryColorScheme: OudsColorScheme.Repository
    get() = with(repositoryColorTokens) {
        OudsColorScheme.Repository(
            accent = OudsColorScheme.Repository.Accent(
                default = repositoryAccentDefault,
                high = repositoryAccentHigh,
                higher = repositoryAccentHigher,
                highest = repositoryAccentHighest,
                low = repositoryAccentLow,
                lower = repositoryAccentLower,
                lowest = repositoryAccentLowest,
            ),
            info = OudsColorScheme.Repository.Info(
                default = repositoryInfoDefault,
                high = repositoryInfoHigh,
                higher = repositoryInfoHigher,
                highest = repositoryInfoHighest,
                low = repositoryInfoLow,
                lower = repositoryInfoLower,
                lowest = repositoryInfoLowest,
            ),
            negative = OudsColorScheme.Repository.Negative(
                default = repositoryNegativeDefault,
                high = repositoryNegativeHigh,
                higher = repositoryNegativeHigher,
                highest = repositoryNegativeHighest,
                low = repositoryNegativeLow,
                lower = repositoryNegativeLower,
                lowest = repositoryNegativeLowest,
            ),
            neutral = OudsColorScheme.Repository.Neutral(
                emphasized = OudsColorScheme.Repository.Neutral.Emphasized(
                    black = repositoryNeutralEmphasizedBlack,
                    high = repositoryNeutralEmphasizedHigh,
                    higher = repositoryNeutralEmphasizedHigher,
                    highest = repositoryNeutralEmphasizedHighest,
                    low = repositoryNeutralEmphasizedLow,
                    lower = repositoryNeutralEmphasizedLower,
                    lowest = repositoryNeutralEmphasizedLowest,
                    medium = repositoryNeutralEmphasizedMedium
                ),
                muted = OudsColorScheme.Repository.Neutral.Muted(
                    high = repositoryNeutralMutedHigh,
                    higher = repositoryNeutralMutedHigher,
                    highest = repositoryNeutralMutedHighest,
                    low = repositoryNeutralMutedLow,
                    lower = repositoryNeutralMutedLower,
                    lowest = repositoryNeutralMutedLowest,
                    medium = repositoryNeutralMutedMedium,
                    white = repositoryNeutralMutedWhite,
                )
            ),
            opacity = OudsColorScheme.Repository.Opacity(
                accent = repositoryOpacityAccent,
                black = OudsColorScheme.Repository.Opacity.Black(
                    high = repositoryOpacityBlackHigh,
                    higher = repositoryOpacityBlackHigher,
                    highest = repositoryOpacityBlackHighest,
                    low = repositoryOpacityBlackLow,
                    lower = repositoryOpacityBlackLower,
                    lowest = repositoryOpacityBlackLowest,
                    medium = repositoryOpacityBlackMedium,
                    mediumHigh = repositoryOpacityBlackMediumHigh,
                    mediumLow = repositoryOpacityBlackMediumLow,
                    transparent = repositoryOpacityBlackTransparent,
                ),
                info = repositoryOpacityInfo,
                negative = repositoryOpacityNegative,
                positive = repositoryOpacityPositive,
                primary = OudsColorScheme.Repository.Opacity.Primary(
                    high = repositoryOpacityPrimaryHigh,
                    higher = repositoryOpacityPrimaryHigher,
                    highest = repositoryOpacityPrimaryHighest,
                    low = repositoryOpacityPrimaryLow,
                    lower = repositoryOpacityPrimaryLower,
                    lowest = repositoryOpacityPrimaryLowest,
                    medium = repositoryOpacityPrimaryMedium,
                    transparent = repositoryOpacityPrimaryTransparent,
                ),
                warning = repositoryOpacityWarning,
                white = OudsColorScheme.Repository.Opacity.White(
                    high = repositoryOpacityWhiteHigh,
                    higher = repositoryOpacityWhiteHigher,
                    highest = repositoryOpacityWhiteHighest,
                    low = repositoryOpacityWhiteLow,
                    lower = repositoryOpacityWhiteLower,
                    lowest = repositoryOpacityWhiteLowest,
                    medium = repositoryOpacityWhiteMedium,
                    mediumLow = repositoryOpacityWhiteMediumLow,
                    transparent = repositoryOpacityWhiteTransparent,
                ),
            ),
            positive = OudsColorScheme.Repository.Positive(
                default = repositoryPositiveDefault,
                high = repositoryPositiveHigh,
                higher = repositoryPositiveHigher,
                highest = repositoryPositiveHighest,
                low = repositoryPositiveLow,
                lower = repositoryPositiveLower,
                lowest = repositoryPositiveLowest,
            ),
            primary = OudsColorScheme.Repository.Primary(
                default = repositoryPrimaryDefault,
                high = repositoryPrimaryHigh,
                higher = repositoryPrimaryHigher,
                highest = repositoryPrimaryHighest,
                low = repositoryPrimaryLow,
                lower = repositoryPrimaryLower,
                lowest = repositoryPrimaryLowest,
            ),
            secondary = OudsColorScheme.Repository.Secondary(
                default = repositorySecondaryDefault,
                high = repositorySecondaryHigh,
                higher = repositorySecondaryHigher,
                highest = repositorySecondaryHighest,
                low = repositorySecondaryLow,
                lower = repositorySecondaryLower,
                lowest = repositorySecondaryLowest
            ),
            tertiary = OudsColorScheme.Repository.Tertiary(
                default = repositoryTertiaryDefault,
                high = repositoryTertiaryHigh,
                higher = repositoryTertiaryHigher,
                highest = repositoryTertiaryHighest,
                low = repositoryTertiaryLow,
                lower = repositoryTertiaryLower,
                lowest = repositoryTertiaryLowest
            ),
            warning = OudsColorScheme.Repository.Warning(
                default = repositoryWarningDefault,
                high = repositoryWarningHigh,
                higher = repositoryWarningHigher,
                highest = repositoryWarningHighest,
                low = repositoryWarningLow,
                lower = repositoryWarningLower,
                lowest = repositoryWarningLowest,
            ),
        )
    }

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
            OudsColorKeyToken.Action.Support.Disabled -> support.disabled
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
            OudsColorKeyToken.Border.BrandSecondary -> brandSecondary
            OudsColorKeyToken.Border.BrandTertiary -> brandTertiary
            OudsColorKeyToken.Border.Default -> default
            OudsColorKeyToken.Border.Emphasized -> emphasized
            OudsColorKeyToken.Border.Focus -> focus
            OudsColorKeyToken.Border.FocusInset -> focusInset
            OudsColorKeyToken.Border.Muted -> muted
            OudsColorKeyToken.Border.OnBrand.Primary -> onBrand.primary
            OudsColorKeyToken.Border.OnBrand.Secondary -> onBrand.secondary
            OudsColorKeyToken.Border.OnBrand.Tertiary -> onBrand.tertiary
            OudsColorKeyToken.Border.Status.Accent -> status.accent
            OudsColorKeyToken.Border.Status.Info -> status.info
            OudsColorKeyToken.Border.Status.Negative -> status.negative
            OudsColorKeyToken.Border.Status.Positive -> status.positive
            OudsColorKeyToken.Border.Status.Warning -> status.warning
        }
    }
}

@Stable
private fun OudsColorScheme.fromToken(token: OudsColorKeyToken.Content): Color {
    return with(content) {
        when (token) {
            OudsColorKeyToken.Content.BrandPrimary -> brandPrimary
            OudsColorKeyToken.Content.BrandSecondary -> brandSecondary
            OudsColorKeyToken.Content.BrandTertiary -> brandTertiary
            OudsColorKeyToken.Content.Default -> default
            OudsColorKeyToken.Content.Disabled -> disabled
            OudsColorKeyToken.Content.Muted -> muted
            OudsColorKeyToken.Content.OnAction.Disabled -> onAction.disabled
            OudsColorKeyToken.Content.OnAction.Enabled -> onAction.enabled
            OudsColorKeyToken.Content.OnAction.Focus -> onAction.focus
            OudsColorKeyToken.Content.OnAction.Highlighted -> onAction.highlighted
            OudsColorKeyToken.Content.OnAction.Hover -> onAction.hover
            OudsColorKeyToken.Content.OnAction.Loading -> onAction.loading
            OudsColorKeyToken.Content.OnAction.Pressed -> onAction.pressed
            OudsColorKeyToken.Content.OnAction.Selected -> onAction.selected
            OudsColorKeyToken.Content.OnBrand.Primary -> onBrand.primary
            OudsColorKeyToken.Content.OnBrand.Secondary -> onBrand.secondary
            OudsColorKeyToken.Content.OnBrand.Tertiary -> onBrand.tertiary
            OudsColorKeyToken.Content.OnOverlay.Emphasized -> onOverlay.emphasized
            OudsColorKeyToken.Content.OnStatus.Neutral.Emphasized -> onStatus.neutral.emphasized
            OudsColorKeyToken.Content.OnStatus.Neutral.Muted -> onStatus.neutral.muted
            OudsColorKeyToken.Content.OnStatus.Positive.Emphasized -> onStatus.positive.emphasized
            OudsColorKeyToken.Content.OnStatus.Positive.Muted -> onStatus.positive.muted
            OudsColorKeyToken.Content.OnStatus.Info.Emphasized -> onStatus.info.emphasized
            OudsColorKeyToken.Content.OnStatus.Info.Muted -> onStatus.info.muted
            OudsColorKeyToken.Content.OnStatus.Warning.Emphasized -> onStatus.warning.emphasized
            OudsColorKeyToken.Content.OnStatus.Warning.Muted -> onStatus.warning.muted
            OudsColorKeyToken.Content.OnStatus.Negative.Emphasized -> onStatus.negative.emphasized
            OudsColorKeyToken.Content.OnStatus.Negative.Muted -> onStatus.negative.muted
            OudsColorKeyToken.Content.OnStatus.Accent.Emphasized -> onStatus.accent.emphasized
            OudsColorKeyToken.Content.OnStatus.Accent.Muted -> onStatus.accent.muted
            OudsColorKeyToken.Content.Status.Accent -> status.accent
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
            OudsColorKeyToken.Decorative.Brand.PrimaryEmphasized -> brand.primaryEmphasized
            OudsColorKeyToken.Decorative.Brand.PrimaryMuted -> brand.primaryMuted
            OudsColorKeyToken.Decorative.Brand.Secondary -> brand.secondary
            OudsColorKeyToken.Decorative.Brand.SecondaryEmphasized -> brand.secondaryEmphasized
            OudsColorKeyToken.Decorative.Brand.SecondaryMuted -> brand.secondaryMuted
            OudsColorKeyToken.Decorative.Brand.Tertiary -> brand.tertiary
            OudsColorKeyToken.Decorative.Brand.TertiaryEmphasized -> brand.tertiaryEmphasized
            OudsColorKeyToken.Decorative.Brand.TertiaryMuted -> brand.tertiaryMuted
            OudsColorKeyToken.Decorative.Neutral.Emphasized.High -> neutral.emphasized.high
            OudsColorKeyToken.Decorative.Neutral.Emphasized.Higher -> neutral.emphasized.higher
            OudsColorKeyToken.Decorative.Neutral.Emphasized.Highest -> neutral.emphasized.highest
            OudsColorKeyToken.Decorative.Neutral.Emphasized.Low -> neutral.emphasized.low
            OudsColorKeyToken.Decorative.Neutral.Emphasized.Lower -> neutral.emphasized.lower
            OudsColorKeyToken.Decorative.Neutral.Emphasized.Lowest -> neutral.emphasized.lowest
            OudsColorKeyToken.Decorative.Neutral.Emphasized.Medium -> neutral.emphasized.medium
            OudsColorKeyToken.Decorative.Neutral.Muted.High -> neutral.muted.high
            OudsColorKeyToken.Decorative.Neutral.Muted.Higher -> neutral.muted.higher
            OudsColorKeyToken.Decorative.Neutral.Muted.Highest -> neutral.muted.highest
            OudsColorKeyToken.Decorative.Neutral.Muted.Low -> neutral.muted.low
            OudsColorKeyToken.Decorative.Neutral.Muted.Lower -> neutral.muted.lower
            OudsColorKeyToken.Decorative.Neutral.Muted.Lowest -> neutral.muted.lowest
            OudsColorKeyToken.Decorative.Neutral.Muted.Medium -> neutral.muted.medium
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
            OudsColorKeyToken.Repository.Accent.High -> accent.high
            OudsColorKeyToken.Repository.Accent.Higher -> accent.higher
            OudsColorKeyToken.Repository.Accent.Highest -> accent.highest
            OudsColorKeyToken.Repository.Accent.Low -> accent.low
            OudsColorKeyToken.Repository.Accent.Lower -> accent.lower
            OudsColorKeyToken.Repository.Accent.Lowest -> accent.lowest
            OudsColorKeyToken.Repository.Info.Default -> info.default
            OudsColorKeyToken.Repository.Info.High -> info.high
            OudsColorKeyToken.Repository.Info.Higher -> info.higher
            OudsColorKeyToken.Repository.Info.Highest -> info.highest
            OudsColorKeyToken.Repository.Info.Low -> info.low
            OudsColorKeyToken.Repository.Info.Lower -> info.lower
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
            OudsColorKeyToken.Repository.Neutral.Emphasized.Low -> neutral.emphasized.low
            OudsColorKeyToken.Repository.Neutral.Emphasized.Lower -> neutral.emphasized.lower
            OudsColorKeyToken.Repository.Neutral.Emphasized.Lowest -> neutral.emphasized.lowest
            OudsColorKeyToken.Repository.Neutral.Emphasized.Medium -> neutral.emphasized.medium
            OudsColorKeyToken.Repository.Neutral.Muted.High -> neutral.muted.high
            OudsColorKeyToken.Repository.Neutral.Muted.Higher -> neutral.muted.higher
            OudsColorKeyToken.Repository.Neutral.Muted.Highest -> neutral.muted.highest
            OudsColorKeyToken.Repository.Neutral.Muted.Low -> neutral.muted.low
            OudsColorKeyToken.Repository.Neutral.Muted.Lower -> neutral.muted.lower
            OudsColorKeyToken.Repository.Neutral.Muted.Lowest -> neutral.muted.lowest
            OudsColorKeyToken.Repository.Neutral.Muted.Medium -> neutral.muted.medium
            OudsColorKeyToken.Repository.Neutral.Muted.White -> neutral.muted.white
            OudsColorKeyToken.Repository.Opacity.Accent -> opacity.accent
            OudsColorKeyToken.Repository.Opacity.Black.High -> opacity.black.high
            OudsColorKeyToken.Repository.Opacity.Black.Higher -> opacity.black.higher
            OudsColorKeyToken.Repository.Opacity.Black.Highest -> opacity.black.highest
            OudsColorKeyToken.Repository.Opacity.Black.Low -> opacity.black.low
            OudsColorKeyToken.Repository.Opacity.Black.Lower -> opacity.black.lower
            OudsColorKeyToken.Repository.Opacity.Black.Lowest -> opacity.black.lowest
            OudsColorKeyToken.Repository.Opacity.Black.Medium -> opacity.black.medium
            OudsColorKeyToken.Repository.Opacity.Black.MediumHigh -> opacity.black.mediumHigh
            OudsColorKeyToken.Repository.Opacity.Black.MediumLow -> opacity.black.mediumLow
            OudsColorKeyToken.Repository.Opacity.Black.Transparent -> opacity.black.transparent
            OudsColorKeyToken.Repository.Opacity.Info -> opacity.info
            OudsColorKeyToken.Repository.Opacity.Negative -> opacity.negative
            OudsColorKeyToken.Repository.Opacity.Positive -> opacity.positive
            OudsColorKeyToken.Repository.Opacity.Primary.High -> opacity.primary.high
            OudsColorKeyToken.Repository.Opacity.Primary.Higher -> opacity.primary.higher
            OudsColorKeyToken.Repository.Opacity.Primary.Highest -> opacity.primary.highest
            OudsColorKeyToken.Repository.Opacity.Primary.Low -> opacity.primary.low
            OudsColorKeyToken.Repository.Opacity.Primary.Lower -> opacity.primary.lower
            OudsColorKeyToken.Repository.Opacity.Primary.Lowest -> opacity.primary.lowest
            OudsColorKeyToken.Repository.Opacity.Primary.Medium -> opacity.primary.medium
            OudsColorKeyToken.Repository.Opacity.Primary.Transparent -> opacity.primary.transparent
            OudsColorKeyToken.Repository.Opacity.Warning -> opacity.warning
            OudsColorKeyToken.Repository.Opacity.White.High -> opacity.white.high
            OudsColorKeyToken.Repository.Opacity.White.Higher -> opacity.white.higher
            OudsColorKeyToken.Repository.Opacity.White.Highest -> opacity.white.highest
            OudsColorKeyToken.Repository.Opacity.White.Low -> opacity.white.low
            OudsColorKeyToken.Repository.Opacity.White.Lower -> opacity.white.lower
            OudsColorKeyToken.Repository.Opacity.White.Lowest -> opacity.white.lowest
            OudsColorKeyToken.Repository.Opacity.White.Medium -> opacity.white.medium
            OudsColorKeyToken.Repository.Opacity.White.MediumLow -> opacity.white.mediumLow
            OudsColorKeyToken.Repository.Opacity.White.Transparent -> opacity.white.transparent
            OudsColorKeyToken.Repository.Positive.Default -> positive.default
            OudsColorKeyToken.Repository.Positive.High -> positive.high
            OudsColorKeyToken.Repository.Positive.Higher -> positive.higher
            OudsColorKeyToken.Repository.Positive.Highest -> positive.highest
            OudsColorKeyToken.Repository.Positive.Low -> positive.low
            OudsColorKeyToken.Repository.Positive.Lower -> positive.lower
            OudsColorKeyToken.Repository.Positive.Lowest -> positive.lowest
            OudsColorKeyToken.Repository.Primary.Default -> primary.default
            OudsColorKeyToken.Repository.Primary.High -> primary.high
            OudsColorKeyToken.Repository.Primary.Higher -> primary.higher
            OudsColorKeyToken.Repository.Primary.Highest -> primary.highest
            OudsColorKeyToken.Repository.Primary.Low -> primary.low
            OudsColorKeyToken.Repository.Primary.Lower -> primary.lower
            OudsColorKeyToken.Repository.Primary.Lowest -> primary.lowest
            OudsColorKeyToken.Repository.Secondary.Default -> secondary.default
            OudsColorKeyToken.Repository.Secondary.High -> secondary.high
            OudsColorKeyToken.Repository.Secondary.Higher -> secondary.higher
            OudsColorKeyToken.Repository.Secondary.Highest -> secondary.highest
            OudsColorKeyToken.Repository.Secondary.Low -> secondary.low
            OudsColorKeyToken.Repository.Secondary.Lower -> secondary.lower
            OudsColorKeyToken.Repository.Secondary.Lowest -> secondary.lowest
            OudsColorKeyToken.Repository.Tertiary.Default -> tertiary.default
            OudsColorKeyToken.Repository.Tertiary.High -> tertiary.high
            OudsColorKeyToken.Repository.Tertiary.Higher -> tertiary.higher
            OudsColorKeyToken.Repository.Tertiary.Highest -> tertiary.highest
            OudsColorKeyToken.Repository.Tertiary.Low -> tertiary.low
            OudsColorKeyToken.Repository.Tertiary.Lower -> tertiary.lower
            OudsColorKeyToken.Repository.Tertiary.Lowest -> tertiary.lowest
            OudsColorKeyToken.Repository.Warning.Default -> warning.default
            OudsColorKeyToken.Repository.Warning.High -> warning.high
            OudsColorKeyToken.Repository.Warning.Higher -> warning.higher
            OudsColorKeyToken.Repository.Warning.Highest -> warning.highest
            OudsColorKeyToken.Repository.Warning.Low -> warning.low
            OudsColorKeyToken.Repository.Warning.Lower -> warning.lower
            OudsColorKeyToken.Repository.Warning.Lowest -> warning.lowest
        }
    }
}

@Stable
private fun OudsColorScheme.fromToken(token: OudsColorKeyToken.Surface): Color {
    return with(surface) {
        when (token) {
            OudsColorKeyToken.Surface.Brand.Primary -> brand.primary
            OudsColorKeyToken.Surface.Brand.Secondary -> brand.secondary
            OudsColorKeyToken.Surface.Brand.Tertiary -> brand.tertiary
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

val OudsMaterialColorTokens.materialLightColorScheme: ColorScheme
    get() = lightColorScheme(
        primary = primaryLight,
        onPrimary = onPrimaryLight,
        primaryContainer = primaryContainerLight,
        onPrimaryContainer = onPrimaryContainerLight,
        secondary = secondaryLight,
        onSecondary = onSecondaryLight,
        secondaryContainer = secondaryContainerLight,
        onSecondaryContainer = onSecondaryContainerLight,
        tertiary = tertiaryLight,
        onTertiary = onTertiaryLight,
        tertiaryContainer = tertiaryContainerLight,
        onTertiaryContainer = onTertiaryContainerLight,
        error = errorLight,
        onError = onErrorLight,
        errorContainer = errorContainerLight,
        onErrorContainer = onErrorContainerLight,
        surfaceDim = surfaceDimLight,
        surface = surfaceLight,
        surfaceBright = surfaceBrightLight,
        onSurface = onSurfaceLight,
        onSurfaceVariant = onSurfaceVariantLight,
        surfaceContainerLowest = surfContainerLowestLight,
        surfaceContainerLow = surfContainerLowLight,
        surfaceContainer = surfContainerLight,
        surfaceContainerHigh = surfContainerHighLight,
        surfaceContainerHighest = surfContainerHighestLight,
        inverseSurface = inverseSurfaceLight,
        inverseOnSurface = inverseOnSurfaceLight,
        inversePrimary = inversePrimaryLight,
        outline = outlineLight,
        outlineVariant = outlineVariantLight,
        scrim = scrimLight,
        background = backgroundLight,
        onBackground = onBackgroundLight,
        surfaceVariant = surfaceVariantLight,
        surfaceTint = surfaceTintLight,
    )

val OudsMaterialColorTokens.materialDarkColorScheme: ColorScheme
    get() = darkColorScheme(
        primary = primaryDark,
        onPrimary = onPrimaryDark,
        primaryContainer = primaryContainerDark,
        onPrimaryContainer = onPrimaryContainerDark,
        secondary = secondaryDark,
        onSecondary = onSecondaryDark,
        secondaryContainer = secondaryContainerDark,
        onSecondaryContainer = onSecondaryContainerDark,
        tertiary = tertiaryDark,
        onTertiary = onTertiaryDark,
        tertiaryContainer = tertiaryContainerDark,
        onTertiaryContainer = onTertiaryContainerDark,
        error = errorDark,
        onError = onErrorDark,
        errorContainer = errorContainerDark,
        onErrorContainer = onErrorContainerDark,
        surfaceDim = surfaceDimDark,
        surface = surfaceDark,
        surfaceBright = surfaceBrightDark,
        onSurface = onSurfaceDark,
        onSurfaceVariant = onSurfaceVariantDark,
        surfaceContainerLowest = surfContainerLowestDark,
        surfaceContainerLow = surfContainerLowDark,
        surfaceContainer = surfContainerDark,
        surfaceContainerHigh = surfContainerHighDark,
        surfaceContainerHighest = surfContainerHighestDark,
        inverseSurface = inverseSurfaceDark,
        inverseOnSurface = inverseOnSurfaceDark,
        inversePrimary = inversePrimaryDark,
        outline = outlineDark,
        outlineVariant = outlineVariantDark,
        scrim = scrimDark,
        background = backgroundDark,
        onBackground = onBackgroundDark,
        surfaceVariant = surfaceVariantDark,
        surfaceTint = surfaceTintDark,
    )

/**
 * Converts an OUDS color token to the local color value provided by the theme.
 */
@Suppress("RecursivePropertyAccessor")
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
        is OudsLightDarkColorKeyToken -> if (isOudsInDarkTheme()) dark.value else light.value
    }

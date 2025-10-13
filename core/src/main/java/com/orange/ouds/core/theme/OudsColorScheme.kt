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
import com.orange.ouds.theme.tokens.Mode
import com.orange.ouds.theme.tokens.OudsColorKeyToken
import com.orange.ouds.theme.tokens.OudsLightDarkColorKeyToken
import com.orange.ouds.theme.tokens.OudsSingleModeColorKeyToken
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
        val inverseHigh: Color,
        val inverseLow: Color,
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
        val inverse: Color,
        val muted: Color,
        val onAction: OnAction,
        val onBrand: OnBrand,
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

        data class OnStatus(
            val positive: Positive,
            val info: Info,
            val warning: Warning,
            val negative: Negative,
            val accent: Accent
        ) {
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

    data class Opacity(
        val transparent: Color,
        val lowest: Color,
        val lower: Color
    )

    data class Overlay(
        val dropdown: Color,
        val drag: Color,
        val modal: Color,
        val tooltip: Color
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
            val high: Color,
            val higher: Color,
            val highest: Color,
            val low: Color,
            val lower: Color,
            val lowest: Color,
            val medium: Color
        )

        data class Info(
            val high: Color,
            val higher: Color,
            val highest: Color,
            val low: Color,
            val lower: Color,
            val lowest: Color,
            val medium: Color
        )

        data class Negative(
            val high: Color,
            val higher: Color,
            val highest: Color,
            val low: Color,
            val lower: Color,
            val lowest: Color,
            val medium: Color
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
            val accent: Accent,
            val black: Black,
            val info: Info,
            val negative: Negative,
            val positive: Positive,
            val primary: Primary,
            val warning: Warning,
            val white: White
        ) {
            data class Accent(
                val low: Color,
                val medium: Color,
            )

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

            data class Info(
                val low: Color,
                val medium: Color,
            )

            data class Negative(
                val low: Color,
                val medium: Color,
            )

            data class Positive(
                val low: Color,
                val medium: Color,
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

            data class Warning(
                val low: Color,
                val medium: Color,
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
            val high: Color,
            val higher: Color,
            val highest: Color,
            val low: Color,
            val lower: Color,
            val lowest: Color,
            val medium: Color
        )

        data class Primary(
            val high: Color,
            val higher: Color,
            val highest: Color,
            val low: Color,
            val lower: Color,
            val lowest: Color,
            val medium: Color
        )

        data class Secondary(
            val high: Color,
            val higher: Color,
            val highest: Color,
            val low: Color,
            val lower: Color,
            val lowest: Color,
            val medium: Color
        )

        data class Tertiary(
            val high: Color,
            val higher: Color,
            val highest: Color,
            val low: Color,
            val lower: Color,
            val lowest: Color,
            val medium: Color
        )

        data class Warning(
            val high: Color,
            val higher: Color,
            val highest: Color,
            val low: Color,
            val lower: Color,
            val lowest: Color,
            val medium: Color
        )
    }

    data class Surface(
        val brand: Brand,
        val inverseHigh: Color,
        val inverseLow: Color,
        val primary: Color,
        val secondary: Color,
        val status: Status,
        val tertiary: Color
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
        val navigationBar: OudsColorMode,
        val onBackground: OnBackground,
        val onBrand: OnBrand,
        val onOverlay: OnOverlay,
        val onStatus: OnStatus,
        val onSurface: OnSurface,
    ) {

        data class OnBackground(
            val inverseHigh: OudsColorMode,
            val inverseLow: OudsColorMode,
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
            val dropdown: OudsColorMode,
            val modal: OudsColorMode,
            val tooltip: OudsColorMode
        )

        data class OnStatus(
            val accent: Accent,
            val info: Info,
            val negative: Negative,
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

            data class Positive(
                val emphasized: OudsColorMode,
                val muted: OudsColorMode
            )

            data class Warning(
                val emphasized: OudsColorMode,
                val muted: OudsColorMode
            )
        }

        data class OnSurface(
            val inverseHigh: OudsColorMode,
            val inverseLow: OudsColorMode,
            val primary: OudsColorMode,
            val secondary: OudsColorMode,
            val tertiary: OudsColorMode
        )
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
                inverseHigh = bgInverseHighLight,
                inverseLow = bgInverseLowLight,
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
                inverse = contentInverseLight,
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
                    secondary = contentOnBrandSecondaryLight,
                    tertiary = contentOnBrandTertiaryLight
                ),
                onStatus = OudsColorScheme.Content.OnStatus(
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
        opacity = with(opacityColorTokens) {
            OudsColorScheme.Opacity(
                transparent = opacityTransparentLight,
                lowest = opacityLowestLight,
                lower = opacityLowerLight,
            )
        },
        overlay = with(overlayColorTokens) {
            OudsColorScheme.Overlay(
                dropdown = overlayDropdownLight,
                drag = overlayDragLight,
                modal = overlayModalLight,
                tooltip = overlayTooltipLight
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
                inverseHigh = surfaceInverseHighLight,
                inverseLow = surfaceInverseLowLight,
                primary = surfacePrimaryLight,
                secondary = surfaceSecondaryLight,
                tertiary = surfaceTertiaryLight,
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
                navigationBar = OudsColorMode.fromString(navigationBarLight),
                onBackground = OudsColorScheme.Modes.OnBackground(
                    inverseHigh = OudsColorMode.fromString(onBgInverseHighLight),
                    inverseLow = OudsColorMode.fromString(onBgInverseLowLight),
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
                    dropdown = OudsColorMode.fromString(onOverlayDropdownLight),
                    modal = OudsColorMode.fromString(onOverlayModalLight),
                    tooltip = OudsColorMode.fromString(onOverlayTooltipLight)
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
                    positive = OudsColorScheme.Modes.OnStatus.Positive(
                        emphasized = OudsColorMode.fromString(onStatusPositiveEmphasizedLight),
                        muted = OudsColorMode.fromString(onStatusPositiveMutedLight)
                    ),
                    warning = OudsColorScheme.Modes.OnStatus.Warning(
                        emphasized = OudsColorMode.fromString(onStatusWarningEmphasizedLight),
                        muted = OudsColorMode.fromString(onStatusWarningMutedLight)
                    )
                ),
                onSurface = OudsColorScheme.Modes.OnSurface(
                    inverseHigh = OudsColorMode.fromString(onSurfaceInverseHighLight),
                    inverseLow = OudsColorMode.fromString(onSurfaceInverseLowLight),
                    primary = OudsColorMode.fromString(onSurfacePrimaryLight),
                    secondary = OudsColorMode.fromString(onSurfaceSecondaryLight),
                    tertiary = OudsColorMode.fromString(onSurfaceTertiaryLight)
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
                inverseHigh = bgInverseHighDark,
                inverseLow = bgInverseLowDark,
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
                inverse = contentInverseDark,
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
        opacity = with(opacityColorTokens) {
            OudsColorScheme.Opacity(
                transparent = opacityTransparentDark,
                lowest = opacityLowestDark,
                lower = opacityLowerDark,
            )
        },
        overlay = with(overlayColorTokens) {
            OudsColorScheme.Overlay(
                dropdown = overlayDropdownDark,
                drag = overlayDragDark,
                modal = overlayModalDark,
                tooltip = overlayTooltipDark,
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
                inverseHigh = surfaceInverseHighDark,
                inverseLow = surfaceInverseLowDark,
                primary = surfacePrimaryDark,
                secondary = surfaceSecondaryDark,
                tertiary = surfaceTertiaryDark,
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
                navigationBar = OudsColorMode.fromString(navigationBarDark),
                onBackground = OudsColorScheme.Modes.OnBackground(
                    inverseHigh = OudsColorMode.fromString(onBgInverseHighDark),
                    inverseLow = OudsColorMode.fromString(onBgInverseLowDark),
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
                    dropdown = OudsColorMode.fromString(onOverlayDropdownDark),
                    modal = OudsColorMode.fromString(onOverlayModalDark),
                    tooltip = OudsColorMode.fromString(onOverlayTooltipDark)

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
                    positive = OudsColorScheme.Modes.OnStatus.Positive(
                        emphasized = OudsColorMode.fromString(onStatusPositiveEmphasizedDark),
                        muted = OudsColorMode.fromString(onStatusPositiveMutedDark)
                    ),
                    warning = OudsColorScheme.Modes.OnStatus.Warning(
                        emphasized = OudsColorMode.fromString(onStatusWarningEmphasizedDark),
                        muted = OudsColorMode.fromString(onStatusWarningMutedDark)
                    )
                ),
                onSurface = OudsColorScheme.Modes.OnSurface(
                    inverseHigh = OudsColorMode.fromString(onSurfaceInverseHighDark),
                    inverseLow = OudsColorMode.fromString(onSurfaceInverseLowDark),
                    primary = OudsColorMode.fromString(onSurfacePrimaryDark),
                    secondary = OudsColorMode.fromString(onSurfaceSecondaryDark),
                    tertiary = OudsColorMode.fromString(onSurfaceTertiaryDark)
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

// Repository colors are the same in light & dark modes
private val OudsColorSemanticTokens.repositoryColorScheme: OudsColorScheme.Repository
    get() = with(repositoryColorTokens) {
        OudsColorScheme.Repository(
            accent = OudsColorScheme.Repository.Accent(
                high = repositoryAccentHigh,
                higher = repositoryAccentHigher,
                highest = repositoryAccentHighest,
                low = repositoryAccentLow,
                lower = repositoryAccentLower,
                lowest = repositoryAccentLowest,
                medium = repositoryAccentMedium,
            ),
            info = OudsColorScheme.Repository.Info(
                high = repositoryInfoHigh,
                higher = repositoryInfoHigher,
                highest = repositoryInfoHighest,
                low = repositoryInfoLow,
                lower = repositoryInfoLower,
                lowest = repositoryInfoLowest,
                medium = repositoryInfoMedium,
            ),
            negative = OudsColorScheme.Repository.Negative(
                high = repositoryNegativeHigh,
                higher = repositoryNegativeHigher,
                highest = repositoryNegativeHighest,
                low = repositoryNegativeLow,
                lower = repositoryNegativeLower,
                lowest = repositoryNegativeLowest,
                medium = repositoryNegativeMedium,
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
                accent = OudsColorScheme.Repository.Opacity.Accent(
                    low = repositoryOpacityAccentLow,
                    medium = repositoryOpacityAccentMedium,
                ),
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
                info = OudsColorScheme.Repository.Opacity.Info(
                    low = repositoryOpacityInfoLow,
                    medium = repositoryOpacityInfoMedium,
                ),
                negative = OudsColorScheme.Repository.Opacity.Negative(
                    low = repositoryOpacityNegativeLow,
                    medium = repositoryOpacityNegativeMedium,
                ),
                positive = OudsColorScheme.Repository.Opacity.Positive(
                    low = repositoryOpacityPositiveLow,
                    medium = repositoryOpacityPositiveMedium,
                ),
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
                warning = OudsColorScheme.Repository.Opacity.Warning(
                    low = repositoryOpacityWarningLow,
                    medium = repositoryOpacityWarningMedium,
                ),
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
                high = repositoryPositiveHigh,
                higher = repositoryPositiveHigher,
                highest = repositoryPositiveHighest,
                low = repositoryPositiveLow,
                lower = repositoryPositiveLower,
                lowest = repositoryPositiveLowest,
                medium = repositoryPositiveMedium,
            ),
            primary = OudsColorScheme.Repository.Primary(
                high = repositoryPrimaryHigh,
                higher = repositoryPrimaryHigher,
                highest = repositoryPrimaryHighest,
                low = repositoryPrimaryLow,
                lower = repositoryPrimaryLower,
                lowest = repositoryPrimaryLowest,
                medium = repositoryPrimaryMedium,
            ),
            secondary = OudsColorScheme.Repository.Secondary(
                high = repositorySecondaryHigh,
                higher = repositorySecondaryHigher,
                highest = repositorySecondaryHighest,
                low = repositorySecondaryLow,
                lower = repositorySecondaryLower,
                lowest = repositorySecondaryLowest,
                medium = repositorySecondaryMedium,
            ),
            tertiary = OudsColorScheme.Repository.Tertiary(
                high = repositoryTertiaryHigh,
                higher = repositoryTertiaryHigher,
                highest = repositoryTertiaryHighest,
                low = repositoryTertiaryLow,
                lower = repositoryTertiaryLower,
                lowest = repositoryTertiaryLowest,
                medium = repositoryTertiaryMedium,
            ),
            warning = OudsColorScheme.Repository.Warning(
                high = repositoryWarningHigh,
                higher = repositoryWarningHigher,
                highest = repositoryWarningHighest,
                low = repositoryWarningLow,
                lower = repositoryWarningLower,
                lowest = repositoryWarningLowest,
                medium = repositoryWarningMedium,
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
            OudsColorKeyToken.Background.InverseLow -> inverseLow
            OudsColorKeyToken.Background.InverseHigh -> inverseHigh
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
            OudsColorKeyToken.Content.Inverse -> inverse
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
private fun OudsColorScheme.fromToken(token: OudsColorKeyToken.Repository): Color {
    return with(repository) {
        when (token) {
            OudsColorKeyToken.Repository.Accent.High -> accent.high
            OudsColorKeyToken.Repository.Accent.Higher -> accent.higher
            OudsColorKeyToken.Repository.Accent.Highest -> accent.highest
            OudsColorKeyToken.Repository.Accent.Low -> accent.low
            OudsColorKeyToken.Repository.Accent.Lower -> accent.lower
            OudsColorKeyToken.Repository.Accent.Lowest -> accent.lowest
            OudsColorKeyToken.Repository.Accent.Medium -> accent.medium
            OudsColorKeyToken.Repository.Info.High -> info.high
            OudsColorKeyToken.Repository.Info.Higher -> info.higher
            OudsColorKeyToken.Repository.Info.Highest -> info.highest
            OudsColorKeyToken.Repository.Info.Low -> info.low
            OudsColorKeyToken.Repository.Info.Lower -> info.lower
            OudsColorKeyToken.Repository.Info.Lowest -> info.lowest
            OudsColorKeyToken.Repository.Info.Medium -> info.medium
            OudsColorKeyToken.Repository.Negative.High -> negative.high
            OudsColorKeyToken.Repository.Negative.Higher -> negative.higher
            OudsColorKeyToken.Repository.Negative.Highest -> negative.highest
            OudsColorKeyToken.Repository.Negative.Low -> negative.low
            OudsColorKeyToken.Repository.Negative.Lower -> negative.lower
            OudsColorKeyToken.Repository.Negative.Lowest -> negative.lowest
            OudsColorKeyToken.Repository.Negative.Medium -> negative.medium
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
            OudsColorKeyToken.Repository.Opacity.Accent.Low -> opacity.accent.low
            OudsColorKeyToken.Repository.Opacity.Accent.Medium -> opacity.accent.medium
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
            OudsColorKeyToken.Repository.Opacity.Info.Low -> opacity.info.low
            OudsColorKeyToken.Repository.Opacity.Info.Medium -> opacity.info.medium
            OudsColorKeyToken.Repository.Opacity.Negative.Low -> opacity.negative.low
            OudsColorKeyToken.Repository.Opacity.Negative.Medium -> opacity.negative.medium
            OudsColorKeyToken.Repository.Opacity.Positive.Low -> opacity.positive.low
            OudsColorKeyToken.Repository.Opacity.Positive.Medium -> opacity.positive.medium
            OudsColorKeyToken.Repository.Opacity.Primary.High -> opacity.primary.high
            OudsColorKeyToken.Repository.Opacity.Primary.Higher -> opacity.primary.higher
            OudsColorKeyToken.Repository.Opacity.Primary.Highest -> opacity.primary.highest
            OudsColorKeyToken.Repository.Opacity.Primary.Low -> opacity.primary.low
            OudsColorKeyToken.Repository.Opacity.Primary.Lower -> opacity.primary.lower
            OudsColorKeyToken.Repository.Opacity.Primary.Lowest -> opacity.primary.lowest
            OudsColorKeyToken.Repository.Opacity.Primary.Medium -> opacity.primary.medium
            OudsColorKeyToken.Repository.Opacity.Primary.Transparent -> opacity.primary.transparent
            OudsColorKeyToken.Repository.Opacity.Warning.Low -> opacity.warning.low
            OudsColorKeyToken.Repository.Opacity.Warning.Medium -> opacity.warning.medium
            OudsColorKeyToken.Repository.Opacity.White.High -> opacity.white.high
            OudsColorKeyToken.Repository.Opacity.White.Higher -> opacity.white.higher
            OudsColorKeyToken.Repository.Opacity.White.Highest -> opacity.white.highest
            OudsColorKeyToken.Repository.Opacity.White.Low -> opacity.white.low
            OudsColorKeyToken.Repository.Opacity.White.Lower -> opacity.white.lower
            OudsColorKeyToken.Repository.Opacity.White.Lowest -> opacity.white.lowest
            OudsColorKeyToken.Repository.Opacity.White.Medium -> opacity.white.medium
            OudsColorKeyToken.Repository.Opacity.White.MediumLow -> opacity.white.mediumLow
            OudsColorKeyToken.Repository.Opacity.White.Transparent -> opacity.white.transparent
            OudsColorKeyToken.Repository.Positive.High -> positive.high
            OudsColorKeyToken.Repository.Positive.Higher -> positive.higher
            OudsColorKeyToken.Repository.Positive.Highest -> positive.highest
            OudsColorKeyToken.Repository.Positive.Low -> positive.low
            OudsColorKeyToken.Repository.Positive.Lower -> positive.lower
            OudsColorKeyToken.Repository.Positive.Lowest -> positive.lowest
            OudsColorKeyToken.Repository.Positive.Medium -> positive.medium
            OudsColorKeyToken.Repository.Primary.High -> primary.high
            OudsColorKeyToken.Repository.Primary.Higher -> primary.higher
            OudsColorKeyToken.Repository.Primary.Highest -> primary.highest
            OudsColorKeyToken.Repository.Primary.Low -> primary.low
            OudsColorKeyToken.Repository.Primary.Lower -> primary.lower
            OudsColorKeyToken.Repository.Primary.Lowest -> primary.lowest
            OudsColorKeyToken.Repository.Primary.Medium -> primary.medium
            OudsColorKeyToken.Repository.Secondary.High -> secondary.high
            OudsColorKeyToken.Repository.Secondary.Higher -> secondary.higher
            OudsColorKeyToken.Repository.Secondary.Highest -> secondary.highest
            OudsColorKeyToken.Repository.Secondary.Low -> secondary.low
            OudsColorKeyToken.Repository.Secondary.Lower -> secondary.lower
            OudsColorKeyToken.Repository.Secondary.Lowest -> secondary.lowest
            OudsColorKeyToken.Repository.Secondary.Medium -> secondary.medium
            OudsColorKeyToken.Repository.Tertiary.High -> tertiary.high
            OudsColorKeyToken.Repository.Tertiary.Higher -> tertiary.higher
            OudsColorKeyToken.Repository.Tertiary.Highest -> tertiary.highest
            OudsColorKeyToken.Repository.Tertiary.Low -> tertiary.low
            OudsColorKeyToken.Repository.Tertiary.Lower -> tertiary.lower
            OudsColorKeyToken.Repository.Tertiary.Lowest -> tertiary.lowest
            OudsColorKeyToken.Repository.Tertiary.Medium -> tertiary.medium
            OudsColorKeyToken.Repository.Warning.High -> warning.high
            OudsColorKeyToken.Repository.Warning.Higher -> warning.higher
            OudsColorKeyToken.Repository.Warning.Highest -> warning.highest
            OudsColorKeyToken.Repository.Warning.Low -> warning.low
            OudsColorKeyToken.Repository.Warning.Lower -> warning.lower
            OudsColorKeyToken.Repository.Warning.Lowest -> warning.lowest
            OudsColorKeyToken.Repository.Warning.Medium -> warning.medium
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
            OudsColorKeyToken.Surface.InverseHigh -> inverseHigh
            OudsColorKeyToken.Surface.InverseLow -> inverseLow
            OudsColorKeyToken.Surface.Primary -> primary
            OudsColorKeyToken.Surface.Secondary -> secondary
            OudsColorKeyToken.Surface.Status.Accent.Emphasized -> status.accent.emphasized
            OudsColorKeyToken.Surface.Status.Accent.Muted -> status.accent.muted
            OudsColorKeyToken.Surface.Status.Info.Emphasized -> status.info.emphasized
            OudsColorKeyToken.Surface.Status.Info.Muted -> status.info.muted
            OudsColorKeyToken.Surface.Status.Negative.Emphasized -> status.negative.emphasized
            OudsColorKeyToken.Surface.Status.Negative.Muted -> status.negative.muted
            OudsColorKeyToken.Surface.Status.Positive.Emphasized -> status.positive.emphasized
            OudsColorKeyToken.Surface.Status.Positive.Muted -> status.positive.muted
            OudsColorKeyToken.Surface.Status.Warning.Emphasized -> status.warning.emphasized
            OudsColorKeyToken.Surface.Status.Warning.Muted -> status.warning.muted
            OudsColorKeyToken.Surface.Tertiary -> tertiary
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
            OudsColorKeyToken.Overlay.Drag -> drag
            OudsColorKeyToken.Overlay.Dropdown -> dropdown
            OudsColorKeyToken.Overlay.Modal -> modal
            OudsColorKeyToken.Overlay.Tooltip -> tooltip
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
        is OudsColorKeyToken.Opacity -> OudsTheme.colorScheme.fromToken(this)
        is OudsColorKeyToken.Overlay -> OudsTheme.colorScheme.fromToken(this)
        is OudsColorKeyToken.Repository -> OudsTheme.colorScheme.fromToken(this)
        is OudsColorKeyToken.Surface -> OudsTheme.colorScheme.fromToken(this)
        is OudsLightDarkColorKeyToken -> if (isOudsInDarkTheme()) dark.value else light.value
        is OudsSingleModeColorKeyToken -> {
            val colorScheme = if (mode == Mode.Dark) LocalDarkColorScheme.current else LocalLightColorScheme.current
            val keyToken = keyToken
            when (keyToken) {
                is OudsColorKeyToken.Action -> colorScheme.fromToken(keyToken)
                is OudsColorKeyToken.Always -> colorScheme.fromToken(keyToken)
                is OudsColorKeyToken.Background -> colorScheme.fromToken(keyToken)
                is OudsColorKeyToken.Border -> colorScheme.fromToken(keyToken)
                is OudsColorKeyToken.Content -> colorScheme.fromToken(keyToken)
                is OudsColorKeyToken.Opacity -> colorScheme.fromToken(keyToken)
                is OudsColorKeyToken.Overlay -> colorScheme.fromToken(keyToken)
                is OudsColorKeyToken.Repository -> colorScheme.fromToken(keyToken)
                is OudsColorKeyToken.Surface -> colorScheme.fromToken(keyToken)
                else -> throw IllegalArgumentException("Unsupported color key token: $keyToken")
            }
        }
    }

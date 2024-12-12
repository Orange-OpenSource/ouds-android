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
import com.orange.ouds.theme.tokens.OudsColorKeyToken.Background
import com.orange.ouds.theme.tokens.android.OudsAndroidColorDarkTokens
import com.orange.ouds.theme.tokens.android.OudsAndroidColorLightTokens
import com.orange.ouds.theme.tokens.android.OudsAndroidColorLightTokens.surfaceVariant
import com.orange.ouds.theme.tokens.semantic.OudsColorSemanticTokens

data class OudsColorScheme(
    val actionColors: Action,
    val alwaysColors: Always,
    val backgroundColors: Background,
    val borderColors: Border,
    val contentColors: Content,
    val decorativeColors: Decorative,
    val opacityColors: Opacity,
    val overlayColors: Overlay,
    val surfaceColors: Surface,
) {

    data class Action(
        val disabled: Color,
        val enabled: Color,
        val focus: Color,
        val highlighted: Color,
        val hover: Color,
        val loading: Color,
        val negativeEnabled: Color,
        val negativeFocus: Color,
        val negativeHover: Color,
        val negativeLoading: Color,
        val negativePressed: Color,
        val pressed: Color,
        val selected: Color,
        val supportEnabled: Color,
        val supportFocus: Color,
        val supportHover: Color,
        val supportLoading: Color,
        val supportPressed: Color,
        val visited: Color,
    )

    data class Always(
        val black: Color,
        val onBlack: Color,
        val onWhite: Color,
        val white: Color,
    )

    data class Background(
        val emphasized: Color,
        val primary: Color,
        val secondary: Color,
        val tertiary: Color,
    )

    data class Border(
        val brandPrimary: Color,
        val default: Color,
        val emphasized: Color,
        val focus: Color,
        val focusInset: Color,
        val onBrandPrimary: Color,
    )

    data class Content(
        val brandPrimary: Color,
        val default: Color,
        val disabled: Color,
        val muted: Color,
        val onActionDisabled: Color,
        val onActionEnabled: Color,
        val onActionFocus: Color,
        val onActionHighlighted: Color,
        val onActionHover: Color,
        val onActionLoading: Color,
        val onActionNegative: Color,
        val onActionPressed: Color,
        val onBrandPrimary: Color,
        val onOverlayEmphasized: Color,
        val onStatusEmphasized: Color,
        val onStatusEmphasizedNeutral: Color,
        val onStatusMuted: Color,
        val statusInfo: Color,
        val statusNegative: Color,
        val statusPositive: Color,
        val statusWarning: Color,
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

    data class Opacity(
        val invisibleBlack: Color,
        val invisibleWhite: Color
    )

    data class Overlay(
        val default: Color,
        val drag: Color,
        val emphasized: Color,
        val modal: Color,
    )

    data class Surface(
        val brandPrimary: Color,
        val statusAccentEmphasized: Color,
        val statusAccentMuted: Color,
        val statusInfoEmphasized: Color,
        val statusInfoMuted: Color,
        val statusNegativeEmphasized: Color,
        val statusNegativeMuted: Color,
        val statusNeutralEmphasized: Color,
        val statusNeutralMuted: Color,
        val statusPositiveEmphasized: Color,
        val statusPositiveMuted: Color,
        val statusWarningEmphasized: Color,
        val statusWarningMuted: Color,
    )

}

val OudsColorSemanticTokens.lightColorScheme: OudsColorScheme
    get() = OudsColorScheme(
        actionColors = with(actionColorTokens) {
            OudsColorScheme.Action(
                disabled = actionDisabledLight,
                negativeEnabled = actionNegativeEnabledLight,
                negativeFocus = actionNegativeFocusLight,
                negativeHover = actionNegativeHoverLight,
                negativeLoading = actionNegativeLoadingLight,
                negativePressed = actionNegativePressedLight,
                enabled = actionEnabledLight,
                focus = actionFocusLight,
                highlighted = actionHighlightedLight,
                hover = actionHoverLight,
                loading = actionLoadingLight,
                pressed = actionPressedLight,
                supportEnabled = actionSupportEnabledLight,
                supportFocus = actionSupportFocusLight,
                supportHover = actionSupportHoverLight,
                supportLoading = actionSupportLoadingLight,
                supportPressed = actionSupportPressedLight,
                selected = actionSelectedLight,
                visited = actionVisitedLight,
            )
        },
        alwaysColors = with(alwaysColorTokens) {
            OudsColorScheme.Always(
                black = alwaysBlackLight,
                onBlack = alwaysOnBlackLight,
                onWhite = alwaysOnWhiteLight,
                white = alwaysWhiteLight,
            )
        },
        backgroundColors = with(backgroundColorTokens) {
            OudsColorScheme.Background(
                emphasized = bgEmphasizedLight,
                primary = bgPrimaryLight,
                secondary = bgSecondaryLight,
                tertiary = bgTertiaryLight,
            )
        },
        borderColors = with(borderColorTokens) {
            OudsColorScheme.Border(
                brandPrimary = borderBrandPrimaryLight,
                default = borderDefaultLight,
                emphasized = borderEmphasizedLight,
                focus = borderFocusLight,
                focusInset = borderFocusInsetLight,
                onBrandPrimary = borderOnBrandPrimaryLight,
            )
        },
        contentColors = with(contentColorTokens) {
            OudsColorScheme.Content(
                brandPrimary = contentBrandPrimaryLight,
                default = contentDefaultLight,
                disabled = contentDisabledLight,
                muted = contentMutedLight,
                onActionDisabled = contentOnActionDisabledLight,
                onActionNegative = contentOnActionNegativeLight,
                onActionEnabled = contentOnActionEnabledLight,
                onActionFocus = contentOnActionFocusLight,
                onActionHighlighted = contentOnActionHighlightedLight,
                onActionHover = contentOnActionHoverLight,
                onActionLoading = contentOnActionLoadingLight,
                onActionPressed = contentOnActionPressedLight,
                onBrandPrimary = contentOnBrandPrimaryLight,
                onOverlayEmphasized = contentOnOverlayEmphasizedLight,
                onStatusEmphasized = contentOnStatusEmphasizedLight,
                onStatusEmphasizedNeutral = contentOnStatusEmphasizedNeutralLight,
                onStatusMuted = contentOnStatusMutedLight,
                statusInfo = contentStatusInfoLight,
                statusNegative = contentStatusNegativeLight,
                statusPositive = contentStatusPositiveLight,
                statusWarning = contentStatusWarningLight,
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
        opacityColors = with(opacityColorTokens) {
            OudsColorScheme.Opacity(
                invisibleBlack = opacityInvisibleBlackLight,
                invisibleWhite = opacityInvisibleWhiteLight,
            )
        },
        overlayColors = with(overlayColorTokens) {
            OudsColorScheme.Overlay(
                default = overlayDefaultLight,
                drag = overlayDragLight,
                emphasized = overlayEmphasizedLight,
                modal = overlayModalLight
            )
        },
        surfaceColors = with(surfaceColorTokens) {
            OudsColorScheme.Surface(
                brandPrimary = surfaceBrandPrimaryLight,
                statusAccentEmphasized = surfaceStatusAccentEmphasizedLight,
                statusAccentMuted = surfaceStatusAccentMutedLight,
                statusInfoEmphasized = surfaceStatusInfoEmphasizedLight,
                statusInfoMuted = surfaceStatusInfoMutedLight,
                statusNegativeEmphasized = surfaceStatusNegativeEmphasizedLight,
                statusNegativeMuted = surfaceStatusNegativeMutedLight,
                statusNeutralEmphasized = surfaceStatusNeutralEmphasizedLight,
                statusNeutralMuted = surfaceStatusNeutralMutedLight,
                statusPositiveEmphasized = surfaceStatusPositiveEmphasizedLight,
                statusPositiveMuted = surfaceStatusPositiveMutedLight,
                statusWarningEmphasized = surfaceStatusWarningEmphasizedLight,
                statusWarningMuted = surfaceStatusWarningMutedLight,
            )
        },
    )

val OudsColorSemanticTokens.darkColorScheme: OudsColorScheme
    get() = OudsColorScheme(
        actionColors = with(actionColorTokens) {
            OudsColorScheme.Action(
                disabled = actionDisabledDark,
                negativeEnabled = actionNegativeEnabledDark,
                negativeFocus = actionNegativeFocusDark,
                negativeHover = actionNegativeHoverDark,
                negativeLoading = actionNegativeLoadingDark,
                negativePressed = actionNegativePressedDark,
                enabled = actionEnabledDark,
                focus = actionFocusDark,
                highlighted = actionHighlightedDark,
                hover = actionHoverDark,
                loading = actionLoadingDark,
                pressed = actionPressedDark,
                supportEnabled = actionSupportEnabledDark,
                supportFocus = actionSupportFocusDark,
                supportHover = actionSupportHoverDark,
                supportLoading = actionSupportLoadingDark,
                supportPressed = actionSupportPressedDark,
                selected = actionSelectedDark,
                visited = actionVisitedDark,
            )
        },
        alwaysColors = with(alwaysColorTokens) {
            OudsColorScheme.Always(
                black = alwaysBlackDark,
                onBlack = alwaysOnBlackDark,
                onWhite = alwaysOnWhiteDark,
                white = alwaysWhiteDark,
            )
        },
        backgroundColors = with(backgroundColorTokens) {
            OudsColorScheme.Background(
                emphasized = bgEmphasizedDark,
                primary = bgPrimaryDark,
                secondary = bgSecondaryDark,
                tertiary = bgTertiaryDark,
            )
        },
        borderColors = with(borderColorTokens) {
            OudsColorScheme.Border(
                brandPrimary = borderBrandPrimaryDark,
                default = borderDefaultDark,
                emphasized = borderEmphasizedDark,
                focus = borderFocusDark,
                focusInset = borderFocusInsetDark,
                onBrandPrimary = borderOnBrandPrimaryDark,
            )
        },
        contentColors = with(contentColorTokens) {
            OudsColorScheme.Content(
                brandPrimary = contentBrandPrimaryDark,
                default = contentDefaultDark,
                disabled = contentDisabledDark,
                muted = contentMutedDark,
                onActionDisabled = contentOnActionDisabledDark,
                onActionNegative = contentOnActionNegativeDark,
                onActionEnabled = contentOnActionEnabledDark,
                onActionFocus = contentOnActionFocusDark,
                onActionHighlighted = contentOnActionHighlightedDark,
                onActionHover = contentOnActionHoverDark,
                onActionLoading = contentOnActionLoadingDark,
                onActionPressed = contentOnActionPressedDark,
                onBrandPrimary = contentOnBrandPrimaryDark,
                onOverlayEmphasized = contentOnOverlayEmphasizedDark,
                onStatusEmphasized = contentOnStatusEmphasizedDark,
                onStatusEmphasizedNeutral = contentOnStatusEmphasizedNeutralDark,
                onStatusMuted = contentOnStatusMutedDark,
                statusInfo = contentStatusInfoDark,
                statusNegative = contentStatusNegativeDark,
                statusPositive = contentStatusPositiveDark,
                statusWarning = contentStatusWarningDark,
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
        opacityColors = with(opacityColorTokens) {
            OudsColorScheme.Opacity(
                invisibleBlack = opacityInvisibleBlackDark,
                invisibleWhite = opacityInvisibleWhiteDark,
            )
        },
        overlayColors = with(overlayColorTokens) {
            OudsColorScheme.Overlay(
                default = overlayDefaultDark,
                drag = overlayDragDark,
                emphasized = overlayEmphasizedDark,
                modal = overlayModalLight
            )
        },
        surfaceColors = with(surfaceColorTokens) {
            OudsColorScheme.Surface(
                brandPrimary = surfaceBrandPrimaryDark,
                statusAccentEmphasized = surfaceStatusAccentEmphasizedDark,
                statusAccentMuted = surfaceStatusAccentMutedDark,
                statusInfoEmphasized = surfaceStatusInfoEmphasizedDark,
                statusInfoMuted = surfaceStatusInfoMutedDark,
                statusNegativeEmphasized = surfaceStatusNegativeEmphasizedDark,
                statusNegativeMuted = surfaceStatusNegativeMutedDark,
                statusNeutralEmphasized = surfaceStatusNeutralEmphasizedDark,
                statusNeutralMuted = surfaceStatusNeutralMutedDark,
                statusPositiveEmphasized = surfaceStatusPositiveEmphasizedDark,
                statusPositiveMuted = surfaceStatusPositiveMutedDark,
                statusWarningEmphasized = surfaceStatusWarningEmphasizedDark,
                statusWarningMuted = surfaceStatusWarningMutedDark,
            )
        },
    )

@Stable
fun OudsColorScheme.fromToken(token: OudsColorKeyToken.Action): Color {
    return with(actionColors) {
        when (token) {
            OudsColorKeyToken.Action.Disabled -> disabled
            OudsColorKeyToken.Action.Enabled -> enabled
            OudsColorKeyToken.Action.Focus -> focus
            OudsColorKeyToken.Action.Highlighted -> highlighted
            OudsColorKeyToken.Action.Hover -> hover
            OudsColorKeyToken.Action.Loading -> loading
            OudsColorKeyToken.Action.Negative.Enabled -> negativeEnabled
            OudsColorKeyToken.Action.Negative.Focus -> negativeFocus
            OudsColorKeyToken.Action.Negative.Hover -> negativeHover
            OudsColorKeyToken.Action.Negative.Loading -> negativeLoading
            OudsColorKeyToken.Action.Negative.Pressed -> negativePressed
            OudsColorKeyToken.Action.Pressed -> pressed
            OudsColorKeyToken.Action.Selected -> selected
            OudsColorKeyToken.Action.Support.Enabled -> supportEnabled
            OudsColorKeyToken.Action.Support.Focus -> supportFocus
            OudsColorKeyToken.Action.Support.Hover -> supportHover
            OudsColorKeyToken.Action.Support.Loading -> supportLoading
            OudsColorKeyToken.Action.Support.Pressed -> supportPressed
            OudsColorKeyToken.Action.Visited -> visited
        }
    }
}

@Stable
fun OudsColorScheme.fromToken(token: OudsColorKeyToken.Always): Color {
    return with(alwaysColors) {
        when (token) {
            OudsColorKeyToken.Always.Black -> black
            OudsColorKeyToken.Always.OnBlack -> onBlack
            OudsColorKeyToken.Always.OnWhite -> onWhite
            OudsColorKeyToken.Always.White -> white
        }
    }
}

@Stable
fun OudsColorScheme.fromToken(token: OudsColorKeyToken.Background): Color {
    return with(backgroundColors) {
        when (token) {
            OudsColorKeyToken.Background.Emphasized -> emphasized
            OudsColorKeyToken.Background.Primary -> primary
            OudsColorKeyToken.Background.Secondary -> secondary
            OudsColorKeyToken.Background.Tertiary -> tertiary
        }
    }
}

@Stable
fun OudsColorScheme.fromToken(token: OudsColorKeyToken.Border): Color {
    return with(borderColors) {
        when (token) {
            OudsColorKeyToken.Border.BrandPrimary -> brandPrimary
            OudsColorKeyToken.Border.Default -> default
            OudsColorKeyToken.Border.Emphasized -> emphasized
            OudsColorKeyToken.Border.Focus -> focus
            OudsColorKeyToken.Border.FocusInset -> focusInset
            OudsColorKeyToken.Border.OnBrand.Primary -> onBrandPrimary
        }
    }
}

@Stable
fun OudsColorScheme.fromToken(token: OudsColorKeyToken.Content): Color {
    return with(contentColors) {
        when (token) {
            OudsColorKeyToken.Content.BrandPrimary -> brandPrimary
            OudsColorKeyToken.Content.Default -> default
            OudsColorKeyToken.Content.Disabled -> disabled
            OudsColorKeyToken.Content.Muted -> muted
            OudsColorKeyToken.Content.OnAction.Disabled -> onActionDisabled
            OudsColorKeyToken.Content.OnAction.Negative -> onActionNegative
            OudsColorKeyToken.Content.OnAction.Enabled -> onActionEnabled
            OudsColorKeyToken.Content.OnAction.Focus -> onActionFocus
            OudsColorKeyToken.Content.OnAction.Highlighted -> onActionHighlighted
            OudsColorKeyToken.Content.OnAction.Hover -> onActionHover
            OudsColorKeyToken.Content.OnAction.Loading -> onActionLoading
            OudsColorKeyToken.Content.OnAction.Pressed -> onActionPressed
            OudsColorKeyToken.Content.OnBrand.Primary -> onBrandPrimary
            OudsColorKeyToken.Content.OnOverlay.Emphasized -> onOverlayEmphasized
            OudsColorKeyToken.Content.OnStatus.Emphasized -> onStatusEmphasized
            OudsColorKeyToken.Content.OnStatus.EmphasizedNeutral -> onStatusEmphasizedNeutral
            OudsColorKeyToken.Content.OnStatus.Muted -> onStatusMuted
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
            OudsColorKeyToken.Decorative.Accent1.Default -> accent1Default
            OudsColorKeyToken.Decorative.Accent1.Emphasized -> accent1Emphasized
            OudsColorKeyToken.Decorative.Accent1.Muted -> accent1Muted
            OudsColorKeyToken.Decorative.Accent2.Default -> accent2Default
            OudsColorKeyToken.Decorative.Accent2.Emphasized -> accent2Emphasized
            OudsColorKeyToken.Decorative.Accent2.Muted -> accent2Muted
            OudsColorKeyToken.Decorative.Accent3.Default -> accent3Default
            OudsColorKeyToken.Decorative.Accent3.Emphasized -> accent3Emphasized
            OudsColorKeyToken.Decorative.Accent3.Muted -> accent3Muted
            OudsColorKeyToken.Decorative.Accent4.Default -> accent4Default
            OudsColorKeyToken.Decorative.Accent4.Emphasized -> accent4Emphasized
            OudsColorKeyToken.Decorative.Accent4.Muted -> accent4Muted
            OudsColorKeyToken.Decorative.Accent5.Default -> accent5Default
            OudsColorKeyToken.Decorative.Accent5.Emphasized -> accent5Emphasized
            OudsColorKeyToken.Decorative.Accent5.Muted -> accent5Muted
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
fun OudsColorScheme.fromToken(token: OudsColorKeyToken.Surface): Color {
    return with(surfaceColors) {
        when (token) {
            OudsColorKeyToken.Surface.Brand.Primary -> brandPrimary
            OudsColorKeyToken.Surface.Status.Accent.Emphasized -> statusAccentEmphasized
            OudsColorKeyToken.Surface.Status.Accent.Muted -> statusAccentMuted
            OudsColorKeyToken.Surface.Status.Info.Emphasized -> statusInfoEmphasized
            OudsColorKeyToken.Surface.Status.Info.Muted -> statusInfoMuted
            OudsColorKeyToken.Surface.Status.Negative.Emphasized -> statusNegativeEmphasized
            OudsColorKeyToken.Surface.Status.Negative.Muted -> statusNegativeMuted
            OudsColorKeyToken.Surface.Status.Neutral.Emphasized -> statusNeutralEmphasized
            OudsColorKeyToken.Surface.Status.Neutral.Muted -> statusNeutralMuted
            OudsColorKeyToken.Surface.Status.Positive.Emphasized -> statusPositiveEmphasized
            OudsColorKeyToken.Surface.Status.Positive.Muted -> statusPositiveMuted
            OudsColorKeyToken.Surface.Status.Warning.Emphasized -> statusWarningEmphasized
            OudsColorKeyToken.Surface.Status.Warning.Muted -> statusWarningMuted
        }
    }
}

@Stable
fun OudsColorScheme.fromToken(token: OudsColorKeyToken.Opacity): Color {
    return when (token) {
        OudsColorKeyToken.Opacity.Invisible.Black -> opacityColors.invisibleBlack
        OudsColorKeyToken.Opacity.Invisible.White -> opacityColors.invisibleWhite
    }
}


@Stable
fun OudsColorScheme.fromToken(token: OudsColorKeyToken.Overlay): Color {
    return when (token) {
        OudsColorKeyToken.Overlay.Default -> overlayColors.default
        OudsColorKeyToken.Overlay.Drag -> overlayColors.drag
        OudsColorKeyToken.Overlay.Emphasized -> overlayColors.emphasized
        OudsColorKeyToken.Overlay.Modal -> overlayColors.modal
    }
}

//TODO Material colors must be able to be overridden by a theme
val materialLightColorScheme: ColorScheme
    get() = with(OudsAndroidColorLightTokens) {
        lightColorScheme(
            primary = primary,
            onPrimary = onPrimary,
            primaryContainer = primaryContainer,
            onPrimaryContainer = onPrimaryContainer,
            inversePrimary = inversePrimary,
            secondary = secondary,
            onSecondary = onSecondary,
            secondaryContainer = secondaryContainer,
            onSecondaryContainer = onSecondaryContainer,
            tertiary = tertiary,
            onTertiary = onTertiary,
            tertiaryContainer = tertiaryContainer,
            onTertiaryContainer = onTertiaryContainer,
            background = background,
            onBackground = onBackground,
            surface = surface,
            onSurface = onSurface,
            surfaceDim = surfaceDim,
            surfaceVariant = surfaceVariant,
            onSurfaceVariant = onSurfaceVariant,
            surfaceTint = surfaceTint,
            inverseSurface = inverseSurface,
            inverseOnSurface = inverseOnSurface,
            error = error,
            onError = onError,
            errorContainer = errorContainer,
            onErrorContainer = onErrorContainer,
            outline = outline,
            outlineVariant = outlineVariant,
            scrim = scrim,
            surfaceBright = surfaceBright,
            surfaceContainer = surfaceContainer,
            surfaceContainerHighest = surfaceContainerHighest,
            surfaceContainerHigh = surfaceContainerHigh,
            surfaceContainerLow = surfaceContainerLow,
            surfaceContainerLowest = surfaceContainerLowest,
        )
    }

val materialDarkColorScheme: ColorScheme
    get() = with(OudsAndroidColorDarkTokens) {
        darkColorScheme(
            primary = primary,
            onPrimary = onPrimary,
            primaryContainer = primaryContainer,
            onPrimaryContainer = onPrimaryContainer,
            inversePrimary = inversePrimary,
            secondary = secondary,
            onSecondary = onSecondary,
            secondaryContainer = secondaryContainer,
            onSecondaryContainer = onSecondaryContainer,
            tertiary = tertiary,
            onTertiary = onTertiary,
            tertiaryContainer = tertiaryContainer,
            onTertiaryContainer = onTertiaryContainer,
            background = background,
            onBackground = onBackground,
            surface = surface,
            onSurface = onSurface,
            surfaceDim = surfaceDim,
            surfaceVariant = surfaceVariant,
            onSurfaceVariant = onSurfaceVariant,
            surfaceTint = surfaceTint,
            inverseSurface = inverseSurface,
            inverseOnSurface = inverseOnSurface,
            error = error,
            onError = onError,
            errorContainer = errorContainer,
            onErrorContainer = onErrorContainer,
            outline = outline,
            outlineVariant = outlineVariant,
            scrim = scrim,
            surfaceBright = surfaceBright,
            surfaceContainer = surfaceContainer,
            surfaceContainerHighest = surfaceContainerHighest,
            surfaceContainerHigh = surfaceContainerHigh,
            surfaceContainerLow = surfaceContainerLow,
            surfaceContainerLowest = surfaceContainerLowest,
        )
    }

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
        is OudsColorKeyToken.Content -> OudsTheme.colorScheme.fromToken(this)
        is OudsColorKeyToken.Decorative -> OudsTheme.colorScheme.fromToken(this)
        is OudsColorKeyToken.Opacity -> OudsTheme.colorScheme.fromToken(this)
        is OudsColorKeyToken.Overlay -> OudsTheme.colorScheme.fromToken(this)
        is OudsColorKeyToken.Surface -> OudsTheme.colorScheme.fromToken(this)
    }
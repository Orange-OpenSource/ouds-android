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

package com.orange.ouds.theme.whitelabel

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import com.orange.ouds.theme.tokens.components.OudsLightDarkColor
import com.orange.ouds.theme.whitelabel.tokens.semantic.WhiteLabelColorActionSemanticTokens
import com.orange.ouds.theme.whitelabel.tokens.semantic.WhiteLabelColorAiSemanticTokens
import com.orange.ouds.theme.whitelabel.tokens.semantic.WhiteLabelColorAlwaysSemanticTokens
import com.orange.ouds.theme.whitelabel.tokens.semantic.WhiteLabelColorBgSemanticTokens
import com.orange.ouds.theme.whitelabel.tokens.semantic.WhiteLabelColorBorderSemanticTokens
import com.orange.ouds.theme.whitelabel.tokens.semantic.WhiteLabelColorContentSemanticTokens
import com.orange.ouds.theme.whitelabel.tokens.semantic.WhiteLabelColorOpacitySemanticTokens
import com.orange.ouds.theme.whitelabel.tokens.semantic.WhiteLabelColorOverlaySemanticTokens
import com.orange.ouds.theme.whitelabel.tokens.semantic.WhiteLabelColorRepositorySemanticTokens
import com.orange.ouds.theme.whitelabel.tokens.semantic.WhiteLabelColorSemanticTokens
import com.orange.ouds.theme.whitelabel.tokens.semantic.WhiteLabelColorSurfaceSemanticTokens
import com.orange.ouds.theme.wireframe.tokens.semantic.WireframeColorActionSemanticTokens
import com.orange.ouds.theme.wireframe.tokens.semantic.WireframeColorAiSemanticTokens
import com.orange.ouds.theme.wireframe.tokens.semantic.WireframeColorAlwaysSemanticTokens
import com.orange.ouds.theme.wireframe.tokens.semantic.WireframeColorBgSemanticTokens
import com.orange.ouds.theme.wireframe.tokens.semantic.WireframeColorBorderSemanticTokens
import com.orange.ouds.theme.wireframe.tokens.semantic.WireframeColorContentSemanticTokens
import com.orange.ouds.theme.wireframe.tokens.semantic.WireframeColorOpacitySemanticTokens
import com.orange.ouds.theme.wireframe.tokens.semantic.WireframeColorOverlaySemanticTokens
import com.orange.ouds.theme.wireframe.tokens.semantic.WireframeColorRepositorySemanticTokens
import com.orange.ouds.theme.wireframe.tokens.semantic.WireframeColorSemanticTokens
import com.orange.ouds.theme.wireframe.tokens.semantic.WireframeColorSurfaceSemanticTokens

/**
 * Customizable color scheme for White Label theme.
 *
 * This class allows customization of semantic colors used throughout OUDS components.
 * Any unspecified color (set to `OudsLightDarkColor.Unspecified` or `Color.Unspecified`)
 * will automatically fall back to the corresponding WireframeTheme color.
 *
 * ## Color Categories
 *
 * The color scheme is organized into these categories:
 * - `action` - Interactive states (enabled, hover, pressed, focus, loading, disabled, selected)
 * - `ai` - AI-specific feature colors
 * - `always` - Theme-invariant colors (black, white, onBlack, onWhite)
 * - `background` - Background surfaces (primary, secondary, tertiary, inverse)
 * - `border` - Borders and outlines (default, emphasized, brand, status, focus)
 * - `content` - Text and icons (default, muted, brand, status, onAction, onBrand, onStatus)
 * - `opacity` - Transparent overlays
 * - `overlay` - Overlay surfaces (backdrop, dropdown, tooltip, modal, floating)
 * - `repository` - Raw color repository for building custom semantic tokens
 * - `surface` - Container surfaces (brand, status)
 *
 * ## Usage Example
 *
 * ```
 * val customColorScheme = WhiteLabelColorScheme(
 *     action = WhiteLabelColorScheme.Action(
 *         enabled = OudsLightDarkColor(Color(0xFF1B5E20), Color(0xFF81C784)),
 *         pressed = OudsLightDarkColor(Color(0xFF0D3D11), Color(0xFFC8E6C9)),
 *         hover = OudsLightDarkColor(Color(0xFF2E7D32), Color(0xFF66BB6A))
 *     ),
 *     content = WhiteLabelColorScheme.Content(
 *         brandPrimary = OudsLightDarkColor(Color(0xFF1B5E20), Color(0xFF81C784)),
 *         onBrand = WhiteLabelColorScheme.Content.OnBrand(
 *             primary = OudsLightDarkColor(Color.White, Color.Black)
 *         )
 *     ),
 *     surface = WhiteLabelColorScheme.Surface(
 *         brand = WhiteLabelColorScheme.Surface.Brand(
 *             primary = OudsLightDarkColor(Color(0xFF1B5E20), Color(0xFF81C784))
 *         )
 *     )
 * )
 *
 * OudsTheme(theme = WhiteLabelTheme(colorScheme = customColorScheme)) {
 *     // Your branded app UI
 * }
 * ```
 *
 * @param action Action colors for interactive states.
 * @param ai AI-specific feature colors.
 * @param always Colors that remain constant regardless of theme mode.
 * @param background Background colors for surfaces.
 * @param border Border and outline colors.
 * @param content Content colors for text and icons.
 * @param opacity Opacity and transparency colors.
 * @param overlay Overlay surface colors.
 * @param repository Raw color repository.
 * @param surface Surface colors for containers.
 */
class WhiteLabelColorScheme(
    val action: Action = Action(),
    val ai: Ai = Ai(),
    val always: Always = Always(),
    val background: Background = Background(),
    val border: Border = Border(),
    val content: Content = Content(),
    val opacity: Opacity = Opacity(),
    val overlay: Overlay = Overlay(),
    val repository: Repository = Repository(),
    val surface: Surface = Surface(),
) {

    /**
     * Action colors for interactive states like buttons, links, and selections.
     *
     * @param disabled Color for disabled interactive elements.
     * @param enabled Color for enabled interactive elements.
     * @param focus Color for focused interactive elements.
     * @param highlighted Color for highlighted interactive elements.
     * @param hover Color for interactive elements on hover.
     * @param loading Color for interactive elements in loading state.
     * @param negative Negative action colors (e.g., destructive actions).
     * @param pressed Color for pressed interactive elements.
     * @param readOnly Read-only action colors.
     * @param selected Color for selected interactive elements.
     * @param support Support action colors.
     * @param visited Color for visited links.
     */
    class Action(
        val disabled: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
        val enabled: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
        val focus: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
        val highlighted: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
        val hover: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
        val loading: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
        val negative: Negative = Negative(),
        val pressed: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
        val readOnly: ReadOnly = ReadOnly(),
        val selected: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
        val support: Support = Support(),
        val visited: OudsLightDarkColor = OudsLightDarkColor.Unspecified
    ) {

        /**
         * Negative action colors for destructive or dangerous actions.
         *
         * @param enabled Color for enabled negative actions.
         * @param focus Color for focused negative actions.
         * @param hover Color for negative actions on hover.
         * @param loading Color for negative actions in loading state.
         * @param pressed Color for pressed negative actions.
         */
        class Negative(
            val enabled: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
            val focus: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
            val hover: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
            val loading: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
            val pressed: OudsLightDarkColor = OudsLightDarkColor.Unspecified
        )

        /**
         * Read-only action colors for non-interactive informational elements.
         *
         * @param primary Primary read-only action color.
         * @param secondary Secondary read-only action color.
         */
        class ReadOnly(
            val primary: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
            val secondary: OudsLightDarkColor = OudsLightDarkColor.Unspecified
        )

        /**
         * Support action colors for secondary or supporting actions.
         *
         * @param disabled Color for disabled support actions.
         * @param enabled Color for enabled support actions.
         * @param focus Color for focused support actions.
         * @param hover Color for support actions on hover.
         * @param loading Color for support actions in loading state.
         * @param pressed Color for pressed support actions.
         */
        class Support(
            val disabled: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
            val enabled: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
            val focus: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
            val hover: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
            val loading: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
            val pressed: OudsLightDarkColor = OudsLightDarkColor.Unspecified
        )
    }

    /**
     * AI-specific feature colors for AI-powered components.
     *
     * @param primary Primary AI color.
     * @param secondary Secondary AI color.
     * @param tertiary Tertiary AI color.
     * @param quaternary Quaternary AI color.
     */
    class Ai(
        val primary: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
        val secondary: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
        val tertiary: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
        val quaternary: OudsLightDarkColor = OudsLightDarkColor.Unspecified
    )

    /**
     * Colors that remain constant regardless of theme mode.
     *
     * @param black Pure black color.
     * @param onBlack Color for content on black backgrounds.
     * @param onWhite Color for content on white backgrounds.
     * @param white Pure white color.
     */
    class Always(
        val black: Color = Color.Unspecified,
        val onBlack: Color = Color.Unspecified,
        val onWhite: Color = Color.Unspecified,
        val white: Color = Color.Unspecified
    )

    /**
     * Background colors for surfaces at different elevations.
     *
     * @param inverseHigh High-emphasis inverse background.
     * @param inverseLow Low-emphasis inverse background.
     * @param primary Primary background color.
     * @param secondary Secondary background color.
     * @param tertiary Tertiary background color.
     */
    class Background(
        val inverseHigh: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
        val inverseLow: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
        val primary: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
        val secondary: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
        val tertiary: OudsLightDarkColor = OudsLightDarkColor.Unspecified
    )

    /**
     * Border and outline colors for component edges and focus indicators.
     *
     * @param brandPrimary Primary brand border color.
     * @param brandSecondary Secondary brand border color.
     * @param brandTertiary Tertiary brand border color.
     * @param default Default border color.
     * @param emphasized Emphasized border color.
     * @param focus Focus ring color.
     * @param focusInset Inset focus ring color.
     * @param minimal Minimal border color.
     * @param muted Muted border color.
     * @param onBrand Border colors for brand surfaces.
     * @param status Status-related border colors.
     */
    class Border(
        val brandPrimary: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
        val brandSecondary: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
        val brandTertiary: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
        val default: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
        val emphasized: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
        val focus: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
        val focusInset: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
        val minimal: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
        val muted: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
        val onBrand: OnBrand = OnBrand(),
        val status: Status = Status()
    ) {

        /**
         * Border colors for content displayed on brand surfaces.
         *
         * @param primary Primary border color on brand surfaces.
         * @param secondary Secondary border color on brand surfaces.
         * @param tertiary Tertiary border color on brand surfaces.
         */
        class OnBrand(
            val primary: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
            val secondary: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
            val tertiary: OudsLightDarkColor = OudsLightDarkColor.Unspecified
        )

        /**
         * Status-related border colors.
         *
         * @param accent Accent status border color.
         * @param info Info status border color.
         * @param negative Negative/error status border color.
         * @param positive Positive/success status border color.
         * @param warning Warning status border color.
         */
        class Status(
            val accent: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
            val info: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
            val negative: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
            val positive: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
            val warning: OudsLightDarkColor = OudsLightDarkColor.Unspecified
        )
    }

    /**
     * Content colors for text, icons, and graphical elements.
     *
     * @param brandPrimary Primary brand content color.
     * @param brandSecondary Secondary brand content color.
     * @param brandTertiary Tertiary brand content color.
     * @param default Default content color.
     * @param disabled Disabled content color.
     * @param inverse Inverse content color.
     * @param muted Muted content color.
     * @param onAction Content colors for action surfaces.
     * @param onBrand Content colors for brand surfaces.
     * @param onStatus Content colors for status surfaces.
     * @param status Status-related content colors.
     */
    class Content(
        val brandPrimary: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
        val brandSecondary: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
        val brandTertiary: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
        val default: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
        val disabled: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
        val inverse: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
        val muted: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
        val onAction: OnAction = OnAction(),
        val onBrand: OnBrand = OnBrand(),
        val onStatus: OnStatus = OnStatus(),
        val status: Status = Status()
    ) {

        /**
         * Content colors for text and icons on action surfaces.
         *
         * @param disabled Content color for disabled action surfaces.
         * @param enabled Content color for enabled action surfaces.
         * @param focus Content color for focused action surfaces.
         * @param highlighted Content color for highlighted action surfaces.
         * @param hover Content color for action surfaces on hover.
         * @param loading Content color for action surfaces in loading state.
         * @param pressed Content color for pressed action surfaces.
         * @param selected Content color for selected action surfaces.
         */
        class OnAction(
            val disabled: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
            val enabled: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
            val focus: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
            val highlighted: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
            val hover: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
            val loading: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
            val pressed: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
            val selected: OudsLightDarkColor = OudsLightDarkColor.Unspecified
        )

        /**
         * Content colors for text and icons on brand surfaces.
         *
         * @param primary Primary content color on brand surfaces.
         * @param secondary Secondary content color on brand surfaces.
         * @param tertiary Tertiary content color on brand surfaces.
         */
        class OnBrand(
            val primary: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
            val secondary: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
            val tertiary: OudsLightDarkColor = OudsLightDarkColor.Unspecified
        )

        /**
         * Content colors for text and icons on status surfaces.
         *
         * @param positive Content colors for positive/success status surfaces.
         * @param info Content colors for info status surfaces.
         * @param warning Content colors for warning status surfaces.
         * @param negative Content colors for negative/error status surfaces.
         * @param accent Content colors for accent status surfaces.
         */
        class OnStatus(
            val positive: Positive = Positive(),
            val info: Info = Info(),
            val warning: Warning = Warning(),
            val negative: Negative = Negative(),
            val accent: Accent = Accent()
        ) {

            /**
             * Content colors for positive/success status surfaces.
             *
             * @param emphasized Emphasized content on positive surfaces.
             * @param muted Muted content on positive surfaces.
             */
            class Positive(
                val emphasized: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
                val muted: OudsLightDarkColor = OudsLightDarkColor.Unspecified
            )

            /**
             * Content colors for info status surfaces.
             *
             * @param emphasized Emphasized content on info surfaces.
             * @param muted Muted content on info surfaces.
             */
            class Info(
                val emphasized: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
                val muted: OudsLightDarkColor = OudsLightDarkColor.Unspecified
            )

            /**
             * Content colors for warning status surfaces.
             *
             * @param emphasized Emphasized content on warning surfaces.
             * @param muted Muted content on warning surfaces.
             */
            class Warning(
                val emphasized: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
                val muted: OudsLightDarkColor = OudsLightDarkColor.Unspecified
            )

            /**
             * Content colors for negative/error status surfaces.
             *
             * @param emphasized Emphasized content on negative surfaces.
             * @param muted Muted content on negative surfaces.
             */
            class Negative(
                val emphasized: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
                val muted: OudsLightDarkColor = OudsLightDarkColor.Unspecified
            )

            /**
             * Content colors for accent status surfaces.
             *
             * @param emphasized Emphasized content on accent surfaces.
             * @param muted Muted content on accent surfaces.
             */
            class Accent(
                val emphasized: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
                val muted: OudsLightDarkColor = OudsLightDarkColor.Unspecified
            )
        }

        /**
         * Status-related content colors.
         *
         * @param accent Accent status content color.
         * @param info Info status content color.
         * @param inverse Inverse status content colors.
         * @param negative Negative/error status content color.
         * @param positive Positive/success status content color.
         * @param warning Warning status content color.
         */
        class Status(
            val accent: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
            val info: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
            val inverse: Inverse = Inverse(),
            val negative: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
            val positive: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
            val warning: OudsLightDarkColor = OudsLightDarkColor.Unspecified
        ) {

            /**
             * Inverse status content colors.
             *
             * @param info Inverse info status content color.
             * @param negative Inverse negative status content color.
             * @param positive Inverse positive status content color.
             * @param warning Inverse warning status content color.
             */
            class Inverse(
                val info: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
                val negative: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
                val positive: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
                val warning: OudsLightDarkColor = OudsLightDarkColor.Unspecified
            )
        }
    }

    /**
     * Opacity and transparency colors for overlays and scrim effects.
     *
     * @param transparent Transparent color.
     * @param transparentInverse Inverse transparent color.
     * @param lowest Lowest opacity level.
     * @param lower Lower opacity level.
     */
    class Opacity(
        val transparent: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
        val transparentInverse: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
        val lowest: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
        val lower: OudsLightDarkColor = OudsLightDarkColor.Unspecified
    )

    /**
     * Overlay surface colors for elevated UI elements.
     *
     * @param backdrop Backdrop overlay color.
     * @param dropdown Dropdown overlay color.
     * @param drag Drag overlay color.
     * @param floating Floating overlay color.
     * @param modalSheet Modal sheet overlay color.
     * @param tooltip Tooltip overlay color.
     */
    class Overlay(
        val backdrop: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
        val dropdown: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
        val drag: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
        val floating: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
        val modalSheet: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
        val tooltip: OudsLightDarkColor = OudsLightDarkColor.Unspecified
    )

    /**
     * Raw color repository providing foundational color values.
     *
     * @param accent Accent color palette.
     * @param info Info color palette.
     * @param negative Negative/error color palette.
     * @param neutral Neutral color palette.
     * @param opacity Opacity color variants.
     * @param positive Positive/success color palette.
     * @param primary Primary color palette.
     * @param secondary Secondary color palette.
     * @param tertiary Tertiary color palette.
     * @param warning Warning color palette.
     */
    class Repository(
        val accent: Accent = Accent(),
        val info: Info = Info(),
        val negative: Negative = Negative(),
        val neutral: Neutral = Neutral(),
        val opacity: Opacity = Opacity(),
        val positive: Positive = Positive(),
        val primary: Primary = Primary(),
        val secondary: Secondary = Secondary(),
        val tertiary: Tertiary = Tertiary(),
        val warning: Warning = Warning()
    ) {

        /**
         * Accent color palette with intensity variants.
         *
         * @param high High intensity accent color.
         * @param higher Higher intensity accent color.
         * @param highest Highest intensity accent color.
         * @param low Low intensity accent color.
         * @param lower Lower intensity accent color.
         * @param lowest Lowest intensity accent color.
         * @param medium Medium intensity accent color.
         */
        class Accent(
            val high: Color = Color.Unspecified,
            val higher: Color = Color.Unspecified,
            val highest: Color = Color.Unspecified,
            val low: Color = Color.Unspecified,
            val lower: Color = Color.Unspecified,
            val lowest: Color = Color.Unspecified,
            val medium: Color = Color.Unspecified
        )

        /**
         * Info color palette with intensity variants.
         *
         * @param high High intensity info color.
         * @param higher Higher intensity info color.
         * @param highest Highest intensity info color.
         * @param low Low intensity info color.
         * @param lower Lower intensity info color.
         * @param lowest Lowest intensity info color.
         * @param medium Medium intensity info color.
         */
        class Info(
            val high: Color = Color.Unspecified,
            val higher: Color = Color.Unspecified,
            val highest: Color = Color.Unspecified,
            val low: Color = Color.Unspecified,
            val lower: Color = Color.Unspecified,
            val lowest: Color = Color.Unspecified,
            val medium: Color = Color.Unspecified
        )

        /**
         * Negative/error color palette with intensity variants.
         *
         * @param high High intensity negative color.
         * @param higher Higher intensity negative color.
         * @param highest Highest intensity negative color.
         * @param low Low intensity negative color.
         * @param lower Lower intensity negative color.
         * @param lowest Lowest intensity negative color.
         * @param medium Medium intensity negative color.
         */
        class Negative(
            val high: Color = Color.Unspecified,
            val higher: Color = Color.Unspecified,
            val highest: Color = Color.Unspecified,
            val low: Color = Color.Unspecified,
            val lower: Color = Color.Unspecified,
            val lowest: Color = Color.Unspecified,
            val medium: Color = Color.Unspecified
        )

        /**
         * Neutral color palette with emphasized and muted variants.
         *
         * @param emphasized Emphasized neutral colors.
         * @param muted Muted neutral colors.
         */
        class Neutral(
            val emphasized: Emphasized = Emphasized(),
            val muted: Muted = Muted()
        ) {

            /**
             * Emphasized neutral colors.
             *
             * @param black Black emphasized neutral color.
             * @param high High intensity emphasized neutral color.
             * @param higher Higher intensity emphasized neutral color.
             * @param highest Highest intensity emphasized neutral color.
             * @param low Low intensity emphasized neutral color.
             * @param lower Lower intensity emphasized neutral color.
             * @param lowest Lowest intensity emphasized neutral color.
             * @param medium Medium intensity emphasized neutral color.
             */
            class Emphasized(
                val black: Color = Color.Unspecified,
                val high: Color = Color.Unspecified,
                val higher: Color = Color.Unspecified,
                val highest: Color = Color.Unspecified,
                val low: Color = Color.Unspecified,
                val lower: Color = Color.Unspecified,
                val lowest: Color = Color.Unspecified,
                val medium: Color = Color.Unspecified
            )

            /**
             * Muted neutral colors.
             *
             * @param high High intensity muted neutral color.
             * @param higher Higher intensity muted neutral color.
             * @param highest Highest intensity muted neutral color.
             * @param low Low intensity muted neutral color.
             * @param lower Lower intensity muted neutral color.
             * @param lowest Lowest intensity muted neutral color.
             * @param medium Medium intensity muted neutral color.
             * @param white White muted neutral color.
             */
            class Muted(
                val high: Color = Color.Unspecified,
                val higher: Color = Color.Unspecified,
                val highest: Color = Color.Unspecified,
                val low: Color = Color.Unspecified,
                val lower: Color = Color.Unspecified,
                val lowest: Color = Color.Unspecified,
                val medium: Color = Color.Unspecified,
                val white: Color = Color.Unspecified
            )
        }

        /**
         * Opacity color variants for semi-transparent effects.
         *
         * @param accent Opacity variants for accent colors.
         * @param black Opacity variants for black.
         * @param info Opacity variants for info colors.
         * @param negative Opacity variants for negative colors.
         * @param positive Opacity variants for positive colors.
         * @param primary Opacity variants for primary colors.
         * @param warning Opacity variants for warning colors.
         * @param white Opacity variants for white.
         */
        class Opacity(
            val accent: Accent = Accent(),
            val black: Black = Black(),
            val info: Info = Info(),
            val negative: Negative = Negative(),
            val positive: Positive = Positive(),
            val primary: Primary = Primary(),
            val warning: Warning = Warning(),
            val white: White = White()
        ) {

            /**
             * Opacity variants for accent colors.
             *
             * @param low Low opacity accent.
             * @param medium Medium opacity accent.
             */
            class Accent(
                val low: Color = Color.Unspecified,
                val medium: Color = Color.Unspecified,
            )

            /**
             * Opacity variants for black.
             *
             * @param high High opacity black.
             * @param higher Higher opacity black.
             * @param highest Highest opacity black.
             * @param low Low opacity black.
             * @param lower Lower opacity black.
             * @param lowest Lowest opacity black.
             * @param medium Medium opacity black.
             * @param mediumHigh Medium-high opacity black.
             * @param mediumLow Medium-low opacity black.
             * @param transparent Transparent black.
             */
            class Black(
                val high: Color = Color.Unspecified,
                val higher: Color = Color.Unspecified,
                val highest: Color = Color.Unspecified,
                val low: Color = Color.Unspecified,
                val lower: Color = Color.Unspecified,
                val lowest: Color = Color.Unspecified,
                val medium: Color = Color.Unspecified,
                val mediumHigh: Color = Color.Unspecified,
                val mediumLow: Color = Color.Unspecified,
                val transparent: Color = Color.Unspecified
            )

            /**
             * Opacity variants for info colors.
             *
             * @param low Low opacity info.
             * @param medium Medium opacity info.
             */
            class Info(
                val low: Color = Color.Unspecified,
                val medium: Color = Color.Unspecified,
            )

            /**
             * Opacity variants for negative colors.
             *
             * @param low Low opacity negative.
             * @param medium Medium opacity negative.
             */
            class Negative(
                val low: Color = Color.Unspecified,
                val medium: Color = Color.Unspecified,
            )

            /**
             * Opacity variants for positive colors.
             *
             * @param low Low opacity positive.
             * @param medium Medium opacity positive.
             */
            class Positive(
                val low: Color = Color.Unspecified,
                val medium: Color = Color.Unspecified,
            )

            /**
             * Opacity variants for primary colors.
             *
             * @param high High opacity primary.
             * @param higher Higher opacity primary.
             * @param highest Highest opacity primary.
             * @param low Low opacity primary.
             * @param lower Lower opacity primary.
             * @param lowest Lowest opacity primary.
             * @param medium Medium opacity primary.
             * @param transparent Transparent primary.
             */
            class Primary(
                val high: Color = Color.Unspecified,
                val higher: Color = Color.Unspecified,
                val highest: Color = Color.Unspecified,
                val low: Color = Color.Unspecified,
                val lower: Color = Color.Unspecified,
                val lowest: Color = Color.Unspecified,
                val medium: Color = Color.Unspecified,
                val transparent: Color = Color.Unspecified
            )

            /**
             * Opacity variants for warning colors.
             *
             * @param low Low opacity warning.
             * @param medium Medium opacity warning.
             */
            class Warning(
                val low: Color = Color.Unspecified,
                val medium: Color = Color.Unspecified,
            )

            /**
             * Opacity variants for white.
             *
             * @param high High opacity white.
             * @param higher Higher opacity white.
             * @param highest Highest opacity white.
             * @param low Low opacity white.
             * @param lower Lower opacity white.
             * @param lowest Lowest opacity white.
             * @param medium Medium opacity white.
             * @param mediumLow Medium-low opacity white.
             * @param transparent Transparent white.
             */
            class White(
                val high: Color = Color.Unspecified,
                val higher: Color = Color.Unspecified,
                val highest: Color = Color.Unspecified,
                val low: Color = Color.Unspecified,
                val lower: Color = Color.Unspecified,
                val lowest: Color = Color.Unspecified,
                val medium: Color = Color.Unspecified,
                val mediumLow: Color = Color.Unspecified,
                val transparent: Color = Color.Unspecified
            )
        }

        /**
         * Positive/success color palette with intensity variants.
         *
         * @param high High intensity positive color.
         * @param higher Higher intensity positive color.
         * @param highest Highest intensity positive color.
         * @param low Low intensity positive color.
         * @param lower Lower intensity positive color.
         * @param lowest Lowest intensity positive color.
         * @param medium Medium intensity positive color.
         */
        class Positive(
            val high: Color = Color.Unspecified,
            val higher: Color = Color.Unspecified,
            val highest: Color = Color.Unspecified,
            val low: Color = Color.Unspecified,
            val lower: Color = Color.Unspecified,
            val lowest: Color = Color.Unspecified,
            val medium: Color = Color.Unspecified
        )

        /**
         * Primary color palette with intensity variants.
         *
         * @param high High intensity primary color.
         * @param higher Higher intensity primary color.
         * @param highest Highest intensity primary color.
         * @param low Low intensity primary color.
         * @param lower Lower intensity primary color.
         * @param lowest Lowest intensity primary color.
         * @param medium Medium intensity primary color.
         */
        class Primary(
            val high: Color = Color.Unspecified,
            val higher: Color = Color.Unspecified,
            val highest: Color = Color.Unspecified,
            val low: Color = Color.Unspecified,
            val lower: Color = Color.Unspecified,
            val lowest: Color = Color.Unspecified,
            val medium: Color = Color.Unspecified
        )

        /**
         * Secondary color palette with intensity variants.
         *
         * @param high High intensity secondary color.
         * @param higherHigh Higher-high intensity secondary color.
         * @param higherLow Higher-low intensity secondary color.
         * @param highest Highest intensity secondary color.
         * @param low Low intensity secondary color.
         * @param lower Lower intensity secondary color.
         * @param lowest Lowest intensity secondary color.
         * @param medium Medium intensity secondary color.
         */
        class Secondary(
            val high: Color = Color.Unspecified,
            val higherHigh: Color = Color.Unspecified,
            val higherLow: Color = Color.Unspecified,
            val highest: Color = Color.Unspecified,
            val low: Color = Color.Unspecified,
            val lower: Color = Color.Unspecified,
            val lowest: Color = Color.Unspecified,
            val medium: Color = Color.Unspecified
        )

        /**
         * Tertiary color palette with intensity variants.
         *
         * @param high High intensity tertiary color.
         * @param higher Higher intensity tertiary color.
         * @param highest Highest intensity tertiary color.
         * @param low Low intensity tertiary color.
         * @param lower Lower intensity tertiary color.
         * @param lowest Lowest intensity tertiary color.
         * @param medium Medium intensity tertiary color.
         */
        class Tertiary(
            val high: Color = Color.Unspecified,
            val higher: Color = Color.Unspecified,
            val highest: Color = Color.Unspecified,
            val low: Color = Color.Unspecified,
            val lower: Color = Color.Unspecified,
            val lowest: Color = Color.Unspecified,
            val medium: Color = Color.Unspecified
        )

        /**
         * Warning color palette with intensity variants.
         *
         * @param high High intensity warning color.
         * @param higher Higher intensity warning color.
         * @param highest Highest intensity warning color.
         * @param low Low intensity warning color.
         * @param lower Lower intensity warning color.
         * @param lowest Lowest intensity warning color.
         * @param medium Medium intensity warning color.
         */
        class Warning(
            val high: Color = Color.Unspecified,
            val higher: Color = Color.Unspecified,
            val highest: Color = Color.Unspecified,
            val low: Color = Color.Unspecified,
            val lower: Color = Color.Unspecified,
            val lowest: Color = Color.Unspecified,
            val medium: Color = Color.Unspecified
        )
    }

    /**
     * Surface colors for containers and backgrounds.
     *
     * @param brand Brand surface colors.
     * @param inverseHigh High-emphasis inverse surface.
     * @param inverseLow Low-emphasis inverse surface.
     * @param primary Primary surface color.
     * @param secondary Secondary surface color.
     * @param status Status-related surface colors.
     * @param tertiary Tertiary surface color.
     */
    class Surface(
        val brand: Brand = Brand(),
        val inverseHigh: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
        val inverseLow: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
        val primary: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
        val secondary: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
        val status: Status = Status(),
        val tertiary: OudsLightDarkColor = OudsLightDarkColor.Unspecified
    ) {

        /**
         * Brand surface colors.
         *
         * @param primary Primary brand surface color.
         * @param secondary Secondary brand surface color.
         * @param tertiary Tertiary brand surface color.
         */
        class Brand(
            val primary: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
            val secondary: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
            val tertiary: OudsLightDarkColor = OudsLightDarkColor.Unspecified
        )

        /**
         * Status-related surface colors.
         *
         * @param accent Accent status surface colors.
         * @param info Info status surface colors.
         * @param negative Negative/error status surface colors.
         * @param positive Positive/success status surface colors.
         * @param warning Warning status surface colors.
         */
        class Status(
            val accent: Accent = Accent(),
            val info: Info = Info(),
            val negative: Negative = Negative(),
            val positive: Positive = Positive(),
            val warning: Warning = Warning()
        ) {

            /**
             * Accent status surface colors.
             *
             * @param emphasized Emphasized accent status surface.
             * @param muted Muted accent status surface.
             */
            class Accent(
                val emphasized: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
                val muted: OudsLightDarkColor = OudsLightDarkColor.Unspecified
            )

            /**
             * Info status surface colors.
             *
             * @param emphasized Emphasized info status surface.
             * @param muted Muted info status surface.
             */
            class Info(
                val emphasized: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
                val muted: OudsLightDarkColor = OudsLightDarkColor.Unspecified
            )

            /**
             * Negative/error status surface colors.
             *
             * @param emphasized Emphasized negative status surface.
             * @param muted Muted negative status surface.
             */
            class Negative(
                val emphasized: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
                val muted: OudsLightDarkColor = OudsLightDarkColor.Unspecified
            )

            /**
             * Positive/success status surface colors.
             *
             * @param emphasized Emphasized positive status surface.
             * @param muted Muted positive status surface.
             */
            class Positive(
                val emphasized: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
                val muted: OudsLightDarkColor = OudsLightDarkColor.Unspecified
            )

            /**
             * Warning status surface colors.
             *
             * @param emphasized Emphasized warning status surface.
             * @param muted Muted warning status surface.
             */
            class Warning(
                val emphasized: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
                val muted: OudsLightDarkColor = OudsLightDarkColor.Unspecified
            )
        }
    }
}

internal val WhiteLabelColorScheme.colorSemanticTokens: WhiteLabelColorSemanticTokens
    get() = with(WireframeColorSemanticTokens()) {
        WhiteLabelColorSemanticTokens(
            actionColorTokens = action.colorActionSemanticTokens,
            aiColorTokens = ai.colorAiSemanticTokens,
            alwaysColorTokens = always.colorAlwaysSemanticTokens,
            backgroundColorTokens = background.colorBgSemanticTokens,
            borderColorTokens = border.colorBorderSemanticTokens,
            contentColorTokens = content.colorContentSemanticTokens,
            opacityColorTokens = opacity.colorOpacitySemanticTokens,
            overlayColorTokens = overlay.colorOverlaySemanticTokens,
            repositoryColorTokens = repository.colorRepositorySemanticTokens,
            surfaceColorTokens = surface.colorSurfaceSemanticTokens,
            colorModeTokens = colorModeTokens
        )
    }

private val WhiteLabelColorScheme.Action.colorActionSemanticTokens: WhiteLabelColorActionSemanticTokens
    get() = with(WireframeColorActionSemanticTokens()) {
        WhiteLabelColorActionSemanticTokens(
            actionDisabledDark = disabled.dark.takeOrElse { actionDisabledDark },
            actionDisabledLight = disabled.light.takeOrElse { actionDisabledLight },
            actionEnabledDark = enabled.dark.takeOrElse { actionEnabledDark },
            actionEnabledLight = enabled.light.takeOrElse { actionEnabledLight },
            actionFocusDark = focus.dark.takeOrElse { actionFocusDark },
            actionFocusLight = focus.light.takeOrElse { actionFocusLight },
            actionHighlightedDark = highlighted.dark.takeOrElse { actionHighlightedDark },
            actionHighlightedLight = highlighted.light.takeOrElse { actionHighlightedLight },
            actionHoverDark = hover.dark.takeOrElse { actionHoverDark },
            actionHoverLight = hover.light.takeOrElse { actionHoverLight },
            actionLoadingDark = loading.dark.takeOrElse { actionLoadingDark },
            actionLoadingLight = loading.light.takeOrElse { actionLoadingLight },
            actionNegativeEnabledDark = negative.enabled.dark.takeOrElse { actionNegativeEnabledDark },
            actionNegativeEnabledLight = negative.enabled.light.takeOrElse { actionNegativeEnabledLight },
            actionNegativeFocusDark = negative.focus.dark.takeOrElse { actionNegativeFocusDark },
            actionNegativeFocusLight = negative.focus.light.takeOrElse { actionNegativeFocusLight },
            actionNegativeHoverDark = negative.hover.dark.takeOrElse { actionNegativeHoverDark },
            actionNegativeHoverLight = negative.hover.light.takeOrElse { actionNegativeHoverLight },
            actionNegativeLoadingDark = negative.loading.dark.takeOrElse { actionNegativeLoadingDark },
            actionNegativeLoadingLight = negative.loading.light.takeOrElse { actionNegativeLoadingLight },
            actionNegativePressedDark = negative.pressed.dark.takeOrElse { actionNegativePressedDark },
            actionNegativePressedLight = negative.pressed.light.takeOrElse { actionNegativePressedLight },
            actionPressedDark = pressed.dark.takeOrElse { actionPressedDark },
            actionPressedLight = pressed.light.takeOrElse { actionPressedLight },
            actionReadOnlyPrimaryDark = readOnly.primary.dark.takeOrElse { actionReadOnlyPrimaryDark },
            actionReadOnlyPrimaryLight = readOnly.primary.light.takeOrElse { actionReadOnlyPrimaryLight },
            actionReadOnlySecondaryDark = readOnly.secondary.dark.takeOrElse { actionReadOnlySecondaryDark },
            actionReadOnlySecondaryLight = readOnly.secondary.light.takeOrElse { actionReadOnlySecondaryLight },
            actionSelectedDark = selected.dark.takeOrElse { actionSelectedDark },
            actionSelectedLight = selected.light.takeOrElse { actionSelectedLight },
            actionSupportDisabledDark = support.disabled.dark.takeOrElse { actionSupportDisabledDark },
            actionSupportDisabledLight = support.disabled.light.takeOrElse { actionSupportDisabledLight },
            actionSupportEnabledDark = support.enabled.dark.takeOrElse { actionSupportEnabledDark },
            actionSupportEnabledLight = support.enabled.light.takeOrElse { actionSupportEnabledLight },
            actionSupportFocusDark = support.focus.dark.takeOrElse { actionSupportFocusDark },
            actionSupportFocusLight = support.focus.light.takeOrElse { actionSupportFocusLight },
            actionSupportHoverDark = support.hover.dark.takeOrElse { actionSupportHoverDark },
            actionSupportHoverLight = support.hover.light.takeOrElse { actionSupportHoverLight },
            actionSupportLoadingDark = support.loading.dark.takeOrElse { actionSupportLoadingDark },
            actionSupportLoadingLight = support.loading.light.takeOrElse { actionSupportLoadingLight },
            actionSupportPressedDark = support.pressed.dark.takeOrElse { actionSupportPressedDark },
            actionSupportPressedLight = support.pressed.light.takeOrElse { actionSupportPressedLight },
            actionVisitedDark = visited.dark.takeOrElse { actionVisitedDark },
            actionVisitedLight = visited.light.takeOrElse { actionVisitedLight }
        )
    }

private val WhiteLabelColorScheme.Ai.colorAiSemanticTokens: WhiteLabelColorAiSemanticTokens
    get() = with(WireframeColorAiSemanticTokens()) {
        WhiteLabelColorAiSemanticTokens(
            aiPrimaryDark = primary.dark.takeOrElse { aiPrimaryDark },
            aiPrimaryLight = primary.light.takeOrElse { aiPrimaryLight },
            aiQuaternaryDark = quaternary.dark.takeOrElse { aiQuaternaryDark },
            aiQuaternaryLight = quaternary.light.takeOrElse { aiQuaternaryLight },
            aiSecondaryDark = secondary.dark.takeOrElse { aiSecondaryDark },
            aiSecondaryLight = secondary.light.takeOrElse { aiSecondaryLight },
            aiTertiaryDark = tertiary.dark.takeOrElse { aiPrimaryDark },
            aiTertiaryLight = tertiary.light.takeOrElse { aiPrimaryLight }
        )
    }

private val WhiteLabelColorScheme.Always.colorAlwaysSemanticTokens: WhiteLabelColorAlwaysSemanticTokens
    get() = with(WireframeColorAlwaysSemanticTokens()) {
        WhiteLabelColorAlwaysSemanticTokens(
            alwaysBlack = black.takeOrElse { alwaysBlack },
            alwaysOnBlack = onBlack.takeOrElse { alwaysOnBlack },
            alwaysOnWhite = onWhite.takeOrElse { alwaysOnWhite },
            alwaysWhite = white.takeOrElse { alwaysWhite }
        )
    }

private val WhiteLabelColorScheme.Background.colorBgSemanticTokens: WhiteLabelColorBgSemanticTokens
    get() = with(WireframeColorBgSemanticTokens()) {
        WhiteLabelColorBgSemanticTokens(
            bgInverseHighDark = inverseHigh.dark.takeOrElse { bgInverseHighDark },
            bgInverseHighLight = inverseHigh.light.takeOrElse { bgInverseHighLight },
            bgInverseLowDark = inverseLow.dark.takeOrElse { bgInverseLowDark },
            bgInverseLowLight = inverseLow.light.takeOrElse { bgInverseLowLight },
            bgPrimaryDark = primary.dark.takeOrElse { bgPrimaryDark },
            bgPrimaryLight = primary.light.takeOrElse { bgPrimaryLight },
            bgSecondaryDark = secondary.dark.takeOrElse { bgSecondaryDark },
            bgSecondaryLight = secondary.light.takeOrElse { bgSecondaryLight },
            bgTertiaryDark = tertiary.dark.takeOrElse { bgTertiaryDark },
            bgTertiaryLight = tertiary.light.takeOrElse { bgTertiaryLight }
        )
    }

private val WhiteLabelColorScheme.Border.colorBorderSemanticTokens: WhiteLabelColorBorderSemanticTokens
    get() = with(WireframeColorBorderSemanticTokens()) {
        WhiteLabelColorBorderSemanticTokens(
            borderBrandPrimaryDark = brandPrimary.dark.takeOrElse { borderBrandPrimaryDark },
            borderBrandPrimaryLight = brandPrimary.light.takeOrElse { borderBrandPrimaryLight },
            borderBrandSecondaryDark = brandSecondary.dark.takeOrElse { borderBrandSecondaryDark },
            borderBrandSecondaryLight = brandSecondary.light.takeOrElse { borderBrandSecondaryLight },
            borderBrandTertiaryDark = brandTertiary.dark.takeOrElse { borderBrandTertiaryDark },
            borderBrandTertiaryLight = brandTertiary.light.takeOrElse { borderBrandTertiaryLight },
            borderDefaultDark = default.dark.takeOrElse { borderDefaultDark },
            borderDefaultLight = default.light.takeOrElse { borderDefaultLight },
            borderEmphasizedDark = emphasized.dark.takeOrElse { borderEmphasizedDark },
            borderEmphasizedLight = emphasized.light.takeOrElse { borderEmphasizedLight },
            borderFocusDark = focus.dark.takeOrElse { borderFocusDark },
            borderFocusInsetDark = focusInset.dark.takeOrElse { borderFocusInsetDark },
            borderFocusInsetLight = focusInset.light.takeOrElse { borderFocusInsetLight },
            borderFocusLight = focus.light.takeOrElse { borderFocusLight },
            borderMinimalDark = minimal.dark.takeOrElse { borderMinimalDark },
            borderMinimalLight = minimal.light.takeOrElse { borderMinimalLight },
            borderMutedDark = muted.dark.takeOrElse { borderMutedDark },
            borderMutedLight = muted.light.takeOrElse { borderMutedLight },
            borderOnBrandPrimaryDark = onBrand.primary.dark.takeOrElse { borderOnBrandPrimaryDark },
            borderOnBrandPrimaryLight = onBrand.primary.light.takeOrElse { borderOnBrandPrimaryLight },
            borderOnBrandSecondaryDark = onBrand.secondary.dark.takeOrElse { borderOnBrandSecondaryDark },
            borderOnBrandSecondaryLight = onBrand.secondary.light.takeOrElse { borderOnBrandSecondaryLight },
            borderOnBrandTertiaryDark = onBrand.tertiary.dark.takeOrElse { borderOnBrandTertiaryDark },
            borderOnBrandTertiaryLight = onBrand.tertiary.light.takeOrElse { borderOnBrandTertiaryLight },
            borderStatusAccentDark = status.accent.dark.takeOrElse { borderStatusAccentDark },
            borderStatusAccentLight = status.accent.light.takeOrElse { borderStatusAccentLight },
            borderStatusInfoDark = status.info.dark.takeOrElse { borderStatusInfoDark },
            borderStatusInfoLight = status.info.light.takeOrElse { borderStatusInfoLight },
            borderStatusNegativeDark = status.negative.dark.takeOrElse { borderStatusNegativeDark },
            borderStatusNegativeLight = status.negative.light.takeOrElse { borderStatusNegativeLight },
            borderStatusPositiveDark = status.positive.dark.takeOrElse { borderStatusPositiveDark },
            borderStatusPositiveLight = status.positive.light.takeOrElse { borderStatusPositiveLight },
            borderStatusWarningDark = status.warning.dark.takeOrElse { borderStatusWarningDark },
            borderStatusWarningLight = status.warning.light.takeOrElse { borderStatusWarningLight }
        )
    }

private val WhiteLabelColorScheme.Content.colorContentSemanticTokens: WhiteLabelColorContentSemanticTokens
    get() = with(WireframeColorContentSemanticTokens()) {
        WhiteLabelColorContentSemanticTokens(
            contentBrandPrimaryDark = brandPrimary.dark.takeOrElse { contentBrandPrimaryDark },
            contentBrandPrimaryLight = brandPrimary.light.takeOrElse { contentBrandPrimaryLight },
            contentBrandSecondaryDark = brandSecondary.dark.takeOrElse { contentBrandSecondaryDark },
            contentBrandSecondaryLight = brandSecondary.light.takeOrElse { contentBrandSecondaryLight },
            contentBrandTertiaryDark = brandTertiary.dark.takeOrElse { contentBrandTertiaryDark },
            contentBrandTertiaryLight = brandTertiary.light.takeOrElse { contentBrandTertiaryLight },
            contentDefaultDark = default.dark.takeOrElse { contentDefaultDark },
            contentDefaultLight = default.light.takeOrElse { contentDefaultLight },
            contentDisabledDark = disabled.dark.takeOrElse { contentDisabledDark },
            contentDisabledLight = disabled.light.takeOrElse { contentDisabledLight },
            contentInverseDark = inverse.dark.takeOrElse { contentInverseDark },
            contentInverseLight = inverse.light.takeOrElse { contentInverseLight },
            contentMutedDark = muted.dark.takeOrElse { contentMutedDark },
            contentMutedLight = muted.light.takeOrElse { contentMutedLight },
            contentOnActionDisabledDark = onAction.disabled.dark.takeOrElse { contentOnActionDisabledDark },
            contentOnActionDisabledLight = onAction.disabled.light.takeOrElse { contentOnActionDisabledLight },
            contentOnActionEnabledDark = onAction.enabled.dark.takeOrElse { contentOnActionEnabledDark },
            contentOnActionEnabledLight = onAction.enabled.light.takeOrElse { contentOnActionEnabledLight },
            contentOnActionFocusDark = onAction.focus.dark.takeOrElse { contentOnActionFocusDark },
            contentOnActionFocusLight = onAction.focus.light.takeOrElse { contentOnActionFocusLight },
            contentOnActionHighlightedDark = onAction.highlighted.dark.takeOrElse { contentOnActionHighlightedDark },
            contentOnActionHighlightedLight = onAction.highlighted.light.takeOrElse { contentOnActionHighlightedLight },
            contentOnActionHoverDark = onAction.hover.dark.takeOrElse { contentOnActionHoverDark },
            contentOnActionHoverLight = onAction.hover.light.takeOrElse { contentOnActionHoverLight },
            contentOnActionLoadingDark = onAction.loading.dark.takeOrElse { contentOnActionLoadingDark },
            contentOnActionLoadingLight = onAction.loading.light.takeOrElse { contentOnActionLoadingLight },
            contentOnActionPressedDark = onAction.pressed.dark.takeOrElse { contentOnActionPressedDark },
            contentOnActionPressedLight = onAction.pressed.light.takeOrElse { contentOnActionPressedLight },
            contentOnActionSelectedDark = onAction.selected.dark.takeOrElse { contentOnActionSelectedDark },
            contentOnActionSelectedLight = onAction.selected.light.takeOrElse { contentOnActionSelectedLight },
            contentOnBrandPrimaryDark = onBrand.primary.dark.takeOrElse { contentOnBrandPrimaryDark },
            contentOnBrandPrimaryLight = onBrand.primary.light.takeOrElse { contentOnBrandPrimaryLight },
            contentOnBrandSecondaryDark = onBrand.secondary.dark.takeOrElse { contentOnBrandSecondaryDark },
            contentOnBrandSecondaryLight = onBrand.secondary.light.takeOrElse { contentOnBrandSecondaryLight },
            contentOnBrandTertiaryDark = onBrand.tertiary.dark.takeOrElse { contentOnBrandTertiaryDark },
            contentOnBrandTertiaryLight = onBrand.tertiary.light.takeOrElse { contentOnBrandTertiaryLight },
            contentOnStatusAccentEmphasizedDark = onStatus.accent.emphasized.dark.takeOrElse { contentOnStatusAccentEmphasizedDark },
            contentOnStatusAccentEmphasizedLight = onStatus.accent.emphasized.light.takeOrElse { contentOnStatusAccentEmphasizedLight },
            contentOnStatusAccentMutedDark = onStatus.accent.muted.dark.takeOrElse { contentOnStatusAccentMutedDark },
            contentOnStatusAccentMutedLight = onStatus.accent.muted.light.takeOrElse { contentOnStatusAccentMutedLight },
            contentOnStatusInfoEmphasizedDark = onStatus.info.emphasized.dark.takeOrElse { contentOnStatusInfoEmphasizedDark },
            contentOnStatusInfoEmphasizedLight = onStatus.info.emphasized.light.takeOrElse { contentOnStatusInfoEmphasizedLight },
            contentOnStatusInfoMutedDark = onStatus.info.muted.dark.takeOrElse { contentOnStatusInfoMutedDark },
            contentOnStatusInfoMutedLight = onStatus.info.muted.light.takeOrElse { contentOnStatusInfoMutedLight },
            contentOnStatusNegativeEmphasizedDark = onStatus.negative.emphasized.dark.takeOrElse { contentOnStatusNegativeEmphasizedDark },
            contentOnStatusNegativeEmphasizedLight = onStatus.negative.emphasized.light.takeOrElse { contentOnStatusNegativeEmphasizedLight },
            contentOnStatusNegativeMutedDark = onStatus.negative.muted.dark.takeOrElse { contentOnStatusNegativeMutedDark },
            contentOnStatusNegativeMutedLight = onStatus.negative.muted.light.takeOrElse { contentOnStatusNegativeMutedLight },
            contentOnStatusPositiveEmphasizedDark = onStatus.positive.emphasized.dark.takeOrElse { contentOnStatusPositiveEmphasizedDark },
            contentOnStatusPositiveEmphasizedLight = onStatus.positive.emphasized.light.takeOrElse { contentOnStatusPositiveEmphasizedLight },
            contentOnStatusPositiveMutedDark = onStatus.positive.muted.dark.takeOrElse { contentOnStatusPositiveMutedDark },
            contentOnStatusPositiveMutedLight = onStatus.positive.muted.light.takeOrElse { contentOnStatusPositiveMutedLight },
            contentOnStatusWarningEmphasizedDark = onStatus.warning.emphasized.dark.takeOrElse { contentOnStatusWarningEmphasizedDark },
            contentOnStatusWarningEmphasizedLight = onStatus.warning.emphasized.light.takeOrElse { contentOnStatusWarningEmphasizedLight },
            contentOnStatusWarningMutedDark = onStatus.warning.muted.dark.takeOrElse { contentOnStatusWarningMutedDark },
            contentOnStatusWarningMutedLight = onStatus.warning.muted.light.takeOrElse { contentOnStatusWarningMutedLight },
            contentStatusAccentDark = status.accent.dark.takeOrElse { contentStatusAccentDark },
            contentStatusAccentLight = status.accent.light.takeOrElse { contentStatusAccentLight },
            contentStatusInfoDark = status.info.dark.takeOrElse { contentStatusInfoDark },
            contentStatusInfoLight = status.info.light.takeOrElse { contentStatusInfoLight },
            contentStatusInverseInfoDark = status.inverse.info.dark.takeOrElse { contentStatusInverseInfoDark },
            contentStatusInverseInfoLight = status.inverse.info.light.takeOrElse { contentStatusInverseInfoLight },
            contentStatusInverseNegativeDark = status.inverse.negative.dark.takeOrElse { contentStatusInverseNegativeDark },
            contentStatusInverseNegativeLight = status.inverse.negative.light.takeOrElse { contentStatusInverseNegativeLight },
            contentStatusInversePositiveDark = status.inverse.positive.dark.takeOrElse { contentStatusInversePositiveDark },
            contentStatusInversePositiveLight = status.inverse.positive.light.takeOrElse { contentStatusInversePositiveLight },
            contentStatusInverseWarningDark = status.inverse.warning.dark.takeOrElse { contentStatusInverseWarningDark },
            contentStatusInverseWarningLight = status.inverse.warning.light.takeOrElse { contentStatusInverseWarningLight },
            contentStatusNegativeDark = status.negative.dark.takeOrElse { contentStatusNegativeDark },
            contentStatusNegativeLight = status.negative.light.takeOrElse { contentStatusNegativeLight },
            contentStatusPositiveDark = status.positive.dark.takeOrElse { contentStatusPositiveDark },
            contentStatusPositiveLight = status.positive.light.takeOrElse { contentStatusPositiveLight },
            contentStatusWarningDark = status.warning.dark.takeOrElse { contentStatusWarningDark },
            contentStatusWarningLight = status.warning.light.takeOrElse { contentStatusWarningLight }
        )
    }

private val WhiteLabelColorScheme.Opacity.colorOpacitySemanticTokens: WhiteLabelColorOpacitySemanticTokens
    get() = with(WireframeColorOpacitySemanticTokens()) {
        WhiteLabelColorOpacitySemanticTokens(
            opacityLowerDark = lower.dark.takeOrElse { opacityLowerDark },
            opacityLowerLight = lower.light.takeOrElse { opacityLowerLight },
            opacityLowestDark = lowest.dark.takeOrElse { opacityLowestDark },
            opacityLowestLight = lowest.light.takeOrElse { opacityLowestLight },
            opacityTransparentDark = transparent.dark.takeOrElse { opacityTransparentDark },
            opacityTransparentInverseDark = transparentInverse.dark.takeOrElse { opacityTransparentInverseDark },
            opacityTransparentInverseLight = transparentInverse.light.takeOrElse { opacityTransparentInverseLight },
            opacityTransparentLight = transparent.light.takeOrElse { opacityTransparentLight }
        )
    }

private val WhiteLabelColorScheme.Overlay.colorOverlaySemanticTokens: WhiteLabelColorOverlaySemanticTokens
    get() = with(WireframeColorOverlaySemanticTokens()) {
        WhiteLabelColorOverlaySemanticTokens(
            overlayBackdropDark = backdrop.dark.takeOrElse { overlayBackdropDark },
            overlayBackdropLight = backdrop.light.takeOrElse { overlayBackdropLight },
            overlayDragDark = drag.dark.takeOrElse { overlayDragDark },
            overlayDragLight = drag.light.takeOrElse { overlayDragLight },
            overlayDropdownDark = dropdown.dark.takeOrElse { overlayDropdownDark },
            overlayDropdownLight = dropdown.light.takeOrElse { overlayDropdownLight },
            overlayFloatingDark = floating.dark.takeOrElse { overlayFloatingDark },
            overlayFloatingLight = floating.light.takeOrElse { overlayFloatingLight },
            overlayModalSheetDark = modalSheet.dark.takeOrElse { overlayModalSheetDark },
            overlayModalSheetLight = modalSheet.light.takeOrElse { overlayModalSheetLight },
            overlayTooltipDark = tooltip.dark.takeOrElse { overlayTooltipDark },
            overlayTooltipLight = tooltip.light.takeOrElse { overlayTooltipLight }
        )
    }

private val WhiteLabelColorScheme.Repository.colorRepositorySemanticTokens: WhiteLabelColorRepositorySemanticTokens
    get() = with(WireframeColorRepositorySemanticTokens()) {
        WhiteLabelColorRepositorySemanticTokens(
            repositoryAccentHigh = accent.high.takeOrElse { repositoryAccentHigh },
            repositoryAccentHigher = accent.higher.takeOrElse { repositoryAccentHigher },
            repositoryAccentHighest = accent.highest.takeOrElse { repositoryAccentHighest },
            repositoryAccentLow = accent.low.takeOrElse { repositoryAccentLow },
            repositoryAccentLower = accent.lower.takeOrElse { repositoryAccentLower },
            repositoryAccentLowest = accent.lowest.takeOrElse { repositoryAccentLowest },
            repositoryAccentMedium = accent.medium.takeOrElse { repositoryAccentMedium },
            repositoryInfoHigh = info.high.takeOrElse { repositoryInfoHigh },
            repositoryInfoHigher = info.higher.takeOrElse { repositoryInfoHigher },
            repositoryInfoHighest = info.highest.takeOrElse { repositoryInfoHighest },
            repositoryInfoLow = info.low.takeOrElse { repositoryInfoLow },
            repositoryInfoLower = info.lower.takeOrElse { repositoryInfoLower },
            repositoryInfoLowest = info.lowest.takeOrElse { repositoryInfoLowest },
            repositoryInfoMedium = info.medium.takeOrElse { repositoryInfoMedium },
            repositoryNegativeHigh = negative.high.takeOrElse { repositoryNegativeHigh },
            repositoryNegativeHigher = negative.higher.takeOrElse { repositoryNegativeHigher },
            repositoryNegativeHighest = negative.highest.takeOrElse { repositoryNegativeHighest },
            repositoryNegativeLow = negative.low.takeOrElse { repositoryNegativeLow },
            repositoryNegativeLower = negative.lower.takeOrElse { repositoryNegativeLower },
            repositoryNegativeLowest = negative.lowest.takeOrElse { repositoryNegativeLowest },
            repositoryNegativeMedium = negative.medium.takeOrElse { repositoryNegativeMedium },
            repositoryNeutralEmphasizedBlack = neutral.emphasized.black.takeOrElse { repositoryNeutralEmphasizedBlack },
            repositoryNeutralEmphasizedHigh = neutral.emphasized.high.takeOrElse { repositoryNeutralEmphasizedHigh },
            repositoryNeutralEmphasizedHigher = neutral.emphasized.higher.takeOrElse { repositoryNeutralEmphasizedHigher },
            repositoryNeutralEmphasizedHighest = neutral.emphasized.highest.takeOrElse { repositoryNeutralEmphasizedHighest },
            repositoryNeutralEmphasizedLow = neutral.emphasized.low.takeOrElse { repositoryNeutralEmphasizedLow },
            repositoryNeutralEmphasizedLower = neutral.emphasized.lower.takeOrElse { repositoryNeutralEmphasizedLower },
            repositoryNeutralEmphasizedLowest = neutral.emphasized.lowest.takeOrElse { repositoryNeutralEmphasizedLowest },
            repositoryNeutralEmphasizedMedium = neutral.emphasized.medium.takeOrElse { repositoryNeutralEmphasizedMedium },
            repositoryNeutralMutedHigh = neutral.muted.high.takeOrElse { repositoryNeutralMutedHigh },
            repositoryNeutralMutedHigher = neutral.muted.higher.takeOrElse { repositoryNeutralMutedHigher },
            repositoryNeutralMutedHighest = neutral.muted.highest.takeOrElse { repositoryNeutralMutedHighest },
            repositoryNeutralMutedLow = neutral.muted.low.takeOrElse { repositoryNeutralMutedLow },
            repositoryNeutralMutedLower = neutral.muted.lower.takeOrElse { repositoryNeutralMutedLower },
            repositoryNeutralMutedLowest = neutral.muted.lowest.takeOrElse { repositoryNeutralMutedLowest },
            repositoryNeutralMutedMedium = neutral.muted.medium.takeOrElse { repositoryNeutralMutedMedium },
            repositoryNeutralMutedWhite = neutral.muted.white.takeOrElse { repositoryNeutralMutedWhite },
            repositoryOpacityAccentLow = opacity.accent.low.takeOrElse { repositoryOpacityAccentLow },
            repositoryOpacityAccentMedium = opacity.accent.medium.takeOrElse { repositoryOpacityAccentMedium },
            repositoryOpacityBlackHigh = opacity.black.high.takeOrElse { repositoryOpacityBlackHigh },
            repositoryOpacityBlackHigher = opacity.black.higher.takeOrElse { repositoryOpacityBlackHigher },
            repositoryOpacityBlackHighest = opacity.black.highest.takeOrElse { repositoryOpacityBlackHighest },
            repositoryOpacityBlackLow = opacity.black.low.takeOrElse { repositoryOpacityBlackLow },
            repositoryOpacityBlackLower = opacity.black.lower.takeOrElse { repositoryOpacityBlackLower },
            repositoryOpacityBlackLowest = opacity.black.lowest.takeOrElse { repositoryOpacityBlackLowest },
            repositoryOpacityBlackMedium = opacity.black.medium.takeOrElse { repositoryOpacityBlackMedium },
            repositoryOpacityBlackMediumHigh = opacity.black.mediumHigh.takeOrElse { repositoryOpacityBlackMediumHigh },
            repositoryOpacityBlackMediumLow = opacity.black.mediumLow.takeOrElse { repositoryOpacityBlackMediumLow },
            repositoryOpacityBlackTransparent = opacity.black.transparent.takeOrElse { repositoryOpacityBlackTransparent },
            repositoryOpacityInfoLow = opacity.info.low.takeOrElse { repositoryOpacityInfoLow },
            repositoryOpacityInfoMedium = opacity.info.medium.takeOrElse { repositoryOpacityInfoMedium },
            repositoryOpacityNegativeLow = opacity.negative.low.takeOrElse { repositoryOpacityNegativeLow },
            repositoryOpacityNegativeMedium = opacity.negative.medium.takeOrElse { repositoryOpacityNegativeMedium },
            repositoryOpacityPositiveLow = opacity.positive.low.takeOrElse { repositoryOpacityPositiveLow },
            repositoryOpacityPositiveMedium = opacity.positive.medium.takeOrElse { repositoryOpacityPositiveMedium },
            repositoryOpacityPrimaryHigh = opacity.primary.high.takeOrElse { repositoryOpacityPrimaryHigh },
            repositoryOpacityPrimaryHigher = opacity.primary.higher.takeOrElse { repositoryOpacityPrimaryHigher },
            repositoryOpacityPrimaryHighest = opacity.primary.highest.takeOrElse { repositoryOpacityPrimaryHighest },
            repositoryOpacityPrimaryLow = opacity.primary.low.takeOrElse { repositoryOpacityPrimaryLow },
            repositoryOpacityPrimaryLower = opacity.primary.lower.takeOrElse { repositoryOpacityPrimaryLower },
            repositoryOpacityPrimaryLowest = opacity.primary.lowest.takeOrElse { repositoryOpacityPrimaryLowest },
            repositoryOpacityPrimaryMedium = opacity.primary.medium.takeOrElse { repositoryOpacityPrimaryMedium },
            repositoryOpacityPrimaryTransparent = opacity.primary.transparent.takeOrElse { repositoryOpacityPrimaryTransparent },
            repositoryOpacityWarningLow = opacity.warning.low.takeOrElse { repositoryOpacityWarningLow },
            repositoryOpacityWarningMedium = opacity.warning.medium.takeOrElse { repositoryOpacityWarningMedium },
            repositoryOpacityWhiteHigh = opacity.white.high.takeOrElse { repositoryOpacityWhiteHigh },
            repositoryOpacityWhiteHigher = opacity.white.higher.takeOrElse { repositoryOpacityWhiteHigher },
            repositoryOpacityWhiteHighest = opacity.white.highest.takeOrElse { repositoryOpacityWhiteHighest },
            repositoryOpacityWhiteLow = opacity.white.low.takeOrElse { repositoryOpacityWhiteLow },
            repositoryOpacityWhiteLower = opacity.white.lower.takeOrElse { repositoryOpacityWhiteLower },
            repositoryOpacityWhiteLowest = opacity.white.lowest.takeOrElse { repositoryOpacityWhiteLowest },
            repositoryOpacityWhiteMedium = opacity.white.medium.takeOrElse { repositoryOpacityWhiteMedium },
            repositoryOpacityWhiteMediumLow = opacity.white.mediumLow.takeOrElse { repositoryOpacityWhiteMediumLow },
            repositoryOpacityWhiteTransparent = opacity.white.transparent.takeOrElse { repositoryOpacityWhiteTransparent },
            repositoryPositiveHigh = positive.high.takeOrElse { repositoryPositiveHigh },
            repositoryPositiveHigher = positive.higher.takeOrElse { repositoryPositiveHigher },
            repositoryPositiveHighest = positive.highest.takeOrElse { repositoryPositiveHighest },
            repositoryPositiveLow = positive.low.takeOrElse { repositoryPositiveLow },
            repositoryPositiveLower = positive.lower.takeOrElse { repositoryPositiveLower },
            repositoryPositiveLowest = positive.lowest.takeOrElse { repositoryPositiveLowest },
            repositoryPositiveMedium = positive.medium.takeOrElse { repositoryPositiveMedium },
            repositoryPrimaryHigh = primary.high.takeOrElse { repositoryPrimaryHigh },
            repositoryPrimaryHigher = primary.higher.takeOrElse { repositoryPrimaryHigher },
            repositoryPrimaryHighest = primary.highest.takeOrElse { repositoryPrimaryHighest },
            repositoryPrimaryLow = primary.low.takeOrElse { repositoryPrimaryLow },
            repositoryPrimaryLower = primary.lower.takeOrElse { repositoryPrimaryLower },
            repositoryPrimaryLowest = primary.lowest.takeOrElse { repositoryPrimaryLowest },
            repositoryPrimaryMedium = primary.medium.takeOrElse { repositoryPrimaryMedium },
            repositorySecondaryHigh = secondary.high.takeOrElse { repositorySecondaryHigh },
            repositorySecondaryHigherHigh = secondary.higherHigh.takeOrElse { repositorySecondaryHigherHigh },
            repositorySecondaryHigherLow = secondary.higherLow.takeOrElse { repositorySecondaryHigherLow },
            repositorySecondaryHighest = secondary.highest.takeOrElse { repositorySecondaryHighest },
            repositorySecondaryLow = secondary.low.takeOrElse { repositorySecondaryLow },
            repositorySecondaryLower = secondary.lower.takeOrElse { repositorySecondaryLower },
            repositorySecondaryLowest = secondary.lowest.takeOrElse { repositorySecondaryLowest },
            repositorySecondaryMedium = secondary.medium.takeOrElse { repositorySecondaryMedium },
            repositoryTertiaryHigh = tertiary.high.takeOrElse { repositoryTertiaryHigh },
            repositoryTertiaryHigher = tertiary.higher.takeOrElse { repositoryTertiaryHigher },
            repositoryTertiaryHighest = tertiary.highest.takeOrElse { repositoryTertiaryHighest },
            repositoryTertiaryLow = tertiary.low.takeOrElse { repositoryTertiaryLow },
            repositoryTertiaryLower = tertiary.lower.takeOrElse { repositoryTertiaryLower },
            repositoryTertiaryLowest = tertiary.lowest.takeOrElse { repositoryTertiaryLowest },
            repositoryTertiaryMedium = tertiary.medium.takeOrElse { repositoryTertiaryMedium },
            repositoryWarningHigh = warning.high.takeOrElse { repositoryWarningHigh },
            repositoryWarningHigher = warning.higher.takeOrElse { repositoryWarningHigher },
            repositoryWarningHighest = warning.highest.takeOrElse { repositoryWarningHighest },
            repositoryWarningLow = warning.low.takeOrElse { repositoryWarningLow },
            repositoryWarningLower = warning.lower.takeOrElse { repositoryWarningLower },
            repositoryWarningLowest = warning.lowest.takeOrElse { repositoryWarningLowest },
            repositoryWarningMedium = warning.medium.takeOrElse { repositoryWarningMedium }
        )
    }

private val WhiteLabelColorScheme.Surface.colorSurfaceSemanticTokens: WhiteLabelColorSurfaceSemanticTokens
    get() = with(WireframeColorSurfaceSemanticTokens()) {
        WhiteLabelColorSurfaceSemanticTokens(
            surfaceBrandPrimaryDark = brand.primary.dark.takeOrElse { surfaceBrandPrimaryDark },
            surfaceBrandPrimaryLight = brand.primary.light.takeOrElse { surfaceBrandPrimaryLight },
            surfaceBrandSecondaryDark = brand.secondary.dark.takeOrElse { surfaceBrandSecondaryDark },
            surfaceBrandSecondaryLight = brand.secondary.light.takeOrElse { surfaceBrandSecondaryLight },
            surfaceBrandTertiaryDark = brand.tertiary.dark.takeOrElse { surfaceBrandTertiaryDark },
            surfaceBrandTertiaryLight = brand.tertiary.light.takeOrElse { surfaceBrandTertiaryLight },
            surfaceInverseHighDark = inverseHigh.dark.takeOrElse { surfaceInverseHighDark },
            surfaceInverseHighLight = inverseHigh.light.takeOrElse { surfaceInverseHighLight },
            surfaceInverseLowDark = inverseLow.dark.takeOrElse { surfaceInverseLowDark },
            surfaceInverseLowLight = inverseLow.light.takeOrElse { surfaceInverseLowLight },
            surfacePrimaryDark = primary.dark.takeOrElse { surfacePrimaryDark },
            surfacePrimaryLight = primary.light.takeOrElse { surfacePrimaryLight },
            surfaceSecondaryDark = secondary.dark.takeOrElse { surfaceSecondaryDark },
            surfaceSecondaryLight = secondary.light.takeOrElse { surfaceSecondaryLight },
            surfaceStatusAccentEmphasizedDark = status.accent.emphasized.dark.takeOrElse { surfaceStatusAccentEmphasizedDark },
            surfaceStatusAccentEmphasizedLight = status.accent.emphasized.light.takeOrElse { surfaceStatusAccentEmphasizedLight },
            surfaceStatusAccentMutedDark = status.accent.muted.dark.takeOrElse { surfaceStatusAccentMutedDark },
            surfaceStatusAccentMutedLight = status.accent.muted.light.takeOrElse { surfaceStatusAccentMutedLight },
            surfaceStatusInfoEmphasizedDark = status.info.emphasized.dark.takeOrElse { surfaceStatusInfoEmphasizedDark },
            surfaceStatusInfoEmphasizedLight = status.info.emphasized.light.takeOrElse { surfaceStatusInfoEmphasizedLight },
            surfaceStatusInfoMutedDark = status.info.muted.dark.takeOrElse { surfaceStatusInfoMutedDark },
            surfaceStatusInfoMutedLight = status.info.muted.light.takeOrElse { surfaceStatusInfoMutedLight },
            surfaceStatusNegativeEmphasizedDark = status.negative.emphasized.dark.takeOrElse { surfaceStatusNegativeEmphasizedDark },
            surfaceStatusNegativeEmphasizedLight = status.negative.emphasized.light.takeOrElse { surfaceStatusNegativeEmphasizedLight },
            surfaceStatusNegativeMutedDark = status.negative.muted.dark.takeOrElse { surfaceStatusNegativeMutedDark },
            surfaceStatusNegativeMutedLight = status.negative.muted.light.takeOrElse { surfaceStatusNegativeMutedLight },
            surfaceStatusPositiveEmphasizedDark = status.positive.emphasized.dark.takeOrElse { surfaceStatusPositiveEmphasizedDark },
            surfaceStatusPositiveEmphasizedLight = status.positive.emphasized.light.takeOrElse { surfaceStatusPositiveEmphasizedLight },
            surfaceStatusPositiveMutedDark = status.positive.muted.dark.takeOrElse { surfaceStatusPositiveMutedDark },
            surfaceStatusPositiveMutedLight = status.positive.muted.light.takeOrElse { surfaceStatusPositiveMutedLight },
            surfaceStatusWarningEmphasizedDark = status.warning.emphasized.dark.takeOrElse { surfaceStatusWarningEmphasizedDark },
            surfaceStatusWarningEmphasizedLight = status.warning.emphasized.light.takeOrElse { surfaceStatusWarningEmphasizedLight },
            surfaceStatusWarningMutedDark = status.warning.muted.dark.takeOrElse { surfaceStatusWarningMutedDark },
            surfaceStatusWarningMutedLight = status.warning.muted.light.takeOrElse { surfaceStatusWarningMutedLight },
            surfaceTertiaryDark = tertiary.dark.takeOrElse { surfaceTertiaryDark },
            surfaceTertiaryLight = tertiary.light.takeOrElse { surfaceTertiaryLight },
        )
    }

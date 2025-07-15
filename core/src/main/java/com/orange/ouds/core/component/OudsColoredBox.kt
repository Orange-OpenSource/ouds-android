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

package com.orange.ouds.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.extensions.filter
import com.orange.ouds.core.theme.LocalColorMode
import com.orange.ouds.core.theme.OudsColorMode
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.OudsThemeTweak
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.CheckedContent
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.utilities.EnumPreviewParameterProvider
import com.orange.ouds.theme.tokens.OudsColorKeyToken

/**
 * A colored box is a [Box] where content color is automatically adjusted to maximize the contrast with the chosen background [color].
 *
 * Moreover, the colors of several OUDS Android components (for instance [OudsButton] or [OudsLink]) are also automatically adjusted.
 * Some tokens associated with these specific colors can be customized and are grouped into `Mono` tokens classes (for instance [com.orange.ouds.theme.tokens.components.OudsButtonMonoTokens]).
 *
 * @param color The background color of the colored box.
 * @param modifier [Modifier] to be applied to the layout corresponding to the colored box.
 * @param contentAlignment The default [Alignment] inside the colored box.
 * @param propagateMinConstraints Whether the incoming min constraints should be passed to content.
 * @param content The content of this colored box.
 *
 * @sample com.orange.ouds.core.component.samples.OudsColoredBoxSample
 */
@Composable
fun OudsColoredBox(
    color: OudsColoredBox.Color,
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    propagateMinConstraints: Boolean = false,
    content: @Composable BoxScope.() -> Unit
) {
    CheckedContent(
        expression = color.mode.isSupported,
        exceptionMessage = { "Current theme does not support an OudsColoredBox with color parameter set to ${color.name}." },
        previewMessage = { "${color.name} is not supported by current theme" }
    ) {
        CompositionLocalProvider(value = LocalColorMode provides color.mode) {
            // Filter the background modifiers in order to force the background color
            // We could theoretically apply the background color after the modifier but in practise a hairline is still visible
            val filteredModifier = modifier.filter { it::class.simpleName != "BackgroundElement" }
            Box(
                modifier = Modifier
                    .background(color.value) // Set the background color first, otherwise padding (if any) is wrongly applied
                    .then(filteredModifier),
                contentAlignment = contentAlignment,
                propagateMinConstraints = propagateMinConstraints,
                content = {
                    OudsThemeTweak(color.mode.tweak) {
                        content()
                    }
                }
            )
        }
    }
}

/**
 * Contains classes to build an [OudsColoredBox].
 */
object OudsColoredBox {

    /**
     * Represents the possible background colors of an [OudsColoredBox].
     */
    enum class Color {
        BackgroundEmphasized,
        BackgroundPrimary,
        BackgroundSecondary,
        BackgroundTertiary,
        BrandPrimary,
        BrandSecondary,
        BrandTertiary,
        OverlayDefault,
        OverlayEmphasized,
        OverlayModal,
        StatusAccentEmphasized,
        StatusAccentMuted,
        StatusInfoEmphasized,
        StatusInfoMuted,
        StatusNegativeEmphasized,
        StatusNegativeMuted,
        StatusNeutralEmphasized,
        StatusNeutralMuted,
        StatusPositiveEmphasized,
        StatusPositiveMuted,
        StatusWarningEmphasized,
        StatusWarningMuted;

        private companion object {

            // These methods are unused but they allows to be notified with a build error if background, overlay or surface key tokens are updated
            fun fromKeyToken(keyToken: OudsColorKeyToken.Background): Color {
                return when (keyToken) {
                    OudsColorKeyToken.Background.Emphasized -> BackgroundEmphasized
                    OudsColorKeyToken.Background.Primary -> BackgroundPrimary
                    OudsColorKeyToken.Background.Secondary -> BackgroundSecondary
                    OudsColorKeyToken.Background.Tertiary -> BackgroundTertiary
                }
            }

            fun fromKeyToken(keyToken: OudsColorKeyToken.Overlay): Color {
                return when (keyToken) {
                    OudsColorKeyToken.Overlay.Default -> OverlayDefault
                    OudsColorKeyToken.Overlay.Drag -> error("OudsColoredBox does not support this color.")
                    OudsColorKeyToken.Overlay.Emphasized -> OverlayEmphasized
                    OudsColorKeyToken.Overlay.Modal -> OverlayModal
                }
            }

            fun fromKeyToken(keyToken: OudsColorKeyToken.Surface): Color {
                return when (keyToken) {
                    OudsColorKeyToken.Surface.Brand.Primary -> BrandPrimary
                    OudsColorKeyToken.Surface.Brand.Secondary -> BrandSecondary
                    OudsColorKeyToken.Surface.Brand.Tertiary -> BrandTertiary
                    OudsColorKeyToken.Surface.Status.Accent.Emphasized -> StatusAccentEmphasized
                    OudsColorKeyToken.Surface.Status.Accent.Muted -> StatusAccentMuted
                    OudsColorKeyToken.Surface.Status.Info.Emphasized -> StatusInfoEmphasized
                    OudsColorKeyToken.Surface.Status.Info.Muted -> StatusInfoMuted
                    OudsColorKeyToken.Surface.Status.Negative.Emphasized -> StatusNegativeEmphasized
                    OudsColorKeyToken.Surface.Status.Negative.Muted -> StatusNegativeMuted
                    OudsColorKeyToken.Surface.Status.Neutral.Emphasized -> StatusNeutralEmphasized
                    OudsColorKeyToken.Surface.Status.Neutral.Muted -> StatusNeutralMuted
                    OudsColorKeyToken.Surface.Status.Positive.Emphasized -> StatusPositiveEmphasized
                    OudsColorKeyToken.Surface.Status.Positive.Muted -> StatusPositiveMuted
                    OudsColorKeyToken.Surface.Status.Warning.Emphasized -> StatusWarningEmphasized
                    OudsColorKeyToken.Surface.Status.Warning.Muted -> StatusWarningMuted
                }
            }
        }

        val value: androidx.compose.ui.graphics.Color
            @Composable
            get() {
                return when (this) {
                    BackgroundEmphasized -> OudsColorKeyToken.Background.Emphasized
                    BackgroundPrimary -> OudsColorKeyToken.Background.Primary
                    BackgroundSecondary -> OudsColorKeyToken.Background.Secondary
                    BackgroundTertiary -> OudsColorKeyToken.Background.Tertiary
                    BrandPrimary -> OudsColorKeyToken.Surface.Brand.Primary
                    BrandSecondary -> OudsColorKeyToken.Surface.Brand.Secondary
                    BrandTertiary -> OudsColorKeyToken.Surface.Brand.Tertiary
                    OverlayDefault -> OudsColorKeyToken.Overlay.Default
                    OverlayEmphasized -> OudsColorKeyToken.Overlay.Emphasized
                    OverlayModal -> OudsColorKeyToken.Overlay.Modal
                    StatusAccentEmphasized -> OudsColorKeyToken.Surface.Status.Accent.Emphasized
                    StatusAccentMuted -> OudsColorKeyToken.Surface.Status.Accent.Muted
                    StatusInfoEmphasized -> OudsColorKeyToken.Surface.Status.Info.Emphasized
                    StatusInfoMuted -> OudsColorKeyToken.Surface.Status.Info.Muted
                    StatusNegativeEmphasized -> OudsColorKeyToken.Surface.Status.Negative.Emphasized
                    StatusNegativeMuted -> OudsColorKeyToken.Surface.Status.Negative.Muted
                    StatusNeutralEmphasized -> OudsColorKeyToken.Surface.Status.Neutral.Emphasized
                    StatusNeutralMuted -> OudsColorKeyToken.Surface.Status.Neutral.Muted
                    StatusPositiveEmphasized -> OudsColorKeyToken.Surface.Status.Positive.Emphasized
                    StatusPositiveMuted -> OudsColorKeyToken.Surface.Status.Positive.Muted
                    StatusWarningEmphasized -> OudsColorKeyToken.Surface.Status.Warning.Emphasized
                    StatusWarningMuted -> OudsColorKeyToken.Surface.Status.Warning.Muted
                }.value
            }

        val mode: OudsColorMode
            @Composable
            get() {
                return with(OudsTheme.colorScheme.modes) {
                    when (this@Color) {
                        BackgroundEmphasized -> onBackground.emphasized
                        BackgroundPrimary -> onBackground.primary
                        BackgroundSecondary -> onBackground.secondary
                        BackgroundTertiary -> onBackground.tertiary
                        BrandPrimary -> onBrand.primary
                        BrandSecondary -> onBrand.secondary
                        BrandTertiary -> onBrand.tertiary
                        OverlayDefault -> onOverlay.default
                        OverlayEmphasized -> onOverlay.emphasized
                        OverlayModal -> onOverlay.modal
                        StatusAccentEmphasized -> onStatus.accent.emphasized
                        StatusAccentMuted -> onStatus.accent.muted
                        StatusInfoEmphasized -> onStatus.info.emphasized
                        StatusInfoMuted -> onStatus.info.muted
                        StatusNegativeEmphasized -> onStatus.negative.emphasized
                        StatusNegativeMuted -> onStatus.negative.muted
                        StatusNeutralEmphasized -> onStatus.neutral.emphasized
                        StatusNeutralMuted -> onStatus.neutral.muted
                        StatusPositiveEmphasized -> onStatus.positive.emphasized
                        StatusPositiveMuted -> onStatus.positive.muted
                        StatusWarningEmphasized -> onStatus.warning.emphasized
                        StatusWarningMuted -> onStatus.warning.muted
                    }
                }
            }
    }
}

@Suppress("PreviewShouldNotBeCalledRecursively")
@PreviewLightDark
@Composable
private fun PreviewOudsColoredBox(@PreviewParameter(OudsColoredBoxPreviewParameterProvider::class) parameter: OudsColoredBox.Color) {
    PreviewOudsColoredBox(darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsColoredBox(
    darkThemeEnabled: Boolean,
    parameter: OudsColoredBox.Color
) = OudsPreview(modifier = Modifier.padding(16.dp), darkThemeEnabled = darkThemeEnabled) {
    OudsColoredBox(color = parameter) {
        Column(
            modifier = Modifier.padding(all = OudsTheme.spaces.fixed.medium),
            verticalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.medium)
        ) {
            Text(
                text = parameter.name,
                color = OudsTheme.colorScheme.content.default
            )
            OudsButton(label = "Button", onClick = {})
            OudsLink(
                label = "Link",
                arrow = OudsLink.Arrow.Next,
                onClick = {},
            )
        }
    }
}

internal class OudsColoredBoxPreviewParameterProvider : EnumPreviewParameterProvider(OudsColoredBox.Color::class.java)

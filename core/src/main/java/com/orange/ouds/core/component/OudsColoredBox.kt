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
import com.orange.ouds.core.theme.LocalColoredBox
import com.orange.ouds.core.theme.LocalUseMonoComponents
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.OudsThemeTweak
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.utilities.EnumPreviewParameterProvider
import com.orange.ouds.theme.tokens.OudsColorKeyToken
import com.orange.ouds.theme.tokens.components.OudsButtonTokens

/**
 * An OUDS colored box is a [Box] where content color is automatically adjusted to maximize the contrast with the chosen background [color].
 *
 * Moreover, the colors of several OUDS components (for instance [OudsButton] or [OudsLink]) are also automatically adjusted.
 * Some tokens associated with these specific colors can be customized and are identified with the `Mono` suffix (for instance [OudsButtonTokens.colorBgDefaultEnabledMono]).
 *
 * @param color The background color.
 * @param modifier Modifier to be applied to the layout corresponding to the colored box.
 * @param contentAlignment The default alignment inside the colored box.
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
    CompositionLocalProvider(
        LocalColoredBox provides true,
        LocalUseMonoComponents provides useMonoComponents(color)
    ) {
        // Filter the background modifiers in order to force the background color
        // We could theoretically apply the background color after the modifier but in practise a hairline is still visible
        val filteredModifier = modifier.foldIn<Modifier>(Modifier) { result, element ->
            if (element::class.simpleName != "BackgroundElement") result.then(element) else result
        }
        Box(
            modifier = Modifier
                .background(color.value) // Set the background color first, otherwise padding (if any) is wrongly applied
                .then(filteredModifier),
            contentAlignment = contentAlignment,
            propagateMinConstraints = propagateMinConstraints,
            content = {
                OudsThemeTweak(tweak(color)) {
                    content()
                }
            }
        )
    }
}

/**
 * Contains classes to build an [com.orange.ouds.core.component.coloredbox.OudsColoredBox].
 */
object OudsColoredBox {

    /**
     * Represents the possible background colors of an [OudsColoredBox].
     */
    enum class Color {
        BrandPrimary,
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

        companion object {

            // This method is unused but it allows to be notified with a build error if surface key tokens are updated
            private fun fromKeyToken(keyToken: OudsColorKeyToken.Surface): Color {
                return when (keyToken) {
                    OudsColorKeyToken.Surface.Brand.Primary -> BrandPrimary
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
                    BrandPrimary -> OudsColorKeyToken.Surface.Brand.Primary
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
    }
}

private fun useMonoComponents(color: OudsColoredBox.Color): Boolean {
    return when (color) {
        OudsColoredBox.Color.BrandPrimary,
        OudsColoredBox.Color.StatusAccentEmphasized,
        OudsColoredBox.Color.StatusInfoEmphasized,
        OudsColoredBox.Color.StatusPositiveEmphasized,
        OudsColoredBox.Color.StatusWarningEmphasized,
        OudsColoredBox.Color.StatusNegativeEmphasized -> true
        OudsColoredBox.Color.StatusNeutralEmphasized,
        OudsColoredBox.Color.StatusAccentMuted,
        OudsColoredBox.Color.StatusInfoMuted,
        OudsColoredBox.Color.StatusNegativeMuted,
        OudsColoredBox.Color.StatusPositiveMuted,
        OudsColoredBox.Color.StatusNeutralMuted,
        OudsColoredBox.Color.StatusWarningMuted -> false
    }
}

@Composable
private fun tweak(color: OudsColoredBox.Color): OudsTheme.Tweak {
    return when (color) {
        OudsColoredBox.Color.BrandPrimary,
        OudsColoredBox.Color.StatusAccentEmphasized,
        OudsColoredBox.Color.StatusInfoEmphasized,
        OudsColoredBox.Color.StatusPositiveEmphasized,
        OudsColoredBox.Color.StatusWarningEmphasized -> OudsTheme.Tweak.ForceLight
        OudsColoredBox.Color.StatusNegativeEmphasized,
        OudsColoredBox.Color.StatusNeutralEmphasized -> if (isSystemInDarkTheme()) OudsTheme.Tweak.ForceLight else OudsTheme.Tweak.ForceDark
        OudsColoredBox.Color.StatusAccentMuted,
        OudsColoredBox.Color.StatusInfoMuted,
        OudsColoredBox.Color.StatusNegativeMuted,
        OudsColoredBox.Color.StatusPositiveMuted,
        OudsColoredBox.Color.StatusNeutralMuted,
        OudsColoredBox.Color.StatusWarningMuted -> if (isSystemInDarkTheme()) OudsTheme.Tweak.ForceDark else OudsTheme.Tweak.ForceLight
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
            OudsButton(text = "OudsButton", onClick = {})
            OudsLink(
                text = "Link",
                arrow = OudsLink.Arrow.Next,
                onClick = { },
            )
        }
    }
}

internal class OudsColoredBoxPreviewParameterProvider : EnumPreviewParameterProvider(OudsColoredBox.Color::class.java)


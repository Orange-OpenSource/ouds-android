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

package com.orange.ouds.core.component.coloredbox

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.component.button.OudsButton
import com.orange.ouds.core.theme.LocalColoredBox
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.OudsThemeTweak
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.foundation.utilities.UiModePreviews
import com.orange.ouds.theme.tokens.OudsColorKeyToken
import com.orange.ouds.theme.tokens.OudsSpaceKeyToken

/**
 * An OUDS colored box is a [Box] where content color is automatically adjusted to maximize the contrast with the chosen background [color].
 *
 * Moreover, the colors of several OUDS components (for instance [OudsButton]) are also automatically adjusted.
 * Some tokens associated with these colors can be customized and are identified with the `Mono` suffix (for instance `colorBgDefaultEnabledMono` in `OudsButtonTokens`).
 *
 * @param color The background color.
 * @param modifier Modifier to be applied to the layout corresponding to the colored box.
 * @param contentAlignment The default alignment inside the colored box.
 * @param propagateMinConstraints Whether the incoming min constraints should be passed to content.
 * @param content The content of this colored box.
 *
 * @sample com.orange.ouds.core.component.samples.SimpleOudsColoredBox
 */
@Composable
fun OudsColoredBox(
    color: OudsColorKeyToken.Surface,
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    propagateMinConstraints: Boolean = false,
    content: @Composable BoxScope.() -> Unit
) {
    CompositionLocalProvider(
        LocalColoredBox provides true
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
                    OudsThemeTweak(tweakFor(color)) {
                        content()
                    }
                }
            )
    }
}

@Composable
private fun tweakFor(color: OudsColorKeyToken.Surface): OudsTheme.Tweak {
    return when (color) {
        OudsColorKeyToken.Surface.Brand.Primary,
        OudsColorKeyToken.Surface.Status.Accent.Emphasized,
        OudsColorKeyToken.Surface.Status.Info.Emphasized,
        OudsColorKeyToken.Surface.Status.Positive.Emphasized,
        OudsColorKeyToken.Surface.Status.Warning.Emphasized -> OudsTheme.Tweak.ForceLight
        OudsColorKeyToken.Surface.Status.Negative.Emphasized,
        OudsColorKeyToken.Surface.Status.Neutral.Emphasized -> if (isSystemInDarkTheme()) OudsTheme.Tweak.ForceLight else OudsTheme.Tweak.ForceDark
        OudsColorKeyToken.Surface.Status.Accent.Muted,
        OudsColorKeyToken.Surface.Status.Info.Muted,
        OudsColorKeyToken.Surface.Status.Negative.Muted,
        OudsColorKeyToken.Surface.Status.Positive.Muted,
        OudsColorKeyToken.Surface.Status.Neutral.Muted,
        OudsColorKeyToken.Surface.Status.Warning.Muted ->  if (isSystemInDarkTheme()) OudsTheme.Tweak.ForceDark else OudsTheme.Tweak.ForceLight
    }
}

@Suppress("PreviewShouldNotBeCalledRecursively")
@UiModePreviews.Default
@Composable
private fun PreviewOudsColoredBox(@PreviewParameter(OudsColoredBoxPreviewParameterProvider::class) parameter: OudsColorKeyToken.Surface) {
    PreviewOudsColoredBox(darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsColoredBox(
    darkThemeEnabled: Boolean,
    parameter: OudsColorKeyToken.Surface
) = OudsPreview(modifier = Modifier.padding(16.dp), darkThemeEnabled = darkThemeEnabled) {
    OudsColoredBox(color = parameter) {
        Column(
            modifier = Modifier.padding(all = OudsSpaceKeyToken.Fixed.Medium.value),
            verticalArrangement = Arrangement.spacedBy(OudsSpaceKeyToken.Fixed.Medium.value)
        ) {
            Text(
                text = parameter.name.removePrefix("OudsColorKeyToken."),
                color = OudsColorKeyToken.Content.Default.value
            )
            OudsButton(text = "OudsButton", onClick = {})
        }
    }
}

internal class OudsColoredBoxPreviewParameterProvider : BasicPreviewParameterProvider<OudsColorKeyToken.Surface>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsColorKeyToken.Surface>
    get() = listOf(
        OudsColorKeyToken.Surface.Brand.Primary,
        OudsColorKeyToken.Surface.Status.Accent.Emphasized,
        OudsColorKeyToken.Surface.Status.Accent.Muted,
        OudsColorKeyToken.Surface.Status.Info.Emphasized,
        OudsColorKeyToken.Surface.Status.Info.Muted,
        OudsColorKeyToken.Surface.Status.Negative.Emphasized,
        OudsColorKeyToken.Surface.Status.Negative.Muted,
        OudsColorKeyToken.Surface.Status.Neutral.Emphasized,
        OudsColorKeyToken.Surface.Status.Neutral.Muted,
        OudsColorKeyToken.Surface.Status.Positive.Emphasized,
        OudsColorKeyToken.Surface.Status.Positive.Muted,
        OudsColorKeyToken.Surface.Status.Warning.Emphasized,
        OudsColorKeyToken.Surface.Status.Warning.Muted,
    )

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

package com.orange.ouds.core.component.contrastedsurface

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ouds.core.component.button.OudsButton
import com.orange.ouds.core.theme.LocalContrastedSurface
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.foundation.utilities.UiModePreviews
import com.orange.ouds.theme.tokens.OudsColorKeyToken
import com.orange.ouds.theme.tokens.OudsSpaceKeyToken

/**
 * An OUDS colored surface is a [Surface] where content color is automatically adjusted to maximize the contrast with the chosen background [color].
 *
 * Moreover, the colors of several OUDS components (for instance [OudsButton]) are also automatically adjusted.
 * Some tokens associated with these colors can be customized and are identified with the `Mono` suffix (for instance `colorBgDefaultEnabledMono` in `OudsButtonTokens`).

 * @param color The background color.
 * @param modifier Modifier to be applied to the layout corresponding to the colored surface.
 * @param shape Defines the surface's shape as well its shadow.
 * @param content The content of this colored surface.
 */
@Composable
fun OudsContrastedSurface(
    color: OudsColorKeyToken.Surface,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalContrastedSurface provides color) {
        Surface(
            modifier = modifier,
            shape = shape,
            color = color.value,
            contentColor = contentColorFor(color),
            content = content
        )
    }
}

@Composable
private fun contentColorFor(color: OudsColorKeyToken.Surface): Color {
    return when (color) {
        OudsColorKeyToken.Surface.Brand.Primary -> OudsColorKeyToken.Content.OnBrand.Primary
        OudsColorKeyToken.Surface.Status.Accent.Emphasized,
        OudsColorKeyToken.Surface.Status.Info.Emphasized,
        OudsColorKeyToken.Surface.Status.Negative.Emphasized,
        OudsColorKeyToken.Surface.Status.Positive.Emphasized,
        OudsColorKeyToken.Surface.Status.Warning.Emphasized -> OudsColorKeyToken.Content.OnStatus.Emphasized
        OudsColorKeyToken.Surface.Status.Neutral.Emphasized -> OudsColorKeyToken.Content.OnStatus.EmphasizedNeutral
        OudsColorKeyToken.Surface.Status.Accent.Muted,
        OudsColorKeyToken.Surface.Status.Info.Muted,
        OudsColorKeyToken.Surface.Status.Negative.Muted,
        OudsColorKeyToken.Surface.Status.Positive.Muted,
        OudsColorKeyToken.Surface.Status.Neutral.Muted,
        OudsColorKeyToken.Surface.Status.Warning.Muted -> OudsColorKeyToken.Content.OnStatus.Muted
    }.value
}

@Suppress("PreviewShouldNotBeCalledRecursively")
@UiModePreviews.Default
@Composable
private fun PreviewOudsContrastedSurface(@PreviewParameter(OudsContrastedSurfacePreviewParameterProvider::class) parameter: OudsColorKeyToken.Surface) {
    PreviewOudsContrastedSurface(darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsContrastedSurface(darkThemeEnabled: Boolean, parameter: OudsColorKeyToken.Surface) = OudsPreview(darkThemeEnabled = darkThemeEnabled) {
    OudsContrastedSurface(color = parameter) {
        Text(
            modifier = Modifier.padding(all = OudsSpaceKeyToken.Fixed.Medium.value),
            text = parameter.name.removePrefix("OudsColorKeyToken."),
        )
    }
}

internal class OudsContrastedSurfacePreviewParameterProvider : BasicPreviewParameterProvider<OudsColorKeyToken.Surface>(*previewParameterValues.toTypedArray())

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

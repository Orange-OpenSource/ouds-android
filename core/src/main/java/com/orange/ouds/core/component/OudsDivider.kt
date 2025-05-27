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

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.utilities.EnumPreviewParameterProvider

//TODO Add DSM link when available
// <a href="https://unified-design-system.orange.com/" class="external" target="_blank">**OUDS Divider design guidelines**</a>
/**
 * Dividers are used to visually structure an interface by clearly separating content sections. It helps to improve readability and content organization
 * without introducing a strong hierarchy like a heading or a container would.
 *
 * The **horizontal divider** renders an horizontal line to separate stacked vertical sections.
 *
 * The color of the divider can be specified using the [OudsDivider.Color] enum, and the thickness is defined by the current theme's divider border width.
 * Note that a divider border width token set to 0 dp will produce a single pixel divider regardless of screen density.
 *
 * @param modifier [Modifier] applied to the divider.
 * @param color The color of the divider, chosen from the [OudsDivider.Color] enum. Default value set to `OudsDivider.Color.Default`.
 *
 * @sample com.orange.ouds.core.component.samples.OudsHorizontalDividerSample
 */
@Composable
fun OudsHorizontalDivider(
    modifier: Modifier = Modifier,
    color: OudsDivider.Color = OudsDivider.Color.Default
) {
    HorizontalDivider(modifier = modifier, color = color.value, thickness = OudsTheme.componentsTokens.divider.borderWidth.value)
}

//TODO Add DSM link when available
// <a href="https://unified-design-system.orange.com/" class="external" target="_blank">**OUDS Divider design guidelines**</a>
/**
 * Dividers are used to visually structure an interface by clearly separating content sections. It helps to improve readability and content organization
 * without introducing a strong hierarchy like a heading or a container would.
 *
 * The **vertical divider** renders an vertical line to separate horizontally aligned elements.
 *
 * The color of the divider can be specified using the [OudsDivider.Color] enum, and the thickness is defined by the current theme's divider border width.
 * Note that a divider border width token set to 0 dp will produce a single pixel divider regardless of screen density.
 *
 * @param modifier [Modifier] applied to the divider.
 * @param color The color of the divider, chosen from the [OudsDivider.Color] enum. Default value set to `OudsDivider.Color.Default`.
 *
 * @sample com.orange.ouds.core.component.samples.OudsVerticalDividerSample
 */
@Composable
fun OudsVerticalDivider(
    modifier: Modifier = Modifier,
    color: OudsDivider.Color = OudsDivider.Color.Default
) {
    VerticalDivider(modifier = modifier, color = color.value, thickness = OudsTheme.componentsTokens.divider.borderWidth.value)
}

/**
 * Contains classes to build an [OudsHorizontalDivider] or an [OudsVerticalDivider].
 */
object OudsDivider {

    /**
     * Represents the possible colors for an [OudsHorizontalDivider] or an [OudsVerticalDivider].
     * Each color corresponds to a specific color key token from the Design System.
     */
    enum class Color {
        Default,
        Muted,
        Emphasized,
        BrandPrimary,
        OnBrandPrimary,
        AlwaysBlack,
        AlwaysOnBlack,
        AlwaysWhite,
        AlwaysOnWhite;

        val value: androidx.compose.ui.graphics.Color
            @Composable
            get() {
                with(OudsTheme.colorScheme) {
                    return when (this@Color) {
                        Default -> border.default
                        Muted -> border.muted
                        Emphasized -> border.emphasized
                        OnBrandPrimary -> border.onBrand.primary
                        BrandPrimary -> border.brandPrimary
                        AlwaysBlack -> always.black
                        AlwaysOnBlack -> always.onBlack
                        AlwaysWhite -> always.white
                        AlwaysOnWhite -> always.onWhite
                    }
                }
            }
    }
}

internal enum class DividerOrientation {
    Horizontal, Vertical
}

@PreviewLightDark
@Composable
private fun PreviewOudsHorizontalDivider(@PreviewParameter(OudsDividerPreviewParameterProvider::class) color: OudsDivider.Color) {
    PreviewOudsDivider(darkThemeEnabled = isSystemInDarkTheme(), orientation = DividerOrientation.Horizontal, color = color)
}

@PreviewLightDark
@Composable
private fun PreviewOudsVerticalDivider(@PreviewParameter(OudsDividerPreviewParameterProvider::class) color: OudsDivider.Color) {
    PreviewOudsDivider(darkThemeEnabled = isSystemInDarkTheme(), orientation = DividerOrientation.Vertical, color = color)
}

@Composable
internal fun PreviewOudsDivider(
    darkThemeEnabled: Boolean,
    orientation: DividerOrientation,
    color: OudsDivider.Color
) = OudsPreview(modifier = Modifier.padding(16.dp), darkThemeEnabled = darkThemeEnabled) {
    val length = 100.dp
    when (orientation) {
        DividerOrientation.Horizontal -> OudsHorizontalDivider(modifier = Modifier.width(length), color = color)
        DividerOrientation.Vertical -> OudsVerticalDivider(modifier = Modifier.height(length), color = color)
    }
}

internal class OudsDividerPreviewParameterProvider : EnumPreviewParameterProvider(OudsDivider.Color::class.java)

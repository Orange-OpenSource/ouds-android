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

import androidx.compose.ui.graphics.takeOrElse
import com.orange.ouds.theme.tokens.components.OudsLightDarkColor
import com.orange.ouds.theme.whitelabel.tokens.material.WhiteLabelMaterialColorTokens
import com.orange.ouds.theme.wireframe.tokens.material.WireframeMaterialColorTokens

/**
 * Customizable Material 3 color scheme for White Label theme.
 *
 * Allows customization of Material 3 color properties used by Jetpack Compose Material components.
 * Unspecified colors fall back to WireframeTheme Material colors.
 *
 * ## Usage Example
 *
 * ```
 * val customMaterialColors = WhiteLabelMaterialColorScheme(
 *     primary = OudsLightDarkColor(Color(0xFF6200EE), Color(0xFFBB86FC)),
 *     primaryContainer = OudsLightDarkColor(Color(0xFFE1BEE7), Color(0xFF3700B3)),
 *     onPrimary = OudsLightDarkColor(Color.White, Color.Black),
 *     surface = OudsLightDarkColor(Color.White, Color(0xFF121212)),
 *     onSurface = OudsLightDarkColor(Color.Black, Color.White)
 * )
 *
 * WhiteLabelTheme(materialColorScheme = customMaterialColors)
 * ```
 *
 * @param primary The primary color for main app actions.
 * @param onPrimary Color for text and icons displayed on the primary color.
 * @param primaryContainer Background color for components using primary color.
 * @param onPrimaryContainer Color for text and icons on primaryContainer.
 * @param inversePrimary Primary color inverted for inverse surfaces.
 * @param secondary The secondary color for less prominent actions.
 * @param onSecondary Color for text and icons on secondary color.
 * @param secondaryContainer Background color for components using secondary color.
 * @param onSecondaryContainer Color for text and icons on secondaryContainer.
 * @param tertiary Accent color for contrasting elements.
 * @param onTertiary Color for text and icons on tertiary color.
 * @param tertiaryContainer Background color for components using tertiary color.
 * @param onTertiaryContainer Color for text and icons on tertiaryContainer.
 * @param background The app background color.
 * @param onBackground Color for text and icons on background.
 * @param surface Background color for surfaces like cards and sheets.
 * @param onSurface Color for text and icons on surface.
 * @param surfaceVariant Alternative surface color for subtle differentiation.
 * @param onSurfaceVariant Color for text and icons on surfaceVariant.
 * @param surfaceTint Color used to tint surfaces.
 * @param inverseSurface Surface color for inverse surfaces.
 * @param inverseOnSurface Color for text and icons on inverseSurface.
 * @param error Color for error states and destructive actions.
 * @param onError Color for text and icons on error color.
 * @param errorContainer Background color for error components.
 * @param onErrorContainer Color for text and icons on errorContainer.
 * @param outline Color for borders and dividers.
 * @param outlineVariant Alternative outline color for subtle differentiation.
 * @param scrim Color for scrims (semi-transparent overlays).
 * @param surfaceBright Brighter surface color variant.
 * @param surfaceDim Dimmer surface color variant.
 * @param surfaceContainer Default container surface color.
 * @param surfaceContainerHigh High-emphasis container surface color.
 * @param surfaceContainerHighest Highest-emphasis container surface color.
 * @param surfaceContainerLow Low-emphasis container surface color.
 * @param surfaceContainerLowest Lowest-emphasis container surface color.
 * @param primaryFixed Primary color that doesn't change with theme.
 * @param primaryFixedDim Dimmed variant of primaryFixed.
 * @param onPrimaryFixed Color for text and icons on primaryFixed.
 * @param onPrimaryFixedVariant Alternative color for content on primaryFixed.
 * @param secondaryFixed Secondary color that doesn't change with theme.
 * @param secondaryFixedDim Dimmed variant of secondaryFixed.
 * @param onSecondaryFixed Color for text and icons on secondaryFixed.
 * @param onSecondaryFixedVariant Alternative color for content on secondaryFixed.
 * @param tertiaryFixed Tertiary color that doesn't change with theme.
 * @param tertiaryFixedDim Dimmed variant of tertiaryFixed.
 * @param onTertiaryFixed Color for text and icons on tertiaryFixed.
 * @param onTertiaryFixedVariant Alternative color for content on tertiaryFixed.
 */
class WhiteLabelMaterialColorScheme(
    val primary: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val onPrimary: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val primaryContainer: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val onPrimaryContainer: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val inversePrimary: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val secondary: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val onSecondary: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val secondaryContainer: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val onSecondaryContainer: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val tertiary: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val onTertiary: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val tertiaryContainer: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val onTertiaryContainer: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val background: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val onBackground: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val surface: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val onSurface: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val surfaceVariant: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val onSurfaceVariant: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val surfaceTint: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val inverseSurface: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val inverseOnSurface: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val error: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val onError: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val errorContainer: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val onErrorContainer: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val outline: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val outlineVariant: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val scrim: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val surfaceBright: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val surfaceDim: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val surfaceContainer: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val surfaceContainerHigh: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val surfaceContainerHighest: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val surfaceContainerLow: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val surfaceContainerLowest: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val primaryFixed: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val primaryFixedDim: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val onPrimaryFixed: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val onPrimaryFixedVariant: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val secondaryFixed: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val secondaryFixedDim: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val onSecondaryFixed: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val onSecondaryFixedVariant: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val tertiaryFixed: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val tertiaryFixedDim: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val onTertiaryFixed: OudsLightDarkColor = OudsLightDarkColor.Unspecified,
    val onTertiaryFixedVariant: OudsLightDarkColor = OudsLightDarkColor.Unspecified
)

internal val WhiteLabelMaterialColorScheme.materialColorTokens: WhiteLabelMaterialColorTokens
    get() = with(WireframeMaterialColorTokens()) {
        WhiteLabelMaterialColorTokens(
            backgroundDark = background.dark.takeOrElse { backgroundDark },
            backgroundLight = background.light.takeOrElse { backgroundLight },
            errorContainerDark = errorContainer.dark.takeOrElse { errorContainerDark },
            errorContainerLight = errorContainer.light.takeOrElse { errorContainerLight },
            errorDark = error.dark.takeOrElse { errorDark },
            errorLight = error.light.takeOrElse { errorLight },
            inverseOnSurfaceDark = inverseOnSurface.dark.takeOrElse { inverseOnSurfaceDark },
            inverseOnSurfaceLight = inverseOnSurface.light.takeOrElse { inverseOnSurfaceLight },
            inversePrimaryDark = inversePrimary.dark.takeOrElse { inversePrimaryDark },
            inversePrimaryLight = inversePrimary.light.takeOrElse { inversePrimaryLight },
            inverseSurfaceDark = inverseSurface.dark.takeOrElse { inverseSurfaceDark },
            inverseSurfaceLight = inverseSurface.light.takeOrElse { inverseSurfaceLight },
            onBackgroundDark = onBackground.dark.takeOrElse { onBackgroundDark },
            onBackgroundLight = onBackground.light.takeOrElse { onBackgroundLight },
            onErrorContainerDark = onErrorContainer.dark.takeOrElse { onErrorContainerDark },
            onErrorContainerLight = onErrorContainer.light.takeOrElse { onErrorContainerLight },
            onErrorDark = onError.dark.takeOrElse { onErrorDark },
            onErrorLight = onError.light.takeOrElse { onErrorLight },
            onPrimaryContainerDark = onPrimaryContainer.dark.takeOrElse { onPrimaryContainerDark },
            onPrimaryContainerLight = onPrimaryContainer.light.takeOrElse { onPrimaryContainerLight },
            onPrimaryDark = onPrimary.dark.takeOrElse { onPrimaryDark },
            onPrimaryFixedDark = onPrimaryFixed.dark.takeOrElse { onPrimaryFixedDark },
            onPrimaryFixedLight = onPrimaryFixed.light.takeOrElse { onPrimaryFixedLight },
            onPrimaryFixedVariantDark = onPrimaryFixedVariant.dark.takeOrElse { onPrimaryFixedVariantDark },
            onPrimaryFixedVariantLight = onPrimaryFixedVariant.light.takeOrElse { onPrimaryFixedVariantLight },
            onPrimaryLight = onPrimary.light.takeOrElse { onPrimaryLight },
            onSecondaryContainerDark = onSecondaryContainer.dark.takeOrElse { onSecondaryContainerDark },
            onSecondaryContainerLight = onSecondaryContainer.light.takeOrElse { onSecondaryContainerLight },
            onSecondaryDark = onSecondary.dark.takeOrElse { onSecondaryDark },
            onSecondaryFixedDark = onSecondaryFixed.dark.takeOrElse { onSecondaryFixedDark },
            onSecondaryFixedLight = onSecondaryFixed.light.takeOrElse { onSecondaryFixedLight },
            onSecondaryFixedVariantDark = onSecondaryFixedVariant.dark.takeOrElse { onSecondaryFixedVariantDark },
            onSecondaryFixedVariantLight = onSecondaryFixedVariant.light.takeOrElse { onSecondaryFixedVariantLight },
            onSecondaryLight = onSecondary.light.takeOrElse { onSecondaryLight },
            onSurfaceDark = onSurface.dark.takeOrElse { onSurfaceDark },
            onSurfaceLight = onSurface.light.takeOrElse { onSurfaceLight },
            onSurfaceVariantDark = onSurfaceVariant.dark.takeOrElse { onSurfaceVariantDark },
            onSurfaceVariantLight = onSurfaceVariant.light.takeOrElse { onSurfaceVariantLight },
            onTertiaryContainerDark = onTertiaryContainer.dark.takeOrElse { onTertiaryContainerDark },
            onTertiaryContainerLight = onTertiaryContainer.light.takeOrElse { onTertiaryContainerLight },
            onTertiaryDark = onTertiary.dark.takeOrElse { onTertiaryDark },
            onTertiaryFixedDark = onTertiaryFixed.dark.takeOrElse { onTertiaryFixedDark },
            onTertiaryFixedLight = onTertiaryFixed.light.takeOrElse { onTertiaryFixedLight },
            onTertiaryFixedVariantDark = onTertiaryFixedVariant.dark.takeOrElse { onTertiaryFixedVariantDark },
            onTertiaryFixedVariantLight = onTertiaryFixedVariant.light.takeOrElse { onTertiaryFixedVariantLight },
            onTertiaryLight = onTertiary.light.takeOrElse { onTertiaryLight },
            outlineDark = outline.dark.takeOrElse { outlineDark },
            outlineLight = outline.light.takeOrElse { outlineLight },
            outlineVariantDark = outlineVariant.dark.takeOrElse { outlineVariantDark },
            outlineVariantLight = outlineVariant.light.takeOrElse { outlineVariantLight },
            primaryContainerDark = primaryContainer.dark.takeOrElse { primaryContainerDark },
            primaryContainerLight = primaryContainer.light.takeOrElse { primaryContainerLight },
            primaryDark = primary.dark.takeOrElse { primaryDark },
            primaryFixedDark = primaryFixed.dark.takeOrElse { primaryFixedDark },
            primaryFixedDimDark = primaryFixedDim.dark.takeOrElse { primaryFixedDimDark },
            primaryFixedDimLight = primaryFixedDim.light.takeOrElse { primaryFixedDimLight },
            primaryFixedLight = primaryFixed.light.takeOrElse { primaryFixedLight },
            primaryLight = primary.light.takeOrElse { primaryLight },
            scrimDark = scrim.dark.takeOrElse { scrimDark },
            scrimLight = scrim.light.takeOrElse { scrimLight },
            secondaryContainerDark = secondaryContainer.dark.takeOrElse { secondaryContainerDark },
            secondaryContainerLight = secondaryContainer.light.takeOrElse { secondaryContainerLight },
            secondaryDark = secondary.dark.takeOrElse { secondaryDark },
            secondaryFixedDark = secondaryFixed.dark.takeOrElse { secondaryFixedDark },
            secondaryFixedDimDark = secondaryFixedDim.dark.takeOrElse { secondaryFixedDimDark },
            secondaryFixedDimLight = secondaryFixedDim.light.takeOrElse { secondaryFixedDimLight },
            secondaryFixedLight = secondaryFixed.light.takeOrElse { secondaryFixedLight },
            secondaryLight = secondary.light.takeOrElse { secondaryLight },
            surfaceBrightDark = surfaceBright.dark.takeOrElse { surfaceBrightDark },
            surfaceBrightLight = surfaceBright.light.takeOrElse { surfaceBrightLight },
            surfaceDark = surface.dark.takeOrElse { surfaceDark },
            surfaceDimDark = surfaceDim.dark.takeOrElse { surfaceDimDark },
            surfaceDimLight = surfaceDim.light.takeOrElse { surfaceDimLight },
            surfaceLight = surface.light.takeOrElse { surfaceLight },
            surfaceTintDark = surfaceTint.dark.takeOrElse { surfaceTintDark },
            surfaceTintLight = surfaceTint.light.takeOrElse { surfaceTintLight },
            surfaceVariantDark = surfaceVariant.dark.takeOrElse { surfaceVariantDark },
            surfaceVariantLight = surfaceVariant.light.takeOrElse { surfaceVariantLight },
            surfContainerDark = surfaceContainer.dark.takeOrElse { surfContainerDark },
            surfContainerHighDark = surfaceContainerHigh.dark.takeOrElse { surfContainerHighDark },
            surfContainerHighestDark = surfaceContainerHighest.dark.takeOrElse { surfContainerHighestDark },
            surfContainerHighestLight = surfaceContainerHighest.light.takeOrElse { surfContainerHighestLight },
            surfContainerHighLight = surfaceContainerHigh.light.takeOrElse { surfContainerHighLight },
            surfContainerLight = surfaceContainer.light.takeOrElse { surfContainerLight },
            surfContainerLowDark = surfaceContainerLow.dark.takeOrElse { surfContainerLowDark },
            surfContainerLowestDark = surfaceContainerLowest.dark.takeOrElse { surfContainerLowestDark },
            surfContainerLowestLight = surfaceContainerLowest.light.takeOrElse { surfContainerLowestLight },
            surfContainerLowLight = surfaceContainerLow.light.takeOrElse { surfContainerLowLight },
            tertiaryContainerDark = tertiaryContainer.dark.takeOrElse { tertiaryContainerDark },
            tertiaryContainerLight = tertiaryContainer.light.takeOrElse { tertiaryContainerLight },
            tertiaryDark = tertiary.dark.takeOrElse { tertiaryDark },
            tertiaryFixedDark = tertiaryFixed.dark.takeOrElse { tertiaryFixedDark },
            tertiaryFixedDimDark = tertiaryFixedDim.dark.takeOrElse { tertiaryFixedDimDark },
            tertiaryFixedDimLight = tertiaryFixedDim.light.takeOrElse { tertiaryFixedDimLight },
            tertiaryFixedLight = tertiaryFixed.light.takeOrElse { tertiaryFixedLight },
            tertiaryLight = tertiary.light.takeOrElse { tertiaryLight }
        )
    }

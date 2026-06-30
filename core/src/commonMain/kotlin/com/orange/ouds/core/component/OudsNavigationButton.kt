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

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ouds.core.theme.LocalDrawableResources
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.OudsPreviewLightDark
import com.orange.ouds.core.utilities.PreviewEnumEntries
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.theme.OudsThemeContract
import org.jetbrains.compose.resources.painterResource

/**
 * Navigation button is a UI element that allows to move between different pages within a multipage interface.
 * Navigation button is typically arrange in sequence to indicate the user's current position and provide controls to access previous, next, or specific pages.
 *
 * Note that if it is placed in an [OudsColoredBox], its monochrome variant is automatically displayed.
 *
 * Rounded corners can be enabled or disabled using the [com.orange.ouds.theme.OudsThemeSettings.roundedCornerButtons] property in the settings of the theme provided
 * when calling the [com.orange.ouds.core.theme.OudsTheme] method.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://r.orange.fr/r/S-ouds-doc-navigation-button)
 *
 * > Design name: Navigation Button
 *
 * > Design version: 3.2.1
 *
 * @param onClick Callback invoked when the button is clicked.
 * @param modifier [Modifier] applied to the button.
 * @param chevron Chevron of the navigation button. See [OudsNavigationButtonChevron] for allowed values.
 * @param label Label displayed in the button describing the navigation action. This makes the action more explicit and accessible especially for new users
 *   or in contexts where clarity is critical.
 * @param enabled Controls the enabled state of the button when there is no [loader].
 *   When `false`, this button will not be clickable.
 *   Has no effect if [loader] is provided.
 * @param loader An optional loading progress indicator displayed in the button to indicate an ongoing operation.
 * @param appearance Appearance of the button among [OudsNavigationButtonAppearance] values.
 *   A button with [OudsNavigationButtonAppearance.Brand] is not allowed as a direct or indirect child of an [OudsColoredBox] and will throw an [IllegalStateException].
 * @param interactionSource An optional hoisted [MutableInteractionSource] for observing and emitting interactions for this button. Note that if `null`
 *   is provided, interactions will still happen internally.
 *
 * @sample com.orange.ouds.core.component.samples.OudsNavigationButtonTextAndIconSample
 * @sample com.orange.ouds.core.component.samples.OudsNavigationButtonIconOnlySample
 */
@Composable
fun OudsNavigationButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    chevron: OudsNavigationButtonChevron = OudsNavigationButtonDefaults.Chevron,
    label: String? = null,
    enabled: Boolean = true,
    loader: OudsButtonLoader? = null,
    appearance: OudsNavigationButtonAppearance = OudsNavigationButtonDefaults.Appearance,
    interactionSource: MutableInteractionSource? = null
) {
    val drawableResources = LocalDrawableResources.current
    val iconResource = when (chevron) {
        OudsNavigationButtonChevron.Next -> drawableResources.component.button.next
        OudsNavigationButtonChevron.Previous -> drawableResources.component.button.previous
    }

    OudsButton(
        nullableIcon = OudsButtonIcon(
            painter = painterResource(resource = iconResource),
            contentDescription = "" // Not necessary because a label is present
        ),
        nullableLabel = label,
        onClick = onClick,
        modifier = modifier,
        component = OudsButtonComponent.NavigationButton(chevron),
        enabled = enabled,
        loader = loader,
        appearance = appearance.toButtonAppearance(),
        interactionSource = interactionSource
    )
}

/**
 * Default values for [OudsNavigationButton].
 */
object OudsNavigationButtonDefaults {

    /**
     * Default appearance of an [OudsNavigationButton].
     */
    val Appearance = OudsNavigationButtonAppearance.Default

    /**
     * Default chevron of an [OudsNavigationButton].
     */
    val Chevron = OudsNavigationButtonChevron.Next
}

/**
 * Represents the chevron of an [OudsNavigationButton]. Its position in the button changes depending on its value.
 */
enum class OudsNavigationButtonChevron {
    /**
     * The Next chevron button is used to move the user forward in a sequence of content, steps or pages.
     */
    Next,

    /**
     * The Previous chevron button allows the user to return to the prior step, page, or section.
     */
    Previous
}

/**
 * Represents the appearance of an [OudsNavigationButton].
 */
enum class OudsNavigationButtonAppearance {
    /**
     * Default navigation buttons are used for navigation between pages that are not critical or emphasized.They typically represent inactive page states
     * and support smooth movement across content.
     * Use case: Standard "next/previous" navigation in product listings or search results.
     */
    Default,

    /**
     * The Strong navigation button should be singular and prominent, reserved for highlighting the active page. It ensures the user always knows their current
     * position within a sequence.
     * Use case: Highlighting the active page in long catalog navigation.
     */
    Strong,

    /**
     * A brand-colored alternative to the Strong navigation button. It should be used sparingly for high-value navigation points or to visually anchor a brand
     * moment. Avoid using it as the default for all pages.
     * Use case: Emphasizing a key page (e.g., a promotional offer) with the brand's primary color.
     */
    Brand,

    /**
     * Minimal navigation buttons are simplified and less emphasized, suitable when pagination is not the primary focus. They can be used independently
     * or in combination with stronger buttons.
     * Use case: Secondary interface, such as blogs or FAQs, where pagination is less critical.
     */
    Minimal
}

/**
 * Converts [OudsNavigationButtonAppearance] to [OudsButtonAppearance].
 */
private fun OudsNavigationButtonAppearance.toButtonAppearance(): OudsButtonAppearance {
    return when (this) {
        OudsNavigationButtonAppearance.Default -> OudsButtonAppearance.Default
        OudsNavigationButtonAppearance.Strong -> OudsButtonAppearance.Strong
        OudsNavigationButtonAppearance.Brand -> OudsButtonAppearance.Brand
        OudsNavigationButtonAppearance.Minimal -> OudsButtonAppearance.Minimal
    }
}

@OudsPreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsNavigationButton(@PreviewParameter(OudsNavigationButtonPreviewParameterProvider::class) parameter: OudsNavigationButtonPreviewParameter) {
    PreviewOudsNavigationButton(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsNavigationButton(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsNavigationButtonPreviewParameter
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        val content: @Composable () -> Unit = {
            PreviewEnumEntries<OudsButtonState>(maxEnumEntriesInEachRow = 2) {
                if (hasLabel) {
                    OudsNavigationButton(
                        label = chevron.name,
                        chevron = chevron,
                        onClick = {},
                        appearance = appearance
                    )
                } else {
                    OudsNavigationButton(
                        chevron = chevron,
                        onClick = {},
                        appearance = appearance
                    )
                }
            }
        }
        if (onColoredBox) {
            OudsColoredBox(color = OudsColoredBoxColor.BrandPrimary) {
                content()
            }
        } else {
            content()
        }
    }
}

@OudsPreview
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsNavigationButtonOnTwoLines() = PreviewOudsNavigationButtonOnTwoLines(getPreviewTheme())

@Composable
internal fun PreviewOudsNavigationButtonOnTwoLines(theme: OudsThemeContract) = OudsPreview(theme = theme) {
    OudsNavigationButton(
        label = "Navigation button\non two lines",
        chevron = OudsNavigationButtonChevron.Next,
        onClick = {},
    )
}

internal data class OudsNavigationButtonPreviewParameter(
    val appearance: OudsNavigationButtonAppearance,
    val chevron: OudsNavigationButtonChevron,
    val hasLabel: Boolean,
    val onColoredBox: Boolean = false
)

internal class OudsNavigationButtonPreviewParameterProvider :
    BasicPreviewParameterProvider<OudsNavigationButtonPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsNavigationButtonPreviewParameter>
    get() = buildList {
        OudsNavigationButtonAppearance.entries.forEach { appearance ->
            OudsNavigationButtonChevron.entries.forEach { chevron ->
                val parameters = listOf(
                    OudsNavigationButtonPreviewParameter(appearance, chevron, hasLabel = true),
                    OudsNavigationButtonPreviewParameter(appearance, chevron, hasLabel = false),
                )
                addAll(parameters)
                addAll(parameters.map { it.copy(onColoredBox = true) })
            }
        }
    }

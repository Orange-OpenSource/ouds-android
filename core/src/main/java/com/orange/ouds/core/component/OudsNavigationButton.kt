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

import android.R.attr.label
import androidx.annotation.StringRes
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ouds.core.R
import com.orange.ouds.core.theme.LocalDrawableResources
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.OudsPreviewLightDark
import com.orange.ouds.core.utilities.PreviewEnumEntries
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.theme.OudsThemeContract

/**
 * Navigation button is a UI element that allows to move between different pages within a multipage interface.
 * Navigation button is typically arrange in sequence to indicate the user's current position and provide controls to access previous, next, or specific pages.
 *
 * This version of the navigation button allows to display a label.
 * Another API is available for this component if you only need a navigation chevron icon.
 *
 * Note that if it is placed in an [OudsColoredBox], its monochrome variant is automatically displayed.
 *
 * Rounded corners can be enabled or disabled using the [com.orange.ouds.theme.OudsThemeSettings.roundedCornerButtons] property in the settings of the theme provided
 * when calling the [com.orange.ouds.core.theme.OudsTheme] method.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://r.orange.fr/r/S-ouds-doc-navigation-button)
 *
 * > Design version: 3.2.0
 *
 * @param label Label displayed in the button describing the navigation action. This makes the action more explicit and accessible especially for new users
 *   or in contexts where clarity is critical.
 * @param layout Layout of the navigation button determining the chevron to display and its position in the button. See [OudsNavigationButtonLayout] for allowed values.
 * @param onClick Callback invoked when the button is clicked.
 * @param modifier [Modifier] applied to the button.
 * @param enabled Controls the enabled state of the button when there is no [loader].
 *   When `false`, this button will not be clickable.
 *   Has no effect if [loader] is provided.
 * @param loader An optional loading progress indicator displayed in the button to indicate an ongoing operation.
 * @param appearance Appearance of the button among [OudsNavigationButtonAppearance] values.
 *   A button with [OudsButtonAppearance.Brand] is not allowed as a direct or indirect child of an [OudsColoredBox] and will throw an [IllegalStateException].
 * @param interactionSource An optional hoisted [MutableInteractionSource] for observing and emitting interactions for this button. Note that if `null`
 *   is provided, interactions will still happen internally.
 *
 * @sample com.orange.ouds.core.component.samples.OudsNavigationButtonTextAndIconSample
 */
@Composable
fun OudsNavigationButton(
    label: String,
    layout: OudsNavigationButtonLayout,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loader: OudsButtonLoader? = null,
    appearance: OudsNavigationButtonAppearance = OudsNavigationButtonDefaults.Appearance,
    interactionSource: MutableInteractionSource? = null
) {
    val drawableResources = LocalDrawableResources.current

    val iconResource = when (layout) {
        OudsNavigationButtonLayout.Next -> drawableResources.component.button.next
        OudsNavigationButtonLayout.Previous -> drawableResources.component.button.previous
    }

    OudsButton(
        nullableIcon = OudsButtonIcon(
            painter = painterResource(iconResource),
            contentDescription = "" // Not necessary because a label is present
        ),
        nullableLabel = label,
        onClick = onClick,
        modifier = modifier,
        component = OudsButtonComponent.NavigationButton(layout),
        enabled = enabled,
        loader = loader,
        appearance = appearance.toButtonAppearance(),
        interactionSource = interactionSource
    )
}

/**
 * Navigation button is a UI element that allows to move between different pages within a multipage interface.
 * Navigation button is typically arrange in sequence to indicate the user's current position and provide controls to access previous, next, or specific pages.
 *
 * This version of the navigation button only displays navigation chevron icon.
 * Another API is available for this component if you need to also display a label in the navigation button.
 *
 * Note that if it is placed in an [OudsColoredBox], its monochrome variant is automatically displayed.
 *
 * Rounded corners can be enabled or disabled using the [com.orange.ouds.theme.OudsThemeSettings.roundedCornerButtons] property in the settings of the theme provided
 * when calling the [com.orange.ouds.core.theme.OudsTheme] method.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://r.orange.fr/r/S-ouds-doc-navigation-button)
 *
 * > Design version: 3.2.0
 *
 * @param layout Layout of the navigation button determining the chevron to display. See [OudsNavigationButtonLayout] for allowed values.
 * @param onClick Callback invoked when the button is clicked.
 * @param modifier [Modifier] applied to the button.
 * @param enabled Controls the enabled state of the button when there is no [loader].
 *   When `false`, this button will not be clickable.
 *   Has no effect if [loader] is provided.
 * @param loader An optional loading progress indicator displayed in the button to indicate an ongoing operation.
 * @param appearance Appearance of the button among [OudsNavigationButtonAppearance] values.
 *   A button with [OudsButtonAppearance.Brand] is not allowed as a direct or indirect child of an [OudsColoredBox] and will throw an [IllegalStateException].
 * @param interactionSource An optional hoisted [MutableInteractionSource] for observing and emitting interactions for this button. Note that if `null`
 *   is provided, interactions will still happen internally.
 *
 * @sample com.orange.ouds.core.component.samples.OudsNavigationButtonIconOnlySample
 */
@Composable
fun OudsNavigationButton(
    layout: OudsNavigationButtonLayout,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loader: OudsButtonLoader? = null,
    appearance: OudsNavigationButtonAppearance = OudsNavigationButtonDefaults.Appearance,
    interactionSource: MutableInteractionSource? = null
) {
    val drawableResources = LocalDrawableResources.current

    val iconResource: Int
    @StringRes val iconContentDescriptionResId: Int
    when (layout) {
        OudsNavigationButtonLayout.Next -> {
            iconResource = drawableResources.component.button.next
            iconContentDescriptionResId = R.string.core_navigationButton_next_a11y
        }
        OudsNavigationButtonLayout.Previous -> {
            iconResource = drawableResources.component.button.previous
            iconContentDescriptionResId = R.string.core_navigationButton_previous_a11y
        }
    }

    OudsButton(
        nullableIcon = OudsButtonIcon(
            painter = painterResource(iconResource),
            contentDescription = stringResource(iconContentDescriptionResId)
        ),
        nullableLabel = null,
        onClick = onClick,
        modifier = modifier,
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
}

/**
 * Represents the layout of an [OudsNavigationButton], which determines the icon to display and its position in the button.
 */
enum class OudsNavigationButtonLayout {
    /**
     * The Next layout button is used to move the user forward in a sequence of content, steps or pages.
     */
    Next,

    /**
     * The Previous layout button allows the user to return to the prior step, page, or section.
     */
    Previous
}

/**
 * Represents the appearance of an [OudsNavigationButton].
 */
enum class OudsNavigationButtonAppearance {
    /**
     * Default navigation buttons are used for actions which are not mandatory or essential for the user.
     */
    Default,

    /**
     * The Strong navigation button on the page should be singular and prominent, ideally limited to one per view.
     * It should be reserved for the most critical navigation action.
     */
    Strong,

    /**
     * A brand primary color alternative to the Strong button. To be used sparingly for high-value specific navigation actions or to visually anchor a brand moment.
     * Do not use it as the default primary button in your interfaces.
     */
    Brand,

    /**
     * Minimal navigation buttons are commonly used for actions that are considered less crucial. They can be used independently or together with a strong button.
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
            PreviewEnumEntries<OudsButtonState>(columnCount = 2) {
                if (hasLabel) {
                    OudsNavigationButton(
                        label = layout.name,
                        layout = layout,
                        onClick = {},
                        appearance = appearance
                    )
                } else {
                    OudsNavigationButton(
                        layout = layout,
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
        layout = OudsNavigationButtonLayout.Next,
        onClick = {},
    )
}

internal data class OudsNavigationButtonPreviewParameter(
    val appearance: OudsNavigationButtonAppearance,
    val layout: OudsNavigationButtonLayout,
    val hasLabel: Boolean,
    val onColoredBox: Boolean = false
)

internal class OudsNavigationButtonPreviewParameterProvider :
    BasicPreviewParameterProvider<OudsNavigationButtonPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsNavigationButtonPreviewParameter>
    get() = buildList {
        OudsNavigationButtonAppearance.entries.forEach { appearance ->
            OudsNavigationButtonLayout.entries.forEach { layout ->
                val parameters = listOf(
                    OudsNavigationButtonPreviewParameter(appearance, layout, hasLabel = true),
                    OudsNavigationButtonPreviewParameter(appearance, layout, hasLabel = false),
                )
                addAll(parameters)
                addAll(parameters.map { it.copy(onColoredBox = true) })
            }
        }
    }

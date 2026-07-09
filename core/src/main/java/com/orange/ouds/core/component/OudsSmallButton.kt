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

import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.OudsPreviewLightDark
import com.orange.ouds.core.utilities.PreviewEnumEntries
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.core.utilities.mapSettings
import com.orange.ouds.core.utilities.rememberRainbowHeartPainter
import com.orange.ouds.foundation.ExperimentalOudsApi
import com.orange.ouds.theme.OudsThemeContract
import com.orange.ouds.theme.OudsThemeSettings
import com.orange.ouds.theme.tokens.components.OudsButtonMonoTokens

/**
 * Button is a UI element that triggers an action or event, and is used to initiate tasks or confirming an action.
 * Button appears in different layouts, styles and states to indicate hierarchy or emphasis.
 *
 * This version of the button uses the *text only* layout, which is the most common layout.
 * Other layouts are available for this component: *text + icon* and *icon only*.
 *
 * This size can be particularly useful in an information-dense interface or in the construction of a template or component requiring the use of small elements (in a "List item" component, for example).
 * The default size is available via [OudsButton].
 *
 * Note that if it is placed in an [OudsColoredBox], its monochrome variant is automatically displayed.
 * The tokens associated with these specific colors can be customized by overriding [OudsButtonMonoTokens].
 *
 * Rounded corners can be enabled or disabled using the [OudsThemeSettings.roundedCornerButtons] property in the settings of the theme provided
 * when calling the [com.orange.ouds.core.theme.OudsTheme] method.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://r.orange.fr/r/S-ouds-doc-button)
 *
 * > Design name: Button
 *
 * > Design version: 3.3.0
 *
 * **Note**: This API is currently marked as experimental because it has been released ahead of
 * the tokens 2.6.0 update. It will be stabilized once tokens 2.6.0 is integrated.
 *
 * @param label Label displayed in the button describing the button action. Use action verbs or phrases to tell the user what will happen next.
 * @param onClick Callback invoked when the button is clicked.
 * @param modifier [Modifier] applied to the button.
 * @param enabled Controls the enabled state of the button when there is no [loader].
 *   When `false`, this button will not be clickable.
 *   Has no effect if [loader] is provided.
 * @param loader An optional loading progress indicator displayed in the button to indicate an ongoing operation.
 * @param appearance Appearance of the button among [OudsButtonAppearance] values.
 *   A button with [OudsButtonAppearance.Negative] is not allowed as a direct or indirect child of an [OudsColoredBox] and will throw an [IllegalStateException].
 * @param interactionSource An optional hoisted [MutableInteractionSource] for observing and emitting [Interaction]s for this button. Note that if `null`
 *   is provided, interactions will still happen internally.
 *
 * @sample com.orange.ouds.core.component.samples.OudsSmallButtonTextOnlySample
 *
 * @sample com.orange.ouds.core.component.samples.OudsSmallButtonTextOnlyOnColoredBackgroundSample
 */
@Composable
@ExperimentalOudsApi
fun OudsSmallButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loader: OudsButtonLoader? = null,
    appearance: OudsButtonAppearance = OudsButtonDefaults.Appearance,
    interactionSource: MutableInteractionSource? = null
) {
    OudsButton(
        nullableIcon = null,
        nullableLabel = label,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        loader = loader,
        appearance = appearance,
        size = OudsButtonSize.Small,
        interactionSource = interactionSource
    )
}

/**
 * Button is a UI element that triggers an action or event, and is used to initiate tasks or confirming an action.
 * Button appears in different layouts, styles and states to indicate hierarchy or emphasis.
 *
 * This version of the button uses the *icon only* layout, which is typically used in business or back-office interfaces. It is rarely used alone (usually part of a group of elements).
 * Other layouts are available for this component: *text only* and *text + icon*.
 *
 * This size can be particularly useful in an information-dense interface or in the construction of a template or component requiring the use of small elements (in a "List item" component, for example).
 * The default size is available via [OudsButton].
 *
 * Note that if it is placed in an [OudsColoredBox], its monochrome variant is automatically displayed.
 * The tokens associated with these specific colors can be customized by overriding [OudsButtonMonoTokens].
 *
 * Rounded corners can be enabled or disabled using the [OudsThemeSettings.roundedCornerButtons] property in the settings of the theme provided
 * when calling the [com.orange.ouds.core.theme.OudsTheme] method.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://r.orange.fr/r/S-ouds-doc-button)
 *
 * > Design name: Button
 *
 * > Design version: 3.3.0
 *
 * **Note**: This API is currently marked as experimental because it has been released ahead of
 * the tokens 2.6.0 update. It will be stabilized once tokens 2.6.0 is integrated.
 *
 * @param icon Icon displayed in the button. Use an icon to add additional affordance where the icon has a clear and well-established meaning.
 * @param onClick Callback invoked when the button is clicked.
 * @param modifier [Modifier] applied to the button.
 * @param enabled Controls the enabled state of the button when there is no [loader].
 *   When `false`, this button will not be clickable.
 *   Has no effect if [loader] is provided.
 * @param loader An optional loading progress indicator displayed in the button to indicate an ongoing operation.
 * @param appearance Appearance of the button among [OudsButtonAppearance] values.
 *   A button with [OudsButtonAppearance.Negative] is not allowed as a direct or indirect child of an [OudsColoredBox] and will throw an [IllegalStateException].
 * @param interactionSource An optional hoisted [MutableInteractionSource] for observing and emitting [Interaction]s for this button. Note that if `null`
 *   is provided, interactions will still happen internally.
 *
 * @sample com.orange.ouds.core.component.samples.OudsSmallButtonIconOnlySample
 *
 * @sample com.orange.ouds.core.component.samples.OudsSmallButtonIconOnlyOnColoredBackgroundSample
 *
 * @sample com.orange.ouds.core.component.samples.OudsSmallButtonIconOnlyWithUntintedIconSample
 */
@Composable
@ExperimentalOudsApi
fun OudsSmallButton(
    icon: OudsButtonIcon,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loader: OudsButtonLoader? = null,
    appearance: OudsButtonAppearance = OudsButtonDefaults.Appearance,
    interactionSource: MutableInteractionSource? = null
) {
    OudsButton(
        nullableIcon = icon,
        nullableLabel = null,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        loader = loader,
        appearance = appearance,
        size = OudsButtonSize.Small,
        interactionSource = interactionSource
    )
}

/**
 * Button is a UI element that triggers an action or event, and is used to initiate tasks or confirming an action.
 * Button appears in different layouts, styles and states to indicate hierarchy or emphasis.
 *
 * This version of the button uses the *text + icon* layout, which should remain specific to clearly identified contexts (e.g., the use of an icon with a
 * "Play" button is standard in the context of TV or video streaming).
 * Other layouts are available for this component: *text only* and *icon only*.
 *
 * This size can be particularly useful in an information-dense interface or in the construction of a template or component requiring the use of small elements (in a "List item" component, for example).
 * The default size is available via [OudsButton].
 *
 * Note that if it is placed in an [OudsColoredBox], its monochrome variant is automatically displayed.
 * The tokens associated with these specific colors can be customized by overriding [OudsButtonMonoTokens].
 *
 * Rounded corners can be enabled or disabled using the [OudsThemeSettings.roundedCornerButtons] property in the settings of the theme provided
 * when calling the [com.orange.ouds.core.theme.OudsTheme] method.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://r.orange.fr/r/S-ouds-doc-button)
 *
 * > Design name: Button
 *
 * > Design version: 3.3.0
 *
 * **Note**: This API is currently marked as experimental because it has been released ahead of
 * the tokens 2.6.0 update. It will be stabilized once tokens 2.6.0 is integrated.
 *
 * @param icon Icon displayed in the button. Use an icon to add additional affordance where the icon has a clear and well-established meaning.
 * @param label Label displayed in the button describing the button action. Use action verbs or phrases to tell the user what will happen next.
 * @param onClick Callback invoked when the button is clicked.
 * @param modifier [Modifier] applied to the button.
 * @param enabled Controls the enabled state of the button when there is no [loader].
 *   When `false`, this button will not be clickable.
 *   Has no effect if [loader] is provided.
 * @param loader An optional loading progress indicator displayed in the button to indicate an ongoing operation.
 * @param appearance Appearance of the button among [OudsButtonAppearance] values.
 *   A button with [OudsButtonAppearance.Negative] is not allowed as a direct or indirect child of an [OudsColoredBox] and will throw an [IllegalStateException].
 * @param interactionSource An optional hoisted [MutableInteractionSource] for observing and emitting [Interaction]s for this button. Note that if `null`
 *   is provided, interactions will still happen internally.
 *
 * @sample com.orange.ouds.core.component.samples.OudsSmallButtonTextAndIconSample
 *
 * @sample com.orange.ouds.core.component.samples.OudsSmallButtonTextAndIconOnColoredBackgroundSample
 *
 * @sample com.orange.ouds.core.component.samples.OudsSmallButtonTextAndIconWithUntintedIconSample
 */
@Composable
@ExperimentalOudsApi
fun OudsSmallButton(
    icon: OudsButtonIcon,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loader: OudsButtonLoader? = null,
    appearance: OudsButtonAppearance = OudsButtonDefaults.Appearance,
    interactionSource: MutableInteractionSource? = null
) {
    OudsButton(
        nullableIcon = icon,
        nullableLabel = label,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        loader = loader,
        appearance = appearance,
        size = OudsButtonSize.Small,
        interactionSource = interactionSource
    )
}

@OudsPreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsSmallButton(@PreviewParameter(OudsButtonPreviewParameterProvider::class) parameter: OudsButtonPreviewParameter) {
    PreviewOudsSmallButton(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsSmallButton(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsButtonPreviewParameter
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        val label = if (hasLabel) appearance.name else null
        val icon = if (hasIcon) OudsButtonIcon(Icons.Filled.FavoriteBorder, "") else null
        val content: @Composable () -> Unit = {
            PreviewEnumEntries<OudsButtonState>(maxEnumEntriesInEachRow = 2) {
                when {
                    icon != null && label != null -> OudsSmallButton(
                        icon = icon,
                        label = label,
                        onClick = {},
                        appearance = appearance
                    )
                    icon != null -> OudsSmallButton(
                        icon = icon,
                        onClick = {},
                        appearance = appearance
                    )
                    label != null -> OudsSmallButton(
                        label = label,
                        onClick = {},
                        appearance = appearance
                    )
                    else -> {}
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
private fun PreviewOudsSmallButtonWithRoundedCorners() = PreviewOudsSmallButtonWithRoundedCorners(theme = getPreviewTheme())

@Composable
internal fun PreviewOudsSmallButtonWithRoundedCorners(
    theme: OudsThemeContract
) = OudsPreview(theme = theme.mapSettings { it.copy(roundedCornerButtons = true) }) {
    val appearance = OudsButtonAppearance.Default
    PreviewEnumEntries<OudsButtonState>(maxEnumEntriesInEachRow = 2) {
        OudsSmallButton(
            icon = OudsButtonIcon(Icons.Filled.FavoriteBorder, ""),
            label = appearance.name,
            onClick = {},
            appearance = appearance
        )
    }
}

@OudsPreview
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsSmallButtonOnTwoLines() = PreviewOudsSmallButtonOnTwoLines(getPreviewTheme())

@Composable
internal fun PreviewOudsSmallButtonOnTwoLines(theme: OudsThemeContract) = OudsPreview(theme = theme) {
    OudsSmallButton(
        icon = OudsButtonIcon(Icons.Filled.FavoriteBorder, ""),
        label = "Button\non two lines",
        onClick = {},
    )
}

@OudsPreview
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsSmallButtonWithUntintedIcon() = PreviewOudsSmallButtonWithUntintedIcon(getPreviewTheme())

@Composable
internal fun PreviewOudsSmallButtonWithUntintedIcon(theme: OudsThemeContract) = OudsPreview(theme = theme) {
    PreviewEnumEntries<OudsButtonState>(maxEnumEntriesInEachRow = 2) {
        OudsSmallButton(
            icon = OudsButtonIcon(
                painter = rememberRainbowHeartPainter(),
                contentDescription = "",
                tinted = false
            ),
            label = "Label",
            onClick = {},
        )
    }
}

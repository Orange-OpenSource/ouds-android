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
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.component.common.outerBorder
import com.orange.ouds.core.extensions.InteractionState
import com.orange.ouds.core.extensions.collectInteractionStateAsState
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.takeUnlessHairline
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.PreviewEnumEntries
import com.orange.ouds.core.utilities.getPreviewEnumEntry
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.theme.OudsThemeContract

/**
 * An input tag is a component that allows users to enter multiple values, each represented as a tag. As users type and submit values (usually by pressing
 * enter, comma, or tab), each value is transformed into a Tag.
 * Input tags are often used for adding labels, categories, or participants. They typically support editing, removing, and validating individual tags.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://unified-design-system.orange.com/472794e18/p/7565ce-tag/t/697817ca4d)
 *
 * > Design version: 1.4.0
 *
 * @param label The label displayed in the input tag.
 * @param onClick Called when the input tag is clicked.
 * @param modifier [Modifier] applied to the input tag.
 * @param enabled Controls the enabled state of this input tag. When `false`, this component will not
 *   respond to user input, and it will appear visually disabled and disabled to accessibility
 *   services.
 * @param interactionSource an optional hoisted [MutableInteractionSource] for observing and
 *   emitting [Interaction]s for this input tag. You can use this to change the input tag's appearance or
 *   preview the input tag in different states. Note that if `null` is provided, interactions will still
 *   happen internally.
 *
 * @sample com.orange.ouds.core.component.samples.OudsInputTagSample
 */
@Composable
fun OudsInputTag(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null
) {
    val tagTokens = OudsTheme.componentsTokens.tag
    @Suppress("NAME_SHADOWING") val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    val interactionState by interactionSource.collectInteractionStateAsState()
    val state = getInputTagState(enabled = enabled, interactionState = interactionState)
    val shape = RoundedCornerShape(tagTokens.borderRadius.value)

    val backgroundColor = rememberNullableInteractionColor(interactionState = interactionState) { inputTagInteractionState ->
        val inputTagState = getInputTagState(enabled = enabled, interactionState = inputTagInteractionState)
        backgroundColor(state = inputTagState)
    }

    val contentColor = rememberInteractionColor(interactionState = interactionState) { inputTagInteractionState ->
        val inputTagState = getInputTagState(enabled = enabled, interactionState = inputTagInteractionState)
        contentColor(state = inputTagState)
    }

    val borderWidth = rememberInteractionValue(
        interactionState = interactionState,
        toAnimatableFloat = { it?.value.orElse { 0f } },
        fromAnimatableFloat = { it.dp }
    ) { inputTagInteractionState ->
        val inputTagState = getInputTagState(enabled = enabled, interactionState = inputTagInteractionState)
        borderWidth(state = inputTagState)
    }
    val borderColor = rememberInteractionColor(interactionState = interactionState) { inputTagInteractionState ->
        val inputTagState = getInputTagState(enabled = enabled, interactionState = inputTagInteractionState)
        borderColor(state = inputTagState)
    }

    Box(
        modifier = Modifier.heightIn(min = tagTokens.sizeMinHeightInteractiveArea.value),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = modifier
                .widthIn(min = tagTokens.sizeMinWidthDefault.dp)
                .heightIn(min = tagTokens.sizeMinHeightDefault.dp)
                .run {
                    backgroundColor.value?.let { color ->
                        background(color = color, shape = shape)
                    }.orElse {
                        this
                    }
                }
                .run {
                    borderWidth.value?.let { borderWidth ->
                        border(width = borderWidth, color = borderColor.value, shape = shape)
                    }.orElse {
                        this
                    }
                }
                .outerBorder(state = state, shape = shape)
                .clickable(
                    enabled = enabled,
                    interactionSource = interactionSource,
                    indication = InteractionValuesIndication(contentColor, backgroundColor, borderColor, borderWidth),
                    onClick = onClick,
                    role = Role.Button
                )
                .padding(vertical = tagTokens.spacePaddingBlockDefault.value)
                .padding(start = tagTokens.spacePaddingInlineDefault.value, end = tagTokens.spacePaddingInlineAssetDefault.value),
            horizontalArrangement = Arrangement.spacedBy(tagTokens.spaceColumnGapDefault.value, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                color = contentColor.value,
                style = OudsTheme.typography.label.strong.medium.run {
                    copy(lineHeightStyle = lineHeightStyle?.copy(alignment = LineHeightStyle.Alignment.Center))
                }
            )
            Icon(
                modifier = Modifier.size(tagTokens.sizeAssetDefault.value * LocalConfiguration.current.fontScale),
                painter = painterResource(id = OudsTheme.drawableResources.delete),
                contentDescription = null,
                tint = contentColor.value
            )
        }
    }
}

/**
 * An input tag is a component that allows users to enter multiple values, each represented as a tag. As users type and submit values (usually by pressing
 * enter, comma, or tab), each value is transformed into a Tag.
 * Input tags are often used for adding labels, categories, or participants. They typically support editing, removing, and validating individual tags.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://unified-design-system.orange.com)
 *
 * > Design version: 1.4.0
 *
 * @param label The label displayed in the input tag.
 * @param onClick Called when the input tag is clicked.
 * @param modifier [Modifier] applied to the input tag.
 * @param enabled Controls the enabled state of this input tag. When `false`, this component will not
 *   respond to user input, and it will appear visually disabled and disabled to accessibility
 *   services.
 * @param interactionSource an optional hoisted [MutableInteractionSource] for observing and
 *   emitting [Interaction]s for this input tag. You can use this to change the input tag's appearance or
 *   preview the input tag in different states. Note that if `null` is provided, interactions will still
 *   happen internally.
 *
 * @sample com.orange.ouds.core.component.samples.OudsInputTagSample
 */
@Composable
@Deprecated("Please use OudsInputTag composable instead, which is the equivalent of Material InputChip in OUDS Android.")
fun OudsInputChip(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null
) {
    OudsInputTag(label = label, onClick = onClick, modifier = modifier, enabled = enabled, interactionSource = interactionSource)
}

@Composable
private fun getInputTagState(interactionState: InteractionState, enabled: Boolean): OudsInputTagState {
    return getPreviewEnumEntry<OudsInputTagState>().orElse {
        when {
            !enabled -> OudsInputTagState.Disabled
            interactionState == InteractionState.Hovered -> OudsInputTagState.Hovered
            interactionState == InteractionState.Pressed -> OudsInputTagState.Pressed
            interactionState == InteractionState.Focused -> OudsInputTagState.Focused
            else -> OudsInputTagState.Enabled
        }
    }
}

@Composable
private fun backgroundColor(state: OudsInputTagState): Color? {
    return with(OudsTheme.componentsTokens.inputTag) {
        when (state) {
            OudsInputTagState.Enabled -> colorBgEnabled.value
            OudsInputTagState.Focused -> colorBgFocus.value
            OudsInputTagState.Hovered -> colorBgHover.value
            OudsInputTagState.Pressed -> colorBgPressed.value
            OudsInputTagState.Disabled -> null
        }
    }
}

@Composable
private fun borderWidth(state: OudsInputTagState): Dp? {
    return with(OudsTheme.componentsTokens.inputTag) {
        when (state) {
            OudsInputTagState.Enabled,
            OudsInputTagState.Disabled -> borderWidthDefault
            OudsInputTagState.Hovered,
            OudsInputTagState.Pressed,
            OudsInputTagState.Focused -> borderWidthDefaultInteraction
        }
    }.value.takeUnlessHairline
}

@Composable
private fun borderColor(state: OudsInputTagState): Color {
    return with(OudsTheme.componentsTokens.inputTag) {
        when (state) {
            OudsInputTagState.Enabled -> colorBorderEnabled.value
            OudsInputTagState.Focused -> colorBorderFocus.value
            OudsInputTagState.Hovered -> colorBorderHover.value
            OudsInputTagState.Pressed -> colorBorderPressed.value
            OudsInputTagState.Disabled -> OudsTheme.colorScheme.action.disabled
        }
    }
}

@Composable
private fun contentColor(state: OudsInputTagState): Color {
    return with(OudsTheme.componentsTokens.inputTag) {
        when (state) {
            OudsInputTagState.Enabled -> colorContentEnabled.value
            OudsInputTagState.Focused -> colorContentFocus.value
            OudsInputTagState.Hovered -> colorContentHover.value
            OudsInputTagState.Pressed -> colorContentPressed.value
            OudsInputTagState.Disabled -> OudsTheme.colorScheme.action.disabled
        }
    }
}

internal enum class OudsInputTagState {
    Enabled, Hovered, Pressed, Disabled, Focused
}

@PreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsInputTag() {
    PreviewOudsInputTag(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme())
}

@Composable
internal fun PreviewOudsInputTag(theme: OudsThemeContract, darkThemeEnabled: Boolean) =
    OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
        PreviewEnumEntries<OudsInputTagState>(columnCount = 3) {
            OudsInputTag(label = "Label", onClick = {})
        }
    }

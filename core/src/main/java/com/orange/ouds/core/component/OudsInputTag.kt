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
import com.orange.ouds.core.BuildConfig
import com.orange.ouds.core.component.common.outerBorder
import com.orange.ouds.core.extensions.InteractionState
import com.orange.ouds.core.extensions.collectInteractionStateAsState
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.PreviewEnumEntries
import com.orange.ouds.core.utilities.getPreviewEnumEntry
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.theme.OudsThemeContract

/**
 * An input tag is a component that allows users to enter multiple values, each represented as a tag. As users type and submit values (usually by pressing
 * enter, comma, or tab), each value is transformed into a Tag.
 * Input tags are often used for adding labels, categories, or participants. They typically support editing, removing, and validating individual tags.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://unified-design-system.orange.com/472794e18/p/7565ce-tag/t/697817ca4d)
 *
 * > Design version: 1.2.0
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
        toAnimatableFloat = { it.value },
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
                .border(width = borderWidth.value, color = borderColor.value, shape = shape)
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
 * > Design version: 1.2.0
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
private fun getInputTagState(interactionState: InteractionState, enabled: Boolean): OudsInputTag.State {
    return getPreviewEnumEntry<OudsInputTag.State>().orElse {
        when {
            !enabled -> OudsInputTag.State.Disabled
            interactionState == InteractionState.Hovered -> OudsInputTag.State.Hovered
            interactionState == InteractionState.Pressed -> OudsInputTag.State.Pressed
            interactionState == InteractionState.Focused -> OudsInputTag.State.Focused
            else -> OudsInputTag.State.Enabled
        }
    }
}

@Composable
private fun backgroundColor(state: OudsInputTag.State): Color? {
    return with(OudsTheme.componentsTokens.tagInput) {
        when (state) {
            OudsInputTag.State.Enabled -> colorBgEnabled.value
            OudsInputTag.State.Focused -> colorBgFocus.value
            OudsInputTag.State.Hovered -> colorBgHover.value
            OudsInputTag.State.Pressed -> colorBgPressed.value
            OudsInputTag.State.Disabled -> null
        }
    }
}

@Composable
private fun borderWidth(state: OudsInputTag.State): Dp {
    return with(OudsTheme.componentsTokens.tagInput) {
        when (state) {
            OudsInputTag.State.Enabled,
            OudsInputTag.State.Disabled -> borderWidthDefault
            OudsInputTag.State.Hovered,
            OudsInputTag.State.Pressed,
            OudsInputTag.State.Focused -> borderWidthDefaultInteraction
        }
    }.value
}

@Composable
private fun borderColor(state: OudsInputTag.State): Color {
    return with(OudsTheme.componentsTokens.tagInput) {
        when (state) {
            OudsInputTag.State.Enabled -> colorBorderEnabled.value
            OudsInputTag.State.Focused -> colorBorderFocus.value
            OudsInputTag.State.Hovered -> colorBorderHover.value
            OudsInputTag.State.Pressed -> colorBorderPressed.value
            OudsInputTag.State.Disabled -> OudsTheme.colorScheme.action.disabled
        }
    }
}

@Composable
private fun contentColor(state: OudsInputTag.State): Color {
    return with(OudsTheme.componentsTokens.tagInput) {
        when (state) {
            OudsInputTag.State.Enabled -> colorContentEnabled.value
            OudsInputTag.State.Focused -> colorContentFocus.value
            OudsInputTag.State.Hovered -> colorContentHover.value
            OudsInputTag.State.Pressed -> colorContentPressed.value
            OudsInputTag.State.Disabled -> OudsTheme.colorScheme.action.disabled
        }
    }
}

/**
 * Contains classes to build an [OudsInputTag].
 */
object OudsInputTag {

    internal enum class State {
        Enabled, Hovered, Pressed, Disabled, Focused
    }
}

@PreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsInputTag() {
    PreviewOudsInputTag(theme = BuildConfig.PREVIEW_THEME, darkThemeEnabled = isSystemInDarkTheme())
}

@Composable
fun PreviewOudsInputTag(theme: OudsThemeContract, darkThemeEnabled: Boolean) =
    OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
        PreviewEnumEntries<OudsInputTag.State>(columnCount = 3) {
            OudsInputTag(label = "Label", onClick = {})
        }
    }
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

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.selection.triStateToggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.orange.ouds.core.R
import com.orange.ouds.core.component.content.OudsComponentContent
import com.orange.ouds.core.component.content.OudsComponentIcon
import com.orange.ouds.core.extensions.InteractionState
import com.orange.ouds.core.extensions.collectInteractionStateAsState
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.outerBorder
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.foundation.utilities.UiModePreviews

/**
 * <a href="https://unified-design-system.orange.com/472794e18/p/23f1c1-checkbox" class="external" target="_blank">OUDS Checkbox design guidelines</a>
 *
 * An OUDS checkbox item is a layout containing an OudsCheckbox, an associated text and several other optional elements.
 * It can be used in a list as a list item or as a single element to validate general conditions for example.
 * By clicking on a checkbox item, the user changes the checked state of its checkbox.
 *
 * If you want to use a standalone checkbox please use [com.orange.ouds.core.component.OudsCheckbox].
 *
 * If you need an indeterminate state for the item's checkbox, please use [OudsTriStateCheckboxItem].
 *
 * @param checked Controls checked state of the item's checkbox.
 * @param text The main text of the checkbox item.
 * @param onCheckedChange Callback invoked on checkbox item click. If `null`, then this is passive and relies entirely on a higher-level component to control
 * the checked state.
 * @param modifier [Modifier] applied to the layout of the checkbox item.
 * @param helperText Optional text displayed below the main text.
 * @param icon Optional icon displayed in the item. By default, it has a trailing position. If [inverted] is set to `true`, it is displayed as a leading element.
 * @param divider Controls the display of a divider at the bottom of the checkbox item.
 * @param inverted When `false`, the checkbox has a leading position and the optional [icon] has a trailing position. It is inverted otherwise.
 * @param enabled Controls the enabled state of the checkbox item. When `false`, the checkbox, the text and the optional icon are disabled, and the item
 * will not be clickable. Note that if it is set to `false` and [readOnly] is set to `true`, the checkbox item will be displayed in read only state.
 * @param readOnly Controls the read only state of the checkbox item. When `true` the item's checkbox is disabled but the text and the icon remain in enabled color.
 * @param error Controls the error state of the checkbox item.
 * @param interactionSource Optional hoisted [MutableInteractionSource] for observing and emitting [Interaction]s for the item's checkbox. Note that if `null`
 * is provided, interactions will still happen internally.
 *
 * @sample com.orange.ouds.core.component.samples.OudsCheckboxItemSample
 */
@Composable
fun OudsCheckboxItem(
    checked: Boolean,
    text: String,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    helperText: String? = null,
    icon: OudsCheckboxItem.Icon? = null,
    divider: Boolean = true,
    inverted: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    error: Boolean = false,
    interactionSource: MutableInteractionSource? = null
) {
    OudsTriStateCheckboxItem(
        state = ToggleableState(checked),
        text = text,
        onClick = if (onCheckedChange != null) {
            { onCheckedChange(!checked) }
        } else null,
        modifier = modifier,
        helperText = helperText,
        icon = icon,
        divider = divider,
        inverted = inverted,
        enabled = enabled,
        readOnly = readOnly,
        error = error,
        interactionSource = interactionSource
    )
}

/**
 * <a href="https://unified-design-system.orange.com/472794e18/p/23f1c1-checkbox" class="external" target="_blank">OUDS Checkbox design guidelines</a>
 *
 * An OUDS checkbox parent item.
 * It is a layout containing an [com.orange.ouds.core.component.OudsTriStateCheckbox], an associated text and several other optional elements which is often
 * used in a list to handle checkbox items hierarchy.
 * By clicking on a checkbox parent item, the user changes the checked state of its checkbox.
 *
 * If you only need a standalone parent checkbox, please use [com.orange.ouds.core.component.OudsTriStateCheckbox].
 *
 * If you don't need an indeterminate state for the item's checkbox, you may prefer [OudsCheckboxItem].
 *
 * @param state Controls whether item's TriStateCheckbox is checked, unchecked or in indeterminate state.
 * @param text The main text of the checkbox item.
 * @param onClick Callback invoked when checkbox item is being clicked, therefore the change of checkbox [ToggleableState] state is requested. If null, then
 * this is passive and relies entirely on a higher-level component to control the state.
 * @param modifier [Modifier] applied to the layout of the checkbox item.
 * @param helperText Optional text displayed below the main text.
 * @param icon Optional icon displayed in the item. By default, it has a trailing position. If [inverted] is set to `true`, it is displayed as a leading element.
 * @param divider Controls the display of a divider at the bottom of the checkbox item.
 * @param inverted When `false`, the checkbox has a leading position and the optional [icon] has a trailing position. It is inverted otherwise.
 * @param enabled Controls the enabled state of the checkbox item. When `false`, the checkbox, the text and the optional icon are disabled, and the item
 * will not be clickable. Note that if it is set to `false` and [readOnly] is set to `true`, the checkbox item will be displayed in read only state.
 * @param readOnly Controls the read only state of the checkbox item. When `true` the item's checkbox is disabled but the text and the icon remain in enabled color.
 * @param error Controls the error state of the checkbox item.
 * @param interactionSource Optional hoisted [MutableInteractionSource] for observing and emitting [Interaction]s for the item's checkbox. Note that
 * if `null` is provided, interactions will still happen internally.
 *
 * @sample com.orange.ouds.core.component.samples.OudsTriStateCheckboxItemSample
 */
@Composable
fun OudsTriStateCheckboxItem(
    state: ToggleableState,
    text: String,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    helperText: String? = null,
    icon: OudsCheckboxItem.Icon? = null,
    divider: Boolean = true,
    inverted: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    error: Boolean = false,
    interactionSource: MutableInteractionSource? = null
) {
    val checkboxInteractionSource = interactionSource ?: remember { MutableInteractionSource() }

    val toggleableModifier = if (onClick != null) {
        Modifier.triStateToggleable(
            interactionSource = checkboxInteractionSource,
            indication = LocalIndication.current,
            state = state,
            onClick = onClick,
            enabled = enabled && !readOnly,
            role = Role.Checkbox
        )
    } else {
        Modifier
    }

    OudsCheckboxItem(
        value = state,
        text = text,
        interactionSource = checkboxInteractionSource,
        modifier = modifier.then(toggleableModifier),
        previewState = null,
        helperText = helperText,
        icon = icon,
        divider = divider,
        inverted = inverted,
        enabled = enabled,
        readOnly = readOnly,
        error = error
    )
}

@Composable
private fun OudsCheckboxItem(
    value: ToggleableState,
    text: String,
    interactionSource: MutableInteractionSource,
    previewState: OudsCheckboxItem.State?,
    modifier: Modifier,
    helperText: String? = null,
    icon: OudsCheckboxItem.Icon? = null,
    divider: Boolean = true,
    inverted: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    error: Boolean = false
) {
    if (error) {
        if (readOnly) throw IllegalStateException("An OudsCheckboxItem or OudsTriStateCheckboxItem set to readOnly with error parameter activated is not allowed.")
        if (!enabled) throw IllegalStateException("An OudsCheckboxItem or OudsTriStateCheckboxItem set to disabled with error parameter activated is not allowed.")
    }

    val interactionState by interactionSource.collectInteractionStateAsState()
    val context = LocalContext.current
    val controlItemTokens = OudsTheme.componentsTokens.controlItem
    val state = previewState.orElse { rememberOudsCheckboxItemState(enabled = enabled, readOnly = readOnly, interactionState = interactionState) }

    val checkboxIndicator: @Composable () -> Unit = {
        OudsCheckboxIndicator(
            state = checkboxState(state),
            value = value,
            error = error
        )
    }

    val itemIcon: (@Composable () -> Unit)? = icon?.let {
        {
            icon.Content(
                extraParameters = OudsCheckboxItem.Icon.ExtraParameters(
                    tint = if (state == OudsCheckboxItem.State.Disabled) OudsTheme.colorScheme.content.disabled else OudsTheme.colorScheme.content.default
                )
            )
        }
    }

    val leadingElement: (@Composable () -> Unit)? = if (inverted) itemIcon else checkboxIndicator
    val trailingElement: (@Composable () -> Unit)? = if (inverted) checkboxIndicator else itemIcon
    val dividerThickness = 1.dp

    Column(
        modifier = modifier
            .height(IntrinsicSize.Min)
            .heightIn(min = controlItemTokens.sizeMinHeight.dp)
            .widthIn(min = controlItemTokens.sizeMinWidth.dp)
            .background(color = backgroundColor(state = state))
            .outerBorder(state = state)
            .semantics(mergeDescendants = true) {
                stateDescription = when (value) {
                    ToggleableState.Off -> context.getString(R.string.core_checkbox_unchecked_a11y)
                    ToggleableState.On -> context.getString(R.string.core_checkbox_checked_a11y)
                    ToggleableState.Indeterminate -> context.getString(R.string.core_checkbox_indeterminate_a11y)
                }
            }
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .padding(all = controlItemTokens.spaceInset.value),
            horizontalArrangement = Arrangement.spacedBy(controlItemTokens.spaceColumnGap.value)
        ) {
            leadingElement?.let { LeadingTrailingBox(leadingElement) }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = text, style = OudsTheme.typography.label.default.large, color = textColor(state = state, error = error))
                helperText?.let {
                    Text(
                        text = helperText,
                        style = OudsTheme.typography.label.default.medium,
                        color = helperTextColor(state = state)
                    )
                }
            }
            trailingElement?.let { LeadingTrailingBox(trailingElement) }
        }
        if (divider) {
            HorizontalDivider(
                thickness = dividerThickness,
                color = OudsTheme.colorScheme.border.default.copy(alpha = 0.2f)
            ) //TODO Replace with OudsHorizontalDivider when available
        }
    }
}

@Composable
private fun rememberOudsCheckboxItemState(
    enabled: Boolean,
    readOnly: Boolean,
    interactionState: InteractionState
): OudsCheckboxItem.State = remember(enabled, readOnly, interactionState) {
    when {
        readOnly -> OudsCheckboxItem.State.ReadOnly
        !enabled -> OudsCheckboxItem.State.Disabled
        interactionState == InteractionState.Hovered -> OudsCheckboxItem.State.Hovered
        interactionState == InteractionState.Pressed -> OudsCheckboxItem.State.Pressed
        interactionState == InteractionState.Focused -> OudsCheckboxItem.State.Focused
        else -> OudsCheckboxItem.State.Enabled
    }
}

/**
 * Contains classes to build an [com.orange.ouds.core.component.OudsCheckboxItem].
 */
object OudsCheckboxItem {
    internal enum class State {
        Enabled, Hovered, Pressed, Disabled, Focused, ReadOnly
    }

    /**
     * An icon in an [OudsCheckboxItem].
     * It is non-clickable and no content description is needed cause a checkbox item text is always present.
     */
    class Icon private constructor(
        graphicsObject: Any,
    ) : OudsComponentIcon<Icon.ExtraParameters>(ExtraParameters::class.java, graphicsObject, "") {

        @ConsistentCopyVisibility
        data class ExtraParameters internal constructor(
            internal val tint: Color
        ) : OudsComponentContent.ExtraParameters()

        /**
         * Creates an instance of [OudsCheckboxItem.Icon].
         *
         * @param painter Painter of the icon.
         */
        constructor(painter: Painter) : this(painter as Any)

        /**
         * Creates an instance of [OudsCheckboxItem.Icon].
         *
         * @param imageVector Image vector of the icon.
         */
        constructor(imageVector: ImageVector) : this(imageVector as Any)

        /**
         * Creates an instance of [OudsCheckboxItem.Icon].
         *
         * @param bitmap Image bitmap of the icon.
         */
        constructor(bitmap: ImageBitmap) : this(bitmap as Any)

        override val tint: Color?
            @Composable
            get() = extraParameters.tint
    }
}

private fun checkboxState(state: OudsCheckboxItem.State) = when (state) {
    OudsCheckboxItem.State.Enabled -> OudsCheckbox.State.Enabled
    OudsCheckboxItem.State.Hovered -> OudsCheckbox.State.Hovered
    OudsCheckboxItem.State.Pressed -> OudsCheckbox.State.Pressed
    OudsCheckboxItem.State.Focused -> OudsCheckbox.State.Focused
    OudsCheckboxItem.State.Disabled, OudsCheckboxItem.State.ReadOnly -> OudsCheckbox.State.Disabled
}

@Composable
private fun LeadingTrailingBox(content: @Composable () -> Unit) {
    val assetContainerMaxHeight = OudsTheme.componentsTokens.controlItem.sizeMaxHeightAssetsContainer.dp
    val checkboxIndicatorSize = OudsTheme.componentsTokens.checkbox.sizeMinHeight.dp

    val maxHeight = max(assetContainerMaxHeight, checkboxIndicatorSize)
    Box(
        modifier = Modifier
            .heightIn(max = maxHeight)
            .fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Composable
private fun Modifier.outerBorder(state: OudsCheckboxItem.State) = if (state == OudsCheckboxItem.State.Focused) {
    outerBorder(
        width = OudsTheme.borders.width.focus,
        color = OudsTheme.colorScheme.border.focus,
        insetWidth = OudsTheme.borders.width.focusInset,
        insetColor = OudsTheme.colorScheme.border.focusInset
    )
} else {
    this
}

@Composable
private fun backgroundColor(state: OudsCheckboxItem.State): Color {
    return with(OudsTheme.componentsTokens.controlItem) {
        when (state) {
            OudsCheckboxItem.State.Enabled, OudsCheckboxItem.State.Disabled, OudsCheckboxItem.State.ReadOnly -> Color.Transparent
            OudsCheckboxItem.State.Hovered -> colorBgHover.value
            OudsCheckboxItem.State.Pressed -> colorBgPressed.value
            OudsCheckboxItem.State.Focused -> colorBgFocus.value
        }
    }
}

@Composable
private fun textColor(state: OudsCheckboxItem.State, error: Boolean) =
    if (error) {
        with(OudsTheme.colorScheme.action.negative) {
            when (state) {
                OudsCheckboxItem.State.Enabled -> enabled
                OudsCheckboxItem.State.Hovered -> hover
                OudsCheckboxItem.State.Pressed -> pressed
                OudsCheckboxItem.State.Focused -> focus
                OudsCheckboxItem.State.Disabled, OudsCheckboxItem.State.ReadOnly -> Color.Unspecified // Not allowed, exception thrown at the beginning of OudsCheckboxItem
            }
        }
    } else {
        if (state == OudsCheckboxItem.State.Disabled) OudsTheme.colorScheme.content.disabled else OudsTheme.colorScheme.content.default
    }

@Composable
private fun helperTextColor(state: OudsCheckboxItem.State) =
    if (state == OudsCheckboxItem.State.Disabled) OudsTheme.colorScheme.content.disabled else OudsTheme.colorScheme.content.muted

@UiModePreviews.Default
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsCheckboxItem(@PreviewParameter(OudsCheckboxItemPreviewParameterProvider::class) parameter: OudsCheckboxItemPreviewParameter) {
    PreviewOudsCheckboxItem(darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsCheckboxItem(
    darkThemeEnabled: Boolean,
    parameter: OudsCheckboxItemPreviewParameter
) = OudsPreview(darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        val content: @Composable () -> Unit = {
            Column(Modifier.padding(16.dp)) {
                states.forEach { state ->
                    OudsCheckboxItem(
                        value = toggleableState,
                        text = "Label",
                        previewState = state,
                        modifier = Modifier,
                        helperText = helperText,
                        error = error,
                        inverted = inverted,
                        icon = if (hasIcon) {
                            OudsCheckboxItem.Icon(imageVector = Icons.Filled.Call)
                        } else {
                            null
                        },
                        interactionSource = remember { MutableInteractionSource() }
                    )
                }
            }
        }
        content()
    }
}

internal data class OudsCheckboxItemPreviewParameter(
    val toggleableState: ToggleableState,
    val helperText: String? = null,
    val hasIcon: Boolean = false,
    val error: Boolean = false,
    val inverted: Boolean = false
) {
    val states: List<OudsCheckboxItem.State> = listOf(
        OudsCheckboxItem.State.Enabled,
        OudsCheckboxItem.State.Pressed,
        OudsCheckboxItem.State.Hovered,
        OudsCheckboxItem.State.Focused,
        OudsCheckboxItem.State.Disabled,
        OudsCheckboxItem.State.ReadOnly
    )
}

internal class OudsCheckboxItemPreviewParameterProvider :
    BasicPreviewParameterProvider<OudsCheckboxItemPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsCheckboxItemPreviewParameter>
    get() {
        val helperText = "Helper text"
        val invertedValues = listOf(false, true)
        return buildList {
            invertedValues.forEach { inverted ->
                val parameters = listOf(
                    OudsCheckboxItemPreviewParameter(toggleableState = ToggleableState.Off, inverted = inverted),
                    OudsCheckboxItemPreviewParameter(
                        toggleableState = ToggleableState.Off,
                        hasIcon = true,
                        helperText = helperText,
                        inverted = inverted
                    ),
                    OudsCheckboxItemPreviewParameter(toggleableState = ToggleableState.On, helperText = helperText, error = true, inverted = inverted),
                    OudsCheckboxItemPreviewParameter(
                        toggleableState = ToggleableState.On,
                        helperText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                        error = true,
                        hasIcon = true,
                        inverted = inverted
                    ),
                )
                addAll(parameters)
            }
        }
    }
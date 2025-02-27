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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
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
import com.orange.ouds.core.R
import com.orange.ouds.core.component.content.OudsComponentContent
import com.orange.ouds.core.component.content.OudsComponentIcon
import com.orange.ouds.core.extensions.InteractionState
import com.orange.ouds.core.extensions.collectInteractionStateAsState
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.foundation.utilities.UiModePreviews

/**
 * <a href="https://unified-design-system.orange.com/472794e18/p/23f1c1-checkbox" class="external" target="_blank">OUDS Checkbox design guidelines</a>
 *
 * An OUDS checkbox control item is a layout containing an OudsCheckbox, an associated text and several other optional elements.
 * It can be used in a list as a list item or as a single element to validate general conditions for example.
 * By clicking on a checkbox control item, the user changes the checked state of its checkbox.
 *
 * If you want to use a standalone checkbox please use [com.orange.ouds.core.component.OudsCheckbox].
 *
 * If you need an indeterminate state for the item's checkbox, please use [OudsTriStateCheckboxControlItem].
 *
 * @param checked Controls checked state of the control item's checkbox.
 * @param text The main text of the checkbox control item.
 * @param onClick Callback invoked on checkbox control item click.
 * @param modifier [Modifier] applied to the layout of the checkbox control item.
 * @param helperText Optional text displayed below the main text.
 * @param icon Optional icon displayed in the item. By default, it has a trailing position. If [inverted] is set to `true`, it is displayed as a leading element.
 * @param divider Controls the display of a divider at the bottom of the checkbox control item.
 * @param inverted When `false`, the checkbox has a leading position and the optional [icon] has a trailing position. It is inverted otherwise.
 * @param enabled Controls the enabled state of the checkbox control item. When `false`, the control item's checkbox will not be clickable.
 * @param readOnly Controls the read only state of the checkbox control item. When `true` the control item's checkbox is disabled.
 * @param error Controls the error state of the checkbox control item.
 * @param interactionSource Optional hoisted [MutableInteractionSource] for observing and emitting [Interaction]s for the item's checkbox. Note that if `null`
 * is provided, interactions will still happen internally.
 *
 * @sample com.orange.ouds.core.component.samples.OudsCheckboxControlItemSample
 */
@Composable
fun OudsCheckboxControlItem(
    checked: Boolean,
    text: String,
    onClick: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    helperText: String? = null,
    icon: OudsCheckboxControlItem.Icon? = null,
    divider: Boolean = true,
    inverted: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    error: Boolean = false,
    interactionSource: MutableInteractionSource? = null
) {
    OudsTriStateCheckboxControlItem(
        state = ToggleableState(checked),
        text = text,
        onClick = { onClick(!checked) },
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
 * An OUDS checkbox parent control item.
 * It is a layout containing an [com.orange.ouds.core.component.OudsTriStateCheckbox], an associated text and several other optional elements which is often
 * used in a list to handle checkbox items hierarchy.
 * By clicking on a checkbox parent control item, the user changes the checked state of its checkbox.
 *
 * If you only need a standalone parent checkbox, please use [com.orange.ouds.core.component.OudsTriStateCheckbox].
 *
 * If you don't need an indeterminate state for the item's checkbox, you may prefer [OudsCheckboxControlItem].
 *
 * @param state Controls whether control item's TriStateCheckbox is checked, unchecked or in indeterminate state.
 * @param text The main text of the checkbox control item.
 * @param onClick Callback invoked when checkbox control item is being clicked, therefore the change of checkbox [ToggleableState] state is requested.
 * @param modifier [Modifier] applied to the layout of the checkbox control item.
 * @param helperText Optional text displayed below the main text.
 * @param icon Optional icon displayed in the item. By default, it has a trailing position. If [inverted] is set to `true`, it is displayed as a leading element.
 * @param divider Controls the display of a divider at the bottom of the checkbox control item.
 * @param inverted When `false`, the checkbox has a leading position and the optional [icon] has a trailing position. It is inverted otherwise.
 * @param enabled Controls the enabled state of the checkbox control item. When `false`, the control item's checkbox will not be clickable.
 * @param readOnly Controls the read only state of the checkbox control item. When `true` the control item's checkbox is disabled.
 * @param error Controls the error state of the checkbox control item.
 * @param interactionSource Optional hoisted [MutableInteractionSource] for observing and emitting [Interaction]s for the control item's checkbox. Note that
 * if `null` is provided, interactions will still happen internally.
 *
 * @sample com.orange.ouds.core.component.samples.OudsTriStateCheckboxControlItemSample
 */
@Composable
fun OudsTriStateCheckboxControlItem(
    state: ToggleableState,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    helperText: String? = null,
    icon: OudsCheckboxControlItem.Icon? = null,
    divider: Boolean = true,
    inverted: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    error: Boolean = false,
    interactionSource: MutableInteractionSource? = null
) {
    val checkboxInteractionSource = interactionSource ?: remember { MutableInteractionSource() }

    OudsCheckboxControlItem(
        value = state,
        text = text,
        interactionSource = checkboxInteractionSource,
        modifier = modifier.triStateToggleable(
            interactionSource = checkboxInteractionSource,
            indication = LocalIndication.current,
            state = state,
            onClick = onClick,
            enabled = enabled,
            role = Role.Checkbox
        ),
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
private fun OudsCheckboxControlItem(
    value: ToggleableState,
    text: String,
    interactionSource: MutableInteractionSource,
    previewState: OudsCheckboxControlItem.State?,
    modifier: Modifier,
    helperText: String? = null,
    icon: OudsCheckboxControlItem.Icon? = null,
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
    val listItemTokens = OudsTheme.componentsTokens.listItem
    val state = previewState.orElse { rememberOudsCheckboxControlItemState(enabled = enabled, readOnly = readOnly, interactionState = interactionState) }

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
                extraParameters = OudsCheckboxControlItem.Icon.ExtraParameters(
                    tint = if (state == OudsCheckboxControlItem.State.Disabled) OudsTheme.colorScheme.content.disabled else OudsTheme.colorScheme.content.default
                )
            )
        }
    }

    val dividerThickness = 1.dp

    Column(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = listItemTokens.sizeMinHeight.dp)
            .background(color = backgroundColor(state = state))
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
                .heightIn(min = listItemTokens.sizeMinHeight.dp - dividerThickness) //TODO Not able to do the same using weight
                .padding(all = listItemTokens.spaceInset.value),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(listItemTokens.spaceColumnGap.value)
        ) {
            if (inverted) itemIcon?.invoke() else checkboxIndicator()
            Column(modifier = Modifier.weight(1f)) {
                Text(text = text, style = OudsTheme.typography.label.default.large, color = textColor(state = state, error = error))
                helperText?.let { Text(text = helperText, style = OudsTheme.typography.label.default.medium, color = helperTextColor(state = state)) }
            }
            if (inverted) checkboxIndicator() else itemIcon?.invoke()
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
private fun rememberOudsCheckboxControlItemState(
    enabled: Boolean,
    readOnly: Boolean,
    interactionState: InteractionState
): OudsCheckboxControlItem.State = remember(enabled, readOnly, interactionState) {
    when {
        readOnly -> OudsCheckboxControlItem.State.ReadOnly
        !enabled -> OudsCheckboxControlItem.State.Disabled
        interactionState == InteractionState.Hovered -> OudsCheckboxControlItem.State.Hovered
        interactionState == InteractionState.Pressed -> OudsCheckboxControlItem.State.Pressed
        interactionState == InteractionState.Focused -> OudsCheckboxControlItem.State.Focused
        else -> OudsCheckboxControlItem.State.Enabled
    }
}

/**
 * Contains classes to build an [com.orange.ouds.core.component.OudsCheckboxControlItem].
 */
object OudsCheckboxControlItem {
    internal enum class State {
        Enabled, Hovered, Pressed, Disabled, Focused, ReadOnly
    }

    /**
     * An icon in an [OudsCheckboxControlItem].
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
         * Creates an instance of [OudsCheckboxControlItem.Icon].
         *
         * @param painter Painter of the icon.
         */
        constructor(painter: Painter) : this(painter as Any)

        /**
         * Creates an instance of [OudsCheckboxControlItem.Icon].
         *
         * @param imageVector Image vector of the icon.
         */
        constructor(imageVector: ImageVector) : this(imageVector as Any)

        /**
         * Creates an instance of [OudsCheckboxControlItem.Icon].
         *
         * @param bitmap Image bitmap of the icon.
         */
        constructor(bitmap: ImageBitmap) : this(bitmap as Any)

        override val tint: Color?
            @Composable
            get() = extraParameters.tint
    }
}

private fun checkboxState(state: OudsCheckboxControlItem.State) = when (state) {
    OudsCheckboxControlItem.State.Enabled -> OudsCheckbox.State.Enabled
    OudsCheckboxControlItem.State.Hovered -> OudsCheckbox.State.Hovered
    OudsCheckboxControlItem.State.Pressed -> OudsCheckbox.State.Pressed
    OudsCheckboxControlItem.State.Focused -> OudsCheckbox.State.Focused
    OudsCheckboxControlItem.State.Disabled, OudsCheckboxControlItem.State.ReadOnly -> OudsCheckbox.State.Disabled
}

@Composable
private fun backgroundColor(state: OudsCheckboxControlItem.State): Color {
    return with(OudsTheme.componentsTokens.listItem) {
        when (state) {
            OudsCheckboxControlItem.State.Enabled, OudsCheckboxControlItem.State.Disabled, OudsCheckboxControlItem.State.ReadOnly -> Color.Transparent
            OudsCheckboxControlItem.State.Hovered -> colorBgHover.value
            OudsCheckboxControlItem.State.Pressed -> colorBgPressed.value
            OudsCheckboxControlItem.State.Focused -> colorBgFocus.value
        }
    }
}

@Composable
private fun textColor(state: OudsCheckboxControlItem.State, error: Boolean) =
    if (error) {
        with(OudsTheme.colorScheme.action.negative) {
            when (state) {
                OudsCheckboxControlItem.State.Enabled -> enabled
                OudsCheckboxControlItem.State.Hovered -> hover
                OudsCheckboxControlItem.State.Pressed -> pressed
                OudsCheckboxControlItem.State.Focused -> focus
                OudsCheckboxControlItem.State.Disabled, OudsCheckboxControlItem.State.ReadOnly -> enabled // Not allowed, exception thrown at the beginning of OudsCheckboxItem
            }
        }
    } else {
        if (state == OudsCheckboxControlItem.State.Disabled) OudsTheme.colorScheme.content.disabled else OudsTheme.colorScheme.content.default
    }

@Composable
private fun helperTextColor(state: OudsCheckboxControlItem.State) =
    if (state == OudsCheckboxControlItem.State.Disabled) OudsTheme.colorScheme.content.disabled else OudsTheme.colorScheme.content.muted

@UiModePreviews.Default
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsCheckboxControlItem(@PreviewParameter(OudsCheckboxControlItemPreviewParameterProvider::class) parameter: OudsCheckboxControlItemPreviewParameter) {
    PreviewOudsCheckboxControlItem(darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsCheckboxControlItem(
    darkThemeEnabled: Boolean,
    parameter: OudsCheckboxControlItemPreviewParameter
) = OudsPreview(darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        val content: @Composable () -> Unit = {
            Column(Modifier.padding(16.dp)) {
                states.forEach { state ->
                    OudsCheckboxControlItem(
                        value = toggleableState,
                        text = "Label",
                        previewState = state,
                        modifier = Modifier,
                        helperText = helperText,
                        error = error,
                        inverted = inverted,
                        icon = if (hasIcon) {
                            OudsCheckboxControlItem.Icon(imageVector = Icons.Filled.Call)
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

internal data class OudsCheckboxControlItemPreviewParameter(
    val toggleableState: ToggleableState,
    val helperText: String? = null,
    val hasIcon: Boolean = false,
    val error: Boolean = false,
    val inverted: Boolean = false
) {
    val states: List<OudsCheckboxControlItem.State> = listOf(
        OudsCheckboxControlItem.State.Enabled,
        OudsCheckboxControlItem.State.Pressed,
        OudsCheckboxControlItem.State.Hovered,
        OudsCheckboxControlItem.State.Focused,
        OudsCheckboxControlItem.State.Disabled,
        OudsCheckboxControlItem.State.ReadOnly
    )
}

internal class OudsCheckboxControlItemPreviewParameterProvider :
    BasicPreviewParameterProvider<OudsCheckboxControlItemPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsCheckboxControlItemPreviewParameter>
    get() {
        val helperText = "Helper text"
        val invertedValues = listOf(false, true)
        return buildList {
            invertedValues.forEach { inverted ->
                val parameters = listOf(
                    OudsCheckboxControlItemPreviewParameter(toggleableState = ToggleableState.Off, inverted = inverted),
                    OudsCheckboxControlItemPreviewParameter(
                        toggleableState = ToggleableState.Off,
                        hasIcon = true,
                        helperText = helperText,
                        inverted = inverted
                    ),
                    OudsCheckboxControlItemPreviewParameter(toggleableState = ToggleableState.On, helperText = helperText, error = true, inverted = inverted),
                )
                addAll(parameters)
            }
        }
    }
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

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.BlendModeColorFilter
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.component.content.OudsComponentIcon
import com.orange.ouds.core.extensions.InteractionState
import com.orange.ouds.core.extensions.collectInteractionStateAsState
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.isOudsInDarkTheme
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.CheckedContent
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.PreviewEnumEntries
import com.orange.ouds.core.utilities.getPreviewEnumEntry
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider

// TODO: Update documentation URL once it is available
/**
 * A Text Input is a user interface component that allows users to enter, edit, or select single-line textual data. It's one of the most fundamental
 * form elements used to capture user input such as names, emails, passwords, or search queries.
 *
 * It provides a visual and interactive affordance for text entry while supporting labels, placeholders, icons, helper messages, and validation feedback.
 *
 * // TODO specify where to customize outlined/filled and rounded corners
 *
 * > Design guidelines: [unified-design-system.orange.com](https://unified-design-system.orange.com)
 *
 * > Design version: 1.1.0
 *
 * @param textFieldState [TextFieldState] object that holds the internal editing state of [BasicTextField] used in [OudsTextInput].
 * @param modifier [Modifier] applied to the text input.
 * @param label Label displayed above the text input. It describe the purpose of the input.
 * @param placeholder Text displayed when the text input is empty. It provides a hint or guidance inside the field to suggest expected input.
 * @param leadingIcon An optional leading icon displayed at the start of the text input. It helps indicate the purpose of the input (magnifying glass for search,
 *   envelope for email, etc.).
 * @param trailingIconButton An optional trailing icon button displayed at the end of the text input. It is used to provide actions related to the field:
 *   clear input, toggle password visibility, etc.
 * @param prefix Text placed before the user's input. Commonly used to indicate expected formatting like a country code, a unit...
 * @param suffix Text placed after the user's input, often used to display a currency or a unit (kg, %, cm…).
 * @param enabled Controls the enabled state of the text input. When `false`, this text input will not be focusable and will not react to input events.
 *   True by default.
 * @param readOnly Controls the read-only state of the text input. When `true`, the text is visible but not editable.
 *   False by default.
 * @param loader An optional loading progress indicator displayed in the text input to indicate an ongoing operation.
 * @param error Controls the error state of the text input. When `true`, the text input will be displayed in an error state to indicates that the user input
 *   does not meet validation rules or expected formatting
 *   False by default.
 * @param helperText An optional helper text displayed below the text input. It conveys additional information about the input field, such as how it will be used.
 * @param helperLink An optional helper link displayed below or in place of the helper text.
 * @param keyboardOptions Software keyboard options that contain configurations such as [KeyboardType] and [ImeAction].
 * @param onKeyboardAction Called when the user presses the action button in the input method editor (IME), or by pressing the enter key on a hardware keyboard.
 *   By default this parameter is null, and would execute the default behavior for a received IME Action e.g., [ImeAction.Done] would close the keyboard,
 *   [ImeAction.Next] would switch the focus to the next focusable item on the screen.
 * @param interactionSource An optional hoisted [MutableInteractionSource] for observing and emitting [Interaction]s for this text input. Note that if `null`
 *   is provided, interactions will still happen internally.
 */
@Composable
fun OudsTextInput(
    textFieldState: TextFieldState,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    leadingIcon: OudsTextInput.LeadingIcon? = null,
    trailingIconButton: OudsTextInput.TrailingIconButton? = null,
    prefix: String? = null,
    suffix: String? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    loader: OudsTextInput.Loader? = null,
    error: Boolean = false,
    helperText: String? = null,
    helperLink: OudsTextInput.HelperLink? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onKeyboardAction: KeyboardActionHandler? = null,
    interactionSource: MutableInteractionSource? = null
) {

    // TODO manage alternative display in theme configuration
    val outlined = false

    @Suppress("NAME_SHADOWING") val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    val interactionState by interactionSource.collectInteractionStateAsState()
    val state = getTextInputState(enabled = enabled, readOnly = readOnly, loader = loader, interactionState = interactionState)

    val isForbidden = (state == OudsTextInput.State.Loading && (textFieldState.text.isEmpty() || error)) || (error && state in listOf(
        OudsTextInput.State.ReadOnly,
        OudsTextInput.State.Disabled
    ))

    CheckedContent(
        expression = !isForbidden,
        exceptionMessage = {
            if (state == OudsTextInput.State.Loading) {
                "An OudsTextInput with error parameter activated or an empty value cannot have a loader."
            } else {
                val parameter = if (readOnly) "readOnly" else "disabled"
                "An OudsTextInput set to $parameter with error parameter activated is not allowed."
            }
        },
        previewMessage = {
            if (state == OudsTextInput.State.Loading) {
                val statusDescription = if (error) "Error" else "Empty"
                "$statusDescription status for Loading state is not relevant"
            } else {
                val stateDescription = if (readOnly) "Read only" else "Disabled"
                "Error status for $stateDescription state is not relevant"
            }
        }
    ) {
        BasicTextField(
            modifier = modifier.fillMaxWidth(),
            state = textFieldState,
            enabled = enabled,
            readOnly = readOnly,
            textStyle = OudsTheme.typography.label.default.large.copy(color = OudsTheme.colorScheme.content.default),
            lineLimits = TextFieldLineLimits.SingleLine,
            cursorBrush = SolidColor(cursorColor(state = state, error = error)),
            keyboardOptions = keyboardOptions,
            onKeyboardAction = onKeyboardAction,
            interactionSource = interactionSource,
            decorator = { innerTextField ->
                with(OudsTheme.componentsTokens.textInput) {
                    val rowModifier = if (outlined) {
                        Modifier.border(
                            width = if (state == OudsTextInput.State.Focused) OudsTextInput.focusBorderWidth else OudsTextInput.defaultBorderWidth,
                            color = indicatorColor(state = state, error = error),
                            shape = RoundedCornerShape(borderRadiusDefault.value)
                        )
                    } else {
                        Modifier
                            .indicator(state = state, error = error, cornerRadius = borderRadiusDefault.value)
                            .background(
                                color = containerColor(state = state, error = error),
                                shape = RoundedCornerShape(borderRadiusDefault.value)
                            )
                    }

                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = rowModifier
                                .fillMaxWidth()
                                .sizeIn(minHeight = sizeMinHeight.dp)
                                .padding(vertical = spacePaddingBlockDefault.value)
                                .padding(
                                    start = spacePaddingInlineDefault.value,
                                    end = if (trailingIconButton != null) spacePaddingInlineTrailingAction.value else spacePaddingInlineDefault.value
                                ),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(spaceColumnGapDefault.value)

                        ) {
                            // Leading icon
                            leadingIcon?.Content()

                            // Central content
                            Column(
                                modifier = Modifier.weight(1f),
                                verticalArrangement = Arrangement.Center
                            ) {
                                if (label != null && (textFieldState.text.isNotEmpty() || !placeholder.isNullOrEmpty())) {
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = label,
                                        style = OudsTheme.typography.label.default.small,
                                        color = labelColor(state = state, error = error)
                                    )
                                }
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(spaceColumnGapInlineText.value)
                                ) {
                                    if (prefix != null) PrefixSuffixText(text = prefix)
                                    Box(modifier = Modifier.weight(1f)) {
                                        if (textFieldState.text.isEmpty()) {
                                            if (!placeholder.isNullOrEmpty()) {
                                                Text(
                                                    text = placeholder,
                                                    style = OudsTheme.typography.label.default.large,
                                                    color = placeholderColor(state = state)
                                                )
                                            } else if (!label.isNullOrEmpty()) {
                                                Text(
                                                    text = label,
                                                    style = OudsTheme.typography.label.default.large,
                                                    color = labelColor(state = state, error = error)
                                                )
                                            }
                                        }
                                        innerTextField()
                                    }
                                    if (suffix != null) PrefixSuffixText(text = suffix)
                                }
                            }

                            // Trailing elements
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(spaceColumnGapTrailingErrorAction.value)
                            ) {
                                if (error) {
                                    // Error icon
                                    Box(modifier = Modifier.size(OudsTheme.componentsTokens.button.sizeIconOnly.value), contentAlignment = Alignment.Center) {
                                        Icon(
                                            painter = painterResource(id = OudsTheme.drawableResources.important),
                                            contentDescription = null,
                                            tint = errorContentColor(state = state)
                                        )
                                    }
                                }

                                if (state == OudsTextInput.State.Loading) {
                                    val progress = if (getPreviewEnumEntry<OudsTextInput.State>() == OudsTextInput.State.Loading) 0.75f else loader?.progress
                                    OudsCircularProgressIndicator(
                                        color = OudsTheme.componentsTokens.button.colorContentMinimalLoading.value,
                                        progress = progress
                                    )
                                } else {
                                    trailingIconButton?.Content()
                                }
                            }
                        }

                        // Helper text
                        if (helperText != null) {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = spacePaddingBlockTopHelperText.value)
                                    .padding(horizontal = spacePaddingInlineDefault.value),
                                text = helperText,
                                style = OudsTheme.typography.label.default.medium,
                                color = if (error) OudsTheme.colorScheme.content.status.negative else OudsTheme.colorScheme.content.muted
                            )
                        }
                    }
                }
            }
        )
    }
}

@Composable
private fun Modifier.indicator(state: OudsTextInput.State, error: Boolean, cornerRadius: Dp) =
    if (state == OudsTextInput.State.ReadOnly) {
        border(width = OudsTextInput.defaultBorderWidth, color = OudsTheme.colorScheme.border.muted)
    } else {
        bottomBorder(
            width = if (state == OudsTextInput.State.Focused) OudsTextInput.focusBorderWidth else OudsTextInput.defaultBorderWidth,
            color = indicatorColor(state = state, error = error),
            cornerRadius = cornerRadius
        )
    }

@Composable
private fun Modifier.bottomBorder(width: Dp, color: Color, cornerRadius: Dp): Modifier {
val backgroundColor = OudsTheme.colorScheme.background.primary
    return drawWithContent {
        val cornerRadiusPx = cornerRadius.toPx()
        drawRoundRect(
            color = color,
            topLeft = Offset(x = 0f, y = size.height - 2 * cornerRadiusPx),
            size = size.copy(width = size.width, height = 2 * cornerRadiusPx + width.toPx()),
            cornerRadius = CornerRadius(cornerRadiusPx),
        )
        drawRoundRect(
            color = Color.Transparent,
            topLeft = Offset(x = 0f, y = 0f),
            size = size.copy(width = size.width, height = size.height),
            cornerRadius = CornerRadius(cornerRadiusPx),
            colorFilter = BlendModeColorFilter(backgroundColor, BlendMode.SrcOut)
        )
        drawContent()
    }
}

@Composable
private fun PrefixSuffixText(text: String) {
    Text(text = text, style = OudsTheme.typography.label.default.large, color = OudsTheme.colorScheme.content.muted)
}

@Composable
private fun getTextInputState(enabled: Boolean, readOnly: Boolean, loader: OudsTextInput.Loader?, interactionState: InteractionState): OudsTextInput.State {
    return getPreviewEnumEntry<OudsTextInput.State>().orElse {
        if (loader != null) {
            OudsTextInput.State.Loading
        } else {
            when {
                !enabled -> OudsTextInput.State.Disabled
                readOnly -> OudsTextInput.State.ReadOnly
                interactionState == InteractionState.Hovered -> OudsTextInput.State.Hovered
                interactionState == InteractionState.Pressed -> OudsTextInput.State.Pressed
                interactionState == InteractionState.Focused -> OudsTextInput.State.Focused
                else -> OudsTextInput.State.Enabled
            }
        }
    }
}

@Composable
private fun containerColor(state: OudsTextInput.State, error: Boolean): Color {
    return if (error) {
        OudsTheme.colorScheme.surface.status.negative.muted
    } else {
        when (state) {
            OudsTextInput.State.Enabled -> OudsTheme.colorScheme.action.support.enabled
            OudsTextInput.State.Hovered -> OudsTheme.colorScheme.action.support.hover
            OudsTextInput.State.Pressed, OudsTextInput.State.Focused -> OudsTheme.colorScheme.action.support.pressed
            OudsTextInput.State.Disabled -> OudsTheme.colorScheme.action.support.disabled
            OudsTextInput.State.ReadOnly -> Color.Transparent
            OudsTextInput.State.Loading -> OudsTheme.colorScheme.action.support.loading
        }
    }
}

@Composable
private fun cursorColor(state: OudsTextInput.State, error: Boolean) =
    if (error) errorContentColor(state = state) else OudsTheme.colorScheme.content.default

@Composable
private fun indicatorColor(state: OudsTextInput.State, error: Boolean) = labelColor(state = state, error = error)

@Composable
private fun labelColor(state: OudsTextInput.State, error: Boolean): Color {
    return if (error) {
        errorContentColor(state = state)
    } else {
        if (state == OudsTextInput.State.Disabled) {
            OudsTheme.colorScheme.action.disabled
        } else {
            OudsTheme.colorScheme.content.muted
        }
    }
}

@Composable
private fun errorContentColor(state: OudsTextInput.State) = when (state) {
    OudsTextInput.State.Enabled -> OudsTheme.colorScheme.action.negative.enabled
    OudsTextInput.State.Hovered -> OudsTheme.colorScheme.action.negative.hover
    OudsTextInput.State.Pressed, OudsTextInput.State.Focused -> OudsTheme.colorScheme.action.negative.pressed
    OudsTextInput.State.Disabled, OudsTextInput.State.ReadOnly, OudsTextInput.State.Loading -> Color.Unspecified // Not relevant, exception thrown at the beginning of OudsTextInput
}

@Composable
private fun placeholderColor(state: OudsTextInput.State) =
    if (state == OudsTextInput.State.Disabled) {
        OudsTheme.colorScheme.action.disabled
    } else {
        OudsTheme.colorScheme.content.muted
    }

/**
 * Contains classes to build an [OudsTextInput].
 */
object OudsTextInput {
    internal val defaultBorderWidth = 1.dp
    internal val focusBorderWidth = 2.dp

    internal enum class State {
        Enabled, Hovered, Pressed, Disabled, Focused, ReadOnly, Loading
    }

    /**
     * An helper link displayed below the text input, below or in place of the helper text.
     */
    data class HelperLink(val text: String, val onClick: () -> Unit)

    /**
     * A circular loading indicator displayed in the text input.
     *
     * @param progress The loading progress, where 0.0 represents no progress and 1.0 represents full progress.
     *   Values outside of this range are coerced into the range.
     *   Set this value to `null` to display a circular indeterminate progress indicator.
     */
    data class Loader(val progress: Float?)

    /**
     * A leading icon in an [OudsTextInput].
     * This icon is non-clickable.
     */
    class LeadingIcon private constructor(
        graphicsObject: Any,
        val contentDescription: String
    ) : OudsComponentIcon<Nothing>(Nothing::class.java, graphicsObject, contentDescription) {

        /**
         * Creates an instance of [OudsTextInput.LeadingIcon].
         *
         * @param painter Painter of the icon.
         * @param contentDescription The content description associated with this [OudsTextInput.LeadingIcon].
         */
        constructor(painter: Painter, contentDescription: String) : this(painter as Any, contentDescription)

        /**
         * Creates an instance of [OudsTextInput.LeadingIcon].
         *
         * @param imageVector Image vector of the icon.
         * @param contentDescription The content description associated with this [OudsTextInput.LeadingIcon].
         */
        constructor(imageVector: ImageVector, contentDescription: String) : this(imageVector as Any, contentDescription)

        /**
         * Creates an instance of [OudsTextInput.LeadingIcon].
         *
         * @param bitmap Image bitmap of the icon.
         * @param contentDescription The content description associated with this [OudsTextInput.LeadingIcon].
         */
        constructor(bitmap: ImageBitmap, contentDescription: String) : this(bitmap as Any, contentDescription)

        override val tint: Color?
            @Composable
            get() = OudsTheme.colorScheme.content.default
    }

    /**
     * A trailing icon button in an [OudsTextInput].
     * Displays an icon only [OudsButton].
     */
    class TrailingIconButton private constructor(
        graphicsObject: Any,
        val contentDescription: String,
        val enabled: Boolean = true,
        val onClick: () -> Unit
    ) : OudsComponentIcon<Nothing>(Nothing::class.java, graphicsObject, contentDescription, enabled, onClick) {

        /**
         * Creates an instance of [OudsTextInput.TrailingIconButton].
         *
         * @param painter Painter of the icon.
         * @param contentDescription The content description associated to this [OudsTextInput.TrailingIconButton].
         * @param enabled Controls the enabled state of the [OudsTextInput.TrailingIconButton]. When `false`, this button will not
         * be clickable, true by default.
         * @param onClick Callback invoked when the button is clicked.
         */
        constructor(
            painter: Painter,
            contentDescription: String,
            enabled: Boolean = true,
            onClick: () -> Unit
        ) : this(painter as Any, contentDescription, enabled, onClick)

        /**
         * Creates an instance of [OudsTextInput.TrailingIconButton].
         *
         * @param imageVector Image vector of the icon.
         * @param contentDescription The content description associated to this [OudsTextInput.TrailingIconButton].
         * @param enabled Controls the enabled state of the [OudsTextInput.TrailingIconButton]. When `false`, this button will not
         * be clickable, true by default.
         * @param onClick Callback invoked when the button is clicked.
         */
        constructor(
            imageVector: ImageVector,
            contentDescription: String,
            enabled: Boolean = true,
            onClick: () -> Unit
        ) : this(imageVector as Any, contentDescription, enabled, onClick)

        /**
         * Creates an instance of [OudsTextInput.TrailingIconButton].
         *
         * @param bitmap Image bitmap of the icon.
         * @param contentDescription The content description associated to this [OudsTextInput.TrailingIconButton].
         * @param enabled Controls the enabled state of the [OudsTextInput.TrailingIconButton]. When `false`, this button will not
         * be clickable, true by default.
         * @param onClick Callback invoked when the button is clicked.
         */
        constructor(
            bitmap: ImageBitmap,
            contentDescription: String,
            enabled: Boolean = true,
            onClick: () -> Unit
        ) : this(bitmap as Any, contentDescription, enabled, onClick)
    }
}

internal object OudsTextInputPreview {
    const val heightDp = 950
}

@Preview(name = "Light", heightDp = OudsTextInputPreview.heightDp)
@Preview(name = "Dark", uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL, heightDp = OudsTextInputPreview.heightDp)
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsTextInput(@PreviewParameter(OudsTextInputPreviewParameterProvider::class) parameter: OudsTextInputPreviewParameter) {
    PreviewOudsTextInput(darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsTextInput(darkThemeEnabled: Boolean, parameter: OudsTextInputPreviewParameter) = OudsPreview(darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        PreviewEnumEntries<OudsTextInput.State>(columnCount = 1) { state ->
            OudsTextInput(
                textFieldState = rememberTextFieldState(initialValue),
                label = label,
                placeholder = placeholder,
                leadingIcon = leadingIcon,
                trailingIconButton = trailingIconButton,
                prefix = prefix,
                suffix = suffix,
                error = error,
                helperText = helperText
            )
        }
    }
}

internal data class OudsTextInputPreviewParameter(
    val initialValue: String,
    val label: String? = null,
    val placeholder: String? = null,
    val leadingIcon: OudsTextInput.LeadingIcon? = null,
    val trailingIconButton: OudsTextInput.TrailingIconButton? = null,
    val prefix: String? = null,
    val suffix: String? = null,
    val enabled: Boolean = true,
    val readOnly: Boolean = false,
    val error: Boolean = false,
    val helperText: String? = null
)

internal class OudsTextInputPreviewParameterProvider : BasicPreviewParameterProvider<OudsTextInputPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsTextInputPreviewParameter>
    get() {
        val label = "Label"
        val leadingIcon = OudsTextInput.LeadingIcon(Icons.Filled.FavoriteBorder, contentDescription = "Icon")
        val trailingIconButton = OudsTextInput.TrailingIconButton(Icons.Filled.FavoriteBorder, contentDescription = "Icon", onClick = {})
        return listOf(
            OudsTextInputPreviewParameter("", label = label),
            OudsTextInputPreviewParameter(
                "",
                label = label,
                placeholder = "Placeholder",
                leadingIcon = leadingIcon,
                trailingIconButton = trailingIconButton,
                prefix = "£",
                suffix = "€",
                error = true
            ),
            OudsTextInputPreviewParameter("Text input", label = label, helperText = "Helper text."),
            OudsTextInputPreviewParameter("Error text", label = label, error = true, helperText = "The format is not valid.")
        )
    }

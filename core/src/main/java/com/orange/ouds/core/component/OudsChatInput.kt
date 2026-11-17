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

@file:OptIn(RestrictedOudsApi::class)

package com.orange.ouds.core.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.hideFromAccessibility
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import com.orange.ouds.core.R
import com.orange.ouds.core.component.common.OudsComponentState
import com.orange.ouds.core.component.common.OudsError
import com.orange.ouds.core.component.common.text.OudsAnnotatedHelperText
import com.orange.ouds.core.component.content.OudsComponentContent
import com.orange.ouds.core.component.content.OudsComponentIcon
import com.orange.ouds.core.extensions.collectInteractionStateAsState
import com.orange.ouds.core.theme.LocalSkeleton
import com.orange.ouds.core.theme.LocalThemeSettings
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.OudsPreviewDevice
import com.orange.ouds.core.utilities.OudsPreviewableComponent
import com.orange.ouds.core.utilities.PreviewCustomContent
import com.orange.ouds.core.utilities.PreviewEnumEntries
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.foundation.RestrictedOudsApi
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.theme.OudsThemeContract

private const val ComponentName = "OudsChatInput"

@Composable
fun OudsChatInput(
    textFieldState: TextFieldState,
    onSend: () -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    bottomIconButtons: List<OudsChatInputBottomIconButton> = emptyList(),
    bottomContent: @Composable (OudsChatInputScope.() -> Unit)? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    loader: OudsTextInputLoader? = null,
    outlined: Boolean = false,
    error: OudsError? = null,
    helperText: String? = null,
    helperLink: OudsTextInputHelperLink? = null,
    constrainedMaxWidth: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onKeyboardAction: KeyboardActionHandler? = null,
    onTextLayout: (Density.(getResult: () -> TextLayoutResult?) -> Unit)? = null,
    inputTransformation: InputTransformation? = null,
    outputTransformation: OutputTransformation? = null,
    interactionSource: MutableInteractionSource? = null,
    autoResize: Boolean = true,
    skeleton: OudsSkeleton? = LocalSkeleton.current
) {
    @Suppress("NAME_SHADOWING") val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    val interactionState by interactionSource.collectInteractionStateAsState()
    val state = getTextInputState(enabled = enabled, readOnly = readOnly, loader = loader, skeleton = skeleton, interactionState = interactionState)
    val emptyText = textFieldState.text.isEmpty()
    val scrollState = rememberScrollState()

    CheckedTextInput(
        componentName = ComponentName,
        state = state,
        emptyText = emptyText,
        readOnly = readOnly,
        error = error,
        basicTextField = {
            SkeletonLayout(
                modifier = modifier,
                componentState = state,
                state = skeleton?.state,
                securityMargin = false
            ) { contentModifier ->
                val value = textFieldState.text.toString()
                val visibleContent = getVisibleTextInputContent(
                    value,
                    label,
                    placeholder,
                    null,
                    null,
                    null,
                    null,
                    error,
                    helperText,
                    null,
                    helperLink,
                    state
                )
                BasicTextField(
                    modifier = contentModifier.textInputSemantics(value, label, placeholder, null, null, visibleContent),
                    state = textFieldState,
                    enabled = state.areInteractionsEnabled,
                    readOnly = readOnly,
                    textStyle = textInputTextStyle(state = state),
                    lineLimits = TextFieldLineLimits.MultiLine(
                        minHeightInLines = ChatInputMinLines,
                        maxHeightInLines = if (autoResize) textAreaMaxLines else ChatInputMinLines
                    ),
                    cursorBrush = textInputCursorBrush(state = state, error = error != null),
                    keyboardOptions = keyboardOptions,
                    onKeyboardAction = onKeyboardAction,
                    onTextLayout = onTextLayout,
                    inputTransformation = inputTransformation,
                    outputTransformation = outputTransformation,
                    interactionSource = interactionSource,
                    decorator = { innerTextField ->
                        OudsChatInputDecorator(
                            innerTextField = innerTextField,
                            value = value,
                            onSend = onSend,
                            state = state,
                            label = label,
                            placeholder = placeholder,
                            bottomIconButtons = bottomIconButtons,
                            bottomContent = bottomContent,
                            loader = loader,
                            outlined = outlined,
                            error = error,
                            helperText = helperText,
                            annotatedHelperText = null,
                            helperLink = helperLink,
                            constrainedMaxWidth = constrainedMaxWidth,
                            scrollState = scrollState,
                        )
                    },
                    scrollState = scrollState
                )
            }
        }
    )
}

@Composable
internal fun OudsChatInputDecorator(
    innerTextField: @Composable () -> Unit,
    value: String,
    onSend: () -> Unit,
    state: OudsTextInputState,
    label: String?,
    placeholder: String?,
    bottomIconButtons: List<OudsChatInputBottomIconButton>,
    bottomContent: @Composable (OudsChatInputScope.() -> Unit)?,
    loader: OudsTextInputLoader?,
    outlined: Boolean,
    error: OudsError?,
    helperText: String?,
    annotatedHelperText: OudsAnnotatedHelperText?,
    helperLink: OudsTextInputHelperLink?,
    constrainedMaxWidth: Boolean,
    scrollState: ScrollState = rememberScrollState(),
) {
    val visibleContent = getVisibleTextInputContent(
        value,
        label,
        placeholder,
        null,
        null,
        null,
        null,
        error,
        helperText,
        annotatedHelperText,
        helperLink,
        state
    )
    val hasError = error != null
    val textInputTokens = OudsTheme.components.textInput
    val textAreaTokens = OudsTheme.components.textArea
    val buttonTokens = OudsTheme.components.button
    val borderWidth = textInputBorderWidth(state = state)
    val borderColor = textInputBorderColor(state = state, outlined = outlined, error = hasError)
    val backgroundColor = textInputBackgroundColor(state = state, outlined = outlined, error = hasError)

    Column(
        modifier = Modifier.widthIn(
            min = textInputTokens.size.minWidth,
            max = if (constrainedMaxWidth) textAreaTokens.size.maxWidth else Dp.Unspecified
        )
    ) {
        Column(
            modifier = Modifier
                .textInputBorder(
                    borderWidth = borderWidth,
                    borderColor = borderColor,
                    shape = chatInputShape,
                    cornerRadius = chatInputBorderRadius,
                    state = state,
                    outlined = outlined,
                    error = error
                )
                .background(color = backgroundColor, shape = chatInputShape)
        ) {
            Row(
                modifier = Modifier
                    .padding(vertical = textAreaTokens.space.paddingBlock)
                    .padding(
                        start = textInputTokens.space.paddingInline.default,
                        end = with(textInputTokens) {
                            if (hasError || state == OudsTextInputState.Loading) space.paddingInline.trailingAction else space.paddingInline.default
                        }
                    )
                    .heightIn(min = textInputTokens.size.minHeight)
                    .textAreaVerticalScrollBar(scrollState = scrollState),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(textInputTokens.space.columnGap.default)
            ) {
                Box(modifier = Modifier.weight(1.0f)) {
                    if (visibleContent.contains(OudsTextInputContent.Label)) {
                        Text(
                            modifier = Modifier.semantics { hideFromAccessibility() },
                            text = label.orEmpty(),
                            style = OudsTheme.typography.label.large.default,
                            color = labelColor(state = state, error = hasError),
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    if (visibleContent.contains(OudsTextInputContent.Placeholder)) {
                        Text(
                            modifier = Modifier.semantics { hideFromAccessibility() },
                            text = placeholder.orEmpty(),
                            style = OudsTheme.typography.label.large.default,
                            color = decorativeContentColor(state = state),
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    innerTextField()
                }

                // Trailing elements (error icon or loader)
                Box(
                    modifier = Modifier
                        .widthIn(min = buttonTokens.size.minWidthDefault)
                        .padding(
                            horizontal = buttonTokens.space.inset.iconOnlyDefault,
                            vertical = textAreaTokens.space.paddingBlockEmptyTrailingContainer
                        )
                ) {
                    // Error icon
                    if (visibleContent.contains(OudsTextInputContent.ErrorIcon)) {
                        OudsTextInputErrorIcon(errorMessage = error?.message, state = state)
                    }

                    // Loader
                    if (visibleContent.contains(OudsTextInputContent.Loader)) {
                        OudsTextInputCircularProgressIndicator(loader)
                    }
                }
            }

            Row(
                modifier = Modifier
                    .padding(bottom = textInputTokens.space.paddingBlock.default)
                    .padding(horizontal = textInputTokens.space.paddingInline.trailingAction)
                    .height(IntrinsicSize.Min),
                verticalAlignment = Alignment.CenterVertically
            ) {
                bottomIconButtons.take(3).forEach { bottomIconButton ->
                    bottomIconButton.Content(
                        extraParameters = OudsChatInputBottomIconButton.ExtraParameters(
                            enabled = state.areInteractionsEnabled
                        )
                    )
                }
                val scope = remember { OudsChatInputScope() }
                Box(modifier = Modifier.weight(1.0f)) {
                    with(scope) {
                        this.state = OudsChatInputState.fromTextInputState(state)
                        bottomContent?.invoke(this)
                    }
                }
                OudsChatInputBottomIconButton(
                    painterResource(OudsTheme.drawableResources.functional.actions.send),
                    stringResource(R.string.core_chatInput_send_a11y),
                    onClick = onSend
                ).Content(
                    extraParameters = OudsChatInputBottomIconButton.ExtraParameters(
                        enabled = state.areInteractionsEnabled
                    )
                )
            }
        }

        OudsTextInputBottomContent(
            state = state,
            error = error,
            helperText = helperText,
            annotatedHelperText = annotatedHelperText,
            helperLink = helperLink,
            horizontalPadding = OudsTheme.components.textInput.space.paddingInline.default,
            visibleContent = visibleContent
        )
    }
}

private val chatInputBorderRadius: Dp
    @Composable
    get() = with(OudsTheme.components.textInput) {
        if (LocalThemeSettings.current.roundedCornerChatInputs == true) border.radius.rounded else border.radius.default
    }

private val chatInputShape: Shape
    @Composable
    get() = RoundedCornerShape(chatInputBorderRadius)

private const val ChatInputMinLines: Int = 1

enum class OudsChatInputState : OudsComponentState {
    Enabled, Hovered, Disabled, Focused, ReadOnly, Loading, Skeleton;

    internal companion object {

        fun fromTextInputState(state: OudsTextInputState): OudsChatInputState {
            return when (state) {
                OudsTextInputState.Enabled -> Enabled
                OudsTextInputState.Hovered -> Hovered
                OudsTextInputState.Disabled -> Disabled
                OudsTextInputState.Focused -> Focused
                OudsTextInputState.ReadOnly -> ReadOnly
                OudsTextInputState.Loading -> Loading
                OudsTextInputState.Skeleton -> Skeleton
            }
        }
    }
}

class OudsChatInputBottomIconButton private constructor(
    graphicsObject: Any,
    contentDescription: String,
    override val tinted: Boolean,
    onClick: () -> Unit
) : OudsComponentIcon<OudsChatInputBottomIconButton.ExtraParameters, OudsChatInputBottomIconButton>(
    ExtraParameters::class.java,
    { graphicsObject },
    { contentDescription },
    onClick
) {

    class ExtraParameters internal constructor(
        internal val enabled: Boolean
    ) : OudsComponentContent.ExtraParameters()

    /**
     * Creates an instance of [OudsChatInputBottomIconButton].
     *
     * @param painter Painter of the icon.
     * @param contentDescription The content description associated with this [OudsChatInputBottomIconButton].
     * @param tinted Controls whether the icon should be tinted with the theme color. Defaults to `true`.
     *   When set to `false`, the icon is displayed with its original colors (e.g., for multi-color icons).
     *   Note that untinted icons must ensure sufficient contrast with the background for accessibility reasons.
     * @param onClick Callback invoked when the icon is clicked.
     */
    constructor(
        painter: Painter,
        contentDescription: String,
        tinted: Boolean = true,
        onClick: () -> Unit
    ) : this(painter as Any, contentDescription, tinted, onClick)

    /**
     * Creates an instance of [OudsChatInputBottomIconButton].
     *
     * @param imageVector Image vector of the icon.
     * @param contentDescription The content description associated with this [OudsChatInputBottomIconButton].
     * @param tinted Controls whether the icon should be tinted with the theme color. Defaults to `true`.
     *   When set to `false`, the icon is displayed with its original colors (e.g., for multi-color icons).
     *   Note that untinted icons must ensure sufficient contrast with the background for accessibility reasons.
     * @param onClick Callback invoked when the icon is clicked.
     */
    constructor(
        imageVector: ImageVector,
        contentDescription: String,
        tinted: Boolean = true,
        onClick: () -> Unit
    ) : this(imageVector as Any, contentDescription, tinted, onClick)

    /**
     * Creates an instance of [OudsChatInputBottomIconButton].
     *
     * @param bitmap Image bitmap of the icon.
     * @param contentDescription The content description associated with this [OudsChatInputBottomIconButton].
     * @param tinted Controls whether the icon should be tinted with the theme color. Defaults to `true`.
     *   When set to `false`, the icon is displayed with its original colors (e.g., for multi-color icons).
     *   Note that untinted icons must ensure sufficient contrast with the background for accessibility reasons.
     * @param onClick Callback invoked when the icon is clicked.
     */
    constructor(
        bitmap: ImageBitmap,
        contentDescription: String,
        tinted: Boolean = true,
        onClick: () -> Unit
    ) : this(bitmap as Any, contentDescription, tinted, onClick)

    override val enabled: Boolean?
        @Composable
        get() = extraParameters.enabled

    override val size: OudsButtonSize
        get() = OudsButtonSize.Small
}

class OudsChatInputScope {

    var state: OudsChatInputState by mutableStateOf(OudsChatInputState.Enabled)
        internal set
}

@Preview(name = "Light", heightDp = OudsPreviewableComponent.ChatInput.Default.PreviewHeightDp, device = OudsPreviewDevice)
@Preview(
    name = "Dark",
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL,
    heightDp = OudsPreviewableComponent.ChatInput.Default.PreviewHeightDp,
    device = OudsPreviewDevice
)
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsChatInput(@PreviewParameter(OudsChatInputPreviewParameterProvider::class) parameter: OudsChatInputPreviewParameter) {
    PreviewOudsChatInput(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsChatInput(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsChatInputPreviewParameter
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    with(parameter) {
        PreviewEnumEntries<OudsTextInputState>(maxEnumEntriesInEachRow = 1) { state ->
            OudsChatInput(
                //modifier = Modifier.width(300.dp),
                textFieldState = rememberTextFieldState(value),
                onSend = {},
                label = label,
                placeholder = placeholder,
                bottomIconButtons = bottomIconButtons,
                bottomContent = bottomContent,
                outlined = outlined,
                error = error,
                helperText = helperText,
                helperLink = helperLink
            )
        }
    }
}

internal data class OudsChatInputPreviewParameter(
    val value: String,
    val label: String? = null,
    val outlined: Boolean = false,
    val placeholder: String? = null,
    val bottomIconButtons: List<OudsChatInputBottomIconButton> = emptyList(),
    val bottomContent: @Composable (OudsChatInputScope.() -> Unit)? = null,
    val error: OudsError? = null,
    val helperText: String? = null,
    val helperLink: OudsTextInputHelperLink? = null
)

internal class OudsChatInputPreviewParameterProvider : BasicPreviewParameterProvider<OudsChatInputPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsChatInputPreviewParameter>
    get() {
        val label = "Label"
        val placeholder = "Placeholder"
        val error = OudsError("Error message.")
        val helperText = "Helper text."
        return listOf(
            OudsChatInputPreviewParameter(value = "", label = label),
            OudsChatInputPreviewParameter(
                value = "",
                label = label,
                bottomIconButtons = listOf(
                    OudsChatInputBottomIconButton(Icons.Filled.Favorite, "") {},
                    OudsChatInputBottomIconButton(Icons.Filled.DateRange, "") {},
                    OudsChatInputBottomIconButton(Icons.Filled.MoreVert, "") {},
                    OudsChatInputBottomIconButton(Icons.Filled.Place, "") {}
                ),
                bottomContent = { PreviewCustomContent(modifier = Modifier.fillMaxHeight(), state = state) }
            ),
            OudsChatInputPreviewParameter(
                "Text content",
                label = label,
                placeholder = placeholder,
                helperText = helperText,
                helperLink = OudsTextInputHelperLink("Helper link") {}
            ),
            OudsChatInputPreviewParameter("Error text", label = label, error = error, helperText = helperText),
            OudsChatInputPreviewParameter("", label = label, outlined = true)
        )
    }

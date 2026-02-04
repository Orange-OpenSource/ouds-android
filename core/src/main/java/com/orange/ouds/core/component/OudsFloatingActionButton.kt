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
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.component.common.outerBorder
import com.orange.ouds.core.component.content.OudsComponentContent
import com.orange.ouds.core.component.content.OudsComponentIcon
import com.orange.ouds.core.extensions.InteractionState
import com.orange.ouds.core.extensions.collectInteractionStateAsState
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.OudsPreviewableComponent
import com.orange.ouds.core.utilities.PreviewEnumEntries
import com.orange.ouds.core.utilities.PreviewGrid
import com.orange.ouds.core.utilities.getPreviewEnumEntry
import com.orange.ouds.core.utilities.getPreviewGridColumn
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.utilities.EnumPreviewParameterProvider
import com.orange.ouds.theme.OudsThemeContract
import kotlin.enums.enumEntries

@Composable
fun OudsFloatingActionButton(
    icon: OudsFloatingActionButtonIcon,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    appearance: OudsFloatingActionButtonAppearance = OudsFloatingActionButtonDefaults.Appearance,
    interactionSource: MutableInteractionSource? = null
) {
    @Suppress("NAME_SHADOWING") val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    val interactionState by interactionSource.collectInteractionStateAsState()
    val state = getFloatingActionButtonState(interactionState = interactionState)

    FloatingActionButton(
        onClick = onClick,
        modifier = modifier.outerBorder(state = state, shape = FloatingActionButtonDefaults.shape),
        containerColor = containerColor(appearance = appearance, state = state),
        contentColor = Color.Unspecified,
        elevation = elevation(),
        interactionSource = interactionSource
    ) {
        Icon(icon = icon, appearance = appearance, state = state)
    }
}

@Composable
fun OudsSmallFloatingActionButton(
    icon: OudsFloatingActionButtonIcon,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    appearance: OudsFloatingActionButtonAppearance = OudsFloatingActionButtonDefaults.Appearance,
    interactionSource: MutableInteractionSource? = null
) {
    @Suppress("NAME_SHADOWING") val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    val interactionState by interactionSource.collectInteractionStateAsState()
    val state = getFloatingActionButtonState(interactionState = interactionState)

    SmallFloatingActionButton(
        onClick = onClick,
        modifier = modifier.outerBorder(state = state, shape = FloatingActionButtonDefaults.smallShape),
        containerColor = containerColor(appearance = appearance, state = state),
        contentColor = Color.Unspecified,
        elevation = elevation(),
        interactionSource = interactionSource
    ) {
        Icon(icon = icon, appearance = appearance, state = state)
    }
}

@Composable
fun OudsLargeFloatingActionButton(
    icon: OudsFloatingActionButtonIcon,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    appearance: OudsFloatingActionButtonAppearance = OudsFloatingActionButtonDefaults.Appearance,
    interactionSource: MutableInteractionSource? = null
) {
    @Suppress("NAME_SHADOWING") val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    val interactionState by interactionSource.collectInteractionStateAsState()
    val state = getFloatingActionButtonState(interactionState = interactionState)

    LargeFloatingActionButton(
        onClick = onClick,
        modifier = modifier.outerBorder(state = state, shape = FloatingActionButtonDefaults.largeShape),
        containerColor = containerColor(appearance = appearance, state = state),
        contentColor = Color.Unspecified,
        elevation = elevation(),
        interactionSource = interactionSource
    ) {
        Icon(icon = icon, appearance = appearance, state = state, large = true)
    }
}

@Composable
fun OudsExtendedFloatingActionButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    appearance: OudsFloatingActionButtonAppearance = OudsFloatingActionButtonDefaults.Appearance,
    interactionSource: MutableInteractionSource? = null
) {
    @Suppress("NAME_SHADOWING") val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    val interactionState by interactionSource.collectInteractionStateAsState()
    val state = getFloatingActionButtonState(interactionState = interactionState)
    ExtendedFloatingActionButton(
        onClick = onClick,
        modifier = modifier.outerBorder(state = state, shape = FloatingActionButtonDefaults.extendedFabShape),
        containerColor = containerColor(appearance = appearance, state = state),
        contentColor = Color.Unspecified,
        elevation = elevation(),
        interactionSource = interactionSource
    ) {
        Text(label = label, appearance = appearance, state = state)
    }
}

@Composable
fun OudsExtendedFloatingActionButton(
    label: String,
    icon: OudsFloatingActionButtonIcon,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    expanded: Boolean = true,
    appearance: OudsFloatingActionButtonAppearance = OudsFloatingActionButtonDefaults.Appearance,
    interactionSource: MutableInteractionSource? = null
) {
    @Suppress("NAME_SHADOWING") val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    val interactionState by interactionSource.collectInteractionStateAsState()
    val state = getFloatingActionButtonState(interactionState = interactionState)
    ExtendedFloatingActionButton(
        text = { Text(label = label, appearance = appearance, state = state) },
        icon = { Icon(icon = icon, appearance = appearance, state = state) },
        onClick = onClick,
        modifier = modifier.outerBorder(state = state, shape = FloatingActionButtonDefaults.extendedFabShape),
        expanded = expanded,
        containerColor = containerColor(appearance = appearance, state = state),
        contentColor = Color.Unspecified,
        elevation = elevation(),
        interactionSource = interactionSource
    )
}

@Composable
private fun elevation() = FloatingActionButtonDefaults.elevation(
    defaultElevation = 0.dp,
    pressedElevation = 0.dp,
    focusedElevation = 0.dp,
    hoveredElevation = 0.dp
)

@Composable
private fun Icon(
    icon: OudsFloatingActionButtonIcon,
    appearance: OudsFloatingActionButtonAppearance,
    state: OudsFloatingActionButtonState,
    large: Boolean = false
) {
    val iconSize = if (large) OudsTheme.sizes.icon.withLabel.large.sizeLarge else OudsTheme.componentsTokens.button.sizeIconOnly.value
    val iconScale = LocalConfiguration.current.fontScale
    val tint = contentColor(appearance = appearance, state = state)
    icon.Content(
        modifier = Modifier
            .size(iconSize * iconScale)
            .semantics {
                contentDescription = icon.contentDescription
            },
        extraParameters = OudsFloatingActionButtonIcon.ExtraParameters(tint = tint)
    )
}

@Composable
private fun Text(label: String, appearance: OudsFloatingActionButtonAppearance, state: OudsFloatingActionButtonState) {
    Text(
        text = label,
        color = contentColor(appearance = appearance, state = state),
        style = OudsTheme.typography.label.strong.large
    )
}

@Composable
private fun getFloatingActionButtonState(interactionState: InteractionState): OudsFloatingActionButtonState {
    return getPreviewEnumEntry<OudsFloatingActionButtonState>().orElse {
        getPreviewGridColumn<OudsFloatingActionButtonState>().orElse {
            when (interactionState) {
                InteractionState.Hovered -> OudsFloatingActionButtonState.Hovered
                InteractionState.Pressed -> OudsFloatingActionButtonState.Pressed
                InteractionState.Focused -> OudsFloatingActionButtonState.Focused
                else -> OudsFloatingActionButtonState.Enabled
            }
        }
    }
}

@Composable
private fun contentColor(appearance: OudsFloatingActionButtonAppearance, state: OudsFloatingActionButtonState): Color {
    return with(OudsTheme.componentsTokens.button) {
        when (appearance) {
            OudsFloatingActionButtonAppearance.Strong -> when (state) {
                OudsFloatingActionButtonState.Enabled -> OudsTheme.colorScheme.content.onAction.enabled
                OudsFloatingActionButtonState.Focused -> OudsTheme.colorScheme.content.onAction.focus
                OudsFloatingActionButtonState.Hovered -> OudsTheme.colorScheme.content.onAction.hover
                OudsFloatingActionButtonState.Pressed -> OudsTheme.colorScheme.content.onAction.pressed
            }
            OudsFloatingActionButtonAppearance.Brand -> when (state) {
                OudsFloatingActionButtonState.Enabled -> colorContentBrandEnabled.value
                OudsFloatingActionButtonState.Focused -> colorContentBrandFocus.value
                OudsFloatingActionButtonState.Hovered -> colorContentBrandHover.value
                OudsFloatingActionButtonState.Pressed -> colorContentBrandPressed.value
            }
        }
    }
}

@Composable
private fun containerColor(appearance: OudsFloatingActionButtonAppearance, state: OudsFloatingActionButtonState): Color {
    return with(OudsTheme.componentsTokens.button) {
        when (appearance) {
            OudsFloatingActionButtonAppearance.Strong -> when (state) {
                OudsFloatingActionButtonState.Enabled -> OudsTheme.colorScheme.action.enabled
                OudsFloatingActionButtonState.Focused -> OudsTheme.colorScheme.action.focus
                OudsFloatingActionButtonState.Hovered -> OudsTheme.colorScheme.action.hover
                OudsFloatingActionButtonState.Pressed -> OudsTheme.colorScheme.action.pressed
            }
            OudsFloatingActionButtonAppearance.Brand -> when (state) {
                OudsFloatingActionButtonState.Enabled -> colorBgBrandEnabled.value
                OudsFloatingActionButtonState.Focused -> colorBgBrandFocus.value
                OudsFloatingActionButtonState.Hovered -> colorBgBrandHover.value
                OudsFloatingActionButtonState.Pressed -> colorBgBrandPressed.value
            }
        }
    }
}

/**
 * Default values for [OudsFloatingActionButton].
 */
object OudsFloatingActionButtonDefaults {

    /**
     * Default appearance of an [OudsFloatingActionButton].
     */
    val Appearance = OudsFloatingActionButtonAppearance.Brand
}

/**
 * An icon in an [OudsFloatingActionButton].
 */
class OudsFloatingActionButtonIcon private constructor(
    graphicsObject: Any,
    val contentDescription: String
) : OudsComponentIcon<OudsFloatingActionButtonIcon.ExtraParameters, OudsFloatingActionButtonIcon>(
    ExtraParameters::class.java,
    graphicsObject,
    contentDescription
) {

    @ConsistentCopyVisibility
    data class ExtraParameters internal constructor(
        internal val tint: Color
    ) : OudsComponentContent.ExtraParameters()

    /**
     * Creates an instance of [OudsFloatingActionButtonIcon].
     *
     * @param painter Painter of the icon.
     * @param contentDescription The content description associated with this [OudsFloatingActionButtonIcon]. This value is ignored if the floating action button also contains label.
     */
    constructor(painter: Painter, contentDescription: String) : this(painter as Any, contentDescription)

    /**
     * Creates an instance of [OudsFloatingActionButtonIcon].
     *
     * @param imageVector Image vector of the icon.
     * @param contentDescription The content description associated with this [OudsFloatingActionButtonIcon]. This value is ignored if the floating action button also contains label.
     */
    constructor(imageVector: ImageVector, contentDescription: String) : this(imageVector as Any, contentDescription)

    /**
     * Creates an instance of [OudsFloatingActionButtonIcon].
     *
     * @param bitmap Image bitmap of the icon.
     * @param contentDescription The content description associated with this [OudsFloatingActionButtonIcon]. This value is ignored if the floating action button also contains label.
     */
    constructor(bitmap: ImageBitmap, contentDescription: String) : this(bitmap as Any, contentDescription)

    override val tint: Color?
        @Composable
        get() = extraParameters.tint
}

/**
 * Represents the appearance of an [OudsFloatingActionButton].
 */
// TODO: Add FAB specific documentation for appearance
enum class OudsFloatingActionButtonAppearance {

    Strong,

    Brand
}

internal enum class OudsFloatingActionButtonState {
    Enabled, Hovered, Pressed, Focused
}

@PreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsFloatingActionButton(@PreviewParameter(OudsFloatingActionButtonPreviewParameterProvider::class) appearance: OudsFloatingActionButtonAppearance) {
    PreviewOudsFloatingActionButton(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), appearance = appearance)
}

@Composable
internal fun PreviewOudsFloatingActionButton(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    appearance: OudsFloatingActionButtonAppearance
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    PreviewEnumEntries<OudsFloatingActionButtonState> {
        OudsFloatingActionButton(
            icon = OudsFloatingActionButtonIcon(Icons.Filled.FavoriteBorder, ""),
            onClick = {},
            appearance = appearance
        )
    }
}

@PreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsSmallFloatingActionButton(@PreviewParameter(OudsFloatingActionButtonPreviewParameterProvider::class) appearance: OudsFloatingActionButtonAppearance) {
    PreviewOudsSmallFloatingActionButton(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), appearance = appearance)
}

@Composable
internal fun PreviewOudsSmallFloatingActionButton(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    appearance: OudsFloatingActionButtonAppearance
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    PreviewEnumEntries<OudsFloatingActionButtonState> {
        OudsSmallFloatingActionButton(
            icon = OudsFloatingActionButtonIcon(Icons.Filled.FavoriteBorder, ""),
            onClick = {},
            appearance = appearance
        )
    }
}

@Preview(name = "Light", widthDp = OudsPreviewableComponent.FloatingActionButton.Large.PreviewWidthDp)
@Preview(name = "Dark", uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL, widthDp = OudsPreviewableComponent.FloatingActionButton.Large.PreviewWidthDp)
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsLargeFloatingActionButton(@PreviewParameter(OudsFloatingActionButtonPreviewParameterProvider::class) appearance: OudsFloatingActionButtonAppearance) {
    PreviewOudsLargeFloatingActionButton(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), appearance = appearance)
}

@Composable
internal fun PreviewOudsLargeFloatingActionButton(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    appearance: OudsFloatingActionButtonAppearance
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    PreviewEnumEntries<OudsFloatingActionButtonState> {
        OudsLargeFloatingActionButton(
            icon = OudsFloatingActionButtonIcon(Icons.Filled.FavoriteBorder, ""),
            onClick = {},
            appearance = appearance
        )
    }
}

@Preview(name = "Light", widthDp = OudsPreviewableComponent.FloatingActionButton.Extended.PreviewWidthDp)
@Preview(name = "Dark", uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL, widthDp = OudsPreviewableComponent.FloatingActionButton.Extended.PreviewWidthDp)
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsExtendedFloatingActionButton(@PreviewParameter(OudsFloatingActionButtonPreviewParameterProvider::class) appearance: OudsFloatingActionButtonAppearance) {
    PreviewOudsExtendedFloatingActionButton(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), appearance = appearance)
}

@Composable
internal fun PreviewOudsExtendedFloatingActionButton(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    appearance: OudsFloatingActionButtonAppearance
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    val rows = listOf(
        OudsExtendedFloatingActionButtonPreviewGridRow.LabelOnly,
        OudsExtendedFloatingActionButtonPreviewGridRow.LabelAndIcon(isExpanded = true),
        OudsExtendedFloatingActionButtonPreviewGridRow.LabelAndIcon(isExpanded = false)
    )
    PreviewGrid(
        columns = enumEntries<OudsFloatingActionButtonState>(),
        rows = rows,
        columnTitle = { it.name },
        rowTitle = { row ->
            when (row) {
                OudsExtendedFloatingActionButtonPreviewGridRow.LabelOnly -> "Label only"
                is OudsExtendedFloatingActionButtonPreviewGridRow.LabelAndIcon -> if (row.isExpanded) "Label and icon expanded" else "Label and icon collapsed"
            }
        }
    ) { _, row ->
        val icon = OudsFloatingActionButtonIcon(Icons.Filled.FavoriteBorder, "")
        when (row) {
            OudsExtendedFloatingActionButtonPreviewGridRow.LabelOnly -> {
                OudsExtendedFloatingActionButton(
                    label = "Label",
                    onClick = {},
                    appearance = appearance
                )
            }
            is OudsExtendedFloatingActionButtonPreviewGridRow.LabelAndIcon -> {
                OudsExtendedFloatingActionButton(
                    label = "Label",
                    icon = icon,
                    onClick = {},
                    expanded = row.isExpanded,
                    appearance = appearance
                )
            }
        }
    }
}

internal class OudsFloatingActionButtonPreviewParameterProvider : EnumPreviewParameterProvider(OudsFloatingActionButtonAppearance::class.java)

private sealed class OudsExtendedFloatingActionButtonPreviewGridRow {

    object LabelOnly : OudsExtendedFloatingActionButtonPreviewGridRow()
    class LabelAndIcon(val isExpanded: Boolean) : OudsExtendedFloatingActionButtonPreviewGridRow()
}

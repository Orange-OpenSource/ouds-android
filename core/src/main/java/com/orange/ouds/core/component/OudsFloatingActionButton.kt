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
import androidx.compose.foundation.Indication
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ouds.core.component.common.outerBorder
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

/**
 * The FAB represents the most important action on a screen. It puts key actions within reach.
 *
 * FAB typically contains an icon, for a FAB with text and an icon, see
 * [OudsExtendedFloatingActionButton].
 *
 * @param icon Icon for this FAB.
 * @param onClick Called when this FAB is clicked.
 * @param modifier The [Modifier] to be applied to this FAB.
 * @param shape Defines the shape of this FAB's container and shadow.
 * @param appearance Appearance of the FAB among [OudsFloatingActionButtonAppearance] values.
 * @param interactionSource An optional hoisted [MutableInteractionSource] for observing and
 *   emitting [Interaction]s for this FAB. You can use this to change the FAB's appearance or
 *   preview the FAB in different states. Note that if `null` is provided, interactions will still
 *   happen internally.
 *
 * @sample com.orange.ouds.core.component.samples.OudsFloatingActionButtonSample
 */
@Composable
fun OudsFloatingActionButton(
    icon: OudsFloatingActionButtonIcon,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = OudsFloatingActionButtonDefaults.shape,
    appearance: OudsFloatingActionButtonAppearance = OudsFloatingActionButtonDefaults.Appearance,
    interactionSource: MutableInteractionSource? = null
) {
    @Suppress("NAME_SHADOWING") val interactionSource = interactionSource ?: remember { MutableInteractionSource() }

    OudsFloatingActionButton(
        appearance = appearance,
        interactionSource = interactionSource
    ) { state, indication, containerColor, contentColor, elevation ->
        FloatingActionButton(
            onClick = onClick,
            modifier = modifier
                .outerBorder(state = state, shape = shape)
                .indication(interactionSource = interactionSource, indication = indication),
            containerColor = containerColor,
            shape = shape,
            contentColor = contentColor,
            elevation = elevation,
            interactionSource = interactionSource
        ) {
            Icon(icon = icon)
        }
    }
}

/**
 * The FAB represents the most important action on a screen. It puts key actions within reach.
 *
 * FAB typically contains an icon, for a FAB with text and an icon, see
 * [OudsExtendedFloatingActionButton].
 *
 * @param icon Icon for this FAB.
 * @param onClick Called when this FAB is clicked.
 * @param modifier The [Modifier] to be applied to this FAB.
 * @param shape Defines the shape of this FAB's container and shadow.
 * @param appearance Appearance of the FAB among [OudsFloatingActionButtonAppearance] values.
 * @param interactionSource An optional hoisted [MutableInteractionSource] for observing and
 *   emitting [Interaction]s for this FAB. You can use this to change the FAB's appearance or
 *   preview the FAB in different states. Note that if `null` is provided, interactions will still
 *   happen internally.
 *
 * @sample com.orange.ouds.core.component.samples.OudsSmallFloatingActionButtonSample
 */
@Composable
fun OudsSmallFloatingActionButton(
    icon: OudsFloatingActionButtonIcon,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = OudsFloatingActionButtonDefaults.shape,
    appearance: OudsFloatingActionButtonAppearance = OudsFloatingActionButtonDefaults.Appearance,
    interactionSource: MutableInteractionSource? = null
) {
    @Suppress("NAME_SHADOWING") val interactionSource = interactionSource ?: remember { MutableInteractionSource() }

    OudsFloatingActionButton(
        appearance = appearance,
        interactionSource = interactionSource
    ) { state, indication, containerColor, contentColor, elevation ->
        SmallFloatingActionButton(
            onClick = onClick,
            modifier = modifier
                .outerBorder(state = state, shape = shape)
                .indication(interactionSource = interactionSource, indication = indication),
            shape = shape,
            containerColor = containerColor,
            contentColor = contentColor,
            elevation = elevation,
            interactionSource = interactionSource
        ) {
            Icon(icon = icon)
        }
    }
}

/**
 * The FAB represents the most important action on a screen. It puts key actions within reach.
 *
 * FAB typically contains an icon, for a FAB with text and an icon, see
 * [OudsExtendedFloatingActionButton].
 *
 * @param icon Icon for this FAB.
 * @param onClick Called when this FAB is clicked.
 * @param modifier The [Modifier] to be applied to this FAB.
 * @param shape Defines the shape of this FAB's container and shadow.
 * @param appearance Appearance of the FAB among [OudsFloatingActionButtonAppearance] values.
 * @param interactionSource An optional hoisted [MutableInteractionSource] for observing and
 *   emitting [Interaction]s for this FAB. You can use this to change the FAB's appearance or
 *   preview the FAB in different states. Note that if `null` is provided, interactions will still
 *   happen internally.
 *
 * @sample com.orange.ouds.core.component.samples.OudsLargeFloatingActionButtonSample
 */
@Composable
fun OudsLargeFloatingActionButton(
    icon: OudsFloatingActionButtonIcon,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = OudsFloatingActionButtonDefaults.shape,
    appearance: OudsFloatingActionButtonAppearance = OudsFloatingActionButtonDefaults.Appearance,
    interactionSource: MutableInteractionSource? = null
) {
    @Suppress("NAME_SHADOWING") val interactionSource = interactionSource ?: remember { MutableInteractionSource() }

    OudsFloatingActionButton(
        appearance = appearance,
        interactionSource = interactionSource
    ) { state, indication, containerColor, contentColor, elevation ->
        LargeFloatingActionButton(
            onClick = onClick,
            modifier = modifier
                .outerBorder(state = state, shape = shape)
                .indication(interactionSource = interactionSource, indication = indication),
            shape = shape,
            containerColor = containerColor,
            contentColor = contentColor,
            elevation = elevation,
            interactionSource = interactionSource
        ) {
            Icon(icon = icon, large = true)
        }
    }
}

/**
 * Extended FABs help people take primary actions. They're wider than FABs to accommodate a text
 * label and larger target area.
 *
 * The other extended floating action button overload supports a text label and icon.
 *
 * @param label Label displayed inside this FAB.
 * @param onClick Called when this FAB is clicked.
 * @param modifier The [Modifier] to be applied to this FAB.
 * @param shape Defines the shape of this FAB's container and shadow.
 * @param appearance Appearance of the FAB among [OudsFloatingActionButtonAppearance] values.
 * @param interactionSource An optional hoisted [MutableInteractionSource] for observing and
 *   emitting [Interaction]s for this FAB. You can use this to change the FAB's appearance or
 *   preview the FAB in different states. Note that if `null` is provided, interactions will still
 *   happen internally.
 *
 * @sample com.orange.ouds.core.component.samples.OudsExtendedFloatingActionButtonSampleWithLabelOnly
 */
@Composable
fun OudsExtendedFloatingActionButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = OudsFloatingActionButtonDefaults.shape,
    appearance: OudsFloatingActionButtonAppearance = OudsFloatingActionButtonDefaults.Appearance,
    interactionSource: MutableInteractionSource? = null
) {
    @Suppress("NAME_SHADOWING") val interactionSource = interactionSource ?: remember { MutableInteractionSource() }

    OudsFloatingActionButton(
        appearance = appearance,
        interactionSource = interactionSource
    ) { state, indication, containerColor, contentColor, elevation ->
        ExtendedFloatingActionButton(
            onClick = onClick,
            modifier = modifier
                .outerBorder(state = state, shape = shape)
                .indication(interactionSource = interactionSource, indication = indication),
            shape = shape,
            containerColor = containerColor,
            contentColor = contentColor,
            elevation = elevation,
            interactionSource = interactionSource
        ) {
            Text(label = label)
        }
    }
}

/**
 * Extended FABs help people take primary actions. They're wider than FABs to accommodate a text
 * label and larger target area.
 *
 * The other extended floating action button overload is for FABs without an icon.
 *
 * Default content description for accessibility is extended from the extended fabs icon. For custom
 * behavior, you can provide your own via [Modifier.semantics].
 *
 * @param label Label displayed inside this FAB.
 * @param icon Icon for this FAB.
 * @param onClick Called when this FAB is clicked.
 * @param modifier The [Modifier] to be applied to this FAB.
 * @param shape Defines the shape of this FAB's container and shadow.
 * @param expanded Controls the expansion state of this FAB. In an expanded state, the FAB will show
 *   both the [icon] and [label]. In a collapsed state, the FAB will show only the [icon].
 * @param interactionSource An optional hoisted [MutableInteractionSource] for observing and
 *   emitting [Interaction]s for this FAB. You can use this to change the FAB's appearance or
 *   preview the FAB in different states. Note that if `null` is provided, interactions will still
 *   happen internally.
 *
 * @sample com.orange.ouds.core.component.samples.OudsExtendedFloatingActionButtonSampleWithLabelAndIcon
 */
@Composable
fun OudsExtendedFloatingActionButton(
    label: String,
    icon: OudsFloatingActionButtonIcon,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    expanded: Boolean = true,
    shape: Shape = OudsFloatingActionButtonDefaults.shape,
    appearance: OudsFloatingActionButtonAppearance = OudsFloatingActionButtonDefaults.Appearance,
    interactionSource: MutableInteractionSource? = null
) {
    @Suppress("NAME_SHADOWING") val interactionSource = interactionSource ?: remember { MutableInteractionSource() }

    OudsFloatingActionButton(
        appearance = appearance,
        interactionSource = interactionSource
    ) { state, indication, containerColor, contentColor, elevation ->
        ExtendedFloatingActionButton(
            text = { Text(label = label) },
            icon = { Icon(icon = icon, label = label) },
            onClick = onClick,
            modifier = modifier
                .outerBorder(state = state, shape = shape)
                .indication(interactionSource = interactionSource, indication = indication),
            shape = shape,
            expanded = expanded,
            containerColor = containerColor,
            contentColor = contentColor,
            elevation = elevation,
            interactionSource = interactionSource
        )
    }
}

@Composable
private fun OudsFloatingActionButton(
    appearance: OudsFloatingActionButtonAppearance,
    interactionSource: MutableInteractionSource,
    content: @Composable (
        state: OudsFloatingActionButtonState,
        indication: Indication,
        containerColor: Color,
        contentColor: Color,
        elevation: FloatingActionButtonElevation,
    ) -> Unit
) {
    val interactionState by interactionSource.collectInteractionStateAsState()
    val state = getFloatingActionButtonState(interactionState = interactionState)
    val containerColor = rememberInteractionColor(interactionState = interactionState) { floatingActionButtonInteractionState ->
        val floatingActionButtonState = getFloatingActionButtonState(interactionState = floatingActionButtonInteractionState)
        containerColor(appearance = appearance, state = floatingActionButtonState)
    }
    val contentColor = rememberInteractionColor(interactionState = interactionState) { floatingActionButtonInteractionState ->
        val floatingActionButtonState = getFloatingActionButtonState(interactionState = floatingActionButtonInteractionState)
        contentColor(appearance = appearance, state = floatingActionButtonState)
    }
    val indication = InteractionValuesIndication(containerColor, contentColor)
    val elevation = OudsTheme.elevations.sticky
    val floatingActionButtonElevation = FloatingActionButtonDefaults.elevation(
        defaultElevation = elevation,
        pressedElevation = elevation,
        focusedElevation = elevation,
        hoveredElevation = elevation
    )

    CompositionLocalProvider(LocalRippleConfiguration provides null) {
        content(state, indication, containerColor.value, contentColor.value, floatingActionButtonElevation)
    }
}

@Composable
private fun Icon(icon: OudsFloatingActionButtonIcon, label: String? = null, large: Boolean = false) {
    val iconSize = if (large) OudsTheme.sizes.icon.withLabel.large.sizeLarge else OudsTheme.componentsTokens.button.sizeIconOnly.value
    val iconScale = LocalConfiguration.current.fontScale
    icon.Content(
        modifier = Modifier
            .size(iconSize * iconScale)
            .semantics {
                contentDescription = icon.contentDescription.takeIf { it.isNotBlank() }.orElse { label }.orEmpty()
            }
    )
}

@Composable
private fun Text(label: String) {
    Text(text = label, style = OudsTheme.typography.label.strong.large)
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
    return when (appearance) {
        OudsFloatingActionButtonAppearance.Strong -> with(OudsTheme.colorScheme.content.onAction) {
            when (state) {
                OudsFloatingActionButtonState.Enabled -> enabled
                OudsFloatingActionButtonState.Focused -> focus
                OudsFloatingActionButtonState.Hovered -> hover
                OudsFloatingActionButtonState.Pressed -> pressed
            }
        }
        OudsFloatingActionButtonAppearance.Brand -> with(OudsTheme.componentsTokens.button) {
            when (state) {
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
    return when (appearance) {
        OudsFloatingActionButtonAppearance.Strong -> with(OudsTheme.colorScheme.action) {
            when (state) {
                OudsFloatingActionButtonState.Enabled -> enabled
                OudsFloatingActionButtonState.Focused -> focus
                OudsFloatingActionButtonState.Hovered -> hover
                OudsFloatingActionButtonState.Pressed -> pressed
            }
        }
        OudsFloatingActionButtonAppearance.Brand -> with(OudsTheme.componentsTokens.button) {
            when (state) {
                OudsFloatingActionButtonState.Enabled -> colorBgBrandEnabled.value
                OudsFloatingActionButtonState.Focused -> colorBgBrandFocus.value
                OudsFloatingActionButtonState.Hovered -> colorBgBrandHover.value
                OudsFloatingActionButtonState.Pressed -> colorBgBrandPressed.value
            }
        }
    }
        // Composite color over an opaque background color otherwise a shadow is visible inside the FAB with non opaque container colors
        .compositeOver(OudsTheme.colorScheme.background.primary)
}

/**
 * Default values for [OudsFloatingActionButton].
 */
object OudsFloatingActionButtonDefaults {

    /**
     * Default appearance of an [OudsFloatingActionButton].
     */
    val Appearance = OudsFloatingActionButtonAppearance.Brand

    /**
     * Default shape of an [OudsFloatingActionButton].
     */
    val shape: Shape
        @Composable
        get() = RoundedCornerShape(OudsTheme.borders.radius.pill)
}

/**
 * An icon in an [OudsFloatingActionButton].
 */
class OudsFloatingActionButtonIcon private constructor(
    graphicsObject: Any,
    val contentDescription: String
) : OudsComponentIcon<Nothing, OudsFloatingActionButtonIcon>(
    Nothing::class.java,
    graphicsObject,
    contentDescription
) {

    /**
     * Creates an instance of [OudsFloatingActionButtonIcon].
     *
     * @param painter Painter of the icon.
     * @param contentDescription The content description associated with this [OudsFloatingActionButtonIcon].
     */
    constructor(painter: Painter, contentDescription: String) : this(painter as Any, contentDescription)

    /**
     * Creates an instance of [OudsFloatingActionButtonIcon].
     *
     * @param imageVector Image vector of the icon.
     * @param contentDescription The content description associated with this [OudsFloatingActionButtonIcon].
     */
    constructor(imageVector: ImageVector, contentDescription: String) : this(imageVector as Any, contentDescription)

    /**
     * Creates an instance of [OudsFloatingActionButtonIcon].
     *
     * @param bitmap Image bitmap of the icon.
     * @param contentDescription The content description associated with this [OudsFloatingActionButtonIcon].
     */
    constructor(bitmap: ImageBitmap, contentDescription: String) : this(bitmap as Any, contentDescription)
}

/**
 * Represents the appearance of an [OudsFloatingActionButton].
 */
enum class OudsFloatingActionButtonAppearance {

    /**
     * Strong appearance of an [OudsFloatingActionButton].
     */
    Strong,

    /**
     * A brand primary color alternative to the Strong floating action button.
     */
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
    val icon = OudsFloatingActionButtonIcon(Icons.Filled.FavoriteBorder, "")
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

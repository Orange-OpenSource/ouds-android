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
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.component.common.OudsComponentState
import com.orange.ouds.core.component.common.bottomBorder
import com.orange.ouds.core.component.common.outerBorder
import com.orange.ouds.core.extensions.InteractionState
import com.orange.ouds.core.extensions.collectInteractionStateAsState
import com.orange.ouds.core.extensions.visible
import com.orange.ouds.core.theme.LocalParentEnabled
import com.orange.ouds.core.theme.LocalSkeleton
import com.orange.ouds.core.theme.LocalThemeSettings
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.LocalPreviewGridColumnEnumEntry
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.OudsPreviewDevice
import com.orange.ouds.core.utilities.OudsPreviewLightDark
import com.orange.ouds.core.utilities.PreviewFlowRow
import com.orange.ouds.core.utilities.PreviewGrid
import com.orange.ouds.core.utilities.getPreviewEnumEntry
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.core.utilities.mapSettings
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.theme.OudsThemeContract

/**
 * A Content Container Card groups related information within a single surface. The card itself is not interactive, but it can contain independently
 * interactive elements, such as buttons and links. Its content is customizable through a slot, allowing designers to adapt the internal layout without
 * changing the behavior of the container.
 *
 * > Design name: Content Container Card
 *
 * > Design version: 1.0.0
 *
 * @param modifier The [Modifier] to be applied to this card.
 * @param style Style of the card among [OudsCardStyle] values.
 * @param enabled Controls the enabled state of the card. When `false`, the OUDS components inside will be in disabled state.
 * @param skeleton A placeholder state of the component used when the content is not yet available (while loading). The component shows the correct visual
 *    structure but with placeholder elements (grey or shimmering blocks) instead of actual content.
 *    Can be set directly or inherited from [OudsSkeletonProvider]. See [OudsSkeleton] class documentation for all approaches.
 * @param content The content of the [OudsCard].
 */
@Composable
fun OudsCard(
    modifier: Modifier = Modifier,
    style: OudsCardStyle = OudsCardDefaults.Style,
    enabled: Boolean = true,
    skeleton: OudsSkeleton? = LocalSkeleton.current,
    content: @Composable OudsCardScope.() -> Unit = { },
) {
    OudsCard(
        nullableOnClick = null,
        modifier = modifier,
        style = style,
        enabled = enabled,
        skeleton = skeleton,
        interactionSource = null,
        content = content
    )
}

/**
 * TODO definition
 *
 * > Design name: Action Container Card
 *
 * > Design version: 1.0.0
 *
 * @param onClick The action to perform when this card is clicked.
 * @param modifier The [Modifier] to be applied to this card.
 * @param style Style of the card among [OudsCardStyle] values.
 * @param enabled Controls the enabled state of the card. When `false`, the OUDS components inside will be in disabled state.
 * @param loader An optional loading progress indicator displayed in place of the card's content to indicate an ongoing operation.
 * @param skeleton A placeholder state of the component used when the content is not yet available (while loading). The component shows the correct visual
 *    structure but with placeholder elements (grey or shimmering blocks) instead of actual content.
 *    Can be set directly or inherited from [OudsSkeletonProvider]. See [OudsSkeleton] class documentation for all approaches.
 * @param interactionSource An optional hoisted [MutableInteractionSource] for observing and emitting [Interaction]s for this card. Note that if `null`
 *    is provided, interactions will still happen internally.
 * @param content The content of the [OudsCard].
 */
@Composable
fun OudsCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: OudsCardStyle = OudsCardDefaults.Style,
    enabled: Boolean = true,
    loader: OudsCardLoader? = null,
    skeleton: OudsSkeleton? = LocalSkeleton.current,
    interactionSource: MutableInteractionSource? = null,
    content: @Composable OudsCardScope.() -> Unit = { },
) {
    OudsCard(
        nullableOnClick = onClick,
        modifier = modifier,
        style = style,
        enabled = enabled,
        loader = loader,
        skeleton = skeleton,
        interactionSource = interactionSource,
        content = content
    )
}

@Composable
@JvmName("OudsCardNullableOnClick")
internal fun OudsCard(
    nullableOnClick: (() -> Unit)?,
    modifier: Modifier,
    style: OudsCardStyle,
    enabled: Boolean,
    loader: OudsCardLoader? = null,
    skeleton: OudsSkeleton?,
    interactionSource: MutableInteractionSource?,
    content: @Composable (OudsCardScope.() -> Unit)
) {
    @Suppress("NAME_SHADOWING") val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    val interactionState by interactionSource.collectInteractionStateAsState()
    val state = getCardState(enabled = enabled, loader = loader, skeleton = skeleton, interactionState = interactionState)

    val borderRadius = if (LocalThemeSettings.current.roundedCornerCards == true) 12.dp else 0.dp // TODO use tokens when available
    val shape = RoundedCornerShape(borderRadius)

    val backgroundColor = when (style) {
        is OudsCardStyle.Filled -> style.backgroundColor
        is OudsCardStyle.Elevated -> style.backgroundColor
        OudsCardStyle.Outlined -> Color.Transparent
    }
    val borderColor = rememberNullableInteractionColor(interactionState = interactionState) { cardInteractionState ->
        val cardState = getCardState(enabled = enabled, loader = loader, skeleton = skeleton, interactionState = cardInteractionState)
        borderColor(style = style, state = cardState)
    }
    val stateOverlayColor = rememberNullableInteractionColor(interactionState = interactionState) { cardInteractionState ->
        val cardState = getCardState(enabled = enabled, loader = loader, skeleton = skeleton, interactionState = cardInteractionState)
        stateOverlayColor(style = style, state = cardState)
    }

    val clickableModifier = if (nullableOnClick != null) {
        Modifier.clickable(
            enabled = state.areInteractionsEnabled,
            interactionSource = interactionSource,
            indication = interactionValuesIndication(borderColor),
            onClick = nullableOnClick
        )
    } else {
        Modifier
    }

    val scope = remember { OudsCardScope() }

    SkeletonLayout(
        modifier = modifier,
        componentState = state,
        state = skeleton?.state,
        securityMargin = false,
        shape = shape
    ) { contentModifier ->
        Card(
            modifier = contentModifier
                .then(clickableModifier)
                .cardDivider(style = style, borderRadius = borderRadius)
                .outerBorder(state = state, shape = shape),
            shape = shape,
            colors = CardColors(
                containerColor = backgroundColor,
                contentColor = Color.Unspecified,
                disabledContainerColor = backgroundColor,
                disabledContentColor = Color.Unspecified
            ),
            elevation = cardElevation(style = style),
            border = borderColor.value?.let { borderColor ->
                BorderStroke(width = 1.dp, color = borderColor)
            }
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopStart
            ) {
                Box(
                    modifier = Modifier.visible(state != OudsCardState.Loading)
                ) {
                    CompositionLocalProvider(LocalParentEnabled provides enabled) {
                        with(scope) {
                            this.state = state
                            content()
                        }
                    }
                }
                if (stateOverlayColor.value != null || state == OudsCardState.Loading) {
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .background(color = stateOverlayColor.value.orElse { Color.Transparent })
                    ) {
                        if (state == OudsCardState.Loading) {
                            OudsCircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center),
                                status = OudsProgressIndicatorStatus.Accent,
                                nullableProgress = loader?.progress?.let { { it } },
                                track = false,
                                indicatorSize = 40.dp
                            )
                        }
                    }
                }
            }
        }
    }
}

object OudsCardDefaults {

    val Style = OudsCardStyle.Filled()

    val BackgroundColor = Color.Transparent
}

sealed class OudsCardStyle {
    /**
     * Filled cards use a background color to separate their content from the surrounding interface.
     *
     * @param divider Controls the display of a divider at the bottom of the card. Defaults to `false`.
     * @param backgroundColor The background color of the card. Defaults to [OudsCardDefaults.BackgroundColor].
     */
    class Filled(val backgroundColor: Color = OudsCardDefaults.BackgroundColor, val divider: Boolean = false) : OudsCardStyle()

    /**
     * Outlined cards define their boundaries with a border.
     */
    object Outlined : OudsCardStyle()

    /**
     * Elevated cards create separation through a shadow, giving the container a raised appearance.
     *
     * @param backgroundColor The background color of the card. Defaults to [OudsCardDefaults.BackgroundColor].
     */
    class Elevated(val backgroundColor: Color = OudsCardDefaults.BackgroundColor) : OudsCardStyle()
}

/**
 * State of an [OudsCard].
 */
enum class OudsCardState : OudsComponentState {
    Enabled, Hovered, Focused, Pressed, Loading, Disabled, Skeleton
}

/**
 * Scope for the content of an [OudsCard].
 *
 * @property state The current state of the card.
 */
class OudsCardScope {
    var state: OudsCardState by mutableStateOf(OudsCardState.Enabled)
        internal set
}

/**
 * A circular loading indicator displayed in an [OudsCard].
 *
 * @param progress The loading progress, where 0.0 represents no progress and 1.0 represents full progress.
 *   Values outside of this range are coerced into the range.
 *   Set this value to `null` to display a circular indeterminate progress indicator.
 */
data class OudsCardLoader(val progress: Float?)

@Composable
private fun cardElevation(style: OudsCardStyle): CardElevation {
    with(OudsTheme.elevations) {
        val elevation = when (style) {
            is OudsCardStyle.Filled, OudsCardStyle.Outlined -> none.value.dp
            is OudsCardStyle.Elevated -> raised.value.dp
        }
        return CardDefaults.cardElevation(
            defaultElevation = elevation,
            pressedElevation = elevation,
            focusedElevation = elevation,
            hoveredElevation = elevation,
            draggedElevation = elevation,
            disabledElevation = elevation
        )
    }
}

@Composable
private fun borderColor(style: OudsCardStyle, state: OudsCardState) = with(OudsTheme.colorScheme) {
    when (style) {
        OudsCardStyle.Outlined -> {
            when (state) {
                OudsCardState.Enabled -> border.default
                OudsCardState.Hovered -> action.hover
                OudsCardState.Focused -> action.focus
                OudsCardState.Pressed -> action.pressed
                OudsCardState.Loading -> action.loading
                OudsCardState.Disabled -> action.disabled
                OudsCardState.Skeleton -> null
            }
        }
        is OudsCardStyle.Filled, is OudsCardStyle.Elevated -> null
    }
}

@Composable
private fun Modifier.cardDivider(style: OudsCardStyle, borderRadius: Dp): Modifier {
    return if (style is OudsCardStyle.Filled && style.divider) {
        bottomBorder(width = 1.dp, color = OudsTheme.colorScheme.border.muted, cornerRadius = borderRadius)
    } else {
        this
    }
}

@Composable
private fun stateOverlayColor(style: OudsCardStyle, state: OudsCardState) = with(OudsTheme.colorScheme.action.support) {
    when (style) {
        is OudsCardStyle.Filled, is OudsCardStyle.Elevated -> {
            when (state) {
                OudsCardState.Hovered -> hover
                OudsCardState.Focused -> focus
                OudsCardState.Pressed -> pressed
                OudsCardState.Loading -> loading
                OudsCardState.Disabled -> disabled
                OudsCardState.Enabled, OudsCardState.Skeleton -> null
            }
        }
        OudsCardStyle.Outlined -> null
    }
}

@Composable
private fun getCardState(enabled: Boolean, loader: OudsCardLoader?, skeleton: OudsSkeleton?, interactionState: InteractionState): OudsCardState {
    return getPreviewEnumEntry<OudsCardState>().orElse {
        when {
            skeleton != null -> OudsCardState.Skeleton
            !enabled -> OudsCardState.Disabled
            loader != null -> OudsCardState.Loading
            interactionState == InteractionState.Hovered -> OudsCardState.Hovered
            interactionState == InteractionState.Pressed -> OudsCardState.Pressed
            interactionState == InteractionState.Focused -> OudsCardState.Focused
            else -> OudsCardState.Enabled
        }
    }
}

@Preview(name = "Light", heightDp = 1400, widthDp = 3000, device = OudsPreviewDevice) // TODO hard-coded value
@Preview(
    name = "Dark",
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL,
    heightDp = 1400,
    widthDp = 3000, // TODO hard-coded value
    device = OudsPreviewDevice
)
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsActionCard(@PreviewParameter(OudsCardPreviewParameterProvider::class) parameter: OudsCardPreviewParameter) {
    PreviewOudsActionCard(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsActionCard(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsCardPreviewParameter
) = with(parameter) {
    OudsPreview(theme = theme.mapSettings { it.copy(roundedCornerCards = roundedCorners) }, darkThemeEnabled = darkThemeEnabled) {
        PreviewGrid(
            columns = OudsCardState.entries.map { it.name },
            rows = previewOudsCardRowNames,
        ) { column, row ->
            val state = enumValueOf<OudsCardState>(column)
            val backgroundColor = OudsTheme.colorScheme.surface.status.accent.muted
            val style = when (row) {
                OudsCardStyle.Filled::class.simpleName -> OudsCardStyle.Filled(backgroundColor = backgroundColor, divider = true)
                OudsCardStyle.Outlined::class.simpleName -> OudsCardStyle.Outlined
                OudsCardStyle.Elevated::class.simpleName -> OudsCardStyle.Elevated(backgroundColor = backgroundColor)
                else -> error("Unknown row $row.")
            }
            CompositionLocalProvider(LocalPreviewGridColumnEnumEntry provides state) {
                OudsCard(
                    modifier = Modifier.width(360.dp),
                    onClick = {},
                    style = style,
                ) {
                    PreviewCardContent()
                }
            }
        }
    }
}

@OudsPreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsContentCard(@PreviewParameter(OudsCardPreviewParameterProvider::class) parameter: OudsCardPreviewParameter) {
    PreviewOudsContentCard(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsContentCard(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsCardPreviewParameter
) = with(parameter) {
    OudsPreview(theme = theme.mapSettings { it.copy(roundedCornerCards = roundedCorners) }, darkThemeEnabled = darkThemeEnabled) {
        val backgroundColor = OudsTheme.colorScheme.surface.status.accent.muted
        PreviewFlowRow(
            items = previewOudsCardRowNames,
            maxItemsInEachRow = 1
        ) { item ->
            val style = when (item) {
                OudsCardStyle.Filled::class.simpleName -> OudsCardStyle.Filled(backgroundColor = backgroundColor, divider = true)
                OudsCardStyle.Outlined::class.simpleName -> OudsCardStyle.Outlined
                OudsCardStyle.Elevated::class.simpleName -> OudsCardStyle.Elevated(backgroundColor = backgroundColor)
                else -> error("Unknown item $item.")
            }
            OudsCard(
                modifier = Modifier.width(360.dp),
                style = style,
            ) {
                PreviewCardContent()
            }
        }
    }
}

@Composable
private fun PreviewCardContent() {
    Column(modifier = Modifier.padding(all = OudsTheme.spaces.fixed.medium), verticalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.medium)) {
        OudsHeadingText(text = "Before activating your eSIM")
        OudsBodyText(text = "Make sure your device is connected to Wi-Fi and compatible with eSIM. Once activation starts, it may take a few minutes to complete.")
        Column {
            OudsListItem(label = "Check device compatibility", onClick = {}, edgeToEdge = false)
            OudsListItem(label = "Learn how eSIM activation works", onClick = {}, edgeToEdge = false)
        }
        OudsButton(label = "Activate eSIM", appearance = OudsButtonAppearance.Strong, onClick = {})
    }
}

private val previewOudsCardRowNames = listOf(
    OudsCardStyle.Filled::class,
    OudsCardStyle.Outlined::class,
    OudsCardStyle.Elevated::class,
).map { it.simpleName.orEmpty() }

internal data class OudsCardPreviewParameter(
    val roundedCorners: Boolean = false
)

internal class OudsCardPreviewParameterProvider : BasicPreviewParameterProvider<OudsCardPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsCardPreviewParameter>
    get() = listOf(
        OudsCardPreviewParameter(),
        OudsCardPreviewParameter(roundedCorners = true),
    )

/**
 * TODO
 */
@Composable
fun OudsElevatedCard(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    skeleton: OudsSkeleton? = LocalSkeleton.current,
    content: @Composable OudsCardScope.() -> Unit = { },
) {
    OudsCard(
        modifier = modifier,
        style = OudsCardStyle.Elevated(),
        enabled = enabled,
        skeleton = skeleton,
        content = content
    )
}

/**
 * TODO
 */
@Composable
fun OudsElevatedCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    skeleton: OudsSkeleton? = LocalSkeleton.current,
    interactionSource: MutableInteractionSource? = null,
    content: @Composable OudsCardScope.() -> Unit = { },
) {
    OudsCard(
        onClick = onClick,
        modifier = modifier,
        style = OudsCardStyle.Elevated(),
        enabled = enabled,
        skeleton = skeleton,
        interactionSource = interactionSource,
        content = content
    )
}

/**
 * TODO
 */
@Composable
fun OudsOutlinedCard(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    skeleton: OudsSkeleton? = LocalSkeleton.current,
    content: @Composable OudsCardScope.() -> Unit = { },
) {
    OudsCard(
        modifier = modifier,
        style = OudsCardStyle.Outlined,
        enabled = enabled,
        skeleton = skeleton,
        content = content
    )
}

/**
 * TODO
 */
@Composable
fun OudsOutlinedCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    skeleton: OudsSkeleton? = LocalSkeleton.current,
    interactionSource: MutableInteractionSource? = null,
    content: @Composable OudsCardScope.() -> Unit = { },
) {
    OudsCard(
        onClick = onClick,
        modifier = modifier,
        style = OudsCardStyle.Outlined,
        enabled = enabled,
        skeleton = skeleton,
        interactionSource = interactionSource,
        content = content
    )
}
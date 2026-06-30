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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import com.orange.ouds.core.component.content.OudsComponentContent
import com.orange.ouds.core.component.content.OudsComponentIcon
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.value
import com.orange.ouds.foundation.ExperimentalOudsApi
import com.orange.ouds.foundation.RestrictedOudsApi
import kotlin.jvm.JvmOverloads

/**
 * An icon in an [OudsFilterChip] or an [OudsSuggestionChip].
 * This icon is not clickable.
 */
class OudsChipIcon private constructor(
    graphicsObject: Any,
    val contentDescription: String,
    override val tinted: Boolean
) : OudsComponentIcon<OudsChipIcon.ExtraParameters, OudsChipIcon>(ExtraParameters::class, graphicsObject, contentDescription) {

    @ConsistentCopyVisibility
    data class ExtraParameters internal constructor(
        internal val tint: Color
    ) : OudsComponentContent.ExtraParameters()

    /**
     * Creates an instance of [OudsChipIcon].
     *
     * @param painter Painter of the icon.
     * @param contentDescription The content description associated with this [OudsChipIcon]. This value is ignored if the chip also contains a label.
     * @param tinted Controls whether the icon should be tinted with the theme color. Defaults to `true`.
     *   When set to `false`, the icon is displayed with its original colors (e.g., for multi-color icons).
     *   Note that untinted icons must ensure sufficient contrast with the background for accessibility reasons.
     */
    @JvmOverloads
    constructor(painter: Painter, contentDescription: String, tinted: Boolean = true) : this(painter as Any, contentDescription, tinted)

    /**
     * Creates an instance of [OudsChipIcon].
     *
     * @param imageVector Image vector of the icon.
     * @param contentDescription The content description associated with this [OudsChipIcon]. This value is ignored if the chip also contains a label.
     * @param tinted Controls whether the icon should be tinted with the theme color. Defaults to `true`.
     *   When set to `false`, the icon is displayed with its original colors (e.g., for multi-color icons).
     *   Note that untinted icons must ensure sufficient contrast with the background for accessibility reasons.
     */
    @JvmOverloads
    constructor(imageVector: ImageVector, contentDescription: String, tinted: Boolean = true) : this(imageVector as Any, contentDescription, tinted)

    /**
     * Creates an instance of [OudsChipIcon].
     *
     * @param bitmap Image bitmap of the icon.
     * @param contentDescription The content description associated with this [OudsChipIcon]. This value is ignored if the chip also contains a label.
     * @param tinted Controls whether the icon should be tinted with the theme color. Defaults to `true`.
     *   When set to `false`, the icon is displayed with its original colors (e.g., for multi-color icons).
     *   Note that untinted icons must ensure sufficient contrast with the background for accessibility reasons.
     */
    @JvmOverloads
    constructor(bitmap: ImageBitmap, contentDescription: String, tinted: Boolean = true) : this(bitmap as Any, contentDescription, tinted)

    override val tint: Color?
        @Composable
        get() = extraParameters.tint
}

/**
 * Represents the different states of a chip.
 * 
 * This state is available in [OudsChipScope] when using "Basic" chip variants,
 * allowing developers to customize chip appearance based on interaction state.
 * 
 * Example:
 * ```
 * OudsBasicSuggestionChip(...) {
 *     when (state) {
 *         OudsChipState.Pressed -> Icon(modifier = Modifier.rotate(10f))
 *         else -> Icon()
 *     }
 * }
 * ```
 */
@RestrictedOudsApi
enum class OudsChipState {

    /** The chip is enabled and can be interacted with. */
    Enabled,

    /** The chip is being hovered over. */
    Hovered,

    /** The chip is being pressed. */
    Pressed,

    /** The chip is disabled and cannot be interacted with. */
    Disabled,

    /** The chip is focused. */
    Focused
}

internal enum class OudsChipIconPosition {
    Start, End
}

/**
 * Scope for the content of a chip.
 *
 * @property state The current state of the chip.
 * @property contentColor The content color of the chip.
 */
@ExperimentalOudsApi
@RestrictedOudsApi
class OudsChipScope {

    var state: OudsChipState by mutableStateOf(OudsChipState.Enabled)
        internal set

    var contentColor: Color by mutableStateOf(Color.Unspecified)
        internal set

    internal var icon: @Composable (Modifier) -> Unit = {}

    internal var label: @Composable (Modifier) -> Unit = {}

    internal var tick: @Composable () -> Unit = {}

    /**
     * The icon of the chip.
     * 
     * @param modifier The [Modifier] to be applied to the icon.
     */
    @Composable
    fun Icon(modifier: Modifier = Modifier) = icon(modifier)

    /**
     * The label of the chip.
     * 
     * @param modifier The [Modifier] to be applied to the label.
     */
    @Composable
    fun Label(modifier: Modifier = Modifier) = label(modifier)

    @Composable
    internal fun Tick() = tick()
}

@Composable
internal fun OudsChipScope.DefaultChipContent(iconPosition: OudsChipIconPosition) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(OudsTheme.componentsTokens.chip.spaceColumnGapIcon.value, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Tick()
        if (iconPosition == OudsChipIconPosition.Start) {
            Icon()
        }
        Label()
        if (iconPosition == OudsChipIconPosition.End) {
            Icon()
        }
    }
}


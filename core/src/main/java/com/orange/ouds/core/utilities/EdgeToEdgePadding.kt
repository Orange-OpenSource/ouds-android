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

package com.orange.ouds.core.utilities

import android.annotation.SuppressLint
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.LayoutModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.TraversableNode
import androidx.compose.ui.node.TraversableNode.Companion.TraverseDescendantsAction
import androidx.compose.ui.node.currentValueOf
import androidx.compose.ui.node.traverseDescendants
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.constrainHeight
import androidx.compose.ui.unit.constrainWidth
import androidx.compose.ui.unit.offset
import com.orange.ouds.core.theme.LocalGrids

/**
 * Applies an horizontal space of `OudsTheme.grids.margin` along the left and right edges of the content.
 *
 * This modifier is used by components that can span the entire width of the screen, such as [com.orange.ouds.core.component.OudsCheckboxItem],
 * [com.orange.ouds.core.component.OudsRadioButtonItem] or [com.orange.ouds.core.component.OudsSwitchItem].
 *
 * @param enabled Indicates whether the modifier is enabled or not.
 */
fun Modifier.edgeToEdgePadding(enabled: Boolean) = this then EdgeToEdgePaddingElement(enabled)

@SuppressLint("ModifierNodeInspectableProperties")
internal data class EdgeToEdgePaddingElement(val enabled: Boolean) : ModifierNodeElement<EdgeToEdgePaddingNode>() {

    override fun create(): EdgeToEdgePaddingNode {
        return EdgeToEdgePaddingNode(enabled)
    }

    override fun update(node: EdgeToEdgePaddingNode) {
        node.enabled = enabled
    }
}

internal class EdgeToEdgePaddingNode(var enabled: Boolean) : Modifier.Node(), LayoutModifierNode, TraversableNode, CompositionLocalConsumerModifierNode {

    override fun MeasureScope.measure(measurable: Measurable, constraints: Constraints): MeasureResult {
        var hasDescendant = false
        traverseDescendants { _ ->
            hasDescendant = true
            TraverseDescendantsAction.CancelTraversal
        }

        val density = currentValueOf(LocalDensity)
        val grids = currentValueOf(LocalGrids)
        val paddingPx = if (!hasDescendant && enabled) with(density) { grids.margin.toPx().toInt() } else 0
        val placeable = measurable.measure(constraints.offset(-2 * paddingPx, 0))
        val width = constraints.constrainWidth(placeable.width + 2 * paddingPx)
        val height = constraints.constrainHeight(placeable.height)

        return layout(width, height) {
            placeable.place(paddingPx, 0)
        }
    }

    override val traverseKey: Any
        get() = EdgeToEdgePaddingNode::class.java.simpleName
}

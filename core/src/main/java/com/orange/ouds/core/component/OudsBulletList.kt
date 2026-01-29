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

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.theme.OudsThemeContract

@DslMarker
annotation class OudsBulletListDslMarker

/**
 * Bullet list is a UI element that helps to view in related individual text items grouped together; items usually starting with a number or a bullet.
 *
 * Bullet list is also known as “Unordered list” or “Ordered list” and is not an interactive element by default, although text items can support hypertext links.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://unified-design-system.orange.com/472794e18/p/49e7bf-bullet-list)
 *
 * > Design version: 1.0.0
 *
 * @param modifier [Modifier] applied to the list.
 * @param type The visual type of the list (e.g., ordered, unordered, bare). See [OudsBulletListType].
 * @param textStyle The typography style for the list items. See [OudsBulletListTextStyle].
 * @param bold Whether the list item text should be bold. This can be overridden for sub-lists. Defaults to `true`.
 * @param builder A lambda scope using the [OudsBulletListBuilder] to define the list items.
 *
 * @sample com.orange.ouds.core.component.samples.OudsBulletListUnorderedSample
 * @sample com.orange.ouds.core.component.samples.OudsBulletListOrderedSample
 * @sample com.orange.ouds.core.component.samples.OudsBulletListBareSample
 */
@Composable
fun OudsBulletList(
    modifier: Modifier = Modifier,
    type: OudsBulletListType = OudsBulletListDefaults.Type,
    textStyle: OudsBulletListTextStyle = OudsBulletListDefaults.TextStyle,
    bold: Boolean = true,
    builder: OudsBulletListBuilder.() -> Unit
) {
    val items = remember(builder) {
        OudsBulletListBuilder().apply(builder).build()
    }

    Column(modifier = modifier) {
        items.forEachIndexed { index, item ->
            OudsBulletListItem(
                item = item,
                currentType = type,
                currentTextStyle = textStyle,
                currentHasBoldText = bold,
                index = index,
                level = OudsBulletListItemNestedLevel.Zero
            )
        }
    }
}

/**
 * A DSL builder for constructing an [OudsBulletList].
 *
 * This class provides a structured way to define list items and nested sub-lists.
 */
@OudsBulletListDslMarker
class OudsBulletListBuilder internal constructor() {
    private val items = mutableListOf<BulletListItem>()

    /**
     * Adds an item to the bullet list.
     *
     * This function can also define a nested sub-list by providing a `builder` lambda.
     *
     * @param label The text content of the list item.
     * @param subListType The specific [OudsBulletListType] for the nested sub-list, if any.
     *   If `null`, the type is inherited from the parent list.
     * @param subListTextStyle The specific [OudsBulletListTextStyle] for the nested sub-list, if any.
     *   If `null`, the text style is inherited from the parent list.
     * @param subListHasBoldText Whether the text of the nested sub-list should be bold.
     *   If `null`, the bold setting is inherited from the parent list.
     * @param builder A lambda scope for defining nested list items.
     */
    fun item(
        label: String,
        subListType: OudsBulletListType? = null,
        subListTextStyle: OudsBulletListTextStyle? = null,
        subListHasBoldText: Boolean? = null,
        builder: (OudsBulletListBuilder.() -> Unit)? = null
    ) {
        val subListItems = builder?.let { OudsBulletListBuilder().apply(it).build() }.orEmpty()
        items.add(BulletListItem(label, subListType, subListTextStyle, subListHasBoldText, subListItems))
    }

    internal fun build(): List<BulletListItem> = items
}

@Composable
private fun OudsBulletListItem(
    item: BulletListItem,
    currentType: OudsBulletListType,
    currentTextStyle: OudsBulletListTextStyle,
    currentHasBoldText: Boolean,
    index: Int,
    level: OudsBulletListItemNestedLevel,
    modifier: Modifier = Modifier
) {
    with(OudsTheme.componentsTokens.bulletList) {
        val typography: TextStyle
        val columnGap: Dp
        val verticalPadding: Dp
        val bulletSize: Dp
        val bulletContainerSize: Dp
        when (currentTextStyle) {
            OudsBulletListTextStyle.BodyLarge -> {
                typography = if (currentHasBoldText) OudsTheme.typography.body.strong.large else OudsTheme.typography.body.default.large
                columnGap = spaceColumnGapBodyLarge.value
                verticalPadding = spacePaddingBlockBodyLarge.value
                bulletSize = OudsTheme.sizes.icon.withBody.large.sizeSmall
                bulletContainerSize = OudsTheme.sizes.icon.withBody.large.sizeMedium
            }
            OudsBulletListTextStyle.BodyMedium -> {
                typography = if (currentHasBoldText) OudsTheme.typography.body.strong.medium else OudsTheme.typography.body.default.medium
                columnGap = spaceColumnGapBodyMedium.value
                verticalPadding = spacePaddingBlockBodyMedium.value
                bulletSize = OudsTheme.sizes.icon.withBody.medium.sizeSmall
                bulletContainerSize = OudsTheme.sizes.icon.withBody.medium.sizeMedium
            }
        }

        val paddingStart = when (level) {
            OudsBulletListItemNestedLevel.Zero -> spacePaddingInlineLevel0
            OudsBulletListItemNestedLevel.One -> spacePaddingInlineLevel1
            OudsBulletListItemNestedLevel.Two -> spacePaddingInlineLevel2
        }.dp

        Row(
            modifier = modifier
                .padding(start = paddingStart)
                .padding(vertical = verticalPadding),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(columnGap)
        ) {
            Box(modifier = Modifier.size(bulletContainerSize), contentAlignment = Alignment.CenterEnd) {
                Bullet(type = currentType, level = level, index = index, typography = typography, size = bulletSize)
            }
            Text(
                text = item.label,
                style = typography,
                color = OudsTheme.colorScheme.content.default
            )
        }

        if (item.subListItems.isNotEmpty()) {
            val nextLevel = OudsBulletListItemNestedLevel.entries.getOrNull(level.ordinal + 1)
            if (nextLevel != null) {
                val nextType = item.subListType ?: currentType
                val nextTextStyle = item.subListTextStyle ?: currentTextStyle
                val nextHasBoldText = item.subListHasBoldText ?: currentHasBoldText

                item.subListItems.forEachIndexed { childIndex, childItem ->
                    OudsBulletListItem(
                        item = childItem,
                        currentType = nextType,
                        currentTextStyle = nextTextStyle,
                        currentHasBoldText = nextHasBoldText,
                        index = childIndex,
                        level = nextLevel
                    )
                }
            } else {
                LaunchedEffect(Unit) {
                    Log.w("OudsBulletList", "Maximum list depth (3 levels) reached. Children of '${item.label}' will not be displayed.")
                }
            }
        }
    }
}

internal data class BulletListItem(
    val label: String,
    val subListType: OudsBulletListType?,
    val subListTextStyle: OudsBulletListTextStyle?,
    val subListHasBoldText: Boolean?,
    val subListItems: List<BulletListItem> = emptyList()
)

@Composable
private fun Bullet(type: OudsBulletListType, level: OudsBulletListItemNestedLevel, index: Int, typography: TextStyle, size: Dp) {
    when (type) {
        is OudsBulletListType.Unordered -> {
            val painter = when (type.icon) {
                is OudsBulletListUnorderedIcon.Bullet -> {
                    val iconRes = when (level) {
                        OudsBulletListItemNestedLevel.Zero -> OudsTheme.drawableResources.component.bulletList.level0
                        OudsBulletListItemNestedLevel.One -> OudsTheme.drawableResources.component.bulletList.level1
                        OudsBulletListItemNestedLevel.Two -> OudsTheme.drawableResources.component.bulletList.level2
                    }
                    painterResource(iconRes)
                }
                is OudsBulletListUnorderedIcon.Tick -> painterResource(OudsTheme.drawableResources.component.bulletList.tick)
                is OudsBulletListUnorderedIcon.Free -> type.icon.painter
            }
            UnorderedBullet(painter = painter, size = size, brandColor = type.brandColor)
        }
        is OudsBulletListType.Ordered -> when (level) {
            OudsBulletListItemNestedLevel.Zero -> OrderedBullet("${index + 1}.", textStyle = typography, size = size)
            OudsBulletListItemNestedLevel.One -> OrderedBullet("${('A' + index)}.", textStyle = typography, size = size)
            OudsBulletListItemNestedLevel.Two -> OrderedBullet("${('a' + index)}.", textStyle = typography, size = size)
        }
        is OudsBulletListType.Bare -> {}
    }
}

@Composable
private fun UnorderedBullet(painter: Painter, size: Dp, brandColor: Boolean) {
    Icon(
        modifier = Modifier.size(size),
        painter = painter,
        tint = if (brandColor) OudsTheme.colorScheme.content.brandPrimary else OudsTheme.colorScheme.content.default,
        contentDescription = null
    )
}

@Composable
private fun OrderedBullet(text: String, textStyle: TextStyle, size: Dp) {
    Text(
        modifier = Modifier.width(size),
        text = text,
        style = textStyle,
        color = OudsTheme.colorScheme.content.default,
        textAlign = TextAlign.End
    )
}

/**
 * Default values for [OudsBulletList].
 */
object OudsBulletListDefaults {

    /**
     * Default type of an [OudsBulletList].
     */
    val Type = OudsBulletListType.Unordered()

    /**
     * Default text style of an [OudsBulletList].
     */
    val TextStyle = OudsBulletListTextStyle.BodyLarge
}

/**
 * The type of an [OudsBulletList].
 */
sealed class OudsBulletListType {

    /**
     * Collects related items that don't need to be in a specific order or sequence. List items are typically marked with bullets, but it is also possible
     * to use a tick or any Solaris icon.
     *
     * @constructor Creates an instance of [OudsBulletListType.Unordered].
     * @param icon The type of icon to display, from the [OudsBulletListUnorderedIcon] sealed class. Defaults to [OudsBulletListUnorderedIcon.Bullet].
     * @param brandColor Controls the color of the unordered bullet. If `true`, the brand color is used; otherwise, the default content color is used.
     */
    class Unordered(val icon: OudsBulletListUnorderedIcon = OudsBulletListUnorderedIcon.Bullet(), val brandColor: Boolean = false) : OudsBulletListType()

    /**
     * Collects related items with numeric order or sequence. Numbering starts at 1 with the first list item and increases by increments of 1 for each
     * successive ordered list item.
     */
    object Ordered : OudsBulletListType()

    /**
     * An unordered list without any bullet or alphanumeric sequence. It sill has left-padding, so list items will appear indented. This is the default and
     * is also known as undecorated "Unstyled" list.
     */
    object Bare : OudsBulletListType()
}

/**
 * The icon to be used in an unordered [OudsBulletList].
 */
sealed class OudsBulletListUnorderedIcon() {

    /**
     * The default bullet style.
     *
     * Its visual representation changes automatically based on the nesting level of the item within the [OudsBulletList]:
     * - **Level 0**: A solid square bullet.
     * - **Level 1**: An outlined square bullet.
     * - **Level 2**: A dash.
     *
     * @constructor Creates an instance of [OudsBulletListUnorderedIcon.Bullet].
     */
    class Bullet : OudsBulletListUnorderedIcon()

    /**
     * A bullet represented by a tick (check) icon.
     *
     * @constructor Creates an instance of [OudsBulletListUnorderedIcon.Tick].
     */
    class Tick : OudsBulletListUnorderedIcon()

    /**
     * A bullet represented by a custom [Painter].
     * This allows for complete visual customization of the bullet, for instance by using a Solaris icon.
     *
     * @constructor Creates an instance of [OudsBulletListUnorderedIcon.Free].
     * @param painter The custom [Painter] to be used as a bullet.
     */
    class Free(val painter: Painter) : OudsBulletListUnorderedIcon()
}

/**
 * The text style of an [OudsBulletList].
 */
enum class OudsBulletListTextStyle {
    /**
     * Make sure to use this reference if the text accompanying the list component is the body large text.
     * This variant is designed for more visual, engaging experiences.
     */
    BodyLarge,

    /**
     * Make sure to use this reference if the text accompanying the list component is the body medium text.
     * This variant is best suited for functional, task oriented experiences.
     */
    BodyMedium
}

private enum class OudsBulletListItemNestedLevel {
    /**
     * Level 0 list items define the main structure.
     * Unordered level 0 list items are marked with full squares.
     * Ordered level 0 list items are marked with numbers.
     */
    Zero,

    /**
     * Level 1 (nested) list items provide hierarchy or subcategories.
     * Unordered level 1 list items are marked with outlined squares.
     * Ordered level 1 list items are marked with uppercase letters.
     */
    One,

    /**
     * Level 2 (nested) list items provide hierarchy or subcategories.
     * Unordered level 1 list items are marked with dashes.
     * Ordered level 1 list items are marked with lowercase letters.
     */
    Two
}

@PreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsBulletList(@PreviewParameter(OudsBulletListPreviewParameterProvider::class) parameter: OudsBulletListPreviewParameter) {
    PreviewOudsBulletList(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsBulletList(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: OudsBulletListPreviewParameter) {
    val customBullet = rememberVectorPainter(Icons.Outlined.FavoriteBorder)
    OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
        with(parameter) {
            val typeName = type.javaClass.simpleName
            OudsBulletList(
                type = type,
                textStyle = textStyle
            ) {
                item(label = "$typeName first item")
                item(
                    label = "$typeName second item with a non-bold, unordered sublist",
                    subListType = OudsBulletListType.Unordered(icon = OudsBulletListUnorderedIcon.Tick(), brandColor = true),
                    subListHasBoldText = false
                ) {
                    item(label = "Unordered subitem")
                    item(
                        label = "Unordered subitem with an ordered sublist",
                        subListType = OudsBulletListType.Ordered,
                    ) {
                        item(label = "Ordered subitem")
                        item(label = "Ordered subitem")
                    }
                }
                item(
                    label = "$typeName third item with a sublist that inherits from the parent type",
                ) {
                    item(label = "$typeName subitem")
                    item(label = "$typeName subitem")
                }
                item(
                    label = "$typeName fourth item with an unordered sublist and free bullets",
                    subListType = OudsBulletListType.Unordered(icon = OudsBulletListUnorderedIcon.Free(customBullet))
                ) {
                    item(label = "Unordered subitem")
                    item(label = "Unordered subitem")
                }
            }
        }
    }
}

internal data class OudsBulletListPreviewParameter(
    val type: OudsBulletListType = OudsBulletListDefaults.Type,
    val textStyle: OudsBulletListTextStyle = OudsBulletListDefaults.TextStyle
)

internal class OudsBulletListPreviewParameterProvider : BasicPreviewParameterProvider<OudsBulletListPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsBulletListPreviewParameter>
    get() = listOf(
        OudsBulletListPreviewParameter(type = OudsBulletListType.Bare),
        OudsBulletListPreviewParameter(type = OudsBulletListType.Ordered, textStyle = OudsBulletListTextStyle.BodyMedium),
        OudsBulletListPreviewParameter(),
    )

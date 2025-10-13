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
import androidx.annotation.DrawableRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
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

@Composable
fun OudsBulletList(
    modifier: Modifier = Modifier,
    type: OudsBulletListType = OudsBulletListDefaults.Type,
    textStyle: OudsBulletListTextStyle = OudsBulletListDefaults.TextStyle,
    bold: Boolean = true,
    builder: OudsBulletListBuilder.() -> Unit
) {
    val items = remember(builder) {
        OudsBulletListBuilderImpl().apply(builder).build()
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

interface OudsBulletListBuilder {
    fun item(
        label: String,
        type: OudsBulletListType? = null,
        textStyle: OudsBulletListTextStyle? = null,
        bold: Boolean? = null,
        builder: (OudsBulletListBuilder.() -> Unit)? = null
    )
}

private class OudsBulletListBuilderImpl : OudsBulletListBuilder {
    private val items = mutableListOf<BulletListItem>()

    override fun item(
        label: String,
        type: OudsBulletListType?,
        textStyle: OudsBulletListTextStyle?,
        bold: Boolean?,
        builder: (OudsBulletListBuilder.() -> Unit)?
    ) {
        val children = builder?.let { OudsBulletListBuilderImpl().apply(it).build() } ?: emptyList()
        items.add(BulletListItem(label, type, textStyle, bold, children))
    }

    fun build(): List<BulletListItem> = items
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
                bulletSize = 20.dp
                bulletContainerSize = 24.dp
            }
            OudsBulletListTextStyle.BodyMedium -> {
                typography = if (currentHasBoldText) OudsTheme.typography.body.strong.medium else OudsTheme.typography.body.default.medium
                columnGap = spaceColumnGapBodyMedium.value
                verticalPadding = spacePaddingBlockBodyMedium.value
                bulletSize = 16.dp
                bulletContainerSize = 20.dp
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
            verticalAlignment = Alignment.Top
        ) {
            Box(modifier = Modifier.size(bulletContainerSize), contentAlignment = Alignment.CenterEnd) {
                Bullet(type = currentType, level = level, index = index, typography = typography, size = bulletSize)
            }

            Column {
                Text(
                    modifier = Modifier.padding(start = columnGap),
                    text = item.label,
                    style = typography,
                    color = OudsTheme.colorScheme.content.default
                )

                if (item.children.isNotEmpty()) {
                    val nextLevel = OudsBulletListItemNestedLevel.entries.getOrNull(level.ordinal + 1)
                    if (nextLevel != null) {
                        val nextType = item.subListType ?: currentType
                        val nextTextStyle = item.subListTextStyle ?: currentTextStyle
                        val nextBoldValue = item.subListHasBoldText ?: currentHasBoldText

                        item.children.forEachIndexed { childIndex, childItem ->
                            OudsBulletListItem(
                                item = childItem,
                                currentType = nextType,
                                currentTextStyle = nextTextStyle,
                                currentHasBoldText = nextBoldValue,
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
    }
}

private data class BulletListItem(
    val label: String,
    val subListType: OudsBulletListType?,
    val subListTextStyle: OudsBulletListTextStyle?,
    val subListHasBoldText: Boolean?,
    val children: List<BulletListItem> = emptyList()
)

@Composable
private fun Bullet(type: OudsBulletListType, level: OudsBulletListItemNestedLevel, index: Int, typography: TextStyle, size: Dp) {
    when (type) {
        OudsBulletListType.Unordered -> when (level) {
            OudsBulletListItemNestedLevel.Zero -> UnorderedBullet(iconRes = OudsTheme.drawableResources.bulletListLevel0, size = size)
            OudsBulletListItemNestedLevel.One -> UnorderedBullet(iconRes = OudsTheme.drawableResources.bulletListLevel1, size = size)
            OudsBulletListItemNestedLevel.Two -> UnorderedBullet(iconRes = OudsTheme.drawableResources.bulletListLevel2, size = size)
        }
        OudsBulletListType.Ordered -> when (level) {
            OudsBulletListItemNestedLevel.Zero -> OrderedBullet("${index + 1}.", textStyle = typography, size = size)
            OudsBulletListItemNestedLevel.One -> OrderedBullet("${('A' + index)}.", textStyle = typography, size = size)
            OudsBulletListItemNestedLevel.Two -> OrderedBullet("${('a' + index)}.", textStyle = typography, size = size)
        }
        OudsBulletListType.Bare -> Box(modifier = Modifier.size(20.dp))
    }
}

@Composable
private fun UnorderedBullet(@DrawableRes iconRes: Int, size: Dp) {
    Icon(
        modifier = Modifier.size(size),
        painter = painterResource(iconRes),
        tint = OudsTheme.colorScheme.content.default,
        contentDescription = null
    )
}

@Composable
private fun OrderedBullet(text: String, textStyle: TextStyle, size: Dp) {
    Box(modifier = Modifier.width(size), contentAlignment = Alignment.CenterEnd) {
        Text(text = text, style = textStyle, color = OudsTheme.colorScheme.content.default)
    }
}

/**
 * Default values for [OudsBulletList].
 */
object OudsBulletListDefaults {

    /**
     * Default type of an [OudsBulletList].
     */
    val Type = OudsBulletListType.Bare

    /**
     * Default text style of an [OudsBulletList].
     */
    val TextStyle = OudsBulletListTextStyle.BodyLarge
}

enum class OudsBulletListType {
    /**
     * Collects related items that don't need to be in a specific order or sequence.List items are typically marked with bullets, but it is also possible
     * to use a tick or any Solaris icon. //TODO list with Solaris icon
     */
    Unordered,

    /**
     * Collects related items with numeric order or sequence. Numbering starts at 1 with the first list item and increases by increments of 1 for each
     * successive ordered list item.
     */
    Ordered,

    /**
     * An unordered list without any bullet or alphanumeric sequence. It sill has left-padding, so list items will appear indented. This is the default and
     * is also known as undecorated "Unstyled" list.
     */
    Bare
}

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
private fun PreviewOudsBulletList(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: OudsBulletListPreviewParameter) {
    OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
        with(parameter) {
            OudsBulletList(
                type = type,
                textStyle = textStyle
            ) {
                item(label = "${type.name} first item")
                item(
                    label = "${type.name} second item with an unordered sublist",
                    type = OudsBulletListType.Unordered
                ) {
                    item(label = "Unordered subitem")
                    item(
                        label = "Unordered subitem with an ordered sublist",
                        type = OudsBulletListType.Ordered,
                        bold = false
                    ) {
                        item(label = "Ordered subitem")
                        item(label = "Ordered subitem")
                    }
                }
                item(
                    label = "${type.name} third item with a sublist that inherits from the parent type",
                ) {
                    item(label = "${type.name} subitem")
                    item(label = "${type.name} subitem")
                }
                item(label = "Ordered fourth item")
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
        OudsBulletListPreviewParameter(),
        OudsBulletListPreviewParameter(type = OudsBulletListType.Ordered, textStyle = OudsBulletListTextStyle.BodyMedium),
        OudsBulletListPreviewParameter(type = OudsBulletListType.Unordered),
    )

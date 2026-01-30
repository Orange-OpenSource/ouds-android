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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.component.content.OudsComponentContent
import com.orange.ouds.core.component.content.OudsComponentIcon
import com.orange.ouds.core.component.content.OudsPolymorphicComponentContent
import com.orange.ouds.core.component.content.PolymorphicContent
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.theme.OudsThemeContract

@DslMarker
annotation class OudsBulletListDslMarker

/**
 * Maximum bullet list depth.
 */
private const val MaxLevelCount = 3

/**
 * Bullet list is a UI element that helps to view in related individual text items grouped together; items usually starting with a number or a bullet.
 *
 * Bullet list is also known as “Unordered list” or “Ordered list” and is not an interactive element by default, although text items can support hypertext links.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://r.orange.fr/r/S-ouds-doc-bullet-list)
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
                parentTypes = emptyList()
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
    parentTypes: List<OudsBulletListType>,
    modifier: Modifier = Modifier,
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

        val level = parentTypes.count()
        val paddingStart = when (level) {
            0 -> spacePaddingInlineLevel0
            1 -> spacePaddingInlineLevel1
            else -> spacePaddingInlineLevel2
        }.dp

        Row(
            modifier = modifier
                .padding(start = paddingStart)
                .padding(vertical = verticalPadding),
            horizontalArrangement = Arrangement.spacedBy(columnGap)
        ) {
            Box(modifier = Modifier.size(bulletContainerSize * LocalDensity.current.fontScale), contentAlignment = Alignment.CenterEnd) {
                Bullet(type = currentType, index = index, typography = typography, size = bulletSize, parentTypes = parentTypes)
            }
            Text(
                text = item.label,
                style = typography,
                color = OudsTheme.colorScheme.content.default
            )
        }

        if (item.subListItems.isNotEmpty()) {
            val nextLevel = level + 1
            if (nextLevel < MaxLevelCount) {
                val nextType = item.subListType ?: currentType
                val nextTextStyle = item.subListTextStyle ?: currentTextStyle
                val nextHasBoldText = item.subListHasBoldText ?: currentHasBoldText

                item.subListItems.forEachIndexed { index, subListItem ->
                    OudsBulletListItem(
                        item = subListItem,
                        currentType = nextType,
                        currentTextStyle = nextTextStyle,
                        currentHasBoldText = nextHasBoldText,
                        index = index,
                        parentTypes = parentTypes + currentType
                    )
                }
            } else {
                LaunchedEffect(Unit) {
                    Log.w("OudsBulletList", "Maximum list depth ($MaxLevelCount levels) reached. Children of '${item.label}' will not be displayed.")
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
private fun Bullet(type: OudsBulletListType, index: Int, typography: TextStyle, size: Dp, parentTypes: List<OudsBulletListType>) {
    when (type) {
        is OudsBulletListType.Unordered -> type.asset.PolymorphicContent(
            extraParameters = OudsBulletListUnorderedAsset.ExtraParameters(size, type.brandColor, parentTypes)
        )
        is OudsBulletListType.Ordered -> {
            val level = parentTypes.count()
            when (level) {
                0 -> OrderedBullet("${index + 1}.", textStyle = typography, size = size)
                1 -> OrderedBullet("${('A' + index)}.", textStyle = typography, size = size)
                else -> OrderedBullet("${('a' + index)}.", textStyle = typography, size = size)
            }
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
     * @param asset The type of asset to display, from [OudsBulletListUnorderedAsset]. Defaults to [OudsBulletListUnorderedAsset.Bullet].
     * @param brandColor Controls the color of the unordered bullet. If `true`, the brand color is used; otherwise, the default content color is used.
     */
    class Unordered(val asset: OudsBulletListUnorderedAsset = OudsBulletListUnorderedAsset.Bullet, val brandColor: Boolean = true) : OudsBulletListType()

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
 * The asset to be used in an unordered [OudsBulletList].
 */
sealed interface OudsBulletListUnorderedAsset : OudsPolymorphicComponentContent {

    /**
     * A bullet represented by a custom icon.
     * This allows for complete visual customization of the bullet, for instance by using a Solaris icon.
     */
    class Icon private constructor(graphicsObject: Any) : OudsBulletListUnorderedAsset,
        OudsComponentIcon<OudsBulletListUnorderedAsset.ExtraParameters, Icon>(OudsBulletListUnorderedAsset.ExtraParameters::class.java, graphicsObject, "") {

        /**
         * Creates an instance of [OudsBulletListUnorderedAsset.Icon].
         *
         * @param painter The custom [Painter] to be used as a bullet.
         */
        constructor(painter: Painter) : this(painter as Any)

        /**
         * Creates an instance of [OudsBulletListUnorderedAsset.Icon].
         *
         * @param imageVector Image vector to be used as a bullet.
         */
        constructor(imageVector: ImageVector) : this(imageVector as Any)

        /**
         * Creates an instance of [OudsBulletListUnorderedAsset.Icon].
         *
         * @param bitmap Image bitmap to be used as a bullet.
         */
        constructor(bitmap: ImageBitmap) : this(bitmap as Any)

        @Composable
        override fun Content(modifier: Modifier) {
            super.Content(modifier.size(extraParameters.size))
        }

        override val tint: Color?
            @Composable
            get() = if (extraParameters.brandColor) OudsTheme.colorScheme.content.brandPrimary else OudsTheme.colorScheme.content.default
    }

    /**
     * The default bullet style.
     *
     * Its visual representation changes automatically based on the nesting level of the item within the [OudsBulletList]:
     * - **Level 0**: A solid square bullet.
     * - **Level 1**: An outlined square bullet.
     * - **Level 2**: A dash.
     */
    object Bullet : OudsBulletListUnorderedAsset,
        OudsComponentContent<OudsBulletListUnorderedAsset.ExtraParameters>(OudsBulletListUnorderedAsset.ExtraParameters::class.java) {

        @Composable
        override fun Content(modifier: Modifier) {
            with(extraParameters) {
                val parentOrderedTypeCount = parentTypes.count { it is OudsBulletListType.Ordered }
                val level = parentTypes.count()
                val unorderedBulletLevel = level - parentOrderedTypeCount
                val iconRes = when (unorderedBulletLevel) {
                    0 -> OudsTheme.drawableResources.component.bulletList.level0
                    1 -> OudsTheme.drawableResources.component.bulletList.level1
                    else -> OudsTheme.drawableResources.component.bulletList.level2
                }
                UnorderedBullet(painter = painterResource(iconRes), size = size, brandColor = brandColor)
            }
        }
    }

    /**
     * A bullet represented by a tick (check) icon.
     */
    object Tick : OudsBulletListUnorderedAsset,
        OudsComponentContent<OudsBulletListUnorderedAsset.ExtraParameters>(OudsBulletListUnorderedAsset.ExtraParameters::class.java) {

        @Composable
        override fun Content(modifier: Modifier) {
            with(extraParameters) {
                UnorderedBullet(painter = painterResource(OudsTheme.drawableResources.component.bulletList.tick), size = size, brandColor = brandColor)
            }
        }
    }

    @ConsistentCopyVisibility
    data class ExtraParameters internal constructor(
        internal val size: Dp,
        internal val brandColor: Boolean,
        internal val parentTypes: List<OudsBulletListType>
    ) : OudsComponentContent.ExtraParameters()
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
                    subListType = OudsBulletListType.Unordered(asset = OudsBulletListUnorderedAsset.Tick, brandColor = false),
                    subListHasBoldText = false
                ) {
                    item(label = "Unordered subitem")
                    item(
                        label = "Unordered subitem with an ordered sublist",
                        subListType = OudsBulletListType.Ordered,
                    ) {
                        repeat(2) {
                            item(label = "Ordered subitem")
                        }
                    }
                }
                item(
                    label = "$typeName third item with a sublist that inherits from the parent type",
                ) {
                    item(label = "$typeName subitem")
                    item(label = "$typeName subitem with an unordered sublist", subListType = OudsBulletListType.Unordered()) {
                        repeat(2) {
                            item(label = "Unordered subitem")
                        }
                    }
                }
                item(
                    label = "$typeName fourth item with an unordered sublist and free bullets",
                    subListType = OudsBulletListType.Unordered(asset = OudsBulletListUnorderedAsset.Icon(customBullet))
                ) {
                    item(label = "Unordered subitem")
                    item(label = "Unordered subitem with an unordered sublist", subListType = OudsBulletListType.Unordered()) {
                        repeat(2) {
                            item(label = "Unordered subitem")
                        }
                    }
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

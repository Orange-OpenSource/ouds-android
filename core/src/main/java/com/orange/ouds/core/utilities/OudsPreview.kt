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

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.orange.ouds.core.component.common.text.buildOudsAnnotatedErrorMessage
import com.orange.ouds.core.component.common.text.buildOudsAnnotatedHelperText
import com.orange.ouds.core.component.common.text.withStrong
import com.orange.ouds.core.extensions.isNightModeEnabled
import com.orange.ouds.core.theme.LocalHighContrastModeEnabled
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.theme.OudsThemeContract
import com.orange.ouds.theme.OudsThemeSettings
import com.orange.ouds.theme.orange.OrangeTheme
import com.orange.ouds.theme.orange.getPreviewOrangeFontFamily
import kotlin.enums.enumEntries
import kotlin.math.ceil
import kotlin.math.min

internal val LocalPreviewRowFlowItem = staticCompositionLocalOf<String?> { null }
internal val LocalPreviewGridRow = staticCompositionLocalOf<String?> { null }
internal val LocalPreviewGridColumn = staticCompositionLocalOf<String?> { null }
private val LocalPreviewRowFlowItemEnumEntry = staticCompositionLocalOf<Any?> { null }
private val LocalPreviewGridRowEnumEntry = staticCompositionLocalOf<Any?> { null }
private val LocalPreviewGridColumnEnumEntry = staticCompositionLocalOf<Any?> { null }

/**
 * The device used in the OUDS preview environment.
 */
const val OudsPreviewDevice = Devices.NEXUS_5

/**
 * Configures the Compose OUDS preview environment in Android Studio.
 *
 * @param modifier The modifier to be applied to the preview content.
 * @param theme The preview theme.
 * @param darkThemeEnabled Indicates whether the dark theme is enabled or not.
 * @param highContrastModeEnabled Indicates whether high contrast mode is enabled for the preview.
 * @param content The content of the preview.
 *
 * @suppress
 */
@Composable
fun OudsPreview(
    modifier: Modifier = Modifier,
    theme: OudsThemeContract = getPreviewTheme(),
    darkThemeEnabled: Boolean = isSystemInDarkTheme(),
    highContrastModeEnabled: Boolean = false,
    content: @Composable () -> Unit
) {
    // Updating the configuration is only needed for UI tests
    // It is not needed for Android Studio previews because the uiMode parameter of the @Preview annotation already configures the UI mode properly
    val configuration = LocalConfiguration.current.apply {
        isNightModeEnabled = darkThemeEnabled
    }
    CompositionLocalProvider(value = LocalConfiguration provides configuration) {
        OudsTheme(
            theme = theme,
            darkThemeEnabled = darkThemeEnabled
        ) {
            // Override theme settings
            // Add a box to be able to see components
            // Use a box instead of a surface to avoid clipping children in cases where something is drawn outside of the component to preview
            Box(
                modifier = Modifier
                    .background(OudsTheme.colorScheme.background.primary)
                    .then(modifier)
            ) {
                CompositionLocalProvider(LocalHighContrastModeEnabled provides highContrastModeEnabled) {
                    content()
                }
            }
        }
    }
}

internal fun OudsThemeContract.mapSettings(transform: (OudsThemeSettings) -> (OudsThemeSettings)): OudsThemeContract {
    return object : OudsThemeContract by this {
        override val settings = transform(this@mapSettings.settings)
    }
}

internal fun getPreviewTheme(): OudsThemeContract = OrangeTheme(getPreviewOrangeFontFamily())

internal fun buildPreviewAnnotatedErrorMessage() = buildOudsAnnotatedErrorMessage {
    append("Error message with ")
    withStrong { append("strong") }
    append(" text")
}

internal fun buildPreviewAnnotatedHelperText() = buildOudsAnnotatedHelperText {
    append("Helper text with ")
    withStrong { append("strong") }
    append(" text")
}

@Composable
internal inline fun <reified T> getPreviewEnumEntry(): T? where T : Enum<T> {
    return listOf(LocalPreviewRowFlowItemEnumEntry, LocalPreviewGridRowEnumEntry, LocalPreviewGridColumnEnumEntry)
        .firstNotNullOfOrNull { providableCompositionLocal ->
            providableCompositionLocal.current as? T
        }
}

@Composable
internal fun getPreviewFlowRowItem(): String? = LocalPreviewRowFlowItem.current

@Composable
internal fun getPreviewGridRow(): String? = LocalPreviewGridRow.current

@Composable
internal fun getPreviewGridColumn(): String? = LocalPreviewGridColumn.current

@Composable
internal fun PreviewFlowRow(
    items: List<String>,
    itemName: (String) -> String = { it },
    maxItemsInEachRow: Int = items.count(),
    edgeToEdge: Boolean = false,
    filter: (String) -> Boolean = { true },
    content: @Composable (String) -> Unit
) {
    val filteredItems = items.filter(filter)
    val chunkedItems = filteredItems.chunked(maxItemsInEachRow)
    Box(modifier = Modifier.padding(vertical = PreviewPaddingDefault, horizontal = if (edgeToEdge) 0.dp else PreviewPaddingDefault)) {
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            repeat(min(maxItemsInEachRow, filteredItems.count())) { columnIndex ->
                val columnItems = chunkedItems.mapNotNull { it.getOrNull(columnIndex) }
                Column {
                    columnItems.forEachIndexed { rowIndex, item ->
                        DimensionTitle(
                            modifier = Modifier
                                .padding(top = if (rowIndex == 0) 0.dp else PreviewPaddingDefault, bottom = 8.dp)
                                .padding(horizontal = if (edgeToEdge) PreviewPaddingDefault else 0.dp),
                            title = itemName(item)
                        )
                        CompositionLocalProvider(LocalPreviewRowFlowItem provides item) {
                            content(item)
                        }
                    }
                }
            }
        }
    }
}

@Composable
internal fun PreviewGrid(
    columns: List<String>,
    rows: List<String>,
    columnTitle: (String) -> String = { it },
    rowTitle: (String) -> String = { it },
    content: @Composable (String, String) -> Unit
) {
    val space = 16.dp
    val columnCount = columns.count()
    val rowCount = rows.count()
    LazyVerticalGrid(
        columns = GridCells.Fixed(1 + columnCount),
        contentPadding = PaddingValues(all = space),
        horizontalArrangement = Arrangement.spacedBy(space),
        verticalArrangement = Arrangement.spacedBy(space)
    ) {
        repeat(1 + rowCount) { rowIndex ->
            repeat(1 + columnCount) { columnIndex ->
                item {
                    val row = rows.getOrNull(rowIndex - 1)
                    val column = columns.getOrNull(columnIndex - 1)
                    when {
                        row == null && column != null -> DimensionTitle(columnTitle(column))
                        row != null && column == null -> DimensionTitle(rowTitle(row))
                        row != null && column != null -> {
                            Box {
                                CompositionLocalProvider(
                                    LocalPreviewGridRow provides row,
                                    LocalPreviewGridColumn provides column
                                ) {
                                    content(column, row)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
internal inline fun <reified T> PreviewEnumEntries(
    noinline itemName: (String) -> String = { it },
    maxEnumEntriesInEachRow: Int = enumEntries<T>().count(),
    edgeToEdge: Boolean = false,
    noinline filter: (T) -> Boolean = { true },
    noinline content: @Composable (T) -> Unit
) where T : Enum<T> {
    PreviewFlowRow(
        items = enumEntries<T>().map { it.name },
        itemName = itemName,
        maxItemsInEachRow = maxEnumEntriesInEachRow,
        edgeToEdge = edgeToEdge,
        filter = { filter(enumValueOf(it)) },
        content = { item ->
            val enumEntry = enumValueOf<T>(item)
            CompositionLocalProvider(LocalPreviewRowFlowItemEnumEntry provides enumEntry) {
                content(enumEntry)
            }
        }
    )
}

@Composable
internal inline fun <reified T, reified S> PreviewEnumEntries(
    noinline columnTitle: (String) -> String = { it },
    noinline rowTitle: (String) -> String = { it },
    noinline content: @Composable (T, S) -> Unit
) where T : Enum<T>, S : Enum<S> {
    PreviewGrid(
        columns = enumEntries<T>().map { it.name },
        rows = enumEntries<S>().map { it.name },
        columnTitle = columnTitle,
        rowTitle = rowTitle,
        content = { column, row ->
            val columnEnumEntry = enumValueOf<T>(column)
            val rowEnumEntry = enumValueOf<S>(row)
            CompositionLocalProvider(
                LocalPreviewGridRowEnumEntry provides rowEnumEntry,
                LocalPreviewGridColumnEnumEntry provides columnEnumEntry
            ) {
                content(columnEnumEntry, rowEnumEntry)
            }
        }
    )
}

@Composable
private fun DimensionTitle(title: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = title,
        color = OudsTheme.colorScheme.content.default,
        fontFamily = FontFamily.Monospace,
        fontSize = 10.sp
    )
}

internal val PreviewPaddingDefault = 16.dp

/**
 * Long text used in previews.
 */
internal const val LoremIpsumText =
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."

internal class CheckerboardPainter(val squareSize: Dp, val primaryColor: Color, val secondaryColor: Color) : Painter() {

    override val intrinsicSize = Size.Unspecified

    override fun DrawScope.onDraw() {
        val squareSizePx = squareSize.toPx()
        val columnCount = ceil(size.width / squareSizePx).toInt()
        val rowCount = ceil(size.height / squareSizePx).toInt()
        val drawSize = Size(squareSizePx, squareSizePx)

        repeat(rowCount) { row ->
            repeat(columnCount) { column ->
                val color = if ((row + column) % 2 == 0) primaryColor else secondaryColor
                drawRect(
                    color = color,
                    topLeft = Offset(column * squareSizePx, row * squareSizePx),
                    size = drawSize
                )
            }
        }
    }
}

@Composable
internal fun rememberRainbowHeartPainter(): Painter {
    val heartPainter = rememberVectorPainter(Icons.Filled.FavoriteBorder)

    return remember(heartPainter) {
        object : Painter() {
            override val intrinsicSize: Size
                get() = heartPainter.intrinsicSize

            override fun DrawScope.onDraw() {
                // Rainbow colors adjusted for better visibility on both light and dark backgrounds
                // Using mid-tone colors that work well on both white and black
                val rainbowColors = listOf(
                    Color(0xFFE63946), // Red - slightly darker
                    Color(0xFFFF8C42), // Orange - vibrant
                    Color(0xFFFFB703), // Yellow-Orange - darker than pure yellow for visibility on white
                    Color(0xFF06D6A0), // Teal-Green - brighter than pure green
                    Color(0xFF118AB2), // Blue - mid-tone
                    Color(0xFF7209B7), // Purple - brighter than indigo
                    Color(0xFFD62598)  // Magenta - visible on both backgrounds
                )

                // Create a horizontal rainbow gradient with equal color distribution
                val colorStops = rainbowColors.mapIndexed { index, color ->
                    (index.toFloat() / (rainbowColors.size - 1)) to color
                }.toTypedArray()

                val rainbowBrush = Brush.horizontalGradient(colorStops = colorStops, startX = 0f, endX = size.width)

                // Draw with the gradient applied
                drawContext.canvas.saveLayer(bounds = Rect(0f, 0f, size.width, size.height), paint = Paint())

                // Draw the heart icon
                with(heartPainter) {
                    draw(size)
                }

                // Apply the rainbow gradient with SrcIn blend mode
                drawRect(brush = rainbowBrush, size = size, blendMode = BlendMode.SrcIn)

                drawContext.canvas.restore()
            }
        }
    }
}


/**
 * Preview annotation to display a composable in both light and dark modes.
 *
 * The composable will be rendered on a [OudsPreviewDevice].
 */
@Preview(name = "Light", device = OudsPreviewDevice)
@Preview(name = "Dark", uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL, device = OudsPreviewDevice)
annotation class OudsPreviewLightDark

/**
 * Preview annotation to display a composable in light mode.
 *
 * The composable will be rendered on a [OudsPreviewDevice].
 */
@Preview(device = OudsPreviewDevice)
annotation class OudsPreview

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

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.SheetValue.Hidden
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.theme.OudsThemeContract

// TODO Add description and design guideline link when available
/**
 * The bottom sheets show secondary content anchored to the bottom of the screen.
 * [OudsModalBottomSheet] displays content that temporarily blocks interaction with the main screen.
 * It appears in front of app content, disabling all other app functionality when they appear, and remaining on screen until confirmed,
 * dismissed, or a required action has been taken.
 *
 * For a bottom sheet that co-exists with the main screen content, use [OudsBottomSheetScaffold].
 *
 * @param onDismissRequest Callback executed when the user clicks outside the bottom sheet, after sheet animates to [Hidden].
 * @param modifier Optional [Modifier] for the modal bottom sheet.
 * @param sheetState The state of the bottom sheet, which controls its visibility and allows for programmatic control. See [rememberModalBottomSheetState].
 * @param sheetGesturesEnabled Whether the bottom sheet can be interacted with by gestures.
 * @param dragHandle Toggles the visibility of the visual drag handle at the top of the sheet. Set to `false` to hide it.
 * @param contentWindowInsets Callback which provides window insets to be passed to the bottom sheet content via [Modifier.windowInsetsPadding].
 *   [ModalBottomSheet] will pre-emptively consume top insets based on its current offset. This keeps content outside the expected window insets at any
 *   position.
 * @param properties [ModalBottomSheetProperties] for further customization of this modal bottom sheet's window behavior.
 * @param content The content to be displayed inside the modal bottom sheet.
 *
 * @sample com.orange.ouds.core.component.samples.OudsModalBottomSheetSample
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OudsModalBottomSheet(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
    sheetGesturesEnabled: Boolean = true,
    dragHandle: Boolean = true,
    contentWindowInsets: @Composable () -> WindowInsets = { BottomSheetDefaults.windowInsets },
    properties: ModalBottomSheetProperties = ModalBottomSheetProperties(),
    content: @Composable ColumnScope.() -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        sheetState = sheetState,
        sheetGesturesEnabled = sheetGesturesEnabled,
        containerColor = OudsTheme.colorScheme.overlay.modal,
        contentColor = OudsTheme.colorScheme.content.default,
        scrimColor = OudsTheme.colorScheme.always.black.copy(alpha = 0.64f),
        dragHandle = if (dragHandle) {
            { OudsBottomSheetDefaults.DragHandle() }
        } else {
            null
        },
        contentWindowInsets = contentWindowInsets,
        properties = properties,
        content = content
    )
}

@Suppress("PreviewShouldNotBeCalledRecursively")
@PreviewLightDark
@Composable
private fun PreviewOudsModalBottomSheet() {
    PreviewOudsModalBottomSheet(
        theme = getPreviewTheme(),
        darkThemeEnabled = isSystemInDarkTheme()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PreviewOudsModalBottomSheet(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    val density = LocalDensity.current
    OudsModalBottomSheet(
        onDismissRequest = { },
        sheetState = remember {
            SheetState(
                skipPartiallyExpanded = true,
                positionalThreshold = { with(density) { 56.dp.toPx() } },
                velocityThreshold = { with(density) { 125.dp.toPx() } },
                initialValue = SheetValue.Expanded,
            )
        }
    ) {
        Column(modifier = Modifier
            .padding(bottom = OudsTheme.spaces.fixed.medium)
            .padding(horizontal = OudsTheme.grids.margin)) {
            Text(text = "Modal bottom sheet content.")
        }
    }
}
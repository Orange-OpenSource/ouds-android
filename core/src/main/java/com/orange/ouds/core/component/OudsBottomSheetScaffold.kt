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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.theme.OudsThemeContract

// TODO Add description and design guideline link when available
/**
 * Bottom sheets show secondary content anchored to the bottom of the screen.
 * [OudsBottomSheetScaffold] lays out screen content with a standard bottom sheet anchored to the bottom. Standard bottom sheets co-exist with the main screen
 * content, allowing users to interact with both simultaneously.
 *
 * For a bottom sheet that appears in front of app content and requires user action to be dismissed (a modal behavior), consider using [OudsModalBottomSheet].
 *
 * @param sheetContent The content of the bottom sheet.
 * @param modifier The [Modifier] to be applied to the entire scaffold.
 * @param scaffoldState The state of the bottom sheet scaffold, which controls its behavior. See [rememberBottomSheetScaffoldState].
 * @param sheetPeekHeight The height of the bottom sheet when it is collapsed.
 * @param sheetDragHandle Toggles the visibility of the drag handle at the top of the bottom sheet. Set to `false` to hide it.
 * @param sheetSwipeEnabled Whether the sheet swiping is enabled and should react to the user's input.
 * @param topBar The top app bar of the screen, typically an [OudsTopAppBar].
 * @param snackbarHost The component to host [Snackbar]s that are pushed to be shown via [SnackbarHostState.showSnackbar], typically a [SnackbarHost].
 * @param content The main content of the screen. The lambda receives a [PaddingValues] that should be applied to the content root via [Modifier.padding]
 *   and [Modifier.consumeWindowInsets] to properly offset top and bottom bars. If using [Modifier.verticalScroll], apply this modifier to the child of the
 *   scroll, and not on the scroll itself.
 *
 * @sample com.orange.ouds.core.component.samples.OudsBottomSheetScaffoldSample
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OudsBottomSheetScaffold(
    sheetContent: @Composable ColumnScope.() -> Unit,
    modifier: Modifier = Modifier,
    scaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
    sheetPeekHeight: Dp = BottomSheetDefaults.SheetPeekHeight,
    sheetDragHandle: Boolean = true,
    sheetSwipeEnabled: Boolean = true,
    topBar: @Composable (() -> Unit)? = null,
    snackbarHost: @Composable (SnackbarHostState) -> Unit = { SnackbarHost(it) },
    content: @Composable (PaddingValues) -> Unit,
) {
    BottomSheetScaffold(
        sheetContent = sheetContent,
        modifier = modifier,
        scaffoldState = scaffoldState,
        sheetPeekHeight = sheetPeekHeight,
        sheetMaxWidth = BottomSheetDefaults.SheetMaxWidth,
        sheetShape = BottomSheetDefaults.ExpandedShape,
        sheetContainerColor = OudsTheme.colorScheme.overlay.modal,
        sheetShadowElevation = OudsTheme.elevations.emphasized,
        sheetDragHandle = if (sheetDragHandle) {
            { OudsBottomSheetDefaults.DragHandle() }
        } else {
            null
        },
        sheetSwipeEnabled = sheetSwipeEnabled,
        topBar = topBar,
        snackbarHost = snackbarHost,
        containerColor = Color.Transparent,
        contentColor = OudsTheme.colorScheme.content.default,
        content = content
    )
}

@Suppress("PreviewShouldNotBeCalledRecursively")
@PreviewLightDark
@Composable
private fun PreviewOudsBottomSheetScaffold() {
    PreviewOudsBottomSheetScaffold(
        theme = getPreviewTheme(),
        darkThemeEnabled = isSystemInDarkTheme()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PreviewOudsBottomSheetScaffold(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    OudsBottomSheetScaffold(
        sheetContent = {
            Column(modifier = Modifier.padding(vertical = OudsTheme.spaces.fixed.medium, horizontal = OudsTheme.grids.margin)) {
                Text(text = "Bottom sheet content")
            }
        },
        scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = rememberStandardBottomSheetState(initialValue = SheetValue.Expanded))
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Main screen content")
        }
    }
}
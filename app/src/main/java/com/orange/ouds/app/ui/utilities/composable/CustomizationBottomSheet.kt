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

package com.orange.ouds.app.ui.utilities.composable

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LifecycleResumeEffect
import com.orange.ouds.app.R
import com.orange.ouds.core.theme.OudsTheme
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomizationBottomSheetScaffold(
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    titleResId: Int = R.string.app_common_customize_label,
    bottomSheetContent: @Composable ColumnScope.() -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetHeaderStateDescription = when (bottomSheetScaffoldState.bottomSheetState.currentValue) {
        SheetValue.Hidden, SheetValue.PartiallyExpanded -> stringResource(R.string.app_common_bottomSheetCollapsed_a11y)
        SheetValue.Expanded -> stringResource(R.string.app_common_bottomSheetExpanded_a11y)
    }
    val screenHeight = LocalConfiguration.current.screenHeightDp
    val customizationContentHeight = screenHeight.dp / 2 - BottomSheetDefaults.SheetPeekHeight

    BackHandler(bottomSheetScaffoldState.bottomSheetState.currentValue == SheetValue.Expanded) {
        coroutineScope.launch {
            bottomSheetScaffoldState.bottomSheetState.partialExpand()
        }
    }
    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetSwipeEnabled = false,
        sheetDragHandle = null,
        containerColor = OudsTheme.colorScheme.background.primary,
        sheetContent = {
            Row(
                modifier = Modifier
                    .clickable {
                        coroutineScope.launch {
                            if (bottomSheetScaffoldState.bottomSheetState.currentValue == SheetValue.Expanded) {
                                bottomSheetScaffoldState.bottomSheetState.partialExpand()
                            } else {
                                bottomSheetScaffoldState.bottomSheetState.expand()
                            }
                        }
                    }
                    .semantics {
                        stateDescription = bottomSheetHeaderStateDescription
                    }
                    .fillMaxWidth()
                    .height(BottomSheetDefaults.SheetPeekHeight)
                    .padding(horizontal = OudsTheme.grids.margin),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.medium)
            ) {
                val degrees = if (bottomSheetScaffoldState.bottomSheetState.currentValue == SheetValue.Expanded) 0f else -180f
                val angle by animateFloatAsState(targetValue = degrees, label = "ComponentCustomizationBottomSheetScaffoldIconRotation")
                Icon(
                    modifier = Modifier.rotate(angle),
                    painter = painterResource(id = R.drawable.ic_chevron_down),
                    contentDescription = null,
                    tint = OudsTheme.colorScheme.content.default
                )
                Text(
                    modifier = Modifier.padding(start = OudsTheme.spaces.fixed.medium),
                    text = stringResource(id = titleResId),
                    style = OudsTheme.typography.body.strong.large
                )
            }

            val scrollState = rememberScrollState()

            Column(
                modifier = Modifier
                    .height(customizationContentHeight)
                    .verticalScrollbar(scrollState)
                    .verticalScroll(scrollState)
            ) {
                bottomSheetContent()
            }
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .consumeWindowInsets(innerPadding)
                    .padding(innerPadding),
            ) {
                content()
            }
        }
    )

    var shouldExpand by rememberSaveable { mutableStateOf(true) }
    LifecycleResumeEffect(Unit) {
        if (shouldExpand) {
            shouldExpand = false
            tryExpandBottomSheet(coroutineScope, bottomSheetScaffoldState.bottomSheetState)
        }
        onPauseOrDispose {}
    }
}

@OptIn(ExperimentalMaterial3Api::class)
private fun tryExpandBottomSheet(coroutineScope: CoroutineScope, sheetState: SheetState, retryCount: Int = 0) {
    coroutineScope.launch {
        try {
            sheetState.expand()
        } catch (exception: CancellationException) {
            // Retry up to 3 times if animation was interrupted by a composition
            if (retryCount < 3) {
                tryExpandBottomSheet(coroutineScope, sheetState, retryCount + 1)
            }
        }
    }
}

@Composable
private fun Modifier.verticalScrollbar(scrollState: ScrollState): Modifier {
    val scrollBarColor = OudsTheme.colorScheme.action.disabled
    val scrollbarWidth = 4.dp

    return drawWithContent {
        drawContent()

        val viewportHeight = this.size.height
        val viewportWidth = this.size.width
        val totalContentHeight = scrollState.maxValue.toFloat() + viewportHeight
        val scrollBarHeight = (viewportHeight / totalContentHeight) * viewportHeight
        val scrollBarStartOffset = (scrollState.value.toFloat() / totalContentHeight) * viewportHeight

        drawRect(
            color = scrollBarColor,
            topLeft = Offset(viewportWidth - scrollbarWidth.toPx(), scrollBarStartOffset),
            size = Size(scrollbarWidth.toPx(), scrollBarHeight)
        )
    }
}
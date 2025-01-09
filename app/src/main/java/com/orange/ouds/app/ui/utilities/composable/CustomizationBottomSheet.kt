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
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
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
    content: @Composable BoxScope.() -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetHeaderStateDescription = when (bottomSheetScaffoldState.bottomSheetState.currentValue) {
        SheetValue.Hidden, SheetValue.PartiallyExpanded -> stringResource(R.string.app_common_bottomSheetCollapsed_a11y)
        SheetValue.Expanded -> stringResource(R.string.app_common_bottomSheetExpanded_a11y)
    }
    BackHandler(bottomSheetScaffoldState.bottomSheetState.currentValue == SheetValue.Expanded) {
        coroutineScope.launch {
            bottomSheetScaffoldState.bottomSheetState.partialExpand()
        }
    }
    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetSwipeEnabled = false,
        sheetDragHandle = null,
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
                    .padding(horizontal = OudsTheme.spaces.fixed.medium),
                verticalAlignment = Alignment.CenterVertically
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
                    style = OudsTheme.typography.heading.medium
                )
            }

            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                bottomSheetContent()
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(OudsTheme.colorScheme.background.primary),
            content = content
        )
    }

    LifecycleResumeEffect(Unit) {
        tryExpandBottomSheet(coroutineScope, bottomSheetScaffoldState.bottomSheetState)
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

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

package com.orange.ouds.core.component.samples

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.core.component.OudsBottomSheetScaffold
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun OudsBottomSheetScaffoldSample() {
    OudsBottomSheetScaffold(
        sheetContent = {
            Text(
                modifier = Modifier.padding(vertical = OudsTheme.spaces.fixed.medium, horizontal = OudsTheme.grids.margin),
                text = "Bottom sheet content"
            )
        },
        scaffoldState = rememberBottomSheetScaffoldState(
            bottomSheetState = rememberStandardBottomSheetState(initialValue = SheetValue.Expanded)
        )
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Main screen content")
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewOudsBottomSheetScaffoldSample() = OudsPreview {
    OudsBottomSheetScaffoldSample()
}
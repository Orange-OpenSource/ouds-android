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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.core.component.OudsModalBottomSheet
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun OudsModalBottomSheetSample() {
    OudsModalBottomSheet(
        onDismissRequest = { },
        sheetState = rememberStandardBottomSheetState()
    ) {
        Column(modifier = Modifier.padding(vertical = OudsTheme.spaces.fixed.medium, horizontal = OudsTheme.grids.margin)) {
            Text(text = "Modal bottom sheet content.")
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewOudsModalBottomSheetSample() = OudsPreview {
    OudsModalBottomSheetSample()
}
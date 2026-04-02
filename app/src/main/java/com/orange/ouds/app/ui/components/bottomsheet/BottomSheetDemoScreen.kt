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

package com.orange.ouds.app.ui.components.bottomsheet

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.R
import com.orange.ouds.core.theme.OudsTheme

@Composable
fun BottomSheetContent() {
    Text(
        modifier = Modifier
            .padding(
                vertical = OudsTheme.spaces.fixed.medium,
                horizontal = OudsTheme.grids.margin
            ),
        text = stringResource(R.string.app_components_bottomSheet_sheetContentExample_text)
    )
}
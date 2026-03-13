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

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.R
import com.orange.ouds.core.theme.OudsTheme

internal object OudsBottomSheetDefaults {
    /**
     * The optional visual marker placed on top of a bottom sheet to indicate it may be dragged.
     */
    @Composable
    fun DragHandle() {
        val dragHandleDescription = stringResource(id = R.string.core_bottomSheet_dragHandle_a11y)
        Surface(
            modifier = Modifier
                .padding(vertical = OudsTheme.spaces.fixed.medium)
                .semantics {
                    contentDescription = dragHandleDescription
                },
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            shape = MaterialTheme.shapes.extraLarge,
        ) {
            Box(Modifier.size(width = 32.dp, height = 4.dp))
        }
    }
}
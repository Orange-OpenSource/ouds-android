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

package com.orange.ouds.app.ui.components.skeleton

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Composable
fun rememberSkeletonDemoState(
    securityMargin: Boolean = false
) = rememberSaveable(securityMargin, saver = SkeletonDemoState.Saver) {
    SkeletonDemoState(securityMargin)
}

class SkeletonDemoState(
    securityMargin: Boolean
) {

    companion object {

        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        securityMargin
                    )
                }
            },
            restore = { list: List<Any?> ->
                SkeletonDemoState(
                    list[0] as Boolean
                )
            }
        )
    }

    var securityMargin: Boolean by mutableStateOf(securityMargin)
}

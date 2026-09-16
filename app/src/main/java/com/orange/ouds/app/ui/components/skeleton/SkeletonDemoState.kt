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
import com.orange.ouds.core.component.OudsSkeletonState
import com.orange.ouds.core.component.rememberOudsSkeletonState

@Composable
fun rememberSkeletonDemoState(
    skeletonState: OudsSkeletonState = rememberOudsSkeletonState(),
    securityMargin: Boolean = false
) = rememberSaveable(skeletonState, securityMargin, saver = SkeletonDemoState.Saver) {
    SkeletonDemoState(skeletonState, securityMargin)
}

class SkeletonDemoState(
    val skeletonState: OudsSkeletonState,
    securityMargin: Boolean
) {

    companion object {

        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        with(OudsSkeletonState.Saver) { save(skeletonState) },
                        securityMargin
                    )
                }
            },
            restore = { list: List<Any?> ->
                val skeletonState = list[0]?.let { OudsSkeletonState.Saver.restore(it) }
                SkeletonDemoState(
                    skeletonState as OudsSkeletonState,
                    list[1] as Boolean
                )
            }
        )
    }

    var securityMargin: Boolean by mutableStateOf(securityMargin)
}

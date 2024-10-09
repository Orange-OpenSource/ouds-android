/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
 */

package com.orange.ouds.app.ui.tokens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.orange.ouds.app.R

val tokens = Token::class.sealedSubclasses.mapNotNull { it.objectInstance }

@Immutable
sealed class Token(
    @StringRes val titleRes: Int,
    @DrawableRes val imageRes: Int,
    @StringRes val descriptionRes: Int
) {

    companion object {
        fun fromId(tokenId: Long?) = tokens.firstOrNull { token -> token.id == tokenId }
    }

    val id: Long = Token::class.sealedSubclasses.indexOf(this::class).toLong()

    data object Opacity : Token(R.string.app_tokens_opacity_label, R.drawable.ic_filter_effects, R.string.app_token_opacity_description_text)
}

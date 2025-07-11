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

package com.orange.ouds.app.ui.utilities

import kotlin.reflect.KClass

val <T : KClass<S>, S : Any> T.previewCompatibleClass: KClass<S>
    get() = PreviewCompatibleClass(this)

/**
 * Using reflection in Compose previews can lead to render issues.
 * This class aims at fixing these issues by catching exceptions causing these render issues and returning default values instead.
 */
class PreviewCompatibleClass<T : Any>(private val clazz: KClass<T>) : KClass<T> by clazz {

    override val sealedSubclasses: List<KClass<out T>> = tryOrDefault(emptyList()) { sealedSubclasses }

    override val nestedClasses: Collection<KClass<*>> = tryOrDefault(emptyList()) { nestedClasses }

    override val isData: Boolean = tryOrDefault(false) { isData }

    private fun <R> tryOrDefault(defaultValue: R, block: KClass<T>.() -> R): R {
        return try {
            clazz.block()
        } catch (_: Exception) {
            defaultValue
        }
    }
}

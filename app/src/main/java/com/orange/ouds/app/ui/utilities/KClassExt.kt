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

import com.orange.ouds.app.ui.tokens.Token
import com.orange.ouds.core.theme.value
import com.orange.ouds.foundation.extensions.asOrNull
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.theme.tokens.OudsBorderKeyToken
import com.orange.ouds.theme.tokens.OudsColorKeyToken
import com.orange.ouds.theme.tokens.OudsElevationKeyToken
import com.orange.ouds.theme.tokens.OudsGridKeyToken
import com.orange.ouds.theme.tokens.OudsOpacityKeyToken
import com.orange.ouds.theme.tokens.OudsSizeKeyToken
import com.orange.ouds.theme.tokens.OudsSpaceKeyToken
import com.orange.ouds.theme.tokens.OudsTypographyKeyToken
import kotlin.reflect.KClass

fun KClass<*>.getTokens(
    recursive: Boolean = true,
    tokenName: (KClass<*>) -> String = { it.getTokenName(this) }
): List<Token<*>> {
    return getNestedObjects(getRootKeyTokenSuperclass(), recursive).map { keyToken ->
        Token(
            name = tokenName(keyToken::class),
            value = {
                when (keyToken) {
                    is OudsBorderKeyToken.Radius -> keyToken.value
                    is OudsBorderKeyToken.Style -> keyToken.value
                    is OudsBorderKeyToken.Width -> keyToken.value
                    is OudsColorKeyToken -> keyToken.value
                    is OudsElevationKeyToken -> keyToken.value
                    is OudsGridKeyToken -> keyToken.value
                    is OudsOpacityKeyToken -> keyToken.value
                    is OudsSizeKeyToken -> keyToken.value
                    is OudsSpaceKeyToken -> keyToken.value
                    is OudsTypographyKeyToken -> keyToken.value
                    else -> null
                }
            }
        )
    }
}

fun KClass<*>.getTokenName(fromParent: KClass<*>? = null): String {
    val prefix = if (fromParent != null) "${fromParent.qualifiedName.orEmpty()}." else "${java.`package`?.name.orEmpty()}."
    
    return qualifiedName.orEmpty()
        .removePrefix(prefix)
        .removeSuffix(".Companion")
}

private fun <T : Any> KClass<*>.getNestedObjects(clazz: KClass<T>, recursive: Boolean = true): List<T> {
    return getNestedClasses(recursive).mapNotNull { it.objectInstance }.filterIsInstance(clazz.java)
}

private fun KClass<*>.getNestedClasses(recursive: Boolean = true): List<KClass<*>> {
    return if (recursive) {
        nestedClasses + nestedClasses.flatMap { it.getNestedClasses(recursive = true) }
    } else {
        nestedClasses.toList()
    }
}

private fun KClass<*>.getRootKeyTokenSuperclass(): KClass<*> {
    return supertypes.firstOrNull()
        ?.classifier
        ?.asOrNull<KClass<*>>()
        ?.takeIf { it != Any::class }
        ?.getRootKeyTokenSuperclass()
        .orElse { this }
}

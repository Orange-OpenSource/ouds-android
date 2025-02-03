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

package com.orange.ouds.app.ui.tokens

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.orange.ouds.app.R
import com.orange.ouds.core.theme.OudsBorders
import com.orange.ouds.core.theme.OudsColorScheme
import com.orange.ouds.core.theme.OudsElevations
import com.orange.ouds.core.theme.OudsGrids
import com.orange.ouds.core.theme.OudsOpacities
import com.orange.ouds.core.theme.OudsSizes
import com.orange.ouds.core.theme.OudsSpaces
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.OudsTypography
import com.orange.ouds.foundation.extensions.asOrNull
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.primaryConstructor

sealed class TokenProperty<T>(
    @StringRes val nameRes: Int?,
    val tokens: List<Token<*>>,
    val categoryClass: KClass<T>
) where T : TokenCategory<T> {

    data object BorderRadius : TokenProperty<TokenCategory.Border>(
        nameRes = R.string.app_tokens_border_radius_label,
        tokens = getTokens<OudsBorders.Radius>(),
        categoryClass = TokenCategory.Border::class
    )

    data object BorderStyle : TokenProperty<TokenCategory.Border>(
        nameRes = R.string.app_tokens_border_style_label,
        tokens = getTokens<OudsBorders.Style>(),
        categoryClass = TokenCategory.Border::class
    )

    data object BorderWidth : TokenProperty<TokenCategory.Border>(
        nameRes = R.string.app_tokens_border_width_label,
        tokens = getTokens<OudsBorders.Width>(),
        categoryClass = TokenCategory.Border::class
    )

    data object ColorAction : TokenProperty<TokenCategory.Color>(
        nameRes = R.string.app_tokens_color_action_label,
        tokens = getTokens<OudsColorScheme.Action>(),
        categoryClass = TokenCategory.Color::class
    )

    data object ColorAlways : TokenProperty<TokenCategory.Color>(
        nameRes = R.string.app_tokens_color_always_label,
        tokens = getTokens<OudsColorScheme.Always>(),
        categoryClass = TokenCategory.Color::class
    )

    data object ColorBackground : TokenProperty<TokenCategory.Color>(
        nameRes = R.string.app_tokens_color_background_label,
        tokens = getTokens<OudsColorScheme.Background>(),
        categoryClass = TokenCategory.Color::class
    )

    data object ColorBorder : TokenProperty<TokenCategory.Color>(
        nameRes = R.string.app_tokens_color_border_label,
        tokens = getTokens<OudsColorScheme.Border>(),
        categoryClass = TokenCategory.Color::class
    )

    data object ColorContent : TokenProperty<TokenCategory.Color>(
        nameRes = R.string.app_tokens_color_content_label,
        tokens = getTokens<OudsColorScheme.Content>(),
        categoryClass = TokenCategory.Color::class
    )

    data object ColorDecorative : TokenProperty<TokenCategory.Color>(
        nameRes = R.string.app_tokens_color_decorative_label,
        tokens = getTokens<OudsColorScheme.Decorative>(),
        categoryClass = TokenCategory.Color::class
    )

    data object ColorOverlay : TokenProperty<TokenCategory.Color>(
        nameRes = R.string.app_tokens_color_overlay_label,
        tokens = getTokens<OudsColorScheme.Overlay>(),
        categoryClass = TokenCategory.Color::class
    )

    data object ColorSurface : TokenProperty<TokenCategory.Color>(
        nameRes = R.string.app_tokens_color_surface_label,
        tokens = getTokens<OudsColorScheme.Surface>(),
        categoryClass = TokenCategory.Color::class
    )

    data object Elevation : TokenProperty<TokenCategory.Elevation>(
        nameRes = null,
        tokens = getTokens<OudsElevations>(),
        categoryClass = TokenCategory.Elevation::class
    )

    data object Grid : TokenProperty<TokenCategory.Grid>(
        nameRes = null,
        tokens = getTokens<OudsGrids>(),
        categoryClass = TokenCategory.Grid::class
    )

    data object Opacity : TokenProperty<TokenCategory.Opacity>(
        nameRes = null,
        tokens = getTokens<OudsOpacities>(),
        categoryClass = TokenCategory.Opacity::class
    )

    data object SizeIconDecorative : TokenProperty<TokenCategory.Dimension.Size>(
        nameRes = R.string.app_tokens_dimension_size_iconDecorative_label,
        tokens = getTokens<OudsSizes.Icon.Decorative>(),
        categoryClass = TokenCategory.Dimension.Size::class
    )

    data object SizeIconWithText : TokenProperty<TokenCategory.Dimension.Size>(
        nameRes = R.string.app_tokens_dimension_size_iconWithText_label,
        tokens = listOf(
            OudsSizes.Icon.WithLabel.Small::class,
            OudsSizes.Icon.WithLabel.Medium::class,
            OudsSizes.Icon.WithLabel.Large::class,
            OudsSizes.Icon.WithLabel.ExtraLarge::class,
            OudsSizes.Icon.WithBody.Small::class,
            OudsSizes.Icon.WithBody.Medium::class,
            OudsSizes.Icon.WithBody.Large::class,
            OudsSizes.Icon.WithHeading.Small::class,
            OudsSizes.Icon.WithHeading.Medium::class,
            OudsSizes.Icon.WithHeading.Large::class,
            OudsSizes.Icon.WithHeading.ExtraLarge::class,
        ).flatMap { getTokens(it) },
        categoryClass = TokenCategory.Dimension.Size::class
    )

    data object SpaceColumnGap : TokenProperty<TokenCategory.Dimension.Space>(
        nameRes = R.string.app_tokens_dimension_space_columnGap_label,
        tokens = getTokens<OudsSpaces.ColumnGap>(),
        categoryClass = TokenCategory.Dimension.Space::class
    )

    data object SpaceFixed : TokenProperty<TokenCategory.Dimension.Space>(
        nameRes = R.string.app_tokens_dimension_space_fixed_label,
        tokens = getTokens<OudsSpaces.Fixed>(),
        categoryClass = TokenCategory.Dimension.Space::class
    )

    data object SpacePaddingInline : TokenProperty<TokenCategory.Dimension.Space>(
        nameRes = R.string.app_tokens_dimension_space_paddingInline_label,
        tokens = getTokens<OudsSpaces.PaddingInline>(),
        categoryClass = TokenCategory.Dimension.Space::class
    )

    data object SpacePaddingInset : TokenProperty<TokenCategory.Dimension.Space>(
        nameRes = R.string.app_tokens_dimension_space_paddingInset_label,
        tokens = getTokens<OudsSpaces.Inset>(),
        categoryClass = TokenCategory.Dimension.Space::class
    )

    data object SpacePaddingStack : TokenProperty<TokenCategory.Dimension.Space>(
        nameRes = R.string.app_tokens_dimension_space_paddingStack_label,
        tokens = getTokens<OudsSpaces.PaddingBlock>(),
        categoryClass = TokenCategory.Dimension.Space::class
    )

    data object SpaceRowGap : TokenProperty<TokenCategory.Dimension.Space>(
        nameRes = R.string.app_tokens_dimension_space_rowGap_label,
        tokens = getTokens<OudsSpaces.RowGap>(),
        categoryClass = TokenCategory.Dimension.Space::class
    )

    data object SpaceScaled : TokenProperty<TokenCategory.Dimension.Space>(
        nameRes = R.string.app_tokens_dimension_space_scaled_label,
        tokens = getTokens<OudsSpaces.Scaled>(),
        categoryClass = TokenCategory.Dimension.Space::class
    )

    data object Typography : TokenProperty<TokenCategory.Typography>(
        nameRes = null,
        tokens = getTokens<OudsTypography>(),
        categoryClass = TokenCategory.Typography::class
    )
}

inline fun <reified T : Any> getTokens() = getTokens(T::class)

fun <T : Any> getTokens(clazz: KClass<T>): List<Token<*>> {
    val packageName = clazz.java.`package`?.name.orEmpty()
    val className = clazz.qualifiedName.orEmpty().removePrefix("$packageName.")
    val rootClassName = className.substringBefore(".")
    val rootClass = Class.forName("$packageName.$rootClassName").kotlin
    val rootPath = getPath(rootClass)

    return getTokenPaths(rootClass, clazz.takeIf { it != rootClass }, rootPath).map { tokenPath ->
        Token(tokenPath, getTokenRelativeName(tokenPath, clazz), { getTokenValue(tokenPath) })
    }
}

private fun getPath(clazz: KClass<*>): String {
    val packageName = clazz.java.`package`?.name.orEmpty()
    val className = clazz.qualifiedName.orEmpty().removePrefix("$packageName.")

    return className.removePrefix("Ouds")
        .split(".")
        .joinToString(".") { string ->
            string.replaceFirstChar { it.lowercaseChar() }
        }
}

/**
 * Recursively browses the class tree starting from [clazz] to build token paths, only taking into account tokens that are children of [fromClass].
 * A token path is a string that identifies a token in the [OudsTheme] object, for instance "sizes.icon.withHeading.medium.sizeSmall".
 *
 * @param clazz The class to browse.
 * @param fromClass The class from which the token paths will be taken into account. For instance, using [OudsColorScheme.Action]
 *   will generate tokens for all children objects of [OudsColorScheme.Action] in the class tree. Other tokens in [OudsColorScheme] such as tokens in
 *   [OudsColorScheme.Background] will be skipped. Pass `null` to take all tokens into account.
 * @param parentPath The path of the parent object in the class tree.
 */
private fun getTokenPaths(clazz: KClass<*>, fromClass: KClass<*>?, parentPath: String): List<String> {
    return clazz.primaryConstructor
        ?.parameters // Use primary constructor parameters instead of declaredMemberProperties because parameters are sorted as they are declared
        .orEmpty()
        .flatMap { parameter ->
            val parameterClass = parameter.type.classifier as KClass<*>
            val pathPrefix = if (parentPath.isEmpty()) "" else "$parentPath."
            val path = "$pathPrefix${parameter.name.orEmpty()}" // Append parent path
            when {
                // parameter is a nested class, we'll continue browsing the class tree
                // When fromClass is encountered we call getTokenPaths recursively with a null fromClass parameter
                // to indicate that we can now take token paths into account
                parameterClass in clazz.nestedClasses -> {
                    getTokenPaths(
                        parameterClass,
                        fromClass.takeIf { parameterClass != fromClass },
                        path
                    )
                }
                // parameter is not a nested class thus a token value, we take the token path into account if fromClass is null
                // meaning we already encountered fromClass when browsing the class tree
                fromClass == null -> listOf(path)
                else -> emptyList()
            }
        }
}

private fun getTokenRelativeName(tokenPath: String, parentClass: KClass<*>): String {
    val parentPath = getPath(parentClass)
    return tokenPath.removePrefix("$parentPath.")
}

@Composable
private fun getTokenValue(tokenPath: String): Any? {
    val rootPropertyName = tokenPath.substringBefore(".")
    // We can't use reflection to get OudsTheme property with rootPropertyName because Composable getters are built differently by the JVM
    // They should be of type KProperty1 but the get method requires 3 parameters instead
    val rootProperty = with(OudsTheme) {
        when (rootPropertyName) {
            "borders" -> borders
            "colorScheme" -> colorScheme
            "elevations" -> elevations
            "grids" -> grids
            "opacities" -> opacities
            "sizes" -> sizes
            "spaces" -> spaces
            "typography" -> typography
            else -> null
        }
    }

    var value: Any? = rootProperty
    tokenPath.substringAfter(".")
        .split(".")
        // Loop through each element in the path to get the value using reflection starting from rootProperty
        .forEach { element ->
            value = value?.let { value ->
                value::class.declaredMemberProperties
                    .firstOrNull { it.name == element }
                    ?.asOrNull<KProperty1<Any?, Any?>>()
                    ?.get(value)
            }
        }

    return value
}

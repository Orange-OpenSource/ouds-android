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

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.PreviewCheckerboardPainter
import com.orange.ouds.core.utilities.PreviewEnumEntries
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.theme.OudsThemeContract

@Composable
internal fun OudsAvatar(
    painter: Painter,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource? = null
) {
    OudsAvatar(
        graphicsObject = painter,
        monogram = null,
        monogramColor = null,
        monogramBackgroundColor = null,
        onClick = onClick,
        modifier = modifier,
        interactionSource = interactionSource
    )
}

@Composable
internal fun OudsAvatar(
    imageVector: ImageVector,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource? = null
) {
    OudsAvatar(
        graphicsObject = imageVector,
        monogram = null,
        monogramColor = null,
        monogramBackgroundColor = null,
        onClick = onClick,
        modifier = modifier,
        interactionSource = interactionSource
    )
}

@Composable
internal fun OudsAvatar(
    bitmap: ImageBitmap,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource? = null
) {
    OudsAvatar(
        graphicsObject = bitmap,
        monogram = null,
        monogramColor = null,
        monogramBackgroundColor = null,
        onClick = onClick,
        modifier = modifier,
        interactionSource = interactionSource
    )
}

@Composable
internal fun OudsAvatar(
    monogram: Char,
    color: Color,
    backgroundColor: Color,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource? = null
) {
    OudsAvatar(
        graphicsObject = null,
        monogram = monogram,
        monogramColor = color,
        monogramBackgroundColor = backgroundColor,
        onClick = onClick,
        modifier = modifier,
        interactionSource = interactionSource
    )
}

@Composable
internal fun OudsAvatar(
    graphicsObject: Any?,
    monogram: Char?,
    monogramColor: Color?,
    monogramBackgroundColor: Color?,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource? = null
) {
    @Suppress("NAME_SHADOWING") val interactionSource = interactionSource ?: remember { MutableInteractionSource() }

    Box(
        modifier = modifier
            .size(OudsTheme.sizes.minInteractiveArea)
            .run {
                if (onClick != null) {
                    clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = onClick,
                        role = Role.Button
                    )
                } else {
                    this
                }
            },
        contentAlignment = Alignment.Center
    ) {
        OudsButton(
            nullableIcon = null,
            nullableLabel = null,
            onClick = {},
            appearance = OudsButtonAppearance.Minimal,
            interactionSource = interactionSource
        )

        val contentModifier = Modifier
            .clip(CircleShape)
            .size(AvatarSize)
        if (graphicsObject != null) {
            val contentScale = ContentScale.Crop
            when (graphicsObject) {
                is Painter -> Image(
                    modifier = contentModifier,
                    painter = graphicsObject,
                    contentDescription = null,
                    contentScale = contentScale
                )
                is ImageVector -> Image(
                    modifier = contentModifier,
                    imageVector = graphicsObject,
                    contentDescription = null,
                    contentScale = contentScale
                )
                is ImageBitmap -> Image(
                    modifier = contentModifier,
                    bitmap = graphicsObject,
                    contentDescription = null,
                    contentScale = contentScale
                )
            }
        } else if (monogram != null && monogramColor != null && monogramBackgroundColor != null) {
            Box(
                modifier = contentModifier.background(monogramBackgroundColor),
                contentAlignment = Alignment.Center,
            ) {
                CompositionLocalProvider(
                    // Do not take user font scale into account
                    value = LocalDensity provides Density(LocalDensity.current.density, 1f)
                ) {
                    Text(
                        modifier = Modifier.clearAndSetSemantics {},
                        text = monogram.uppercase(),
                        color = monogramColor,
                        style = MaterialTheme.typography.titleMedium, // This looks like the most accurate style according to Material specs at https://m3.material.io/components/app-bars/specs#606c6564-ce7d-489d-8852-af2b3b478bc6
                        fontFamily = OudsTheme.typography.fontFamily
                    )
                }
            }
        }
    }
}

private val AvatarSize = 32.dp

@PreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsAvatar(@PreviewParameter(OudsAvatarPreviewParameterProvider::class) isMonogram: Boolean) {
    PreviewOudsAvatar(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), isMonogram = isMonogram)
}

@Composable
internal fun PreviewOudsAvatar(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    isMonogram: Boolean
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    PreviewEnumEntries<OudsButtonState>(filter = { it !in listOf(OudsButtonState.Loading, OudsButtonState.Disabled) }) {
        if (isMonogram) {
            OudsAvatar(
                monogram = 'A',
                color = Color.White,
                backgroundColor = Color(0xffd5204e),
                onClick = {}
            )
        } else {
            OudsAvatar(
                painter = PreviewCheckerboardPainter(
                    squareSize = 6.dp,
                    primaryColor = Color(0xff247a85),
                    secondaryColor = Color(0xfffbcd00)
                ),
                onClick = {}
            )
        }
    }
}

internal class OudsAvatarPreviewParameterProvider : BasicPreviewParameterProvider<Boolean>(false, true)

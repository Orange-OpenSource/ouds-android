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

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.OudsPreviewLightDark
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.theme.OudsThemeContract

/**
 * A skeleton is a UI element that indicates when content is loading. The skeleton enhances user experience by
 * temporarily replacing content with gray areas or animations that simulate the visual structure of the forthcoming
 * content.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://r.orange.fr/r/S-ouds-doc-skeleton)
 *
 * > Design name: Skeleton
 *
 * > Design version: 1.0.0
 */
@Composable
fun OudsSkeleton(
    modifier: Modifier = Modifier,
    securityMargin: Boolean = true
) {
    with(OudsTheme.components.skeleton) {
        val verticalPadding = if (securityMargin) OudsTheme.spaces.paddingBlock.threeExtraSmall else 0.dp
        Box(
            modifier = modifier
                .padding(vertical = verticalPadding)
                .background(color.background)
        )
    }
}

@OudsPreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsSkeleton(@PreviewParameter(OudsSkeletonPreviewParameterProvider::class) securityMargin: Boolean) {
    PreviewOudsSkeleton(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), securityMargin = securityMargin)
}

@Composable
internal fun PreviewOudsSkeleton(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    securityMargin: Boolean
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    OudsSkeleton(
        modifier = Modifier.size(width = 200.dp, height = 62.dp),
        securityMargin = securityMargin
    )
}

internal class OudsSkeletonPreviewParameterProvider : BasicPreviewParameterProvider<Boolean>(true, false)

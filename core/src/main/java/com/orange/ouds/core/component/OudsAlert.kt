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

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ouds.core.R
import com.orange.ouds.core.component.content.OudsComponentContent
import com.orange.ouds.core.component.content.OudsComponentIcon
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.LayeredTintedPainter
import com.orange.ouds.foundation.extensions.orElse

/**
 * Base class for defining the semantic status of an alert component ([OudsInlineAlert] or [OudsAlertMessage]).
 * It holds the common logic for determining the colors and default icons.
 */
internal sealed class OudsAlertStatus(
    private val defaultIconPainterProvider: (@Composable (OudsAlertStatus) -> Painter?),
    private val defaultIconContentDescriptionProvider: (@Composable (OudsAlertStatus) -> String) = { "" }
) {

    class Accent(defaultIconPainterProvider: (@Composable (OudsAlertStatus) -> Painter?) = { getDefaultIconPainter(it) }) :
        OudsAlertStatus(defaultIconPainterProvider)

    class Neutral(defaultIconPainterProvider: (@Composable (OudsAlertStatus) -> Painter?) = { getDefaultIconPainter(it) }) :
        OudsAlertStatus(defaultIconPainterProvider)

    class Negative(
        defaultIconPainterProvider: (@Composable (OudsAlertStatus) -> Painter?) = { getDefaultIconPainter(it) },
        defaultIconContentDescriptionProvider: (@Composable (OudsAlertStatus) -> String) = { stringResource(R.string.core_common_error_a11y) }
    ) : OudsAlertStatus(defaultIconPainterProvider, defaultIconContentDescriptionProvider)

    class Positive(defaultIconPainterProvider: (@Composable (OudsAlertStatus) -> Painter?) = { getDefaultIconPainter(it) }) :
        OudsAlertStatus(defaultIconPainterProvider)

    class Info(defaultIconPainterProvider: (@Composable (OudsAlertStatus) -> Painter?) = { getDefaultIconPainter(it) }) :
        OudsAlertStatus(defaultIconPainterProvider)

    class Warning(
        defaultIconPainterProvider: (@Composable (OudsAlertStatus) -> Painter?) = { getDefaultIconPainter(it) },
        defaultIconContentDescriptionProvider: (@Composable (OudsAlertStatus) -> String) = { stringResource(R.string.core_common_warning_a11y) }
    ) :
        OudsAlertStatus(defaultIconPainterProvider, defaultIconContentDescriptionProvider)

    companion object {

        @Composable
        protected fun getDefaultIconPainter(status: OudsAlertStatus): Painter? {
            return when (status) {
                is Negative -> painterResource(OudsTheme.drawableResources.component.alert.importantFill)
                is Positive -> painterResource(OudsTheme.drawableResources.component.alert.tickConfirmationFill)
                is Info -> painterResource(OudsTheme.drawableResources.component.alert.infoFill)
                is Warning -> {
                    val iconTokens = OudsTheme.componentsTokens.icon
                    LayeredTintedPainter(
                        backPainter = painterResource(id = OudsTheme.drawableResources.component.alert.warningExternalShape),
                        backPainterColor = iconTokens.colorContentStatusWarningExternalShape.value,
                        frontPainter = painterResource(id = OudsTheme.drawableResources.component.alert.warningInternalShape),
                        frontPainterColor = iconTokens.colorContentStatusWarningInternalShape.value
                    )
                }
                is Accent,
                is Neutral -> null
            }
        }
    }

    /**
     * The default painter associated with a functional status (e.g., success, warning).
     * Returns `null` for non-functional statuses like Accent or Neutral, which do not have a default icon.
     */
    val defaultIconPainter: Painter?
        @Composable
        get() = defaultIconPainterProvider(this)

    val defaultIconContentDescription: String
        @Composable
        get() = defaultIconContentDescriptionProvider(this)

    /**
     * The asset color associated with this status.
     */
    val assetColor
        @Composable
        get() = with(OudsTheme.colorScheme.content) {
            when (this@OudsAlertStatus) {
                is Neutral -> default
                is Accent -> status.accent
                is Positive -> status.positive
                is Warning -> Color.Unspecified
                is Negative -> status.negative
                is Info -> status.info
            }
        }
}

/**
 * Represents a non-clickable icon to be displayed within an [OudsAlertMessage] or an [OudsInlineAlert].
 *
 * This class handles the creation of the icon from different sources like [Painter], [ImageVector], or [ImageBitmap].
 * An accessibility description is not required, as the alert's main `label` should provide the necessary context.
 */
open class OudsAlertIcon private constructor(
    graphicsObjectProvider: @Composable (OudsAlertIcon) -> Any,
    contentDescriptionProvider: @Composable (OudsAlertIcon) -> String
) :
    OudsComponentIcon<OudsAlertIcon.ExtraParameters, OudsAlertIcon>(
        ExtraParameters::class.java,
        graphicsObjectProvider,
        contentDescriptionProvider
    ) {

    /**
     * The default icon of an [OudsAlertMessage] or an [OudsInlineAlert].
     * This icon is non-clickable. A content description is only set for Warning and Error statuses to provide context. No content description is needed
     * for other statuses because the alert's `label` should provide the necessary context.
     */
    object Default : OudsAlertIcon(
        { icon ->
            with(icon.extraParameters) {
                status.defaultIconPainter.orElse {
                    error("No default icon for status ${status::class.simpleName}")
                }
            }
        },
        { icon -> icon.extraParameters.status.defaultIconContentDescription })

    /**
     * Creates an instance of [OudsAlertIcon].
     *
     * @property painter Painter of the icon.
     */
    constructor(painter: Painter) : this({ painter }, { "" })

    /**
     * Creates an instance of [OudsAlertIcon].
     *
     * @property imageVector Image vector of the icon.
     */
    constructor(imageVector: ImageVector) : this({ imageVector }, { "" })

    /**
     * Creates an instance of [OudsAlertIcon].
     *
     * @property bitmap Image bitmap of the icon.
     */
    constructor(bitmap: ImageBitmap) : this({ bitmap }, { "" })

    override val tint: Color?
        @Composable
        get() = extraParameters.tint

    @ConsistentCopyVisibility
    data class ExtraParameters internal constructor(
        internal val tint: Color,
        internal val status: OudsAlertStatus
    ) : OudsComponentContent.ExtraParameters()
}
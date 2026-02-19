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

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.theme.OudsThemeContract

/**
 * Inline alert is a lightweight UI element, placed in the content flow, that displays information, system feedback, status changes throughout short, prominent,
 * persistent and non actionable communication. Inline alert includes functional icon and semantic colour, and does not include a close button and/or action
 * link. Inline alert does not disappear and remains visible.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://r.orange.fr/r/S-ouds-doc-inline-alert)
 *
 * > Design version: 1.0.0
 *
 * @param label Label displayed in the inline alert. Main message that should be short, clear, and readable at a glance.
 * @param modifier [Modifier] applied to the inline alert.
 * @param status The status of the inline alert. Its background color and its icon color are based on this status.
 *   There are two types of statuses:
 *   - Non-functional statuses ([OudsInlineAlertStatus.Neutral] or [OudsInlineAlertStatus.Accent]) used for informational or decorative messages not tied to system logic.
 *   They are flexible in tone and visual expression, allowing the use of custom or brand-related (decorative) icons depending on context.
 *   These alerts help highlight content or support communication in a subtle, branded way.
 *   - Functional statuses communicate specific system statuses, results, or warnings tied directly to UX logic or user actions: [OudsInlineAlertStatus.Positive],
 *   [OudsInlineAlertStatus.Warning], [OudsInlineAlertStatus.Negative], [OudsInlineAlertStatus.Info].
 *   These alerts follow strict semantic conventions for icon, color, and tone — ensuring clear, accessible communication across all digital products.
 *   Each type has a dedicated, standardized icon that expresses its meaning clearly.
 *
 * @sample com.orange.ouds.core.component.samples.OudsInlineAlertNonFunctionalStatusSample
 *
 * @sample com.orange.ouds.core.component.samples.OudsInlineAlertFunctionalStatusSample
 */
@Composable
fun OudsInlineAlert(
    label: String,
    modifier: Modifier = Modifier,
    status: OudsInlineAlertStatus = OudsInlineAlertDefaults.Status
) {
    with(OudsTheme.componentsTokens.alert) {
        val scale = LocalConfiguration.current.fontScale
        Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(spaceColumnGap.value)) {
            status.icon.Content(
                modifier = Modifier
                    .size(sizeIcon.value * scale),
                extraParameters = OudsAlertIcon.ExtraParameters(
                    tint = status.assetColor(),
                    status = status
                )
            )
            Text(
                modifier = Modifier.widthIn(max = OudsTheme.sizes.maxWidth.type.label.large),
                text = label,
                color = status.textColor(),
                style = OudsTheme.typography.label.moderate.large
            )
        }
    }
}


/**
 * Default values for [OudsInlineAlert].
 */
object OudsInlineAlertDefaults {

    /**
     * Default status of an [OudsInlineAlert].
     */
    val Status: OudsInlineAlertStatus = OudsInlineAlertStatus.Neutral(OudsAlertIcon.Default)
}

/**
 * The status of an [OudsInlineAlert]. Each status is designed to convey a specific meaning and ensure clarity in communication.
 * It determines the text and the icon colors of the inline alert.
 * It also carries the icon to be displayed in the inline alert. Depending on the status, this icon can be customizable or be a status dedicated icon.
 *
 * @property icon The [OudsAlertIcon] to be displayed in the inline alert.
 */
sealed class OudsInlineAlertStatus(status: Companion.Status, val icon: OudsAlertIcon) : OudsAlertStatus(status) {

    /**
     * Neutral status can be used as a generic informative alert without semantic meaning or colour association. Suitable for a wide range of contexts — such as
     * tips, general information, or descriptive labels — where no specific feedback or urgency is required. Appropriate for help sections, dashboards, or
     * onboarding flows.
     *
     * @property icon The [OudsAlertIcon] to be displayed in the inline alert.
     */
    class Neutral(icon: OudsAlertIcon) : OudsInlineAlertStatus(Companion.Status.Neutral, icon) {
        override val defaultIconPainter
            @Composable
            get() = painterResource(OudsTheme.drawableResources.functional.socialAndEngagement.heartEmpty)
    }

    /**
     * Accent status uses brand colours to draw attention to promotional or highlighted information while remaining non-critical. Ideal for marketing content,
     * announcements, or feature highlights, where you want to subtly engage users without introducing functional semantics. Ideal for promotional banners,
     * product updates, or customer engagement moments.
     *
     * @property icon Icon to be displayed in the inline alert.
     */
    class Accent(icon: OudsAlertIcon) : OudsInlineAlertStatus(Companion.Status.Accent, icon)

    /**
     * Positive status confirms that an action was completed successfully.
     * It uses a green color and the standard success icon.
     */
    object Positive : OudsInlineAlertStatus(Companion.Status.Positive, OudsAlertIcon.Default)

    /**
     * Info status provides neutral information or updates about the system or account status.
     * It uses blue for neutrality and clarity and a dedicated default icon.
     */
    object Info : OudsInlineAlertStatus(Companion.Status.Info, OudsAlertIcon.Default)

    /**
     * Warning status draws attention to potential issues or upcoming changes.
     * It uses yellow to signal caution while remaining non-blocking and a dedicated default icon.
     */
    object Warning : OudsInlineAlertStatus(Companion.Status.Warning, OudsAlertIcon.Default)

    /**
     * Negative status communicates a critical issue or error that prevents the user from proceeding until it is resolved. These alerts remain visible until
     * the problem is fixed or dismissed by the user.
     * This status displays a dedicated default icon.
     */
    object Negative : OudsInlineAlertStatus(Companion.Status.Negative, OudsAlertIcon.Default)

    /**
     * The text color associated with this status.
     */
    @Composable
    fun textColor(): Color {
        return with(OudsTheme.colorScheme.content) {
            when (this@OudsInlineAlertStatus) {
                is Neutral, is Accent -> default
                is Positive -> status.positive
                is Warning -> status.warning
                is Negative -> status.negative
                is Info -> status.info
            }
        }
    }
}


@PreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsInlineAlert(@PreviewParameter(OudsInlineAlertPreviewParameterProvider::class) label: String) {
    PreviewOudsInlineAlert(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), label = label)
}

@Composable
internal fun PreviewOudsInlineAlert(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    label: String
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    val icon = OudsAlertIcon(Icons.Outlined.FavoriteBorder)
    Column {
        listOf(
            OudsInlineAlertStatus.Neutral(icon),
            OudsInlineAlertStatus.Accent(icon),
            OudsInlineAlertStatus.Positive,
            OudsInlineAlertStatus.Warning,
            OudsInlineAlertStatus.Negative,
            OudsInlineAlertStatus.Info
        ).forEach { status ->
            OudsInlineAlert(
                modifier = Modifier.padding(all = 10.dp),
                label = label,
                status = status
            )
        }
    }
}

internal class OudsInlineAlertPreviewParameterProvider :
    BasicPreviewParameterProvider<String>("Label", "Label with a very long name that need more than one line to be displayed.")
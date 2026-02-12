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

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.R
import com.orange.ouds.core.component.content.OudsComponentContent
import com.orange.ouds.core.component.content.OudsComponentIcon
import com.orange.ouds.core.theme.LocalDrawableResources
import com.orange.ouds.core.theme.LocalThemeSettings
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.takeUnlessHairline
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.OudsPreviewableComponent
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.theme.OudsThemeContract

/**
 * Alert message is a UI element that displays system feedback, status changes or required action; throughout detailed, prominent, persistent and actionable
 * communication. Alert message includes functional icon and semantic colour, and may include as well a close button and/or action link.
 * Alert Message does not disappear automatically and remains visible until dismissed or resolved by the user.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://r.orange.fr/r/S-ouds-doc-alert-message)
 *
 * > Design version: 1.0.0
 *
 * @param label Label displayed in the alert message. Main message that should be short, clear, and readable at a glance.
 * @param modifier [Modifier] applied to the alert message.
 * @param status The status of the alert message. Its background color and its icon color are based on this status.
 *   There are two types of statuses:
 *   - Non-functional statuses ([OudsAlertMessageStatus.Neutral] or [OudsAlertMessageStatus.Accent]) used for informational or decorative alert messages. They
 *   provide context or highlight content without implying a specific state, system event, or user action. These alerts are not tied to UX patterns such as
 *   success, error, or warning, and may use contextual or brand-related icons to enhance recognition or storytelling.
 *   - Functional statuses communicate specific system statuses, results, or user feedback: [OudsAlertMessageStatus.Positive], [OudsAlertMessageStatus.Warning],
 *   [OudsAlertMessageStatus.Negative], [OudsAlertMessageStatus.Info].
 *   Each variant conveys a clear semantic meaning and must always be paired with its dedicated functional icon to ensure clarity and accessibility.
 *   Use functional alerts to inform user about state changes, confirmations, or issues that are directly connected to system logic or user actions. These
 *   messages carry functional meaning and help guide user response or acknowledgment.
 * @param description Optional supplementary text in an alert message. Use only when additional detail or guidance is needed beyond the label. It should remain
 *   short, clear and scannable, helping the user to understand what happened and what he can do next.
 * @param onClose Callback invoked when the close button is clicked. If `null`, the close button is not displayed and the alert message remains visible until
 *   the context changes (e.g., the issue is resolved, the screen is refreshed). Otherwise, the alert message is dismissable and includes a close button,
 *   allowing the user to dismiss it when he has acknowledged the message.
 *   Some alerts must remain visible to ensure user is aware of important information; others can be closed to reduce visual clutter.
 * @param link An optional link to be displayed in the alert message. It can be used to trigger an action.
 * @param bulletList An optional list of bullet points to be displayed in the alert message following the label or the optional [description].
 *   Add this list when you need to highlight multiple points, such as service features, plan details, or next steps. Each bullet should be short and written
 *   as a clear phrase or fragment — avoid long sentences or complex structures.
 *
 * @sample com.orange.ouds.core.component.samples.OudsAlertMessageSample
 *
 * @sample com.orange.ouds.core.component.samples.OudsAlertMessageFunctionalWithTopEndLinkSample
 */
@Composable
fun OudsAlertMessage(
    label: String,
    modifier: Modifier = Modifier,
    status: OudsAlertMessageStatus = OudsAlertMessageDefaults.Status,
    description: String? = null,
    onClose: (() -> Unit)? = null,
    link: OudsAlertMessageLink? = null,
    bulletList: List<String>? = null
) {
    with(OudsTheme.componentsTokens.alert) {
        val scale = LocalConfiguration.current.fontScale
        val borderRadius = if (LocalThemeSettings.current.roundedCornerAlertMessages == true) borderRadiusRounded else borderRadiusDefault
        val shape = RoundedCornerShape(borderRadius.value)
        val hasCloseButton = onClose != null
        Row(
            modifier = modifier
                .widthIn(min = sizeMinWidth.dp)
                .heightIn(min = if (link != null && link.position == OudsAlertMessageLinkPosition.Bottom) sizeMinHeightBottomActionPlacement.dp else sizeMinHeight.value)
                .background(color = status.backgroundColor(), shape = shape)
                .padding(start = spacePaddingInline.value, end = if (hasCloseButton) 0.dp else spacePaddingInline.value)
                .run {
                    borderWidth.value.takeUnlessHairline?.let {
                        border(width = it, color = status.borderColor(), shape = shape)
                    } ?: this
                },
            horizontalArrangement = Arrangement.spacedBy(spaceColumnGap.value)
        ) {
            status.icon?.Content(
                modifier = Modifier
                    .padding(top = spacePaddingBlock.value)
                    .size(sizeIcon.value * scale),
                extraParameters = OudsAlertMessageIcon.ExtraParameters(
                    tint = status.assetColor()
                )
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = spacePaddingBlock.value)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(spaceRowGap.value)) {
                    Text(
                        modifier = Modifier.widthIn(max = OudsTheme.sizes.maxWidth.type.label.large),
                        text = label,
                        color = OudsTheme.colorScheme.content.default,
                        style = OudsTheme.typography.label.moderate.large
                    )
                    description?.let {
                        Text(
                            modifier = Modifier.widthIn(max = OudsTheme.sizes.maxWidth.type.label.medium),
                            text = description,
                            color = OudsTheme.colorScheme.content.muted,
                            style = OudsTheme.typography.label.default.medium
                        )
                    }
                    bulletList?.let { list ->
                        Column(verticalArrangement = Arrangement.spacedBy(spaceRowGapBullet.value)) {
                            list.forEach { label ->
                                if (label.isNotBlank()) OudsAlertMessageBulletListItem(label = label)
                            }
                        }
                    }
                }
                if (link != null && link.position == OudsAlertMessageLinkPosition.Bottom) {
                    OudsLink(
                        modifier = Modifier.padding(top = spaceRowGapAction.value),
                        label = link.label,
                        onClick = link.onClick
                    )
                }
            }

            val hasTopEndLink = link?.position == OudsAlertMessageLinkPosition.TopEnd
            if (hasCloseButton || hasTopEndLink) {
                Row(horizontalArrangement = Arrangement.spacedBy(spaceColumnGapAction.value)) {
                    if (hasTopEndLink) {
                        link.Content()
                    }
                    onClose?.let {
                        OudsButton(
                            modifier = Modifier.clip(shape = shape),
                            icon = OudsButtonIcon(
                                painter = painterResource(LocalDrawableResources.current.component.button.expurge),
                                contentDescription = stringResource(R.string.core_alertMessage_close_a11y)
                            ),
                            onClick = onClose,
                            appearance = OudsButtonAppearance.Minimal
                        )
                    }
                }
            }
        }
    }
}

/**
 * Default values for [OudsAlertMessage].
 */
object OudsAlertMessageDefaults {
    /**
     * Default status of an [OudsAlertMessage].
     */
    val Status = OudsAlertMessageStatus.Positive()

    /**
     * Default position of an [OudsAlertMessage] action link.
     */
    val LinkPosition = OudsAlertMessageLinkPosition.Bottom
}

/**
 * Link to be displayed in an [OudsAlertMessage].
 *
 * @param label Text label of the link.
 * @param onClick Callback invoked when the link is clicked.
 * @param position [OudsAlertMessageLinkPosition] of the link within the alert message.
 */
data class OudsAlertMessageLink(
    val label: String,
    val onClick: () -> Unit,
    val position: OudsAlertMessageLinkPosition = OudsAlertMessageDefaults.LinkPosition
) {
    @Composable
    fun Content(modifier: Modifier = Modifier) {
        with(OudsTheme.componentsTokens.alert) {
            OudsLink(
                modifier = modifier,
                label = label,
                onClick = onClick
            )
        }
    }
}

/**
 * The position of an [OudsAlertMessageLink] in the alert message.
 */
enum class OudsAlertMessageLinkPosition {
    /**
     * The link is displayed at the bottom of the alert message below the main message content.
     * Recommended for mobile or narrow layouts, or when the text spans multiple lines. This vertical structure improves clarity and ensures the action remains
     * visible after the message is read.
     */
    Bottom,

    /**
     * The link is displayed at the top-end corner of the alert message.
     * Best suited for wider layouts or short, single-line alerts where horizontal alignment keeps content compact and balanced.
     */
    TopEnd
}

/**
 * The status of an [OudsAlertMessage]. Each status is designed to convey a specific meaning and ensure clarity in communication.
 * It determines the background and the icon colors of the alert message.
 * It also carries the optional icon to be displayed in the alert message. Depending on the status, this icon can be customizable or be a status dedicated icon.
 */
sealed class OudsAlertMessageStatus {

    internal abstract val icon: OudsAlertMessageIcon?
        @Composable get

    /**
     * Neutral status can be used as a generic informative alert without semantic meaning or colour association. Suitable for a wide range of contexts — such as
     * tips, general information, or descriptive labels — where no specific feedback or urgency is required. Appropriate for help sections, dashboards, or
     * onboarding flows.
     *
     * @param icon Optional icon to be displayed in the alert message. Pass `null` if no icon is needed.
     */
     class Neutral(icon: OudsAlertMessageIcon? = null) : OudsAlertMessageStatus() {

        private val _icon = icon
        override val icon: OudsAlertMessageIcon?
            @Composable
            get() = _icon
    }

    /**
     * Accent status uses brand colours to draw attention to promotional or highlighted information while remaining non-critical. Ideal for marketing content,
     * announcements, or feature highlights, where you want to subtly engage users without introducing functional semantics. Ideal for promotional banners,
     * product updates, or customer engagement moments.
     *
     * @param icon Optional icon to be displayed in the alert message. Pass `null` if no icon is needed.
     */
     class Accent(icon: OudsAlertMessageIcon? = null) : OudsAlertMessageStatus() {

        private val _icon = icon
        override val icon: OudsAlertMessageIcon?
            @Composable
            get() = _icon
    }

    /**
     * Positive status indicates that a task or process has been completed successfully. These alerts reassure users and confirm that no further action is needed.
     * This status displays a dedicated default icon.
     *
     * @param showIcon Controls whether the icon should be displayed or not. `true` displays the default positive icon.
     */
    data class Positive(val showIcon: Boolean = true) : OudsAlertMessageStatus() {
        override val icon: OudsAlertMessageIcon?
            @Composable
            get() = if (showIcon) {
                OudsAlertMessageIcon(painter = painterResource(OudsTheme.drawableResources.component.alert.tickConfirmationFill))
            } else {
                null
            }
    }

    /**
     * Info status is used to share neutral system information or service updates that do not require immediate action. Ideal for background processes or status
     * messages where users simply need to stay informed.
     * This status displays a dedicated default icon.
     *
     * @param showIcon Controls whether the icon should be displayed or not. `true` displays the default info icon.
     */
    data class Info(val showIcon: Boolean = true) : OudsAlertMessageStatus() {
        override val icon: OudsAlertMessageIcon?
            @Composable
            get() = if (showIcon) {
                OudsAlertMessageIcon(painter = painterResource(OudsTheme.drawableResources.component.alert.infoFill))
            } else {
                null
            }
    }

    /**
     * Used to draw attention to potential issues or upcoming changes that might affect the user’s service or experience. Warnings encourage awareness but
     * typically do not block actions.
     * This status displays a dedicated default icon.
     *
     * @param showIcon Controls whether the icon should be displayed or not. `true` displays the default warning icon.
     */
    data class Warning(val showIcon: Boolean = true) : OudsAlertMessageStatus() {
        override val icon: OudsAlertMessageIcon?
            @Composable
            get() = if (showIcon) {
                OudsAlertMessageIcon(painter = painterResource(id = OudsTheme.drawableResources.component.alert.warningExternalShape))
            } else {
                null
            }
    }

    /**
     * Negative status communicates a critical issue or error that prevents the user from proceeding until it is resolved. These alerts remain visible until
     * the problem is fixed or dismissed by the user.
     * This status displays a dedicated default icon.
     *
     * @param showIcon Controls whether the icon should be displayed or not. `true` displays the default negative icon.
     */
    data class Negative(val showIcon: Boolean = true) : OudsAlertMessageStatus() {
        override val icon: OudsAlertMessageIcon?
            @Composable
            get() = if (showIcon) {
                OudsAlertMessageIcon(painter = painterResource(OudsTheme.drawableResources.component.alert.importantFill))
            } else {
                null
            }
    }

    /**
     * The color associated with this status.
     */
    @Composable
    fun backgroundColor(): Color {
        return when (this) {
            is Neutral -> OudsTheme.colorScheme.surface.secondary
            is Accent -> OudsTheme.colorScheme.surface.status.accent.muted
            is Positive -> OudsTheme.colorScheme.surface.status.positive.muted
            is Warning -> OudsTheme.colorScheme.surface.status.warning.muted
            is Negative -> OudsTheme.colorScheme.surface.status.negative.muted
            is Info -> OudsTheme.colorScheme.surface.status.info.muted
        }
    }

    @Composable
    internal fun assetColor(): Color {
        return with(OudsTheme.colorScheme.content) {
            when (this@OudsAlertMessageStatus) {
                is Neutral -> default
                is Accent -> status.accent
                is Positive -> status.positive
                is Warning -> status.warning
                is Negative -> status.negative
                is Info -> status.info
            }
        }
    }

    @Composable
    internal fun borderColor(): Color {
        return with(OudsTheme.colorScheme.border) {
            when (this@OudsAlertMessageStatus) {
                is Neutral -> default
                is Accent -> status.accent
                is Positive -> status.positive
                is Warning -> status.warning
                is Negative -> status.negative
                is Info -> status.info
            }
        }
    }
}

/**
 * Represents a non-clickable icon to be displayed within an [OudsAlertMessage].
 *
 * This class handles the creation of the icon from different sources like [Painter], [ImageVector], or [ImageBitmap].
 * An accessibility description is not required as the alert's main `label` should provide the necessary context.
 */
class OudsAlertMessageIcon private constructor(graphicsObject: Any) :
    OudsComponentIcon<OudsAlertMessageIcon.ExtraParameters, OudsAlertMessageIcon>(
        OudsAlertMessageIcon.ExtraParameters::class.java,
        graphicsObject,
        ""
    ) {

    /**
     * Creates an instance of [OudsAlertMessageIcon].
     *
     * @param painter Painter of the icon.
     */
    constructor(painter: Painter) : this(painter as Any)

    /**
     * Creates an instance of [OudsAlertMessageIcon].
     *
     * @param imageVector Image vector of the icon.
     */
    constructor(imageVector: ImageVector) : this(imageVector as Any)

    /**
     * Creates an instance of [OudsAlertMessageIcon].
     *
     * @param bitmap Image bitmap of the icon.
     */
    constructor(bitmap: ImageBitmap) : this(bitmap as Any)

    override val tint: Color?
        @Composable
        get() = extraParameters.tint

    @ConsistentCopyVisibility
    data class ExtraParameters internal constructor(
        internal val tint: Color
    ) : OudsComponentContent.ExtraParameters()
}

@Composable
private fun OudsAlertMessageBulletListItem(label: String) {
    val scale = LocalConfiguration.current.fontScale
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.spacedBy(OudsTheme.componentsTokens.bulletList.spaceColumnGapBodyMedium.value)
    ) {
        Box(
            modifier = Modifier
                .width(OudsTheme.sizes.icon.withLabel.medium.sizeMedium * scale)
                .heightIn(max = OudsTheme.typography.label.default.medium.lineHeight.value.dp * scale)
                .fillMaxHeight(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Icon(
                modifier = Modifier
                    .size(OudsTheme.sizes.icon.withLabel.medium.sizeSmall * scale)
                    .clearAndSetSemantics {},
                painter = painterResource(OudsTheme.drawableResources.component.bulletList.level0),
                contentDescription = null,
                tint = OudsTheme.colorScheme.content.muted
            )
        }
        Text(
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentHeight() // Allows to center the text vertically when its height is smaller than the row height
                .widthIn(max = OudsTheme.sizes.maxWidth.type.label.medium),
            text = label,
            style = OudsTheme.typography.label.default.medium,
            color = OudsTheme.colorScheme.content.muted
        )
    }
}

@Preview(name = "Light", heightDp = OudsPreviewableComponent.AlertMessage.PreviewHeightDp)
@Preview(name = "Dark", uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL, heightDp = OudsPreviewableComponent.AlertMessage.PreviewHeightDp)
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsAlertMessage(@PreviewParameter(OudsAlertMessagePreviewParameterProvider::class) parameter: OudsAlertMessagePreviewParameter) {
    PreviewOudsAlertMessage(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsAlertMessage(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
    parameter: OudsAlertMessagePreviewParameter
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    val label = "Label"
    with(parameter) {
        Column {
            statuses.forEach { status ->
                OudsAlertMessage(
                    modifier = Modifier.padding(all = 10.dp),
                    label = label,
                    status = status,
                    description = description,
                    onClose = onClose,
                    link = link,
                    bulletList = bulletList
                )
            }
        }
    }
}

internal data class OudsAlertMessagePreviewParameter(
    val statuses: List<OudsAlertMessageStatus> = listOf(
        OudsAlertMessageStatus.Neutral(),
        OudsAlertMessageStatus.Accent(),
        OudsAlertMessageStatus.Positive(),
        OudsAlertMessageStatus.Warning(),
        OudsAlertMessageStatus.Negative(),
        OudsAlertMessageStatus.Info()
    ),
    val description: String? = null,
    val onClose: (() -> Unit)? = null,
    val link: OudsAlertMessageLink? = null,
    val bulletList: List<String>? = null
)

internal class OudsAlertMessagePreviewParameterProvider :
    BasicPreviewParameterProvider<OudsAlertMessagePreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsAlertMessagePreviewParameter>
    get() {
        val icon = OudsAlertMessageIcon(Icons.Outlined.FavoriteBorder)
        val statusesWithIcon = listOf(
            OudsAlertMessageStatus.Neutral(icon = icon),
            OudsAlertMessageStatus.Accent(icon = icon),
            OudsAlertMessageStatus.Positive(),
            OudsAlertMessageStatus.Warning(),
            OudsAlertMessageStatus.Negative(),
            OudsAlertMessageStatus.Info()
        )
        val description = "Here is a long description that need two lines to be displayed."
        val linkLabel = "Action"

        return listOf(
            OudsAlertMessagePreviewParameter(
                statusesWithIcon,
                description = description,
                link = OudsAlertMessageLink(linkLabel, onClick = {}, position = OudsAlertMessageLinkPosition.TopEnd),
                bulletList = listOf("Bullet 1", "Bullet 3 is a bullet with a very long label to test the wrapping", "Bullet 3")
            ),
            OudsAlertMessagePreviewParameter(
                statusesWithIcon,
                description = description,
                onClose = {},
                link = OudsAlertMessageLink(linkLabel, onClick = {})
            ),
        )
    }

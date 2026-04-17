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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.R
import com.orange.ouds.core.component.common.text.OudsAnnotatedAlertMessageBulletListLabel
import com.orange.ouds.core.component.common.text.OudsAnnotatedAlertMessageDescription
import com.orange.ouds.core.component.common.text.OudsAnnotatedString
import com.orange.ouds.core.component.common.text.OudsLinkAnnotation
import com.orange.ouds.core.component.common.text.buildOudsAnnotatedAlertMessageBulletListLabel
import com.orange.ouds.core.component.common.text.buildOudsAnnotatedAlertMessageDescription
import com.orange.ouds.core.component.common.text.withLink
import com.orange.ouds.core.component.common.text.withStrong
import com.orange.ouds.core.component.content.OudsComponentContent
import com.orange.ouds.core.theme.LocalDrawableResources
import com.orange.ouds.core.theme.LocalThemeSettings
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.takeUnlessHairline
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.OudsPreviewDevice
import com.orange.ouds.core.utilities.OudsPreviewLightDark
import com.orange.ouds.core.utilities.OudsPreviewableComponent
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.theme.OudsThemeContract

/**
 * Alert message is a UI element that displays system feedback, status changes or required action; throughout detailed, prominent, persistent and actionable
 * communication. Alert message includes functional icon and semantic colour, and may include as well a close button and/or action link.
 * Alert Message does not disappear automatically and remains visible until dismissed or resolved by the user.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://r.orange.fr/r/S-ouds-doc-alert-message)
 *
 * > Design version: 1.1.0
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
 * @param actionLink An optional link to be displayed in the alert message. It can be used to trigger an action.
 * @param bulletList An optional list of bullet points to be displayed in the alert message following the label or the optional [description].
 *   Add this list when you need to highlight multiple points, such as service features, plan details, or next steps. Each bullet should be short and written
 *   as a clear phrase or fragment — avoid long sentences or complex structures.
 *
 * @sample com.orange.ouds.core.component.samples.OudsAlertMessageSample
 *
 * @sample com.orange.ouds.core.component.samples.OudsAlertMessageFunctionalWithTopEndActionLinkSample
 */
@Composable
fun OudsAlertMessage(
    label: String,
    modifier: Modifier = Modifier,
    status: OudsAlertMessageStatus = OudsAlertMessageDefaults.Status,
    description: String? = null,
    onClose: (() -> Unit)? = null,
    actionLink: OudsAlertMessageActionLink? = null,
    bulletList: List<String>? = null
) {
    OudsAlertMessage(
        label = label,
        modifier = modifier,
        status = status,
        description = description,
        annotatedDescription = null,
        onClose = onClose,
        actionLink = actionLink,
        bulletList = bulletList,
        annotatedBulletList = null
    )
}

/**
 * Alert message is a UI element that displays system feedback, status changes or required action; throughout detailed, prominent, persistent and actionable
 * communication. Alert message includes functional icon and semantic colour, and may include as well a close button and/or action link.
 * Alert Message does not disappear automatically and remains visible until dismissed or resolved by the user.
 *
 * > Design guidelines: [unified-design-system.orange.com](https://r.orange.fr/r/S-ouds-doc-alert-message)
 *
 * > Design version: 1.1.0
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
 * @param description Annotated supplementary text in an alert message. Use only when additional detail or guidance is needed beyond the label. It should remain
 *   short, clear and scannable, helping the user to understand what happened and what he can do next.
 * @param onClose Callback invoked when the close button is clicked. If `null`, the close button is not displayed and the alert message remains visible until
 *   the context changes (e.g., the issue is resolved, the screen is refreshed). Otherwise, the alert message is dismissable and includes a close button,
 *   allowing the user to dismiss it when he has acknowledged the message.
 *   Some alerts must remain visible to ensure user is aware of important information; others can be closed to reduce visual clutter.
 * @param actionLink An optional link to be displayed in the alert message. It can be used to trigger an action.
 * @param bulletList A list of annotated bullet points to be displayed in the alert message following the label or the optional [description].
 *   Add this list when you need to highlight multiple points, such as service features, plan details, or next steps. Each bullet should be short and written
 *   as a clear phrase or fragment — avoid long sentences or complex structures.
 *
 * @sample com.orange.ouds.core.component.samples.OudsAlertMessageSample
 *
 * @sample com.orange.ouds.core.component.samples.OudsAlertMessageFunctionalWithTopEndActionLinkSample
 */
@Composable
fun OudsAlertMessage(
    label: String,
    modifier: Modifier = Modifier,
    status: OudsAlertMessageStatus = OudsAlertMessageDefaults.Status,
    description: OudsAnnotatedAlertMessageDescription,
    onClose: (() -> Unit)? = null,
    actionLink: OudsAlertMessageActionLink? = null,
    bulletList: List<OudsAnnotatedAlertMessageBulletListLabel>?
) {
    OudsAlertMessage(
        label = label,
        modifier = modifier,
        status = status,
        description = null,
        annotatedDescription = description,
        onClose = onClose,
        actionLink = actionLink,
        bulletList = null,
        annotatedBulletList = bulletList
    )
}

@Composable
private fun OudsAlertMessage(
    label: String,
    modifier: Modifier = Modifier,
    status: OudsAlertMessageStatus = OudsAlertMessageDefaults.Status,
    description: String? = null,
    annotatedDescription: OudsAnnotatedAlertMessageDescription? = null,
    onClose: (() -> Unit)? = null,
    actionLink: OudsAlertMessageActionLink? = null,
    bulletList: List<String>? = null,
    annotatedBulletList: List<OudsAnnotatedAlertMessageBulletListLabel>? = null
) {
    with(OudsTheme.componentsTokens.alert) {
        val scale = LocalConfiguration.current.fontScale
        val borderRadius = if (LocalThemeSettings.current.roundedCornerAlertMessages == true) borderRadiusRounded else borderRadiusDefault
        val shape = RoundedCornerShape(borderRadius.value)
        val hasCloseButton = onClose != null
        val hasActionLink = actionLink != null && actionLink.label.isNotBlank()
        Row(
            modifier = modifier
                .widthIn(min = sizeMinWidth.dp)
                .heightIn(min = if (hasActionLink && actionLink.position == OudsAlertMessageActionLinkPosition.Bottom) sizeMinHeightBottomActionPlacement.dp else sizeMinHeight.value)
                .background(color = status.backgroundColor, shape = shape)
                .clip(shape)
                .run {
                    borderWidth.value.takeUnlessHairline?.let {
                        border(width = it, color = status.borderColor, shape = shape)
                    } ?: this
                }
                .semantics(mergeDescendants = true) {}
                .padding(start = spacePaddingInline.value, end = if (hasCloseButton) 0.dp else spacePaddingInline.value),
            horizontalArrangement = Arrangement.spacedBy(spaceColumnGap.value)
        ) {
            status.icon?.Content(
                modifier = Modifier
                    .padding(top = spacePaddingBlock.value)
                    .size(sizeIcon.value * scale),
                extraParameters = OudsAlertIcon.ExtraParameters(
                    tint = status.assetColor,
                    status = status.value
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
                        color = status.contentColor,
                        style = OudsTheme.typography.label.moderate.large
                    )
                    val descriptionModifier = Modifier.widthIn(max = OudsTheme.sizes.maxWidth.type.label.medium)
                    val descriptionColor = status.contentColor
                    val descriptionStyle = OudsTheme.typography.label.default.medium
                    if (annotatedDescription != null) {
                        Text(modifier = descriptionModifier, text = annotatedDescription.annotatedString(), color = descriptionColor, style = descriptionStyle)
                    } else if (description != null) {
                        Text(modifier = descriptionModifier, text = description, color = descriptionColor, style = descriptionStyle)
                    }
                    annotatedBulletList.orElse { bulletList }?.let { list ->
                        Column(verticalArrangement = Arrangement.spacedBy(spaceRowGapBullet.value)) {
                            list.forEach { label ->
                                if (label.isNotBlank()) OudsAlertMessageBulletListItem(label = label, color = status.contentColor)
                            }
                        }
                    }
                }
                if (hasActionLink && actionLink.position == OudsAlertMessageActionLinkPosition.Bottom) {
                    OudsLink(
                        modifier = Modifier.padding(top = spaceRowGapAction.value),
                        label = actionLink.label,
                        onClick = actionLink.onClick
                    )
                }
            }

            val hasTopEndActionLink = hasActionLink && actionLink.position == OudsAlertMessageActionLinkPosition.TopEnd
            if (hasCloseButton || hasTopEndActionLink) {
                Row(horizontalArrangement = Arrangement.spacedBy(spaceColumnGapAction.value)) {
                    if (hasTopEndActionLink) {
                        actionLink.Content()
                    }
                    onClose?.let {
                        OudsButton(
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
    val Status = OudsAlertMessageStatus.Positive

    /**
     * Default position of an [OudsAlertMessage] action link.
     */
    val ActionLinkPosition = OudsAlertMessageActionLinkPosition.Bottom
}

/**
 * Action link to be displayed in an [OudsAlertMessage].
 *
 * @param label Text label of the link.
 * @param onClick Callback invoked when the link is clicked.
 * @param position [OudsAlertMessageActionLinkPosition] of the link within the alert message.
 */
data class OudsAlertMessageActionLink(
    val label: String,
    val onClick: () -> Unit,
    val position: OudsAlertMessageActionLinkPosition = OudsAlertMessageDefaults.ActionLinkPosition
) : OudsComponentContent<Nothing>(Nothing::class.java) {
    @Composable
    override fun Content(modifier: Modifier) {
        OudsLink(
            modifier = modifier,
            label = label,
            onClick = onClick
        )
    }
}

/**
 * The position of an [OudsAlertMessageActionLink] in the alert message.
 */
enum class OudsAlertMessageActionLinkPosition {
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
 * It also defines the icon to be displayed in the alert message. For non-functional statuses ([Neutral] and [Accent]), a custom icon can be provided optionally.
 * For functional statuses, a dedicated, non-overridable icon is used to guarantee semantic consistency.
 *
 * @property value The [OudsAlertStatus] associated with this status.
 * @property icon The [OudsAlertIcon] to be displayed in the alert message, or `null` if there is no icon.
 */
sealed class OudsAlertMessageStatus(internal val value: OudsAlertStatus, val icon: OudsAlertIcon? = null) {

    /**
     * Neutral status can be used as a generic informative alert without semantic meaning or colour association.
     * Suitable for a wide range of contexts — such as tips, general information, or descriptive labels — where no specific feedback or urgency is required.
     * Appropriate for help sections, dashboards, or onboarding flows.
     *
     * @property icon The optional [OudsAlertIcon] to be displayed at the start of the alert message.
     */
    class Neutral(icon: OudsAlertIcon? = null) : OudsAlertMessageStatus(OudsAlertStatus.Neutral(), icon)

    /**
     * Accent status uses brand colours to draw attention to promotional or highlighted information while remaining non-critical.
     * Ideal for marketing content, announcements, or feature highlights, where you want to subtly engage users without introducing functional semantics.
     * Ideal for promotional banners, product updates, or customer engagement moments.
     *
     * @property icon The optional [OudsAlertIcon] to be displayed at the start of the alert message.
     */
    class Accent(icon: OudsAlertIcon? = null) : OudsAlertMessageStatus(OudsAlertStatus.Accent(), icon)

    /**
     * Negative status communicates a critical issue or error that prevents the user from proceeding until it is resolved.
     * These alerts remain visible until the problem is fixed or dismissed by the user.
     * This status displays a dedicated default icon.
     */
    data object Negative : OudsAlertMessageStatus(OudsAlertStatus.Negative(), OudsAlertIcon.Default)

    /**
     * Positive status indicates that a task or process has been completed successfully.
     * These alerts reassure users and confirm that no further action is needed.
     * This status displays a dedicated default icon.
     */
    data object Positive : OudsAlertMessageStatus(OudsAlertStatus.Positive(), OudsAlertIcon.Default)

    /**
     * Info status is used to share neutral system information or service updates that do not require immediate action.
     * Ideal for background processes or status messages where users simply need to stay informed.
     * This status displays a dedicated default icon.
     */
    data object Info : OudsAlertMessageStatus(OudsAlertStatus.Info(), OudsAlertIcon.Default)

    /**
     * Used to draw attention to potential issues or upcoming changes that might affect the user’s service or experience.
     * Warnings encourage awareness but typically do not block actions.
     * This status displays a dedicated default icon.
     */
    data object Warning : OudsAlertMessageStatus(OudsAlertStatus.Warning(), OudsAlertIcon.Default)

    internal val assetColor
        @Composable
        get() = value.assetColor

    /**
     * The background color associated with this status.
     */
    val backgroundColor
        @Composable
        get() = with(OudsTheme.colorScheme.surface) {
            when (this@OudsAlertMessageStatus) {
                is Neutral -> secondary
                is Accent -> status.accent.muted
                is Positive -> status.positive.muted
                is Warning -> status.warning.muted
                is Negative -> status.negative.muted
                is Info -> status.info.muted
            }
        }

    /**
     * The content color associated with this status.
     */
    val contentColor
        @Composable
        get() = with(OudsTheme.colorScheme.content) {
            when (this@OudsAlertMessageStatus) {
                is Neutral -> default
                is Accent -> onStatus.accent.muted
                is Positive -> onStatus.positive.muted
                is Warning -> onStatus.warning.muted
                is Negative -> onStatus.negative.muted
                is Info -> onStatus.info.muted
            }
        }

    internal val borderColor
        @Composable
        get() = with(OudsTheme.colorScheme.border) {
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
private fun OudsAlertMessageBulletListItem(label: CharSequence, color: Color) {
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
                    .size(OudsTheme.sizes.icon.withLabel.medium.sizeSmall * scale),
                painter = painterResource(OudsTheme.drawableResources.component.bulletList.level0),
                contentDescription = null,
                tint = color
            )
        }
        val modifier = Modifier
            .fillMaxHeight()
            .wrapContentHeight() // Allows to center the text vertically when its height is smaller than the row height
            .widthIn(max = OudsTheme.sizes.maxWidth.type.label.medium)
        val style = OudsTheme.typography.label.default.medium
        when (label) {
            is OudsAnnotatedString<*> -> Text(modifier = modifier, text = label.annotatedString(), color = color, style = style)
            is String -> Text(modifier = modifier, text = label, color = color, style = style)
        }
    }
}

@Preview(name = "Light", heightDp = OudsPreviewableComponent.AlertMessage.Default.PreviewHeightDp, device = OudsPreviewDevice)
@Preview(
    name = "Dark",
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL,
    heightDp = OudsPreviewableComponent.AlertMessage.Default.PreviewHeightDp,
    device = OudsPreviewDevice
)
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
    with(parameter) {
        val icon = if (hasIcon) OudsAlertIcon(Icons.Outlined.FavoriteBorder) else null
        Column {
            listOf(
                OudsAlertMessageStatus.Neutral(icon),
                OudsAlertMessageStatus.Accent(icon),
                OudsAlertMessageStatus.Negative,
                OudsAlertMessageStatus.Positive,
                OudsAlertMessageStatus.Info,
                OudsAlertMessageStatus.Warning
            ).forEach { status ->
                OudsAlertMessage(
                    modifier = Modifier.padding(all = 10.dp),
                    label = "Label",
                    status = status,
                    description = description,
                    onClose = onClose,
                    actionLink = actionLink,
                    bulletList = bulletList
                )
            }
        }
    }
}

@OudsPreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsAlertMessageWithRichText() {
    PreviewOudsAlertMessageWithRichText(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme())
}

@Composable
internal fun PreviewOudsAlertMessageWithRichText(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    val description = buildOudsAnnotatedAlertMessageDescription {
        append("Here is a description that contains a ")
        withStrong { append("strong") }
        append(" text and a ")
        withLink(OudsLinkAnnotation.Clickable("")) { append("link") }
    }
    val bulletList = listOf(
        buildOudsAnnotatedAlertMessageBulletListLabel { append("Bullet 1") },
        buildOudsAnnotatedAlertMessageBulletListLabel {
            append("Bullet 2 is a bullet that contains a ")
            withStrong { append("strong") }
            append(" text and a ")
            withLink(OudsLinkAnnotation.Clickable("")) { append("link") }
        },
        buildOudsAnnotatedAlertMessageBulletListLabel { append("Bullet 3") }
    )
    OudsAlertMessage(
        modifier = Modifier.padding(all = 10.dp),
        label = "Label",
        description = description,
        bulletList = bulletList
    )
}

internal data class OudsAlertMessagePreviewParameter(
    val hasIcon: Boolean = false,
    val description: String? = null,
    val onClose: (() -> Unit)? = null,
    val actionLink: OudsAlertMessageActionLink? = null,
    val bulletList: List<String>? = null
)

internal class OudsAlertMessagePreviewParameterProvider :
    BasicPreviewParameterProvider<OudsAlertMessagePreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsAlertMessagePreviewParameter>
    get() {
        val description = "Here is a long description that need two lines to be displayed."
        val actionLinkLabel = "Action"
        return listOf(
            OudsAlertMessagePreviewParameter(
                hasIcon = true,
                description = description,
                actionLink = OudsAlertMessageActionLink(actionLinkLabel, onClick = {}, position = OudsAlertMessageActionLinkPosition.TopEnd),
                bulletList = listOf("Bullet 1", "Bullet 2 is a bullet with a very long label to test the wrapping", "Bullet 3")
            ),
            OudsAlertMessagePreviewParameter(
                hasIcon = true,
                description = description,
                onClose = {},
                actionLink = OudsAlertMessageActionLink(actionLinkLabel, onClick = {})
            ),
            OudsAlertMessagePreviewParameter(
                onClose = {},
                actionLink = OudsAlertMessageActionLink(actionLinkLabel, onClick = {}, position = OudsAlertMessageActionLinkPosition.TopEnd)
            ),
        )
    }

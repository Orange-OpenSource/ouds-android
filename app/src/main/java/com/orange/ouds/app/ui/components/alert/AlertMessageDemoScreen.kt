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

package com.orange.ouds.app.ui.components.alert

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.annotatedStringArgument
import com.orange.ouds.app.ui.components.iconArgument
import com.orange.ouds.app.ui.components.labelArgument
import com.orange.ouds.app.ui.components.onClickArgument
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.LocalThemeDrawableResources
import com.orange.ouds.app.ui.utilities.ThemeDrawableResources
import com.orange.ouds.app.ui.utilities.appendHtml
import com.orange.ouds.app.ui.utilities.composable.AppPreview
import com.orange.ouds.app.ui.utilities.composable.CustomizationDropdownMenu
import com.orange.ouds.app.ui.utilities.composable.CustomizationDropdownMenuItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChip
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChips
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextInput
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.app.ui.utilities.nestedName
import com.orange.ouds.app.ui.utilities.rememberUntintedIconPainter
import com.orange.ouds.core.component.OudsAlertIcon
import com.orange.ouds.core.component.OudsAlertMessage
import com.orange.ouds.core.component.OudsAlertMessageActionLink
import com.orange.ouds.core.component.OudsAlertMessageActionLinkPosition
import com.orange.ouds.core.component.OudsAlertMessageStatus
import com.orange.ouds.core.component.common.text.OudsAnnotatedAlertMessageBulletListLabel
import com.orange.ouds.core.component.common.text.OudsAnnotatedAlertMessageDescription
import com.orange.ouds.core.component.common.text.buildOudsAnnotatedAlertMessageBulletListLabel
import com.orange.ouds.core.component.common.text.buildOudsAnnotatedAlertMessageDescription
import com.orange.ouds.foundation.extensions.toSentenceCase
import com.orange.ouds.foundation.extensions.tryOrNull
import com.orange.ouds.theme.OudsVersion
import kotlin.reflect.full.createInstance

@Composable
fun AlertMessageDemoScreen() {
    val state = rememberAlertMessageDemoState()
    val themeDrawableResources = LocalThemeDrawableResources.current
    DemoScreen(
        description = stringResource(id = R.string.app_components_alert_alertMessage_description_text),
        bottomSheetContent = { AlertMessageDemoBottomSheetContent(state = state) },
        codeSnippet = { alertMessageDemoCodeSnippet(state = state, themeDrawableResources = themeDrawableResources) },
        demoContent = { AlertMessageDemoContent(state = state) },
        version = OudsVersion.Component.AlertMessage
    )
}

@Composable
private fun AlertMessageDemoBottomSheetContent(state: AlertMessageDemoState) {
    with(state) {
        val statuses = if (LocalInspectionMode.current) {
            // Fixes a bug where calling sealedSubclasses returns an empty list in Compose previews
            // See https://issuetracker.google.com/issues/240601093
            listOf(
                OudsAlertMessageStatus.Accent(),
                OudsAlertMessageStatus.Neutral(),
                OudsAlertMessageStatus.Positive,
                OudsAlertMessageStatus.Info,
                OudsAlertMessageStatus.Warning,
                OudsAlertMessageStatus.Negative
            )
        } else {
            OudsAlertMessageStatus::class.sealedSubclasses.mapNotNull { kClass ->
                tryOrNull {
                    (kClass.objectInstance ?: kClass.createInstance())
                }
            }
        }
        CustomizationDropdownMenu(
            applyTopPadding = false,
            label = stringResource(id = R.string.app_components_common_status_tech),
            items = statuses.map { status ->
                CustomizationDropdownMenuItem(
                    label = status::class.simpleName.orEmpty().toSentenceCase(),
                    leadingIcon = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(status.backgroundColor)
                        )
                    }
                )
            },
            selectedItemIndex = statuses.indexOfFirst { it::class.qualifiedName == status::class.qualifiedName },
            onSelectionChange = { status = statuses[it] }
        )
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_icon_tech),
            chips = AlertMessageDemoState.Icon.entries.map { CustomizationFilterChip(stringResource(it.labelRes), it in enabledIcons) },
            selectedChipIndex = AlertMessageDemoState.Icon.entries.indexOf(icon),
            onSelectionChange = { index -> icon = AlertMessageDemoState.Icon.entries[index] }
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_alert_alertMessage_closeButton_tech),
            checked = hasCloseButton,
            onCheckedChange = { hasCloseButton = it },
        )
        CustomizationTextInput(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_label_tech),
            value = label,
            onValueChange = { value -> label = value }
        )
        CustomizationTextInput(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_description_tech),
            value = description.orEmpty(),
            onValueChange = { value -> description = value },
            enabled = descriptionTextInputEnabled,
            helperText = stringResource(id = R.string.app_components_common_annotatedTextHelperText_tech)
        )
        CustomizationTextInput(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_alert_alertMessage_actionLink_tech),
            value = actionLink.orEmpty(),
            onValueChange = { value -> actionLink = value },
        )
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_alert_alertMessage_actionLinkPosition_tech),
            chips = OudsAlertMessageActionLinkPosition.entries.map {
                CustomizationFilterChip(
                    it.name.toSentenceCase(),
                    enabled = actionLinkPositionChipsEnabled
                )
            },
            selectedChipIndex = OudsAlertMessageActionLinkPosition.entries.indexOf(actionLinkPosition),
            onSelectionChange = { id -> actionLinkPosition = OudsAlertMessageActionLinkPosition.entries[id] }
        )
        for (index in 0..<AlertMessageDemoState.MaxBulletCount) {
            CustomizationTextInput(
                applyTopPadding = true,
                label = stringResource(R.string.app_components_alert_alertMessage_bullet_tech, index + 1),
                value = bulletList[index],
                onValueChange = { value ->
                    bulletList = bulletList.toMutableList().apply { set(index, value) }.toList()
                },
                enabled = bulletListTextInputsEnabled,
                helperText = stringResource(id = R.string.app_components_common_annotatedTextHelperText_tech)
            )
        }
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_common_annotatedText_tech),
            checked = annotatedText,
            onCheckedChange = { annotatedText = it },
        )
    }
}

@Composable
private fun AlertMessageDemoContent(state: AlertMessageDemoState) {
    with(state) {
        val alertIcon = when (icon) {
            AlertMessageDemoState.Icon.None -> null
            AlertMessageDemoState.Icon.Tinted -> OudsAlertIcon(painter = painterResource(LocalThemeDrawableResources.current.tipsAndTricks), tinted = true)
            AlertMessageDemoState.Icon.Untinted -> OudsAlertIcon(painter = rememberUntintedIconPainter(), tinted = false)
        }
        val alertMessageStatus = when (status) {
            is OudsAlertMessageStatus.Accent -> OudsAlertMessageStatus.Accent(alertIcon)
            is OudsAlertMessageStatus.Neutral -> OudsAlertMessageStatus.Neutral(alertIcon)
            is OudsAlertMessageStatus.Info -> OudsAlertMessageStatus.Info
            is OudsAlertMessageStatus.Negative -> OudsAlertMessageStatus.Negative
            is OudsAlertMessageStatus.Positive -> OudsAlertMessageStatus.Positive
            is OudsAlertMessageStatus.Warning -> OudsAlertMessageStatus.Warning
        }
        val onClose = if (hasCloseButton) {
            {}
        } else {
            null
        }
        val alertMessageActionLink = actionLink?.let { actionLinkLabel ->
            OudsAlertMessageActionLink(label = actionLinkLabel, onClick = {}, position = actionLinkPosition)
        }
        if (annotatedText) {
            val annotatedDescriptionHtml = stringResource(R.string.app_components_alert_alertMessage_annotatedDescription_text)
            val annotatedDescription = buildOudsAnnotatedAlertMessageDescription {
                appendHtml(annotatedDescriptionHtml)
            }
            val annotatedBullet1Html = stringResource(R.string.app_components_alert_alertMessage_annotatedBullet1_text)
            val annotatedBullet2Html = stringResource(R.string.app_components_alert_alertMessage_annotatedBullet2_text)
            val annotatedBullet3Html = stringResource(R.string.app_components_alert_alertMessage_annotatedBullet3_text)
            val annotatedBulletList = listOf(
                buildOudsAnnotatedAlertMessageBulletListLabel {
                    appendHtml(annotatedBullet1Html)
                },
                buildOudsAnnotatedAlertMessageBulletListLabel {
                    appendHtml(annotatedBullet2Html)
                },
                buildOudsAnnotatedAlertMessageBulletListLabel {
                    appendHtml(annotatedBullet3Html)
                }
            )
            OudsAlertMessage(
                label = label,
                description = annotatedDescription,
                status = alertMessageStatus,
                onClose = onClose,
                actionLink = alertMessageActionLink,
                bulletList = annotatedBulletList
            )
        } else {
            OudsAlertMessage(
                label = label,
                description = description,
                status = alertMessageStatus,
                onClose = onClose,
                actionLink = alertMessageActionLink,
                bulletList = bulletList
            )
        }
    }
}

private fun Code.Builder.alertMessageDemoCodeSnippet(state: AlertMessageDemoState, themeDrawableResources: ThemeDrawableResources) {
    with(state) {
        functionCall("OudsAlertMessage") {
            val statusParameterName = "status"
            when (status) {
                is OudsAlertMessageStatus.Accent,
                is OudsAlertMessageStatus.Neutral -> {
                    functionCallArgument(statusParameterName, status::class.java.nestedName) {
                        if (icon != AlertMessageDemoState.Icon.None) {
                            iconArgument<OudsAlertIcon>("icon", themeDrawableResources.tipsAndTricks, tinted = icon == AlertMessageDemoState.Icon.Tinted)
                        }
                    }
                }
                OudsAlertMessageStatus.Info,
                OudsAlertMessageStatus.Negative,
                OudsAlertMessageStatus.Positive,
                OudsAlertMessageStatus.Warning -> {
                    rawArgument(statusParameterName, status::class.java.nestedName)
                }
            }
            labelArgument(label)
            if (annotatedText) {
                annotatedStringArgument<OudsAnnotatedAlertMessageDescription>("description")
            } else {
                description?.let { typedArgument("description", description) }
            }
            if (hasCloseButton) {
                lambdaArgument("onClose") {
                    comment("Close alert message")
                }
            }
            if (!actionLink.isNullOrEmpty()) {
                constructorCallArgument<OudsAlertMessageActionLink>("actionLink") {
                    labelArgument(actionLink)
                    onClickArgument {
                        comment("Implement click")
                    }
                    typedArgument("position", actionLinkPosition)
                }
            }
            if (bulletList.any { it.isNotBlank() } || annotatedText) {
                functionCallArgument("bulletList", "listOf") {
                    if (annotatedText) {
                        repeat(AlertMessageDemoState.MaxBulletCount) {
                            annotatedStringArgument<OudsAnnotatedAlertMessageBulletListLabel>(null)
                        }
                    } else {
                        bulletList.forEach { label ->
                            if (label.isNotBlank()) {
                                typedArgument(null, label)
                            }
                        }
                    }
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewAlertMessageDemoScreen() = AppPreview {
    AlertMessageDemoScreen()
}
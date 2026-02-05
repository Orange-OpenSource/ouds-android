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
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.painterArgument
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.LocalThemeDrawableResources
import com.orange.ouds.app.ui.utilities.ThemeDrawableResources
import com.orange.ouds.app.ui.utilities.composable.CustomizationDropdownMenu
import com.orange.ouds.app.ui.utilities.composable.CustomizationDropdownMenuItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChip
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChips
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextInput
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.app.ui.utilities.nestedName
import com.orange.ouds.app.ui.utilities.toSentenceCase
import com.orange.ouds.core.component.OudsAlertMessage
import com.orange.ouds.core.component.OudsAlertMessageIcon
import com.orange.ouds.core.component.OudsAlertMessageLink
import com.orange.ouds.core.component.OudsAlertMessageLinkPosition
import com.orange.ouds.core.component.OudsAlertMessageStatus
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
                OudsAlertMessageStatus.Positive(),
                OudsAlertMessageStatus.Info(),
                OudsAlertMessageStatus.Warning(),
                OudsAlertMessageStatus.Negative()
            )
        } else {
            OudsAlertMessageStatus::class.sealedSubclasses.mapNotNull { kClass ->
                tryOrNull {
                    kClass.createInstance()
                }
            }
        }
        CustomizationDropdownMenu(
            applyTopPadding = true,
            label = stringResource(id = R.string.app_components_common_status_label),
            items = statuses.map { status ->
                CustomizationDropdownMenuItem(
                    label = status::class.simpleName.orEmpty().toSentenceCase(),
                    leadingIcon = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(status.backgroundColor())
                        )
                    }
                )
            },
            selectedItemIndex = statuses.indexOfFirst { it::class.qualifiedName == status::class.qualifiedName },
            onSelectionChange = { status = statuses[it] }
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_alert_alertMessage_statusIcon_label),
            checked = hasStatusIcon,
            onCheckedChange = { hasStatusIcon = it },
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_alert_alertMessage_closeButton_label),
            checked = hasCloseButton,
            onCheckedChange = { hasCloseButton = it },
        )
        CustomizationTextInput(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_label_label),
            value = label,
            onValueChange = { value -> label = value }
        )
        CustomizationTextInput(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_description_label),
            value = description.orEmpty(),
            onValueChange = { value -> description = value }
        )
        CustomizationTextInput(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_alert_alertMessage_actionLink_label),
            value = actionLink.orEmpty(),
            onValueChange = { value -> actionLink = value },
        )
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_alert_alertMessage_actionLinkPosition_label),
            chips = OudsAlertMessageLinkPosition.entries.map { CustomizationFilterChip(it.name.toSentenceCase(), enabled = actionLinkPositionChipsEnabled) },
            selectedChipIndex = OudsAlertMessageLinkPosition.entries.indexOf(actionLinkPosition),
            onSelectionChange = { id -> actionLinkPosition = OudsAlertMessageLinkPosition.entries[id] }
        )
    }
}

@Composable
private fun AlertMessageDemoContent(state: AlertMessageDemoState) {
    val icon = OudsAlertMessageIcon(painter = painterResource(LocalThemeDrawableResources.current.tipsAndTricks))
    with(state) {
        OudsAlertMessage(
            label = label,
            description = description,
            status = when (status) {
                is OudsAlertMessageStatus.Accent -> OudsAlertMessageStatus.Accent(if (hasStatusIcon) icon else null)
                is OudsAlertMessageStatus.Neutral -> OudsAlertMessageStatus.Neutral(if (hasStatusIcon) icon else null)
                is OudsAlertMessageStatus.Info -> OudsAlertMessageStatus.Info(showIcon = hasStatusIcon)
                is OudsAlertMessageStatus.Negative -> OudsAlertMessageStatus.Negative(showIcon = hasStatusIcon)
                is OudsAlertMessageStatus.Positive -> OudsAlertMessageStatus.Positive(showIcon = hasStatusIcon)
                is OudsAlertMessageStatus.Warning -> OudsAlertMessageStatus.Warning(showIcon = hasStatusIcon)
            },
            onClose = if (hasCloseButton) {
                {}
            } else {
                null
            },
            link = actionLink.takeIf { !it.isNullOrEmpty() }?.let { actionLinkLabel ->
                OudsAlertMessageLink(label = actionLinkLabel, onClick = {}, position = actionLinkPosition)
            }
        )
    }
}

private fun Code.Builder.alertMessageDemoCodeSnippet(state: AlertMessageDemoState, themeDrawableResources: ThemeDrawableResources) {
    with(state) {
        functionCall("OudsAlertMessage") {
            val statusParameterName = "status"
            functionCallArgument(statusParameterName, status::class.java.nestedName) {
                when (status) {
                    is OudsAlertMessageStatus.Neutral,
                    is OudsAlertMessageStatus.Accent -> {
                        constructorCallArgument<OudsAlertMessageIcon>("icon") {
                            painterArgument(themeDrawableResources.tipsAndTricks)
                        }
                    }
                    is OudsAlertMessageStatus.Positive,
                    is OudsAlertMessageStatus.Warning,
                    is OudsAlertMessageStatus.Info,
                    is OudsAlertMessageStatus.Negative -> {
                        typedArgument("showIcon", hasStatusIcon)
                    }
                }
            }
            typedArgument("label", label)
            description?.let { typedArgument("description", description) }
            if (hasCloseButton) {
                lambdaArgument("onClose") {
                    comment("Close alert message")
                }
            }
            if (!actionLink.isNullOrEmpty()) {
                functionCallArgument("link", OudsAlertMessageLink::class.java.nestedName) {
                    typedArgument("label", actionLink)
                    lambdaArgument("onClick") {
                        comment("Implement click")
                    }
                    typedArgument("position", actionLinkPosition)
                }
            }
        }
    }
}
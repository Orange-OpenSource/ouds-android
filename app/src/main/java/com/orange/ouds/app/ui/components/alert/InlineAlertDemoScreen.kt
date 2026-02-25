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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.painterArgument
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.LocalThemeDrawableResources
import com.orange.ouds.app.ui.utilities.ThemeDrawableResources
import com.orange.ouds.app.ui.utilities.composable.AppPreview
import com.orange.ouds.app.ui.utilities.composable.CustomizationDropdownMenu
import com.orange.ouds.app.ui.utilities.composable.CustomizationDropdownMenuItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextInput
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.app.ui.utilities.nestedName
import com.orange.ouds.app.ui.utilities.toSentenceCase
import com.orange.ouds.core.component.OudsAlertIcon
import com.orange.ouds.core.component.OudsInlineAlert
import com.orange.ouds.core.component.OudsInlineAlertStatus
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.extensions.tryOrNull
import com.orange.ouds.theme.OudsVersion

@Composable
fun InlineAlertDemoScreen() {
    val state = rememberInlineAlertDemoState()
    val themeDrawableResources = LocalThemeDrawableResources.current
    DemoScreen(
        description = stringResource(id = R.string.app_components_alert_inlineAlert_description_text),
        bottomSheetContent = { InlineAlertDemoBottomSheetContent(state = state) },
        codeSnippet = { inlineAlertDemoCodeSnippet(state = state, themeDrawableResources = themeDrawableResources) },
        demoContent = { InlineAlertDemoContent(state = state) },
        version = OudsVersion.Component.AlertMessage
    )
}

@Composable
private fun InlineAlertDemoBottomSheetContent(state: InlineAlertDemoState) {
    with(state) {
        val statuses = if (LocalInspectionMode.current) {
            // Fixes a bug where calling sealedSubclasses returns an empty list in Compose previews
            // See https://issuetracker.google.com/issues/240601093
            listOf(
                OudsInlineAlertStatus.Accent(OudsAlertIcon.Default),
                OudsInlineAlertStatus.Neutral(OudsAlertIcon.Default),
                OudsInlineAlertStatus.Positive,
                OudsInlineAlertStatus.Info,
                OudsInlineAlertStatus.Warning,
                OudsInlineAlertStatus.Negative
            )
        } else {
            OudsInlineAlertStatus::class.sealedSubclasses.mapNotNull { kClass ->
                tryOrNull {
                    when (kClass) {
                        OudsInlineAlertStatus.Neutral::class -> OudsInlineAlertStatus.Neutral(OudsAlertIcon.Default)
                        OudsInlineAlertStatus.Accent::class -> OudsInlineAlertStatus.Accent(OudsAlertIcon.Default)
                        else -> kClass.objectInstance
                    }
                }
            }
        }
        CustomizationDropdownMenu(
            applyTopPadding = false,
            label = stringResource(id = R.string.app_components_common_status_label),
            items = statuses.map { status ->
                CustomizationDropdownMenuItem(
                    label = status::class.simpleName.orEmpty().toSentenceCase(),
                    leadingIcon = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(status.assetColor.takeIf { it != Color.Unspecified }.orElse { status.textColor })
                        )
                    }
                )
            },
            selectedItemIndex = statuses.indexOfFirst { it::class.qualifiedName == status::class.qualifiedName },
            onSelectionChange = { status = statuses[it] }
        )
        CustomizationTextInput(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_label_label),
            value = label,
            onValueChange = { value -> label = value }
        )
    }
}

@Composable
private fun InlineAlertDemoContent(state: InlineAlertDemoState) {
    val icon = OudsAlertIcon(painter = painterResource(LocalThemeDrawableResources.current.tipsAndTricks))
    with(state) {
        OudsInlineAlert(
            label = label,
            status = when (status) {
                is OudsInlineAlertStatus.Accent -> OudsInlineAlertStatus.Accent(icon)
                is OudsInlineAlertStatus.Neutral -> OudsInlineAlertStatus.Neutral(icon)
                is OudsInlineAlertStatus.Info -> OudsInlineAlertStatus.Info
                is OudsInlineAlertStatus.Negative -> OudsInlineAlertStatus.Negative
                is OudsInlineAlertStatus.Positive -> OudsInlineAlertStatus.Positive
                is OudsInlineAlertStatus.Warning -> OudsInlineAlertStatus.Warning
            },
        )
    }
}

private fun Code.Builder.inlineAlertDemoCodeSnippet(state: InlineAlertDemoState, themeDrawableResources: ThemeDrawableResources) {
    with(state) {
        functionCall("OudsInlineAlert") {
            val statusParameterName = "status"
            when (status) {
                is OudsInlineAlertStatus.Accent,
                is OudsInlineAlertStatus.Neutral -> {
                    functionCallArgument(statusParameterName, status::class.java.nestedName) {
                        constructorCallArgument<OudsAlertIcon>("icon") {
                            painterArgument(themeDrawableResources.tipsAndTricks)
                        }
                    }
                }
                OudsInlineAlertStatus.Info,
                OudsInlineAlertStatus.Negative,
                OudsInlineAlertStatus.Positive,
                OudsInlineAlertStatus.Warning -> {
                    rawArgument(statusParameterName, status::class.java.nestedName)
                }
            }
            typedArgument("label", label)
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewInlineAlertDemoScreen() = AppPreview {
    InlineAlertDemoScreen()
}
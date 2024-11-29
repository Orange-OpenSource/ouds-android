package com.orange.ouds.app.ui.components.button

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.Component
import com.orange.ouds.app.ui.utilities.composable.CustomizationBottomSheetScaffold
import com.orange.ouds.app.ui.utilities.composable.CustomizationChoiceChipsColumn
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchListItem
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.app.ui.utilities.composable.DetailScreenDescription
import com.orange.ouds.core.component.button.OudsButton
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.utilities.UiModePreviews
import com.orange.ouds.theme.tokens.OudsSpaceKeyToken


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ButtonDemoScreen() = DemoScreen(rememberButtonDemoState()) {
    CustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
        bottomSheetContent = {
            CustomizationSwitchListItem(
                label = stringResource(R.string.app_common_enabled_label),
                checked = enabled,
                onCheckedChange = { enabled = it },
                enabled = style == OudsButton.Style.Default
            )
            CustomizationChoiceChipsColumn(
                modifier = Modifier.padding(top = OudsSpaceKeyToken.Fixed.Medium.value),
                label = stringResource(R.string.app_components_button_hierarchy_label),
                chipsLabels = OudsButton.Hierarchy.entries.map { it.name },
                selectedChipIndex = OudsButton.Hierarchy.entries.indexOf(hierarchy),
                onSelectionChange = { id -> hierarchy = OudsButton.Hierarchy.entries[id] }
            )
            val styles = remember {
                listOf(
                    OudsButton.Style.Default,
                    OudsButton.Style.Loading(progress = null),
                    OudsButton.Style.Skeleton
                )
            }
            CustomizationChoiceChipsColumn(
                modifier = Modifier.padding(top = OudsSpaceKeyToken.Fixed.Medium.value),
                label = stringResource(R.string.app_components_button_style_label),
                chipsLabels = styles.map { it::class.simpleName.orEmpty() },
                selectedChipIndex = styles.indexOf(style),
                onSelectionChange = { id -> style = styles[id] }
            )
            CustomizationChoiceChipsColumn(
                modifier = Modifier.padding(top = OudsSpaceKeyToken.Fixed.Medium.value),
                label = stringResource(R.string.app_components_button_layout_label),
                chipsLabels = ButtonDemoState.Layout.entries.map { stringResource(it.labelRes) },
                selectedChipIndex = ButtonDemoState.Layout.entries.indexOf(layout),
                onSelectionChange = { id -> layout = ButtonDemoState.Layout.entries[id] }
            )
        }
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            DetailScreenDescription(
                modifier = Modifier.padding(all = OudsSpaceKeyToken.Fixed.Medium.value),
                descriptionRes = Component.Button.descriptionRes
            )
            val text = hierarchy.name
            val icon = OudsButton.Icon(painterResource(id = R.drawable.ic_heart))
            val modifier = Modifier.align(Alignment.CenterHorizontally)
            when (layout) {
                ButtonDemoState.Layout.TextOnly -> {
                    OudsButton(
                        modifier = modifier,
                        text = text,
                        onClick = {},
                        enabled = enabled,
                        style = style,
                        hierarchy = hierarchy
                    )
                }
                ButtonDemoState.Layout.IconAndText -> {
                    OudsButton(
                        modifier = modifier,
                        icon = icon,
                        text = text,
                        onClick = {},
                        enabled = enabled,
                        style = style,
                        hierarchy = hierarchy
                    )
                }
                ButtonDemoState.Layout.IconOnly -> {
                    OudsButton(
                        modifier = modifier,
                        icon = icon,
                        onClick = {},
                        enabled = enabled,
                        style = style,
                        hierarchy = hierarchy
                    )
                }
            }
        }
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewButtonExampleScreen() = OudsPreview {
    ButtonDemoScreen()
}
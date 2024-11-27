package com.orange.ouds.app.ui.components.button

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.Component
import com.orange.ouds.app.ui.utilities.composable.CustomizationBottomSheetScaffold
import com.orange.ouds.app.ui.utilities.composable.CustomizationChoiceChipsColumn
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchListItem
import com.orange.ouds.app.ui.utilities.composable.DetailScreenDescription
import com.orange.ouds.app.ui.utilities.composable.Screen
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.utilities.UiModePreviews
import com.orange.ouds.theme.tokens.OudsColorKeyToken
import com.orange.ouds.theme.tokens.OudsSpaceKeyToken

enum class TempButtonHierarchy {
    Default, Strong, Minimal, Negative
} //TODO replace it by lib enum

enum class ButtonLayout(@StringRes val labelRes: Int) {
    TextOnly(R.string.app_components_button_textOnlyLayout_label),
    IconAndText(R.string.app_components_button_iconAndTextLayout_label),
    TextAndIcon(R.string.app_components_button_textAndIconLayout_label),
    IconOnly(R.string.app_components_button_iconOnlyLayout_label)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ButtonDemoScreen() {
    val customizationState = rememberButtonDemoState()

    Screen {
        with(customizationState) {
            CustomizationBottomSheetScaffold(
                bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
                bottomSheetContent = {
                    CustomizationSwitchListItem(
                        label = stringResource(R.string.app_common_enabled_label),
                        checked = enabled.value,
                        onCheckedChange = { enabled.value = it })
                    CustomizationSwitchListItem(
                        label = stringResource(R.string.app_common_loading_label),
                        checked = loading.value,
                        onCheckedChange = { loading.value = it })
                    CustomizationChoiceChipsColumn(
                        modifier = Modifier.padding(top = OudsSpaceKeyToken.Fixed.Medium.value),
                        label = stringResource(R.string.app_components_button_hierarchy_label),
                        chipsLabels = TempButtonHierarchy.entries.map { it.name },
                        selectedChipIndex = TempButtonHierarchy.entries.indexOf(hierarchy.value),
                        onSelectionChange = { id -> hierarchy.value = TempButtonHierarchy.entries[id] }
                    )
                    CustomizationChoiceChipsColumn(
                        modifier = Modifier.padding(top = OudsSpaceKeyToken.Fixed.Medium.value),
                        label = stringResource(R.string.app_components_button_layout_label),
                        chipsLabels = ButtonLayout.entries.map { stringResource(it.labelRes) },
                        selectedChipIndex = ButtonLayout.entries.indexOf(layout.value),
                        onSelectionChange = { id -> layout.value = ButtonLayout.entries[id] }
                    )
                }
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    DetailScreenDescription(
                        modifier = Modifier.padding(all = OudsSpaceKeyToken.Fixed.Medium.value),
                        descriptionRes = Component.Button.descriptionRes
                    )
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = "// enabled=${isEnabled} / loading=${isLoading} / hierarchy=${hierarchy.value} / layout=${layout.value} //",
                        textAlign = TextAlign.Center,
                        color = OudsColorKeyToken.Content.Default.value
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
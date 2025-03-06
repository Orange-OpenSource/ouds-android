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

package com.orange.ouds.app.ui.about

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.orange.ouds.app.ui.utilities.composable.Screen
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.utilities.UiModePreviews
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutMaterialComponentsScreen() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scaffoldState = rememberBottomSheetScaffoldState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Screen {
        BottomSheetScaffoldSample(scope = scope, snackbarHostState = snackbarHostState, scaffoldState = scaffoldState) {
            NavigationDrawerSample(drawerState = drawerState, scope = scope) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(bottom = OudsTheme.spaces.fixed.medium)
                ) {

                    // APP BARS
                    SectionColumn(title = "App bars") {
                        AppBarsSample()
                    }

                    // BUTTONS
                    SectionColumn(title = "Buttons") {
                        CommonButtonsSample()
                        CommonButtonsSample(enabled = false)
                        FloatingActionButtonsSample()
                        SegmentedButtonsSample()
                    }

                    // CARDS
                    SectionColumn(title = "Cards") {
                        CardsSample()
                    }

                    // CHECKBOXES
                    SectionColumn(title = "Checkboxes") {
                        CheckboxesSample()
                        CheckboxesSample(enabled = false)
                    }

                    // CHIPS
                    SectionColumn(title = "Chips") {
                        ChipsSample()
                        ChipsSample(enabled = false)
                    }

                    // DATE PICKER
                    SectionColumn(title = "Date picker") {
                        val datePickerState = rememberDatePickerState(initialSelectedDateMillis = 1578096000000)
                        DatePicker(state = datePickerState)
                    }

                    // DIALOG
                    SectionColumn(title = "Dialog") {
                        DialogSample()
                    }

                    // LIST ITEM
                    SectionColumn(title = "List items", horizontalPadding = false, verticalSpacing = false) {
                        ListItemsSample()
                    }

                    // MENUS
                    SectionColumn(title = "Menus") {
                        MenusSample()
                    }

                    // NAVIGATION
                    SectionColumn(title = "Navigation") {
                        NavigationBarSample()
                        FilledTonalButton(onClick = { scope.launch { drawerState.open() } }) { Text("Open Navigation Drawer") }
                        NavigationRailSample()
                    }

                    // PROGRESS INDICATORS
                    SectionColumn(title = "Progress indicators") {
                        LinearProgressIndicator()
                        CircularProgressIndicator()
                    }

                    // RADIO BUTTONS
                    SectionColumn(title = "Radio buttons") {
                        RadioButtonsSample()
                        RadioButtonsSample(enabled = false)
                    }

                    // SHEETS
                    SectionColumn(title = "Sheets") {
                        FilledTonalButton(
                            onClick = { scope.launch { scaffoldState.bottomSheetState.expand() } }
                        ) { Text("Open sheet") }
                    }

                    // SLIDERS
                    SectionColumn(title = "Sliders") {
                        SlidersSample()
                        SlidersSample(enabled = false)
                    }

                    // SNACKBAR
                    SectionColumn(title = "Snackbar") {
                        FilledTonalButton(
                            onClick = { scope.launch { snackbarHostState.showSnackbar(message = "Snackbar message", actionLabel = "Action") } }
                        ) { Text("Show snackbar") }
                    }

                    // SWITCHES
                    SectionColumn(title = "Switches") {
                        SwitchesSample()
                        SwitchesSample(enabled = false)
                    }

                    // TABS
                    SectionColumn("Tabs") {
                        TabsSample()
                    }

                    // TEXT FIELDS
                    SectionColumn("Text fields") {
                        TextFieldsSample()
                        TextFieldsSample(isError = true)
                        TextFieldsSample(enabled = false)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBarsSample() {
    TopAppBar(
        title = {
            Text("Top App Bar")
        },
        navigationIcon = {
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        },
        actions = {
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = null
                )
            }
        },
    )

    val imageVectors = listOf(Icons.Filled.Check, Icons.Filled.Edit, Icons.Filled.AccountCircle, Icons.Filled.Build)
    BottomAppBar(
        actions = {
            imageVectors.forEach { icon ->
                IconButton(onClick = { /* do something */ }) {
                    Icon(icon, contentDescription = null)
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* do something */ },
                containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
            ) {
                Icon(Icons.Filled.Add, null)
            }
        }
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CommonButtonsSample(enabled: Boolean = true) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.short),
        verticalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.shortest)
    ) {
        Button(onClick = { /* do something */ }, enabled = enabled) { Text("Filled") }
        FilledTonalButton(onClick = { /* do something */ }, enabled = enabled) { Text("Tonal") }
        OutlinedButton(onClick = { /* do something */ }, enabled = enabled) { Text("Outlined") }
        ElevatedButton(onClick = { /* do something */ }, enabled = enabled) { Text("Elevated") }
        TextButton(onClick = { /* do something */ }, enabled = enabled) { Text("Text Button") }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FloatingActionButtonsSample() {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.short),
        verticalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.short)
    ) {
        FloatingActionButton(onClick = { /* do something */ }) { Icon(Icons.Filled.Add, "Floating action button.") }
        SmallFloatingActionButton(onClick = { /* do something */ }) { Icon(Icons.Filled.Add, "Floating action button.") }
        LargeFloatingActionButton(onClick = { /* do something */ }) { Icon(Icons.Filled.Add, "Large floating action button") }
        ExtendedFloatingActionButton(
            onClick = { /* do something */ },
            icon = { Icon(Icons.Filled.Edit, "Extended floating action button.") },
            text = { Text(text = "Extended FAB") },
        )
    }
}

@Composable
private fun SegmentedButtonsSample() {
    val checkedList = remember { mutableStateListOf<Int>() }
    val options = listOf("Favorites", "Trending", "Saved")
    val icons = listOf(
        Icons.Filled.Star,
        Icons.Filled.ShoppingCart,
        Icons.Filled.ThumbUp
    )

    MultiChoiceSegmentedButtonRow {
        options.forEachIndexed { index, label ->
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size),
                icon = {
                    SegmentedButtonDefaults.Icon(active = index in checkedList) {
                        Icon(
                            imageVector = icons[index],
                            contentDescription = null,
                            modifier = Modifier.size(SegmentedButtonDefaults.IconSize)
                        )
                    }
                },
                onCheckedChange = {
                    if (index in checkedList) {
                        checkedList.remove(index)
                    } else {
                        checkedList.add(index)
                    }
                },
                checked = index in checkedList,
                enabled = index != 2
            ) {
                Text(label)
            }
        }
    }
}

@Composable
private fun CardsSample() {
    val cardHeight = 60.dp
    Column(verticalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.shorter)) {
        Card(modifier = Modifier.fillMaxWidth().height(cardHeight)) {
            Box(modifier = Modifier.fillMaxSize()) { Text(text = "Filled card", modifier = Modifier.align(Alignment.Center)) }
        }

        OutlinedCard(modifier = Modifier.fillMaxWidth().height(cardHeight)) {
            Box(modifier = Modifier.fillMaxSize()) { Text(text = "Outlined card", modifier = Modifier.align(Alignment.Center)) }
        }

        ElevatedCard(modifier = Modifier.fillMaxWidth().height(cardHeight)) {
            Box(modifier = Modifier.fillMaxSize()) { Text(text = "Elevated card", modifier = Modifier.align(Alignment.Center)) }
        }
    }
}

@Composable
private fun CheckboxesSample(enabled: Boolean = true) {
    val checkedState = remember { mutableStateOf(true) }
    Row(horizontalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.short)) {
        Checkbox(checked = checkedState.value, enabled = enabled, onCheckedChange = { checkedState.value = it })
        Checkbox(checked = !checkedState.value, enabled = enabled, onCheckedChange = { checkedState.value = !it })
    }
}

@Composable
private fun ChipsSample(enabled: Boolean = true) {
    Column {
        val settingsIcon: @Composable () -> Unit = {
            Icon(
                Icons.Filled.Settings,
                contentDescription = "Localized description",
                Modifier.size(AssistChipDefaults.IconSize)
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.short),
        ) {
            AssistChip(
                onClick = { /* Do something! */ },
                label = { Text("Assist Chip") },
                leadingIcon = settingsIcon,
                enabled = enabled
            )

            ElevatedAssistChip(
                onClick = { /* Do something! */ },
                label = { Text("Elevated Assist Chip") },
                leadingIcon = settingsIcon,
                enabled = enabled
            )
        }

        FilterChipsRow(enabled = enabled, selected = false)

        var inputChipSelected by remember { mutableStateOf(false) }

        InputChip(
            selected = inputChipSelected,
            onClick = { inputChipSelected = !inputChipSelected },
            label = { Text("Input Chip") },
            avatar = {
                Icon(
                    Icons.Filled.Person,
                    contentDescription = "Localized description",
                    Modifier.size(InputChipDefaults.AvatarSize)
                )
            },
            enabled = enabled
        )

        SuggestionChip(onClick = { /* Do something! */ }, label = { Text("Suggestion Chip") }, enabled = enabled)
    }
}

@Composable
private fun FilterChipsRow(enabled: Boolean, selected: Boolean) {
    Row(horizontalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.short)) {
        var filterChipSelected by remember { mutableStateOf(selected) }

        FilterChip(
            selected = filterChipSelected,
            onClick = { filterChipSelected = !filterChipSelected },
            label = { Text("Filter chip") },
            leadingIcon = if (filterChipSelected) {
                {
                    Icon(
                        imageVector = Icons.Filled.Done,
                        contentDescription = "Localized Description",
                        modifier = Modifier.size(FilterChipDefaults.IconSize)
                    )
                }
            } else {
                null
            },
            enabled = enabled
        )

        ElevatedFilterChip(
            selected = filterChipSelected,
            onClick = { filterChipSelected = !filterChipSelected },
            label = { Text("Elevated filter chip") },
            leadingIcon =
            if (filterChipSelected) {
                {
                    Icon(
                        imageVector = Icons.Filled.Done,
                        contentDescription = "Localized Description",
                        modifier = Modifier.size(FilterChipDefaults.IconSize)
                    )
                }
            } else {
                null
            },
            enabled = enabled
        )
    }
}

@Composable
private fun DialogSample() {
    val openDialog = remember { mutableStateOf(false) }
    val closeDialog = { openDialog.value = false }
    FilledTonalButton(onClick = { openDialog.value = true }) { Text("Open dialog") }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = closeDialog,
            title = { Text(text = "Title") },
            text = { Text(text = "Turned on by default") },
            confirmButton = {
                TextButton(onClick = closeDialog) { Text("Confirm") }
            },
            dismissButton = {
                TextButton(onClick = closeDialog) { Text("Dismiss") }
            }
        )
    }
}

@Composable
private fun ListItemsSample() {
    val favoriteIcon: @Composable () -> Unit = {
        Icon(
            Icons.Filled.Favorite,
            contentDescription = "Localized description",
        )
    }
    val trailingContent: @Composable () -> Unit = { Text("meta") }

    ListItem(
        modifier = Modifier.clickable {  },
        headlineContent = { Text("One line list item with 24x24 icon") },
        leadingContent = favoriteIcon
    )
    ListItem(
        modifier = Modifier.clickable {  },
        headlineContent = { Text("Two line list item with trailing") },
        supportingContent = { Text("Secondary text") },
        trailingContent = trailingContent,
        leadingContent = favoriteIcon
    )
    ListItem(
        modifier = Modifier.clickable {  },
        headlineContent = { Text("Three line list item") },
        supportingContent = {
            Text("Secondary text that is long and perhaps goes onto another line")
        },
        leadingContent = favoriteIcon,
        trailingContent = trailingContent
    )
}

@Composable
private fun MenusSample() {
    var expanded by remember { mutableStateOf(false) }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = "Click to expand")
        Box(modifier = Modifier.fillMaxSize()) {
            IconButton(onClick = { expanded = true }) {
                Icon(Icons.Default.MoreVert, contentDescription = "Localized description")
            }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                DropdownMenuItem(
                    text = { Text("Edit") },
                    onClick = { /* Handle edit! */ },
                    leadingIcon = { Icon(Icons.Outlined.Edit, contentDescription = null) }
                )
                DropdownMenuItem(
                    text = { Text("Settings") },
                    onClick = { /* Handle settings! */ },
                    leadingIcon = { Icon(Icons.Outlined.Settings, contentDescription = null) }
                )
                HorizontalDivider()
                DropdownMenuItem(
                    text = { Text("Send Feedback") },
                    onClick = { /* Handle send feedback! */ },
                    leadingIcon = { Icon(Icons.Outlined.Email, contentDescription = null) },
                    trailingIcon = { Text("F11", textAlign = TextAlign.Center) }
                )
            }
        }
    }
}

@Composable
private fun NavigationBarSample() {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("Songs", "Artists", "Playlists")
    val selectedIcons = listOf(Icons.Filled.Home, Icons.Filled.Favorite, Icons.Filled.Star)
    val unselectedIcons = listOf(Icons.Outlined.Home, Icons.Outlined.FavoriteBorder, Icons.Outlined.Star)

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    BadgedBox(
                        badge = {
                            Badge {
                                val badgeNumber = "2"
                                Text(
                                    badgeNumber,
                                    modifier =
                                    Modifier.semantics {
                                        contentDescription = "$badgeNumber new notifications"
                                    }
                                )
                            }
                        }
                    ) {
                        Icon(
                            if (selectedItem == index) selectedIcons[index] else unselectedIcons[index],
                            contentDescription = item
                        )
                    }
                },
                label = { Text(item) },
                selected = selectedItem == index,
                onClick = { selectedItem = index }
            )
        }
    }
}

@Composable
private fun NavigationDrawerSample(drawerState: DrawerState, scope: CoroutineScope, content: @Composable () -> Unit) {
    val items = listOf(
        Icons.Default.AccountCircle,
        Icons.Default.Build,
        Icons.Default.Call,
        Icons.Default.Email,
        Icons.Default.Favorite,
        Icons.Default.Home,
        Icons.Default.Info,
        Icons.Default.Place,
        Icons.Default.CheckCircle,
    )
    val selectedItem = remember { mutableStateOf(items[0]) }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(drawerState) {
                Column(Modifier.verticalScroll(rememberScrollState())) {
                    Spacer(Modifier.height(12.dp))
                    items.forEach { item ->
                        NavigationDrawerItem(
                            icon = { Icon(item, contentDescription = null) },
                            label = { Text(item.name.substringAfterLast(".")) },
                            selected = item == selectedItem.value,
                            onClick = {
                                scope.launch { drawerState.close() }
                                selectedItem.value = item
                            },
                            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                        )
                    }
                }
            }
        },
        content = content
    )
}

@Composable
private fun NavigationRailSample() {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("Home", "Search", "Settings")
    val selectedIcons = listOf(Icons.Filled.Home, Icons.Filled.Favorite, Icons.Filled.Star)
    val unselectedIcons = listOf(Icons.Outlined.Home, Icons.Outlined.FavoriteBorder, Icons.Outlined.Star)
    NavigationRail {
        items.forEachIndexed { index, item ->
            NavigationRailItem(
                icon = {
                    Icon(
                        if (selectedItem == index) selectedIcons[index] else unselectedIcons[index],
                        contentDescription = item
                    )
                },
                label = { Text(item) },
                selected = selectedItem == index,
                onClick = { selectedItem = index }
            )
        }
    }
}

@Composable
private fun RadioButtonsSample(enabled: Boolean = true) {
    var state by remember { mutableStateOf(true) }
    val modifier = Modifier.semantics { contentDescription = "Localized Description" }
    Row(Modifier.selectableGroup()) {
        RadioButton(
            selected = state,
            onClick = { state = !state },
            modifier = modifier,
            enabled = enabled
        )
        RadioButton(
            selected = !state,
            onClick = { state = !state },
            modifier = modifier,
            enabled = enabled
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BottomSheetScaffoldSample(
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    scaffoldState: BottomSheetScaffoldState,
    content: @Composable () -> Unit
) {
    BottomSheetScaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Sheet content")
                FilledTonalButton(
                    modifier = Modifier.padding(top = OudsTheme.spaces.fixed.short, bottom = OudsTheme.spaces.fixed.huge),
                    onClick = { scope.launch { scaffoldState.bottomSheetState.partialExpand() } }
                ) {
                    Text("Collapse sheet")
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .consumeWindowInsets(innerPadding)
                .padding(innerPadding)
        ) {
            content()
        }
    }
}

@Composable
private fun SlidersSample(enabled: Boolean = true) {
    Column {
        var sliderPosition by remember { mutableFloatStateOf(0.3f) }
        Text(text = "%.2f".format(sliderPosition))
        Slider(
            modifier = Modifier.semantics { contentDescription = "Localized Description" },
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            enabled = enabled
        )
        var steppedSliderPosition by remember { mutableFloatStateOf(10f) }
        Text(text = steppedSliderPosition.roundToInt().toString())
        Slider(
            modifier = Modifier.semantics { contentDescription = "Localized Description" },
            value = steppedSliderPosition,
            onValueChange = { steppedSliderPosition = it },
            valueRange = 0f..100f,
            onValueChangeFinished = {},
            steps = 9,
            enabled = enabled
        )
    }
}

@Composable
private fun SwitchesSample(enabled: Boolean = true) {
    var checked by remember { mutableStateOf(true) }
    val checkIcon: @Composable () -> Unit = {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = null,
            modifier = Modifier.size(SwitchDefaults.IconSize),
        )
    }

    Row(horizontalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.short)) {
        Switch(
            checked = checked,
            onCheckedChange = { checked = !checked },
            enabled = enabled
        )
        Switch(
            checked = !checked,
            onCheckedChange = { checked = !checked },
            enabled = enabled
        )
        Switch(
            checked = checked,
            onCheckedChange = { checked = !checked },
            thumbContent = { if (checked) checkIcon() },
            enabled = enabled
        )
        Switch(
            checked = !checked,
            onCheckedChange = { checked = !checked },
            thumbContent = { if (!checked) checkIcon() },
            enabled = enabled
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TabsSample() {
    var state by remember { mutableIntStateOf(0) }
    TabRow(selectedTabIndex = state) {
        Tabs(state = state, onTabClick = { index -> state = index })
    }

    SecondaryTabRow(selectedTabIndex = state) {
        Tabs(state = state, onTabClick = { index -> state = index })
    }
}

@Composable
private fun Tabs(state: Int, onTabClick: (Int) -> Unit) {
    val titles = listOf("Tab 1", "Tab 2", "Tab 3 with lots of text")
    titles.forEachIndexed { index, title ->
        Tab(
            selected = state == index,
            onClick = { onTabClick(index) },
            text = { Text(text = title, maxLines = 2, overflow = TextOverflow.Ellipsis) }
        )
    }
}

@Composable
private fun TextFieldsSample(enabled: Boolean = true, isError: Boolean = false) {
    var text by rememberSaveable { mutableStateOf("") }
    var textForOutlined by rememberSaveable { mutableStateOf("") }

    val label: @Composable () -> Unit = { Text(text = "Label") }
    val placeholder: @Composable () -> Unit = { Text(text = "Placeholder") }
    val supportingText: @Composable () -> Unit = { Text(text = "Supporting text") }
    val icon: @Composable () -> Unit = { Icon(Icons.Filled.Favorite, contentDescription = null) }

    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = text,
        onValueChange = { text = it },
        label = label,
        placeholder = placeholder,
        leadingIcon = icon,
        isError = isError,
        supportingText = supportingText,
        enabled = enabled
    )

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = textForOutlined,
        onValueChange = { textForOutlined = it },
        label = label,
        placeholder = placeholder,
        trailingIcon = icon,
        isError = isError,
        supportingText = supportingText,
        enabled = enabled
    )
}

@Composable
private fun SectionColumn(title: String, horizontalPadding: Boolean = true, verticalSpacing: Boolean = true, content: @Composable () -> Unit) {
    Text(
        text = title,
        style = OudsTheme.typography.heading.medium,
        modifier = Modifier.padding(bottom = OudsTheme.spaces.fixed.short, top = OudsTheme.spaces.fixed.taller).padding(horizontal = OudsTheme.spaces.fixed.medium)
    )
    Column(
        modifier = if (horizontalPadding) Modifier.padding(horizontal = OudsTheme.spaces.fixed.medium) else Modifier,
        verticalArrangement = if (verticalSpacing) Arrangement.spacedBy(OudsTheme.spaces.fixed.short) else Arrangement.Top
    ) {
        content()
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewAboutMaterialComponentsScreen() = OudsPreview {
    AboutMaterialComponentsScreen()
}

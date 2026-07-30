# OUDS Android — Control Components

All components are in the `com.orange.ouds.core.component` package.  
All user-visible strings must use `stringResource(R.string.*)` — never hardcode.

> **Naming Convention:** All OUDS components follow the `Ouds*` prefix naming pattern (e.g., `OudsButton`, `OudsTag`, `OudsTextInput`).

## Table of Contents

- [Checkbox](#checkbox) — Standalone checkbox
- [CheckboxItem](#checkboxitem) — Checkbox with label and description
- [RadioButton](#radiobutton) — Standalone radio button
- [RadioButtonItem](#radiobuttonitem) — Radio button with label and description
- [Switch](#switch) — Standalone switch
- [SwitchItem](#switchitem) — Toggle switch with label and description
  - **Chip**
    - [FilterChip](#filterchip) — Selectable filter chip
    - [SuggestionChip](#suggestionchip) — Suggestion and action chip

---

## CheckboxItem

Signature: `OudsCheckboxItem(checked, label, onCheckedChange, modifier, description?, icon?, divider?, enabled?, error?)`  
Tri-state variant: `OudsTriStateCheckboxItem(state: ToggleableState, label, onClick, …)`

```kotlin
// Basic
var checked by remember { mutableStateOf(false) }
OudsCheckboxItem(
    checked = checked,
    label = stringResource(R.string.terms),
    onCheckedChange = { checked = it }
)

// With description and icon
OudsCheckboxItem(
    checked = checked,
    label = stringResource(R.string.terms),
    description = stringResource(R.string.terms_desc),
    icon = OudsControlItemIcon(imageVector = Icons.Filled.FavoriteBorder),
    onCheckedChange = { checked = it }
)

// With untinted icon
OudsCheckboxItem(
    checked = checked,
    label = stringResource(R.string.terms),
    icon = OudsControlItemIcon(painter = myPainter, tinted = false),
    onCheckedChange = { checked = it }
)

// With error
OudsCheckboxItem(
    checked = checked,
    label = stringResource(R.string.terms),
    onCheckedChange = { checked = it },
    error = OudsError(message = stringResource(R.string.error_required))
)

// Tri-state
var state by remember { mutableStateOf(ToggleableState.Off) }
OudsTriStateCheckboxItem(
    state = state,
    label = stringResource(R.string.select_all),
    onClick = {
        state = when (state) {
            ToggleableState.On -> ToggleableState.Off
            ToggleableState.Off -> ToggleableState.Indeterminate
            ToggleableState.Indeterminate -> ToggleableState.On
        }
    }
)
```

---

## RadioButtonItem

Signature: `OudsRadioButtonItem(selected, label, onClick, modifier, description?, icon?, divider?, enabled?, error?)`  
**Always** wrap a group of radio items in `Modifier.selectableGroup()`.

```kotlin
val options = listOf(
    stringResource(R.string.option_a),
    stringResource(R.string.option_b)
)
var selected by rememberSaveable { mutableStateOf(options.first()) }

Column(modifier = Modifier.selectableGroup()) {
    options.forEach { option ->
        OudsRadioButtonItem(
            selected = option == selected,
            label = option,
            onClick = { selected = option },
            divider = true
        )
    }
}

// With icon
OudsRadioButtonItem(
    selected = selected == option,
    label = option,
    icon = OudsControlItemIcon(imageVector = Icons.Filled.FavoriteBorder),
    onClick = { selected = option }
)

// With error (typically on the last item)
OudsRadioButtonItem(
    selected = selected == option,
    label = option,
    onClick = { selected = option },
    error = OudsError(message = stringResource(R.string.selection_required))
)
```

---

## SwitchItem

Signature: `OudsSwitchItem(checked, label, onCheckedChange, modifier, description?, icon?, divider?, enabled?, error?)`

```kotlin
var checked by remember { mutableStateOf(true) }

OudsSwitchItem(
    checked = checked,
    label = stringResource(R.string.notifications),
    description = stringResource(R.string.notifications_desc),
    icon = OudsControlItemIcon(imageVector = Icons.Filled.FavoriteBorder),
    onCheckedChange = { checked = it }
)

// With untinted icon
OudsSwitchItem(
    checked = checked,
    label = stringResource(R.string.notifications),
    icon = OudsControlItemIcon(painter = myPainter, tinted = false),
    onCheckedChange = { checked = it }
)

// With error
OudsSwitchItem(
    checked = checked,
    label = stringResource(R.string.notifications),
    onCheckedChange = { checked = it },
    error = OudsError(message = stringResource(R.string.notifications_required))
)
```

---

## Checkbox

**Standalone checkbox** without label — use when checkbox is nested within another component with an alternative label.  
**See also:** [CheckboxItem](#checkboxitem) for checkbox with label and description.

```kotlin
var checked by remember { mutableStateOf(false) }

// Basic checkbox
OudsCheckbox(
    checked = checked,
    onCheckedChange = { checked = it }
)

// Disabled
OudsCheckbox(
    checked = checked,
    onCheckedChange = { checked = it },
    enabled = false
)

// Tri-state checkbox
var state by remember { mutableStateOf(ToggleableState.Off) }
OudsTriStateCheckbox(
    state = state,
    onClick = {
        state = when (state) {
            ToggleableState.On -> ToggleableState.Off
            ToggleableState.Off -> ToggleableState.Indeterminate
            ToggleableState.Indeterminate -> ToggleableState.On
        }
    }
)
```

---

## RadioButton

**Standalone radio button** without label — use when radio button is nested within another component with an alternative label.  
**See also:** [RadioButtonItem](#radiobuttonitem) for radio button with label and description.  
**Always** wrap a group of radio buttons in `Modifier.selectableGroup()`.

```kotlin
val options = listOf("Option A", "Option B", "Option C")
var selected by remember { mutableStateOf(options[0]) }

Column(modifier = Modifier.selectableGroup()) {
    options.forEach { option ->
        OudsRadioButton(
            selected = option == selected,
            onClick = { selected = option }
        )
    }
}

// Disabled
OudsRadioButton(
    selected = true,
    onClick = null,
    enabled = false
)
```

---

## Switch

**Standalone switch** without label — use when switch is nested within another component with an alternative label.  
**See also:** [SwitchItem](#switchitem) for switch with label and description.

```kotlin
var checked by remember { mutableStateOf(true) }

// Basic switch
OudsSwitch(
    checked = checked,
    onCheckedChange = { checked = it }
)

// Disabled
OudsSwitch(
    checked = checked,
    onCheckedChange = { checked = it },
    enabled = false
)

// With icon (when checked)
OudsSwitch(
    checked = checked,
    onCheckedChange = { checked = it },
    thumbContent = {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = null,
            modifier = Modifier.size(16.dp)
        )
    }
)
```

---

## FilterChip

**Selectable chip** used for filtering content.

```kotlin
// Text only
OudsFilterChip(text = stringResource(R.string.label), onClick = { })

// With icon
OudsFilterChip(
    icon = OudsChipIcon(imageVector = Icons.Filled.FavoriteBorder),
    text = stringResource(R.string.label),
    onClick = { }
)

// Icon only
OudsFilterChip(
    icon = OudsChipIcon(imageVector = Icons.Filled.FavoriteBorder),
    contentDescription = stringResource(R.string.label_desc),
    onClick = { }
)

// Selected state
var selected by remember { mutableStateOf(false) }
OudsFilterChip(
    text = stringResource(R.string.label),
    selected = selected,
    onClick = { selected = !selected }
)
```

---

## SuggestionChip

**Action chip** used for suggestions and quick actions.

```kotlin
// Text only
OudsSuggestionChip(text = stringResource(R.string.label), onClick = { })

// With icon
OudsSuggestionChip(
    icon = OudsChipIcon(imageVector = Icons.Filled.FavoriteBorder),
    text = stringResource(R.string.label),
    onClick = { }
)

// Icon only
OudsSuggestionChip(
    icon = OudsChipIcon(imageVector = Icons.Filled.Add),
    contentDescription = stringResource(R.string.add_desc),
    onClick = { }
)
```

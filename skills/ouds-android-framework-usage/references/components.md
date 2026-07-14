# OUDS Android — Components Reference

All components are in the `com.orange.ouds.core.component` package.  
All user-visible strings must use `stringResource(R.string.*)` — never hardcode.

---

## Button

**Layouts:** text only · icon only · text + icon  
**Sizes:** default (`OudsButton`) · small (`OudsSmallButton`)  
**Appearances:** `OudsButtonAppearance` — `Default`, `Strong`, `Brand`, `Minimal`, `Negative`  
**Note:** `Negative` appearance is forbidden inside `OudsColoredBox`.  
Inside `OudsColoredBox`, the button automatically adopts its monochrome variant.

```kotlin
// Text only
OudsButton(
    label = stringResource(R.string.action),
    onClick = { }
)

// Icon only — contentDescription required
OudsButton(
    icon = OudsButtonIcon(
        imageVector = Icons.Filled.FavoriteBorder,
        contentDescription = stringResource(R.string.favorite_desc)
    ),
    onClick = { }
)

// Text + icon
OudsButton(
    icon = OudsButtonIcon(imageVector = Icons.Filled.FavoriteBorder, contentDescription = ""),
    label = stringResource(R.string.action),
    onClick = { }
)

// Untinted icon (multi-color / brand icon)
OudsButton(
    icon = OudsButtonIcon(painter = myPainter, contentDescription = "", tinted = false),
    onClick = { }
)

// With loading state
OudsButton(
    label = stringResource(R.string.action),
    loader = OudsButtonLoader(progress = null), // indeterminate
    onClick = { }
)

// On colored background — colors adjusted automatically
OudsColoredBox(color = OudsColoredBoxColor.StatusInfoEmphasized) {
    OudsButton(label = stringResource(R.string.action), onClick = { })
}
```

---

## SmallButton

Same API as `OudsButton` but uses the small size variant.

```kotlin
OudsSmallButton(label = stringResource(R.string.action), onClick = { })

OudsSmallButton(
    icon = OudsButtonIcon(imageVector = Icons.Filled.FavoriteBorder, contentDescription = ""),
    label = stringResource(R.string.action),
    onClick = { }
)
```

---

## Tag

**Statuses:** `OudsTagStatus` — `Neutral`, `Accent`, `Positive`, `Warning`, `Negative`, `Info`  
**Assets:** `OudsTagAsset.Bullet` · `OudsTagAsset.Icon(…)` · `OudsTagAsset.Icon.Default` (functional icon per status)  
**Appearances:** `OudsTagAppearance` — `Emphasized` (default), `Muted`  
**Sizes:** `OudsTagSize` — `Default`, `Small`

```kotlin
// Text only
OudsTag(label = stringResource(R.string.label))

// With bullet
OudsTag(
    label = stringResource(R.string.label),
    status = OudsTagStatus.Positive(asset = OudsTagAsset.Bullet)
)

// With default functional icon (icon per status)
OudsTag(
    label = stringResource(R.string.label),
    status = OudsTagStatus.Positive(asset = OudsTagAsset.Icon.Default)
)

// With custom icon (Neutral or Accent only)
OudsTag(
    label = stringResource(R.string.label),
    status = OudsTagStatus.Neutral(asset = OudsTagAsset.Icon(imageVector = Icons.Filled.FavoriteBorder))
)

// With untinted icon
OudsTag(
    label = stringResource(R.string.label),
    status = OudsTagStatus.Neutral(asset = OudsTagAsset.Icon(painter = myPainter, tinted = false))
)

// Small size
OudsTag(label = stringResource(R.string.label), size = OudsTagSize.Small)

// With loader (indeterminate)
OudsTag(label = stringResource(R.string.label), loader = OudsTagLoader(progress = null))
```

---

## Badge

**Statuses (plain/count):** `OudsBadgeStatus` — `Neutral`, `Accent`, `Positive`, `Warning`, `Negative`, `Info`  
**Statuses (icon):** `OudsIconBadgeStatus` — `Neutral(icon?)`, `Accent(icon?)`, `Positive`, `Warning`, `Negative`, `Info`  
**Sizes:** `OudsBadgeSize` — `ExtraSmall`, `Small`, `Medium`, `Large`  
**Note:** Always provide a `contentDescription` via `Modifier.semantics { contentDescription = "…" }`.

```kotlin
// Standard dot badge
OudsBadge(
    modifier = Modifier.semantics { contentDescription = stringResource(R.string.info_desc) },
    status = OudsBadgeStatus.Info,
    size = OudsBadgeSize.Small
)

// Badge with count
val count = 10
OudsBadge(
    modifier = Modifier.semantics { contentDescription = stringResource(R.string.unread_count, count) },
    status = OudsBadgeStatus.Accent,
    count = count
)

// Badge with default functional icon
OudsBadge(
    modifier = Modifier.semantics { contentDescription = stringResource(R.string.info_desc) },
    status = OudsIconBadgeStatus.Info,
    size = OudsBadgeSize.Large
)

// Badge with custom icon
OudsBadge(
    modifier = Modifier.semantics { contentDescription = stringResource(R.string.favorite_desc) },
    status = OudsIconBadgeStatus.Accent(OudsBadgeIcon(imageVector = Icons.Filled.FavoriteBorder)),
    size = OudsBadgeSize.Large
)

// Badge with untinted custom icon
OudsBadge(
    modifier = Modifier.semantics { contentDescription = stringResource(R.string.brand_desc) },
    status = OudsIconBadgeStatus.Neutral(OudsBadgeIcon(painter = myPainter, tinted = false)),
    size = OudsBadgeSize.Large
)

// Typical use: badged navigation item
BadgedBox(
    badge = {
        OudsBadge(
            modifier = Modifier.semantics { contentDescription = stringResource(R.string.unread_count, 8) },
            count = 8,
            status = OudsBadgeStatus.Accent
        )
    }
) {
    Icon(imageVector = Icons.Filled.Notifications, contentDescription = null)
}
```

---

## AlertMessage

**Statuses:** `OudsAlertMessageStatus` — `Neutral`, `Accent(icon?)`, `Positive`, `Warning`, `Negative`, `Info`  
**Action link positions:** `OudsAlertMessageActionLinkPosition` — `Bottom` (default), `TopEnd`

```kotlin
// Minimal
OudsAlertMessage(label = stringResource(R.string.title))

// With functional status (no icon param)
OudsAlertMessage(
    label = stringResource(R.string.title),
    description = stringResource(R.string.description),
    status = OudsAlertMessageStatus.Positive,
    onClose = { /* dismiss */ }
)

// With non-functional status and custom icon
OudsAlertMessage(
    label = stringResource(R.string.title),
    description = stringResource(R.string.description),
    status = OudsAlertMessageStatus.Accent(OudsAlertIcon(imageVector = Icons.Filled.FavoriteBorder)),
    onClose = { /* dismiss */ },
    actionLink = OudsAlertMessageActionLink(
        label = stringResource(R.string.learn_more),
        onClick = { /* navigate */ }
    ),
    bulletList = listOf(
        stringResource(R.string.point_1),
        stringResource(R.string.point_2)
    )
)

// With untinted icon
OudsAlertMessage(
    label = stringResource(R.string.title),
    status = OudsAlertMessageStatus.Accent(OudsAlertIcon(painter = myPainter, tinted = false)),
    onClose = { }
)

// Action link at top end
OudsAlertMessage(
    label = stringResource(R.string.title),
    status = OudsAlertMessageStatus.Positive,
    onClose = { },
    actionLink = OudsAlertMessageActionLink(
        label = stringResource(R.string.details),
        onClick = { },
        position = OudsAlertMessageActionLinkPosition.TopEnd
    )
)
```

---

## InlineAlert

**Statuses:** `OudsInlineAlertStatus` — `Neutral`, `Accent(icon?)`, `Positive`, `Warning`, `Negative`, `Info`  
Functional statuses (`Positive`, `Warning`, `Negative`, `Info`) display a default icon automatically; no icon param.

```kotlin
// Functional status — icon automatic
OudsInlineAlert(
    label = stringResource(R.string.label),
    status = OudsInlineAlertStatus.Positive
)

// Non-functional with default icon
OudsInlineAlert(
    label = stringResource(R.string.label),
    status = OudsInlineAlertStatus.Accent(OudsAlertIcon.Default)
)

// Non-functional with custom icon
OudsInlineAlert(
    label = stringResource(R.string.label),
    status = OudsInlineAlertStatus.Accent(OudsAlertIcon(imageVector = Icons.Filled.FavoriteBorder))
)

// Non-functional with untinted icon
OudsInlineAlert(
    label = stringResource(R.string.label),
    status = OudsInlineAlertStatus.Accent(OudsAlertIcon(painter = myPainter, tinted = false))
)
```

---

## BulletList

**Types:** `OudsBulletListType` — `Unordered` (default, `brandColor: Boolean`), `Ordered`, `Bare`

```kotlin
// Unordered (brand color)
OudsBulletList {
    item(label = stringResource(R.string.item_1))
    item(label = stringResource(R.string.item_2), subListType = OudsBulletListType.Unordered(brandColor = false)) {
        item(label = stringResource(R.string.sub_item_1))
    }
}

// Ordered
OudsBulletList(type = OudsBulletListType.Ordered) {
    item(label = stringResource(R.string.step_1))
    item(label = stringResource(R.string.step_2)) {
        item(label = stringResource(R.string.sub_step_1))
    }
}

// Bare (no bullet)
OudsBulletList(type = OudsBulletListType.Bare) {
    item(label = stringResource(R.string.item_1))
}
```

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

## TextInput

Two API variants: **state-based** (`textFieldState`) and **value-based** (`value` + `onValueChange`).  
Prefer the state-based API for new code.

```kotlin
// State-based — basic
OudsTextInput(
    textFieldState = rememberTextFieldState(),
    label = stringResource(R.string.label)
)

// State-based — full featured
OudsTextInput(
    textFieldState = rememberTextFieldState(),
    label = stringResource(R.string.label),
    placeholder = stringResource(R.string.placeholder),
    leadingIcon = OudsTextInputLeadingIcon(imageVector = Icons.Filled.Search, contentDescription = ""),
    prefix = stringResource(R.string.prefix),
    suffix = stringResource(R.string.suffix),
    helperText = stringResource(R.string.helper),
    helperLink = OudsTextInputHelperLink(text = stringResource(R.string.more), onClick = { })
)

// With trailing action button
OudsTextInput(
    textFieldState = rememberTextFieldState(),
    label = stringResource(R.string.date),
    trailingIconButton = OudsTextInputTrailingIconButton(
        imageVector = Icons.Filled.DateRange,
        contentDescription = stringResource(R.string.open_calendar),
        onClick = { }
    ),
    outlined = true
)

// With error
OudsTextInput(
    textFieldState = rememberTextFieldState(),
    label = stringResource(R.string.label),
    error = OudsError(message = stringResource(R.string.field_required))
)

// Value-based
var value by remember { mutableStateOf("") }
OudsTextInput(
    value = value,
    onValueChange = { value = it },
    label = stringResource(R.string.label)
)

// Untinted leading icon
OudsTextInput(
    textFieldState = rememberTextFieldState(),
    label = stringResource(R.string.label),
    leadingIcon = OudsTextInputLeadingIcon(painter = myPainter, contentDescription = "", tinted = false)
)
```

---

## TextArea

Same two API variants as `TextInput` (state-based / value-based).

```kotlin
// State-based
OudsTextArea(
    textFieldState = rememberTextFieldState(),
    label = stringResource(R.string.feedback),
    placeholder = stringResource(R.string.feedback_placeholder),
    helperText = stringResource(R.string.feedback_helper)
)

// With error
OudsTextArea(
    textFieldState = rememberTextFieldState(),
    label = stringResource(R.string.comment),
    outlined = true,
    error = OudsError(message = stringResource(R.string.min_chars_error))
)

// Value-based
var value by remember { mutableStateOf("") }
OudsTextArea(
    value = value,
    onValueChange = { value = it },
    label = stringResource(R.string.description)
)
```

---

## PasswordInput

Uses `OudsPasswordInputState` to manage visibility toggle. Create the state with `rememberOudsPasswordInputState()`.

```kotlin
OudsPasswordInput(
    state = rememberOudsPasswordInputState(),
    label = stringResource(R.string.password),
    lockIcon = true,
    helperText = stringResource(R.string.password_helper)
)

// With error
OudsPasswordInput(
    state = rememberOudsPasswordInputState(),
    label = stringResource(R.string.password),
    error = OudsError(message = stringResource(R.string.password_error))
)
```

---

## PinCodeInput

**Lengths:** `OudsPinCodeInputLength` — `Four`, `Six`

```kotlin
var value by remember { mutableStateOf("") }

OudsPinCodeInput(
    value = value,
    onValueChange = { value = it },
    length = OudsPinCodeInputLength.Four,
    helperText = stringResource(R.string.pin_helper)
)

// With error
OudsPinCodeInput(
    value = value,
    onValueChange = { value = it },
    length = OudsPinCodeInputLength.Four,
    error = OudsError(message = stringResource(R.string.pin_error))
)
```

---

## FilterChip / SuggestionChip

```kotlin
// Filter chip — text
OudsFilterChip(text = stringResource(R.string.label), onClick = { })

// Filter chip — with icon
OudsFilterChip(
    icon = OudsChipIcon(imageVector = Icons.Filled.FavoriteBorder),
    text = stringResource(R.string.label),
    onClick = { }
)

// Filter chip — icon only
OudsFilterChip(
    icon = OudsChipIcon(imageVector = Icons.Filled.FavoriteBorder),
    contentDescription = stringResource(R.string.label_desc),
    onClick = { }
)

// Suggestion chip
OudsSuggestionChip(text = stringResource(R.string.label), onClick = { })

// Suggestion chip — with icon
OudsSuggestionChip(
    icon = OudsChipIcon(imageVector = Icons.Filled.FavoriteBorder),
    text = stringResource(R.string.label),
    onClick = { }
)
```

---

## Link

**Chevrons:** `OudsLinkChevron` — `Next`, `Back`

```kotlin
// Text only
OudsLink(
    label = stringResource(R.string.link_label),
    onClick = { }
)

// With icon
OudsLink(
    label = stringResource(R.string.link_label),
    icon = OudsLinkIcon(imageVector = Icons.Filled.FavoriteBorder),
    onClick = { }
)

// With chevron
OudsLink(
    label = stringResource(R.string.link_label),
    chevron = OudsLinkChevron.Next,
    onClick = { }
)

// With untinted icon
OudsLink(
    label = stringResource(R.string.link_label),
    icon = OudsLinkIcon(painter = myPainter, tinted = false),
    onClick = { }
)
```

---

## Divider

```kotlin
// Horizontal
OudsHorizontalDivider(modifier = Modifier.fillMaxWidth())

// Vertical
OudsVerticalDivider(modifier = Modifier.height(50.dp))
```

---

## NavigationBar

```kotlin
var selectedIndex by rememberSaveable { mutableIntStateOf(0) }

OudsNavigationBar(
    items = listOf(
        OudsNavigationBarItem(
            selected = selectedIndex == 0,
            onClick = { selectedIndex = 0 },
            icon = OudsNavigationBarItemIcon(imageVector = Icons.Default.Home),
            label = stringResource(R.string.home)
        ),
        OudsNavigationBarItem(
            selected = selectedIndex == 1,
            onClick = { selectedIndex = 1 },
            icon = OudsNavigationBarItemIcon(imageVector = Icons.Default.Email),
            label = stringResource(R.string.messages),
            badge = OudsNavigationBarItemBadge(
                contentDescription = stringResource(R.string.unread_count, 5),
                count = 5
            )
        )
    )
)
```

---

## TopAppBar

Four variants: `OudsTopAppBar`, `OudsCenterAlignedTopAppBar`, `OudsMediumTopAppBar`, `OudsLargeTopAppBar`.  
**Navigation icons:** `OudsTopAppBarNavigationIcon.Back { }` · `OudsTopAppBarNavigationIcon.Menu { }`  
**Actions:** `OudsTopAppBarAction.Icon(…)` · `OudsTopAppBarAction.Avatar(…)`

```kotlin
@OptIn(ExperimentalMaterial3Api::class)
OudsTopAppBar(
    title = stringResource(R.string.screen_title),
    navigationIcon = OudsTopAppBarNavigationIcon.Back { /* navigate back */ },
    actions = listOf(
        OudsTopAppBarAction.Icon(
            imageVector = Icons.Outlined.Settings,
            contentDescription = stringResource(R.string.settings_desc)
        ) { /* open settings */ }
    )
)

// Large top app bar
@OptIn(ExperimentalMaterial3Api::class)
OudsLargeTopAppBar(
    title = stringResource(R.string.screen_title),
    navigationIcon = OudsTopAppBarNavigationIcon.Back { }
)
```

---

## ColoredBox

Creates a colored surface where child OUDS components automatically switch to their monochrome variant.  
**Colors:** `OudsColoredBoxColor` — `BrandPrimary`, `StatusNeutralEmphasized`, `StatusAccentEmphasized`, `StatusPositiveEmphasized`, `StatusInfoEmphasized`, `StatusWarningEmphasized`, `StatusNegativeEmphasized`, and more.

```kotlin
OudsColoredBox(color = OudsColoredBoxColor.StatusInfoEmphasized) {
    // Child OUDS components adopt monochrome colors automatically
    OudsButton(label = stringResource(R.string.action), onClick = { })
    Text(
        text = stringResource(R.string.description),
        color = OudsTheme.colorScheme.content.default
    )
}
```

# OUDS Android — Input Components

All components are in the `com.orange.ouds.core.component` package.  
All user-visible strings must use `stringResource(R.string.*)` — never hardcode.

> **Naming Convention:** All OUDS components follow the `Ouds*` prefix naming pattern (e.g., `OudsButton`, `OudsTag`, `OudsTextInput`).

## Table of Contents

- [OudsTextInput](#textinput) — Single-line text field
- [OudsTextArea](#textarea) — Multi-line text field
- [OudsPasswordInput](#passwordinput) — Password field with visibility toggle
- [OudsPinCodeInput](#pincodeinput) — PIN code input (4 or 6 digits)

---

## TextInput

Two API variants for OudsTextInput: **state-based** (`textFieldState`) and **value-based** (`value` + `onValueChange`).  
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

Same two API variants as `OudsTextInput` (state-based / value-based).

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

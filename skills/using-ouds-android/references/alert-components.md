# OUDS Android — Alert Components

All components are in the `com.orange.ouds.core.component` package.  
All user-visible strings must use `stringResource(R.string.*)` — never hardcode.

> **Naming Convention:** All OUDS components follow the `Ouds*` prefix naming pattern (e.g., `OudsButton`, `OudsTag`, `OudsTextInput`).

## Table of Contents

- [AlertMessage](#alertmessage) — Full-featured alert with actions
- [InlineAlert](#inlinealert) — Compact inline alert

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

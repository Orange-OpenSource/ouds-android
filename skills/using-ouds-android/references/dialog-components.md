# OUDS Android — Dialog Components

All components are in the `com.orange.ouds.core.component` package.  
All user-visible strings must use `stringResource(R.string.*)` — never hardcode.

> **Naming Convention:** All OUDS components follow the `Ouds*` prefix naming pattern (e.g., `OudsButton`, `OudsTag`, `OudsTextInput`).

## Table of Contents

- [OudsAlertMessage](#alertmessage) — Full-featured alert with actions
- [OudsBottomSheetScaffold](#bottomsheetscaffold) — Standard bottom sheet scaffold
- [OudsInlineAlert](#inlinealert) — Compact inline alert
- [OudsModalBottomSheet](#modalbottomsheet) — Modal bottom sheet

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

## BottomSheetScaffold

**Standard bottom sheet** that co-exists with main screen content, allowing simultaneous interaction.  
**See also:** [OudsModalBottomSheet](#modalbottomsheet) for modal behavior that blocks main content.

```kotlin
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScreen() {
    val scaffoldState = rememberBottomSheetScaffoldState()

    OudsBottomSheetScaffold(
        sheetContent = {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(stringResource(R.string.sheet_title))
                Text(stringResource(R.string.sheet_content))
            }
        },
        sheetPeekHeight = 128.dp,
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Text(stringResource(R.string.main_content))
            }
        }
    )
}

// Without drag handle
OudsBottomSheetScaffold(
    sheetContent = { /* content */ },
    sheetDragHandle = false,
    content = { /* main content */ }
)

// With custom peek height
OudsBottomSheetScaffold(
    sheetContent = { /* content */ },
    sheetPeekHeight = 200.dp,
    content = { /* main content */ }
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

## ModalBottomSheet

**Modal bottom sheet** that appears in front of app content and blocks interaction until dismissed.  
**See also:** [OudsBottomSheetScaffold](#bottomsheetscaffold) for non-modal variant.

```kotlin
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScreen() {
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    Button(onClick = { showBottomSheet = true }) {
        Text(stringResource(R.string.show_sheet))
    }

    if (showBottomSheet) {
        OudsModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(stringResource(R.string.sheet_title))
                Text(stringResource(R.string.sheet_content))
                Button(onClick = { showBottomSheet = false }) {
                    Text(stringResource(R.string.close))
                }
            }
        }
    }
}

// Without drag handle
OudsModalBottomSheet(
    onDismissRequest = { /* dismiss */ },
    dragHandle = false
) {
    // Content
}

// With gestures disabled
OudsModalBottomSheet(
    onDismissRequest = { /* dismiss */ },
    sheetGesturesEnabled = false
) {
    // Content
}
```

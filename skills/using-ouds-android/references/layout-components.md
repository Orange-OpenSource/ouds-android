# OUDS Android — Layout Components

All components are in the `com.orange.ouds.core.component` package.  
All user-visible strings must use `stringResource(R.string.*)` — never hardcode.

> **Naming Convention:** All OUDS components follow the `Ouds*` prefix naming pattern (e.g., `OudsButton`, `OudsTag`, `OudsTextInput`).

## Table of Contents

- [OudsBottomSheetScaffold](#bottomsheetscaffold) — Standard bottom sheet scaffold
- [OudsColoredBox](#coloredbox) — Colored surface container
- [OudsDivider](#divider) — Horizontal and vertical dividers
- [OudsModalBottomSheet](#modalbottomsheet) — Modal bottom sheet

---

## ColoredBox

Creates a colored surface where child OUDS components automatically switch to their monochrome variant.

**Colors:** 24 values organized by category:
- **Background** (5): `BackgroundInverseHigh`, `BackgroundInverseLow`, `BackgroundPrimary`, `BackgroundSecondary`, `BackgroundTertiary`
- **Brand** (3): `BrandPrimary`, `BrandSecondary`, `BrandTertiary`
- **Overlay** (3): `OverlayDropdown`, `OverlayModal`, `OverlayTooltip`
- **Status** (8): `StatusAccentEmphasized`, `StatusAccentMuted`, `StatusInfoEmphasized`, `StatusInfoMuted`, `StatusNegativeEmphasized`, `StatusNegativeMuted`, `StatusPositiveEmphasized`, `StatusPositiveMuted`, `StatusWarningEmphasized`, `StatusWarningMuted`
- **Surface** (5): `SurfaceInverseHigh`, `SurfaceInverseLow`, `SurfacePrimary`, `SurfaceSecondary`, `SurfaceTertiary`

> **Note:** Not all colors are supported by all themes. Check `color.isSupported` before using a color in production code.

```kotlin
// Basic usage
OudsColoredBox(color = OudsColoredBoxColor.StatusInfoEmphasized) {
    // Child OUDS components adopt monochrome colors automatically
    OudsButton(label = stringResource(R.string.action), onClick = { })
    Text(
        text = stringResource(R.string.description),
        color = OudsTheme.colorScheme.content.default
    )
}

// Check if color is supported by current theme
val color = OudsColoredBoxColor.BrandPrimary
if (color.isSupported) {
    OudsColoredBox(color = color) {
        // Content
    }
}

// Different color categories
OudsColoredBox(color = OudsColoredBoxColor.BrandPrimary) { /* Brand colors */ }
OudsColoredBox(color = OudsColoredBoxColor.StatusPositiveEmphasized) { /* Status colors */ }
OudsColoredBox(color = OudsColoredBoxColor.SurfacePrimary) { /* Surface colors */ }
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

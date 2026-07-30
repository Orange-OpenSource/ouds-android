# OUDS Android — Action Components

All components are in the `com.orange.ouds.core.component` package.  
All user-visible strings must use `stringResource(R.string.*)` — never hardcode.

> **Naming Convention:** All OUDS components follow the `Ouds*` prefix naming pattern (e.g., `OudsButton`, `OudsTag`, `OudsTextInput`).

## Table of Contents

- [Button](#button) — Default and small buttons
- [FloatingActionButton](#floatingactionbutton) — Floating action button (FAB)
- [NavigationButton](#navigationbutton) — Navigation button with chevron
- [SmallButton](#smallbutton) — Small size button variant

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

## FloatingActionButton

**Sizes:** `OudsFloatingActionButton` (default) · `OudsSmallFloatingActionButton` · `OudsLargeFloatingActionButton` · `OudsExtendedFloatingActionButton` (with text)  
**Appearances:** `OudsFloatingActionButtonAppearance` — `Primary`, `Secondary`

```kotlin
// Icon only (default size)
OudsFloatingActionButton(
    icon = OudsFloatingActionButtonIcon(
        imageVector = Icons.Filled.Add,
        contentDescription = stringResource(R.string.add)
    ),
    onClick = { }
)

// Small size
OudsSmallFloatingActionButton(
    icon = OudsFloatingActionButtonIcon(
        imageVector = Icons.Filled.Edit,
        contentDescription = stringResource(R.string.edit)
    ),
    onClick = { }
)

// Large size
OudsLargeFloatingActionButton(
    icon = OudsFloatingActionButtonIcon(
        imageVector = Icons.Filled.FavoriteBorder,
        contentDescription = stringResource(R.string.favorite)
    ),
    onClick = { }
)

// Extended (with text)
OudsExtendedFloatingActionButton(
    text = stringResource(R.string.create),
    icon = OudsFloatingActionButtonIcon(imageVector = Icons.Filled.Add, contentDescription = ""),
    onClick = { }
)

// With secondary appearance
OudsFloatingActionButton(
    icon = OudsFloatingActionButtonIcon(
        imageVector = Icons.Filled.Settings,
        contentDescription = stringResource(R.string.settings)
    ),
    appearance = OudsFloatingActionButtonAppearance.Secondary,
    onClick = { }
)

// With untinted icon (multi-color)
OudsFloatingActionButton(
    icon = OudsFloatingActionButtonIcon(
        painter = myBrandPainter,
        contentDescription = stringResource(R.string.brand_action),
        tinted = false
    ),
    onClick = { }
)
```

---

## NavigationButton

**Chevrons:** `OudsNavigationButtonChevron` — `Next`, `Previous`  
**Appearances:** `OudsNavigationButtonAppearance` — `Default`, `Strong`, `Brand`, `Minimal`  
**Note:** `Brand` appearance is forbidden inside `OudsColoredBox`.  
Inside `OudsColoredBox`, the button automatically adopts its monochrome variant.

```kotlin
// Icon only (chevron)
OudsNavigationButton(
    chevron = OudsNavigationButtonChevron.Next,
    onClick = { }
)

// With label
OudsNavigationButton(
    label = stringResource(R.string.next),
    chevron = OudsNavigationButtonChevron.Next,
    onClick = { }
)

// Previous chevron
OudsNavigationButton(
    label = stringResource(R.string.previous),
    chevron = OudsNavigationButtonChevron.Previous,
    onClick = { }
)

// With appearance
OudsNavigationButton(
    label = stringResource(R.string.next),
    chevron = OudsNavigationButtonChevron.Next,
    appearance = OudsNavigationButtonAppearance.Strong,
    onClick = { }
)

// With loader
OudsNavigationButton(
    label = stringResource(R.string.next),
    chevron = OudsNavigationButtonChevron.Next,
    loader = OudsButtonLoader(progress = null), // indeterminate
    onClick = { }
)

// On colored background — colors adjusted automatically
OudsColoredBox(color = OudsColoredBoxColor.StatusInfoEmphasized) {
    OudsNavigationButton(
        label = stringResource(R.string.next),
        chevron = OudsNavigationButtonChevron.Next,
        onClick = { }
    )
}
```

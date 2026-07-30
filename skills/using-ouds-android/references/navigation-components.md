# OUDS Android — Navigation Components

All components are in the `com.orange.ouds.core.component` package.  
All user-visible strings must use `stringResource(R.string.*)` — never hardcode.

> **Naming Convention:** All OUDS components follow the `Ouds*` prefix naming pattern (e.g., `OudsButton`, `OudsTag`, `OudsTextInput`).

## Table of Contents

- [OudsLink](#link) — Text link with optional icon/chevron
- [OudsNavigationBar](#navigationbar) — Bottom navigation bar
- [OudsTopAppBar](#topappbar) — Top app bar with variants

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

# OUDS Android — Navigation Components

All components are in the `com.orange.ouds.core.component` package.  
All user-visible strings must use `stringResource(R.string.*)` — never hardcode.

> **Naming Convention:** All OUDS components follow the `Ouds*` prefix naming pattern (e.g., `OudsButton`, `OudsTag`, `OudsTextInput`).

## Table of Contents

- [OudsCardItem](#carditem) — Card item with label, description, and optional elements
- [OudsLink](#link) — Text link with optional icon/chevron
- [OudsListItem](#listitem) — List item with label, description, and optional elements
- [OudsNavigationBar](#navigationbar) — Bottom navigation bar
- [OudsSmallCardItem](#smallcarditem) — Compact card item variant
- [OudsSmallListItem](#smalllistitem) — Compact list item variant
- [OudsTopAppBar](#topappbar) — Top app bar with variants

---

## Link

**Indicators:** `OudsLinkIndicator` — `Next`, `Back`, `External`

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

// With indicator
OudsLink(
    label = stringResource(R.string.link_label),
    indicator = OudsLinkIndicator.Next,
    onClick = { }
)

// With external indicator
OudsLink(
    label = stringResource(R.string.link_label),
    indicator = OudsLinkIndicator.External,
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

## CardItem

**Note:** `@ExperimentalOudsApi` — API may evolve  
**Decorations:** `OudsListItemDecoration.Background(divider)`, `OudsListItemDecoration.Outlined`, `OudsListItemDecoration.None`  
**Indicators:** `OudsListItemIndicator.Next`, `OudsListItemIndicator.Previous`, `OudsListItemIndicator.External`

```kotlin
// Static card item (non-clickable)
OudsCardItem(
    label = "Hotel Paradise",
    description = "Luxury hotel in the city center",
    leading = OudsListItemLeading.Icon(
        imageVector = Icons.Outlined.LocationOn,
        contentDescription = "Location icon"
    ),
    trailing = OudsListItemTrailing.Text(label = "4.5★", style = OudsListItemTextStyle.LabelStrong)
)

// Navigation card item (clickable)
OudsCardItem(
    label = "View details",
    onClick = { /* Navigate forward */ },
    indicator = OudsListItemIndicator.Next,
    decoration = OudsListItemDecoration.Outlined
)

// With image
OudsCardItem(
    label = "Premium Suite",
    description = "Spacious room with panoramic view",
    leading = OudsListItemLeading.Image(
        painter = CheckerboardPainter,
        contentDescription = "Suite image",
        size = OudsListItemImageSize.Large,
        ratio = OudsListItemImageRatio.Square
    ),
    trailing = OudsListItemTrailing.Text(label = "€450/night", style = OudsListItemTextStyle.LabelStrong),
    decoration = OudsListItemDecoration.Outlined
)

// With all elements
OudsCardItem(
    overline = "Featured destination",
    label = "Paris, France",
    extraLabel = "Special offer",
    description = "Discover the city of lights with exclusive deals.",
    leading = OudsListItemLeading.Icon(
        imageVector = Icons.Outlined.Favorite,
        contentDescription = "Favorite icon"
    ),
    trailing = OudsListItemTrailing.Text(label = "From €299", style = OudsListItemTextStyle.LabelStrong),
    helperText = "Limited time offer - Book now!",
    boldLabel = true,
    decoration = OudsListItemDecoration.Background(divider = true)
)

// With untinted icon
OudsCardItem(
    label = "Wishlist",
    description = "Your favorite destinations",
    leading = OudsListItemLeading.Icon(
        painter = rememberRainbowHeartPainter(),
        contentDescription = "Wishlist icon",
        tinted = false
    ),
    decoration = OudsListItemDecoration.Outlined
)
```

---

## ListItem

**Note:** `@ExperimentalOudsApi` — API may evolve  
**Use case:** Stacked items with no spacing (unlike CardItem which has spacing)  
**Indicators:** `OudsListItemIndicator.Next`, `OudsListItemIndicator.Previous`, `OudsListItemIndicator.External`

```kotlin
// Static list item (non-clickable)
Column {
    OudsListItem(
        label = "Name",
        description = "John Doe",
        leading = OudsListItemLeading.Icon(
            imageVector = Icons.Outlined.Person,
            contentDescription = "Person icon"
        )
    )
    OudsListItem(
        label = "Email",
        description = "john.doe@example.com",
        leading = OudsListItemLeading.Icon(
            imageVector = Icons.Outlined.Email,
            contentDescription = "Email icon"
        )
    )
}

// Navigation list item (clickable)
Column {
    OudsListItem(
        label = "Back to previous screen",
        onClick = { /* Navigate back */ },
        indicator = OudsListItemIndicator.Previous
    )
    OudsListItem(
        label = "Go to next screen",
        onClick = { /* Navigate forward */ },
        indicator = OudsListItemIndicator.Next
    )
}

// With image
OudsListItem(
    label = "Product name",
    description = "Product description with details",
    leading = OudsListItemLeading.Image(
        painter = CheckerboardPainter,
        contentDescription = "Product image",
        size = OudsListItemImageSize.Large,
        ratio = OudsListItemImageRatio.Square
    ),
    trailing = OudsListItemTrailing.Text(label = "€29.99", style = OudsListItemTextStyle.Label)
)

// With all elements
OudsListItem(
    overline = "Overline text",
    label = "Main label",
    extraLabel = "Extra label",
    description = "This is a description that provides additional context.",
    leading = OudsListItemLeading.Icon(
        imageVector = Icons.Outlined.Favorite,
        contentDescription = "Favorite icon"
    ),
    trailing = OudsListItemTrailing.Text(label = "99+", style = OudsListItemTextStyle.Label),
    helperText = "Helper text appears below the item",
    boldLabel = true,
    background = true,
    divider = true
)

// With untinted icon
OudsListItem(
    label = "Favorites",
    description = "View your favorite items",
    leading = OudsListItemLeading.Icon(
        painter = rememberRainbowHeartPainter(),
        contentDescription = "Favorites icon",
        tinted = false
    )
)
```

---

## SmallCardItem

**Note:** `@ExperimentalOudsApi` — API may evolve  
**Compact variant** of CardItem (omits overline and extraLabel)  
**Decorations:** `OudsListItemDecoration.Background(divider)`, `OudsListItemDecoration.Outlined`, `OudsListItemDecoration.None`  
**Indicators:** `OudsListItemIndicator.Next`, `OudsListItemIndicator.Previous`, `OudsListItemIndicator.External`

```kotlin
// Static small card item
OudsSmallCardItem(
    label = "Notifications",
    description = "3 new alerts",
    leading = OudsSmallListItemLeading.Icon(
        imageVector = Icons.Outlined.Notifications,
        contentDescription = "Notifications icon"
    ),
    decoration = OudsListItemDecoration.Outlined
)

// Navigation small card item
OudsSmallCardItem(
    label = "Next",
    onClick = { /* Navigate forward */ },
    indicator = OudsListItemIndicator.Next,
    decoration = OudsListItemDecoration.Outlined
)

// With image
OudsSmallCardItem(
    label = "Special offer",
    description = "Limited time only",
    leading = OudsSmallListItemLeading.Image(
        painter = CheckerboardPainter,
        contentDescription = "Offer image",
        ratio = OudsListItemImageRatio.Square
    ),
    trailing = OudsSmallListItemTrailing.Text(label = "-50%", style = OudsListItemTextStyle.LabelStrong),
    decoration = OudsListItemDecoration.Outlined
)

// With all elements
OudsSmallCardItem(
    label = "Paris, France",
    description = "Discover the city of lights with exclusive deals.",
    leading = OudsSmallListItemLeading.Icon(
        imageVector = Icons.Outlined.Favorite,
        contentDescription = "Favorite icon"
    ),
    trailing = OudsSmallListItemTrailing.Text(label = "From €299", style = OudsListItemTextStyle.LabelStrong),
    helperText = "Limited time offer - Book now!",
    boldLabel = true,
    decoration = OudsListItemDecoration.Background(divider = true)
)

// With untinted icon
OudsSmallCardItem(
    label = "Featured",
    description = "Exclusive content",
    leading = OudsSmallListItemLeading.Icon(
        painter = rememberRainbowHeartPainter(),
        contentDescription = "Featured icon",
        tinted = false
    ),
    decoration = OudsListItemDecoration.Outlined
)
```

---

## SmallListItem

**Note:** `@ExperimentalOudsApi` — API may evolve  
**Compact variant** of ListItem (omits overline and extraLabel)  
**Use case:** Stacked items with no spacing  
**Indicators:** `OudsListItemIndicator.Next`, `OudsListItemIndicator.Previous`, `OudsListItemIndicator.External`

```kotlin
// Static small list item
Column {
    OudsSmallListItem(
        label = "Notifications",
        description = "Push notifications enabled",
        leading = OudsSmallListItemLeading.Icon(
            imageVector = Icons.Outlined.Notifications,
            contentDescription = "Notifications icon"
        )
    )
    OudsSmallListItem(
        label = "Settings",
        description = "App preferences",
        leading = OudsSmallListItemLeading.Icon(
            imageVector = Icons.Outlined.Settings,
            contentDescription = "Settings icon"
        )
    )
}

// Navigation small list item
Column {
    OudsSmallListItem(
        label = "Go back",
        onClick = { /* Navigate back */ },
        indicator = OudsListItemIndicator.Previous
    )
    OudsSmallListItem(
        label = "Continue",
        onClick = { /* Navigate forward */ },
        indicator = OudsListItemIndicator.Next
    )
}

// With image
OudsSmallListItem(
    label = "Compact view",
    description = "Quick access to content",
    leading = OudsSmallListItemLeading.Image(
        painter = CheckerboardPainter,
        contentDescription = "Content image",
        ratio = OudsListItemImageRatio.Square
    ),
    trailing = OudsSmallListItemTrailing.Text(label = "New", style = OudsListItemTextStyle.LabelStrong)
)

// With all elements
OudsSmallListItem(
    label = "Main label",
    description = "This is a description that provides additional context.",
    leading = OudsSmallListItemLeading.Icon(
        imageVector = Icons.Outlined.Favorite,
        contentDescription = "Favorite icon"
    ),
    trailing = OudsSmallListItemTrailing.Text(label = "99+", style = OudsListItemTextStyle.Label),
    helperText = "Helper text appears below the item",
    boldLabel = true,
    background = true,
    divider = true
)

// With untinted icon
OudsSmallListItem(
    label = "Premium features",
    description = "Unlock exclusive content",
    leading = OudsSmallListItemLeading.Icon(
        painter = rememberRainbowHeartPainter(),
        contentDescription = "Premium icon",
        tinted = false
    )
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

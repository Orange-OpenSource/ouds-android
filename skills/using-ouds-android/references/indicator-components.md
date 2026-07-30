# OUDS Android — Indicator Components

All components are in the `com.orange.ouds.core.component` package.  
All user-visible strings must use `stringResource(R.string.*)` — never hardcode.

> **Naming Convention:** All OUDS components follow the `Ouds*` prefix naming pattern (e.g., `OudsButton`, `OudsTag`, `OudsTextInput`).

## Table of Contents

- [Badge](#badge) — Count and status badges
- [CircularProgressIndicator](#circularprogressindicator) — Circular loading indicator
- [LinearProgressIndicator](#linearprogressindicator) — Linear loading indicator
- [Tag](#tag) — Status and category tags

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

## CircularProgressIndicator

**Statuses:** `OudsProgressIndicatorStatus` — `Accent` (default), `Neutral`  
**Variants:** Determinate (with progress value) · Indeterminate (loading animation)  
**Track:** Optional background track for better visibility

```kotlin
// Indeterminate (loading)
OudsCircularProgressIndicator()

// Determinate (with progress)
OudsCircularProgressIndicator(progress = { 0.75f })

// With status
OudsCircularProgressIndicator(
    progress = { 0.5f },
    status = OudsProgressIndicatorStatus.Neutral
)

// Without track (minimal)
OudsCircularProgressIndicator(
    progress = { 0.75f },
    track = false
)

// Custom size
OudsCircularProgressIndicator(
    modifier = Modifier.size(64.dp),
    progress = { 0.75f }
)
```

---

## LinearProgressIndicator

**Statuses:** `OudsProgressIndicatorStatus` — `Accent` (default), `Neutral`  
**Variants:** Determinate (with progress value) · Indeterminate (loading animation)  
**Track:** Optional background track for better visibility  
**Stop Indicator:** Optional end marker for accessibility (required if contrast < 3:1)

```kotlin
// Indeterminate (loading)
OudsLinearProgressIndicator(
    helperText = stringResource(R.string.loading)
)

// Determinate (with progress)
OudsLinearProgressIndicator(
    progress = { 0.75f },
    helperText = stringResource(R.string.loading_percent, 75)
)

// With status
OudsLinearProgressIndicator(
    progress = { 0.5f },
    status = OudsProgressIndicatorStatus.Neutral,
    helperText = stringResource(R.string.uploading)
)

// Without track (minimal)
OudsLinearProgressIndicator(
    progress = { 0.75f },
    track = false
)

// With stop indicator (for accessibility)
OudsLinearProgressIndicator(
    progress = { 0.75f },
    stopIndicator = true,
    helperText = stringResource(R.string.processing)
)

// Full width with helper text
OudsLinearProgressIndicator(
    modifier = Modifier.fillMaxWidth(),
    progress = { 0.75f },
    helperText = "Uploading file: document.pdf"
)
```

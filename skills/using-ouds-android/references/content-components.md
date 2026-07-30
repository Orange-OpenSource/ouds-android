# OUDS Android — Content Components

All components are in the `com.orange.ouds.core.component` package.  
All user-visible strings must use `stringResource(R.string.*)` — never hardcode.

> **Naming Convention:** All OUDS components follow the `Ouds*` prefix naming pattern (e.g., `OudsButton`, `OudsTag`, `OudsTextInput`).

## Table of Contents

- [OudsBulletList](#bulletlist) — Ordered, unordered, and bare lists

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

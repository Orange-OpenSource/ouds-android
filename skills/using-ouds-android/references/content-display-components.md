# OUDS Android — Content Display Components

All components are in the `com.orange.ouds.core.component` package.  
All user-visible strings must use `stringResource(R.string.*)` — never hardcode.

> **Naming Convention:** All OUDS components follow the `Ouds*` prefix naming pattern (e.g., `OudsButton`, `OudsTag`, `OudsTextInput`).

## Table of Contents

- [OudsBulletList](#bulletlist) — Ordered, unordered, and bare lists

---

## BulletList

**Types:** `OudsBulletListType.Unordered(brandColor: Boolean = true)`, `OudsBulletListType.Ordered`, `OudsBulletListType.Bare`  
**API:** DSL builder pattern with `item()` function

```kotlin
// Unordered (brand color by default)
OudsBulletList {
    item(label = "Milk")
    item(label = "Vegetables", subListType = OudsBulletListType.Unordered(brandColor = false)) {
        item(label = "Tomatoes")
        item(label = "Salad") {
            item(label = "Lettuce")
            item(label = "Arugula")
        }
    }
}

// Ordered
OudsBulletList(type = OudsBulletListType.Ordered) {
    item(label = "Prepare the ingredients")
    item(label = "Cook the pasta") {
        item(label = "Boil water in a large pot")
        item(label = "Add salt and then the pasta") {
            item(label = "Cook for 8-10 minutes")
            item(label = "Stir occasionally")
        }
    }
    item(label = "Drain the pasta and serve")
}

// Bare (no bullet)
OudsBulletList(type = OudsBulletListType.Bare) {
    item(label = "Event Planning")
    item(label = "Logistic Team") {
        item(label = "Venue Booking")
        item(label = "Catering")
    }
    item(label = "Communication Team") {
        item(label = "Invitations")
        item(label = "Social Media")
    }
}

// With annotated labels (strong text, links)
OudsBulletList {
    item(
        label = buildOudsAnnotatedBulletListLabel {
            withStrong { append("Important notice:") }
            append(" Read the following information carefully")
        }
    )
    item(
        label = buildOudsAnnotatedBulletListLabel {
            append("Product details available on our ")
            withLink(OudsLinkAnnotation.Url("https://example.com/products")) {
                append("website")
            }
        }
    )
}
```

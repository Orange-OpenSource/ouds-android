# OUDS Android — Foundation Components

Foundation components provide the typography primitives for building consistent text hierarchies across your app.

All components are in the `com.orange.ouds.core.component` package.  
All user-visible strings must use `stringResource(R.string.*)` — never hardcode.

> **Naming Convention:** All OUDS components follow the `Ouds*` prefix naming pattern (e.g., `OudsButton`, `OudsTag`, `OudsTextInput`).

## Table of Contents

- [OudsBodyText](#bodytext) — Paragraphs, descriptions, and everyday text content
- [OudsCodeText](#codetext) — Code snippets and technical content
- [OudsDisplayText](#displaytext) — Large, high-impact titles
- [OudsHeadingText](#headingtext) — Section headings and titles
- [OudsLabelText](#labeltext) — UI controls and interface labels

---

## BodyText

Body styles are designed for everyday text content such as paragraphs, descriptions, and informational messages. They prioritize readability and provide a comfortable reading experience across all screen sizes.

**Sizes:** `OudsBodyTextSize` — `Large`, `Medium` (default), `Small`  
**Weights:** `OudsTextWeight` — `Default` (default), `Moderate`, `Strong`

```kotlin
// Basic body text
OudsBodyText(
    modifier = Modifier.padding(OudsTheme.spaces.fixed.small),
    text = "Body",
    size = OudsBodyTextSize.Medium,
    weight = OudsTextWeight.Moderate
)

// Body text with rich formatting
val highlightColor = OudsTheme.colorScheme.content.brandPrimary
OudsBodyText(
    modifier = Modifier.padding(OudsTheme.spaces.fixed.small),
    text = buildOudsAnnotatedBodyText {
        withStrong {
            withColor(color = highlightColor) { append("Important update") }
        }
        append(", ")
        withStrikethrough {
            append("old information")
        }
        append(", see details ")
        withLink(link = OudsLinkAnnotation.Url("https://example.com/details")) { append("here") }
        append(".")
    },
    size = OudsBodyTextSize.Large
)
```

---

## CodeText

Code styles are designed for displaying code snippets, technical content, and monospace text. They use a monospace font family optimized for code readability.

```kotlin
// Basic code text
OudsCodeText(
    modifier = Modifier.padding(OudsTheme.spaces.fixed.small),
    text = "Code",
)

// Code text with colored formatting
OudsCodeText(
    modifier = Modifier.padding(OudsTheme.spaces.fixed.small),
    text = buildOudsAnnotatedCodeText {
        append("Code with ")
        withColor(color = OudsTheme.colorScheme.content.brandPrimary) { append("colored text") }
        append(".")
    }
)
```

---

## DisplayText

Display styles are the largest and most prominent typography option, designed for maximum visual impact. Use them for hero sections, landing pages, or major content introductions.

**Sizes:** `OudsDisplayTextSize` — `Large`, `Medium` (default), `Small`

```kotlin
// Basic display text
OudsDisplayText(
    modifier = Modifier.padding(OudsTheme.spaces.fixed.small),
    text = "Display",
    size = OudsDisplayTextSize.Medium
)

// Display text with highlighted content
val highlightColor = OudsTheme.colorScheme.content.brandPrimary
OudsDisplayText(
    modifier = Modifier.padding(OudsTheme.spaces.fixed.small),
    text = buildOudsAnnotatedDisplayText {
        append("Display with ")
        withColor(color = highlightColor) { append("highlighted text") }
    },
    size = OudsDisplayTextSize.Small
)
```

---

## HeadingText

Heading styles establish content hierarchy and structure. They help organize information and guide users through the interface.

**Sizes:** `OudsHeadingTextSize` — `ExtraLarge` (default), `Large`, `Medium`, `Small`  
**Note:** `Large` size supports an optional brand-colored marker for visual emphasis.

```kotlin
// Basic heading text
OudsHeadingText(
    modifier = Modifier.padding(OudsTheme.spaces.fixed.small),
    text = "Heading",
    size = OudsHeadingTextSize.Medium
)

// Large heading with marker (default)
OudsHeadingText(
    modifier = Modifier.padding(OudsTheme.spaces.fixed.small),
    text = "Heading",
    size = OudsHeadingTextSize.Large()
)

// Large heading without marker
OudsHeadingText(
    modifier = Modifier.padding(OudsTheme.spaces.fixed.small),
    text = "Heading",
    size = OudsHeadingTextSize.Large(marker = false)
)

// Heading with highlighted content
val highlightColor = OudsTheme.colorScheme.content.brandPrimary
OudsHeadingText(
    text = buildOudsAnnotatedHeadingText {
        append("Heading with ")
        withColor(highlightColor) { append("highlighted text") }
    },
    size = OudsHeadingTextSize.Large(marker = false)
)
```

---

## LabelText

Label styles are designed for UI controls and interface elements. They provide clear, concise text for buttons, form fields, and other interactive components.

**Sizes:** `OudsLabelTextSize` — `Large` (default), `Medium`, `Small`  
**Weights:** `OudsTextWeight` — `Default`, `Moderate`, `Strong` (default)

```kotlin
// Basic label text
OudsLabelText(
    modifier = Modifier.padding(OudsTheme.spaces.fixed.small),
    text = "Label",
    size = OudsLabelTextSize.Large,
    weight = OudsTextWeight.Strong
)

// Label with rich formatting
val highlightColor = OudsTheme.colorScheme.content.brandPrimary
OudsLabelText(
    modifier = Modifier.padding(OudsTheme.spaces.fixed.small),
    text = buildOudsAnnotatedLabelText {
        withStrong {
            withColor(color = highlightColor) { append("Important update") }
        }
        append(", ")
        withStrikethrough {
            append("old information")
        }
        append(", see details ")
        withLink(link = OudsLinkAnnotation.Url("https://example.com/details")) { append("here") }
        append(".")
    },
    size = OudsLabelTextSize.Large
)
```

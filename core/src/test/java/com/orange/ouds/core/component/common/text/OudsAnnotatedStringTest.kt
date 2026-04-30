/*
 * Software Name: OUDS Android
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
 */

package com.orange.ouds.core.component.common.text

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.intl.LocaleList
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class OudsAnnotatedStringTest {

    //region  OudsAnnotatedString tests

    @Test
    fun oudsAnnotatedString_textProperty_returnsPlainTextWithoutAnnotations() {
        val annotatedString = buildTestAnnotatedString {
            append("Password must be ")
            withStrong { append("at least 8 characters") }
            append(" long")
        }

        assertEquals("Password must be at least 8 characters long", annotatedString.text)
    }

    @Test
    fun oudsAnnotatedString_plusOperator_concatenatesTwoAnnotatedStrings() {
        val first = buildTestAnnotatedString {
            append("First part")
        }
        val second = buildTestAnnotatedString {
            append(" Second part")
        }

        val result = first.plus(second)

        assertEquals("First part Second part", result.text)
    }

    @Test
    fun oudsAnnotatedString_plusOperator_preservesStrongAnnotations() {
        val first = buildTestAnnotatedString {
            withStrong { append("Bold") }
        }

        val second = buildTestAnnotatedString {
            append(" normal")
        }

        val result = first.plus(second)

        assertEquals("Bold normal", result.text)
    }

    @Test
    fun oudsAnnotatedString_plusOperator_preservesLinkAnnotations() {
        val first = buildTestAnnotatedString {
            withLink(OudsLinkAnnotation.Url("https://example.com")) {
                append("Link")
            }
        }

        val second = buildTestAnnotatedString {
            append(" text")
        }

        val result = first.plus(second)

        assertEquals("Link text", result.text)
    }

    @Test
    fun oudsAnnotatedString_plusOperator_preservesAnnotationsFromBothStrings() {
        val first = buildTestAnnotatedString {
            withStrong { append("Bold") }
        }

        val second = buildTestAnnotatedString {
            append(" ")
            withLink(OudsLinkAnnotation.Url("https://example.com")) {
                append("link")
            }
        }

        val result = first.plus(second)

        assertEquals("Bold link", result.text)
    }

    @Test
    fun oudsAnnotatedString_toUpperCase_convertsTextToUpperCase() {
        val annotatedString = buildTestAnnotatedString {
            append("lowercase text")
        }

        val uppercase = annotatedString.toUpperCase()

        assertEquals("LOWERCASE TEXT", uppercase.text)
    }

    @Test
    fun oudsAnnotatedString_toLowerCase_convertsTextToLowerCase() {
        val annotatedString = buildTestAnnotatedString {
            append("UPPERCASE TEXT")
        }

        val lowercase = annotatedString.toLowerCase()

        assertEquals("uppercase text", lowercase.text)
    }

    @Test
    fun oudsAnnotatedString_capitalize_capitalizesFirstCharacter() {
        val annotatedString = buildTestAnnotatedString {
            append("lowercase start")
        }

        val capitalized = annotatedString.capitalize()

        assertEquals("Lowercase start", capitalized.text)
    }

    @Test
    fun oudsAnnotatedString_decapitalize_decapitalizesFirstCharacter() {
        val annotatedString = buildTestAnnotatedString {
            append("Uppercase start")
        }

        val decapitalized = annotatedString.decapitalize()

        assertEquals("uppercase start", decapitalized.text)
    }

    @Test
    fun oudsAnnotatedString_equals_returnsTrueForSameContent() {
        val first = buildTestAnnotatedString {
            append("Same text")
        }
        val second = buildTestAnnotatedString {
            append("Same text")
        }

        assertEquals(first, second)
    }

    @Test
    fun oudsAnnotatedString_equals_returnsFalseForDifferentContent() {
        val first = buildTestAnnotatedString {
            append("First text")
        }
        val second = buildTestAnnotatedString {
            append("Second text")
        }

        assertNotEquals(first, second)
    }

    @Test
    fun oudsAnnotatedString_hashCode_isConsistentForSameContent() {
        val first = buildTestAnnotatedString {
            append("Same text")
        }
        val second = buildTestAnnotatedString {
            append("Same text")
        }

        assertEquals(first.hashCode(), second.hashCode())
    }

    @Test
    fun oudsAnnotatedString_toString_returnsStringRepresentation() {
        val annotatedString = buildTestAnnotatedString {
            append("Test text")
        }

        assertEquals("Test text", annotatedString.toString())
    }

    @Test
    fun oudsAnnotatedString_charSequenceInterfaceLength_works() {
        val annotatedString = buildTestAnnotatedString {
            append("Hello")
        }

        assertEquals(5, annotatedString.length)
    }

    @Test
    fun oudsAnnotatedString_charSequenceInterfaceGet_works() {
        val annotatedString = buildTestAnnotatedString {
            append("Hello")
        }

        assertEquals('H', annotatedString[0])
        assertEquals('e', annotatedString[1])
        assertEquals('l', annotatedString[2])
        assertEquals('l', annotatedString[3])
        assertEquals('o', annotatedString[4])
    }

    @Test
    fun oudsAnnotatedString_charSequenceInterfaceSubSequence_works() {
        val annotatedString = buildTestAnnotatedString {
            append("Hello World")
        }

        val subSequence = annotatedString.subSequence(0, 5)

        assertEquals("Hello", subSequence.toString())
    }

    @Test
    fun oudsAnnotatedString_toUpperCaseWithLocaleList_works() {
        val annotatedString = buildTestAnnotatedString {
            append("test")
        }

        val localeList = LocaleList("en-US")
        val uppercase = annotatedString.toUpperCase(localeList)

        assertEquals("TEST", uppercase.text)
    }

    @Test
    fun oudsAnnotatedString_toLowerCaseWithLocaleList_works() {
        val annotatedString = buildTestAnnotatedString {
            append("TEST")
        }

        val localeList = LocaleList("en-US")
        val lowercase = annotatedString.toLowerCase(localeList)

        assertEquals("test", lowercase.text)
    }

    @Test
    fun oudsAnnotatedString_capitalizeWithLocaleList_works() {
        val annotatedString = buildTestAnnotatedString {
            append("test")
        }

        val localeList = LocaleList("en-US")
        val capitalized = annotatedString.capitalize(localeList)

        assertEquals("Test", capitalized.text)
    }

    @Test
    fun oudsAnnotatedString_decapitalizeWithLocaleList_works() {
        val annotatedString = buildTestAnnotatedString {
            append("Test")
        }

        val localeList = LocaleList("en-US")
        val decapitalized = annotatedString.decapitalize(localeList)

        assertEquals("test", decapitalized.text)
    }

    //endregion

    //region OudsAnnotatedString.Builder tests

    @Test
    fun oudsAnnotatedStringBuilder_stringConstructor_initializesText() {
        val builder = TestAnnotatedString.Builder("Initial text")
        val annotatedString = builder.toAnnotatedString()

        assertEquals("Initial text", annotatedString.text)
    }

    @Test
    fun oudsAnnotatedStringBuilder_annotatedStringConstructor_copiesContent() {
        val original = buildTestAnnotatedString {
            append("Original")
        }

        val builder = TestAnnotatedString.Builder(original)
        builder.append(" Copy")
        val annotatedString = builder.toAnnotatedString()

        assertEquals("Original Copy", annotatedString.text)
    }

    @Test
    fun oudsAnnotatedStringBuilder_appendChar_appendsSingleCharacter() {
        val builder = TestAnnotatedString.Builder()
        builder.append('A')
        builder.append('B')
        builder.append('C')
        val annotatedString = builder.toAnnotatedString()

        assertEquals("ABC", annotatedString.text)
    }

    @Test
    fun oudsAnnotatedStringBuilder_appendString_appendsText() {
        val builder = TestAnnotatedString.Builder()
        builder.append("Hello")
        builder.append(" ")
        builder.append("World")
        val annotatedString = builder.toAnnotatedString()

        assertEquals("Hello World", annotatedString.text)
    }

    @Test
    fun oudsAnnotatedStringBuilder_appendCharSequence_appendsText() {
        val builder = TestAnnotatedString.Builder()
        val charSequence: CharSequence = "Test"
        builder.append(charSequence)
        val annotatedString = builder.toAnnotatedString()

        assertEquals("Test", annotatedString.text)
    }

    @Test
    fun oudsAnnotatedStringBuilder_appendCharSequenceWithRange_appendsSubstring() {
        val builder = TestAnnotatedString.Builder()
        val charSequence: CharSequence = "Hello World"
        builder.append(charSequence, 0, 5)
        val annotatedString = builder.toAnnotatedString()

        assertEquals("Hello", annotatedString.text)
    }

    @Test
    fun oudsAnnotatedStringBuilder_appendAnnotatedString_preservesContent() {
        val existing = buildTestAnnotatedString {
            append("Existing")
        }

        val builder = TestAnnotatedString.Builder()
        builder.append("New ")
        builder.append(existing)
        val annotatedString = builder.toAnnotatedString()

        assertEquals("New Existing", annotatedString.text)
    }

    @Test
    fun oudsAnnotatedStringBuilder_appendAnnotatedStringWithRange_appendsSubstring() {
        val existing = buildTestAnnotatedString {
            append("Hello World")
        }

        val builder = TestAnnotatedString.Builder()
        builder.append(existing, 0, 5)
        val annotatedString = builder.toAnnotatedString()

        assertEquals("Hello", annotatedString.text)
    }

    //endregion

    //region OudsAnnotatedString.LinkBuilder tests

    @Test
    fun oudsAnnotatedStringLinkBuilder_withLink_addsLinkAnnotation() {
        val annotatedString = buildTestAnnotatedString {
            append("Visit our ")
            withLink(OudsLinkAnnotation.Url("https://example.com")) {
                append("website")
            }
        }

        assertEquals("Visit our website", annotatedString.text)
    }

    @Test
    fun oudsAnnotatedStringLinkBuilder_addLinkWithUrl_addsLinkAnnotationToRange() {
        val builder = TestAnnotatedString.Builder()
        builder.append("Visit our website")
        builder.addLink(OudsLinkAnnotation.Url("https://example.com"), 10, 17) // "website"
        val annotatedString = builder.toAnnotatedString()

        assertEquals("Visit our website", annotatedString.text)
    }

    @Test
    fun oudsAnnotatedStringLinkBuilder_addLinkWithClickable_addsLinkAnnotationToRange() {
        val builder = TestAnnotatedString.Builder()
        builder.append("Click details for more")
        builder.addLink(OudsLinkAnnotation.Clickable("show-details"), 6, 13) // "details"
        val annotatedString = builder.toAnnotatedString()

        assertEquals("Click details for more", annotatedString.text)
    }

    @Test
    fun oudsAnnotatedStringLinkBuilder_addLinkMultiple_addsMultipleNonOverlappingLinkAnnotations() {
        val builder = TestAnnotatedString.Builder()
        builder.append("Read terms and privacy policy")
        builder.addLink(OudsLinkAnnotation.Url("https://example.com/terms"), 5, 10) // "terms"
        builder.addLink(OudsLinkAnnotation.Url("https://example.com/privacy"), 15, 29) // "privacy policy"
        val annotatedString = builder.toAnnotatedString()

        assertEquals("Read terms and privacy policy", annotatedString.text)
    }

    @Test
    fun oudsAnnotatedStringLinkBuilder_pushLinkAndPop_addsLinkAnnotation() {
        val builder = TestAnnotatedString.Builder()
        builder.append("Visit our ")
        builder.pushLink(OudsLinkAnnotation.Url("https://example.com"))
        builder.append("website")
        builder.pop()
        builder.append(" for more")
        val annotatedString = builder.toAnnotatedString()

        assertEquals("Visit our website for more", annotatedString.text)
    }

    @Test
    fun oudsAnnotatedStringLinkBuilder_pushLinkWithIndexAndPop_addsLinkAnnotation() {
        val builder = TestAnnotatedString.Builder()
        builder.append("Click ")
        val index = builder.pushLink(OudsLinkAnnotation.Clickable("action"))
        builder.append("here")
        builder.pop(index)
        builder.append(" to continue")
        val annotatedString = builder.toAnnotatedString()

        assertEquals("Click here to continue", annotatedString.text)
    }

    @Test
    fun oudsAnnotatedStringLinkBuilder_nestStrongWithinLink_succeeds() {
        val annotatedString = buildTestAnnotatedString {
            append("Visit ")
            withLink(OudsLinkAnnotation.Url("https://example.com")) {
                append("our ")
                withStrong {
                    append("official")
                }
                append(" website")
            }
        }

        assertEquals("Visit our official website", annotatedString.text)
    }

    //endregion

    //region OudsAnnotatedString.StrongBuilder tests

    @Test
    fun oudsAnnotatedStringStrongBuilder_withStrong_addsStrongAnnotation() {
        val annotatedString = buildTestAnnotatedString {
            append("Normal ")
            withStrong {
                append("bold")
            }
            append(" text")
        }

        assertEquals("Normal bold text", annotatedString.text)
    }

    @Test
    fun oudsAnnotatedStringStrongBuilder_addStrong_addsStrongAnnotationToRange() {
        val builder = TestAnnotatedString.Builder()
        builder.append("Normal bold text")
        builder.addStrong(7, 11) // "bold"
        val annotatedString = builder.toAnnotatedString()

        assertEquals("Normal bold text", annotatedString.text)
    }

    @Test
    fun oudsAnnotatedStringStrongBuilder_addStrongMultiple_addsMultipleNonOverlappingAnnotations() {
        val builder = TestAnnotatedString.Builder()
        builder.append("First bold second bold")
        builder.addStrong(6, 10) // "bold"
        builder.addStrong(18, 22) // "bold"
        val annotatedString = builder.toAnnotatedString()

        assertEquals("First bold second bold", annotatedString.text)
    }

    @Test
    fun oudsAnnotatedStringStrongBuilder_pushStrongAndPop_addsStrongAnnotation() {
        val builder = TestAnnotatedString.Builder()
        builder.append("Normal ")
        builder.pushStrong()
        builder.append("bold")
        builder.pop()
        builder.append(" text")
        val annotatedString = builder.toAnnotatedString()

        assertEquals("Normal bold text", annotatedString.text)
    }

    @Test
    fun oudsAnnotatedStringStrongBuilder_pushStrongWithIndexAndPop_addsStrongAnnotation() {
        val builder = TestAnnotatedString.Builder()
        builder.append("Normal ")
        val index = builder.pushStrong()
        builder.append("bold")
        builder.pop(index)
        builder.append(" text")
        val annotatedString = builder.toAnnotatedString()

        assertEquals("Normal bold text", annotatedString.text)
    }

    @Test
    fun oudsAnnotatedStringStrongBuilder_nestedPushStrongAndPop_worksCorrectly() {
        val builder = TestAnnotatedString.Builder()
        builder.append("Start ")
        builder.pushStrong()
        builder.append("outer ")
        builder.pushStrong()
        builder.append("inner")
        builder.pop()
        builder.append(" outer")
        builder.pop()
        builder.append(" end")
        val annotatedString = builder.toAnnotatedString()

        assertEquals("Start outer inner outer end", annotatedString.text)
    }

    @Test
    fun oudsAnnotatedStringStrongBuilder_nestLinkWithinStrong_succeeds() {
        val annotatedString = buildTestAnnotatedString {
            withStrong {
                append("Important: ")
                withLink(OudsLinkAnnotation.Url("https://example.com")) {
                    append("read this")
                }
            }
        }

        assertEquals("Important: read this", annotatedString.text)
    }

    //endregion
}

private class TestAnnotatedString internal constructor(annotatedString: AnnotatedString) : OudsAnnotatedString<TestAnnotatedString>(annotatedString) {

    class Builder(capacity: Int = 16) : OudsAnnotatedString.Builder<TestAnnotatedString>(capacity, TestAnnotatedString::class.java), StrongBuilder,
        LinkBuilder {

        constructor(text: String) : this() {
            append(text)
        }

        constructor(text: TestAnnotatedString) : this() {
            append(text)
        }

        override fun addStrong(start: Int, end: Int) = addStrongImpl(start, end)

        override fun pushStrong(): Int = pushStrongImpl()

        override fun addLink(url: OudsLinkAnnotation.Url, start: Int, end: Int) = addLinkImpl(url, start, end)

        override fun addLink(clickable: OudsLinkAnnotation.Clickable, start: Int, end: Int) = addLinkImpl(clickable, start, end)

        override fun pushLink(link: OudsLinkAnnotation): Int = pushLinkImpl(link)
    }
}

private fun buildTestAnnotatedString(builder: (TestAnnotatedString.Builder).() -> Unit): TestAnnotatedString {
    return buildOudsAnnotatedString<TestAnnotatedString, TestAnnotatedString.Builder>(builder)
}

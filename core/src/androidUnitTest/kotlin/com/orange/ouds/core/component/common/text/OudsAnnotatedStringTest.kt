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
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.text.withAnnotation
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
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
        // Verify that both strong and link annotations are preserved
        val strongAnnotations = result.getStrongAnnotations()
        assertEquals(1, strongAnnotations.size)
        assertEquals(0, strongAnnotations[0].start)
        assertEquals(4, strongAnnotations[0].end) // "Bold" has 4 characters
        val linkAnnotations = result.getLinkAnnotations()
        assertEquals(1, linkAnnotations.size)
        assertEquals(5, linkAnnotations[0].start) // "Bold " has 5 characters
        assertEquals(9, linkAnnotations[0].end) // "Bold link" has 9 characters
        assertTrue(linkAnnotations[0].item is LinkAnnotation.Url)
    }

    @Test
    fun oudsAnnotatedString_toUpperCase_convertsTextToUpperCase() {
        val annotatedString = buildTestAnnotatedString {
            append("lowercase ")
            withStrong { append("bold") }
        }

        val uppercase = annotatedString.toUpperCase()

        assertEquals("LOWERCASE BOLD", uppercase.text)
        // Verify that strong annotation is preserved after case transformation
        val strongAnnotations = uppercase.getStrongAnnotations()
        assertEquals(1, strongAnnotations.size)
        assertEquals(10, strongAnnotations[0].start) // "LOWERCASE " has 10 characters
        assertEquals(14, strongAnnotations[0].end) // "LOWERCASE BOLD" has 14 characters
    }

    @Test
    fun oudsAnnotatedString_toLowerCase_convertsTextToLowerCase() {
        val annotatedString = buildTestAnnotatedString {
            append("UPPERCASE ")
            withLink(OudsLinkAnnotation.Url("https://example.com")) {
                append("WEBSITE")
            }
        }

        val lowercase = annotatedString.toLowerCase()

        assertEquals("uppercase website", lowercase.text)
        // Verify that link annotation is preserved after case transformation
        val linkAnnotations = lowercase.getLinkAnnotations()
        assertEquals(1, linkAnnotations.size)
        assertEquals(10, linkAnnotations[0].start) // "uppercase " has 10 characters
        assertEquals(17, linkAnnotations[0].end) // "uppercase website" has 17 characters
        assertTrue(linkAnnotations[0].item is LinkAnnotation.Url)
    }

    @Test
    fun oudsAnnotatedString_capitalize_capitalizesFirstCharacter() {
        val annotatedString = buildTestAnnotatedString {
            withStrong { append("lowercase") }
            append(" start")
        }

        val capitalized = annotatedString.capitalize()

        assertEquals("Lowercase start", capitalized.text)
        // Verify that strong annotation is preserved after capitalization
        val strongAnnotations = capitalized.getStrongAnnotations()
        assertEquals(1, strongAnnotations.size)
        assertEquals(0, strongAnnotations[0].start)
        assertEquals(9, strongAnnotations[0].end) // "Lowercase" has 9 characters
    }

    @Test
    fun oudsAnnotatedString_decapitalize_decapitalizesFirstCharacter() {
        val annotatedString = buildTestAnnotatedString {
            withStrong { append("Uppercase") }
            append(" start")
        }

        val decapitalized = annotatedString.decapitalize()

        assertEquals("uppercase start", decapitalized.text)
        // Verify that strong annotation is preserved after decapitalization
        val strongAnnotations = decapitalized.getStrongAnnotations()
        assertEquals(1, strongAnnotations.size)
        assertEquals(0, strongAnnotations[0].start)
        assertEquals(9, strongAnnotations[0].end) // "uppercase" has 9 characters
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
            append("Existing ")
            withStrong { append("bold") }
        }

        val builder = TestAnnotatedString.Builder()
        builder.append("New ")
        builder.append(existing)
        val annotatedString = builder.toAnnotatedString()

        assertEquals("New Existing bold", annotatedString.text)
        // Verify that annotations from the appended string are preserved
        val strongAnnotations = annotatedString.getStrongAnnotations()
        assertEquals(1, strongAnnotations.size)
        assertEquals(13, strongAnnotations[0].start) // "New Existing " has 13 characters
        assertEquals(17, strongAnnotations[0].end) // "New Existing bold" has 17 characters
    }

    @Test
    fun oudsAnnotatedStringBuilder_appendAnnotatedStringWithRange_appendsSubstring() {
        val existing = buildTestAnnotatedString {
            append("Hello ")
            withStrong { append("bold") }
            append(" World")
        }

        val builder = TestAnnotatedString.Builder()
        builder.append(existing, 0, 11) // "Hello bold " (11 characters, includes the strong annotation)
        val annotatedString = builder.toAnnotatedString()

        assertEquals("Hello bold ", annotatedString.text)
        // Verify that annotations within the appended range are preserved
        val strongAnnotations = annotatedString.getStrongAnnotations()
        assertEquals(1, strongAnnotations.size)
        assertEquals(6, strongAnnotations[0].start) // "Hello " has 6 characters
        assertEquals(10, strongAnnotations[0].end) // "Hello bold" has 10 characters
    }

    @Test
    fun oudsAnnotatedStringBuilder_appendAnnotatedString_filtersSupportedAnnotations() {
        // Create an AnnotatedString with various font weights
        val annotatedString = buildAnnotatedString {
            // Font weights less than Bold - should be filtered out
            withStyle(SpanStyle(fontWeight = FontWeight.Light)) {
                append("Light ")
            }
            withStyle(SpanStyle(fontWeight = FontWeight.Normal)) {
                append("Normal ")
            }
            withStyle(SpanStyle(fontWeight = FontWeight.Medium)) {
                append("Medium ")
            }
            withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) {
                append("SemiBold ")
            }

            // Font weights >= Bold - should be converted to strong annotations
            withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                append("Bold ")
            }
            withStyle(SpanStyle(fontWeight = FontWeight.ExtraBold)) {
                append("ExtraBold ")
            }
            withStyle(SpanStyle(fontWeight = FontWeight.Black)) {
                append("Black ")
            }

            // Link annotation - should be preserved
            withLink(LinkAnnotation.Url("https://example.com")) {
                append("Link ")
            }

            // String annotation - should be filtered out
            withAnnotation("", "String") {
                append("String")
            }
        }

        // Append to OudsAnnotatedString builder
        val builder = TestAnnotatedString.Builder()
        builder.append("Prefix ")
        builder.append(annotatedString)
        val result = builder.toAnnotatedString()

        // Verify text is fully preserved
        assertEquals("Prefix Light Normal Medium SemiBold Bold ExtraBold Black Link String", result.text)

        // Verify strong annotations (only Bold, ExtraBold, Black should be converted)
        val strongAnnotations = result.getStrongAnnotations()
        assertEquals(3, strongAnnotations.size)

        // Verify Bold annotation at correct position
        assertEquals(36, strongAnnotations[0].start) // "Prefix Light Normal Medium SemiBold " = 36 chars
        assertEquals(41, strongAnnotations[0].end) // "Bold " = 5 chars

        // Verify ExtraBold annotation at correct position
        assertEquals(41, strongAnnotations[1].start) // After "Bold "
        assertEquals(51, strongAnnotations[1].end) // "ExtraBold " = 10 chars

        // Verify Black annotation at correct position
        assertEquals(51, strongAnnotations[2].start) // After "ExtraBold "
        assertEquals(57, strongAnnotations[2].end) // "Black " = 6 chars

        // Verify link annotation is preserved
        val linkAnnotations = result.getLinkAnnotations()
        assertEquals(1, linkAnnotations.size)
        assertEquals(57, linkAnnotations[0].start) // After "Black "
        assertEquals(62, linkAnnotations[0].end) // "Link " = 5 chars
        assertTrue(linkAnnotations[0].item is LinkAnnotation.Url)

        // Verify other annotations are filtered out
        assertEquals(0, result.spanStyles.size)
        assertEquals(strongAnnotations.size, result.getStringAnnotations().size)
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
        // Verify link annotation is present
        val linkAnnotations = annotatedString.getLinkAnnotations()
        assertEquals(1, linkAnnotations.size)
        assertEquals(10, linkAnnotations[0].start) // "Visit our " has 10 characters
        assertEquals(17, linkAnnotations[0].end) // "Visit our website" has 17 characters
        assertTrue(linkAnnotations[0].item is LinkAnnotation.Url)
    }

    @Test
    fun oudsAnnotatedStringLinkBuilder_addLinkWithUrl_addsLinkAnnotationToRange() {
        val builder = TestAnnotatedString.Builder()
        builder.append("Visit our website")
        builder.addLink(OudsLinkAnnotation.Url("https://example.com"), 10, 17) // "website"
        val annotatedString = builder.toAnnotatedString()

        assertEquals("Visit our website", annotatedString.text)
        // Verify link annotation is present at the specified range
        val linkAnnotations = annotatedString.getLinkAnnotations()
        assertEquals(1, linkAnnotations.size)
        assertEquals(10, linkAnnotations[0].start)
        assertEquals(17, linkAnnotations[0].end)
        assertTrue(linkAnnotations[0].item is LinkAnnotation.Url)
    }

    @Test
    fun oudsAnnotatedStringLinkBuilder_addLinkWithClickable_addsLinkAnnotationToRange() {
        val builder = TestAnnotatedString.Builder()
        builder.append("Click details for more")
        builder.addLink(OudsLinkAnnotation.Clickable("show-details"), 6, 13) // "details"
        val annotatedString = builder.toAnnotatedString()

        assertEquals("Click details for more", annotatedString.text)
        // Verify clickable link annotation is present at the specified range
        val linkAnnotations = annotatedString.getLinkAnnotations()
        assertEquals(1, linkAnnotations.size)
        assertEquals(6, linkAnnotations[0].start)
        assertEquals(13, linkAnnotations[0].end)
        assertTrue(linkAnnotations[0].item is LinkAnnotation.Clickable)
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
        // Verify link annotation is present
        val linkAnnotations = annotatedString.getLinkAnnotations()
        assertEquals(1, linkAnnotations.size)
        assertEquals(10, linkAnnotations[0].start) // "Visit our " has 10 characters
        assertEquals(17, linkAnnotations[0].end) // "Visit our website" has 17 characters
        assertTrue(linkAnnotations[0].item is LinkAnnotation.Url)
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
        // Verify clickable link annotation is present
        val linkAnnotations = annotatedString.getLinkAnnotations()
        assertEquals(1, linkAnnotations.size)
        assertEquals(6, linkAnnotations[0].start) // "Click " has 6 characters
        assertEquals(10, linkAnnotations[0].end) // "Click here" has 10 characters
        assertTrue(linkAnnotations[0].item is LinkAnnotation.Clickable)
    }

    @Test
    fun oudsAnnotatedStringLinkBuilder_nestedPushLinkAndPop_worksCorrectly() {
        val builder = TestAnnotatedString.Builder()
        builder.append("Start ")
        builder.pushLink(OudsLinkAnnotation.Url("https://example.com/outer"))
        builder.append("outer ")
        builder.pushLink(OudsLinkAnnotation.Url("https://example.com/inner"))
        builder.append("inner")
        builder.pop()
        builder.append(" outer")
        builder.pop()
        builder.append(" end")
        val annotatedString = builder.toAnnotatedString()

        assertEquals("Start outer inner outer end", annotatedString.text)
        // Verify nested link annotations are present
        val linkAnnotations = annotatedString.getLinkAnnotations()
        assertEquals(2, linkAnnotations.size)
        assertEquals(6, linkAnnotations[0].start) // "Start " has 6 characters, outer starts
        assertEquals(23, linkAnnotations[0].end) // "Start outer inner outer" has 23 characters
        assertTrue(linkAnnotations[0].item is LinkAnnotation.Url)
        assertEquals(12, linkAnnotations[1].start) // "Start outer " has 12 characters, inner starts
        assertEquals(17, linkAnnotations[1].end) // "Start outer inner" has 17 characters
        assertTrue(linkAnnotations[1].item is LinkAnnotation.Url)
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
        // Verify both link and strong annotations are present
        val linkAnnotations = annotatedString.getLinkAnnotations()
        assertEquals(1, linkAnnotations.size)
        assertEquals(6, linkAnnotations[0].start) // "Visit " has 6 characters
        assertEquals(26, linkAnnotations[0].end) // "Visit our official website" has 26 characters
        assertTrue(linkAnnotations[0].item is LinkAnnotation.Url)

        val strongAnnotations = annotatedString.getStrongAnnotations()
        assertEquals(1, strongAnnotations.size)
        assertEquals(10, strongAnnotations[0].start) // "Visit our " has 10 characters
        assertEquals(18, strongAnnotations[0].end) // "Visit our official" has 18 characters
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
        // Verify strong annotation is present
        val strongAnnotations = annotatedString.getStrongAnnotations()
        assertEquals(1, strongAnnotations.size)
        assertEquals(7, strongAnnotations[0].start) // "Normal " has 7 characters
        assertEquals(11, strongAnnotations[0].end) // "Normal bold" has 11 characters
    }

    @Test
    fun oudsAnnotatedStringStrongBuilder_addStrong_addsStrongAnnotationToRange() {
        val builder = TestAnnotatedString.Builder()
        builder.append("Normal bold text")
        builder.addStrong(7, 11) // "bold"
        val annotatedString = builder.toAnnotatedString()

        assertEquals("Normal bold text", annotatedString.text)
        // Verify strong annotation is present at the specified range
        val strongAnnotations = annotatedString.getStrongAnnotations()
        assertEquals(1, strongAnnotations.size)
        assertEquals(7, strongAnnotations[0].start)
        assertEquals(11, strongAnnotations[0].end)
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
        // Verify strong annotation is present
        val strongAnnotations = annotatedString.getStrongAnnotations()
        assertEquals(1, strongAnnotations.size)
        assertEquals(7, strongAnnotations[0].start) // "Normal " has 7 characters
        assertEquals(11, strongAnnotations[0].end) // "Normal bold" has 11 characters
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
        // Verify strong annotation is present
        val strongAnnotations = annotatedString.getStrongAnnotations()
        assertEquals(1, strongAnnotations.size)
        assertEquals(7, strongAnnotations[0].start) // "Normal " has 7 characters
        assertEquals(11, strongAnnotations[0].end) // "Normal bold" has 11 characters
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
        // Verify nested strong annotations are present
        val strongAnnotations = annotatedString.getStrongAnnotations()
        assertEquals(2, strongAnnotations.size)
        assertEquals(6, strongAnnotations[0].start) // "Start " has 6 characters, outer starts
        assertEquals(23, strongAnnotations[0].end) // "Start outer inner outer" has 23 characters
        assertEquals(12, strongAnnotations[1].start) // "Start outer " has 12 characters, inner starts
        assertEquals(17, strongAnnotations[1].end) // "Start outer inner" has 17 characters
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
        // Verify both strong and link annotations are present
        val strongAnnotations = annotatedString.getStrongAnnotations()
        assertEquals(1, strongAnnotations.size)
        assertEquals(0, strongAnnotations[0].start)
        assertEquals(20, strongAnnotations[0].end) // "Important: read this" has 20 characters

        val linkAnnotations = annotatedString.getLinkAnnotations()
        assertEquals(1, linkAnnotations.size)
        assertEquals(11, linkAnnotations[0].start) // "Important: " has 11 characters
        assertEquals(20, linkAnnotations[0].end) // "Important: read this" has 20 characters
        assertTrue(linkAnnotations[0].item is LinkAnnotation.Url)
    }

    //endregion
}

private class TestAnnotatedString(private val annotatedString: AnnotatedString) : OudsAnnotatedString<TestAnnotatedString>(annotatedString) {

    val spanStyles: List<AnnotatedString.Range<SpanStyle>> = annotatedString.spanStyles

    fun getLinkAnnotations(): List<AnnotatedString.Range<LinkAnnotation>> = with(annotatedString) { getLinkAnnotations(0, length) }

    fun getStringAnnotations(): List<AnnotatedString.Range<String>> = with(annotatedString) { getStringAnnotations(0, length) }

    fun getStrongAnnotations(): List<AnnotatedString.Range<String>> = getStringAnnotations().filter { it.item == StrongAnnotation }

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

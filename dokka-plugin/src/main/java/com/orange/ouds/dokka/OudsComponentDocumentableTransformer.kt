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

package com.orange.ouds.dokka

import org.jetbrains.dokka.model.DAnnotation
import org.jetbrains.dokka.model.DClass
import org.jetbrains.dokka.model.DClasslike
import org.jetbrains.dokka.model.DEnum
import org.jetbrains.dokka.model.DFunction
import org.jetbrains.dokka.model.DInterface
import org.jetbrains.dokka.model.DModule
import org.jetbrains.dokka.model.DObject
import org.jetbrains.dokka.model.DPackage
import org.jetbrains.dokka.model.DParameter
import org.jetbrains.dokka.model.DProperty
import org.jetbrains.dokka.model.DTypeAlias
import org.jetbrains.dokka.model.Documentable
import org.jetbrains.dokka.model.SourceSetDependent
import org.jetbrains.dokka.model.WithChildren
import org.jetbrains.dokka.model.doc.A
import org.jetbrains.dokka.model.doc.B
import org.jetbrains.dokka.model.doc.Big
import org.jetbrains.dokka.model.doc.BlockQuote
import org.jetbrains.dokka.model.doc.Br
import org.jetbrains.dokka.model.doc.Caption
import org.jetbrains.dokka.model.doc.Cite
import org.jetbrains.dokka.model.doc.CodeBlock
import org.jetbrains.dokka.model.doc.CodeInline
import org.jetbrains.dokka.model.doc.CustomDocTag
import org.jetbrains.dokka.model.doc.Dd
import org.jetbrains.dokka.model.doc.Description
import org.jetbrains.dokka.model.doc.Dfn
import org.jetbrains.dokka.model.doc.Dir
import org.jetbrains.dokka.model.doc.Div
import org.jetbrains.dokka.model.doc.Dl
import org.jetbrains.dokka.model.doc.DocTag
import org.jetbrains.dokka.model.doc.DocumentationLink
import org.jetbrains.dokka.model.doc.DocumentationNode
import org.jetbrains.dokka.model.doc.Dt
import org.jetbrains.dokka.model.doc.Em
import org.jetbrains.dokka.model.doc.Font
import org.jetbrains.dokka.model.doc.Footer
import org.jetbrains.dokka.model.doc.Frame
import org.jetbrains.dokka.model.doc.FrameSet
import org.jetbrains.dokka.model.doc.H1
import org.jetbrains.dokka.model.doc.H2
import org.jetbrains.dokka.model.doc.H3
import org.jetbrains.dokka.model.doc.H4
import org.jetbrains.dokka.model.doc.H5
import org.jetbrains.dokka.model.doc.H6
import org.jetbrains.dokka.model.doc.Head
import org.jetbrains.dokka.model.doc.Header
import org.jetbrains.dokka.model.doc.HorizontalRule
import org.jetbrains.dokka.model.doc.Html
import org.jetbrains.dokka.model.doc.I
import org.jetbrains.dokka.model.doc.IFrame
import org.jetbrains.dokka.model.doc.Img
import org.jetbrains.dokka.model.doc.Index
import org.jetbrains.dokka.model.doc.Input
import org.jetbrains.dokka.model.doc.Li
import org.jetbrains.dokka.model.doc.Link
import org.jetbrains.dokka.model.doc.Listing
import org.jetbrains.dokka.model.doc.Main
import org.jetbrains.dokka.model.doc.Menu
import org.jetbrains.dokka.model.doc.Meta
import org.jetbrains.dokka.model.doc.Nav
import org.jetbrains.dokka.model.doc.NoFrames
import org.jetbrains.dokka.model.doc.NoScript
import org.jetbrains.dokka.model.doc.Ol
import org.jetbrains.dokka.model.doc.P
import org.jetbrains.dokka.model.doc.Pre
import org.jetbrains.dokka.model.doc.Script
import org.jetbrains.dokka.model.doc.Section
import org.jetbrains.dokka.model.doc.Small
import org.jetbrains.dokka.model.doc.Span
import org.jetbrains.dokka.model.doc.Strikethrough
import org.jetbrains.dokka.model.doc.Strong
import org.jetbrains.dokka.model.doc.Sub
import org.jetbrains.dokka.model.doc.Sup
import org.jetbrains.dokka.model.doc.TBody
import org.jetbrains.dokka.model.doc.TFoot
import org.jetbrains.dokka.model.doc.THead
import org.jetbrains.dokka.model.doc.Table
import org.jetbrains.dokka.model.doc.Td
import org.jetbrains.dokka.model.doc.Text
import org.jetbrains.dokka.model.doc.Th
import org.jetbrains.dokka.model.doc.Title
import org.jetbrains.dokka.model.doc.Tr
import org.jetbrains.dokka.model.doc.Tt
import org.jetbrains.dokka.model.doc.U
import org.jetbrains.dokka.model.doc.Ul
import org.jetbrains.dokka.model.doc.Var
import org.jetbrains.dokka.model.firstMemberOfTypeOrNull
import org.jetbrains.dokka.model.withDescendants
import org.jetbrains.dokka.plugability.DokkaContext
import org.jetbrains.dokka.transformers.documentation.DocumentableTransformer

class OudsComponentDocumentableTransformer(val context: DokkaContext) : DocumentableTransformer {

    override fun invoke(original: DModule, context: DokkaContext): DModule = original.transform() as DModule

    private fun <T : Documentable> T.transform(): Documentable {
        return when (this) {
            is DModule -> copy(packages = packages.map { it.transform() as DPackage })
            is DPackage -> copy(
                classlikes = classlikes.map { it.transform() as DClasslike },
                functions = functions.map { it.transform() as DFunction },
                properties = properties.map { it.transform() as DProperty },
                typealiases = typealiases.map { it.transform() as DTypeAlias }
            )
            is DClass -> copy(
                documentation = replaceDesignDocTags(),
                classlikes = classlikes.map { it.transform() as DClasslike },
                functions = functions.map { it.transform() as DFunction },
                properties = properties.map { it.transform() as DProperty }
            )
            is DEnum -> copy(
                documentation = replaceDesignDocTags(),
                classlikes = classlikes.map { it.transform() as DClasslike },
                functions = functions.map { it.transform() as DFunction },
                properties = properties.map { it.transform() as DProperty }
            )
            is DInterface -> copy(
                documentation = replaceDesignDocTags(),
                classlikes = classlikes.map { it.transform() as DClasslike },
                functions = functions.map { it.transform() as DFunction },
                properties = properties.map { it.transform() as DProperty }
            )
            is DObject -> copy(
                documentation = replaceDesignDocTags(),
                classlikes = classlikes.map { it.transform() as DClasslike },
                functions = functions.map { it.transform() as DFunction },
                properties = properties.map { it.transform() as DProperty }
            )
            is DTypeAlias -> copy(documentation = replaceDesignDocTags())
            is DAnnotation -> copy(
                documentation = replaceDesignDocTags(),
                classlikes = classlikes.map { it.transform() as DClasslike },
                functions = functions.map { it.transform() as DFunction },
                properties = properties.map { it.transform() as DProperty }
            )
            is DFunction -> copy(documentation = replaceDesignDocTags())
            is DProperty -> copy(documentation = replaceDesignDocTags())
            is DParameter -> copy(documentation = replaceDesignDocTags())
            else -> this
        }
    }

    private fun Documentable.replaceDesignDocTags(): SourceSetDependent<DocumentationNode> {
        val designVersionRegex = "Design version: (.*)".toRegex()
        val designGuidelinesRegex = "Design guidelines: (.*)".toRegex()

        return documentation.mapValues { (_, node) ->
            val nodeChildren = node.children.map { tagWrapper ->
                if (tagWrapper is Description) {
                    val version = designVersionRegex.find(tagWrapper.root)?.groupValues?.getOrNull(1)
                    val guidelinesBlockquote = tagWrapper.root.firstMemberOfTypeOrNull<BlockQuote> { designGuidelinesRegex.find(it) != null }
                    val guidelinesAnchor = guidelinesBlockquote?.firstMemberOfTypeOrNull<A>()
                    val guidelinesText = guidelinesAnchor?.firstMemberOfTypeOrNull<Text>()?.body
                    val guidelinesHref = guidelinesAnchor?.params["href"]
                    val designDocTag = if (version != null && guidelinesText != null && guidelinesHref != null) {
                        getDesignDocTag(version, guidelinesText, guidelinesHref)
                    } else {
                        null
                    }

                    val tagWrapperRoot = tagWrapper.root
                        .removeDescendant { designVersionRegex.find(it) != null }
                        .removeDescendant { designGuidelinesRegex.find(it) != null }
                        .run { if (designDocTag != null) addChild(designDocTag) else this }

                    tagWrapper.copy(root = tagWrapperRoot)
                } else {
                    tagWrapper
                }
            }
            node.copy(children = nodeChildren)
        }
    }

    private fun getDesignDocTag(version: String, guidelinesText: String, guidelinesHref: String): DocTag {
        val guidelinesBody = "<a href=\"$guidelinesHref\" class=\"external\" target=\"_blank\">$guidelinesText</a>"
        val guidelinesParams = mapOf("content-type" to "html")

        return Div(
            listOf(
                H4(listOf(Text("Design"))),
                Table(
                    listOf(
                        Tr(
                            listOf(
                                Td(listOf(Text("Guidelines"))),
                                Td(listOf(Text(guidelinesBody, params = guidelinesParams)))
                            )
                        ),
                        Tr(
                            listOf(
                                Td(listOf(Text("Version"))),
                                Td(listOf(Text(version)))
                            )
                        )
                    )
                )
            )
        )
    }

    private fun <T : DocTag> T.addChild(child: DocTag): T {
        return genericCopy(children + child)
    }

    private fun <T : DocTag> T.removeDescendant(predicate: (DocTag) -> Boolean): T {
        val filteredChildren = children.filterNot(predicate).map { it.removeDescendant(predicate) }
        return genericCopy(filteredChildren)
    }

    private fun <T : DocTag> T.genericCopy(children: List<DocTag>): T {
        @Suppress("UNCHECKED_CAST")
        return when (this) {
            is A -> copy(children = children)
            is B -> copy(children = children)
            is Big -> copy(children = children)
            is BlockQuote -> copy(children = children)
            Br -> this
            is Caption -> copy(children = children)
            is Cite -> copy(children = children)
            is CodeBlock -> copy(children = children)
            is CodeInline -> copy(children = children)
            is CustomDocTag -> copy(children = children)
            is Dd -> copy(children = children)
            is Dfn -> copy(children = children)
            is Dir -> copy(children = children)
            is Div -> copy(children = children)
            is Dl -> copy(children = children)
            is DocumentationLink -> copy(children = children)
            is Dt -> copy(children = children)
            is Em -> copy(children = children)
            is Font -> copy(children = children)
            is Footer -> copy(children = children)
            is Frame -> copy(children = children)
            is FrameSet -> copy(children = children)
            is H1 -> copy(children = children)
            is H2 -> copy(children = children)
            is H3 -> copy(children = children)
            is H4 -> copy(children = children)
            is H5 -> copy(children = children)
            is H6 -> copy(children = children)
            is Head -> copy(children = children)
            is Header -> copy(children = children)
            HorizontalRule -> this
            is Html -> copy(children = children)
            is I -> copy(children = children)
            is IFrame -> copy(children = children)
            is Img -> copy(children = children)
            is Index -> copy(children = children)
            is Input -> copy(children = children)
            is Li -> copy(children = children)
            is Link -> copy(children = children)
            is Listing -> copy(children = children)
            is Main -> copy(children = children)
            is Menu -> copy(children = children)
            is Meta -> copy(children = children)
            is Nav -> copy(children = children)
            is NoFrames -> copy(children = children)
            is NoScript -> copy(children = children)
            is Ol -> copy(children = children)
            is P -> copy(children = children)
            is Pre -> copy(children = children)
            is Script -> copy(children = children)
            is Section -> copy(children = children)
            is Small -> copy(children = children)
            is Span -> copy(children = children)
            is Strikethrough -> copy(children = children)
            is Strong -> copy(children = children)
            is Sub -> copy(children = children)
            is Sup -> copy(children = children)
            is TBody -> copy(children = children)
            is TFoot -> copy(children = children)
            is THead -> copy(children = children)
            is Table -> copy(children = children)
            is Td -> copy(children = children)
            is Text -> copy(children = children)
            is Th -> copy(children = children)
            is Title -> copy(children = children)
            is Tr -> copy(children = children)
            is Tt -> copy(children = children)
            is U -> copy(children = children)
            is Ul -> copy(children = children)
            is Var -> copy(children = children)
        } as T
    }

    private fun Regex.find(docTag: DocTag, startIndex: Int = 0): MatchResult? {
        return docTag.withDescendants()
            .filterIsInstance<Text>()
            .firstNotNullOfOrNull { find(it.body, startIndex) }
    }

    inline fun <reified T> WithChildren<WithChildren<*>>.firstMemberOfTypeOrNull(predicate: (T) -> Boolean): T? where T : WithChildren<*> {
        return withDescendants().filterIsInstance<T>().firstOrNull(predicate)
    }
}
